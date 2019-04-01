package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jp.co.common.frame.dao.BaseDao;
import jp.co.fourseeds.fsnet.beans.SubPageProxyUserFormBean;
import jp.co.fourseeds.fsnet.beans.page.ProxyUserBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;

/**
 * 承認者機能Dao実装クラス
 * 
 * Created: 2015/09/22
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2016/01/12		    NTS        	       作成
 *
 **/
@SuppressWarnings(value={"rawtypes"})
@Repository
public class SubPageProxyUserDao extends BaseDao {
	
	/**
	 * 承認者リスト取得
	 * @param formBean
	 *        フォーム
	 * @return
	 *        承認者リスト
	 * */
	public List getSubProxyUserList(SubPageProxyUserFormBean formBean)  {
		Map<String, Object> param = new HashMap<String, Object>();
		// ユーザー名称
		param.put("PARA_USER_NAME", formBean.getUserSearchName());
		param.put("PARA_COMPANY_ID", formBean.getSearchCompanyId());
		param.put("PARA_UNIFICATION_ID", formBean.getSearchUnificationId());
		param.put("PARA_BUSINESS_ID", formBean.getSearchBusinessId());
		param.put("PARA_SALES_ID", formBean.getSearchSalesId());
		param.put("PARA_SEARCH_ORGANIZATION_ID", formBean.getSearchOrganizationId());
		param.put("PARA_SEARCH_PEMPLOYEE_ID", formBean.getSearchPemployeeId());
		
		if (StringUtil.isBlank(formBean.getUserSearchName())) {
			// 画面条件【氏名 】存在しないの場合
			param.put("PARA_FLAG", "1");
		} else {
			param.put("PARA_FLAG", "0");
		}
		return this.sqlSessionTemplate.selectList("subPageProxyUser.searchSubProxyUserList", param);
	}
	
	/**
	 * ユーザ有効情報を取得
	 * @param userId
	 */
	public List<ProxyUserBean> getProxyMailList(String userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", StringUtil.isEmpty(userId)? "''" : userId);
		return this.sqlSessionTemplate.selectList("subPageProxyUser.searchProxyMailList", param);
	}
	
}