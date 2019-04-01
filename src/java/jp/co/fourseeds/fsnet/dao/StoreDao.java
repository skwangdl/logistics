package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.StoreBean;

/**
 * 店舗情報機能Dao実装クラス
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2016/01/21		    NTS        	       作成
 * 1.1		2017/09/06			NTS			     見直し修正
 **/
@Repository
public class StoreDao extends BaseDao {
	
	/**
	 * 店舗情報を取得
	 * @param storeBean
	 * @param strOrderBy
	 * @return List
	 */
	public List<StoreBean> getStoreList(StoreBean storeBean, String strOrderBy, LoginUserBean loginUser) {
		// 検索条件を設定
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_ROLE", loginUser.getRole());
		param.put("PARA_ORDER_BY", strOrderBy); 
		param.put("PARA_STORE_ID",storeBean.getSearchStoreId());
		param.put("PARA_STORE_NAME", storeBean.getSearchStoreName());
		param.put("PARA_COMPANY_ID",storeBean.getSearchCompanyId());
		param.put("PARA_UNIFICATION_ID", storeBean.getSearchUnificationId());
		param.put("PARA_BUSINESS_ID", storeBean.getSearchBusinessId());
		param.put("PARA_SALES_ID",storeBean.getSearchSalesId());
		param.put("PARA_SHOW_FLAG",storeBean.getShowFlag());
		param.put("PARA_FCFLAG",storeBean.getFcFlag());
		param.put("PARA_OTHER_FLAG",storeBean.getOtherFlag());
		param.put("PARA_SUPERVISOR_NAME",storeBean.getSupervisorName());
		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("store.getStoreList", param);
	}
	
	/**
	 * 店舗詳細情報を取得
	 * @param storeBean
	 * @return StoreBean
	 */
	public StoreBean getStoreDetailInfo(StoreBean storeBean, LoginUserBean loginUser) {
		// 検索条件を設定
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_STORE_ID", storeBean.getStoreId());
		param.put("PARA_ROLE", loginUser.getRole());
		
		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectOne("store.getStoreDetailInfo", param);
	}
	
	/**
	 * 業務委託受託公開情報を取得
	 * @param storeBean
	 * @return 検索結果
	 */
	public Map<String, String> getOpenConsignAccessionInfo(StoreBean storeBean, String consignAccessionFlag) {
		// 検索条件を設定
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_STORE_ID", storeBean.getStoreId());
		param.put("PARA_CONSIGN_ACCESSION_FLAG", consignAccessionFlag);
		
		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectOne("store.getOpenConsignAccessionInfo", param);
	}
	
	/**
	 * 業務委託受託予約情報を取得
	 * @param storeBean
	 * @return 検索結果
	 */
	public Map<String, String> getVailedConsignAccessionInfo(StoreBean storeBean, String consignAccessionFlag) {
		// 検索条件を設定
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_STORE_ID", storeBean.getStoreId());
		param.put("PARA_CONSIGN_ACCESSION_FLAG", consignAccessionFlag);
		
		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectOne("store.getVailedConsignAccessionInfo", param);
	}
	
	/**
	 * 店舗情報を取得
	 * @param storeBean
	 */
	public Map<String, String> getStoreMaster(StoreBean storeBean, String consignAccessionFlag) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_STORE_ID", storeBean.getStoreId());
		param.put("PARA_CONSIGN_ACCESSION_FLAG", consignAccessionFlag);
		
		return this.sqlSessionTemplate.selectOne("store.getStoreMaster", param);
	}
	
	/**
	 * 店舗情報を削除
	 * @param storeBean
	 */
	public void deleteStoreMaster(StoreBean storeBean, String openVailedFlag) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_STORE_ID", storeBean.getStoreId());
		param.put("PARA_OPEN_VAILED_FLAG", openVailedFlag);
		this.sqlSessionTemplate.delete("store.deleteStoreMaster", param);
	}
	
	
	/**
	 * 店舗情報を倫理削除
	 * @param storeBean
	 */
	public void updateStoreFlag(StoreBean storeBean, String userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_STORE_ID", storeBean.getStoreId());
		param.put("PARA_UPDATE_BY", userId);
		this.sqlSessionTemplate.delete("store.updateStoreFlag", param);
	}
	
	/**
	 * 店舗情報を更新
	 * @param storeBean
	 */
	public void updateStoreMaster(StoreBean storeBean, String role, String userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_SHOW_FLAG", storeBean.getShowFlag());
		param.put("PARA_UPDATE_BY", userId);
		param.put("PARA_STORE_ID", storeBean.getStoreId());
		param.put("PARA_ROLE", role);
		this.sqlSessionTemplate.update("store.updateStoreMaster", param);
	}
	
	/**
	 * 店舗情報を登録
	 * @param storeId 店舗ID
	 * @param consignAccessionFlag 委託受託区分
	 * @param storeSet 有効開始日
	 * @param storeEx 有効終了日
	 * @param yconrc オーナーコード
	 * @param createDate 作成日時
	 * @param createBy 作成ユーザID
	 * @param updateBy 更新者
	 */
	public void insertStoreMaster(String storeId, String consignAccessionFlag, String storeSet, String storeEx,
			String yconrc, String createDate, String createBy, String updateBy) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_STORE_ID", storeId);
		param.put("PARA_CONSIGN_ACCESSION_FLAG", consignAccessionFlag);
		param.put("PARA_STORE_SET", storeSet);
		param.put("PARA_STORE_EX", storeEx);
		param.put("PARA_YCONRC", yconrc);
		param.put("PARA_CREATE_DATE", createDate);
		param.put("PARA_CREATE_BY", createBy);
		param.put("PARA_UPDATE_BY", updateBy);

		this.sqlSessionTemplate.insert("store.insertStoreMaster", param);
	}
}