package jp.co.fourseeds.fsnet.service;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.common.frame.beans.LabelValueBean;
import jp.co.common.frame.service.BaseBusinessService;
import jp.co.common.frame.service.CommonService;
import jp.co.common.frame.util.DateUtil;
import jp.co.common.frame.util.FreemarkerUtil;
import jp.co.common.frame.util.NumberUtil;
import jp.co.common.frame.util.prop.FsPropertyUtil;
import jp.co.fourseeds.fsnet.beans.personalMail.PersonalMailBean;
import jp.co.fourseeds.fsnet.beans.personalMail.PersonalMailFormBean;
import jp.co.fourseeds.fsnet.common.util.SendPersonalMail;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.PersonalMailDao;

/**
 *  メールメンテ機能サービス実装クラス
 * 
 * File Name: PersonalMailService.java 
 * Created: 2015/12/02
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 * 1.0		2015/12/02		    NTS            作成
 *
 **/
@Component
public class PersonalMailService extends BaseBusinessService{

	@Autowired
	private PersonalMailDao personalMailDao;
	
	@Autowired
	private CommonService commonService;
	
	// メール送信
	SendPersonalMail sendMail = new SendPersonalMail();
	
	// 【イントラ】個別メール登録完了通知
	private static String PIZZALA_SETTING_KB = "PIZZALA_SETTING";
	
	// 【イントラ】個別メール削除完了通知
	private static String PIZZALA_EXPIRED_KB = "PIZZALA_EXPIRED";
	
	// 【イントラ】個別メール設定依頼通知(robuchon.jp)
	private static String ROBUCHON_SETTING_KB = "ROBUCHON_SETTING";
	
	// 【イントラ】個別メール登録完了通知(robuchon.jp)
	private static String ROBUCHON_FINISH_KB = "ROBUCHON_FINISH";
	
	// 【イントラ】個別メール削除設定依頼通知(robuchon.jp)
	private static String ROBUCHON_EXPIRED_KB = "ROBUCHON_EXPIRED";
	
	// 【イントラ】個別メール削除完了通知(robuchon.jp)
	private static String ROBUCHON_EXPIRED_FINISH_KB = "ROBUCHON_EXPIRED_FINISH";
	
