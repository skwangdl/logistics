package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.userGroup.UserGroupBean;
import jp.co.fourseeds.fsnet.beans.userGroup.UserGroupDetailBean;

/**
 * ユーザグループ情報機能Dao実装クラス
 * 
 * File Name: DeptGroupDao.java 
 * Created: 2015/12/22
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2016/01/04		    NTS        	       作成
 * 1.1      2017/11/17          NTS            見直し修正
 **/
@Repository
public class UserGroupDao extends BaseDao {

	/**
	 *  ユーザグループ名称によって、ユーザグループ情報を取る
	 * @param searchSql　検索sql
	 * @param userGroupBean　画面入力値
	 * @param strOrderBy　ソート
	 * @return　検索結果
	 */
	public List<UserGroupBean> getUserGroupList(String searchSql, UserGroupBean userGroupBean, String strOrderBy) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// ユーザグループ名称
		param.put("PARA_USER_GROUP_NAME", userGroupBean.getSearchUserGroupName());
		// ソート
		param.put("PARA_ORDER_BY", strOrderBy);
		return this.sqlSessionTemplate.selectList(searchSql, param);
	}
	
	/**
	 * ユーザグループIDと検索テーブルによって、ユーザグループ情報を取得する。
	 * @param userGroupId　ユーザグループID
	 * @param userGroupTable　検索ユーザグループテーブル
	 * @return　検索結果
	 */
	public UserGroupBean getUserGroupHeader(String userGroupId, String userGroupTable) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// ユーザグループID
		param.put("PARA_USER_GROUP_ID", userGroupId);
		// ユーザグループ情報検索テーブル
		param.put("PARA_USER_GROUP_TABLE", userGroupTable);
		return this.sqlSessionTemplate.selectOne("userGroup.getUserGroupHeader", param);
	}
	
	/**
	 * ユーザグループIDと検索テーブルによって、ユーザグループ所属する有効のみユーザ情報を取得する。
	 * @param userGroupId　ユーザグループID
	 * @param userGroupTable　検索ユーザグループテーブル
	 * @return　検索結果
	 */
	public List<UserGroupDetailBean> getUserGroupDetail(String userGroupId, String userGroupTable) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// ユーザグループID
		param.put("PARA_USER_GROUP_ID", userGroupId);
		// ユーザグループ情報検索テーブル
		param.put("PARA_USER_GROUP_TABLE", userGroupTable);
		return this.sqlSessionTemplate.selectList("userGroup.getUserGroupDetail", param);
	}
	
	/**
	 * ユーザグループIDを採番する
	 * 
	 */
	public String getNewUserGroupId() {
		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectOne("userGroup.getNewUserGroupId");
	}

	/**
	 * 登録済データとのユーザグループ名称重複データを取得
	 * @param userGroupBean 
	 */
	public int getUserGroupNmCheckInfo(UserGroupBean userGroupBean, String eventType) {
		// 検索条件
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_GROUP_NAME", userGroupBean.getUserGroupName());
		param.put("PARA_USER_GROUP_ID", userGroupBean.getUserGroupId());
		param.put("PARA_EVENT_TYPE", eventType);
		//　DBから検索結果を取得
		return (Integer)this.sqlSessionTemplate.selectOne("userGroup.getUserGroupNm", param);
	}

	/**
	 * ユーザグループ情報登録
	 * @param userGroupBean 
	 * 
	 */
	public void insertUserGroup(UserGroupBean userGroupBean) {
		this.sqlSessionTemplate.insert("userGroup.insertUserGroup", userGroupBean);
	}

	/**
	 * ユーザグループのユーザ情報登録
	 * @param userGroupDetailBean 
	 * 
	 */
	public void insertUserGroupDetail(UserGroupDetailBean userGroupDetailBean) {
		this.sqlSessionTemplate.insert("userGroup.insertUserGroupDetail", userGroupDetailBean);
	}

	/**
	 * ユーザグループ情報を物理削除する。
	 * @param param 
	 */
	public void deleteUserGroupInfo(Map<String, Object> param) {
		// ユーザグループヘッダー情報（予約）を物理削除する。
		this.sqlSessionTemplate.delete("userGroup.deleteUserGroupHeader", param);
		// ユーザグループ明細情報情報（予約）を物理削除する。
		this.sqlSessionTemplate.delete("userGroup.deleteUserGroupDetail", param);
	}

	/**
	 * ユーザグループ情報テーブルを論理削除する。
	 * @param userId
	 * @param userGroupBean
	 */
	public void updateUserGroupInfoInvalid(String userId, UserGroupBean userGroupBean) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// ユーザグループID
		param.put("PARA_USER_GROUP_ID", userGroupBean.getUserGroupId());
		// ユーザグループ反映予定日時
		param.put("PARA_REFLECTION_SCHEDULE_DATE", userGroupBean.getUgRefDate());
		param.put("PARA_UPDATE_BY", userId);
		// ユーザグループヘッダー情報テーブルを論理削除する。
		this.sqlSessionTemplate.update("userGroup.updateUserGroupHeaderInvalid", param);
		// ユーザグループ明細情報テーブルを論理削除する。
		this.sqlSessionTemplate.update("userGroup.updateUserGroupDetailInvalid", param);
	}
}