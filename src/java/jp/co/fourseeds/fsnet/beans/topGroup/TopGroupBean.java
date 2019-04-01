/**
 * トップグループBean
 * 
 * @author NTS
 * @version 1.0.0 : 2016.01.19 新規作成
 */

package jp.co.fourseeds.fsnet.beans.topGroup;

import java.util.List;

import jp.co.common.frame.beans.BaseBean;

public class TopGroupBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/**■■■■■■■■ここから、検索Bean■■■■■■■■■■■■■■■■*/
	/** 検索トップグループid*/
	private String searchTopGroupId;
	
	/** 検索トップグループ名*/
	private String searchTopGroupName;
	
	/** 検索区分*/
	private String searchOriginType;
	
	/** 検索テーブル区分　1:公開　2:予約 */
	private String editFlag;
	
	/** 検索次の層グループid*/
	private String searchSubGroupId;
	
	/** 検索次の層グループ区分*/
	private String searchSubGroupFlag;
	
	/** 検索次の層グループ行*/
	private String searchSubGroupRowNum;
	
	/** 検索次の層id*/
	private String searchSubId;
	
	/** 検索店舗グループのアルバイト含むフラグ*/
	private String searchPartTimeIncludeFlag;
	
	/** 初期検索 */
	private String isFirstSearch;
	
	/** ページ毎に表示件数情報格納 */
	private int searchRowNum;
	
	/**■■■■■■■■ここから、検索結果Bean■■■■■■■■■■■■*/
	/** トップグループID */
	private String topGroupId;
	
	/** トップグループ名 */
	private String topGroupName;
	
	/** トップグループ紹介 */
	private String topGroupIntro;
	
	/**反映予定日時*/
	private String lgRefDate;
	
	/** 個人グループ作成ユーザID */
	private String personalCreateUserId;
	
	/** データ種類　1:公開　2:予約　12:公開&予約 */
	private String originType;
	
	/**部署グループリスト*/
	private List<TopGroupDetailBean> deptGroupList;
	
	/**部署グループID*/
	private String deptGroupId;
	
	/**部署グループ名*/
	private String deptGroupName;
	
	/**店舗グループリスト*/
	private List<TopGroupDetailBean> storeGroupList;
	
	/**店舗グループID*/
	private String storeGroupId;
	
	/**店舗グループ名*/
	private String storeGroupName;
	
	/**ユーザグループリスト*/
	private List<TopGroupDetailBean> userGroupList;
	
	/**ユーザグループID*/
	private String userGroupId;
	
	/**ユーザグループ名*/
	private String userGroupName;
	
	/** 社員区分Id*/
	private String conditionPemployeeId;
	
	/** 検索店舗名*/
	private String searchStoreGroupName;
	
	/** 検索部署名*/
	private String searchDeptGroupName;
	
	/** 検索ユーザ?名*/
	private String searchUserGroupName;
	
	/** LEVEL*/
	private int level;
	
	/** 社員区分*/
	private String searchConditionPemployeeId;
	
	/** 店舗区分*/
	private String searchStoredivision;

	public String getSearchTopGroupId() {
		return searchTopGroupId;
	}

	public void setSearchTopGroupId(String searchTopGroupId) {
		this.searchTopGroupId = searchTopGroupId;
	}

	public String getSearchTopGroupName() {
		return searchTopGroupName;
	}

	public void setSearchTopGroupName(String searchTopGroupName) {
		this.searchTopGroupName = searchTopGroupName;
	}

	public String getSearchOriginType() {
		return searchOriginType;
	}

	public void setSearchOriginType(String searchOriginType) {
		this.searchOriginType = searchOriginType;
	}

	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}

	public String getIsFirstSearch() {
		return isFirstSearch;
	}

	public void setIsFirstSearch(String isFirstSearch) {
		this.isFirstSearch = isFirstSearch;
	}

	public int getSearchRowNum() {
		return searchRowNum;
	}

	public void setSearchRowNum(int searchRowNum) {
		this.searchRowNum = searchRowNum;
	}

	public String getTopGroupId() {
		return topGroupId;
	}

	public void setTopGroupId(String topGroupId) {
		this.topGroupId = topGroupId;
	}

	public String getTopGroupName() {
		return topGroupName;
	}

	public void setTopGroupName(String topGroupName) {
		this.topGroupName = topGroupName;
	}

	public String getTopGroupIntro() {
		return topGroupIntro;
	}

	public void setTopGroupIntro(String topGroupIntro) {
		this.topGroupIntro = topGroupIntro;
	}

	public List<TopGroupDetailBean> getDeptGroupList() {
		return deptGroupList;
	}

	public void setDeptGroupList(List<TopGroupDetailBean> deptGroupList) {
		this.deptGroupList = deptGroupList;
	}

	public String getDeptGroupId() {
		return deptGroupId;
	}

	public void setDeptGroupId(String deptGroupId) {
		this.deptGroupId = deptGroupId;
	}

	public String getDeptGroupName() {
		return deptGroupName;
	}

	public void setDeptGroupName(String deptGroupName) {
		this.deptGroupName = deptGroupName;
	}

	public List<TopGroupDetailBean> getStoreGroupList() {
		return storeGroupList;
	}

	public void setStoreGroupList(List<TopGroupDetailBean> storeGroupList) {
		this.storeGroupList = storeGroupList;
	}

	public String getStoreGroupId() {
		return storeGroupId;
	}

	public void setStoreGroupId(String storeGroupId) {
		this.storeGroupId = storeGroupId;
	}

	public String getStoreGroupName() {
		return storeGroupName;
	}

	public void setStoreGroupName(String storeGroupName) {
		this.storeGroupName = storeGroupName;
	}

	public List<TopGroupDetailBean> getUserGroupList() {
		return userGroupList;
	}

	public void setUserGroupList(List<TopGroupDetailBean> userGroupList) {
		this.userGroupList = userGroupList;
	}

	public String getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}

	public String getUserGroupName() {
		return userGroupName;
	}

	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}

	public String getLgRefDate() {
		return lgRefDate;
	}

	public void setLgRefDate(String lgRefDate) {
		this.lgRefDate = lgRefDate;
	}

	public String getSearchSubGroupId() {
		return searchSubGroupId;
	}

	public void setSearchSubGroupId(String searchSubGroupId) {
		this.searchSubGroupId = searchSubGroupId;
	}

	public String getSearchSubGroupFlag() {
		return searchSubGroupFlag;
	}

	public void setSearchSubGroupFlag(String searchSubGroupFlag) {
		this.searchSubGroupFlag = searchSubGroupFlag;
	}

	public String getSearchSubId() {
		return searchSubId;
	}

	public void setSearchSubId(String searchSubId) {
		this.searchSubId = searchSubId;
	}

	public String getOriginType() {
		return originType;
	}

	public void setOriginType(String originType) {
		this.originType = originType;
	}

	public String getSearchSubGroupRowNum() {
		return searchSubGroupRowNum;
	}

	public void setSearchSubGroupRowNum(String searchSubGroupRowNum) {
		this.searchSubGroupRowNum = searchSubGroupRowNum;
	}

	public String getPersonalCreateUserId() {
		return personalCreateUserId;
	}

	public void setPersonalCreateUserId(String personalCreateUserId) {
		this.personalCreateUserId = personalCreateUserId;
	}

	public String getSearchPartTimeIncludeFlag() {
		return searchPartTimeIncludeFlag;
	}

	public void setSearchPartTimeIncludeFlag(String searchPartTimeIncludeFlag) {
		this.searchPartTimeIncludeFlag = searchPartTimeIncludeFlag;
	}

	public String getConditionPemployeeId() {
		return conditionPemployeeId;
	}

	public void setConditionPemployeeId(String conditionPemployeeId) {
		this.conditionPemployeeId = conditionPemployeeId;
	}

	public String getSearchStoreGroupName() {
		return searchStoreGroupName;
	}

	public void setSearchStoreGroupName(String searchStoreGroupName) {
		this.searchStoreGroupName = searchStoreGroupName;
	}

	public String getSearchDeptGroupName() {
		return searchDeptGroupName;
	}

	public void setSearchDeptGroupName(String searchDeptGroupName) {
		this.searchDeptGroupName = searchDeptGroupName;
	}

	public String getSearchUserGroupName() {
		return searchUserGroupName;
	}

	public void setSearchUserGroupName(String searchUserGroupName) {
		this.searchUserGroupName = searchUserGroupName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getSearchConditionPemployeeId() {
		return searchConditionPemployeeId;
	}

	public void setSearchConditionPemployeeId(String searchConditionPemployeeId) {
		this.searchConditionPemployeeId = searchConditionPemployeeId;
	}

	public String getSearchStoredivision() {
		return searchStoredivision;
	}

	public void setSearchStoredivision(String searchStoredivision) {
		this.searchStoredivision = searchStoredivision;
	}
	
}