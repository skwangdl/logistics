package jp.co.fourseeds.fsnet.beans.personalMail;

import java.util.List;

import jp.co.common.frame.beans.BaseBean;

public class PersonalMailBean extends BaseBean {

	private static final long serialVersionUID = 1L;
	
	/**
	 * SEQUENCE
	 * */
	private String sequence;
	
	/**
	 * ユーザＩＤ
	 * */
	private String userId;
	
	/**
	 * 利用者姓
	 * */
	private String userSei;
	
	/**
	 * 利用者名
	 * */
	private String userMei;
	
	/**
	 * ステータス
	 * */
	private String mailTaskStatus;

	/**
	 * メールアドレス(確定情報)
	 * */
	private String mailAddress;

	/**
	 * 利用開始日(確定情報)
	 * */
	private String mailSet;

	/**
	 * 利用終了日(確定情報)
	 * */
	private String mailEx;

	/**
	 * 分類
	 * */
	private String mailUserDivision;

	/**
	 * ユーザ名
	 * */
	private String mailId;

	/**
	 * パスワード
	 * */
	private String password;

	/**
	 * Softbank登録日
	 * */
	private String entryDate;

	/**
	 * 退職日
	 * */
	private String retirementDate;

	/**
	 * Softbank削除日
	 * */
	private String deleteDate1;

	/**
	 * Google削除日
	 * */
	private String deleteDate2;

	/**
	 * 依頼者ＩＤ
	 * */
	private String requestId;

	/**
	 * 作業者ＩＤ
	 * */
	private String registryId;

	/**
	 * 所属名称
	 * */
	private String departmentName;

	/**
	 * 所属ＩＤ
	 * */
	private String departmentId;

	/**
	 * メールドメインフラグ
	 * */
	private String mailSuffixFlag;

	/**
	 * 分類表示名
	 * */
	private String mailUserDivName;

	/**
	 * 登録可能残数
	 * */
	private int validMailCnt;

	/**
	 * CSVファイル名前
	 * */
	private String fileName;

	/**
	 * 添付フラグ（1:添付する）
	 * */
	private String attachFlag;

	/**
	 * CSVファイル名前
	 * */
	private String filePath;

	/**
	 * CSV内容リスト
	 * */
	private List csvInfoList;

	/**
	 * 送信者メール
	 * */
	private String fromMail;

	/**
	 * 送信者名前
	 * */
	private String fromName;

	/**
	 * 宛先のメールリスト
	 * */
	private List toMailList;

	/**
	 * 宛先の名前リスト
	 * */
	private List toNameList;

	/**
	 * CCのメールリスト
	 * */
	private List ccMailList;

	/**
	 * CCの名前リスト
	 * */
	private List ccNameList;

	/**
	 * メール件名
	 * */
	private String mailSubject;

	/**
	 * メール内容
	 * */
	private String mailContent;

	/**
	 * 店舗固有ユーザFLAG
	 * */
	private String specificUserFlag;

	/**
	 * 本外部社員区分FLAG
	 * */
	private String pEmployeeFlag;

	/**
	 * 店舗ID
	 * */
	private String storeId;

	/**
	 * メール区分フラグ（0:pizza-la.co.jp、1:robuchon.jp）
	 * */
	private String mailDivisionFlag;

	/**
	 * 処理区分フラグ（1:新規、2:更新、3:削除）
	 * */
	private String dealDivisionFlag;

	/**
	 * 送信メール
	 * */
	private String uMail;

	/**
	 * 送信ユーザー
	 * */
	private String uName;

	/**
	 * 個別設定フラグ（ヌル：個別メール存在しない、0：個別メール設定依頼、1：個別メール完了通知）
	 * */
	private String personalMailSettingFlag;

	/**
	 * プロファイル区分
	 * */
	private String profileDiv;

	/**
	 * プロファイル区分表示名
	 * */
	private String profileDivName;

	/**
	 * 期限有効フラグ
	 * */
	private String validDateFlag;

	/**
	 * @return the sequence
	 */
	public String getSequence() {
		return sequence;
	}

	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userSei
	 */
	public String getUserSei() {
		return userSei;
	}

	/**
	 * @param userSei the userSei to set
	 */
	public void setUserSei(String userSei) {
		this.userSei = userSei;
	}

	/**
	 * @return the userMei
	 */
	public String getUserMei() {
		return userMei;
	}

	/**
	 * @param userMei the userMei to set
	 */
	public void setUserMei(String userMei) {
		this.userMei = userMei;
	}

	/**
	 * @return the mailTaskStatus
	 */
	public String getMailTaskStatus() {
		return mailTaskStatus;
	}

