package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.personalMail.PersonalMailBean;
import jp.co.fourseeds.fsnet.beans.personalMail.PersonalMailFormBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;

/**
 *  メールメンテ機能DAO実装クラス
 * 
 * File Name: PersonalMailDao.java 
 * Created: 2015/12/02
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 * 1.0		2015/12/02          NTS            作成
 *
 **/
@Repository
public class PersonalMailDao extends BaseDao {
	
	/**
	 * 登録数を取得
	 * @return
	 * @throws DataBaseException
	 */
	public int getMailCnt() {
		
		return (Integer)super.sqlSessionTemplate.selectOne("personalMail.getMailCnt");
	}
	
	/**
	 * 個別メール情報を取得
	 * @param form
	 * @param param
	 *              ソート項目
	 * @return
	 * @throws DataBaseException
	 */
	public List<PersonalMailBean> getPersonalMailList(PersonalMailFormBean form,Map<String, Object> param) {
		// 登録依頼のみフラグ
		param.put("PARA_ENTRY_FLAG", form.getEntryFlag());
		// 登録依頼のみフラグ
		param.put("PARA_PERSONAL_FLAG", form.getPersonalFlag());
		// ID
		param.put("PARA_USER_ID", form.getSearchUserId());
		// 利用者名
		param.put("PARA_USER_NAME", form.getSearchUserName());
		// 所属
		param.put("PARA_DEPT_NAME", form.getSearchDeptName());
		// メールアドレス
		param.put("PARA_MAIL_ID", form.getSearchMailId());
		// メールドメイン
		param.put("PARA_MAIL_SUFFIX", form.getSearchMailSuffixName());
		// 分類
		param.put("PARA_USER_DIV", form.getSearchUserDiv());
		
		return super.sqlSessionTemplate.selectList("personalMail.getPersonalMailList",param);
	}
	
	/**
	 * 退職者情報を取得（完了通知＋削除する＋退職日存在）
	 * @param form
	 * @param param
	 *              ソート項目
	 * @return
	 * @throws DataBaseException
	 */
	public List<PersonalMailBean> getQuitUserInfoList(PersonalMailFormBean form,Map<String, Object> param) {
		// ID
		param.put("PARA_USER_ID", form.getSearchUserId());
		// 利用者名
		param.put("PARA_USER_NAME", form.getSearchUserName());
		// 所属
		param.put("PARA_DEPT_NAME", form.getSearchDeptName());
		// メールアドレス
		param.put("PARA_MAIL_ID", form.getSearchMailId());
		// メールドメイン
		param.put("PARA_MAIL_SUFFIX", form.getSearchMailSuffixName());
		// 分類
		param.put("PARA_USER_DIV", form.getSearchUserDiv());
		
		return super.sqlSessionTemplate.selectList("personalMail.getQuitUserInfoList",param);
	}
	
	/**
	 * ユーザ情報を取得
	 * @param form
	 * @param param
	 *              ソート項目
	 * @return
	 * @throws DataBaseException
	 */
	public List<PersonalMailBean> getUserInfoList(PersonalMailFormBean form,Map<String, Object> param) {
		// ID
		param.put("PARA_USER_ID", form.getSearchUserId());
		// 利用者名
		param.put("PARA_USER_NAME", form.getSearchUserName());
		// 所属
		param.put("PARA_DEPT_NAME", form.getSearchDeptName());
		// メールアドレス
		param.put("PARA_MAIL_ID", form.getSearchMailId());
		// メールドメイン
		param.put("PARA_MAIL_SUFFIX", form.getSearchMailSuffixName());

		return super.sqlSessionTemplate.selectList("personalMail.getUserInfoList",param);
	}

	/**
	 * パスワードを取得
	 * @param なし
	 * @param 取得のパスワード
	 * 
	 */
	public String getPassword() {
		return super.sqlSessionTemplate.selectOne("personalMail.getPassword");
	}
	
