/**
 * File Name	: UserSearchResult.java
 * Created Date	: 2007/04/06
 * COPYRIGHT(c)	: DHC
 */

package jp.co.fourseeds.fsnet.beans.department;

import jp.co.common.frame.beans.BaseBean;

public class DepartmentBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/** 部門ID */
	private String departmentId;

	/** 親部門ID */
	private String pDepartmentId;
	
	/** 部門名称 */
	private String departmentName;

	/** 部門紹介 */
	private String departmentCont;

	/** 部門有効終了日付 */
	private String departmentEx;
	
	/** 部門有効開始日付 */
	private String departmentSet;

	/** TEL */
	private String tel;

	/** FAX */
	private String fax;

	/** 代表内線 */
	private String representativeNaisen;
	
	/** ORDER_BY */
	private String orderBy;
	
	/**表示フラグのメンテナンス*/
	private String showFlag;
	
	/**発信部門フラグのメンテナンス*/
	private String sourceDeptFlag;
	
	/** 親部門名称 */
	private String pDepartmentName;
	
	/** 会社コード */
	private String companyId;
	
	/** 会社名 */
	private String companyName;
	
	/** 統括コード */
	private String unificationId;
	
	/** 統括名 */
	private String unificationName;

	/** 事業コード */
	private String businessId;

	/** 事業名 */
	private String businessName;

	/** 営業部コード */
	private String salesId;

	/** 営業部名 */
	private String salesName;
	
	public String getShowFlag() {
		return showFlag;
	}

	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}

	public String getSourceDeptFlag() {
		return sourceDeptFlag;
	}

	public void setSourceDeptFlag(String sourceDeptFlag) {
		this.sourceDeptFlag = sourceDeptFlag;
	}
	
	/**
	 * @return orderBy
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * @param orderBy 設定する orderBy
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * @return departmentCont
	 */
	public String getDepartmentCont() {
		return departmentCont;
	}

	/**
	 * @param departmentCont
	 *            設定する departmentCont
	 */
	public void setDepartmentCont(String departmentCont) {
		this.departmentCont = departmentCont;
	}

	/**
	 * @return departmentEx
	 */
	public String getDepartmentEx() {
		return departmentEx;
	}

	/**
	 * @param departmentEx
	 *            設定する departmentEx
	 */
	public void setDepartmentEx(String departmentEx) {
		this.departmentEx = departmentEx;
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
	 * @return departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName
	 *            設定する departmentName
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * @return fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax
	 *            設定する fax
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return representativeNaisen
	 */
	public String getRepresentativeNaisen() {
		return representativeNaisen;
	}

	/**
	 * @param representativeNaisen
	 *            設定する representativeNaisen
	 */
	public void setRepresentativeNaisen(String representativeNaisen) {
		this.representativeNaisen = representativeNaisen;
	}

	/**
	 * @return tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel
	 *            設定する tel
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return pDepartmentId
	 */
	public String getPDepartmentId() {
		return pDepartmentId;
	}

	/**
	 * @param departmentId 設定する pDepartmentId
	 */
	public void setPDepartmentId(String departmentId) {
		pDepartmentId = departmentId;
	}
	/**
	 * @return departmentSet
	 */
	public String getDepartmentSet() {
		return departmentSet;
	}
	/**
	 * @param departmentSet 設定する departmentSet
	 */
	public void setDepartmentSet(String departmentSet) {
		this.departmentSet = departmentSet;
	}

	public String getpDepartmentId() {
		return pDepartmentId;
	}

	public void setpDepartmentId(String pDepartmentId) {
		this.pDepartmentId = pDepartmentId;
	}

	public String getPDepartmentName() {
		return pDepartmentName;
	}

	public void setPDepartmentName(String pDepartmentName) {
		this.pDepartmentName = pDepartmentName;
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

	public String getUnificationId() {
		return unificationId;
	}

	public void setUnificationId(String unificationId) {
		this.unificationId = unificationId;
	}

	public String getUnificationName() {
		return unificationName;
	}

	public void setUnificationName(String unificationName) {
		this.unificationName = unificationName;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getSalesId() {
		return salesId;
	}

	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}

	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}
	
}
