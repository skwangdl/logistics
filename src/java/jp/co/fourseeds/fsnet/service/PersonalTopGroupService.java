
package jp.co.fourseeds.fsnet.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.dao.CommonDao;

import jp.co.fourseeds.fsnet.beans.personalTopGroup.PersonalTopGroupBean;
import jp.co.fourseeds.fsnet.beans.personalTopGroup.PersonalTopGroupDetailBean;
import jp.co.fourseeds.fsnet.beans.topGroup.TopGroupDetailBean;
import jp.co.fourseeds.fsnet.beans.userGroup.UserGroupBean;
import jp.co.fourseeds.fsnet.beans.userGroup.UserGroupDetailBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.DeptGroupDao;
import jp.co.fourseeds.fsnet.dao.PersonalTopGroupDao;
import jp.co.fourseeds.fsnet.dao.StoreGroupDao;
import jp.co.fourseeds.fsnet.dao.TopGroupDao;
import jp.co.fourseeds.fsnet.dao.UserGroupDao;

import jp.co.common.frame.service.BaseBusinessService;

/**
 * 個人用トップグループ情報機能サービス実装クラス
 * 
 * File Name: TopGroupService.java 
 * Created: 2016/01/19
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2016/02/02		    NTS        	       作成
 *
 **/
@Component
public class PersonalTopGroupService extends BaseBusinessService{

	@Autowired
	private PersonalTopGroupDao personalTopGroupDao;
	
	@Autowired
	private TopGroupDao topGroupDao;
	
	@Autowired
	private CommonDao commonDao;
	
	@Autowired
	private DeptGroupDao deptGroupDao;
	
	@Autowired
	private StoreGroupDao storeGroupDao;
	
	@Autowired
	private UserGroupDao userGroupDao;

	/**
	 * 個人用トップグループ情報を取る
	 * @param personalTopGroupBean　画面入力値
	 * @param strOrderBy　ソート
	 * @return　検索結果
	 */
	public List<PersonalTopGroupBean> getPersTopGroupList(PersonalTopGroupBean personalTopGroupBean, String strOrderBy) {
		return personalTopGroupDao.getPersTopGroupList(personalTopGroupBean, strOrderBy, getLoginUserBean());
	}
	
	/**
	 *  個人用トップグループIDによって、 個人用トップグループ情報と トップグループ所属する有効のみグループ情報を取得する。
	 * @param personalTopGroupBean　画面入力値
	 * @return　検索結果
	 */
	public PersonalTopGroupBean getPersTopGroupInfo(PersonalTopGroupBean personalTopGroupBean) {
		// 個人用トップグループID
		String personalTopGroupId = personalTopGroupBean.getSearchTopGroupId();
		// トップグループ情報検索テーブル
		String topGroupTable = "V_LEADING_GROUP_OPEN";
		// 部署グループ情報検索テーブル
		String deptGroupTable = "V_DEPARTMENT_GROUP_OPEN";
		// 店舗グループ情報検索テーブル
		String storeGroupTable = "V_STORE_GROUP_OPEN";
		// 個人用トップグループ情報を取得する
		PersonalTopGroupBean personalTopGroupInfo = personalTopGroupDao.getPersTopGroupHeader(personalTopGroupId);
		if(personalTopGroupInfo != null){
			// 個人用トップグループ所属する有効のみ部署グループ情報を取得する
			List<TopGroupDetailBean> deptGroupDetailList = topGroupDao.getTopGroupDetail_DeptGroup(personalTopGroupId, topGroupTable ,deptGroupTable);
			// 個人用トップグループ所属する有効のみ店舗グループ情報を取得する
			List<TopGroupDetailBean> storeGroupDetailList = topGroupDao.getTopGroupDetail_StoreGroup(personalTopGroupId, topGroupTable, storeGroupTable, getLoginUserBean());
			// 個人用トップグループ所属する有効のみユーザリスト情報を取得する。
			List<PersonalTopGroupDetailBean> userDetailList = personalTopGroupDao.getPersTopGroupDetail_PersUser(personalTopGroupId);
			// 個人用トップグループの共有者情報を取得する。
			List<PersonalTopGroupDetailBean> shareUserDetailList = personalTopGroupDao.getPersTopGroupDetail_ShareUser(personalTopGroupId);
			
			personalTopGroupInfo.setDeptGroupList(deptGroupDetailList);
			personalTopGroupInfo.setStoreGroupList(storeGroupDetailList);
			personalTopGroupInfo.setUserList(userDetailList);
			personalTopGroupInfo.setShareUserList(shareUserDetailList);
		}
		
		return personalTopGroupInfo;
	}

