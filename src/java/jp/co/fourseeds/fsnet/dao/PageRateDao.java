package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jp.co.common.frame.dao.BaseDao;
import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.pageRate.PageRateDetailBean;
import jp.co.fourseeds.fsnet.beans.pageRate.PageRateDetailFormBean;
import jp.co.fourseeds.fsnet.beans.pageRate.PageRateSearchFormBean;
import jp.co.fourseeds.fsnet.beans.pageRate.PageRateSearchResultBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;

/**
 * コンテンツ評価状況機能Dao実装クラス
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2016/02/03		    NTS        	       作成
 *
 **/
@Repository
public class PageRateDao extends BaseDao {
	
	/**
	 * コンテンツ情報
	 * @param EvaluationStatusFormBean
	 * @param LoginUserBean
	 * 
	 * @return List<EvaluationStatusBean>
	 */
	public List<PageRateSearchResultBean> getContentResultsList(PageRateSearchFormBean contentForm,LoginUserBean loginUser, String strOrderBy) {
		// パラメータＭａｐ
		Map<String, Object> param = new HashMap<String, Object>();
		// 公開開始日付
		String startDate= contentForm.getStartDate();
		// 公開終了日付
		String endDate= contentForm.getEndDate();
		
		// 検索条件を設定
		// 公開開始日指定しない場合、最小の公開開始日を設定
		if (StringUtil.isEmpty(startDate)) {
			param.put("PARA_START_DATE", "1900/01/01");
		// 公開開始日指定の場合、画面の 公開開始日を設定
		} else {
			param.put("PARA_START_DATE", startDate);
		}
		// 公開終了日指定しない場合、最大の公開終了日を設定
		if (StringUtil.isEmpty(endDate)) {
			param.put("PARA_END_DATE", "2998/12/31");
		// 公開終了日指定の場合、画面の 公開終了日を設定
		} else {
			param.put("PARA_END_DATE", endDate);
		}
		// タイトル名
		if (!StringUtil.isEmpty(contentForm.getTitle())) {
			param.put("PARA_TITLE", contentForm.getTitle());
		}
		// 現在有効なコンテンツのみ対象フラグ
		param.put("PARA_VALID_FLAG", contentForm.getHyoukaValidFlag());
		// システム利用区分
		param.put("PARA_ROLE", loginUser.getRole());
		// ユーザーＩＤ
		param.put("PARA_USER_ID", loginUser.getUserId());
		// ソート
		param.put("PARA_ORDER_BY", strOrderBy);
		
		// DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("pageRate.getContentResultsList", param);
	}
	
	/**
	 * コンテンツ評価情報
	 * @param HyoukaContentsForm
	 * @return
	 */
	public List<PageRateDetailBean> getPageRateaResultList(String pageId) {
		// パラメータＭａｐ
		Map<String, Object> param = new HashMap<String, Object>();
		// ページＩＤ
		param.put("PARA_PAGE_ID", pageId);
		// DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("pageRate.getPageRateaResultList", param);
	}
	
	/**
	 * コンテンツ情報
	 * @param pageId
	 * @param loginUser
	 * @return
	 * @throws DataBaseException
	 */
	public List<PageRateSearchResultBean> getEvaluationDetailList(String pageId, LoginUserBean loginUser) {
		// パラメータＭａｐ
		Map<String, Object> param = new HashMap<String, Object>();
		// ページＩＤ
		param.put("PARA_PAGE_ID", pageId);
		// 最小の公開開始日を設定
		param.put("PARA_START_DATE", "1900/01/01");
		// 最大の公開終了日を設定
		param.put("PARA_END_DATE", "2998/12/31");
		// システム利用区分
		param.put("PARA_ROLE", loginUser.getRole());
		// ユーザーＩＤ
		param.put("PARA_USER_ID", loginUser.getUserId());
		// ソート
		param.put("PARA_ORDER_BY", "");
		// DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("pageRate.getContentResultsList", param);
	}
	
	/**
	 * 評価詳細統計リスト(評価詳細CSV出力用)
	 * @param pageId
	 *           ページID
	 * @return List<PageRateDetailBean>
	 *           検索結果
	 */
	public List<PageRateDetailBean> getEvaluationCountList(String pageId) {
		// パラメータＭａｐ
		Map<String, Object> param = new HashMap<String, Object>();
		// ページＩＤ
		param.put("PARA_PAGE_ID", pageId);
		// DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("pageRate.getEvaluationCountList", param);
	}
	
	/**
	 * 抽出条件（評価）評価する
	 * @param pageId
	 *           ページID
	 * @param count
	 *           件数
	 * @return List<PageRateDetailBean>
	 *           検索結果
	 */
	public List<PageRateDetailBean> getEvaluationUserIdList(String pageId, int count) {
		// パラメータＭａｐ
		Map<String, Object> param = new HashMap<String, Object>();
		// ページＩＤ
		param.put("PARA_PAGE_ID", pageId);
		// COUNT
		param.put("PARA_COUNT", count);
		// DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("pageRate.getEvaluationUserIdList", param);
	}
	
	/**
	 * 抽出条件（評価）評価する(「And条件」チェックしない)
	 * @param pageId
	 *           ページID
	 * @param str
	 *           SQL
	 * @return List<String>
	 *           検索結果
	 */
	public List<String> getPluralNotCheckHyoukaUserIdList(String pageId, String str) {
		// パラメータＭａｐ
		Map<String, Object> param = new HashMap<String, Object>();
		// ページＩＤ
		param.put("PARA_PAGE_ID", pageId);
		// SQL
		param.put("PARA_SQL", str);
		// DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("pageRate.getPluralNotCheckHyoukaUserIdList", param);
	}
	
	/**
	 * 抽出条件（対象者）必須
	 * @param pageId
	 *           ページID
	 * @return List<String>
	 *           検索結果
	 */
	public List<String> getNecesseryReaderList(String pageId) {
		// パラメータＭａｐ
		Map<String, Object> param = new HashMap<String, Object>();
		// ページＩＤ
		param.put("PARA_PAGE_ID", pageId);
		// DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("pageRate.getNecesseryReaderList", param);
	}
	
	/**
	 * コメント情報
	 * @param detailForm
	 *           明細フォーム
	 * @param mergelist
	 *           リスト
	 * @return List<PageRateDetailBean>
	 *           検索結果
	 */
	public List<PageRateDetailBean> getCommentList(PageRateDetailFormBean detailForm, List<String> mergelist, String strOrderBy) {
		
		// パラメータＭａｐ
		Map<String, Object> param = new HashMap<String, Object>();
		// コメント内キーワード
		String commentKeyWord = StringUtil.nullToBlank(detailForm.getCommentKeyWord());
		String uId = "";
		if (mergelist != null && mergelist.size() > 0) {
			for (int i = 0; i < mergelist.size(); i++) {
				if (i == mergelist.size() - 1) {
					uId = uId + "'" + (String)mergelist.get(i) + "'";
				} else if ((i % 999) == 0 && i > 0) {
					uId += "'" + (String)mergelist.get(i) + "') OR T1.USER_ID IN ( ";
				} else {
					uId = uId + "'" + (String)mergelist.get(i) + "',";
				}
			}
		}
		// ページＩＤ
		param.put("PARA_PAGE_ID", detailForm.getPageId());
		// コメント内キーワード
		param.put("PARA_COMMENT_KEY_WORD", commentKeyWord);
		// ユーザー
		param.put("PARA_USER_ID", uId);
		// ソート
		param.put("PARA_ORDER_BY", strOrderBy);
		// DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("pageRate.getCommentList", param);
	}
	
}