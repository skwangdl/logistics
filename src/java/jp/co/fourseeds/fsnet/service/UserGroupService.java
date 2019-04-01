
package jp.co.fourseeds.fsnet.service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.dao.CommonDao;

import jp.co.fourseeds.fsnet.beans.userGroup.UserGroupBean;
import jp.co.fourseeds.fsnet.beans.userGroup.UserGroupDetailBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.TopGroupDao;
import jp.co.fourseeds.fsnet.dao.UserGroupDao;

import jp.co.common.frame.service.BaseBusinessService;

/**
 * ユーザグループ情報機能サービス実装クラス
 * 
 * File Name: DeptGroupService.java 
 * Created: 2015/12/22
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2016/01/04		    NTS        	       作成
 * 1.1      2017/11/17          NTS            見直し修正
 **/
@Component
public class UserGroupService extends BaseBusinessService{

	@Autowired
	private UserGroupDao userGroupDao;
	
	@Autowired
	private TopGroupDao topGroupDao;
	
	@Autowired
	private CommonDao commonDao;

	/**
	 * ユーザグループ情報を取る
	 * @param userGroupBean　画面入力値
	 * @param strOrderBy　ソート
	 * @return　検索結果
	 */
	public List<UserGroupBean> getUserGroupList(UserGroupBean userGroupBean, String strOrderBy) {
		String searchSql = "";
		// '1'(システム管理者)以外の場合
		if(!"1".equals(getLoginUserBean().getRole())){
			searchSql = "userGroup.getUserGroupList_Open";
		}else{
			if("0".equals(userGroupBean.getSearchOriginType())){
				// 全て
				searchSql = "userGroup.getUserGroupList_All";
			}else if("1".equals(userGroupBean.getSearchOriginType())){
				// 現在有効なもの全て
				searchSql = "userGroup.getUserGroupList_Valid";
			}else if("2".equals(userGroupBean.getSearchOriginType())){
				// 予約情報のみ
				searchSql = "userGroup.getUserGroupList_Rsv";
			}
		}
		
		return userGroupDao.getUserGroupList(searchSql, userGroupBean, strOrderBy);
	}
	
	/**
	 *  ユーザグループIDと検索テーブルによって、 ユーザグループ情報と ユーザグループ所属する有効のみ ユーザ情報を取得する。
	 * @param userGroupBean　画面入力値
	 * @return　検索結果
	 */
	public UserGroupBean getUserGroupInfo(UserGroupBean userGroupBean) {
		// ユーザグループID
		String userGroupId = userGroupBean.getSearchUserGroupId();
		// ユーザグループ情報検索テーブル
		String userGroupTable = "";
		// システム利用区分が'1'(システム管理者)以外の場合
		if(!"1".equals(getLoginUserBean().getRole())){
			userGroupTable = "V_USER_GROUP_OPEN";
		} else {
			// 公開の場合
			if("1".equals(userGroupBean.getEditFlag())){
				userGroupTable = "V_USER_GROUP_OPEN";
			// 予約の場合
			} else {
				userGroupTable = "V_USER_GROUP_RESERVE";
			}
		}
		UserGroupBean userGroupInfo = userGroupDao.getUserGroupHeader(userGroupId, userGroupTable);
		if(userGroupInfo != null){
			List<UserGroupDetailBean> userGroupDetailList = userGroupDao.getUserGroupDetail(userGroupId, userGroupTable);
			userGroupInfo.setUserList(userGroupDetailList);
		}
		return userGroupInfo;
	}

	/**
	 * ユーザグループIDを採番する
	 * 
	 */
	public String getNewUserGroupId() {
		return userGroupDao.getNewUserGroupId();
	}

