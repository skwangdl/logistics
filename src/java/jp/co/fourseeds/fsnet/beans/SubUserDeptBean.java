package jp.co.fourseeds.fsnet.beans;

import java.util.List;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.beans.LabelValueBean;

/**
 * ユーザ情報の本部Bean
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2017/09/06		    NTS        	       新規作成
 *
 **/

public class SubUserDeptBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/**■■■■■■■■ここから、検索Bean■■■■■■■■■■■■■■■■*/
	
	/** 検索会社ID*/
	private String searchCompanyId;
	
	/** 検索営会社名*/
	private String searchCompanyName;
	
	/** 検索統括ID*/
	private String searchUnificationId;
	
	/** 検索統括名*/
	private String searchUnificationName;
	
	/** 検索事業ID*/
	private String searchBusinessId;
	
	/** 検索事業名*/
	private String searchBusinessName;
	
	/** 検索本部ID*/
	private String searchDeptId;
	
	/** 検索本部名*/
	private String searchDeptName;
	
	/** 初期検索 */
	private String isFirstSearch;
	
	/** ページ毎に表示件数情報格納 */
	private int searchRowNum;

	/**統括リスト*/
	private List<LabelValueBean> companyList;
	
	/**統括リスト*/
	private List<LabelValueBean> unificationList;
	
	/**事業リスト*/
	private List<LabelValueBean> businessList;
	
	/** 所属コード*/
	private String DeptId;
	
	/** 所属名*/
	private String DeptName;
	
	/**■■■■■■■■ここから、検索結果Bean■■■■■■■■■■■■*/

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

	public String getDeptId() {
		return DeptId;
	}

	public void setDeptId(String deptId) {
		DeptId = deptId;
	}

	public String getDeptName() {
		return DeptName;
	}

	public void setDeptName(String deptName) {
		DeptName = deptName;
	}
	
}
