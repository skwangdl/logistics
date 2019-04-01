package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jp.co.common.frame.dao.BaseDao;

/**
 * 公開するグループ機能Dao実装クラス
 * 
 * Created: 2016/01/12
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
public class SubPageAuthGroupDao extends BaseDao {
	
	/**
	 * トップグループ情報を取得
	 * @param topGroupName
	 *        グループ名称
	 * @return
	 *        グループリスト
	 */
	public List searchCommTopGroupList(String topGroupName)  {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TOP_GROUP_NAME", topGroupName);
		return this.sqlSessionTemplate.selectList("subPageAuthGroup.searchCommTopGroupList", param);
	}
	
	/**
	 * テンプレート個人用グループ
	 * @param groupName
	 *        グループ名称
	 * @param currentUserId
	 *        ログインユーザー
	 * @param role
	 *        役割
	 * @return
	 *        グループリスト
	 */
	public List searchPersTopGroupList(String groupName, String currentUserId, String role) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TOP_GROUP_NAME", groupName);
		param.put("PARA_CURRENT_USER_ID", currentUserId);
		param.put("PARA_ROLE", role);
		return this.sqlSessionTemplate.selectList("subPageAuthGroup.searchPersTopGroupList", param);
	}
	
}