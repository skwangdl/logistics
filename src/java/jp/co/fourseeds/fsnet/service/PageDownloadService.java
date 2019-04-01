package jp.co.fourseeds.fsnet.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jp.co.common.frame.service.BaseBusinessService;
import jp.co.fourseeds.fsnet.beans.accessLog.AccessLogBean;
import jp.co.fourseeds.fsnet.beans.page.PageAttachmentBean;
import jp.co.fourseeds.fsnet.common.ConstantContainer;
import jp.co.fourseeds.fsnet.common.util.AccessLogUtil;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.PageDownloadDao;

@Component
public class PageDownloadService extends BaseBusinessService{
	
	@Autowired
	private PageDownloadDao pageDownloadDao;
	
	//アクセスログ出力用ツールクラス
	@Autowired
	public AccessLogUtil accessLogUtil;
	
	private Map<String,String> pageNameMap = null;
	
	public List<PageAttachmentBean> getAttachmentList(String pageId, String isReserve) {
		return pageDownloadDao.getAttachmentList(pageId, isReserve);
	}

	/**
	 * ログフラグを設定する
	 * @param pageId
	 *            ページＩＤ
	 */
	public void updateLogFlag(String pageId) {
		pageDownloadDao.updateLogFlag(super.getLoginUserBean().getUserId(), pageId);
	}
	
	/**
	 * ファイルダウンロード
	 * @param pageId
	 *            ページＩＤ
	 * @param outputStream
	 *            ストリーム
	 * @param isStatic
	 *            静的コンテンツフラグ（１：静 ０：動）
	 * @param downloadAttchmentList
	 *            ダウンロードファイル名
	 * @param isReserve
	 *            予約フラグ（１：予約 ０：予約以外）
	 * @throws Exception 
	 */
	public void downloadContent(String pageId, OutputStream outputStream, boolean isStatic,
			String downloadAttchmentList, String isReserve, AccessLogBean accessLogBean, String pageNm) throws Exception {
		// ダウンロードファイル名Map
		Map<String, String> downloadAttchmentMap = null;
		// 動的コンテンツフラグ
		if (!isStatic) {
			// ダウンロードファイル名Map初期化
			downloadAttchmentMap = new HashMap<String, String>();
			// ダウンロードファイル名配列を取得
			StringTokenizer stringTokenizer = new StringTokenizer(downloadAttchmentList, "|");
			// ダウンロードファイルがある場合。
			while (stringTokenizer.hasMoreElements()) {
				// ダウンロードファイル名称を取得
				String downloadAttchment = (String) stringTokenizer.nextElement();
				// 取得のファイル名称はブランク以外場合
				if (!downloadAttchment.equals("")) {
					// 取得のファイル名称はダウンロードファイル名Mapに設定する。
					downloadAttchmentMap.put(downloadAttchment, "");
				}
			}
		}
		// ファイル保存パスを取得する。
		List<String> contentPathList = pageDownloadDao.getContentAbsolutePath(pageId, isStatic, isReserve);
		// ファイル保存Mapを取得。
		pageNameMap = pageDownloadDao.getPageNameMap();
		// 
		ZipOutputStream zipOutputStream = null;
		// アクセスログ詳細
		StringBuffer message = new StringBuffer();
		message.append(pageNm + "【アクション】添付ファイルダウンロード([");
		try {
			zipOutputStream = new ZipOutputStream(new BufferedOutputStream(outputStream));
			// ダウンロードパスを取得する
			for (String path : contentPathList) {
				// ファイルを設定
				File fileObject = new File(path);
				// 動的コンテンツフラグ且ファイル場合
				if (!isStatic && fileObject.isFile()) {
					// ファイル名称はダウンロードファイル名Mapにありません場合
					if (downloadAttchmentMap.get(fileObject.getName()) == null) {
						continue;
					}
				}
				// ダウンロードファイル名称を設定する。
				String zipEntryName = (String) pageNameMap.get(fileObject.getName());
				// ファイルダウンロードログ情報削除
				pageDownloadDao.deleteFileToZip(super.getLoginUserBean().getUserId(), pageId, zipEntryName);
				// ファイルダウンロードログ情報登録
				pageDownloadDao.insertDownFlag(super.getLoginUserBean().getUserId(), pageId, zipEntryName);
				// ダウンロードファイル名称は設定しなかった場合。
				if (zipEntryName == null || zipEntryName.equals("")) {
					zipEntryName = fileObject.getName();
				}
				// ファイルを圧縮する。
				addFileToZip(fileObject, zipOutputStream, zipEntryName);
				message.append(zipEntryName + ",");
			}
			String messageStr = message.toString();
			if (messageStr.lastIndexOf(",") > 0) {
				messageStr = messageStr.substring(0, messageStr.lastIndexOf(","));
			}
			// アクセス詳細を設定する。
			accessLogBean.setAccessDetail(messageStr + "])");

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (zipOutputStream != null) {
				try {
					zipOutputStream.close();
				} catch (IOException e) {
				}
			}
		}

	}
	