	// 最大登録可能数を取得
	private static String FIELD_NAME_MAX_MAIL_NUM = "MAX_MAIL_NUM";
	/**
	 * 個別メールリスト情報
	 * @param PersonalMailForm
	 * @param asc_desc
	 *              ソート降順・昇順
	 * @param sort_key
	 *              ソート項目
	 * @return
	 * @throws ServiceException
	 * @throws DataBaseException
	 */
	public void getPersonalMailSearchInfo(PersonalMailFormBean form,String asc_desc,String sort_key) {
		
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		
		int totalMailCnt = 0;
		List<LabelValueBean> labelList = commonService.getDivisionMaster(FIELD_NAME_MAX_MAIL_NUM);
		if (labelList.size()!=0) {
			String cnt = labelList.get(0).getValue();
			if (NumberUtil.isNumber(cnt)) {
				totalMailCnt = Integer.parseInt(cnt);
			}
		}
		form.setTotalMailCnt(totalMailCnt);

		// 登録可能残数を取得
		int mailCnt = personalMailDao.getMailCnt();
		int validMailCnt = totalMailCnt - mailCnt;
		form.setValidMailCnt(validMailCnt);

		// 個別メール情報を取得
		List<PersonalMailBean> personalMailList = personalMailDao.getPersonalMailList(form,param);
		
		// 「退職者も含む」チェックする、「登録依頼のみ」チェックしない
		List<PersonalMailBean> quitUserList = new ArrayList<PersonalMailBean>();
		if (form.getQuitFlag() != null && "1".equals(form.getQuitFlag()) && !"1".equals(form.getEntryFlag())) {
			// 退職者情報を取得
			quitUserList = personalMailDao.getQuitUserInfoList(form,param);
		}
		
		// ユーザ情報を取得
		List<PersonalMailBean> userInfoList = new ArrayList<PersonalMailBean>();
		// 「登録依頼のみ」チェックしない、「個別設定のみ」チェックしない、「退職者も含む」チェックしない
		if (!"1".equals(form.getEntryFlag()) && !"1".equals(form.getPersonalFlag()) && !"1".equals(form.getQuitFlag())) {
			param.put("PARA_USERDIV", FsPropertyUtil.getStringProperty("mail.userDiv.fieldName"));
			userInfoList = personalMailDao.getUserInfoList(form,param);
		}
		
		// ユーザＩＤリスト
		List<String> userIdList = new ArrayList<String>();
		// 結果リスト
		List<PersonalMailBean> resultList = new ArrayList<PersonalMailBean>();
		
		// 個別メール情報リスト
		if (personalMailList != null) {
			for (int i = 0; i < personalMailList.size(); i ++) {
				// 個別メール情報取得する
				PersonalMailBean bean = (PersonalMailBean) personalMailList.get(i);
				String field = FsPropertyUtil.getStringProperty("mail.userDiv.fieldName");
				// 区分名称を取得
				String mailUserDivName = commonService.getDivisionName(field, bean.getMailUserDivision());
				// 区分名称を設定
				bean.setMailUserDivName(mailUserDivName);
				String field1 = FsPropertyUtil.getStringProperty("profile.div.fieldName");
				// 区分名称を取得
				String profileName = commonService.getDivisionName(field1, bean.getProfileDiv());
				// 区分名称を設定
				bean.setProfileDivName(profileName);
				// 結果リストを追加する（個別メール情報）。
				resultList.add(bean);
				// 結果リストを追加する（ユーザーID）。
				userIdList.add(bean.getUserId());
			}
		}
		// 退職者情報リスト		
		if (quitUserList != null) {
			for (int i = 0; i < quitUserList.size(); i ++) {
				// 個別メール情報取得する
				PersonalMailBean bean = (PersonalMailBean)quitUserList.get(i);
				// 結果リストに退職者のユーザーIDがあります場合、
				if (!userIdList.contains(bean.getUserId()) || "".equals(bean.getUserId())) {
					String field = FsPropertyUtil.getStringProperty("mail.userDiv.fieldName");
					// 区分名称を取得
					String mailUserDivName = commonService.getDivisionName(field, bean.getMailUserDivision());
					// 区分名称を設定
					bean.setMailUserDivName(mailUserDivName);
					String field1 = FsPropertyUtil.getStringProperty("profile.div.fieldName");
					// 区分名称を取得
					String profileName = commonService.getDivisionName(field1, bean.getProfileDiv());
					// 区分名称を設定
					bean.setProfileDivName(profileName);
					// 結果リストを追加する（個別メール情報）。
					resultList.add(bean);
					// 結果リストを追加する（ユーザーID）。
					userIdList.add(bean.getUserId());
				}
			}
		}
		// ユーザマスト情報リスト
		if (userInfoList != null) {
			String userDiv = StringUtil.nullToBlank(form.getSearchUserDiv());
			for (int i = 0; i < userInfoList.size(); i ++) {
				PersonalMailBean bean = (PersonalMailBean)userInfoList.get(i);
				// 本部・店舗社員を設定
				bean.setMailUserDivision(bean.getpEmployeeFlag());
				
				if (!userIdList.contains(bean.getUserId())) {
					// 分類を選択しない
					if ("".equals(userDiv)) {
						resultList.add(bean);
						userIdList.add(bean.getUserId());

					// 本部社員を選択
					} else if ("0".equals(userDiv)) {
						if (!"".equals(bean.getUserId()) && "0".equals(bean.getpEmployeeFlag())) {
							resultList.add(bean);
							userIdList.add(bean.getUserId());
						}

					// 店舗社員を選択
					} else if ("1".equals(userDiv)) {
						if ("1".equals(bean.getpEmployeeFlag())) {
							resultList.add(bean);
							userIdList.add(bean.getUserId());
						}
					}
				}
			}
		}
		
		// 画面ソート
		this.sort(resultList,asc_desc,sort_key);
		// 検索結果リスト
		form.setPersonalMailList(resultList);
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
	public void opeDownloadCsv(HttpServletResponse response,PersonalMailFormBean formBean) throws Exception {
		// CSVファイル名称を取得する。
		String csvName = FsPropertyUtil.getStringProperty("page.csv.name");
		response.setCharacterEncoding("shift_jis");
		response.setContentType("text/plain;charset=shift-jis");
		response.setHeader("Content-Disposition", "attachment; filename=" + getCsvFileName(csvName));
		response.setHeader("Cache-Control","");
		response.setHeader("Pragma","");
		OutputStream os = response.getOutputStream();
		OutputStreamWriter outPut = new OutputStreamWriter(os); 
		BufferedWriter writer = new BufferedWriter(outPut); 
		try {
			//CSVヘッダ
			writer.write("利用者名,");
			writer.write("ID,");
			writer.write("ユーザー名,");
			writer.write("パスワード,");
			writer.write("プロファイル区分,");
			writer.write("分類,");
			writer.write("メールアドレス,");
			writer.write("利用開始日,");
			writer.write("利用終了日,");
			writer.write("所属,");
			writer.write("Softbank登録日,");
			writer.write("退職日,");
			writer.write("Softbank削除日,");
			writer.write("Google削除日");
			writer.write("\r\n");
			// CSV明細
			List<PersonalMailBean> list = (List<PersonalMailBean>)formBean.getPersonalMailCsvList();
			PersonalMailBean row = new PersonalMailBean();
			if (list != null && list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					row = (PersonalMailBean)list.get(i);
					//利用者名
					writer.write(StringUtil.nullToBlank(row.getUserSei()) + StringUtil.nullToBlank(row.getUserMei()) + ",");
					//ID
					writer.write(StringUtil.nullToBlank(row.getUserId()) + ",");
					//ユーザー名
					writer.write(StringUtil.nullToBlank(row.getMailId()) + ",");
					//パスワード
					writer.write(StringUtil.nullToBlank(row.getPassword()) + ",");
					//プロファイル区分
					writer.write(StringUtil.nullToBlank(row.getProfileDivName()) + ",");
					//分類
					writer.write(StringUtil.nullToBlank(row.getMailUserDivName()) + ",");
					//メールアドレス
					writer.write(StringUtil.nullToBlank(row.getMailAddress()) + ",");
					//利用開始日
					writer.write(StringUtil.nullToBlank(row.getMailSet()) + ",");
					//利用終了日
					writer.write(StringUtil.nullToBlank(row.getMailEx()) + ",");
					//所属
					writer.write(StringUtil.nullToBlank(row.getDepartmentName()) + ",");
					//Softbank登録日
					writer.write(StringUtil.nullToBlank(row.getEntryDate()) + ",");
					//退職日
					writer.write(StringUtil.nullToBlank(row.getRetirementDate()) + ",");
					//Softbank削除日
					writer.write(StringUtil.nullToBlank(row.getDeleteDate1()) + ",");
					//Google削除日
					writer.write(StringUtil.nullToBlank(row.getDeleteDate2()));
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
	 * パスワードを取得
	 * @param form
	 *            フォムビーン
	 * */
	public void getAddInfo(PersonalMailFormBean form) {
		// パスワードを取得
		String password = personalMailDao.getPassword();
		// システム日付
		Date date = new Date();
		// 分類を設定
		form.setMailUserDivision("2");
		// フォーマット
		form.setMailSet(StringUtil.formatTheDate(date, "yyyy/MM/dd"));
		// 利用終了日(確定情報)
		form.setMailEx("2199/12/31");
		// パスワードを設定する。
		form.setPassword(password);
	}
	
	/**
	 * 指定利用者個別メール詳細情報
	 * 
	 * @param PersonalMailForm
	 * @return
	 * @throws ServiceException
	 * @throws DataBaseException
	 */
	public void getPersonalMailDetailInfo(PersonalMailFormBean form) {
		PersonalMailBean detailBean = new PersonalMailBean();
		// 個別設定フラグ（ヌル：個別メール存在しない、0：個別メール設定依頼、1：個別メール完了通知）
		if (StringUtil.isEmpty(form.getPersonalMailSettingFlag())) {
			// ユーザマスタの明細データを取得
			detailBean = personalMailDao.getUserMasterDetailInfo(form.getUserId());
			// データがある場合
			if (detailBean != null) {
				// 本部・店舗社員を設定
				if ("1".equals(detailBean.getSpecificUserFlag())) {
					form.setMailUserDivision("1");
				} else {
					if ("1".equals(detailBean.getpEmployeeFlag())) {
						form.setMailUserDivision("1");
					} else {
						form.setMailUserDivision("0");
					}
				}
				// メールアドレス
				String mailAddress = detailBean.getMailAddress();
				// メールアドレスがある場合
				if (!StringUtil.isEmpty(mailAddress)) {
					if (mailAddress.indexOf("@") > 0) {
						// メールアドレス名
						form.setDisplayMailId(mailAddress.substring(0, mailAddress.indexOf("@")));
						// メール種別名
						form.setMailSuffixName(mailAddress.substring(mailAddress.indexOf("@") + 1, mailAddress.length()));
					}
				}
			}
		} else {
			// 個別メールアドレステーブルデータを取得
			detailBean = personalMailDao.getPersonalMailDetailInfo(form.getSequence());
			// データがある場合
			if (detailBean != null) {
				// ステータス
				form.setMailTaskStatus(detailBean.getMailTaskStatus());
				// 分類
				form.setMailUserDivision(detailBean.getMailUserDivision());
				// Softbank登録日
				form.setEntryDate(detailBean.getEntryDate());
				// 退職日
				form.setRetirementDate(detailBean.getRetirementDate());
				// Softbank削除日
				form.setDeleteDate1(detailBean.getDeleteDate1());
				// Google削除日
				form.setDeleteDate2(detailBean.getDeleteDate2());
				// プロファイル区分
				form.setProfileDiv(detailBean.getProfileDiv());
				// メールドメインフラグ
				form.setMailSuffixFlag(detailBean.getMailSuffixFlag());
				// 依頼者ＩＤ
				form.setRequestId(detailBean.getRequestId());
				// 削除フラグ
				form.setDeleteFlag(detailBean.getDeleteFlag());
				// 作業者ＩＤ
				form.setRegistryId(detailBean.getRegistryId());
				// メールアドレス
				String mailAddress = detailBean.getMailAddress();
				// メールアドレスがある場合
				if (!StringUtil.isEmpty(mailAddress)) {
					if (mailAddress.indexOf("@") > 0) {
						// メールアドレス名
						form.setDisplayMailId(mailAddress.substring(0, mailAddress.indexOf("@")));
					}
				}
			}
		}
		if (detailBean != null) {
			// ID
			form.setUserId(detailBean.getUserId());
			// 利用者姓
			form.setUserSei(detailBean.getUserSei());
			// 利用者名
			form.setUserMei(detailBean.getUserMei());
			// メールアドレス
			form.setMailAddress(detailBean.getMailAddress());
			// 利用開始日
			form.setMailSet(detailBean.getMailSet());
			// 利用終了日
			form.setMailEx(detailBean.getMailEx());
			// 所属名称
			form.setDepartmentName(detailBean.getDepartmentName());
			// 所属ID
			form.setDepartmentId(detailBean.getDepartmentId());
			// ユーザー名
			form.setMailId(detailBean.getMailId());
			// パスワード
			form.setPassword(detailBean.getPassword());
		}
	}
	
	/**
	 * アルバイト個別存在性チェック
	 * @param form
	 * @return
	 * @throws ServiceException
	 * @throws DataBaseException
	 */
	public int getValidPersonalCnt(PersonalMailFormBean form) {
		// メールアドレスを取得する。
		String perMailAddr = form.getDisplayMailId() + "@" + form.getMailSuffixName();
		// メールアドレスを設定する。
		form.setMailAddress(perMailAddr);
		return personalMailDao.getValidPersonalCnt(form);
	}
	
	
	/**
	 * 同じ個別メール存在性チェック
	 * @param form
	 * @return
	 * @throws ServiceException
	 * @throws DataBaseException
	 */
	public int getValidMailCnt(PersonalMailFormBean form) {
		// メールアドレスを取得する。
		String perMailAddr = form.getDisplayMailId() + "@" + form.getMailSuffixName();
		// メールアドレスを設定する。
		form.setMailAddress(perMailAddr);
		return personalMailDao.getValidMailCnt(form);
	}
	
	/**
	 * 「設定依頼」ボタン押下
	 * 
	 * @param form
	 *               フォームビーン
	 * @throws Exception
	 */
	public void modifyModeEdit(PersonalMailFormBean form) throws Exception{
		
		// 依頼者ＩＤ
		form.setRequestId(getLoginUserBean().getUserId());
		// メールドメインフラグ
		if(!"1".equals(form.getMailSuffixFlag())) {
			// 作業者ＩＤ
			form.setRegistryId(getLoginUserBean().getUserId());
		}
		
		// 個別メールを物理削除
		personalMailDao.deletePersonalMail(form);
		// 個別メールを登録
		personalMailDao.insertPersonalMail(form);
		// メールを送信情報ビーン
		PersonalMailBean mailInfo = new PersonalMailBean();
		
		// ■１．robuchon.jp
		if ("1".equals(form.getMailSuffixFlag())) {
			// 【イントラ】個別メール設定依頼通知(robuchon.jp)
			setPersonalMailBean(form, mailInfo, ROBUCHON_SETTING_KB);
		// ■２．pizzala.co.jp 或いは four-seeds.jp
		} else {
			// 【イントラ】個別メール登録完了通知
			setPersonalMailBean(form, mailInfo ,PIZZALA_SETTING_KB);
		}
		// メール送信
		sendMail.sendPersonalMail(mailInfo, getLoginUserBean().getUserId());
	}
	
	/**
	 * 「削除」ボタン押下
	 * 
	 * @param form
	 *               フォームビーン
	 * @throws Exception 
	 */	
	public void modifyModeDelete(PersonalMailFormBean form) throws Exception {
		
		// メールを送信情報ビーン
		PersonalMailBean mailInfo = new PersonalMailBean();
		// ■１．robuchon.jp
		if ("1".equals(form.getMailSuffixFlag())) {
			// 個別テーブルを更新
			personalMailDao.updatePersonalMail(form);
			// 【イントラ】個別メール削除設定依頼通知(robuchon.jp)
			setPersonalMailBean(form, mailInfo,ROBUCHON_EXPIRED_KB);
		// ■２．pizza-la.co.jp 或いは four-seeds.jp
		} else {
			// 個別テーブルを論理削除
			personalMailDao.deletePersonalMailInvalid(form);
			// 【イントラ】個別メール削除完了通知
			setPersonalMailBean(form, mailInfo ,PIZZALA_EXPIRED_KB);
		}
		// メール発送
		sendMail.sendPersonalMail(mailInfo, getLoginUserBean().getUserId());
	}
	
	/**
	 * 「完了通知」ボタン押下
	 * 
	 * @param form
	 *               フォームビーン
	 * @throws Exception 
	 */	
	public void modifyModeFinish(PersonalMailFormBean form) throws Exception {
		
		// メールを送信情報ビーン
		PersonalMailBean mailInfo = new PersonalMailBean();

		// 設定依頼中⇒完了通知
		if ("1".equals(form.getMailTaskStatus())) {
			// 作業者ＩＤを設定する。
			form.setRegistryId(getLoginUserBean().getUserId());
			// 個別テーブルを更新
			personalMailDao.updatePersonalMail(form);
			// 設定依頼メールを送信
			setPersonalMailBean(form, mailInfo, ROBUCHON_FINISH_KB);
		// 更新設定依頼中⇒完了通知
		} else if ("2".equals(form.getMailTaskStatus())) {
			// 個別テーブルを論理削除
			personalMailDao.deletePersonalMailInvalid(form);
			// 設定依頼メールを送信
			setPersonalMailBean(form, mailInfo, ROBUCHON_EXPIRED_FINISH_KB);
		}
		// メール発送
		sendMail.sendPersonalMail(mailInfo, getLoginUserBean().getUserId());
	}

	/**
	 * CSV名前
	 * 
	 * @param csvName
	 * @return
	 */
	private String getCsvFileName(String csvName) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return csvName + df.format(new Date()) + ".csv";
	}
	
	/**
	 * @param form
	 * 設定依頼メールを送信
	 * @param mailKb()
	 * @throws Exception 
	 */
	private void setPersonalMailBean(PersonalMailFormBean form, PersonalMailBean bean, String mailKb) throws Exception {
		
		// メールパラメータ
		Map<String, Object> mailParamMap = new HashMap<String, Object>();
		// メール種別を設定する。
		mailParamMap.put("MAIL_FLAG", mailKb);

		// メール送信区分より、メール情報を設定する。
		if(PIZZALA_SETTING_KB.equals(mailKb)) {
			// ■１．【イントラ】個別メール登録完了通知
			pizzalaSetting(bean, form, mailKb, mailParamMap);
		} else if(PIZZALA_EXPIRED_KB.equals(mailKb)) {
			// ■２．【イントラ】個別メール削除完了通知
			pizzalaExpired(bean, form, mailKb, mailParamMap);
		} else if(ROBUCHON_SETTING_KB.equals(mailKb)) {
			// ■３．【イントラ】個別メール設定依頼通知(robuchon.jp)
			robuchonSetting(bean, form, mailKb, mailParamMap);
		} else if(ROBUCHON_FINISH_KB.equals(mailKb)) {
			// ■４．【イントラ】個別メール登録完了通知(robuchon.jp)
			robuchonFinish(bean, form, mailKb, mailParamMap);
		} else if(ROBUCHON_EXPIRED_KB.equals(mailKb)) {
			// ■５．【イントラ】個別メール削除設定依頼通知(robuchon.jp)
			robuchonExpired(bean, form, mailKb, mailParamMap);
		} else if(ROBUCHON_EXPIRED_FINISH_KB.equals(mailKb)) {
			// ■６．【イントラ】個別メール削除完了通知(robuchon.jp)
			robuchonExpiredFinish(bean, form, mailKb, mailParamMap);
		}
		// メールテンプレートを指定する。
		bean.setMailContent(new FreemarkerUtil().getTemplateStr(mailParamMap,"mail_personalMail.ftl"));
		
		// 【イントラ】個別メール設定依頼通知(robuchon.jp) や【イントラ】個別メール削除設定依頼通知(robuchon.jp)場合
		if (ROBUCHON_SETTING_KB.equals(mailKb) || ROBUCHON_EXPIRED_KB.equals(mailKb)) {
			// CSVファイル内容リスト
			writeCsvFileList(bean);
		}
		// 有効宛先、ＣＣメールリストチェック
		checkValidToMail(bean);
	}
	
	/**
	 * 【イントラ】個別メール登録完了通知
	 * @param bean
	 *                 個人メールビーン
	 * @param form
	 *                 個人メールフォーム
	 * @param mailKb
	 *                 ケール種別
	 * @param mailParamMap
	 *                 メール内容パラメータ
	 * @return 
	 *               なし
	 * @throws Exception 
	 * */ 
	private void pizzalaSetting(PersonalMailBean bean, PersonalMailFormBean form, String mailKb,
			Map<String, Object> mailParamMap) throws Exception {
		
		// 宛先ユーザID
		String[] toUserId = FsPropertyUtil.getStringProperty("personal.mail.to.address.pizzala").split(",");
		// CCユーザID
		String[] ccUserId = { form.getRequestId() };
		// 件名 + 登録されたメールアドレスのドメイン
		bean.setMailSubject(FsPropertyUtil.getStringProperty("personal.mail.title.pizzala.setting"));
		// 添付フラグを設定
		bean.setAttachFlag("0");
		// 処理区分を設定（1：「登録」）
		bean.setDealDivisionFlag(FsPropertyUtil.getStringProperty("deal.division.flag.set"));
		// メール共通を設定する。
		setExpiredCommonContent(bean, form, toUserId, ccUserId, mailKb, mailParamMap);
	}
	
	/**
	 * 【イントラ】個別メール削除完了通知
	 * @param bean
	 *                 個人メールビーン
	 * @param form
	 *                 個人メールフォーム
	 * @param mailKb
	 *                 ケール種別
	 * @param mailParamMap
	 *                 メール内容パラメータ
	 * @return 
	 *               なし
	 * @throws Exception 
	 * */ 
	private void pizzalaExpired(PersonalMailBean bean, PersonalMailFormBean form, String mailKb,
			Map<String, Object> mailParamMap) throws Exception {
		// 宛先ユーザID
		String[] toUserId = FsPropertyUtil.getStringProperty("personal.mail.to.address.pizzala").split(",");
		// CCユーザID
		String[] ccUserId = { form.getRequestId() };
		// 件名
		bean.setMailSubject(new String(FsPropertyUtil.getStringProperty("personal.mail.title.pizzala.expired")));
		// 添付フラグを設定
		bean.setAttachFlag("0");
		// 処理区分を設定（3：「削除」）
		bean.setDealDivisionFlag(FsPropertyUtil.getStringProperty("deal.division.flag.delete"));
		// メール共通を設定する。
		setExpiredCommonContent(bean, form, toUserId, ccUserId, mailKb, mailParamMap);
	}
	
	/**
	 * 【イントラ】個別メール設定依頼通知(robuchon.jp)
	 * @param bean
	 *                 個人メールビーン
	 * @param form
	 *                 個人メールフォーム
	 * @param mailKb
	 *                 ケール種別
	 * @param mailParamMap
	 *                 メール内容パラメータ
	 * @return 
	 *               なし
	 * @throws Exception 
	 * */ 
	private void robuchonSetting(PersonalMailBean bean, PersonalMailFormBean form, String mailKb,
			Map<String, Object> mailParamMap) throws Exception {
		// 宛先ユーザーID
		String[] toUserId = FsPropertyUtil.getStringProperty("personal.mail.to.address.robuchon.setting").split(",");
		// CCメールユーザーID配列
		String[] ccUserId = FsPropertyUtil.getStringProperty("personal.mail.cc.address.robuchon.setting").split(",");
		// 件名
		bean.setMailSubject(new String(FsPropertyUtil.getStringProperty("personal.mail.title.robuchon.setting")));
		// ↓↓↓↓↓ CSV用↓↓↓↓↓
		// パスワード
		bean.setPassword(form.getPassword());
		// プロファイル区分
		bean.setProfileDiv(form.getProfileDiv());
		// メールドメインフラグ
		bean.setMailSuffixFlag(form.getMailSuffixFlag());
		// メールアドレス
		bean.setMailAddress(form.getMailAddress());
		// メール：ユーザ名
		bean.setMailId(form.getMailId());
		// 利用者姓
		bean.setUserSei(form.getUserSei());
		// 利用者名
		bean.setUserMei(form.getUserMei());
		// 添付フラグを設定
		bean.setAttachFlag("1");
		// ↑↑↑↑↑ CSV用↑↑↑↑↑
		// 登録可能残数設定
		setValidMailCnt(bean);
		// 登録可能残数：
		mailParamMap.put("VALID_MAIL_CNT", bean.getValidMailCnt());
		// 処理区分を設定（1：「登録」）
		bean.setDealDivisionFlag(FsPropertyUtil.getStringProperty("deal.division.flag.set"));
		// メール共通を設定する。
		setExpiredCommonContent(bean, form, toUserId, ccUserId, mailKb, mailParamMap);
	}

	
	/**
	 * 【イントラ】個別メール登録完了通知(robuchon.jp)
	 * 
	 * @param bean
	 *                 個人メールビーン
	 * @param form
	 *                 個人メールフォーム
	 * @param mailKb
	 *                 ケール種別
	 * @param mailParamMap
	 *                 メール内容パラメータ
	 * @return 
	 *               なし
	 * @throws Exception 
	 * */ 
	private void robuchonFinish(PersonalMailBean bean, PersonalMailFormBean form, String mailKb,
			Map<String, Object> mailParamMap) throws Exception {
		
		// 宛先ユーザーID
		String[] toUserId = { form.getRequestId() };
		// CCメールユーザーID配列
		String[] ccUserId = FsPropertyUtil.getStringProperty("personal.mail.cc.address.robuchon.expired").split(",");
		// 件名
		bean.setMailSubject(new String(FsPropertyUtil.getStringProperty("personal.mail.title.robuchon.finish")));
		// 添付フラグを設定
		bean.setAttachFlag("0");
		// 登録可能残数設定
		setValidMailCnt(bean);
		// 登録可能残数：
		mailParamMap.put("VALID_MAIL_CNT", bean.getValidMailCnt());
		// 処理区分を設定（1：「登録」）
		bean.setDealDivisionFlag(FsPropertyUtil.getStringProperty("deal.division.flag.set"));
		// メール共通を設定する。
		setExpiredCommonContent(bean, form, toUserId, ccUserId, mailKb, mailParamMap);
	}
	
	/**
	 * 【イントラ】個別メール削除設定依頼通知(robuchon.jp)
	 * 
	 * @param bean
	 *                 個人メールビーン
	 * @param form
	 *                 個人メールフォーム
	 * @param mailKb
	 *                 ケール種別
	 * @param mailParamMap
	 *                 メール内容パラメータ
	 * @return 
	 *               なし
	 * @throws Exception 
	 * */ 
	private void robuchonExpired(PersonalMailBean bean, PersonalMailFormBean form, String mailKb,
			Map<String, Object> mailParamMap) throws Exception {
		
		// 宛先ユーザーID
		String[] toUserId = FsPropertyUtil.getStringProperty("personal.mail.to.address.robuchon.expired").split(",");
		// CCメールユーザーID配列
		String[] ccUserId = FsPropertyUtil.getStringProperty("personal.mail.cc.address.robuchon.expired").split(",");
		// 件名
		bean.setMailSubject(new String(FsPropertyUtil.getStringProperty("personal.mail.title.robuchon.expired")));
		// 登録可能残数設定
		setValidMailCnt(bean);
		// ↓↓↓↓↓ CSV用↓↓↓↓↓
		// メールドメインフラグ
		bean.setMailSuffixFlag(form.getMailSuffixFlag());
		// メールアドレス
		bean.setMailAddress(form.getMailAddress());
		// メール：ユーザ名
		bean.setMailId(form.getMailId());
		// 利用者姓
		bean.setUserSei(form.getUserSei());
		// 利用者名
		bean.setUserMei(form.getUserMei());
		// 添付フラグを設定
		bean.setAttachFlag("1");
		// ↑↑↑↑↑ CSV用↑↑↑↑↑
		// 登録可能残数：
		mailParamMap.put("VALID_MAIL_CNT", bean.getValidMailCnt());
		// 処理区分を設定（3：「削除」）
		bean.setDealDivisionFlag(FsPropertyUtil.getStringProperty("deal.division.flag.delete"));
		// メール共通を設定する。
		setExpiredCommonContent(bean, form, toUserId, ccUserId, mailKb, mailParamMap);
	}
	
	
	/**
	 * 【イントラ】個別メール削除完了通知(robuchon.jp)
	 * 
	 * @param bean
	 *                 個人メールビーン
	 * @param form
	 *                 個人メールフォーム
	 * @param mailKb
	 *                 ケール種別
	 * @param mailParamMap
	 *                 メール内容パラメータ
	 * @return 
	 *               なし
	 * @throws Exception 
	 * */ 
	private void robuchonExpiredFinish(PersonalMailBean bean, PersonalMailFormBean form, String mailKb,
			Map<String, Object> mailParamMap) throws Exception {
		
		// TOメールユーザーID配列
		String[] toUserId = { form.getRequestId() };
		// CCメールユーザーID配列
		String[] ccUserId = FsPropertyUtil.getStringProperty("personal.mail.cc.address.robuchon.expired").split(",");
		// 件名
		bean.setMailSubject(new String(FsPropertyUtil.getStringProperty("personal.mail.title.robuchon.expired.finish")));
		// 登録可能残数設定
		setValidMailCnt(bean);
		// 登録可能残数：
		mailParamMap.put("VALID_MAIL_CNT", bean.getValidMailCnt());
		// 添付フラグを設定
		bean.setAttachFlag("0");
		// 処理区分を設定（3：「削除」）
		bean.setDealDivisionFlag(FsPropertyUtil.getStringProperty("deal.division.flag.delete"));
		// メール共通を設定する。
		setExpiredCommonContent(bean, form, toUserId, ccUserId, mailKb, mailParamMap);
	}
	
	/**
	 * CSVファイル内容リスト
	 * @param personalMailBean
	 * @throws Exception
	 */
	private void writeCsvFileList(PersonalMailBean bean) throws Exception {
		List<String> csvList = new ArrayList<String>();
		List<String> list = new ArrayList<String>();
		// 処理区分フラグ（新規：1　削除：3）
		String dealDivision = "";
		if ("1".equals(StringUtil.nullToBlank(bean.getDealDivisionFlag()))) {
			dealDivision = "CREATE";
		} else if ("3".equals(StringUtil.nullToBlank(bean.getDealDivisionFlag()))) {
			dealDivision = "DELETE";
		}
		list.add(dealDivision);
		
		// メール区分表示名
		String fieldName = FsPropertyUtil.getStringProperty("mail.suffix.fieldName");
		String div = commonService.getDispDivisionName(fieldName, bean.getMailSuffixFlag());
		list.add(div);
		
		// メンテ画面のユーザ姓
		list.add(StringUtil.nullToBlank(bean.getUserSei()));
		// メンテ画面のユーザ名
		list.add(StringUtil.nullToBlank(bean.getUserMei()));
		// メンテ画面のユーザ姓+名
		list.add(StringUtil.nullToBlank(bean.getUserSei()) + StringUtil.nullToBlank(bean.getUserMei()));
		
		// アカウントID（画面のユーザー名）
		list.add(StringUtil.nullToBlank(bean.getMailId()));
				
		// メンテ画面のパスワード、登録時のみ
		if (FsPropertyUtil.getStringProperty("deal.division.flag.set").equals(bean.getDealDivisionFlag()) && bean.getPassword() != null) {
			list.add(bean.getPassword());
		} else {
			list.add("");
		}
		// パスワード変更フラグ「0」固定
		list.add("FALSE");
		// 役職、省略
		list.add("");
		// 備考
		list.add("依頼日：　" + DateUtil.getNowDate("yyyy/MM/dd"));
		
		// プロファイル名、登録時のみ
		if (bean.getProfileDiv() != null) {
			String field = FsPropertyUtil.getStringProperty("profile.div.fieldName");
			String profileName = commonService.getDivisionName(field, bean.getProfileDiv());
			list.add(profileName);
		} else {
			list.add("");
		}
		
		list.add("TRUE");
		
		// アカウント　＋　@　＋　ドメイン
		if (bean.getMailId() != null && div != null && !"".equals(div)) {
			list.add(bean.getMailId() + "@" + div);
		} else {
			list.add("");
		}
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			sb.append("\"");
			String content = (String) list.get(i);
			sb.append(content);
			if (i != list.size()-1) {
            	sb.append("\",");
            } else {
            	sb.append("\"");
            }
		}
		
		// CSVTitle追加
        String csvTitle = FsPropertyUtil.getStringProperty("personal.mail.csvTitle");
        csvList.add(csvTitle);

		// CSV内容リスト１件を追加
		csvList.add(sb.toString());

		// robuchon.jp場合、CSVファイルリスト
		String now = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		// CSVパス
		String path = FsPropertyUtil.getStringProperty("csv.file.path");
		// ファイル名前
		bean.setFilePath(path);
		// CSVファイル名前
		String fileName = FsPropertyUtil.getStringProperty("csv.file.name") + now;
		// ファイル名前
		bean.setFileName(fileName);
		// CSV内容リスト
		bean.setCsvInfoList(csvList);
	}

	/**
	 * 有効宛先、ＣＣメールリストチェック
	 * @param bean
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void checkValidToMail(PersonalMailBean bean) throws Exception {
		List<String> toList = bean.getToMailList();
		List<String> newList = new ArrayList<String>();
		String toMail = new String(FsPropertyUtil.getStringProperty("personal.mail.to.address"));
		if (toList == null || toList.size() == 0) {
			newList.add(toMail);
			bean.setToMailList(newList);
		}
	}
	
	/**
	 * メール共通
	 * 
	 * @param bean
	 *                 個人メールビーン
	 * @param form
	 *                 個人メールフォーム
	 * @param toMailList
	 *                 宛先ユーザーアドレス
	 * @param toNameList
	 *                 宛先ユーザー名称
	 * @param toUserId
	 *                 宛先ユーザーID
	 * @param ccUserId
	 *                 CCメールユーザーID配列
	 * @param mailKb
	 *                 ケール種別
	 * @param mailParamMap
	 *                 メール内容パラメータ
	 * @return 
	 *               なし
	 * @throws Exception 
	 * 
	 * */
	private void setExpiredCommonContent(PersonalMailBean bean, PersonalMailFormBean form, String[] toUserId,
			String[] ccUserId, String mailKb,Map<String, Object> mailParamMap) throws Exception {
		
		// 宛先ユーザーアドレス
		List<String> toMailList = new ArrayList<String>();
		// 宛先ユーザー名称
		List<String> toNameList = new ArrayList<String>();
		// CCユーザーID
		List<String> ccIdList = new ArrayList<String>();
		// CCユーザーメールアドレス
		List<String> ccMailList = new ArrayList<String>();
		// 送信者名前(設定依頼者の名称)
		String fromUserName;
		
		// 送信者メール
		bean.setFromMail(new String(FsPropertyUtil.getStringProperty("personal.mail.from.address")));
				
		// 宛先ユーザーIDより、送信メールと送信ユーザー名称を設定する
		setMailToName(toUserId, toMailList, toNameList, bean);
		
		// CCユーザーIDより、CCメールアドレを設定する
		setMailcc(ccIdList, ccUserId, ccMailList, form, bean, mailKb);
		
		// 【イントラ】個別メール登録完了通知(robuchon.jp) OR 【イントラ】個別メール削除完了通知(robuchon.jp)の場合
		if(mailKb.equals(ROBUCHON_FINISH_KB) || mailKb.equals(ROBUCHON_EXPIRED_FINISH_KB)) {
			// 送信者名前(設定作業者の名称)
			fromUserName = personalMailDao.getFromUserName(form.getRegistryId());
		} else{
			// 送信者名前(設定依頼者の名称)
			fromUserName = personalMailDao.getFromUserName(form.getRequestId());
		}
		// 送信者名前を設定する。
		bean.setFromName(fromUserName);
		
		// 宛先
		StringBuffer toUserNameBuff = new StringBuffer();
		if (toNameList != null && toNameList.size() > 0) {
			for (int i = 0; i < toNameList.size(); i++) {
				String toUserName = (String) toNameList.get(i);
				toUserNameBuff.append(toUserName);
				// 宛先の名称中「、」ご間隔
				if (i != toNameList.size()-1) {
					toUserNameBuff.append(new String(FsPropertyUtil.getStringProperty("personal.mail.common.content.head1")));
					toUserNameBuff.append("、");
				} else {
					toUserNameBuff.append(new String(FsPropertyUtil.getStringProperty("personal.mail.common.content.head1")));
				}
			}
		} else {
			// 宛先不在の場合は、”＜宛先不在＞”とする
			toUserNameBuff.append(new String(FsPropertyUtil.getStringProperty("personal.mail.no.mailto.username")));
		}
		// 宛先
		mailParamMap.put("MAIL_TO_NAME", toUserNameBuff.toString());
		// 送信者の名称
		if (!StringUtil.isEmpty(bean.getFromName())) {
			mailParamMap.put("MAIL_FROM_NAME", bean.getFromName());
		// 送信者名前存在しないの場合、「イントラ保守担当」を利用する
		} else {
			mailParamMap.put("MAIL_FROM_NAME", FsPropertyUtil.getStringProperty("personal.mail.from.name"));
		}
		// ID：
		mailParamMap.put("MAIL_USER_ID", StringUtil.nullToBlank(form.getUserId()));
		// 名称：		
		mailParamMap.put("MAIL_USER_NAME", StringUtil.nullToBlank(form.getUserSei()) + StringUtil.nullToBlank(form.getUserMei()));
		// 登録者分類：
		String fieldName = FsPropertyUtil.getStringProperty("mail.userDiv.fieldName");
		// IDより名称を取得
		String mailUserDivName = commonService.getDivisionName(fieldName, form.getMailUserDivision());
		// 取得の名称
		mailParamMap.put("MAIL_USER_DIV_NAME", StringUtil.nullToBlank(mailUserDivName));
		// メールアドレス：
		mailParamMap.put("MAIL_ADDRESS", StringUtil.nullToBlank(form.getMailAddress()));
	}
	
	/**
	 * 宛先ユーザーIDより、送信メールと送信ユーザー名称を設定する
	 * 
	 * @param bean
	 *                 個人メールビーン
	 * @param toMailList
	 *                 宛先ユーザーアドレス
	 * @param toNameList
	 *                 宛先ユーザー名称
	 * @param toUserId
	 *                 宛先ユーザーID
	 * @return 
	 *               なし
	 * 
	 * */ 
	private void setMailToName(String[] toUserId,List<String> toMailList,List<String> toNameList,PersonalMailBean bean) {
		// 宛先ユーザーIDがある場合
		if (toUserId != null && toUserId.length > 0) {
			for (int i = 0; i < toUserId.length; i ++) {
				// ユーザのメールとユーザ名前を取得
				PersonalMailBean userInfoBean = personalMailDao.getMailToUserInfo(toUserId[i]);
				// ユーザのメールとユーザ名前が取得される場合
				if (userInfoBean != null) {
					// 送信メール
					toMailList.add(userInfoBean.getuMail());
					// 送信ユーザー名称
					toNameList.add(userInfoBean.getuName());
				}
			}
		}
		// 宛先ユーザーメールリスト
		bean.setToMailList(toMailList);
		// 宛先ユーザー名称リスト
		bean.setToNameList(toNameList);
		
	}
	
	/**
	 * CCユーザーIDより、CCメールアドレを設定する
	 * 
	 * @param ccIdList
	 *                 CCユーザーID
	 * @param ccMailList
	 *                 CCユーザーメールアドレス
	 * @param ccUserId
	 *                 CCメールユーザーID配列
	 * @param form
	 *                 個人メールフォーム
	 * @param bean
	 *                 個人メールビーン
	 * @param mailKb
	 *                 メール種別
	 * @return 
	 *               なし
	 * 
	 * */ 
	private void setMailcc(List<String> ccIdList,String[] ccUserId,List<String> ccMailList,
			PersonalMailFormBean form,PersonalMailBean bean,String mailKb) {
		// CCメールユーザーIDがある場合。
		if (ccUserId != null && ccUserId.length > 0) {
			for (int i = 0; i < ccUserId.length; i ++) {
				// ユーザのメールとユーザ名前を取得
				PersonalMailBean userInfoBean = personalMailDao.getMailToUserInfo(ccUserId[i]);
				// ユーザのメールとユーザ名前が取得される場合
				if (userInfoBean != null) {
					// CCメールアドレスを追加する。
					ccMailList.add(userInfoBean.getuMail());
					// CCユーザーIDを追加する。
					ccIdList.add(ccUserId[i]);
				}
			}
		}
		// 依頼者と作業者はCCMailに追加。
		if (ROBUCHON_SETTING_KB.equals(mailKb)) {
			// ■登録依頼通知(robuchon.jp)
			// 依頼者のメールを取得
			if (!StringUtil.isEmpty(form.getRequestId())) {
				// ユーザのメールとユーザ名前を取得
				PersonalMailBean userInfoBean = personalMailDao.getMailToUserInfo(form.getRequestId());
				// ユーザのメールとユーザ名前を取得できる且CCユーザーIDの作業者ＩＤがありません場合
				if (userInfoBean != null && !ccIdList.contains(form.getRegistryId())) {
					// CCユーザーメールアドレスを追加する。
					ccMailList.add(userInfoBean.getuMail());
				}
			}
		} else if(ROBUCHON_EXPIRED_KB.equals(mailKb)) {
			// ■削除設定依頼通知(robuchon.jp)
			// 依頼者のメールを取得
			if (!StringUtil.isEmpty(form.getRequestId())) {
				// ユーザのメールとユーザ名前を取得
				PersonalMailBean userInfoBean = personalMailDao.getMailToUserInfo(form.getRequestId());
				// ユーザのメールとユーザ名前を取得できる且CCユーザーIDの作業者ＩＤがありません場合
				if (userInfoBean != null && !ccIdList.contains(form.getRegistryId())) {
					// CCユーザーメールアドレスを追加する。
					ccMailList.add(userInfoBean.getuMail());
				}
			}
		} else if(ROBUCHON_FINISH_KB.equals(mailKb) || ROBUCHON_EXPIRED_FINISH_KB.equals(mailKb)) {
			// ■登録完了通知(robuchon.jp) OR 削除完了通知(robuchon.jp)
			// 依頼者のメールを取得
			if (!StringUtil.isEmpty(form.getRegistryId())) {
				// ユーザのメールとユーザ名前を取得
				PersonalMailBean userInfoBean = personalMailDao.getMailToUserInfo(form.getRegistryId());
				// ユーザのメールとユーザ名前を取得できる且CCユーザーIDの作業者ＩＤがありません場合
				if (userInfoBean != null && !ccIdList.contains(form.getRegistryId())) {
					// CCユーザーメールアドレスを追加する。
					ccMailList.add(userInfoBean.getuMail());
				}
			}
		}
		
		// CCメールアドリスを追加する。
		bean.setCcMailList(ccMailList);
	}
	
	/**
	 * 登録可能残数設定
	 * @param bean メールビーン
	 * 
	 * */
	private void setValidMailCnt (PersonalMailBean bean) {
		// 全件数
		int totalMailCnt = 0;
		List<LabelValueBean> labelList = commonService.getDivisionMaster(FIELD_NAME_MAX_MAIL_NUM);
		if (labelList.size()!=0) {
			String cnt = labelList.get(0).getValue();
			if (NumberUtil.isNumber(cnt)) {
				totalMailCnt = Integer.parseInt(cnt);
			}
		}
		// 登録済件数
		int mailCnt = personalMailDao.getMailCnt();
		// 登録可能残数を取得
		int validMailCnt = totalMailCnt - mailCnt;
		// 登録可能残数設定
		bean.setValidMailCnt(validMailCnt);
	}
	
	/**
	 * リストソート
	 * 
	 * @param asc_desc
	 *           ソート降順・昇順
	 * @param sort_key
	 *           ソート項目
	 *
	 * @return なし
	 */
	public void sort (List<PersonalMailBean> oldList,String asc_desc,String sort_key) {
		// 初期場合、ソートしない
		if (StringUtil.isEmpty(sort_key)) {
			return;
		}
		if ("asc".equals(asc_desc)) {
			// 昇順
			if ("userId".equals(sort_key)) {
				// ID昇順
				Collections.sort(oldList,userIdAsc);
			} else if("userName".equals(sort_key)) {
				// 利用者名昇順
				Collections.sort(oldList,userNameAsc);
			} else if("mailAddress".equals(sort_key)) {
				// メールアドレース昇順
				Collections.sort(oldList,mailAddressAsc);
			} else if("mailSet".equals(sort_key)) {
				// 開始日昇順
				Collections.sort(oldList,mailSetAsc);
			} else if("mailEx".equals(sort_key)) {
				// 終了日昇順
				Collections.sort(oldList,mailExAsc);
			} else if("departmentName".equals(sort_key)) {
				// 所属昇順
				Collections.sort(oldList,departmentNameAsc);
			}
		} else {
			// 降順
			if ("userId".equals(sort_key)) {
				// ID降順
				Collections.sort(oldList,userIdDesc);
			} else if("userName".equals(sort_key)) {
				// 利用者名降順
				Collections.sort(oldList,userNameDesc);
			} else if("mailAddress".equals(sort_key)) {
				// メールアドレース降順
				Collections.sort(oldList,mailAddressDesc);
			} else if("mailSet".equals(sort_key)) {
				// 開始日降順
				Collections.sort(oldList,mailSetDesc);
			} else if("mailEx".equals(sort_key)) {
				// 終了日降順
				Collections.sort(oldList,mailExDesc);
			} else if("departmentName".equals(sort_key)) {
				// 所属降順
				Collections.sort(oldList,departmentNameDesc);
			}
		}
	}
	
	/** ID昇順 */
	Comparator<PersonalMailBean> userIdAsc = new Comparator<PersonalMailBean>()  {
		public int compare(PersonalMailBean arg1, PersonalMailBean arg0) {
			return arg1.getUserId().compareTo(arg0.getUserId());
		}
	};
	/** 利用者名昇順 */
	Comparator<PersonalMailBean> userNameAsc = new Comparator<PersonalMailBean>()  {
		public int compare(PersonalMailBean arg1, PersonalMailBean arg0) {
			return (StringUtil.nullToBlank(arg1.getUserSei()) + StringUtil.nullToBlank(arg1.getUserMei()))
					.compareTo(StringUtil.nullToBlank(arg0.getUserSei()) + StringUtil.nullToBlank(arg0.getUserMei()));
		}
	};
	/** メールアドレース昇順 */
	Comparator<PersonalMailBean> mailAddressAsc = new Comparator<PersonalMailBean>()  {
		public int compare(PersonalMailBean arg1, PersonalMailBean arg0) {
			return arg1.getMailAddress().compareTo(arg0.getMailAddress());
		}
	};
	/** 開始日昇順 */
	Comparator<PersonalMailBean> mailSetAsc = new Comparator<PersonalMailBean>()  {
		public int compare(PersonalMailBean arg1, PersonalMailBean arg0) {
			return arg1.getMailSet().compareTo(arg0.getMailSet());
		}
	};
	/** 終了日昇順 */
	Comparator<PersonalMailBean> mailExAsc = new Comparator<PersonalMailBean>()  {
		public int compare(PersonalMailBean arg1, PersonalMailBean arg0) {
			return arg1.getMailEx().compareTo(arg0.getMailEx());
		}
	};
	/** 所属昇順 */
	Comparator<PersonalMailBean> departmentNameAsc = new Comparator<PersonalMailBean>()  {
		public int compare(PersonalMailBean arg1, PersonalMailBean arg0) {
			return arg1.getDepartmentName().compareTo(arg0.getDepartmentName());
		}
	};
	
	/** ID降順 */
	Comparator<PersonalMailBean> userIdDesc = new Comparator<PersonalMailBean>()  {
		public int compare(PersonalMailBean arg1, PersonalMailBean arg0) {
			return arg0.getUserId().compareTo(arg1.getUserId());
		}
	};
	/** 利用者名降順 */
	Comparator<PersonalMailBean> userNameDesc = new Comparator<PersonalMailBean>()  {
		public int compare(PersonalMailBean arg1, PersonalMailBean arg0) {
			return (StringUtil.nullToBlank(arg0.getUserSei()) + StringUtil.nullToBlank(arg0.getUserMei()))
					.compareTo(StringUtil.nullToBlank(arg1.getUserSei()) + StringUtil.nullToBlank(arg1.getUserMei()));
		}
	};
	/** メールアドレース降順 */
	Comparator<PersonalMailBean> mailAddressDesc = new Comparator<PersonalMailBean>()  {
		public int compare(PersonalMailBean arg1, PersonalMailBean arg0) {
			return arg0.getMailAddress().compareTo(arg1.getMailAddress());
		}
	};
	/** 開始日降順 */
	Comparator<PersonalMailBean> mailSetDesc = new Comparator<PersonalMailBean>()  {
		public int compare(PersonalMailBean arg1, PersonalMailBean arg0) {
			return arg0.getMailSet().compareTo(arg1.getMailSet());
		}
	};
	/** 終了日降順 */
	Comparator<PersonalMailBean> mailExDesc = new Comparator<PersonalMailBean>()  {
		public int compare(PersonalMailBean arg1, PersonalMailBean arg0) {
			return arg0.getMailEx().compareTo(arg1.getMailEx());
		}
	};
	/** 所属降順 */
	Comparator<PersonalMailBean> departmentNameDesc = new Comparator<PersonalMailBean>()  {
		public int compare(PersonalMailBean arg1, PersonalMailBean arg0) {
			return arg0.getDepartmentName().compareTo(arg1.getDepartmentName());
		}
	};
}