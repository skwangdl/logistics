package jp.co.common.frame.beans;

import java.util.Date;

/**
 *  DB EntityBeanオブジェクトの基底クラス
 * <pre>
 *  共通フィールドのセット・取得メソッドを提供する。
 * </pre>
 * <ul>
 *   <li>共通プロパティを取得</li>
 *   <li>共通プロパティを設定</li>
 * </ul> 
 * @author NTS
 * @version 1.0 
 */
public class BaseBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String createBy;
	
	private Date createDate;
	
	private String updateBy;
	
	private Date updateDate;
	
	private String deleteFlag;
	// 更新ユーザ名称（排他制御用）
	private String updateUserName;
	// 更新日時（排他制御用）
	private String updateDateStr;

	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getUpdateDateStr() {
		return updateDateStr;
	}
	public void setUpdateDateStr(String updateDateStr) {
		this.updateDateStr = updateDateStr;
	}
	public String getUpdateUserName() {
		return updateUserName;
	}
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
}