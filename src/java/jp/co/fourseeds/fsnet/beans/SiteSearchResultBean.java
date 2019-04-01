package jp.co.fourseeds.fsnet.beans;

import jp.co.fourseeds.fsnet.beans.page.PageBean;

/**
 * サイト内検索結果を格納
 * 既存UserPageListInfo　⇒　コピー新規作成
 * 既存HaitiInfoBean　⇒　コピー新規作成
 * 
 * @author 
 * @version 1.0
 * @function The form bean for search page.
 */
public class SiteSearchResultBean extends PageBean {

	/** Serial version uid */
	private static final long serialVersionUID = 7611032090331762173L;

	/** 検索結果：DBとFessのマッピングキー */
	private String matchKey = null;
	
	/** 検索結果：添付ファイル名前 */
	private String attachmentName = null;
	
	/** 検索結果：添付ファイルのリンク先 */
	private String attachmentFileUrl = null;

	/** 検索結果：部署名 */
	private String departmentName = null;
	
	/** 検索結果：配置先 */
	private String haitisaki = null;
	
	/** 配置先計算用変数　START */
	private String existFlag = null;					//サイト検索結果中、「コンテンツ掲載場所」コンテンツを含む、existFlag = 1
	private String strPageKey = null;
	private int countPass = 0;
	private int count = 0;
	private String linkName = null;
	private String linkTitle = null;
	private boolean isJituFlag = false;
	/** 配置先計算用変数　END */

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getMatchKey() {
		return matchKey;
	}

	public void setMatchKey(String matchKey) {
		this.matchKey = matchKey;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getAttachmentFileUrl() {
		return attachmentFileUrl;
	}

	public void setAttachmentFileUrl(String attachmentFileUrl) {
		this.attachmentFileUrl = attachmentFileUrl;
	}

	public String getHaitisaki() {
		return haitisaki;
	}

	public void setHaitisaki(String haitisaki) {
		this.haitisaki = haitisaki;
	}

	public String getExistFlag() {
		return existFlag;
	}

	public void setExistFlag(String existFlag) {
		this.existFlag = existFlag;
	}

	public String getStrPageKey() {
		return strPageKey;
	}

	public void setStrPageKey(String strPageKey) {
		this.strPageKey = strPageKey;
	}

	public int getCountPass() {
		return countPass;
	}

	public void setCountPass(int countPass) {
		this.countPass = countPass;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getLinkTitle() {
		return linkTitle;
	}

	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}

	public boolean isJituFlag() {
		return isJituFlag;
	}

	public void setJituFlag(boolean isJituFlag) {
		this.isJituFlag = isJituFlag;
	}
}
