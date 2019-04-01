package jp.co.fourseeds.fsnet.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jp.co.common.frame.dao.BaseDao;
import jp.co.common.frame.util.prop.FsPropertyUtil;
import jp.co.fourseeds.fsnet.beans.page.PageAttachmentBean;
import jp.co.fourseeds.fsnet.beans.page.PageBean;

/**
 * 社員検索機能Dao実装クラス
 * 
 * File Name: UserDao.java 
 * Created: 2015/09/22
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2015/09/22		    NTS        	       作成
 *
 **/
@Repository
public class PageDownloadDao extends BaseDao {
	
	/**ページ名称Ｍａｐ*/
	private Map<String, String> pageNameMap = null;

	/**
	 * 社員検索結果を取得
	 * @param param　検索条件
	 * @param from　検索開始
	 * @param length　検索レコード数
	 * @return　検索結果
	 */
	public List<PageAttachmentBean> getAttachmentList(String pageId, String IsReserve) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_IS_RESERVE", IsReserve);
		
		return this.sqlSessionTemplate.selectList("pageDownload.getAttachmentList", param);
	}
	
	/**
	 * ログ更新
	 * @param currentUserId
	 *           ログインユーザーＩＤ
	 * @param pageId
	 *           ページＩＤ
	 */
	public void updateLogFlag(String currentUserId, String pageId){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_UPDATE_BY", currentUserId);
		param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_USER_ID", currentUserId.toString());
		this.sqlSessionTemplate.update("pageDownload.updateLogFlag", param);
	}
	
	/**
	 * ファイルパス取得
	 * @param pageId
	 *           ページＩＤ
	 * @param isStatic
	 *           静的コンテンツフラグ（１：静 ０：動）
	 * @param isReserve
	 *           予約フラグ（１：予約 ０：予約以外）
	 */
	public List<String> getContentAbsolutePath(String pageId, boolean isStatic, String isReserve) {
		// 検索結果配列
		String[] resultArr = null;
		// 検索結果リスト
		List<String[]> resultList = new ArrayList<String[]>();
		// ファイルパス
		String fileDirectory = "";
		// パラメータ
		Map<String, Object> param = new HashMap<String, Object>();
		// ページＩＤ
		param.put("PARA_PAGE_ID", pageId);
		// 予約フラグ
		param.put("PARA_IS_RESERVE", isReserve);
		// 静的コンテンツ場合
		if (isStatic) {
			// フォルダを取得する。
			fileDirectory = (isReserve == "1" ? FsPropertyUtil.getStringProperty("htmlFile.temp.path")
					: FsPropertyUtil.getStringProperty("htmlFile.path"));
			// パス情報取得
			List<PageBean> pageList = this.sqlSessionTemplate.selectList("pageDownload.getSaticContentAbsolutePath", param);
			for (PageBean tempBean:pageList) {
				resultArr = new String[2];
				// ファイル場合
				if (tempBean.getFileSuffix().startsWith(".")) {
					resultArr[0] = tempBean.getTitle() + tempBean.getFileSuffix();
					resultArr[1] = tempBean.getPageId() + tempBean.getFileSuffix();
				// フォルダ場合
				} else {
					resultArr[0] = tempBean.getTitle();
					resultArr[1] = tempBean.getPageId();
				}
				// 検索結果リストを追加する。
				resultList.add(resultArr);
			}
		// 動的コンテンツ場合
		} else {
			// フォルダを取得する。
			fileDirectory = (isReserve == "1" ? FsPropertyUtil.getStringProperty("server.upload.temp.path")
					: FsPropertyUtil.getStringProperty("server.upload.path"));
			// パス情報取得
			List<PageAttachmentBean> attachmentList = this.sqlSessionTemplate.selectList("pageDownload.getAttachmentList", param);
			for (PageAttachmentBean tempBean:attachmentList) {
				resultArr = new String[2];
				resultArr[0] = tempBean.getAttachmentName();
				resultArr[1] = tempBean.getAttachmentFileUrl();
				resultArr[1] = resultArr[1].substring(resultArr[1].lastIndexOf("/") + 1);
				// 検索結果リストを追加する。
				resultList.add(resultArr);
			}
		}
		// ページ名称Ｍａｐ
		pageNameMap = new HashMap<String, String>();
		// パスリスト
		List<String> pathList = new ArrayList<String>();
		// 検索リストループ
		for (int i = 0; i < resultList.size(); i++) {
			String[] string = (String[]) resultList.get(i);
			pathList.add(fileDirectory + "\\" + string[1]);
			// KEY：PageId　VALUE:Title
			pageNameMap.put(string[1], string[0]);
			if (string[1].indexOf(".") == -1) {
				pageNameMap.put(string[1] + ".html", string[0] + ".html");
			}
		}
		return pathList;
	}
	
	/**
	 * ファイルダウンロードログ情報削除
	 * @param currentUserId
	 *           ログインユーザーＩＤ
	 * @param pageId
	 *           ページＩＤ
	 * @param zipEntryName
	 *           ファイル名
	 */
	public void deleteFileToZip(String currentUserId ,String pageId,String zipEntryName) {
		// パラメータ
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", currentUserId);
		param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_FILE_NAME", zipEntryName);
		this.sqlSessionTemplate.update("pageDownload.deleteFileToZip", param);
	}
	
	/**
	 * ファイルダウンロードログ情報登録
	 * @param currentUserId
	 *           ログインユーザーＩＤ
	 * @param pageId
	 *           ページＩＤ
	 * @param zipEntryName
	 *           ファイル名
	 */
	public void insertDownFlag(String currentUserId ,String pageId,String zipEntryName) {
		// パラメータ
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", currentUserId);
		param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_FILE_NAME", zipEntryName);
		param.put("PARA_CREATE_BY", currentUserId);
		this.sqlSessionTemplate.update("pageDownload.insertDownFlag", param);

	}
	
	/**
	 * ページ名称Ｍａｐ取得
	 */
	public Map<String,String> getPageNameMap() {
		return pageNameMap;
	}
}