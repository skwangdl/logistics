package jp.co.fourseeds.fsnet.beans;

import jp.co.common.frame.beans.BaseBean;

/**
 * @author NTS
 * @version 1.0.0 : 2015.11.24 新規作成
 */
public class HomeBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/** 検索条件対象者 */
	private String searchUserDivision;
	
	/** 対象者 */
	private String userDivision;
	
	/** 重要なお知らせ表示開始日 */
	private String necessityDisplayStartDate;
	
	/** 状況*/
	private String readStatus;
	
	/** タイトル */
	private String title;
	
	/** 発信部署 */
	private String departmentName;
	
	/** 公開日 */
	private String confirmDate;
	
	/** 新規/更新状態*/
	private String newOldLable;
	
	/** ページID */
	private String pageId;
	
	/** ファイルURL(ファイル名とアドレス) */
	private String htmlFileUrl;

	public String getSearchUserDivision() {
		return searchUserDivision;
	}

	public void setSearchUserDivision(String searchUserDivision) {
		this.searchUserDivision = searchUserDivision;
	}

	public String getUserDivision() {
		return userDivision;
	}

	public void setUserDivision(String userDivision) {
		this.userDivision = userDivision;
	}

	public String getNecessityDisplayStartDate() {
		return necessityDisplayStartDate;
	}

	public void setNecessityDisplayStartDate(String necessityDisplayStartDate) {
		this.necessityDisplayStartDate = necessityDisplayStartDate;
	}

	public String getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getNewOldLable() {
		return newOldLable;
	}

	public void setNewOldLable(String newOldLable) {
		this.newOldLable = newOldLable;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getHtmlFileUrl() {
		return htmlFileUrl;
	}

	public void setHtmlFileUrl(String htmlFileUrl) {
		this.htmlFileUrl = htmlFileUrl;
	}
}