	/**
	 * 登録済データとのユーザグループ名称重複データを取得
	 * 
	 */
	public boolean checkUserGroupNmRepeat(UserGroupBean userGroupBean, String eventType) {
		int count = userGroupDao.getUserGroupNmCheckInfo(userGroupBean, eventType);
		// 登録済データとのユーザグループ名称重複データを存在の場合、メッセージボックスを出す
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * ユーザグループ情報登録
	 * @param userGroupBean 
	 */
	public void insertUserGroup(UserGroupBean userGroupBean) {
		
		// ■■■ユーザグループヘッダー情報登録
		userGroupDao.insertUserGroup(userGroupBean);

		// ユーザID
		String[] userId = StringUtil.split(userGroupBean.getUserId(),",");
		for(int i = 0; i < userId.length; i++){
			UserGroupDetailBean userGroupDetailBean = new UserGroupDetailBean();
			// ユーザーグループID
			userGroupDetailBean.setUserGroupId(userGroupBean.getUserGroupId());
			// ユーザーグループ反映予定日時
			userGroupDetailBean.setUgRefDate(userGroupBean.getUgRefDate());
			// ユーザーID
			userGroupDetailBean.setUserId(userId[i]);
			// ユーザーグループ詳細登録引数情報設定
			userGroupDetailBean.setCreateBy(userGroupBean.getCreateBy());
			userGroupDetailBean.setCreateDate(userGroupBean.getCreateDate());
			userGroupDetailBean.setUpdateBy(getLoginUserBean().getUserId());
			userGroupDetailBean.setUpdateDate(userGroupBean.getUpdateDate());
			// ユーザーグループの明細情報登録
			userGroupDao.insertUserGroupDetail(userGroupDetailBean);
		}
	}

	/**
	 * ユーザグループ情報更新
	 * @param userGroupBean 
	 * @throws ParseException 
	 */
	public void updateUserGroupInfo(UserGroupBean userGroupBean) throws ParseException {
		
		// 公開ユーザグループ情報の作成者と作成日付を取得
		BaseBean createInfo = commonDao.getDbCommonInfo("V_USER_GROUP_OPEN", "USER_GROUP_ID", userGroupBean.getUserGroupId());
		// 公開ユーザグループ情報の作成者と作成日付がない場合、予約ユーザグループ情報の取得
		if(StringUtil.isBlank(createInfo)){
			createInfo = commonDao.getDbCommonInfo("V_USER_GROUP_RESERVE", "USER_GROUP_ID", userGroupBean.getUserGroupId());
		} 
		// 登録引数情報設定
		userGroupBean.setCreateBy(createInfo.getCreateBy());
		userGroupBean.setCreateDate(createInfo.getCreateDate());
		userGroupBean.setUpdateBy(getLoginUserBean().getUserId());
		userGroupBean.setUpdateDate(new Date());
		
		// システム日付
		Date dateNow = StringUtil.getNowDate("yyyy/MM/dd");
		// 反映予定日時
		Date dgRefDate = StringUtil.parseTheDate(userGroupBean.getUgRefDate(), "yyyy/MM/dd");
		// 画面入力された反映予定日 > システム日付の場合
		if(dateNow.before(dgRefDate)){
			// ユーザグループの予約情報を取得する
			UserGroupBean userGroupInfo =  userGroupDao.getUserGroupHeader(userGroupBean.getUserGroupId(), "V_USER_GROUP_RESERVE");
			// ユーザグループの予約情報存在する場合、ユーザグループの予約情報を物理削除する
			if(!StringUtil.isBlank(userGroupInfo)){
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("PARA_USER_GROUP_ID", userGroupInfo.getUserGroupId());			// ユーザグループID
				param.put("PARA_REFLECTION_SCHEDULE_DATE", userGroupInfo.getUgRefDate());	// ユーザグループ反映予定日時
				
				// ユーザグループ情報（予約）を物理削除する。
				userGroupDao.deleteUserGroupInfo(param);
			}
		// 画面入力された反映予定日 <= システム日付の場合
		} else {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("PARA_USER_GROUP_ID", userGroupBean.getUserGroupId());				// ユーザグループID
			param.put("PARA_REFLECTION_SCHEDULE_DATE", "");									// ユーザグループ反映予定日時
			// ユーザグループ情報（両方）を物理削除する。
			userGroupDao.deleteUserGroupInfo(param);
		}
		// ユーザグループ情報登録
		this.insertUserGroup(userGroupBean);
	}

	/**
	 * ユーザグループ情報削除
	 * @param userGroupBean 
	 */
	public void deleteUserGroup(UserGroupBean userGroupBean) {
		// 検索テーブル区分　1:公開　2:予約
		String editFlag = userGroupBean.getEditFlag();
		// データ種類　1:公開　2:予約　12:公開&予約
		String originType = userGroupBean.getOriginType();
		// 削除データは予約である場合
		if("2".equals(editFlag)){
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("PARA_USER_GROUP_ID", userGroupBean.getUserGroupId());				// ユーザグループID
			param.put("PARA_REFLECTION_SCHEDULE_DATE", userGroupBean.getUgRefDate());		// ユーザグループ反映予定日時

			// ユーザグループ情報（予約）を物理削除する。
			userGroupDao.deleteUserGroupInfo(param);
			// 削除データに相関する公開データがない場合、トップグループ明細情報テーブルを論理削除する。
			if("2".equals(originType)){
				topGroupDao.updateTopGroupDetailInvalid(getLoginUserBean().getUserId(), userGroupBean.getUserGroupId(), "U");
			}
		// 削除データは公開である場合
		} else {
			// ユーザグループの予約情報を取得する
			UserGroupBean userGroupInfo = userGroupDao.getUserGroupHeader(userGroupBean.getUserGroupId(), "V_USER_GROUP_RESERVE");
			// ユーザグループの予約情報存在する場合、ユーザグループの予約情報を物理削除する
			if(!StringUtil.isBlank(userGroupInfo)){
				Map<String, Object> param = new HashMap<String, Object>();
				// ユーザグループID
				param.put("PARA_USER_GROUP_ID", userGroupInfo.getUserGroupId());			
				// ユーザグループ反映予定日時
				param.put("PARA_REFLECTION_SCHEDULE_DATE", userGroupInfo.getUgRefDate());	
				// ユーザグループ情報（予約）を物理削除する。
				userGroupDao.deleteUserGroupInfo(param);
			}
			// ユーザグループ情報テーブルを論理削除する。
			userGroupDao.updateUserGroupInfoInvalid(getLoginUserBean().getUserId(), userGroupBean);
			// トップグループ明細情報テーブルを削除する。
			topGroupDao.updateTopGroupDetailInvalid(getLoginUserBean().getUserId(), userGroupBean.getUserGroupId(), "U");
		}
	}
}