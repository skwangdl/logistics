package jp.co.fourseeds.fsnet.beans;

import jp.co.common.frame.beans.BaseBean;

/**
 * 閲覧履歴画面FormBean
 * 
 * @author NTS
 * @version 1.0.0 : 2015.12.18 新規作成
 */
public class ReadHistoryFormBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/**■■■■■■■■ここから、検索Bean■■■■■■■■■■■■■■■■*/
	/** 検索閲覧開始日 */
	private String searchStartDate;
	
	/** 検索閲覧終了日*/
	private String searchEndDate;
	
	/** 検索タイトル */
	private String searchTitle;
	
	/** 初期検索 */
	private String isFirstSearch;
	
	/** ページ毎に表示件数情報格納 */
	private int searchRowNum;
	
	/**■■■■■■■■ここから、検索結果Bean■■■■■■■■■■■■*/
	/** ページID */
	private String pageId;
	
	/** 閲覧開始日 */
	private String startDate;
	
	/** 閲覧終了日 */
	private String endDate;
	
	/** タイトル */
	private String title;
	
	/** ファイルURL(ファイル名とアドレス) */
	private String htmlFileUrl;
	
	/** 閲覧フラグ：未読者のみ/全員 */
	private String readFlag;
	
	/** 対象者ID */
	private String userId;
	
	/** 対象者名 */
	private String userName;
	
	/** 所属 */
	private String belong;
	
	/** 組織区分 */
	private String original;

	/** 閲覧日 */
	private String readedDate;
	
	/** ダウンロードフラグ */
	private String downloadFlag;
	
	/** 督促可否フラグ */
	private String mailSendFlag;
	
	/** 未読者数 */
	private String unReadCount;
	
	/** 対象ページURL */
	private String linkUrl;
	
	/** メール送信ログ */
	private String sendMailLog;
	
	/** メールアドレス */
	private String mailAddress;
	
	/** 転送先メールアドレス */
	private String trmailAddress;
	
	/** メール表題 */
	private String mailTitle;
	
	/** メール内容 */
	private String mailContent;

	public String getSearchStartDate() {
		return searchStartDate;
	}

	public void setSearchStartDate(String searchStartDate) {
		this.searchStartDate = searchStartDate;
	}

	public String getSearchEndDate() {
		return searchEndDate;
	}

	public void setSearchEndDate(String searchEndDate) {
		this.searchEndDate = searchEndDate;
	}

	public String getSearchTitle() {
		return searchTitle;
	}

	public void setSearchTitle(String searchTitle) {
		this.searchTitle = searchTitle;
	}

	public String getIsFirstSearch() {
		return isFirstSearch;
	}

	public void setIsFirstSearch(String isFirstSearch) {
		this.isFirstSearch = isFirstSearch;
	}

	public int getSearchRowNum() {
		return searchRowNum;
	}

	public void setSearchRowNum(int searchRowNum) {
		this.searchRowNum = searchRowNum;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
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

	public String getHtmlFileUrl() {
		return htmlFileUrl;
	}

	public void setHtmlFileUrl(String htmlFileUrl) {
		this.htmlFileUrl = htmlFileUrl;
	}

	public String getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
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
	public String getBelong() {
		return belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public String getReadedDate() {
		return readedDate;
	}

	public void setReadedDate(String readedDate) {
		this.readedDate = readedDate;
	}

	public String getDownloadFlag() {
		return downloadFlag;
	}

	public void setDownloadFlag(String downloadFlag) {
		this.downloadFlag = downloadFlag;
	}

	public String getMailSendFlag() {
		return mailSendFlag;
	}

	public void setMailSendFlag(String mailSendFlag) {
		this.mailSendFlag = mailSendFlag;
	}

	public String getUnReadCount() {
		return unReadCount;
	}

	public void setUnReadCount(String unReadCount) {
		this.unReadCount = unReadCount;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getSendMailLog() {
		return sendMailLog;
	}

	public void setSendMailLog(String sendMailLog) {
		this.sendMailLog = sendMailLog;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getTrmailAddress() {
		return trmailAddress;
	}

	public void setTrmailAddress(String trmailAddress) {
		this.trmailAddress = trmailAddress;
	}

	public String getMailTitle() {
		return mailTitle;
	}

	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}
}
