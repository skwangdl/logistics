package jp.co.fourseeds.fsnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.fourseeds.fsnet.beans.HomeBean;
import jp.co.fourseeds.fsnet.dao.HomeDao;

import jp.co.common.frame.service.BaseBusinessService;

/**
 * ホーム機能サービス実装クラス
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2015/11/24		    NTS        	       作成
 *
 **/
@Component
public class HomeService extends BaseBusinessService{

	@Autowired
	private HomeDao homeDao;
	
	/**
	 * 対象者名称セレクトボックスの取得
	 * @return List
	 */
	public List<HomeBean> getUserDivisionList() {
		return homeDao.getUserDivisionList(getLoginUserBean());
	}
	
	/**
	 * 新着情報を取る
	 * @param param
	 * @return List
	 */
	public List<HomeBean> getNewsList(HomeBean homeBean, String strOrderBy) {
		return homeDao.getNewsList(homeBean, strOrderBy, getLoginUserBean());
	}
	
	/**
	 * 重要なお知らせ情報を取る
	 * @param param
	 * @return List
	 */
	public List<HomeBean> getInformationList(HomeBean homeBean, String strOrderBy) {
		return homeDao.getInformationList(homeBean, strOrderBy, getLoginUserBean());
	}
}