package jp.co.fourseeds.fsnet.beans;


import java.util.List;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.beans.LabelValueBean;

/**
 * 全コンテンツBean
 * 
 * @author NTS
 * @version 1.1.0 : 2017.12.16 見直し対応
 */
public class SubWebPageReplaceStuffBean extends BaseBean {

	private static final long serialVersionUID = 5906995637865185563L;
	
	/** 従業員コード*/
	private String userId;
	
	/** 従業員氏名*/
	private String userName;
	
	/** 検索ユーザーId*/
	private String searchUserName;
	
	/** 会社Id*/
	private String conditionCompanyId;
	
	/** 統括Id*/
	private String conditionUnificationId;
	
	/** 事業Id*/
	private String conditionBusinessId;
	
	/** 営業部Id*/
	private String conditionSalesId;
	
	/** 会社名称*/
	private String conditionCompanyName;
	
	/** 統括名称*/
	private String conditionUnificationName;
	
	/** 事業名称*/
	private String conditionBusinessName;
	
	/** 営業部名称*/
	private String conditionSalesName;
	
	/** 会社リスト*/
	private List<LabelValueBean> companyList;
	
	/** 統括リスト*/
	private List<LabelValueBean> unificationList;
	
	/** 事業リスト*/
	private List<LabelValueBean> businessList;
	
	/** 営業部リスト*/
	private List<LabelValueBean> salesList;
	
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

	public String getSearchUserName() {
		return searchUserName;
	}

	public void setSearchUserName(String searchUserName) {
		this.searchUserName = searchUserName;
	}

	public String getConditionCompanyId() {
		return conditionCompanyId;
	}

	public void setConditionCompanyId(String conditionCompanyId) {
		this.conditionCompanyId = conditionCompanyId;
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

	public String getConditionCompanyName() {
		return conditionCompanyName;
	}

	public void setConditionCompanyName(String conditionCompanyName) {
		this.conditionCompanyName = conditionCompanyName;
	}

	public String getConditionUnificationName() {
		return conditionUnificationName;
	}

	public void setConditionUnificationName(String conditionUnificationName) {
		this.conditionUnificationName = conditionUnificationName;
	}

	public String getConditionBusinessName() {
		return conditionBusinessName;
	}

	public void setConditionBusinessName(String conditionBusinessName) {
		this.conditionBusinessName = conditionBusinessName;
	}

	public String getConditionSalesName() {
		return conditionSalesName;
	}

	public void setConditionSalesName(String conditionSalesName) {
		this.conditionSalesName = conditionSalesName;
	}
	
}