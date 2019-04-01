package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jp.co.common.frame.dao.BaseDao;
import jp.co.fourseeds.fsnet.beans.HotPageFormBean;
import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.common.util.CommonUtil;

/**
 * Subお気に入りDAO実装クラス
 * 
 * File Name: HotPageDao.java 
 * Created: 2016/01/06
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0      2016/01/06          NTS            作成
 *
 **/
@Repository
public class HotPageDao extends BaseDao {

	/**
	 * お気に入りデータ取得
	 * @param currentUserId
	 *            ログインユーザーID
	 * @return 
	 *            お気に入りデータリスト
	 */
	public List<HotPageFormBean> getHotPageFile (String currentUserId) {
		// パラメータ
		Map<String, Object> param = new HashMap<String, Object>();
		// ログインユーザー
		param.put("PARA_USER_ID", currentUserId);
		// SUBお気に入りデータ取得SQL
		return this.sqlSessionTemplate.selectList("hotPage.getHotPageFile", param);
	}
	
	/**
	 * MAXお気に入りフォルダIDを取得
	 * @param formBean
	 *            フォームビン
	 * @param  userId
	 *            ユーザーID
	 * @return 
	 *             MAXお気に入りフォルダID
	 */
	public String getMaxHotPageId(HotPageFormBean formBean, String userId) {
		// パラメータ
		Map<String, Object> param = new HashMap<String, Object>();
		// ログインユーザー
		param.put("PARA_USER_ID", userId);
		// SUBお気に入りデータ取得SQL
		return this.sqlSessionTemplate.selectOne("hotPage.getMaxHotPageId", param);
	}
	
	/**
	 * MAX配列順を取得
	 * @param formBean
	 *            フォームビン
	 * @param  userId
	 *            ユーザーID
	 * @return 
	 *            MAX配列順
	 */
	public String getMaxOrderBy(HotPageFormBean formBean, String userId) {
		// パラメータ
		Map<String, Object> param = new HashMap<String, Object>();
		// ログインユーザー
		param.put("PARA_USER_ID", userId);
		// お気に入りフォルダID
		param.put("PARA_HOT_PAGE_ID", formBean.getHotPageId());
		// SUBお気に入りデータ取得SQL
		return this.sqlSessionTemplate.selectOne("hotPage.getMaxOrderBy", param);
	}
	
	/**
	 * お気に入りコンテンツ情報論理削除 
	 * @param formBean
	 *            フォームビン
	 * @param  userId
	 *            ユーザーID
	 * @return 
	 *            なし
	 */
	public void updateHotPageInvalid(HotPageFormBean formBean, String userId) {
		// パラメータ
		Map<String, Object> param = new HashMap<String, Object>();
		// ログインユーザー
		param.put("PARA_USER_ID", userId);
		// ページID
		param.put("PARA_HOT_PAGE_ID", formBean.getHotPageId());
		// SUBお気に入りデータ取得SQL
		this.sqlSessionTemplate.update("hotPage.updateHotPageInvalid", param);
	}
	
	/**
	 * お気に入りコンテンツ情報登録
	 * @param formBean
	 *            フォームビン
	 * @param  userId
	 *            ユーザーID
	 * @return 
	 *            なし
	 */
	public void insertHotPage(HotPageFormBean formBean, String userId) {
		// パラメータ
		Map<String, Object> param = new HashMap<String, Object>();
		// ログインユーザー
		param.put("PARA_USER_ID", userId);
		// ページID
		param.put("PARA_PAGE_ID", formBean.getPageId());
		// ページタイトル名
		param.put("PARA_TITLE", formBean.getTitle());
		// ページタイトル名
		param.put("PARA_ORDER_BY", formBean.getOrderBy());
		// お気に入りフォルダID
		param.put("PARA_HOT_PAGE_ID", formBean.getHotPageId());
		// SUBお気に入りデータ取得SQL
		this.sqlSessionTemplate.insert("hotPage.insertHotPage", param);
	}
	
	/**
	 * お気に入り情報登録
	 * @param formBean
	 *            フォームビン
	 * @param  userId
	 *            ユーザーID
	 * @return 
	 *            なし
	 */
	public void insertHotPageFile(HotPageFormBean formBean, String userId) {
		// パラメータ
		Map<String, Object> param = new HashMap<String, Object>();
		// ログインユーザー
		param.put("PARA_USER_ID", userId);
		// お気に入りフォルダID
		param.put("PARA_HOT_PAGE_ID", formBean.getHotPageId());
		// お気に入りフォルダ名
		param.put("PARA_HOT_PAGE_TITLE", formBean.getHotPageTitle());
		// SUBお気に入りデータ取得SQL
		this.sqlSessionTemplate.insert("hotPage.insertHotPageFile", param);
	}
	
