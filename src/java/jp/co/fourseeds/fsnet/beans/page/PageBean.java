package jp.co.fourseeds.fsnet.beans.page;

import jp.co.common.frame.beans.BaseBean;

/**
 * コンテンツ情報Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2015/11/25 新規作成
 *
 **/
public class PageBean extends BaseBean {

	private static final long serialVersionUID = 1L;
	
	// ページID
	private String pageId;
	// 親ページID
	private String pPageId;
	// タイトル名
	private String title;
	// 公開開始日付
	private String startDate;
	// 公開終了日付
	private String endDate;
	// 公開開始日基準フラグ
	private String startdateOpenFlag;
	// ファイルURL(ファイル名とアドレス)
	private String htmlFileUrl;
	// 予約非公開フラグ
	private String reserveConfirmFlag;
	// 予約公開基準日
	private String reserveConfirmDate;
	// 非公開フラグ
	private String confirmFlag;
	// 公開基準日
	private String confirmDate;
	// 初回公開基準日
	private String firstConfirmDate;
	// 資料公開日時
	private String openDate;
	// 資料公開者ID
	private String openUser;
	// 表示フラグ
	private String displayFlag;
	// リンクフラグ
	private String linkFlag;
	// 画面フラグ
	private String htmlFlag;
	// 配列順
	private String orderBy;
	// ファイル拡張子
	private String fileSuffix;
	// ダウンロード不可フラグ
	private String denyDownloadFlag;
	// 文書リンク表示不可フラグ
	private String denyLinkfileFlag;
	// ダウンロードパスワード
	private String downloadPassword;
	// ページKEY
	private String pageKey;
	// 新着情報非表示フラグ
	private String newUndisplayFlag;
	// 公開基準日付非更新フラグ
	private String confirmNoupdateFlag;
	// 新規表示維持フラグ
	private String newKeepFlag;
	// 公開回数
	private String openNum;
	// 公開維持編集フラグ
	private String onEditFlag;
	// 対象者
	private String userDivision;	
	// 発信部署
	private String sourceDepartment;
	// コンテンツ評価フラグ
	private String evaluationFlag;
	// 評価者氏名表示フラグ
	private String evaluatorDisplayFlag;
	// 前回公開評価者氏名表示フラグ
	private String prevEvaluatorDisplayFlag;
	// 評価者コメント編集可フラグ
	private String commentEditFlag;
	// 複数項目評価可フラグ
	private String pluralEvaluationFlag;
	// 前回複数項目評価可フラグ
	private String prevPluralEvaluationFlag;
	// 評価をクリアするフラグ
	private String evaluationClearFlag;
	// コンテンツ
	private String content;

	// ※※※※※※※※※※※※※※※※※※※テンプレート用開始※※※※※※※※※※※※※※※※※※※※※※
	// テンプレートページID
	private String templatePageId;
	// テンプレート作成者ID
	private String templateCreateBy;
	// ※※※※※※※※※※※※※※※※※※※テンプレート用終了※※※※※※※※※※※※※※※※※※※※※※
	
	// 以降の変数はTBLに存在していない
	// コンテンツ区分(0:真実のみ、1:予約のみ、2:両方)-----子コンテンツ情報取得で使用
	private String pageDivision;

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getPPageId() {
		return pPageId;
	}

