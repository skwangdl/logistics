/**
 * 部署グループBean
 * 
 * @author NTS
 * @version 1.0.0 : 2015.12.25 新規作成
 * 
 * @author NTS
 * @version 1.1.0 : 2017.12.05 見直し修正
 */

package jp.co.fourseeds.fsnet.beans.deptGroup;

import java.util.List;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.beans.LabelValueBean;

public class DeptGroupBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/**■■■■■■■■ここから、検索Bean■■■■■■■■■■■■■■■■*/
	/** 検索部署グループid*/
	private String searchDeptGroupId;
	
	/** 検索部署グループ名*/
	private String searchDeptGroupName;
	
	/** 検索区分*/
	private String searchOriginType;
	
	/** 検索テーブル区分　1:公開　2:予約 */
	private String editFlag;
	
	/** 初期検索 */
	private String isFirstSearch;
	
	/** ページ毎に表示件数情報格納 */
	private int searchRowNum;
	
	/**■■■■■■■■ここから、検索結果Bean■■■■■■■■■■■■*/
	/** 部署グループID */
	private String deptGroupId;
	
	/** 部署グループ名 */
	private String deptGroupName;
	
	/** 部署グループ紹介 */
	private String deptGroupIntro;
	
	/**反映予定日時*/
	private String dgRefDate;
	
	/** データ種類　1:公開　2:予約　12:公開&予約 */
	private String originType;
	
	/** 部門リスト */
	private List<DeptGroupDetailBean> departmentList;
	
	/** 部門リスト */
	private List<DeptGroupDetailBean> departmentDetailList;
	
	/** 部門ID */
	private String departmentId;
	
	/** 部門名称 */
	private String departmentName;
	
	/** SS部門ID */
	private String searchDepartmentId;

	/** SS部門名称 */
	private String searchDepartmentName;
	
	/** 会社Id*/
	private String conditionCompanyId;
	
	/** 統括Id*/
	private String conditionUnificationId;
	
	/** 事業部Id*/
	private String conditionBusinessId;
	
	/** 編集画面の会社リスト*/
	private List<LabelValueBean> companyList;
	
	/** 編集画面の統括リスト*/
	private List<LabelValueBean> unificationList;
	
	/** 編集画面の事業部リスト*/
	private List<LabelValueBean> businessList;

	/** 編集画面の社員区分リスト*/
	private List<LabelValueBean> pemployeeList;

	/** 社員区分Id*/
	private String conditionPemployeeId;

	/** 社員区分Name*/
	private String conditionPemployeeName;

	/** VALUE */
	private String value;
	
	/** LEVEL */
	private int level;
	
	/**気分flag**/
	private String groupFlag;
	
	public String getSearchDeptGroupId() {
		return searchDeptGroupId;
	}

	public void setSearchDeptGroupId(String searchDeptGroupId) {
		this.searchDeptGroupId = searchDeptGroupId;
	}

	public String getSearchDeptGroupName() {
		return searchDeptGroupName;
	}

	public void setSearchDeptGroupName(String searchDeptGroupName) {
		this.searchDeptGroupName = searchDeptGroupName;
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

	public String getDeptGroupIntro() {
		return deptGroupIntro;
	}

	public void setDeptGroupIntro(String deptGroupIntro) {
		this.deptGroupIntro = deptGroupIntro;
	}

	public String getDgRefDate() {
		return dgRefDate;
	}

	public void setDgRefDate(String dgRefDate) {
		this.dgRefDate = dgRefDate;
	}

	public List<DeptGroupDetailBean> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<DeptGroupDetailBean> departmentList) {
		this.departmentList = departmentList;
	}

	public List<DeptGroupDetailBean> getDepartmentDetailList() {
		return departmentDetailList;
	}

	public void setDepartmentDetailList(List<DeptGroupDetailBean> departmentDetailList) {
		this.departmentDetailList = departmentDetailList;
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

	public String getOriginType() {
		return originType;
	}

	public void setOriginType(String originType) {
		this.originType = originType;
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

	public String getSearchDepartmentId() {
		return searchDepartmentId;
	}

	public void setSearchDepartmentId(String searchDepartmentId) {
		this.searchDepartmentId = searchDepartmentId;
	}

	public String getSearchDepartmentName() {
		return searchDepartmentName;
	}

	public void setSearchDepartmentName(String searchDepartmentName) {
		this.searchDepartmentName = searchDepartmentName;
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

	public List<LabelValueBean> getPemployeeList() {
		return pemployeeList;
	}

	public void setPemployeeList(List<LabelValueBean> pemployeeList) {
		this.pemployeeList = pemployeeList;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getGroupFlag() {
		return groupFlag;
	}

	public void setGroupFlag(String groupFlag) {
		this.groupFlag = groupFlag;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
}
