package jp.co.common.frame.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.beans.LabelValueBean;
import jp.co.common.frame.dao.BaseDao;
import jp.co.common.frame.dao.CommonDao;

@SuppressWarnings("rawtypes")
@Component
public class CommonDao extends BaseDao{

	/**
	 * DBデータの作成情報を取得
	 */
	public BaseBean getDbCommonInfo(String tableName, String idKey, Object idValue) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TABLE_NAME", tableName);
		param.put("PARA_ID_KEY", idKey);
		param.put("PARA_ID_VALUE", idValue);
		
		List rtn = this.sqlSessionTemplate.selectList("common.getDbCommonInfo", param);
		if (rtn != null && rtn.size() > 0) {
			return (BaseBean)rtn.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 部署マスタのSelectBoxアイテム取得
	 */
	public List<LabelValueBean> getDepartmentList() {
		return this.sqlSessionTemplate.selectList("common.getDepartmentList");
	}
	
	/**
	 * リスト取得
	 * @param param
	 *           検索条件
	 * @return
	 *           リスト内容
	 */
	public List<LabelValueBean> getDivisionMaster(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectList("common.getDivisionMaster", param);
	}
	
	/**
	 * 区分名称を取得
	 * @param param
	 *           検索条件
	 * @return
	 *           区分名称
	 */
	public String getDivisionName(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectOne("common.getDivisionName", param);
	}
	
	/**
	 * 表示区分名称を取得
	 * @param param
	 *           検索条件
	 * @return
	 *           区分名称
	 */
	public String getDispDivisionName(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectOne("common.getDispDivisionName", param);
	}
	
	/**
	 * 区分を取得
	 * @param param
	 *           検索条件
	 * @return
	 *           区分
	 */
	public String getDivision(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectOne("common.getDivision", param);
	}
	
	/**
	 * ユーザ氏名を取得
	 * @param param
	 *           検索条件
	 * @return
	 *           区分名称
	 */
	public String getUserName(String userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userId);
		return this.sqlSessionTemplate.selectOne("common.getUserName", param);
	}
	
	/**
	 * ユーザ情報検索の共通メソッド
	 * 呼出元
	 * １）部署グループ詳細画面
	 * ２）店舗グループ詳細画面
	 * ３）トップグループ詳細画面
	 * 
	 * 
	 * 検索元（V_NXXA00P）も引数として引渡す
	 * @param 検索条件
	 * @return 区分?と区分内容
	 */
	public List<Map<String, String>> getUserList(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectList("common.getUserList", param);
	}
	
	/**
	 * 共有ユーザ情報検索の共通メソッド
	 * 呼出元
	 * １）個人トップグループの共有者検索
	 * ３）店舗グループの共有ユーザ検索
	 * 
	 * @param 検索条件
	 * @return 区分?と区分内容
	 */
	public List<Map<String, String>> getShareUserList(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectList("common.getShareUserList", param);
	}

	/**
	 * 部署グループリストを取得
	 * @param groupTableName 検索部署グループテーブル名
	 * @return 部署グループリスト
	 */
	public List<LabelValueBean> getDeptGroupList_ForTop(String groupTableName, String deptGroupName) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_DEPARTMENT_GROUP_TABLE", groupTableName);
		param.put("PARA_DEPARTMENT_GROUP_NAME", deptGroupName);
		return this.sqlSessionTemplate.selectList("common.getDeptGroupList_ForTop", param);
	}

	/**
	 * 店舗グループリストを取得
	 * @param groupTableName 検索店舗グループテーブル名
	 * @param role 権限
	 * @param userId ユーザID
	 * @return 店舗グループリスト
	 */
	public List<LabelValueBean> getStoreGroupList_ForTop(String storesql, String groupTableName, String role, String userId, String storeGroupName) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_STORE_GROUP_TABLE", groupTableName);
		param.put("PARA_ROLE", role);
		param.put("PARA_USER_ID", userId);
		param.put("PARA_STORE_GROUP_NAME", storeGroupName);
		return this.sqlSessionTemplate.selectList(storesql, param);
	}

	/**
	 * ユーザグループリストを取得
	 * @param groupTableName 検索ユーザグループテーブル名
	 * @return ユーザグループリスト
	 */
	public List<LabelValueBean> getUserGroupList_ForTop(String groupTableName, String userGroupName) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_GROUP_TABLE", groupTableName);
		param.put("PARA_USER_GROUP_NAME", userGroupName);
		return this.sqlSessionTemplate.selectList("common.getUserGroupList_ForTop", param);
	}
	
	/**
	 * 会社リストを取得
	 * @param param
	 * @return
	 */
	public List<LabelValueBean> getCompanyList() {
		return this.sqlSessionTemplate.selectList("common.getCompanyList");
	}
	
	/**
	 * 統括リストを取得
	 * @param param
	 * @return
	 */
	public List<LabelValueBean> getUnificationList(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectList("common.getUnificationList", param);
	}
	
	/**
	 * 事業リストを取得
	 * @param param
	 * @return
	 */
	public List<LabelValueBean> getBusinessList(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectList("common.getBusinessList", param);
	}
	
	/**
	 * 営業リストを取得
	 * @param param
	 * @return
	 */
	public List<LabelValueBean> getSalesList(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectList("common.getSalesList", param);
	}
	
	/**
	 * 各種マスタリストを取得
	 * @param param
	 * @return
	 */
	public List<LabelValueBean> getCommonList(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectList("common.getCommonList", param);
	}
	
	/**
	 * ユーザ情報検索の共通メソッド
	 * 呼出元
	 * １）個人用トップグループ登録・更新画面でユーザ検索
	 * ２）コンテンツ登録・更新画面で公開する個人サブ画面
	 * ３）ユーザ情報サブ画面
	 * ４）ユーザグループ登録・更新画面でユーザ検索
	 * 
	 * 検索元（V_NXXA00P）
	 * @param 検索条件
	 * @return 区分?と区分内容
	 */
	public List<Map<String, String>> getUserListByCondition(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectList("common.getUserListByCondition", param);
	}
}