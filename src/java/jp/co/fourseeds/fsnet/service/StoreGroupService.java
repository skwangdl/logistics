
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

import jp.co.fourseeds.fsnet.beans.storeGroup.StoreGroupBean;
import jp.co.fourseeds.fsnet.beans.storeGroup.StoreGroupDetailBean;
import jp.co.fourseeds.fsnet.common.ConstantContainer;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.StoreGroupDao;
import jp.co.fourseeds.fsnet.dao.TopGroupDao;

import jp.co.common.frame.service.BaseBusinessService;

/**
 * 店舗グループ情報機能サービス実装クラス
 * 
 * File Name: StoreGroupService.java 
 * Created: 2016/01/08
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2016/01/08		    NTS        	       作成
 * 1.1		2017/12/05			NTS			見直し修正
 *
 **/
@Component
public class StoreGroupService extends BaseBusinessService{

	@Autowired
	private StoreGroupDao storeGroupDao;
	
	@Autowired
	private TopGroupDao topGroupDao;
	
	@Autowired
	private CommonDao commonDao;

	/**
	 * 店舗グループ情報を取る
	 * @param storeGroupBean　画面入力値
	 * @param strOrderBy　ソート
	 * @return　検索結果
	 */
	public List<StoreGroupBean> getStoreGroupList(StoreGroupBean storeGroupBean, String strOrderBy) {
		String searchSql = "";
		// '1'(システム管理者)以外の場合
		if(!"1".equals(getLoginUserBean().getRole())){
			searchSql = "storeGroup.getStoreGroupList_Open";
		}else{
			if("0".equals(storeGroupBean.getSearchOriginType())){
				// 全て
				searchSql = "storeGroup.getStoreGroupList_All";
			}else if("1".equals(storeGroupBean.getSearchOriginType())){
				// 現在有効なもの全て
				searchSql = "storeGroup.getStoreGroupList_Valid";
			}else if("2".equals(storeGroupBean.getSearchOriginType())){
				// 予約情報のみ
				searchSql = "storeGroup.getStoreGroupList_Rsv";
			}
		}
		
		return storeGroupDao.getStoreGroupList(searchSql, storeGroupBean, strOrderBy, getLoginUserBean());
	}
	
	/**
	 * 店舗グループIDと検索テーブルによって、店舗グループ情報と店舗グループ所属する有効のみ店舗情報を取得する。
	 * @param storeGroupBean　画面入力値
	 * @return 検索結果
	 */
	public StoreGroupBean getStoreGroupInfo(StoreGroupBean storeGroupBean) {
		// 店舗グループID
		String storeGroupId = storeGroupBean.getSearchStoreGroupId();
		// 店舗グループ情報検索テーブル
		String storeGroupTable = "";
		// 公開の場合
		if("1".equals(storeGroupBean.getEditFlag())){
			storeGroupTable = "V_STORE_GROUP_OPEN";
		// 予約の場合
		} else {
			storeGroupTable = "V_STORE_GROUP_RESERVE";
		}
		StoreGroupBean storeGroupInfo = storeGroupDao.getStoreGroupHeader(storeGroupId, storeGroupTable);
		if(storeGroupInfo != null){
			List<StoreGroupDetailBean> shareUserList = storeGroupDao.getShareUserList(storeGroupId, storeGroupInfo.getSgRefDate());
			List<StoreGroupDetailBean> storeList = storeGroupDao.getStoreGroupDetail(storeGroupId, storeGroupTable, "Y");
			List<StoreGroupDetailBean> storeDetailList = storeGroupDao.getStoreDetail(storeGroupId, storeGroupTable, "Y");
			storeGroupInfo.setShareUserList(shareUserList);
			storeGroupInfo.setStoreList(storeList);
			storeGroupInfo.setStoreDetailList(storeDetailList);
		}
		return storeGroupInfo;
	}
	
	/**
	 * 店舗グループIDを採番する
	 * 
	 */
	public String getNewStoreGroupId() {
		return storeGroupDao.getNewStoreGroupId();
	}

