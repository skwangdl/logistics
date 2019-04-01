package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;
import jp.co.common.frame.util.prop.FsPropertyUtil;

import jp.co.fourseeds.fsnet.beans.AllPageBean;
import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.common.util.CommonUtil;

/**
 * 全コンテンツ機能Dao実装クラス
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2015/12/17		    NTS        	       作成
 *
 **/
@Repository
public class AllPageDao extends BaseDao {
	
	/**
	 * 全コンテンツ情報を取る
	 * @param param 検索条件
	 * @return List 検索結果
	 */
	public List<AllPageBean> getAllPageList(LoginUserBean loginUser, String strOrderBy) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		// 更新/新規設定日を取得
		String update_date = FsPropertyUtil.getStringProperty("update.date");
		
		// 検索条件を設定
		param.put("PARA_UPDATE_DATE", update_date);
		param.put("PARA_USER_ID", loginUser.getUserId());
		param.put("PARA_TOP_GROUP_LIST", CommonUtil.getGroupSql(loginUser.getTopGroupList()));
		param.put("PARA_ROLE", loginUser.getRole());
		param.put("PARA_ORDER_BY", strOrderBy);

		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("allPage.getAllPageList", param);
	}
}