package jp.co.fourseeds.fsnet.beans;

import java.util.List;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.beans.LabelValueBean;

/**
 * ユーザ情報の店舗Bean
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2017/09/06		    NTS        	       新規作成
 *
 **/

public class SubUserStoreBean extends BaseBean {

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
	
	/** 検索営業部ID*/
	private String searchSalesId;
	
	/** 検索営業部名*/
	private String searchSalesName;
	
	/** 検索店舗ID*/
	private String searchStoreId;
	
	/** 検索店舗名*/
	private String searchStoreName;
	
	/** 検索店舗区分*/
	private String searchFCFlag;
	
	/** 検索店舗区分名*/
	private String searchFCName;
	
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
	
	/**営業部リスト*/
	private List<LabelValueBean> salesList;
	
	/**組織リスト*/
	private List<LabelValueBean> storeAttrList;
	
	/** 所属コード*/
	private String StoreId;
	
	/** 所属名*/
	private String StoreName;
	
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

	public List<LabelValueBean> getSalesList() {
		return salesList;
	}

	public void setSalesList(List<LabelValueBean> salesList) {
		this.salesList = salesList;
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

	public String getSearchFCFlag() {
		return searchFCFlag;
	}

	public void setSearchFCFlag(String searchFCFlag) {
		this.searchFCFlag = searchFCFlag;
	}

	public String getSearchFCName() {
		return searchFCName;
	}

	public void setSearchFCName(String searchFCName) {
		this.searchFCName = searchFCName;
	}

	public List<LabelValueBean> getStoreAttrList() {
		return storeAttrList;
	}

	public void setStoreAttrList(List<LabelValueBean> storeAttrList) {
		this.storeAttrList = storeAttrList;
	}

	public String getStoreId() {
		return StoreId;
	}

	public void setStoreId(String storeId) {
		StoreId = storeId;
	}

	public String getStoreName() {
		return StoreName;
	}

	public void setStoreName(String storeName) {
		StoreName = storeName;
	}
	
}