	/**
	 * @param mailTaskStatus the mailTaskStatus to set
	 */
	public void setMailTaskStatus(String mailTaskStatus) {
		this.mailTaskStatus = mailTaskStatus;
	}

	/**
	 * @return the mailAddress
	 */
	public String getMailAddress() {
		return mailAddress;
	}

	/**
	 * @param mailAddress the mailAddress to set
	 */
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	/**
	 * @return the mailSet
	 */
	public String getMailSet() {
		return mailSet;
	}

	/**
	 * @param mailSet the mailSet to set
	 */
	public void setMailSet(String mailSet) {
		this.mailSet = mailSet;
	}

	/**
	 * @return the mailEx
	 */
	public String getMailEx() {
		return mailEx;
	}

	/**
	 * @param mailEx the mailEx to set
	 */
	public void setMailEx(String mailEx) {
		this.mailEx = mailEx;
	}

	/**
	 * @return the mailUserDivision
	 */
	public String getMailUserDivision() {
		return mailUserDivision;
	}

	/**
	 * @param mailUserDivision the mailUserDivision to set
	 */
	public void setMailUserDivision(String mailUserDivision) {
		this.mailUserDivision = mailUserDivision;
	}

	/**
	 * @return the mailId
	 */
	public String getMailId() {
		return mailId;
	}

	/**
	 * @param mailId the mailId to set
	 */
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the entryDate
	 */
	public String getEntryDate() {
		return entryDate;
	}

	/**
	 * @param entryDate the entryDate to set
	 */
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * @return the retirementDate
	 */
	public String getRetirementDate() {
		return retirementDate;
	}

	/**
	 * @param retirementDate the retirementDate to set
	 */
	public void setRetirementDate(String retirementDate) {
		this.retirementDate = retirementDate;
	}

	/**
	 * @return the deleteDate1
	 */
	public String getDeleteDate1() {
		return deleteDate1;
	}

	/**
	 * @param deleteDate1 the deleteDate1 to set
	 */
	public void setDeleteDate1(String deleteDate1) {
		this.deleteDate1 = deleteDate1;
	}

	/**
	 * @return the deleteDate2
	 */
	public String getDeleteDate2() {
		return deleteDate2;
	}

	/**
	 * @param deleteDate2 the deleteDate2 to set
	 */
	public void setDeleteDate2(String deleteDate2) {
		this.deleteDate2 = deleteDate2;
	}

	/**
	 * @return the requestId
	 */
	public String getRequestId() {
		return requestId;
	}

	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	/**
	 * @return the registryId
	 */
	public String getRegistryId() {
		return registryId;
	}

	/**
	 * @param registryId the registryId to set
	 */
	public void setRegistryId(String registryId) {
		this.registryId = registryId;
	}

	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * @return the departmentId
	 */
	public String getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return the mailSuffixFlag
	 */
	public String getMailSuffixFlag() {
		return mailSuffixFlag;
	}

	/**
	 * @param mailSuffixFlag the mailSuffixFlag to set
	 */
	public void setMailSuffixFlag(String mailSuffixFlag) {
		this.mailSuffixFlag = mailSuffixFlag;
	}

	/**
	 * @return the mailUserDivName
	 */
	public String getMailUserDivName() {
		return mailUserDivName;
	}

	/**
	 * @param mailUserDivName the mailUserDivName to set
	 */
	public void setMailUserDivName(String mailUserDivName) {
		this.mailUserDivName = mailUserDivName;
	}

	/**
	 * @return the validMailCnt
	 */
	public int getValidMailCnt() {
		return validMailCnt;
	}

