package jp.co.fourseeds.fsnet.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.department.DepartmentDutyBean;
import jp.co.fourseeds.fsnet.beans.department.DepartmentFormBean;
import jp.co.fourseeds.fsnet.beans.department.DepartmentLinkBean;
import jp.co.fourseeds.fsnet.beans.department.DepartmentBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.DepartmentDao;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.dao.CommonDao;
import jp.co.common.frame.service.BaseBusinessService;

/**
 * 部署情報機能サービス実装クラス
 * 
 * File Name: DepartmentService.java 
 * Created: 2015/09/22
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2015/09/22		    NTS        	       作成
 *　1.1      2017/11/21          NTS			       見直し修正 
 *
 **/
@SuppressWarnings(value={"rawtypes","unchecked"})
@Component
public class DepartmentService extends BaseBusinessService{

	@Autowired
	private DepartmentDao departmentDao;

	@Autowired
	private CommonDao commonDao;
	
	List objs = new ArrayList();
	
	/**
	 * 部門名称とシステム利用区分によって、部門情報を取る
	 * @param param　検索条件
	 * @return　検索結果
	 */
	public List<DepartmentBean> getDeptList(DepartmentFormBean formBean, String strOrderBy) {
		
		List<DepartmentBean> result = null;
		//　検索用パラメタを設定
		//　検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_ORDER_BY", strOrderBy);
		//　部署ID
		param.put("PARA_DEPARTMENT_ID", formBean.getSearchDepartmentId());
		//　部署名
		param.put("PARA_DEPARTMENT_NAME", formBean.getSearchDepartmentName());
		//　表示フラグ
		param.put("PARA_SHOWFLAG_ID", formBean.getSearchShowFlag());
		//　会社ID
		param.put("PARA_COMPANY_ID", formBean.getSearchCompanyId());
		//　統括ID
		param.put("PARA_UNIFICATION_ID", formBean.getSearchUnificationId());
		//　事業ID
		param.put("PARA_BUSINESS_ID", formBean.getSearchBusinessId());
		//　営業部ID
		param.put("PARA_SALES_ID", formBean.getSearchSalesId());
		//　 システム利用区分	
		param.put("PARA_ROLE", getLoginUserBean().getRole());
		
		result = departmentDao.getDeptList(param);
		return result;
	}

	/**
	 * 部門IDによって、部署名称を取る
	 * @param departmentId
	 *            部門ID
	 */
	public DepartmentBean getDept_Open(String departmentId) {
		return departmentDao.getDept_Open(departmentId);
	}
	
	/**
	 * 部門IDによって、部門情報を取る
	 * @param departmentId
	 *            部門ID
	 */
	public DepartmentBean getDeptInfo(String departmentId) {
		return departmentDao.getDeptInfo(departmentId);
	}
	
	/**
	 * 部門IDによって、部門職務情報を取る
	 * @param departmentId
	 *            部門ID
	 */
	public List getDeptDutyList(String departmentId) {

		List<DepartmentDutyBean> list = new ArrayList<DepartmentDutyBean>();
		List temp = new ArrayList();
		temp = departmentDao.getDeptDutyList(departmentId);
		HashMap map = new HashMap();
		List orderList = new ArrayList();
		for (int i = 0; i < temp.size(); i++) {
			DepartmentDutyBean duty = (DepartmentDutyBean) temp.get(i);
			DepartmentDutyBean tempDuty = (DepartmentDutyBean) map.get(duty.getDutyDescription());
			if (tempDuty != null) {
//				String dutyDesc = duty.getDutyDescription();
//				String temDesc = tempDuty.getDutyDescription();
				String name = tempDuty.getUserName();
				String id = tempDuty.getUserId();
				name = name + "," + duty.getKanziSei() + duty.getKanziMei();
				id = id + "," + duty.getUserId();
				tempDuty.setUserName(name);
				tempDuty.setUserId(id);
				map.put(duty.getDutyDescription(), tempDuty);

			} else {
				String name = duty.getKanziSei() + duty.getKanziMei();
				duty.setUserName(name);
				map.put(duty.getDutyDescription(), duty);
				orderList.add(duty.getDutyDescription());
			}
		}

		for (int i = 0; i < orderList.size(); i++) {
			String strKey = (String) orderList.get(i);
			DepartmentDutyBean tempDuty = (DepartmentDutyBean) map.get(strKey);
			list.add(tempDuty);
		}

		return list;
	}
	
	/**
	 * 部門IDによって、部門リンク情報を取る
	 * @param departmentId
	 *            部門ID
	 */
	public List getDeptLinkList(String departmentId) {
		return departmentDao.getDeptLinkList(departmentId);
	}
	
	/**
	 * 部門情報を更新
	 * @param departmentAddForm
	 *            部門追加フォーム
	 */
	public void updateDepartment(DepartmentFormBean formBean) throws Exception {
		LoginUserBean loginUser = super.getLoginUserBean();
		formBean.setRole(loginUser.getRole());
		String departmentId = formBean.getDepartmentId();
		// 公開部署情報の作成者と作成日付を取得
		BaseBean createInfo = commonDao.getDbCommonInfo("V_NUUE00P", "UEBMCD", departmentId);
		
		formBean.setRole(loginUser.getRole());
		formBean.setUpdateBy(loginUser.getUserId());
		// 部門マスタ情報を変更
		departmentDao.updateDepartmentMaster(formBean);

		// 部門職務情報を変更
		updateLinkAndDuty(formBean, createInfo);
	}

	/**
	 *部門職務情報と部門リンク情報データ更新
	 * */
	private void updateLinkAndDuty(DepartmentFormBean formBean, BaseBean createInfo) {
		
		String departmentId = formBean.getDepartmentId();
		// 物理削除
		departmentDao.deleteDepartmentDuty(departmentId);
		departmentDao.deleteDepartmentLink(departmentId);
		
		insertLinkAndDuty(formBean, createInfo);
	}
	

	/**
	 *部門職務情報と部門リンク情報データ更新
	 * */
	private void insertLinkAndDuty(DepartmentFormBean formBean, BaseBean createInfo) {
		
		LoginUserBean loginUser = super.getLoginUserBean();
		String departmentId = formBean.getDepartmentId();
		
		// １．部門職務情報を追加
		List<DepartmentDutyBean> dutyList = formBean.getDutyList();
		DepartmentDutyBean departmentDutyBean; 
		if (dutyList != null) {
			for (int i = 0; i < dutyList.size(); i++) {
				departmentDutyBean = dutyList.get(i);
				departmentDutyBean.setDepartmentId(departmentId);

				String strUserId = StringUtil.nullToBlank(departmentDutyBean.getUserId());
				String userId[] = strUserId.split(",");
				for (int j = 0; j < userId.length; j++) {
					departmentDutyBean.setUserId(userId[j]);
					departmentDao.insertDepartmentDuty(departmentDutyBean, loginUser, createInfo);
				}
			}
		}
		
		// ２．部門リンク情報を追加
		List<DepartmentLinkBean> urlList = formBean.getUrlList();
		if (urlList != null) {
			for (int i = 0; i < urlList.size(); i++) {
				departmentDao.insertDepartmentLink(departmentId, urlList.get(i), loginUser, createInfo);
			}
		}
	}
}