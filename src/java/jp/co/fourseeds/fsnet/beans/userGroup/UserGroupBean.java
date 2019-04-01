/**
 * ユーザグループBean
 * 
 * @author NTS
 * @version 1.0.0 : 2016.01.04 新規作成
 */

package jp.co.fourseeds.fsnet.beans.userGroup;

import java.util.List;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.beans.LabelValueBean;

public class UserGroupBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/**■■■■■■■■ここから、検索Bean■■■■■■■■■■■■■■■■*/
	/** 検索組織区分*/
	private String searchOrganizationId;
	
	/** 検索従業員区分*/
	private String searchPemployeeId;
	
	/** 検索ユーザグループid*/
	private String searchUserGroupId;
	
	/** 検索ユーザグループ名*/
	private String searchUserGroupName;
	
	/** 検索区分*/
	private String searchOriginType;
	
	/** 検索テーブル区分　1:公開　2:予約 */
	private String editFlag;
	
	/** 検索ユーザ名*/
	private String searchUserName;
	
	/** 初期検索 */
	private String isFirstSearch;
	
	/** ページ毎に表示件数情報格納 */
	private int searchRowNum;
	
	/**■■■■■■■■ここから、検索結果Bean■■■■■■■■■■■■*/
	/** ユーザグループID */
	private String userGroupId;
	
	/** ユーザグループ名 */
	private String userGroupName;
	
	/** ユーザグループ紹介 */
	private String userGroupIntro;
	
	/**反映予定日時*/
	private String ugRefDate;
	
	/** データ種類　1:公開　2:予約　12:公開&予約 */
	private String originType;
	
	/** ユーザリスト */
	private List<UserGroupDetailBean> userList;
	
	/** ユーザID */
	private String userId;
	
	/** ユーザ名称 */
	private String userName;
	
	/** 個人グループフラグ*/
	private String personalGroupFlag;
	
	/** 統括Id*/
	private String conditionUnificationId;
	
	/** 事業部Id*/
	private String conditionBusinessId;
	
	/** 営業部Id*/
	private String conditionSalesId;
	
	/** 会社id*/
	private String conditionCompanyId;
	
	/** 作成者名 */
	private String createUserName;
	
	/** 会社リスト*/
	private List<LabelValueBean> companyList;
	
	/** 統括リスト*/
	private List<LabelValueBean> unificationList;
	
	/** 事業部リスト*/
	private List<LabelValueBean> businessList;
	
	/** 営業部リスト*/
	private List<LabelValueBean> salesList;
	
	/**組織リスト*/
	private List<LabelValueBean> organizationList;
	
	/**従業員区分リスト*/
	private List<LabelValueBean> pemployeeList;

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

	public String getConditionCompanyId() {
		return conditionCompanyId;
	}

	public void setConditionCompanyId(String conditionCompanyId) {
		this.conditionCompanyId = conditionCompanyId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getConditionUnificationId() {
		return conditionUnificationId;
	}

	public void setConditionUnificationId(String conditionUnificationId) {
		this.conditionUnificationId = conditionUnificationId;
	}

	public String getConditionBusinessId() {
		return conditionBusinessId;
	}

	public void setConditionBusinessId(String conditionBusinessId) {
		this.conditionBusinessId = conditionBusinessId;
	}

	public String getConditionSalesId() {
		return conditionSalesId;
	}

	public void setConditionSalesId(String conditionSalesId) {
		this.conditionSalesId = conditionSalesId;
	}

	public String getSearchUserGroupId() {
		return searchUserGroupId;
	}

	public void setSearchUserGroupId(String searchUserGroupId) {
		this.searchUserGroupId = searchUserGroupId;
	}

	public String getSearchUserGroupName() {
		return searchUserGroupName;
	}

	public void setSearchUserGroupName(String searchUserGroupName) {
		this.searchUserGroupName = searchUserGroupName;
	}

	public String getSearchOriginType() {
		return searchOriginType;
	}

	public void setSearchOriginType(String searchOriginType) {
		this.searchOriginType = searchOriginType;
	}

	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}

	public String getSearchUserName() {
		return searchUserName;
	}

	public void setSearchUserName(String searchUserName) {
		this.searchUserName = searchUserName;
	}

	public String getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}

	public String getUserGroupName() {
		return userGroupName;
	}

	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}

	public String getUserGroupIntro() {
		return userGroupIntro;
	}

	public void setUserGroupIntro(String userGroupIntro) {
		this.userGroupIntro = userGroupIntro;
	}

	public String getUgRefDate() {
		return ugRefDate;
	}

	public void setUgRefDate(String ugRefDate) {
		this.ugRefDate = ugRefDate;
	}

	public String getOriginType() {
		return originType;
	}

	public void setOriginType(String originType) {
		this.originType = originType;
	}

	public List<UserGroupDetailBean> getUserList() {
		return userList;
	}

	public void setUserList(List<UserGroupDetailBean> userList) {
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

	public String getPersonalGroupFlag() {
		return personalGroupFlag;
	}

	public void setPersonalGroupFlag(String personalGroupFlag) {
		this.personalGroupFlag = personalGroupFlag;
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
}