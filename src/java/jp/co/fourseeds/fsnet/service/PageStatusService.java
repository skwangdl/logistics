package jp.co.fourseeds.fsnet.service;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.fourseeds.fsnet.beans.pageStatus.PageStatusBean;
import jp.co.fourseeds.fsnet.beans.pageStatus.PageStatusResultBean;
import jp.co.fourseeds.fsnet.common.ConstantContainer;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.PageStatusDao;
import jp.co.common.frame.constant.BaseSystemConstant;
import jp.co.common.frame.service.BaseBusinessService;
import jp.co.common.frame.util.NumberUtil;
import jp.co.common.frame.util.WrappedBeanUtil;
import jp.co.common.frame.util.prop.FsPropertyUtil;

/**
 * コンテンツ状況確認サービス実装クラス
 * 
 * File Name: PageStatusService.java 
 * Created: 2015/11/23
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2015/11/23		    NTS        	       作成
 *
 **/
@Component
public class PageStatusService extends BaseBusinessService{

	@Autowired
	private PageStatusDao pageStatusDao;

	//検索結果ページID格納用
	private StringBuffer mainPageId = new StringBuffer();

	/**
	 * <p>
	 * 検索コンテンツ実配置を取得
	 * </p>
	 * 
	 * @param formBean
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws InstantiationException 
	 * @throws IllegalAccessException 
	 * 
	 */
	public Map<String,Object> getPageStatusList(PageStatusBean formBean) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
		
		//戻り値
		Map<String,Object> pageStatusMap = new HashMap<String,Object>();
		StringBuffer strPageLocationPath = new StringBuffer();
		StringBuffer strPageLocationTree = new StringBuffer();
		StringBuffer strPageLinkTree = new StringBuffer();
		
		//CSV値
		List<PageStatusResultBean> pageLocationPathList = new ArrayList<PageStatusResultBean>();
		List<PageStatusResultBean> pageLocationTreeList = new ArrayList<PageStatusResultBean>();
		List<PageStatusResultBean> pageLinkTreeList = new ArrayList<PageStatusResultBean>();
		
		//検索条件
		String searchPageId = formBean.getSearchPageId();
		String searchEditor = formBean.getSearchEditor();
		String searchTitle = formBean.getSearchTitle();
		String searchUser = formBean.getSearchUser();
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("PARA_PAGE_ID", searchPageId);
		param.put("PARA_EDITOR", searchEditor);
		param.put("PARA_TITLE", searchTitle);
		mainPageId.append("__");
		//■■■抽出データの整理
		//検索条件に該当するコンテンツ
		List<PageStatusResultBean> hintPageListMoto = pageStatusDao.getPageStatusList(param);
		List<PageStatusResultBean> hintPageList = null;
		String searchRowNums = FsPropertyUtil.getStringProperty("page.status.search.rowNum");
		if (hintPageListMoto == null || hintPageListMoto.size()==0) {
			hintPageList = hintPageListMoto;
		} else if(StringUtil.isBlank(searchRowNums) || !NumberUtil.isNumber(searchRowNums) || hintPageListMoto.size()<=NumberUtil.toInt(searchRowNums)) {
			hintPageList = hintPageListMoto;
		} else {
			hintPageList = hintPageListMoto.subList(0, NumberUtil.toInt(searchRowNums));
		}
		
		//検索条件に該当するコンテンツ（上位と下位コンテンツを含む）
		List<PageStatusResultBean> hintPageAllList = new ArrayList<PageStatusResultBean>();
		//検索条件に該当するコンテンツのリンク先
		List<PageStatusResultBean> hintPageLinkList = new ArrayList<PageStatusResultBean>();
		//リンク抽出の際の利用されている最上位コンテンツのページID格納用配列
		Map<String, String> topPageIdMap = new HashMap<String, String>();