	/**
	 * 個人用トップグループ情報登録
	 * @param personalTopGroupBean 
	 * @throws ParseException 
	 */
	public void insertPersTopGroup(PersonalTopGroupBean personalTopGroupBean) throws ParseException {
		// ■■■トップグループヘッダー情報登録
		topGroupDao.insertTopGroupHeader(personalTopGroupBean);
		// サブ部署グループID
		String[] deptGroupId = StringUtil.split(personalTopGroupBean.getDeptGroupId(),",");
		for(int i = 0; i < deptGroupId.length; i++){
			TopGroupDetailBean subDeptGroupBean = new TopGroupDetailBean();
			// トップグループID
			subDeptGroupBean.setTopGroupId(personalTopGroupBean.getTopGroupId());
			// サブグループ反映予定日時
			subDeptGroupBean.setLgRefDate(personalTopGroupBean.getLgRefDate());
			// サブグループID
			subDeptGroupBean.setSubGroupId(deptGroupId[i]);
			// サブグループ区分
			subDeptGroupBean.setSubGroupFlag("D");
			// トップグループ詳細登録引数情報設定
			subDeptGroupBean.setCreateBy(personalTopGroupBean.getCreateBy());
			subDeptGroupBean.setCreateDate(personalTopGroupBean.getCreateDate());
			subDeptGroupBean.setUpdateBy(getLoginUserBean().getUserId());
			subDeptGroupBean.setUpdateDate(personalTopGroupBean.getUpdateDate());
			// ■■■トップグループの明細情報登録
			topGroupDao.insertTopGroupDetail(subDeptGroupBean);
		}
		
		// サブ店舗グループID
		String[] storeGroupId = StringUtil.split(personalTopGroupBean.getStoreGroupId(),",");
		for(int i = 0; i < storeGroupId.length; i++){
			TopGroupDetailBean subStoreGroupBean = new TopGroupDetailBean();
			// トップグループID
			subStoreGroupBean.setTopGroupId(personalTopGroupBean.getTopGroupId());
			// サブグループ反映予定日時
			subStoreGroupBean.setLgRefDate(personalTopGroupBean.getLgRefDate());
			// サブグループID
			subStoreGroupBean.setSubGroupId(storeGroupId[i]);
			// サブグループ区分
			subStoreGroupBean.setSubGroupFlag("S");
			// トップグループ詳細登録引数情報設定
			subStoreGroupBean.setCreateBy(personalTopGroupBean.getCreateBy());
			subStoreGroupBean.setCreateDate(personalTopGroupBean.getCreateDate());
			subStoreGroupBean.setUpdateBy(getLoginUserBean().getUserId());
			subStoreGroupBean.setUpdateDate(personalTopGroupBean.getUpdateDate());
			// ■■■トップグループの明細情報登録
			topGroupDao.insertTopGroupDetail(subStoreGroupBean);
		}
		
		//個人ユーザリストが入力された場合
		if(!StringUtil.isBlank(personalTopGroupBean.getUserId())){
			// ユーザID
			String[] userId = StringUtil.split(personalTopGroupBean.getUserId(),",");
			// ユーザグループIDを採番する
			String persUserGroupId = userGroupDao.getNewUserGroupId();
			TopGroupDetailBean subUserGroupBean = new TopGroupDetailBean();
			// トップグループID
			subUserGroupBean.setTopGroupId(personalTopGroupBean.getTopGroupId());
			// サブグループ反映予定日時
			subUserGroupBean.setLgRefDate(personalTopGroupBean.getLgRefDate());
			// サブグループID
			subUserGroupBean.setSubGroupId(persUserGroupId);
			// サブグループ区分
			subUserGroupBean.setSubGroupFlag("U");
			// トップグループ詳細登録引数情報設定
			subUserGroupBean.setCreateBy(personalTopGroupBean.getCreateBy());
			subUserGroupBean.setCreateDate(personalTopGroupBean.getCreateDate());
			subUserGroupBean.setUpdateBy(getLoginUserBean().getUserId());
			subUserGroupBean.setUpdateDate(personalTopGroupBean.getUpdateDate());
			// ■■■トップグループの明細情報登録
			topGroupDao.insertTopGroupDetail(subUserGroupBean);
			
			// ユーザグループIDを採番する
			UserGroupBean userGroupBean = new UserGroupBean();
			userGroupBean.setUserGroupId(persUserGroupId);
			userGroupBean.setUserGroupName(personalTopGroupBean.getTopGroupName());
			userGroupBean.setUserGroupIntro("");
			userGroupBean.setUgRefDate(personalTopGroupBean.getLgRefDate());
			userGroupBean.setPersonalGroupFlag("1");
			// 登録引数情報設定
			userGroupBean.setCreateBy(personalTopGroupBean.getCreateBy());
			userGroupBean.setCreateDate(personalTopGroupBean.getCreateDate());
			userGroupBean.setUpdateBy(getLoginUserBean().getUserId());
			userGroupBean.setUpdateDate(personalTopGroupBean.getUpdateDate());
			// ■■■ユーザグループヘッダー情報登録
			userGroupDao.insertUserGroup(userGroupBean);
			
			for(int i = 0; i < userId.length; i++){
				UserGroupDetailBean userGroupDetailBean = new UserGroupDetailBean();
				// ユーザグループID
				userGroupDetailBean.setUserGroupId(userGroupBean.getUserGroupId());
				// ユーザグループ反映予定日時
				userGroupDetailBean.setUgRefDate(userGroupBean.getUgRefDate());
				// ユーザID
				userGroupDetailBean.setUserId(userId[i]);
				// ユーザグループ詳細登録引数情報設定
				userGroupDetailBean.setCreateBy(userGroupBean.getCreateBy());
				userGroupDetailBean.setCreateDate(userGroupBean.getCreateDate());
				userGroupDetailBean.setUpdateBy(getLoginUserBean().getUserId());
				userGroupDetailBean.setUpdateDate(userGroupBean.getUpdateDate());
				// ■■■ユーザグループの明細情報登録
				userGroupDao.insertUserGroupDetail(userGroupDetailBean);
			}
		}
		
		// 共有対象者リストを取得
		List<PersonalTopGroupDetailBean> shareUserList = personalTopGroupBean.getShareUserList();
		if(!StringUtil.isBlank(shareUserList)){
			for(int i = 0; i < shareUserList.size(); i++){
				PersonalTopGroupDetailBean shareUserBean = shareUserList.get(i);
				// トップグループID
				shareUserBean.setTopGroupId(personalTopGroupBean.getTopGroupId());
				// 共有対象者詳細登録引数情報設定
				shareUserBean.setCreateBy(personalTopGroupBean.getCreateBy());
				shareUserBean.setCreateDate(personalTopGroupBean.getCreateDate());
				shareUserBean.setUpdateBy(getLoginUserBean().getUserId());
				shareUserBean.setUpdateDate(personalTopGroupBean.getUpdateDate());
				// ■■■トップグループの共有対象者情報登録
				personalTopGroupDao.insertShareUser(shareUserBean);
			}
		}
	}

