package jp.co.fourseeds.fsnet.service;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.fourseeds.fsnet.beans.LoginImageFormBean;
import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.MailBean;
import jp.co.fourseeds.fsnet.beans.personalMail.PersonalMailBean;
import jp.co.fourseeds.fsnet.beans.test.TestBean;
import jp.co.fourseeds.fsnet.common.ConstantContainer;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.LoginDao;
import jp.co.fourseeds.fsnet.dao.PersonalMailDao;
import jp.co.fourseeds.fsnet.logic.LoginLogic;

import jp.co.common.frame.service.BaseBusinessService;
import jp.co.common.frame.util.DateUtil;
import jp.co.common.frame.util.FreemarkerUtil;
import jp.co.common.frame.util.MailUtil;
import jp.co.common.frame.util.prop.FsPropertyUtil;

/**
 *  ログイン機能サービス実装。
 * 
 * @author NTS
 * @version 1.0.0 : 2015.09.14 新規作成
 * 
 * @author NTS
 * @version 1.1.0 : 2017.10.13 見直し修正
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class LoginService extends BaseBusinessService{

	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private PersonalMailDao personalMailDao;

	@Autowired
	private LoginLogic loginLogic;
	
	/**
	 * 登録ユーザーのIDとパスワードによって、ユーザーの情報を取る
	 * @param userId 
	 *             ユーザーID
	 * @param password 
	 *             パスワード
	 * @return loginUser 
	 *             ログインユーザーの情報
	 * @throws Exception 
	 */
	public LoginUserBean updateLoginUserInfo(String userId, String password) throws Exception{
		String sendMailFlag = "0";
		LoginUserBean loginUser = loginDao.getLoginUserInfo(userId, password, sendMailFlag);
//		事務検証
		loginLogic.upA(password);
		System.out.print("success!");
		System.out.println("error");
		loginLogic.upB(password);
		return loginUser;
	}
	
	public LoginUserBean getLoginInfo(String userId) {
		String sendMailFlag = "1";
		LoginUserBean loginUser = loginDao.getLoginUserInfo(userId, null, sendMailFlag);
		return loginUser;
	}
	
	/**
	 * 画面のメイン画像取得
	 * @return
	 * @throws Exception
	 */
	public String getLoginImageMainUrl() throws Exception{
		// 現時点で有効なデータは取得する
		LoginImageFormBean loginImageBean = loginDao.getLoginImageMainUrl();
		if (loginImageBean == null) {
			return "";
		}
		// 画像ファイルパススを取得
		String filePath = FsPropertyUtil.getStringProperty("loginImage.url.path") + ConstantContainer.MAIN_IMG_PATH + loginImageBean.getLoginImageId();
		// 有効画像リスト
		List<String> imgList = new ArrayList<String>();
		for (int i = 1; i <= 10; i++) {
			// 画像iファイル名を取得
			String imgFileName = (String) getMethodValue(loginImageBean, "img" + i + "FileName");
			// 画像i表示フラグが「１」、画像iファイル名存在の場合
			if (!StringUtil.isBlank(imgFileName)) {
				// 画像ファイルの拡張子を取得
				String suffix = imgFileName.substring(imgFileName.lastIndexOf("."));
				// 有効画像ファイルパススを取得
				String imgUrl = filePath + "/" + loginImageBean.getLoginImageId() + i + suffix;
				imgList.add(imgUrl);
			}
		}
		if (imgList.size() == 0) {
			return "";
		}
		// 画像の掲載開始日を取得
		String baseDate = loginImageBean.getStartDate();
		// システム日付を取得
		String nowDate = DateUtil.getNowDate("yyyy/MM/dd");
		// 日付期間を取得
		int baseCount = DateUtil.dateDiff(baseDate, nowDate, "yyyy/MM/dd");
		// 現時点で表示画像を取得
		int listAt = baseCount % imgList.size();
		return imgList.get(listAt);
	}
	
	private Object getMethodValue(LoginImageFormBean loginImageBean, String methodKey) throws Exception {
		Class<? extends LoginImageFormBean> clazz = loginImageBean.getClass();
		PropertyDescriptor pd = new PropertyDescriptor(methodKey, clazz);
		Method getMethod = pd.getReadMethod();
		return getMethod.invoke(loginImageBean);
	}

	/**
	 * トップグループ情報とサブユーザ情報の設定
	 * @param loginUser 
	 *           ログインユーザー情報
	 * @return   なし
	 * */
	public void setUserInfoList(LoginUserBean loginUser) {
		// 所属ユーザグループリスト
		List<String> userGroupList = null;

		userGroupList = loginDao.getTopGroupList(loginUser);
		
		// ユーザ所属グループを設定する。
		loginUser.setTopGroupList(userGroupList);
	}

	/**
	 * メールアドレスへ送信
	 * @param userId
	 *         ユーザーID
	 * @param mailFlag
	 *         メールフラグ（C:パスワード更新　S:パスワード再送　P:プロファイルパスワード変更）
	 * @param user
	 *         
	 * @return 
	 *         送信結果(Y:送信済　M:メールアドレースなし　U：パスワード情報なし)
	 * */
	public String sendPassword(LoginUserBean userResult, String mailFlag, HttpServletRequest request) throws Exception {
		// パスワード取得できない場合
		if (userResult == null || StringUtil.isEmpty(userResult.getPassword())) {
			// パスワード存在しません
			return "U";
		}
		String userId = userResult.getUserId();
		// メールアドレス情報を取得
		String toMail = userResult.getMail();
		// メールアドレース取得できない場合
		if (toMail == null || toMail.isEmpty()) {
			// メール存在しません
			return "M";
		}
		// （漢字）姓
		String firstName = userResult.getKanjiSei();
		// （漢字）名
		String lastName = userResult.getKanjiMei();
		// ユーザー名称を設定する。
		String user = userResult.getKanjiSei() + userResult.getKanjiMei() + "(" + userId + ")";
		// 転送先メールアドレスを取得する
		String trmailAddress = userResult.getTrmailAddress();
		// メール内容設定
		MailBean mailInfo = new MailBean();
		// 基盤メール送信
		MailUtil mail = new MailUtil();
		mailInfo.setMailType(MailBean.MAIL_TYPE_HTML);
		mailInfo.setFrom(FsPropertyUtil.getStringProperty("mail.user.address"));
		// 宛先設定
		mailInfo.setTo(new String[] { toMail });
		// 転送先メールアドレスを設定する。
		mailInfo.setCc(StringUtil.isEmpty(trmailAddress) ? null : new String[] { trmailAddress });
		// 機能名称
		String functionNm = "";
		// メールパラメータ
		Map<String, Object> rootMap = new HashMap<String, Object>();
		// パスワード再送
		if(mailFlag.equals("S")){
			// 機能名称を設定する。
			functionNm =new String(FsPropertyUtil.getStringProperty("passwordsend.context"));
			// メールタイトル
			mailInfo.setSubject(FsPropertyUtil.getStringProperty("mail.subject.send"));
			
			String accessTime = String.valueOf(new GregorianCalendar().getTimeInMillis());
			// 暗号キー
			String accessKey;
			boolean flag;
			do {
				// 暗号キーを取得
				accessKey = loginDao.getAccessKey();
				// 暗号キーフラグ（true:暗号キー存在  false:暗号キー不存在）
				flag = loginDao.searchAccessKey(accessKey);
				
				if (!flag) {
					// 暗号キ不存在の場合、パスワード再発行制御データを設定する
					setPwCtrlInfo(userId, accessKey, accessTime);
				}
			} while (flag);
			// メールURL
			String mailUrl = "http://" + request.getServerName() + request.getContextPath() + "/login_loginMail.do?accessKey=" + accessKey;
			
			int lineMinute = FsPropertyUtil.getIntProperty("password.expired.minutes");
			rootMap.put("lineMinute", lineMinute);
			rootMap.put("linkURL", mailUrl);
		// パスワード変更
		} else {
			// 機能名称を設定する。
			functionNm = FsPropertyUtil.getStringProperty("passwordupdate.context");
			// メールタイトル
			mailInfo.setSubject(FsPropertyUtil.getStringProperty("mail.subject.change"));
		}
		// メールフラグ（C:パスワード更新　S:パスワード再送）
		rootMap.put("mailFlag", mailFlag);
		// ユーザー名
		rootMap.put("userName", firstName + " " + lastName);
		// TEL
		rootMap.put("tel", FsPropertyUtil.getStringProperty("login.tel"));
		// メールTO
		rootMap.put("mailto", FsPropertyUtil.getStringProperty("login.mailto"));
		// メールフラグより、メールテンプレートを指定する。
		mailInfo.setContent(new FreemarkerUtil().getTemplateStr(rootMap,"mail_send_password.ftl"));
		mail.sendMail(mailInfo,user,functionNm);
		// 送信済み
		return "Y";
	}

	/**
	 * パスワード変更
	 * @param userId
	 *           ユーザーID
	 * @param newPassword
	 *           新パースワード
	 * @return
	 *           なし
	 * */
	public void updatePassword(String userId, String newPassword) {
		loginDao.updatePassword(userId, newPassword);
	}

	
	/**
	 * デバイス別機能別制御情報取得
	 */
	@SuppressWarnings("rawtypes")
	public List getDeviceControlList(String userAgent) {
		return loginDao.getDeviceControlList(userAgent);
	}

	/** 作成中コンテンツ存在フラグ */
	public void setConfirmFlag(LoginUserBean loginUser) {

		String flag = loginDao.getConfirmFlag(loginUser.getUserId());

		loginUser.setConfirmFlag(flag);
	}
	
	/** 公開待ちコンテンツ存在フラグ */
	public void setFetureFlag(LoginUserBean loginUser) {

		String flag = loginDao.getFetureFlag(loginUser.getUserId());

		loginUser.setFetureFlag(flag);
	}
	
	/** テンプレートコンテンツ存在フラグ */
	public void setTemplateFlag(LoginUserBean loginUser) {

		String flag = loginDao.getTemplateFlag(loginUser.getUserId(), loginUser.getRole());

		loginUser.setTemplateFlag(flag);
	}
	
	/** 期限切れコンテンツ存在フラグ */
	public void setPastFlag(LoginUserBean loginUser) {

		String pastflag = loginDao.getPastFlag(loginUser.getUserId());

		loginUser.setPastFlag(pastflag);
	}
	
	/**
	 * <p>
	 * 画面の画像取得
	 * </p>
	 * 
	 * @param displayPosition 
	 *         バナー
	 * @return LoginImageFormBean
	 *         画像情報
	 * */
	public LoginImageFormBean getLoginImage(String displayPosition) {
		return loginDao.getLoginImage(displayPosition);
	}
	
	/**
	 * パスワード再発行制御テーブルにデータ登録
	 */
	public void setPwCtrlInfo(String userId, String accessKey, String accessTime) {
		loginDao.insertPwCtrlInfo(userId, accessKey, accessTime);
	}
	
	/**
	 * パスワード再発行制御テーブルのデータを削除
	 */
	public void deletePwCtrlInfo(String userId) {
		loginDao.deletePwCtrlInfo(userId);
	}

	/**
	 * パスワード再発行制御テーブルのアクセス時間を取得
	 */
	public LoginUserBean getAccessTimeInfo(String accessKey) {
		return loginDao.getAccessTimeInfo(accessKey);
	}
	
	/**
	 * メールアドレスのチェック、メールアドレスなし通知送信処理する。
	 * @param loginUser 
	 *           ログインユーザー情報
	 * @return
	 *           true:メールアドレスが登録
	 *           false:メールアドレスが登録されていない
	 * */
	public Boolean checkMail(LoginUserBean userResult, HttpServletRequest request) throws Exception {
		
		// メールアドレス情報を取得
		String vaildMail = userResult.getMail();
		// メールアドレース取得できない
		if (vaildMail == null || vaildMail.isEmpty() || ("").equals(vaildMail)) {
			// メール通知不要フラグ:通知の場合
			if (("0").equals(userResult.getMailNoticeFlag())) {
				String userId = userResult.getUserId();
				// （漢字）姓
				String firstName = userResult.getKanjiSei();
				// （漢字）名
				String lastName = userResult.getKanjiMei();
				// ユーザー名称を設定する。
				String user = userResult.getKanjiSei() + userResult.getKanjiMei() + "(" + userId + ")";
				// 機能名称
				String functionNm = "";
				// メール内容設定
				MailBean mailInfo = new MailBean();
				// 基盤メール送信
				MailUtil mail = new MailUtil();
				mailInfo.setMailType(MailBean.MAIL_TYPE_HTML);
				mailInfo.setFrom(FsPropertyUtil.getStringProperty("mail.user.address"));
				// 受信ユーザID
				String[] toUserId;
				if (ConstantContainer.USER_ORIGINAL.equals(userResult.getOriginalFlag())) {
					toUserId = FsPropertyUtil.getStringProperty("notice.mail.address.to.honbu").split(",");
				} else {
					toUserId = FsPropertyUtil.getStringProperty("notice.mail.address.to.helpdesk").split(",");
				}
				// 宛先ユーザーアドレス
				String[] toMail = null;
				// 宛先ユーザー名称
				String[] toName = null;
				// 宛先ユーザーIDより、送信メールと送信ユーザー名称を設定する
				// 宛先ユーザーIDがある場合
				if (toUserId != null && toUserId.length > 0) {
					toMail = new String[toUserId.length];
					toName = new String[toUserId.length];
					for (int i = 0; i < toUserId.length; i ++) {
						// ユーザのメールとユーザ名前を取得
						PersonalMailBean userInfoBean = personalMailDao.getMailToUserInfo(toUserId[i]);
						// 送信メール
						toMail[i] = userInfoBean.getuMail();
						// 送信ユーザー名称
						toName[i] = userInfoBean.getuName();
					}
				}
				// 宛先
				StringBuffer toUserNameBuff = new StringBuffer();
				if (toName != null && toName.length > 0) {
					for (int i = 0; i < toName.length; i++) {
						String toUserName = (String) toName[i];
						toUserNameBuff.append(toUserName);
						// 宛先の名称中「、」ご間隔
						if (i != toName.length-1) {
							toUserNameBuff.append(new String(FsPropertyUtil.getStringProperty("notice.mail.address.content.head1")));
							toUserNameBuff.append("、");
						} else {
							toUserNameBuff.append(new String(FsPropertyUtil.getStringProperty("notice.mail.address.content.head1")));
						}
					}
				} else {
					// 宛先不在の場合は、”＜宛先不在＞”とする
					toUserNameBuff.append(new String(FsPropertyUtil.getStringProperty("notice.mail.address.no.mailto.username")));
				}
				//宛先設定
				mailInfo.setTo(toMail);
				// メールパラメータ
				Map<String, Object> rootMap = new HashMap<String, Object>();
				// ユーザー名
				rootMap.put("userName", firstName + " " + lastName);
				// メールTOユーザー名
				rootMap.put("mailToUserName",toUserNameBuff );
				// メールフラグより、メールテンプレートを指定する。
				mailInfo.setContent(new FreemarkerUtil().getTemplateStr(rootMap,"mail_send_notice_no_mail.ftl"));
				mail.sendMail(mailInfo,user,functionNm);
			}
			return false;
		}
		
		return true;
	}
	
	/**
	 * 店舗委託情報を取得する
	 * 
	 * @param loginUser 
	 *         ログインユーザ
	 * @return consign
	 *         店舗委託
	 *         
	 */
	public int getConsign(LoginUserBean loginUser) {
		// 店舗委託情報
		int consign = loginDao.getConsign(loginUser);
		
		return consign;
	}

	/**
	 * 店舗委託オーナー情報を取得する
	 * 
	 *  * @param loginUser 
	 *         ログインユーザ
	 * @return ownerId
	 *         店舗委託オーナーコード
	 *         
	 */
	public String getConsignOwner(LoginUserBean loginUser,String flag) {
		// 店舗委託オーナー
		String consignOwner = null;
		
		List<String> consign = loginDao.getConsignOwner(loginUser,flag);
		// 店舗委託オーナー存在
		if (consign != null && consign.size() != 0) {
			consignOwner = consign.get(0);
		}
		
		return consignOwner;
	}
	
	/**
	 * 店舗受託情報を取得する
	 * 
	 * @param loginUser 
	 *         ログインユーザ
	 * @return accession
	 *         店舗受託
	 *         
	 */
	public int getAccession(LoginUserBean loginUser,String flag) {
		// 店舗受託情報
		int accession = loginDao.getAccession(loginUser,flag);
		
		return accession;
	}
	
	/**
	 * ITR利用者企業コード情報を取得する
	 * 
	 * @param loginUser 
	 *         ログインユーザ
	 * @return ITRId
	 *         ITRコード
	 *
	 */
	public String getITRUserId(LoginUserBean loginUser) {
		return loginDao.getITRUserId(loginUser);
	}
	

	/**
	 * メールアドレス変更
	 * @param mailAddress
	 *           メールアドレス
	 * @param loginUser 
	 *         ログインユーザ
	 * @return
	 *           なし
	 * */
	public String updateMailAddress(String mailAddress,String userId) {
		return loginDao.updateMailAddress(mailAddress,userId);
	}

	public List<TestBean> getTestData(String shopcd) {
		return loginDao.getTestData(shopcd);
	}

	public int insertTestData(TestBean testBean) {
		return loginDao.insertTestData(testBean);
	}

	public int updateTestData(TestBean testBean) {
		return loginDao.updateTestData(testBean);
	}

	public int deleteTestData(TestBean testBean) {
		return loginDao.deleteTestData(testBean);
	}

	public List<LoginUserBean> getListUser() throws Exception{
		return loginLogic.getListUser();
	}

	public List<TestBean> getTestDataJqGrid() {
		return loginDao.getTestDataJqGrid();
	}

}