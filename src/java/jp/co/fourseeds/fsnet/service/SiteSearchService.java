package jp.co.fourseeds.fsnet.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.SiteSearchFormBean;
import jp.co.fourseeds.fsnet.beans.SiteSearchResultBean;
import jp.co.fourseeds.fsnet.common.ConstantContainer;
import jp.co.fourseeds.fsnet.common.util.CommonUtil;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.SiteSearchDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import jp.co.common.frame.service.BaseBusinessService;
import jp.co.common.frame.util.prop.FsPropertyUtil;

/**
 *  サイト内検索機能サービス実装。
 * 
 * @author NTS
 * @version 1.0.0 : 2015.11.14 新規作成
 */
@Component
public class SiteSearchService extends BaseBusinessService{

	private final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private SiteSearchDao siteSearchDao;

	public void getSearchResults(SiteSearchFormBean searchBean) throws Exception {
		LoginUserBean loginUser = getLoginUserBean();
		//Fessの検索結果格納リスト
		List<String> resultListByFess = new ArrayList<String>();
		//ログインユーザの閲覧可能の検索結果格納リスト（画面全部表示情報含む)
		List<SiteSearchResultBean> resultListByUser = new ArrayList<SiteSearchResultBean>();
		//オラクルの検索結果格納リスト
		List<String> resultListByDB = new ArrayList<String>();
		
		//現在のシステム時刻を取得する
		long time = System.currentTimeMillis();
		
		String keyWord = StringUtil.nullToBlank(searchBean.getKeyWord()).trim();
		//検索キーワードは全角に変換
		keyWord = StringUtil.hankakuKatakanaToZenkakuKatakana(keyWord);
		
		//■■■ログインユーザの閲覧可能の検索結果
		resultListByUser = siteSearchDao.getUserPageList(loginUser, searchBean.getSort());

		//DBの検索対象項目：１）タイトル名　２）コンテンツ内容　３）サイト内検索キー　４）添付ファイル名前
		//「PKG_COMMON.FN_KNACHG()」方法：半角小文字To全角大文字
		List titlekeyWordList = changeOracleKeyWord(keyWord, "PKG_COMMON.FN_KNACHG(Upper(T1.TITLE))", loginUser);
		// 用既存の方法「PKG_COMMON.FN_KNACHG(Upper())」変換、文字列の長さを超える、用「TO_MULTI_BYTE()」方法変換
		List contentkeyWordList = changeOracleKeyWord(keyWord, "T1.CONTENT", loginUser);
		List pagekeyWordList = changeOracleKeyWord(keyWord, "PKG_COMMON.FN_KNACHG(Upper(T1.PAGE_KEY))", loginUser);
		List attachmetkeyWordList = changeOracleKeyWord(keyWord, "PKG_COMMON.FN_KNACHG(Upper(T2.ATTACHMENT_NAME))", loginUser);
		// notキーワード検索以外の文字列
		String titlekeyWord = (String)titlekeyWordList.get(0);
		String contentkeyWord = (String)contentkeyWordList.get(0);
		String pagekeyWord = (String)pagekeyWordList.get(0);
		String attachmetkeyWord = (String)attachmetkeyWordList.get(0);
		// notキーワード検索の文字列
		String titleNotkeyWord = (String)titlekeyWordList.get(1);
		String contentNotkeyWord = (String)contentkeyWordList.get(1);
		String pagekeyNotWord = (String)pagekeyWordList.get(1);
		String attachmetNotkeyWord = (String)attachmetkeyWordList.get(1);
		String oraclekeyWord = "";
		String attachmetKeyWord1 = "";
		if(titleNotkeyWord != null && !"".equals(titleNotkeyWord)) {
			oraclekeyWord = "((" + titlekeyWord + ") OR (" + contentkeyWord + ") OR (" + pagekeyWord + ") OR (" + attachmetkeyWord
					+ ")) AND ((" + titleNotkeyWord 
					+ ") AND ((" + contentNotkeyWord  + ") OR (T1.CONTENT IS NULL))"
					+ " AND ((" + pagekeyNotWord + ") OR (T1.PAGE_KEY IS NULL))"
					+ " AND ((" + attachmetNotkeyWord + ") OR (T2.ATTACHMENT_NAME IS NULL))" + ")";
			attachmetKeyWord1 = attachmetkeyWord + " AND " + attachmetNotkeyWord;	
		} else {
			oraclekeyWord = "(" + titlekeyWord + ") OR (" + contentkeyWord + ") OR (" + pagekeyWord + ") OR (" + attachmetkeyWord + ")";
			attachmetKeyWord1 = attachmetkeyWord;
		}
		// オラクル検索のキーワード
		
		try{
			//■■■オラクルの検索結果
			resultListByDB = siteSearchDao.getOraclePageList(oraclekeyWord, attachmetkeyWord);
		}catch(Exception e){
			e.printStackTrace();
			CommonUtil.writeActionLog(loginUser.getUserId(),"オラクル異常メッセージ：" + oraclekeyWord);
		}

		//■■■Fessの検索結果
		
		Pattern pattern = Pattern.compile("\\b(and|or|not)\\b|");
		Matcher matcher = pattern.matcher(keyWord);
		
		StringBuffer sb = new StringBuffer(); 
		boolean isMatch = matcher.find();
		while(isMatch) { 
			matcher.appendReplacement(sb, matcher.group(0).toUpperCase());
			isMatch = matcher.find();
		}
		matcher.appendTail(sb);
		
		resultListByFess = (List<String>) searchByFess(sb.toString(),loginUser.getUserId());
		
		//画面表示用リスト
		List<SiteSearchResultBean> result = new ArrayList<SiteSearchResultBean>();
		HashSet<String> uniqueMatchSet = new HashSet<String>();
		boolean flag = false;
		SiteSearchResultBean beforeBean = null;
		for(int i = 0; i < resultListByUser.size(); i++){
			SiteSearchResultBean currentBean = resultListByUser.get(i);

			String fileName = StringUtil.nullToBlank(currentBean.getAttachmentFileUrl());
			String fileSuffix = "";
			if(fileName!=null && !"".equals(fileName)) {
				try {
					fileSuffix = fileName.substring(fileName.lastIndexOf(".")).trim();
					fileName = fileName.substring(fileName.lastIndexOf("/") + 1,
							fileName.lastIndexOf("."));
				} catch (Exception e) {
				}
				String pageId = fileName.substring(0, 13);
				String index = fileName.substring(13, fileName.length());
				currentBean.setAttachmentFileUrl("/fsnet/pageView_openAttachment.do?formBean.pageId=" + pageId + "&index=" + index +"&suffix=" + fileSuffix);
			}

			if (flag) {
				if (!currentBean.getPageId().equals(beforeBean.getPageId())) {
					if (!uniqueMatchSet.contains(beforeBean.getPageId())) {
						result.add(beforeBean);
						//ページID格納（重複コンテンツ外す判断用)
						uniqueMatchSet.add(beforeBean.getPageId());
					}
					flag = false;
				}
			}
			
			//動的添付ファイルヒット、あるいは、添付ファイルなし動的コンテンツヒット(オラクルから)
			if (resultListByDB.contains(currentBean.getMatchKey())) {
				result.add(currentBean);
				//ページID格納（重複コンテンツ外す判断用)
				uniqueMatchSet.add(currentBean.getPageId());
			} else {
				//動的添付ファイルヒット、あるいは、添付ファイルなし動的コンテンツヒットあるいは静的コンテンツ(Fessから)
				if (resultListByFess.contains(currentBean.getMatchKey())) {
					result.add(currentBean);
					//ページID格納（重複コンテンツ外す判断用)
					uniqueMatchSet.add(currentBean.getPageId());
				} 
				else {
					//動的添付ヒット(オラクルから)
					if (resultListByDB.contains(currentBean.getPageId() + "|")) {
						if (!uniqueMatchSet.contains(currentBean.getPageId())) {
							currentBean.setAttachmentName("");
							if (i == resultListByUser.size() - 1) {
								result.add(currentBean);
								//ページID格納（重複コンテンツ外す判断用)
								uniqueMatchSet.add(currentBean.getPageId());
							} else {
								flag = true;
								beforeBean = currentBean;
							}
						}
					} else {
						//動的添付ヒット(Fessから)
						if (resultListByFess.contains(currentBean.getPageId() + "|")) {
							if (!uniqueMatchSet.contains(currentBean.getPageId())) {
								currentBean.setAttachmentName("");
								if (i == resultListByUser.size() - 1) {
									result.add(currentBean);
									//ページID格納（重複コンテンツ外す判断用)
									uniqueMatchSet.add(currentBean.getPageId());
								} else {
									flag = true;
									beforeBean = currentBean;
								}
							}
						}
					}
				}
			}
		}
		//検索のページ号
		int searchPageNo = Integer.parseInt(searchBean.getSearchPageNo());
		//検索の件数
		int perPageNo = Integer.parseInt(searchBean.getPerPageNo());
		//現在のページの開始件数
		int j = (searchPageNo - 1) * perPageNo;
		if (result.size() < searchPageNo * perPageNo) {
			perPageNo = result.size();
		} else {
			perPageNo = perPageNo * searchPageNo;
		}
		//現在のページの配置先の取得
		for (int i = j; i < perPageNo; i++) {
			SiteSearchResultBean infobean = result.get(i);
			SiteSearchResultBean getHaitiBean = getStrHaitisaki(infobean, loginUser);
			infobean.setHaitisaki(getHaitiBean.getHaitisaki());
			infobean.setExistFlag(getHaitiBean.getExistFlag());
		}

		//検索結果リスト
		searchBean.setSearchPageList(result);

		//検索秒数を取得する
		if (result.size() != 0) {
			double usedTimeDouble = Double.parseDouble(String.valueOf(System
					.currentTimeMillis()
					- time));
			usedTimeDouble /= 1000;
			searchBean.setUsedTime(usedTimeDouble + "");
		}
	}
	
