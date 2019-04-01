package jp.co.fourseeds.fsnet.service;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.fourseeds.fsnet.beans.LoginImageFormBean;
import jp.co.fourseeds.fsnet.common.ConstantContainer;
import jp.co.fourseeds.fsnet.common.util.CommonUtil;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.LoginImageDao;

import jp.co.common.frame.service.BaseBusinessService;
import jp.co.common.frame.util.FileUtil;
import jp.co.common.frame.util.prop.FsPropertyUtil;

/**
 * ログイン画面画像切替機能サービス実装クラス
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2015/12/01		    NTS        	       作成
 *
 **/
@Component
public class LoginImageService extends BaseBusinessService{
	
	@Autowired
	private LoginImageDao loginImageDao;
	
	/**
	 * 画像IDを取得
	 * 
	 * @return String
	 */
	public String getLoginImageId() {
		return loginImageDao.getLoginImageId();
	}
	
	/**
	 * ログイン画面画像切替情報を取得
	 * @param param
	 * @return List
	 */
	public List<LoginImageFormBean> getLoginImageList(LoginImageFormBean loginImageFormBean, String strOrderBy) {
		return loginImageDao.getLoginImageList(loginImageFormBean, strOrderBy);
	}
	
	/**
	 * 登録済データとの期間重複チェック
	 * 
	 * @return int
	 */
	public boolean checkPeriodRepeat(LoginImageFormBean loginImageFormBean) {
		
		int count = loginImageDao.checkPeriodRepeat(loginImageFormBean);
		// 登録済データとの期間重複データを存在の場合、メッセージボックスを出す
		if (count > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 登録済データとの期間重複チェック
	 * 
	 * @return int
	 */
	public boolean checkPeriodRepeatMain(LoginImageFormBean loginImageFormBean) {
		
		int count = loginImageDao.checkPeriodRepeatMain(loginImageFormBean);
		// 登録済データとの期間重複データを存在の場合、メッセージボックスを出す
		if (count > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 登録済データとの期間重複チェック
	 * 
	 * @return int
	 */
	public boolean isBeforeInfoExist(LoginImageFormBean loginImageFormBean) {
		
		int count = loginImageDao.isBeforeInfoExist(loginImageFormBean);
		// 登録済データとの期間重複データを存在の場合、メッセージボックスを出す
		if (count > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 登録済データとの期間重複チェック
	 * 
	 * @return int
	 */
	public boolean checkPeriodRepeatCopy(LoginImageFormBean loginImageFormBean) {
		
		int count = loginImageDao.checkPeriodRepeatCopy(loginImageFormBean);
		// 登録済データとの期間重複データを存在の場合、メッセージボックスを出す
		if (count > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * ログイン画面画像切替情報を追加
	 * 
	 * @param loginImageFormBean
	 * @param loginUser
	 * @return flag
	 * @throws Exception 
	 */
	public void insertLoginImgMaster(LoginImageFormBean loginImageFormBean, String imgId) throws Exception {
		
		// 画像IDを設定
		loginImageFormBean.setLoginImageId(imgId);
		// 作成者更新者を設定
		loginImageFormBean.setCreateBy(getLoginUserBean().getUserId());
		loginImageFormBean.setUpdateBy(getLoginUserBean().getUserId());
		
		// ログイン画面画像切替情報を追加
		loginImageDao.insertLoginImgMaster(loginImageFormBean);
		
		//■■■画像ファイルアップロード
		try {
			uploadFile(loginImageFormBean, imgId);
		} catch (Exception e) {
			if (!FileUtil.ping(FsPropertyUtil.getStringProperty("file.server"))){
				FileUtil.ServerAccessException();
			} else {
				throw e;
			}
		}
	}
	
	/**
	 * ログイン画面画像切替情報を追加
	 * 
	 * @param loginImageFormBean
	 * @param loginUser
	 * @return flag
	 * @throws Exception 
	 */
	public void insertLoginImgMasterMain(LoginImageFormBean loginImageFormBean, String imgId) throws Exception {
		
		// 画像IDを設定
		loginImageFormBean.setLoginImageId(imgId);
		// 作成者更新者を設定
		loginImageFormBean.setCreateBy(getLoginUserBean().getUserId());
		loginImageFormBean.setUpdateBy(getLoginUserBean().getUserId());
		
		// ログイン画面画像切替情報を追加
		loginImageDao.insertLoginImgMasterMain(loginImageFormBean);
		
		//■■■画像ファイルアップロード
		try {
			uploadFileMain(loginImageFormBean, imgId);
		} catch (Exception e) {
			if (!FileUtil.ping(FsPropertyUtil.getStringProperty("file.server"))){
				FileUtil.ServerAccessException();
			} else {
				throw e;
			}
		}
	}
	
	/**
	 * ログイン画面画像切替情報を追加
	 * 
	 * @param loginImageFormBean
	 * @param loginUser
	 * @return flag
	 * @throws Exception 
	 */
	public void copyLoginImgMasterMain(LoginImageFormBean loginImageFormBean, String imgId, String oldImgId) throws Exception {
		
		// 画像IDを設定
		loginImageFormBean.setLoginImageId(imgId);
		// 作成者更新者を設定
		loginImageFormBean.setCreateBy(getLoginUserBean().getUserId());
		loginImageFormBean.setUpdateBy(getLoginUserBean().getUserId());
		
		// 画像ファイルパススを取得
		String filePath = FsPropertyUtil.getStringProperty("loginImage.upload.path") + "/" + ConstantContainer.MAIN_IMG_PATH;
		for (int i = 1; i <= 10; i++) {
			// 画面の参照画像ファイルを取得
			File img = (File) getMethodValue(loginImageFormBean, "img" + i);
			// 画面の画像削除フラグを取得
			String imgDeleteFlag = (String) getMethodValue(loginImageFormBean, "img" + i + "DeleteFlag");
			// 旧画像ファイル名を取得
			String oldImgFileName = (String) getMethodValue(loginImageFormBean, "oldImg" + i + "FileName");
			// 画像削除フラグ　＜＞　１の場合
			if (!"1".equals(imgDeleteFlag)) {
				// 画面の画像不存在且旧画像存在の場合
				if (StringUtil.isBlank(img) && !StringUtil.isBlank(oldImgFileName)) {
					// 旧画像ファイルの拡張子を取得
					String suffix = oldImgFileName.substring(oldImgFileName.lastIndexOf("."));
					// 旧画像ファイルのパスを取得
					String oldImgPath = filePath + oldImgId + "/" + oldImgId + i + suffix;
					// 旧画像ファイルを取得
					File imgNewFile = new File(oldImgPath);
					// 画像ファイルを設定
					setMethodValue(loginImageFormBean, "img" + i, imgNewFile);
					// 旧画像ファイル名を取得
					String imgFileName = (String) getMethodValue(loginImageFormBean, "oldImg" + i + "FileName");
					// 画像ファイル名を設定
					setMethodValue(loginImageFormBean, "img" + i + "FileName", imgFileName);
				}
			} else {
				// 画像ファイル名を設定
				setMethodValue(loginImageFormBean, "img" + i + "FileName", "");
			}
		}
		
		// ログイン画面画像切替情報を追加
		loginImageDao.insertLoginImgMasterMain(loginImageFormBean);
		
		//■■■画像ファイルアップロード
		try {
			uploadFileMain(loginImageFormBean, imgId);
		} catch (Exception e) {
			if (!FileUtil.ping(FsPropertyUtil.getStringProperty("file.server"))){
				FileUtil.ServerAccessException();
			} else {
				throw e;
			}
		}
	}
	
	/**
	 * ログイン画面画像切替情報を削除
	 * 
	 * @param loginImageFormBean
	 */
	public String updateLoginImgMasterInvalid(LoginImageFormBean loginImageFormBean) throws Exception {
		
		String strDeleteImgId = "";
		// 画面のログイン画面画像切替情報リストを取得
		List<LoginImageFormBean> loginImageList = loginImageFormBean.getLoginImageList();
		
		for (int i = 0; i < loginImageList.size(); i++) {
			// 該当ログイン画面画像切替情報を取得
			LoginImageFormBean loginImageBean = loginImageList.get(i);
			
			// 画面のセレクトボックスを選択の場合、該当ログイン画面画像切替情報を削除
			if("on".equals(loginImageBean.getSelectFlag())){
				
				// 更新者を設定
				loginImageBean.setUpdateBy(getLoginUserBean().getUserId());
				
				// ログイン画面画像切替を削除
				loginImageDao.updateLoginImgMasterInvalid(loginImageBean);
				
				// 画像ID
				String imgId = loginImageBean.getLoginImageId();
				// 削除の画像ＩＤリストを設定
				strDeleteImgId = strDeleteImgId + imgId + "|";
				
				//■■■旧画像ファイル削除
				try {
					deleteFile(loginImageBean, imgId);
				} catch (Exception e) {
					if (!FileUtil.ping(FsPropertyUtil.getStringProperty("file.server"))){
						FileUtil.ServerAccessException();
					} else {
						throw e;
					}
				}
			}
		}
		return strDeleteImgId;
	}
	
	/**
	 * ログイン画面画像切替情報を削除
	 * 
	 * @param loginImageFormBean
	 */
	public boolean checkValidImg(LoginImageFormBean loginImageFormBean) throws Exception {
		
		// 画面のログイン画面画像切替情報リストを取得
		List<LoginImageFormBean> loginImageList = loginImageFormBean.getLoginImageList();
		List<String> loginImageIdList = new ArrayList<String>();
		for (int i = 0; i < loginImageList.size(); i++) {
			// 該当ログイン画面画像切替情報を取得
			LoginImageFormBean loginImageBean = loginImageList.get(i);
			
			// 画面のセレクトボックスを選択の場合、該当ログイン画面画像切替情報を削除
			if("on".equals(loginImageBean.getSelectFlag()) 
					&& "2".equals(loginImageBean.getDisplayPositionCd())){
				loginImageIdList.add(loginImageBean.getLoginImageId());
			}
		}
		LoginImageFormBean loginImageBean = new LoginImageFormBean();
		loginImageBean.setLoginImageIdList(CommonUtil.getGroupSql(loginImageIdList));
		loginImageBean.setDisplayPositionCd("2");
		if (loginImageIdList.size() > 0 && !isBeforeInfoExist(loginImageBean)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 更新画面のログイン画面画像切替情報を取る
	 * 
	 * @param loginImageFormBean
	 */
	public LoginImageFormBean getLoginImageDetailInfo(LoginImageFormBean loginImageFormBean) {
		return loginImageDao.getLoginImageDetailInfo(loginImageFormBean);
	}
	
	/**
	 * メイン更新画面のログイン画面画像切替情報を取る
	 * 
	 * @param loginImageFormBean
	 */
	public LoginImageFormBean getLoginImageMainInfo(LoginImageFormBean loginImageFormBean) {
		return loginImageDao.getLoginImageMainInfo(loginImageFormBean);
	}
	
	/**
	 * ログイン画面画像切替情報を更新
	 * 
	 * @param loginImageFormBean
	 * @param loginUser
	 * @return flag
	 * @throws Exception 
	 */
	public void updateLoginImgMaster(LoginImageFormBean loginImageFormBean) throws Exception {
		// 更新者情報を設定
		loginImageFormBean.setUpdateBy(getLoginUserBean().getUserId());
		// ログイン画面画像切替情報を更新
		loginImageDao.updateLoginImgMaster(loginImageFormBean);
		
		// 画面の参照画像ファイルを取得
		File img = loginImageFormBean.getImg();
		// 添付ファイル追加(画面の画像ファイル変更)の場合
		if (!StringUtil.isBlank(img)) {
			try {
				// 画像ID
				String imgId = loginImageFormBean.getLoginImageId();
				
				//■■■旧画像ファイル削除
				deleteFile(loginImageFormBean, imgId);
				
				//■■■新画像ファイルアップロード
				uploadFile(loginImageFormBean, imgId);
			} catch (Exception e) {
				if (!FileUtil.ping(FsPropertyUtil.getStringProperty("file.server"))){
					FileUtil.ServerAccessException();
				} else {
					throw e;
				}
			}
		}
	}
	
	/**
	 * ログイン画面画像切替情報を更新
	 * 
	 * @param loginImageFormBean
	 * @param loginUser
	 * @return flag
	 * @throws Exception 
	 */
	public void updateLoginImgMasterMain(LoginImageFormBean loginImageFormBean) throws Exception {
		// 更新者情報を設定
		loginImageFormBean.setUpdateBy(getLoginUserBean().getUserId());
		// ログイン画面画像切替情報を更新
		loginImageDao.updateLoginImgMasterMain(loginImageFormBean);
		
		try {
			// 画像ID
			String imgId = loginImageFormBean.getLoginImageId();
			
			//■■■旧画像ファイル削除
			deleteFileMain(loginImageFormBean, imgId);
			
			//■■■新画像ファイルアップロード
			uploadFileMain(loginImageFormBean, imgId);
		} catch (Exception e) {
			if (!FileUtil.ping(FsPropertyUtil.getStringProperty("file.server"))){
				FileUtil.ServerAccessException();
			} else {
				throw e;
			}
		}
	}

	/**
	 * ログインイメージファイルをアップロード
	 * 
	 * @param loginImageFormBean
	 */
	private void uploadFile(LoginImageFormBean loginImageBean, String imgId) throws Exception {
		// 画像ファイルパススを取得
		String filePath = FsPropertyUtil.getStringProperty("loginImage.upload.path");

		// 画面の参照画像ファイルを取得
		File imgNewFile = new File(loginImageBean.getImg().getPath());
		// 画面の画像名を取得
		String imgFileName = loginImageBean.getImgFileName();
		// 画面の新画像ファイルの拡張子を取得
		String newFileSuffix = imgFileName.substring(imgFileName.lastIndexOf("."));
		// アップロードファイル名を設定
		String uploadimgFileName = imgId + newFileSuffix;
		// 添付ファイルをアップロードする
		FileUtil.uploadFile(imgNewFile, uploadimgFileName , filePath);
	}
	
	/**
	 * ログインイメージファイルをアップロード
	 * 
	 * @param loginImageFormBean
	 */
	private void uploadFileMain(LoginImageFormBean loginImageBean, String imgId) throws Exception {
		// 画像ファイルパススを取得
		String filePath = FsPropertyUtil.getStringProperty("loginImage.upload.path") + "/" + ConstantContainer.MAIN_IMG_PATH + imgId;
		for (int i = 1; i <= 10; i++) {
			// 画面の参照画像ファイルを取得
			File img = (File) getMethodValue(loginImageBean, "img" + i);
			// 画面の画像削除フラグを取得
			String imgDeleteFlag = (String) getMethodValue(loginImageBean, "img" + i + "DeleteFlag");
			// 画面の画像削除フラグ　＜＞　１且画像存在の場合、
			if (!"1".equals(imgDeleteFlag) && !StringUtil.isBlank(img)) {
				// 画面の画像を取得
				File imgNewFile = new File(img.getPath());
				// 画面の画像名を取得
				String imgFileName = (String) getMethodValue(loginImageBean, "img" + i + "FileName");
				// 画面の新画像ファイルの拡張子を取得
				String newFileSuffix = imgFileName.substring(imgFileName.lastIndexOf("."));
				// アップロードファイル名を設定
				String uploadimgFileName = imgId + i + newFileSuffix;
				// 添付ファイルをアップロードする
				FileUtil.uploadFile(imgNewFile, uploadimgFileName , filePath);
			}
		}
	}
	
	/**
	 * ログインイメージファイルを削除
	 * 
	 * @param loginImageFormBean
	 */
	private void deleteFileMain(LoginImageFormBean loginImageBean, String imgId) throws Exception {
		// 画像ファイルパススを取得
		String filePath = FsPropertyUtil.getStringProperty("loginImage.upload.path") + "/" + ConstantContainer.MAIN_IMG_PATH + imgId;
		for (int i = 1; i <= 10; i++) {
			// 旧画像名
			String oldImgName = (String) getMethodValue(loginImageBean, "oldImg" + i + "FileName");
			// 画像削除フラグ
			String imgDeleteFlag = (String) getMethodValue(loginImageBean, "img" + i + "DeleteFlag");
			// 画像削除フラグが「１」且旧画像名存在の場合、画像削除
			if ("1".equals(imgDeleteFlag) && !StringUtil.isBlank(oldImgName)) {
				// 画面の旧画像ファイルの拡張子を取得
				String oldFileSuffix = oldImgName.substring(oldImgName.lastIndexOf("."));
				// 削除ファイル名を設定
				String moveImgName = imgId + i + oldFileSuffix;
				// 旧ログイン画面画像切替のファイルを削除
				FileUtil.deleteFile(filePath, moveImgName);
			}
		}
	}
	
	private Object getMethodValue(LoginImageFormBean loginImageBean, String methodKey) throws Exception {
		Class<? extends LoginImageFormBean> clazz = loginImageBean.getClass();
		PropertyDescriptor pd = new PropertyDescriptor(methodKey, clazz);
		Method getMethod = pd.getReadMethod();
		return getMethod.invoke(loginImageBean);
	}
	
	private Object setMethodValue(LoginImageFormBean loginImageBean, String methodKey, Object methodValue) throws Exception {
		Class<? extends LoginImageFormBean> clazz = loginImageBean.getClass();
		PropertyDescriptor pd = new PropertyDescriptor(methodKey, clazz);
		Method setMethod = pd.getWriteMethod();
		return setMethod.invoke(loginImageBean, methodValue);
	}

	/**
	 * ログインイメージファイルを削除
	 * 
	 * @param loginImageFormBean
	 */
	private void deleteFile(LoginImageFormBean loginImageBean, String imgId) throws Exception {
		// メイン画像の場合、
		if ("2".equals(loginImageBean.getDisplayPositionCd())) {
			String filePath = FsPropertyUtil.getStringProperty("loginImage.upload.path");
			// 旧ログイン画面画像切替のファイルを削除
			FileUtil.deleteFile(new File(filePath + "/" + ConstantContainer.MAIN_IMG_PATH + "/" + imgId));
		// メイン画像以外の場合、
		} else {
			// 旧画像名
			String oldImgName = loginImageBean.getOldImgFileName();
			// 画面の旧画像ファイルの拡張子を取得
			String oldFileSuffix = oldImgName.substring(oldImgName.lastIndexOf("."));
			// 削除ファイル名を設定
			String moveImgName = imgId + oldFileSuffix;
			// 画像ファイルパススを取得
			String filePath = FsPropertyUtil.getStringProperty("loginImage.upload.path");
			// 旧ログイン画面画像切替のファイルを削除
			FileUtil.deleteFile(filePath, moveImgName);
		}
	}
}