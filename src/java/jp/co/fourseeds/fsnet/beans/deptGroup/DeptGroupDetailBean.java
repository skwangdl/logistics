/**
 * O[vΪΧBean
 * 
 * @author NTS
 * @version 1.0.0 : 2015.12.25 VKμ¬
 * 
 * @author NTS
 * @version 1.1.0 : 2017.12.05 ©Ό΅C³
 */

package jp.co.fourseeds.fsnet.beans.deptGroup;

import jp.co.common.frame.beans.BaseBean;

public class DeptGroupDetailBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/** O[vID */
	private String deptGroupId;
	
	/**½f\θϊ*/
	private String dgRefDate;
	
	/** εID */
	private String departmentId;
	
	/** εΌΜ */
	private String departmentName;

	/** ΠυζͺID */
	private String conditionPemployeeId;

	/** ΠυζͺName */
	private String conditionPemployeeName;

	/** πtO */
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
