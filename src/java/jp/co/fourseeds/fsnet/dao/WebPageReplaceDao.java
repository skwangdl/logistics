package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.WebPageReplaceBean;
/**
 * Web担当者ID差し替え情報機能Dao実装クラス
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 * 1.1      2017/09/11          NTS            見直し対応
 * 
 **/
@Repository
public class WebPageReplaceDao extends BaseDao {

	/**
	 * 社員コンテンツ関連情報を取得
	 * @param webPageReplaceBean
	 * @param strOrderBy
	 * @return List
	 */
	public List<WebPageReplaceBean> getPageList(WebPageReplaceBean webPageReplaceBean, String strOrderBy) {
		// 検索条件を設定
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID",webPageReplaceBean.getSearchUserId());
		param.put("PARA_ORDER_BY", strOrderBy);
		
		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("webPageReplace.getPageList", param);
	}

	/**
	 * 社員コンテンツ関連情報を削除
	 * @param webPageReplaceBean
	 * @param param
	 * @return List
	 */
	public void deleteReplaceSearchUserName(WebPageReplaceBean webPageReplaceBean) {
		// 更新条件を設定
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_REPLACE_SEARCHUSER_ID",webPageReplaceBean.getReplaceSearchUserId());
		param.put("PARA_SEARCH_USER_ID",webPageReplaceBean.getSearchUserId());
		
		//　DBから検索結果を取得
		this.sqlSessionTemplate.delete("webPageReplace.deleteAuthUser", param);
		this.sqlSessionTemplate.delete("webPageReplace.deleteAuthUserReserve",param);
		this.sqlSessionTemplate.delete("webPageReplace.deleteProxyUser",param);
		this.sqlSessionTemplate.delete("webPageReplace.deleteProxyUserReserve",param);
	}
	
	/**
	 * 社員コンテンツ関連情報を更新
	 * @param webPageReplaceBean
	 * @param param
	 * @return List
	 */
	public void updateReplaceSearchUserName(WebPageReplaceBean webPageReplaceBean, String userId) {
		// 更新条件を設定
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_REPLACE_SEARCHUSER_ID",webPageReplaceBean.getReplaceSearchUserId());
		param.put("PARA_UPDATE_BY", userId);
		param.put("PARA_SEARCH_USER_ID",webPageReplaceBean.getSearchUserId());
		
		//　DBから検索結果を取得
		this.sqlSessionTemplate.update("webPageReplace.updateAuthUser", param);
		this.sqlSessionTemplate.update("webPageReplace.updateAuthUserReserve",param);
		this.sqlSessionTemplate.update("webPageReplace.updateProxyUser",param);
		this.sqlSessionTemplate.update("webPageReplace.updateProxyUserReserve",param);
	}
	
	/**
	 * 社員コンテンツ関連情報を追加
	 * @param webPageReplaceBean
	 * @param param
	 * @return List
	 */
	public void addReplaceSearchUserName(WebPageReplaceBean webPageReplaceBean, String userId) {
		// 更新条件を設定
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_REPLACE_SEARCHUSER_ID",webPageReplaceBean.getReplaceSearchUserId());
		param.put("PARA_UPDATE_BY", userId);
		param.put("PARA_SEARCH_USER_ID",webPageReplaceBean.getSearchUserId());
		
		//　DBから検索結果を取得
		this.sqlSessionTemplate.insert("webPageReplace.insertAuthUser", param);
		this.sqlSessionTemplate.insert("webPageReplace.insertAuthUserReserve",param);
		this.sqlSessionTemplate.insert("webPageReplace.insertProxyUser",param);
		this.sqlSessionTemplate.insert("webPageReplace.insertProxyUserReserve",param);
	}
}
