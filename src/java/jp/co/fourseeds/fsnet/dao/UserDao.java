package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.UserBean;
import jp.co.fourseeds.fsnet.common.ConstantContainer;

/**
 * ユーザ情報機能Dao実装クラス
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2016/01/27		    NTS        	       作成
 * 1.1		2017/09/05			NTS			見直し修正
 *
 **/
@Repository
public class UserDao extends BaseDao {
	
	/**
	 * ユーザ情報を取得
	 * @param userBean
	 * @param strOrderBy
	 * @return List
	 */
	public List<UserBean> getUserList(UserBean userBean, String strOrderBy, LoginUserBean loginUser) {
		// 検索条件を設定
		Map<String, Object> param = new HashMap<String, Object>();
		// 権限
		param.put("PARA_ROLE", loginUser.getRole());
		// 社員番号
		param.put("PARA_USER_ID", userBean.getSearchUserId());
		// 氏名
		param.put("PARA_USER_NAME", userBean.getSearchUserName());
		// 店舗
		param.put("PARA_STORE_NAME", userBean.getSearchStoreName());
		// 本部
		param.put("PARA_DEPT_NAME", userBean.getSearchDeptName());
		// 従業員区分
		param.put("PARA_PEMPLOYEE_ID", userBean.getSearchPemployeeId());
		// 表示フラグ
		param.put("PARA_SHOWFLAG_ID", userBean.getSearchShowFlag());
		// ソート条件
		param.put("PARA_ORDER_BY", strOrderBy);
		// 従業員区分
		param.put("USER_DIVISION_KEYCODE", ConstantContainer.USER_DIVISION_KEYCODE);
		// 店舗区分
		param.put("STORE_DIVISION_KEYCODE", ConstantContainer.STORE_DIVISION_KEYCODE);
		// 代行の場合、「アルバイト（代行）」で表示
		param.put("PARTTIME_NAME", "（代行）");
		
		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("user.getUserList", param);
	}
	
	/**
	 * ユーザ詳細情報を取得
	 * @param userBean
	 * @return UserBean
	 */
	public UserBean getUserDetailInfo(UserBean userBean) {
		// 検索条件を設定
		Map<String, Object> param = new HashMap<String, Object>();
		// 社員番号
		param.put("PARA_USER_ID", userBean.getUserId());
		// 従業員区分
		param.put("USER_DIVISION_KEYCODE", ConstantContainer.USER_DIVISION_KEYCODE);
		// 従業員区分
		param.put("SEX_KEYCODE", ConstantContainer.SEX_KEYCODE);
		// システム利用区分
		param.put("ROLE", ConstantContainer.ROLE);
		// 会社区分
		param.put("COMPANY_KEYCODE", ConstantContainer.COMPANY_KEYCODE);
		// 統括区分
		param.put("UNIFICATION_KEYCODE", ConstantContainer.UNIFICATION_KEYCODE);
		// 事業区分
		param.put("BUSINESS_KEYCODE", ConstantContainer.BUSINESS_KEYCODE);
		// 営業部区分
		param.put("SALES_KEYCODE", ConstantContainer.SALES_KEYCODE);
		
		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectOne("user.getUserDetailInfo", param);
	}
	
	/**
	 * イメージ情報を取得
	 * @param userBean
	 * @return UserBean
	 */
	public byte[] getImgDat(String userId) {
		// 検索条件を設定
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userId);
		
		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectOne("user.getImgDat", param);
	}
	
	/**
	 * ユーザイメージ件数を取得
	 * @param userBean
	 * @return 検索結果
	 */
	public int getImgCount(UserBean userBean) {
		// 検索条件を設定
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userBean.getUserId());
		
		//　DBから検索結果を取得
		return (Integer)this.sqlSessionTemplate.selectOne("user.getImgCount", param);
	}
	
	/**
	 * ユーザイメージ情報を登録
	 * @param userBean
	 */
	public void insertUserImgMaster(UserBean userBean, LoginUserBean loginUser) {
		// 作成者更新者を設定
		setUserInfo(userBean, loginUser);
		
		this.sqlSessionTemplate.insert("user.insertUserImgMaster", userBean);
	}
	
	/**
	 * ユーザイメージ情報を更新
	 * @param userBean
	 */
	public void updateUserImgMaster(UserBean userBean, LoginUserBean loginUser) {
		// 作成者更新者を設定
		setUserInfo(userBean, loginUser);
		
		this.sqlSessionTemplate.insert("user.updateUserImgMaster", userBean);
	}
	
	/**
	 * ユーザ情報を更新
	 * @param userBean
	 */
	public void updateUserMaster(UserBean userBean, LoginUserBean loginUser) {
		// 更新者を設定
		setUserInfo(userBean, loginUser);
		userBean.setLoginRoleId(loginUser.getRole());
		
		this.sqlSessionTemplate.update("user.updateUserMaster", userBean);
	}
	
	/**
	 * ユーザグループ明細情報既存の変更ユーザ物理削除
	 * @param userBean
	 */
	public void deleteUserGroup(UserBean userBean) {
		// 検索条件を設定
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userBean.getUserId());
		param.put("PARA_OLD_ROLE", userBean.getOldRoleId());
		param.put("USER_GROUP_ROLE", ConstantContainer.USER_GROUP_ROLE);
		this.sqlSessionTemplate.delete("user.deleteUserGroup", param);
	}
	
	/**
	 * ユーザグループ情報取得
	 * @param userBean
	 */
	public UserBean saerchUserGroupInfo(UserBean userBean) {
		// 検索条件を設定
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_ROLE", userBean.getRoleId());
		param.put("USER_GROUP_ROLE", ConstantContainer.USER_GROUP_ROLE);
		return this.sqlSessionTemplate.selectOne("user.searchUserGroup", param);
	}
	
	/**
	 * ユーザグループを更新
	 * @param userBean
	 */
	public void insertUserGroupInfo(UserBean userBean, LoginUserBean loginUser) {
		// 作成者更新者を設定
		setUserInfo(userBean, loginUser);
		
		this.sqlSessionTemplate.insert("user.insertUserGroup", userBean);
	}
	
	/**
	 * 作成者更新者を設定
	 * @param userBean
	 */
	public void setUserInfo(UserBean userBean, LoginUserBean loginUser) {
		userBean.setCreateBy(loginUser.getUserId());
		userBean.setUpdateBy(loginUser.getUserId());
	}
}