	//配置先の長さが同じ場合、page_id大きな取得
	private SiteSearchResultBean checkPageIdOrder(SiteSearchResultBean haitiInfoBeanNew, SiteSearchResultBean haitiInfoBeanP) {
		String pageIdnew[] = haitiInfoBeanNew.getStrPageKey().split("|");
		String pageIdP[] = haitiInfoBeanP.getStrPageKey().split("|");
		SiteSearchResultBean rtnHaitiInfoBean = new SiteSearchResultBean();
		boolean breakFlag = false;
		for (int i = 0; i < pageIdP.length; i++) {
			if (pageIdnew[i].compareTo(pageIdP[i]) == 0) {
				continue;
			} else if (pageIdnew[i].compareTo(pageIdP[i]) > 0) {
				rtnHaitiInfoBean = haitiInfoBeanNew;
				breakFlag = true;
				break;
			} else if (pageIdnew[i].compareTo(pageIdP[i]) < 0) {
				rtnHaitiInfoBean = haitiInfoBeanP;
				breakFlag = true;
				break;
			}
		}
		if (!breakFlag) {
			rtnHaitiInfoBean = haitiInfoBeanNew;
		}
		return rtnHaitiInfoBean;
	}
	
	private SiteSearchResultBean getStrHaitisaki(SiteSearchResultBean infobean, LoginUserBean loginUser) {
		SiteSearchResultBean returnBean = new SiteSearchResultBean();
		List<SiteSearchResultBean> listHaitisaki = new ArrayList<SiteSearchResultBean>();
		SiteSearchResultBean locationBean = new SiteSearchResultBean();
		//page_idの配置先取得
		locationBean.setPageId(infobean.getPageId());
		//配置先
		locationBean.setHaitisaki("-[" + infobean.getTitle() + "]");
		//配置先の長さが同じ場合、比較でkey
		locationBean.setStrPageKey(infobean.getPageId() + "|");
		//最短パス
		locationBean.setCountPass(1);
		locationBean.setPPageId(infobean.getPPageId());
		locationBean.setJituFlag(false);
		listHaitisaki.add(locationBean);
		//配置先取得
		getListHaitisaki(listHaitisaki, 0, loginUser);

		//配置先
		String haitisaki = "";
		String strKey = "";
		//配置先リスト内の空のデータを削除します
		for (int i = 0; i < listHaitisaki.size(); i++) {
			SiteSearchResultBean nullHaitiInfoBean = listHaitisaki.get(i);
			if ("".equals(nullHaitiInfoBean.getHaitisaki())) {
				listHaitisaki.remove(i);
				i--;
			}
		}
		
		if (listHaitisaki.size() > 0) {
			int countPass = 0;
			//最短パスチェック(countPassペアごと比較、countPass最小値を取得)
			for (int i = 0; i < listHaitisaki.size(); i++) {
				SiteSearchResultBean haitiInfoBeanP = listHaitisaki.get(i);
				if (i == 0) {
					countPass = haitiInfoBeanP.getCountPass();
				} else {
					//比較最短パス
					if (haitiInfoBeanP.getCountPass() < countPass) {
						countPass = haitiInfoBeanP.getCountPass();
					}
				}
			}
			//配置先の長さが同じ場合、page_id大きな取得
			SiteSearchResultBean haitiInfoBeanNew = new SiteSearchResultBean();
			for (int i = 0; i < listHaitisaki.size(); i++) {
				SiteSearchResultBean haitiInfoBeanP = listHaitisaki.get(i);
				if (countPass == haitiInfoBeanP.getCountPass()) {
					if (haitiInfoBeanNew.getStrPageKey() == null) {
						haitiInfoBeanNew = haitiInfoBeanP;
					} else {
						//pageID降順チェック
						haitiInfoBeanNew = checkPageIdOrder(haitiInfoBeanNew, haitiInfoBeanP);
					}
				}
			}
			haitisaki = haitiInfoBeanNew.getHaitisaki();
			strKey = haitiInfoBeanNew.getStrPageKey();
		} else {
			//上記取得していない
			SiteSearchResultBean jitubean = getHaiTiSaki(infobean.getPageId(), loginUser);
			haitisaki = jitubean.getHaitisaki();
			strKey = jitubean.getStrPageKey();
		}
		
		String pId = FsPropertyUtil.getStringProperty("contents.pageId");
		if(StringUtil.isBlank(strKey) || strKey.indexOf(pId) > -1) {
			returnBean.setExistFlag("1");
		} else {
			returnBean.setExistFlag("0");
		}
		returnBean.setHaitisaki(haitisaki);
		return returnBean;
	}
	
