package jp.co.fourseeds.fsnet.beans.pageStatus;

import jp.co.common.frame.beans.BaseBean;

public class PageStatusBean extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7715997010417294705L;

	/**ページID*/
	private String searchPageId = "";
	
	/**タイトル名*/
	private String searchTitle;
	
	/** 検索条件 作成・更新ユーザID */
	private String searchEditor;
	
	/** 検索条件 閲覧可不可確認対象ユーザID */
	private String searchUser;
	
	public String getSearchPageId() {
		return searchPageId;
	}

	public void setSearchPageId(String searchPageId) {
		this.searchPageId = searchPageId;
	}

	public String getSearchTitle() {
		return searchTitle;
	}

	public void setSearchTitle(String searchTitle) {
		this.searchTitle = searchTitle;
	}

	public String getSearchEditor() {
		return searchEditor;
	}

	public void setSearchEditor(String searchEditor) {
		this.searchEditor = searchEditor;
	}

	public String getSearchUser() {
		return searchUser;
	}

	public void setSearchUser(String searchUser) {
		this.searchUser = searchUser;
	}
}