	/**
	 * お気に入り明細データ取得
	 * @param detailBean
	 *            お気に入りビン
	 * @param loginUser
	 *            ログインユーザービン
	 * @return 
	 *            お気に入り明細データリスト
	 */
	public List<HotPageFormBean> getHotPageDetail(HotPageFormBean detailBean, LoginUserBean loginUser) {
		// パラメータ
		Map<String, Object> param = new HashMap<String, Object>();
		// ログインユーザー
		param.put("PARA_USER_ID", loginUser.getUserId());
		// システム利用区分
		param.put("PARA_ROLE", loginUser.getRole());
		// お気に入りフォルダID
		param.put("PARA_HOT_PAGE_ID", detailBean.getHotPageId());
		// トップグルップリスト
		param.put("PARA_TOP_GROUP_LIST", CommonUtil.getGroupSql(loginUser.getTopGroupList()));
		// SUBお気に入りデータ取得SQL
		return this.sqlSessionTemplate.selectList("hotPage.getHotPageDetail", param);
	}
	
	/**
	 * お気に入り情報を削除する
	 * @param formBean
	 *            お気に入りビン
	 * @param userId
	 *            ログインユーザーID
	 * @return 
	 *            お気に入り明細データリスト
	 */
	public void updateHotPageFileInvalid(HotPageFormBean formBean, String userId) {
		// パラメータ
		Map<String, Object> param = new HashMap<String, Object>();
		// ログインユーザー
		param.put("PARA_USER_ID", userId);
		// お気に入りフォルダID
		param.put("PARA_HOT_PAGE_ID", formBean.getHotPageId());
		// SUBお気に入りデータ取得SQL
		this.sqlSessionTemplate.update("hotPage.updateHotPageFileInvalid", param);
	}
	
	/**
	 * お気に入りフォルダ名を更新する。
	 * @param formBean
	 *            お気に入りビン
	 * @param userId
	 *            ログインユーザーID
	 * @return 
	 *            お気に入り明細データリスト
	 */
	public void updateHotPageFileTitle(HotPageFormBean formBean, String userId) {
		// パラメータ
		Map<String, Object> param = new HashMap<String, Object>();
		// ログインユーザー
		param.put("PARA_USER_ID", userId);
		// お気に入りフォルダID
		param.put("PARA_HOT_PAGE_ID", formBean.getHotPageId());
		// お気に入りフォルダID
		param.put("PARA_HOT_PAGE_TITLE", formBean.getHotPageTitle());
		// SUBお気に入りデータ取得SQL
		this.sqlSessionTemplate.update("hotPage.updateHotPageFileTitle", param);
	}
	
	/**
	 * フォルダID別お気に入り情報タイトルを削除する。
	 * @param formBean
	 *            お気に入りビン
	 * @param userId
	 *            ログインユーザーID
	 * @return 
	 *            お気に入り明細データリスト
	 */
	public void deleteHotPageAll(HotPageFormBean formBean, String userId) {
		// パラメータ
		Map<String, Object> param = new HashMap<String, Object>();
		// ログインユーザー
		param.put("PARA_USER_ID", userId);
		// お気に入りフォルダID
		param.put("PARA_HOT_PAGE_ID", formBean.getHotPageId());
		// SUBお気に入りデータ取得SQL
		this.sqlSessionTemplate.update("hotPage.deleteHotPage", param);
	}
	
	/**
	 * ページID別お気に入り情報タイトルを削除する。
	 * @param formBean
	 *            お気に入りビン
	 * @param userId
	 *            ログインユーザーID
	 * @return 
	 *            お気に入り明細データリスト
	 */
	public void deleteHotPageSingle(HotPageFormBean formBean, String userId) {
		// パラメータ
		Map<String, Object> param = new HashMap<String, Object>();
		// ログインユーザー
		param.put("PARA_USER_ID", userId);
		// ページID
		param.put("PARA_PAGE_ID", formBean.getPageId());
		// SUBお気に入りデータ取得SQL
		this.sqlSessionTemplate.update("hotPage.deleteHotPage", param);
	}
	
}