package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.pageStatus.PageStatusResultBean;

/**
 * コンテンツ状況確認Dao実装クラス
 * 
 * File Name: PageStatusDao.java 
 * Created: 2015/11/23
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2015/11/23		    NTS        	       作成
 *
 **/
@Repository
public class PageStatusDao extends BaseDao {

	/**
	 * <p>
	 * 検索コンテンツ実配置を取得
	 * </p>
	 * 
	 * @param departmentId
	 * 
	 */
	public List<PageStatusResultBean> getPageStatusList(Map<String, Object> param) {
		//DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("pageStatus.getPageStatusList", param);
	}

	/**
	 * <p>
	 * リンク未設定もしくはリンク先コンテンツ削除済判定
	 * </p>
	 * 
	 * @param pageId
	 * 
	 */
	public int getPageLinkNum(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return (Integer)this.sqlSessionTemplate.selectOne("pageStatus.getPageLinkNum", pageId);
	}

	/**
	 * <p>
	 * 公開したまま編集判定（コンテンツ予約テーブルに削除されていない同じページＩＤのコンテンツが存在するか）
	 * </p>
	 * 
	 * @param pageId
	 * 
	 */
	public int getReservePageNum(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return (Integer)this.sqlSessionTemplate.selectOne("pageStatus.getReservePageNum", param);
	}
	/**
	 * <p>
	 * 指定ユーザが該当コンテンツを閲覧できるか判定
	 * </p>
	 * 
	 * @param pageId
	 * @param userId
	 * 
	 */
	public int getInspflg(String pageId, String userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userId);
		param.put("PARA_PAGE_ID", pageId);
		return (Integer)this.sqlSessionTemplate.selectOne("pageStatus.getInspNum", param);
	}

	/**
	 * <p>
	 * 下位コンテンツを抽出
	 * </p>
	 * 
	 * @param param
	 * 
	 */
	public List<PageStatusResultBean> getChildPageList(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectList("pageStatus.getChildPageList", param);
	}

	public List<PageStatusResultBean> getLinkPageList(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectList("pageStatus.getLinkPageList", param);
	}

	/**
	 * <p>
	 * コンテンツ情報を取得
	 * </p>
	 * 
	 * @param searchPageId
	 * 
	 */
	public PageStatusResultBean getPageInfo(String searchPageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", searchPageId);
		return this.sqlSessionTemplate.selectOne("pageStatus.getPageInfo", param);
	}

	/**
	 * <p>
	 * 公開するグループリストを取得
	 * </p>
	 * 
	 * @param searchPageId
	 * 
	 */
	public List<PageStatusResultBean> getOpenGroupList(String searchPageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", searchPageId);
		return this.sqlSessionTemplate.selectList("pageStatus.getOpenGroupList", param);
	}

	/**
	 * <p>
	 * 公開する個人リストを取得
	 * </p>
	 * 
	 * @param searchPageId
	 * 
	 */
	public List<PageStatusResultBean> getOpenPersonalList(String searchPageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", searchPageId);
		return this.sqlSessionTemplate.selectList("pageStatus.getOpenPersonalList", param);
	}

	/**
	 * <p>
	 * 承認者リストを取得
	 * </p>
	 * 
	 * @param searchPageId
	 * 
	 */
	public List<PageStatusResultBean> getAuthorizerList(String searchPageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", searchPageId);
		return this.sqlSessionTemplate.selectList("pageStatus.getAuthorizerList", param);
	}

	/**
	 * <p>
	 * リンクリストを取得
	 * </p>
	 * 
	 * @param searchPageId
	 * 
	 */
	public List<PageStatusResultBean> getPageLinkList(String searchPageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", searchPageId);
		return this.sqlSessionTemplate.selectList("pageStatus.getLinkList", param);
	}

	/**
	 * <p>
	 * 添付ファイルリストを取得
	 * </p>
	 * 
	 * @param searchPageId
	 * 
	 */
	public List<PageStatusResultBean> getAttachmentList(String searchPageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", searchPageId);
		return this.sqlSessionTemplate.selectList("pageStatus.getAttachmentList", param);
	}
}