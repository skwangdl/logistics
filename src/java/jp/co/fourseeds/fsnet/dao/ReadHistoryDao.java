package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.ReadHistoryFormBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;

/**
 * 閲覧履歴の確認機能Dao実装クラス
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2015/12/18		    NTS        	       作成
 *
 **/
@Repository
public class ReadHistoryDao extends BaseDao {
	
	/**
	 * 配信者確認情報を取得
	 * @param param 検索条件
	 * @return List 検索結果
	 */
	public List<ReadHistoryFormBean> getNecessityReadPageList(ReadHistoryFormBean readHistoryFormBean, String strOrderBy, LoginUserBean loginUser) {
		Map<String, Object> param = new HashMap<String, Object>();
		// 閲覧開始日
		String startDate= readHistoryFormBean.getSearchStartDate();
		// 閲覧終了日
		String endDate= readHistoryFormBean.getSearchEndDate();
		
		// 検索条件を設定
		// 閲覧開始日指定しない場合、最小の閲覧開始日を設定
		if (StringUtil.isEmpty(startDate)) {
			param.put("PARA_START_DATE", "19000101");
		// 閲覧開始日指定の場合、画面の 閲覧開始日を設定
		} else {
			param.put("PARA_START_DATE", startDate);
		}
		
		// 閲覧終了日指定しない場合、最大の閲覧終了日を設定
		if (StringUtil.isEmpty(endDate)) {
			param.put("PARA_END_DATE", "29981231");
		// 閲覧終了日指定の場合、画面の 閲覧終了日を設定
		} else {
			param.put("PARA_END_DATE", endDate);
		}
		param.put("PARA_TITLE", readHistoryFormBean.getSearchTitle());
		param.put("PARA_USER_ID", loginUser.getUserId());
		param.put("PARA_ROLE", loginUser.getRole());
		param.put("PARA_ORDER_BY", strOrderBy);
		
		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("readHistory.getNecessityReadPageList", param);
	}
	
	/**
	 * 閲覧管理対象者照会情報を取得
	 * @param param 検索条件
	 * @return List 検索結果
	 */
	public List<ReadHistoryFormBean> getNecessityReadUserListByPage(ReadHistoryFormBean readHistoryFormBean, String strOrderBy) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		// 検索条件を設定
		param.put("PARA_PAGE_ID", readHistoryFormBean.getPageId());
		param.put("PARA_READ_FLAG", readHistoryFormBean.getReadFlag());
		param.put("PARA_ORDER_BY", strOrderBy);
		
		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("readHistory.getNecessityReadUserListByPage", param);
	}
	
	/**
	 * ユーザのメールアドレスを取得
	 * @param param 検索条件
	 * @return List 検索結果
	 */
	public String getUserMailAddress(String userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userId);
		
		//　DBから検索結果を取得
		List<String> mailList = this.sqlSessionTemplate.selectList("readHistory.getUserMailAddress", param);
		if (StringUtil.isBlank(mailList) || mailList.size() ==0 ) {
			return null;
		} else {
			return mailList.get(0);
		}
	}
}