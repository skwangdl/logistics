package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.storeGroup.StoreGroupBean;
import jp.co.fourseeds.fsnet.beans.storeGroup.StoreGroupDetailBean;

/**
 * 店舗グループ情報機能Dao実装クラス
 * 
 * File Name: StoreGroupDao.java 
 * Created: 2016/01/08
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2016/01/08		    NTS        	       作成
 * 1.1		2017/12/05			NTS			見直し修正
 *
 **/
@Repository
public class StoreGroupDao extends BaseDao {

	/**
	 *  店舗グループ名称によって、店舗グループ情報を取る
	 * @param loginUser ユーザ
	 * @param searchSql　検索sql
	 * @param storeGroupBean　画面入力値
	 * @param strOrderBy　ソート
	 * @return　検索結果
	 */
	public List<StoreGroupBean> getStoreGroupList(String searchSql, StoreGroupBean storeGroupBean, String strOrderBy, LoginUserBean loginUser) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// 店舗グループ名称
		param.put("PARA_STORE_GROUP_NAME", storeGroupBean.getSearchStoreGroupName());
		// 権限
		param.put("PARA_ROLE", loginUser.getRole());
		// ログインユーザID
		param.put("PARA_USER_ID", loginUser.getUserId());
		// ソート
		param.put("PARA_ORDER_BY", strOrderBy);
		return this.sqlSessionTemplate.selectList(searchSql, param);
	}
	
	/**
	 * 店舗グループIDと検索テーブルによって、店舗グループ情報を取得する。
	 * @param storeGroupId　店舗グループID
	 * @param storeGroupTable　検索店舗グループテーブル
	 * @return　検索結果
	 */
	public StoreGroupBean getStoreGroupHeader(String storeGroupId, String storeGroupTable) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// 店舗グループID
		param.put("PARA_STORE_GROUP_ID", storeGroupId);
		// 店舗グループ情報検索テーブル
		param.put("PARA_STORE_GROUP_TABLE", storeGroupTable);
		return this.sqlSessionTemplate.selectOne("storeGroup.getStoreGroupHeader", param);
	}
	
	/**
	 * 店舗グループIDと検索テーブルによって、店舗グループ所属する有効のみ店舗情報を取得する。
	 * @param storeGroupId　店舗グループID
	 * @param storeGroupTable　検索店舗グループテーブル
	 * @param storeFcDisplay　店舗区分名称（FC、直営）「Y:名称表示」
	 * @return　検索結果
	 */
	public List<StoreGroupDetailBean> getStoreGroupDetail(String storeGroupId, String storeGroupTable, String storeFcDisplay) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// 店舗グループID
		param.put("PARA_STORE_GROUP_ID", storeGroupId);
		// 店舗グループ情報検索テーブル
		param.put("PARA_STORE_GROUP_TABLE", storeGroupTable);
		// 店舗FC区分名
		param.put("PARA_STORE_FC_DISPLAY", storeFcDisplay);
		return this.sqlSessionTemplate.selectList("storeGroup.getStoreGroupDetail", param);
	}
	
	/**
	 * 店舗グループIDと検索テーブルによって、店舗グループの店舗情報を取得する。
	 * @param storeGroupId　店舗グループID
	 * @return　検索結果
	 */
	public List<StoreGroupDetailBean> getStoreDetail(String storeGroupId, String storeGroupTable, String storeFcDisplay) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// 店舗グループID
		param.put("PARA_STORE_GROUP_ID", storeGroupId);
		// 店舗グループ情報検索テーブル
		param.put("PARA_STORE_GROUP_TABLE", storeGroupTable);
		// 店舗FC区分名
		param.put("PARA_STORE_FC_DISPLAY", storeFcDisplay);
		return this.sqlSessionTemplate.selectList("storeGroup.getStoreDetail", param);
	}
	
	/**
	 * 店舗グループIDと検索テーブルによって、店舗グループ所属する有効のみ共有対象者情報を取得する。
	 * @param sgRefDate 
	 * @param storeGroupId　店舗グループID
	 * @return　検索結果
	 */
	public List<StoreGroupDetailBean> getShareUserList(String storeGroupId, String sgRefDate) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// 店舗グループID
		param.put("PARA_STORE_GROUP_ID", storeGroupId);
		// 反映予定日
		param.put("PARA_REFLECTION_SCHEDULE_DATE", sgRefDate);
		return this.sqlSessionTemplate.selectList("storeGroup.getShareUserList", param);
	}
	
	/**
	 * 店舗グループIDを採番する
	 * 
	 */
	public String getNewStoreGroupId() {
		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectOne("storeGroup.getNewStoreGroupId");
	}

	/**
	 * 登録済データとの店舗グループ名称重複データを取得
	 * @param storeGroupBean 
	 */
	public int getStoreGroupNmCheckInfo(StoreGroupBean storeGroupBean, String eventType) {
		// 検索条件
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_STORE_GROUP_NAME", storeGroupBean.getStoreGroupName());
		param.put("PARA_STORE_GROUP_ID", storeGroupBean.getStoreGroupId());
		param.put("PARA_EVENT_TYPE", eventType);
		//　DBから検索結果を取得
		return (Integer)this.sqlSessionTemplate.selectOne("storeGroup.getStoreGroupNm", param);
	}

	/**
	 * 店舗グループ情報登録
	 * @param storeGroupBean 
	 * 
	 */
	public void insertStoreGroup(StoreGroupBean storeGroupBean) {
		this.sqlSessionTemplate.insert("storeGroup.insertStoreGroup", storeGroupBean);
	}

	/**
	 * 店舗グループの店舗情報登録
	 * @param storeGroupDetailBean 
	 * 
	 */
	public void insertStoreGroupDetail(StoreGroupDetailBean storeGroupDetailBean) {
		this.sqlSessionTemplate.insert("storeGroup.insertStoreGroupDetail", storeGroupDetailBean);
	}
	
	/**
	 * 店舗グループの共有対象者情報登録
	 * @param shareUserBean 
	 * 
	 */
	public void insertShareUser(StoreGroupDetailBean shareUserBean) {
		this.sqlSessionTemplate.insert("storeGroup.insertShareUser", shareUserBean);
	}

	/**
	 * 店舗グループ情報を物理削除する。
	 * @param param 
	 */
	public void deleteStoreGroupInfo(Map<String, Object> param) {
		// 店舗グループヘッダー情報（予約）を物理削除する。
		this.sqlSessionTemplate.delete("storeGroup.deleteStoreGroupHeader", param);
		// 店舗グループ明細情報情報（予約）を物理削除する。
		this.sqlSessionTemplate.delete("storeGroup.deleteStoreGroupDetail", param);
		// 店舗グループ共有対象者（予約）を物理削除する。
		this.sqlSessionTemplate.delete("storeGroup.deleteStoreGroupShareUser", param);
	}

	/**
	 * 店舗グループ情報テーブルを論理削除する。
	 * @param userId
	 * @param storeGroupBean
	 */
	public void updateStoreGroupInfoInvalid(String userId, StoreGroupBean storeGroupBean) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// 店舗グループID
		param.put("PARA_STORE_GROUP_ID", storeGroupBean.getStoreGroupId());
		// 店舗グループ反映予定日時
		param.put("PARA_REFLECTION_SCHEDULE_DATE", storeGroupBean.getSgRefDate());
		// ユーザID
		param.put("PARA_UPDATE_BY", userId);
		// 店舗グループヘッダー情報テーブルを論理削除する。
		this.sqlSessionTemplate.update("storeGroup.updateStoreGroupHeaderInvalid", param);
		// 店舗グループ明細情報テーブルを論理削除する。
		this.sqlSessionTemplate.update("storeGroup.updateStoreGroupDetailInvalid", param);
		// 店舗グループ共有対象者（予約）を論理削除する。
		this.sqlSessionTemplate.update("storeGroup.updateStoreGroupShareUserInvalid", param);
	}

	/**
	 * 店舗検索を実行（組織）
	 * @param param 
	 */
	public List<StoreGroupDetailBean> getStoreList_left(Map<String, Object> param) {
		// DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("storeGroup.getStoreList_left", param);
	}
	
	/**
	 * 店舗検索を実行
	 * @param param 
	 */
	public List<StoreGroupDetailBean> getStoreList_right(Map<String, Object> param) {
		// DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("storeGroup.getStoreList_right", param);
	}
	
	/**
	 * 部門店舗グループ所属人数情報登録
	 * @param param 
	 */
	public void insertDepartmentStoreGroupUser(StoreGroupBean storeGroupBean) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// 店舗グループID
		param.put("PARA_STORE_GROUP_ID", storeGroupBean.getStoreGroupId());
		this.sqlSessionTemplate.insert("storeGroup.insertDepartmentStoreGroupUser", param);
	}
	
	/**
	 * 部門店舗グループ所属人数情報削除
	 * @param param 
	 */
	public void deleteDepartmentStoreGroupUser(StoreGroupBean storeGroupBean) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// 店舗グループID
		param.put("PARA_STORE_GROUP_ID", storeGroupBean.getStoreGroupId());
		this.sqlSessionTemplate.delete("storeGroup.deleteDepartmentStoreGroupUser", param);
	}
	
	/**
	 * 部署リンクをクリック、階層レベル検索
	 * @param param
	 * @return　検索結果
	 */
	public String getDeptLevel(Map<String, Object> param) {
		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectOne("storeGroup.getDeptLevel", param);
	}
	
	/**
	 * 会社リンクをクリック、所属統括検索
	 * @param param
	 * @return　検索結果
	 */
	public List<Map<String, String>> getSecondDeptList(Map<String, Object> param) {
		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("storeGroup.getSecondDeptList", param);
	}
	
	/**
	 * 統括リンクをクリック、所属事業検索
	 * @param param
	 * @return　検索結果
	 */
	public List<Map<String, String>> getThirdDeptList(Map<String, Object> param) {
		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("storeGroup.getThirdDeptList", param);
	}
	
	/**
	 * 事業リンクをクリック、所属営業部検索
	 * @param param
	 * @return　検索結果
	 */
	public List<Map<String, String>> getFourthDeptList(Map<String, Object> param) {
		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("storeGroup.getFourthDeptList", param);
	}
	
	/**
	 * 営業部リンクをクリック、所属店舗検索
	 * @param param
	 * @return　検索結果
	 */
	public List<Map<String, String>> getFifthDeptList(Map<String, Object> param) {
		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("storeGroup.getFifthDeptList", param);
	}
}