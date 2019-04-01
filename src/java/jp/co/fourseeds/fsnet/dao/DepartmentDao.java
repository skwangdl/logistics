package jp.co.fourseeds.fsnet.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.department.DepartmentDutyBean;
import jp.co.fourseeds.fsnet.beans.department.DepartmentFormBean;
import jp.co.fourseeds.fsnet.beans.department.DepartmentLinkBean;
import jp.co.fourseeds.fsnet.beans.department.DepartmentBean;

/**
 * 部署情報機能Dao実装クラス
 * 
 * File Name: DepartmentDao.java 
 * Created: 2015/09/22
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2015/09/22		    NTS        	       作成
 *　1.1		2017/11/21		    NTS        	       見直し修正
 *
 **/
@SuppressWarnings(value={"rawtypes"})
@Repository
public class DepartmentDao extends BaseDao {

	/**
	 *  部門名称とシステム利用区分によって、全てを取る
	 * @param param　検索条件
	 * @return　検索結果
	 */
	public List<DepartmentBean> getDeptList(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectList("department.getDeptList", param);
	}
	
	/**
	 * <p>
	 * 部門IDによって、部門情報を取る
	 * </p>
	 * 
	 * @param departmentId
	 */
	public DepartmentBean getDeptInfo(String departmentId) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_DEPARTMENT_ID", departmentId);
		
		return this.sqlSessionTemplate.selectOne("department.getDeptInfo", param);
	}
	
	/**
	 * <p>
	 * 部門IDによって、部署名称を取る
	 * </p>
	 * 
	 * @param departmentId
	 */
	public DepartmentBean getDept_Open(String departmentId) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_DEPARTMENT_ID", departmentId);
		
		return this.sqlSessionTemplate.selectOne("department.getDept_Open", param);
	}
	
	/**
	 * 部門IDによって、部門職務情報を取る
	 * @param departmentId
	 *            部門ID
	 */
	public List getDeptDutyList(String departmentId) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_DEPARTMENT_ID", departmentId);

		return this.sqlSessionTemplate.selectList("department.getDeptDutyList", param);
	}
	
	/**
	 * 部門IDによって、部門リンク情報を取る
	 * 
	 * @param departmentId
	 *            部門ID
	 */
	public List getDeptLinkList(String departmentId) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_DEPARTMENT_ID", departmentId);

		return this.sqlSessionTemplate.selectList("department.getDeptLinkList", param);
	}
	
	/**
	 * 部門IDによって、部門情報を変更
	 * @param departmentUpdateForm
	 *            部門変更フォーム
	 */
	public void updateDepartmentMaster(DepartmentFormBean departmentFormBean) {
		this.sqlSessionTemplate.update("department.updateDepartmentMaster", departmentFormBean);
	}
	
	/**
	 * 部門IDによって、部門職務情報を削除
	 * @param 部門ID
	 */
	public void deleteDepartmentDuty(String departmentId) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_DEPARTMENT_ID", departmentId);
		
		this.sqlSessionTemplate.delete("department.deleteDepartmentDuty", param);
	}

	/**
	 * 部門IDによって、部門リンク情報を削除
	 * @param 部門ID
	 */
	public void deleteDepartmentLink(String departmentId) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_DEPARTMENT_ID", departmentId);
		
		this.sqlSessionTemplate.delete("department.deleteDepartmentLink", param);
	}
	
	/**
	 * 部門職務情報を追加
	 * 
	 * @param departmentId
	 *            部門ID
	 * @param departmentDutyBean
	 *            部門職務モデル
	 * @param loginUser
	 *            ログインユーザ
	 * 
	 */
	public boolean insertDepartmentDuty(DepartmentDutyBean departmentDutyBean, LoginUserBean loginUser, BaseBean createInfo) {
		
		Date nowDate = new Date();
		departmentDutyBean.setCreateDate(createInfo.getCreateDate());
		departmentDutyBean.setCreateBy(createInfo.getCreateBy());
		departmentDutyBean.setUpdateDate(nowDate);
		departmentDutyBean.setUpdateBy(loginUser.getUserId());
		
		int returnValue = this.sqlSessionTemplate.insert("department.insertDepartmentDuty", departmentDutyBean);
		if (returnValue > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 部門リンク情報を追加
	 * 
	 * @param departmentId
	 *            部門ID
	 * @param departmentLinkBean
	 *            部門リンクモデル
	 * @param loginUser
	 *            ログインユーザ
	 * 
	 */
	public boolean insertDepartmentLink(String departmentId, DepartmentLinkBean departmentLinkBean, LoginUserBean loginUser, BaseBean createInfo) {
		
		departmentLinkBean.setDepartmentId(departmentId);
		
		Date nowDate = new Date();
		departmentLinkBean.setCreateDate(createInfo.getCreateDate());
		departmentLinkBean.setCreateBy(createInfo.getCreateBy());
		departmentLinkBean.setUpdateDate(nowDate);
		departmentLinkBean.setUpdateBy(loginUser.getUserId());
		int returnValue = this.sqlSessionTemplate.insert("department.insertDepartmentLink", departmentLinkBean);
		if (returnValue > 0) {
			return true;
		}
		
		return false;
	}
}