		hintPageAllList.addAll(hintPageList);
		for(int i = 0; i < hintPageList.size(); i++){
			PageStatusResultBean pageStatusBean = hintPageList.get(i);
			PageStatusResultBean pageStatusCsv = new PageStatusResultBean();
			String pageId = pageStatusBean.getPageId();
			String pPageId = pageStatusBean.getpPageId();
			pageStatusCsv.setPageId(pageId);
			
			setPageInfo(searchUser, pageStatusBean, pageId);
			
			mainPageId.append(pageId + "_");
			
			//一番の実配置を設定
			strPageLocationPath.append("　[HOME]");
			StringBuffer strPageLocationPathTemp = new StringBuffer();
			StringBuffer pageLocationPathCsv = new StringBuffer();
			setPageLocationPath(pageStatusBean, strPageLocationPathTemp, pageLocationPathCsv);
			//最上位コンテンツ（0:HOME)までを順次取得し、配列に格納
			while(!pPageId.equals(pageId)){
				pageId = pPageId;
				//親ページIDが最上位でない場合に配列にその親コンテンツの情報を格納
				if(!"0".equals(pageId)){
					param.put("PARA_PAGE_ID", pageId);
					List<PageStatusResultBean> parentPageStatusList =  pageStatusDao.getPageStatusList(param);
					if(parentPageStatusList.size() > 0){
						PageStatusResultBean pPageBean = parentPageStatusList.get(0);
						pPageId = pPageBean.getpPageId();
						setPageInfo(searchUser, pPageBean, pageId);
						//検索コンテンツ実配置を設定
						setPageLocationPath(pPageBean, strPageLocationPathTemp, pageLocationPathCsv);
					}
					//親ページを追加
					hintPageAllList.addAll(parentPageStatusList);
				}
			}
			//検索コンテンツ実配置を設定
			strPageLocationPath.append(strPageLocationPathTemp);
			strPageLocationPath.append("</br>");
			pageStatusCsv.setTitle(pageStatusBean.getTitle());
			pageStatusCsv.setCsvPath("　[HOME]" + pageLocationPathCsv.toString());
			pageLocationPathList.add(pageStatusCsv);
			
			pageId = pageStatusBean.getPageId();
			//下位コンテンツを抽出
			List<PageStatusResultBean> childPageStatusList = new ArrayList<PageStatusResultBean>();
			this.getChildPageStatusList(pageId, searchUser, childPageStatusList);
			hintPageAllList.addAll(childPageStatusList);

			//対象コンテンツへのリンク設定がされている（PAGE_LINKにて）コンテンツの取得
			this.getLinkPageStatusList(searchUser, pageStatusBean, hintPageLinkList, topPageIdMap);
		}
		
		//■■■検索コンテンツ実配置ツリーイメージ出力
		if(hintPageAllList.size() > 0){
			strPageLocationTree = makePageStatusTree(hintPageAllList, pageLocationTreeList);
		}
		
		//リンク配置配列ヘッダ出力
		//リンクでの最上位コンテンツの取得
		for(Map.Entry<String, String> entry : topPageIdMap.entrySet()){
			param.put("PARA_PAGE_ID", entry.getKey());
			List<PageStatusResultBean> topLinkPageStatusList = pageStatusDao.getPageStatusList(param);
			PageStatusResultBean topLinkPageBean = topLinkPageStatusList.get(0);
			String topLinkPageId = topLinkPageBean.getPageId();
			//親ページIDは'0'を設定
			topLinkPageBean.setpPageId("0");
			//有効期限フラグは'0'を設定
			topLinkPageBean.setTermFlag("0");
			
			setPageInfo(searchUser, topLinkPageBean, topLinkPageId);
			hintPageLinkList.add(topLinkPageBean);
		}
		//■■■閲覧者向けコンテンツツリーイメージ出力
		if(hintPageLinkList.size() > 0){
			strPageLinkTree =  makePageStatusTree(hintPageLinkList, pageLinkTreeList);
		}
		pageStatusMap.put("LOCATION_PATH", strPageLocationPath.toString());
		pageStatusMap.put("LOCATION_TREE", strPageLocationTree.toString());
		pageStatusMap.put("LINK_TREE", strPageLinkTree.toString());
		
