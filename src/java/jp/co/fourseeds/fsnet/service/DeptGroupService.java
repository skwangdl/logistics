
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

import jp.co.fourseeds.fsnet.beans.deptGroup.DeptGroupBean;
import jp.co.fourseeds.fsnet.beans.deptGroup.DeptGroupDetailBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.DeptGroupDao;
import jp.co.fourseeds.fsnet.dao.TopGroupDao;

import jp.co.common.frame.service.BaseBusinessService;

/**
 * 部署グループ情報機能サービス実装クラス
 * 
 * File Name: DeptGroupService.java 
 * Created: 2015/12/22
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2015/12/22		    NTS        	       作成
 * 1.1		2017/12/05			NTS			見直し修正
 *
 **/
@Component
public class DeptGroupService extends BaseBusinessService{

	@Autowired
	private DeptGroupDao deptGroupDao;
	
	@Autowired
	private TopGroupDao topGroupDao;
	
	@Autowired
	private CommonDao commonDao;

	/**
	 * 部署グループ情報を取る
	 * @param deptGroupBean　画面入力値
	 * @param strOrderBy　ソート
	 * @return　検索結果
	 */
	public List<DeptGroupBean> getDeptGroupList(DeptGroupBean deptGroupBean, String strOrderBy) {
		String searchSql = "";
		// '1'(システム管理者)以外の場合
		if(!"1".equals(getLoginUserBean().getRole())){
			searchSql = "deptGroup.getDeptGroupList_Open";
		}else{
			if("0".equals(deptGroupBean.getSearchOriginType())){
				// 全て
				searchSql = "deptGroup.getDeptGroupList_All";
			}else if("1".equals(deptGroupBean.getSearchOriginType())){
				// 現在有効なもの全て
				searchSql = "deptGroup.getDeptGroupList_Valid";
			}else if("2".equals(deptGroupBean.getSearchOriginType())){
				// 予約情報のみ
				searchSql = "deptGroup.getDeptGroupList_Rsv";
			}
		}
		
		return deptGroupDao.getDeptGroupList(searchSql, deptGroupBean, strOrderBy);
	}
	
	/**
	 * 部署グループIDと検索テーブルによって、部署グループ情報と部署グループ所属する有効のみ部署情報を取得する。
	 * @param deptGroupBean　画面入力値
	 * @return　検索結果
	 */
	public DeptGroupBean getDeptGroupInfo(DeptGroupBean deptGroupBean) {
		//　部署グループID
		String deptGroupId = deptGroupBean.getSearchDeptGroupId();
		// 部署グループ情報検索テーブル
		String deptGroupTable = "";
		//システム利用区分が'1'(システム管理者)以外の場合
		if(!"1".equals(getLoginUserBean().getRole())){
			deptGroupTable = "V_DEPARTMENT_GROUP_OPEN";
		} else {
			//公開の場合
			if("1".equals(deptGroupBean.getEditFlag())){
				deptGroupTable = "V_DEPARTMENT_GROUP_OPEN";
			//予約の場合
			} else {
				deptGroupTable = "V_DEPARTMENT_GROUP_RESERVE";
			}
		}
		DeptGroupBean deptGroupInfo = deptGroupDao.getDeptGroupHeader(deptGroupId, deptGroupTable);
		if (deptGroupInfo != null) {
			List<DeptGroupDetailBean> deptGroupDetailList = deptGroupDao.getDeptGroupDetail(deptGroupId, deptGroupTable);
			List<DeptGroupDetailBean> departmentDetailList = deptGroupDao.getDeptDetail(deptGroupId, deptGroupTable);
			deptGroupInfo.setDepartmentList(deptGroupDetailList);
			deptGroupInfo.setDepartmentDetailList(departmentDetailList);
		}
		return deptGroupInfo;
	}
	
	/**
	 * 部署情報を検索
	 * 
	 */
	public List<DeptGroupDetailBean> getDeptList(DeptGroupBean deptGroupBean) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// 会社
		param.put("PARA_COMPANY_ID", deptGroupBean.getConditionCompanyId());
		// 統括
		param.put("PARA_UNIFICATION_ID", deptGroupBean.getConditionUnificationId());
		// 事業
		param.put("PARA_BUSINESS_ID", deptGroupBean.getConditionBusinessId());
		// SS部門コード
		param.put("PARA_SSDEPT_ID", deptGroupBean.getSearchDepartmentId());
		// SS部門名
		param.put("PARA_SSDEPT_NAME", deptGroupBean.getSearchDepartmentName());
		
