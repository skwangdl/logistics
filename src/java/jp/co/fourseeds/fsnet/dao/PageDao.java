package jp.co.fourseeds.fsnet.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.beans.LabelValueBean;
import jp.co.common.frame.dao.BaseDao;
import jp.co.common.frame.util.prop.FsPropertyUtil;

import jp.co.fourseeds.fsnet.beans.page.AuthGroupBean;
import jp.co.fourseeds.fsnet.beans.page.AuthUserBean;
import jp.co.fourseeds.fsnet.beans.page.PageAttachmentBean;
import jp.co.fourseeds.fsnet.beans.page.PageBean;
import jp.co.fourseeds.fsnet.beans.page.PageCommentAdminBean;
import jp.co.fourseeds.fsnet.beans.page.PageCommentRateBean;
import jp.co.fourseeds.fsnet.beans.page.PageDeleteBean;
import jp.co.fourseeds.fsnet.beans.page.PageLinkBean;
import jp.co.fourseeds.fsnet.beans.page.PageRateItemBean;
import jp.co.fourseeds.fsnet.beans.page.PageUserCommentBean;
import jp.co.fourseeds.fsnet.beans.page.PageUserRateBean;
import jp.co.fourseeds.fsnet.beans.page.ProxyUserBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;

/**
 * コンテンツ情報機能Dao実装クラス
 * 
 * Created: 2015/09/22
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2015/09/22		    NTS        	       作成
 *
 **/
@SuppressWarnings(value={"rawtypes"})
@Repository
public class PageDao extends BaseDao {
	public PageBean getPage(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectOne("page.getPage", param);
	}
	
	public PageBean getPageReserve(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectOne("page.getPageReserve", param);
	}
	
	public List getPageLinkList(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getPageLinkList", param);
	}
	
	public List getPageLinkListRsv(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getPageLinkListRsv", param);
	}
	
	public List getPageAttachList(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getPageAttachList", param);
	}
	
	public List getPageAttachListRsv(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getPageAttachListRsv", param);
	}
	
	public List<AuthGroupBean> getAuthGroupList(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getAuthGroupList", param);
	}
	
	public List<AuthGroupBean> getAuthGroupListRsv(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getAuthGroupListRsv", param);
	}
	
	public List<AuthUserBean> getAuthUserList(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getAuthUserList", param);
	}
	
	public List<AuthUserBean> getAuthUserListRsv(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getAuthUserListRsv", param);
	}
	
	public List<ProxyUserBean> getProxyUserList(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getProxyUserList", param);
	}
	
	public List<ProxyUserBean> getProxyUserListRsv(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getProxyUserListRsv", param);
	}
	
	public List getPageRateItemList(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getPageRateItemList", param);
	}
	
	public List getPageRateItemListRsv(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getPageRateItemListRsv", param);
	}
	
	public List getCommentAdminList(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getCommentAdminList", param);
	}
	
	public List getCommentAdminListRsv(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getCommentAdminListRsv", param);
	}
	
	public List getCommentAdminOptList(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getCommentAdminOptList", param);
	}
	
	public List getCommentAdminOptListRsv(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getCommentAdminOptListRsv", param);
	}
	
