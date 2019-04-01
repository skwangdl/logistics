package jp.co.fourseeds.fsnet.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.fourseeds.fsnet.beans.StoreBean;
import jp.co.fourseeds.fsnet.common.ConstantContainer;
import jp.co.fourseeds.fsnet.dao.StoreDao;

import jp.co.common.frame.service.BaseBusinessService;
import jp.co.common.frame.util.DateUtil;

/**
 * 店舗情報機能サービス実装クラス
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2016/01/21		    NTS        	       作成
 *  1.1		2017/09/06			NTS			      見直し修正
 **/
@Component
public class StoreService extends BaseBusinessService{
	
	@Autowired
	private StoreDao storeDao;
	//委託
	private final String CONSIGN_FLAG = "1";
	//受託
	private final String ACCESSION_FLAG = "2";
	//公開
	private final String OPEN = "1";
	//予約
	private final String VAILED = "0";
	
	/**
	 * 店舗情報を取得
	 * @param storeBean
	 * @param strOrderBy
	 * @return 検索結果
	 */
	public List<StoreBean> getStoreList(StoreBean storeBean, String strOrderBy) {
		return storeDao.getStoreList(storeBean, strOrderBy, getLoginUserBean());
	}
	
	/**
	 * 店舗詳細情報を取得
	 * @param storeBean
	 * @return 検索結果
	 */
	public StoreBean getStoreDetailInfo(StoreBean storeBean) {
		return storeDao.getStoreDetailInfo(storeBean, getLoginUserBean());
	}
	
	/**
	 * 業務委託/受託情報を取得
	 * @param storeBean
	 * @return StoreBean
	 */
	public StoreBean getConsignAccessionInfo(StoreBean storeBean) {
		//業務委託(公開)情報
		Map<String, String> openConsign = storeDao.getOpenConsignAccessionInfo(storeBean, CONSIGN_FLAG);
		if (openConsign != null) {
			storeBean.setOpenConsignFlag(conversionNullToEmpty(openConsign.get("CONSIGN_FLAG")));
			storeBean.setOpenConsignStoreSet(conversionNullToEmpty(openConsign.get("CONSIGN_STORE_SET")));
			storeBean.setOpenConsignStoreEx(conversionNullToEmpty(openConsign.get("CONSIGN_STORE_EX")));
			storeBean.setOpenYconrc(conversionNullToEmpty(openConsign.get("YCONRC")));
			storeBean.setOpenName(conversionNullToEmpty(openConsign.get("NAME")));
		}

		//業務委託(予約)情報
		Map<String, String> vailedConsign = storeDao.getVailedConsignAccessionInfo(storeBean, CONSIGN_FLAG);
		if (vailedConsign != null) {
			storeBean.setVailedConsignFlag(conversionNullToEmpty(vailedConsign.get("CONSIGN_FLAG")));
			storeBean.setVailedConsignStoreSet(conversionNullToEmpty(vailedConsign.get("CONSIGN_STORE_SET")));
			storeBean.setVailedConsignStoreEx(conversionNullToEmpty(vailedConsign.get("CONSIGN_STORE_EX")));
			storeBean.setVailedYconrc(conversionNullToEmpty(vailedConsign.get("YCONRC")));
			storeBean.setVailedName(conversionNullToEmpty(vailedConsign.get("NAME")));
		}

		//業務受託(公開)情報
		Map<String, String> openAccession = storeDao.getOpenConsignAccessionInfo(storeBean, ACCESSION_FLAG);
		if (openAccession != null) {
			storeBean.setOpenAccessionFlag(conversionNullToEmpty(openAccession.get("CONSIGN_FLAG")));
			storeBean.setOpenAccessionStoreSet(conversionNullToEmpty(openAccession.get("CONSIGN_STORE_SET")));
			storeBean.setOpenAccessionStoreEx(conversionNullToEmpty(openAccession.get("CONSIGN_STORE_EX")));
		}

		//業務受託(予約)情報
		Map<String, String> vailedAccession = storeDao.getVailedConsignAccessionInfo(storeBean, ACCESSION_FLAG);
		if (vailedAccession != null) {
			storeBean.setVailedAccessionFlag(conversionNullToEmpty(vailedAccession.get("CONSIGN_FLAG")));
			storeBean.setVailedAccessionStoreSet(conversionNullToEmpty(vailedAccession.get("CONSIGN_STORE_SET")));
			storeBean.setVailedAccessionStoreEx(conversionNullToEmpty(vailedAccession.get("CONSIGN_STORE_EX")));
		}
		return storeBean;
	}
	
