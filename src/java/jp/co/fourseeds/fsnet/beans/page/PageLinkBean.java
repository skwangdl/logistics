package jp.co.fourseeds.fsnet.beans.page;

import jp.co.common.frame.beans.BaseBean;

/**
 * コンテンツリンク情報Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2015/11/30 新規作成
 *
 **/
public class PageLinkBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	// SEQUENCE
	private String sequence;
	// ページID
	private String pageId;
	// リンクキーワード
	private String linkName;
	// リンクURL
	private String linkUrl;
	// リンク削除
	private String linkDeleteFlag;
	// ※※※※※※※※※※※※※※※※※※※以下はコンテンツ削除用開始
	// 送信フラグ（コンテンツ削除用）
	private boolean sendFlag;
	// 公開開始日付
	private String startDate;
	// 公開終了日付
	private String endDate;
	// タイトル名
	private String title;
	private String createUserName;
	private String updateUserName;
	private String pageLocation;
	private String dateFlag;
	// ※※※※※※※※※※※※※※※※※※※以下はコンテンツ削除用終了
	
	// ※※※※※※※※※※※※※※※※※※※以下はテンプレート用開始
	// テンプレートページID
	private String templatePageId;
	// テンプレート作成者ユーザID
	private String templateCreateBy;
	
	// ※※※※※※※※※※※※※※※※※※※以下はテンプレート用終了
	
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getLinkDeleteFlag() {
		return linkDeleteFlag;
	}
	public void setLinkDeleteFlag(String linkDeleteFlag) {
		this.linkDeleteFlag = linkDeleteFlag;
	}
	public boolean isSendFlag() {
		return sendFlag;
	}
	public void setSendFlag(boolean sendFlag) {
		this.sendFlag = sendFlag;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getUpdateUserName() {
		return updateUserName;
	}
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
	public String getDateFlag() {
		return dateFlag;
	}
	public void setDateFlag(String dateFlag) {
		this.dateFlag = dateFlag;
	}
	public String getPageLocation() {
		return pageLocation;
	}
	public void setPageLocation(String pageLocation) {
		this.pageLocation = pageLocation;
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