package jp.co.fourseeds.fsnet.beans.page;

import java.util.List;

/**
 * 子コンテンツへ情報を再設定フォームBean
 * 
 * @author NTS
 * @version 1.0.0 : 2015/11/27 新規作成
 *
 **/
public class ChildPageFormBean {

	// ページID（親ページIDを表す）
	private String pageId;
	// 「1」の場合、子画面Onloadで画面閉じる
	private String isClose;
	// グループ継承種類		COVER：置き換え　ADD：追加
	private String groupInheritType;
	// ユーザー継承種類		COVER：置き換え　ADD：追加
	private String userInheritType;
	// 承認者継承種類		COVER：置き換え　ADD：追加
	private String proxyInheritType;
	// コンテンツ閲覧権限グループ情報(公開するグループ)リスト
	private List<AuthGroupBean> authGroupList;
	// コンテンツ閲覧権限ユーザ情報(公開する個人)リスト
	private List<AuthUserBean> authUserList;
	// コンテンツ更新代行者リスト(承認者)
	private List<ProxyUserBean> proxyUserList;
	// 子コンテンツ継承設定リスト
	private List<ChildPageInheritSetBean> inheritSetList;

	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getIsClose() {
		return isClose;
	}
	public void setIsClose(String isClose) {
		this.isClose = isClose;
	}
	public List<AuthGroupBean> getAuthGroupList() {
		return authGroupList;
	}
	public void setAuthGroupList(List<AuthGroupBean> authGroupList) {
		this.authGroupList = authGroupList;
	}
	public List<AuthUserBean> getAuthUserList() {
		return authUserList;
	}
	public void setAuthUserList(List<AuthUserBean> authUserList) {
		this.authUserList = authUserList;
	}
	public List<ProxyUserBean> getProxyUserList() {
		return proxyUserList;
	}
	public void setProxyUserList(List<ProxyUserBean> proxyUserList) {
		this.proxyUserList = proxyUserList;
	}
	public List<ChildPageInheritSetBean> getInheritSetList() {
		return inheritSetList;
	}
	public void setInheritSetList(List<ChildPageInheritSetBean> inheritSetList) {
		this.inheritSetList = inheritSetList;
	}
	public String getGroupInheritType() {
		return groupInheritType;
	}
	public void setGroupInheritType(String groupInheritType) {
		this.groupInheritType = groupInheritType;
	}
	public String getUserInheritType() {
		return userInheritType;
	}
	public void setUserInheritType(String userInheritType) {
		this.userInheritType = userInheritType;
	}
	public String getProxyInheritType() {
		return proxyInheritType;
	}
	public void setProxyInheritType(String proxyInheritType) {
		this.proxyInheritType = proxyInheritType;
	}
}