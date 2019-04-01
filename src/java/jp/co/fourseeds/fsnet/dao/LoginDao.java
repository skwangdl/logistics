package jp.co.fourseeds.fsnet.dao;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import jp.co.common.frame.dao.BaseDao;
import jp.co.common.frame.util.prop.FsPropertyUtil;
import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.test.TestBean;
import jp.co.fourseeds.fsnet.beans.LoginImageFormBean;

/**
 * ntsAppシステムのログインDAOの実装クラス
 *
 * @author NTS
 * @version 1.0.0 : 2015.09.14 新規作成
 * 
 * @author NTS
 * @version 1.1.0 : 2017.10.13 見直し修正
 */
@Repository
public class LoginDao extends BaseDao{

	/** Log4jの定義 */
	private final Log logger = LogFactory.getLog(getClass());
	/**
	 * 登録ユーザーのIDとパスワードによって、ユーザーの情報を取る
	 * 
	 * @param userId
	 *            ユーザーID
	 * @param password
	 *            パスワード
	 */
	public LoginUserBean getLoginUserInfo(String userId, String password, String sendMailFlag) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", userId);
		param.put("PARA_PASSWORD", password);
		param.put("PARA_SENDMAIL_FLAG", sendMailFlag);

		return this.sqlSessionTemplate.selectOne("login.getLoginUserInfo", param);
	}
	
	/**
	 * 画面のメイン画像を取得
	 * @return
	 */
	public LoginImageFormBean getLoginImageMainUrl() {
		return this.sqlSessionTemplate.selectOne("login.getLoginImageMainUrl");
	}
	
	/** 
	 * 該当ユーザの所属ユーザグループを取得する
	 * 
	 * @param loginUser		ログインユーザ情報
	 * @return				 所属ユーザグループリスト
	 */
	public List<String> getTopGroupList(LoginUserBean loginUser) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", loginUser.getUserId());
		param.put("PARA_BELONG_ID", loginUser.getBelong());
		param.put("PARA_ORIGINAL_ID", loginUser.getOriginalFlag());
		param.put("PARA_USER_DIVISION",loginUser.getUserFlag());

		return this.sqlSessionTemplate.selectList("login.getTopGroupList", param);
	}
	
	/**
	 * パスワード更新
	 * 
	 * @param userId
	 *           ユーザーID
	 * @param newPassword
	 *           パスワード
	 * @return 
	 *           なし
	 * */
	public void updatePassword(String userId, String newPassword) {
		
		// パスワード変更督促日取得
		String validDay = FsPropertyUtil.getStringProperty("password.valid.days");
		int days = Integer.parseInt(validDay);
		// システム日付を取得する。
		GregorianCalendar today = new GregorianCalendar();
		today.add(GregorianCalendar.DATE, days);
		Date nextDate = today.getTime();
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", userId);
		param.put("PARA_PASSWORD", newPassword);
		param.put("PARA_NEXTDATE", nextDate);
		// Oracle更新
		this.sqlSessionTemplate.update("login.changePassword", param);
	}
	
	/**
	 * デバイス別機能別制御情報取得
	 * @param request.getHeader("user-agent")情報 
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public List getDeviceControlList(String userAgent) {
		//requestのuser-agentから括弧内の内容を抽出
		Pattern pattern = Pattern.compile("(?<=\\()[^)]*(?=\\))");
		Matcher matcher = pattern.matcher(userAgent);
		String os = "";
		while(matcher.find()){
			os = matcher.group().toUpperCase();
			break;
		}
		
//		logger.info("os========"+os);
		Map<String, Object> param = new HashMap<String, Object>();
		
		//requestのuser-agentにょり、クライアント端末のOSを判断する
		if(os.indexOf("ANDROID")>= 0){
			param.put("PARA_REQUEST_INFO", "ANDROID");
		} else if(os.indexOf("WINDOWS") >= 0){
			param.put("PARA_REQUEST_INFO", "WINDOWS");
		} else if(os.indexOf("IPHONE")>= 0 || os.indexOf("LIKE MAC")>= 0){
			param.put("PARA_REQUEST_INFO", "IOS");
		} else {
			// 特殊アンドロイド
			String[] androidKeys = FsPropertyUtil.getStringProperty("android.keyword.others").split(",");
			for(int i=0; i< androidKeys.length; i++){
				if(os.indexOf(androidKeys[i])>= 0){
					param.put("PARA_REQUEST_INFO", "ANDROID");
					break;
				}
			}
		}
		
//		logger.info("変換後========"+param.get("PARA_REQUEST_INFO"));
		return this.sqlSessionTemplate.selectList("login.getDeviceControlList", param);
	}
	
	/**
	 * <p>
	 * 画面の画像取得
	 * </p>
	 * 
	 * @param displayPosition 
	 *         バナー
	 * @return loginImgMasterBean
	 *         画像情報
	 * */
	public LoginImageFormBean getLoginImage(String displayPosition) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_DISPLAY_POSITION", displayPosition);
		return (LoginImageFormBean)this.sqlSessionTemplate.selectOne("login.getLoginImage", param);
	}
	
	/** 作成中コンテンツ存在フラグ */
	public String getConfirmFlag(String userId) {
		List<String> results = null;
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", userId);

		results = this.sqlSessionTemplate.selectList("login.getConfirmFlag", param);
		if(results != null && !results.isEmpty()){
			return "Y";
		} else {
			return "N";
		}
	}
	
	/** 公開待ちコンテンツ存在フラグ */
	public String getFetureFlag(String userId) {
		List<String> results = null;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", userId);
		param.put("PARA_SYSDATE", new Date());

		results = this.sqlSessionTemplate.selectList("login.getFetureFlag", param);
		if(results != null && !results.isEmpty()){
			return "Y";
		} else {
			return "N";
		}
	}
	/** テンプレートコンテンツ存在フラグ */
	public String getTemplateFlag(String userId, String role) {
		List<HashMap<String, String>> results = null;
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", userId);
		param.put("PARA_ROLE", role);

		results = this.sqlSessionTemplate.selectList("login.getTemplateFlag", param);
		if(results != null && !results.isEmpty()){
			return "Y";
		} else {
			return "N";
		}
	}

	/** 期限切れコンテンツ存在フラグ */
	public String getPastFlag(String userId) {
		List<String> results = null;
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", userId);
		param.put("PARA_SYSDATE", new Date());

		results = this.sqlSessionTemplate.selectList("login.getPastFlag", param);
		if(results != null && !results.isEmpty()){
			return "Y";
		} else {
			return "N";
		}
	}


	/**
	 * パスワード再発行制御テーブルにデータ登録
	 */
	public void insertPwCtrlInfo(String userId, String accessKey, String accessTime) {
		//該当ユーザの以前のアクセスデータを削除
		deletePwCtrlInfo(userId);
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userId);
		param.put("PARA_ACCESS_KEY", accessKey);
		param.put("PARA_ACCESS_TIME", accessTime);
		param.put("PARA_CREATE_BY", userId);
		param.put("PARA_UPDATE_BY", userId);
		//該当ユーザの今回のアクセスデータを新規登録
		this.sqlSessionTemplate.insert("login.setPwCtrlInfo", param);
	}

	/**
	 * パスワード再発行制御テーブルのアクセス時間を取得
	 */
	public LoginUserBean getAccessTimeInfo(String accessKey) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_ACCESS_KEY", accessKey);
		
		LoginUserBean bean = (LoginUserBean)this.sqlSessionTemplate.selectOne("login.getAccessTimeInfo", param);
		
		return bean;
	}
	
	/**
	 * パスワード再発行制御テーブルのデータを削除
	 */
	public void deletePwCtrlInfo(String userId) {
		this.sqlSessionTemplate.update("login.deletePwCtrlInfo", userId);
	}

	/**
	 * パスワード再発行制御テーブルの暗号キーを取得
	 */
	public String getAccessKey() {
		return (String)this.sqlSessionTemplate.selectOne("login.getAccessKey");
	}

	/**
	 * true:暗号キー存在 false:暗号キー不存在
	 */
	public boolean searchAccessKey(String accessKey) {
		Integer result = (Integer)this.sqlSessionTemplate.selectOne("login.searchAccessKey", accessKey);
		return result >= 1;
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
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_STOREID", loginUser.getStoreId());
		
		return (Integer)this.sqlSessionTemplate.selectOne("login.getConsign", param);
	}
	
	/**
	 * 店舗委託オーナー情報を取得する
	 * 
	 * @param loginUser 
	 *         ログインユーザ
	 * @return ownerId
	 *         店舗委託オーナーコード
	 *
	 */
	public List<String> getConsignOwner(LoginUserBean loginUser,String flag) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("PARA_STOREID", loginUser.getStoreId());
		if ("vaild".equals(flag)) {
			param.put("PARA_VALID", "1");
		} else {
			param.put("PARA_VALID", "2");
		}
		
		return this.sqlSessionTemplate.selectList("login.getConsignOwner", param);
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
		Map<String, String> param = new HashMap<String, String>();
		param.put("PARA_STOREID", loginUser.getStoreId());
		if ("vaild".equals(flag)) {
			param.put("PARA_VALID", "1");
		} else {
			param.put("PARA_VALID", "2");
		}
		
		return (Integer)this.sqlSessionTemplate.selectOne("login.getAccession", param);
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
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", loginUser.getUserId());
		param.put("PARA_STOREID", loginUser.getStoreId());
		
		return this.sqlSessionTemplate.selectOne("login.getITRUserId", param);
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
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("MAIL_ADDRESS", mailAddress);
		param.put("PARA_USERID", userId);
		return this.sqlSessionTemplate.selectOne("login.updateMailAddress", param);
	}

	public void updateTest(String password) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("aaa", password);
		this.sqlSessionTemplate.delete("login.updateTest", param);
	}
	public void updateTest2(String password) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("aaa", password);
		this.sqlSessionTemplate.delete("login.updateTest", param);
	}

	public List<TestBean> getTestData(String shopcd) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_SHOP_CD", shopcd);
		return this.sqlSessionTemplate.selectList("test.getTestData",param);
	}

	public int insertTestData(TestBean testBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARAM_BUMON_CD", testBean.getBumonCd());
		param.put("PARAM_DEPO_CD", testBean.getDepoCd());
		return this.sqlSessionTemplate.insert("test.insertTest", param);	
	}

	public int updateTestData(TestBean testBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARAM_BUMON_CD", testBean.getBumonCd());
		param.put("PARAM_DEPO_CD", testBean.getDepoCd());
		return this.sqlSessionTemplate.insert("test.updateTest", param);	
	}

	public int deleteTestData(TestBean testBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARAM_BUMON_CD", testBean.getBumonCd());
		param.put("PARAM_DEPO_CD", testBean.getDepoCd());
		return this.sqlSessionTemplate.insert("test.deleteTest", param);	
	}
	
	public List<LoginUserBean> getListUser() {
		return this.sqlSessionTemplate.selectList("test.getListUser");
	}

	public List<TestBean> getTestDataJqGrid() {
		return this.sqlSessionTemplate.selectList("test.getTestDataJqGrid");
	}
}
