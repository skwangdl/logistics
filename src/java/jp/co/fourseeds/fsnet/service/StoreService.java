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
 * Xάξρ@\T[rXΐNX
 * 
 *-----------------------------------------------------------
 *@Version      When            Who            Why
 *-----------------------------------------------------------
 *@1.0		2016/01/21		    NTS        	       μ¬
 *  1.1		2017/09/06			NTS			      ©Ό΅C³
 **/
@Component
public class StoreService extends BaseBusinessService{
	
	@Autowired
	private StoreDao storeDao;
	//Ου
	private final String CONSIGN_FLAG = "1";
	//συ
	private final String ACCESSION_FLAG = "2";
	//φJ
	private final String OPEN = "1";
	//\ρ
	private final String VAILED = "0";
	
	/**
	 * XάξρπζΎ
	 * @param storeBean
	 * @param strOrderBy
	 * @return υΚ
	 */
	public List<StoreBean> getStoreList(StoreBean storeBean, String strOrderBy) {
		return storeDao.getStoreList(storeBean, strOrderBy, getLoginUserBean());
	}
	
	/**
	 * XάΪΧξρπζΎ
	 * @param storeBean
	 * @return υΚ
	 */
	public StoreBean getStoreDetailInfo(StoreBean storeBean) {
		return storeDao.getStoreDetailInfo(storeBean, getLoginUserBean());
	}
	
	/**
	 * Ζ±Ου/συξρπζΎ
	 * @param storeBean
	 * @return StoreBean
	 */
	public StoreBean getConsignAccessionInfo(StoreBean storeBean) {
		//Ζ±Ου(φJ)ξρ
		Map<String, String> openConsign = storeDao.getOpenConsignAccessionInfo(storeBean, CONSIGN_FLAG);
		if (openConsign != null) {
			storeBean.setOpenConsignFlag(conversionNullToEmpty(openConsign.get("CONSIGN_FLAG")));
			storeBean.setOpenConsignStoreSet(conversionNullToEmpty(openConsign.get("CONSIGN_STORE_SET")));
			storeBean.setOpenConsignStoreEx(conversionNullToEmpty(openConsign.get("CONSIGN_STORE_EX")));
			storeBean.setOpenYconrc(conversionNullToEmpty(openConsign.get("YCONRC")));
			storeBean.setOpenName(conversionNullToEmpty(openConsign.get("NAME")));
		}

		//Ζ±Ου(\ρ)ξρ
		Map<String, String> vailedConsign = storeDao.getVailedConsignAccessionInfo(storeBean, CONSIGN_FLAG);
		if (vailedConsign != null) {
			storeBean.setVailedConsignFlag(conversionNullToEmpty(vailedConsign.get("CONSIGN_FLAG")));
			storeBean.setVailedConsignStoreSet(conversionNullToEmpty(vailedConsign.get("CONSIGN_STORE_SET")));
			storeBean.setVailedConsignStoreEx(conversionNullToEmpty(vailedConsign.get("CONSIGN_STORE_EX")));
			storeBean.setVailedYconrc(conversionNullToEmpty(vailedConsign.get("YCONRC")));
			storeBean.setVailedName(conversionNullToEmpty(vailedConsign.get("NAME")));
		}

		//Ζ±συ(φJ)ξρ
		Map<String, String> openAccession = storeDao.getOpenConsignAccessionInfo(storeBean, ACCESSION_FLAG);
		if (openAccession != null) {
			storeBean.setOpenAccessionFlag(conversionNullToEmpty(openAccession.get("CONSIGN_FLAG")));
			storeBean.setOpenAccessionStoreSet(conversionNullToEmpty(openAccession.get("CONSIGN_STORE_SET")));
			storeBean.setOpenAccessionStoreEx(conversionNullToEmpty(openAccession.get("CONSIGN_STORE_EX")));
		}

		//Ζ±συ(\ρ)ξρ
		Map<String, String> vailedAccession = storeDao.getVailedConsignAccessionInfo(storeBean, ACCESSION_FLAG);
		if (vailedAccession != null) {
			storeBean.setVailedAccessionFlag(conversionNullToEmpty(vailedAccession.get("CONSIGN_FLAG")));
			storeBean.setVailedAccessionStoreSet(conversionNullToEmpty(vailedAccession.get("CONSIGN_STORE_SET")));
			storeBean.setVailedAccessionStoreEx(conversionNullToEmpty(vailedAccession.get("CONSIGN_STORE_EX")));
		}
		return storeBean;
	}
	
	/**
	 * XάξρπXV
	 * @param storeBean
	 * @param loginUserId
	 */
	public void updateStoreMaster(StoreBean storeBean, String loginUserId, String role) {
		
		//Ουξρ
		if (ConstantContainer.USER_DIRECT.equals(storeBean.getFcFlag())) {
			Map<String, String> consignInfo = storeDao.getStoreMaster(storeBean, CONSIGN_FLAG);
			if (consignInfo == null) {
				consignInfo = new HashMap<String, String>();
				consignInfo.put("CREATE_DATE", DateUtil.getNowTime("yyyy/MM/dd HH:mm:ss"));
				consignInfo.put("CREATE_BY", loginUserId);
			}
			
			//ΟυξρOPEN
			if ("1".equals(storeBean.getOpenConsignFlag())) {
				if (!"".equals(consignInfo.get("STORE_ID")) || null != consignInfo.get("STORE_ID")) {
					//Ουξρπν
					storeDao.deleteStoreMaster(storeBean, OPEN);
				}
				//o^Ουξρ
				storeDao.insertStoreMaster(storeBean.getStoreId(), CONSIGN_FLAG, storeBean.getOpenConsignStoreSet(),
						storeBean.getOpenConsignStoreEx(), storeBean.getOpenYconrc(), consignInfo.get("CREATE_DATE"),
						consignInfo.get("CREATE_BY"), loginUserId);
			} else {
				if (!"".equals(consignInfo.get("STORE_ID")) || null != consignInfo.get("STORE_ID")) {
					//Ουξρπν
					storeDao.updateStoreFlag(storeBean, loginUserId);
				}
			}
			
			//ΟυξρVAILED
			if ("1".equals(storeBean.getVailedConsignFlag())) {
				if (!"".equals(consignInfo.get("STORE_ID")) || null != consignInfo.get("STORE_ID")) {
					//ΟυξρVAILEDπν
					storeDao.deleteStoreMaster(storeBean, VAILED);
				}
				storeDao.insertStoreMaster(storeBean.getStoreId(), CONSIGN_FLAG, storeBean.getVailedConsignStoreSet(),
						storeBean.getVailedConsignStoreEx(), storeBean.getVailedYconrc(), consignInfo.get("CREATE_DATE"),
						consignInfo.get("CREATE_BY"), loginUserId);
			} else {
				if (!"".equals(consignInfo.get("STORE_ID")) || null != consignInfo.get("STORE_ID")) {
					//ΟυξρVAILEDπν
					storeDao.deleteStoreMaster(storeBean, VAILED);
				}
			}
		}
		//συξρ
		if (ConstantContainer.USER_FC.equals(storeBean.getFcFlag())) {
			Map<String, String> accessionInfo = storeDao.getStoreMaster(storeBean, ACCESSION_FLAG);
			if (accessionInfo == null) {
				accessionInfo = new HashMap<String, String>();
				accessionInfo.put("CREATE_DATE", DateUtil.getNowTime("yyyy/MM/dd HH:mm:ss"));
				accessionInfo.put("CREATE_BY", loginUserId);
			}
			
			//συξρOPEN
			if ("1".equals(storeBean.getOpenAccessionFlag())) {
				if (!"".equals(accessionInfo.get("STORE_ID")) || null != accessionInfo.get("STORE_ID")) {
					//συξρπν
					storeDao.deleteStoreMaster(storeBean, OPEN);
				}
				//o^συξρ
				storeDao.insertStoreMaster(storeBean.getStoreId(), ACCESSION_FLAG, storeBean.getOpenAccessionStoreSet(),
						storeBean.getOpenAccessionStoreEx(), "", accessionInfo.get("CREATE_DATE"),
						accessionInfo.get("CREATE_BY"), loginUserId);
			} else {
				if (!"".equals(accessionInfo.get("STORE_ID")) || null != accessionInfo.get("STORE_ID")) {
					//συξρπν
					storeDao.updateStoreFlag(storeBean, loginUserId);
				}
			}
			
			//συξρVAILED
			if ("1".equals(storeBean.getVailedAccessionFlag())) {
				if (!"".equals(accessionInfo.get("STORE_ID")) || null != accessionInfo.get("STORE_ID")) {
					//συξρVAILEDπν
					storeDao.deleteStoreMaster(storeBean, VAILED);
				}
				storeDao.insertStoreMaster(storeBean.getStoreId(), ACCESSION_FLAG, storeBean.getVailedAccessionStoreSet(),
						storeBean.getVailedAccessionStoreEx(), "", accessionInfo.get("CREATE_DATE"),
						accessionInfo.get("CREATE_BY"), loginUserId);
			} else {
				//συξρVAILEDπν
				storeDao.deleteStoreMaster(storeBean, VAILED);
			}
		}
		
		//‘‘‘‘‘XάξρπXV
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