package jp.co.fourseeds.fsnet.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.common.frame.service.BaseBusinessService;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.SubUserStoreBean;
import jp.co.fourseeds.fsnet.common.ConstantContainer;
import jp.co.fourseeds.fsnet.dao.SubUserStoreDao;

/**
 * ユーザ情報の店舗検索ポップアップ機能サービス実装クラス
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2017/09/06		    NTS        	       新規作成
 *
 **/
@Component
public class SubUserStoreService extends BaseBusinessService{
	@Autowired
	private SubUserStoreDao subUserStoreDao;
	
	/**
	 * 店舗情報を取得
	 * @param SubUserStoreBean
	 * @param strOrderBy
	 * @return 検索結果
	 */
	public List<SubUserStoreBean> getStoreList(SubUserStoreBean bean, String strOrderBy, LoginUserBean LoginUserBean) {
		// 検索条件を設定
		Map<String, Object> param = new HashMap<String, Object>();
		// システム利用区分
		param.put("PARA_LOGIN_ROLE", LoginUserBean.getRole());
		// 店舗コード
		param.put("PARA_STORE_ID", bean.getSearchStoreId());
		//店舗名
		param.put("PARA_STORE_NAME", bean.getSearchStoreName());
		// 会社コード
		param.put("PARA_COMPANY_ID", bean.getSearchCompanyId());
		// 統括コード
		param.put("PARA_UNIFICATION_ID", bean.getSearchUnificationId());
		// 事業コード
		param.put("PARA_BUSINESS_ID", bean.getSearchBusinessId());
		// 営業部コード
		param.put("PARA_SALES_ID", bean.getSearchSalesId());
		// 店舗区分
		param.put("PARA_FC_FLAG", bean.getSearchFCFlag());
		// 組織区分:直営
		param.put("PARA_USER_DIRECT", ConstantContainer.USER_DIRECT);
		// ソート条件
		param.put("PARA_ORDER_BY", strOrderBy);
		
		return subUserStoreDao.getStoreList(param);
	}
}
