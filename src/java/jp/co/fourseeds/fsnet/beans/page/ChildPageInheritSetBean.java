package jp.co.fourseeds.fsnet.beans.page;

import jp.co.common.frame.beans.BaseBean;

/**
 * 子コンテンツへ情報を再設定フォームBean
 * 
 * @author NTS
 * @version 1.0.0 : 2015/11/27 新規作成
 *
 **/
public class ChildPageInheritSetBean extends BaseBean{

	private static final long serialVersionUID = 1L;

	// ページID
	private String pageId;
	// タイトル名
	private String title;
	// グループ継承
	private String isGroupInherit;
	// ユーザ継承
	private String isUserInherit;
	// 承認者継承
	private String isProxyInherit;
	// コンテンツ区分(0:真実のみ、1:予約のみ、2:両方)-----子コンテンツ情報取得で使用
	private String pageDivision;

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
	public String getIsGroupInherit() {
		return isGroupInherit;
	}
	public void setIsGroupInherit(String isGroupInherit) {
		this.isGroupInherit = isGroupInherit;
	}
	public String getIsUserInherit() {
		return isUserInherit;
	}
	public void setIsUserInherit(String isUserInherit) {
		this.isUserInherit = isUserInherit;
	}
	public String getIsProxyInherit() {
		return isProxyInherit;
	}
	public void setIsProxyInherit(String isProxyInherit) {
		this.isProxyInherit = isProxyInherit;
	}
	public String getPageDivision() {
		return pageDivision;
	}
	public void setPageDivision(String pageDivision) {
		this.pageDivision = pageDivision;
	}
}