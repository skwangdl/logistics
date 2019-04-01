package jp.co.fourseeds.fsnet.beans.page;

import jp.co.common.frame.beans.BaseBean;

/**
 * コンテンツコメント管理者情報Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2015/12/02 新規作成
 *
 **/
public class PageCommentAdminBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	// ページID
	private String pageId;
	// ユーザID
	private String userId;
	// ユーザ名称
	private String userName;
	
	// ※※※※※※※※※※※※※※※※※※※以下はテンプレート用開始
	// テンプレートページID
	private String templatePageId;
	// テンプレート作成者ユーザID
	private String templateCreateBy;
	
	// ※※※※※※※※※※※※※※※※※※※以下はテンプレート用終了
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
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
	public String getTemplatePageId() {
		return templatePageId;
	}
	public void setTemplatePageId(String templatePageId) {
		this.templatePageId = templatePageId;
	}
	public String getTemplateCreateBy() {
		return templateCreateBy;
	}
	public void setTemplateCreateBy(String templateCreateBy) {
		this.templateCreateBy = templateCreateBy;
	}

}