		List<DeptGroupDetailBean> getDeptList = null;
		// 部署リストを取得
		if (!StringUtil.isBlank(deptGroupBean.getSearchDepartmentId()) || !StringUtil.isBlank(deptGroupBean.getSearchDepartmentName())) {
			// 画面条件【 SS部署コード とSS部署名】存在の場合
			getDeptList = deptGroupDao.getDeptList_right(param);
		} else {
			// 画面条件【 会社コード  】存在しないの場合
			if (StringUtil.isBlank(deptGroupBean.getConditionCompanyId())) {
				getDeptList = deptGroupDao.getDeptList_right(param);
			} else {
				getDeptList = deptGroupDao.getDeptList_left(param);
			}
		}
		
		return getDeptList;
	}

	/**
	 * 部署グループIDを採番する
	 * 
	 */
	public String getNewDeptGroupId() {
		return deptGroupDao.getNewDeptGroupId();
	}

	/**
	 * 登録済データとの部署名称重複データを取得
	 * 
	 */
	public boolean checkDeptGroupNmRepeat(DeptGroupBean deptGroupBean, String eventType) {
		int count = deptGroupDao.getDeptGroupNmCheckInfo(deptGroupBean, eventType);
		// 登録済データとの部署名称重複データを存在の場合、メッセージボックスを出す
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 部署グループ情報登録
	 * @param deptGroupBean 
	 * @param loginUser 
	 */
	public void insertDeptGroup(DeptGroupBean deptGroupBean) {
		// ■■■部署グループヘッダー情報登録
		deptGroupDao.insertDeptGroup(deptGroupBean);

		// 部署ID
		String[] departmentId = StringUtil.split(deptGroupBean.getDepartmentId(),",");
		for(int i = 0; i < departmentId.length; i++){
			DeptGroupDetailBean deptGroupDetailBean = new DeptGroupDetailBean();
			// 部署グループID
			deptGroupDetailBean.setDeptGroupId(deptGroupBean.getDeptGroupId());
			// 部署グループ反映予定日時
			deptGroupDetailBean.setDgRefDate(deptGroupBean.getDgRefDate());
			// 部署ID
			deptGroupDetailBean.setDepartmentId(departmentId[i].substring(1, departmentId[i].length()));
			// 条件フラグ
			deptGroupDetailBean.setInputFlag(departmentId[i].substring(0, 1));
			// 部署グループ詳細登録引数情報設定
			deptGroupDetailBean.setCreateBy(deptGroupBean.getCreateBy());
			deptGroupDetailBean.setCreateDate(deptGroupBean.getCreateDate());
			deptGroupDetailBean.setUpdateBy(getLoginUserBean().getUserId());
			deptGroupDetailBean.setUpdateDate(deptGroupBean.getUpdateDate());
			// ■■■部署グループの明細情報登録
			deptGroupDao.insertDeptGroupDetail(deptGroupDetailBean);
		}
		
		// 部門店舗グループ所属人数情報削除
		deptGroupDao.deleteDepartmentStoreGroupUser(deptGroupBean);
		
		// 部門店舗グループ所属人数情報登録
		deptGroupDao.insertDepartmentStoreGroupUser(deptGroupBean);
	}

	/**
	 * 部署グループ情報更新
	 * @param deptGroupBean 
	 * @param loginUser 
	 * @throws ParseException 
	 */
	public void updateDeptGroup(DeptGroupBean deptGroupBean) throws ParseException {
		
		// 公開部署グループ情報の作成者と作成日付を取得
		BaseBean createInfo = commonDao.getDbCommonInfo("V_DEPARTMENT_GROUP_OPEN", "DEPARTMENT_GROUP_ID", deptGroupBean.getDeptGroupId());
		// 公開部署グループ情報の作成者と作成日付がない場合、予約部署グループ情報の取得
		if(StringUtil.isBlank(createInfo)){
			createInfo = commonDao.getDbCommonInfo("V_DEPARTMENT_GROUP_RESERVE", "DEPARTMENT_GROUP_ID", deptGroupBean.getDeptGroupId());
		} 
		// 登録引数情報設定
		deptGroupBean.setCreateBy(createInfo.getCreateBy());
		deptGroupBean.setCreateDate(createInfo.getCreateDate());
		deptGroupBean.setUpdateBy(getLoginUserBean().getUserId());
		deptGroupBean.setUpdateDate(new Date());
		// システム日付
		Date dateNow = StringUtil.getNowDate("yyyy/MM/dd");
		// 反映予定日時
		Date dgRefDate = StringUtil.parseTheDate(deptGroupBean.getDgRefDate(), "yyyy/MM/dd");
		// 画面入力された反映予定日 > システム日付の場合
		if(dateNow.before(dgRefDate)){
			// 部署グループの予約情報を取得する
			DeptGroupBean deptGroupInfo =  deptGroupDao.getDeptGroupHeader(deptGroupBean.getDeptGroupId(), "V_DEPARTMENT_GROUP_RESERVE");
			// 部署グループの予約情報存在する場合、部署グループの予約情報を物理削除する
			if(!StringUtil.isBlank(deptGroupInfo)){
				Map<String, Object> param = new HashMap<String, Object>();					// 部署グループID
				param.put("PARA_DEPARTMENT_GROUP_ID", deptGroupInfo.getDeptGroupId());		// 部署グループID
				param.put("PARA_REFLECTION_SCHEDULE_DATE", deptGroupInfo.getDgRefDate());	// 部署グループ反映予定日時

				// 部署グループ情報（予約）を物理削除する。
				deptGroupDao.deleteDeptGroupInfo(param);
			}
		// 画面入力された反映予定日 <= システム日付の場合
		} else {
			Map<String, Object> param = new HashMap<String, Object>();						// 削除条件MAP
			param.put("PARA_DEPARTMENT_GROUP_ID", deptGroupBean.getDeptGroupId());			// 部署グループID
			param.put("PARA_REFLECTION_SCHEDULE_DATE", "");									// 部署グループ反映予定日時

			// 部署グループ情報（両方）を物理削除する。
			deptGroupDao.deleteDeptGroupInfo(param);
		}
		// 部署グループ情報登録
		this.insertDeptGroup(deptGroupBean);
	}

	/**
	 * 部署グループ情報削除
	 * @param deptGroupBean 
	 */
	public void deleteDeptGroup(DeptGroupBean deptGroupBean) {
		// 検索テーブル区分　1:公開　2:予約
		String editFlag = deptGroupBean.getEditFlag();
		// データ種類　1:公開　2:予約　12:公開&予約
		String originType = deptGroupBean.getOriginType();
		// 削除データは予約である場合
		if("2".equals(editFlag)){
			Map<String, Object> param = new HashMap<String, Object>();						// 部署グループID
			param.put("PARA_DEPARTMENT_GROUP_ID", deptGroupBean.getDeptGroupId());			// 部署グループID
			param.put("PARA_REFLECTION_SCHEDULE_DATE", deptGroupBean.getDgRefDate());		// 部署グループ反映予定日時

			// 部署グループ情報（予約）を物理削除する。
			deptGroupDao.deleteDeptGroupInfo(param);
			// 削除データに相関する公開データがない場合、トップグループ明細情報テーブルを論理削除する。
			if("2".equals(originType)){
				topGroupDao.updateTopGroupDetailInvalid(getLoginUserBean().getUserId(), deptGroupBean.getDeptGroupId(), "D");
			}
		// 削除データは公開である場合
		} else {
			// 部署グループの予約情報を取得する
			DeptGroupBean deptGroupInfo = deptGroupDao.getDeptGroupHeader(deptGroupBean.getDeptGroupId(), "V_DEPARTMENT_GROUP_RESERVE");
			// 部署グループの予約情報存在する場合、部署グループの予約情報を物理削除する
			if(!StringUtil.isBlank(deptGroupInfo)){
				Map<String, Object> param = new HashMap<String, Object>();					// 部署グループID
				param.put("PARA_DEPARTMENT_GROUP_ID", deptGroupInfo.getDeptGroupId());		// 部署グループID
				param.put("PARA_REFLECTION_SCHEDULE_DATE", deptGroupInfo.getDgRefDate());	// 部署グループ反映予定日時

				// 部署グループ情報（予約）を物理削除する。
				deptGroupDao.deleteDeptGroupInfo(param);
			}
			// 部門グループ情報テーブルを論理削除する。
			deptGroupDao.updateDeptGroupInfoInvalid(getLoginUserBean().getUserId(), deptGroupBean);
			// トップグループ明細情報テーブルを削除する。
			topGroupDao.updateTopGroupDetailInvalid(getLoginUserBean().getUserId(), deptGroupBean.getDeptGroupId(), "D");
		}
	}
	
	/**
	 * 部署リンクをクリック、階層レベル検索
	 * @param deptGroupBean 
	 */
	public String getDeptLevel(Map<String, Object> param) {
		
		return deptGroupDao.getDeptLevel(param);
	}
	
	/**
	 * 会社リンクをクリック、所属統括検索
	 * @param deptGroupBean 
	 */
	public List<Map<String, String>> getSecondDeptList(Map<String, Object> param) {
		
		return deptGroupDao.getSecondDeptList(param);
	}
	
	/**
	 * 統括リンクをクリック、所属事業検索
	 * @param deptGroupBean 
	 */
	public List<Map<String, String>> getThirdDeptList(Map<String, Object> param) {
		
		return deptGroupDao.getThirdDeptList(param);
	}
	
	/**
	 * 事業リンクをクリック、所属営業部検索
	 * @param deptGroupBean 
	 */
	public List<Map<String, String>> getFourthDeptList(Map<String, Object> param) {
		
		return deptGroupDao.getFourthDeptList(param);
	}
}