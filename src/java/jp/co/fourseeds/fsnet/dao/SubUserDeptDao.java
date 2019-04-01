package jp.co.fourseeds.fsnet.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.SubUserDeptBean;

/**
 * ユーザ情報の本部検索ポップアップ機能Dao実装クラス
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2017/09/06		    NTS        	       新規作成
 **/
@Repository
public class SubUserDeptDao extends BaseDao{

	/**
	 * 本部情報を取得
	 * @param param
	 * @return List
	 */
	public List<SubUserDeptBean> getDeptList(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectList("subUserDept.getDeptList", param);
	}
}
