package jp.co.fourseeds.fsnet.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.fourseeds.fsnet.beans.MailBean;
import jp.co.fourseeds.fsnet.beans.ReadHistoryFormBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.ReadHistoryDao;

import jp.co.common.frame.service.BaseBusinessService;
import jp.co.common.frame.util.FreemarkerUtil;
import jp.co.common.frame.util.MailUtil;
import jp.co.common.frame.util.prop.FsPropertyUtil;

/**
 *  閲覧履歴の確認機能サービス実装クラス
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2015/12/18		    NTS        	       作成
 *
 **/
@Component
public class ReadHistoryService extends BaseBusinessService{

	@Autowired
	private ReadHistoryDao readHistoryDao;
	
	/**
	 * 配信者確認情報を取得
	 * @param param
	 * @return List
	 */
	public List<ReadHistoryFormBean> getNecessityReadPageList(ReadHistoryFormBean readHistoryFormBean, String strOrderBy) {
		return readHistoryDao.getNecessityReadPageList(readHistoryFormBean, strOrderBy, getLoginUserBean());
	}
	
	/**
	 * 閲覧管理対象者照会情報を取得
	 * @param param
	 * @return List
	 */
	public Map<String, Object> getNecessityReadUserListByPage(ReadHistoryFormBean readHistoryFormBean, String strOrderBy) {
		
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, String> repeatUser = new HashMap<String, String>();
		List<ReadHistoryFormBean> resultList = new ArrayList<ReadHistoryFormBean>();
		
		// 閲覧管理対象者照会情報リスト
		List<ReadHistoryFormBean> resultMailList = readHistoryDao.getNecessityReadUserListByPage(readHistoryFormBean, strOrderBy);
		
		// 未読件数
		int unReadCount = 0;
		
		for(int i = 0;i < resultMailList.size(); i++) {
			ReadHistoryFormBean resultBean = resultMailList.get(i);
			
			if (!repeatUser.containsKey(resultBean.getUserId())) {
				// ユーザのメールアドレス存在、かつ未読の場合
				if (!StringUtil.isBlank(resultBean.getMailAddress()) && "未読".equals(resultBean.getReadedDate())) {
					unReadCount = unReadCount + 1;
				}
				repeatUser.put(resultBean.getUserId(), "");
				resultList.add(resultBean);
			}
		}
		
		retMap.put("resultList", resultList);
		retMap.put("resultMailList", resultMailList);
		retMap.put("unReadCount", unReadCount);
		
		return retMap;
	}
	
	/**
	 * 未読ユーザーメール送信
	 * @param param
	 */
	@SuppressWarnings("rawtypes")
	public void sendMailToNotReaded(ReadHistoryFormBean readHistoryFormBean, List<ReadHistoryFormBean> notReadInfoList) throws Exception {
		
		// 発送者情報を取得
		String userId = getLoginUserBean().getUserId();
		String kanaMei = getLoginUserBean().getKanjiMei();
		String kanaSei = getLoginUserBean().getKanjiSei();
		String user = kanaSei + kanaMei + "("+ userId + ")";
		
		// 発送者メールを取得
		String fromUser = readHistoryDao.getUserMailAddress(userId);
		String mailFrom = null;
		if (StringUtil.isBlank(fromUser)) {
			mailFrom = FsPropertyUtil.getStringProperty("mail.user.address");
		} else {
			mailFrom = StringUtil.nullToBlank(fromUser);
		}
		
		// 未読ユーザーを取得
		for (int i = 0; i < notReadInfoList.size(); i++) {
			ReadHistoryFormBean notReadInfo = notReadInfoList.get(i);
			
			// 送信者督促可の場合、メールを発送
			if (!"不可".equals(notReadInfo.getMailSendFlag())){
				// メール設定
				MailBean mailInfo = new MailBean();
				mailInfo.setMailType("text/html");
				// 発送者設定
				mailInfo.setFrom(mailFrom);
				// 送信者設定
				String toList[] = new String[1];
				toList[0] = notReadInfo.getMailAddress();
				mailInfo.setTo(toList);
				// 転送先設定
				String mailCc = notReadInfo.getTrmailAddress();
				if (!StringUtil.isBlank(mailCc)) {
					String ccList[] = new String[1];
					ccList[0] = mailCc;
					mailInfo.setCc(ccList);
				}
				// メールタイトル設定
				mailInfo.setSubject(readHistoryFormBean.getMailTitle());
				
				// メールパラメータ
				Map<String, Object> rootMap = new HashMap<String, Object>();
				// ユーザー名
				rootMap.put("userName", notReadInfo.getUserName());
				// メール本文
				rootMap.put("mailContent",readHistoryFormBean.getMailContent().replaceAll("\n", "<br>"));
				// タイトル
				rootMap.put("title", readHistoryFormBean.getTitle());
				// 対象ページURL
				rootMap.put("linkUrl", readHistoryFormBean.getLinkUrl());
				
				// メール内容設定、メールテンプレートを指定する。
				mailInfo.setContent(new FreemarkerUtil().getTemplateStr(rootMap, "mail_page_unread.ftl"));
				
				// メール送信
				MailUtil mail = new MailUtil();
				mail.sendMail(mailInfo, user, FsPropertyUtil.getStringProperty("noread.context"));
			}
		}
	}
}