package jp.co.fourseeds.fsnet.common.util;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jp.co.common.frame.dao.BaseDao;
import jp.co.common.frame.util.DateUtil;
import jp.co.common.frame.util.prop.FsPropertyUtil;

import jp.co.fourseeds.fsnet.beans.accessLog.AccessLogBean;
import jp.co.fourseeds.fsnet.beans.accessLog.PageNameDicBean;

@Component
@Scope("prototype")
public class AccessLogUtil extends BaseDao {
	
	/** Log4j action logger instance */
	private final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private PageNameDicBean pageNameDicBean;
	
	/**
	 * アクセスログ出力
	 * 
	 * @param AccessLogBean アクセスログ情報
	 * 
	 * ■■■コンテンツ表示の場合、以下のパラメータ設定
	 * 1)ページID		pageId
	 * 2)トリガー		triggerNm		※イベント名称で設定、例）検索、確認
	 * 3)アクセス詳細	accessDetail
	 * 4)遷移元			originType
	 * 5)処理結果		result
	 * 6)予約フラグ		isReserve
	 * 
	 * ■■■コンテンツ表示以外の場合、以下のパラメータ設定
	 * 1)ページID		pageId
	 * 1-1)ページ名		pageNm			※pageId無しの場合、ページ名設定
	 * 2)トリガー		triggerNm		※イベント名称で設定、例）検索、確認
	 * 3)アクセス詳細	accessDetail
	 * 
	 * @throws ParseException 
	 */
	public void writeAccessLog(AccessLogBean bean) throws ParseException{
		
		String endTimeStr = DateUtil.getNowTime();
		bean.setEndTime(endTimeStr);																	//終了時間
		bean.setTime(DateUtil.getTimeRange(bean.getStartTime(), endTimeStr, DateUtil.TIME_FORMAT));		//処理時間
		
		//ページIDにより、ページ名称設定
		String pageId = bean.getPageId();
		if(StringUtil.isNotBlank(pageId)){
			// ホームの場合、HOMEで設定
			if ("0".equals(pageId)) {
				bean.setPageNm("HOME");
			} else if (StringUtil.isEmpty(bean.getPageNm())) {
				String pageName = "";
				
				if("treeTemplate".equals(bean.getOriginType())) {
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("PARA_TEMPLATE_PAGE_ID", pageId);
					pageName = (String) this.sqlSessionTemplate.selectOne("userAccessLog.getAccessLogTemplatePageName", param);
				} else {
					// 辞書に格納したの場合、辞書に取得
					if(pageNameDicBean.hasPage(pageId)){
						pageName = pageNameDicBean.getPageName(pageId);
					} else {
						// 辞書に格納しなかたの場合、データベースに取得
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("PARA_PAGE_ID", pageId);
						param.put("PARA_IS_RESERVE", bean.getIsReserve());
						pageName = (String) this.sqlSessionTemplate.selectOne("userAccessLog.getAccessLogPageName", param);
						// 機能コンテンツの場合、辞書に格納
						if (pageId.matches("^\\p{Alpha}000000000001$")) {
							pageNameDicBean.setPageName(pageId, pageName);
						}
					}
				}
				bean.setPageNm(pageName);
			}
		}
		
		// 遷移元区分(originType)により、リンクタイプへ変換
		String originType = bean.getOriginType();															//遷移元区分
		if(StringUtil.isNotBlank(originType)
				&& !"null".equals(originType)){
			String linkTypeProperty = FsPropertyUtil.getInstance().getPropertyString("access.log.linktype." + originType);
			if(StringUtil.isNotBlank(linkTypeProperty)){
				bean.setLinkType(linkTypeProperty.split(",")[0]);												//リンクタイプ
				bean.setLinkTypeNm(linkTypeProperty.split(",")[1]);												//リンクタイプ名
			} else {
				bean.setLinkType("99");																			//リンクタイプ
				bean.setLinkTypeNm("その他");																	//リンクタイプ名
			}
			bean.setTriggerNm("リンク");
			if (StringUtil.isBlank(bean.getResult())){
				bean.setResult("OK");
			}
		} else {
			bean.setLinkType("");
			bean.setLinkTypeNm("");
			bean.setResult("OK");
		}
		
		this.sqlSessionTemplate.insert("userAccessLog.INSERT_ACCESS_LOG", bean);
		
		logger.info("[USER_ID:" + bean.getUserId() + "] - 【機能】" + bean.getAccessDetail());
	}
}
