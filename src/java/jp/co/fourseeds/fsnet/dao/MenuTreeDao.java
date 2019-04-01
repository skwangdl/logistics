package jp.co.fourseeds.fsnet.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.MenuTreeBean;
import jp.co.fourseeds.fsnet.common.util.CommonUtil;

/**
 * <p>
 * MenuとTreeを作成するDao
 * </p>
 * 
 * @author NTS
 * @version 1.0.0 : 2015.09.14 新規作成
 *
 **/
@Repository
public class MenuTreeDao extends BaseDao {

	/**
	 * システム利用区分が「1」システム管理者の場合、Menuを作成するクエリ
	 * 
	 */
	public List<MenuTreeBean> getMenuList() {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_SYSDATE", new Date());

		return this.sqlSessionTemplate.selectList("menuTree.getMenuAllList", param);
	}
	
	/**
	 * profileMenuを作成するクエリ
	 * 
	 */
	public MenuTreeBean getProfileInfo() {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_SYSDATE", new Date());

		return this.sqlSessionTemplate.selectOne("menuTree.getProfileInfo", param);
	}
	
	/**
	 * システム利用区分が「1」システム管理者以外の場合、Menuを作成するクエリ
	 * 
	 */
	public List<MenuTreeBean> getMenuList(String userId, List<String> groupList, String role) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", userId);
		param.put("PARA_GROUP", CommonUtil.getGroupSql(groupList));
		param.put("PARA_ROLE", role);
		param.put("PARA_SYSDATE", new Date());

		return this.sqlSessionTemplate.selectList("menuTree.getMenuList", param);
	}

	/**
	 * 引渡されたPageIDにより、すべての親PageIDを抽出してからListを格納する
	 */
	public String getAllParentPageList(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGEID", pageId);
		param.put("PARA_SYSDATE", new Date());

		return this.sqlSessionTemplate.selectOne("menuTree.getAllParentPageList", param);
	}
	
	/**
	 * システム利用区分が「1」システム管理者以外の場合、Treeを作成する
	 */
	public List<MenuTreeBean> getTreeListByRole(LoginUserBean loginUser, String parentPageId) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", loginUser.getUserId());
		param.put("PARA_GROUP", CommonUtil.getGroupSql(loginUser.getTopGroupList()));
		param.put("PARA_ROLE", loginUser.getRole());
		param.put("PARA_PID", parentPageId);
		param.put("PARA_CONFIRM_FLAG", loginUser.getConfirmFlag());
		param.put("PARA_FETURE_FLAG", loginUser.getFetureFlag());
		param.put("PARA_TEMPLATE_FLAG", loginUser.getTemplateFlag());
		param.put("PARA_PAST_FLAG", loginUser.getPastFlag());
		param.put("PARA_SYSDATE", new Date());

		return this.sqlSessionTemplate.selectList("menuTree.getTreeListByRole", param);
	}

	/**
	 * システム利用区分が「1」システム管理者の場合、Treeを作成する
	 */
	public List<MenuTreeBean> getTreeListNoRole(String parentPageId) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PID", parentPageId);
		param.put("PARA_SYSDATE", new Date());

		return this.sqlSessionTemplate.selectList("menuTree.getTreeListNoRole", param);
	}
		
	/**
	 * 親ページID取得
	 */
	public String getParentPageId(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGEID", pageId);

		return this.sqlSessionTemplate.selectOne("menuTree.getParentPageId", param);
	}

	/**
	 * 真実作成中のコンテンツ
	 */
	public List<MenuTreeBean> getUnConfirmList(String userId, String role) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", userId);
		param.put("PARA_ROLE", role);
		param.put("PARA_SYSDATE", new Date());

		return this.sqlSessionTemplate.selectList("menuTree.getUnConfirmList", param);
	}
	
	/**
	 * 予約作成中のコンテンツ
	 */
	public List<MenuTreeBean> getUnConfirmListRsv(String userId, String role) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", userId);
		param.put("PARA_ROLE", role);
		param.put("PARA_SYSDATE", new Date());

		return this.sqlSessionTemplate.selectList("menuTree.getUnConfirmListRsv", param);
	}

	/**
	 * 真実公開待ちのコンテンツ
	 */
	public List<MenuTreeBean> getFutureList(String userId, String role) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", userId);
		param.put("PARA_ROLE", role);
		param.put("PARA_SYSDATE", new Date());

		return this.sqlSessionTemplate.selectList("menuTree.getFutureList", param);
	}
	
	/**
	 * 予約公開待ちのコンテンツ
	 */
	public List<MenuTreeBean> getFutureListRsv(String userId, String role) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", userId);
		param.put("PARA_ROLE", role);
		param.put("PARA_SYSDATE", new Date());

		return this.sqlSessionTemplate.selectList("menuTree.getFutureListRsv", param);
	}
	
	/**
	 * テンプレートコンテンツ
	 */
	public List<MenuTreeBean> getTemplateList(String userId, String role) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", userId);
		param.put("PARA_ROLE", role);
		param.put("PARA_SYSDATE", new Date());

		return this.sqlSessionTemplate.selectList("menuTree.getTemplateList", param);
	}
	
	/**
	 * 期限切れコンテンツ
	 */
	public List<MenuTreeBean> getPastList(String userId, String role) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", userId);
		param.put("PARA_ROLE", role);
		param.put("PARA_SYSDATE", new Date());

		return this.sqlSessionTemplate.selectList("menuTree.getPastList", param);
	}
	
	/**
	 * 内部リンク遷移する時、ログインユーザー情報とPageIDにより閲覧権限チェック用
	 */
	public List<MenuTreeBean> getPagesWithPermission(LoginUserBean loginUser, String pageId) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", loginUser.getUserId());
		param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_GROUP", CommonUtil.getGroupSql(loginUser.getTopGroupList()));
		param.put("PARA_ROLE", loginUser.getRole());
		param.put("PARA_CONFIRM_FLAG", loginUser.getConfirmFlag());
		param.put("PARA_SYSDATE", new Date());

		return this.sqlSessionTemplate.selectList("menuTree.getPagesWithPermission", param);
	}

	/**
	 * 内部リンク指定と親コンテンツ指定用のTree生成
	 * 
	 */
	public List<MenuTreeBean> getSubTreeList(String pId, String urlFlag) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PID", pId);
		param.put("PARA_SYSDATE", new Date());
		param.put("PARA_URLFLAG", urlFlag);

		return this.sqlSessionTemplate.selectList("menuTree.getSubTreeList", param);
	}
	
	/**
	 * 内部リンクツリー用真実公開待ちのコンテンツ
	 */
	public List<MenuTreeBean> getFutureListForSubTree() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_SYSDATE", new Date());

		return this.sqlSessionTemplate.selectList("menuTree.getFutureListForSubTree", param);
	}
}
