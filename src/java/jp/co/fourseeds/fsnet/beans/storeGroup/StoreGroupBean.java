/**
 * 店舗グループBean
 * 
 * @author NTS
 * @version 1.0.0 : 2016.01.08 新規作成
 * 
 * @author NTS
 * @version 1.1.0 : 2017.12.05 見直し修正
 */

package jp.co.fourseeds.fsnet.beans.storeGroup;

import java.util.List;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.beans.LabelValueBean;

public class StoreGroupBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/**■■■■■■■■ここから、検索Bean■■■■■■■■■■■■■■■■*/
	/** 検索店舗グループid*/
	private String searchStoreGroupId;
	
	/** 検索店舗グループ名*/
	private String searchStoreGroupName;
	
	/** 検索区分*/
	private String searchOriginType;
	
	/** 検索テーブル区分　1:公開　2:予約 */
	private String editFlag;
	
	/** 検索ユーザ名*/
	private String searchUserName;
	
	/** 検索部署Id*/
	private String searchDepartmentId;
	
	/** 検索店舗名*/
	private String searchStoreName;
	
	/** 検索店舗Id*/
	private String searchStoreId;
	
	/** 初期検索 */
	private String isFirstSearch;
	
	/** ページ毎に表示件数情報格納 */
	private int searchRowNum;
	
	/**■■■■■■■■ここから、検索結果Bean■■■■■■■■■■■■*/
	/** 店舗グループID */
	private String storeGroupId;
	
	/** 店舗グループ名 */
	private String storeGroupName;
	
	/** 店舗グループ紹介 */
	private String storeGroupIntro;
	
	/**反映予定日時*/
	private String sgRefDate;
	
	/** 店舗Id From */
	private String conditionStoreIdF;
	
	/** 店舗Id To */
	private String conditionStoreIdT;
	
	/** データ種類　1:公開　2:予約　12:公開&予約 */
	private String originType;
	
	/** 共有ユーザリスト */
	private List<StoreGroupDetailBean> shareUserList;
	
	/** 店舗リスト */
	private List<StoreGroupDetailBean> storeList;
	
	/** 店舗リスト */
	private List<StoreGroupDetailBean> storeDetailList;
	
	/** 店舗ID */
	private String storeId;
	
	/** 店舗名称 */
	private String storeName;
	
	/** 編集画面の会社リスト*/
	private List<LabelValueBean> shareCompanyList;
	
	/** 編集画面の統括リスト*/
	private List<LabelValueBean> shareUnificationList;
	
	/** 編集画面の事業部リスト*/
	private List<LabelValueBean> shareBusinessList;
	
	/** 編集画面の営業部リスト*/
	private List<LabelValueBean> shareSalesList;
	
	/** 編集画面の会社リスト*/
	private List<LabelValueBean> companyList;
	
	/** 編集画面の統括リスト*/
	private List<LabelValueBean> unificationList;
	
	/** 編集画面の事業部リスト*/
	private List<LabelValueBean> businessList;
	
	/** 編集画面の営業部リスト*/
	private List<LabelValueBean> salesList;
	
	/** 編集画面の店舗区分リスト*/
	private List<LabelValueBean> storeDivisionList;
	
	/** 個人用_汎用FLAG */
	private String grouptype;
	
	/** 店舗区分Id*/
	private String shareConditionStoreDivisionId;
	
	/** 店舗区分*/
	private String conditionStoreDivisionName;
	
	/** 店舗区分FLAG*/
	private String conditionFcFlag;
	
	/** 店舗区分FLAG:店舗リスト検索条件*/
	private String conditionFcFlag2;
	
	/** 共有ユーザ名 */
	private String shareUserName;
	
	/** 共有会社Id*/
	private String shareConditionCompanyId;
	
	/** 共有統括Id*/
	private String shareConditionUnificationId;
	
	/** 共有事業部Id*/
	private String shareConditionBusinessId;
	
	/** 共有営業部Id*/
	private String shareConditionSalesId;
	
	/** 会社Id*/
	private String conditionCompanyId;
	
	/** 統括Id*/
	private String conditionUnificationId;
	
	/** 事業部Id*/
	private String conditionBusinessId;
	
	/** 営業部Id*/
	private String conditionSalesId;
	
	/** 編集可能フラグ */
	private String authEditFlag;
	
	/** 作成者名 */
	private String createUserName;
	
	/** 詳細画面の中で、編集と削除ボタン表示権限 */
	private String btnDisplayRight;
	
	/**気分flag**/
	private String groupFlag;
	
	/** 編集画面の社員区分リスト*/
	private List<LabelValueBean> pemployeeList;
	
	/** 社員区分Id*/
	private String conditionPemployeeId;

	/** 社員区分Name*/
	private String conditionPemployeeName;
	
	/** LEVEL */
	private int level;

	public String getSearchStoreGroupId() {
		return searchStoreGroupId;
	}

	public void setSearchStoreGroupId(String searchStoreGroupId) {
		this.searchStoreGroupId = searchStoreGroupId;
	}

	public String getSearchStoreGroupName() {
		return searchStoreGroupName;
	}

	public void setSearchStoreGroupName(String searchStoreGroupName) {
		this.searchStoreGroupName = searchStoreGroupName;
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

	public String getSearchUserName() {
		return searchUserName;
	}

	public void setSearchUserName(String searchUserName) {
		this.searchUserName = searchUserName;
	}

	public String getSearchDepartmentId() {
		return searchDepartmentId;
	}

	public void setSearchDepartmentId(String searchDepartmentId) {
		this.searchDepartmentId = searchDepartmentId;
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

	public String getStoreGroupIntro() {
		return storeGroupIntro;
	}

	public void setStoreGroupIntro(String storeGroupIntro) {
		this.storeGroupIntro = storeGroupIntro;
	}

	public String getSgRefDate() {
		return sgRefDate;
	}

	public void setSgRefDate(String sgRefDate) {
		this.sgRefDate = sgRefDate;
	}

	public String getOriginType() {
		return originType;
	}

	public void setOriginType(String originType) {
		this.originType = originType;
	}

	public List<StoreGroupDetailBean> getShareUserList() {
		return shareUserList;
	}

	public void setShareUserList(List<StoreGroupDetailBean> shareUserList) {
		this.shareUserList = shareUserList;
	}

	public List<StoreGroupDetailBean> getStoreList() {
		return storeList;
	}

	public void setStoreList(List<StoreGroupDetailBean> storeList) {
		this.storeList = storeList;
	}

	public List<StoreGroupDetailBean> getStoreDetailList() {
		return storeDetailList;
	}

	public void setStoreDetailList(List<StoreGroupDetailBean> storeDetailList) {
		this.storeDetailList = storeDetailList;
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

	public List<LabelValueBean> getShareCompanyList() {
		return shareCompanyList;
	}

	public void setShareCompanyList(List<LabelValueBean> shareCompanyList) {
		this.shareCompanyList = shareCompanyList;
	}

	public List<LabelValueBean> getShareUnificationList() {
		return shareUnificationList;
	}

	public void setShareUnificationList(List<LabelValueBean> shareUnificationList) {
		this.shareUnificationList = shareUnificationList;
	}

	public List<LabelValueBean> getShareBusinessList() {
		return shareBusinessList;
	}

	public void setShareBusinessList(List<LabelValueBean> shareBusinessList) {
		this.shareBusinessList = shareBusinessList;
	}

	public List<LabelValueBean> getShareSalesList() {
		return shareSalesList;
	}

	public void setShareSalesList(List<LabelValueBean> shareSalesList) {
		this.shareSalesList = shareSalesList;
	}

	public List<LabelValueBean> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<LabelValueBean> companyList) {
		this.companyList = companyList;
	}

	public List<LabelValueBean> getUnificationList() {
		return unificationList;
	}

	public void setUnificationList(List<LabelValueBean> unificationList) {
		this.unificationList = unificationList;
	}

	public List<LabelValueBean> getBusinessList() {
		return businessList;
	}

	public void setBusinessList(List<LabelValueBean> businessList) {
		this.businessList = businessList;
	}

	public List<LabelValueBean> getSalesList() {
		return salesList;
	}

	public void setSalesList(List<LabelValueBean> salesList) {
		this.salesList = salesList;
	}

	public List<LabelValueBean> getStoreDivisionList() {
		return storeDivisionList;
	}

	public void setStoreDivisionList(List<LabelValueBean> storeDivisionList) {
		this.storeDivisionList = storeDivisionList;
	}

	public String getGrouptype() {
		return grouptype;
	}

	public void setGrouptype(String grouptype) {
		this.grouptype = grouptype;
	}

	public String getShareConditionStoreDivisionId() {
		return shareConditionStoreDivisionId;
	}

	public void setShareConditionStoreDivisionId(String shareConditionStoreDivisionId) {
		this.shareConditionStoreDivisionId = shareConditionStoreDivisionId;
	}

	public String getConditionStoreDivisionName() {
		return conditionStoreDivisionName;
	}

	public void setConditionStoreDivisionName(String conditionStoreDivisionName) {
		this.conditionStoreDivisionName = conditionStoreDivisionName;
	}

	public String getAuthEditFlag() {
		return authEditFlag;
	}

	public void setAuthEditFlag(String authEditFlag) {
		this.authEditFlag = authEditFlag;
	}

	public String getSearchStoreName() {
		return searchStoreName;
	}

	public void setSearchStoreName(String searchStoreName) {
		this.searchStoreName = searchStoreName;
	}

	public String getSearchStoreId() {
		return searchStoreId;
	}

	public void setSearchStoreId(String searchStoreId) {
		this.searchStoreId = searchStoreId;
	}

	public String getConditionFcFlag() {
		return conditionFcFlag;
	}

	public void setConditionFcFlag(String conditionFcFlag) {
		this.conditionFcFlag = conditionFcFlag;
	}

	public String getConditionFcFlag2() {
		return conditionFcFlag2;
	}

	public void setConditionFcFlag2(String conditionFcFlag2) {
		this.conditionFcFlag2 = conditionFcFlag2;
	}

	public String getConditionStoreIdF() {
		return conditionStoreIdF;
	}

	public void setConditionStoreIdF(String conditionStoreIdF) {
		this.conditionStoreIdF = conditionStoreIdF;
	}

	public String getConditionStoreIdT() {
		return conditionStoreIdT;
	}

	public void setConditionStoreIdT(String conditionStoreIdT) {
		this.conditionStoreIdT = conditionStoreIdT;
	}

	public String getShareUserName() {
		return shareUserName;
	}

	public void setShareUserName(String shareUserName) {
		this.shareUserName = shareUserName;
	}

	public String getShareConditionCompanyId() {
		return shareConditionCompanyId;
	}

	public void setShareConditionCompanyId(String shareConditionCompanyId) {
		this.shareConditionCompanyId = shareConditionCompanyId;
	}

	public String getShareConditionUnificationId() {
		return shareConditionUnificationId;
	}

	public void setShareConditionUnificationId(String shareConditionUnificationId) {
		this.shareConditionUnificationId = shareConditionUnificationId;
	}

	public String getShareConditionBusinessId() {
		return shareConditionBusinessId;
	}

	public void setShareConditionBusinessId(String shareConditionBusinessId) {
		this.shareConditionBusinessId = shareConditionBusinessId;
	}

	public String getShareConditionSalesId() {
		return shareConditionSalesId;
	}

	public void setShareConditionSalesId(String shareConditionSalesId) {
		this.shareConditionSalesId = shareConditionSalesId;
	}

	public String getConditionCompanyId() {
		return conditionCompanyId;
	}

	public void setConditionCompanyId(String conditionCompanyId) {
		this.conditionCompanyId = conditionCompanyId;
	}

	public String getConditionUnificationId() {
		return conditionUnificationId;
	}

	public void setConditionUnificationId(String conditionUnificationId) {
		this.conditionUnificationId = conditionUnificationId;
	}

	public String getConditionBusinessId() {
		return conditionBusinessId;
	}

	public void setConditionBusinessId(String conditionBusinessId) {
		this.conditionBusinessId = conditionBusinessId;
	}

	public String getConditionSalesId() {
		return conditionSalesId;
	}

	public void setConditionSalesId(String conditionSalesId) {
		this.conditionSalesId = conditionSalesId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getBtnDisplayRight() {
		return btnDisplayRight;
	}

	public void setBtnDisplayRight(String btnDisplayRight) {
		this.btnDisplayRight = btnDisplayRight;
	}
	
	public String getGroupFlag() {
		return groupFlag;
	}

	public void setGroupFlag(String groupFlag) {
		this.groupFlag = groupFlag;
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

	public List<LabelValueBean> getPemployeeList() {
		return pemployeeList;
	}

	public void setPemployeeList(List<LabelValueBean> pemployeeList) {
		this.pemployeeList = pemployeeList;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
}