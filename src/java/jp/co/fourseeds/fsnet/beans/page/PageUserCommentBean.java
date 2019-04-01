package jp.co.fourseeds.fsnet.beans.page;

import jp.co.common.frame.beans.BaseBean;

/**
 * コンテンツ評価アイテム情報Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2015/12/2 新規作成
 *
 **/
public class PageUserCommentBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	// ページID
	private String pageId;
	// 項目順番
	private String commentOrderBy;
	// 項目名称
	private String userId;
	// SEQUENCE
	private String commentInfo;
	// 評価数
	private String commentDate;
	
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getCommentOrderBy() {
		return commentOrderBy;
	}
	public void setCommentOrderBy(String commentOrderBy) {
		this.commentOrderBy = commentOrderBy;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCommentInfo() {
		return commentInfo;
	}
	public void setCommentInfo(String commentInfo) {
		this.commentInfo = commentInfo;
	}
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
}