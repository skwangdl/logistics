package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.ContentSearchResultBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;

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
public class SendMailDao extends BaseDao {

	/**
	 * 社員検索結果を取得
	 * @param param　検索条件
	 * @param from　検索開始
	 * @param length　検索レコード数
	 * @return　検索結果
	 */
	public List getMailAddCount (ContentSearchResultBean notReadedInfo) {
		String userid = StringUtil.nullToBlank(notReadedInfo.getUserId());
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userid);
		
		return this.sqlSessionTemplate.selectList("mailCountSearch.getMailAddCount", param);
	}
}