	//配置先取得
	private void getListHaitisaki(List<SiteSearchResultBean> listHaitisaki, int count, LoginUserBean loginUser) {
		if (count > 100) {
			listHaitisaki = new ArrayList<SiteSearchResultBean>();
			return;
		}
		List<SiteSearchResultBean> listHaitisakiNew = new ArrayList<SiteSearchResultBean>();
		boolean isLoopFlag = false;
		for (int i = 0; i < listHaitisaki.size(); i++) {
			SiteSearchResultBean locationBean = listHaitisaki.get(i);
			if ("".equals(locationBean.getPageId()) || "0".equals(locationBean.getPageId())) {
				listHaitisakiNew.add(locationBean);
				continue;
			}
			//再帰呼び出すフラグ
			isLoopFlag = true;
			
			//リンク配置先の取得
			List<SiteSearchResultBean> linkPageInfoList = new ArrayList<SiteSearchResultBean>();
			if (!locationBean.isJituFlag()) {
				linkPageInfoList = siteSearchDao.getLinkPageInfo(locationBean, loginUser);
			}
			
			//リンク配置先存在しない、実配置先の取得
			if (linkPageInfoList.size() == 0) {
				//親コンテンツは「HOME」
				if("0".equals(locationBean.getPPageId())){
					SiteSearchResultBean haitiInfoBean1 = new SiteSearchResultBean();
					haitiInfoBean1.setPageId("");
					haitiInfoBean1.setHaitisaki("[HOME]" + locationBean.getHaitisaki());
					haitiInfoBean1.setStrPageKey(locationBean.getStrPageKey() + "0|");
					haitiInfoBean1.setCountPass(locationBean.getCountPass());
					listHaitisakiNew.add(haitiInfoBean1);
					continue;
				}
				//実配置先の取得
				List<SiteSearchResultBean> jituPageInfoList = siteSearchDao.getJituPageInfo(locationBean, loginUser);
				
				//配置先取得しない、nullを戻る
				if (jituPageInfoList.size() == 0) {
					//実配置先はnull
					SiteSearchResultBean haitiInfoBean1 = new SiteSearchResultBean();
					haitiInfoBean1.setPageId("");
					haitiInfoBean1.setHaitisaki("");
					haitiInfoBean1.setStrPageKey("");
					haitiInfoBean1.setCountPass(0);
					
					listHaitisakiNew.add(haitiInfoBean1);
				} else {
					for (int j = 0; j < jituPageInfoList.size(); j++) {
						SiteSearchResultBean haitiInfoBean2 = (SiteSearchResultBean) jituPageInfoList.get(j);
						SiteSearchResultBean haitiInfoBean1 = new SiteSearchResultBean();
						haitiInfoBean1.setPageId(haitiInfoBean2.getPageId());
						haitiInfoBean1.setPPageId(haitiInfoBean2.getPPageId());
						haitiInfoBean1.setHaitisaki("-[" + haitiInfoBean2.getTitle() + "]" + locationBean.getHaitisaki());
						haitiInfoBean1.setStrPageKey(locationBean.getStrPageKey() + haitiInfoBean2.getPageId() + "|");
						haitiInfoBean1.setCountPass(locationBean.getCountPass() + 1);
						haitiInfoBean1.setLinkTitle("");
						haitiInfoBean1.setJituFlag(true);
						listHaitisakiNew.add(haitiInfoBean1);
					}
				}
			} else {
				for (int j = 0; j < linkPageInfoList.size(); j++) {
					SiteSearchResultBean haitiInfoBean2 = (SiteSearchResultBean) linkPageInfoList.get(j);
					
					SiteSearchResultBean haitiInfoBean1 = new SiteSearchResultBean();
					haitiInfoBean1.setPageId(haitiInfoBean2.getPageId());
					haitiInfoBean1.setPPageId(haitiInfoBean2.getPPageId());
					String haitisaki = locationBean.getHaitisaki();
					//リンク配置先のタイトル名を削除する
					if(locationBean.getLinkTitle() != null && !"".equals(locationBean.getLinkTitle())) {
						String linkTitle = locationBean.getLinkTitle();
						haitisaki = haitisaki.substring(haitisaki.indexOf(linkTitle) + linkTitle.length(), haitisaki.length());
					}
					haitiInfoBean1.setLinkTitle("-[" + haitiInfoBean2.getTitle() + "]");
					haitiInfoBean1.setHaitisaki("-[" + haitiInfoBean2.getTitle() + "]" + "-[\"" + haitiInfoBean2.getLinkName() + "\"]" + haitisaki);
					haitiInfoBean1.setStrPageKey(locationBean.getStrPageKey() + haitiInfoBean2.getPageId() + "|");
					haitiInfoBean1.setCountPass(locationBean.getCountPass() + 1);
					
					listHaitisakiNew.add(haitiInfoBean1);
				}
			}
		}
		
		if (isLoopFlag) {
			listHaitisaki.clear();
			for (int i = 0; i < listHaitisakiNew.size(); i++) {
				listHaitisaki.add(listHaitisakiNew.get(i));
			}
			count++;
			getListHaitisaki(listHaitisaki, count, loginUser);
		}
	}

