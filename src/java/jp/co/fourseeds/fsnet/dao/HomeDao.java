package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;
import jp.co.common.frame.util.prop.FsPropertyUtil;

import jp.co.fourseeds.fsnet.beans.HomeBean;
import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.common.util.CommonUtil;
import jp.co.fourseeds.fsnet.common.util.StringUtil;

/**
 * ホーム機能Dao実装クラス
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2015/11/24		    NTS        	       作成
 *
 **/
@Repository
public class HomeDao extends BaseDao {

	/**
	 * 対象者名称セレクトボックス情報を取る
	 * @param param 検索条件
	 * @return List 検索結果
	 */
	public List<HomeBean> getUserDivisionList(LoginUserBean loginUser) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		// 検索条件を設定
		param.put("PARA_ROLE", loginUser.getRole());
		param.put("PARA_TOP_GROUP_LIST", CommonUtil.getGroupSql(loginUser.getTopGroupList()));
		param.put("PARA_USER_ID", loginUser.getUserId());
		
		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("home.getUserDivisionList", param);
	}
	
	/**
	 * 新着情報を取る
	 * @param param 検索条件
	 * @return List 検索結果
	 */
	public List<HomeBean> getNewsList(HomeBean homeBean, String strOrderBy, LoginUserBean loginUser) {
		Map<String, Object> param = new HashMap<String, Object>();
		// 更新/新規設定日を取得
		String update_date = FsPropertyUtil.getStringProperty("update.date");
		
		// 検索条件を設定
		param.put("PARA_ROLE", loginUser.getRole());
		param.put("PARA_UPDATE_DATE", update_date);
		param.put("PARA_TOP_GROUP_LIST", CommonUtil.getGroupSql(loginUser.getTopGroupList()));
		param.put("PARA_USER_ID", loginUser.getUserId());
		param.put("PARA_USER_DIV", StringUtil.nullToBlank(homeBean.getSearchUserDivision()));	// 対象者
		param.put("PARA_ORDER_BY", strOrderBy);

		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("home.getNewsList", param);
	}
	
	/**
	 * 重要なお知らせ情報を取る
	 * @param param 検索条件
	 * @return List 検索結果
	 */
	public List<HomeBean> getInformationList(HomeBean homeBean, String strOrderBy, LoginUserBean loginUser) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		// 更新/新規設定日を取得
		String update_date = FsPropertyUtil.getStringProperty("update.date");
		
		// 検索条件を設定
		param.put("PARA_UPDATE_DATE", update_date);
		param.put("PARA_TOP_GROUP_LIST", CommonUtil.getGroupSql(loginUser.getTopGroupList()));
		param.put("PARA_USER_ID", loginUser.getUserId());
		param.put("PARA_NECESSITY_DISPLAY_STARTDATE", loginUser.getNecessityDisplayStartDate());	// 重要なお知らせ表示開始日
		param.put("PARA_READ_STATUS", homeBean.getReadStatus());
		param.put("PARA_ORDER_BY", strOrderBy);
		
		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("home.getInformationList", param);
	}
}