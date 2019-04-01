
package jp.co.fourseeds.fsnet.beans.page;

import java.util.List;

import jp.co.common.frame.beans.LabelValueBean;

import jp.co.fourseeds.fsnet.beans.page.AuthUserBean;
import jp.co.fourseeds.fsnet.beans.page.PageBean;

/**
 * コンテンツフォームBean
 * 
 * @author NTS
 * @version 1.0.0 : 2015/11/27 新規作成
 *
 **/
public class PageFormBean extends PageBean {

	private static final long serialVersionUID = 1L;

	// コンテンツリンク情報(リンク添付)リスト
	private List<PageLinkBean> pageLinkList;
	// コンテンツ添付ファイル情報（文書添付）リスト
	private List<PageAttachmentBean> pageAttachmentList;
	// コンテンツ閲覧権限ユーザ情報(公開する個人)リスト
	private List<AuthUserBean> authUserList;
	// コンテンツ閲覧権限グループ情報(公開するグループ)リスト
	private List<AuthGroupBean> authGroupList;
	// コンテンツ更新代行者(承認者)リスト
	private List<ProxyUserBean> proxyUserList;
	// 対象者選択区分
	private String userType;
	// 対象者ドロップダウンリスト
	private List<LabelValueBean> userDivisionDropList;
	// 発信部署ドロップダウンリスト
	private List<LabelValueBean> sourceDeptDropList;
	// 対象者入力
	private String userDivisionR;
	// コンテンツ評価アイテム情報リスト
	private List<PageRateItemBean> pageRateItemBeanList;
	// コンテンツコメント管理者情報選択(問題報告対応者)リスト
	private List<PageCommentAdminBean> pageCommentAdminOptionList;
	// コンテンツコメント管理者情報選択(問題報告対応者)ユーザID
	private String commentAdminOptionUserId;
	// コンテンツコメント管理者情報選択(問題報告対応者)ユーザ名称
	private String commentAdminOptionUserName;
	// コンテンツコメント管理者情報(問題報告対応者)リスト
	private List<PageCommentAdminBean> pageCommentAdminList;
	// コンテンツコメント管理者情報(問題報告対応者)ユーザID
	private String commentAdminUserId;
	// コンテンツコメント管理者情報(問題報告対応者)ユーザ名称
	private String commentAdminUserName;
	// コンテンツ（リンクを含む）
	private String contents;
	// ページ表示フラグ「コンテンツ登録：ADD、コンテンツ更新：UPD、コンテンツ公開:OPEN」
	private String pageViewFlag;
	// コンテンツ配置先、例えば[HOME]-[案内/通達]-[コンテンツ配置先表示] 
	private String pageLocation;
	// 子コンテンツへ情報を再設定情報
	private ChildPageFormBean childPageFormBean;
	// 「1以外(公開中)、1(予約中)」を表す
	private String updateradio;
	// 評価者氏名表示フラグ(コンテンツ情報)
	private String evaluatorDisplayFlagPage;
	// 前回複数項目評価可フラグ(コンテンツ情報)
	private String prevPluralEvaluationFlagPage;
	// 公開確認日付非更新フラグ(公開日付変更なし)表示フラグ
	private String viewConfirmNoupdateFlag;
	// 「新規」表示維持表示フラグ
	private String viewNewKeepFlag;
	// 画面遷移元区分
	private String originType;
	// 子コンテンツ反映ログ
	private List<String> updateChildLog;
	// 当コンテンツは予約　OR　真実を標識
	private String isReserve;
	// テンプレート存在フラグ
	private String templateExistFlag;
	// 作成ユーザ名称
	private String createUserName;
	// 文書ファイルのリンク表示不可
	private String showFlag;
	// 「資料公開」、「メール送信」操作制御フラグ
	private String openEnableFlag;
	// 添付ファイルいるフラグ
	private String downloadAccessFlag;
	// 更新権限
	private String editEnableFlag;
	// リクエストのContextPathを保持
	private String contextPath;
	// リクエストのserverNameを保持
	private String serverName;
	// child存在フラグ
	private String childExistFlag;
	// コンテンツ評価フラグ(コンテンツ情報)
	private String evaluationFlagPage;
	// コンテンツ評価表示フラグ
	private String showHyoukaFlag;
	// コンテンツコメント統計情報
	private int commentCount;
	// 評価異常メッセージ
	private String hyoukaErrorMessage;
	// 評価検索区分
	private String searchCommentFlag;
	// 評価編集区分
	private String editCommentFlag;
	//
	private List<PageCommentRateBean> commentList;
	//
	private String pencilEditFlag;
	//
	private String addCommentInfo;
	//
	private String commentUserId;
	//
	private String commentOrderBy;
	//
	private String commentFlag;
	// コンテンツ添付ファイルリスト
	private String downloadAttchmentList;
	// タイトル名（編集）
	private String titles;
	// 文書添付存在フラグ
	private String attachmentExistFlag;
	// 親ページ存在フラグ
	private String ppageExsitsFlg;
	// 未来公開フラグ
	private String futureOpenFlag;
	// 「0：(予約のみ削除)、1(予約及び公開中のコンテンツを削除)」を表す
	private String deleteradio;
	// このコンテンツのURL
	private String pageUrl;
	// メインファイル
	private String mainFile;
	// コンテンツファイルURL
	private String pageFileUrl;
	// 発信部署ドロップダウンリスト
	private String sourceDeptNm;
	// ノードID
	private String nodeId;
	// HTML作成イベント
	private String createHtmlEvent;
	// コンテンツ削除用メールログ
	private String deleteMailLog;
	// 「テンプレートから」区分
	private String isTemplateFrom;
	// テンプレートのケース（発信部署が存在しない）
	private String templateNoExistDepartmentId;
	// 配置先設定フラグ
	private String pageLocationSetFlag;
	