	//	コンテンツ配置先、例えば[HOME]-[案内/通達]-[コンテンツ配置先表示]
	public SiteSearchResultBean getHaiTiSaki(String PageId, LoginUserBean loginUser) {
		SiteSearchResultBean jitubean = new SiteSearchResultBean();
		String strHaiTiSaki=new String();
		String strKey=new String();
		Boolean flag=true;
		String pageid=PageId;
		int i = 0;
		while(flag){
			if("0".equals(pageid)){         
				strHaiTiSaki="[HOME]"+strHaiTiSaki;
				strKey = "0|" + strKey;
				flag = false; 
			}else{
				SiteSearchResultBean page = siteSearchDao.getPage(pageid, loginUser);
				if (page == null) {
					strHaiTiSaki = "参照先が特定できません。";
					strKey = "";
					jitubean.setHaitisaki(strHaiTiSaki);
					jitubean.setStrPageKey(strKey);
					return jitubean;
				}
				String strP_Title="["+page.getTitle()+"]";
				strHaiTiSaki="-"+strP_Title+strHaiTiSaki;
				String strPkey = page.getPageId() + "|";
				strKey = strPkey + strKey;
				pageid=page.getPPageId();
			}
			if (i++ >= ConstantContainer.SITE_SEARCH_MAX) {
				flag = false;
				strHaiTiSaki = "参照先が特定できません。";
				strKey = "";
				jitubean.setHaitisaki(strHaiTiSaki);
				jitubean.setStrPageKey(strKey);
				return jitubean;
			}
		}
		jitubean.setHaitisaki(strHaiTiSaki);
		jitubean.setStrPageKey(strKey);
		return jitubean;
	}

