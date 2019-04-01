package jp.co.fourseeds.fsnet.beans.department;

import java.util.List;

import jp.co.common.frame.beans.LabelValueBean;

public class DepartmentFormBean extends DepartmentBean {
	private static final long serialVersionUID = -7715997010417294705L;

	/** 業務担当リスト */
	private List<DepartmentDutyBean> dutyList;

	/** URLリスト */
	private List<DepartmentLinkBean> urlList;

	/** 部門ID */
	private String deptno;
	
	/** システム利用区分 */
	private String role;
	
	/** 検索入力部署ID */
	private String searchDepartmentId;
	
	/** 検索入力部署名称 */
	private String searchDepartmentName;
	
	/** 初期検索区分 */
	private String isFirstSearch;
	
	/** ページ毎に表示件数情報格納 */
	private String searchRowNum;
	
	/** 検索表示フラグ*/
	private String searchShowFlag;
	
	/** 検索表示フラグ名*/
	private String searchShowFlagName;
	
	/** 会社リスト*/
	private List<LabelValueBean> companyList;
	
	/** 統括リスト*/
	private List<LabelValueBean> unificationList;
	
	/** 事業リスト*/
	private List<LabelValueBean> businessList;
	
	/** 営業部リスト*/
	private List<LabelValueBean> salesList;
	
	/** 表示フラグリスト*/
	private List<LabelValueBean> showFlagList;
	
	/** 検索入力会社ID */
	private String searchCompanyId;
	
	/** 検索入力会社名称 */
	private String searchCompanyName;
	
	/** 検索入力統括ID */
	private String searchUnificationId;
	
	/** 検索入力統括名称 */
	private String searchUnificationName;
	
	/** 検索入力事業ID */
	private String searchBusinessId;
	
	/** 検索入力事業名称 */
	private String searchBusinessName;
	
	/** 検索入力営業部ID */
	private String searchSalesId;
	
	/** 検索入力営業部名称 */
	private String searchSalesName;
	
	public List<DepartmentDutyBean> getDutyList() {
		return dutyList;
	}
	public void setDutyList(List<DepartmentDutyBean> dutyList) {
		this.dutyList = dutyList;
	}
	public List<DepartmentLinkBean> getUrlList() {
		return urlList;
	}
	public void setUrlList(List<DepartmentLinkBean> urlList) {
		this.urlList = urlList;
	}
	public String getDeptno() {
		return deptno;
	}
	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getSearchDepartmentId() {
		return searchDepartmentId;
	}
	public void setSearchDepartmentId(String searchDepartmentId) {
		this.searchDepartmentId = searchDepartmentId;
	}
	public String getSearchDepartmentName() {
		return searchDepartmentName;
	}
	public void setSearchDepartmentName(String searchDepartmentName) {
		this.searchDepartmentName = searchDepartmentName;
	}
	public String getIsFirstSearch() {
		return isFirstSearch;
	}
	public void setIsFirstSearch(String isFirstSearch) {
		this.isFirstSearch = isFirstSearch;
	}
	public String getSearchRowNum() {
		return searchRowNum;
	}
	public void setSearchRowNum(String searchRowNum) {
		this.searchRowNum = searchRowNum;
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
	public List<LabelValueBean> getShowFlagList() {
		return showFlagList;
	}
	public void setShowFlagList(List<LabelValueBean> showFlagList) {
		this.showFlagList = showFlagList;
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
	public String getSearchBusinessId() {
		return searchBusinessId;
	}
	public void setSearchBusinessId(String searchBusinessId) {
		this.searchBusinessId = searchBusinessId;
	}
	public String getSearchBusinessName() {
		return searchBusinessName;
	}
	public void setSearchBusinessName(String searchBusinessName) {
		this.searchBusinessName = searchBusinessName;
	}
	public String getSearchSalesId() {
		return searchSalesId;
	}
	public void setSearchSalesId(String searchSalesId) {
		this.searchSalesId = searchSalesId;
	}
	public String getSearchSalesName() {
		return searchSalesName;
	}
	public void setSearchSalesName(String searchSalesName) {
		this.searchSalesName = searchSalesName;
	}
	
}
