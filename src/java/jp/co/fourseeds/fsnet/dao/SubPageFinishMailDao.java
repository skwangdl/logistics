package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.common.util.CommonUtil;

/**
 * コンテンツ作成完了通知メール送信機能Daoクラス
 * 
 * Created: 2016/01/12
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2016/01/12		    NTS        	       作成
 *
 **/
@Repository
public class SubPageFinishMailDao extends BaseDao {
	
	/**
	 * トップグループ情報を取得
	 * @param topGroupName
	 *        グループ名称
	 * @return
	 *        グループリスト
	 */
	public List<String> getUserMailAddress(Map<String, Object> param)  {
		return this.sqlSessionTemplate.selectList("subPageFinishMail.getUserMailAddress", param);
	}
	
	/**
	 * 社員転送先メールアドレスを取得
	 * @param userIdList　社員IDリスト
	 * @return　検索結果
	 */
	public String[] getTrMailAddress(List<String> userIdList ) {
		
		// パラメータ
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID_LIST", CommonUtil.getGroupSql(userIdList));
		
		List<String> mailAddressList = this.sqlSessionTemplate.selectList("subPageFinishMail.getTrMailAddress", param);
		
		return mailAddressList.toArray(new String[mailAddressList.size()]);
	}
}