	/**
	 * ファイルダウンロード
	 * @param pageId
	 *            ページＩＤ
	 * @param outputStream
	 *            ストリーム
	 * @param isStatic
	 *            静的コンテンツフラグ（０：静 １：動）
	 * @param downloadAttchmentList
	 *            ダウンロードファイル名
	 * @param response
	 *            レスポンス
	 * @param isReserve
	 *            予約フラグ（１：予約 ０：予約以外）
	 * @throws Exception 
	 */
	public void downloadContent(String pageId, OutputStream outputStream, boolean isStatic,
			String downloadAttchmentList, HttpServletResponse response, String isReserve, AccessLogBean accessLogBean,
			String pageNm)
					throws Exception {

		// ファイル名を取得する。
		String zipEntryName = null;
		// ファイルパスリスト
		List<String> contentPathList = pageDownloadDao.getContentAbsolutePath(pageId, isStatic, isReserve);
		// ページ名称Ｍａｐ取得
		pageNameMap = pageDownloadDao.getPageNameMap();
		// ファイルパス取得
		String filePath = contentPathList.get(0);
		// 静的コンテンツ(一つファイルがあるだけ)ダウンロード時
		if (isStatic) {
			// ファイルパス取得よりファイルを取得する。
			File fileObject = new File(filePath);
			// 
			String key = new String();
			// ファイル場合
			if (fileObject.isFile()) {
				key = fileObject.getName();
			}
			// ファイル名を取得する。
			zipEntryName = pageNameMap.get(key);
			// ファイルダウンロードログ情報削除
			pageDownloadDao.deleteFileToZip(super.getLoginUserBean().getUserId(), pageId, zipEntryName);
		} else {
			// ファイルパスワード名
			String filePasswordName = downloadAttchmentList.substring(0, downloadAttchmentList.indexOf("|"));
			// ファイル名取得
			zipEntryName = pageNameMap.get(filePasswordName);
			// ファイルダウンロードログ情報削除
			pageDownloadDao.deleteFileToZip(super.getLoginUserBean().getUserId(), pageId, zipEntryName);
			// ファイルダウンロードログ情報登録
			pageDownloadDao.insertDownFlag(super.getLoginUserBean().getUserId(), pageId, zipEntryName);
			for (int i = 0; i < contentPathList.size(); i++) {
				String temp = contentPathList.get(i);
				if (temp.indexOf(filePasswordName) > 0) {
					filePath = temp;
					break;
				}
			}
		}
		// コード化形式を設定する。
		zipEntryName = URLEncoder.encode(zipEntryName,"UTF8");
		// ダウンロード済
		response.setCharacterEncoding("shift_jis");
		response.setContentType("text/plain;charset=shift-jis");
		response.setHeader("Content-Disposition", "attachment; filename=" + zipEntryName);
		response.setHeader("Cache-Control", "");
		response.setHeader("Pragma", "");
		if (!isStatic) {
			// アクセスログ詳細
			StringBuffer message = new StringBuffer();
			message.append(pageNm + "【アクション】添付ファイルダウンロード([");
			message.append(zipEntryName + "])");
			String messageStr = message.toString();
			// アクセス詳細を設定する。
			accessLogBean.setAccessDetail(messageStr);
			accessLogUtil.writeAccessLog(accessLogBean);
		}
		try {
			File file;
			// パスよりファイル取得
			file = new File(filePath);
			BufferedInputStream br = new BufferedInputStream(new FileInputStream(file));
			byte[] buf = new byte[ConstantContainer.BUFFER_SIZE];
			int len = 0;
			while ((len = br.read(buf)) > 0)
				outputStream.write(buf, 0, len);
			br.close();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * @param fileObject
	 *            圧縮ファイル
	 * @param zipOutputStream
	 *            圧縮ストリーム
	 * @param zipEntryName
	 *            圧縮ファイル名
	 */
	private void addFileToZip(File fileObject, ZipOutputStream zipOutputStream,
			String zipEntryName) throws Exception {
		
		// パラメータはフォルダ場合
		if (fileObject.isDirectory()) {
			// ファイルリストを取得
			File files[] = fileObject.listFiles();
			for (int i = 0; i < files.length; i++) {
				// ファイル名を取得
				String subZipEntryName = (String) pageNameMap.get(files[i].getName());
				if (subZipEntryName == null || subZipEntryName.equals("")) {
					// ファイルを設定する。
					subZipEntryName = StringUtil.nullToBlank((files[i].getName()));
				}
				// 圧縮ファイル
				addFileToZip(files[i], zipOutputStream, zipEntryName + "\\" + subZipEntryName);
			}

		// パラメータはファイル場合
		} else {
			byte dataBuffer[] = new byte[ConstantContainer.ZIP_BUFFER_SIZE];
			BufferedInputStream bufferedInputStream = null;
			try {
				bufferedInputStream = new BufferedInputStream(new FileInputStream(fileObject), ConstantContainer.ZIP_BUFFER_SIZE);
				// 圧縮ファイル
				zipOutputStream.putNextEntry(new ZipEntry(zipEntryName));
				int count;
				while ((count = bufferedInputStream.read(dataBuffer, 0, ConstantContainer.ZIP_BUFFER_SIZE)) != -1) {
					zipOutputStream.write(dataBuffer, 0, count);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			} finally {
				if (bufferedInputStream != null) {
					bufferedInputStream.close();
				}
			}
		}
	}
}