	/**
	 * 店舗情報を更新
	 * @param storeBean
	 * @param loginUserId
	 */
	public void updateStoreMaster(StoreBean storeBean, String loginUserId, String role) {
		
		//委託情報
		if (ConstantContainer.USER_DIRECT.equals(storeBean.getFcFlag())) {
			Map<String, String> consignInfo = storeDao.getStoreMaster(storeBean, CONSIGN_FLAG);
			if (consignInfo == null) {
				consignInfo = new HashMap<String, String>();
				consignInfo.put("CREATE_DATE", DateUtil.getNowTime("yyyy/MM/dd HH:mm:ss"));
				consignInfo.put("CREATE_BY", loginUserId);
			}
			
			//委託情報OPEN
			if ("1".equals(storeBean.getOpenConsignFlag())) {
				if (!"".equals(consignInfo.get("STORE_ID")) || null != consignInfo.get("STORE_ID")) {
					//委託情報を削除
					storeDao.deleteStoreMaster(storeBean, OPEN);
				}
				//登録委託情報
				storeDao.insertStoreMaster(storeBean.getStoreId(), CONSIGN_FLAG, storeBean.getOpenConsignStoreSet(),
						storeBean.getOpenConsignStoreEx(), storeBean.getOpenYconrc(), consignInfo.get("CREATE_DATE"),
						consignInfo.get("CREATE_BY"), loginUserId);
			} else {
				if (!"".equals(consignInfo.get("STORE_ID")) || null != consignInfo.get("STORE_ID")) {
					//委託情報を削除
					storeDao.updateStoreFlag(storeBean, loginUserId);
				}
			}
			
			//委託情報VAILED
			if ("1".equals(storeBean.getVailedConsignFlag())) {
				if (!"".equals(consignInfo.get("STORE_ID")) || null != consignInfo.get("STORE_ID")) {
					//委託情報VAILEDを削除
					storeDao.deleteStoreMaster(storeBean, VAILED);
				}
				storeDao.insertStoreMaster(storeBean.getStoreId(), CONSIGN_FLAG, storeBean.getVailedConsignStoreSet(),
						storeBean.getVailedConsignStoreEx(), storeBean.getVailedYconrc(), consignInfo.get("CREATE_DATE"),
						consignInfo.get("CREATE_BY"), loginUserId);
			} else {
				if (!"".equals(consignInfo.get("STORE_ID")) || null != consignInfo.get("STORE_ID")) {
					//委託情報VAILEDを削除
					storeDao.deleteStoreMaster(storeBean, VAILED);
				}
			}
		}
		//受託情報
		if (ConstantContainer.USER_FC.equals(storeBean.getFcFlag())) {
			Map<String, String> accessionInfo = storeDao.getStoreMaster(storeBean, ACCESSION_FLAG);
			if (accessionInfo == null) {
				accessionInfo = new HashMap<String, String>();
				accessionInfo.put("CREATE_DATE", DateUtil.getNowTime("yyyy/MM/dd HH:mm:ss"));
				accessionInfo.put("CREATE_BY", loginUserId);
			}
			
			//受託情報OPEN
			if ("1".equals(storeBean.getOpenAccessionFlag())) {
				if (!"".equals(accessionInfo.get("STORE_ID")) || null != accessionInfo.get("STORE_ID")) {
					//受託情報を削除
					storeDao.deleteStoreMaster(storeBean, OPEN);
				}
				//登録受託情報
				storeDao.insertStoreMaster(storeBean.getStoreId(), ACCESSION_FLAG, storeBean.getOpenAccessionStoreSet(),
						storeBean.getOpenAccessionStoreEx(), "", accessionInfo.get("CREATE_DATE"),
						accessionInfo.get("CREATE_BY"), loginUserId);
			} else {
				if (!"".equals(accessionInfo.get("STORE_ID")) || null != accessionInfo.get("STORE_ID")) {
					//受託情報を削除
					storeDao.updateStoreFlag(storeBean, loginUserId);
				}
			}
			
			//受託情報VAILED
			if ("1".equals(storeBean.getVailedAccessionFlag())) {
				if (!"".equals(accessionInfo.get("STORE_ID")) || null != accessionInfo.get("STORE_ID")) {
					//受託情報VAILEDを削除
					storeDao.deleteStoreMaster(storeBean, VAILED);
				}
				storeDao.insertStoreMaster(storeBean.getStoreId(), ACCESSION_FLAG, storeBean.getVailedAccessionStoreSet(),
						storeBean.getVailedAccessionStoreEx(), "", accessionInfo.get("CREATE_DATE"),
						accessionInfo.get("CREATE_BY"), loginUserId);
			} else {
				//受託情報VAILEDを削除
				storeDao.deleteStoreMaster(storeBean, VAILED);
			}
		}
		
		//■■■■■店舗情報を更新
		storeDao.updateStoreMaster(storeBean,role,loginUserId);
	}
	
	/**
	 * @function Conversion the null to string Empty
	 * @param Object
	 * @return String
	 */
	private static String conversionNullToEmpty(Object o) {
		if (o == null || "".equals(o) || "null".equals(o)) {
			o = "";
		}
		
		return o.toString();
	} 
}