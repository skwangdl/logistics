package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.LoginImageFormBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;

/**
 * ログイン画面画像切替機能Dao実装クラス
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2015/12/01		    NTS        	       作成
 *
 **/
@Repository
public class LoginImageDao extends BaseDao {
	
	/**
	 * 画像IDを取得
	 * 
	 * @return String 検索結果
	 */
	public String getLoginImageId() {
		return this.sqlSessionTemplate.selectOne("loginImage.getLoginImageId");
	}
	
	/**
	 * ログイン画面画像切替情報を取る
	 * 
	 * @param param 検索条件
	 * @return List 検索結果
	 */
	public List<LoginImageFormBean> getLoginImageList(LoginImageFormBean loginImageFormBean, String strOrderBy) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		// 掲載期間 From
		String startDate= loginImageFormBean.getSearchStartDate();
		// 掲載期間 To
		String endDate= loginImageFormBean.getSearchEndDate();
		
		// 検索条件を設定
		// 掲載期間 From指定しない場合、最小の掲載期間 Fromを設定
		if (StringUtil.isEmpty(startDate)) {
			param.put("PARA_START_DATE", "19000101");
		// 掲載期間 From指定の場合、画面の 掲載期間 Fromを設定
		} else {
			param.put("PARA_START_DATE", startDate);
		}
		// 掲載期間 To指定しない場合、最大の掲載期間 Toを設定
		if (StringUtil.isEmpty(endDate)) {
			param.put("PARA_END_DATE", "29981231");
		// 掲載期間 To指定の場合、画面の 掲載期間 Toを設定
		} else {
			param.put("PARA_END_DATE", endDate);
		}
		param.put("PARA_DISPLAY_POSITION", loginImageFormBean.getSearchDisplayPosition());
		param.put("PARA_ORDER_BY", strOrderBy);
		
		//　DBから検索結果を取得
		return this.sqlSessionTemplate.selectList("loginImage.getLoginImageList", param);
	}
	
	/**
	 * 登録済データとの期間重複情報を取得
	 * 
	 * @return int 検索結果
	 */
	public int checkPeriodRepeat(LoginImageFormBean loginImageFormBean) {
		return (Integer)this.sqlSessionTemplate.selectOne("loginImage.checkPeriodRepeat", loginImageFormBean);
	}
	
	/**
	 * 登録済データとの期間重複情報を取得
	 * 
	 * @return int 検索結果
	 */
	public int checkPeriodRepeatMain(LoginImageFormBean loginImageFormBean) {
		return (Integer)this.sqlSessionTemplate.selectOne("loginImage.checkPeriodRepeatMain", loginImageFormBean);
	}
	
	/**
	 * 登録済データとの期間重複情報を取得
	 * 
	 * @return int 検索結果
	 */
	public int isBeforeInfoExist(LoginImageFormBean loginImageFormBean) {
		return (Integer)this.sqlSessionTemplate.selectOne("loginImage.isBeforeInfoExist", loginImageFormBean);
	}
	
	/**
	 * 登録済データとの期間重複情報を取得
	 * 
	 * @return int 検索結果
	 */
	public int checkPeriodRepeatCopy(LoginImageFormBean loginImageFormBean) {
		return (Integer)this.sqlSessionTemplate.selectOne("loginImage.checkPeriodRepeatCopy", loginImageFormBean);
	}
	
	/**
	 * ログイン画面画像切替情報を追加
	 * 
	 * @param loginImageFormBean
	 */
	public void insertLoginImgMaster(LoginImageFormBean loginImageFormBean) {
		
		this.sqlSessionTemplate.insert("loginImage.insertLoginImgMaster", loginImageFormBean);
	}
	
	/**
	 * ログイン画面画像切替情報を追加
	 * 
	 * @param loginImageFormBean
	 */
	public void insertLoginImgMasterMain(LoginImageFormBean loginImageFormBean) {
		
		this.sqlSessionTemplate.insert("loginImage.insertLoginImgMasterMain", loginImageFormBean);
	}
	
	/**
	 * ログイン画面画像切替情報を削除
	 * 
	 * @param loginImageBean
	 */
	public void updateLoginImgMasterInvalid(LoginImageFormBean loginImageFormBean) {
		
		this.sqlSessionTemplate.delete("loginImage.updateLoginImgMasterInvalid", loginImageFormBean);
	}
	
	/**
	 * 更新画面のログイン画面画像切替情報を取る
	 * 
	 * @param loginImageFormBean 検索条件
	 * @return List 検索結果
	 */
	public LoginImageFormBean getLoginImageDetailInfo(LoginImageFormBean loginImageFormBean) {
		
		return this.sqlSessionTemplate.selectOne("loginImage.getLoginImageDetailInfo", loginImageFormBean);
	}
	
	/**
	 * メイン更新画面のログイン画面画像切替情報を取る
	 * 
	 * @param loginImageFormBean 検索条件
	 * @return List 検索結果
	 */
	public LoginImageFormBean getLoginImageMainInfo(LoginImageFormBean loginImageFormBean) {
		
		return this.sqlSessionTemplate.selectOne("loginImage.getLoginImageMainInfo", loginImageFormBean);
	}
	
	
	/**
	 * ログイン画面画像切替情報を更新
	 * 
	 * @param loginImageFormBean
	 */
	public void updateLoginImgMaster(LoginImageFormBean loginImageFormBean) {
		
		this.sqlSessionTemplate.update("loginImage.updateLoginImgMaster", loginImageFormBean);
	}
	
	/**
	 * ログイン画面画像切替情報を更新
	 * 
	 * @param loginImageFormBean
	 */
	public void updateLoginImgMasterMain(LoginImageFormBean loginImageFormBean) {
		
		this.sqlSessionTemplate.update("loginImage.updateLoginImgMasterMain", loginImageFormBean);
	}
}