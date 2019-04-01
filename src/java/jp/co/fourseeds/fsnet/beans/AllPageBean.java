package jp.co.fourseeds.fsnet.beans;

import jp.co.common.frame.beans.BaseBean;

/**
 * 全コンテンツBean
 * 
 * @author NTS
 * @version 1.0.0 : 2015.12.16 新規作成
 * @version 1.1.0 : 2017.10.18 minaosi
 */
public class AllPageBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/** ページID */
	private String pageId;
	
	/** タイトル */
	private String title;
	
	/** 公開基準日 */
	private String confirmDate;
	
	/** ファイルURL(ファイル名とアドレス) */
	private String htmlFileUrl;
	
	/** 対象者*/
	private String userDivision;
	
	/** 新規/更新状態*/
	private String newOldLable;
	
	/** 発信部署 */
	private String ssmDepartmentName;

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

	public String getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getHtmlFileUrl() {
		return htmlFileUrl;
	}

	public void setHtmlFileUrl(String htmlFileUrl) {
		this.htmlFileUrl = htmlFileUrl;
	}

	public String getUserDivision() {
		return userDivision;
	}

	public void setUserDivision(String userDivision) {
		this.userDivision = userDivision;
	}

	public String getNewOldLable() {
		return newOldLable;
	}

	public void setNewOldLable(String newOldLable) {
		this.newOldLable = newOldLable;
	}

	public String getSsmDepartmentName() {
		return ssmDepartmentName;
	}

	public void setSsmDepartmentName(String ssmDepartmentName) {
		this.ssmDepartmentName = ssmDepartmentName;
	}

}
