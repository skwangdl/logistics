package jp.co.fourseeds.fsnet.beans;

import java.util.List;
import jp.co.common.frame.beans.BaseBean;

public class HotPageFormBean extends BaseBean {
	
	/** The Field serialVersionUID */
	private static final long serialVersionUID = 2479865448514900978L;
	
	/**
	 * ページID
	 * */
	private String pageId;
	
	/**
	 * タイトル名
	 * */
	private String title;
	
	/**
	 * SUBペッジID
	 * */
	private String hotPageId;
	
	/**
	 * SUBペッジ名
	 * */
	private String hotPageTitle;
	
	/**
	 * 配列順
	 * */
	private String orderBy;
	
	/**
	 * 公開開始日付
	 * */
	private String startDate;
	
	/**
	 * 公開終了日付
	 * */
	private String endDate;
	
	/**
	 * ページID（ログ用）
	 * */
	private String nodeId;
	
	/**
	 * ファイルURL(ファイル名とアドレス)
	 * */
	private String htmlFileUrl;
	
	/**
	 * SUBお気に入りリスト
	 * */
	private List<HotPageFormBean> hotPageList;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the pageId
	 */
	public String getPageId() {
		return pageId;
	}

	/**
	 * @param pageId the pageId to set
	 */
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	/**
	 * @return the hotPageId
	 */
	public String getHotPageId() {
		return hotPageId;
	}

	/**
	 * @param hotPageId the hotPageId to set
	 */
	public void setHotPageId(String hotPageId) {
		this.hotPageId = hotPageId;
	}

	/**
	 * @return the hotPageName
	 */
	public String getHotPageTitle() {
		return hotPageTitle;
	}

	/**
	 * @param hotPageName the hotPageName to set
	 */
	public void setHotPageTitle(String hotPageName) {
		this.hotPageTitle = hotPageName;
	}

	/**
	 * @return the hotPageList
	 */
	public List<HotPageFormBean> getHotPageList() {
		return hotPageList;
	}

	/**
	 * @param hotPageList the hotPageList to set
	 */
	public void setHotPageList(List<HotPageFormBean> hotPageList) {
		this.hotPageList = hotPageList;
	}

	/**
	 * @return the orderBy
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * @param orderBy the orderBy to set
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the nodeId
	 */
	public String getNodeId() {
		return nodeId;
	}

	/**
	 * @param nodeId the nodeId to set
	 */
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	/**
	 * @return the htmlFileUrl
	 */
	public String getHtmlFileUrl() {
		return htmlFileUrl;
	}

	/**
	 * @param htmlFileUrl the htmlFileUrl to set
	 */
	public void setHtmlFileUrl(String htmlFileUrl) {
		this.htmlFileUrl = htmlFileUrl;
	}

}
