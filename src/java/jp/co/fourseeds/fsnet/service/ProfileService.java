package jp.co.fourseeds.fsnet.service;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import jp.co.fourseeds.fsnet.beans.profile.ProfileBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;

import org.springframework.stereotype.Component;

import jp.co.fourseeds.fsnet.dao.ProfileDao;

import jp.co.common.frame.service.BaseBusinessService;

import jp.co.common.frame.util.FileUtil;
import jp.co.common.frame.util.prop.FsPropertyUtil;

/**
 * プロフィールサービス実装クラス
 * 
 * File Name: ProfileService.java 
 * Created: 2016/01/07
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2016/01/07		    NTS        	       作成
 * 1.1		2017/12/11		    NTS        	       見直し修正
 *
 **/
@Component
public class ProfileService extends BaseBusinessService{

	@Autowired
	private ProfileDao profileDao;

	/**
	 * プロフィール情報を取得
	 * @param profileBean　プロフィール情報
	 * @return　検索結果
	 * @throws ParseException 
	 */
	@SuppressWarnings("rawtypes")
	public ProfileBean getProfile(ProfileBean profileBean) throws ParseException {
		//　DBから検索結果を取得
		ProfileBean profile = profileDao.getProfile(profileBean);
		
		//日付格式を yyyy/MM/DD で変更（元：　yyyy-MM-DD HH:mm:ss:S）
		String NecessityDisplayStartDate = profile.getNecessityDisplayStartDate();
		if(!"".equals(NecessityDisplayStartDate.trim())){
			// Convert input string into a date
			DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
				
			if("".equals(NecessityDisplayStartDate)){
				Date nowDate = new Date();
				NecessityDisplayStartDate = inputFormat.format(nowDate);
			}
			
			// Format date into output format
			SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd");
			String outputString = outputFormat.format(inputFormat.parse(NecessityDisplayStartDate));
			profile.setNecessityDisplayStartDate(outputString);
			
		}
		
		return profile;
	}
	/**
	 * プロフィール情報を取得
	 * 顔画像取得ため
	 * @param profileBean　プロフィール情報
	 * @return　検索結果
	 */
	@SuppressWarnings("rawtypes")
	public ProfileBean getProfileByUserId(ProfileBean profileBean) throws ParseException {
		
		return profileDao.getProfileByUserId(profileBean);
	}
	
	public void updateProfile(ProfileBean profilebean) throws Exception {
		//■■■写真のファイルアを処理
		// 写真のファイルア設定の場合
		if (!StringUtil.isBlank(profilebean.getPersonImgPath())) {
			// 社員写真アドレスと個人登録画像を設定
			this.setPhotoInfo(profilebean);
			
			// ユーザイメージ存在しない場合
			if (profileDao.getPersonIMGCount(profilebean) == 0) {
				// ユーザイメージ情報を登録
				profileDao.insertPersonIMG(profilebean);
			} else {
				// ユーザイメージ情報を更新
				profileDao.updateUserImgMasterProfile(profilebean);
			}
		}
		else{
			if("0".equals(profilebean.getOriginal())){
				// ユーザイメージ情報を更新 imgflag only
				profileDao.updateUserImgMasterProfileimgflgOnly(profilebean);
			}
		}
		profileDao.updateProfile(profilebean);
	}
	
	/**
	 * 社員写真アドレスと個人登録画像を設定
	 * @param profilebean
	 * @throws Exception 
	 */
	public void setPhotoInfo(ProfileBean profilebean) throws Exception {
		// 社員写真アドレスを設定
		String photoUrlPath = FsPropertyUtil.getStringProperty("photo.Url.path");
		String photoAdd = photoUrlPath + "/" + profilebean.getPersonImgName();
		profilebean.setPersonImgAdd(photoAdd);
		
		// 個人登録画像を設定
		File photo = new File(profilebean.getPersonImgPath());
		profilebean.setPersonImgDat(FileUtil.fileToByte(photo));
	}
	
}