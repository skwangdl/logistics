package jp.co.fourseeds.fsnet.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.SubUserStoreBean;

/**
 * ユーザ情報の店舗検索ポップアップ機能Dao実装クラス
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2017/09/06		    NTS        	       新規作成
 **/
@Repository
public class SubUserStoreDao extends BaseDao{

	/**
	 * 店舗情報を取得
	 * @param param
	 * @return List
	 */
	public List<SubUserStoreBean> getStoreList(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectList("subUserStore.getStoreList", param);
	}
}
