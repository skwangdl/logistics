package jp.co.fourseeds.fsnet.beans;

import java.util.List;

import jp.co.common.frame.beans.BaseBean;

/**
 * サイト内検索画面項目（検索条件など）を格納
 * 
 * @author 
 * @version 1.0
 * @function The form bean for search page.
 */
public class SiteSearchFormBean extends BaseBean {

	private static final long serialVersionUID = 7611032090331762173L;

	/** 検索条件：検索キー */
	private String keyWord = null;

	/** 検索条件：ページ毎の表示件数 */
	private String perPageNo = "10";		//デフォルト値は「10」

	/** 検索条件：ソート順 */
	private String sort = "";

	/** 検索結果リスト */
	private List<SiteSearchResultBean> searchPageList = null;
	
	/** 検索結果：合計件数 */
	private int total;

	/** 検索結果：検索掛かる時間 */
	private String usedTime = null;
	
	/** 検索結果：カレントページ数 */
	private String searchPageNo = "1";		//初期値は「1」
	
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getPerPageNo() {
		return perPageNo;
	}
	public void setPerPageNo(String perPageNo) {
		this.perPageNo = perPageNo;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getUsedTime() {
		return usedTime;
	}
	public void setUsedTime(String usedTime) {
		this.usedTime = usedTime;
	}
	public List<SiteSearchResultBean> getSearchPageList() {
		return searchPageList;
	}
	public void setSearchPageList(List<SiteSearchResultBean> searchPageList) {
		this.searchPageList = searchPageList;
	}
	public String getSearchPageNo() {
		return searchPageNo;
	}
	public void setSearchPageNo(String searchPageNo) {
		this.searchPageNo = searchPageNo;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
