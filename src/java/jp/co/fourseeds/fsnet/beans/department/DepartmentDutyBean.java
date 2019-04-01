package jp.co.fourseeds.fsnet.beans.department;

import jp.co.common.frame.beans.BaseBean;

public class DepartmentDutyBean extends BaseBean{
	private static final long serialVersionUID = 1122334455987654335L;
	/** •”–åID */
	private String departmentId;

	/** ’S“–E–±Ğ‰î */
	private String dutyDescription;

	/** ’S“–ƒ†[ƒUID */
	private String userId;

	/** ©Š¿š */
	private String kanziSei;
	
	/** –¼Š¿š */
	private String kanziMei;
	
	/** ’S“–Ò–¼ */
	private String userName;
	
	/** ‰æ–Êíœƒtƒ‰ƒO */
	private String chkDutyDelFlag;
	
	
	/**
	 * @return departmentId
	 */
	public String getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId
	 *            İ’è‚·‚é departmentId
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return dutyDescription
	 */
	public String getDutyDescription() {
		return dutyDescription;
	}

	/**
	 * @param dutyDescription
	 *            İ’è‚·‚é dutyDescription
	 */
	public void setDutyDescription(String dutyDescription) {
		this.dutyDescription = dutyDescription;
	}

	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            İ’è‚·‚é userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName İ’è‚·‚é userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getKanziSei() {
		return kanziSei;
	}

	public void setKanziSei(String kanziSei) {
		this.kanziSei = kanziSei;
	}

	public String getKanziMei() {
		return kanziMei;
	}

	public void setKanziMei(String kanziMei) {
		this.kanziMei = kanziMei;
	}

	public String getChkDutyDelFlag() {
		return chkDutyDelFlag;
	}

	public void setChkDutyDelFlag(String chkDutyDelFlag) {
		this.chkDutyDelFlag = chkDutyDelFlag;
	}
}
