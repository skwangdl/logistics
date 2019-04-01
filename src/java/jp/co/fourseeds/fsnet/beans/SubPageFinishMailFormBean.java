package jp.co.fourseeds.fsnet.beans;

import jp.co.fourseeds.fsnet.beans.page.PageFormBean;

public class SubPageFinishMailFormBean implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6887191460814975536L;
	
	private String pageId;								//コンテンツID
	
	private String mailBody;							//メール内容
	
	private String sendTo;								//メール宛先

	private String ccTo;								//メールCC
	
	private String subject;								//メール件名

	private String onEditFlag;
	
	private PageFormBean pageEdit;
	
	private PageFormBean pageOpen;
	
	private String editFileUrl;
	
	private String openFileUrl;
	
	private int numbersGroup;
	
	private int numbersUser;
	
	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getMailBody() {
		return mailBody;
	}

	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	public String getCcTo() {
		return ccTo;
	}

	public void setCcTo(String ccTo) {
		this.ccTo = ccTo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getOnEditFlag() {
		return onEditFlag;
	}

	public void setOnEditFlag(String onEditFlag) {
		this.onEditFlag = onEditFlag;
	}

	public PageFormBean getPageEdit() {
		return pageEdit;
	}

	public void setPageEdit(PageFormBean pageEdit) {
		this.pageEdit = pageEdit;
	}

	public PageFormBean getPageOpen() {
		return pageOpen;
	}

	public void setPageOpen(PageFormBean pageOpen) {
		this.pageOpen = pageOpen;
	}

	public String getEditFileUrl() {
		return editFileUrl;
	}

	public void setEditFileUrl(String editFileUrl) {
		this.editFileUrl = editFileUrl;
	}

	public String getOpenFileUrl() {
		return openFileUrl;
	}

	public void setOpenFileUrl(String openFileUrl) {
		this.openFileUrl = openFileUrl;
	}

	public int getNumbersGroup() {
		return numbersGroup;
	}

	public void setNumbersGroup(int numbersGroup) {
		this.numbersGroup = numbersGroup;
	}

	public int getNumbersUser() {
		return numbersUser;
	}

	public void setNumbersUser(int numbersUser) {
		this.numbersUser = numbersUser;
	}
}
