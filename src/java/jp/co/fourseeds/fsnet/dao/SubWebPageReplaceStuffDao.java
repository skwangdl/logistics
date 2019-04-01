package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.SubWebPageReplaceStuffBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;

/**
 * サブ画面(Web担当者ID差し替え_社員情報設定)機能Dao実装クラス
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 * 1.1      2017/09/11          NTS            見直し対応
 * 
 **/
@Repository
public class SubWebPageReplaceStuffDao extends BaseDao {

	public List<SubWebPageReplaceStuffBean> getStuffList(SubWebPageReplaceStuffBean stuffBean, String strOrderBy, String popFromFlag) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// 検索氏名 
		param.put("PARA_USER_NAME", stuffBean.getSearchUserName());
		// 会社ID
		param.put("PARA_COMPANY_ID", stuffBean.getConditionCompanyId());
		// 統括ID
		param.put("PARA_UNIFICATION_ID", stuffBean.getConditionUnificationId());
		// 事業部ID
		param.put("PARA_BUSINESS_ID", stuffBean.getConditionBusinessId());
		// 営業部ID
		param.put("PARA_SALES_ID", stuffBean.getConditionSalesId());
		
		if (StringUtil.isBlank(stuffBean.getSearchUserName())) {
			// 画面条件【氏名 】存在しないの場合
			param.put("PARA_FLAG", "1");
		} else {
			param.put("PARA_FLAG", "0");
		}
		return this.sqlSessionTemplate.selectList("subWebPageReplaceStuff.getReplaceList", param);
		
	}
}