		pageStatusMap.put("LOCATION_PATH_CSV", pageLocationPathList);
		pageStatusMap.put("LOCATION_TREE_CSV", pageLocationTreeList);
		pageStatusMap.put("LINK_TREE_CSV", pageLinkTreeList);
		return pageStatusMap;
	}
	
	/**
	 * <p>
	 * コンテンツ整理
	 * </p>
	 * 
	 * @return
	 */
	private StringBuffer makePageStatusTree(List<PageStatusResultBean> pageStatusList, List<PageStatusResultBean> pageStatusCsvList) {

		StringBuffer strReturn = new StringBuffer();
		if(pageStatusList.size() > 0){
			pageStatusList.add(0, getFirstBean());
		}
		//検索コンテンツ配列の単一化
		List<PageStatusResultBean> newPageStatusList = removeRepeat(pageStatusList);
		
		//ツリー作成用配列の初期化
		ArrayList<PageStatusResultBean> broths = new ArrayList<PageStatusResultBean>();
		HashMap<String, String> childs = new HashMap<String, String>();
		HashMap<String, String> texts  = new HashMap<String, String>();
		HashMap<String, String> term_flgs = new HashMap<String, String>();
		HashMap<String, String> insp_flgs = new HashMap<String, String>();
		HashMap<String, String> confirm_flgs = new HashMap<String, String>();
		HashMap<String, String> link_flgs = new HashMap<String, String>();
		HashMap<String, String> future_flgs = new HashMap<String, String>();
		HashMap<String, String> Reserve_flgs = new HashMap<String, String>();
		
		for(int i = 0; i < newPageStatusList.size(); i++){
			PageStatusResultBean bean = newPageStatusList.get(i);
			if (!"★".equals(bean.getpPageId())) {
				if (bean.getpPageId().equals(childs.get(bean.getPageId()))) {
					PageStatusResultBean tempBean = new PageStatusResultBean();
					tempBean.setPageId(bean.getPageId());
					tempBean.setpPageId(bean.getpPageId());
					tempBean.setTail("★");
					broths.add(tempBean);
					
					if (!childs.containsKey(bean.getpPageId())) {
						childs.put(bean.getpPageId(), "★");
					}
				} else {
					if (childs.containsKey(bean.getpPageId())) {
						PageStatusResultBean tempBean = new PageStatusResultBean();
						tempBean.setPageId(bean.getPageId());
						tempBean.setpPageId(bean.getpPageId());
						tempBean.setTail(childs.get(bean.getpPageId()));
						broths.add(tempBean);
					} else {
						PageStatusResultBean tempBean = new PageStatusResultBean();
						tempBean.setPageId(bean.getPageId());
						tempBean.setpPageId(bean.getpPageId());
						tempBean.setTail("★");
						broths.add(tempBean);
					}
					childs.put(bean.getpPageId(), bean.getPageId());
				}
			}
			texts.put(bean.getPageId(), bean.getTitle());
			term_flgs.put(bean.getPageId(), bean.getTermFlag());
			insp_flgs.put(bean.getPageId(), bean.getInspFlag());
			confirm_flgs.put(bean.getPageId(), bean.getConfirmFlag());
			link_flgs.put(bean.getPageId(), bean.getPageLinkFlag());
			future_flgs.put(bean.getPageId(), bean.getFutureFlag());
			Reserve_flgs.put(bean.getPageId(), bean.getReserveFlag());
		}
		
		putTree("0", "", broths, childs, texts, term_flgs, insp_flgs, confirm_flgs, link_flgs, future_flgs, Reserve_flgs, strReturn, pageStatusCsvList, "");
		
		return strReturn;
	}

	/**
	 * <p>
	 * コンテンツツリー作成
	 * </p>
	 * 
	 * @return
	 */
	private void putTree(String no, String line, 
			ArrayList<PageStatusResultBean> broths, 
			HashMap<String, String> childs, 
			HashMap<String, String> texts, 
			HashMap<String, String> term_flgs, 
			HashMap<String, String> insp_flgs, 
			HashMap<String, String> confirm_flgs, 
			HashMap<String, String> link_flgs, 
			HashMap<String, String> future_flgs, 
			HashMap<String, String> Reserve_flgs,
			StringBuffer strReturn,
			List<PageStatusResultBean> pageStatusCsvList,
			String lineCsv) {
		StringBuffer csvPath = new StringBuffer();
		// 初期内容
		strReturn.append("<span class='line'>" + line + "</span>");
		csvPath.append(lineCsv);
		csvPath.append("▼,[" + no + "]" + texts.get(no));
		if ("0".equals(no)) {
			strReturn.append("▼[" + no + "]" + texts.get(no) + "</br>");
		} else {
			//検索対象の場合
			if(mainPageId.indexOf(no) > 0){
				strReturn.append("<font color='blue'>▼");
				csvPath.append("(" + ConstantContainer.BLUE_CONTENTS + ")");
			} else {
				strReturn.append("▼");
			}
			//リンクNG
			if("NG".equals(link_flgs.get(no))){
				strReturn.append("<img src='./images/nolink.jpg'/>");
				csvPath.append("(" + ConstantContainer.NOLINK_CONTENTS + ")");
			}
			//未来公開開始
			if("1".equals(future_flgs.get(no))){
				strReturn.append("<img src='./images/fo.JPG'/>");
				csvPath.append("(" + ConstantContainer.FO_CONTENTS + ")");
			}
			//未公開
			if("1".equals(confirm_flgs.get(no))){
				strReturn.append("<img src='./images/ne.jpg'/>");
				csvPath.append("(" + ConstantContainer.NE_CONTENTS + ")");
			}
			//予約あり（公開したまま編集）
			if("Yes".equals(Reserve_flgs.get(no))){
				strReturn.append("<img src='./images/oe.JPG'/>");
				csvPath.append("(" + ConstantContainer.OE_CONTENTS + ")");
			}
			//閲覧権限なし
			if("false".equals(insp_flgs.get(no))){
				strReturn.append("<img src='./images/dontlookimage.jpg'/>");
				csvPath.append("(" + ConstantContainer.DONTLOOKIMAGE_CONTENTS + ")");
			}
			//有効期限切れ
			if("1".equals(term_flgs.get(no))){
				strReturn.append("<del>");
				strReturn.append("[<a href = '#' style='text-decoration:underline;' onclick='searchPageDetail(\"" + no + "\");return false;'>" + no + "</a>]" + texts.get(no) + "</br>");
				strReturn.append("</del></font>");
				csvPath.append("(" + ConstantContainer.DEL_CONTENTS + ")");
			} else {
				strReturn.append("[<a href = '#' style='text-decoration:underline;' onclick='searchPageDetail(\"" + no + "\");return false;'>" + no + "</a>]" + texts.get(no)+ "</font></br>");
			}
		}
		
		line = line.replaceAll("├", "│");
		line = line.replaceAll("└", "　");
		lineCsv = lineCsv.replaceAll("├", "│");
		lineCsv = lineCsv.replaceAll("└", "　");
		String strPno = no;
		no = childs.containsKey(no)? childs.get(no) : "★";
		
		PageStatusResultBean csvBean = new PageStatusResultBean();
		csvBean.setCsvPath(csvPath.toString());
		pageStatusCsvList.add(csvBean);
		
		while (!"★".equals(no)) {
			for (int i=0; i<broths.size(); i++) {
				PageStatusResultBean tempBean = (PageStatusResultBean)broths.get(i);
				if (no.equals(tempBean.getPageId()) && (strPno.equals(tempBean.getpPageId()))) {
					String strTail = !"★".equals(tempBean.getTail())? "├" : "└";
					String strTailCsv = strTail + BaseSystemConstant.COMMA_SEPARATOR;
					putTree(no, line+strTail, broths, childs, texts,term_flgs,insp_flgs,confirm_flgs,link_flgs,future_flgs,Reserve_flgs, strReturn, pageStatusCsvList, lineCsv + strTailCsv);
					no = tempBean.getTail();
					break;
				}
			}
		}
	}
	/**
	 * <p>
	 * 重複コンテンツを削除
	 * </p>
	 * 
	 * @return
	 */
	private List<PageStatusResultBean> removeRepeat(List<PageStatusResultBean> oldList) {
		List<PageStatusResultBean> newList = new ArrayList<PageStatusResultBean>();
		for(int i = 0; i < oldList.size(); i++){
			PageStatusResultBean bean = oldList.get(i);
			if(!newList.contains(bean)){
				newList.add(bean);
			}
		}
		return newList;
	}

	/**
	 * <p>
	 * デフォルトコンテンツの取得
	 * </p>
	 * 
	 * @return
	 */
	private PageStatusResultBean getFirstBean() {
		PageStatusResultBean pageBean = new PageStatusResultBean();
		//ページID
		pageBean.setPageId("0");
		//親ページID
		pageBean.setpPageId("★");
		//タイトル
		pageBean.setTitle("HOME");
		//有効期限フラグ
		pageBean.setTermFlag("0");
		//閲覧権限フラグ
		pageBean.setInspFlag("true");
		//非公開フラグ
		pageBean.setConfirmFlag("0");
		//リンク未設定もしくはリンク先コンテンツ削除済判定フラグ
		pageBean.setPageLinkFlag("OK");
		//未来公開開始フラグ
		pageBean.setFutureFlag("true");
		//公開したまま編集判定フラグ
		pageBean.setReserveFlag("No");
		return pageBean;
	}

	/**
	 * <p>
	 * 対象コンテンツへのリンク設定がされている（PAGE_LINKにて）コンテンツの取得
	 * </p>
	 * 
	 * @param searchUser
	 * @param pageStatusBean
	 * @param topPageIdMap 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws InstantiationException 
	 * @throws IllegalAccessException 
	 */
	private void getLinkPageStatusList(String searchUser, PageStatusResultBean fromBean, List<PageStatusResultBean> hintPageLinkList, Map<String, String> topPageIdMap) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

		String pageId = fromBean.getPageId();
		//検索条件
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("PARA_PAGE_ID", pageId);
		List<PageStatusResultBean> linkPageResultList = pageStatusDao.getLinkPageList(param);
		
		for(int i = 0; i < linkPageResultList.size(); i++){
			PageStatusResultBean linkPageBean = linkPageResultList.get(i);
			String linkPageId = linkPageBean.getPageId();
			String parentLinkPageId = linkPageBean.getpPageId();
			//公開したまま編集判定
			linkPageBean.setReserveFlag(this.getReserveFlag(linkPageId));
			//さらに取得したコンテンツに対してリンク設定がされているか、もしくは、親が最上位であれば、リンク配置用配列に格納
			param.put("PARA_PAGE_ID", linkPageId);
			param.put("PARA_CHILD_LINK_FLAG", "1");
			List<PageStatusResultBean> fromLinkPageResultList = pageStatusDao.getLinkPageList(param);
			String fromLinkPageId = "";
			//リンク元Beanのクローン作成
			PageStatusResultBean pageStatusBean = this.cloneBean(fromBean);
			if(fromLinkPageResultList.size() > 0 || "0".equals(parentLinkPageId)){
				if(fromLinkPageResultList.size() > 0){
					PageStatusResultBean fromLinkPageBean = fromLinkPageResultList.get(0);
					fromLinkPageId = fromLinkPageBean.getPageId();
				}
				//指定ユーザが該当コンテンツを閲覧できるか判定
				if("".equals(searchUser)){
					pageStatusBean.setInspFlag("true");
				}else{
					pageStatusBean.setInspFlag(getInspflg(pageId, searchUser));
				}
				//リンク未設定もしくはリンク先コンテンツ削除済判定
				pageStatusBean.setPageLinkFlag(this.getPageLinkFlag(pageId));
				//
				pageStatusBean.setpPageId(linkPageId);
				hintPageLinkList.add(pageStatusBean);
				
				if("0".equals(parentLinkPageId)){
					topPageIdMap.put(linkPageId, linkPageId);
				}
				if(!"".equals(linkPageId) && !fromLinkPageId.equals(pageId)){
					getLinkPageStatusList(searchUser, linkPageBean, hintPageLinkList, topPageIdMap);
				}
			} 
			//リンクがなかった場合は、実配置にする。
			else {
				//指定ユーザが該当コンテンツを閲覧できるか判定
				if("".equals(searchUser)){
					pageStatusBean.setInspFlag("true");
				}else{
					pageStatusBean.setInspFlag(getInspflg(pageId, searchUser));
				}
				pageStatusBean.setReserveFlag(getReserveFlag(pageId));
				//リンク未設定もしくはリンク先コンテンツ削除済判定
				pageStatusBean.setPageLinkFlag("OK");
				pageStatusBean.setpPageId(linkPageId);
				hintPageLinkList.add(pageStatusBean);
				this.getParentLinkPageList(searchUser,linkPageId, hintPageLinkList);
			}
		}
	}
	
	/**
	 * <p>
	 * リンク元Beanのクローン作成
	 * </p>
	 * @param fromBean
	 * 
	 */
	private PageStatusResultBean cloneBean(PageStatusResultBean fromBean) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
		PageStatusResultBean cloneBean = new PageStatusResultBean();
		cloneBean = (PageStatusResultBean) WrappedBeanUtil.cloneBean(fromBean);
		return cloneBean;
	}
	
	/**
	 * <p>
	 * リンクコンテンツを抽出
	 * </p>
	 * 
	 * @param pageId
	 * @param searchUser
	 * 
	 */
	private void getParentLinkPageList(String searchUser, String linkPageId, List<PageStatusResultBean> hintPageLinkList) {
		//検索条件
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("PARA_PAGE_ID", linkPageId);
		List<PageStatusResultBean> parentPageResultList = pageStatusDao.getPageStatusList(param);
		for(int i = 0; i < parentPageResultList.size(); i++){
			PageStatusResultBean  parentPageBean = parentPageResultList.get(i);
			String pageId = parentPageBean.getPageId();
			String pPageId = parentPageBean.getpPageId();
			//指定ユーザが該当コンテンツを閲覧できるか判定
			if("".equals(searchUser)){
				parentPageBean.setInspFlag("true");
			}else{
				parentPageBean.setInspFlag(getInspflg(pageId, searchUser));
			}
			//公開したまま編集判定
			parentPageBean.setReserveFlag(this.getReserveFlag(pageId));
			hintPageLinkList.add(parentPageBean);
			if("0".equals(pageId)){
				break;
			}
			this.getParentLinkPageList(searchUser,pPageId, hintPageLinkList);
		}
	}

	/**
	 * <p>
	 * 下位コンテンツを抽出
	 * </p>
	 * 
	 * @param pageId
	 * @param searchUser
	 * 
	 */
	private void getChildPageStatusList(String pageId, String searchUser, List<PageStatusResultBean> childPageStatusList) {
		//検索条件
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("PARA_PAGE_ID", pageId);
		//下位コンテンツを取得
		List<PageStatusResultBean> childPageList = pageStatusDao.getChildPageList(param);
		for(int i = 0; i < childPageList.size(); i++){
			PageStatusResultBean childPageBean = childPageList.get(i);
			String childPageId = childPageBean.getPageId();
			
			setPageInfo(searchUser, childPageBean, childPageId);
			childPageStatusList.add(childPageBean);
			this.getChildPageStatusList(childPageId, searchUser, childPageStatusList);
		}
	}

	private void setPageInfo(String searchUser, PageStatusResultBean pageBean, String pageId) {
		//リンク未設定もしくはリンク先コンテンツ削除済判定
		pageBean.setPageLinkFlag(this.getPageLinkFlag(pageId));
		//公開したまま編集判定
		pageBean.setReserveFlag(this.getReserveFlag(pageId));
		//指定ユーザが該当コンテンツを閲覧できるか判定
		if("".equals(searchUser)){
			pageBean.setInspFlag("true");
		}else{
			pageBean.setInspFlag(this.getInspflg(pageId, searchUser));
		}
	}

	/**
	 * <p>
	 * 検索コンテンツ実配置を設定
	 * </p>
	 * 
	 * @param pageBean
	 * @param strReturn
	 * 
	 */
	private void setPageLocationPath(PageStatusResultBean pageBean, StringBuffer strReturn, StringBuffer pageLocationPathCsv){
		if("true".equals(pageBean.getInspFlag())){
			if("0".equals(pageBean.getTermFlag())){
				strReturn.insert(0, "-[" + pageBean.getTitle() + "]");
				pageLocationPathCsv.insert(0, "-[" + pageBean.getTitle() + "]");
			} else {
				strReturn.insert(0, "-<I><font color='gray'>[" + pageBean.getTitle() + "]</font></I>");
				pageLocationPathCsv.insert(0, "-[" + pageBean.getTitle() + "](" + ConstantContainer.GRAY_CONTENTS + ")");
			}
		} else {
			strReturn.insert(0, "-<I><font color='gray'>[" + pageBean.getTitle() + "]</font></I>");
			pageLocationPathCsv.insert(0, "-[" + pageBean.getTitle() + "](" + ConstantContainer.GRAY_CONTENTS + ")");
		}
	}

	/**
	 * <p>
	 * 指定ユーザが該当コンテンツを閲覧できるか判定
	 * </p>
	 * 
	 * @param pageId
	 * @param userId
	 * 
	 */
	private String getInspflg(String pageId, String userId) {
		//コンテンツリンクフラグ
		String inspflg;
		int count = pageStatusDao.getInspflg(pageId, userId);
		inspflg = count > 0 ? "true" : "false";
		return inspflg;
	}

	/**
	 * <p>
	 * リンク未設定もしくはリンク先コンテンツ削除済判定
	 * </p>
	 * 
	 * @param pageId
	 * 
	 */
	private String getPageLinkFlag(String pageId) {
		//コンテンツリンクフラグ
		String pageLinkFlag;
		int count = pageStatusDao.getPageLinkNum(pageId);
		pageLinkFlag = count > 0 ? "NG" : "OK";
		return pageLinkFlag;
	}

	/**
	 * <p>
	 * 公開したまま編集判定（コンテンツ予約テーブルに削除されていない同じページＩＤのコンテンツが存在するか）
	 * </p>
	 * 
	 * @param pageId
	 * 
	 */
	private String getReserveFlag(String pageId) {
		//コンテンツ予約フラグ
		String reserveFlag;
		int count = pageStatusDao.getReservePageNum(pageId);
		reserveFlag = count > 0 ? "Yes" : "No";
		return reserveFlag;
	}

	/**
	 * <p>
	 * コンテンツ情報を取得
	 * </p>
	 * @param formBean
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws InstantiationException 
	 * @throws IllegalAccessException 
	 */
	public PageStatusResultBean getPageInfo(PageStatusBean formBean) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
		StringBuffer strPageLocationPath = new StringBuffer();
		String searchPageId = formBean.getSearchPageId();
		//コンテンツ情報を取得
		PageStatusResultBean pageInfo = pageStatusDao.getPageInfo(searchPageId);
		if(!StringUtil.isBlank(pageInfo)){
			PageStatusResultBean pageStatusBean = cloneBean(pageInfo);
			//該当コンテンツ実配置先情報を取得
			setDetailPageLocationPath(pageInfo, formBean.getSearchUser(), strPageLocationPath);
			//親コンテンツ実配置先情報を取得
			getRealLocationPath(pageStatusBean, formBean.getSearchUser(), strPageLocationPath);
			//一番の実配置を設定
			strPageLocationPath.insert(0, "[HOME]");
			pageInfo.setRealLocationPath(strPageLocationPath.toString());
		}
		return pageInfo;
	}

	/**
	 * <p>
	 * 実配置先情報を取得
	 * </p>
	 * @param pageInfo
	 * @param formBean
	 */
	private void getRealLocationPath(PageStatusResultBean pageStatusBean, String searchUser, StringBuffer strReturn) {
		
		String pageId = pageStatusBean.getPageId();
		String pPageId = pageStatusBean.getpPageId();
		//最上位コンテンツ（0:HOME)までを順次取得し、配列に格納
		while(!pPageId.equals(pageId)){
			pageId = pPageId;
			//親ページIDが最上位でない場合に配列にその親コンテンツの情報を格納
			if(!"0".equals(pageId)){
				PageStatusResultBean parentPageInfo =  pageStatusDao.getPageInfo(pageId);
				if(!StringUtil.isBlank(parentPageInfo)){
					pPageId = parentPageInfo.getpPageId();
					setDetailPageLocationPath(parentPageInfo, searchUser, strReturn);
				} else {
					if(!"0".equals(pPageId)){
						strReturn.insert(0, "-[削除され経路不明]");
					}
				}
				
			}
		}
	}
	
	/**
	 * <p>
	 * 詳細コンテンツ実配置を設定
	 * </p>
	 * 
	 * @param pageBean
	 * @param strReturn
	 * 
	 */
	private void setDetailPageLocationPath(PageStatusResultBean pageBean, String searchUser, StringBuffer strReturn ){
		String deleteFlag = pageBean.getDeleteFlag();
		String termFlag = pageBean.getTermFlag();
		String title = pageBean.getTitle();
		String pageId = pageBean.getPageId();
		//親コンテンツは削除済みの場合
		if("1".equals(deleteFlag)){
			strReturn.insert(0, "-<I><font color=gray>[" + title + "]</font></I>");
		} else {
			//親コンテンツは期限切れの場合
			if("1".equals(termFlag)){
				strReturn.insert(0, "-<I><font color=gray>[" + title + "]</font></I>");
			} else {
				//指定ユーザが該当コンテンツを閲覧できるか判定
				if("".equals(searchUser)){
					pageBean.setInspFlag("true");
				}else{
					pageBean.setInspFlag(this.getInspflg(pageId, searchUser));
				}
				//コンテンツ閲覧権限無しの場合
				if("false".equals(pageBean.getInspFlag())){
					strReturn.insert(0, "-<I><font color=gray>[" + title + "]</font></I>");
				//有効な閲覧可能コンテンツの場合
				} else {
					strReturn.insert(0, "-[" + title + "]");
				}
			}
		}
	}

	/**
	 * <p>
	 * 公開するグループリストを取得
	 * </p>
	 * @param formBean
	 * 
	 */
	public List<PageStatusResultBean> getOpenGroupList(PageStatusBean formBean) {
		return pageStatusDao.getOpenGroupList(formBean.getSearchPageId());
	}

	/**
	 * <p>
	 * 公開する個人リストを取得
	 * </p>
	 * @param formBean
	 * 
	 */
	public List<PageStatusResultBean> getOpenPersonalList(PageStatusBean formBean) {
		return pageStatusDao.getOpenPersonalList(formBean.getSearchPageId());
	}

	/**
	 * <p>
	 * 承認者リストを取得
	 * </p>
	 * @param formBean
	 * 
	 */
	public List<PageStatusResultBean> getAuthorizerList(PageStatusBean formBean) {
		return pageStatusDao.getAuthorizerList(formBean.getSearchPageId());
	}

	/**
	 * <p>
	 * リンクリストを取得
	 * </p>
	 * @param formBean
	 * 
	 */
	public List<PageStatusResultBean> getPageLinkList(PageStatusBean formBean) {
		List<PageStatusResultBean> linkPageList = pageStatusDao.getPageLinkList(formBean.getSearchPageId());
		if(linkPageList.size() > 0){
			for(int i = 0; i < linkPageList.size(); i++){
				PageStatusResultBean linkPageBean = linkPageList.get(i);
				String linkUrl = linkPageBean.getLinkUrl();
				String linkTile = linkPageBean.getLinkTitle();
				StringBuffer linkTitleBuffer = new StringBuffer();
				if(linkUrl.length() >= 4 && ("http".equals(linkUrl.substring(0, 4)) || "www.".equals(linkUrl.substring(0, 4)))){
					linkTitleBuffer.append("<font color = 'green' size = '2'>" + linkUrl + "</font>");
				} else if(linkTile.length() >= 5 && "<font".equals(linkTile.substring(0, 5))){
					linkTitleBuffer.append("<font size = '2'>" + linkTile + "</font>");
				} else if(linkTile.length() >= 14){
					linkTitleBuffer.append("<font size = '2'>[<a href ='#' style='text-decoration:underline;' onclick='searchPageDetail(\"" + linkTile.substring(1, 14) + "\");return false;'>" + linkTile.substring(1, 14) + "</a>" + linkTile.substring(14,linkTile.length()>100?100:linkTile.length()) + "</font>");
				}
				if(linkTitleBuffer.length() > 0){
					linkPageBean.setLinkTitle(linkTitleBuffer.toString());
				}
			}
		}
		return linkPageList;
	}

	/**
	 * <p>
	 * 添付ファイルリストを取得
	 * </p>
	 * @param formBean
	 * 
	 */
	public List<PageStatusResultBean> getAttachmentList(PageStatusBean formBean) {
		return pageStatusDao.getAttachmentList(formBean.getSearchPageId());
	}
	
	/**
	 * CSVファイル出力
	 * 
	 * @param response
	 *           
	 * @param formBean
	 *           
	 * @return
	 * @throws Exception 
	 *           
	 * */
	public void csvDownloadContents(HttpServletResponse response,List<PageStatusResultBean> formBean, String csvFileName) throws Exception {
		response.setCharacterEncoding("shift_jis");
		response.setContentType("text/plain;charset=shift-jis");
		response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(getCsvFileName(csvFileName), "UTF8"));
		response.setHeader("Cache-Control","");
		response.setHeader("Pragma","");
		OutputStream os = response.getOutputStream();
		OutputStreamWriter outPut = new OutputStreamWriter(os); 
		BufferedWriter writer = new BufferedWriter(outPut); 
		try {
			//CSVヘッダ
			writer.write("ページID" + BaseSystemConstant.COMMA_SEPARATOR);
			writer.write("タイトル" + BaseSystemConstant.COMMA_SEPARATOR);
			writer.write("配置先");
			writer.write("\r\n");
			// CSV明細
			PageStatusResultBean row = new PageStatusResultBean();
			if (formBean != null && formBean.size() != 0) {
				for (int i = 0; i < formBean.size(); i++) {
					row = formBean.get(i);
					//利用者名
					writer.write(StringUtil.nullToBlank((String)row.getPageId()) + BaseSystemConstant.COMMA_SEPARATOR);
					//ID
					writer.write(StringUtil.nullToBlank((String)row.getTitle()) + BaseSystemConstant.COMMA_SEPARATOR);
					writer.write(StringUtil.nullToBlank((String)row.getCsvPath()));
					//改行
					writer.write("\r\n");
				}
			}
		} catch(Exception e) {
			throw e;
		} finally {
			writer.close();
			outPut.close();
			// 出力処理
			os.flush();
			os.close();
		}
	}
	
	/**
	 * CSVファイル出力
	 * 
	 * @param response
	 *           
	 * @param formBean
	 *           
	 * @return
	 * @throws Exception 
	 *           
	 * */
	public void csvDownloadContentsTree(HttpServletResponse response,List<PageStatusResultBean> formBean, String csvFileName) throws Exception {
		response.setCharacterEncoding("shift_jis");
		response.setContentType("text/plain;charset=shift-jis");
		response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(getCsvFileName(csvFileName), "UTF8"));
		response.setHeader("Cache-Control","");
		response.setHeader("Pragma","");
		OutputStream os = response.getOutputStream();
		OutputStreamWriter outPut = new OutputStreamWriter(os); 
		BufferedWriter writer = new BufferedWriter(outPut); 
		try {
			// CSV明細
			if (formBean != null && formBean.size() != 0) {
				for (int i = 0; i < formBean.size(); i++) {
					writer.write(StringUtil.nullToBlank(formBean.get(i).getCsvPath()));
					//改行
					writer.write("\r\n");
				}
			}
		} catch(Exception e) {
			throw e;
		} finally {
			writer.close();
			outPut.close();
			// 出力処理
			os.flush();
			os.close();
		}
	}
	
	/**
	 * CSV名前
	 * 
	 * @param csvName
	 * @return
	 */
	private String getCsvFileName(String csvName) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return csvName + "_" + df.format(new Date()) + ".csv";
	}
}