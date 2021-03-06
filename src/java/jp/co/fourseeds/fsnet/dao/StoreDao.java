package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.StoreBean;

/**
 * XÜîñ@\DaoÀNX
 * 
 *-----------------------------------------------------------
 *@Version      When            Who            Why
 *-----------------------------------------------------------
 *@1.0		2016/01/21		    NTS        	       ì¬
 * 1.1		2017/09/06			NTS			     ©¼µC³
 **/
@Repository
public class StoreDao extends BaseDao {
	
	/**
	 * XÜîñðæ¾
	 * @param storeBean
	 * @param strOrderBy
	 * @return List
	 */
	public List<StoreBean> getStoreList(StoreBean storeBean, String strOrderBy, LoginUserBean loginUser) {
		// õððÝè
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
		//@DB©çõÊðæ¾
		return this.sqlSessionTemplate.selectList("store.getStoreList", param);
	}
	
	/**
	 * XÜÚ×îñðæ¾
	 * @param storeBean
	 * @return StoreBean
	 */
	public StoreBean getStoreDetailInfo(StoreBean storeBean, LoginUserBean loginUser) {
		// õððÝè
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_STORE_ID", storeBean.getStoreId());
		param.put("PARA_ROLE", loginUser.getRole());
		
		//@DB©çõÊðæ¾
		return this.sqlSessionTemplate.selectOne("store.getStoreDetailInfo", param);
	}
	
	/**
	 * Æ±ÏõóõöJîñðæ¾
	 * @param storeBean
	 * @return õÊ
	 */
	public Map<String, String> getOpenConsignAccessionInfo(StoreBean storeBean, String consignAccessionFlag) {
		// õððÝè
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_STORE_ID", storeBean.getStoreId());
		param.put("PARA_CONSIGN_ACCESSION_FLAG", consignAccessionFlag);
		
		//@DB©çõÊðæ¾
		return this.sqlSessionTemplate.selectOne("store.getOpenConsignAccessionInfo", param);
	}
	
	/**
	 * Æ±Ïõóõ\ñîñðæ¾
	 * @param storeBean
	 * @return õÊ
	 */
	public Map<String, String> getVailedConsignAccessionInfo(StoreBean storeBean, String consignAccessionFlag) {
		// õððÝè
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_STORE_ID", storeBean.getStoreId());
		param.put("PARA_CONSIGN_ACCESSION_FLAG", consignAccessionFlag);
		
		//@DB©çõÊðæ¾
		return this.sqlSessionTemplate.selectOne("store.getVailedConsignAccessionInfo", param);
	}
	
	/**
	 * XÜîñðæ¾
	 * @param storeBean
	 */
	public Map<String, String> getStoreMaster(StoreBean storeBean, String consignAccessionFlag) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_STORE_ID", storeBean.getStoreId());
		param.put("PARA_CONSIGN_ACCESSION_FLAG", consignAccessionFlag);
		
		return this.sqlSessionTemplate.selectOne("store.getStoreMaster", param);
	}
	
	/**
	 * XÜîñðí
	 * @param storeBean
	 */
	public void deleteStoreMaster(StoreBean storeBean, String openVailedFlag) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_STORE_ID", storeBean.getStoreId());
		param.put("PARA_OPEN_VAILED_FLAG", openVailedFlag);
		this.sqlSessionTemplate.delete("store.deleteStoreMaster", param);
	}
	
	
	/**
	 * XÜîñðÏí
	 * @param storeBean
	 */
	public void updateStoreFlag(StoreBean storeBean, String userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_STORE_ID", storeBean.getStoreId());
		param.put("PARA_UPDATE_BY", userId);
		this.sqlSessionTemplate.delete("store.updateStoreFlag", param);
	}
	
	/**
	 * XÜîñðXV
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
	 * XÜîñðo^
	 * @param storeId XÜID
	 * @param consignAccessionFlag Ïõóõæª
	 * @param storeSet LøJnú
	 * @param storeEx LøI¹ú
	 * @param yconrc I[i[R[h
	 * @param createDate ì¬ú
	 * @param createBy ì¬[UID
	 * @param updateBy XVÒ
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