/**
 * トップグループBean
 * 
 * @author NTS
 * @version 1.0.0 : 2016.01.19 新規作成
 */

package jp.co.fourseeds.fsnet.beans.personalTopGroup;

import java.util.List;

import jp.co.common.frame.beans.LabelValueBean;

import jp.co.fourseeds.fsnet.beans.topGroup.TopGroupBean;

public class PersonalTopGroupBean extends TopGroupBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/**■■■■■■■■ここから、検索Bean■■■■■■■■■■■■■■■■*/

	/** 検索組織区分*/
	private String searchOrganizationId;
	
	/** 検索従業員区分*/
	private String searchPemployeeId;
	
	/**組織リスト*/
	private List<LabelValueBean> organizationList;
	
	/**従業員区分リスト*/
	private List<LabelValueBean> pemployeeList;
	
	/** 検索共有ユーザ名*/
	private String searchShareUserName;
	
	/** 検索共有ユーザの部署Id*/
	private String searchShareDeptId;
	
	/** 検索ユーザ名*/
	private String searchUserName;
	
	/** 検索部署Id*/
	private String searchDepartmentId;
	
	/**■■■■■■■■ここから、検索結果Bean■■■■■■■■■■■■*/
	
	/** 共有ユーザリスト */
	private List<PersonalTopGroupDetailBean> shareUserList;
	
	/** ユーザリスト */
	private List<PersonalTopGroupDetailBean> userList;
	
	/** ユーザID */
	private String userId;
	
	/** ユーザ名称 */
	private String userName;
	
	/** 作成者名 */
	private String createUserName;
	
	/** 編集可能フラグ */
	private String authEditFlag;
	
	/** 詳細画面の中で、編集と削除ボタン表示権限 */
	private String btnDisplayRight;
	
	/** 検索店舗名*/
	private String searchStoreGroupName;
	
	/** 検索部署名*/
	private String searchDeptGroupName;
	
	/** 会社Id*/
	private String searchShareCompanyId;
	
	/** 統括Id*/
	private String searchShareUnificationId;
	
	/** 事業部Id*/
	private String searchShareBusinessId;
	
	/** 営業部Id*/
	private String searchShareSalesId;
	
	/** 会社id*/
	private String searchCompanyId;
	
	/** 統括Id*/
	private String searchUnificationId;
	
	/** 事業部Id*/
	private String searchBusinessId;
	
	/** 営業部Id*/
	private String searchSalesId;
	
	/** 部署グループ検索用社員区分*/
	private String conditionPemployeeId;
	
	/** 編集画面の会社リスト*/
	private List<LabelValueBean> shareCompanyList;
	
	/** 編集画面の統括リスト*/
	private List<LabelValueBean> shareUnificationList;
	
	/** 編集画面の事業部リスト*/
	private List<LabelValueBean> shareBusinessList;
	
	/** 編集画面の営業部リスト*/
	private List<LabelValueBean> shareSalesList;
	
	/** 編集画面の会社リスト*/
	private List<LabelValueBean> companyList;
	
	/** 編集画面の統括リスト*/
	private List<LabelValueBean> unificationList;
	
	/** 編集画面の事業部リスト*/
	private List<LabelValueBean> businessList;
	
	/** 編集画面の営業部リスト*/
	private List<LabelValueBean> salesList;
	
	/** LEVEL*/
	private int level;
	
	/** 社員区分*/
	private String searchConditionPemployeeId;
	
	/** 店舗区分*/
	private String searchStoredivision;

	public String getSearchShareUserName() {
		return searchShareUserName;
	}

	public void setSearchShareUserName(String searchShareUserName) {
		this.searchShareUserName = searchShareUserName;
	}

	public String getSearchShareDeptId() {
		return searchShareDeptId;
	}

	public void setSearchShareDeptId(String searchShareDeptId) {
		this.searchShareDeptId = searchShareDeptId;
	}

	public String getSearchUserName() {
		return searchUserName;
	}

	public void setSearchUserName(String searchUserName) {
		this.searchUserName = searchUserName;
	}

	public String getSearchDepartmentId() {
		return searchDepartmentId;
	}

	public void setSearchDepartmentId(String searchDepartmentId) {
		this.searchDepartmentId = searchDepartmentId;
	}

	public List<PersonalTopGroupDetailBean> getShareUserList() {
		return shareUserList;
	}

	public void setShareUserList(List<PersonalTopGroupDetailBean> shareUserList) {
		this.shareUserList = shareUserList;
	}

	public List<PersonalTopGroupDetailBean> getUserList() {
		return userList;
	}

	public void setUserList(List<PersonalTopGroupDetailBean> userList) {
		this.userList = userList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getBtnDisplayRight() {
		return btnDisplayRight;
	}

	public void setBtnDisplayRight(String btnDisplayRight) {
		this.btnDisplayRight = btnDisplayRight;
	}

	public String getAuthEditFlag() {
		return authEditFlag;
	}

	public void setAuthEditFlag(String authEditFlag) {
		this.authEditFlag = authEditFlag;
	}

	public String getSearchStoreGroupName() {
		return searchStoreGroupName;
	}

	public void setSearchStoreGroupName(String searchStoreGroupName) {
		this.searchStoreGroupName = searchStoreGroupName;
	}

	public String getSearchDeptGroupName() {
		return searchDeptGroupName;
	}

	public void setSearchDeptGroupName(String searchDeptGroupName) {
		this.searchDeptGroupName = searchDeptGroupName;
	}

	public String getSearchShareCompanyId() {
		return searchShareCompanyId;
	}

	public void setSearchShareCompanyId(String searchShareCompanyId) {
		this.searchShareCompanyId = searchShareCompanyId;
	}

	public String getSearchShareUnificationId() {
		return searchShareUnificationId;
	}

	public void setSearchShareUnificationId(String searchShareUnificationId) {
		this.searchShareUnificationId = searchShareUnificationId;
	}

	public String getSearchShareBusinessId() {
		return searchShareBusinessId;
	}

	public void setSearchShareBusinessId(String searchShareBusinessId) {
		this.searchShareBusinessId = searchShareBusinessId;
	}

	public String getSearchShareSalesId() {
		return searchShareSalesId;
	}

	public void setSearchShareSalesId(String searchShareSalesId) {
		this.searchShareSalesId = searchShareSalesId;
	}

	public String getSearchCompanyId() {
		return searchCompanyId;
	}

	public void setSearchCompanyId(String searchCompanyId) {
		this.searchCompanyId = searchCompanyId;
	}

	public String getSearchUnificationId() {
		return searchUnificationId;
	}

	public void setSearchUnificationId(String searchUnificationId) {
		this.searchUnificationId = searchUnificationId;
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

	public String getConditionPemployeeId() {
		return conditionPemployeeId;
	}

	public void setConditionPemployeeId(String conditionPemployeeId) {
		this.conditionPemployeeId = conditionPemployeeId;
	}

	public List<LabelValueBean> getShareCompanyList() {
		return shareCompanyList;
	}

	public void setShareCompanyList(List<LabelValueBean> shareCompanyList) {
		this.shareCompanyList = shareCompanyList;
	}

	public List<LabelValueBean> getShareUnificationList() {
		return shareUnificationList;
	}

	public void setShareUnificationList(List<LabelValueBean> shareUnificationList) {
		this.shareUnificationList = shareUnificationList;
	}

	public List<LabelValueBean> getShareBusinessList() {
		return shareBusinessList;
	}

	public void setShareBusinessList(List<LabelValueBean> shareBusinessList) {
		this.shareBusinessList = shareBusinessList;
	}

	public List<LabelValueBean> getShareSalesList() {
		return shareSalesList;
	}

	public void setShareSalesList(List<LabelValueBean> shareSalesList) {
		this.shareSalesList = shareSalesList;
	}

	public List<LabelValueBean> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<LabelValueBean> companyList) {
		this.companyList = companyList;
	}

	public List<LabelValueBean> getUnificationList() {
		return unificationList;
	}

	public void setUnificationList(List<LabelValueBean> unificationList) {
		this.unificationList = unificationList;
	}

	public List<LabelValueBean> getBusinessList() {
		return businessList;
	}

	public void setBusinessList(List<LabelValueBean> businessList) {
		this.businessList = businessList;
	}

	public List<LabelValueBean> getSalesList() {
		return salesList;
	}

	public void setSalesList(List<LabelValueBean> salesList) {
		this.salesList = salesList;
	}

	public String getSearchOrganizationId() {
		return searchOrganizationId;
	}

	public void setSearchOrganizationId(String searchOrganizationId) {
		this.searchOrganizationId = searchOrganizationId;
	}

	public String getSearchPemployeeId() {
		return searchPemployeeId;
	}

	public void setSearchPemployeeId(String searchPemployeeId) {
		this.searchPemployeeId = searchPemployeeId;
	}

	public List<LabelValueBean> getOrganizationList() {
		return organizationList;
	}

	public void setOrganizationList(List<LabelValueBean> organizationList) {
		this.organizationList = organizationList;
	}

	public List<LabelValueBean> getPemployeeList() {
		return pemployeeList;
	}

	public void setPemployeeList(List<LabelValueBean> pemployeeList) {
		this.pemployeeList = pemployeeList;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getSearchConditionPemployeeId() {
		return searchConditionPemployeeId;
	}

	public void setSearchConditionPemployeeId(String searchConditionPemployeeId) {
		this.searchConditionPemployeeId = searchConditionPemployeeId;
	}

	public String getSearchStoredivision() {
		return searchStoredivision;
	}

	public void setSearchStoredivision(String searchStoredivision) {
		this.searchStoredivision = searchStoredivision;
	}
	
}