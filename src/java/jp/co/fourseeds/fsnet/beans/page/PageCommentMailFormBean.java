
package jp.co.fourseeds.fsnet.beans.page;

import jp.co.common.frame.beans.BaseBean;

/**
 * コンテンツフォームBean
 * 
 * @author NTS
 * @version 1.0.0 : 2015/11/27 新規作成
 *
 **/
public class PageCommentMailFormBean extends BaseBean {

	private static final long serialVersionUID = 1L;
	
	// ページID
	private String pageId;
	
	// タイトル名
	private String title;
	
	//
	private String evaluatorDisplayFlag;
	
	//
	private String openDate;
	
	//
	private String commentMailTitle;
	
	//
	private String commentSendMailUserName;
	
	//
	private String commentMailInfo;
	
	private String mailToUserName;
	
	private String hyoukaErrorMessage;
	
	private PageCommentRateBean commentBean;

	public String getCommentMailInfo() {
		return commentMailInfo;
	}

	public void setCommentMailInfo(String commentMailInfo) {
		this.commentMailInfo = commentMailInfo;
	}

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

	public PageCommentRateBean getCommentBean() {
		return commentBean;
	}

	public void setCommentBean(PageCommentRateBean commentBean) {
		this.commentBean = commentBean;
	}

	public String getEvaluatorDisplayFlag() {
		return evaluatorDisplayFlag;
	}

	public void setEvaluatorDisplayFlag(String evaluatorDisplayFlag) {
		this.evaluatorDisplayFlag = evaluatorDisplayFlag;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getCommentMailTitle() {
		return commentMailTitle;
	}

	public void setCommentMailTitle(String commentMailTitle) {
		this.commentMailTitle = commentMailTitle;
	}

	public String getCommentSendMailUserName() {
		return commentSendMailUserName;
	}

	public void setCommentSendMailUserName(String commentSendMailUserName) {
		this.commentSendMailUserName = commentSendMailUserName;
	}

	public String getMailToUserName() {
		return mailToUserName;
	}

	public void setMailToUserName(String mailToUserName) {
		this.mailToUserName = mailToUserName;
	}

	public String getHyoukaErrorMessage() {
		return hyoukaErrorMessage;
	}

	public void setHyoukaErrorMessage(String hyoukaErrorMessage) {
		this.hyoukaErrorMessage = hyoukaErrorMessage;
	}
}