	/**
	 * ユーザ名称を取得（問題報告対応者）
	 * @param userId ユーザID
	 * @return　ユーザ名称
	 */
	public String getCreateUserName(String userId)  {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userId);
		return this.sqlSessionTemplate.selectOne("page.getCreateUserName", param);
	}
	
	/**
	 * ページIDによって、ページ相関TBL情報を物理削除
	 */
	public void deletePageExtend(String pageId, String tblNm) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TABLE_NAME", tblNm);
		param.put("PARA_PAGE_ID", pageId);

		this.sqlSessionTemplate.delete("page.deletePageExtend", param);
	}
	
	/**
	 * ページIDによって、ページ相関TBL情報を論理削除
	 */
	public void updatePageExtendInvalid(String pageId, String tblNm, String userId) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TABLE_NAME", tblNm);
		param.put("PARA_PAGE_ID", pageId);
		param.put("updateBy", userId);

		this.sqlSessionTemplate.delete("page.updatePageExtendInvalid", param);
	}	

	/**
	 * 実子コンテンツ情報リストを取得
	 * @param pageId 親ページID
	 */
	public List<PageBean> getChildPageList(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_P_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getChildPageList", param);
	}

	/**
	 * 予約子コンテンツ情報リストを取得
	 * @param pageId 親ページID
	 */
	public List<PageBean> getChildPageReserveList(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_P_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getChildPageReserveList", param);
	}
	
	public List<PageBean> getOpenChildPageList(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_P_PAGE_ID", pageId);
		param.put("PARA_END_DATE", new Date());
		return this.sqlSessionTemplate.selectList("page.getOpenChildPageList", param);
	}
	
	/**
	 * 親ページIDと表示順によって、コンテンツ情報更新
	 * 
	 * @param 
	 */
	public void updateBrothersOrderBy(String pageId, String ppageId, String orderBy, String userId) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_P_PAGE_ID", ppageId);
		param.put("PARA_ORDER_BY", orderBy);
		param.put("PARA_USER_ID", userId);
		
		this.sqlSessionTemplate.update("page.updateBrothersOrderBy", param);
	}

	public void insertPage(PageBean bean) {
		this.sqlSessionTemplate.insert("page.insertPage", bean);
	}
	public void insertPageRsv(PageBean bean) {
		this.sqlSessionTemplate.insert("page.insertPageReserve", bean);
	}
	public int updatePage(PageBean bean) {
		return this.sqlSessionTemplate.update("page.updatePage", bean);
	}
	
	/**
	 * コンテンツ予約情報を更新
	 * @param bean
	 */
	public int updatePageReserve(PageBean bean) {
		return this.sqlSessionTemplate.update("page.updatePageReserve", bean);
	}
	
	/**
	 * 真実コンテンツの公開維持編集フラグ「ON_EDIT_FLAG」を更新
	 * A）予約コンテンツ登録時点で、1(公開したまま編集となる)に更新
	 * B）予約コンテンツ削除時点で、0(公開したまま編集にはならない)に更新
	 * 
	 * @param 
	 */
	public int updateOnEditFlag(PageBean pageBean) {
		return this.sqlSessionTemplate.update("page.updateOnEditFlag", pageBean);
	}
	
	/**
	 * 真実コンテンツの公開維持編集フラグ「ON_EDIT_FLAG」を更新
	 * A）予約コンテンツ登録時点で、1(公開したまま編集となる)に更新
	 * B）予約コンテンツ削除時点で、0(公開したまま編集にはならない)に更新
	 * 
	 * @param 
	 */
	public void deleteLogInfo(String page_id, String userid) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", page_id);
		param.put("PARA_UPDATE_BY", userid);
		
		this.sqlSessionTemplate.update("page.deleteLogInfo", param);
	}
	public void insertPageLink(PageLinkBean bean) {
		this.sqlSessionTemplate.insert("page.insertPageLink", bean);
	}
	public void insertPageLinkRsv(PageLinkBean bean) {
		this.sqlSessionTemplate.insert("page.insertPageLinkRsv", bean);
	}
	
	public void deletePageAttachment(PageAttachmentBean bean) {
		this.sqlSessionTemplate.update("page.deletePageAttachment", bean);
	}
	public void updatePageAttachment(PageAttachmentBean bean) {
		this.sqlSessionTemplate.update("page.updatePageAttachment", bean);
	}
	public void insertPageAttachment(PageAttachmentBean bean) {
		this.sqlSessionTemplate.update("page.insertPageAttachment", bean);
	}
	public void insertPageAttachmentRsv(PageAttachmentBean bean) {
		this.sqlSessionTemplate.update("page.insertPageAttachmentRsv", bean);
	}

	public void insertAuthGroup(AuthGroupBean bean) {
		// 必須閲覧区分
		bean.setNecessityReadFlag(StringUtil.nullToZero(bean.getNecessityReadFlag()));
		this.sqlSessionTemplate.insert("page.insertAuthGroup", bean);
	}
	public void insertAuthGroupRsv(AuthGroupBean bean) {
		// 必須閲覧区分
		bean.setNecessityReadFlag(StringUtil.nullToZero(bean.getNecessityReadFlag()));
		this.sqlSessionTemplate.insert("page.insertAuthGroupRsv", bean);
	}
	public void insertAuthUser(AuthUserBean bean) {
		// 必須閲覧区分
		bean.setNecessityReadFlag(StringUtil.nullToZero(bean.getNecessityReadFlag()));
		this.sqlSessionTemplate.insert("page.insertAuthUser", bean);
	}
	public void insertAuthUserRsv(AuthUserBean bean) {
		// 必須閲覧区分
		bean.setNecessityReadFlag(StringUtil.nullToZero(bean.getNecessityReadFlag()));
		this.sqlSessionTemplate.insert("page.insertAuthUserRsv", bean);
	}
	public void insertProxyUser(ProxyUserBean bean) {
		this.sqlSessionTemplate.insert("page.insertProxyUser", bean);
	}
	public void insertProxyUserRsv(ProxyUserBean bean) {
		this.sqlSessionTemplate.insert("page.insertProxyUserRsv", bean);
	}
	public void insertPageRateItem(PageRateItemBean bean) {
		this.sqlSessionTemplate.insert("page.insertPageRateItem", bean);
	}
	public void insertPageRateItemRsv(PageRateItemBean bean) {
		this.sqlSessionTemplate.insert("page.insertPageRateItemRsv", bean);
	}
	public void updatePageRateItem(PageRateItemBean bean) {
		this.sqlSessionTemplate.update("page.updatePageRateItem", bean);
	}
	public void insertPageCommentAdmin(PageCommentAdminBean bean) {
		this.sqlSessionTemplate.insert("page.insertPageCommentAdmin", bean);
	}
	public void insertPageCommentAdminRsv(PageCommentAdminBean bean) {
		this.sqlSessionTemplate.insert("page.insertPageCommentAdminRsv", bean);
	}
	public void deleteAuthGroup(AuthGroupBean bean) {
		this.sqlSessionTemplate.delete("page.deleteAuthGroup", bean);
	}
	public void deleteAuthGroupRsv(AuthGroupBean bean) {
		this.sqlSessionTemplate.delete("page.deleteAuthGroupRsv", bean);
	}
	public void deleteAuthUser(AuthUserBean bean) {
		this.sqlSessionTemplate.delete("page.deleteAuthUser", bean);
	}
	public void deleteAuthUserRsv(AuthUserBean bean) {
		this.sqlSessionTemplate.delete("page.deleteAuthUserRsv", bean);
	}
	public void deleteProxyUser(ProxyUserBean bean) {
		this.sqlSessionTemplate.delete("page.deleteProxyUser", bean);
	}
	public void deleteProxyUserRsv(ProxyUserBean bean) {
		this.sqlSessionTemplate.delete("page.deleteProxyUserRsv", bean);
	}

	/**
	 * 「新規」表示維持表示フラグを取得
	 * @param pageId ページID
	 * @return　「新規」表示維持表示フラグ
	 */
	public String getViewNewKeepFlag(String pageId)  {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_UPDATE_DATE", Integer.parseInt(FsPropertyUtil.getStringProperty("update.date")));
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectOne("page.getViewNewKeepFlag", param);
	}
	
	/**
	 * 対象者のドロップダウンリスト
	 * @param 検索条件
	 * @return 区分?と区分内容
	 */
	public List<LabelValueBean> getUserDivisionDropDownList(Map<Object, Object> param) {
		return this.sqlSessionTemplate.selectList("page.getUserDivisionDropDownList", param);
	}	
	
	/**
	 * コンテンツ添付ファイル情報の最大URLを取得
	 * @param pageId ページID
	 * @return 最大URLを返す
	 */
	public String getMaxAttachmentUrl(String pageId) {
		String strValue = null;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		List list =  this.sqlSessionTemplate.selectList("page.getMaxAttachmentUrl", param);
		if (list != null && list.size() > 0) {
			strValue = (String) list.get(0);
		}
		return strValue;
	}
	
	/**
	 * コンテンツコメント統計情報を取得
	 * @param pageId
	 * @return int コンテンツコメント統計
	 * */
	public int getCommentCount(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return (Integer)this.sqlSessionTemplate.selectOne("page.getCommentCount", param);
	}
	
	/**
	 * 評価項目表示フラグ
	 * @param pageId
	 * @return
	 * @throws DataBaseException
	 */
	public int getShowHyoukaFlag(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return (Integer)this.sqlSessionTemplate.selectOne("page.getShowHyoukaFlag", param);
	}
	
	/**
	 * コンテンツ評価情報、評価統計情報を取得
	 * @param pageId
	 * @return List
	 * @throws DataBaseException
	 */
	public List<PageRateItemBean> getEvaluationTotalList(String pageId, String userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_USER_ID", userId);
		return this.sqlSessionTemplate.selectList("page.getEvaluationTotalList", param);
	}
	
	/**
     * 該当ユーザのコンテンツ評価統計情報テーブルの評価情報を物理削除する
     * @param pageId
     * @param currentUserId
     * @param hyoukaSequence
     * @param hyoukaOrderBy
     */
	public void deleteEvaluationCountByUser(String pageId, String userId, String hyoukaSequence, String hyoukaOrderBy) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_USER_ID", userId);
		param.put("PARA_EVALUATION_ORDER_BY", hyoukaOrderBy);
		param.put("PARA_SEQUENCE", hyoukaSequence);
		this.sqlSessionTemplate.delete("page.deleteEvaluationCountByUser", param);
	}
	
	/**
	 * 単一項目評価の時に、該当ユーザの全て評価情報を物理削除する
	 * @param pageId
	 * @param currentUserId
	 */
	public void deleteEvaluationByUser(String pageId, String currentUserId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_USER_ID", currentUserId);
		this.sqlSessionTemplate.delete("page.deleteEvaluationByUser", param);
	}
	
	/**
	 * ユーザIDと項目順番をコンテンツ評価統計情報テーブルに登録する
	 * @param pageId
	 * @param currentUserId
	 * @param hyoukaSequence
	 * @param hyoukaOrderBy
	 */
	public void insertEvaluationCountByUser(String pageId, String userId, String hyoukaSequence, String hyoukaOrderBy) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_USER_ID", userId);
		param.put("PARA_EVALUATION_ORDER_BY", hyoukaOrderBy);
		param.put("PARA_SEQUENCE", hyoukaSequence);
		this.sqlSessionTemplate.insert("insertEvaluationCountByUser", param);
	}
	
	/**
	 * コンテンツコメント情報、コメント統計情報を取得
	 * @param pageId
	 * @param userId
	 * @param flag
	 * @return List
	 * @throws DataBaseException
	 */
	public List<PageCommentRateBean> getCommentTotalList(String pageId, String userId, String flag) {
		String commentSize = FsPropertyUtil.getStringProperty("comment.size");
		String commentDisplaySize = FsPropertyUtil.getStringProperty("commentdisplay.size");
		
		StringBuffer sqlWhere = new StringBuffer("");
		sqlWhere.append(" WHERE ");
		sqlWhere.append("  ROWNUM <= ");
		if ("init".equals(flag)) {
			sqlWhere.append(commentDisplaySize);
		} else if("showAll".equals(flag)) {
			sqlWhere.append(commentSize);
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_USER_ID", userId);
		param.put("SQL_WHERE", sqlWhere.toString());
		
		return this.sqlSessionTemplate.selectList("page.getCommentTotalList", param);
	}
	
	/**
	 * コンテンツコメント情報を追加(投稿)
	 * @param pageId
	 * @param userId
	 * @param addCommentInfo
	 * @throws ServiceException
	 * @throws DataBaseException
	 */
	public void addComment(String pageId,  String userId, String addCommentInfo) {
		PageUserCommentBean pageUserComentBean = new PageUserCommentBean();
		pageUserComentBean.setPageId(pageId);
		pageUserComentBean.setUserId(userId);
		pageUserComentBean.setCreateBy(userId);
		pageUserComentBean.setCommentInfo(addCommentInfo);
		this.sqlSessionTemplate.insert("page.addComment", pageUserComentBean);
	}
	 /**
     * 該当ユーザのコメント情報を更新する
     * @param pageId
     * @param currentUserId
     * @param commentOrderBy
     * @param commentUserId
     * @param commentInfo
     * @throws DataBaseException
     */
    public void updateComment(String pageId, String currentUserId, String commentOrderBy, String commentUserId, String commentInfo) {
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_USER_ID", commentUserId);
		param.put("PARA_COMMENT_INFO", commentInfo);
		param.put("PARA_UPDATE_BY", currentUserId);
		param.put("PARA_COMMENT_ORDER_BY", commentOrderBy);
		this.sqlSessionTemplate.update("page.updateComment", param);
    }
    
    /**
     * コメント情報を論理削除(更新)するの場合、該当コメント統計情報を物理削除する
     * @param pageId
     * @param commentOrderBy
     * @throws DataBaseException
     */
    public void deleteCommentCountByOrderBy(String pageId, String commentOrderBy) {
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_COMMENT_ORDER_BY", commentOrderBy);
    	this.sqlSessionTemplate.delete("page.deleteCommentCountByOrderBy", param);
    }
    
    /**
     * 該当ユーザのコメント情報を論理削除する
     * @param pageId
     * @param currentUserId
     * @param commentOrderBy
     * @param commentUserId
     * @throws DataBaseException
     */
    public void deleteComment(String pageId, String currentUserId, String commentOrderBy, String commentUserId) {
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("PARA_PAGE_ID", pageId);
    	param.put("PARA_USER_ID", commentUserId);
		param.put("PARA_COMMENT_ORDER_BY", commentOrderBy);
		param.put("PARA_UPDATE_BY", currentUserId);
    	this.sqlSessionTemplate.delete("page.deleteComment", param);
    }
    
    /**
     * 該当ユーザのコメント統計情報テーブルのコメント情報を物理削除する
     * @param pageId
     * @param userId
     * @param commentOrderBy
     * @throws ServiceException
     * @throws DataBaseException
     */
    public void deleteCommentCountByUser(String pageId, String userId, String commentOrderBy) {
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("PARA_PAGE_ID", pageId);
    	param.put("PARA_USER_ID", userId);
		param.put("PARA_COMMENT_ORDER_BY", commentOrderBy);
    	this.sqlSessionTemplate.delete("page.deleteCommentCountByUser", param);
    }
    
    /**
     * ユーザIDとコメント順番をコメント統計情報テーブルに登録する
     * @param pageId
     * @param userId
     * @param commentOrderBy
     * @throws ServiceException
     * @throws DataBaseException
     */
    public void insertCommentCountByUser(String pageId, String userId, String commentOrderBy) {
    	PageCommentRateBean pageCommentRateBean = new PageCommentRateBean();
    	pageCommentRateBean.setPageId(pageId);
    	pageCommentRateBean.setCommentOrderBy(commentOrderBy);
    	pageCommentRateBean.setUserId(userId);
    	
		this.sqlSessionTemplate.insert("page.insertCommentCountByUser", pageCommentRateBean);
    }
    
	/**
	 * コンテンツ確認画面の評価数を取得
	 * @param pageId ページID
	 * @param sequence SEQUENCE
	 * @return　評価数
	 */
	public String getEvaluationCount(String pageId, String sequence)  {
		Map<String, Object> param = new HashMap<String, Object>();
		// ページID
		param.put("PARA_PAGE_ID", pageId);
		// SEQUENCE
		param.put("PARA_SEQUENCE", sequence);
		return this.sqlSessionTemplate.selectOne("page.getEvaluationCount", param);
	}

	/**
	 * コンテンツ評価アイテム情報を論理削除
	 * @param page_id ページID
	 * @param sequence　SEQUENCE
	 * @param userId　ユーザID
	 */
	public void updateEvaluationInvalidBySeq(String page_id, String sequence, String userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("updateBy", userId);
		param.put("PARA_PAGE_ID", page_id);
		param.put("PARA_SEQUENCE", sequence);
		this.sqlSessionTemplate.update("page.updateEvaluationInvalidBySeq", param);
	}
	
	/**
	 * コメントメール送信画面でユーザコメント明細を取得
	 * @param pageId ページID
	 * @param commentOrderBy コメント順番
	 * @param userId ページID
	 */
	public PageCommentRateBean getUserCommentForMail(String pageId, String commentOrderBy, String userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userId);
		param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_COMMENT_ORDER_BY", commentOrderBy);
		return this.sqlSessionTemplate.selectOne("page.getUserCommentForMail", param);
	}
	
	/**
	 * @param String userId
	 * @function 送信宛先、ＣＣのメールと氏名
	 */
	public List getMailAddress(String userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userId);
		return this.sqlSessionTemplate.selectList("page.getMailAddress", param);
	}
	
	/**
	 * 資料公開コンテンツ情報を更新
	 * @param PageBean bean
	 */
	public int updatePageForOpen(PageBean bean) {
		return this.sqlSessionTemplate.update("page.updatePageForOpen", bean);
	}

	/**
	 * コンテンツユーザー評価情報を削除
	 * @param PageUserRateBean bean
	 */
	public int updatePageUserRateInvalidByPageId(PageUserRateBean bean) {
		return this.sqlSessionTemplate.update("page.updatePageUserRateInvalidByPageId", bean);
	}	
	
	/**
	 * コンテンツ評価アイテム情報の削除情報により、コンテンツユーザー評価情報を論理削除
	 * @param pageId ページID
	 * @param userId 更新ユーザID
	 */
	public int updatePageUserRateInvalidByRateItem(String pageId, String userId) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("updateBy", userId);
		param.put("pageId", pageId);

		return this.sqlSessionTemplate.update("page.updatePageUserRateInvalidByRateItem", param);
	}

	/**
	 * コンテンツユーザー評価情報の項目順番を更新
	 * 更新キー：ページID、SEQUENCE
	 * @param bean
	 */
	public int updatePageUserRateOrderBySeq(PageUserRateBean pageUserRateBean) {
		return this.sqlSessionTemplate.update("page.updatePageUserRateOrderBySeq", pageUserRateBean);
	}

	/**
	 * コンテンツリンク予約情報からコンテンツリンク情報に移動（キー：ページID）
	 * @param bean
	 */
	public int linkReserveToLinkByPageId(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);

		return this.sqlSessionTemplate.update("page.linkReserveToLinkByPageId", param);
	}

	/**
	 * コンテンツ添付ファイル予約情報からコンテンツ添付ファイル情報に移動（キー：ページID）
	 * @param bean
	 */
	public int attachmentReserveToAttachmentByPageId(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);

		return this.sqlSessionTemplate.update("page.attachmentReserveToAttachmentByPageId", param);
	}

	/**
	 * コンテンツ閲覧権限トップグループ予約情報からコンテンツ閲覧権限トップグループ情報に移動（キー：ページID）
	 * @param bean
	 */
	public int leadingGroupReserveToLeadingGroupByPageId(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);

		return this.sqlSessionTemplate.update("page.leadingGroupReserveToLeadingGroupByPageId", param);
	}

	/**
	 * コンテンツ閲覧権限ユーザ予約情報からコンテンツ閲覧権限ユーザ情報に移動（キー：ページID）
	 * @param bean
	 */
	public int authUserReserveToAuthUserByPageId(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);

		return this.sqlSessionTemplate.update("page.authUserReserveToAuthUserByPageId", param);
	}
	
	/**
	 * 更新代行者予約情報から更新代行者情報に移動（キー：ページID）
	 * @param bean
	 */
	public int proxyUserReserveToProxyUserByPageId(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);

		return this.sqlSessionTemplate.update("page.proxyUserReserveToProxyUserByPageId", param);
	}
	
	/**
	 * コンテンツ評価アイテム予約情報に存在しなければ、コンテンツ評価アイテム情報を論理削除
	 * @param bean
	 */
	public int updateRateItemInvalidByRateItemReserve(PageRateItemBean pageRateItemBean) {
		return this.sqlSessionTemplate.update("page.updateRateItemInvalidByRateItemReserve", pageRateItemBean);
	}
	
	/**
	 * コンテンツ評価アイテム情報を削除（主キー）
	 * @param departmentId
	 */
	public int deletePageRateItemByPK(PageRateItemBean pageRateItemBean) {
		return this.sqlSessionTemplate.delete("page.deletePageRateItemByPK", pageRateItemBean);
	}
	
	/**
	 * コンテンツコメント管理者予約情報からコンテンツコメント管理者情報に移動（キー：ページID）
	 * @param bean
	 */
	public int commentAdminReserveToCommentAdminByPageId(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);

		return this.sqlSessionTemplate.update("page.commentAdminReserveToCommentAdminByPageId", param);
	}
	
	/**
	 * コンテンツ予約情報からコンテンツ情報に更新（キー：ページID）
	 * @param pageId　ページID
	 */
	public int updatePageFromReserve(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);

		return this.sqlSessionTemplate.update("page.updatePageFromReserve", param);
	}
	
	/**
	 * ログ情報を登録
	 * @param userId ユーザID
	 * @param pageId ページID
	 * @param updateBy 更新ユーザ
	 */
	public void insertLog(String userId, String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userId);
		param.put("PARA_PAGE_ID", pageId);
		
		this.sqlSessionTemplate.insert("page.insertLog", param);
	}
	
	/**
	 * 実コンテンツ情報を論理削除
	 * 
	 * @param 
	 */
	public int updatePageInvalid(PageBean pageBean) {
		return this.sqlSessionTemplate.update("page.updatePageInvalid", pageBean);
	}
	
	/**
	 * コンテンツ削除メール先ユーザ名称
	 * @param userId ユーザID
	 * @return　ユーザ名称
	 */
	public String getMailToUserName(String userId, String flag)  {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userId);
		param.put("PARA_FLAG", flag);
		return this.sqlSessionTemplate.selectOne("page.getMailToUserName", param);
	}

	/**
	 * 期間有効なコンテンツを取得
	 * @param pageId
	 * @return
	 */
	public PageDeleteBean getPagePeriodValid(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectOne("page.getPagePeriodValid", param);
	}
	
	/**
	 * ユーザ名称を取得
	 * @param userId ユーザID
	 * @return　ユーザ名称
	 */
	public String getUserName(String userId)  {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userId);
		return this.sqlSessionTemplate.selectOne("page.getUserName", param);
	}

	/**
	 * コンテンツリンク情報を取得（キー：リンクURL）
	 * @param tableName
	 * @param pageId
	 * @return
	 */
	public List getPageLinkListByUrl(String tableName, String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TABLE_NAME", tableName);
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getPageLinkListByUrl", param);
	}
	
	/**
	 * 期限切れ日数を取得
	 * @param pageId
	 * @return int コンテンツコメント統計
	 * */
	public int getPageLinkExpiredDateCount(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_EXPIRED_DATE", Integer.parseInt(FsPropertyUtil.getStringProperty("expired.date")));
		return (Integer)this.sqlSessionTemplate.selectOne("page.getPageLinkExpiredDateCount", param);
	}
	
	/**
	 * 転送先メールアドレスリストを取得
	 * @param userIds
	 * @return
	 */
	public List<String> getTrmailAddressList(String userIds) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userIds);
		return this.sqlSessionTemplate.selectList("page.getTrmailAddressList", param);
	}

}