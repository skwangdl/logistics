package jp.co.fourseeds.fsnet.beans;

import jp.co.common.frame.beans.BaseBean;

public class WebPageReplaceBean extends BaseBean {

	private static final long serialVersionUID = 4388234923404188955L;
	
	/**■■■■■■■■ここから、検索Bean■■■■■■■■■■■■■■■■*/
	/** 検索社員番号*/
	private String searchUserId;
	
	/** 検索氏名*/
	private String searchUserName;
	
	/** 初期検索 */
	private String isFirstSearch;
	
	/** ページ毎に表示件数情報格納 */
	private int searchRowNum;
	
	/**■■■■■■■■ここから、検索結果Bean■■■■■■■■■■■■*/
	
	/** 検索社員番号*/
	private String replaceSearchUserId;
	
	/** ページID*/
	private String pageId;
	
	/** タイトル名*/
	private String title;
	
	/** 公開開始日付*/
	private String startDate;
	
	/** 対象者*/
	private String userDivision;
	
	/** 発信部署*/
	private String sourceDepartment;
	
	/** 予約コンテンツ*/
	private String reserveFlag;
	
	/** ファイルURL(ファイル名とアドレス) */
	private String htmlFileUrl;
	
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

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getUserDivision() {
		return userDivision;
	}

	public void setUserDivision(String userDivision) {
		this.userDivision = userDivision;
	}

	public String getSourceDepartment() {
		return sourceDepartment;
	}

	public void setSourceDepartment(String sourceDepartment) {
		this.sourceDepartment = sourceDepartment;
	}

	public String getReserveFlag() {
		return reserveFlag;
	}

	public void setReserveFlag(String reserveFlag) {
		this.reserveFlag = reserveFlag;
	}

	public String getReplaceSearchUserId() {
		return replaceSearchUserId;
	}

	public void setReplaceSearchUserId(String replaceSearchUserId) {
		this.replaceSearchUserId = replaceSearchUserId;
	}

	public String getHtmlFileUrl() {
		return htmlFileUrl;
	}

	public void setHtmlFileUrl(String htmlFileUrl) {
		this.htmlFileUrl = htmlFileUrl;
	}
	
}