	/**
	 * @param validMailCnt the validMailCnt to set
	 */
	public void setValidMailCnt(int validMailCnt) {
		this.validMailCnt = validMailCnt;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the attachFlag
	 */
	public String getAttachFlag() {
		return attachFlag;
	}

	/**
	 * @param attachFlag the attachFlag to set
	 */
	public void setAttachFlag(String attachFlag) {
		this.attachFlag = attachFlag;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the csvInfoList
	 */
	public List getCsvInfoList() {
		return csvInfoList;
	}

	/**
	 * @param csvInfoList the csvInfoList to set
	 */
	public void setCsvInfoList(List csvInfoList) {
		this.csvInfoList = csvInfoList;
	}

	/**
	 * @return the fromMail
	 */
	public String getFromMail() {
		return fromMail;
	}

	/**
	 * @param fromMail the fromMail to set
	 */
	public void setFromMail(String fromMail) {
		this.fromMail = fromMail;
	}

	/**
	 * @return the fromName
	 */
	public String getFromName() {
		return fromName;
	}

	/**
	 * @param fromName the fromName to set
	 */
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	/**
	 * @return the toMailList
	 */
	public List getToMailList() {
		return toMailList;
	}

	/**
	 * @param toMailList the toMailList to set
	 */
	public void setToMailList(List toMailList) {
		this.toMailList = toMailList;
	}

	/**
	 * @return the toNameList
	 */
	public List getToNameList() {
		return toNameList;
	}

	/**
	 * @param toNameList the toNameList to set
	 */
	public void setToNameList(List toNameList) {
		this.toNameList = toNameList;
	}

	/**
	 * @return the ccMailList
	 */
	public List getCcMailList() {
		return ccMailList;
	}

	/**
	 * @param ccMailList the ccMailList to set
	 */
	public void setCcMailList(List ccMailList) {
		this.ccMailList = ccMailList;
	}

	/**
	 * @return the ccNameList
	 */
	public List getCcNameList() {
		return ccNameList;
	}

	/**
	 * @param ccNameList the ccNameList to set
	 */
	public void setCcNameList(List ccNameList) {
		this.ccNameList = ccNameList;
	}

	/**
	 * @return the mailSubject
	 */
	public String getMailSubject() {
		return mailSubject;
	}

	/**
	 * @param mailSubject the mailSubject to set
	 */
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	/**
	 * @return the mailContent
	 */
	public String getMailContent() {
		return mailContent;
	}

	/**
	 * @param mailContent the mailContent to set
	 */
	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	/**
	 * @return the specificUserFlag
	 */
	public String getSpecificUserFlag() {
		return specificUserFlag;
	}

	/**
	 * @param specificUserFlag the specificUserFlag to set
	 */
	public void setSpecificUserFlag(String specificUserFlag) {
		this.specificUserFlag = specificUserFlag;
	}

	/**
	 * @return the pEmployeeFlag
	 */
	public String getpEmployeeFlag() {
		return pEmployeeFlag;
	}

	/**
	 * @param pEmployeeFlag the pEmployeeFlag to set
	 */
	public void setpEmployeeFlag(String pEmployeeFlag) {
		this.pEmployeeFlag = pEmployeeFlag;
	}

	/**
	 * @return the storeId
	 */
	public String getStoreId() {
		return storeId;
	}

	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	/**
	 * @return the mailDivisionFlag
	 */
	public String getMailDivisionFlag() {
		return mailDivisionFlag;
	}

	/**
	 * @param mailDivisionFlag the mailDivisionFlag to set
	 */
	public void setMailDivisionFlag(String mailDivisionFlag) {
		this.mailDivisionFlag = mailDivisionFlag;
	}

	/**
	 * @return the dealDivisionFlag
	 */
	public String getDealDivisionFlag() {
		return dealDivisionFlag;
	}

	/**
	 * @param dealDivisionFlag the dealDivisionFlag to set
	 */
	public void setDealDivisionFlag(String dealDivisionFlag) {
		this.dealDivisionFlag = dealDivisionFlag;
	}

	/**
	 * @return the uName
	 */
	public String getuName() {
		return uName;
	}

	/**
	 * @param uName the uName to set
	 */
	public void setuName(String uName) {
		this.uName = uName;
	}

	/**
	 * @return the personalMailSettingFlag
	 */
	public String getPersonalMailSettingFlag() {
		return personalMailSettingFlag;
	}

	/**
	 * @param personalMailSettingFlag the personalMailSettingFlag to set
	 */
	public void setPersonalMailSettingFlag(String personalMailSettingFlag) {
		this.personalMailSettingFlag = personalMailSettingFlag;
	}

	/**
	 * @return the profileDiv
	 */
	public String getProfileDiv() {
		return profileDiv;
	}

	/**
	 * @param profileDiv the profileDiv to set
	 */
	public void setProfileDiv(String profileDiv) {
		this.profileDiv = profileDiv;
	}

	/**
	 * @return the profileDivName
	 */
	public String getProfileDivName() {
		return profileDivName;
	}

	/**
	 * @param profileDivName the profileDivName to set
	 */
	public void setProfileDivName(String profileDivName) {
		this.profileDivName = profileDivName;
	}

	/**
	 * @return the validDateFlag
	 */
	public String getValidDateFlag() {
		return validDateFlag;
	}

	/**
	 * @param validDateFlag the validDateFlag to set
	 */
	public void setValidDateFlag(String validDateFlag) {
		this.validDateFlag = validDateFlag;
	}

	/**
	 * @return the uMail
	 */
	public String getuMail() {
		return uMail;
	}

	/**
	 * @param uMail the uMail to set
	 */
	public void setuMail(String uMail) {
		this.uMail = uMail;
	}
	
}
