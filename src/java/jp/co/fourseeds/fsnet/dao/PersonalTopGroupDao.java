package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.personalTopGroup.PersonalTopGroupBean;
import jp.co.fourseeds.fsnet.beans.personalTopGroup.PersonalTopGroupDetailBean;

/**
 * 個人用トップグループ情報機能Dao実装クラス
 * 
 * File Name: DeptGroupDao.java 
 * Created: 2016/02/02
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2016/02/02		    NTS        	       作成
 *
 **/
@Repository
public class PersonalTopGroupDao extends BaseDao {

	/**
	 * 個人用トップグループ情報を取る
	 * @param personalTopGroupBean　画面入力値
	 * @param strOrderBy　ソート
	 * @return　検索結果
	 */
	public List<PersonalTopGroupBean> getPersTopGroupList(PersonalTopGroupBean personalTopGroupBean, String strOrderBy, LoginUserBean loginUser) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// 個人用トップグループ名称
		param.put("PARA_TOP_GROUP_NAME", personalTopGroupBean.getSearchTopGroupName());
		// ソート
		param.put("PARA_ORDER_BY", strOrderBy);
		// 権限
		param.put("PARA_ROLE", loginUser.getRole());
		// ログインユーザID
		param.put("PARA_USER_ID", loginUser.getUserId());
		return this.sqlSessionTemplate.selectList("personalTopGroup.getPersTopGroupList", param);
	}

	/**
	 * 個人用トップグループIDによって、個人用トップグループ情報を取得する。
	 * @param personalTopGroupId　個人用トップグループID
	 * @return　検索結果
	 */
	public PersonalTopGroupBean getPersTopGroupHeader(String personalTopGroupId) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// トップグループID
		param.put("PARA_TOP_GROUP_ID", personalTopGroupId);
		return this.sqlSessionTemplate.selectOne("personalTopGroup.getPersTopGroupHeader", param);
	}

	/**
	 * 個人用トップグループIDによって、個人用トップグループ所属する有効のみユーザリスト情報を取得する。
	 * @param personalTopGroupId　個人用トップグループID
	 * @return　検索結果
	 */
	public List<PersonalTopGroupDetailBean> getPersTopGroupDetail_PersUser(String personalTopGroupId) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// トップグループID
		param.put("PARA_TOP_GROUP_ID", personalTopGroupId);
		return this.sqlSessionTemplate.selectList("personalTopGroup.getPersTopGroupDetail_PersUser", param);
	}

	/**
	 * 個人用トップグループIDによって、個人用トップグループの共有者情報を取得する。
	 * @param personalTopGroupId　個人用トップグループID
	 * @return　検索結果
	 */
	public List<PersonalTopGroupDetailBean> getPersTopGroupDetail_ShareUser(String personalTopGroupId) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// トップグループID
		param.put("PARA_TOP_GROUP_ID", personalTopGroupId);
		return this.sqlSessionTemplate.selectList("personalTopGroup.getPersTopGroupDetail_ShareUser", param);
	}
	
	/**
	 * 個人用ユーザリスト情報を論理削除する。
	 * @param userId
	 * @param personalTopGroupBean
	 */
	public void updatePersUserGroupInvalid(String userId, PersonalTopGroupBean personalTopGroupBean) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// トップグループID
		param.put("PARA_TOP_GROUP_ID", personalTopGroupBean.getTopGroupId());
		param.put("PARA_UPDATE_BY", userId);
		// トップグループ明細情報テーブルを論理削除する。
		this.sqlSessionTemplate.update("personalTopGroup.updatePersUserGroupDetailInvalid", param);
		// トップグループヘッダー情報テーブルを論理削除する。
		this.sqlSessionTemplate.update("personalTopGroup.updatePersUserGroupHeaderInvalid", param);
	}

	/**
	 * 共有対象者を論理削除する。
	 * @param userId
	 * @param personalTopGroupBean
	 */
	public void updateTopGroupShareInvalid(String userId, PersonalTopGroupBean personalTopGroupBean) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// トップグループID
		param.put("PARA_TOP_GROUP_ID", personalTopGroupBean.getTopGroupId());
		param.put("PARA_UPDATE_BY", userId);
		// トップグループヘッダー情報テーブルを論理削除する。
		this.sqlSessionTemplate.update("personalTopGroup.updateTopGroupShareInvalid", param);
	}
	
	/**
	 * トップグループの共有対象者情報登録
	 * @param shareUserBean 
	 * 
	 */
	public void insertShareUser(PersonalTopGroupDetailBean shareUserBean) {
		this.sqlSessionTemplate.insert("personalTopGroup.insertShareUser", shareUserBean);
	}

	/**
	 * 個人ユーザリスト情報を物理削除する
	 * @param param 
	 * 
	 */
	public void deletePersUserGroupInvalid(Map<String, Object> param) {
		// 個人ユーザ明細情報を物理削除する。
		this.sqlSessionTemplate.delete("personalTopGroup.deletePersUserGroupDetail", param);
		// 個人ユーザヘッダー情報を物理削除する。
		this.sqlSessionTemplate.delete("personalTopGroup.deletePersUserGroupHeader", param);
	}

	/**
	 * 共有者情報を物理削除する
	 * @param param 
	 * 
	 */
	public void deleteTopGroupShareInvalid(Map<String, Object> param) {
		// 共有者情報を物理削除する。
		this.sqlSessionTemplate.delete("personalTopGroup.deleteTopGroupShare", param);
	}
}