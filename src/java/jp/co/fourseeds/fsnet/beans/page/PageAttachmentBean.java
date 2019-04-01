package jp.co.fourseeds.fsnet.beans.page;

import java.io.File;

import jp.co.common.frame.beans.BaseBean;

/**
 * コンテンツ添付ファイル情報Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2015/12/03 新規作成
 *
 **/
public class PageAttachmentBean extends BaseBean {
	
	private static final long serialVersionUID = 1L;

	// SEQUENCE
	private String sequence;
	// ページID
	private String pageId;
	// 添付ファイル名称
	private String attachmentName;
	// 添付ファイルURL(ファイル名とアドレス)
	private String attachmentFileUrl;
	// 添付ファイル閲覧URL(閲覧リンク)
	private String attachmentPreviewUrl;
	// 配列順
	private String orderBy;
	// 添付ファイル
	private File attachment;
	// 添付ファイルタイプ 
	private String attachmentContentType;
	// 添付ファイル名称
	private String attachmentFileName;
	// 削除フラグ
	private String fileDeleteFlag;
	// アップロードファイル名称
	private String attUploadFileName;
	// 一時保存アップロードファイル名称
	private String tempSaveUploadFileName;
	
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
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public String getAttachmentFileUrl() {
		return attachmentFileUrl;
	}
	public void setAttachmentFileUrl(String attachmentFileUrl) {
		this.attachmentFileUrl = attachmentFileUrl;
	}
	public String getAttachmentPreviewUrl() {
		return attachmentPreviewUrl;
	}
	public void setAttachmentPreviewUrl(String attachmentPreviewUrl) {
		this.attachmentPreviewUrl = attachmentPreviewUrl;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getFileDeleteFlag() {
		return fileDeleteFlag;
	}
	public void setFileDeleteFlag(String fileDeleteFlag) {
		this.fileDeleteFlag = fileDeleteFlag;
	}
	public File getAttachment() {
		return attachment;
	}
	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}
	public String getAttachmentContentType() {
		return attachmentContentType;
	}
	public void setAttachmentContentType(String attachmentContentType) {
		this.attachmentContentType = attachmentContentType;
	}
	public String getAttachmentFileName() {
		return attachmentFileName;
	}
	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}
	public String getAttUploadFileName() {
		return attUploadFileName;
	}
	public void setAttUploadFileName(String attUploadFileName) {
		this.attUploadFileName = attUploadFileName;
	}
	public String getTempSaveUploadFileName() {
		return tempSaveUploadFileName;
	}
	public void setTempSaveUploadFileName(String tempSaveUploadFileName) {
		this.tempSaveUploadFileName = tempSaveUploadFileName;
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