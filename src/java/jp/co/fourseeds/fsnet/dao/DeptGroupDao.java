package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.deptGroup.DeptGroupBean;
import jp.co.fourseeds.fsnet.beans.deptGroup.DeptGroupDetailBean;

/**
 * 部署グループ情報機能Dao実装クラス
 * 
 * File Name: DeptGroupDao.java 
 * Created: 2015/12/22
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2015/12/22		    NTS        	       作成
 * 1.1		2017/12/05			NTS			見直し修正
 *
 **/
@Repository
public class DeptGroupDao extends BaseDao {

	/**
	 *  部署グループ名称によって、部署グループ情報を取る
	 * @param searchSql　検索sql
	 * @param deptGroupBean　画面入力値
	 * @param strOrderBy　ソート
	 * @return　検索結果
	 */
	public List<DeptGroupBean> getDeptGroupList(String searchSql, DeptGroupBean deptGroupBean, String strOrderBy) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// 部署グループ名称
		param.put("PARA_DEPARTMENT_GROUP_NAME", deptGroupBean.getSearchDeptGroupName());
		// ソート
		param.put("PARA_ORDER_BY", strOrderBy);
		return this.sqlSessionTemplate.selectList(searchSql, param);
	}
	
	/**
	 * 部署グループIDと検索テーブルによって、部署グループ情報を取得する。
	 * @param deptGroupId　部署グループID
	 * @param deptGroupTable　検索部署グループテーブル
	 * @return　検索結果
	 */
	public DeptGroupBean getDeptGroupHeader(String deptGroupId, String deptGroupTable) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// 部署グループID
		param.put("PARA_DEPARTMENT_GROUP_ID", deptGroupId);
		// 部署グループ情報検索テーブル
		param.put("PARA_DEPARTMENT_GROUP_TABLE", deptGroupTable);
		return this.sqlSessionTemplate.selectOne("deptGroup.getDeptGroupHeader", param);
	}
	
	/**
	 * 部署グループIDと検索テーブルによって、部署グループ所属する有効のみ部署情報を取得する。
	 * @param deptGroupId　部署グループID
	 * @param deptGroupTable　検索部署グループテーブル
	 * @param departmentTable　検索部署テーブル
	 * @return　検索結果
	 */
	public List<DeptGroupDetailBean> getDeptGroupDetail(String deptGroupId, String deptGroupTable) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// 部署グループID
		param.put("PARA_DEPARTMENT_GROUP_ID", deptGroupId);
		// 部署グループ情報検索テーブル
		param.put("PARA_DEPARTMENT_GROUP_TABLE", deptGroupTable);
		return this.sqlSessionTemplate.selectList("deptGroup.getDeptGroupDetail", param);
	}
	
	/**
	 * 部署グループIDと検索テーブルによって、部署グループの部署情報を取得する。
	 * @param deptGroupId　部署グループID
	 * @return　検索結果
	 */
	public List<DeptGroupDetailBean> getDeptDetail(String deptGroupId, String deptGroupTable) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// 部署グループID
		param.put("PARA_DEPARTMENT_GROUP_ID", deptGroupId);
		// 部署グループ情報検索テーブル
		param.put("PARA_DEPARTMENT_GROUP_TABLE", deptGroupTable);
		return this.sqlSessionTemplate.selectList("deptGroup.getDeptDetail", param);
	}
	
	/**
	 * 部署情報を検索
	 * @param param
	 * @return　検索結果
	 */
	public List<DeptGroupDetailBean> getDeptList_left(Map<String, Object> param) {
		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("deptGroup.getDeptList_left", param);
	}

	/**
	 * 部署情報を検索
	 * @param param
	 * @return　検索結果
	 */
	public List<DeptGroupDetailBean> getDeptList_right(Map<String, Object> param) {
		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("deptGroup.getDeptList_right", param);
	}
	
	/**
	 * 部署グループIDを採番する
	 * 
	 */
	public String getNewDeptGroupId() {
		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectOne("deptGroup.getNewDeptGroupId");
	}

	/**
	 * 登録済データとの部署グループ名称重複データを取得
	 * @param deptGroupBean 
	 */
	public int getDeptGroupNmCheckInfo(DeptGroupBean deptGroupBean, String eventType) {
		// 検索条件
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_DEPARTMENT_GROUP_NAME", deptGroupBean.getDeptGroupName());
		param.put("PARA_DEPARTMENT_GROUP_ID", deptGroupBean.getDeptGroupId());
		param.put("PARA_EVENT_TYPE", eventType);
		//　DBから検索結果を取得
		return (Integer)this.sqlSessionTemplate.selectOne("deptGroup.getDeptGroupNm", param);
	}

	/**
	 * 部署グループ情報登録
	 * @param deptGroupBean 
	 * 
	 */
	public void insertDeptGroup(DeptGroupBean deptGroupBean) {
		this.sqlSessionTemplate.insert("deptGroup.insertDeptGroup", deptGroupBean);
	}

	/**
	 * 部署グループの部署情報登録
	 * @param deptGroupDetailBean 
	 * 
	 */
	public void insertDeptGroupDetail(DeptGroupDetailBean deptGroupDetailBean) {
		this.sqlSessionTemplate.insert("deptGroup.insertDeptGroupDetail", deptGroupDetailBean);
	}

	/**
	 * 部署グループ情報を物理削除する。
	 * @param deptGroupBean 
	 * @param deleteFlag reserve:予約情報削除  both:予約と公開情報削除
	 */
	public void deleteDeptGroupInfo(Map<String, Object> param) {
		// 部署グループヘッダー情報（予約）を物理削除する。
		this.sqlSessionTemplate.delete("deptGroup.deleteDeptGroupHeader", param);
		// 部署グループ明細情報情報（予約）を物理削除する。
		this.sqlSessionTemplate.delete("deptGroup.deleteDeptGroupDetail", param);
	}

	/**
	 * 部門グループ情報テーブルを論理削除する。
	 * @param userId
	 * @param deptGroupBean
	 */
	public void updateDeptGroupInfoInvalid(String userId, DeptGroupBean deptGroupBean) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// 部署グループID
		param.put("PARA_DEPARTMENT_GROUP_ID", deptGroupBean.getDeptGroupId());
		// 部署グループ反映予定日時
		param.put("PARA_REFLECTION_SCHEDULE_DATE", deptGroupBean.getDgRefDate());
		// 更新者
		param.put("PARA_UPDATE_BY", userId);
		// 部門グループヘッダー情報テーブルを論理削除する。
		this.sqlSessionTemplate.update("deptGroup.updateDeptGroupHeaderInvalid", param);
		// 部門グループ明細情報テーブルを論理削除する。
		this.sqlSessionTemplate.update("deptGroup.updateDeptGroupDetailInvalid", param);
	}
	
	/**
	 * 部門店舗グループ所属人数情報削除
	 * @param param 
	 */
	public void deleteDepartmentStoreGroupUser(DeptGroupBean deptGroupBean){
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// 店舗グループID
		param.put("PARA_DEPT_GROUP_ID",deptGroupBean.getDeptGroupId());
		this.sqlSessionTemplate.delete("deptGroup.deleteDepartmentStoreGroupUser", param);
	}
	
	/**
	 * 部門店舗グループ所属人数情報登録
	 * @param param 
	 */
	public void insertDepartmentStoreGroupUser(DeptGroupBean deptGroupBean){
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// 店舗グループID
		param.put("PARA_DEPT_GROUP_ID",deptGroupBean.getDeptGroupId());
		this.sqlSessionTemplate.insert("deptGroup.insertDepartmentStoreGroupUser", param);
	}
	
	/**
	 * 部署リンクをクリック、階層レベル検索
	 * @param param
	 * @return　検索結果
	 */
	public String getDeptLevel(Map<String, Object> param) {
		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectOne("deptGroup.getDeptLevel", param);
	}
	
	/**
	 * 会社リンクをクリック、所属統括検索
	 * @param param
	 * @return　検索結果
	 */
	public List<Map<String, String>> getSecondDeptList(Map<String, Object> param) {
		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("deptGroup.getSecondDeptList", param);
	}
	
	/**
	 * 統括リンクをクリック、所属事業検索
	 * @param param
	 * @return　検索結果
	 */
	public List<Map<String, String>> getThirdDeptList(Map<String, Object> param) {
		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("deptGroup.getThirdDeptList", param);
	}
	
	/**
	 * 事業リンクをクリック、所属営業部検索
	 * @param param
	 * @return　検索結果
	 */
	public List<Map<String, String>> getFourthDeptList(Map<String, Object> param) {
		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("deptGroup.getFourthDeptList", param);
	}
}