	// オラクル検索用キーワード
	private List changeOracleKeyWord(String OracleKeyWord, String str, LoginUserBean loginUser) {
		List<String> keyList = new ArrayList<String>();
		try {
			String oKeyWord = "";
			//オラクル検索用キーワード半角小文字To全角大文字
			oKeyWord = siteSearchDao.changeOracleKeyWord(OracleKeyWord);
			
			if (oKeyWord != null) {
				oKeyWord = oKeyWord.replaceAll("％", "\\\\％").replaceAll("＿", "\\\\＿");
			}
			
			String key = "";
			String key1[] = null;
			String key2[] = null;
			String key3[] = null;
			String key4[] = null;
			String key5[] = null;
			
			String notKey = "";
			
			oKeyWord = trimString(oKeyWord);
			oKeyWord = removeStar(oKeyWord);
			oKeyWord = trimString(oKeyWord);
			key1 = oKeyWord.split("　ＡＮＤ　");
			for(int i = 0; i < key1.length; i++) {
				key1[i] = trimString(key1[i]);
				key1[i] = removeStar(key1[i]);
				key1[i] = trimString(key1[i]);
				if(key1[i].contains("　ＯＲ　") || key1[i].contains("　ＮＯＴ　")) {
					key2 = key1[i].split("　ＯＲ　");
					key1[i] = "";
					for(int j = 0; j < key2.length; j++) {
						key2[j] = trimString(key2[j]);
						key2[j] = removeStar(key2[j]);
						key2[j] = trimString(key2[j]);
						if(key2[j].contains("　ＮＯＴ　")) {
							key3 = key2[j].split("　ＮＯＴ　");
							key2[j] = "";
							for(int k = 0; k < key3.length; k++) {
								key3[k] = trimString(key3[k]);
								key3[k] = removeStar(key3[k]);
								key3[k] = trimString(key3[k]);
								if(k == 0) {
									key3[k] = str + " like '%" + key3[k] + "%' escape '\\'";
								} else {
									key3[k] = "like '%" + key3[k] + "%' escape '\\'";
								}
								if (k == key3.length - 1) {
									key2[j] = key2[j] + key3[k];
								} else {
									key2[j] = key2[j] + key3[k] + " and " + str + " not ";
								}
								/** 添付*/
								if(k == key3.length -1) {
									String tempkey = key2[j];
									key2[j] = tempkey.substring(0, tempkey.indexOf(" and"));
									notKey = notKey + tempkey.substring(tempkey.indexOf(" and"), tempkey.length());
								}
							}
						} else {
							key2[j] = str + " like '%" + key2[j] + "%' escape '\\'";
						}
						if (j == key2.length - 1) {
							key1[i] = key1[i] + key2[j];
						} else {
							key1[i] = key1[i] + key2[j] + " or ";
						}
					}
				} else {
					key1[i] = str + " like '%" + key1[i] + "%' escape '\\'";
				}
				if (i == key1.length - 1) {
					key = key + key1[i];
				} else {
					key = key + key1[i] + " and ";	
				}
			}
			if(key.contains("　")) {
				key4 = key.split("　");
				key = "";
				for(int n = 0; n < key4.length; n++) {
					key4[n] = removeStar(key4[n]);
					key4[n] = key4[n].trim();
					if(!"".equals(key4[n])) {
						if(n == 0) {
							key = key + key4[n] + "%' escape '\\'";
						} else if (n == key4.length - 1) {
							key4[n] = " and " + str + " like '%" + key4[n];
							key = key + key4[n];
						} else {
							key4[n] = " and " + str + " like '%" + key4[n] + "%' escape '\\'";
							key = key + key4[n] ;
						}
					}
				}
			}
			
			if(notKey.contains("　")) {
				key5 = notKey.split("　");
				notKey = "";
				for(int n = 0; n < key5.length; n++) {
					key5[n] = removeStar(key5[n]);
					key5[n] = key5[n].trim();
					if(!"".equals(key5[n])) {
						if(n == 0) {
							notKey = " " + notKey + key5[n] + "%' escape '\\'";
						} else if (n == key5.length - 1) {
							if(key5[n].indexOf("and") != -1) {
								notKey = notKey + key5[n].substring(key5[n].indexOf("and")-1, key5[n].length());
								key5[n] = " and " + str + " like '%" + key5[n].substring(0,key5[n].indexOf("and"));
							} else {
								key5[n] = " and " + str + " like '%" + key5[n];
							}
							key = key + key5[n]; 
						} else {
							if(key5[n].indexOf("and") != -1) {
								notKey = notKey + key5[n].substring(key5[n].indexOf("and")-1, key5[n].length()) + "%' escape '\\'";
								key5[n] = " and " + str + " like '%" + key5[n].substring(0,key5[n].indexOf("and"));
							} else {
								key5[n] = " and " + str + " like '%" + key5[n] + "%' escape '\\'";
							}
							key = key + key5[n] ;
						}
					}
				}
			}
			
			if(notKey != null && !"".equals(notKey)) {
				notKey = notKey.substring(notKey.indexOf(" and ") + 4, notKey.length());		
			} else {
				notKey = "";
			}
			keyList.add(key);
			keyList.add(notKey);
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.writeActionLog(loginUser.getUserId(),"オラクル検索用キーワード異常メッセージ：" + OracleKeyWord);
			String key =  str + " like '%" + OracleKeyWord + "%' escape '\\'";
			keyList.add(key);
			keyList.add("");
		}
		return keyList;
	}
	
