package jp.co.fourseeds.fsnet.beans;

import jp.co.common.frame.beans.BaseBean;

/**
 *  ログインユーザー情報クラス
 * <pre>
 *  共通フィールドのセット・取得メソッドを提供する。
 * </pre>
 * <ul>
 *   <li></li>
 * </ul>
 * @author NTS
 * @version 1.0 
 */
public class MenuTreeBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	// ノードID（PAGE_ID）
	private String nodeId = "";
	// ノード名（TITLE）
	private String nodeName = "";
	// 親ノードID（P_PAGE_ID）
	private String parentNodeId = "";
	// 表示フラグ
	private String displayFlag = "";
	// リンクフラグ
	private String linkFlag = "";
	// ファイルURL(ファイル名とアドレス)
	private String url = "";
	// 表示順
	private String orderBy = "";
	// 当コンテンツは予約　OR　真実を標識
	private String isReserve;
	
	private String childrenCount;
	
	private String cnt;
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getParentNodeId() {
		return parentNodeId;
	}
	public void setParentNodeId(String parentNodeId) {
		this.parentNodeId = parentNodeId;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getIsReserve() {
		return isReserve;
	}
	public void setIsReserve(String isReserve) {
		this.isReserve = isReserve;
	}
	public String getChildrenCount() {
		return childrenCount;
	}
	public void setChildrenCount(String childrenCount) {
		this.childrenCount = childrenCount;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
}