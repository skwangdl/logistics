package jp.co.fourseeds.fsnet.beans;

import java.io.File;
import java.util.Date;
import java.util.List;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.beans.LabelValueBean;

/**
 * ユーザ情報Bean
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2016/01/27		    NTS        	       作成
 * 1.1		2017/09/05			NTS			見直し修正
 *
 **/
public class UserBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/**■■■■■■■■ここから、検索Bean■■■■■■■■■■■■■■■■*/
	/** 検索社員番号*/
	private String searchUserId;
	
	/** 検索氏名*/
	private String searchUserName;
	
	/** 検索会社ID*/
	private String searchCompanyId;
	
	/** 検索会社名*/
	private String searchCompanyName;
	
	/** 検索統括ID*/
	private String searchUnificationId;
	
	/** 検索統括名*/
	private String searchUnificationName;
	
	/** 検索事業ID*/
	private String searchBusinessId;
	
	/** 検索事業名*/
	private String searchBusinessName;
	
	/** 検索営業部ID*/
	private String searchSalesId;
	
	/** 検索営業部名*/
	private String searchSalesName;
	
	/** 検索本部ID*/
	private String searchDeptId;
	
	/** 検索本部名*/
	private String searchDeptName;
	
	/** 検索所店舗ID*/
	private String searchStoreId;
	
	/** 検索店舗名*/
	private String searchStoreName;
	
	/** 検索従業員区分*/
	private String searchPemployeeId;
	
	/** 検索従業員区分名*/
	private String searchPemployeeName;
	
	/** 検索表示フラグ*/
	private String searchShowFlag;
	
	/** 検索表示フラグ名*/
	private String searchShowFlagName;
	
	/** 初期検索 */
	private String isFirstSearch;
	
	/** ページ毎に表示件数情報格納 */
	private int searchRowNum;
	
	/**従業員区分リスト*/
	private List<LabelValueBean> pemployeeList;
	
	/**表示フラグリスト*/
	private List<LabelValueBean> showFlagList;
	
	/**システム利用区分リスト*/
	private List<LabelValueBean> roleList;
	
	/**写真閲覧区分リスト*/
	private List<LabelValueBean> viewPhotoList;
	
	/**■■■■■■■■ここから、検索結果Bean■■■■■■■■■■■■*/
	
	/** 社員番号 */
	private String userId;
	
	/** 漢字氏名*/
	private String kanziName;
	
	/** カナ氏名*/
	private String kanaName;
	
	/** 所属名*/
	private String belongName;
	
	/** 会社名*/
	private String companyName;
	
	/** 統括名*/
	private String unificationName;
	
	/** 事業名*/
	private String businessName;
	
	/** 営業部名*/
	private String salesName;
	
	/** 社有携帯番号*/
	private String mobileNum;
	
	/** 内線*/
	private String naisenNum;
	
	/** 組織区分*/
	private String organizationId;
	
	/** 組織区分名*/
	private String organizationName;
	
	/** 有効開始日*/
	private String userSet;
	
	/** 有効終了日*/
	private String userEx;
	
	/** パスワード*/
	private String password;
	
	/** パスワード有効終了日*/
	private String pwExpire;
	
	/** 漢字姓*/
	private String kanziSei;
	
	/** 漢字名*/
	private String kanziMei;
	
	/** カナ姓*/
	private String kanaSei;
	
	/** カナ名*/
	private String kanaMei;
	
	/** システム利用区分*/
	private String roleId;
	
	/** システム利用区分名称*/
	private String roleName;
	
	/** 旧システム利用区分*/
	private String oldRoleId;
	
	/**システム利用区分リスト*/
	private String loginRoleId;
	
	/** 従業員旧システム利用区分*/
	private String userOldRoleId;
	
	/** 従業員区分*/
	private String pEmployeeFlag;
	
	/** 従業員区分名称*/
	private String pEmployeeFlagName;
	
	/** 所属ID*/
	private String belongId;
	
	/** 性別*/
	private String sex;
	
	/** 性別名称*/
	private String sexName;
	
	/** 写真閲覧区分*/
	private String viewPhoto;
	
	/** ログインユーザの写真閲覧区分*/
	private String loginUserViewPhoto;
	
	/** 重要なお知らせ 表示開始日設定*/
	private String necessityDisplayStartDate;
	
	/** イメージデータ*/
	private byte[] imgDat;

	/** 写 真*/
	private File photo;
	
	/** 写 真パス*/
	private String photoPath;
	
	/** 写 真名 */
	private String photoFileName;
	
	/** 社員写真アドレス*/
	private String photoAdd;
	
	/** 旧社員写真アドレス*/
	private String oldPhotoAdd;
	
	/**メールアドレス*/
	private String mailAddress;
	
	/**イメージ表示区分*/
	private String imgFlag;
	
	/**メール通知フラグ*/
	private String mailNoticFlag;
	
	/**メール非表示フラグ*/
	private String mailShowFlag;
	
	/**表示設定フラグ*/
	private String showFlag;
	
	/**表示設定フラグ名*/
	private String showFlagName;
	
	/**会社ID*/
	private String companyId;
	
	/**統括ID*/
	private String unificationId;
	
	/**事業ID*/
	private String businessId;
	
	/**営業部ID*/
	private String salesId;
	
	/**ユーザグループID*/
	private String userGroupId;
	
	/**反映予定日時*/
	private Date ugRefDate;
	
	public String getShowFlag() {
		return showFlag;
	}

	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}

	public String getShowFlagName() {
		return showFlagName;
	}

	public void setShowFlagName(String showFlagName) {
		this.showFlagName = showFlagName;
	}

	public String getUnificationId() {
		return unificationId;
	}

	public void setUnificationId(String unificationId) {
		this.unificationId = unificationId;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getSalesId() {
		return salesId;
	}

	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}

	public String getMailNoticFlag() {
		return mailNoticFlag;
	}

	public void setMailNoticFlag(String mailNoticFlag) {
		this.mailNoticFlag = mailNoticFlag;
	}

	public String getSearchUserId() {
		return searchUserId;
	}

	public void setSearchUserId(String searchUserId) {
		this.searchUserId = searchUserId;
	}

	public String getSearchUserName() {
		return searchUserName;
	}

	public void setSearchUserName(String searchUserName) {
		this.searchUserName = searchUserName;
	}

	public List<LabelValueBean> getShowFlagList() {
		return showFlagList;
	}

	public void setShowFlagList(List<LabelValueBean> showFlagList) {
		this.showFlagList = showFlagList;
	}
	
	public String getSearchBusinessName() {
		return searchBusinessName;
	}

	public void setSearchBusinessName(String searchBusinessName) {
		this.searchBusinessName = searchBusinessName;
	}

	public String getSearchSalesName() {
		return searchSalesName;
	}

	public void setSearchSalesName(String searchSalesName) {
		this.searchSalesName = searchSalesName;
	}

	public String getSearchPemployeeName() {
		return searchPemployeeName;
	}

	public void setSearchPemployeeName(String searchPemployeeName) {
		this.searchPemployeeName = searchPemployeeName;
	}

	public String getSearchShowFlag() {
		return searchShowFlag;
	}

	public void setSearchShowFlag(String searchShowFlag) {
		this.searchShowFlag = searchShowFlag;
	}

	public String getSearchShowFlagName() {
		return searchShowFlagName;
	}

	public void setSearchShowFlagName(String searchShowFlagName) {
		this.searchShowFlagName = searchShowFlagName;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getpEmployeeFlag() {
		return pEmployeeFlag;
	}

	public void setpEmployeeFlag(String pEmployeeFlag) {
		this.pEmployeeFlag = pEmployeeFlag;
	}

	public String getpEmployeeFlagName() {
		return pEmployeeFlagName;
	}

	public void setpEmployeeFlagName(String pEmployeeFlagName) {
		this.pEmployeeFlagName = pEmployeeFlagName;
	}

	public String getSearchBusinessId() {
		return searchBusinessId;
	}

	public void setSearchBusinessId(String searchBusinessId) {
		this.searchBusinessId = searchBusinessId;
	}

	public String getSearchSalesId() {
		return searchSalesId;
	}

	public void setSearchSalesId(String searchSalesId) {
		this.searchSalesId = searchSalesId;
	}

	public String getSearchUnificationId() {
		return searchUnificationId;
	}

	public void setSearchUnificationId(String searchUnificationId) {
		this.searchUnificationId = searchUnificationId;
	}

	public String getSearchUnificationName() {
		return searchUnificationName;
	}

	public void setSearchUnificationName(String searchUnificationName) {
		this.searchUnificationName = searchUnificationName;
	}

	public String getIsFirstSearch() {
		return isFirstSearch;
	}

	public void setIsFirstSearch(String isFirstSearch) {
		this.isFirstSearch = isFirstSearch;
	}

	public int getSearchRowNum() {
		return searchRowNum;
	}

	public void setSearchRowNum(int searchRowNum) {
		this.searchRowNum = searchRowNum;
	}
	
	public String getSearchPemployeeId() {
		return searchPemployeeId;
	}

	public void setSearchPemployeeId(String searchUserDivision) {
		this.searchPemployeeId = searchUserDivision;
	}

	public List<LabelValueBean> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<LabelValueBean> roleList) {
		this.roleList = roleList;
	}

	public List<LabelValueBean> getPemployeeList() {
		return pemployeeList;
	}

	public void setPemployeeList(List<LabelValueBean> pemployeeList) {
		this.pemployeeList = pemployeeList;
	}

	public List<LabelValueBean> getViewPhotoList() {
		return viewPhotoList;
	}

	public void setViewPhotoList(List<LabelValueBean> viewPhotoList) {
		this.viewPhotoList = viewPhotoList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getKanziName() {
		return kanziName;
	}

	public void setKanziName(String kanziName) {
		this.kanziName = kanziName;
	}

	public String getKanaName() {
		return kanaName;
	}

	public void setKanaName(String kanaName) {
		this.kanaName = kanaName;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getNaisenNum() {
		return naisenNum;
	}

	public void setNaisenNum(String naisenNum) {
		this.naisenNum = naisenNum;
	}

	public String getUserSet() {
		return userSet;
	}

	public void setUserSet(String userSet) {
		this.userSet = userSet;
	}

	public String getUserEx() {
		return userEx;
	}

	public void setUserEx(String userEx) {
		this.userEx = userEx;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPwExpire() {
		return pwExpire;
	}

	public void setPwExpire(String pwExpire) {
		this.pwExpire = pwExpire;
	}

	public String getKanziSei() {
		return kanziSei;
	}

	public void setKanziSei(String kanziSei) {
		this.kanziSei = kanziSei;
	}

	public String getKanziMei() {
		return kanziMei;
	}

	public void setKanziMei(String kanziMei) {
		this.kanziMei = kanziMei;
	}

	public String getKanaSei() {
		return kanaSei;
	}

	public void setKanaSei(String kanaSei) {
		this.kanaSei = kanaSei;
	}

	public String getKanaMei() {
		return kanaMei;
	}

	public void setKanaMei(String kanaMei) {
		this.kanaMei = kanaMei;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getOldRoleId() {
		return oldRoleId;
	}

	public void setOldRoleId(String oldRoleId) {
		this.oldRoleId = oldRoleId;
	}

	public String getPEmployeeFlag() {
		return pEmployeeFlag;
	}

	public void setPEmployeeFlag(String pEmployeeFlag) {
		this.pEmployeeFlag = pEmployeeFlag;
	}

	public String getPEmployeeFlagName() {
		return pEmployeeFlagName;
	}

	public void setPEmployeeFlagName(String pEmployeeFlagName) {
		this.pEmployeeFlagName = pEmployeeFlagName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public String getViewPhoto() {
		return viewPhoto;
	}

	public void setViewPhoto(String viewPhoto) {
		this.viewPhoto = viewPhoto;
	}

	public String getLoginUserViewPhoto() {
		return loginUserViewPhoto;
	}

	public void setLoginUserViewPhoto(String loginUserViewPhoto) {
		this.loginUserViewPhoto = loginUserViewPhoto;
	}

	public String getNecessityDisplayStartDate() {
		return necessityDisplayStartDate;
	}

	public void setNecessityDisplayStartDate(String necessityDisplayStartDate) {
		this.necessityDisplayStartDate = necessityDisplayStartDate;
	}

	public byte[] getImgDat() {
		return imgDat;
	}

	public void setImgDat(byte[] imgDat) {
		this.imgDat = imgDat;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	public String getPhotoAdd() {
		return photoAdd;
	}

	public void setPhotoAdd(String photoAdd) {
		this.photoAdd = photoAdd;
	}

	public String getOldPhotoAdd() {
		return oldPhotoAdd;
	}

	public void setOldPhotoAdd(String oldPhotoAdd) {
		this.oldPhotoAdd = oldPhotoAdd;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getImgFlag() {
		return imgFlag;
	}

	public void setImgFlag(String imgFlag) {
		this.imgFlag = imgFlag;
	}

	public String getUnificationName() {
		return unificationName;
	}

	public void setUnificationName(String unificationName) {
		this.unificationName = unificationName;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}

	public String getMailShowFlag() {
		return mailShowFlag;
	}

	public void setMailShowFlag(String mailShowFlag) {
		this.mailShowFlag = mailShowFlag;
	}

	public String getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}

	public Date getUgRefDate() {
		return ugRefDate;
	}

	public void setUgRefDate(Date ugRefDate) {
		this.ugRefDate = ugRefDate;
	}

	public String getUserOldRoleId() {
		return userOldRoleId;
	}

	public void setUserOldRoleId(String userOldRoleId) {
		this.userOldRoleId = userOldRoleId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSearchCompanyId() {
		return searchCompanyId;
	}

	public void setSearchCompanyId(String searchCompanyId) {
		this.searchCompanyId = searchCompanyId;
	}

	public String getSearchCompanyName() {
		return searchCompanyName;
	}

	public void setSearchCompanyName(String searchCompanyName) {
		this.searchCompanyName = searchCompanyName;
	}

	public String getBelongName() {
		return belongName;
	}

	public void setBelongName(String belongName) {
		this.belongName = belongName;
	}

	public String getBelongId() {
		return belongId;
	}

	public void setBelongId(String belongId) {
		this.belongId = belongId;
	}

	public String getLoginRoleId() {
		return loginRoleId;
	}

	public void setLoginRoleId(String loginRoleId) {
		this.loginRoleId = loginRoleId;
	}

	public String getSearchDeptId() {
		return searchDeptId;
	}

	public void setSearchDeptId(String searchDeptId) {
		this.searchDeptId = searchDeptId;
	}

	public String getSearchDeptName() {
		return searchDeptName;
	}

	public void setSearchDeptName(String searchDeptName) {
		this.searchDeptName = searchDeptName;
	}

	public String getSearchStoreId() {
		return searchStoreId;
	}

	public void setSearchStoreId(String searchStoreId) {
		this.searchStoreId = searchStoreId;
	}

	public String getSearchStoreName() {
		return searchStoreName;
	}

	public void setSearchStoreName(String searchStoreName) {
		this.searchStoreName = searchStoreName;
	}
	
}