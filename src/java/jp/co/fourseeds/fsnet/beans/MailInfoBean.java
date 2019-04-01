package jp.co.fourseeds.fsnet.beans;

import javax.activation.DataHandler;

/**
 * @author liuzh
 * @version 1.0
 * @function catches the info of sending mail
 */
public class MailInfoBean {

	/** mail type plain text */
	public static final String MAIL_TYPE_TEXT = "text/plain";

	/** mail type HTML */
	public static final String MAIL_TYPE_HTML = "text/html";

	/** mail from */
	private String from = null;

	/** mail to */
	private String[] to = null;

	/** mail cc */
	private String[] cc = null;

	/** mail bcc */
	private String[] bcc = null;

	/** mail subject */
	private String subject = null;

	/** mail content */
	private String content = null;

	/** mail type */
	private String mailType = null;

	/** mail toUserName */
	private String mailToUserName = null;
	
	private DataHandler dAttach = null;
	
	private String fileName = null;
	
	private String attachFlag = null;
	public String getAttachFlag() {
		return attachFlag;
	}

	public void setAttachFlag(String attachFlag) {
		this.attachFlag = attachFlag;
	}

	/**
	 * @return Returns the bcc.
	 */
	public String[] getBcc() {
		return bcc;
	}

	/**
	 * @param bcc
	 *            The bcc to set.
	 */
	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}

	/**
	 * @return Returns the cc.
	 */
	public String[] getCc() {
		return cc;
	}

	/**
	 * @param cc
	 *            The cc to set.
	 */
	public void setCc(String[] cc) {
		this.cc = cc;
	}

	/**
	 * @return Returns the content.
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            The content to set.
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return Returns the from.
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            The from to set.
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return Returns the mailType.
	 */
	public String getMailType() {
		return mailType;
	}

	/**
	 * @param mailType
	 *            The mailType to set.
	 */
	public void setMailType(String mailType) {
		this.mailType = mailType;
	}

	/**
	 * @return Returns the subject.
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            The subject to set.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return Returns the to.
	 */
	public String[] getTo() {
		return to;
	}

	/**
	 * @param to
	 *            The to to set.
	 */
	public void setTo(String[] to) {
		this.to = to;
	}

	public String getMailToUserName() {
		return mailToUserName;
	}

	public void setMailToUserName(String mailToUserName) {
		this.mailToUserName = mailToUserName;
	}

	public DataHandler getDAttach() {
		return dAttach;
	}

	public void setDAttach(DataHandler attach) {
		dAttach = attach;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
