package jp.co.fourseeds.fsnet.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.common.frame.service.BaseBusinessService;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.SubUserDeptBean;
import jp.co.fourseeds.fsnet.dao.SubUserDeptDao;

/**
 * ユーザ情報の所属検索ポップアップ機能サービス実装クラス
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2017/09/06		    NTS        	       新規作成
 *
 **/
@Component
public class SubUserDeptService extends BaseBusinessService{
	@Autowired
	private SubUserDeptDao subUserDeptDao;
	
	/**
	 * 本部情報を取得
	 * @param SubUserDeptBean
	 * @param strOrderBy
	 * @return 検索結果
	 */
	public List<SubUserDeptBean> getStoreList(SubUserDeptBean bean, String strOrderBy, LoginUserBean LoginUserBean) {
		// 検索条件を設定
		Map<String, Object> param = new HashMap<String, Object>();
		// 所属コード
		param.put("PARA_DEPT_ID", bean.getSearchDeptId());
		// 所属名
		param.put("PARA_DEPT_NAME", bean.getSearchDeptName());
		// 会社コード
		param.put("PARA_COMPANY_ID", bean.getSearchCompanyId());
		// 統括コード
		param.put("PARA_UNIFICATION_ID", bean.getSearchUnificationId());
		// 事業コード
		param.put("PARA_BUSINESS_ID", bean.getSearchBusinessId());
		// ソート条件
		param.put("PARA_ORDER_BY", strOrderBy);
		
		return subUserDeptDao.getDeptList(param);
	}
}
