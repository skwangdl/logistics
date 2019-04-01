package jp.co.fourseeds.fsnet.beans.page;

import java.util.List;

/**
 * コンテンツ削除情報Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2015/11/25 新規作成
 *
 **/
@SuppressWarnings("rawtypes")
public class PageDeleteBean extends PageBean {

	private static final long serialVersionUID = 1L;
	// コンテンツ配置先、例えば[HOME]-[案内/通達]-[コンテンツ配置先表示] 
	private String pageLocation;
	private List<String> toAddressList;
	private List<String> toAddressIdList;
	private List<String> toNameList;
	private List<String> proxyUserList;
	private List<String> ccNameList;
	private List<String> ccAddressList;
	private List<String> bccAddressList;
	private List<String> bccNameList;
	private String dateFlag;
	private List<PageLinkBean> linkContentsList;
	private List<List<PageLinkBean>> linkedList;
	// リクエストのContextPathを保持
	private String contextPath;
	public String getPageLocation() {
		return pageLocation;
	}
	public void setPageLocation(String pageLocation) {
		this.pageLocation = pageLocation;
	}
	public List<String> getToAddressList() {
		return toAddressList;
	}
	public void setToAddressList(List<String> toAddressList) {
		this.toAddressList = toAddressList;
	}
	public List<String> getToNameList() {
		return toNameList;
	}
	public void setToNameList(List<String> toNameList) {
		this.toNameList = toNameList;
	}
	public List<String> getProxyUserList() {
		return proxyUserList;
	}
	public void setProxyUserList(List<String> proxyUserList) {
		this.proxyUserList = proxyUserList;
	}
	public List<String> getCcNameList() {
		return ccNameList;
	}
	public void setCcNameList(List<String> ccNameList) {
		this.ccNameList = ccNameList;
	}
	public List<String> getCcAddressList() {
		return ccAddressList;
	}
	public void setCcAddressList(List<String> ccAddressList) {
		this.ccAddressList = ccAddressList;
	}
	public List<String> getBccAddressList() {
		return bccAddressList;
	}
	public void setBccAddressList(List<String> bccAddressList) {
		this.bccAddressList = bccAddressList;
	}
	public List<String> getBccNameList() {
		return bccNameList;
	}
	public void setBccNameList(List<String> bccNameList) {
		this.bccNameList = bccNameList;
	}
	public void setLinkContentsList(List<PageLinkBean> linkContentsList) {
		this.linkContentsList = linkContentsList;
	}
	public String getDateFlag() {
		return dateFlag;
	}
	public void setDateFlag(String dateFlag) {
		this.dateFlag = dateFlag;
	}
	public List getLinkContentsList() {
		return linkContentsList;
	}
	public List<List<PageLinkBean>> getLinkedList() {
		return linkedList;
	}
	public void setLinkedList(List<List<PageLinkBean>> linkedList) {
		this.linkedList = linkedList;
	}
	public String getContextPath() {
		return contextPath;
	}
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	public List<String> getToAddressIdList() {
		return toAddressIdList;
	}
	public void setToAddressIdList(List<String> toAddressIdList) {
		this.toAddressIdList = toAddressIdList;
	}
	
}