	/**
	 * 登録済データとの店舗グループ名称重複データを取得
	 * 
	 */
	public boolean checkStoreGroupNmRepeat(StoreGroupBean storeGroupBean, String eventType) {
		int count = storeGroupDao.getStoreGroupNmCheckInfo(storeGroupBean, eventType);
		// 登録済データとのユーザグループ名称重複データを存在の場合、メッセージボックスを出す
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 店舗グループ情報登録
	 * @param userGroupBean 
	 */
	public void insertStoreGroup(StoreGroupBean storeGroupBean) {
		// ■■■店舗グループヘッダー情報登録
		storeGroupDao.insertStoreGroup(storeGroupBean);
		// 店舗グループの明細情報登録
		// 店舗ID
		String[] storeId = StringUtil.split(storeGroupBean.getStoreId(),",");
		for(int i = 0; i < storeId.length; i++){
			StoreGroupDetailBean storeGroupDetailBean = new StoreGroupDetailBean();
			// 店舗グループID
			storeGroupDetailBean.setStoreGroupId(storeGroupBean.getStoreGroupId());
			// 店舗ID
			storeGroupDetailBean.setStoreId(storeId[i].substring(1, storeId[i].length()));
			// 条件フラグ
			storeGroupDetailBean.setInputFlag(storeId[i].substring(0, 1));
			// 店舗グループ反映予定日時
			storeGroupDetailBean.setSgRefDate(storeGroupBean.getSgRefDate());
			// 個人グループフラグ
			storeGroupDetailBean.setGrouptype(storeGroupBean.getGrouptype());
			// 店舗グループ詳細登録引数情報設定
			storeGroupDetailBean.setCreateBy(storeGroupBean.getCreateBy());
			storeGroupDetailBean.setCreateDate(storeGroupBean.getCreateDate());
			storeGroupDetailBean.setUpdateBy(getLoginUserBean().getUserId());
			storeGroupDetailBean.setUpdateDate(storeGroupBean.getUpdateDate());
			// ■■■店舗グループの明細情報登録
			storeGroupDao.insertStoreGroupDetail(storeGroupDetailBean);
		}
		// 個人グループフラグは個人の場合、共有対象者情報登録
		if (ConstantContainer.PERSONAL_KEYCODE.equals(storeGroupBean.getGrouptype())) {
			// 共有対象者リストを取得
			List<StoreGroupDetailBean> shareUserList = storeGroupBean.getShareUserList();
			if(!StringUtil.isBlank(shareUserList)){
				for(int i = 0; i < shareUserList.size(); i++){
					StoreGroupDetailBean shareUserBean = shareUserList.get(i);
					// 店舗グループID
					shareUserBean.setStoreGroupId(storeGroupBean.getStoreGroupId());
					// 店舗グループ反映予定日時
					shareUserBean.setSgRefDate(storeGroupBean.getSgRefDate());
					// 共有対象者詳細登録引数情報設定
					shareUserBean.setCreateBy(storeGroupBean.getCreateBy());
					shareUserBean.setCreateDate(storeGroupBean.getCreateDate());
					shareUserBean.setUpdateBy(getLoginUserBean().getUserId());
					shareUserBean.setUpdateDate(storeGroupBean.getUpdateDate());
					// ■■■店舗グループの共有対象者情報登録
					storeGroupDao.insertShareUser(shareUserBean);
				}
			}
		}
		
		// 部門店舗グループ所属人数情報削除
		storeGroupDao.deleteDepartmentStoreGroupUser(storeGroupBean);
		
		// 部門店舗グループ所属人数情報登録
		storeGroupDao.insertDepartmentStoreGroupUser(storeGroupBean);
	}

	/**
	 * 店舗グループ情報更新
	 * @param storeGroupBean 
	 * @throws ParseException 
	 */
	public void updateStoreGroupInfo(StoreGroupBean storeGroupBean) throws ParseException {
		
		// 公開店舗グループ情報の作成者と作成日付を取得
		BaseBean createInfo = commonDao.getDbCommonInfo("V_STORE_GROUP_OPEN", "STORE_GROUP_ID", storeGroupBean.getStoreGroupId());
		// 公開店舗グループ情報の作成者と作成日付がない場合、予約店舗グループ情報の取得
		if(StringUtil.isBlank(createInfo)){
			createInfo = commonDao.getDbCommonInfo("V_STORE_GROUP_RESERVE", "STORE_GROUP_ID", storeGroupBean.getStoreGroupId());
		} 
		// 登録引数情報設定
		storeGroupBean.setCreateBy(createInfo.getCreateBy());
		storeGroupBean.setCreateDate(createInfo.getCreateDate());
		storeGroupBean.setUpdateBy(getLoginUserBean().getUserId());
		storeGroupBean.setUpdateDate(new Date());
		
		// システム日付
		Date dateNow = StringUtil.getNowDate("yyyy/MM/dd");
		// 反映予定日時
		Date sgRefDate = StringUtil.parseTheDate(storeGroupBean.getSgRefDate(), "yyyy/MM/dd");
		// 画面入力された反映予定日 > システム日付の場合
		if(dateNow.before(sgRefDate)){
			// 店舗グループの予約情報を取得する
			StoreGroupBean storeGroupInfo =  storeGroupDao.getStoreGroupHeader(storeGroupBean.getStoreGroupId(), "V_STORE_GROUP_RESERVE");
			// 店舗グループの予約情報存在する場合、店舗グループの予約情報を物理削除する
			if(!StringUtil.isBlank(storeGroupInfo)){
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("PARA_STORE_GROUP_ID", storeGroupInfo.getStoreGroupId());			// 店舗グループID
				param.put("PARA_REFLECTION_SCHEDULE_DATE", storeGroupInfo.getSgRefDate());	// 店舗グループ反映予定日時
				
				// 店舗グループ情報（予約）を物理削除する。
				storeGroupDao.deleteStoreGroupInfo(param);
			}
		// 画面入力された反映予定日 <= システム日付の場合
		} else {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("PARA_STORE_GROUP_ID", storeGroupBean.getStoreGroupId());				// 店舗グループID
			param.put("PARA_REFLECTION_SCHEDULE_DATE", "");									// 店舗グループ反映予定日時
			// 店舗グループ情報（両方）を物理削除する。
			storeGroupDao.deleteStoreGroupInfo(param);
		}
		// ユーザグループ情報登録
		this.insertStoreGroup(storeGroupBean);
	}

	/**
	 * 店舗グループ情報削除
	 * @param storeGroupBean 
	 */
	public void deleteStoreGroup(StoreGroupBean storeGroupBean) {
		// 検索テーブル区分　1:公開　2:予約
		String editFlag = storeGroupBean.getEditFlag();
		// データ種類　1:公開　2:予約　12:公開&予約
		String originType = storeGroupBean.getOriginType();
		// 削除データは予約である場合
		if("2".equals(editFlag)){
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("PARA_STORE_GROUP_ID", storeGroupBean.getStoreGroupId());				// 店舗グループID
			param.put("PARA_REFLECTION_SCHEDULE_DATE", storeGroupBean.getSgRefDate());		// 店舗グループ反映予定日時

			// 店舗グループ情報（予約）を物理削除する。
			storeGroupDao.deleteStoreGroupInfo(param);
			// 削除データに相関する公開データがない場合、トップグループ明細情報テーブルを論理削除する。
			if("2".equals(originType)){
				topGroupDao.updateTopGroupDetailInvalid(getLoginUserBean().getUserId(), storeGroupBean.getStoreGroupId(), "S");
			}
		// 削除データは公開である場合
		} else {
			// 店舗グループの予約情報を取得する
			StoreGroupBean storeGroupInfo = storeGroupDao.getStoreGroupHeader(storeGroupBean.getStoreGroupId(), "V_STORE_GROUP_RESERVE");
			// ユーザグループの予約情報存在する場合、ユーザグループの予約情報を物理削除する
			if(!StringUtil.isBlank(storeGroupInfo)){
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("PARA_STORE_GROUP_ID", storeGroupInfo.getStoreGroupId());			// 店舗グループID
				param.put("PARA_REFLECTION_SCHEDULE_DATE", storeGroupInfo.getSgRefDate());	// 店舗グループ反映予定日時

				// 店舗グループ情報（予約）を物理削除する。
				storeGroupDao.deleteStoreGroupInfo(param);
			}
			// 店舗グループ情報テーブルを論理削除する。
			storeGroupDao.updateStoreGroupInfoInvalid(getLoginUserBean().getUserId(), storeGroupBean);
			// トップグループ明細情報テーブルを削除する。
			topGroupDao.updateTopGroupDetailInvalid(getLoginUserBean().getUserId(), storeGroupBean.getStoreGroupId(), "S");
		}
	}

	/**
	 * 店舗検索を実行（組織）
	 * @param storeGroupBean　画面入力値
	 * @return 検索結果
	 */
	public List<StoreGroupDetailBean> getStoreList(StoreGroupBean storeGroupBean) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// 会社
		param.put("PARA_COMPANY_ID", storeGroupBean.getConditionCompanyId());
		// 統括
		param.put("PARA_UNIFICATION_ID", storeGroupBean.getConditionUnificationId());
		// 事業
		param.put("PARA_BUSINESS_ID", storeGroupBean.getConditionBusinessId());
		// 営業部
		param.put("PARA_SALES_ID", storeGroupBean.getConditionSalesId());
		// 店舗コード
		param.put("PARA_STORE_ID", storeGroupBean.getSearchStoreId());
		// 店舗名称
		param.put("PARA_STORE_NAME", storeGroupBean.getSearchStoreName());
		// 店舗区分
		param.put("PARA_CONDITION_FC_FLAG2", storeGroupBean.getConditionFcFlag2());
		// 店舗FC区分名
		param.put("PARA_STORE_FC_DISPLAY", "Y");
		
		List<StoreGroupDetailBean> getStoreList = null;
		// 画面条件【 店舗コード 、 店舗名称  】存在の場合
		if (!StringUtil.isBlank(storeGroupBean.getSearchStoreId()) || !StringUtil.isBlank(storeGroupBean.getSearchStoreName())) {
			getStoreList = storeGroupDao.getStoreList_right(param);
		} else {
			// 画面条件【 会社コード  】存在しないの場合
			if (StringUtil.isBlank(storeGroupBean.getConditionCompanyId())) {
				getStoreList = storeGroupDao.getStoreList_right(param);
			} else {
				// 画面条件【 営業部コード  】存在の場合
				if (!StringUtil.isBlank(storeGroupBean.getConditionSalesId())){
					getStoreList = storeGroupDao.getStoreList_right(param);
				} else {
					getStoreList = storeGroupDao.getStoreList_left(param);
				}
			}
		}
		return getStoreList;
	}

