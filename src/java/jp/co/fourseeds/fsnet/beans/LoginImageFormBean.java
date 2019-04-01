package jp.co.fourseeds.fsnet.beans;

import java.io.File;
import java.util.List;

import jp.co.common.frame.beans.BaseBean;

/**
 * @author NTS
 * @version 1.0.0 : 2015.12.02 新規作成
 */
public class LoginImageFormBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/**■■■■■■■■ここから、検索Bean■■■■■■■■■■■■■■■■*/
	/** 表示位置 */
	private String searchDisplayPosition;
	
	/** 開始日 */
	private String searchStartDate;
	
	/** 終了日*/
	private String searchEndDate;
	
	/** 選択フラグ */
	private List<LoginImageFormBean> loginImageList;
	
	/** 選択フラグ */
	private String selectFlag;
	
	/** 初期検索 */
	private String isFirstSearch;
	
	/** ページ毎に表示件数情報格納 */
	private int searchRowNum;
	
	/**■■■■■■■■ここから、検索結果Bean■■■■■■■■■■■■*/
	/** 最終更新者 */
	private String lastUser;
	
	/** 最終更新日 */
	private String lastDate;
	
	/** ID */
	private String loginImageId;
	
	/** IDリスト */
	private String loginImageIdList;
	
	/** タイトル */
	private String title;
	
	/** 表示位置コード */
	private String displayPositionCd;
	
	/** 表示位置名 */
	private String displayPositionName;
	
	/** 掲載開始日 */
	private String startDate;
	
	/** 掲載終了日 */
	private String endDate;
	
	/** 画像ファイル */
	private File img;
	
	/** 画像ファイル名 */
	private String imgFileName;
	
	/** 旧画像ファイル名 */
	private String oldImgFileName;
	
	/** 画像説明 */
	private String imgComment;
	
	/** 画像リンク */
	private String imgLinkUrl;
	
	/** 上部文字 */
	private String topComment;
	
	/** 上部文字リンク */
	private String topLinkUrl;
	
	/** 下部文字１ */
	private String bottomComment1;
	
	/** 下部文字２ */
	private String bottomComment2;
	
	/** 画像１ */
	private File img1;
	
	/** 画像１ファイル名 */
	private String img1FileName;
	
	/** 旧画像１ファイル名 */
	private String oldImg1FileName;
	
	/** 画像１削除フラグ */
	private String img1DeleteFlag;
	
	/** 画像２ */
	private File img2;
	
	/** 画像２ファイル名 */
	private String img2FileName;
	
	/** 旧画像２ファイル名 */
	private String oldImg2FileName;
	
	/** 画像２削除フラグ */
	private String img2DeleteFlag;
	
	/** 画像３ */
	private File img3;
	
	/** 画像３ファイル名 */
	private String img3FileName;
	
	/** 旧画像３ファイル名 */
	private String oldImg3FileName;
	
	/** 画像３削除フラグ */
	private String img3DeleteFlag;
	
	/** 画像４ */
	private File img4;
	
	/** 画像４ファイル名 */
	private String img4FileName;
	
	/** 旧画像４ファイル名 */
	private String oldImg4FileName;
	
	/** 画像４削除フラグ */
	private String img4DeleteFlag;
	
	/** 画像５ */
	private File img5;
	
	/** 画像５ファイル名 */
	private String img5FileName;
	
	/** 旧画像５ファイル名 */
	private String oldImg5FileName;
	
	/** 画像５削除フラグ */
	private String img5DeleteFlag;
	
	/** 画像６ */
	private File img6;
	
	/** 画像６ファイル名 */
	private String img6FileName;
	
	/** 旧画像６ファイル名 */
	private String oldImg6FileName;
	
	/** 画像６削除フラグ */
	private String img6DeleteFlag;
	
	/** 画像７ */
	private File img7;
	
	/** 画像７ファイル名 */
	private String img7FileName;
	
	/** 旧画像７ファイル名 */
	private String oldImg7FileName;
	
	/** 画像７削除フラグ */
	private String img7DeleteFlag;
	
	/** 画像８ */
	private File img8;
	
	/** 画像８ファイル名 */
	private String img8FileName;
	
	/** 旧画像８ファイル名 */
	private String oldImg8FileName;
	
	/** 画像８削除フラグ */
	private String img8DeleteFlag;
	
	/** 画像９ */
	private File img9;
	
	/** 画像９ファイル名 */
	private String img9FileName;
	
	/** 旧画像９ファイル名 */
	private String oldImg9FileName;
	
	/** 画像９削除フラグ */
	private String img9DeleteFlag;
	
	/** 画像１０ */
	private File img10;
	
	/** 画像１０ファイル名 */
	private String img10FileName;
	
	/** 旧画像１０ファイル名 */
	private String oldImg10FileName;
	
	/** 画像１０削除フラグ */
	private String img10DeleteFlag;

	public String getSearchDisplayPosition() {
		return searchDisplayPosition;
	}

	public void setSearchDisplayPosition(String searchDisplayPosition) {
		this.searchDisplayPosition = searchDisplayPosition;
	}

	public String getSearchStartDate() {
		return searchStartDate;
	}

	public void setSearchStartDate(String searchStartDate) {
		this.searchStartDate = searchStartDate;
	}

	public String getSearchEndDate() {
		return searchEndDate;
	}

	public void setSearchEndDate(String searchEndDate) {
		this.searchEndDate = searchEndDate;
	}

	public List<LoginImageFormBean> getLoginImageList() {
		return loginImageList;
	}

	public void setLoginImageList(List<LoginImageFormBean> loginImageList) {
		this.loginImageList = loginImageList;
	}

	public String getSelectFlag() {
		return selectFlag;
	}

	public void setSelectFlag(String selectFlag) {
		this.selectFlag = selectFlag;
	}

	public String getIsFirstSearch() {
		return isFirstSearch;
	}

	public void setIsFirstSearch(String isFirstSearch) {
		this.isFirstSearch = isFirstSearch;
	}

	public int getSearchRowNum() {
		return searchRowNum;
	}

	public void setSearchRowNum(int searchRowNum) {
		this.searchRowNum = searchRowNum;
	}

	public String getLastUser() {
		return lastUser;
	}

	public void setLastUser(String lastUser) {
		this.lastUser = lastUser;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	public String getLoginImageId() {
		return loginImageId;
	}

	public void setLoginImageId(String loginImageId) {
		this.loginImageId = loginImageId;
	}

	public String getLoginImageIdList() {
		return loginImageIdList;
	}

	public void setLoginImageIdList(String loginImageIdList) {
		this.loginImageIdList = loginImageIdList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDisplayPositionCd() {
		return displayPositionCd;
	}

	public void setDisplayPositionCd(String displayPositionCd) {
		this.displayPositionCd = displayPositionCd;
	}

	public String getDisplayPositionName() {
		return displayPositionName;
	}

	public void setDisplayPositionName(String displayPositionName) {
		this.displayPositionName = displayPositionName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public String getOldImgFileName() {
		return oldImgFileName;
	}

	public void setOldImgFileName(String oldImgFileName) {
		this.oldImgFileName = oldImgFileName;
	}

	public String getImgComment() {
		return imgComment;
	}

	public void setImgComment(String imgComment) {
		this.imgComment = imgComment;
	}

	public String getImgLinkUrl() {
		return imgLinkUrl;
	}

	public void setImgLinkUrl(String imgLinkUrl) {
		this.imgLinkUrl = imgLinkUrl;
	}

	public String getTopComment() {
		return topComment;
	}

	public void setTopComment(String topComment) {
		this.topComment = topComment;
	}

	public String getTopLinkUrl() {
		return topLinkUrl;
	}

	public void setTopLinkUrl(String topLinkUrl) {
		this.topLinkUrl = topLinkUrl;
	}

	public String getBottomComment1() {
		return bottomComment1;
	}

	public void setBottomComment1(String bottomComment1) {
		this.bottomComment1 = bottomComment1;
	}

	public String getBottomComment2() {
		return bottomComment2;
	}

	public void setBottomComment2(String bottomComment2) {
		this.bottomComment2 = bottomComment2;
	}

	public File getImg1() {
		return img1;
	}

	public void setImg1(File img1) {
		this.img1 = img1;
	}

	public String getImg1FileName() {
		return img1FileName;
	}

	public void setImg1FileName(String img1FileName) {
		this.img1FileName = img1FileName;
	}

	public String getOldImg1FileName() {
		return oldImg1FileName;
	}

	public void setOldImg1FileName(String oldImg1FileName) {
		this.oldImg1FileName = oldImg1FileName;
	}

	public String getImg1DeleteFlag() {
		return img1DeleteFlag;
	}

	public void setImg1DeleteFlag(String img1DeleteFlag) {
		this.img1DeleteFlag = img1DeleteFlag;
	}

	public File getImg2() {
		return img2;
	}

	public void setImg2(File img2) {
		this.img2 = img2;
	}

	public String getImg2FileName() {
		return img2FileName;
	}

	public void setImg2FileName(String img2FileName) {
		this.img2FileName = img2FileName;
	}

	public String getOldImg2FileName() {
		return oldImg2FileName;
	}

	public void setOldImg2FileName(String oldImg2FileName) {
		this.oldImg2FileName = oldImg2FileName;
	}

	public String getImg2DeleteFlag() {
		return img2DeleteFlag;
	}

	public void setImg2DeleteFlag(String img2DeleteFlag) {
		this.img2DeleteFlag = img2DeleteFlag;
	}

	public File getImg3() {
		return img3;
	}

	public void setImg3(File img3) {
		this.img3 = img3;
	}

	public String getImg3FileName() {
		return img3FileName;
	}

	public void setImg3FileName(String img3FileName) {
		this.img3FileName = img3FileName;
	}

	public String getOldImg3FileName() {
		return oldImg3FileName;
	}

	public void setOldImg3FileName(String oldImg3FileName) {
		this.oldImg3FileName = oldImg3FileName;
	}

	public String getImg3DeleteFlag() {
		return img3DeleteFlag;
	}

	public void setImg3DeleteFlag(String img3DeleteFlag) {
		this.img3DeleteFlag = img3DeleteFlag;
	}

	public File getImg4() {
		return img4;
	}

	public void setImg4(File img4) {
		this.img4 = img4;
	}

	public String getImg4FileName() {
		return img4FileName;
	}

	public void setImg4FileName(String img4FileName) {
		this.img4FileName = img4FileName;
	}

	public String getOldImg4FileName() {
		return oldImg4FileName;
	}

	public void setOldImg4FileName(String oldImg4FileName) {
		this.oldImg4FileName = oldImg4FileName;
	}

	public String getImg4DeleteFlag() {
		return img4DeleteFlag;
	}

	public void setImg4DeleteFlag(String img4DeleteFlag) {
		this.img4DeleteFlag = img4DeleteFlag;
	}

	public File getImg5() {
		return img5;
	}

	public void setImg5(File img5) {
		this.img5 = img5;
	}

	public String getImg5FileName() {
		return img5FileName;
	}

	public void setImg5FileName(String img5FileName) {
		this.img5FileName = img5FileName;
	}

	public String getOldImg5FileName() {
		return oldImg5FileName;
	}

	public void setOldImg5FileName(String oldImg5FileName) {
		this.oldImg5FileName = oldImg5FileName;
	}

	public String getImg5DeleteFlag() {
		return img5DeleteFlag;
	}

	public void setImg5DeleteFlag(String img5DeleteFlag) {
		this.img5DeleteFlag = img5DeleteFlag;
	}

	public File getImg6() {
		return img6;
	}

	public void setImg6(File img6) {
		this.img6 = img6;
	}

	public String getImg6FileName() {
		return img6FileName;
	}

	public void setImg6FileName(String img6FileName) {
		this.img6FileName = img6FileName;
	}

	public String getOldImg6FileName() {
		return oldImg6FileName;
	}

	public void setOldImg6FileName(String oldImg6FileName) {
		this.oldImg6FileName = oldImg6FileName;
	}

	public String getImg6DeleteFlag() {
		return img6DeleteFlag;
	}

	public void setImg6DeleteFlag(String img6DeleteFlag) {
		this.img6DeleteFlag = img6DeleteFlag;
	}

	public File getImg7() {
		return img7;
	}

	public void setImg7(File img7) {
		this.img7 = img7;
	}

	public String getImg7FileName() {
		return img7FileName;
	}

	public void setImg7FileName(String img7FileName) {
		this.img7FileName = img7FileName;
	}

	public String getOldImg7FileName() {
		return oldImg7FileName;
	}

	public void setOldImg7FileName(String oldImg7FileName) {
		this.oldImg7FileName = oldImg7FileName;
	}

	public String getImg7DeleteFlag() {
		return img7DeleteFlag;
	}

	public void setImg7DeleteFlag(String img7DeleteFlag) {
		this.img7DeleteFlag = img7DeleteFlag;
	}

	public File getImg8() {
		return img8;
	}

	public void setImg8(File img8) {
		this.img8 = img8;
	}

	public String getImg8FileName() {
		return img8FileName;
	}

	public void setImg8FileName(String img8FileName) {
		this.img8FileName = img8FileName;
	}

	public String getOldImg8FileName() {
		return oldImg8FileName;
	}

	public void setOldImg8FileName(String oldImg8FileName) {
		this.oldImg8FileName = oldImg8FileName;
	}

	public String getImg8DeleteFlag() {
		return img8DeleteFlag;
	}

	public void setImg8DeleteFlag(String img8DeleteFlag) {
		this.img8DeleteFlag = img8DeleteFlag;
	}

	public File getImg9() {
		return img9;
	}

	public void setImg9(File img9) {
		this.img9 = img9;
	}

	public String getImg9FileName() {
		return img9FileName;
	}

	public void setImg9FileName(String img9FileName) {
		this.img9FileName = img9FileName;
	}

	public String getOldImg9FileName() {
		return oldImg9FileName;
	}

	public void setOldImg9FileName(String oldImg9FileName) {
		this.oldImg9FileName = oldImg9FileName;
	}

	public String getImg9DeleteFlag() {
		return img9DeleteFlag;
	}

	public void setImg9DeleteFlag(String img9DeleteFlag) {
		this.img9DeleteFlag = img9DeleteFlag;
	}

	public File getImg10() {
		return img10;
	}

	public void setImg10(File img10) {
		this.img10 = img10;
	}

	public String getImg10FileName() {
		return img10FileName;
	}

	public void setImg10FileName(String img10FileName) {
		this.img10FileName = img10FileName;
	}

	public String getOldImg10FileName() {
		return oldImg10FileName;
	}

	public void setOldImg10FileName(String oldImg10FileName) {
		this.oldImg10FileName = oldImg10FileName;
	}

	public String getImg10DeleteFlag() {
		return img10DeleteFlag;
	}

	public void setImg10DeleteFlag(String img10DeleteFlag) {
		this.img10DeleteFlag = img10DeleteFlag;
	}

}
