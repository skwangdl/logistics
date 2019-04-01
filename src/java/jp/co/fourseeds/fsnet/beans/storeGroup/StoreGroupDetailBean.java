/**
 * 店舗グループ詳細Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2016.01.08 新規作成
 * 
 * @author NTS
 * @version 1.1.0 : 2017.12.05 見直し修正
 */

package jp.co.fourseeds.fsnet.beans.storeGroup;

import jp.co.common.frame.beans.BaseBean;

public class StoreGroupDetailBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/** 店舗グループID */
	private String storeGroupId;
	
	/** 反映予定日時 */
	private String sgRefDate;
	
	/** 店舗ID */
	private String storeId;
	
	/** 店舗名称 */
	private String storeName;
	
	/** 共有対象者ユーザID */
	private String shareTargetUserId;
	
	/** 共有対象者ユーザ名 */
	private String shareTargetUserName;
	
	/** 社員区分ID */
	private String conditionPemployeeId;

	/** 社員区分Name */
	private String conditionPemployeeName;
	
	/** 編集可能フラグ */
	private String authEditFlag;
	
	/** 条件フラグ */
	private String inputFlag;
	
	/** VALUE値 */
	private String value;
	
	/** 個人用_汎用 */
	private String grouptype;
	
	/** 店舗区分FLAG*/
	private String conditionFcFlag;

	public String getStoreGroupId() {
		return storeGroupId;
	}

	public void setStoreGroupId(String storeGroupId) {
		this.storeGroupId = storeGroupId;
	}

	public String getSgRefDate() {
		return sgRefDate;
	}

	public void setSgRefDate(String sgRefDate) {
		this.sgRefDate = sgRefDate;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getShareTargetUserId() {
		return shareTargetUserId;
	}

	public void setShareTargetUserId(String shareTargetUserId) {
		this.shareTargetUserId = shareTargetUserId;
	}

	public String getAuthEditFlag() {
		return authEditFlag;
	}

	public void setAuthEditFlag(String authEditFlag) {
		this.authEditFlag = authEditFlag;
	}

	public String getShareTargetUserName() {
		return shareTargetUserName;
	}

	public void setShareTargetUserName(String shareTargetUserName) {
		this.shareTargetUserName = shareTargetUserName;
	}

	public String getInputFlag() {
		return inputFlag;
	}

	public void setInputFlag(String inputFlag) {
		this.inputFlag = inputFlag;
	}


	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getGrouptype() {
		return grouptype;
	}

	public void setGrouptype(String grouptype) {
		this.grouptype = grouptype;
	}

	public String getConditionPemployeeId() {
		return conditionPemployeeId;
	}

	public void setConditionPemployeeId(String conditionPemployeeId) {
		this.conditionPemployeeId = conditionPemployeeId;
	}

	public String getConditionPemployeeName() {
		return conditionPemployeeName;
	}

	public void setConditionPemployeeName(String conditionPemployeeName) {
		this.conditionPemployeeName = conditionPemployeeName;
	}

	public String getConditionFcFlag() {
		return conditionFcFlag;
	}

	public void setConditionFcFlag(String conditionFcFlag) {
		this.conditionFcFlag = conditionFcFlag;
	}
	
}