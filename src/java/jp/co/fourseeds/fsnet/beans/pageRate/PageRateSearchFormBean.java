package jp.co.fourseeds.fsnet.beans.pageRate;

import jp.co.common.frame.beans.BaseBean;
/**
 * 評価状況確認フォームBEAN
 * 
 * @author NTS
 * @version 1.0.0 : 2015.12.04 新規作成
 *
 **/
public class PageRateSearchFormBean extends BaseBean {
	
	private static final long serialVersionUID = -7715997010417294705L;

	/**初回検索フラグ*/
	private String isFirstSearch = null;
	
	/**ページ表示件数*/
	private String searchRowNum = null;
	
	/** 公開開始日付 */
	private String startDate = null;

	/** 公開終了日付 */
	private String endDate = null;

	/** タイトル名 */
	private String title = null;
	
	/** 現在有効なコンテンツのみ対象フラグ */
	private String hyoukaValidFlag = null;
	
	/** 検索結果件数 */
	private int resultCount;
	
	/** 画面状態フラグ「初期化画面：0」 */
	private String statusFlag = null;
	
	/**
	 * @return the isFirstSearch
	 */
	public String getIsFirstSearch() {
		return isFirstSearch;
	}

	/**
	 * @param isFirstSearch the isFirstSearch to set
	 */
	public void setIsFirstSearch(String isFirstSearch) {
		this.isFirstSearch = isFirstSearch;
	}

	/**
	 * @return the searchRowNum
	 */
	public String getSearchRowNum() {
		return searchRowNum;
	}

	/**
	 * @param searchRowNum the searchRowNum to set
	 */
	public void setSearchRowNum(String searchRowNum) {
		this.searchRowNum = searchRowNum;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the hyoukaValidFlag
	 */
	public String getHyoukaValidFlag() {
		return hyoukaValidFlag;
	}

	/**
	 * @param hyoukaValidFlag the hyoukaValidFlag to set
	 */
	public void setHyoukaValidFlag(String hyoukaValidFlag) {
		this.hyoukaValidFlag = hyoukaValidFlag;
	}

	/**
	 * @return the resultCount
	 */
	public int getResultCount() {
		return resultCount;
	}

	/**
	 * @param resultCount the resultCount to set
	 */
	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	/**
	 * @return the statusFlag
	 */
	public String getStatusFlag() {
		return statusFlag;
	}

	/**
	 * @param statusFlag the statusFlag to set
	 */
	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}
}
