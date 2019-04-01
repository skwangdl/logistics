package jp.co.fourseeds.fsnet.beans.accessLog;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
/**
 *  ページ名称の辞書クラス
 * @Scope 単例 
 * @author NTS
 * @version 1.0 
 */
public class PageNameDicBean {
	// ページ辞書マップ
	private Map<String, String>pageDic = new HashMap<String, String>();
	
	// ページ名称取得 
	public String getPageName(String pageId) {
		return pageDic.get(pageId);
	}
	// ページ名称格納
	public void setPageName(String pageId, String pageName) {
		pageDic.put(pageId, pageName);
	}
	// 格納したとか判断
	public boolean hasPage (String pageId) {
		return pageDic.containsKey(pageId);
	}
}
