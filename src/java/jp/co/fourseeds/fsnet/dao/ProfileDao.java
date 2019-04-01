package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import jp.co.fourseeds.fsnet.beans.profile.ProfileBean;

import jp.co.common.frame.beans.LabelValueBean;
import jp.co.common.frame.dao.BaseDao;

/**
 * プロフィール機能Dao実装クラス
 * 
 * File Name: ProfileDao.java 
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
@Repository
public class ProfileDao extends BaseDao {

	/**
	 * プロフィール情報を取得
	 * @param ProfileBean　プロフィールbean　(　ユーザIDと　ユーザパスワード)
	 * @return　検索結果
	 */
	@SuppressWarnings("rawtypes")
	public ProfileBean getProfile(ProfileBean profileBean) {
		//　DBから検索結果を取得
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", profileBean.getUserId());
		param.put("PARA_PASSWORD", profileBean.getPassword());
		
		return this.sqlSessionTemplate.selectOne("profile.getProfile", param);
	}/**
	 * プロフィール情報を取得
	 * 顔画像取得ため
	 * @param ProfileBean　プロフィールbean　(　ユーザID)
	 * @return　検索結果
	 */
	@SuppressWarnings("rawtypes")
	public ProfileBean getProfileByUserId(ProfileBean profileBean) {
		//　DBから検索結果を取得
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", profileBean.getUserId());
		
		return this.sqlSessionTemplate.selectOne("profile.getProfileByUserId", param);
	}

	/**プロフィール更新
	 * @param　profileBean
	 * */
	public void updateProfile(ProfileBean profileBean){
		this.sqlSessionTemplate.update("profile.updateUserMasterProfile", profileBean);
	}
	/**
	 * ユーザイメージ件数を取得
	 * @param profileBean
	 * @return 検索結果
	 * */
	public int getPersonIMGCount(ProfileBean profileBean){
		// 検索条件を設定
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", profileBean.getUserId());
		
		//　DBから検索結果を取得
		return (Integer)this.sqlSessionTemplate.selectOne("profile.getPersonIMGCount", param);
	}
	/**顔画像登録
	 * @param　profileBean
	 * */
	public void insertPersonIMG(ProfileBean profileBean){
		this.sqlSessionTemplate.insert("profile.insertPersonIMG", profileBean);
	}
	/**顔画像更新
	 * @param　profileBean
	 * */
	public void updateUserImgMasterProfile(ProfileBean profileBean){
		this.sqlSessionTemplate.update("profile.updateUserImgMasterProfile", profileBean);
	}
	/**顔画像更新 imgflgOnly
	 * @param　profileBean
	 * */
	public void updateUserImgMasterProfileimgflgOnly(ProfileBean profileBean){
		this.sqlSessionTemplate.update("profile.updateUserImgMasterProfileimgflgOnly", profileBean);
	}
}