	//文字列両端のスペースを削除する
	private String trimString(String str) {
		char trimStr = ' ';
		String str1 = "";
		String str2 = "";
		boolean flag = false;
		boolean flag1 = false;
		//削除文字列前端のスペース
		for (int i = 0; i < str.length(); i++){
			if(!flag) {
				trimStr = str.charAt(i);
				if(trimStr != '　' && trimStr != ' ') {
					flag = true;
					str1 = str.substring(i);
				}
			}
		}
		//削除文字列後端のスペース
		for (int i = 0; i < str1.length(); i++){
			if(!flag1) {
				trimStr = str1.charAt(str1.length() - 1 - i);
				if(trimStr != '　'&& trimStr != ' ') {
					flag1 = true;
					str2 = str1.substring(0, str1.length() - i);
				}
			}
		}
		return str2;
	}
	
	//文字列両端のアスタリスクを削除する
	private String removeStar(String str) {
		char removeStr = ' ';
		String str1 = "";
		String str2 = "";
		boolean flag = false;
		boolean flag1 = false;
		//削除文字列前端のアスタリスク
		for (int i = 0; i < str.length(); i++){
			if(!flag) {
				removeStr = str.charAt(i);
				if(removeStr != '＊' && removeStr != '*') {
					flag = true;
					str1 = str.substring(i);
				}
			}
		}
		//削除文字列後端のアスタリスク
		for (int i = 0; i < str1.length(); i++){
			if(!flag1) {
				removeStr = str1.charAt(str1.length() - 1 - i);
				if(removeStr != '＊' && removeStr != '*') {
					flag1 = true;
					str2 = str1.substring(0, str1.length() - i);
				}
			}
		}
		return str2;
	}
	/**
	 * @param keyWord
	 * @param sort
	 * @return
	 * @function Get the filted search results by Fess.
	 */
	private List<String> searchByFess(String keyWord,String userId) {
		
		List<String> resultsFessList = new ArrayList<String>();
		HttpURLConnection connection = null;
		String result = "",lines;
		String matchKey = "";
		String fileName = "";
		String fileNameNoSuffix = "";
		
		String fessUrl = FsPropertyUtil.getStringProperty("fess.url") + "?query=";
		try{
			URL getUrl = new URL(fessUrl + URLEncoder.encode(keyWord, "UTF-8"));
			
			connection = (HttpURLConnection) getUrl.openConnection();
			connection.setConnectTimeout(30000);
			connection.connect();
		
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
			while ((lines = reader.readLine()) != null) { 
	
	            result = result + lines;
			} 
			if (logger.isDebugEnabled()) {
				logger.debug("Fess検索結果：\r\n" + result);
			}

			JSONObject dataJson=JSONObject.fromObject(result);
			JSONObject  response=dataJson.getJSONObject("response");
			if (!"0".equals(response.getString("recordCount"))) {
				JSONArray data=response.getJSONArray("result");
				for(int i=0; i<data.size(); i++) {
					JSONObject info=data.getJSONObject(i);
					
					fileName = info.getString("url");
					fileName = fileName.substring(fileName.lastIndexOf("/")+1);
					if(fileName.lastIndexOf(".")>=0){
						fileNameNoSuffix = fileName.substring(0, fileName.lastIndexOf("."));
					} else {
						fileNameNoSuffix = fileName;
					}
					if (logger.isDebugEnabled()) {
						logger.debug("Fess検索ファイル名前：" + fileName);
					}

					if (fileNameNoSuffix.matches("^\\d{13,}$")) {
						if (fileNameNoSuffix.matches("^\\d{13}$")) {
							matchKey = fileNameNoSuffix + "|";
						} else {
							matchKey = fileNameNoSuffix.substring(0, 13) + "|" + fileName;
						}
						// Add the search result record to results list
						resultsFessList.add(matchKey);
					}
				}
			}
		} catch (Exception e){
			logger.error("Fess検索がシステムエラーが発生します", e);
		}

		return resultsFessList;
	}
}