	public List<PageLinkBean> getPageLinkList() {
		return pageLinkList;
	}
	public void setPageLinkList(List<PageLinkBean> pageLinkList) {
		this.pageLinkList = pageLinkList;
	}
	public List<PageAttachmentBean> getPageAttachmentList() {
		return pageAttachmentList;
	}
	public void setPageAttachmentList(List<PageAttachmentBean> pageAttachmentList) {
		this.pageAttachmentList = pageAttachmentList;
	}
	public List<AuthUserBean> getAuthUserList() {
		return authUserList;
	}
	public void setAuthUserList(List<AuthUserBean> authUserList) {
		this.authUserList = authUserList;
	}
	public List<AuthGroupBean> getAuthGroupList() {
		return authGroupList;
	}
	public void setAuthGroupList(List<AuthGroupBean> authGroupList) {
		this.authGroupList = authGroupList;
	}
	public List<ProxyUserBean> getProxyUserList() {
		return proxyUserList;
	}
	public void setProxyUserList(List<ProxyUserBean> proxyUserList) {
		this.proxyUserList = proxyUserList;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public List<LabelValueBean> getUserDivisionDropList() {
		return userDivisionDropList;
	}
	public void setUserDivisionDropList(List<LabelValueBean> userDivisionDropList) {
		this.userDivisionDropList = userDivisionDropList;
	}
	public List<LabelValueBean> getSourceDeptDropList() {
		return sourceDeptDropList;
	}
	public void setSourceDeptDropList(List<LabelValueBean> sourceDeptDropList) {
		this.sourceDeptDropList = sourceDeptDropList;
	}
	public String getUserDivisionR() {
		return userDivisionR;
	}
	public void setUserDivisionR(String userDivisionR) {
		this.userDivisionR = userDivisionR;
	}
	public List<PageRateItemBean> getPageRateItemBeanList() {
		return pageRateItemBeanList;
	}
	public void setPageRateItemBeanList(List<PageRateItemBean> pageRateItemBeanList) {
		this.pageRateItemBeanList = pageRateItemBeanList;
	}
	public List<PageCommentAdminBean> getPageCommentAdminOptionList() {
		return pageCommentAdminOptionList;
	}
	public void setPageCommentAdminOptionList(List<PageCommentAdminBean> pageCommentAdminOptionList) {
		this.pageCommentAdminOptionList = pageCommentAdminOptionList;
	}
	public String getCommentAdminOptionUserId() {
		return commentAdminOptionUserId;
	}
	public void setCommentAdminOptionUserId(String commentAdminOptionUserId) {
		this.commentAdminOptionUserId = commentAdminOptionUserId;
	}
	public String getCommentAdminOptionUserName() {
		return commentAdminOptionUserName;
	}
	public void setCommentAdminOptionUserName(String commentAdminOptionUserName) {
		this.commentAdminOptionUserName = commentAdminOptionUserName;
	}
	public List<PageCommentAdminBean> getPageCommentAdminList() {
		return pageCommentAdminList;
	}
	public void setPageCommentAdminList(List<PageCommentAdminBean> pageCommentAdminList) {
		this.pageCommentAdminList = pageCommentAdminList;
	}
	public String getCommentAdminUserId() {
		return commentAdminUserId;
	}
	public void setCommentAdminUserId(String commentAdminUserId) {
		this.commentAdminUserId = commentAdminUserId;
	}
	public String getCommentAdminUserName() {
		return commentAdminUserName;
	}
	public void setCommentAdminUserName(String commentAdminUserName) {
		this.commentAdminUserName = commentAdminUserName;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getPageViewFlag() {
		return pageViewFlag;
	}
	public void setPageViewFlag(String pageViewFlag) {
		this.pageViewFlag = pageViewFlag;
	}
	public String getPageLocation() {
		return pageLocation;
	}
	public void setPageLocation(String pageLocation) {
		this.pageLocation = pageLocation;
	}
	public ChildPageFormBean getChildPageFormBean() {
		return childPageFormBean;
	}
	public void setChildPageFormBean(ChildPageFormBean childPageFormBean) {
		this.childPageFormBean = childPageFormBean;
	}
	public String getUpdateradio() {
		return updateradio;
	}
	public void setUpdateradio(String updateradio) {
		this.updateradio = updateradio;
	}
	public String getEvaluatorDisplayFlagPage() {
		return evaluatorDisplayFlagPage;
	}
	public void setEvaluatorDisplayFlagPage(String evaluatorDisplayFlagPage) {
		this.evaluatorDisplayFlagPage = evaluatorDisplayFlagPage;
	}
	public String getViewConfirmNoupdateFlag() {
		return viewConfirmNoupdateFlag;
	}
	public void setViewConfirmNoupdateFlag(String viewConfirmNoupdateFlag) {
		this.viewConfirmNoupdateFlag = viewConfirmNoupdateFlag;
	}
	public String getViewNewKeepFlag() {
		return viewNewKeepFlag;
	}
	public void setViewNewKeepFlag(String viewNewKeepFlag) {
		this.viewNewKeepFlag = viewNewKeepFlag;
	}
	public String getOriginType() {
		return originType;
	}
	public void setOriginType(String originType) {
		this.originType = originType;
	}
	public List<String> getUpdateChildLog() {
		return updateChildLog;
	}
	public void setUpdateChildLog(List<String> updateChildLog) {
		this.updateChildLog = updateChildLog;
	}
	public String getIsReserve() {
		return isReserve;
	}
	public void setIsReserve(String isReserve) {
		this.isReserve = isReserve;
	}
	public String getTemplateExistFlag() {
		return templateExistFlag;
	}
	public void setTemplateExistFlag(String templateExistFlag) {
		this.templateExistFlag = templateExistFlag;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getShowFlag() {
		return showFlag;
	}
	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}
	public String getOpenEnableFlag() {
		return openEnableFlag;
	}
	public void setOpenEnableFlag(String openEnableFlag) {
		this.openEnableFlag = openEnableFlag;
	}
	public String getDownloadAccessFlag() {
		return downloadAccessFlag;
	}
	public void setDownloadAccessFlag(String downloadAccessFlag) {
		this.downloadAccessFlag = downloadAccessFlag;
	}
	public String getEditEnableFlag() {
		return editEnableFlag;
	}
	public void setEditEnableFlag(String editEnableFlag) {
		this.editEnableFlag = editEnableFlag;
	}
	public String getContextPath() {
		return contextPath;
	}
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	public String getChildExistFlag() {
		return childExistFlag;
	}
	public void setChildExistFlag(String childExistFlag) {
		this.childExistFlag = childExistFlag;
	}
	public String getPrevPluralEvaluationFlagPage() {
		return prevPluralEvaluationFlagPage;
	}
	public void setPrevPluralEvaluationFlagPage(String prevPluralEvaluationFlagPage) {
		this.prevPluralEvaluationFlagPage = prevPluralEvaluationFlagPage;
	}
	public String getEvaluationFlagPage() {
		return evaluationFlagPage;
	}
	public void setEvaluationFlagPage(String evaluationFlagPage) {
		this.evaluationFlagPage = evaluationFlagPage;
	}
	public String getShowHyoukaFlag() {
		return showHyoukaFlag;
	}
	public void setShowHyoukaFlag(String showHyoukaFlag) {
		this.showHyoukaFlag = showHyoukaFlag;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public String getHyoukaErrorMessage() {
		return hyoukaErrorMessage;
	}
	public void setHyoukaErrorMessage(String hyoukaErrorMessage) {
		this.hyoukaErrorMessage = hyoukaErrorMessage;
	}
	public String getSearchCommentFlag() {
		return searchCommentFlag;
	}
	public void setSearchCommentFlag(String searchCommentFlag) {
		this.searchCommentFlag = searchCommentFlag;
	}
	public String getEditCommentFlag() {
		return editCommentFlag;
	}
	public void setEditCommentFlag(String editCommentFlag) {
		this.editCommentFlag = editCommentFlag;
	}
	public List<PageCommentRateBean> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<PageCommentRateBean> commentList) {
		this.commentList = commentList;
	}
	public String getPencilEditFlag() {
		return pencilEditFlag;
	}
	public void setPencilEditFlag(String pencilEditFlag) {
		this.pencilEditFlag = pencilEditFlag;
	}
	public String getAddCommentInfo() {
		return addCommentInfo;
	}
	public void setAddCommentInfo(String addCommentInfo) {
		this.addCommentInfo = addCommentInfo;
	}
	public String getCommentUserId() {
		return commentUserId;
	}
	public void setCommentUserId(String commentUserId) {
		this.commentUserId = commentUserId;
	}
	public String getCommentOrderBy() {
		return commentOrderBy;
	}
	public void setCommentOrderBy(String commentOrderBy) {
		this.commentOrderBy = commentOrderBy;
	}
	public String getCommentFlag() {
		return commentFlag;
	}
	public void setCommentFlag(String commentFlag) {
		this.commentFlag = commentFlag;
	}
	public String getAttachmentExistFlag() {
		return attachmentExistFlag;
	}
	public void setAttachmentExistFlag(String attachmentExistFlag) {
		this.attachmentExistFlag = attachmentExistFlag;
	}
	public String getDownloadAttchmentList() {
		return downloadAttchmentList;
	}
	public void setDownloadAttchmentList(String downloadAttchmentList) {
		this.downloadAttchmentList = downloadAttchmentList;
	}
	public String getTitles() {
		return titles;
	}
	public void setTitles(String titles) {
		this.titles = titles;
	}
	public String getPpageExsitsFlg() {
		return ppageExsitsFlg;
	}
	public void setPpageExsitsFlg(String ppageExsitsFlg) {
		this.ppageExsitsFlg = ppageExsitsFlg;
	}
	public String getFutureOpenFlag() {
		return futureOpenFlag;
	}
	public void setFutureOpenFlag(String futureOpenFlag) {
		this.futureOpenFlag = futureOpenFlag;
	}
	public String getDeleteradio() {
		return deleteradio;
	}
	public void setDeleteradio(String deleteradio) {
		this.deleteradio = deleteradio;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public String getMainFile() {
		return mainFile;
	}
	public void setMainFile(String mainFile) {
		this.mainFile = mainFile;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getPageFileUrl() {
		return pageFileUrl;
	}
	public void setPageFileUrl(String pageFileUrl) {
		this.pageFileUrl = pageFileUrl;
	}
	public String getSourceDeptNm() {
		return sourceDeptNm;
	}
	public void setSourceDeptNm(String sourceDeptNm) {
		this.sourceDeptNm = sourceDeptNm;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getCreateHtmlEvent() {
		return createHtmlEvent;
	}
	public void setCreateHtmlEvent(String createHtmlEvent) {
		this.createHtmlEvent = createHtmlEvent;
	}
	public String getDeleteMailLog() {
		return deleteMailLog;
	}
	public void setDeleteMailLog(String deleteMailLog) {
		this.deleteMailLog = deleteMailLog;
	}
	public String getIsTemplateFrom() {
		return isTemplateFrom;
	}
	public void setIsTemplateFrom(String isTemplateFrom) {
		this.isTemplateFrom = isTemplateFrom;
	}
	public String getTemplateNoExistDepartmentId() {
		return templateNoExistDepartmentId;
	}
	public void setTemplateNoExistDepartmentId(String templateNoExistDepartmentId) {
		this.templateNoExistDepartmentId = templateNoExistDepartmentId;
	}
	public String getPageLocationSetFlag() {
		return pageLocationSetFlag;
	}
	public void setPageLocationSetFlag(String pageLocationSetFlag) {
		this.pageLocationSetFlag = pageLocationSetFlag;
	}
	
}