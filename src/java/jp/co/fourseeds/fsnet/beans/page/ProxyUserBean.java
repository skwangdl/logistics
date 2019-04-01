package jp.co.fourseeds.fsnet.beans.page;

import jp.co.common.frame.beans.BaseBean;

/**
 * コンテンツ承認者情報Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2015/12/03 新規作成
 *
 **/
public class ProxyUserBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	// SEQUENCE
	private String sequence;
	// ページID
	private String pageId;
	// 更新代行ユーザID
	private String proxyUserId;
	// 更新代行ユーザ名称
	private String proxyUserName;
	// 更新代行ユーザメールフラグ
	private String proxyUserMailFlag;
	// 削除区分
	private String proxyDeleteFlag;
	
	// ※※※※※※※※※※※※※※※※※※※以下はテンプレート用開始
	// テンプレートページID
	private String templatePageId;
	// テンプレート作成者ユーザID
	private String templateCreateBy;
	
	// ※※※※※※※※※※※※※※※※※※※以下はテンプレート用終了
	
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getProxyUserId() {
		return proxyUserId;
	}
	public void setProxyUserId(String proxyUserId) {
		this.proxyUserId = proxyUserId;
	}
	public String getProxyUserName() {
		return proxyUserName;
	}
	public void setProxyUserName(String proxyUserName) {
		this.proxyUserName = proxyUserName;
	}
	public String getProxyDeleteFlag() {
		return proxyDeleteFlag;
	}
	public void setProxyDeleteFlag(String proxyDeleteFlag) {
		this.proxyDeleteFlag = proxyDeleteFlag;
	}
	public String getProxyUserMailFlag() {
		return proxyUserMailFlag;
	}
	public void setProxyUserMailFlag(String proxyUserMailFlag) {
		this.proxyUserMailFlag = proxyUserMailFlag;
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
