package jp.co.fourseeds.fsnet.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.SiteSearchResultBean;
import jp.co.fourseeds.fsnet.common.ConstantContainer;
import jp.co.fourseeds.fsnet.common.util.CommonUtil;

/**
 * サイト内検索DAOの実装クラス
 *
 * @author NTS
 * @version 1.0.0 : 2015.11.14 新規作成
 */
@Repository
public class SiteSearchDao extends BaseDao{

	//ログインユーザの閲覧可能の検索結果
	public List<SiteSearchResultBean> getUserPageList(LoginUserBean loginUser, String sort) {
		List<SiteSearchResultBean> seachPageList = new ArrayList<SiteSearchResultBean>();

		// get the top group list sql
		String topGroupListSql = CommonUtil.getGroupSql(loginUser.getTopGroupList());
		String strSort = "";
		if(sort.equals(ConstantContainer.SORT_CONFIRM_DATE_LATE)) {
			strSort = " T1.CONFIRM_DATE desc, T1.TITLE asc";
		} else if(sort.equals(ConstantContainer.SORT_START_DATE_LATE)) {
			strSort = " T1.START_DATE desc, T1.TITLE asc";
		} else if(sort.equals(ConstantContainer.SORT_FIELD_SUBJECT_ASCENDING)) {
			strSort = " T1.TITLE asc, T1.CONFIRM_DATE desc";
		} else if(sort.equals(ConstantContainer.SORT_FIELD_SUBJECT_DESCENDING)) {
			strSort = " T1.TITLE desc, T1.CONFIRM_DATE desc";
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_ROLE", loginUser.getRole());
		param.put("PARA_USER_ID", loginUser.getUserId()); 
		param.put("PARA_TOP_GROUP_LIST", topGroupListSql);
		param.put("PARA_SORT", strSort);

		seachPageList = this.sqlSessionTemplate.selectList("siteSearch.getUserPageList", param);
		
		return seachPageList;
	}
	
	//オラクルの検索結果
	public List<String> getOraclePageList(String oracleKeyWord, String attachmetKeyWord) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_ATTACHMET_KEY", attachmetKeyWord);
		param.put("PARA_ORACLE_KEY", oracleKeyWord);

		return this.sqlSessionTemplate.selectList("siteSearch.getOraclePageList", param);
	}
	
	//	リンク配置先の取得
	public List<SiteSearchResultBean> getLinkPageInfo(SiteSearchResultBean haitiInfoBean, LoginUserBean loginUser) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_ROLE", loginUser.getRole());
		param.put("PARA_USER_ID", loginUser.getUserId());
		param.put("PARA_TOP_GROUP_LIST", CommonUtil.getGroupSql(loginUser.getTopGroupList()));
		param.put("PARA_PAGE_ID", haitiInfoBean.getPageId());
		param.put("PARA_PAGE_KEY", haitiInfoBean.getStrPageKey());

		return this.sqlSessionTemplate.selectList("siteSearch.getLinkPageInfo", param);
	}
	
	//実配置先の取得
	public List<SiteSearchResultBean> getJituPageInfo(SiteSearchResultBean haitiInfoBean, LoginUserBean loginUser) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_ROLE", loginUser.getRole());
		param.put("PARA_USER_ID", loginUser.getUserId());
		param.put("PARA_TOP_GROUP_LIST", CommonUtil.getGroupSql(loginUser.getTopGroupList()));
		param.put("PARA_PAGE_ID", haitiInfoBean.getPageId());
		param.put("PARA_PAGE_KEY", haitiInfoBean.getStrPageKey());

		return this.sqlSessionTemplate.selectList("siteSearch.getJituPageInfo", param);
	}
	
	//半角小文字To全角大文字
	public String changeOracleKeyWord(String OracleKeyWord) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_ORACLE_KEY", OracleKeyWord);
		
		return this.sqlSessionTemplate.selectOne("siteSearch.changeOracleKeyWord", param);
	}
	
	/**
	 * コンテンツ情報を取得
	 * 
	 * 
	 * 
	 * @param pageId
	 */
	public SiteSearchResultBean getPage(String pageId, LoginUserBean loginUser) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_ROLE", loginUser.getRole());
		param.put("PARA_USER_ID", loginUser.getUserId());
		param.put("PARA_TOP_GROUP_LIST", CommonUtil.getGroupSql(loginUser.getTopGroupList()));
		param.put("PARA_PAGE_ID", pageId);

		return this.sqlSessionTemplate.selectOne("siteSearch.getPage", param);
	}
}