	public void setPPageId(String pPageId) {
		this.pPageId = pPageId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getStartdateOpenFlag() {
		return startdateOpenFlag;
	}

	public void setStartdateOpenFlag(String startdateOpenFlag) {
		this.startdateOpenFlag = startdateOpenFlag;
	}

	public String getHtmlFileUrl() {
		return htmlFileUrl;
	}

	public void setHtmlFileUrl(String htmlFileUrl) {
		this.htmlFileUrl = htmlFileUrl;
	}

	public String getConfirmFlag() {
		return confirmFlag;
	}

	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

	public String getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getFirstConfirmDate() {
		return firstConfirmDate;
	}

	public void setFirstConfirmDate(String firstConfirmDate) {
		this.firstConfirmDate = firstConfirmDate;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getOpenUser() {
		return openUser;
	}

	public void setOpenUser(String openUser) {
		this.openUser = openUser;
	}

	public String getDisplayFlag() {
		return displayFlag;
	}

	public void setDisplayFlag(String displayFlag) {
		this.displayFlag = displayFlag;
	}

	public String getLinkFlag() {
		return linkFlag;
	}

	public void setLinkFlag(String linkFlag) {
		this.linkFlag = linkFlag;
	}

	public String getHtmlFlag() {
		return htmlFlag;
	}

	public void setHtmlFlag(String htmlFlag) {
		this.htmlFlag = htmlFlag;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public String getDenyDownloadFlag() {
		return denyDownloadFlag;
	}

	public void setDenyDownloadFlag(String denyDownloadFlag) {
		this.denyDownloadFlag = denyDownloadFlag;
	}

	public String getDenyLinkfileFlag() {
		return denyLinkfileFlag;
	}

	public void setDenyLinkfileFlag(String denyLinkfileFlag) {
		this.denyLinkfileFlag = denyLinkfileFlag;
	}

	public String getDownloadPassword() {
		return downloadPassword;
	}

	public void setDownloadPassword(String downloadPassword) {
		this.downloadPassword = downloadPassword;
	}

	public String getPageKey() {
		return pageKey;
	}

	public void setPageKey(String pageKey) {
		this.pageKey = pageKey;
	}

	public String getNewUndisplayFlag() {
		return newUndisplayFlag;
	}

	public void setNewUndisplayFlag(String newUndisplayFlag) {
		this.newUndisplayFlag = newUndisplayFlag;
	}

	public String getConfirmNoupdateFlag() {
		return confirmNoupdateFlag;
	}

	public void setConfirmNoupdateFlag(String confirmNoupdateFlag) {
		this.confirmNoupdateFlag = confirmNoupdateFlag;
	}

	public String getNewKeepFlag() {
		return newKeepFlag;
	}

	public void setNewKeepFlag(String newKeepFlag) {
		this.newKeepFlag = newKeepFlag;
	}

	public String getOpenNum() {
		return openNum;
	}

	public void setOpenNum(String openNum) {
		this.openNum = openNum;
	}

	public String getOnEditFlag() {
		return onEditFlag;
	}

	public void setOnEditFlag(String onEditFlag) {
		this.onEditFlag = onEditFlag;
	}

	public String getUserDivision() {
		return userDivision;
	}

	public void setUserDivision(String userDivision) {
		this.userDivision = userDivision;
	}

	public String getSourceDepartment() {
		return sourceDepartment;
	}

	public void setSourceDepartment(String sourceDepartment) {
		this.sourceDepartment = sourceDepartment;
	}

	public String getEvaluationFlag() {
		return evaluationFlag;
	}

	public void setEvaluationFlag(String evaluationFlag) {
		this.evaluationFlag = evaluationFlag;
	}

	public String getEvaluatorDisplayFlag() {
		return evaluatorDisplayFlag;
	}

	public void setEvaluatorDisplayFlag(String evaluatorDisplayFlag) {
		this.evaluatorDisplayFlag = evaluatorDisplayFlag;
	}

	public String getPrevEvaluatorDisplayFlag() {
		return prevEvaluatorDisplayFlag;
	}

	public void setPrevEvaluatorDisplayFlag(String prevEvaluatorDisplayFlag) {
		this.prevEvaluatorDisplayFlag = prevEvaluatorDisplayFlag;
	}

	public String getCommentEditFlag() {
		return commentEditFlag;
	}

	public void setCommentEditFlag(String commentEditFlag) {
		this.commentEditFlag = commentEditFlag;
	}

	public String getPluralEvaluationFlag() {
		return pluralEvaluationFlag;
	}

	public void setPluralEvaluationFlag(String pluralEvaluationFlag) {
		this.pluralEvaluationFlag = pluralEvaluationFlag;
	}

	public String getPrevPluralEvaluationFlag() {
		return prevPluralEvaluationFlag;
	}

	public void setPrevPluralEvaluationFlag(String prevPluralEvaluationFlag) {
		this.prevPluralEvaluationFlag = prevPluralEvaluationFlag;
	}

	public String getEvaluationClearFlag() {
		return evaluationClearFlag;
	}

	public void setEvaluationClearFlag(String evaluationClearFlag) {
		this.evaluationClearFlag = evaluationClearFlag;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPageDivision() {
		return pageDivision;
	}

	public void setPageDivision(String pageDivision) {
		this.pageDivision = pageDivision;
	}

	public String getReserveConfirmFlag() {
		return reserveConfirmFlag;
	}

	public void setReserveConfirmFlag(String reserveConfirmFlag) {
		this.reserveConfirmFlag = reserveConfirmFlag;
	}

	public String getReserveConfirmDate() {
		return reserveConfirmDate;
	}

	public void setReserveConfirmDate(String reserveConfirmDate) {
		this.reserveConfirmDate = reserveConfirmDate;
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