	/**
	 * ユーザマスタの明細データを取得
	 * @param userId
	 *                  ユーザーID
	 * @return
	 *                  取得の明細データ
	 */
	public PersonalMailBean getUserMasterDetailInfo (String userId) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// ユーザーID
		param.put("PARA_USER_ID", userId);
		// ユーザマスタの明細データを取得 -
		return super.sqlSessionTemplate.selectOne("personalMail.getUserMasterDetailInfo",param);
	}
	
	/**
	 * 個別メールアドレステーブルデータを取得
	 * @param sequence
	 *                  SEQUENCE 
	 * @return
	 *                  取得の明細データ
	 */
	public PersonalMailBean getPersonalMailDetailInfo (String sequence) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// SEQUENCE
		param.put("PARA_SEQUENCE", sequence);
		// 個別メールアドレステーブルデータを取得
		return super.sqlSessionTemplate.selectOne("personalMail.getPersonalMailDetailInfo",param);
	}
	
	/**
	 * 個別メールアドレステーブルデータを取得
	 * @param form
	 *                  フォームビン 
	 * @return
	 *                  取得の明細データ
	 */ 
	public int getValidPersonalCnt (PersonalMailFormBean form) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// ユーザー姓
		param.put("PARA_USER_SEI", form.getUserSei());
		// ユーザー名
		param.put("PARA_USER_MEI", form.getUserMei());
		// メールアドレス
		param.put("PARA_MAIL_ADDRESS", form.getMailAddress());
		// 個別メールアドレステーブルデータを取得
		return (Integer)super.sqlSessionTemplate.selectOne("personalMail.getValidCnt",param);
	}
	
	/**
	 * 個別メールアドレステーブルデータを取得
	 * @param form
	 *                  フォームビン 
	 * @return
	 *                  取得の明細データ
	 */
	public int getValidMailCnt (PersonalMailFormBean form) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// メールアドレス
		param.put("PARA_MAIL_ADDRESS", form.getMailAddress());
		// 個別メールアドレステーブルデータを取得
		return (Integer)super.sqlSessionTemplate.selectOne("personalMail.getValidCnt",param);
	}
	
	/**
	 * 個別メールを物理削除
	 * @param form
	 *                  フォームビン 
	 * @return なし
	 * 
	 */
	public void deletePersonalMail(PersonalMailFormBean form) {
		
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// ユーザーIDがある場合。
		if(!StringUtil.isEmpty(form.getUserId())) {
			// メールアドレス
			param.put("PARA_USER_ID", form.getUserId());
		} else {
			// メールアドレス
			param.put("PARA_USER_SEI", form.getUserMei());
			// メールアドレス
			param.put("PARA_USER_MEI", form.getUserSei());
			// メールアドレス
			param.put("PARA_MAIL_ADDRESS", form.getMailAddress());
		}
		// 個別メールを物理削除
		super.sqlSessionTemplate.delete("personalMail.deletePersonalMail",param);
	}
	
	/**
	 * 個別メールを登録
	 * @param form
	 *                  フォームビン 
	 * @return なし
	 * 
	 */
	public void insertPersonalMail(PersonalMailFormBean form) {
		// 個別メールを登録
		super.sqlSessionTemplate.insert("personalMail.insertPersonalMail",form);
	}
	
	/**
	 * ユーザのメールとユーザ名前を取得
	 * @param userId
	 *                 ユーザーID 
	 * @param pwdSendDiv
	 *                 パスワード送付区分 
	 * @return
	 * 
	 */
	public PersonalMailBean getMailToUserInfo(String userId) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// ユーザーID 
		param.put("PARA_USER_ID", userId);
		// ユーザのメールアドレースを更新
		return super.sqlSessionTemplate.selectOne("personalMail.getMailToUserInfo",param);
	}
	
	/**
	 * 送信ユーザ名前を取得
	 * @param userId
	 *                 ユーザーID 
	 * @return
	 *                 取得のユーザー名称
	 * 
	 */
	public String getFromUserName(String userId) {
		// 検索条件MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// ユーザーID 
		param.put("PARA_USER_ID", userId);
		// ユーザのメールアドレースを更新
		return super.sqlSessionTemplate.selectOne("personalMail.getFromUserName",param);
	}
	
	/**
	 * 個別メールを論理削除
	 * @param form
	 */
	public void deletePersonalMailInvalid(PersonalMailFormBean form) {
		// 個別メールを論理削除
		super.sqlSessionTemplate.update("personalMail.deletePersonalMailInvalid",form);
	}
	
	/**
	 * 個別メールを更新
	 * @param form
	 */
	public void updatePersonalMail(PersonalMailFormBean form) {
		// 個別メールを更新
		super.sqlSessionTemplate.update("personalMail.updatePersonalMail",form);
	}
}