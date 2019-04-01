package jp.co.fourseeds.fsnet.beans.pageRate;

import java.util.Date;

import jp.co.fourseeds.fsnet.beans.page.PageRateItemBean;

/**
 * コンテンツ評価情報明細Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2016/02/03 新規作成1.1
 * @version 1.1.0 : 2017/10/12イントラminaosi
 *
 **/
public class PageRateDetailBean extends PageRateItemBean {

	private static final long serialVersionUID = 1L;
	
	/** ユーザーID */
	private String userId;
	
	/** 氏名 */
	private String userName;
	
	/** コメント順番 */
	private String commentOrderBy;
	
	/** コメント内容 */
	private String commentInfo;
	
	/** コメント内容CSV */
	private String commentInfoCsv;
	
	/** コメント日時 */
	private String commentDate;
	
	/** コメント日時CSV用 */
	private Date commentDateCsv;
	
	/** 所属ID */
	private String belong;
	
	/** 所属名称 */
	private String belongName;
	
	/**
	 * @return the commentDateCsv
	 */
	public Date getCommentDateCsv() {
		return commentDateCsv;
	}

	/**
	 * @param commentDateCsv the commentDateCsv to set
	 */
	public void setCommentDateCsv(Date commentDateCsv) {
		this.commentDateCsv = commentDateCsv;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the commentOrderBy
	 */
	public String getCommentOrderBy() {
		return commentOrderBy;
	}

	/**
	 * @param commentOrderBy the commentOrderBy to set
	 */
	public void setCommentOrderBy(String commentOrderBy) {
		this.commentOrderBy = commentOrderBy;
	}

	/**
	 * @return the commentInfo
	 */
	public String getCommentInfo() {
		return commentInfo;
	}

	/**
	 * @param commentInfo the commentInfo to set
	 */
	public void setCommentInfo(String commentInfo) {
		this.commentInfo = commentInfo;
	}

	/**
	 * @return the commentInfoCsv
	 */
	public String getCommentInfoCsv() {
		return commentInfoCsv;
	}

	/**
	 * @param commentInfoCsv the commentInfoCsv to set
	 */
	public void setCommentInfoCsv(String commentInfoCsv) {
		this.commentInfoCsv = commentInfoCsv;
	}

	/**
	 * @return the commentDate
	 */
	public String getCommentDate() {
		return commentDate;
	}

	/**
	 * @param commentDate the commentDate to set
	 */
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	/**
	 * @return the belong
	 */
	public String getBelong() {
		return belong;
	}

	/**
	 * @param belong the belong to set
	 */
	public void setBelong(String belong) {
		this.belong = belong;
	}

	/**
	 * @return the belongName
	 */
	public String getBelongName() {
		return belongName;
	}

	/**
	 * @param belongName the belongName to set
	 */
	public void setBelongName(String belongName) {
		this.belongName = belongName;
	}

}