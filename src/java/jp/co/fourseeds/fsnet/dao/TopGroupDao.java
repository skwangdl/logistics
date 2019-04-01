package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.topGroup.TopGroupBean;
import jp.co.fourseeds.fsnet.beans.topGroup.TopGroupDetailBean;

/**
 * トップグループ情報機能Dao実装クラス
 * 
 * File Name: DeptGroupDao.java 
 * Created: 2015/12/22
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2015/12/22		    NTS        	       作成
 *
 **/
@Repository
public class TopGroupDao extends BaseDao {

	/**
	 * トップグループ明細情報テーブルを論理削除する。
	 * @param userId
	 * @param subGroupId
	 * @param subGroupFlag
	 */
	public void updateTopGroupDetailInvalid(String userId, String subGroupId, String subGroupFlag) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_SUB_GROUP_ID", subGroupId);
		param.put("PARA_SUB_GROUP_FLAG", subGroupFlag);
		param.put("PARA_UPDATE_BY", userId);
		this.sqlSessionTemplate.delete("topGroup.updateTopGroupDetailInvalid", param);
	}
	
	/**
	 *  トップグループ名称によって、トップグループ情報を取る
	 * @param searchSql　検索sql
	 * @param topGroupBean　画面入力値
	 * @param strOrderBy　ソート
	 * @return　検索結果
	 */
	public List<TopGroupBean> getTopGroupList(String searchSql, TopGroupBean topGroupBean, String strOrderBy) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// ユーザグループ名称
		param.put("PARA_TOP_GROUP_NAME", topGroupBean.getSearchTopGroupName());
		// ソート
		param.put("PARA_ORDER_BY", strOrderBy);
		return this.sqlSessionTemplate.selectList(searchSql, param);
	}

	/**
	 * トップグループIDと検索テーブルによって、トップグループ情報を取得する。
	 * @param topGroupId　トップグループID
	 * @param topGroupTable　検索トップグループテーブル
	 * @return　検索結果
	 */
	public TopGroupBean getTopGroupHeader(String topGroupId, String topGroupTable) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// トップグループID
		param.put("PARA_TOP_GROUP_ID", topGroupId);
		// トップグループ情報検索テーブル
		param.put("PARA_LEADING_GROUP_TABLE", topGroupTable);
		return this.sqlSessionTemplate.selectOne("topGroup.getTopGroupHeader", param);
	}

	/**
	 * トップグループIDと検索テーブルによって、トップグループ所属する有効のみ部署グループ情報を取得する。
	 * @param topGroupId　トップグループID
	 * @param topGroupTable　検索トップグループテーブル
	 * @param deptGroupTable　検索部署グループテーブル
	 * @return　検索結果
	 */
	public List<TopGroupDetailBean> getTopGroupDetail_DeptGroup(String topGroupId, String topGroupTable, String deptGroupTable) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// トップグループID
		param.put("PARA_TOP_GROUP_ID", topGroupId);
		// トップグループ情報検索テーブル
		param.put("PARA_LEADING_GROUP_TABLE", topGroupTable);
		// 部署グループ情報検索テーブル
		param.put("PARA_DEPARTMENT_GROUP_TABLE", deptGroupTable);
		return this.sqlSessionTemplate.selectList("topGroup.getTopGroupDetail_DeptGroup", param);
	}
	
	/**
	 * トップグループIDと検索テーブルによって、トップグループ所属する有効のみ店舗グループ情報を取得する。
	 * @param topGroupId　トップグループID
	 * @param topGroupTable　検索トップグループテーブル
	 * @param storeGroupTable　検索店舗グループテーブル
	 * @param loginUser ユーザ
	 * @return　検索結果
	 */
	public List<TopGroupDetailBean> getTopGroupDetail_StoreGroup(String topGroupId, String topGroupTable, String storeGroupTable, LoginUserBean loginUser) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// トップグループID
		param.put("PARA_TOP_GROUP_ID", topGroupId);
		// トップグループ情報検索テーブル
		param.put("PARA_LEADING_GROUP_TABLE", topGroupTable);
		// 店舗グループ情報検索テーブル
		param.put("PARA_STORE_GROUP_TABLE", storeGroupTable);
		// 権限
		param.put("PARA_ROLE", loginUser.getRole());
		// ログインユーザID
		param.put("PARA_USER_ID", loginUser.getUserId());
		return this.sqlSessionTemplate.selectList("topGroup.getTopGroupDetail_StoreGroup", param);
	}
	
	/**
	 * トップグループIDと検索テーブルによって、トップグループ所属する有効のみユーザグループ情報を取得する。
	 * @param topGroupId　トップグループID
	 * @param topGroupTable　検索トップグループテーブル
	 * @param userGroupTable　検索ユーザグループテーブル
	 * @return　検索結果
	 */
	public List<TopGroupDetailBean> getTopGroupDetail_UserGroup(String topGroupId, String topGroupTable, String userGroupTable) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// トップグループID
		param.put("PARA_TOP_GROUP_ID", topGroupId);
		// トップグループ情報検索テーブル
		param.put("PARA_LEADING_GROUP_TABLE", topGroupTable);
		// ユーザグループ情報検索テーブル
		param.put("PARA_USER_GROUP_TABLE", userGroupTable);
		return this.sqlSessionTemplate.selectList("topGroup.getTopGroupDetail_UserGroup", param);
	}

	/**
	 * トップグループ情報を物理削除する。
	 * @param param 
	 */
	public void deleteTopGroupInfo(Map<String, Object> param) {
		// トップグループヘッダー情報（予約）を物理削除する。
		this.sqlSessionTemplate.delete("topGroup.deleteTopGroupHeader", param);
		// トップグループ明細情報情報（予約）を物理削除する。
		this.sqlSessionTemplate.delete("topGroup.deleteTopGroupDetail", param);
	}

	/**
	 * トップグループ情報テーブルを論理削除する。
	 * @param userId
	 * @param topGroupBean
	 */
	public void updateTopGroupInfoInvalid(String userId, TopGroupBean topGroupBean) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// トップグループID
		param.put("PARA_TOP_GROUP_ID", topGroupBean.getTopGroupId());
		// トップグループ反映予定日時
		param.put("PARA_REFLECTION_SCHEDULE_DATE", topGroupBean.getLgRefDate());
		param.put("PARA_UPDATE_BY", userId);
		// トップグループヘッダー情報テーブルを論理削除する。
		this.sqlSessionTemplate.update("topGroup.updateTopGroupHeaderInvalid", param);
		// トップグループ明細情報テーブルを論理削除する。
		this.sqlSessionTemplate.update("topGroup.updateTopGroupDetailInvalid_ForTop", param);
		
	}

	/**
	 * コンテンツ閲覧権限トップグループ情報を論理削除する
	 * @param userId
	 * @param topGroupId
	 */
	public void updateAuthLeadingGroupInvalid(String userId, String topGroupId) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// トップグループID
		param.put("PARA_TOP_GROUP_ID", topGroupId);
		param.put("PARA_UPDATE_BY", userId);
		// コンテンツ閲覧権限トップグループ情報を論理削除する
		this.sqlSessionTemplate.update("topGroup.updateAuthLeadingGroupInvalid", param);
	}

	/**
	 * コンテンツ閲覧権限トップグループ予約情報を物理削除する。
	 * @param topGroupId
	 */
	public void deleteAuthLeadingGroupRsv(String topGroupId) {
		Map<String, Object> param = new HashMap<String, Object>();
		// トップグループID
		param.put("PARA_TOP_GROUP_ID", topGroupId);
		// コンテンツ閲覧権限トップグループ予約情報を物理削除する。
		this.sqlSessionTemplate.delete("topGroup.deleteAuthLeadingGroupRsv", param);
	}
	
	/**
	 * 登録済データとのトップグループ名称重複データを取得
	 * @param topGroupBean 
	 */
	public int getTopGroupNmCheckInfo(TopGroupBean topGroupBean, String eventType) {
		// 検索条件
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TOP_GROUP_NAME", topGroupBean.getTopGroupName());
		param.put("PARA_TOP_GROUP_ID", topGroupBean.getTopGroupId());
		param.put("PARA_EVENT_TYPE", eventType);
		//　DBから検索結果を取得
		return (Integer)this.sqlSessionTemplate.selectOne("topGroup.getTopGroupNm", param);
	}
	
	/**
	 * トップグループIDを採番する
	 * 
	 */
	public String getNewTopGroupId() {
		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectOne("topGroup.getNewTopGroupId");
	}
	
	/**
	 * トップグループヘッダ情報登録
	 * @param topGroupBean 
	 * 
	 */
	public void insertTopGroupHeader(TopGroupBean topGroupBean) {
		this.sqlSessionTemplate.insert("topGroup.insertTopGroupHeader", topGroupBean);
	}

	/**
	 * トップグループのサブグループ情報登録
	 * @param topGroupDetailBean 
	 * 
	 */
	public void insertTopGroupDetail(TopGroupDetailBean topGroupDetailBean) {
		this.sqlSessionTemplate.insert("topGroup.insertTopGroupDetail", topGroupDetailBean);
	}
}