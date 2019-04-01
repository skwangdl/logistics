
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

import jp.co.fourseeds.fsnet.beans.topGroup.TopGroupBean;
import jp.co.fourseeds.fsnet.beans.topGroup.TopGroupDetailBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.DeptGroupDao;
import jp.co.fourseeds.fsnet.dao.StoreGroupDao;
import jp.co.fourseeds.fsnet.dao.TopGroupDao;
import jp.co.fourseeds.fsnet.dao.UserGroupDao;

import jp.co.common.frame.service.BaseBusinessService;

/**
 * トップグループ情報機能サービス実装クラス
 * 
 * File Name: TopGroupService.java 
 * Created: 2016/01/19
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2016/01/19		    NTS        	       作成
 *　1.0		2016/01/19		    NTS        	       見直し修正
 **/
@Component
public class TopGroupService extends BaseBusinessService{

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
	 * トップグループ情報を取る
	 * @param topGroupBean　画面入力値
	 * @param strOrderBy　ソート
	 * @return　検索結果
	 */
	public List<TopGroupBean> getTopGroupList(TopGroupBean topGroupBean, String strOrderBy) {
		String searchSql = "";
		// '1'(システム管理者)以外の場合
		if(!"1".equals(getLoginUserBean().getRole())){
			searchSql = "topGroup.getTopGroupList_Open";
		}else{
			if("0".equals(topGroupBean.getSearchOriginType())){
				// 全て
				searchSql = "topGroup.getTopGroupList_All";
			}else if("1".equals(topGroupBean.getSearchOriginType())){
				// 現在有効なもの全て
				searchSql = "topGroup.getTopGroupList_Valid";
			}else if("2".equals(topGroupBean.getSearchOriginType())){
				// 予約情報のみ
				searchSql = "topGroup.getTopGroupList_Rsv";
			}
		}
		
		return topGroupDao.getTopGroupList(searchSql, topGroupBean, strOrderBy);
	}
	
	/**
	 *  トップグループIDと検索テーブルによって、 トップグループ情報と トップグループ所属する有効のみグループ情報を取得する。
	 * @param topGroupBean　画面入力値
	 * @return　検索結果
	 */
	public TopGroupBean getTopGroupInfo(TopGroupBean topGroupBean) {
		// トップグループID
		String topGroupId = topGroupBean.getSearchTopGroupId();
		// トップグループ情報検索テーブル
		String topGroupTable = "";
		// 部署グループ情報検索テーブル
		String deptGroupTable = "";
		// 店舗グループ情報検索テーブル
		String storeGroupTable = "";
		// ユーザグループ情報検索テーブル
		String userGroupTable = "";
		// システム利用区分が'1'(システム管理者)以外の場合
		if(!"1".equals(getLoginUserBean().getRole())){
			topGroupTable = "V_LEADING_GROUP_OPEN";
			deptGroupTable = "V_DEPARTMENT_GROUP_OPEN";
			storeGroupTable = "V_STORE_GROUP_OPEN";
			userGroupTable = "V_USER_GROUP_OPEN";
		} else {
			
			// 公開の場合
			if("1".equals(topGroupBean.getEditFlag())){
				topGroupTable = "V_LEADING_GROUP_OPEN";
			// 予約の場合
			} else {
				topGroupTable = "V_LEADING_GROUP_RESERVE";
			}
			deptGroupTable = "V_DEPARTMENT_GROUP_ALL";
			storeGroupTable = "V_STORE_GROUP_ALL";
			userGroupTable = "V_USER_GROUP_ALL";
		}
		// トップグループ情報を取得する
		TopGroupBean topGroupInfo = topGroupDao.getTopGroupHeader(topGroupId, topGroupTable);
		if(topGroupInfo != null){
			// トップグループ所属する有効のみ部署グループ情報を取得する
			List<TopGroupDetailBean> deptGroupDetailList = topGroupDao.getTopGroupDetail_DeptGroup(topGroupId, topGroupTable ,deptGroupTable);
			// トップグループ所属する有効のみ店舗グループ情報を取得する
			List<TopGroupDetailBean> storeGroupDetailList = topGroupDao.getTopGroupDetail_StoreGroup(topGroupId, topGroupTable, storeGroupTable, getLoginUserBean());
			// トップグループ所属する有効のみユーザグループ情報を取得する
			List<TopGroupDetailBean> userGroupDetailList = topGroupDao.getTopGroupDetail_UserGroup(topGroupId, topGroupTable, userGroupTable);
			topGroupInfo.setDeptGroupList(deptGroupDetailList);
			topGroupInfo.setStoreGroupList(storeGroupDetailList);
			topGroupInfo.setUserGroupList(userGroupDetailList);
		}
		return topGroupInfo;
	}

