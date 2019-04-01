package jp.co.fourseeds.fsnet.beans.page;

import jp.co.common.frame.beans.BaseBean;

/**
 * コンテンツ公開する個人情報Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2015/12/03 新規作成
 *
 **/
public class AuthUserBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	// ページID
	private String pageId;
	// ユーザID
	private String userId;
	// 必須閲覧区分
	private String necessityReadFlag;
	// ユーザ名称
	private String userName;
	// ユーザ削除区分
	private String userDeleteFlag;
	// ユーザーメールフラグ
	private String userMailFlag;
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
	public String getNecessityReadFlag() {
		return necessityReadFlag;
	}
	public void setNecessityReadFlag(String necessityReadFlag) {
		this.necessityReadFlag = necessityReadFlag;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserDeleteFlag() {
		return userDeleteFlag;
	}
	public void setUserDeleteFlag(String userDeleteFlag) {
		this.userDeleteFlag = userDeleteFlag;
	}
	public String getUserMailFlag() {
		return userMailFlag;
	}
	public void setUserMailFlag(String userMailFlag) {
		this.userMailFlag = userMailFlag;
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