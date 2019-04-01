/**
 * トップグループ詳細Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2016.01.19 新規作成
 */

package jp.co.fourseeds.fsnet.beans.topGroup;

import jp.co.common.frame.beans.BaseBean;

public class TopGroupDetailBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/** トップグループID */
	private String topGroupId;
	
	/** 反映予定日時 */
	private String lgRefDate;
	
	/** サブグループＩＤ */
	private String subGroupId;
	
	/** サブグループ名称 */
	private String subGroupName;
	
	/** サブグループ区分 */
	private String subGroupFlag;

	public String getTopGroupId() {
		return topGroupId;
	}

	public void setTopGroupId(String topGroupId) {
		this.topGroupId = topGroupId;
	}

	public String getLgRefDate() {
		return lgRefDate;
	}

	public void setLgRefDate(String lgRefDate) {
		this.lgRefDate = lgRefDate;
	}

	public String getSubGroupId() {
		return subGroupId;
	}

	public void setSubGroupId(String subGroupId) {
		this.subGroupId = subGroupId;
	}

	public String getSubGroupName() {
		return subGroupName;
	}

	public void setSubGroupName(String subGroupName) {
		this.subGroupName = subGroupName;
	}

	public String getSubGroupFlag() {
		return subGroupFlag;
	}

	public void setSubGroupFlag(String subGroupFlag) {
		this.subGroupFlag = subGroupFlag;
	}
}