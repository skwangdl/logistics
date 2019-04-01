package jp.co.fourseeds.fsnet.beans.page;

import jp.co.common.frame.beans.BaseBean;

/**
 * コンテンツ公開するグループ情報Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2015/12/03 新規作成
 *
 **/
public class AuthGroupBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	// ページID
	private String pageId;
	// トップグループID
	private String topGroupId;
	// 必須閲覧区分
	private String necessityReadFlag;
	// トップグループ名称
	private String topGroupName;
	// グループ削除区分
	private String groupDeleteFlag;
	// グループ種類「0:汎用 1:個人」
	private String topGroupType;
	//　個人グループ作成ユーザID
	private String personalCreateUserid;
	// ※※※※※※※※※※※※※※※※※※※以下はテンプレート用開始
	// テンプレートページID
	private String templatePageId;
	// テンプレート作成者ユーザID
	private String templateCreateBy;
	
	// ※※※※※※※※※※※※※※※※※※※以下はテンプレート用終了
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getTopGroupId() {
		return topGroupId;
	}
	public void setTopGroupId(String topGroupId) {
		this.topGroupId = topGroupId;
	}
	public String getNecessityReadFlag() {
		return necessityReadFlag;
	}
	public void setNecessityReadFlag(String necessityReadFlag) {
		this.necessityReadFlag = necessityReadFlag;
	}
	public String getTopGroupName() {
		return topGroupName;
	}
	public void setTopGroupName(String topGroupName) {
		this.topGroupName = topGroupName;
	}
	public String getGroupDeleteFlag() {
		return groupDeleteFlag;
	}
	public void setGroupDeleteFlag(String groupDeleteFlag) {
		this.groupDeleteFlag = groupDeleteFlag;
	}
	public String getPersonalCreateUserid() {
		return personalCreateUserid;
	}
	public void setPersonalCreateUserid(String personalCreateUserid) {
		this.personalCreateUserid = personalCreateUserid;
	}
	public String getTopGroupType() {
		return topGroupType;
	}
	public void setTopGroupType(String topGroupType) {
		this.topGroupType = topGroupType;
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