	/**
	 * 個人用トップグループ情報更新
	 * @param personalTopGroupBean 
	 * @throws ParseException 
	 */
	public void updatePerTopGroupInfo(PersonalTopGroupBean personalTopGroupBean) throws ParseException {
		
		// 公開トップグループ情報の作成者と作成日付を取得
		BaseBean createInfo = commonDao.getDbCommonInfo("V_LEADING_GROUP_OPEN", "TOP_GROUP_ID", personalTopGroupBean.getTopGroupId());
		// 登録引数情報設定
		personalTopGroupBean.setCreateBy(createInfo.getCreateBy());
		personalTopGroupBean.setCreateDate(createInfo.getCreateDate());
		personalTopGroupBean.setUpdateBy(getLoginUserBean().getUserId());
		personalTopGroupBean.setUpdateDate(new Date());
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TOP_GROUP_ID", personalTopGroupBean.getTopGroupId());					// トップグループID
		param.put("PARA_REFLECTION_SCHEDULE_DATE", "");									// トップグループ反映予定日時
		// 個人ユーザリスト情報を物理削除する
		personalTopGroupDao.deletePersUserGroupInvalid(param);
		// 共有者情報を物理削除する
		personalTopGroupDao.deleteTopGroupShareInvalid(param);
		// トップグループ情報（両方）を物理削除する。
		topGroupDao.deleteTopGroupInfo(param);
		// トップグループ情報登録
		this.insertPersTopGroup(personalTopGroupBean);
	}

	/**
	 * 個人用トップグループ情報削除
	 * @param personalTopGroupBean 
	 */
	public void deletePersTopGroup(PersonalTopGroupBean personalTopGroupBean) {
		// トップグループ情報テーブルを論理削除する。
		topGroupDao.updateTopGroupInfoInvalid(getLoginUserBean().getUserId(), personalTopGroupBean);
		// 個人用ユーザリスト情報を論理削除する
		personalTopGroupDao.updatePersUserGroupInvalid(getLoginUserBean().getUserId(), personalTopGroupBean);
		// 共有対象者を論理削除する。
		personalTopGroupDao.updateTopGroupShareInvalid(getLoginUserBean().getUserId(), personalTopGroupBean);
		// コンテンツ閲覧権限トップグループ情報を論理削除する。
		topGroupDao.updateAuthLeadingGroupInvalid(getLoginUserBean().getUserId(), personalTopGroupBean.getTopGroupId());
		// コンテンツ閲覧権限トップグループ予約情報を物理削除する。
		topGroupDao.deleteAuthLeadingGroupRsv(personalTopGroupBean.getTopGroupId());
	}

	/**
	 * 次の層グループ情報を取得
	 * @param subGroupId 
	 * @param subGroupFlag 
	 */
	@SuppressWarnings("rawtypes")
	public List getSubGroupList(String subGroupId, String subGroupFlag) {
		List subGroupList = new ArrayList();
		
		// 部署グループ検索の場合
		if("D".equals(subGroupFlag)){
			subGroupList = deptGroupDao.getDeptGroupDetail(subGroupId, "V_DEPARTMENT_GROUP_OPEN");
		// 店舗グループ検索の場合
		} else if ("S".equals(subGroupFlag)){
			subGroupList = storeGroupDao.getStoreGroupDetail(subGroupId, "V_STORE_GROUP_OPEN", "");
		}
		return subGroupList;
	}
}