package jp.co.fourseeds.fsnet.beans;

import java.util.List;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.beans.LabelValueBean;

@SuppressWarnings("rawtypes")
public class SubPageAuthUserFormBean extends BaseBean {
	/** The Field serialVersionUID */
	private static final long serialVersionUID = 2479865448514900978L;

	/**検索条件：ユーザー名称*/
	private String userSearchName;
	
	/** 検索組織区分*/
	private String searchOrganizationId;
	
	/** 検索組織区分名*/
	private String searchOrganizationName;
	
	/** 検索従業員区分*/
	private String searchPemployeeId;
	
	/** 検索従業員区分名*/
	private String searchPemployeeName;
	
	
	/**組織リスト*/
	private List<LabelValueBean> organizationList;
	
	/**従業員区分リスト*/
	private List<LabelValueBean> pemployeeList;
	
	/** 会社リスト*/
	private List<LabelValueBean> companyList;
	
	/** 統括リスト*/
	private List<LabelValueBean> unificationList;
	
	/** 事業リスト*/
	private List<LabelValueBean> businessList;
	
	/** 営業部リスト*/
	private List<LabelValueBean> salesList;
	
	/** 検索入力会社ID */
	private String searchCompanyId;
	
	/** 検索入力統括ID */
	private String searchUnificationId;
	
	/** 検索入力事業ID */
	private String searchBusinessId;
	
	/** 検索入力営業部ID */
	private String searchSalesId;
	
	/**検索結果：ユーザーリスト*/
	private List userList;
	
	private String tempUserList;
	
	/** 検索入力会社名称 */
	private String searchCompanyName;
	
	/** 検索入力統括名称 */
	private String searchUnificationName;
	
	/** 検索入力事業名称 */
	private String searchBusinessName;
	
	/** 検索入力営業部名称 */
	private String searchSalesName;

	public String getUserSearchName() {
		return userSearchName;
	}

	public void setUserSearchName(String userSearchName) {
		this.userSearchName = userSearchName;
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

	public List getUserList() {
		return userList;
	}

	public void setUserList(List userList) {
		this.userList = userList;
	}

	public String getTempUserList() {
		return tempUserList;
	}

	public void setTempUserList(String tempUserList) {
		this.tempUserList = tempUserList;
	}

	public String getSearchCompanyName() {
		return searchCompanyName;
	}

	public void setSearchCompanyName(String searchCompanyName) {
		this.searchCompanyName = searchCompanyName;
	}

	public String getSearchUnificationName() {
		return searchUnificationName;
	}

	public void setSearchUnificationName(String searchUnificationName) {
		this.searchUnificationName = searchUnificationName;
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

	public String getSearchOrganizationName() {
		return searchOrganizationName;
	}

	public void setSearchOrganizationName(String searchOrganizationName) {
		this.searchOrganizationName = searchOrganizationName;
	}

	public String getSearchPemployeeName() {
		return searchPemployeeName;
	}

	public void setSearchPemployeeName(String searchPemployeeName) {
		this.searchPemployeeName = searchPemployeeName;
	}
	
}
