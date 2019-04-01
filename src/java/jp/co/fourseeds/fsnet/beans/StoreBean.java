package jp.co.fourseeds.fsnet.beans;

import java.util.List;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.beans.LabelValueBean;

import jp.co.fourseeds.fsnet.common.util.StringUtil;

/**
 * 店舗情報Bean
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2016/01/21		    NTS        	       作成
 * 1.1		2017/09/06			NTS			      見直し修正
 **/
public class StoreBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/**■■■■■■■■ここから、検索Bean■■■■■■■■■■■■■■■■*/
	/** 検索店舗Id*/
	private String searchStoreId;
	
	/** 検索店舗名*/
	private String searchStoreName;
	
	/** 初期検索 */
	private String isFirstSearch;
	
	/** ページ毎に表示件数情報格納 */
	private int searchRowNum;
	
	/**■■■■■■■■ここから、検索結果Bean■■■■■■■■■■■■*/
	/** 店舗ID */
	private String StoreId;
	
	/** 店舗名 */
	private String StoreName;
	
	/** 店舗メールアドレス */
	private String storeMail;
	
	/** 店舗属性コード */
	private String fcFlag;
	
	/** 店舗属性名称 */
	private String storeAttrName;
	
	/** 有効開始日 */
	private String storeSet;
	
	/** 有効終了日 */
	private String storeEx;
	
	/** 電話番号１ */
	private String telNumber;
	
	/** 電話番号その他 */
	private String otherTelNumber;
	
	/** FAX1番号 */
	private String faxNumber1;
	
	/** FAX2番号 */
	private String faxNumber2;	
	
	/** SV名 */
	private String supervisorName;
	
	/** 統括名称 */
	private String unificationName;
	
	/** 事業名称 */
	private String businessName;
	
	/** 営業部名称 */
	private String salesName;
	
	/** 都道府県Id*/
	private String areaId;
	
	/** 都道府県名*/
	private String areaNm;

	/** 名漢字 */
	private String kanziMei;
	
	/** 名カナ */
	private String kanaMei;
	
	/** 住所名 */
	private String Address;
	
	/** 番地 */
	private String houseNumber;
	
	/** 号 */
	private String houseDetailNumber;
	
	/** 建物名 */
	private String buildName;
	
	/**平日営業開始時刻１*/
	private String weekdayOpen1;
	
	/**平日営業終了時刻１*/
	private String weekdayClose1;
	
	/**平日営業開始時刻２*/
	private String weekdayOpen2;
	
	/**平日営業終了時刻２*/
	private String weekdayClose2;
	
	/**土曜営業開始時刻１*/
	private String saturdayOpen1;
	
	/**土曜営業終了時刻１*/
	private String saturdayClose1;
	
	/**土曜営業開始時刻２*/
	private String saturdayOpen2;
	
	/**土曜営業終了時刻２*/
	private String saturdayClose2;
	
	/**日祝営業開始時刻１*/
	private String holidayOpen1;
	
	/**日祝営業終了時刻１*/
	private String holidayClose1;
	
	/**日祝営業開始時刻２*/
	private String holidayOpen2;
	
	/**日祝営業終了時刻２*/
	private String holidayClose2;
	
	/**季節営業開始期間*/
	private String seasonStartDate;
	
	/**季節営業終了期間*/
	private String seasonEndDate;
	
	/**季節営業開始時刻*/
	private String seasonStartTime;
	
	/**季節営業終了時刻*/
	private String seasonEndTime;
	
	/**オーナーコード*/
	private String owenerCode;
	
	/**オーナー名*/
	private String owenerName;
	
	/**オーナー社名*/
	private String owenerCompanyName;
	
	/**郵便番号*/
	private String zipCode;
	
	/**オーナー表示名*/
	private String dispOwenerName;
	
	/**住所名+番地+号+建物名　ページ表示*/
	private String addressAll;
	
	private String SearchownerCode;
	private String SearchownerName;
	
	/** 業務委託数 */
	private String consignCount;
	
	/** 業務受託数 */
	private String accessionCount;
	
	/** 店舗運営区分 */
	private String otherFlag;
	
	/** 店舗運営区分 */
	private String otherFlagName;
	
	/** 表示フラグ */
	private String showFlag;
	
	/** 表示フラグ名 */
	private String showFlagName;
	
	/** 住所コード */
	private String addressCode; 
	
	/** 部署ID */
	private String departmentId;
	
	/** 表示グループ名称 */
	private String displayGroupName;
	
	/** 表示グループコード*/
	private String displayGroupCd;
	
	/**ＳＶコード*/
	private String supervisorCode;
	
	/**店舗属性リスト*/
	private List<LabelValueBean> storeAttrList;
	
	/**ＳＶ名リスト*/
	private List<LabelValueBean> supervisorNameList;
	
	/**店舗運営区分リスト*/
	private List<LabelValueBean> otherFlagList;

	/** 会社グループリスト */
	private List<LabelValueBean> companyList;
			
	/** 統括グループリスト */
	private List<LabelValueBean> unificationList;
	
	/** 事業グループリスト */
	private List<LabelValueBean> businessList;
	
	/** 営業部グループリスト */
	private List<LabelValueBean> salesList;
	
	/** 表示フラグリスト */
	private List<LabelValueBean> showFlagList;
	
	/** 会社コード */
	private String searchCompanyId;
	
	/** 統括コード */
	private String searchUnificationId;
	
	/** 事業コード */
	private String searchBusinessId;
	
	/** 営業部コード */
	private String searchSalesId;
	
	/** 会社名称 */
	private String companyName;
	
	/**業務委託フラグ*/
	private String consign_flag;
	
	/**業務受託フラグ*/
	private String accession_flag;
	
	/** 業務委託フラグ */
	private String openConsignFlag;
	
	/** 委託有効開始日(公開)*/
	private String openConsignStoreSet;
	
	/** 委託有効終了日(公開)*/
	private String openConsignStoreEx;
	
	/** 委託(公開)オーナーコード  */
	private String openYconrc;
	
	/** 委託(公開)オーナー名 */
	private String openName;
	
	/** 業務委託フラグ */
	private String vailedConsignFlag;
	
	/** 委託有効開始日(予約)*/
	private String vailedConsignStoreSet;
	
	/** 委託有効終了日(予約)*/
	private String vailedConsignStoreEx;
	
	/** 委託(予約)オーナーコード  */
	private String vailedYconrc;
	
	/** 委託(予約)オーナー名 */
	private String vailedName;
	
	/** 業務受託フラグ */
	private String openAccessionFlag;
	
	/** 受託有効開始日(公開)*/
	private String openAccessionStoreSet;
	
	/** 受託有効終了日(公開)*/
	private String openAccessionStoreEx;
	
	/** 業務受託フラグ*/
	private String vailedAccessionFlag;
	
	/** 受託有効開始日(予約)*/
	private String vailedAccessionStoreSet;
	
	/** 受託有効終了日(予約)*/
	private String vailedAccessionStoreEx;
	
	public String getAddressAll() {
		addressAll = StringUtil.nullToBlank(Address) + (!StringUtil.isEmpty(houseNumber) ? "－" + houseNumber : "") +
							(!StringUtil.isEmpty(houseDetailNumber) ? "－"+houseDetailNumber : "") +
							(!StringUtil.isEmpty(buildName) ? "　"+buildName : "");
		return addressAll;
	}

	public void setAddressAll(String addressAll) {
		this.addressAll = addressAll;
	}

	public String getSearchStoreId() {
		return searchStoreId;
	}

	public void setSearchStoreId(String searchStoreId) {
		this.searchStoreId = searchStoreId;
	}

	public String getSearchStoreName() {
		return searchStoreName;
	}

	public void setSearchStoreName(String searchStoreName) {
		this.searchStoreName = searchStoreName;
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

	public String getStoreId() {
		return StoreId;
	}

	public void setStoreId(String storeId) {
		StoreId = storeId;
	}

	public String getStoreName() {
		return StoreName;
	}

	public void setStoreName(String storeName) {
		StoreName = storeName;
	}

	public String getStoreMail() {
		return storeMail;
	}

	public void setStoreMail(String storeMail) {
		this.storeMail = storeMail;
	}

	public String getFcFlag() {
		return fcFlag;
	}

	public void setFcFlag(String fcFlag) {
		this.fcFlag = fcFlag;
	}

	public String getStoreAttrName() {
		return storeAttrName;
	}

	public void setStoreAttrName(String storeAttrName) {
		this.storeAttrName = storeAttrName;
	}

	public String getStoreSet() {
		return storeSet;
	}

	public void setStoreSet(String storeSet) {
		this.storeSet = storeSet;
	}

	public String getStoreEx() {
		return storeEx;
	}

	public void setStoreEx(String storeEx) {
		this.storeEx = storeEx;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String getOtherTelNumber() {
		return otherTelNumber;
	}

	public void setOtherTelNumber(String otherTelNumber) {
		this.otherTelNumber = otherTelNumber;
	}

	public String getFaxNumber1() {
		return faxNumber1;
	}

	public void setFaxNumber1(String faxNumber1) {
		this.faxNumber1 = faxNumber1;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getKanziMei() {
		return kanziMei;
	}

	public void setKanziMei(String kanziMei) {
		this.kanziMei = kanziMei;
	}

	public String getKanaMei() {
		return kanaMei;
	}

	public void setKanaMei(String kanaMei) {
		this.kanaMei = kanaMei;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaNm() {
		return areaNm;
	}

	public void setAreaNm(String areaNm) {
		this.areaNm = areaNm;
	}

	public String getFaxNumber2() {
		return faxNumber2;
	}

	public void setFaxNumber2(String faxNumber2) {
		this.faxNumber2 = faxNumber2;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getHouseDetailNumber() {
		return houseDetailNumber;
	}

	public void setHouseDetailNumber(String houseDetailNumber) {
		this.houseDetailNumber = houseDetailNumber;
	}

	public String getBuildName() {
		return buildName;
	}

	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

	public String getWeekdayOpen1() {
		return StringUtil.timeFormat(weekdayOpen1);
	}

	public void setWeekdayOpen1(String weekdayOpen1) {
		this.weekdayOpen1 = weekdayOpen1;
	}

	public String getWeekdayClose1() {
		return StringUtil.timeFormat(weekdayClose1);
	}

	public void setWeekdayClose1(String weekdayClose1) {
		this.weekdayClose1 = weekdayClose1;
	}

	public String getWeekdayOpen2() {
		return StringUtil.timeFormat(weekdayOpen2);
	}

	public void setWeekdayOpen2(String weekdayOpen2) {
		this.weekdayOpen2 = weekdayOpen2;
	}

	public String getWeekdayClose2() {
		return StringUtil.timeFormat(weekdayClose2);
	}

	public void setWeekdayClose2(String weekdayClose2) {
		this.weekdayClose2 = weekdayClose2;
	}

	public String getSaturdayOpen1() {
		return StringUtil.timeFormat(saturdayOpen1);
	}

	public void setSaturdayOpen1(String saturdayOpen1) {
		this.saturdayOpen1 = saturdayOpen1;
	}

	public String getSaturdayClose1() {
		return StringUtil.timeFormat(saturdayClose1);
	}

	public void setSaturdayClose1(String saturdayClose1) {
		this.saturdayClose1 = saturdayClose1;
	}

	public String getSaturdayOpen2() {
		return StringUtil.timeFormat(saturdayOpen2);
	}

	public void setSaturdayOpen2(String saturdayOpen2) {
		this.saturdayOpen2 = saturdayOpen2;
	}

	public String getSaturdayClose2() {
		return StringUtil.timeFormat(saturdayClose2);
	}

	public void setSaturdayClose2(String saturdayClose2) {
		this.saturdayClose2 = saturdayClose2;
	}

	public String getHolidayOpen1() {
		return StringUtil.timeFormat(holidayOpen1);
	}

	public void setHolidayOpen1(String holidayOpen1) {
		this.holidayOpen1 = holidayOpen1;
	}

	public String getHolidayClose1() {
		return StringUtil.timeFormat(holidayClose1);
	}

	public void setHolidayClose1(String holidayClose1) {
		this.holidayClose1 = holidayClose1;
	}

	public String getHolidayOpen2() {
		return StringUtil.timeFormat(holidayOpen2);
	}

	public void setHolidayOpen2(String holidayOpen2) {
		this.holidayOpen2 = holidayOpen2;
	}

	public String getHolidayClose2() {
		return StringUtil.timeFormat(holidayClose2);
	}

	public void setHolidayClose2(String holidayClose2) {
		this.holidayClose2 = holidayClose2;
	}

	public String getSeasonStartDate() {
		return StringUtil.formatString(seasonStartDate);
	}

	public void setSeasonStartDate(String seasonStartDate) {
		this.seasonStartDate = seasonStartDate;
	}

	public String getSeasonEndDate() {
		return StringUtil.formatString(seasonEndDate);
	}

	public void setSeasonEndDate(String seasonEndDate) {
		this.seasonEndDate = seasonEndDate;
	}

	public String getSeasonStartTime() {
		return StringUtil.timeFormat(seasonStartTime);
	}

	public void setSeasonStartTime(String seasonStartTime) {
		this.seasonStartTime = seasonStartTime;
	}

	public String getSeasonEndTime() {
		return StringUtil.timeFormat(seasonEndTime);
	}

	public void setSeasonEndTime(String seasonEndTime) {
		this.seasonEndTime = seasonEndTime;
	}

	public String getOwenerCode() {
		return owenerCode;
	}

	public void setOwenerCode(String owenerCode) {
		this.owenerCode = owenerCode;
	}

	public String getOwenerName() {
		return owenerName;
	}

	public void setOwenerName(String owenerName) {
		this.owenerName = owenerName;
	}

	public String getOwenerCompanyName() {
		return owenerCompanyName;
	}

	public void setOwenerCompanyName(String owenerCompanyName) {
		this.owenerCompanyName = owenerCompanyName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getUnificationName() {
		return unificationName;
	}

	public void setUnificationName(String unificationName) {
		this.unificationName = unificationName;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}

	public String getDispOwenerName() {
		if (StringUtil.isEmpty(owenerCode)) {
			dispOwenerName = StringUtil.nullToBlank(owenerName);
		} else {
			dispOwenerName = "(" + owenerCode + ")" + StringUtil.nullToBlank(owenerName);;
		}
		return dispOwenerName;
	}

	public void setDispOwenerName(String dispOwenerName) {
		this.dispOwenerName = dispOwenerName;
	}

	public List<LabelValueBean> getStoreAttrList() {
		return storeAttrList;
	}

	public void setStoreAttrList(List<LabelValueBean> storeAttrList) {
		this.storeAttrList = storeAttrList;
	}

	public List<LabelValueBean> getSupervisorNameList() {
		return supervisorNameList;
	}

	public void setSupervisorNameList(List<LabelValueBean> supervisorNameList) {
		this.supervisorNameList = supervisorNameList;
	}

	public List<LabelValueBean> getOtherFlagList() {
		return otherFlagList;
	}

	public void setOtherFlagList(List<LabelValueBean> otherFlagList) {
		this.otherFlagList = otherFlagList;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOpenConsignFlag() {
		return openConsignFlag;
	}

	public void setOpenConsignFlag(String openConsignFlag) {
		this.openConsignFlag = openConsignFlag;
	}

	public String getOpenConsignStoreSet() {
		return openConsignStoreSet;
	}

	public void setOpenConsignStoreSet(String openConsignStoreSet) {
		this.openConsignStoreSet = openConsignStoreSet;
	}

	public String getOpenConsignStoreEx() {
		return openConsignStoreEx;
	}

	public void setOpenConsignStoreEx(String openConsignStoreEx) {
		this.openConsignStoreEx = openConsignStoreEx;
	}

	public String getOpenYconrc() {
		return openYconrc;
	}

	public void setOpenYconrc(String openYconrc) {
		this.openYconrc = openYconrc;
	}

	public String getOpenName() {
		return openName;
	}

	public void setOpenName(String openName) {
		this.openName = openName;
	}

	public String getVailedConsignFlag() {
		return vailedConsignFlag;
	}

	public void setVailedConsignFlag(String vailedConsignFlag) {
		this.vailedConsignFlag = vailedConsignFlag;
	}

	public String getVailedConsignStoreSet() {
		return vailedConsignStoreSet;
	}

	public void setVailedConsignStoreSet(String vailedConsignStoreSet) {
		this.vailedConsignStoreSet = vailedConsignStoreSet;
	}

	public String getVailedConsignStoreEx() {
		return vailedConsignStoreEx;
	}

	public void setVailedConsignStoreEx(String vailedConsignStoreEx) {
		this.vailedConsignStoreEx = vailedConsignStoreEx;
	}

	public String getVailedYconrc() {
		return vailedYconrc;
	}

	public void setVailedYconrc(String vailedYconrc) {
		this.vailedYconrc = vailedYconrc;
	}

	public String getVailedName() {
		return vailedName;
	}

	public void setVailedName(String vailedName) {
		this.vailedName = vailedName;
	}

	public String getOpenAccessionFlag() {
		return openAccessionFlag;
	}

	public void setOpenAccessionFlag(String openAccessionFlag) {
		this.openAccessionFlag = openAccessionFlag;
	}

	public String getOpenAccessionStoreSet() {
		return openAccessionStoreSet;
	}

	public void setOpenAccessionStoreSet(String openAccessionStoreSet) {
		this.openAccessionStoreSet = openAccessionStoreSet;
	}

	public String getOpenAccessionStoreEx() {
		return openAccessionStoreEx;
	}

	public void setOpenAccessionStoreEx(String openAccessionStoreEx) {
		this.openAccessionStoreEx = openAccessionStoreEx;
	}

	public String getVailedAccessionFlag() {
		return vailedAccessionFlag;
	}

	public void setVailedAccessionFlag(String vailedAccessionFlag) {
		this.vailedAccessionFlag = vailedAccessionFlag;
	}

	public String getVailedAccessionStoreSet() {
		return vailedAccessionStoreSet;
	}

	public void setVailedAccessionStoreSet(String vailedAccessionStoreSet) {
		this.vailedAccessionStoreSet = vailedAccessionStoreSet;
	}

	public String getVailedAccessionStoreEx() {
		return vailedAccessionStoreEx;
	}

	public void setVailedAccessionStoreEx(String vailedAccessionStoreEx) {
		this.vailedAccessionStoreEx = vailedAccessionStoreEx;
	}

	public String getConsignCount() {
		return consignCount;
	}

	public void setConsignCount(String consignCount) {
		this.consignCount = consignCount;
	}

	public String getAccessionCount() {
		return accessionCount;
	}

	public void setAccessionCount(String accessionCount) {
		this.accessionCount = accessionCount;
	}

	public List<LabelValueBean> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<LabelValueBean> companyList) {
		this.companyList = companyList;
	}

	public String getSearchCompanyId() {
		return searchCompanyId;
	}

	public void setSearchCompanyId(String searchCompanyId) {
		this.searchCompanyId = searchCompanyId;
	}

	public String getSearchUnificationId() {
		return searchUnificationId;
	}

	public void setSearchUnificationId(String searchUnificationId) {
		this.searchUnificationId = searchUnificationId;
	}

	public String getSearchBusinessId() {
		return searchBusinessId;
	}

	public void setSearchBusinessId(String searchBusinessId) {
		this.searchBusinessId = searchBusinessId;
	}

	public String getSearchSalesId() {
		return searchSalesId;
	}

	public void setSearchSalesId(String searchSalesId) {
		this.searchSalesId = searchSalesId;
	}

	
	public String getSearchownerCode() {
		return SearchownerCode;
	}

	public void setSearchownerCode(String searchownerCode) {
		SearchownerCode = searchownerCode;
	}

	public String getSearchownerName() {
		return SearchownerName;
	}

	public void setSearchownerName(String searchownerName) {
		SearchownerName = searchownerName;
	}

	public String getSupervisorCode() {
		return supervisorCode;
	}

	public void setSupervisorCode(String supervisorCode) {
		this.supervisorCode = supervisorCode;
	}

	public String getDisplayGroupCd() {
		return displayGroupCd;
	}

	public void setDisplayGroupCd(String displayGroupCd) {
		this.displayGroupCd = displayGroupCd;
	}
	
	public String getConsign_flag() {
		return consign_flag;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	
	public String getDisplayGroupName() {
		return displayGroupName;
	}

	public void setDisplayGroupName(String displayGroupName) {
		this.displayGroupName = displayGroupName;
	}

	public void setConsign_flag(String consign_flag) {
		this.consign_flag = consign_flag;
	}

	public String getAccession_flag() {
		return accession_flag;
	}

	public void setAccession_flag(String accession_flag) {
		this.accession_flag = accession_flag;
	}

	public void setUnificationList(List<LabelValueBean> unificationList) {
		this.unificationList = unificationList;
	}

	public List<LabelValueBean> getUnificationList() {
		return unificationList;
	}
	
	public void setShowFlagList(List<LabelValueBean> showFlagList) {
		this.showFlagList = showFlagList;
	}

	public List<LabelValueBean> getShowFlagList() {
		return showFlagList;
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
	
	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}
	
	public String getShowFlag() {
		return showFlag;
	}

	public String getAddressCode() {
		return addressCode;
	}
	
	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}
	
	public void setOtherFlag(String otherFlag) {
		this.otherFlag = otherFlag;
	}

	public String getOtherFlag() {
		return otherFlag;
	}

	public String getOtherFlagName() {
		return otherFlagName;
	}

	public void setOtherFlagName(String otherFlagName) {
		this.otherFlagName = otherFlagName;
	}

	public String getShowFlagName() {
		return showFlagName;
	}

	public void setShowFlagName(String showFlagName) {
		this.showFlagName = showFlagName;
	}
	
}