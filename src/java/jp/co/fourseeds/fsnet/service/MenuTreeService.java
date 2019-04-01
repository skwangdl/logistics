package jp.co.fourseeds.fsnet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.MenuTreeBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.MenuTreeDao;

import jp.co.common.frame.service.BaseBusinessService;

/**
 * <p>
 * MenuとTreeを作成するService
 * </p>
 * 
 * @author NTS
 * @version 1.0.0 : 2015.09.14 新規作成
 *
 **/
@Component
public class MenuTreeService extends BaseBusinessService{

	@Autowired
	private MenuTreeDao menuTreeDao;

	/**
	 * システム利用区分が「1」システム管理者の場合、Menuを作成する
	 */
	public List<MenuTreeBean> getMenuList() {
		return menuTreeDao.getMenuList();
	}
	
	public MenuTreeBean getProfileInfo() {
		return menuTreeDao.getProfileInfo();
	}

	/**
	 * システム利用区分が「1」システム管理者以外の場合、Menuを作成する
	 */
	public List<MenuTreeBean> getMenuList(String userId, List<String> groupList, String role) {
		return menuTreeDao.getMenuList(userId, groupList, role);
	}

	/**
	 * 引渡されたPageIDにより、すべての親PageIDを抽出してからListを格納する
	 */
	public void getAllParentPageList(String pageId, List<String> list) {
		if (!"0".equals(pageId)) {
			String parentPageId = "0";
			parentPageId = menuTreeDao.getAllParentPageList(pageId);
			if(parentPageId == null){
				list = new ArrayList<String>();
			} else {
				list.add(parentPageId);
				getAllParentPageList(parentPageId, list);
			}
		}
	}

	/**
	 * ログインユーザ情報とすべて親PageIDリストにより、画面左側のTree情報を取得する
	 */
	public List getTreeList(LoginUserBean loginUser, List<String> allParentPageList) {

		List list = new ArrayList();
		List<MenuTreeBean> resultList = null;
		for (int i = 0; i < allParentPageList.size(); i++) {
			String parentPageId = (String) allParentPageList.get(i);
			if (!"1".equals(loginUser.getRole())) {
				//システム利用区分が「1」システム管理者以外の場合、Treeを作成する
				resultList = menuTreeDao.getTreeListByRole(loginUser, parentPageId);
			} 
			else {
				//システム利用区分が「1」システム管理者の場合、Treeを作成する
				resultList = menuTreeDao.getTreeListNoRole(parentPageId);
			}
			list.add(resultList);
		}
		return list;
	}
	
	/**
	 * 引渡されたPageIDはtargetIdの子孫の場合、Trueを戻る
	 */
	public boolean isParent(String pageId,String parentPageId) {
		if(StringUtil.isEmpty(pageId)||pageId.equals("0")){
			return false;
		}
		while(!pageId.equals(parentPageId)){
			pageId = menuTreeDao.getParentPageId(pageId);
			if(StringUtil.isEmpty(pageId)||pageId.equals("0")){
				return false;
			}
		}
		return true;		
	}

	/**
	 * 作成中のコンテンツ
	 */
	public List<MenuTreeBean> getUnConfirmList(String userId, String role) {
		//真実コンテンツ
		List<MenuTreeBean> resultList = menuTreeDao.getUnConfirmList(userId, role);
		//予約コンテンツ
		List<MenuTreeBean> resultListRsv= menuTreeDao.getUnConfirmListRsv(userId,role);

		MenuTreeBean bean=null;
		if(resultListRsv!=null) {
			for(int i=0;i<resultListRsv.size();i++) {
				bean=(MenuTreeBean) resultListRsv.get(i);
				bean.setNodeName("※" + bean.getNodeName());
				resultListRsv.set(i, bean);
				resultList.add(resultListRsv.get(i));
			}
		}
		return resultList;
	}

	/**
	 * 公開待ちのコンテンツ
	 */
	public List<MenuTreeBean> getFutureList(String userId, String role) {
		//真実コンテンツ
		List<MenuTreeBean> resultList = menuTreeDao.getFutureList(userId, role);
		//予約コンテンツ
		List<MenuTreeBean> resultListRsv = menuTreeDao.getFutureListRsv(userId,role);

		if(resultListRsv!=null) {
			for(int i=0;i<resultListRsv.size();i++) {
				resultList.add(resultListRsv.get(i));
			}
		}
		return resultList;
	}
	
	/**
	 * テンプレートコンテンツ
	 */
	public List<MenuTreeBean> getTemplateList(String userId, String role) {
		return menuTreeDao.getTemplateList(userId, role);
	}
	
	/**
	 * 期限切れコンテンツ
	 */
	public List<MenuTreeBean> getPastList(String userId, String role) {
		return menuTreeDao.getPastList(userId, role);
	}
	
	/**
	 * 内部リンク遷移する時、ログインユーザー情報とPageIDにより閲覧権限チェック用
	 */
	public boolean isPageWithPermission(LoginUserBean loginUser, String pageId) {
		if("1".equals(loginUser.getRole())){
			return true;
		} else {
			return menuTreeDao.getPagesWithPermission(loginUser, pageId).size() > 0;
		}
	}
	
	/**
	 * すべて親PageIDリストとサブツリー区分により情報を取得する
	 * urlFlag⇒0:内部リンク指定　　1:コンテンツ作成配置位置指定
	 */
	public List getSubTreeList(List<String> allParentPageList, String urlFlag) {

		List list = new ArrayList();
		int size = allParentPageList.size();
	
		for (int i = 0; i < size; i++) {
			String pId = (String) allParentPageList.get(i);
			List<MenuTreeBean> resultList = menuTreeDao.getSubTreeList(pId, urlFlag);
			list.add(resultList);
		}
	
		return list;
	}
	
	/**
	 * 内部リンクツリーと親コンテンツ指定用
	 */
	public List<MenuTreeBean> getFutureListForSubTree() {
		return menuTreeDao.getFutureListForSubTree();
	}
	
	/**
	 * サブツリーの子コンテンツリスト取得
	 */
	public List<MenuTreeBean> getSubTreeChildrenList(String pageId, String urlFlag) {
		return menuTreeDao.getSubTreeList(pageId, urlFlag);
	}
}