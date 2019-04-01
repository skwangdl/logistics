/**
 * File Name	: ProfieBean.java
 * Created Date	: 2016/01/07
 * COPYRIGHT(c)	: NTS
 */

package jp.co.fourseeds.fsnet.beans.profile;

import java.io.File;
import java.util.List;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.beans.LabelValueBean;

public class ProfileBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/** ユーザID */
	private String userId;

	/** パスワード */
	private String password;
	
	/** 新パスワード */
	private String newPassword;
	
	/** 表示パスワード */
	private String showPassword;

	/** 姓漢字  */
	private String kanziSei;

	/** 名漢字  */
	private String kanziMei;

	/** 姓カナ */
	private String kanaSei;
	
	/** 名カナ  */
	private String kanaMei;

	/** 旧姓漢字  */
	private String oldKanziSei;

	/** 旧姓カナ */
	private String oldKanaSei;
	
	/** 社有携帯番号 */
	private String mobileCorp;
	
	/** 内線 */
	private String extention;
	
	/**メールアドレス*/
	private String mailAddress;
	
	/**メールアドレス*/
	private String passwordSendingDivision;

	/** 画像フラグ   0. デフォルト　1,　選択画像*/
	private String imgFlag;

	/** 登録画像名 */
	private String personImgName;
	
	/** 登録画像名（戻る用） */
	private String personImgOldName;
	
	/** 登録画像*/
	private File personImg;
	
	/** 登録画像名 */
	private String personImgFileName;

	/** 登録画像パス*/
	private String personImgPath;
	
	/** 登録画像アドレス*/
	private String personImgAdd;
	
	/** 登録画像データ */
	private byte[] personImgDat;
	
	/** 登録画像データ size*/
	private String personImgDatSize;
	

	/** イメージデータ */
	private byte[] imgImgDat;
	
	/** イメージデータ size*/
	private String imgImgDatSize;
	
	/** 重要なお知らせ表示開始日 */
	private String necessityDisplayStartDate;
		
	/** 表示グループ */
	private String displayGroup;

	/** 組織区分 */
	private String original;
	
	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId 設定する userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password 設定する password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword 設定する newPassword
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @return showPassword
	 */
	public String getShowPassword() {
		return showPassword;
	}

	/**
	 * @param showPassword 設定する showPassword
	 */
	public void setShowPassword(String showPassword) {
		this.showPassword = showPassword;
	}
	
	/**
	 * @return kanziSei
	 */
	public String getKanziSei() {
		return kanziSei;
	}

	/**
	 * @param kanziSei 設定する kanziSei
	 */
	public void setKanziSei(String kanziSei) {
		this.kanziSei = kanziSei;
	}

	/**
	 * @return kanziMei
	 */
	public String getKanziMei() {
		return kanziMei;
	}

	/**
	 * @param kanziMei 設定する kanziMei
	 */
	public void setKanziMei(String kanziMei) {
		this.kanziMei = kanziMei;
	}

	/**
	 * @return kanaSei
	 */
	public String getKanaSei() {
		return kanaSei;
	}

	/**
	 * @param kanaSei 設定する kanaSei
	 */
	public void setKanaSei(String kanaSei) {
		this.kanaSei = kanaSei;
	}

	/**
	 * @return kanaMei
	 */
	public String getKanaMei() {
		return kanaMei;
	}

	/**
	 * @param kanaMei 設定する kanaMei
	 */
	public void setKanaMei(String kanaMei) {
		this.kanaMei = kanaMei;
	}

	/**
	 * @return OldKanziSei
	 */
	public String getOldKanziSei() {
		return oldKanziSei;
	}

	/**
	 * @param OldKanziSei 設定する OldKanziSei
	 */
	public void setOldKanziSei(String oldKanziSei) {
		this.oldKanziSei = oldKanziSei;
	}

	/**
	 * @return oldKanaSei
	 */
	public String getOldKanaSei() {
		return oldKanaSei;
	}

	/**
	 * @param oldKanaSei 設定する oldKanaSei
	 */
	public void setOldKanaSei(String oldKanaSei) {
		this.oldKanaSei = oldKanaSei;
	}

	/**
	 * @return mobileCorp
	 */
	public String getMobileCorp() {
		return mobileCorp;
	}

	/**
	 * @param mobileCorp 設定する mobileCorp
	 */
	public void setMobileCorp(String mobileCorp) {
		this.mobileCorp = mobileCorp;
	}

	/**
	 * @return extention
	 */
	public String getExtention() {
		return extention;
	}

	/**
	 * @param extention 設定する extention
	 */
	public void setExtention(String extention) {
		this.extention = extention;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPasswordSendingDivision() {
		return passwordSendingDivision;
	}

	public void setPasswordSendingDivision(String passwordSendingDivision) {
		this.passwordSendingDivision = passwordSendingDivision;
	}

	/**
	 * @return imgFlag
	 */
	public String getImgFlag() {
		return imgFlag;
	}

	/**
	 * @param imgFlag 設定する imgFlag
	 */
	public void setImgFlag(String imgFlag) {
		this.imgFlag = imgFlag;
	}

	/**
	 * @return personImgName
	 */
	public String getPersonImgName() {
		return personImgName;
	}

	/**
	 * @param personImgName 設定する personImgName
	 */
	public void setPersonImgName(String personImgName) {
		this.personImgName = personImgName;
	}


	/**
	 * @return personImg
	 */
	public File getPersonImg() {
		return personImg;
	}

	/**
	 * @param personImg 設定する personImg
	 */
	public void setPersonImg(File personImg) {
		this.personImg = personImg;
	}
	
	/**
	 * @return personImgFileName
	 */
	public String getPersonImgFileName() {
		return personImgFileName;
	}
	
	/**
	 * @param personImgFileName 設定する personImgFileName
	 */
	public void setPersonImgFileName(String personImgFileName) {
		this.personImgFileName = personImgFileName;
	}

	/**
	 * @return personImgPath
	 */
	public String getPersonImgPath() {
		return personImgPath;
	}

	/**
	 * @param personImgPath 設定する personImgPath
	 */
	public void setPersonImgPath(String personImgPath) {
		this.personImgPath = personImgPath;
	}

	/**
	 * @return personImgAdd
	 */
	public String getPersonImgAdd() {
		return personImgAdd;
	}

	/**
	 * @param personImgAdd 設定する personImgAdd
	 */
	public void setPersonImgAdd(String personImgAdd) {
		this.personImgAdd = personImgAdd;
	}
	
	/**
	 * @return personImgDat
	 */
	public byte[] getPersonImgDat() {
		return personImgDat;
	}

	/**
	 * @param personImgDat 設定する personImgDat
	 */
	public void setPersonImgDat(byte[] personImgDat) {
		this.personImgDat = personImgDat;
	}

	/**
	 * @return imgImgDat
	 */
	public byte[] getImgImgDat() {
		return imgImgDat;
	}

	/**
	 * @param imgImgDat 設定する imgImgDat
	 */
	public void setImgImgDat(byte[] imgImgDat) {
		this.imgImgDat = imgImgDat;
	}

	/**
	 * @return personImgDatSize
	 */
	public String getPersonImgDatSize() {
		return personImgDatSize;
	}

	/**
	 * @param personImgDatSize 設定する personImgDatSize
	 */
	public void setPersonImgDatSize(String personImgDatSize) {
		this.personImgDatSize = personImgDatSize;
	}

	/**
	 * @return imgImgDatSize
	 */
	public String getImgImgDatSize() {
		return imgImgDatSize;
	}

	/**
	 * @param imgImgDatSize 設定する imgImgDatSize
	 */
	public void setImgImgDatSize(String imgImgDatSize) {
		this.imgImgDatSize = imgImgDatSize;
	}
	/**
	 * @return necessityDisplayStartDate
	 */
	public String getNecessityDisplayStartDate() {
		return necessityDisplayStartDate;
	}

	/**
	 * @param necessityDisplayStartDate 設定する necessityDisplayStartDate
	 */
	public void setNecessityDisplayStartDate(String necessityDisplayStartDate) {
		this.necessityDisplayStartDate = necessityDisplayStartDate;
	}

	/**
	 * @return displayGroup
	 */
	public String getDisplayGroup() {
		return displayGroup;
	}

	/**
	 * @param displayGroup 設定する displayGroup
	 */
	public void setDisplayGroup(String displayGroup) {
		this.displayGroup = displayGroup;
	}

	public String getPersonImgOldName() {
		return personImgOldName;
	}

	public void setPersonImgOldName(String personImgOldName) {
		this.personImgOldName = personImgOldName;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}



}
