package jp.co.fourseeds.fsnet.beans.accessLog;

import org.springframework.stereotype.Component;

import jp.co.common.frame.beans.BaseBean;


/**
 * アクセスのユーザーのログ情報
 * @author NTS
 *
 */
@Component
public class AccessLogBean extends BaseBean {

	private static final long serialVersionUID = -5183121655806120533L;
	
	//セッションID
	private String sessionId;

	//年月日
	private String yymmdd;

	//曜日
	private String dayofweek;

	//開始時間
	private String startTime;

	//終了時間
	private String endTime;

	//処理時間
	private String time;

	//ユーザーID
	private String userId;

	//ユーザー氏名
	private String userNm;

	//ページID
	private String pageId;

	//予約コンテンツ
	private String isReserve;
	
	//ページ名
	private String pageNm;

	//トリガー
	private String triggerNm;

	//リンクタイプ
	private String linkType;

	//リンクタイプ名
	private String linkTypeNm;

	//処理結果
	private String result;

	//アクセス詳細
	private String accessDetail;
	
	//通信キャリア
	private String telecomCarrier;
	//ブラウザー情報
	private String userAgent;
	//グローバルIPアドレス
	private String remoteAddr;
	//ホスト名
	private String remoteHost;
	//画面遷移元区分
	private String originType;
	//統括CD
	private String unificationId;
	//統括名
	private String unificationNm;
	//事業CD
	private String businessId;
	//事業部名
	private String businessNm;
	//営業部CD
	private String salesId;
	//営業部CD
	private String salesNm;
	//ログアウト区分
	private String logoutFlg;
	
	//組織区分 
	private String originalFlag;
	
	//従業員区分 
	private String userFlag;
		
	//所属 
	private String belong;
		
	//所属名称 
	private String belongName;
	

	// **********************Setter/Getter*****************************
	
	public String getSessionId() {
		return sessionId;
	}
	public String getOriginType() {
		return originType;
	}
	public void setOriginType(String originType) {
		this.originType = originType;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getYymmdd() {
		return yymmdd;
	}
	public void setYymmdd(String yymmdd) {
		this.yymmdd = yymmdd;
	}
	public String getDayofweek() {
		return dayofweek;
	}
	public void setDayofweek(String dayofweek) {
		this.dayofweek = dayofweek;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getPageNm() {
		return pageNm;
	}
	public void setPageNm(String pageNm) {
		this.pageNm = pageNm;
	}
	public String getTriggerNm() {
		return triggerNm;
	}
	public void setTriggerNm(String triggerNm) {
		this.triggerNm = triggerNm;
	}
	public String getLinkType() {
		return linkType;
	}
	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}
	public String getLinkTypeNm() {
		return linkTypeNm;
	}
	public void setLinkTypeNm(String linkTypeNm) {
		this.linkTypeNm = linkTypeNm;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getAccessDetail() {
		return accessDetail;
	}
	public void setAccessDetail(String accessDetail) {
		this.accessDetail = accessDetail;
	}
	public String getTelecomCarrier() {
		return telecomCarrier;
	}
	public void setTelecomCarrier(String telecomCarrier) {
		this.telecomCarrier = telecomCarrier;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getRemoteAddr() {
		return remoteAddr;
	}
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	public String getRemoteHost() {
		return remoteHost;
	}
	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}
	public String getUnificationId() {
		return unificationId;
	}
	public void setUnificationId(String unificationId) {
		this.unificationId = unificationId;
	}
	public String getUnificationNm() {
		return unificationNm;
	}
	public void setUnificationNm(String unificationNm) {
		this.unificationNm = unificationNm;
	}
	
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getBusinessNm() {
		return businessNm;
	}
	public void setBusinessNm(String businessNm) {
		this.businessNm = businessNm;
	}
	
	public String getSalesId() {
		return salesId;
	}
	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}
	public String getSalesNm() {
		return salesNm;
	}
	public void setSalesNm(String salesNm) {
		this.salesNm = salesNm;
	}
	public String getIsReserve() {
		return isReserve;
	}
	public void setIsReserve(String isReserve) {
		this.isReserve = isReserve;
	}
	public String getLogoutFlg() {
		return logoutFlg;
	}
	public void setLogoutFlg(String logoutFlg) {
		this.logoutFlg = logoutFlg;
	}
	public String getOriginalFlag() {
		return originalFlag;
	}
	public void setOriginalFlag(String originalFlag) {
		this.originalFlag = originalFlag;
	}
	public String getUserFlag() {
		return userFlag;
	}
	public void setUserFlag(String userFlag) {
		this.userFlag = userFlag;
	}
	public String getBelong() {
		return belong;
	}
	public void setBelong(String belong) {
		this.belong = belong;
	}
	public String getBelongName() {
		return belongName;
	}
	public void setBelongName(String belongName) {
		this.belongName = belongName;
	}
	
}
