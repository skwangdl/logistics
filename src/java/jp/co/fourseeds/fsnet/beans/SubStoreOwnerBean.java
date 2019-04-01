package jp.co.fourseeds.fsnet.beans;

import jp.co.common.frame.beans.BaseBean;

public class SubStoreOwnerBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/**■■■■■■■■ここから、検索Bean■■■■■■■■■■■■■■■■*/
	/** 検索オーナーコード*/
	private String searchOwnerCode;
	
	/** 検索オーナーコード名*/
	private String searchOwnerName;
	
	/** 初期検索 */
	private String isFirstSearch;
	
	/** ページ毎に表示件数情報格納 */
	private int searchRowNum;
	
	/**オーナーコード*/
	private String ownerCode;
	
	/**オーナー名*/
	private String ownerName;


	public String getSearchOwnerCode() {
		return searchOwnerCode;
	}

	public void setSearchOwnerCode(String searchOwnerCode) {
		this.searchOwnerCode = searchOwnerCode;
	}

	public String getSearchOwnerName() {
		return searchOwnerName;
	}

	public void setSearchOwnerName(String searchownerName) {
		this.searchOwnerName = searchownerName;
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

	public String getOwnerCode() {
		return ownerCode;
	}

	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
}
