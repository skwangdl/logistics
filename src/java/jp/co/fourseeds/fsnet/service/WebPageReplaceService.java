package jp.co.fourseeds.fsnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.common.frame.service.BaseBusinessService;

import jp.co.fourseeds.fsnet.beans.WebPageReplaceBean;
import jp.co.fourseeds.fsnet.dao.WebPageReplaceDao;
/**
 * Web担当者ID差し替え情報
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 * 1.1		2017/09/11			NTS			見直し対応
 * 
 **/

@Component
public class WebPageReplaceService extends BaseBusinessService {
	
	@Autowired
	private WebPageReplaceDao webPageReplaceDao;
	
	/**
	 * 社員コンテンツ関連情報を取得
	 * @param webPageReplaceBean 
	 * @param strOrderBy
	 * @return 検索結果
	 */
	public List<WebPageReplaceBean> getPageList(WebPageReplaceBean webPageReplaceBean, String strOrderBy) {
		return webPageReplaceDao.getPageList(webPageReplaceBean, strOrderBy);
	}
	
	/**
	 * 社員コンテンツ関連情報を更新
	 * @param webPageReplaceBean 
	 * @param strOrderBy
	 * @return 検索結果
	 */
	public void updateReplaceSearchUserName(WebPageReplaceBean webPageReplaceBean, String userId) {
		webPageReplaceDao.deleteReplaceSearchUserName(webPageReplaceBean);
		webPageReplaceDao.updateReplaceSearchUserName(webPageReplaceBean,userId);
	}
	

	/**
	 * 社員コンテンツ関連情報を追加
	 * @param webPageReplaceBean 
	 * @param strOrderBy
	 * @return 検索結果
	 */
	public void addReplaceSearchUserName(WebPageReplaceBean webPageReplaceBean, String userId) {
		webPageReplaceDao.deleteReplaceSearchUserName(webPageReplaceBean);
		webPageReplaceDao.addReplaceSearchUserName(webPageReplaceBean,userId);
	}
}
