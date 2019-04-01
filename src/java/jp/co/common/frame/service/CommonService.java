package jp.co.common.frame.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.common.frame.beans.LabelValueBean;
import jp.co.common.frame.dao.CommonDao;

@Component
public class CommonService {

	@Autowired
	private CommonDao commonDao;
	
	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	
	/**
	 * リスト取得
	 * @param fieldName
	 *           フィールド名
	 * @return
	 *           リスト内容
	 */
	public List<LabelValueBean> getDivisionMaster(String fieldName) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_FIELD_NAME", fieldName);
		return commonDao.getDivisionMaster(param);
	}
	
	/**
	 * 区分名称を取得
	 * @param fieldName
	 *           フィールド名
	 * @param mailUserDivision
	 *           区分
	 * @return
	 *           区分名称
	 */
	public String getDivisionName(String field,String mailUserDivision) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_FIELD_NAME", field);
		param.put("PARA_DIVISION", mailUserDivision);
		return commonDao.getDivisionName(param);
	}
	
	/**
	 * 表示区分名称を取得
	 * @param fieldName
	 *           フィールド名
	 * @param mailUserDivision
	 *           区分
	 * @return
	 *           区分名称
	 */
	public String getDispDivisionName(String field, String mailUserDivision) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_FIELD_NAME", field);
		param.put("PARA_DIVISION", mailUserDivision);
		return commonDao.getDispDivisionName(param);
	}
	
	/**
	 * 区分を取得
	 * @param fieldName
	 *           フィールド名
	 * @return
	 *           区分
	 */
	public String getDivision(String field) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_FIELD_NAME", field);
		return commonDao.getDivision(param);
	}

	/**
	 * ユーザリストを取得
	 * @param 検索条件
	 * @return ユーザリスト
	 */
	public List<Map<String, String>> getUserList(Map<String, Object> param) {
		return commonDao.getUserList(param);
	}
	
	/**
	 * 共有ユーザリストを取得
	 * @param 検索条件
	 * @return ユーザリスト
	 */
	public List<Map<String, String>> getShareUserList(Map<String, Object> param) {
		return commonDao.getShareUserList(param);
	}

	/**
	 * 部署グループリストを取得
	 * @param groupTableName 検索部署グループテーブル名
	 * @return 部署グループリスト
	 */
	public List<LabelValueBean> getDeptGroupList_ForTop(String groupTableName, String deptGroupName) {
		return commonDao.getDeptGroupList_ForTop(groupTableName,deptGroupName);
	}

	/**
	 * 店舗グループリストを取得
	 * @param groupTableName 検索店舗グループテーブル名
	 * @param role 権限
	 * @param userId ユーザID
	 * @return 店舗グループリスト
	 */
	public List<LabelValueBean> getStoreGroupList_ForTop(String storesql,String groupTableName, String role, String userId , String storeGroupName) {
		return commonDao.getStoreGroupList_ForTop(storesql,groupTableName, role, userId,storeGroupName);
	}

	/**
	 * ユーザグループリストを取得
	 * @param groupTableName 検索ユーザグループテーブル名
	 * @return ユーザグループリスト
	 */
	public List<LabelValueBean> getUserGroupList_ForTop(String groupTableName, String userGroupName) {
		return commonDao.getUserGroupList_ForTop(groupTableName,userGroupName);
	}
	
	/**
	 * 会社リストを取得
	 * @param param
	 * @return
	 */
	public List<LabelValueBean> getCompanyList() {
		return commonDao.getCompanyList();
	}
	
	/**
	 * 統括リストを取得
	 * @param param
	 * @return
	 */
	public List<LabelValueBean> getUnificationList(String companyId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_COMPANY_ID", companyId);
		return commonDao.getUnificationList(param);
	}
	
	/**
	 * 事業リストを取得
	 * @param param
	 * @return
	 */
	public List<LabelValueBean> getBusinessList(String unificationId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_UNIFICATION_ID", unificationId);
		return commonDao.getBusinessList(param);
	}
	
	/**
	 * 営業リストを取得
	 * @param param
	 * @return
	 */
	public List<LabelValueBean> getSalesList(String businessId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_BUSINESS_ID", businessId);
		return commonDao.getSalesList(param);
	}
	
	/**
	 * 各種マスタリストを取得
	 * @param param
	 * @return
	 */
	public List<LabelValueBean> getCommonList(String keyCode) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_KEY_CODE", keyCode);
		return commonDao.getCommonList(param);
	}
	
	/**
	 * ユーザリストを取得
	 * @param 検索条件
	 * @return ユーザリスト
	 */
	public List<Map<String, String>> getUserListByCondition(Map<String, Object> param) {
		return commonDao.getUserListByCondition(param);
	}
	
}