	/**
	 * トップグループIDを採番する
	 * 
	 */
	public String getNewTopGroupId() {
		return topGroupDao.getNewTopGroupId();
	}

	/**
	 * 登録済データとのトップグループ名称重複データを取得
	 * 
	 */
	public boolean checkTopGroupNmRepeat(TopGroupBean topGroupBean, String eventType) {
		int count = topGroupDao.getTopGroupNmCheckInfo(topGroupBean, eventType);
		// 登録済データとのトップグループ名称重複データを存在の場合、メッセージボックスを出す
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * トップグループ情報登録
	 * @param userGroupBean 
	 */
	public void insertTopGroup(TopGroupBean topGroupBean) {
		
		// ■■■トップグループヘッダー情報登録
		topGroupDao.insertTopGroupHeader(topGroupBean);

		// サブ部署グループID
		String[] deptGroupId = StringUtil.split(topGroupBean.getDeptGroupId(),",");
		for(int i = 0; i < deptGroupId.length; i++){
			TopGroupDetailBean subDeptGroupBean = new TopGroupDetailBean();
			// トップグループID
			subDeptGroupBean.setTopGroupId(topGroupBean.getTopGroupId());
			// サブグループ反映予定日時
			subDeptGroupBean.setLgRefDate(topGroupBean.getLgRefDate());
			// サブグループID
			subDeptGroupBean.setSubGroupId(deptGroupId[i]);
			// サブグループ区分
			subDeptGroupBean.setSubGroupFlag("D");
			// トップグループ詳細登録引数情報設定
			subDeptGroupBean.setCreateBy(topGroupBean.getCreateBy());
			subDeptGroupBean.setCreateDate(topGroupBean.getCreateDate());
			subDeptGroupBean.setUpdateBy(getLoginUserBean().getUserId());
			subDeptGroupBean.setUpdateDate(topGroupBean.getUpdateDate());
			// ■■■トップグループの明細情報登録
			topGroupDao.insertTopGroupDetail(subDeptGroupBean);
		}
		
		// サブ店舗グループID
		String[] storeGroupId = StringUtil.split(topGroupBean.getStoreGroupId(),",");
		for(int i = 0; i < storeGroupId.length; i++){
			TopGroupDetailBean subStoreGroupBean = new TopGroupDetailBean();
			// トップグループID
			subStoreGroupBean.setTopGroupId(topGroupBean.getTopGroupId());
			// サブグループ反映予定日時
			subStoreGroupBean.setLgRefDate(topGroupBean.getLgRefDate());
			// サブグループID
			subStoreGroupBean.setSubGroupId(storeGroupId[i]);
			// サブグループ区分
			subStoreGroupBean.setSubGroupFlag("S");
			// トップグループ詳細登録引数情報設定
			subStoreGroupBean.setCreateBy(topGroupBean.getCreateBy());
			subStoreGroupBean.setCreateDate(topGroupBean.getCreateDate());
			subStoreGroupBean.setUpdateBy(getLoginUserBean().getUserId());
			subStoreGroupBean.setUpdateDate(topGroupBean.getUpdateDate());
			// ■■■トップグループの明細情報登録
			topGroupDao.insertTopGroupDetail(subStoreGroupBean);
		}
		
		// サブユーザグループID
		String[] userGroupId = StringUtil.split(topGroupBean.getUserGroupId(),",");
		for(int i = 0; i < userGroupId.length; i++){
			TopGroupDetailBean subUserGroupBean = new TopGroupDetailBean();
			// トップグループID
			subUserGroupBean.setTopGroupId(topGroupBean.getTopGroupId());
			// サブグループ反映予定日時
			subUserGroupBean.setLgRefDate(topGroupBean.getLgRefDate());
			// サブグループID
			subUserGroupBean.setSubGroupId(userGroupId[i]);
			// サブグループ区分
			subUserGroupBean.setSubGroupFlag("U");
			// トップグループ詳細登録引数情報設定
			subUserGroupBean.setCreateBy(topGroupBean.getCreateBy());
			subUserGroupBean.setCreateDate(topGroupBean.getCreateDate());
			subUserGroupBean.setUpdateBy(getLoginUserBean().getUserId());
			subUserGroupBean.setUpdateDate(topGroupBean.getUpdateDate());
			// ■■■トップグループの明細情報登録
			topGroupDao.insertTopGroupDetail(subUserGroupBean);
		}
	}

	/**
	 * トップグループ情報更新
	 * @param topGroupBean 
	 * @throws ParseException 
	 */
	public void updateTopGroupInfo(TopGroupBean topGroupBean) throws ParseException {
		
		// 公開トップグループ情報の作成者と作成日付を取得
		BaseBean createInfo = commonDao.getDbCommonInfo("V_LEADING_GROUP_OPEN", "TOP_GROUP_ID", topGroupBean.getTopGroupId());
		// 公開トップグループ情報の作成者と作成日付がない場合、予約トップグループ情報の取得
		if(StringUtil.isBlank(createInfo)){
			createInfo = commonDao.getDbCommonInfo("V_LEADING_GROUP_RESERVE", "TOP_GROUP_ID", topGroupBean.getTopGroupId());
		} 
		// 登録引数情報設定
		topGroupBean.setCreateBy(createInfo.getCreateBy());
		topGroupBean.setCreateDate(createInfo.getCreateDate());
		topGroupBean.setUpdateBy(getLoginUserBean().getUserId());
		topGroupBean.setUpdateDate(new Date());
		
		// システム日付
		Date dateNow = StringUtil.getNowDate("yyyy/MM/dd");
		// 反映予定日時
		Date lgRefDate = StringUtil.parseTheDate(topGroupBean.getLgRefDate(), "yyyy/MM/dd");
		// 画面入力された反映予定日 > システム日付の場合
		if(dateNow.before(lgRefDate)){
			// トップグループの予約情報を取得する
			TopGroupBean topGroupInfo =  topGroupDao.getTopGroupHeader(topGroupBean.getTopGroupId(), "V_LEADING_GROUP_RESERVE");
			// トップグループの予約情報存在する場合、トップグループの予約情報を物理削除する
			if(!StringUtil.isBlank(topGroupInfo)){
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("PARA_TOP_GROUP_ID", topGroupInfo.getTopGroupId());				// トップグループID
				param.put("PARA_REFLECTION_SCHEDULE_DATE", topGroupInfo.getLgRefDate());	// トップグループ反映予定日時
				
				// トップグループ情報（予約）を物理削除する。
				topGroupDao.deleteTopGroupInfo(param);
			}
		// 画面入力された反映予定日 <= システム日付の場合
		} else {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("PARA_TOP_GROUP_ID", topGroupBean.getTopGroupId());					// トップグループID
			param.put("PARA_REFLECTION_SCHEDULE_DATE", "");									// トップグループ反映予定日時
			// トップグループ情報（両方）を物理削除する。
			topGroupDao.deleteTopGroupInfo(param);
		}
		// トップグループ情報登録
		this.insertTopGroup(topGroupBean);
	}

	/**
	 * トップグループ情報削除
	 * @param topGroupBean 
	 */
	public void deleteTopGroup(TopGroupBean topGroupBean) {
		// 検索テーブル区分　1:公開　2:予約
		String editFlag = topGroupBean.getEditFlag();
		// 削除データは予約である場合
		if("2".equals(editFlag)){
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("PARA_TOP_GROUP_ID", topGroupBean.getTopGroupId());					// トップグループID
			param.put("PARA_REFLECTION_SCHEDULE_DATE", topGroupBean.getLgRefDate());		// トップグループ反映予定日時

			// トップグループ情報（予約）を物理削除する。
			topGroupDao.deleteTopGroupInfo(param);
		// 削除データは公開である場合
		} else {
			// トップグループの予約情報を取得する
			TopGroupBean topGroupInfo = topGroupDao.getTopGroupHeader(topGroupBean.getTopGroupId(), "V_LEADING_GROUP_RESERVE");
			// トップグループの予約情報存在する場合、トップグループの予約情報を物理削除する
			if(!StringUtil.isBlank(topGroupInfo)){
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("PARA_TOP_GROUP_ID", topGroupInfo.getTopGroupId());				// トップグループID
				param.put("PARA_REFLECTION_SCHEDULE_DATE", topGroupInfo.getLgRefDate());	// トップグループ反映予定日時

				// トップグループ情報（予約）を物理削除する。
				topGroupDao.deleteTopGroupInfo(param);
			}
			// トップグループ情報テーブルを論理削除する。
			topGroupDao.updateTopGroupInfoInvalid(getLoginUserBean().getUserId(), topGroupBean);
			// コンテンツ閲覧権限トップグループ情報を論理削除する。
			topGroupDao.updateAuthLeadingGroupInvalid(getLoginUserBean().getUserId(), topGroupBean.getTopGroupId());
			// コンテンツ閲覧権限トップグループ予約情報を物理削除する。
			topGroupDao.deleteAuthLeadingGroupRsv(topGroupBean.getTopGroupId());
		}
	}

	/**
	 * 次の層グループ情報を取得
	 * @param subGroupId 
	 * @param subGroupFlag 
	 */
	@SuppressWarnings("rawtypes")
	public List getSubGroupList(String subGroupId, String subGroupFlag) {
		// ユーザ権限
		String role = getLoginUserBean().getRole();
		List subGroupList = new ArrayList();
		// 部署グループ検索の場合
		if("D".equals(subGroupFlag)){
			// システム利用区分が'1'(システム管理者)場合
			if("1".equals(role)){
				subGroupList = deptGroupDao.getDeptGroupDetail(subGroupId, "V_DEPARTMENT_GROUP_ALL");
			} else {
				subGroupList = deptGroupDao.getDeptGroupDetail(subGroupId, "V_DEPARTMENT_GROUP_OPEN");
			}
		// 店舗グループ検索の場合
		} else if ("S".equals(subGroupFlag)){
			// システム利用区分が'1'(システム管理者)場合
			if("1".equals(role)){
				subGroupList = storeGroupDao.getStoreGroupDetail(subGroupId, "V_STORE_GROUP_ALL", "");
			} else {
				subGroupList = storeGroupDao.getStoreGroupDetail(subGroupId, "V_STORE_GROUP_OPEN", "");
			}
		// ユーザグループ検索の場合
		} else if ("U".equals(subGroupFlag)){
			// システム利用区分が'1'(システム管理者)場合
			if("1".equals(role)){
				subGroupList = userGroupDao.getUserGroupDetail(subGroupId, "V_USER_GROUP_ALL");
			} else {
				subGroupList = userGroupDao.getUserGroupDetail(subGroupId, "V_USER_GROUP_OPEN");
			}
		}
		return subGroupList;
	}
	
	/**
	 * 部署リンクをクリック、階層レベル検索
	 * @param deptGroupBean 
	 */
	public String getDeptLevel(Map<String, Object> param, String subGroupFlag) {
		String level = new String();
		
		if ("D".equals(subGroupFlag)) {
			level = deptGroupDao.getDeptLevel(param);
		} else if ("S".equals(subGroupFlag)){
			level = storeGroupDao.getDeptLevel(param);
		}
		
		return level;
	}
	
	/**
	 * 会社リンクをクリック、所属統括検索
	 * @param deptGroupBean 
	 */
	public List<Map<String, String>> getSecondDeptList(Map<String, Object> param, String subGroupFlag) {
		List<Map<String, String>> subGroupList = new ArrayList<Map<String, String>>();
		
		if ("D".equals(subGroupFlag)) {
			subGroupList = deptGroupDao.getSecondDeptList(param);
		} else if ("S".equals(subGroupFlag)){
			subGroupList = storeGroupDao.getSecondDeptList(param);
		}
		
		return subGroupList;
	}
	
	/**
	 * 統括リンクをクリック、所属事業検索
	 * @param deptGroupBean 
	 */
	public List<Map<String, String>> getThirdDeptList(Map<String, Object> param, String subGroupFlag) {
		List<Map<String, String>> subGroupList = new ArrayList<Map<String, String>>();
		
		if ("D".equals(subGroupFlag)) {
			subGroupList = deptGroupDao.getThirdDeptList(param);
		} else if ("S".equals(subGroupFlag)){
			subGroupList = storeGroupDao.getThirdDeptList(param);
		}
		
		return subGroupList;
	}
	
	/**
	 * 事業リンクをクリック、所属営業部検索
	 * @param deptGroupBean 
	 */
	public List<Map<String, String>> getFourthDeptList(Map<String, Object> param, String subGroupFlag) {
		List<Map<String, String>> subGroupList = new ArrayList<Map<String, String>>();
		
		if ("D".equals(subGroupFlag)) {
			subGroupList = deptGroupDao.getFourthDeptList(param);
		} else if ("S".equals(subGroupFlag)){
			subGroupList = storeGroupDao.getFourthDeptList(param);
		}
		
		return subGroupList;
	}
	
	/**
	 * 営業部リンクをクリック、所属店舗検索
	 * @param deptGroupBean 
	 */
	public List<Map<String, String>> getFifthDeptList(Map<String, Object> param, String subGroupFlag) {
		List<Map<String, String>> subGroupList = new ArrayList<Map<String, String>>();
		
		if ("S".equals(subGroupFlag)){
			subGroupList = storeGroupDao.getFifthDeptList(param);
		}
		return subGroupList;
	}
}