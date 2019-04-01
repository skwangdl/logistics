/**
 * 部署グループ詳細Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2015.12.25 新規作成
 * 
 * @author NTS
 * @version 1.1.0 : 2017.12.05 見直し修正
 */

package jp.co.fourseeds.fsnet.beans.deptGroup;

import jp.co.common.frame.beans.BaseBean;

public class DeptGroupDetailBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/** 部署グループID */
	private String deptGroupId;
	
	/**反映予定日時*/
	private String dgRefDate;
	
	/** 部門ID */
	private String departmentId;
	
	/** 部門名称 */
	private String departmentName;

	/** 社員区分ID */
	private String conditionPemployeeId;

	/** 社員区分Name */
	private String conditionPemployeeName;

	/** 条件フラグ */
	private String inputFlag;
	
	/** VALUE */
	private String value;
	
	public String getDeptGroupId() {
		return deptGroupId;
	}

	public void setDeptGroupId(String deptGroupId) {
		this.deptGroupId = deptGroupId;
	}

	public String getDgRefDate() {
		return dgRefDate;
	}

	public void setDgRefDate(String dgRefDate) {
		this.dgRefDate = dgRefDate;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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
	
}
