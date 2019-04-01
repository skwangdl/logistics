package jp.co.fourseeds.fsnet.beans.pageRate;

import java.util.List;

import jp.co.common.frame.beans.BaseBean;
/**
 * 評価状況確認明細フォームBEAN
 * 
 * @author NTS
 * @version 1.0.0 : 2015.12.04 新規作成
 *
 **/
public class PageRateDetailFormBean extends BaseBean {
	
	private static final long serialVersionUID = -7715997010417294705L;

	/** ページ表示件数 */
	private String searchRowNum;
	
	/** ページID */
	private String pageId;
	
	/** 評価項目 */
	private String evaluationRadioFlag;
	
	/** AND条件 */
	private String pluralEvaluationFlag;
	
	/** And条件チェックボックス */
	private String evaluationFlag;
	
	/** SEQUENCE */
	private String evaluationSequence;
	
	/** コメント内キーワード */
	private String commentKeyWord;
	
	/** 評価者氏名表示フラグ */
	private String evaluatorDisplayFlag;
	
	/** タイトル */
	private String title;

	/** コンテンツ閲覧者数 */
	private String readerCount;

	/** コメント数 */
	private String commentCount;
	
	/** コメント情報リスト */
	private List<PageRateDetailBean> commentList;
	
	/** 評価状況詳細画面コンテンツ情報リスト */
	private List<PageRateSearchResultBean> detailSearchResultsList;
	
	/** 評価リスト */
	private List<PageRateDetailBean> evaluationContentsList;
	
	/** 評価詳細統計リスト(評価詳細CSV出力用) */
	private List<PageRateDetailBean> evaluationCountList;
	
	/** コメントCSV １:出力可０：出力不可 */
	private String commentCsvFlag;
	
	/** 評価CSV １:出力可０：出力不可 */
	private String evaluationCsvFlag;
	
	/**
	 * @return the commonCsvFlag
	 */
	public String getCommentCsvFlag() {
		return commentCsvFlag;
	}

	/**
	 * @param commonCsvFlag the commonCsvFlag to set
	 */
	public void setCommentCsvFlag(String commentCsvFlag) {
		this.commentCsvFlag = commentCsvFlag;
	}

	/**
	 * @return the evaluationCsvFlag
	 */
	public String getEvaluationCsvFlag() {
		return evaluationCsvFlag;
	}

	/**
	 * @param evaluationCsvFlag the evaluationCsvFlag to set
	 */
	public void setEvaluationCsvFlag(String evaluationCsvFlag) {
		this.evaluationCsvFlag = evaluationCsvFlag;
	}

	/**
	 * @return the readerCount
	 */
	public String getReaderCount() {
		return readerCount;
	}

	/**
	 * @param readerCount the readerCount to set
	 */
	public void setReaderCount(String readerCount) {
		this.readerCount = readerCount;
	}

	/**
	 * @return the commentCount
	 */
	public String getCommentCount() {
		return commentCount;
	}

	/**
	 * @param commentCount the commentCount to set
	 */
	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}

	/**
	 * @return the evaluatorDisplayFlag
	 */
	public String getEvaluatorDisplayFlag() {
		return evaluatorDisplayFlag;
	}

	/**
	 * @param evaluatorDisplayFlag the evaluatorDisplayFlag to set
	 */
	public void setEvaluatorDisplayFlag(String evaluatorDisplayFlag) {
		this.evaluatorDisplayFlag = evaluatorDisplayFlag;
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
	 * @return the evaluationContentsList
	 */
	public List<PageRateDetailBean> getEvaluationContentsList() {
		return evaluationContentsList;
	}

	/**
	 * @param evaluationContentsList the evaluationContentsList to set
	 */
	public void setEvaluationContentsList(List<PageRateDetailBean> evaluationContentsList) {
		this.evaluationContentsList = evaluationContentsList;
	}

	/**
	 * @return the evaluationCountList
	 */
	public List<PageRateDetailBean> getEvaluationCountList() {
		return evaluationCountList;
	}

	/**
	 * @param evaluationCountList the evaluationCountList to set
	 */
	public void setEvaluationCountList(List<PageRateDetailBean> evaluationCountList) {
		this.evaluationCountList = evaluationCountList;
	}

	/**
	 * @return the commentList
	 */
	public List<PageRateDetailBean> getCommentList() {
		return commentList;
	}

	/**
	 * @return the detailSearchResultsList
	 */
	public List<PageRateSearchResultBean> getDetailSearchResultsList() {
		return detailSearchResultsList;
	}

	/**
	 * @param detailSearchResultsList the detailSearchResultsList to set
	 */
	public void setDetailSearchResultsList(List<PageRateSearchResultBean> detailSearchResultsList) {
		this.detailSearchResultsList = detailSearchResultsList;
	}

	/**
	 * @param commentList the commentList to set
	 */
	public void setCommentList(List<PageRateDetailBean> commentList) {
		this.commentList = commentList;
	}

	/**
	 * @return the evaluationFlag
	 */
	public String getEvaluationFlag() {
		return evaluationFlag;
	}

	/**
	 * @param evaluationFlag the evaluationFlag to set
	 */
	public void setEvaluationFlag(String evaluationFlag) {
		this.evaluationFlag = evaluationFlag;
	}

	/**
	 * @return the evaluationSequence
	 */
	public String getEvaluationSequence() {
		return evaluationSequence;
	}

	/**
	 * @param evaluationSequence the evaluationSequence to set
	 */
	public void setEvaluationSequence(String evaluationSequence) {
		this.evaluationSequence = evaluationSequence;
	}

	/**
	 * @return the searchRowNum
	 */
	public String getSearchRowNum() {
		return searchRowNum;
	}

	/**
	 * @return the evaluationRadioFlag
	 */
	public String getEvaluationRadioFlag() {
		return evaluationRadioFlag;
	}

	/**
	 * @param evaluationRadioFlag the evaluationRadioFlag to set
	 */
	public void setEvaluationRadioFlag(String evaluationRadioFlag) {
		this.evaluationRadioFlag = evaluationRadioFlag;
	}

	/**
	 * @return the pluralEvaluationFlag
	 */
	public String getPluralEvaluationFlag() {
		return pluralEvaluationFlag;
	}

	/**
	 * @param pluralEvaluationFlag the pluralEvaluationFlag to set
	 */
	public void setPluralEvaluationFlag(String pluralEvaluationFlag) {
		this.pluralEvaluationFlag = pluralEvaluationFlag;
	}

	/**
	 * @param searchRowNum the searchRowNum to set
	 */
	public void setSearchRowNum(String searchRowNum) {
		this.searchRowNum = searchRowNum;
	}

	/**
	 * @return the pageId
	 */
	public String getPageId() {
		return pageId;
	}

	/**
	 * @param pageId the pageId to set
	 */
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	/**
	 * @return the commentKeyWord
	 */
	public String getCommentKeyWord() {
		return commentKeyWord;
	}

	/**
	 * @param commentKeyWord the commentKeyWord to set
	 */
	public void setCommentKeyWord(String commentKeyWord) {
		this.commentKeyWord = commentKeyWord;
	}
	
}