	/**
	 * 店舗グループの予約情報を取得
	 * 
	 */
	public boolean checkRsvStoreGroupInfo(StoreGroupBean storeGroupBean) {
		// 店舗グループID
		String storeGroupId = storeGroupBean.getSearchStoreGroupId();
		// 店舗グループ情報検索テーブル
		String storeGroupTable = "V_STORE_GROUP_RESERVE";
		StoreGroupBean storeGroupInfo = storeGroupDao.getStoreGroupHeader(storeGroupId, storeGroupTable);
		// 店舗グループの予約情報を存在の場合、メッセージボックスを出す
		if (!StringUtil.isBlank(storeGroupInfo)) {
			return true;
		}
		return false;
	}

	/**
	 * 部署リンクをクリック、階層レベル検索
	 * @param deptGroupBean 
	 */
	public String getDeptLevel(Map<String, Object> param) {
		
		return storeGroupDao.getDeptLevel(param);
	}
	
	/**
	 * 会社リンクをクリック、所属統括検索
	 * @param deptGroupBean 
	 */
	public List<Map<String, String>> getSecondDeptList(Map<String, Object> param) {
		
		return storeGroupDao.getSecondDeptList(param);
	}
	
	/**
	 * 統括リンクをクリック、所属事業検索
	 * @param deptGroupBean 
	 */
	public List<Map<String, String>> getThirdDeptList(Map<String, Object> param) {
		
		return storeGroupDao.getThirdDeptList(param);
	}
	
	/**
	 * 事業リンクをクリック、所属営業部検索
	 * @param deptGroupBean 
	 */
	public List<Map<String, String>> getFourthDeptList(Map<String, Object> param) {
		
		return storeGroupDao.getFourthDeptList(param);
	}
	
	/**
	 * 営業部リンクをクリック、所属店舗検索
	 * @param deptGroupBean 
	 */
	public List<Map<String, String>> getFifthDeptList(Map<String, Object> param) {
		
		return storeGroupDao.getFifthDeptList(param);
	}
}

