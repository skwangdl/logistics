package jp.co.fourseeds.fsnet.beans.department;

import jp.co.common.frame.beans.BaseBean;

public class DepartmentLinkBean extends BaseBean {
	private static final long serialVersionUID = 1122334455987654338L;
	/** 部門ID */
	private String departmentId;

	/** リンク名称 */
	private String linkName;

	/** リンクURL */
	private String linkUrl;

	/** リンクコンテンツ */
	private String linkContent;

	/** リンク(表示用) */
	private String link;
	
	private String chkUrlDelFlag;
	
	/**
	 * @return departmentId
	 */
	public String getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId
	 *            設定する departmentId
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return linkContent
	 */
	public String getLinkContent() {
		return linkContent;
	}

	/**
	 * @param linkContent
	 *            設定する linkContent
	 */
	public void setLinkContent(String linkContent) {
		this.linkContent = linkContent;
	}

	/**
	 * @return linkName
	 */
	public String getLinkName() {
		return linkName;
	}

	/**
	 * @param linkName
	 *            設定する linkName
	 */
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	/**
	 * @return linkUrl
	 */
	public String getLinkUrl() {
		return linkUrl;
	}

	/**
	 * @param linkUrl
	 *            設定する linkUrl
	 */
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	/**
	 * @return link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link 設定する link
	 */
	public void setLink(String link) {
		this.link = link;
	}

	public String getChkUrlDelFlag() {
		return chkUrlDelFlag;
	}

	public void setChkUrlDelFlag(String chkUrlDelFlag) {
		this.chkUrlDelFlag = chkUrlDelFlag;
	}

	
}
