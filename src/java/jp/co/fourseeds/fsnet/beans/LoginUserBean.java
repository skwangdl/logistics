package jp.co.fourseeds.fsnet.beans;

import java.util.Date;
import java.util.List;

import jp.co.common.frame.beans.BaseBean;

/**
 * ログインユーザー情報の格納クラス
 * 
 * @author NTS
 * @version 1.0.0 : 2015.09.14 新規作成（旧プロジェクトのLoginUser.javaから移動）
 * 
 * @author NTS
 * @version 1.1.0 : 2017.10.13 見直し修正
 * 
 */
public class LoginUserBean extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4388234923404188955L;
	
	/** 暗号キー */
	private String accessKey = null;
	
	/** アクセス時間 */
	private String accessTime = null;

	/** ユーザID */
	private String userId = null;

	/** パスワード */
	private String password = null;

	/** パスワード有効終了日 */
	private Date pwExpire = null;

	/** 姓漢字 */
	private String kanjiSei = null;

	/** 名漢字 */
	private String kanjiMei = null;

	/** 姓カナ */
	private String kanaSei = null;

	/** 名カナ */
	private String kanaMei = null;

	/** 性別 */
	private String sex = null;

	/** 携帯 */
	private String mobile = null;

	/** 役職ID */
	private String positionId = null;

	/** 部門ID */
	private String departmentId = null;

	/** 店舗ID */
	private String storeId = null;

	/** 内線 */
	private String naisen = null;

	/** システム利用区分 */
	private String role = null;

	/** 社員写真閲覧区分 */
	private String viewPhotoFlag = null;

	/** 社員写真アドレス */
	private String photoAddress = null;

	/** 当前システム時間 */
	private Date currentDate = null;

	private String pageId = null;

	private Date pwSetDate = null;
	
	/** the user belongs group */
	private List topGroupList = null;
	
	/** 役職名称  */
	private String positionName = null;
	
	private String newConfirmFlag = null;
	
	/** 重要なお知らせ 表示開始日*/
	private String necessityDisplayStartDate = null;
	
	/**転送先メールアドレス*/
	private String trmailAddress = null;
	
	/** 作成中コンテンツ存在フラグ*/
	private String confirmFlag = null;
	/** 公開待ちコンテンツ存在フラグ*/
	private String fetureFlag = null;
	/** テンプレートコンテンツ存在フラグ*/
	private String templateFlag = null;
	/** 期限切れコンテンツ存在フラグ*/
	private String pastFlag = null;
	/** 統括CD*/
	private String unificationId = null;
	/** 統括名称*/
	private String unificationNm = null;
	/** 事業コード*/
	private String businessId = null;
	/** 事業名称*/
	private String businessNm = null;
	/** 営業部コード*/
	private String salesId = null;
	/** 営業部名称*/
	private String salesNm = null;

	/** 従業員区分 */
	private String userFlag = null;

	/** 従業員区分名称 */
	private String userFlagName = null;

	/** 組織区分 */
	private String originalFlag = null;

	/** 組織区分名称 */
	private String originalName = null;

	/** 所属 */
	private String belong = null;

	/** 所属名称 */
	private String belongName = null;

	/** 店舗区分*/
	private String storeFcFLag = null;

	/** 店舗オーナーコード*/
	private String ownerId = null;

	/** メール通知不要フラグ*/
	private String mailNoticeFlag = null;
	
	/** メール*/
	private String mail = null;
	
	/** アルバイト名称*/
	private String xaallkName = null;
	
	/**
	 * @return the confirmFlag
	 */
	public String getNewConfirmFlag() {
		return newConfirmFlag;
	}

	/**
	 * @param confirmFlag the confirmFlag to set
	 */
	public void setNewConfirmFlag(String confirmFlag) {
		this.newConfirmFlag = confirmFlag;
	}

	/**
	 * @return the positionName
	 */
	public String getPositionName() {
		return positionName;
	}

	/**
	 * @param positionName the positionName to set
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	/**
	 * @return currentDate
	 */
	public Date getCurrentDate() {
		return currentDate;
	}

	/**
	 * @param currentDate
	 *            設定する currentDate
	 */
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	/**
	 * @return departmentId
	 */
	public String getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId
	 *            設定する departmentId
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return kanaMei
	 */
	public String getKanaMei() {
		return kanaMei;
	}

	/**
	 * @param kanaMei
	 *            設定する kanaMei
	 */
	public void setKanaMei(String kanaMei) {
		this.kanaMei = kanaMei;
	}

	/**
	 * @return kanaSei
	 */
	public String getKanaSei() {
		return kanaSei;
	}

	/**
	 * @param kanaSei
	 *            設定する kanaSei
	 */
	public void setKanaSei(String kanaSei) {
		this.kanaSei = kanaSei;
	}

	/**
	 * @return kanjiMei
	 */
	public String getKanjiMei() {
		return kanjiMei;
	}

	/**
	 * @param kanjiMei
	 *            設定する kanjiMei
	 */
	public void setKanjiMei(String kanjiMei) {
		this.kanjiMei = kanjiMei;
	}

	/**
	 * @return kanjiSei
	 */
	public String getKanjiSei() {
		return kanjiSei;
	}

	/**
	 * @param kanjiSei
	 *            設定する kanjiSei
	 */
	public void setKanjiSei(String kanjiSei) {
		this.kanjiSei = kanjiSei;
	}

	/**
	 * @return mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            設定する mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return naisen
	 */
	public String getNaisen() {
		return naisen;
	}

	/**
	 * @param naisen
	 *            設定する naisen
	 */
	public void setNaisen(String naisen) {
		this.naisen = naisen;
	}

	/**
	 * @return pageId
	 */
	public String getPageId() {
		return pageId;
	}

	/**
	 * @param pageId
	 *            設定する pageId
	 */
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            設定する password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return photoAddress
	 */
	public String getPhotoAddress() {
		return photoAddress;
	}

	/**
	 * @param photoAddress
	 *            設定する photoAddress
	 */
	public void setPhotoAddress(String photoAddress) {
		this.photoAddress = photoAddress;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	/**
	 * @return pwExpire
	 */
	public Date getPwExpire() {
		return pwExpire;
	}

	/**
	 * @param pwExpire
	 *            設定する pwExpire
	 */
	public void setPwExpire(Date pwExpire) {
		this.pwExpire = pwExpire;
	}

	/**
	 * @return pwSetDate
	 */
	public Date getPwSetDate() {
		return pwSetDate;
	}

	/**
	 * @param pwSetDate
	 *            設定する pwSetDate
	 */
	public void setPwSetDate(Date pwSetDate) {
		this.pwSetDate = pwSetDate;
	}

	/**
	 * @return role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role
	 *            設定する role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            設定する sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return storeId
	 */
	public String getStoreId() {
		return storeId;
	}

	/**
	 * @param storeId
	 *            設定する storeId
	 */
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	/**
	 * @return topGroupList
	 */
	public List<String> getTopGroupList() {
		return topGroupList;
	}

	/**
	 * @param topGroupList
	 *            設定する topGroupList
	 */
	public void setTopGroupList(List<String> topGroupList) {
		this.topGroupList = topGroupList;
	}

	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            設定する userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return viewPhotoFlag
	 */
	public String getViewPhotoFlag() {
		return viewPhotoFlag;
	}

	/**
	 * @param viewPhotoFlag
	 *            設定する viewPhotoFlag
	 */
	public void setViewPhotoFlag(String viewPhotoFlag) {
		this.viewPhotoFlag = viewPhotoFlag;
	}

	public String getNecessityDisplayStartDate() {
		return necessityDisplayStartDate;
	}

	public void setNecessityDisplayStartDate(String necessityDisplayStartDate) {
		this.necessityDisplayStartDate = necessityDisplayStartDate;
	}

	public String getTrmailAddress() {
		return trmailAddress;
	}

	public void setTrmailAddress(String trmailAddress) {
		this.trmailAddress = trmailAddress;
	}

	public String getConfirmFlag() {
		return confirmFlag;
	}

	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

	public String getFetureFlag() {
		return fetureFlag;
	}

	public void setFetureFlag(String fetureFlag) {
		this.fetureFlag = fetureFlag;
	}

	public String getTemplateFlag() {
		return templateFlag;
	}

	public void setTemplateFlag(String templateFlag) {
		this.templateFlag = templateFlag;
	}

	public String getPastFlag() {
		return pastFlag;
	}

	public void setPastFlag(String pastFlag) {
		this.pastFlag = pastFlag;
	}

	public String getUnificationId() {
		return unificationId;
	}

	public void setUnificationId(String unificationId) {
		this.unificationId = unificationId;
	}

	public String getUnificationNm() {
		return unificationNm;
	}

	public void setUnificationNm(String unificationNm) {
		this.unificationNm = unificationNm;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getBusinessNm() {
		return businessNm;
	}

	public void setBusinessNm(String businessNm) {
		this.businessNm = businessNm;
	}

	public String getSalesId() {
		return salesId;
	}

	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}

	public String getSalesNm() {
		return salesNm;
	}

	public void setSalesNm(String salesNm) {
		this.salesNm = salesNm;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(String accessTime) {
		this.accessTime = accessTime;
	}

	public String getUserFlag() {
		return userFlag;
	}

	public void setUserFlag(String userFlag) {
		this.userFlag = userFlag;
	}

	public String getUserFlagName() {
		return userFlagName;
	}

	public void setUserFlagName(String userFlagName) {
		this.userFlagName = userFlagName;
	}

	public String getOriginalFlag() {
		return originalFlag;
	}

	public void setOriginalFlag(String originalFlag) {
		this.originalFlag = originalFlag;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getBelong() {
		return belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}

	public String getBelongName() {
		return belongName;
	}

	public void setBelongName(String belongName) {
		this.belongName = belongName;
	}

	public String getStoreFcFLag() {
		return storeFcFLag;
	}

	public void setStoreFcFLag(String storeFcFLag) {
		this.storeFcFLag = storeFcFLag;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getMailNoticeFlag() {
		return mailNoticeFlag;
	}

	public void setMailNoticeFlag(String mailNoticeFlag) {
		this.mailNoticeFlag = mailNoticeFlag;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getXaallkName() {
		return xaallkName;
	}

	public void setXaallkName(String xaallkName) {
		this.xaallkName = xaallkName;
	}
	
}
