package jp.co.fourseeds.fsnet.service;

import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.fourseeds.fsnet.beans.ContentSearchResultBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.SendMailDao;

import jp.co.common.frame.service.BaseBusinessService;

/**
 * 社員検索機能サービス実装クラス
 * 
 * File Name: UserService.java 
 * Created: 2015/09/22
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2015/09/22		    NTS        	       作成
 *
 **/
@Component
public class SendMailService extends BaseBusinessService{

	@Autowired
	private SendMailDao sendMailDao;

	/**
	 * 社員検索結果を取得
	 * @param param　検索条件
	 * @param from　検索開始
	 * @param length　検索レコード数
	 * @return　検索結果
	 */
	public String getMailAddCount(String userId) {
		List fromList = null;

		ContentSearchResultBean notReadedInfo = new ContentSearchResultBean();
		notReadedInfo.setUserId(userId);
		fromList = sendMailDao.getMailAddCount(notReadedInfo);
		String mailFrom = null;
		// TODO 
		if ( 1==1 || fromList.size() == 0) {
			ResourceBundle bundle = ResourceBundle.getBundle("fsnet");
			mailFrom = StringUtil.nullToBlank(bundle.getString("mail.user.address"));
		} else {
			mailFrom = StringUtil.nullToBlank((String) fromList.get(0));
		}
		return mailFrom;
	}
}