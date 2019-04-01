package jp.co.fourseeds.fsnet.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.fourseeds.fsnet.beans.UserBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.UserDao;

import jp.co.common.frame.service.BaseBusinessService;
import jp.co.common.frame.util.FileUtil;
import jp.co.common.frame.util.prop.FsPropertyUtil;

/**
 * ユーザ情報機能サービス実装クラス
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2016/01/27		    NTS        	       作成
 * 1.1		2017/09/05			NTS			見直し修正
 *
 **/
@Component
public class UserService extends BaseBusinessService{
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * ユーザ情報を取得
	 * @param userBean
	 * @param strOrderBy
	 * @return 検索結果
	 */
	public List<UserBean> getUserList(UserBean userBean, String strOrderBy) {
		return userDao.getUserList(userBean, strOrderBy,getLoginUserBean());
	}
	
	/**
	 * ユーザ詳細情報を取得
	 * @param userBean
	 * @return 検索結果
	 */
	public UserBean getUserDetailInfo(UserBean userBean) {
		// ユーザ詳細情報を取得
		UserBean resultBean = userDao.getUserDetailInfo(userBean);
		
		return resultBean;
	}
	
	/**
	 * ユーザ写真詳細情報を取得
	 * @param userBean
	 * @return 検索結果
	 */
	public UserBean getUserImgDetail(UserBean userBean) {
		return userDao.getUserDetailInfo(userBean);
	}
	
	/**
	 * イメージ情報を取得
	 * @param userBean
	 * @return 検索結果
	 */
	public byte[] getImgDat(String userId) {
		return userDao.getImgDat(userId);
	}
	
	/**
	 * ユーザ情報を更新
	 * @param userBean
	 * @throws Exception 
	 */
	public void updateUserMaster(UserBean userBean) throws Exception {
		//■■■写真のファイルアを処理
		// 写真のファイルア設定の場合
		if (!StringUtil.isBlank(userBean.getPhotoPath())) {
			// 社員写真アドレスと個人登録画像を設定
			setPhotoInfo(userBean);
			
			// ユーザイメージ存在しない場合
			if (userDao.getImgCount(userBean) == 0) {
				// ユーザイメージ情報を登録
				userDao.insertUserImgMaster(userBean, getLoginUserBean());
			} else {
				// ユーザイメージ情報を更新
				userDao.updateUserImgMaster(userBean, getLoginUserBean());
			}
		}
		
		//■■■ユーザ情報を更新
		userDao.updateUserMaster(userBean, getLoginUserBean());
		
	}
	
	/**
	 * ユーザグループ明細情報既存の変更ユーザ物理削除
	 * @param userBean
	 * @throws Exception 
	 */
	public void deleteUserGroup(UserBean userBean) throws Exception {
		userDao.deleteUserGroup(userBean);
	}
	
	/**
	 * ユーザグループ情報取得
	 * @param userBean
	 * @throws Exception 
	 */
	public UserBean saerchUserGroupInfo(UserBean userBean) throws Exception {
		return userDao.saerchUserGroupInfo(userBean);
	}
	
	/**
	 * ユーザグループを更新
	 * @param userBean
	 * @throws Exception 
	 */
	public void insertUserGroupInfo(UserBean userBean) throws Exception {
		userDao.insertUserGroupInfo(userBean, getLoginUserBean());
	}
	
	/**
	 * 社員写真アドレスと個人登録画像を設定
	 * @param userBean
	 * @throws Exception 
	 */
	private void setPhotoInfo(UserBean userBean) throws Exception {
		// 社員写真アドレスを設定
		String photoUrlPath = FsPropertyUtil.getStringProperty("photo.Url.path");
		String photoAdd = photoUrlPath + "/" + userBean.getPhotoFileName();
		userBean.setPhotoAdd(photoAdd);
		
		// 個人登録画像を設定
		File photo = new File(userBean.getPhotoPath());
		userBean.setImgDat(FileUtil.fileToByte(photo));
	}
	
}