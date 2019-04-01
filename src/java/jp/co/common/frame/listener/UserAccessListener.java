package jp.co.common.frame.listener;

import java.text.ParseException;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jp.co.common.frame.util.DateUtil;

import jp.co.fourseeds.fsnet.beans.accessLog.AccessLogBean;
import jp.co.fourseeds.fsnet.common.util.AccessLogUtil;

public class UserAccessListener implements HttpSessionBindingListener {

	/** Log4j */
	private final Log logger = LogFactory.getLog(this.getClass());
	
	private AccessLogUtil accessLogUtil;
	
	public AccessLogBean accessLogBean;
	
	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public UserAccessListener(AccessLogUtil accessLogUtil, AccessLogBean accessLogBean) {
		this.accessLogUtil = accessLogUtil;
		this.accessLogBean = accessLogBean;
	}
	
	@Override
	public void valueUnbound(HttpSessionBindingEvent be) {
		// ログアウトの場合、ログアウト区分設定、動作なし
		if (accessLogBean != null && "logout".equals(accessLogBean.getLogoutFlg())) {
			
		} else {
			// アクセス機能のログ情報 start
			String yymmdd = DateUtil.getNowDate();
			accessLogBean.setYymmdd(yymmdd);															//年月日
			accessLogBean.setDayofweek(DateUtil.getDayOfWeek(yymmdd, "yyyyMMdd"));						//曜日
			accessLogBean.setStartTime(DateUtil.getNowTime());											//開始時間
			accessLogBean.setPageId("");
			accessLogBean.setPageNm("セッションタイムアウト");
			accessLogBean.setAccessDetail("セッションタイムアウトが発生しました。");
			logger.error("セッションタイムアウトが発生しました。");
			try {
				accessLogUtil.writeAccessLog(accessLogBean);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
	
}
