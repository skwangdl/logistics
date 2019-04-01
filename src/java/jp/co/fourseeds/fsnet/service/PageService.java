package jp.co.fourseeds.fsnet.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.MailBean;
import jp.co.fourseeds.fsnet.beans.page.AuthGroupBean;
import jp.co.fourseeds.fsnet.beans.page.AuthUserBean;
import jp.co.fourseeds.fsnet.beans.page.ChildPageFormBean;
import jp.co.fourseeds.fsnet.beans.page.ChildPageInheritSetBean;
import jp.co.fourseeds.fsnet.beans.page.PageAttachmentBean;
import jp.co.fourseeds.fsnet.beans.page.ProxyUserBean;
import jp.co.fourseeds.fsnet.beans.page.PageBean;
import jp.co.fourseeds.fsnet.beans.page.PageCommentAdminBean;
import jp.co.fourseeds.fsnet.beans.page.PageCommentMailFormBean;
import jp.co.fourseeds.fsnet.beans.page.PageCommentRateBean;
import jp.co.fourseeds.fsnet.beans.page.PageDeleteBean;
import jp.co.fourseeds.fsnet.beans.page.PageFormBean;
import jp.co.fourseeds.fsnet.beans.page.PageLinkBean;
import jp.co.fourseeds.fsnet.beans.page.PageRateItemBean;
import jp.co.fourseeds.fsnet.beans.page.PageUserRateBean;
import jp.co.fourseeds.fsnet.common.ConstantContainer;
import jp.co.fourseeds.fsnet.common.util.CommonUtil;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.PageDao;
import jp.co.fourseeds.fsnet.dao.PageDownloadDao;
import jp.co.fourseeds.fsnet.dao.TemplatePageDao;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.beans.LabelValueBean;
import jp.co.common.frame.dao.CommonDao;
import jp.co.common.frame.exception.BusinessServiceException;
import jp.co.common.frame.exception.CommonErrorPageException;
import jp.co.common.frame.exception.LocalRuntimeException;
import jp.co.common.frame.exception.OptimisticLockException;
import jp.co.common.frame.service.BaseBusinessService;
import jp.co.common.frame.util.FileUtil;
import jp.co.common.frame.util.FreemarkerUtil;
import jp.co.common.frame.util.MailUtil;
import jp.co.common.frame.util.NumberUtil;
import jp.co.common.frame.util.WrappedBeanUtil;
import jp.co.common.frame.util.prop.FsPropertyUtil;

/**
 * コンテンツ情報機能サービス実装クラス 
 * 
 * Created: 2015/09/22
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *　VersionWhen	  Who	  Why
 *-----------------------------------------------------------
 *　1.0		2015/09/22		 NTS	 		作成
 *
 **/
@SuppressWarnings(value={"rawtypes","unchecked"})
@Component
public class PageService extends BaseBusinessService{
	
	@Autowired
	private PageDao pageDao;
	@Autowired
	private PageDownloadDao pageDownloadDao;
	@Autowired
	private CommonDao commonDao;
	@Autowired
	private TemplatePageDao templatePageDao;
	@Autowired
	private TemplatePageService templatePageService;
	
	/**
	 * コンテンツ登録の場合、画面の初期化
	 * @param fromBean フォーム　
	 * @throws Exception 
	 */
	public void init(PageFormBean formBean) throws Exception {
		// 対象者、発信部署ドロップダウンリストを作成
		getDropdownList(formBean);
		// コンテンツ評価フラグ←「0:しない」
		formBean.setEvaluationFlag("0");
		// ページ表示フラグ←「ADD」
		formBean.setPageViewFlag("ADD");
		// 評価者氏名表示フラグ
		formBean.setEvaluatorDisplayFlag("1");
		// 評価者コメント編集可フラグ
		formBean.setCommentEditFlag("1");
		// 複数項目評価可フラグ
		formBean.setPluralEvaluationFlag("0");
		// 配置先設定フラグ
		formBean.setPageLocationSetFlag("0");
		// 対象者選択区分を設定
		setUserType(formBean);
		// 問題報告対応者リストを設定
		setPageCommentAdminList(formBean, getLoginUserBean().getUserId());
	}
	
	/**
	 * テンプレートコンテンツ作成初期化
	 * @param fromBean フォーム　
	 * @throws Exception 
	 */
	public void templateInit(PageFormBean formBean) throws Exception {
		// テンプレートページID
		String templatePageId = formBean.getTemplatePageId();
		// テンプレートテンプレートコンテンツ情報の取得
		PageFormBean pageFormBean = templatePageService.getTemplatePageForm(templatePageId);
		// テンプレートコンテンツ情報が存在してない場合、以降の処理を中止する。
		if (pageFormBean == null) {
			// エラー画面へ遷移
			optimisticLockCheck(templatePageId, "2");
		}
		
		// 対象者、発信部署ドロップダウンリストを作成
		getDropdownList(formBean);
		// 公開確認日付非更新フラグ(公開日付変更なし)表示フラグ
		formBean.setViewConfirmNoupdateFlag("0");
		// 公開基準日付非更新フラグ
		formBean.setConfirmNoupdateFlag("0");
		// 「テンプレートから」区分
		formBean.setIsTemplateFrom("1");
		
		// 対象者
		formBean.setUserDivision(pageFormBean.getUserDivision());
		// 発信部署
		formBean.setSourceDepartment(pageFormBean.getSourceDepartment());
		// 新着情報非表示フラグ
		formBean.setNewUndisplayFlag(pageFormBean.getNewUndisplayFlag());
		//「新規」表示維持
		formBean.setNewKeepFlag(pageFormBean.getNewKeepFlag());
		// 親ページID
		formBean.setPPageId(pageFormBean.getPPageId());
		// 配列順
		formBean.setOrderBy(pageFormBean.getOrderBy());
		// ページKEY
		formBean.setPageKey(pageFormBean.getPageKey());
		// タイトル
		formBean.setTitle(pageFormBean.getTitle());
		// コンテンツ
		formBean.setContent(contentReplace(pageFormBean.getContent()));
		// 公開開始日付
		formBean.setStartDate(pageFormBean.getStartDate());
		// 公開終了日付
		formBean.setEndDate(pageFormBean.getEndDate());
		// 画面フラグ
		formBean.setHtmlFlag(pageFormBean.getHtmlFlag());
		// ファイルURL(ファイル名とアドレス)
		formBean.setHtmlFileUrl(pageFormBean.getHtmlFileUrl());
		// 非公開フラグ
		formBean.setConfirmFlag(pageFormBean.getConfirmFlag());
		// ユーザID
		formBean.setCreateBy(pageFormBean.getCreateBy());
		// ダウンロードパスワード
		formBean.setDownloadPassword(pageFormBean.getDownloadPassword());
		// ダウンロード不可フラグ
		formBean.setDenyDownloadFlag(pageFormBean.getDenyDownloadFlag());
		// 文書リンク表示不可フラグ
		formBean.setDenyLinkfileFlag(pageFormBean.getDenyLinkfileFlag());
		// コンテンツリンク情報(リンク添付)リスト
		formBean.setPageLinkList(pageFormBean.getPageLinkList());
		//コンテンツ添付ファイル情報（文書添付）リスト
		formBean.setPageAttachmentList(pageFormBean.getPageAttachmentList());
		// コンテンツ閲覧権限グループ情報(公開するグループ)リスト
		formBean.setAuthGroupList(pageFormBean.getAuthGroupList());
		// コンテンツ閲覧権限ユーザ情報(公開する個人)リスト
		formBean.setAuthUserList(pageFormBean.getAuthUserList());
		// コンテンツ更新代行者(承認者)リスト
		formBean.setProxyUserList(pageFormBean.getProxyUserList());
		// 公開維持編集フラグ
		formBean.setOnEditFlag("0");
		// 公開基準日
		formBean.setConfirmDate(pageFormBean.getConfirmDate());
		// 公開開始日基準フラグ
		formBean.setStartdateOpenFlag(pageFormBean.getStartdateOpenFlag());
		// コンテンツ評価フラグ
		formBean.setEvaluationFlag(pageFormBean.getEvaluationFlag());
		// 評価者氏名表示フラグ
		formBean.setEvaluatorDisplayFlag(pageFormBean.getEvaluatorDisplayFlag());
		// 評価者コメント編集可フラグ
		formBean.setCommentEditFlag(pageFormBean.getCommentEditFlag());
		// 複数評価有無フラグ
		formBean.setPluralEvaluationFlag(pageFormBean.getPluralEvaluationFlag());
		// 評価をクリアするフラグ
		formBean.setEvaluationClearFlag(pageFormBean.getEvaluationClearFlag());
		// コンテンツ評価アイテム情報リスト
		formBean.setPageRateItemBeanList(pageFormBean.getPageRateItemBeanList());
		// コンテンツコメント管理者情報選択(問題報告対応者)リスト
		formBean.setPageCommentAdminOptionList(pageFormBean.getPageCommentAdminOptionList());
		// コンテンツコメント管理者情報(問題報告対応者)リスト
		formBean.setPageCommentAdminList(pageFormBean.getPageCommentAdminList());

		// ページ表示フラグ←「ADD」
		formBean.setPageViewFlag("ADD");
		
		// 親ページ存在フラグ
		if (pageDao.getPage(formBean.getPPageId()) == null){
			formBean.setPpageExsitsFlg("0");
		} else {
			formBean.setPpageExsitsFlg("1");
		}		

		//コンテンツ作成者ID
		String currentUserId = getLoginUserBean().getUserId();
		formBean.setCreateBy(currentUserId);
		// コンテンツ作成者名前
		String strUserName = pageDao.getCreateUserName(currentUserId);
		formBean.setCreateUserName(strUserName);
		// ユーザ名称がスペースではない場合、「コンテンツコメント管理者情報選択リスト、コンテンツコメント管理者情報リスト」にログインユーザを格納
		if (!StringUtil.isEmpty(strUserName)) {
			
			// コンテンツコメント管理者情報選択(問題報告対応者)リスト
			List<PageCommentAdminBean> pageCommentAdminOptionList = formBean.getPageCommentAdminOptionList();
			Boolean flag = false;
			if (pageCommentAdminOptionList != null) {
				for (int i = 0; i < pageCommentAdminOptionList.size(); i ++) {
					PageCommentAdminBean bean = pageCommentAdminOptionList.get(i);
					String uid = bean.getUserId();
					if(uid.equals(currentUserId)) {
						flag = true;
						break;
					}
				}
			} else{
				pageCommentAdminOptionList = new ArrayList<PageCommentAdminBean>();
			}
			if(!flag) {
				PageCommentAdminBean bean = new PageCommentAdminBean();
				// ユーザID
				bean.setUserId(currentUserId);
				// ユーザ名称
				bean.setUserName(strUserName);
				pageCommentAdminOptionList.add(bean);
				// コンテンツコメント管理者情報選択リストに格納
				formBean.setPageCommentAdminOptionList(pageCommentAdminOptionList);
			}
		}		

		// 問題報告対応者リストを設定
		if ("0".equals(formBean.getEvaluationFlag())) {
			if (StringUtil.isNotEmpty(formBean.getCreateUserName())) {
				List<PageCommentAdminBean> pageCommentAdminList = new ArrayList<PageCommentAdminBean>();
				PageCommentAdminBean bean = new PageCommentAdminBean();
				bean.setUserId(formBean.getCreateBy());
				bean.setUserName(formBean.getCreateUserName());
				pageCommentAdminList.add(bean);
				// 問題報告対応者リスト
				formBean.setPageCommentAdminList(pageCommentAdminList);
			}
		}
		
		// 対象者選択区分を設定
		setUserType(formBean);

		// テンプレート相関チェック
		templateCheck(formBean);

	}	
	
	/**
	 * コンテンツ詳細情報を検索
	 * @param fromBean フォーム　
	 * @throws Exception 
	 */
	public void getPageDetails(PageFormBean formBean) throws Exception {
		// コンテンツ情報を取得
		PageFormBean dbPageInfo = null;
		// コンテンツ情報Bean
		PageBean pageBean = null;
		// コンテンツ予約情報Bean
		PageBean pageRsvBean = null;
		// コンテンツ情報取得済フラグ
		boolean bolPageSelectedFlag = false;
		// 非公開フラグ(ページ)
		String confirmFlagPage = "";
		//　ページID
		String pageId = formBean.getPageId();

		// 1(予約中)の場合
		if ("1".equals(formBean.getUpdateradio())){
			//　コンテンツ予約情報の取得(画面表示用)
			dbPageInfo = getPageRsvForm(pageId);
			// コンテンツ予約情報が存在した場合
			if (dbPageInfo != null) {
				// コンテンツ予約情報Beanにコピーする。
				pageRsvBean = new PageBean();
				WrappedBeanUtil.copyProperties(pageRsvBean, dbPageInfo);
			}
		} else {
			//　コンテンツ情報の取得(画面表示用)
			dbPageInfo = getPageForm(pageId);
			// コンテンツ情報が存在した場合、
			if (dbPageInfo != null){
				// コンテンツ情報Beanにコピーする。
				pageBean = new PageBean();
				WrappedBeanUtil.copyProperties(pageBean, dbPageInfo);
				// 非公開フラグ(ページ)
				confirmFlagPage = dbPageInfo.getConfirmFlag();				
			}
			// コンテンツ情報取得済
			bolPageSelectedFlag = true;
		}

		// コンテンツ情報が存在してない場合、以降の処理を中止する。
		if (dbPageInfo == null) {
			// エラー画面へ遷移
			optimisticLockCheck(pageId,formBean.getUpdateradio());
		}		
		
		// コンテンツ情報取得未済の場合、コンテンツ情報を再取得
		if (!bolPageSelectedFlag) {
			//　コンテンツ情報を取得
			pageBean = getPageDB(pageId);				
		}
		
		// コンテンツ情報が存在してない場合、以降の処理を中止する。
		if (pageBean == null) {
			// エラー画面へ遷移
			optimisticLockCheck(pageId, "0");
		}
		
		// child存在フラグ
		formBean.setChildExistFlag("0");
		// パラメータ（ページID）直下の子コンテンツ情報抽出
		List childList = getOpenChildPageList(pageId);
		// 子コンテンツ（公開中）があった場合
		if (childList != null && childList.size() > 0){
			formBean.setChildExistFlag("1");
		}
		
		// 「公開したまま編集する」の場合
		if ("1".equals(formBean.getOnEditFlag())){
			// 評価者氏名表示フラグ(ページ)
			formBean.setEvaluatorDisplayFlagPage(pageBean.getEvaluatorDisplayFlag());
			// 前回複数項目評価可フラグ(ページ)
			formBean.setPrevPluralEvaluationFlagPage(pageBean.getPrevPluralEvaluationFlag());
			// 非公開フラグ(ページ)
			confirmFlagPage = dbPageInfo.getConfirmFlag();				
		}

		// 公開確認日付非更新フラグ(公開日付変更なし)表示フラグを設定
		setViewConfirmNoupdateFlag(formBean, pageBean);

		// 「新規」表示維持表示フラグを設定
		formBean.setViewNewKeepFlag(pageDao.getViewNewKeepFlag(pageId));
		
		// DBからフォームに設定
		setFromDbToForm(formBean, dbPageInfo);
		
		// 親ページ存在フラグ
		if (pageDao.getPage(formBean.getPPageId()) == null){
			formBean.setPpageExsitsFlg("0");
		} else {
			formBean.setPpageExsitsFlg("1");
		}
		
		// 問題報告対応者リストを設定
		if ("0".equals(formBean.getEvaluationFlag()) && "0".equals(confirmFlagPage)) {
			if (StringUtil.isNotEmpty(formBean.getCreateUserName())) {
				List<PageCommentAdminBean> pageCommentAdminList = new ArrayList<PageCommentAdminBean>();
				PageCommentAdminBean bean = new PageCommentAdminBean();
				bean.setUserId(formBean.getCreateBy());
				bean.setUserName(formBean.getCreateUserName());
				pageCommentAdminList.add(bean);
				// 問題報告対応者リスト
				formBean.setPageCommentAdminList(pageCommentAdminList);
			}
		}

		// 対象者、発信部署ドロップダウンリストを作成
		getDropdownList(formBean);
		
		// 対象者選択区分を設定
		setUserType(formBean);
		
		// 更新日時（排他制御用）
		formBean.setUpdateDateStr(pageBean.getUpdateDateStr());
		
//		// 親ページID
//		String strHaiTiSaki = getPageLocation(formBean.getPPageId());
//		if (StringUtil.isEmpty(strHaiTiSaki)){
//			formBean.setPPageId("");
//		}

		// 配置先設定フラグ
		formBean.setPageLocationSetFlag("0");

		// ページ表示フラグ←「EDIT」
		formBean.setPageViewFlag("EDIT");

	}
	
	/**
	 * DBからフォームに設定
	 * @param dbPageInfo DBから取得したコンテンツ情報Bean
	 * @param formBean フォームBean
	 */
	private void setFromDbToForm(PageFormBean formBean, PageFormBean dbPageInfo){
		// 対象者
		formBean.setUserDivision(dbPageInfo.getUserDivision());
		// 発信部署
		formBean.setSourceDepartment(dbPageInfo.getSourceDepartment());
		// 公開確認日付非更新フラグ
		formBean.setConfirmNoupdateFlag(dbPageInfo.getConfirmNoupdateFlag());
		// 新着情報非表示フラグ
		formBean.setNewUndisplayFlag(dbPageInfo.getNewUndisplayFlag());
		//「新規」表示維持
		formBean.setNewKeepFlag(dbPageInfo.getNewKeepFlag());
		// 親ページID
		formBean.setPPageId(dbPageInfo.getPPageId());
		// 配列順
		formBean.setOrderBy(dbPageInfo.getOrderBy());
		// ページKEY
		formBean.setPageKey(dbPageInfo.getPageKey());
		// タイトル
		formBean.setTitle(dbPageInfo.getTitle());
		// コンテンツ
		formBean.setContent(contentReplace(dbPageInfo.getContent()));
		// 公開開始日付
		formBean.setStartDate(dbPageInfo.getStartDate());
		// 公開終了日付
		formBean.setEndDate(dbPageInfo.getEndDate());
		// 画面フラグ
		formBean.setHtmlFlag(dbPageInfo.getHtmlFlag());
		// ファイルURL(ファイル名とアドレス)
		formBean.setHtmlFileUrl(dbPageInfo.getHtmlFileUrl());
		// 非公開フラグ
		formBean.setConfirmFlag(dbPageInfo.getConfirmFlag());
		// ユーザID
		formBean.setCreateBy(dbPageInfo.getCreateBy());
		// ダウンロードパスワード
		formBean.setDownloadPassword(dbPageInfo.getDownloadPassword());
		// ダウンロード不可フラグ
		formBean.setDenyDownloadFlag(dbPageInfo.getDenyDownloadFlag());
		// 文書リンク表示不可フラグ
		formBean.setDenyLinkfileFlag(dbPageInfo.getDenyLinkfileFlag());
		// コンテンツリンク情報(リンク添付)リスト
		formBean.setPageLinkList(dbPageInfo.getPageLinkList());
		//コンテンツ添付ファイル情報（文書添付）リスト
		formBean.setPageAttachmentList(dbPageInfo.getPageAttachmentList());
		// コンテンツ閲覧権限グループ情報(公開するグループ)リスト
		formBean.setAuthGroupList(dbPageInfo.getAuthGroupList());
		// コンテンツ閲覧権限ユーザ情報(公開する個人)リスト
		formBean.setAuthUserList(dbPageInfo.getAuthUserList());
		// コンテンツ更新代行者(承認者)リスト
		formBean.setProxyUserList(dbPageInfo.getProxyUserList());
		// コンテンツ評価フラグ
		formBean.setEvaluationFlag(dbPageInfo.getEvaluationFlag());
		// 評価者氏名表示フラグ
		formBean.setEvaluatorDisplayFlag(dbPageInfo.getEvaluatorDisplayFlag());
		// 評価者コメント編集可フラグ
		formBean.setCommentEditFlag(dbPageInfo.getCommentEditFlag());
		// 複数評価有無フラグ
		formBean.setPluralEvaluationFlag(dbPageInfo.getPluralEvaluationFlag());
		// 評価をクリアするフラグ
		formBean.setEvaluationClearFlag(dbPageInfo.getEvaluationClearFlag());
		// コンテンツ評価アイテム情報リスト
		formBean.setPageRateItemBeanList(dbPageInfo.getPageRateItemBeanList());
		// 前回公開評価者氏名表示フラグ
		formBean.setPrevEvaluatorDisplayFlag(dbPageInfo.getPrevEvaluatorDisplayFlag());
		// 前回複数項目評価可フラグ
		formBean.setPrevPluralEvaluationFlag(dbPageInfo.getPrevPluralEvaluationFlag());
		// コンテンツ評価フラグ(コンテンツ情報)
		formBean.setEvaluationFlagPage(dbPageInfo.getEvaluationFlag());
		// 公開基準日
		formBean.setConfirmDate(dbPageInfo.getConfirmDate());
		// 公開開始日基準フラグ
		formBean.setStartdateOpenFlag(dbPageInfo.getStartdateOpenFlag());
		// コンテンツコメント管理者情報選択(問題報告対応者)リスト
		formBean.setPageCommentAdminOptionList(dbPageInfo.getPageCommentAdminOptionList());
		// コンテンツコメント管理者情報(問題報告対応者)リスト
		formBean.setPageCommentAdminList(dbPageInfo.getPageCommentAdminList());
		// コンテンツ作成者名前
		formBean.setCreateUserName(pageDao.getCreateUserName(formBean.getCreateBy()));
	}
	
	/**
	 * 資料公開処理
	 * @param fromBean フォーム　
	 * @throws Exception 
	 */
	public void updatePageOpen(PageFormBean formBean) throws Exception {
		// コンテンツ情報Bean
		PageBean pageBeanReal = null;
		// コンテンツ情報予約Bean
		PageBean pageBeanRsv = null;
		// 予約コンテンツ添付ファイル情報（文書添付）リスト
		List<PageAttachmentBean> pageAttachmentListRsv = null;
		// ページID
		String pageId = formBean.getPageId();

		//　コンテンツ情報を取得
		pageBeanReal = getPageDB(pageId);				
		// コンテンツ情報が存在してない場合、以降の処理を中止する。
		if (pageBeanReal == null) {
			// エラー画面へ遷移
			optimisticLockCheck(pageId, "0");
		}

		// 公開基準日付非更新フラグ
		String confirmNoupdateFlag = StringUtil.EMPTY;
		// 予約の場合
		if ("1".equals(formBean.getIsReserve())){
			//　コンテンツ情報予約を取得
			pageBeanRsv = getPageRsvDB(pageId);
			// コンテンツ情報予約が存在してない場合、以降の処理を中止する。
			if (pageBeanRsv == null) {
				// エラー画面へ遷移
				optimisticLockCheck(pageId, "1");
			}
			// 排他制御
			if (!pageBeanRsv.getUpdateDateStr().equals(formBean.getUpdateDateStr())){
				// 排他チェック（別ユーザで更新された場合、排他制御を行う。）
				optimisticLockCheck(pageId, "1");
			}
			// 公開基準日付非更新フラグを設定
			confirmNoupdateFlag = pageBeanRsv.getConfirmNoupdateFlag();
		}else {
			// 排他制御
			if (!pageBeanReal.getUpdateDateStr().equals(formBean.getUpdateDateStr())){
				// 排他チェック（別ユーザで更新された場合、排他制御を行う。）
				optimisticLockCheck(pageId, "0");
			}
			// 公開基準日付非更新フラグを設定
			confirmNoupdateFlag = pageBeanReal.getConfirmNoupdateFlag();
		}

		// システム日付取得
		Date dateNow = StringUtil.getNowDate("yyyy/MM/dd");
		String strDateNow = StringUtil.formatTheDate(dateNow, "yyyy/MM/dd");
		// 未来公開フラグが「未来」の場合
		if ("1".equals(formBean.getFutureOpenFlag())){
			// 更新用Bean
			PageBean bean = new PageBean();
			// 公開基準日付非更新フラグが「ON」の場合
			if ("1".equals(confirmNoupdateFlag)){
				// 予約公開基準日
				bean.setConfirmDate(pageBeanReal.getConfirmDate());
			} else{
				// 予約公開基準日:公開開始日
				bean.setConfirmDate(formBean.getStartDate());
			}
			// 更新者
			bean.setUpdateBy(getLoginUserBean().getUserId());
			//　ページID
			bean.setPageId(pageId);
			//　コンテンツ予約情報を更新
			pageDao.updatePageReserve(bean);
		} else{
			// 未来公開フラグが「未来」以外の場合
			// 予約の場合
			if ("1".equals(formBean.getIsReserve())) {
				// 予約コンテンツ添付ファイル情報（文書添付）リスト
				pageAttachmentListRsv = pageDao.getPageAttachListRsv(pageId);
				// コンテンツ相関予約情報からコンテンツ相関情報に移動する。
				moveRsvToReal(formBean, pageBeanRsv);
				// コンテンツ相関予約情報の物理削除
				deletePageRsv(formBean);
				// 公開基準日付非更新フラグが「OFF」の場合
				if (!"1".equals(confirmNoupdateFlag)){
					// ログ情報を削除
					pageDao.deleteLogInfo(pageId, getLoginUserBean().getUserId());
					// ログ情報を登録
					insertLog(getLoginUserBean().getUserId(), pageId);
				}
			}

			//　コンテンツ資料公開Bean
			PageBean pageBeanUpdOpen = new PageBean();
			// コンテンツ情報Beanからコンテンツ資料公開Beanにコピー
			WrappedBeanUtil.copyProperties(pageBeanUpdOpen, pageBeanReal);

			// 初回公開基準日
			boolean firstConfirmDateCompare = false;
			Date firstConfirmDate = null;
			if (!StringUtil.isEmpty(pageBeanUpdOpen.getFirstConfirmDate())){
				// 初回公開基準日
				firstConfirmDate = StringUtil.parseTheDate(pageBeanUpdOpen.getFirstConfirmDate(), "yyyy/MM/dd");
				// 初回公開基準日 > システム日付、true
				firstConfirmDateCompare = dateNow.before(firstConfirmDate);
			}
			// 公開開始日
			boolean startDateCompare = false;
			// 公開基準日付非更新フラグが「ON」の場合
			if ("1".equals(confirmNoupdateFlag)){
				// 予約の場合
				if ("1".equals(formBean.getIsReserve())) {
					// 公開開始日付
					pageBeanUpdOpen.setStartDate(pageBeanRsv.getStartDate());
				}
				// 公開基準日がスペースではない場合
				if (!StringUtil.isEmpty(pageBeanUpdOpen.getConfirmDate())){
					boolean confirmDateCompare = false;
					boolean dateCompare1 = false;
					boolean dateCompare2 = false;
					Date startDate = StringUtil.parseTheDate(pageBeanUpdOpen.getStartDate(), "yyyy/MM/dd");
					Date confirmDate = StringUtil.parseTheDate(pageBeanUpdOpen.getConfirmDate(), "yyyy/MM/dd");
					// 公開開始日 > システム日付、true
					startDateCompare = dateNow.before(startDate);
					// 公開基準日 > システム日付、true
					confirmDateCompare = dateNow.before(confirmDate);
					// 公開基準日 > 公開開始日、true
					dateCompare1 = startDate.before(confirmDate);
					// 初回公開基準日 > 公開開始日、true
					dateCompare2 = startDate.before(firstConfirmDate);
					// 公開開始日:未来、公開基準日 > 公開開始日 ⇒ 公開基準日:公開開始日で設定
					if(startDateCompare && dateCompare1) {
						pageBeanUpdOpen.setConfirmDate(pageBeanUpdOpen.getStartDate());
					// 公開開始日:過去、公開基準日:未来 ⇒ 公開基準日:システム日付で設定
					} else if(!startDateCompare && confirmDateCompare) {
						pageBeanUpdOpen.setConfirmDate(strDateNow);
					}
					// 公開開始日:未来、初回公開基準日 > 公開開始日 ⇒ 初回公開基準日:公開開始日で設定
					if(startDateCompare && dateCompare2) {
						pageBeanUpdOpen.setFirstConfirmDate(pageBeanUpdOpen.getStartDate());
					// 公開開始日:過去、初回公開基準日:未来 ⇒ 初回公開基準日:システム日付で設定
					} else if(!startDateCompare && firstConfirmDateCompare) {
						pageBeanUpdOpen.setFirstConfirmDate(strDateNow);
					}
				}				
			// 公開基準日付非更新フラグが「OFF」の場合
			} else{
				Date startDate = StringUtil.parseTheDate(pageBeanUpdOpen.getStartDate(), "yyyy/MM/dd");
				// 公開開始日 > システム日付、true
				startDateCompare = dateNow.before(startDate);
				
				// 予約の場合
				if("1".equals(formBean.getIsReserve())){
					// 公開基準日 ⇒  システム日付
					pageBeanUpdOpen.setConfirmDate(strDateNow);
					// コンテンツ評価
					pageBeanUpdOpen.setEvaluationFlag(pageBeanRsv.getEvaluationFlag());
					// 評価をクリアする
					pageBeanUpdOpen.setEvaluationClearFlag(pageBeanRsv.getEvaluationClearFlag());
					// 初回公開基準日>= システム日付の場合
					if(dateNow.before(firstConfirmDate)) {
						// 初回公開基準日 ⇒  システム日付
						pageBeanUpdOpen.setFirstConfirmDate(strDateNow);
					}
			    } else {
					// 公開基準日がスペース(初回公開)の場合
					if (StringUtil.isEmpty(pageBeanUpdOpen.getConfirmDate())){
						// 初回公開　且つ　公開開始日基準フラグ が「1(公開開始日基準）」の場合
						if ("1".equals(pageBeanUpdOpen.getStartdateOpenFlag())) {
							// 公開基準日
							pageBeanUpdOpen.setConfirmDate(pageBeanUpdOpen.getStartDate());
							// 初回公開基準日
							pageBeanUpdOpen.setFirstConfirmDate(pageBeanUpdOpen.getStartDate());
						// 初回公開　且つ　公開開始日基準で公開が「0(資料公開クリック日時）」の場合
						} else {
							// 公開開始日 > システム日付の場合、 公開基準日：公開開始日で設定
							if (startDateCompare) {
								// 公開基準日
								pageBeanUpdOpen.setConfirmDate(pageBeanUpdOpen.getStartDate());
								// 初回公開基準日
								pageBeanUpdOpen.setFirstConfirmDate(pageBeanUpdOpen.getStartDate());
							// 公開開始日 < システム日付の場合、 公開基準日：システム日付で設定
							} else {
								// 公開基準日
								pageBeanUpdOpen.setConfirmDate(strDateNow);
								// 初回公開基準日
								pageBeanUpdOpen.setFirstConfirmDate(strDateNow);
							}
						}
					// 編集された場合
					} else {
						// 公開基準日：公開開始日とシステムの最大値
						pageBeanUpdOpen.setConfirmDate(startDateCompare ? pageBeanUpdOpen.getStartDate(): strDateNow);
						// 初回公開基準日が未来場合
						if(dateNow.before(firstConfirmDate)) {
							// 初回公開基準日:公開開始日とシステムの最大値
							pageBeanUpdOpen.setFirstConfirmDate(startDateCompare ? pageBeanUpdOpen.getStartDate() : strDateNow);
						}
					}
			    }
				// コンテンツ評価相関更新
				// 前回公開評価者氏名表示フラグ
				pageBeanUpdOpen.setPrevEvaluatorDisplayFlag(pageBeanUpdOpen.getEvaluatorDisplayFlag());
				// 前回複数項目評価可フラグ
				pageBeanUpdOpen.setPrevPluralEvaluationFlag(pageBeanUpdOpen.getPluralEvaluationFlag());
				String loginUserId = getLoginUserBean().getUserId();
				// コンテンツ評価が「する」の場合
				if ("1".equals(pageBeanUpdOpen.getEvaluationFlag())){
					// 「評価をクリアする」をチェックする場合、
					if ("1".equals(pageBeanUpdOpen.getEvaluationClearFlag())) {
						// コンテンツユーザー評価情報を論理削除
						pageDao.updatePageExtendInvalid(pageId, "PAGE_USER_RATE", loginUserId);
						// コンテンツユーザーコメント情報を物理削除
						pageDao.deletePageExtend(pageId, "PAGE_USER_COMMENT");
						// コンテンツコメント評価情報を物理削除
						pageDao.deletePageExtend(pageId, "PAGE_COMMENT_RATE");
						// 評価をクリアするフラグ
						pageBeanUpdOpen.setEvaluationClearFlag("0");
					}
					// コンテンツ評価アイテム情報の削除情報により、コンテンツユーザー評価情報を論理削除
					pageDao.updatePageUserRateInvalidByRateItem(pageId, loginUserId);
					// テーブルからコンテンツ評価アイテム情報を取得
					List<PageRateItemBean> pageRateItemList = pageDao.getPageRateItemList(pageId);
					if (pageRateItemList != null){
						// コンテンツ評価アイテム情報リストにより、下記の処理を繰り返す
						for(PageRateItemBean pageRateItemBean:pageRateItemList){
							PageUserRateBean pageUserRateBean = new PageUserRateBean();
							// 項目順番
							pageUserRateBean.setEvaluationOrderBy(pageRateItemBean.getEvaluationOrderBy());
							// 更新ユーザID
							pageUserRateBean.setUpdateBy(getLoginUserBean().getUserId());
							// ページID
							pageUserRateBean.setPageId(pageId);
							// SEQUENCE
							pageUserRateBean.setSequence(pageRateItemBean.getSequence());
							// コンテンツユーザー評価情報の項目順番を更新
							pageDao.updatePageUserRateOrderBySeq(pageUserRateBean);
						}
					}
				} else{
					// コンテンツユーザー評価情報を論理削除
					pageDao.updatePageExtendInvalid(pageId, "PAGE_USER_RATE", loginUserId);
					// コンテンツユーザーコメント情報を物理削除
					pageDao.deletePageExtend(pageId, "PAGE_USER_COMMENT");
					// コンテンツコメント評価情報を物理削除
					pageDao.deletePageExtend(pageId, "PAGE_COMMENT_RATE");
					// コンテンツ評価アイテム情報を論理削除
					pageDao.updatePageExtendInvalid(pageId, "PAGE_RATE_ITEM", loginUserId);
					// コンテンツコメント管理者情報を物理削除
					pageDao.deletePageExtend(pageId, "PAGE_COMMENT_ADMIN");
				}
			}
			// 更新ユーザID
			pageBeanUpdOpen.setUpdateBy(getLoginUserBean().getUserId());
			// 公開維持編集フラグ
			pageBeanUpdOpen.setOnEditFlag("0");
			// 公開回数プラス処理
			if(!(firstConfirmDateCompare && "1".equals(pageBeanUpdOpen.getOpenNum()))){
				// 「公開回数+1」処理
				pageBeanUpdOpen.setOpenNum(String.valueOf(NumberUtil.toLong(pageBeanUpdOpen.getOpenNum()) +1));
			}
			// 資料公開コンテンツ情報を更新
			pageDao.updatePageForOpen(pageBeanUpdOpen);
			
			// 予約の場合
			if("1".equals(formBean.getIsReserve())){
				// ファイル相関処理
				fileRelatedForOpen(formBean, pageAttachmentListRsv, pageBeanRsv.getFileSuffix());
			}
		}
		
		//■■■　コンテンツの配置先の設定
		formBean.setPageLocation(getPageLocation(pageId));
		
		// 実コンテンツ、予約コンテンツを判断
		String isReserve = "0";
		// 未来公開フラグが「未来」の場合、予約　それ以外には実コンテンツ
		if ("1".equals(formBean.getFutureOpenFlag())){
			isReserve = "1";
		}
		formBean.setIsReserve(isReserve);

		// このコンテンツのURLを設定
		setPageUrl(formBean);
		
		// ページ表示フラグ←「OPEN」
		formBean.setPageViewFlag("OPEN");
		// 「資料公開」、「メール送信」操作制御フラグ
		formBean.setOpenEnableFlag("0");
	}
	
	/**
	 * このコンテンツのURLを設定
	 * @param formBean フォームBean
	 */
	public void insertLog(String userId, String pageId){
		pageDao.insertLog(getLoginUserBean().getUserId(), pageId);
	}
	
	/**
	 * このコンテンツのURLを設定
	 * @param formBean フォームBean
	 */
	private void setPageUrl(PageFormBean formBean){
		StringBuffer pageUrl = new StringBuffer();
		pageUrl.append(formBean.getContextPath());
		pageUrl.append("/pageView_view.do?formBean.originType=mail&formBean.pageId=");
		// ページID
		pageUrl.append(formBean.getPageId());
		// 当コンテンツは「予約　または　真実」を設定
		pageUrl.append("&formBean.isReserve=" + formBean.getIsReserve());
		// このコンテンツのURL
		formBean.setPageUrl(pageUrl.toString());
	}
	
	/**
	 * コンテンツ相関予約情報からコンテンツ相関情報に移動
	 * @param formBean フォームBean
	 * @param pageBeanRsv コンテンツ情報予約Bean
	 */
	private void moveRsvToReal(PageFormBean formBean, PageBean pageBeanRsv) {
		// 動的コンテンツの場合
		if (ConstantContainer.PAGE_DYNAMIC.equals(formBean.getHtmlFlag())) {
			// コンテンツリンク情報に関してのテーブル操作（予約→実テーブル）
			// コンテンツリンク情報を削除
			pageDao.deletePageExtend(formBean.getPageId(), "PAGE_LINK");
			// コンテンツリンク予約情報からコンテンツリンク情報に移動（キー：ページID）
			pageDao.linkReserveToLinkByPageId(formBean.getPageId());

			// コンテンツ添付ファイル情報に関してのテーブル操作（予約→実テーブル）
			// コンテンツ添付ファイル情報を削除
			pageDao.deletePageExtend(formBean.getPageId(), "PAGE_ATTACHMENT");
			// コンテンツ添付ファイル予約情報からコンテンツ添付ファイル情報に移動（キー：ページID）
			pageDao.attachmentReserveToAttachmentByPageId(formBean.getPageId());
		}
		// コンテンツ閲覧権限トップグループ情報に関してのテーブル操作（予約→実テーブル）
		// コンテンツ閲覧権限トップグループ情報を削除
		pageDao.deletePageExtend(formBean.getPageId(), "AUTH_LEADING_GROUP");
		// コンテンツ閲覧権限トップグループ予約情報からコンテンツ閲覧権限トップグループ情報に移動（キー：ページID）
		pageDao.leadingGroupReserveToLeadingGroupByPageId(formBean.getPageId());

		// コンテンツ閲覧権限ユーザ情報に関してのテーブル操作（予約→実テーブル）
		// コンテンツ閲覧権限ユーザ情報を削除
		pageDao.deletePageExtend(formBean.getPageId(), "AUTH_USER");
		// コンテンツ閲覧権限ユーザ予約情報からコンテンツ閲覧権限ユーザ情報に移動（キー：ページID）
		pageDao.authUserReserveToAuthUserByPageId(formBean.getPageId());

		// 更新代行者情報に関してのテーブル操作（予約→実テーブル）
		// 更新代行者情報を削除
		pageDao.deletePageExtend(formBean.getPageId(), "UPDATE_PROXY_USER");
		// 更新代行者予約情報から更新代行者情報に移動（キー：ページID）
		pageDao.proxyUserReserveToProxyUserByPageId(formBean.getPageId());
		
		// コンテンツ評価が「する」の場合
		if ("1".equals(pageBeanRsv.getEvaluationFlag())){
			// コンテンツ評価アイテム情報に関してのテーブル操作（予約→実テーブル）
			PageRateItemBean pageRateItemBean = new PageRateItemBean();
			// ページID
			pageRateItemBean.setPageId(formBean.getPageId());
			//　更新ユーザID
			pageRateItemBean.setUpdateBy(getLoginUserBean().getUserId());
			// コンテンツ評価アイテム予約情報に存在しなければ、コンテンツ評価アイテム情報を論理削除
			pageDao.updateRateItemInvalidByRateItemReserve(pageRateItemBean);
			// コンテンツ評価アイテム予約情報テーブルからデータを抽出
			List<PageRateItemBean> pageRateItemListRsv = pageDao.getPageRateItemListRsv(formBean.getPageId());
			// コンテンツ評価アイテム情報テーブルからデータを抽出
			List dbPageRateItemList = pageDao.getPageRateItemList(formBean.getPageId());
			if (pageRateItemListRsv != null){
				if (dbPageRateItemList != null){
					// 取得したコンテンツ評価アイテム情報リストにより、下記の処理を繰り返す
					for (int i=0; i<dbPageRateItemList.size();i++){
						// DBコンテンツ評価アイテム情報
						PageRateItemBean dbPageRateItemBean = (PageRateItemBean) dbPageRateItemList.get(i);
						Boolean deleteFlag = true;
						// 画面入力のコンテンツ評価アイテム情報リストにより、下記の処理を繰り返す
						for (PageRateItemBean dbPageRateItemBeanRsv:pageRateItemListRsv){
							// DB、画面両方存在した場合
							if (dbPageRateItemBean.getSequence().equals(dbPageRateItemBeanRsv.getSequence())){
								deleteFlag = false;
								break;
							}
						}
						// DBに存在、画面に存在しない場合、DB論理削除を行う
						if (deleteFlag){
							pageDao.updateEvaluationInvalidBySeq(dbPageRateItemBean.getPageId(), 
									dbPageRateItemBean.getSequence(), getLoginUserBean().getUserId());
						}
					}
				}
				// 下記の処理を繰り返す
				for (PageRateItemBean rateItemRsvBean:pageRateItemListRsv){
					String seq = rateItemRsvBean.getSequence();
					rateItemRsvBean.setCreateDate(new Date());
					rateItemRsvBean.setUpdateDate(new Date());
					// コンテンツ評価アイテム情報に存在しない場合、登録
					if ("0".equals(seq)) {
						// コンテンツ評価アイテム情報テーブルを登録
						pageDao.insertPageRateItem(rateItemRsvBean);
					} else {
						if (dbPageRateItemList != null){
							Boolean deleteFlag = true;
							// 画面入力のコンテンツ評価アイテム情報リストにより、下記の処理を繰り返す
							for (int i=0; i<dbPageRateItemList.size();i++){
								// DBコンテンツ評価アイテム情報
								PageRateItemBean dbPageRateItemBean = (PageRateItemBean) dbPageRateItemList.get(i);
								// DB、画面両方存在した場合
								if (dbPageRateItemBean.getSequence().equals(rateItemRsvBean.getSequence())){
									deleteFlag = false;
									// コンテンツ評価アイテム情報テーブルを更新
									pageDao.updatePageRateItem(rateItemRsvBean);
									break;
								}
							}
							// 画面存在、テーブール存在しない
							if (deleteFlag) {
								// コンテンツ評価アイテム情報を削除（主キー）
								pageDao.deletePageRateItemByPK(rateItemRsvBean);
								// コンテンツ評価アイテム情報テーブルを登録
								pageDao.insertPageRateItem(rateItemRsvBean);
							}
						}
					}
				}
			}			
		}
		
		// コンテンツコメント管理者情報に関してのテーブル操作（予約→実テーブル）
		// コンテンツコメント管理者情報を削除
		pageDao.deletePageExtend(formBean.getPageId(), "PAGE_COMMENT_ADMIN");
		// コンテンツコメント管理者予約情報からコンテンツコメント管理者情報に移動（キー：ページID）
		pageDao.commentAdminReserveToCommentAdminByPageId(formBean.getPageId());
		
		// コンテンツ予約情報からコンテンツ情報かに更新（キー：ページID）
		pageDao.updatePageFromReserve(formBean.getPageId());
	}
	
	/**
	 * ファイル相関処理
	 * @param formBean フォームBean
	 * @param pageAttachmentListReal 実コンテンツ添付ファイル情報（文書添付）リスト
	 * @param pageAttachmentListRsv 予約コンテンツ添付ファイル情報（文書添付）リスト
	 * @throws Exception 
	 */
	private void fileRelatedForOpen(PageFormBean formBean, List<PageAttachmentBean> pageAttachmentListRsv, 
			String fileSuffix) throws Exception {
		
		try {
			// 動的コンテンツの場合
			if (ConstantContainer.PAGE_DYNAMIC.equals(formBean.getHtmlFlag())) {
				// 実コンテンツ添付格納パス
				String serverUploadPath = FsPropertyUtil.getStringProperty("server.upload.path");
				// 予約コンテンツ添付格納パス
				String serverUploadTempPath = FsPropertyUtil.getStringProperty("server.upload.temp.path");
				// バッチファイルパス
				String batchFile = FsPropertyUtil.getStringProperty("batch.file");
				// 予約の場合
				if ("1".equals(formBean.getIsReserve())) {
					// 実コンテンツ添付ファイル情報（文書添付）リストがあった場合、とりあえず、実添付ファイルを削除
			        FileUtil.deletePurgeFile(batchFile, formBean.getPageId());
				}

				// 予約コンテンツ添付ファイル情報（文書添付）リストがあった場合、予約→実フォルダにコピー
				if (pageAttachmentListRsv != null) {
					for (PageAttachmentBean bean : pageAttachmentListRsv) {
						String fileUrl = bean.getAttachmentFileUrl();
						String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
						// 予約→実フォルダにコピー
						FileUtil.copyFile(serverUploadTempPath, serverUploadPath, fileName);
						// 予約添付ファイルを削除
						FileUtil.deleteFile(serverUploadTempPath, fileName);

					}
				}
				// HTMLファイルパス
				String publishHtmlPath = FsPropertyUtil.getStringProperty("htmlFile.path");
				String publishtempHtmlPath = FsPropertyUtil.getStringProperty("htmlFile.temp.path");
				// 予約→実フォルダにコピー（URL変更必要あり）
				String htmlFileName =  formBean.getPageId() + ".html";
				FileUtil.copyHtmlRsvToReal(publishtempHtmlPath, publishHtmlPath, htmlFileName);
				// 予約添付ファイルを削除
				FileUtil.deleteFile(publishtempHtmlPath, htmlFileName);
			} else{
				// 静的コンテンツの場合
				// HTMLファイルパス
				String publishHtmlPath = FsPropertyUtil.getStringProperty("htmlFile.path");
				String publishtempHtmlPath = FsPropertyUtil.getStringProperty("htmlFile.temp.path");
				// コピー先ファイルを削除
				FileUtil.deletePurgeFile(publishHtmlPath, formBean.getPageId());
				
				fileSuffix = StringUtil.nullToBlank(fileSuffix);
				String fileName = "";
				// ZIPファイルの場合
				if (CommonUtil.checkStaticFileSuffix(formBean.getPageId(), fileSuffix)) {
					fileName = formBean.getPageId();
				} else {
					// ZIPファイル以外の場合
					fileName = formBean.getPageId() + fileSuffix;
				}
				FileUtil.fileCopyForStatic(publishtempHtmlPath, publishHtmlPath, fileName, fileName);
				// 予約ファイルを削除
				FileUtil.deletePurgeFile(publishtempHtmlPath, formBean.getPageId());
			}
		} catch (Exception e) {
			// ファイルサーバー
			String fileSever = FsPropertyUtil.getStringProperty("file.server");
			if (!FileUtil.ping(fileSever)){
				FileUtil.ServerAccessException();
			} else {
				throw e;
			}
		}
	}
	
	/**
	 * コンテンツ情報削除処理
	 * @param fromBean フォーム　
	 * @throws Exception 
	 */
	public void deletePageInfo(PageFormBean formBean) throws Exception {
		// コンテンツ情報Bean(排他チェック)
		PageBean pageBeanCheck = null;
		
		// 予約の場合
		if ("1".equals(formBean.getIsReserve())){
			//　コンテンツ情報予約を取得
			pageBeanCheck = getPageRsvDB(formBean.getPageId());
		} else{
			//　コンテンツ情報を取得
			pageBeanCheck = getPageDB(formBean.getPageId());
		}
		// A)存在しなければ、排他チェックエラー
		// B)既に別人で更新された場合、、排他チェックエラー
		if (pageBeanCheck == null) {
			// エラー画面へ遷移
			optimisticLockCheck(formBean.getPageId(), formBean.getIsReserve());
		}
		// 排他制御
		if (!pageBeanCheck.getUpdateDateStr().equals(formBean.getUpdateDateStr())){
			// 排他チェック（別ユーザで更新された場合、排他制御を行う。）
			optimisticLockCheck(pageBeanCheck.getPageId(),formBean.getIsReserve());
		}

		// ■■■■■■■■■■■■■■■■メール相関ロジック開始■■■■■■■■■■■■■■■■■■■■
		// 送信区分フラグ、divFlag = 1　⇒　両方を削除する、divFlag = 0　⇒　実のみを削除する
		String divFlag = "";
		
		// コンテンツ区分フラグ、conFlag = 0　⇒　公開中コンテンツ、conFlag = 1　⇒　作成中コンテンツ、conFlag = 2　⇒　公開待ちコンテンツ
		String conFlag = "0";
		
		// 実コンテンツを取得
		PageBean sendMailPage = getPageDB(formBean.getPageId());
		PageDeleteBean pageDeleteBean =  new PageDeleteBean();
		WrappedBeanUtil.copyProperties(pageDeleteBean, sendMailPage);
		// リクエストのContextPathを保持
		pageDeleteBean.setContextPath(formBean.getContextPath());		

		Date dateNow = null;
		Date startDate = null;
		// システム日付取得
		dateNow = StringUtil.getNowDate("yyyy/MM/dd");
		// ページテーブルの公開開始日取得
		startDate = StringUtil.parseTheDate(pageDeleteBean.getStartDate(), "yyyy/MM/dd");
		boolean dateCompare = dateNow.before(startDate);
		
		// 作成中コンテンツ
		if ("1".equals(pageDeleteBean.getConfirmFlag())) {
			conFlag = "1";
		// 公開待ちコンテンツ
		} else if (dateCompare) {
			conFlag = "2";
		}
		
		// 両方
		if ("1".equals(formBean.getOnEditFlag())) {
			if ("1".equals(formBean.getDeleteradio())) {
				divFlag = "1";
			}
		} else {
			// 実のみを削除する
			divFlag = "0";
		}
		
		// 削除コンテンツの全て情報List
		List<PageDeleteBean> deleteList = new ArrayList();
		if (!StringUtil.isEmpty(divFlag)) {
			deleteList = getDeleteData(pageDeleteBean, divFlag, conFlag);
		}
		// ■■■■■■■■■■■■■■■■メール相関ロジック終了■■■■■■■■■■■■■■■■■■■■
		
		// 「0：(公開予定（編集）中コンテンツのみ削除)」の場合
		if ("0".equals(formBean.getDeleteradio())){
			// 予約コンテンツ情報の物理削除
			deletePageRsv(formBean);
			
			// 公開維持編集フラグを更新（削除用）
			updateOnEditFlagForDelete(formBean);			
			
		} else {
			// 「1：(公開予定（編集）中及び公開中のコンテンツを削除)」の場合
			// A.したまま編集　且つ　1(予約及び公開中のコンテンツを削除)
			// B.したまま編集ではない
			//   ケース１：実コンテンツのみ存在
			//   ケース２：実コンテンツ、予約コンテンツ両方存在

			// 実子コンテンツ情報リストを取得
			List<PageBean> childPageList = pageDao.getChildPageList(formBean.getPageId());
			// 予約子コンテンツ情報リストを取得
			List<PageBean> childPageReserveList = pageDao.getChildPageReserveList(formBean.getPageId());
			// 子コンテンツがあった場合、エラー
			if ((childPageList != null && childPageList.size() > 0)
					|| (childPageReserveList != null && childPageReserveList.size() > 0)){
				// 当コンテンツには子コンテンツが紐付いているため削除できませんのエラーが出す
				String message = getErrorMessage("MSGOE156", new String[]{formBean.getPageId()});
				CommonErrorPageException e = new CommonErrorPageException(message);
				e.getErrorMessageList().add(message);
				throw e;
			}			
			
			String loginUserId = getLoginUserBean().getUserId();
			// 実コンテンツ情報を論理削除
			PageBean pageBean = new PageBean();
			// ページID
			pageBean.setPageId(formBean.getPageId());
			// 更新ユーザID
			pageBean.setUpdateBy(loginUserId);
			pageDao.updatePageInvalid(pageBean);
			
			// 予約コンテンツ情報の物理削除
			deletePageRsv(formBean);
			
			// 動的コンテンツの場合
			if (ConstantContainer.PAGE_DYNAMIC.equals(formBean.getHtmlFlag())) {
				// コンテンツリンク情報を論理削除
				pageDao.updatePageExtendInvalid(formBean.getPageId(), "PAGE_LINK", loginUserId);
				// コンテンツ添付ファイル情報を論理削除
				pageDao.updatePageExtendInvalid(formBean.getPageId(), "PAGE_ATTACHMENT", loginUserId);
			}
			
			// コンテンツ閲覧権限トップグループ情報を論理削除
			pageDao.updatePageExtendInvalid(formBean.getPageId(), "AUTH_LEADING_GROUP", loginUserId);
			// コンテンツ閲覧権限ユーザ情報を論理削除
			pageDao.updatePageExtendInvalid(formBean.getPageId(), "AUTH_USER", loginUserId);
			// 更新代行者情報を論理削除
			pageDao.updatePageExtendInvalid(formBean.getPageId(), "UPDATE_PROXY_USER", loginUserId);
			// ログ情報を論理削除
			pageDao.updatePageExtendInvalid(formBean.getPageId(), "LOG", loginUserId);
			// コンテンツ評価アイテム情報を論理削除
			pageDao.updatePageExtendInvalid(formBean.getPageId(), "PAGE_RATE_ITEM", loginUserId);
			// コンテンツユーザー評価情報を論理削除
			pageDao.updatePageExtendInvalid(formBean.getPageId(), "PAGE_USER_RATE", loginUserId);
			// コンテンツユーザーコメント情報を論理削除
			pageDao.updatePageExtendInvalid(formBean.getPageId(), "PAGE_USER_COMMENT", loginUserId);
			// コンテンツコメント評価情報を論理削除
			pageDao.updatePageExtendInvalid(formBean.getPageId(), "PAGE_COMMENT_RATE", loginUserId);
			// コンテンツコメント管理者情報を論理削除
			pageDao.updatePageExtendInvalid(formBean.getPageId(), "PAGE_COMMENT_ADMIN", loginUserId);
		}
		
		// ■■■■■■■■■■■■■■■■メール相関ロジック開始■■■■■■■■■■■■■■■■■■■■
		LoginUserBean loginUser = getLoginUserBean();
		String userid = loginUser.getUserId();
		String kanaMei = StringUtil.nullToBlank(loginUser.getKanjiMei());
		String kanaSei = StringUtil.nullToBlank(loginUser.getKanjiSei());
		String user = kanaSei + kanaMei + "("+userid+")";

		// リンク元やリンク先存在の場合、送信
		if (deleteList.size() > 0) {
			PageDeleteBean pageDeleteBeanMail = deleteList.get(0);
			List linkContentsList = pageDeleteBeanMail.getLinkContentsList();
			boolean linkContentsflag = getContentFlag(linkContentsList, "0");
			List<List<PageLinkBean>> linkedList = pageDeleteBeanMail.getLinkedList();
			boolean linkedflag = getContentFlag(linkedList, "1");
			Date endDate = null;
			// ページテーブルの公開終了日取得
			endDate = StringUtil.parseTheDate(pageDeleteBeanMail.getEndDate(), "yyyy/MM/dd");
			boolean enddateCompare = dateNow.before(endDate);

			//メールの内容処理と送信
			if (enddateCompare) {
				if (linkContentsflag || linkedflag) {
					deleteSendMail(deleteList, user);
					// ログを出力
					formBean.setDeleteMailLog(deleteMailLog(deleteList));
				}
			}
		}
		// ■■■■■■■■■■■■■■■■メール相関ロジック終了■■■■■■■■■■■■■■■■■■■■
		
		// ファイル相関処理(コンテンツ削除)
		fileRelatedForDelete(formBean);
	}
	
	/**
	 * 公開維持編集フラグを更新（削除用）
	 * 「0:公開したまま編集ではない」に更新
	 * @param formBean フォームBean
	 */
	private void updateOnEditFlagForDelete(PageFormBean formBean) {
		// 予約コンテンツ情報の物理削除
		deletePageRsv(formBean);
		PageBean page = new PageBean();
		// ページID
		page.setPageId(formBean.getPageId());
		// 公開維持編集フラグ
		page.setOnEditFlag("0");
		// 更新ユーザID
		page.setUpdateBy(getLoginUserBean().getUserId());
		// コンテンツ情報の更新
		//■■■　真実コンテンツの公開維持編集フラグ「ON_EDIT_FLAG」を0(公開したまま編集ではない)に更新
		pageDao.updateOnEditFlag(page);
	}
	
	/**
	 * ファイル相関処理(コンテンツ削除)
	 * @param formBean フォームBean
	 * @throws Exception 
	 */
	private void fileRelatedForDelete(PageFormBean formBean) throws Exception{
		try {
			// 動的コンテンツの場合
			if (ConstantContainer.PAGE_DYNAMIC.equals(formBean.getHtmlFlag())) {
				// 予約コンテンツ添付格納パス
				String serverUploadTempPath = FsPropertyUtil.getStringProperty("server.upload.temp.path");
				FileUtil.deletePurgeFile(serverUploadTempPath, formBean.getPageId());

				// HTMLファイルパス
				String publishtempHtmlPath = FsPropertyUtil.getStringProperty("htmlFile.temp.path");
				String htmlFileName =  formBean.getPageId() + ".html";
				// 予約添付ファイルを削除
				FileUtil.deleteFile(publishtempHtmlPath, htmlFileName);
			} else{
				// 静的コンテンツの場合
				// 予約HTML格納フォルダを削除
				String staticFilePath = FsPropertyUtil.getStringProperty("htmlFile.temp.path");
				FileUtil.deletePurgeFile(staticFilePath, formBean.getPageId());
				// 一時格納フォルダを削除
				staticFilePath = FsPropertyUtil.getStringProperty("temp.temp.path");
				FileUtil.deletePurgeFile(staticFilePath, formBean.getPageId());
			}			
		} catch (Exception e) {
			// ファイルサーバー
			String fileSever = FsPropertyUtil.getStringProperty("file.server");
			if (!FileUtil.ping(fileSever)){
				FileUtil.ServerAccessException();
			} else {
				throw e;
			}
		}
	}
	
	/**
	 * コンテンツ確認処理
	 * @param fromBean フォーム　
	 * @throws Exception 
	 */
	public void confirmPage(PageFormBean formBean) throws Exception {
		// 公開維持編集フラグ
		formBean.setOnEditFlag(StringUtil.nullToZero(formBean.getOnEditFlag()));
		// コンテンツコメント管理者情報(問題報告対応者)
		pageCommentAdminRelated(formBean);
		// コンテンツ登録の場合、ページIDを採番
		if ("ADD".equals(formBean.getPageViewFlag())) {
			GregorianCalendar now = new GregorianCalendar();
			String nowTime = String.valueOf(now.getTimeInMillis());
			// ページIDを設定
			formBean.setPageId(nowTime);			
		}
		// 動的コンテンツの場合
		if (ConstantContainer.PAGE_DYNAMIC.equals(formBean.getHtmlFlag())){
			// タイトル名（編集）
			formBean.setTitles(CommonUtil.getErrorContent(formBean.getTitle()));
			// コンテンツリンク情報相関処理
			pageLinkRelated(formBean, formBean.getContextPath());
		}
		// コンテンツ添付ファイル情報相関処理
		pageFileRelated(formBean);
		// コンテンツ評価情報相関処理
		pageRateRelated(formBean);
		// テンプレートからの場合、テンプレート相関チェックを実施
		if ("1".equals(formBean.getIsTemplateFrom())){
			// テンプレート相関チェック
			templateCheck(formBean);
		}
	}
	
	/**
	 * コンテンツリンク情報相関処理
	 * @param fromBean フォーム　
	 * @param strContextPath リクエスト対象のContextPath
	 */
	private void pageLinkRelated(PageFormBean formBean, String strContextPath) {
		// コンテンツ
		String contents = formBean.getContent();
		// コンテンツリンク添付
		List<PageLinkBean> pageLinkList = formBean.getPageLinkList();
		//　画面リンク添付が入力された場合
		if (pageLinkList != null && pageLinkList.size() > 0) {	
			//　内部リンクURL
			String strUrl = strContextPath + "/";
			// 外部リンク画面
			String windowStyle = "toolbar=yes,location=yes,channelmode=no,fullscreen=no,directories=no,menubar=yes,resizable=yes,scrollbars=yes,minimize=no";
			// 画面のリンク添付リストにより、下記の処理を繰り返し
			for (PageLinkBean bean : pageLinkList) {
				// キーワード
				String linkName = bean.getLinkName();
				//　URL
				String linkUrl = bean.getLinkUrl();
				//　削除以外　且つ　リンク添付キーワードが入力された場合
				if (!"1".equals(bean.getLinkDeleteFlag())
						&& StringUtil.isNotEmpty(linkName)
						&& StringUtil.isNotEmpty(linkUrl)) {
					StringBuffer linkSbr = new StringBuffer("");
					String pageURL = "";
					if (linkUrl.startsWith(strUrl)) {
						pageURL = linkUrl.substring(linkUrl.indexOf("&pageURL=") + 9);
					}
					// 内部リンクの場合
					if (pageURL.startsWith(strUrl)){
						linkSbr.append("<a href='#' onclick=\"doLink('");
						linkSbr.append(linkUrl);
						linkSbr.append("')\">");
						linkSbr.append(linkName);
						linkSbr.append("</a>");
					} else {
						// 外部リンクの場合
//						linkSbr.append("<a href='#' onclick=\"window.open('");
						linkSbr.append("<a target='_blank' href='");
						if (!linkUrl.startsWith(strUrl)) {
							if (!linkUrl.toLowerCase().startsWith("http")) {
								linkUrl = "http://" + linkUrl;
							}
						}
						linkSbr.append(linkUrl);
						linkSbr.append("'>");
//						linkSbr.append("','");
//						linkSbr.append("','");
//						linkSbr.append(windowStyle);
//						linkSbr.append("')\">");
						linkSbr.append(linkName);
						linkSbr.append("</a>");
					}

					contents = contents.replace(linkName, linkSbr.toString());
				}
			}
		}
		// コンテンツのエラー変換
		contents = CommonUtil.getErrorContent(contents);
		contents = contentReplace(contents);
		//　フォームに設定
		formBean.setContents(contents);
	}
	
	/**
	 * ファイル情報相関処理
	 * @param fromBean フォーム　
	 * @throws Exception 
	 */
	private void pageFileRelated(PageFormBean formBean) throws Exception {
		
		try {
			// コンテンツ添付ファイル一時ファイルをアップロードする
			String tempPageAttachment = FsPropertyUtil.getStringProperty("temp.page.attachment");
			// 一時ファイルを削除
			FileUtil.deleteFilePeriod(tempPageAttachment, FsPropertyUtil.getIntProperty("upload.temp.file.days"));

			// コンテンツURL
			String pageFileUrl = StringUtil.EMPTY;
			// コンテンツ一時URL
			String tempFileUrl = StringUtil.EMPTY;
			// 静的コンテンツ一時保存パス
			String tempStaticFilePath = StringUtil.EMPTY;
			// 「1(公開したまま編集する)」の場合
			if("1".equals(formBean.getOnEditFlag())) {
				tempFileUrl = FsPropertyUtil.getStringProperty("temp.temp.url");
				tempStaticFilePath = FsPropertyUtil.getStringProperty("temp.temp.path");
			} else {
				tempFileUrl = FsPropertyUtil.getStringProperty("temp.url");
				tempStaticFilePath = FsPropertyUtil.getStringProperty("temp.path");
			}			
			// 動コンテンツ
			if (ConstantContainer.PAGE_DYNAMIC.equals(formBean.getHtmlFlag())) {
				
				// 文書添付存在フラグ
				formBean.setAttachmentExistFlag("0");
				// コンテンツ添付ファイル情報の最大URLを取得
				String attachmentFileUrl = pageDao.getMaxAttachmentUrl(formBean.getPageId());
				String strAttCount = "";
				int intAttCount = 0;
				// DBに存在した場合
				if (StringUtil.isNotBlank(attachmentFileUrl)) {
					if (attachmentFileUrl.lastIndexOf(".") < 0) {
						strAttCount = attachmentFileUrl.substring(attachmentFileUrl.lastIndexOf("/") + 14, attachmentFileUrl.length());
					} else {
						strAttCount = attachmentFileUrl.substring(attachmentFileUrl.lastIndexOf("/") + 14, attachmentFileUrl.lastIndexOf("."));
					}
					intAttCount = Integer.parseInt(strAttCount) + 1;
				}
				// コンテンツ添付ファイル情報（文書添付）リスト
				List<PageAttachmentBean> fileList = formBean.getPageAttachmentList();
				List<PageAttachmentBean> fileListNew = new ArrayList<PageAttachmentBean>();
				// 添付ファイルがあった場合、添付ファイルがないレコードを削除
				if (fileList != null) {
					for (PageAttachmentBean bean : fileList) {
						if (bean != null){
							fileListNew.add(bean);
						}
					}
					formBean.setPageAttachmentList(fileListNew);
				}

				// 添付ファイルがあった場合
				fileList = formBean.getPageAttachmentList();
				if (fileList != null) {
					// ファイルパス
					String filePath = "";
					// 「1(公開したまま編集する)」の場合
					if("1".equals(formBean.getOnEditFlag())) {
						filePath = FsPropertyUtil.getStringProperty("attachmentFile.temp.url");
					} else {
						filePath = FsPropertyUtil.getStringProperty("attachmentFile.url");
					}
					for (PageAttachmentBean bean : fileList) {
						bean.setFileDeleteFlag(StringUtil.nullToZero(bean.getFileDeleteFlag()));
						//既存添付ファイル以外、削除以外の場合
						if (StringUtil.isEmpty(bean.getSequence()) 
								&& !"1".equals(bean.getFileDeleteFlag())) {
							// 添付ファイル名称
							String fileName = bean.getAttachmentFileName();
							String suffix = fileName.lastIndexOf(".") < 0 ? "" : fileName.substring(fileName.lastIndexOf("."));
							// アップロードファイル名称
							String uploadFileName = formBean.getPageId() + String.valueOf(intAttCount) + suffix;
							bean.setAttUploadFileName(uploadFileName);
							// 添付ファイルURL(ファイル名とアドレス)
							bean.setAttachmentFileUrl(filePath + "/" + uploadFileName);
							// 添付ファイル名称
							bean.setAttachmentName(bean.getAttachmentFileName());
							FileUtil.uploadFile(bean.getAttachment(), bean.getAttachment().getName(), tempPageAttachment);
							// 一時保存アップロードファイル名称
							String file = tempPageAttachment + File.separator + bean.getAttachment().getName();
							bean.setAttachment(new File(file));
							intAttCount = intAttCount + 1;
						}
						// 文書添付存在フラグを設定
						if (!"1".equals(bean.getFileDeleteFlag())){
							// 文書添付存在フラグ
							formBean.setAttachmentExistFlag("1");
						}
					}
				}
				
				// 動コンテンツのHTMLファイル作成
				createHtmlFile(formBean);
				// コンテンツURL
				pageFileUrl = tempFileUrl + "/" + formBean.getPageId() + ".html";
				// コンテンツURL
				formBean.setPageFileUrl(pageFileUrl);

			} else{
				//　静的コンテンツの場合
				// 一時格納フォルダを削除
				FileUtil.deletePurgeFile(tempStaticFilePath, formBean.getPageId());
				
				// 文書添付リスト（静的コンテンツは一つレコードのみ存在）
				List<PageAttachmentBean> fileList = formBean.getPageAttachmentList();
				// 添付ファイルがあった場合
				if (fileList != null && !StringUtil.isEmpty(fileList.get(0).getAttachmentFileName())) {
					// 添付ファイルBean
					PageAttachmentBean bean = fileList.get(0);
					// 添付ファイル名称
					String fileName = bean.getAttachmentFileName();
					// 拡張子
					String fileSuffix = fileName.lastIndexOf(".") >= 0 ? fileName.substring(fileName.lastIndexOf(".")) : "";
					// ZIPファイルの場合
					if (bean.getAttachmentFileName().toLowerCase().endsWith(".zip")) {
						String mainFile = formBean.getMainFile();
						String suffix = mainFile.lastIndexOf(".") >= 0 ? mainFile.substring(mainFile.lastIndexOf(".")): "";
						String mainName = formBean.getPageId() + suffix;
						
						// ファイルをアップロード
						FileUtil.uploadFile(bean.getAttachment(), formBean.getPageId() + ".zip", tempStaticFilePath);
						String zipUploadName = tempStaticFilePath + File.separator + formBean.getPageId() + ".zip";
						
						// ZIPファイルチェック
						zipFileCheck(zipUploadName, mainFile);

						// ZIPの場合、格納パスはページIDの子フォルダーに格納
						String uploadPath = tempStaticFilePath + File.separator + formBean.getPageId() + File.separator;
						File uploadPathFile = new File(uploadPath);
						// 存在した場合、削除
						if (uploadPathFile.exists()) {
							uploadPathFile.delete();
						}
						// フォルダーを作成
						uploadPathFile.mkdirs();

						// ZIPファイルを解凍し、元ZIPファイルを削除する。
						FileUtil.unzipFile(uploadPath, zipUploadName, mainFile, mainName);
						
						List<File> filelist = new ArrayList<File>();
						
						getFileList(uploadPath, filelist);
						for (int i = 0; i < filelist.size(); i++) {
							replaceMeta(filelist.get(i));
							replaceLink(filelist.get(i), mainName, mainFile);
						}
						// コンテンツURL
						pageFileUrl = tempFileUrl + "/" + formBean.getPageId() + "/" + mainName;
						// ファイル拡張子
						formBean.setFileSuffix(mainName);
					} else{
						// コンテンツURL
						pageFileUrl = tempFileUrl + "/" + formBean.getPageId() + fileSuffix;
						// ファイルをアップロード
						FileUtil.uploadFile(bean.getAttachment(), formBean.getPageId() + fileSuffix, tempStaticFilePath);
						if (bean.getAttachmentFileName().toLowerCase().endsWith(".html") 
								|| bean.getAttachmentFileName().toLowerCase().endsWith(".htm")) {
							replaceMeta(new File(tempStaticFilePath + "\\" + formBean.getPageId() + fileSuffix));
						}
						// ファイル拡張子
						formBean.setFileSuffix(fileSuffix);
					}
					// コンテンツURL
					formBean.setPageFileUrl(pageFileUrl);
				} else{
					// 添付ファイルがない場合
					// コンテンツ更新の場合
					if ("EDIT".equals(formBean.getPageViewFlag())){
						// ページBean
						PageBean pageBean = new PageBean();
						String fileUrlPath = StringUtil.EMPTY;
						String htmlFilePath = StringUtil.EMPTY;
						// 1(予約中)の場合
						if ("1".equals(formBean.getUpdateradio())){
							//　コンテンツ予約情報の取得
							pageBean = getPageRsvDB(formBean.getPageId());
							fileUrlPath = FsPropertyUtil.getStringProperty("fileUrl.temp.path");
							htmlFilePath = FsPropertyUtil.getStringProperty("htmlFile.temp.path");
						} else {
							//　コンテンツ情報の取得
							pageBean = getPageDB(formBean.getPageId());
							fileUrlPath = FsPropertyUtil.getStringProperty("fileUrl.path");
							htmlFilePath = FsPropertyUtil.getStringProperty("htmlFile.path");
						}
						// コンテンツ情報が存在してない場合、以降の処理を中止する。
						if (pageBean == null) {
							// エラー画面へ遷移
							optimisticLockCheck(formBean.getPageId(), formBean.getUpdateradio());
						}
						
						String fileSuffix = StringUtil.nullToBlank(pageBean.getFileSuffix());
						String fileName = "";
						if (CommonUtil.checkStaticFileSuffix(formBean.getPageId(), fileSuffix)) {
							fileName = formBean.getPageId();
							// コンテンツURL
							pageFileUrl = fileUrlPath + "/" + formBean.getPageId() + "/"+ fileSuffix;
						} else {
							fileName = formBean.getPageId() + fileSuffix;
							// コンテンツURL
							pageFileUrl = fileUrlPath + "/" + formBean.getPageId() + fileSuffix;
						}
						// 「予約中」以外の場合
						if (!"1".equals(formBean.getUpdateradio())){
							FileUtil.fileCopyForStatic(htmlFilePath, tempStaticFilePath, fileName, fileName);
						}
						// コンテンツURL
						formBean.setPageFileUrl(pageFileUrl);
						// ファイル拡張子
						formBean.setFileSuffix(pageBean.getFileSuffix());
					}
					// テンプレートからの場合
					if ("1".equals(formBean.getIsTemplateFrom())){
						// テンプレートコンテンツ情報を取得
						String templatePageId = formBean.getTemplatePageId();
						PageBean pageBean = templatePageService.getTemplatePagebyID(templatePageId);
						String fileSuffix = pageBean.getFileSuffix();
						String fromFileName = StringUtil.EMPTY;
						String toFileName = StringUtil.EMPTY;
						String fileUrlPath = FsPropertyUtil.getStringProperty("template.fileUrl.path")+ "/";
						String templateHtmlFilePath = FsPropertyUtil.getStringProperty("template.htmlFile.path") 
								+ File.separator + pageBean.getTemplateCreateBy();
						if (!StringUtil.isEmpty(fileSuffix) && !fileSuffix.startsWith(".")) {
							pageFileUrl = fileUrlPath + pageBean.getTemplateCreateBy() + "/" + templatePageId + "/" + fileSuffix;
							fileSuffix = fileSuffix.replaceAll(templatePageId, formBean.getPageId());
							fromFileName = templatePageId;
							toFileName = formBean.getPageId();
			                FileUtil.copyStaticForTemplate(templateHtmlFilePath, tempStaticFilePath, templatePageId, formBean.getPageId());
						} else {
							pageFileUrl = fileUrlPath + pageBean.getTemplateCreateBy() + "/" + templatePageId + fileSuffix;
							fromFileName = templatePageId + fileSuffix;
							toFileName = formBean.getPageId() + fileSuffix;
		                    FileUtil.copyFile(templateHtmlFilePath, tempStaticFilePath, fromFileName, toFileName);
						}
						// コンテンツURL
						formBean.setPageFileUrl(pageFileUrl);
						// ファイル拡張子
						formBean.setFileSuffix(fileSuffix);
					}
				}
				// 文書添付存在フラグ
				formBean.setAttachmentExistFlag("1");
			}
		} catch (BusinessServiceException e) {
			throw e;
		} catch (Exception e){
			// ファイルサーバー
			String fileSever = FsPropertyUtil.getStringProperty("file.server");
			if (!FileUtil.ping(fileSever)){
				FileUtil.ServerAccessException();
			} else {
				throw e;
			}
		}
	}
	
	/**
	 * 静的htmlコンテンツの<meta>追加
	 * @param file
	 */
	private void replaceMeta(File file){
		boolean isMetaFlag = false;
		boolean isHeadFlag = false;
		boolean isHTML5Flag = false;
		String[] trimChar = {" ", "　"};
		String metaInfo = "";
		try {
			String charset = getEncodeTextStr(file);
			String metaCharset = "utf-8".equals(charset) ? "utf-8" : "shift_jis";
			List<String> readList = getReadFileList(file, charset);
			for (int i = 0; i < readList.size(); i++) {
				String readLine = StringUtil.lowerCase(readList.get(i));
				// 先頭に<!DOCTYPE>として宣言されるのがHTML5と認識する
				// HTML5判定：一行目（全角、半角スペースＴＲＩＭ）が<!DOCTYPEHTML>
				if (StringUtil.alltrimer(trimChar, readLine).indexOf("<!doctypehtml>") >= 0) {
					isHTML5Flag = true;
				}
				// head存在判定
				if (readLine.indexOf("<head>") >= 0) {
					isHeadFlag = true;
				}
				// 「<meta」、「charset」という文言を探して、
				if (readLine.indexOf("<meta") >= 0 && readLine.indexOf("charset") >= 0) {
					// ある場合、何もしない
					isMetaFlag = true;
				}
			}
			// HTML5の場合、<meta charset="ＸＸＸＸ";>を設定
			if (isHTML5Flag) {
				metaInfo = "<meta charset=\"" + metaCharset + "\">";
			// HTML5以外の場合、<meta http-equiv=Content-Type content="text/html; charset=XXXXX">を設定
			} else {
				metaInfo = "<meta http-equiv=Content-Type content=\"text/html; charset=" + metaCharset + "\">";
			}
			// meta無し場合、
			if (!isMetaFlag) {
				// <head>存在の場合
				if (isHeadFlag) {
					for (int i = 0; i < readList.size(); i++) {
						String readLine = readList.get(i);
						// <head></head>内にcharset設定
						int charAt = StringUtil.lowerCase(readLine).indexOf("<head>");
						if (charAt >= 0) {
							readLine = readLine.substring(0, charAt + 6) + metaInfo + readLine.substring(charAt + 6);
						}
						readList.set(i, readLine);
					}
				// <head>不存在の場合、<head>＋charset設定
				} else {
					readList.add(0, "<head>" + metaInfo + "</head>");
				}
				// ファール書きます
				writeFileList(file, readList, charset);
			}
		} catch (Exception e) {
			throw new LocalRuntimeException("ファイル処理にて、エラーが発生しました。", e);
		}
	}
	
	/**
	 * 静的htmlコンテンツのリンクファール名置換
	 * @param file
	 */
	private void replaceLink(File file, String mainName, String mainFile) {
		if (mainFile.indexOf("/") > 0 && mainFile.indexOf("/") + 1 < mainFile.length()) {
			mainFile = mainFile.substring(mainFile.indexOf("/") + 1);
		}
		boolean isMainName = false;
		try{
			String charset = getEncodeTextStr(file);
			List<String> readList = getReadFileList(file, charset);
			for (int i = 0; i < readList.size(); i++) {
				String readLine = StringUtil.lowerCase(readList.get(i));
				int mainFileAt = readLine.indexOf(StringUtil.lowerCase(mainFile));
				// リンクにファール名存在の場合
				if (mainFileAt >= 0) {
					isMainName = true;
				}
			}
			// リンクにファール名存在の場合、置換する
			if (isMainName) {
				for (int i = 0; i < readList.size(); i++) {
					String readLine = readList.get(i);
					int mainFileAt = StringUtil.lowerCase(readLine).indexOf(StringUtil.lowerCase(mainFile));
					// リンクにファール名存在の場合
					if (mainFileAt >= 0) {
						// リンクファール名置換　ファイル名→ページ名
						readLine = readLine.substring(0, mainFileAt) + mainName + readLine.substring(mainFileAt + mainFile.length());
					}
					
					readList.set(i, readLine);
					// ファール書きます
					writeFileList(file, readList, charset);
				}
			}
		} catch (Exception e) {
			throw new LocalRuntimeException("ファイル処理にて、エラーが発生しました。", e);
		}
	}
	
	/**
	 * ファール書きます
	 * @param file
	 * @param readList
	 * @param charset
	 * @throws Exception
	 */
	private void writeFileList(File file, List<String> readList, String charset) throws Exception{
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), charset));
			for (int i = 0; i < readList.size(); i++){
				String readLine = readList.get(i);
				writer.write(readLine);
				writer.newLine();
			}
			writer.flush();
		} catch (Exception e) {
			throw new LocalRuntimeException("ファイル処理にて、エラーが発生しました。", e);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	/**
	 * ファール取り込み
	 * @param file
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	private List<String> getReadFileList(File file, String charset) throws Exception{
		BufferedReader reader = null;
		List<String> result = new ArrayList<String>();
		String readLine = "";
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
			while ((readLine = reader.readLine()) != null) {
				result.add(readLine);
			}
		} catch (Exception e) {
			throw new LocalRuntimeException("ファイル処理にて、エラーが発生しました。", e);
		} finally {
			if (reader != null){
				reader.close();
				reader = null;
			}
		}
		return result;
	}
	
	/**
	 * コンテンツエンコード判定、PageViewActionを参照する
	 * @param fileUrl
	 * @return
	 * @throws IOException
	 */
	private String getEncodeTextStr(File file) throws IOException {
		
		boolean canEncode = true;
		String[] charsetNameArray = {"shift_jis", "ms932", "utf-8"};
		String result = "utf-8";
		
		for (int i=0; i<charsetNameArray.length; i ++) {
			String charsetName = charsetNameArray[i];
			canEncode = getEncodeTextStrByCharsetName(file, charsetName, canEncode);
			if (canEncode) {
				result = charsetName;
				break;
			}
		}
		return result;
	}
	
	/**
	 * コンテンツエンコード判定、PageViewActionを参照する
	 * @param fileUrl
	 * @return
	 * @throws IOException
	 */
	private boolean getEncodeTextStrByCharsetName(File file, String charsetName, boolean canEncode) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream, charsetName));
		String line = "";
		while ((line = br.readLine()) != null) {
			
			if(!StringUtil.isEmpty(charsetName)){
				if (!Charset.forName(charsetName).newEncoder().canEncode(line)) {
					canEncode = false;
					break;
				}
				canEncode = true;
			}
		}
		br.close();
		return canEncode;
	}
	
	/**
	 * ZIPの全てファールパースを取得
	 * @param strPath
	 * @param filelist
	 * @return
	 */
	public static List<File> getFileList(String strPath, List<File> filelist) {
		List<File> files = new ArrayList<File>();
		files = FileUtil.getFileList(strPath, files);
		for (int i = 0; i < files.size(); i++) {
			File file = files.get(i);
			String fileName = StringUtil.lowerCase(file.getName());
			if (fileName.endsWith("html") || fileName.endsWith("htm")) {
				filelist.add(file);
			}
		}
		return filelist;
	}
	
	/**
	 * コンテンツ評価情報相関処理
	 * @param fromBean フォーム　
	 */
	private void pageRateRelated(PageFormBean formBean) {
		// コンテンツ評価フラグが「する」の場合
		if ("1".equals(formBean.getEvaluationFlag())) {
			// コンテンツ評価アイテム情報リスト
			List<PageRateItemBean> pageRateItemBeanList = formBean.getPageRateItemBeanList();
			// 評価項目リストが入力された場合、実は必須入力
			if (pageRateItemBeanList != null){
				// コンテンツ評価アイテム情報リストにより、下記の処理を繰り返す
				for (PageRateItemBean pageRateItemBean : pageRateItemBeanList){
					// 評価カウント
					String evaluationCount = "0";
					// 「1：公開したまま編集」　または　「評価をクリアする」 が選択された　または「新規追加行」の場合、「0」
					if ("1".equals(formBean.getOnEditFlag()) 
							|| "1".equals(formBean.getEvaluationClearFlag())
							|| StringUtil.isEmpty(pageRateItemBean.getSequence())) {
						evaluationCount = "0";
					// 上記以外の場合
					} else{
						// コンテンツ確認画面の評価数を取得
						evaluationCount = pageDao.getEvaluationCount(formBean.getPageId(), pageRateItemBean.getSequence());
					}
					// Beanに評価数を設定
					pageRateItemBean.setEvaluationCount(evaluationCount);
				}
			}
			
			// コンテンツコメント統計を取得
			// 「1：公開したまま編集」　または　「評価をクリアする」 が選択された場合、「0」
			if ("1".equals(formBean.getOnEditFlag()) 
					|| "1".equals(formBean.getEvaluationClearFlag())){
				formBean.setCommentCount(0);
			} else{
				// コンテンツコメント統計情報を取得
				formBean.setCommentCount(pageDao.getCommentCount(formBean.getPageId()));
			}
		}
	}
	
	/**
	 * ドロップダウンリスト作成
	 * @param fromBean フォーム　
	 * @throws ParseException 
	 */
	public void getDropdownList(PageFormBean formBean) throws ParseException {
		//　検索条件MAP
		Map<Object, Object> param = null;
		
		// 対象者ドロップダウンリストを作成
		param = new HashMap<Object, Object>();
		// フィールド名
		param.put("PARA_FIELD_NAME", "TARGET_USER");
		// 対象者リストを取得
		formBean.setUserDivisionDropList(pageDao.getUserDivisionDropDownList(param));
		
		// 発信部署ドロップダウンリストを作成
		formBean.setSourceDeptDropList(commonDao.getDepartmentList());

	}

	/**
	 * 対象者選択区分を設定する。
	 * @param fromBean フォーム　
	 */
	private void setUserType(PageFormBean formBean) {
		// 対象者選択区分がスペースの場合
		if (StringUtil.isEmpty(formBean.getUserType())) {
			// 対象者がスペースの場合
			if (StringUtil.isNotEmpty(formBean.getUserDivision())){
				Map UserDivisionMap = new HashMap();
				// 対象者ドロップリストがNULLではない場合
				List userDivisionDropList = formBean.getUserDivisionDropList();
				if (!StringUtil.isBlank(userDivisionDropList)) {
					for(int i = 0; i < userDivisionDropList.size(); i++) {
						LabelValueBean labelValueBean = (LabelValueBean) userDivisionDropList.get(i);
						UserDivisionMap.put(labelValueBean.getValue(), labelValueBean.getLabel());
					}
				}
				// 対象者ドロップリストに「対象者」が含められた場合
				if (UserDivisionMap.containsKey(formBean.getUserDivision())) {
					// 対象者選択に設定
					formBean.setUserType("0");
				} else{
					// 対象者入力に設定
					formBean.setUserType("1");
					// 対象者入力
					formBean.setUserDivisionR(formBean.getUserDivision());
				}
			} else{
				formBean.setUserType("0");				
			}
		}
	}
	
	/**
	 * 問題報告対応者リストを設定
	 * @param fromBean フォーム　
	 * @param userId ユーザID
	 */
	private void setPageCommentAdminList(PageFormBean formBean, String userId) {
		// ユーザ名称を取得（問題報告対応者）
		String strUserName = pageDao.getCreateUserName(userId);
		// 作成ユーザID
		formBean.setCreateBy(userId);
		// ユーザ名称がスペースではない場合、「コンテンツコメント管理者情報選択リスト、コンテンツコメント管理者情報リスト」にログインユーザを格納
		if (!StringUtil.isEmpty(strUserName)) {
			PageCommentAdminBean bean = new PageCommentAdminBean();
			List list = new ArrayList();
			// ユーザID
			bean.setUserId(userId);
			// ユーザ名称
			bean.setUserName(strUserName);
			list.add(bean);
			// コンテンツコメント管理者情報選択リストに格納
			formBean.setPageCommentAdminOptionList(list);
			// コンテンツコメント管理者情報リストに格納
			formBean.setPageCommentAdminList(list);
			// 作成名称
			formBean.setCreateUserName(strUserName);
		}
	}	
	
	/**
	 * コンテンツコメント管理者情報(問題報告対応者)相関処理
	 * @param fromBean フォーム　
	 */
	private void pageCommentAdminRelated(PageFormBean formBean) {
		// コンテンツコメント管理者情報選択(問題報告対応者)は画面からフォームに格納
		List pageCommentAdminOptionList = new ArrayList();
		String commentAdminOptionUserId = formBean.getCommentAdminOptionUserId();
		String commentAdminOptionUserName = formBean.getCommentAdminOptionUserName();
		// 画面の問題報告対応者選択リストに設定値があった場合
		if (StringUtil.isNotEmpty(commentAdminOptionUserId)) {
			String[] userId = commentAdminOptionUserId.split(",");
			String[] userName = commentAdminOptionUserName.split(",");
			for (int i = 0; i < userId.length; i ++) {
				PageCommentAdminBean bean = new PageCommentAdminBean();
				bean.setUserId(userId[i]);
				bean.setUserName(userName[i]);
				pageCommentAdminOptionList.add(bean);
			}			
		}
		// フォームに格納
		formBean.setPageCommentAdminOptionList(pageCommentAdminOptionList);
		
		// コンテンツコメント管理者情報(問題報告対応者)は画面からフォームに格納
		List pageCommentAdminList = new ArrayList();
		String commentAdminUserId = formBean.getCommentAdminUserId();
		String commentAdminUserName = formBean.getCommentAdminUserName();
		// 画面の問題報告対応者選択リストに設定値があった場合
		if (StringUtil.isNotEmpty(commentAdminUserId)) {
			String[] userId = commentAdminUserId.split(",");
			String[] userName = commentAdminUserName.split(",");
			for (int i = 0; i < userId.length; i ++) {
				PageCommentAdminBean bean = new PageCommentAdminBean();
				bean.setUserId(userId[i]);
				bean.setUserName(userName[i]);
				pageCommentAdminList.add(bean);
			}			
		}
		// フォームに格納
		formBean.setPageCommentAdminList(pageCommentAdminList);

	}
	
	/**
	 * 公開確認日付非更新フラグ(公開日付変更なし)表示フラグを設定
	 * @param fromBean フォーム　
	 * @throws Exception 
	 */
	private void setViewConfirmNoupdateFlag(PageFormBean formBean, PageBean pageBean) throws Exception {
		boolean viewNotFlag = false;
		formBean.setViewConfirmNoupdateFlag("0");
		// コンテンツ情報が存在した場合
		if (pageBean != null) {
			// 公開基準日がスペースの場合
			if (StringUtil.isEmpty(pageBean.getConfirmDate())){
				viewNotFlag = true;
			} else {
				viewNotFlag = false;				
			}
		} else {
			// コンテンツ情報が存在しない場合
			viewNotFlag = true;
		}
		if (!viewNotFlag){
			formBean.setViewConfirmNoupdateFlag("1");
		}
	}
	
	/**
	 * 実コンテンツ情報の取得(画面表示用)
	 * @param param　
	 * @return　
	 */
	public PageFormBean getPageForm(String pageId) throws Exception {
		
		PageFormBean formBean = null;

		// コンテンツ情報を取得
		PageBean pageBean = pageDao.getPage(pageId);
		if (pageBean != null) {
			formBean = new PageFormBean();
			WrappedBeanUtil.copyProperties(formBean, pageBean);
			
			// 動コンテンツ
			if (ConstantContainer.PAGE_DYNAMIC.equals(pageBean.getHtmlFlag())) {
				// コンテンツリンク情報を取得
				formBean.setPageLinkList(pageDao.getPageLinkList(pageId));
				
				// コンテンツ添付ファイル情報を取得
				formBean.setPageAttachmentList(pageDao.getPageAttachList(pageId));
			}
			
			// コンテンツ閲覧権限トップグループ情報を取得
			formBean.setAuthGroupList(pageDao.getAuthGroupList(pageId));
			
			// コンテンツ閲覧権限ユーザ情報を取得
			formBean.setAuthUserList(pageDao.getAuthUserList(pageId));
			
			// 更新代行者情報情報を取得
			formBean.setProxyUserList(pageDao.getProxyUserList(pageId));
			
			// コンテンツ評価リスト
			formBean.setPageRateItemBeanList(pageDao.getPageRateItemList(pageId));
			
			// コンテンツコメント管理者リスト
			formBean.setPageCommentAdminList(pageDao.getCommentAdminList(pageId));
			
			// コンテンツコメント管理者選択リスト
			formBean.setPageCommentAdminOptionList(pageDao.getCommentAdminOptList(pageId));
		}

		return formBean;
		
	}
	/**
	 * 予約コンテンツ情報の取得(画面表示用)
	 * @param param　
	 * @return　
	 */
	public PageFormBean getPageRsvForm(String pageId) throws Exception {
		
		PageFormBean formBean = null;

		// コンテンツ情報を取得
		PageBean pageBean = pageDao.getPageReserve(pageId);
		if (pageBean != null) {
			formBean = new PageFormBean();
			WrappedBeanUtil.copyProperties(formBean, pageBean);
			
			// 動コンテンツ
			if (ConstantContainer.PAGE_DYNAMIC.equals(pageBean.getHtmlFlag())) {
				// コンテンツリンク情報を取得
				formBean.setPageLinkList(pageDao.getPageLinkListRsv(pageId));
				
				// コンテンツ添付ファイル情報を取得
				formBean.setPageAttachmentList(pageDao.getPageAttachListRsv(pageId));
				
			}
			
			// コンテンツ閲覧権限トップグループ情報を取得
			formBean.setAuthGroupList(pageDao.getAuthGroupListRsv(pageId));
			
			// コンテンツ閲覧権限ユーザ情報を取得
			formBean.setAuthUserList(pageDao.getAuthUserListRsv(pageId));
			
			// 更新代行者情報情報を取得
			formBean.setProxyUserList(pageDao.getProxyUserListRsv(pageId));
			
			// コンテンツ評価リスト
			formBean.setPageRateItemBeanList(pageDao.getPageRateItemListRsv(pageId));
			
			// コンテンツコメント管理者リスト
			formBean.setPageCommentAdminList(pageDao.getCommentAdminListRsv(pageId));
			
			// コンテンツコメント管理者選択リスト
			formBean.setPageCommentAdminOptionList(pageDao.getCommentAdminOptListRsv(pageId));
		}

		return formBean;
	}
	
	
	/**
	 * ページIDにより、HashMap形式の公開するグループ結果を取得
	 * 呼び出し元
	 * A)子コンテンツ情報へ再設定
	 * 
	 * @param param　
	 * @return　
	 */
	private HashMap<String, String> getAuthGroupMap(String pageId, String reserveFlag) {
		
		HashMap<String, String> authGroupMap = new HashMap<String, String>();
		List<AuthGroupBean> authGroupList = null;
		if ("reserve".equals(reserveFlag)) {
			authGroupList = pageDao.getAuthGroupListRsv(pageId);
		} else {
			authGroupList = pageDao.getAuthGroupList(pageId);
		}
		for(int i=0; i<authGroupList.size(); i++) {
			AuthGroupBean bean = (AuthGroupBean)authGroupList.get(i);
			authGroupMap.put(bean.getTopGroupId(), "");
		}
		return authGroupMap;
	}
	
	/**
	 * ページIDにより、HashMap形式の公開する個人結果を取得
	 * 呼び出し元
	 * A)子コンテンツ情報へ再設定
	 * 
	 * @param param　
	 * @return　
	 */
	private HashMap<String, String> getAuthUserMap(String pageId, String reserveFlag) {
		
		HashMap<String, String> authUserMap = new HashMap<String, String>();
		List<AuthUserBean> authUserList = null;
		List<ProxyUserBean> proxyUserList = null;
		if ("reserve".equals(reserveFlag)) {
			authUserList = pageDao.getAuthUserListRsv(pageId);
			proxyUserList = pageDao.getProxyUserListRsv(pageId);
		} else {
			authUserList = pageDao.getAuthUserList(pageId);
			proxyUserList = pageDao.getProxyUserList(pageId);
		}
		for(int i=0; i<authUserList.size(); i++) {
			AuthUserBean bean = (AuthUserBean)authUserList.get(i);
			authUserMap.put(bean.getUserId(), "");
		}
		for(int i=0; i<proxyUserList.size(); i++) {
			ProxyUserBean bean = (ProxyUserBean)proxyUserList.get(i);
			authUserMap.put(bean.getProxyUserId(), "");
		}
		return authUserMap;
	}

	/**
	 * ページIDにより、HashMap形式の承認者結果を取得
	 * 呼び出し元
	 * A)子コンテンツ情報へ再設定
	 * 
	 * @param param　
	 * @return　
	 */
	private HashMap<String, String> getProxyUserMap(String pageId, String reserveFlag) {
		
		HashMap<String, String> proxyUserMap = new HashMap<String, String>();
		List<ProxyUserBean> proxyUserList = null;
		if ("reserve".equals(reserveFlag)) {
			proxyUserList = pageDao.getProxyUserListRsv(pageId);
		} else {
			proxyUserList = pageDao.getProxyUserList(pageId);
		}
		for(int i=0; i<proxyUserList.size(); i++) {
			ProxyUserBean bean = (ProxyUserBean)proxyUserList.get(i);
			proxyUserMap.put(bean.getProxyUserId(), "");
		}
		return proxyUserMap;
	}

	/**
	 * 実コンテンツ情報の取得
	 * @param param　
	 * @return　
	 */
	public PageBean getPageDB(String pageId) {
		return pageDao.getPage(pageId);
	}

	/**
	 * 予約コンテンツ情報の取得
	 * @param param　
	 * @return　
	 */
	public PageBean getPageRsvDB(String pageId) {
		return pageDao.getPageReserve(pageId);
	}

	/**
	 * ログインユーザーは該コンテンツの閲覧権限を持っているかどうか判断する
	 * @param param　
	 * @return　
	 */
	public boolean getViewRight(PageFormBean pageForm) {
		LoginUserBean loginUser = getLoginUserBean();
		
		//①システム利用区分が'1'(システム管理者)
		if ("1".equals(loginUser.getRole())) {
			return true;
		} 
		
		//②ログインユーザが該当コンテンツの公開する個人である　且つ　(該コンテンツ状態＝公開中 OR ログインユーザが該当コンテンツ作成者である)
		List userList = pageForm.getAuthUserList();
		if (userList != null) {
			for (int i = 0; i < userList.size(); i++) {
				AuthUserBean user = (AuthUserBean) userList.get(i);
				if (loginUser.getUserId().equals(user.getUserId())) {
					if ("0".equals(pageForm.getConfirmFlag()) || loginUser.getUserId().equals(pageForm.getCreateBy())) {
						return true;
					}
				}
			}
		}

		//③ログインユーザの所属トップグループが該当コンテンツの公開するトップグループである　且つ (該コンテンツ状態＝公開中 OR ログインユーザが該当コンテンツ作成者である)
		List groupList = pageForm.getAuthGroupList();
		if (groupList != null) {
			List loginGroupList = loginUser.getTopGroupList();

			if (loginGroupList != null) {
				for (int i = 0; i < groupList.size(); i++) {
					AuthGroupBean group = (AuthGroupBean) groupList.get(i);

					if (loginGroupList.contains(group.getTopGroupId())) {
						if ("0".equals(pageForm.getConfirmFlag()) || loginUser.getUserId().equals(pageForm.getCreateBy())) {
							return true;
						}
					} 
				}
			}
		}
		return false;
	}
	
	/**
	 * ログインユーザーは該コンテンツの更新権限を持っているかどうか判断する
	 * @param param　
	 * @return　
	 */
	public boolean getEditRight(PageFormBean pageForm) {
		LoginUserBean loginUser = getLoginUserBean();
		
		//①システム利用区分が'1'(システム管理者)
		if ("1".equals(loginUser.getRole())) {
			return true;
		} 
		
		//②ログインユーザが該当コンテンツ作成者である、且つ、システム利用区分がWEB担当者、WEBADMINである
		if (loginUser.getUserId().equals(pageForm.getCreateBy()) &&
				 ("4".equals(loginUser.getRole()) || "6".equals(loginUser.getRole()))) {
			return true;
		}
		
		//③ログインユーザが該当コンテンツの更新代行者である
		List proxyList = pageForm.getProxyUserList();
		if (proxyList != null) {
			for (int i = 0; i < proxyList.size(); i++) {
				ProxyUserBean proxy = (ProxyUserBean) proxyList.get(i);
				if (loginUser.getUserId().equals(proxy.getProxyUserId())) {
					return true;
				}
			}
		}

		return false;
	}
	
	/**
	 * ログインユーザーは該コンテンツの資料公開権限を持っているかどうか判断する
	 * @param param　
	 * @return　
	 */
	public boolean getOpenRight(PageFormBean pageForm) {
		LoginUserBean loginUser = getLoginUserBean();
		
		//該コンテンツ状態＝編集中
		if ("1".equals(pageForm.getConfirmFlag())) {
			//①システム利用区分が'1'(システム管理者)
			if ("1".equals(loginUser.getRole())) {
				return true;
			} 
			
			//②ログインユーザが該当コンテンツ作成者である、且つ、システム利用区分がWEBADMINである
			if (loginUser.getUserId().equals(pageForm.getCreateBy()) && "6".equals(loginUser.getRole())) {
				return true;
			}
			
			//③ログインユーザが該当コンテンツの更新代行者である
			List proxyList = pageForm.getProxyUserList();
			if (proxyList != null) {
				for (int i = 0; i < proxyList.size(); i++) {
					ProxyUserBean proxy = (ProxyUserBean) proxyList.get(i);
					if (loginUser.getUserId().equals(proxy.getProxyUserId()) && "6".equals(loginUser.getRole())) {
						return true;
					}
				}
			}
		}

		return false;
	}
	
	
	/**
	 * コンテンツDB更新処理前、O/RマッピングBeanを加工する
	 * @param param　
	 * @return　
	 */
	private void setPageInfo(PageFormBean pageDbOperBean) {
		//　「NULL」から「ゼロ」に変換　例えば、画面のチェックボックスが選択されない場合、「ゼロ」に変更
		convertFlagNullToZero(pageDbOperBean);
		
		// ファイルURL(ファイル名とアドレス)
		pageDbOperBean.setHtmlFileUrl(pageDbOperBean.getContextPath() + "/pageView_view.do");
		
		// 対象者←対象者入力
		if ("1".equals(pageDbOperBean.getUserType())) {
			pageDbOperBean.setUserDivision(pageDbOperBean.getUserDivisionR());
		}

		// コンテンツ評価フラグ:しない
		if ("0".equals(pageDbOperBean.getEvaluationFlag())) {
			// 評価者氏名表示フラグ
			pageDbOperBean.setEvaluatorDisplayFlag("0");
			// 評価者コメント編集可フラグ
			pageDbOperBean.setCommentEditFlag("0");
			// 複数評価有無フラグ
			pageDbOperBean.setPluralEvaluationFlag("0");
			// 評価をクリアするフラグ
			pageDbOperBean.setEvaluationClearFlag("0");
		}
	}
	
	/**
	 * コンテンツ情報を登録・更新
	 * @param pageForm フォームBean
	 * @throws Exception 
	 */
	public void insertOrUpdatePage(PageFormBean formBean) throws Exception {
		// フォームBeanからページDB更新Beanにコピー
		PageFormBean pageDbOperBean =  new PageFormBean();
		WrappedBeanUtil.copyProperties(pageDbOperBean, formBean);

		// コンテンツ情報設定
		setPageInfo(pageDbOperBean);

		// ページ配置先再設定　又は　テンプレートからの場合
		if ("1".equals(pageDbOperBean.getPageLocationSetFlag())
				|| "1".equals(pageDbOperBean.getIsTemplateFrom())){
			// ■■■　親コンテンツ直下該コンテンツ後ろのすべてコンテンツは表示順＋１に更新
			updateBrothersOrderBy(pageDbOperBean);
		}

		// コンテンツ情報登録の場合
		if ("ADD".equals(formBean.getPageViewFlag())){
			// 実コンテンツ情報の登録
			insertPage(pageDbOperBean);
		} else{
			// コンテンツ情報更新の場合
			// 「1：公開したまま編集」以外の場合
			if (!"1".equals(formBean.getOnEditFlag())){
				// 実コンテンツ情報の更新
				updatePage(pageDbOperBean);
			} else{
				// 「1：公開したまま編集」の場合
				// 予約コンテンツ情報の登録（削除してから登録処理を行う　）
				insertPageRsv(pageDbOperBean);
			}
		}
		
		// 文書添付をアップロード
		uploadFileRelated(pageDbOperBean);
		
		//■■■　コンテンツの配置先の設定
		formBean.setPageLocation(getPageLocation(formBean.getPageId()));

		// 「資料公開」、「メール送信」操作制御フラグ
		formBean.setOpenEnableFlag("1");

		// 実コンテンツ、予約コンテンツを判断
		String isReserve = "0";
		// 「1(したまま編集)」の場合、予約　それ以外には実コンテンツ
		if ("1".equals(formBean.getOnEditFlag())){
			isReserve = "1";
		}
		formBean.setIsReserve(isReserve);
		
		// 子コンテンツ反映ログ
		formBean.setUpdateChildLog(pageDbOperBean.getUpdateChildLog());
		
		// このコンテンツのURLを設定
		setPageUrl(formBean);

	}
	
	/**
	 * 文書添付をアップロード
	 * @param pageDbOperBean
	 * @throws Exception 
	 */
	private void uploadFileRelated(PageFormBean pageDbOperBean) throws Exception {
		try {
			// 動コンテンツ
			if (ConstantContainer.PAGE_DYNAMIC.equals(pageDbOperBean.getHtmlFlag())) {

				String filefrom = StringUtil.EMPTY;
				// HTMLファイル（予約）を削除
		        filefrom = FsPropertyUtil.getStringProperty("htmlFile.temp.path");
		        FileUtil.deletePurgeFile(filefrom, pageDbOperBean.getPageId());
		        //　「予約中」以外の場合
		        if (!"1".equals(pageDbOperBean.getUpdateradio())) {
		        	// アップロードファイル（予約）を削除
			        filefrom = FsPropertyUtil.getStringProperty("server.upload.temp.path");
			        FileUtil.deletePurgeFile(filefrom, pageDbOperBean.getPageId());
		        }
				
				// ファイルパス
				String filePath = "";
				String serverUploadPath = FsPropertyUtil.getStringProperty("server.upload.path");
				String serverUploadTempPath = FsPropertyUtil.getStringProperty("server.upload.temp.path");
				// 「1(公開したまま編集する)」の場合
				if("1".equals(pageDbOperBean.getOnEditFlag())) {
					filePath = serverUploadTempPath;
				} else {
					filePath = serverUploadPath;
				}
				
				// 画面にて削除されたファイルを削除
				List<PageAttachmentBean> fileList = pageDbOperBean.getPageAttachmentList();
				// コンテンツ更新の場合
				if ("EDIT".equals(pageDbOperBean.getPageViewFlag())){
					if (fileList != null) {
						String copyto = FsPropertyUtil.getStringProperty("batch.file");
						for (int i = 0; i < fileList.size(); i++) {
							PageAttachmentBean bean = (PageAttachmentBean)fileList.get(i);
							// 既存添付ファイル　且つ　画面にて削除された場合
							if (!StringUtil.isBlank(bean.getSequence())
									&& "1".equals(bean.getFileDeleteFlag())) {
								String fileUrl = bean.getAttachmentFileUrl();
								String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
								// 「したまま編集」以外の場合、画面にて削除されたファイルを削除
								if (!"1".equals(pageDbOperBean.getOnEditFlag())) {
									// 削除済み添付ファイル、パージのフォルダを移動する
									FileUtil.copyFile(serverUploadPath, copyto, fileName);
									FileUtil.deleteFile(serverUploadPath, fileName);
								} else {
									// 削除済み添付ファイルを削除
									FileUtil.deleteFile(serverUploadTempPath, fileName);
								}
							}
						}
					}					
				}

				// A.画面でアップロードファイルをアップロードする。
				// B.コンテンツファイルフォルダからコンテンツ予約フォルダにコピー
				// C.テンプレートコンテンツファイルフォルダからコンテンツフォルダにコピー
				if (fileList != null) {
					int count = 0;
					int index = 0;
					for (PageAttachmentBean bean : fileList) {
						if (StringUtil.isEmpty(bean.getSequence()) 
								&& !"1".equals(bean.getFileDeleteFlag())) {
							index = index + 1;
						}
					}

					for (PageAttachmentBean bean : fileList) {
						// A.画面でアップロードファイルをアップロードする。
						//既存添付ファイル以外、削除以外の場合
						if (StringUtil.isEmpty(bean.getSequence()) 
								&& !"1".equals(bean.getFileDeleteFlag())) {
							// 添付ファイルをアップロードする
							FileUtil.uploadFile(bean.getAttachment(), bean.getAttUploadFileName() , filePath);
							// 一時ファイルを削除する。
							if (bean.getAttachment().exists()){
								bean.getAttachment().delete();
							}
						}
						
						// テンプレートからの場合
						if ("1".equals(pageDbOperBean.getIsTemplateFrom())){
							// C.テンプレートコンテンツファイルフォルダからコンテンツフォルダにコピー
							if (!StringUtil.isEmpty(bean.getSequence())
									&& !"1".equals(bean.getFileDeleteFlag())){
								String fileUrl = bean.getAttachmentFileUrl();
								String suffix = fileUrl.substring(fileUrl.indexOf("."));
								String[] fileUrlArr = fileUrl.split("/");
								String folderurl = FsPropertyUtil.getStringProperty("attachmentFile.url");
								String toFileName = pageDbOperBean.getPageId() + (index + count) + suffix;
								count ++;
								String serverTemplateUploadPath = FsPropertyUtil.getStringProperty("template.server.upload.path") 
										+ File.separator + fileUrlArr[fileUrlArr.length-2];
								FileUtil.copyFile(serverTemplateUploadPath , serverUploadPath, fileUrlArr[fileUrlArr.length-1], toFileName);
								bean.setAttachmentFileUrl(folderurl + "/" + toFileName);
							}							
						} else{
							// B.コンテンツファイルフォルダからコンテンツ予約フォルダにコピー
							//既存添付ファイル、削除以外、「公開中」の場合
							if (!StringUtil.isEmpty(bean.getSequence()) 
									&& !"1".equals(bean.getFileDeleteFlag())
									&& ("0".equals(pageDbOperBean.getUpdateradio())
											|| (StringUtil.isEmpty(pageDbOperBean.getUpdateradio()) && "1".equals(pageDbOperBean.getOnEditFlag())))) {
								String fileUrl = bean.getAttachmentFileUrl();
								String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
								// 実フォルダにコピー
								FileUtil.copyFile(serverUploadPath, serverUploadTempPath, fileName);
							}							
						}
					}
				}
				// 動コンテンツのHTMLファイル作成
				createHtmlFile(pageDbOperBean);

			} else{
				String filefrom = StringUtil.EMPTY;
		        if (StringUtil.isEmpty(pageDbOperBean.getUpdateradio())) {
			        filefrom = FsPropertyUtil.getStringProperty("htmlFile.temp.path");
			        FileUtil.deletePurgeFile(filefrom, pageDbOperBean.getPageId());
		        }
				
				// 静的コンテンツの場合
				// 文書添付リスト（静的コンテンツは一つレコードのみ存在）
				List<PageAttachmentBean> fileList = pageDbOperBean.getPageAttachmentList();
				boolean fileChangeFlag = false;
				// 添付ファイルがあった場合
				if (fileList != null && !StringUtil.isEmpty(fileList.get(0).getAttachmentFileName())) {
					fileChangeFlag = true;
				} else{
					// 「予約中」以外の場合
					if (!"1".equals(pageDbOperBean.getUpdateradio())){
						fileChangeFlag = true;
					}
					// テンプレートからの場合
					if ("1".equals(pageDbOperBean.getIsTemplateFrom())){
						fileChangeFlag = true;
					}
				}

				// ファイルが変わった場合
				if (fileChangeFlag) {
					// 静的コンテンツ一時保存パス
					String tempStaticFilePath = StringUtil.EMPTY;
					// ファイルパス
					String htmlFilePath = "";
					// 「1(公開したまま編集する)」の場合
					if("1".equals(pageDbOperBean.getOnEditFlag())) {
						tempStaticFilePath = FsPropertyUtil.getStringProperty("temp.temp.path");
						htmlFilePath = FsPropertyUtil.getStringProperty("htmlFile.temp.path");
					} else {
						tempStaticFilePath = FsPropertyUtil.getStringProperty("temp.path");
						htmlFilePath = FsPropertyUtil.getStringProperty("htmlFile.path");
					}
					
					// 一時格納フォルダを削除
					FileUtil.deletePurgeFile(htmlFilePath, pageDbOperBean.getPageId());
					
					String fileSuffix = StringUtil.nullToBlank(pageDbOperBean.getFileSuffix());
					String fileName = "";
					if (CommonUtil.checkStaticFileSuffix(pageDbOperBean.getPageId(), fileSuffix)) {
						fileName = pageDbOperBean.getPageId();
					} else {
						fileName = pageDbOperBean.getPageId() + fileSuffix;
					}
					FileUtil.fileCopyForStatic(tempStaticFilePath, htmlFilePath, fileName, fileName);

					// 一時格納フォルダを削除
					FileUtil.deletePurgeFile(tempStaticFilePath, pageDbOperBean.getPageId());
				}
			}
		} catch (Exception e) {
			// ファイルサーバー
			String fileSever = FsPropertyUtil.getStringProperty("file.server");
			// サーバに接続できない場合、エラー
			if (!FileUtil.ping(fileSever)){
				FileUtil.ServerAccessException();
			} else {
				throw e;
			}
		}
	}
	
	/**
	 * 実コンテンツ情報の登録
	 * @param pageDbOperBean　
	 */
	private void insertPage(PageFormBean pageDbOperBean) throws Exception {
		
		//作成情報取得
		BaseBean createInfo = null;

		//■■■　コンテンツ情報登録処理
		PageBean page = new PageBean();
		//画面入力された値はBeanに格納
		WrappedBeanUtil.copyProperties(page, pageDbOperBean);

		//作成ユーザID
		page.setCreateBy(getLoginUserBean().getUserId());
		//作成日時
		page.setCreateDate(new Date());
		//更新ユーザID
		page.setUpdateBy(getLoginUserBean().getUserId());
		//更新日時
		page.setUpdateDate(new Date());

		//コンテンツ情報登録
		pageDao.insertPage(page);
		
		//■■■　子コンテンツへ情報再設定
		updateChildPage(pageDbOperBean, createInfo);
		
		//動コンテンツ
		if (ConstantContainer.PAGE_DYNAMIC.equals(pageDbOperBean.getHtmlFlag())) {
			//■■■　リンク情報の処理
			insertPageLink(pageDbOperBean, createInfo, "add");

			//■■■　添付文書情報の処理
			insertPageAttach(pageDbOperBean, createInfo, "add");
		}

		//■■■　公開するグループ情報の処理
		insertAuthGroup(pageDbOperBean, createInfo, "add");

		//■■■　公開する個人情報の処理
		insertAuthUser(pageDbOperBean, createInfo, "add");
		
		//■■■　更新代行者情報の処理
		insertProxyUser(pageDbOperBean, createInfo, "add");
		
		// コンテンツ評価する：チェックする
		if ("1".equals(pageDbOperBean.getEvaluationFlag())) {
			
			//■■■　評価アイテムの処理
			insertPageRateItem(pageDbOperBean, createInfo, "add");
			
			//■■■　問題報告対応者の処理
			insertPageCommentAdmin(pageDbOperBean, createInfo, "add");
		}
	}
	
	/**
	 * 予約コンテンツ情報の登録（削除してから登録処理を行う　）
	 * @param param　
	 * @return　
	 */
	private void insertPageRsv(PageFormBean pageDbOperBean) throws Exception {
		
		//■■■　コンテンツ予約情報登録処理
		PageBean page = new PageBean();
		//画面入力された値はBeanに格納
		WrappedBeanUtil.copyProperties(page, pageDbOperBean);
		//更新者
		page.setUpdateBy(getLoginUserBean().getUserId());
		
		//コンテンツ情報の更新
		//■■■　真実コンテンツの公開維持編集フラグ「ON_EDIT_FLAG」を1(公開したまま編集となる)に更新
		int ret = pageDao.updateOnEditFlag(page);
		if (ret == 0){
			// 排他チェック
			optimisticLockCheck(page.getPageId(), "0");
		}

		//作成情報取得
		BaseBean createInfo = commonDao.getDbCommonInfo("page", "page_id", pageDbOperBean.getPageId());

		//■■■　予約コンテンツ削除
		deletePageRsv(pageDbOperBean);

		//真実データの「初回公開基準日」を予約データに設定
		PageBean pagetemp = pageDao.getPage(pageDbOperBean.getPageId());
		page.setFirstConfirmDate(pagetemp.getFirstConfirmDate());
		
		//作成ユーザIDと作成日時
		page.setCreateBy(createInfo.getCreateBy());
		page.setCreateDate(createInfo.getCreateDate());
		
		//更新ユーザIDと更新日時
		page.setUpdateBy(getLoginUserBean().getUserId());
		page.setUpdateDate(new Date());
		
		//コンテンツ予約情報登録
		pageDao.insertPageRsv(page);
		
		//■■■　子コンテンツへ情報再設定
		updateChildPage(pageDbOperBean, createInfo);
		
		//動コンテンツ
		if (ConstantContainer.PAGE_DYNAMIC.equals(pageDbOperBean.getHtmlFlag())) {
			//■■■　リンク情報の処理
			insertPageLink(pageDbOperBean, createInfo, "update");

			//■■■　添付文書情報の処理
			insertPageAttach(pageDbOperBean, createInfo, "update");
		}

		//■■■　公開するグループ情報の処理
		insertAuthGroup(pageDbOperBean, createInfo, "update");

		//■■■　公開する個人情報の処理
		insertAuthUser(pageDbOperBean, createInfo, "update");
		
		//■■■　更新代行者情報の処理
		insertProxyUser(pageDbOperBean, createInfo, "update");

		// コンテンツ評価する：チェックする
		if ("1".equals(pageDbOperBean.getEvaluationFlag())) {
			
			//■■■　評価アイテムの処理
			insertPageRateItem(pageDbOperBean, createInfo, "update");
			
			//■■■　問題報告対応者の処理
			insertPageCommentAdmin(pageDbOperBean, createInfo, "update");
		}
	}

	/**
	 * 実コンテンツ情報の更新
	 * @param param　
	 * @return　
	 */
	private void updatePage(PageFormBean pageDbOperBean) throws Exception {
		//■■■　コンテンツ情報の更新
		PageBean page = new PageBean();
		//画面入力された値はBeanに格納
		WrappedBeanUtil.copyProperties(page, pageDbOperBean);

		//編集中
		page.setConfirmFlag("1");
		
		//削除済みのデータは復活
		page.setDeleteFlag("0");
		
		//更新者
		page.setUpdateBy(getLoginUserBean().getUserId());

		//コンテンツ情報の更新
		int ret = pageDao.updatePage(page);
		if (ret == 0){
			// 排他チェック
			optimisticLockCheck(page.getPageId(), "0");
		}

		//作成情報取得
		BaseBean createInfo = commonDao.getDbCommonInfo("page", "page_id", pageDbOperBean.getPageId());       

		//■■■　予約コンテンツ削除、以下の二つケースは該メソッドを呼び出すことができる
		//A）真実コンテンツ編集、状態＝作成中
		//B）真実コンテンツ編集、状態＝公開中、公開したまま編集を選択しない場合（このケースは予約TBLを削除する要）
		if("0".equals(pageDbOperBean.getOnEditFlag())) {
			deletePageRsv(pageDbOperBean);
		}

		//■■■　公開日付変更なしチェックON時、ログテールを制御しない
		if("0".equals(pageDbOperBean.getConfirmNoupdateFlag())){
			pageDao.deleteLogInfo(pageDbOperBean.getPageId(), getLoginUserBean().getUserId());
		}
		
		//■■■　子コンテンツへ情報再設定
		updateChildPage(pageDbOperBean, createInfo);
		
		//動コンテンツ
		if (ConstantContainer.PAGE_DYNAMIC.equals(pageDbOperBean.getHtmlFlag())) {
			//■■■　リンク情報の処理
			pageDao.deletePageExtend(pageDbOperBean.getPageId(), "PAGE_LINK");
			insertPageLink(pageDbOperBean, createInfo, "update");

			//■■■　添付文書情報の処理
			List fileList = pageDbOperBean.getPageAttachmentList();
			if (fileList != null) {
				for (int i = 0; i < fileList.size(); i++) {
					PageAttachmentBean bean = (PageAttachmentBean)fileList.get(i);
					bean.setPageId(pageDbOperBean.getPageId());
					bean.setOrderBy(String.valueOf(i));
					//既存添付ファイルの場合
					if (!StringUtil.isBlank(bean.getSequence())) {
						bean.setUpdateBy(getLoginUserBean().getUserId());
						//既存ファイル削除の場合
						if ("1".equals(bean.getFileDeleteFlag())) {
							pageDao.deletePageAttachment(bean);
						//既存ファイル更新の場合
						} else {
							pageDao.updatePageAttachment(bean);
						}
					//新規作成の添付ファイルの場合
					} else {
						if (!"1".equals(bean.getFileDeleteFlag())) {
							setCreateInfo(bean, createInfo, "update");
							pageDao.insertPageAttachment(bean);
						}
					}
				}
			}
		}

		//■■■　公開するグループ情報の処理
		pageDao.deletePageExtend(pageDbOperBean.getPageId(), "AUTH_LEADING_GROUP");
		insertAuthGroup(pageDbOperBean, createInfo, "update");

		//■■■　公開する個人情報の処理
		pageDao.deletePageExtend(pageDbOperBean.getPageId(), "AUTH_USER");
		insertAuthUser(pageDbOperBean, createInfo, "update");
		
		//■■■　更新代行者情報の処理
		pageDao.deletePageExtend(pageDbOperBean.getPageId(), "UPDATE_PROXY_USER");
		insertProxyUser(pageDbOperBean, createInfo, "update");

		// 「コンテンツ評価する」が「する」の場合
		if ("1".equals(pageDbOperBean.getEvaluationFlag())) {
			// コンテンツ評価アイテム情報に関してのDB操作
			doPageRateItemDbOper(pageDbOperBean, createInfo, "update");
			
			//■■■　問題報告対応者の処理
			pageDao.deletePageExtend(pageDbOperBean.getPageId(), "PAGE_COMMENT_ADMIN");
			insertPageCommentAdmin(pageDbOperBean, createInfo, "update");

		}
	}
	
	/**
	 * 子コンテンツへ情報再設定（公開する個人、公開するグループ、承認者）
	 * @param param　
	 * @return　
	 */
	private void updateChildPage(PageFormBean pageForm, BaseBean createInfo) throws Exception {
		
		ChildPageFormBean childForm = pageForm.getChildPageFormBean();
		//■■■ 1．子コンテンツへ情報再設定の情報はセットされていない場合、何もしない
		if (StringUtil.isBlank(childForm)) {
			return;
		}
		List<String> logList = new ArrayList<String>();	
		
		//■■■ 2．DBから全ての子コンテンツ取得
		HashMap<String, PageBean> childByDbMap = new HashMap<String, PageBean>();
		getAllOpenChildPageList(pageForm.getPageId(), childByDbMap);			
		
		//■■■ 3．画面の子コンテンツを繰り返して情報反映
		List<ChildPageInheritSetBean> childByFormList = childForm.getInheritSetList();
		for(int j=0; j<childByFormList.size(); j++) {
			ChildPageInheritSetBean childFormBean = (ChildPageInheritSetBean)childByFormList.get(j);
			String pageId = childFormBean.getPageId();
			//コンテンツ区分(0:真実のみ、1:予約のみ、2:両方)-----子コンテンツ情報取得で使用
			String subDivFlag = childFormBean.getPageDivision();

			//●●●　3.1　画面入力されたコンテンツはDBに存在しない場合、次のレコードへ
			if (!childByDbMap.containsKey(pageId + "," + subDivFlag)) {
				continue;
			} 
			
			//●●●　3.2　グループ情報の更新
			if ("1".equals(childFormBean.getIsGroupInherit())) {
				
				HashMap<String, String> groupMap = new HashMap<String, String>();
				HashMap<String, String> groupRsvMap = new HashMap<String, String>();
				
				//▲▲▲　3.2.1　グループ継承種類＝置き換え
				if ("COVER".equals(childForm.getGroupInheritType())) {
					// 追加 実のみと両方を存在するの場合、真実テーブルを削除する
					if ("0".equals(subDivFlag) || "2".equals(subDivFlag)) {
						pageDao.deletePageExtend(pageId, "AUTH_LEADING_GROUP");
					}

					// 追加 予約のみと両方を存在するの場合、予約テーブルを削除する
					if ("1".equals(subDivFlag) || "2".equals(subDivFlag)) {
						pageDao.deletePageExtend(pageId, "AUTH_LEADING_GROUP_RESERVE");
					}
				}
				//▲▲▲　3.2.2　グループ継承種類＝追加
				if ("ADD".equals(childForm.getGroupInheritType())) {
					//子コンテンツの公開するグループ情報を退避
					groupMap = getAuthGroupMap(pageId, "");
					groupRsvMap = getAuthGroupMap(pageId, "reserve");
				}

				//▲▲▲　3.2.3　グループ情報を繰り返して子コンテンツへ反映
				for (int i = 0; i < childForm.getAuthGroupList().size(); i++) {
					AuthGroupBean bean = (AuthGroupBean)childForm.getAuthGroupList().get(i);
					String delete = bean.getGroupDeleteFlag();
					String id = bean.getTopGroupId();
					
					bean.setPageId(pageId);
					
					if (!"1".equals(delete) && !StringUtil.isEmpty(id)) {
						// 追加 実のみと両方を存在するの場合、真実テーブルを追加する
						if ("0".equals(subDivFlag) || "2".equals(subDivFlag)) {
							
							if (groupMap.containsKey(id)) {
								setCreateInfo(bean, createInfo, "update");
							} else {
								setCreateInfo(bean, createInfo, "add");
							}
							//削除してから登録を行う
							pageDao.deleteAuthGroup(bean);
							pageDao.insertAuthGroup(bean);
							logList.add(pageId);
						}
						
						// 追加 予約のみと両方を存在するの場合、予約テーブルを追加する
						if ("1".equals(subDivFlag) || "2".equals(subDivFlag)) {
							if (groupRsvMap.containsKey(id)) {
								setCreateInfo(bean, createInfo, "update");
							} else {
								setCreateInfo(bean, createInfo, "add");
							}
							//削除してから登録を行う
							pageDao.deleteAuthGroupRsv(bean);
							pageDao.insertAuthGroupRsv(bean);
							logList.add("※" + pageId);
						}
					}
				}
			}
			
			//●●●　3.3　公開する個人情報の更新
			if ("1".equals(childFormBean.getIsUserInherit())) {
				
				HashMap<String, String> userMap = new HashMap<String, String>();
				HashMap<String, String> userRsvMap = new HashMap<String, String>();
				
				//▲▲▲　3.3.1　継承種類＝置き換え
				if ("COVER".equals(childForm.getUserInheritType())) {
					// 追加 実のみと両方を存在するの場合、真実テーブルを削除する
					if ("0".equals(subDivFlag) || "2".equals(subDivFlag)) {
						pageDao.deletePageExtend(pageId, "AUTH_USER");
					}
					
					// 追加 予約のみと両方を存在するの場合、予約テーブルを削除する
					if ("1".equals(subDivFlag) || "2".equals(subDivFlag)) {
						pageDao.deletePageExtend(pageId, "AUTH_USER_RESERVE");
					}
				}
				//▲▲▲　3.3.2　継承種類＝追加
				if ("ADD".equals(childForm.getUserInheritType())) {
					//子コンテンツの公開する個人情報を退避
					userMap = getAuthUserMap(pageId, "");
					userRsvMap = getAuthUserMap(pageId, "reserve");
				}

				//▲▲▲　3.3.3　ユーザー情報を繰り返して子コンテンツへ反映
				for (int i = 0; i < childForm.getAuthUserList().size(); i++) {
					AuthUserBean bean = (AuthUserBean)childForm.getAuthUserList().get(i);
					String delete = bean.getUserDeleteFlag();
					String id = bean.getUserId();
					
					bean.setPageId(pageId);
					
					if (!"1".equals(delete) && !StringUtil.isEmpty(id)) {
						// 追加 実のみと両方を存在するの場合、真実テーブルを追加する
						if ("0".equals(subDivFlag) || "2".equals(subDivFlag)) {
							
							if (userMap.containsKey(id)) {
								setCreateInfo(bean, createInfo, "update");
							} else {
								setCreateInfo(bean, createInfo, "add");
							}
							//削除してから登録を行う
							pageDao.deleteAuthUser(bean);
							pageDao.insertAuthUser(bean);
							userMap.put(id, "");
							logList.add(pageId);
						}
						
						// 追加 予約のみと両方を存在するの場合、予約テーブルを追加する
						if ("1".equals(subDivFlag) || "2".equals(subDivFlag)) {
							if (userRsvMap.containsKey(id)) {
								setCreateInfo(bean, createInfo, "update");
							} else {
								setCreateInfo(bean, createInfo, "add");
							}
							//削除してから登録を行う
							pageDao.deleteAuthUserRsv(bean);
							pageDao.insertAuthUserRsv(bean);
							userRsvMap.put(id, "");
							logList.add("※" + pageId);
						}
					}
				}
				
				//▲▲▲　3.3.4　承認者も子コンテンツの公開する個人へ反映
				for (int i = 0; i < childForm.getProxyUserList().size(); i++) {
					ProxyUserBean bean = (ProxyUserBean)childForm.getProxyUserList().get(i);
					String delete = bean.getProxyDeleteFlag();
					String id = bean.getProxyUserId();
					
					if (!"1".equals(delete) && !StringUtil.isEmpty(id)) {
						// 追加 実のみと両方を存在するの場合、真実テーブルを追加する
						if ("0".equals(subDivFlag) || "2".equals(subDivFlag)) {
							
							if (!userMap.containsKey(id)) {
								AuthUserBean user = new AuthUserBean();
								user.setPageId(pageId);
								user.setUserId(id);
								user.setNecessityReadFlag("0");
								
								setCreateInfo(user, createInfo, "update");
								//削除してから登録を行う
								pageDao.deleteAuthUser(user);
								pageDao.insertAuthUser(user);
								userMap.put(id, "");
								logList.add(pageId);
							}
						}
						
						// 追加 予約のみと両方を存在するの場合、予約テーブルを追加する
						if ("1".equals(subDivFlag) || "2".equals(subDivFlag)) {
							if (!userRsvMap.containsKey(id)) {
								AuthUserBean user = new AuthUserBean();
								user.setPageId(pageId);
								user.setUserId(id);
								user.setNecessityReadFlag("0");
								
								setCreateInfo(user, createInfo, "update");
								//削除してから登録を行う
								pageDao.deleteAuthUserRsv(user);
								pageDao.insertAuthUserRsv(user);
								userRsvMap.put(id, "");
								logList.add("※" + pageId);
							}
						}
					}
				}
				//▲▲▲　3.3.5　ログインユーザーも子コンテンツの公開する個人へ反映
				// 追加 実のみと両方を存在するの場合、真実テーブルを追加する
				if ("0".equals(subDivFlag) || "2".equals(subDivFlag)) {
					
					if (!userMap.containsKey(getLoginUserBean().getUserId())) {
						AuthUserBean user = new AuthUserBean();
						user.setPageId(pageId);
						user.setUserId(getLoginUserBean().getUserId());
						user.setNecessityReadFlag("0");
						
						setCreateInfo(user, createInfo, "update");
						//削除してから登録を行う
						pageDao.deleteAuthUser(user);
						pageDao.insertAuthUser(user);
						logList.add(pageId);
					}
				}
				
				// 追加 予約のみと両方を存在するの場合、予約テーブルを追加する
				if ("1".equals(subDivFlag) || "2".equals(subDivFlag)) {
					if (!userRsvMap.containsKey(getLoginUserBean().getUserId())) {
						AuthUserBean user = new AuthUserBean();
						user.setPageId(pageId);
						user.setUserId(getLoginUserBean().getUserId());
						user.setNecessityReadFlag("0");
						
						setCreateInfo(user, createInfo, "update");
						//削除してから登録を行う
						pageDao.deleteAuthUserRsv(user);
						pageDao.insertAuthUserRsv(user);
						logList.add("※" + pageId);
					}
				}
			}

			//●●● 3.4 承認者情報の更新
			if ("1".equals(childFormBean.getIsProxyInherit())) {
				
				HashMap<String, String> proxyMap = new HashMap<String, String>();
				HashMap<String, String> proxyRsvMap = new HashMap<String, String>();
				
				//▲▲▲　3.2.1　グループ継承種類＝置き換え
				if ("COVER".equals(childForm.getProxyInheritType())) {
					// 追加 実のみと両方を存在するの場合、真実テーブルを削除する
					if ("0".equals(subDivFlag) || "2".equals(subDivFlag)) {
						pageDao.deletePageExtend(pageId, "UPDATE_PROXY_USER");
					}
					
					// 追加 予約のみと両方を存在するの場合、予約テーブルを削除する
					if ("1".equals(subDivFlag) || "2".equals(subDivFlag)) {
						pageDao.deletePageExtend(pageId, "UPDATE_PROXY_USER_RESERVE");
					}
				}
				//▲▲▲　3.2.2　グループ継承種類＝追加
				if ("ADD".equals(childForm.getProxyInheritType())) {
					//子コンテンツの公開するグループ情報を退避
					proxyMap = getProxyUserMap(pageId, "");
					proxyRsvMap = getProxyUserMap(pageId, "reserve");
				}

				//▲▲▲　3.2.3　承認者情報を繰り返して子コンテンツへ反映
				for (int i = 0; i < childForm.getProxyUserList().size(); i++) {
					ProxyUserBean bean = (ProxyUserBean)childForm.getProxyUserList().get(i);
					String delete = bean.getProxyDeleteFlag();
					String id = bean.getProxyUserId();
					
					bean.setPageId(pageId);
					
					if (!"1".equals(delete) && !StringUtil.isEmpty(id)) {
						// 追加 実のみと両方を存在するの場合、真実テーブルを追加する
						if ("0".equals(subDivFlag) || "2".equals(subDivFlag)) {
							
							if (proxyMap.containsKey(id)) {
								setCreateInfo(bean, createInfo, "update");
							} else {
								setCreateInfo(bean, createInfo, "add");
							}
							//削除してから登録を行う
							pageDao.deleteProxyUser(bean);
							pageDao.insertProxyUser(bean);
							logList.add(pageId);
						}
						
						// 追加 予約のみと両方を存在するの場合、予約テーブルを追加する
						if ("1".equals(subDivFlag) || "2".equals(subDivFlag)) {
							if (proxyRsvMap.containsKey(id)) {
								setCreateInfo(bean, createInfo, "update");
							} else {
								setCreateInfo(bean, createInfo, "add");
							}
							//削除してから登録を行う
							pageDao.deleteProxyUserRsv(bean);
							pageDao.insertProxyUserRsv(bean);
							logList.add("※" + pageId);
						}
					}
				}
			}
		}
		pageForm.setUpdateChildLog(logList);
	}
	
	/**
	 * 添付文書情報のDB更新処理
	 * 呼び出し元
	 * A)真実コンテンツ登録処理
	 * B)予約コンテンツ登録処理
	 * 
	 * @param param　
	 * @return　
	 */
	private void insertPageAttach(PageFormBean pageForm, BaseBean createInfo, String updateDiv) {
		if (ConstantContainer.PAGE_DYNAMIC.equals(pageForm.getHtmlFlag())) {
			List fileList = pageForm.getPageAttachmentList();
			if (fileList != null) {
				int count = 0;
				int index = 0;
				for (int i = 0; i < fileList.size(); i++) {
					PageAttachmentBean bean = (PageAttachmentBean)fileList.get(i);
					if (StringUtil.isEmpty(bean.getSequence()) 
							&& !"1".equals(bean.getFileDeleteFlag())) {
						index = index + 1;
					}
				}
				for (int i = 0; i < fileList.size(); i++) {
					PageAttachmentBean bean = (PageAttachmentBean)fileList.get(i);
					if (!"1".equals(bean.getFileDeleteFlag())) {
						bean.setOrderBy(String.valueOf(i));
						bean.setPageId(pageForm.getPageId());
						setCreateInfo(bean, createInfo, updateDiv);
						
						String url = bean.getAttachmentFileUrl();
						// テンプレートからの場合
						if ("1".equals(pageForm.getIsTemplateFrom())){
							if (!StringUtil.isBlank(bean.getSequence())) {
								String suffix = url.substring(url.indexOf("."));
								String folderurl = FsPropertyUtil.getStringProperty("attachmentFile.url");
								String fullUrl = folderurl + "/" + pageForm.getPageId() + (index + count) + suffix;
								count ++;
								bean.setAttachmentFileUrl(fullUrl);
							}							
						}

						//公開したまま編集の場合、予約データ作成
						if("1".equals(pageForm.getOnEditFlag())) {
							pageDao.insertPageAttachmentRsv(bean);
						} else {
							pageDao.insertPageAttachment(bean);						
						}
						bean.setAttachmentFileUrl(url);
					}
				}
			}
		}		
	}
	
	/**
	 * コンテンツリンク情報のDB更新処理
	 * 呼び出し元
	 * A)真実コンテンツ登録処理
	 * B)真実コンテンツ更新処理
	 * C)予約コンテンツ登録処理
	 * 
	 * @param param　
	 * @return　
	 */
	private void insertPageLink(PageFormBean pageForm, BaseBean createInfo, String updateDiv) {
		if (pageForm.getPageLinkList() != null) {
			for (int i = 0; i < pageForm.getPageLinkList().size(); i++) {
				PageLinkBean bean = (PageLinkBean)pageForm.getPageLinkList().get(i);
				String linkDelete = bean.getLinkDeleteFlag();
				String linkName = bean.getLinkName();
				if (!"1".equals(linkDelete) && !StringUtil.isEmpty(linkName)) {
					bean.setPageId(pageForm.getPageId());
					setCreateInfo(bean, createInfo, updateDiv);

					//公開したまま編集の場合、予約データ作成
					if("1".equals(pageForm.getOnEditFlag())) {
						pageDao.insertPageLinkRsv(bean);
					} else {
						pageDao.insertPageLink(bean);
					}
				}
			}
		}
	}
	
	/**
	 * コンテンツ公開するグループのDB更新処理
	 * 呼び出し元
	 * A)真実コンテンツ登録処理
	 * B)真実コンテンツ更新処理
	 * C)予約コンテンツ登録処理
	 * 
	 * @param param　
	 * @return　
	 */
	private void insertAuthGroup(PageFormBean pageForm, BaseBean createInfo, String updateDiv) {
		if (pageForm.getAuthGroupList() != null) {
			for (int i = 0; i < pageForm.getAuthGroupList().size(); i++) {
				AuthGroupBean bean = (AuthGroupBean)pageForm.getAuthGroupList().get(i);
				String delete = bean.getGroupDeleteFlag();
				String id = bean.getTopGroupId();
				if (!"1".equals(delete) && !StringUtil.isEmpty(id)) {
					
					bean.setPageId(pageForm.getPageId());
					// 必須閲覧区分
					bean.setNecessityReadFlag(StringUtil.nullToZero(bean.getNecessityReadFlag()));
					setCreateInfo(bean, createInfo, updateDiv);

					//公開したまま編集の場合、予約データ作成
					if("1".equals(pageForm.getOnEditFlag())) {
						pageDao.insertAuthGroupRsv(bean);
					} else {
						pageDao.insertAuthGroup(bean);
					}
				}
			}
		}
	}

	/**
	 * コンテンツ公開する個人のDB更新処理
	 * 呼び出し元
	 * A)真実コンテンツ登録処理
	 * B)真実コンテンツ更新処理
	 * C)予約コンテンツ登録処理
	 * 
	 * @param param　
	 * @return　
	 */
	private void insertAuthUser(PageFormBean pageForm, BaseBean createInfo, String updateDiv) {
		//ログインユーザー
		String loginUserID = getLoginUserBean().getUserId();
		List userIdList = new ArrayList();
		if (pageForm.getAuthUserList() != null) {
			for (int i = 0; i < pageForm.getAuthUserList().size(); i++) {
				AuthUserBean bean = (AuthUserBean)pageForm.getAuthUserList().get(i);
				String delete = bean.getUserDeleteFlag();
				String userId = bean.getUserId();
				if (!"1".equals(delete) && !StringUtil.isEmpty(userId)) {
					
					bean.setPageId(pageForm.getPageId());
					// 必須閲覧区分
					bean.setNecessityReadFlag(StringUtil.nullToZero(bean.getNecessityReadFlag()));
					setCreateInfo(bean, createInfo, updateDiv);

					userIdList.add(userId);
					//公開したまま編集の場合、予約データ作成
					if("1".equals(pageForm.getOnEditFlag())) {
						pageDao.insertAuthUserRsv(bean);
					} else {
						pageDao.insertAuthUser(bean);
					}
				}
			}
		}
		
		//更新代行者も閲覧ユーザーリストに格納
		if (pageForm.getProxyUserList() != null) {
			for (int i = 0; i < pageForm.getProxyUserList().size(); i++) {
				ProxyUserBean bean = (ProxyUserBean)pageForm.getProxyUserList().get(i);
				String delete = bean.getProxyDeleteFlag();
				String id = bean.getProxyUserId();
				if (!"1".equals(delete) && !StringUtil.isEmpty(id) && !userIdList.contains(id) ) {
					
					AuthUserBean user = new AuthUserBean();
					user.setNecessityReadFlag("0");
					user.setUserId(id);
					user.setPageId(pageForm.getPageId());
					setCreateInfo(user, createInfo, updateDiv);

					userIdList.add(id);
					//公開したまま編集の場合、予約データ作成
					if("1".equals(pageForm.getOnEditFlag())) {
						pageDao.insertAuthUserRsv(user);
					} else {
						pageDao.insertAuthUser(user);
					}
				}
			}
		}
		
		//コンテンツ作成者　OR　更新者も閲覧ユーザーリストに格納
		if (!userIdList.contains(loginUserID)) {
			AuthUserBean user = new AuthUserBean();
			user.setNecessityReadFlag("0");
			user.setUserId(loginUserID);
			user.setPageId(pageForm.getPageId());
			setCreateInfo(user, createInfo, updateDiv);
			
			//公開したまま編集の場合、予約データ作成
			if("1".equals(pageForm.getOnEditFlag())) {
				pageDao.insertAuthUserRsv(user);
			} else {
				pageDao.insertAuthUser(user);
			}
		}
	}

	/**
	 * コンテンツ承認者のDB更新処理
	 * 呼び出し元
	 * A)真実コンテンツ登録処理
	 * B)真実コンテンツ更新処理
	 * C)予約コンテンツ登録処理
	 * 
	 * @param param　
	 * @return　
	 */
	private void insertProxyUser(PageFormBean pageForm, BaseBean createInfo, String updateDiv) {
		if (pageForm.getProxyUserList() != null) {
			for (int i = 0; i < pageForm.getProxyUserList().size(); i++) {
				ProxyUserBean bean = (ProxyUserBean)pageForm.getProxyUserList().get(i);
				String delete = bean.getProxyDeleteFlag();
				String id = bean.getProxyUserId();
				if (!"1".equals(delete) && !StringUtil.isEmpty(id)) {
					
					bean.setPageId(pageForm.getPageId());
					setCreateInfo(bean, createInfo, updateDiv);

					//公開したまま編集の場合、予約データ作成
					if("1".equals(pageForm.getOnEditFlag())) {
						pageDao.insertProxyUserRsv(bean);
					} else {
						pageDao.insertProxyUser(bean);
					}
				}
			}
		}
	}

	/**
	 * コンテンツ評価アイテムのDB更新処理
	 * 呼び出し元
	 * A)真実コンテンツ登録処理
	 * B)予約コンテンツ登録処理
	 * 
	 * @param param　
	 * @return　
	 */
	private void insertPageRateItem(PageFormBean pageForm, BaseBean createInfo, String updateDiv) {
		List pageRateItemList = pageForm.getPageRateItemBeanList();
		if (pageRateItemList != null) {
			for (int i = 0; i < pageRateItemList.size(); i++) {
				PageRateItemBean bean = (PageRateItemBean)pageRateItemList.get(i);
				bean.setPageId(pageForm.getPageId());
				bean.setEvaluationOrderBy((i + 1) + "");
				
				setCreateInfo(bean, createInfo, updateDiv);

				//公開したまま編集の場合、予約データ作成
				if("1".equals(pageForm.getOnEditFlag())) {
					if(StringUtil.isEmpty(bean.getSequence())) {
						bean.setSequence("0");
						pageDao.insertPageRateItemRsv(bean);
					} else {
						pageDao.insertPageRateItemRsv(bean);
					}
				} else {
					pageDao.insertPageRateItem(bean);
				}
			}
		}
	}

	/**
	 * コンテンツ問題対応者のDB更新処理
	 * 呼び出し元
	 * A)真実コンテンツ登録処理
	 * B)真実コンテンツ更新処理
	 * C)予約コンテンツ登録処理
	 * 
	 * @param param　
	 * @return　
	 */
	private void insertPageCommentAdmin(PageFormBean pageForm, BaseBean createInfo, String updateDiv) {
		List pageCommentAdminList = pageForm.getPageCommentAdminList();
		if (pageCommentAdminList != null) {
			for (int i = 0; i < pageCommentAdminList.size(); i++) {
				PageCommentAdminBean bean = (PageCommentAdminBean)pageCommentAdminList.get(i);
				bean.setPageId(pageForm.getPageId());
				
				setCreateInfo(bean, createInfo, updateDiv);

				//公開したまま編集の場合、予約データ作成
				if("1".equals(pageForm.getOnEditFlag())) {
					pageDao.insertPageCommentAdminRsv(bean);
				} else {
					pageDao.insertPageCommentAdmin(bean);
				}
			}
		}
	}
	
	/**
	 * DB更新処理前、共通項目の設定
	 * @param param　
	 * @return　
	 */
	private void setCreateInfo(BaseBean bean, BaseBean createInfo, String updateDiv ) {
		if ("update".equals(updateDiv)) {
			bean.setCreateBy(createInfo.getCreateBy());
			bean.setCreateDate(createInfo.getCreateDate());
		} else {
			bean.setCreateBy(getLoginUserBean().getUserId());
			bean.setCreateDate(new Date());
		}
		bean.setUpdateBy(getLoginUserBean().getUserId());
		bean.setUpdateDate(new Date());
	}
	
	/**
	 * 該コンテンツ配置位置指定した後で、親コンテンツ直下該コンテンツ後ろのすべてコンテンツは表示順＋１に更新
	 * @param param　
	 * @return　
	 */
	public void updateBrothersOrderBy(PageFormBean pageForm) throws Exception {
		pageDao.updateBrothersOrderBy(pageForm.getPageId(), pageForm.getPPageId(), pageForm.getOrderBy(), getLoginUserBean().getUserId());
		// 配列順＝配列順＋１
		pageForm.setOrderBy(String.valueOf(NumberUtil.toInt(pageForm.getOrderBy()) + 1));
	}

	/**
	 * パラメータ（ページID）直下の子コンテンツ情報（再帰呼び出して子層、孫層すべて抽出）
	 * 
	 * 利用箇所：
	 * １）子コンテンツ情報再設定の更新処理
	 * 
	 * @param param　
	 * @return　
	 */
	public void getAllOpenChildPageList(String pageId, HashMap<String, PageBean> allOpenChildPageMap) throws Exception {
		List allOpenChildPageList = new ArrayList(); 
		this.getAllOpenChildPageList(pageId, allOpenChildPageList);
		for(int i=0; i<allOpenChildPageList.size(); i++){
			PageBean page=(PageBean)allOpenChildPageList.get(i);
			allOpenChildPageMap.put(page.getPageId() + "," + page.getPageDivision(), page);
		}
	}

	/**
	 * 予約コンテンツ情報の物理削除
	 * @param param　フォームBean
	 */
	public void deletePageRsv(PageFormBean pageForm) {
		// 動的コンテンツの場合
		if (ConstantContainer.PAGE_DYNAMIC.equals(pageForm.getHtmlFlag())) {
			// コンテンツリンク予約情報を削除
			pageDao.deletePageExtend(pageForm.getPageId(), "PAGE_LINK_RESERVE");
			// コンテンツ添付ファイル予約情報を削除
			pageDao.deletePageExtend(pageForm.getPageId(), "PAGE_ATTACHMENT_RESERVE");
		}
		// コンテンツ閲覧権限トップグループ予約情報を削除
		pageDao.deletePageExtend(pageForm.getPageId(), "AUTH_LEADING_GROUP_RESERVE");
		// コンテンツ閲覧権限ユーザ予約情報を削除
		pageDao.deletePageExtend(pageForm.getPageId(), "AUTH_USER_RESERVE");
		// 更新代行者予約情報を削除
		pageDao.deletePageExtend(pageForm.getPageId(), "UPDATE_PROXY_USER_RESERVE");
		// コンテンツ評価アイテム予約情報を削除
		pageDao.deletePageExtend(pageForm.getPageId(), "PAGE_RATE_ITEM_RESERVE");
		// コンテンツコメント管理者予約情報
		pageDao.deletePageExtend(pageForm.getPageId(), "PAGE_COMMENT_ADMIN_RESERVE");
		// コンテンツ予約情報を削除
		pageDao.deletePageExtend(pageForm.getPageId(), "PAGE_RESERVE");
	}
	
	/**
	 * コンテンツ配置先の取得
	 * 例えば[HOME]-[案内/通達]-[コンテンツ配置先表示]
	 * @param param　
	 * @return　
	 */
	public String getPageLocation(String PageId ) {
		String strLocation = new String();
		Boolean flag=true;
		String pageid=PageId;
		String strP_Title="";
		int count =0;
		while(flag){
			if("0".equals(pageid)){	  
				strLocation="[HOME]"+strLocation;
				flag = false; 
			} else {
				PageBean page_check=pageDao.getPage(pageid);
				if (page_check == null) {
					return "";
				}
				if("1".equals(page_check.getOnEditFlag()) && count ==0){
					PageBean page=pageDao.getPageReserve(pageid);
					if (page == null) {
						return "";
					}
					strP_Title="["+page.getTitle()+"]";
					pageid=page.getPPageId();
					count=count+1;
				} else {
					PageBean page=pageDao.getPage(pageid);
					if (page == null) {
						return "";
					}
					strP_Title="["+page.getTitle()+"]";
					pageid=page.getPPageId();  
				}
				strLocation="-"+strP_Title+strLocation;
			}
		}
		return strLocation;
	}
	
	//コンテンツ配置先、例えば[HOME]-[案内/通達]-[コンテンツ配置先表示]
	public String getPageLocationOpen(String PageId) {
		String strHaiTiSaki=new String();
		Boolean flag=true;
		String pageid=PageId;
		while(flag){
			if ("0".equals(pageid)) {
				strHaiTiSaki="[HOME]"+strHaiTiSaki;
				flag = false; 
			} else {
				PageBean page=pageDao.getPage(pageid);
				if (page == null) {
					return "";
				}
				String strP_Title="["+page.getTitle()+"]";
				strHaiTiSaki="-"+strP_Title+strHaiTiSaki;
				pageid=page.getPPageId();
			}
		}
		return strHaiTiSaki;
	}
	
	/**
	 * コンテンツ評価アイテム情報に関してのDB操作
	 * @param pageDbOperBean
	 */
	private void doPageRateItemDbOper(PageFormBean pageDbOperBean, BaseBean createInfo, String updateDiv) {
		// テーブルからコンテンツ評価アイテム情報を取得
		List dbPageRateItemList = pageDao.getPageRateItemList(pageDbOperBean.getPageId());
		if (dbPageRateItemList != null){
			// 取得したコンテンツ評価アイテム情報リストにより、下記の処理を繰り返す
			for (int i=0; i<dbPageRateItemList.size();i++){
				// DBコンテンツ評価アイテム情報
				PageRateItemBean dbPageRateItemBean = (PageRateItemBean) dbPageRateItemList.get(i);
				//2.画面入力された評価アイテムにより、DB更新処理を行う
				List<PageRateItemBean> formPageRateItemList = pageDbOperBean.getPageRateItemBeanList();
				Boolean deleteFlag = true;
				if (formPageRateItemList != null) {
					// 画面入力のコンテンツ評価アイテム情報リストにより、下記の処理を繰り返す
					for (PageRateItemBean formPageRateItemBean:formPageRateItemList){
						// DB、画面両方存在した場合
						if (dbPageRateItemBean.getSequence().equals(formPageRateItemBean.getSequence())){
							deleteFlag = false;
							break;
						}
					}
				}
				// DBに存在、画面に存在しない場合、DB論理削除を行う
				if (deleteFlag){
					pageDao.updateEvaluationInvalidBySeq(dbPageRateItemBean.getPageId(), 
							dbPageRateItemBean.getSequence(), getLoginUserBean().getUserId());
				}
			}
		}
		// 画面入力された評価アイテムにより、DB更新処理を行う
		List pageRateItemList = pageDbOperBean.getPageRateItemBeanList();
		if (pageRateItemList != null) {
			for (int i = 0; i < pageRateItemList.size(); i++) {
				PageRateItemBean itemBean = (PageRateItemBean)pageRateItemList.get(i);
				itemBean.setPageId(pageDbOperBean.getPageId());
				itemBean.setEvaluationOrderBy(String.valueOf(i+1));
				
				setCreateInfo(itemBean, createInfo, updateDiv);
				
				//新規追加
				if (StringUtil.isEmpty(itemBean.getSequence())) {
					itemBean.setUpdateBy(getLoginUserBean().getUserId());
					itemBean.setCreateBy(getLoginUserBean().getUserId());
					pageDao.insertPageRateItem(itemBean);
				} else {
					//更新
					itemBean.setUpdateBy(getLoginUserBean().getUserId());
					pageDao.updatePageRateItem(itemBean);
				}
			}
		}
	}
	
	/**
	 * 動コンテンツのHTMLファイル作成
	 * @param param　
	 * @return　
	 */
	public void createHtmlFile(PageFormBean pageForm) throws Exception {
		// ページID
		String pageId = pageForm.getPageId();
		// アップロードパス
		String uploadPath="";
		// 「したまま編集」の場合
		if("1".equals(pageForm.getOnEditFlag())) {
			uploadPath = "CONFIRM".equals(pageForm.getCreateHtmlEvent()) ? "temp.temp.path" : "htmlFile.temp.path";
		} else {
			uploadPath = "CONFIRM".equals(pageForm.getCreateHtmlEvent()) ? "temp.path" : "htmlFile.path";
		}
		uploadPath = FsPropertyUtil.getStringProperty(uploadPath);
		// ファイル名
		String fileName = pageId + ".html";
		File path = new File(uploadPath);
		if (!path.exists()) {
			path.mkdirs();
		}
		File fileTo = new File(uploadPath, fileName);
		BufferedWriter fo = new BufferedWriter(new FileWriter(fileTo));
		String htmlStr = new FreemarkerUtil().getTemplateStr(pageForm, "html_page_create.ftl", "UTF-8");
		fo.write(htmlStr);
		fo.flush();
		fo.close();
	}
	
	/**
	 * パラメータ（ページID）直下の子コンテンツ情報（再帰呼び出して子層、孫層すべて抽出）
	 * 
	 * 利用箇所：
	 * １）子コンテンツ情報再設定の子画面初期表示
	 * ２）子コンテンツ情報再設定の更新処理
	 * 
	 * 既存のgetSubBrotherList3の代わりに
	 * @param param　
	 * @return　
	 */
	public void getAllOpenChildPageList(String pageId, List allOpenChildPageList) throws Exception {
		List childList = this.getOpenChildPageList(pageId);
		getAllOpenChildPageList(childList, allOpenChildPageList);

	}
	
	private void getAllOpenChildPageList(List childList, List allOpenChildPageList) throws Exception {
		if (childList != null && childList.size() > 0) {
			allOpenChildPageList.addAll(childList);
		}

		List childListEach = new ArrayList();

		for(int i=0; i<childList.size(); i++){
			PageBean page = (PageBean)childList.get(i);
			List childList1 = this.getOpenChildPageList(page.getPageId());
			childListEach.addAll(childList1);
		}
		if (childListEach != null && childListEach.size() > 0) {
			this.getAllOpenChildPageList(childListEach, allOpenChildPageList);
		}
	}
	
	/**
	 * パラメータ（ページID）直下の子コンテンツ情報抽出
	 * １）抽出状態：真実と予約両方　且つ　状態＝公開中
	 * ２）子層のみ抽出
	 * 
	 * @param param　
	 * @return　
	 */
	public List getOpenChildPageList(String pageId) throws Exception {
		// 真実コンテンツ
		return pageDao.getOpenChildPageList(pageId);
	}
	
	/**
	 * 「NULL」から「ゼロ」に変換　例えば、画面のチェックボックスが選択されない場合、「ゼロ」に変更
	 * @param formBean　フォームBean
	 */
	private void convertFlagNullToZero(PageFormBean formBean){
		// 公開開始日基準フラグ
		formBean.setStartdateOpenFlag(StringUtil.nullToZero(formBean.getStartdateOpenFlag()));
		// 非公開フラグ
		formBean.setConfirmFlag(StringUtil.nullToZero(formBean.getConfirmFlag()));
		// 予約非公開フラグ
		formBean.setReserveConfirmFlag(StringUtil.nullToZero(formBean.getReserveConfirmFlag()));
		// 表示フラグ
		formBean.setDisplayFlag(StringUtil.nullToZero(formBean.getDisplayFlag()));
		// リンクフラグ
		formBean.setLinkFlag(StringUtil.nullToZero(formBean.getLinkFlag()));
		// 画面フラグ
		formBean.setHtmlFlag(StringUtil.nullToZero(formBean.getHtmlFlag()));
		// 文書リンク表示不可フラグ
		formBean.setDenyLinkfileFlag(StringUtil.nullToZero(formBean.getDenyLinkfileFlag()));
		// ダウンロード不可フラグ
		formBean.setDenyDownloadFlag(StringUtil.nullToZero(formBean.getDenyDownloadFlag()));
		// 新着情報非表示フラグ
		formBean.setNewUndisplayFlag(StringUtil.nullToZero(formBean.getNewUndisplayFlag()));
		// 公開確認日付非更新フラグ
		formBean.setConfirmNoupdateFlag(StringUtil.nullToZero(formBean.getConfirmNoupdateFlag()));
		// 新規表示維持フラグ
		formBean.setNewKeepFlag(StringUtil.nullToZero(formBean.getNewKeepFlag()));
		// 公開維持編集フラグ
		formBean.setOnEditFlag(StringUtil.nullToZero(formBean.getOnEditFlag()));
		// コンテンツ評価フラグ
		formBean.setEvaluationFlag(StringUtil.nullToZero(formBean.getEvaluationFlag()));
		// 評価者氏名表示フラグ
		formBean.setEvaluatorDisplayFlag(StringUtil.nullToZero(formBean.getEvaluatorDisplayFlag()));
		// 前回公開評価者氏名表示フラグ
		formBean.setPrevEvaluatorDisplayFlag(StringUtil.nullToZero(formBean.getPrevEvaluatorDisplayFlag()));
		// 評価者コメント編集可フラグ
		formBean.setCommentEditFlag(StringUtil.nullToZero(formBean.getCommentEditFlag()));
		// 複数項目評価可フラグ
		formBean.setPluralEvaluationFlag(StringUtil.nullToZero(formBean.getPluralEvaluationFlag()));
		// 前回複数項目評価可フラグ
		formBean.setPrevPluralEvaluationFlag(StringUtil.nullToZero(formBean.getPrevPluralEvaluationFlag()));
		// 評価をクリアするフラグ
		formBean.setEvaluationClearFlag(StringUtil.nullToZero(formBean.getEvaluationClearFlag()));
	}
	
	/**
	 * コンテンツコメント統計情報を取得
	 * @param pageId
	 * @return int
	 * @throws ServiceException
	 * @throws DataBaseException
	 */
	public int getCommentCount(String pageId) {
		return pageDao.getCommentCount(pageId);
	}
	
	/**
	 * 評価項目表示フラグ
	 * @param pageId
	 * @return
	 * @throws ServiceException
	 * @throws DataBaseException
	 */
	public String getShowHyoukaFlag(String pageId) {
		int count = pageDao.getShowHyoukaFlag(pageId);
		String flag = "0";
		if (count > 0) {
			flag = "1";
		}
		return flag;
	}
	
	/**
	 * チェックコンテンツ
	 * @param pageId
	 */
	public String getHyoukaErrorMessage(String pageId, String openDate) {
		String hyoukaErrorMessage = "";
		PageBean pageInfo = pageDao.getPage(pageId);
		// コンテンツ削除する
		if (pageInfo == null) {
			hyoukaErrorMessage = "対象のコンテンツは削除されたため、評価できません。";
			return hyoukaErrorMessage;
		}
		// コンテンツ編集中
		if ("1".equals(pageInfo.getConfirmFlag())) {
			hyoukaErrorMessage = "現在編集中のため、評価できません、再公開後に評価ください。";
			return hyoukaErrorMessage;
		}
		// コンテンツ再公開
		if (!openDate.equals(pageInfo.getOpenDate())) {
			hyoukaErrorMessage = "コンテンツが編集されたため、一旦、\nトップページを表示した後、再度評価を行ってください";
			return hyoukaErrorMessage;
		}
		return hyoukaErrorMessage;
	}
	
	/**
	 * コンテンツ評価情報、評価統計情報を取得
	 * @param pageId
	 * @return List
	 * @throws ServiceException
	 * @throws DataBaseException
	 */
	public List<PageRateItemBean> getEvaluationTotalList(String pageId, String userId) {
		return pageDao.getEvaluationTotalList(pageId, userId);
	}
	
	/**
	 * 単一項目評価の時に、該当ユーザの全て評価情報を物理削除する
	 * @param pageId
	 * @param currentUserId
	 */
	public void deleteEvaluationByUser(String pageId, String currentUserId) {
		pageDao.deleteEvaluationByUser(pageId, currentUserId);
	}
	
	/**
     * ユーザIDと項目順番をコンテンツ評価統計情報テーブルに登録する
     * @param pageId
     * @param currentUserId
     * @param hyoukaSequence
     * @param hyoukaOrderBy
     * @throws ServiceException
     * @throws DataBaseException
     */
    public void insertEvaluationCountByUser(String pageId, String userId, String hyoukaSequence, String hyoukaOrderBy) {
    	pageDao.insertEvaluationCountByUser(pageId, userId, hyoukaSequence, hyoukaOrderBy);
    }
	
    /**
     * 該当ユーザのコンテンツ評価統計情報テーブルの評価情報を物理削除する
     * @param pageId
     * @param currentUserId
     * @param hyoukaSequence
     * @param hyoukaOrderBy
     * @throws ServiceException
     * @throws DataBaseException
     */
    public void deleteEvaluationCountByUser(String pageId, String userId, String hyoukaSequence, String hyoukaOrderBy) {
    	pageDao.deleteEvaluationCountByUser(pageId, userId, hyoukaSequence, hyoukaOrderBy);
    }
    
	/**
	 * コンテンツコメント情報、コメント統計情報を取得
	 * @param pageId
	 * @param userId
	 * @param flag
	 * @return List
	 * @throws ServiceException
	 * @throws DataBaseException
	 */
	public List<PageCommentRateBean> getCommentTotalList(String pageId, String userId, String flag) {
		return pageDao.getCommentTotalList(pageId, userId, flag);
	}
	
	/**
	 * コンテンツコメント情報を追加(投稿)
	 * @param pageId
	 * @param userId
	 * @param addCommentInfo
	 */
	public void addComment(String pageId,  String userId, String addCommentInfo) {
		pageDao.addComment(pageId, userId, addCommentInfo);
	}

	/**
	 * 該当ユーザのコメント情報を更新する
	 * @param pageId
	 * @param currentUserId
	 * @param commentOrderBy
	 * @param commentUserId
	 * @param commentInfo
	 */
	public void updateComment(String pageId, String currentUserId, String commentOrderBy, String commentUserId, String commentInfo) {
		pageDao.updateComment(pageId, currentUserId, commentOrderBy, commentUserId, commentInfo);
		// 投稿内容は変更するので、いいねのカウント数はクリアします
		pageDao.deleteCommentCountByOrderBy(pageId, commentOrderBy);
		
	}
	
	/**
	 * 該当ユーザのコメント情報を論理削除する
	 * @param pageId
	 * @param currentUserId
	 * @param commentOrderBy
	 * @param commentUserId
	 */
	public void deleteComment(String pageId, String currentUserId, String commentOrderBy, String commentUserId) {
		pageDao.deleteComment(pageId, currentUserId, commentOrderBy, commentUserId);
		pageDao.deleteCommentCountByOrderBy(pageId, commentOrderBy);
		
	}
	
	/**
     * 該当ユーザのコメント統計情報テーブルのコメント情報を物理削除する
     * @param pageId
     * @param userId
     * @param commentOrderBy
     */
    public void deleteCommentCountByUser(String pageId, String userId, String commentOrderBy) {
		pageDao.deleteCommentCountByUser(pageId, userId, commentOrderBy);
    }
    
    /**
     * ユーザIDとコメント順番をコメント統計情報テーブルに登録する
     * @param pageId
     * @param userId
     * @param commentOrderBy
     */
    public void insertCommentCountByUser(String pageId, String userId, String commentOrderBy) {
		pageDao.insertCommentCountByUser(pageId, userId, commentOrderBy);
    }
    
	/**
	 * コメントメール送信画面でユーザコメント明細を取得
	 * @param pageId ページID
	 * @param commentOrderBy コメント順番
	 * @param userId ページID
	 * @param evaluatorDisplayFlag 評価者氏名表示フラグ
 	 */
	public PageCommentRateBean getUserCommentForMail(String pageId, String commentOrderBy, String userId, String evaluatorDisplayFlag) {
		PageCommentRateBean result = pageDao.getUserCommentForMail(pageId, commentOrderBy, userId);
		if(result != null && "1".equals(evaluatorDisplayFlag)){
			result.setUserName("　評価者氏名：" + commonDao.getUserName(userId));
		}
		return result;
	}
	
	/**
	 * コメントメール送信
	 * @param List list
	 * @param String user
	 * @throws Exception
	 * @function メールの内容処理
	 */
	public void commentSendMail(PageCommentMailFormBean formBean, String userId) throws Exception{
		MailBean mailInfo = new MailBean();
		MailUtil mail = new MailUtil();
		// コンテンツコメント管理者情報
		List commentAdminList = null;
		commentAdminList = pageDao.getCommentAdminList(formBean.getPageId());
		mailInfo.setMailType(MailBean.MAIL_TYPE_HTML);
		mailInfo.setFrom(FsPropertyUtil.getStringProperty("mail.from.address"));
		//メール主題
		mailInfo.setSubject(formBean.getCommentMailTitle());
		
		List mailToAddressList = new ArrayList();
		
		List toNameList = new ArrayList();
		// メール内容と宛先
		if (commentAdminList != null) {
			for (int i = 0; i < commentAdminList.size(); i ++) {
				PageCommentAdminBean commentAdmin = (PageCommentAdminBean)commentAdminList.get(i);
				String commentAdminUserId = commentAdmin.getUserId();
				String commentAdminUserName = commentAdmin.getUserName();
				commentAdminUserName = commentAdminUserName + "さん";
				List userMail = pageDao.getMailAddress(commentAdminUserId);
				if (userMail != null) {
					toNameList.add(commentAdminUserName);
					for (int k = 0; k < userMail.size(); k ++) {
						mailToAddressList.add(userMail.get(k));
					}
				}
			}
		}
		
		// 宛先
		String[] mailToList = new String[mailToAddressList.size()];
		for (int i = 0; i < mailToList.length; i++) {
			mailToList[i] = (String) mailToAddressList.get(i);
		}
		
		//ＢＣＣ
		String[] mailToBccList = new String[1];
		mailToBccList[0] = FsPropertyUtil.getStringProperty("comment.mail.bcc.address");
		
		mailInfo.setTo(mailToList);
		mailInfo.setBcc(mailToBccList);
		
		// メール内容のトップ（○○さん）
		String mailToUserName = "";
		for (int i = 0; i < toNameList.size(); i++) {
			if (i == 0) {
				mailToUserName = (String)toNameList.get(i);
			} else {
				mailToUserName += "、" + (String)toNameList.get(i);
			}
		}
		formBean.setMailToUserName(mailToUserName);
		
		//メール内容
		String mailTemplate = new FreemarkerUtil().getTemplateStr(formBean, "mail_page_comment.ftl");
		mailInfo.setContent(mailTemplate);
		
		String kinowu = FsPropertyUtil.getStringProperty("comment.context");
		
		//送信
		mail.sendMail(mailInfo, "", kinowu);
	}
	
	/**
	 * 削除データを事前に取得
	 * @param pageDeleteBean
	 * @param divFlag
	 * @param conFlag
	 * @throws Exception 
	 */
	private List<PageDeleteBean> getDeleteData(PageDeleteBean pageDeleteBean, String divFlag, String conFlag) throws Exception{
		List<PageDeleteBean> pageDeleteList = new ArrayList<PageDeleteBean>();
		String haitisaki = "";
		String pageId = pageDeleteBean.getPageId();
		String title = pageDeleteBean.getTitle();
		
		// 配置先
		haitisaki = getPageLocationOpen(pageId);
		
		// conFlag = 0　⇒　公開中コンテンツ、conFlag = 1　⇒　作成中コンテンツ、conFlag = 2　⇒　公開待ちコンテンツ
		// divFlag = 1　⇒　両方を削除する、divFlag = 0　⇒　実のみを削除する
		if ("1".equals(divFlag)) {
			title = title + "「公開したまま編集中。作成中コンテンツ配下に編集中コンテンツあり」";
			if ("2".equals(conFlag)) {
				haitisaki = haitisaki + "「公開待ちコンテンツ」";
			}
		} else {
			if ("1".equals(conFlag)) {
				haitisaki = haitisaki + "「作成中コンテンツ」";
			} else if ("2".equals(conFlag)) {
				haitisaki = haitisaki + "「公開待ちコンテンツ」";
			}
		}

		pageDeleteBean.setPageLocation(haitisaki);
		pageDeleteBean.setTitle(title);
		
		//作成者
		String createBy = pageDeleteBean.getCreateBy();
		
		// 宛先 メールアドレスList
		List<String> toAddressList = new ArrayList();
		List<String> toAddressIdList = new ArrayList();
		
		// 宛先 氏名 List
		List<String> toNameList = new ArrayList();
		
		String createUserName = pageDao.getMailToUserName(createBy, "1");
		
		if (!StringUtil.isEmpty(createUserName)) {
			createUserName = createUserName + "さん";
			List<String> createUserMail = pageDao.getMailAddress(createBy);
			if (createUserMail != null) {
				toNameList.add(createUserName);
				for (int k = 0; k < createUserMail.size(); k ++) {
					toAddressList.add(createUserMail.get(k));
					toAddressIdList.add(createBy);
				}
			}
		}
		
		//更新者
		String updateBy = pageDeleteBean.getUpdateBy();
		
		if (!(toNameList.size() > 0 && createBy.equals(updateBy))) {
			String updateUserName = pageDao.getMailToUserName(updateBy, "1");

			if (!StringUtil.isEmpty(updateUserName)) {
				updateUserName = updateUserName + "さん";
				List<String> updateUserMail = pageDao.getMailAddress(updateBy);
				if (updateUserMail.size() > 0) {
					toNameList.add(updateUserName);
					for (int k = 0; k < updateUserMail.size(); k ++) {
						toAddressList.add(updateUserMail.get(k));
						toAddressIdList.add(updateBy);
					}
				}
			}
		}
		
		// 宛先List
		pageDeleteBean.setToAddressList(toAddressList);
		pageDeleteBean.setToAddressIdList(toAddressIdList);
		pageDeleteBean.setToNameList(toNameList);
		
		// 承認者List
		List<ProxyUserBean> updateProxyUserList = pageDao.getProxyUserList(pageId);

		// ログ出力用承認者IDリスト
		List<String> proxyUserList = new ArrayList();
		
		// cc メールアドレスList
		List<String> ccAddressList = new ArrayList();
		
		// cc 氏名 List
		List<String> ccNameList = new ArrayList(); 
		
		for (int j = 0; j < updateProxyUserList.size(); j++) {
			ProxyUserBean proxyUserBean = updateProxyUserList.get(j);
			String updateProxyUserId = proxyUserBean.getProxyUserId();
			String updateProxyUserName = pageDao.getMailToUserName(updateProxyUserId, "0");
			
			if (!StringUtil.isEmpty(updateProxyUserName)) {
				updateProxyUserName = updateProxyUserName + "さん";
				List<String> proxyUserMail = pageDao.getMailAddress(updateProxyUserId);
				if (proxyUserMail != null) {
					ccNameList.add(updateProxyUserName);
					for (int k = 0; k < proxyUserMail.size(); k ++) {
						ccAddressList.add(proxyUserMail.get(k));
						proxyUserList.add(updateProxyUserId);
					}
				}
			}
		}
					
		// ログ出力用承認者IDリスト		
		pageDeleteBean.setProxyUserList(proxyUserList);
		
		// CC List
		pageDeleteBean.setCcNameList(ccNameList);
		pageDeleteBean.setCcAddressList(ccAddressList);

		//	システム担当者の氏名
		String bccUserName = FsPropertyUtil.getStringProperty("mail.bcc.name");
							 
		//システム担当者のメールアドレス
		String bccUserMail = FsPropertyUtil.getStringProperty("mail.bcc.address");
		
		// BCC メールアドレス List
		List<String> bccAddressList = new ArrayList();
		
		// BCC 氏名 List
		List<String> bccNameList = new ArrayList(); 
		
		bccAddressList.add(bccUserMail);
		bccNameList.add(bccUserName);
		
		pageDeleteBean.setBccAddressList(bccAddressList);
		pageDeleteBean.setBccNameList(bccNameList);
		
		//	動的コンテンツのリンク先List
		List<PageLinkBean> linkContentsList = new ArrayList();
		if ("1".equals(pageDeleteBean.getHtmlFlag())) {
			linkContentsList = getLinkContentsList(pageDeleteBean);
		}
		pageDeleteBean.setLinkContentsList(linkContentsList);
		
		//リンク元List
		List<List<PageLinkBean>> linkedList = getLinkedList(pageDeleteBean);
		pageDeleteBean.setLinkedList(linkedList);

		pageDeleteList.add(pageDeleteBean);
		return pageDeleteList;
	}
	
	/**
	 * リンク先リストを取得
	 * @param pageDeleteBean
	 * @return
	 */
	private List<PageLinkBean> getLinkContentsList(PageDeleteBean pageDeleteBean) {
		String pageId = pageDeleteBean.getPageId();
		// コンテンツリンク添付
		List<PageLinkBean> pageLinkList = pageDao.getPageLinkList(pageId);

		for (int i = 0; i < pageLinkList.size(); i++) {
			PageLinkBean link = (PageLinkBean)pageLinkList.get(i);
			link.setSendFlag(true);
			String linkUrl = link.getLinkUrl();
			
			//リンク先コンテンツpageId
			String linkPageId = "";
			
			//リンク先コンテンツpageId取得
			if (linkUrl != null) {
				if(linkUrl.startsWith(pageDeleteBean.getContextPath() + "/linkAccessFilter_view.do?pageId=")) {
					linkUrl = linkUrl.substring(linkUrl.indexOf("pageId=") + 7);
					if(!linkUrl.startsWith("0") && linkUrl.length() > 13) {
						linkPageId = linkUrl.substring(0, 13);
					}
				}
			}
			
			// 本リンク先コンテンツは、別ルートのリンク元コンテンツがあり、且つ、当リンク元が(作成中＆公開中(終了日>システム日付+14)、公開待ち)が存在する場合
			if (StringUtil.isEmpty(linkPageId) || pageId.equals(linkPageId)) {
				link.setSendFlag(false);
			} else {
				PageDeleteBean linkPage = pageDao.getPagePeriodValid(linkPageId);
				if (linkPage != null) {
					// 設定Bean中コラムの値
					setParam(link, linkPage, "0");
					//本リンク先コンテンツは、別ルートのリンク元コンテンツ取得
					List<PageLinkBean> linkedPageIdList = pageDao.getPageLinkListByUrl("PAGE_LINK", linkPageId);
					
					for (int j = 0; j < linkedPageIdList.size(); j++) {
						PageLinkBean tempPage = linkedPageIdList.get(j);
						String linkedPageId = tempPage.getPageId();
						
						if (!linkedPageId.equals(pageId)) {
							//期限切れなかったリンク元コンテンツを存在
							int count = pageDao.getPageLinkExpiredDateCount(linkedPageId);
							
							//別ルートの期限切れなかったリンク元を存在
							if(count > 0) {
								link.setSendFlag(false);
								break;
							}
						}
					}
				} else {
					link.setSendFlag(false);
				}
			}
		}
		return pageLinkList;
	}
	
	/**
	 * @param Page page
	 * @throws Exception 
	 * @function リンク元List取得
	 */
	private List<List<PageLinkBean>> getLinkedList(PageDeleteBean pageDeleteBean) throws Exception {
		List<List<PageLinkBean>> list = new ArrayList();
		String pageId = pageDeleteBean.getPageId();
		String endDate = pageDeleteBean.getEndDate();
		// 実リンク元List
		List<PageLinkBean> linkList = pageDao.getPageLinkListByUrl("PAGE_LINK", pageId);
		
		// 予約リンク元List
		List<PageLinkBean> linkReserveList = pageDao.getPageLinkListByUrl("PAGE_LINK_RESERVE", pageId);
		
		// 両方存在リンク元List
		List<PageLinkBean> linkBothPageList = new ArrayList<PageLinkBean>();
		
		List<PageLinkBean> finalLinkList = new ArrayList<PageLinkBean>();
		
		List<PageLinkBean> finalLinkReserveList = new ArrayList<PageLinkBean>();

		//実ありのPAGEリスト
		List<String> pageidList = new ArrayList();
		
		//予約のPAGEリスト
		List<String> pageidListRev = new ArrayList();
		
		for(int i = 0; i < linkReserveList.size(); i++) {
			PageLinkBean linkReservePage = (PageLinkBean)linkReserveList.get(i);
			String linkReservePageId = linkReservePage.getPageId();
			pageidListRev.add(linkReservePageId);
		}
		
		// 実リンク元List
		for(int i = 0; i < linkList.size(); i++) {
			PageLinkBean linkPage = (PageLinkBean)linkList.get(i);
			String linkPageId = linkPage.getPageId();
			PageBean pageBean = pageDao.getPage(linkPageId);
			if (!linkPageId.equals(pageId) && pageBean != null) {
				PageDeleteBean linkPageTemp = pageDao.getPagePeriodValid(linkPageId);
				if (linkPageTemp != null) {
					PageBean linkPageReserve = pageDao.getPageReserve(linkPageId);
					if (linkPageReserve == null) {
						linkPage.setSendFlag(true);
						setParam(linkPage, linkPageTemp, "1");
						finalLinkList.add(linkPage);
					} else {
						PageDeleteBean linkPageReserveTemp =  new PageDeleteBean();
						WrappedBeanUtil.copyProperties(linkPageReserveTemp, linkPageReserve);
						Date startDateDate = StringUtil.parseTheDate(linkPageReserveTemp.getStartDate(), "yyyy/MM/dd");
						Date endDateDate = StringUtil.parseTheDate(endDate, "yyyy/MM/dd");
						if (endDateDate.before(startDateDate)){
							linkPageReserveTemp.setDateFlag("0");
						} else{
							linkPageReserveTemp.setDateFlag("1");
						}
						if ((!pageidListRev.contains(linkPageId)) && "1".equals(linkPageReserveTemp.getDateFlag())) {
						} else {
							linkPage.setSendFlag(true);
							setParam(linkPage, linkPageTemp, "1");
							linkBothPageList.add(linkPage);
						}
					}
					pageidList.add(linkPageTemp.getPageId());
				}
			}
		}
		
		// 予約リンク元List
		for(int i = 0; i < linkReserveList.size(); i++) {
			PageLinkBean linkReservePage = (PageLinkBean)linkReserveList.get(i);
			String linkReservePageId = linkReservePage.getPageId();
			if (pageidList.contains(linkReservePageId)) {
				continue;
			} else {
				PageBean pageBean = pageDao.getPageReserve(linkReservePageId);
				if (!linkReservePageId.equals(pageId) && pageBean != null) {
					PageBean linkPageTemp = pageDao.getPageReserve(linkReservePageId);
					if (linkPageTemp != null) {
						PageDeleteBean linkPageReserveTemp =  new PageDeleteBean();
						WrappedBeanUtil.copyProperties(linkPageReserveTemp, linkPageTemp);
						Date startDateDate = StringUtil.parseTheDate(linkPageReserveTemp.getStartDate(), "yyyy/MM/dd");
						Date endDateDate = StringUtil.parseTheDate(endDate, "yyyy/MM/dd");
						if (endDateDate.before(startDateDate)){
							linkPageReserveTemp.setDateFlag("0");
						} else{
							linkPageReserveTemp.setDateFlag("1");
						}
						linkReservePage.setSendFlag(true);
						setParam(linkReservePage, linkPageReserveTemp, "1");
						finalLinkReserveList.add(linkReservePage);
					}
				}
			}
		}
		
		//リンク元List
		if (finalLinkList.size() > 0 || finalLinkReserveList.size() > 0 || linkBothPageList.size() > 0) {
			list.add(finalLinkList);
			list.add(finalLinkReserveList);
			list.add(linkBothPageList);
		}
		return list;
	}
	
	/**
	 * @param Page bean1
	 * @param Page bean2
	 * @function 設定Bean中コラムの値
	 */
	private void setParam(PageLinkBean bean1, PageDeleteBean bean2, String flag) {
		bean1.setStartDate(bean2.getStartDate());
		bean1.setEndDate(bean2.getEndDate());
		bean1.setCreateBy(bean2.getCreateBy());
		bean1.setUpdateBy(bean2.getUpdateBy());
		bean1.setTitle(bean2.getTitle());
		//createByユーザ氏名
		String createUserName = pageDao.getUserName(bean2.getCreateBy());
		
		//updateByユーザ氏名
		String updateUserName = pageDao.getUserName(bean2.getUpdateBy());
		
		bean1.setCreateUserName(createUserName);
		bean1.setUpdateUserName(updateUserName);
		
		//flag = 1、リンク元コンテンツの配置先の値設定
		if ("1".equals(flag)) {
			String haitisaki = getPageLocationOpen(bean2.getPageId());
			bean1.setPageLocation(haitisaki);
			bean1.setDateFlag(bean2.getDateFlag());
		}
	}
	
	/**
	 * List list
	 * String flag
	 * リンク元、リンク先List 存在フラグ
	 */ 
	private boolean getContentFlag(List<?> list, String flag) {
		boolean contentFlag = false;
		// flag = 0、リンク先の場合
		if ("0".equals(flag)) {
			for (int i = 0; i < list.size(); i ++) {
				PageLinkBean page = (PageLinkBean)list.get(i);
				if (page.isSendFlag()) {
					contentFlag = true;
					break;
				}
			}
		// flag = 1、リンク元の場合
		} else {
			if (list.size() > 0) {
				List<PageLinkBean> pageList = (List)list.get(0);
				List<PageLinkBean> pageReserveList = (List)list.get(1);
				List<PageLinkBean> bothPageList = (List)list.get(2);
				for (int i = 0; i < pageList.size(); i ++) {
					PageLinkBean page = pageList.get(i);
					if (page.isSendFlag()) {
						contentFlag = true;
						break;
					}
				}
				if (!contentFlag) {
					for (int i = 0; i < pageReserveList.size(); i ++) {
						PageLinkBean page = pageReserveList.get(i);
						if (page.isSendFlag()) {
							contentFlag = true;
							break;
						}
					}
				}
				if (!contentFlag) {
					if (bothPageList.size() > 0) {
						contentFlag = true;
					}
				}
			}
		}
		return contentFlag;
	}	

	private void deleteSendMail(List<PageDeleteBean> deleteList, String user) throws Exception{
		PageDeleteBean pageDeleteBean = deleteList.get(0);
		// メール内容設定
		MailBean mailInfo = new MailBean();

		// 基盤メール送信
		MailUtil mail = new MailUtil();
		mailInfo.setMailType(MailBean.MAIL_TYPE_HTML);
		mailInfo.setFrom(FsPropertyUtil.getStringProperty("mail.from.address"));

		// subjectを設定
		String mailSubject = "[" + pageDeleteBean.getTitle() + "]";
		mailSubject = mailSubject + FsPropertyUtil.getStringProperty("deleteContents.mail.subject");
		mailInfo.setSubject(mailSubject);
		
		// メールアドレスをセット
		mailAddress(pageDeleteBean, mailInfo);

		//メール内容
		StringBuffer contentBuffer = new StringBuffer();
		
		//メールtop内容
		getMailHeadContents(pageDeleteBean, mailInfo, contentBuffer);
		
		//メールtop内容
		getMailContents(pageDeleteBean, contentBuffer);
		
		//メールtop内容
		getMailMiddleContents(pageDeleteBean, contentBuffer);
		
		//リンク先
		getLinkContentsInfo(pageDeleteBean, contentBuffer);

		//リンク元
		getLinkedInfo(pageDeleteBean, contentBuffer);
		
		//メール内容
		mailInfo.setContent(contentBuffer.toString());
	
		String kinowu = FsPropertyUtil.getStringProperty("expired.context");
		//送信
		mail.sendMail(mailInfo, "OutOfDayBatch", kinowu);		
		
	}
	
	/**
	 * メールアドレスをセット
	 * @param page
	 * @param mailInfo
	 */
	private void mailAddress(PageDeleteBean page, MailBean mailInfo) {
		// 宛先List
		List mailToAddressList = new ArrayList();
		
		// 宛先ユーザ氏名List
		List mailToNameList = new ArrayList();
		
		// ＣＣList
		List mailCcAddressList = new ArrayList();
		
		// ＢＣＣList
		List mailBccAddressList = new ArrayList();
		
		// 宛先List
		List toAddressList = page.getToAddressList();
		List toAddressIdList = page.getToAddressIdList();
		
		// 宛先ユーザ氏名List
		List toNameList = page.getToNameList();
		
		// ＣＣList
		List ccAddressList = page.getCcAddressList();
		
		// ＣＣユーザ氏名List
		List ccNameList = page.getCcNameList();
		
		// ＢＣＣList
		List bccAddressList = page.getBccAddressList();
		
		// ＢＣＣユーザ氏名List
		List bccNameList = page.getBccNameList();
		
		// 作成者と更新者を存在するの場合、宛先：作成者と更新者、ＣＣ：承認者、ＢＣＣ：システム担当者
		if (toAddressList.size() > 0) {
			mailToAddressList = toAddressList;
			mailToNameList = toNameList;
			mailCcAddressList = ccAddressList;
			mailBccAddressList = bccAddressList;
			setTrmailAddress(mailCcAddressList, toAddressIdList);
			
		// 作成者と更新者を存在しないの場合、
		} else {
			//　承認者を存在するの場合、宛先：承認者、ＢＣＣ：システム担当者
			if (ccAddressList.size() > 0) {
				mailToAddressList = ccAddressList;
				mailToNameList = ccNameList;
				mailBccAddressList = bccAddressList;
				setTrmailAddress(mailCcAddressList, page.getProxyUserList());
			
			//　承認者を存在しないの場合、宛先：システム担当者				
			} else {
				mailToAddressList = bccAddressList;
				mailToNameList = bccNameList;
			}
		}
		
		//宛先
		String[] mailToList = new String[mailToAddressList.size()];
		for (int i = 0; i < mailToList.length; i++) {
			mailToList[i] = (String) mailToAddressList.get(i);
		}
		
		//ＣＣ
		String[] mailToCcList = new String[mailCcAddressList.size()];
		for (int i = 0; i < mailToCcList.length; i++) {
			mailToCcList[i] = (String) mailCcAddressList.get(i);
		}
		
		//ＢＣＣ
		String[] mailToBccList = new String[mailBccAddressList.size()];
		for (int i = 0; i < mailToBccList.length; i++) {
			mailToBccList[i] = (String) mailBccAddressList.get(i);
		}
		
		//宛先ユーザ氏名文字列
		String mailToUserName = "";
		for (int i = 0; i < mailToNameList.size(); i++) {
			if (i == 0) {
				mailToUserName = (String)mailToNameList.get(i);
			} else {
				mailToUserName += "、" + (String)mailToNameList.get(i);
			}
		}
		
		mailInfo.setTo(mailToList);
		mailInfo.setCc(mailToCcList);
		mailInfo.setBcc(mailToBccList);
		mailInfo.setMailToUserName(mailToUserName);
	}
	
	/**
	 * 転送先メールアドレスを設定
	 * @param mailCcAddressList　ＣＣ
	 * @param mailToAddressIdList　宛先
	 */
	private void setTrmailAddress(List mailCcAddressList, List mailToAddressIdList){
		String userIdArr = "";
		for (int i=0; i< mailToAddressIdList.size(); i++) {
			String userId = (String)mailToAddressIdList.get(i);
			if(StringUtil.isEmpty(userIdArr)) {
				userIdArr = "'" + userId + "'";
			} else {
				userIdArr += ",'" + userId + "'";
			}
		}

		// 転送先メールアドレスリストを取得
		if (!StringUtil.isEmpty(userIdArr)){
			List<String> trmailAddressList = pageDao.getTrmailAddressList(userIdArr);
			if (trmailAddressList != null && trmailAddressList.size() > 0){
				for (String trmailAddress : trmailAddressList){
					if (StringUtil.isNotBlank(trmailAddress)){
						mailCcAddressList.add(trmailAddress);
					}
				}
			}
		}
	}
	
	/**
	 * メールヘッダー
	 * @param page
	 * @param mailInfo
	 * @param contentBuffer
	 */
	private void getMailHeadContents(PageDeleteBean page, MailBean mailInfo, StringBuffer contentBuffer) {
		String mailToUserName = mailInfo.getMailToUserName();
		contentBuffer.append(mailToUserName);
		contentBuffer.append(FsPropertyUtil.getStringProperty("deleteContents.mail.headinfo1"));
		contentBuffer.append("<br><br>");
		contentBuffer.append(FsPropertyUtil.getStringProperty("deleteContents.mail.headinfo2"));
		contentBuffer.append("<br><br>");
	}
	
	/**
	 * メールContents
	 * @param page
	 * @param contentBuffer
	 */
	private void getMailContents(PageDeleteBean page, StringBuffer contentBuffer) {
		contentBuffer.append(FsPropertyUtil.getStringProperty("deleteContents.mail.contentsinfo1"));
		contentBuffer.append(page.getTitle());
		contentBuffer.append("<br>");
		contentBuffer.append(FsPropertyUtil.getStringProperty("deleteContents.mail.contentsinfo2"));
		contentBuffer.append(page.getPageLocation());
		contentBuffer.append("<br><br>");
	}
	
	/**
	 * @param Page page
	 * @param StringBuffer contentBuffer
	 * @function メールMiddle
	 */
	private void getMailMiddleContents(PageDeleteBean page, StringBuffer contentBuffer) {
		contentBuffer.append(FsPropertyUtil.getStringProperty("deleteContents.mail.middleinfo1"));
		contentBuffer.append("<br><br>");
		contentBuffer.append(FsPropertyUtil.getStringProperty("deleteContents.mail.middleinfo2"));
		contentBuffer.append("<br>");
		contentBuffer.append(FsPropertyUtil.getStringProperty("deleteContents.mail.middleinfo3"));
		contentBuffer.append("<br><br>");
	}
	
	/**
	 * リンク先メール内容
	 * @param page
	 * @param contentBuffer
	 */
	private void getLinkContentsInfo(PageDeleteBean page, StringBuffer contentBuffer) {
		List linkContentsList = page.getLinkContentsList();
		boolean contentflag = getContentFlag(linkContentsList, "0");
		if (contentflag) {
			contentBuffer.append(FsPropertyUtil.getStringProperty("deleteContents.mail.linkinfo"));
			contentBuffer.append("<br>");
			
			for(int j = 0; j < linkContentsList.size(); j++) {
				PageLinkBean linkContentsPage = (PageLinkBean) linkContentsList.get(j);
				if (linkContentsPage.isSendFlag()) {
					// リンクキーワード
					contentBuffer.append(FsPropertyUtil.getStringProperty("delete.mail.maininfo1"));
					contentBuffer.append(linkContentsPage.getLinkName());
					contentBuffer.append("<br>");
					
					//リンク先コンテンツ名
					contentBuffer.append("　　　");
					contentBuffer.append(FsPropertyUtil.getStringProperty("delete.mail.maininfo3"));
					
					//リンク先コンテンツ名
					contentBuffer.append("「" + linkContentsPage.getTitle() + "」");
					
					//公開期限
					contentBuffer.append("（");
					contentBuffer.append(FsPropertyUtil.getStringProperty("delete.mail.maininfo4"));
					contentBuffer.append(linkContentsPage.getStartDate());
					contentBuffer.append("～");
					contentBuffer.append(linkContentsPage.getEndDate());
					contentBuffer.append("）");
					contentBuffer.append("<br>");
					
					//リンク先作成者、リンク先更新者
					String createUserName = linkContentsPage.getCreateUserName();
					String updateUserName = linkContentsPage.getUpdateUserName();
					String createUser = FsPropertyUtil.getStringProperty("delete.mail.maininfo6");
					String updateUser = FsPropertyUtil.getStringProperty("delete.mail.maininfo7");
					String temp = "";
					if (!"".equals(createUserName)) {
						temp = "　　　" + createUser + createUserName + "<br>";
					}
					if (!"".equals(updateUserName)) {
						temp += "　　　" + updateUser + updateUserName;
					}
					if (temp.endsWith("<br>")) {
						temp = temp.substring(0, temp.length() - 4);
					}
					contentBuffer.append(temp);
					contentBuffer.append("<br>");
				}
			}
			contentBuffer.append("<br>");
		}
	}
	
	/**
	 * リンク元のコラムセット
	 * @param page
	 * @param contentBuffer
	 * @param divFlag
	 */
	private void getStr(PageLinkBean page, StringBuffer contentBuffer, String divFlag) {
		// 実のみコンテンツ名
		contentBuffer.append("・「" + page.getTitle() + "」");
		
		//実のみ公開期限
		contentBuffer.append("（");
		contentBuffer.append(FsPropertyUtil.getStringProperty("delete.mail.maininfo4"));
		contentBuffer.append(page.getStartDate());
		contentBuffer.append("～");
		contentBuffer.append(page.getEndDate());
		contentBuffer.append("）");
		
		if ("R".equals(divFlag)) {
			contentBuffer.append(FsPropertyUtil.getStringProperty("delete.mail.reserveinfo"));
		} else if ("B".equals(divFlag)) {
			contentBuffer.append(FsPropertyUtil.getStringProperty("delete.mail.bothinfo"));
		}
		contentBuffer.append("<br>");
		contentBuffer.append("　　　");
		
		//リンク元コンテンツ配置先
		contentBuffer.append(FsPropertyUtil.getStringProperty("delete.mail.maininfo2"));
		contentBuffer.append(page.getPageLocation());
		contentBuffer.append("<br>");
		
		//実のみ作成者、リンク先更新者
		String createUserName = page.getCreateUserName();
		String updateUserName = page.getUpdateUserName();
		String createUser = FsPropertyUtil.getStringProperty("delete.mail.maininfo6");
		String updateUser = FsPropertyUtil.getStringProperty("delete.mail.maininfo7");
		String temp = "";
		if (!"".equals(createUserName)) {
			temp = "　　　" + createUser + createUserName + "<br>";
		}
		if (!"".equals(updateUserName)) {
			temp += "　　　" + updateUser + updateUserName;
		}
		if (temp.endsWith("<br>")) {
			temp = temp.substring(0, temp.length() - 4);
		}
		contentBuffer.append(temp);
		contentBuffer.append("<br>");

		//リンク元コンテンツ、リンクキーワード
		contentBuffer.append("　　　");
		contentBuffer.append(FsPropertyUtil.getStringProperty("delete.mail.maininfo5"));
		contentBuffer.append(page.getLinkName());
		contentBuffer.append("<br>");
	}
	
	/**
	 * @param Page page
	 * @param StringBuffer contentBuffer
	 * @param String flag
	 * @function リンク元メール内容
	 */
	private void getLinkedInfo(PageDeleteBean page, StringBuffer contentBuffer) {
		List linkedList = page.getLinkedList();
		boolean contentflag = getContentFlag(linkedList, "1");
		if (contentflag) {
			contentBuffer.append(FsPropertyUtil.getStringProperty("deleteContents.mail.linked.info"));
			contentBuffer.append("<br>");
			
			//実のみList
			List<PageLinkBean> linkList = (List)linkedList.get(0);
			
			//予約のみList
			List<PageLinkBean> linkReserveList = (List)linkedList.get(1);
			
			//両方List
			List<PageLinkBean> linkBothPageList = (List)linkedList.get(2);
			
			for(int i = 0; i < linkList.size(); i++) {
				PageLinkBean linkedPage = linkList.get(i);
				if (linkedPage.isSendFlag()) {
					getStr(linkedPage, contentBuffer, "");
				}
			}
			
			for(int i = 0; i < linkReserveList.size(); i++) {
				PageLinkBean linkedPageReserve = linkReserveList.get(i);
				if (linkedPageReserve.isSendFlag()) {
					getStr(linkedPageReserve, contentBuffer, "R");
				}
			}
			
			for(int i = 0; i < linkBothPageList.size(); i++) {
				PageLinkBean bothPage = linkBothPageList.get(i);
				getStr(bothPage, contentBuffer, "B");
			}
		}
	}
	
	/**
	 * ログを出力
	 * @param list
	 * @return
	 */
	public String deleteMailLog(List list) {
		PageDeleteBean page = (PageDeleteBean)list.get(0);
		String pageId = page.getPageId();
		String endDate = page.getEndDate();
		String createUserId = page.getCreateBy();
		String updateUserId = page.getUpdateBy();
		String title = page.getTitle();
		
		//承認者List
		List<String> proxyUserList = page.getProxyUserList();
		
		//承認者str
		String proxyUserIdStr = "";
		for (int j = 0; j < proxyUserList.size(); j++ ) {
			if (j == 0) {
				proxyUserIdStr = proxyUserList.get(j);
			} else {
				proxyUserIdStr +=  "," + proxyUserList.get(j);
			}
		}
		
		String confirmFlag = page.getConfirmFlag();
		
		String contents = "";
		boolean linkedFlag = getContentFlag(page.getLinkedList(), "1");
		if (linkedFlag) {
			contents = "リンク元/";
		}
		
		boolean linkContentsFlag = getContentFlag(page.getLinkContentsList(), "0");
		if (linkContentsFlag) {
			contents += "リンク先";
		}

		if(contents.endsWith("/")) {
			contents = contents.substring(0, contents.length() - 1);
		}
		
		StringBuffer message = new StringBuffer();
		message.append("[コンテンツを削除]");
		message.append(" - PAGE_ID[");
		message.append(pageId);
		message.append("],公開終了日[");
		message.append(endDate);
		message.append("],作成者[");
		message.append(createUserId);
		message.append("],更新者[");
		message.append(updateUserId);
		message.append("],承認者[");
		message.append(proxyUserIdStr);
		if ("1".equals(confirmFlag)) {
			message.append("],ステータス[編集中]");
		} else {
			message.append("],ステータス[公開中]");
		}
		message.append(",対象コンテンツ[");
		message.append(title);
		message.append("],対象[");
		message.append(contents);
		message.append("]");
		return message.toString();
	}
	
	/**
	 * テンプレート相関チェック
	 * @param formBean
	 */
	public void templateCheck(PageFormBean formBean) {
		// コンテンツ閲覧権限ユーザ情報相関チェック
		boolean userNoExistFlag = false;
		if (formBean.getAuthUserList() != null){
			String userId = "";
			for (int i=0; i<formBean.getAuthUserList().size(); i++) {
				AuthUserBean authUserBean = formBean.getAuthUserList().get(i);
				if("1".equals(authUserBean.getUserDeleteFlag())) {
					continue;
				} else {
					if(StringUtil.isEmpty(userId)) {
						userId = "'" + authUserBean.getUserId() + "'";
					} else {
						userId += ",'" + authUserBean.getUserId() + "'";
					}
				}
			}
			
			// ユーザ情報存在リストを取得
			List<String> userExistList = null;
			if (!StringUtil.isEmpty(userId)){
				userExistList = templatePageDao.getUserExistList(userId);
			}
			if (userExistList == null){
				userExistList = new ArrayList<String>();
			}
			
			for (int i=0; i<formBean.getAuthUserList().size(); i++) {
				AuthUserBean authUserBean = formBean.getAuthUserList().get(i);
				String authUserId = authUserBean.getUserId();
				if("1".equals(authUserBean.getUserDeleteFlag())) {
					continue;
				} else {
					if(!userExistList.contains(authUserId)) {
						authUserBean.setUserDeleteFlag("1");
						userNoExistFlag = true;
					}
				}
			}			
		}
		
		// 更新代行者情報相関チェック
		boolean proxyNoExistFlag = false;
		if (formBean.getProxyUserList() != null){
			String proxyUserId = "";
			for (int i=0; i<formBean.getProxyUserList().size(); i++) {
				ProxyUserBean proxy = formBean.getProxyUserList().get(i);
				if("1".equals(proxy.getProxyDeleteFlag())) {
					continue;
				} else {
					if(StringUtil.isEmpty(proxyUserId)) {
						proxyUserId = "'" + proxy.getProxyUserId() + "'";
					} else {
						proxyUserId += ",'" + proxy.getProxyUserId() + "'";
					}
				}
			}
			
			// ユーザ情報存在リストを取得
			List proxyExistList = null;
			if (!StringUtil.isEmpty(proxyUserId)){
				proxyExistList = templatePageDao.getUserExistList(proxyUserId);
			}
			if (proxyExistList == null){
				proxyExistList = new ArrayList<String>();
			}
			
			for (int i=0; i<formBean.getProxyUserList().size(); i++) {
				ProxyUserBean proxy = formBean.getProxyUserList().get(i);
				String proxyUserIdTemp = proxy.getProxyUserId();
				if("1".equals(proxy.getProxyDeleteFlag())) {
					continue;
				} else {
					if(!proxyExistList.toString().contains(proxyUserIdTemp)) {
						proxy.setProxyDeleteFlag("1");
						proxyNoExistFlag = true;
					}
				}
			}			
		}
		
		// コンテンツ閲覧権限トップグループ情報相関チェック
		boolean topgroupNoExistFlag = false;
		if (formBean.getAuthGroupList() != null){
			String topgroupId = "";
			for (int i=0; i<formBean.getAuthGroupList().size(); i++) {
				AuthGroupBean authGroupBean = formBean.getAuthGroupList().get(i);
				if("1".equals(authGroupBean.getGroupDeleteFlag())) {
					continue;
				} else {
					if(StringUtil.isEmpty(topgroupId)) {
						topgroupId = authGroupBean.getTopGroupId();
					} else {
						topgroupId += "," + authGroupBean.getTopGroupId();
					}
				}
			}
			
			// トップグループ情報存在リストを取得
			List<String> groupList = null;
			if (!StringUtil.isEmpty(topgroupId)){
				groupList = templatePageDao.getTopGroupExistList(topgroupId);
			}
			if (groupList == null){
				groupList = new ArrayList<String>();
			}

			for (int i=0; i<formBean.getAuthGroupList().size(); i++) {
				AuthGroupBean authGroupBean = formBean.getAuthGroupList().get(i);
				String TopGroupIdTemp = authGroupBean.getTopGroupId();
				if("1".equals(authGroupBean.getGroupDeleteFlag())) {
					continue;
				} else {
					if(!groupList.toString().contains(TopGroupIdTemp)) {
						authGroupBean.setGroupDeleteFlag("1");
						topgroupNoExistFlag = true;
					}
				}
			}
		}
		
		// コンテンツリンク情報
		boolean linkNoExistFlag = false;
		if (formBean.getPageLinkList() != null){
			String linkUrl = "";
			String linkPageId = "";
			for (int i=0; i<formBean.getPageLinkList().size(); i++) {
				PageLinkBean pageLinkBean = formBean.getPageLinkList().get(i);
				linkUrl = pageLinkBean.getLinkUrl();
				if (!StringUtil.isEmpty(linkUrl)) {
					if(linkUrl.startsWith(formBean.getContextPath()) && linkUrl.indexOf("pageId=") > 0) {
						linkUrl = linkUrl.substring(linkUrl.indexOf("pageId=") + 7);
						if("1".equals(pageLinkBean.getLinkDeleteFlag())) {
							continue;
						} else {
							if(linkUrl.startsWith("0")) {
								linkUrl = "0";
							} else {
								if (linkUrl.length() > 13) {
									linkUrl = linkUrl.substring(0, 13);
								} else {
									continue;
								}
							}
							if("".equals(linkPageId)) {
								linkPageId = "'" + linkUrl + "'";
							} else {
								linkPageId += ",'" + linkUrl + "'";
							}
						}
					}
				}
			}
			
			// ページリンク情報存在リストを取得
			List<String> linkExistList = null;
			if (!StringUtil.isEmpty(linkPageId)){
				linkExistList = templatePageDao.getPageLinkExistList(linkPageId);
			}
			if (linkExistList == null){
				linkExistList = new ArrayList<String>();
			}
			
			for (int i=0; i<formBean.getPageLinkList().size(); i++) {
				PageLinkBean pageLinkBean = formBean.getPageLinkList().get(i);
				linkUrl = pageLinkBean.getLinkUrl();
				if (linkUrl != null) {
					if(linkUrl.startsWith(formBean.getContextPath()) && linkUrl.indexOf("pageId=") > 0) {
						linkUrl = linkUrl.substring(linkUrl.indexOf("pageId=") + 7);
						if("1".equals(pageLinkBean.getLinkDeleteFlag())) {
							continue;
						} else {
							if(linkUrl.startsWith("0")) {
								linkUrl = "0";
							} else {
								if (linkUrl.length() > 13) {
									linkUrl = linkUrl.substring(0, 13);
								} else {
									continue;
								}
							}
							if(!linkExistList.toString().contains(linkUrl)) {
								pageLinkBean.setLinkDeleteFlag("1");
								linkNoExistFlag = true;
							}
						}
					}
				}
			}			
		}
		
		// 親ページID
		String strHaiTiSaki = getPageLocation(formBean.getPPageId());
		if (StringUtil.isEmpty(strHaiTiSaki)){
			formBean.setPPageId("");
		}
		
		// 発信部署
		String depId = formBean.getSourceDepartment();
		String departmentName = templatePageDao.getSourceDeptName(depId,null);
		if(StringUtil.isEmpty(departmentName)) {
			formBean.setTemplateNoExistDepartmentId(depId);
			String templateNoExistDepartmentName = templatePageDao.getSourceDeptName(depId, "1");
			if (!StringUtil.isEmpty(templateNoExistDepartmentName)){
				List<LabelValueBean> sourceDeptDropList = formBean.getSourceDeptDropList();
				if (sourceDeptDropList == null){
					sourceDeptDropList = new ArrayList<LabelValueBean>();
				}
				LabelValueBean departmentBean = new LabelValueBean();
				departmentBean.setValue(depId);
				departmentBean.setLabel(templateNoExistDepartmentName);
				sourceDeptDropList.add(departmentBean);
			}
		} else {
			formBean.setTemplateNoExistDepartmentId("");
		}

		StringBuffer message = new StringBuffer();
		if(userNoExistFlag || proxyNoExistFlag || topgroupNoExistFlag || linkNoExistFlag || !StringUtil.isEmpty(formBean.getTemplateNoExistDepartmentId())) {
			if(topgroupNoExistFlag) {
				message.append("\\\\n\\\\n・公開するグループ");
			} 
			if(userNoExistFlag) {
				message.append("\\\\n\\\\n・公開する個人");
			} 
			if(linkNoExistFlag) {
				message.append("\\\\n\\\\n・リンク添付");
			} 
			if(proxyNoExistFlag) {
				message.append("\\\\n\\\\n・承認者");
			} 
			if(!StringUtil.isEmpty(formBean.getTemplateNoExistDepartmentId())) {
				message.append("\\\\n\\\\n・発信部署");
			}
			throw new BusinessServiceException("MSGOE109", new String[]{message.toString()});
		}
	}
	
	/**
	 * 排他チェック
	 * @param pageBean
	 * @param dataKb　0:マスタ　1：予約 2：テンプレート
	 */
	public void optimisticLockCheck(String pageId, String dataKb) {
		// DB共通情報取得
		BaseBean dbCommonInfo = null;
		String errMessage = "コンテンツ";
		// テンプレートの場合
		if ("2".equals(dataKb)){
			errMessage = "テンプレートコンテンツ";
			dbCommonInfo = commonDao.getDbCommonInfo("TEMPLATE_PAGE", "TEMPLATE_PAGE_ID", pageId);
		}else if ("1".equals(dataKb)){
			// 予約の場合
			dbCommonInfo = commonDao.getDbCommonInfo("PAGE_RESERVE", "PAGE_ID", pageId);
		} else{
			// マスタの場合
			dbCommonInfo = commonDao.getDbCommonInfo("PAGE", "PAGE_ID", pageId);
		}
		//　該当ページが削除された場合、エラー
		if (dbCommonInfo == null){
			String message = getErrorMessage("MSGOE157", new String[]{errMessage, ""});
			OptimisticLockException e = new OptimisticLockException(message);
			e.getErrorMessageList().add(message);
			throw e;
		} else {
			String updateUserName = StringUtil.trimer(' ',dbCommonInfo.getUpdateUserName());
			// 該当ページが論理削除された場合、エラー
			if ("1".equals(dbCommonInfo.getDeleteFlag())){
				String message = getErrorMessage("MSGOE157", new String[]{errMessage, updateUserName + "さんにより"});
				OptimisticLockException e = new OptimisticLockException(message);
				e.getErrorMessageList().add(message);
				throw e;
			} else{
				// 他ユーザにより更新された場合、エラー
				String message = getErrorMessage("MSGOE158", new String[]{updateUserName});
				OptimisticLockException e = new OptimisticLockException(message);
				e.getErrorMessageList().add(message);
				throw e;
			}
		}
	}
	
	/**
	 * ZIPファイルチェック
	 * @throws Exception 
	 */
	private void zipFileCheck(String zipuUploadName, String mainFile) throws Exception{
		mainFile = StringUtil.isEmpty(mainFile) ? "":mainFile.toLowerCase();
		ZipFile zfile = new ZipFile(zipuUploadName);
		Enumeration zListCheck = zfile.getEntries();
		ZipEntry ze = null;
		boolean hasMain = false;
		while (zListCheck.hasMoreElements()) {
			ze = (ZipEntry) zListCheck.nextElement();
			String fileName = ze.getName();
			
			// ファイル名チェック
			isAlphanumeric(fileName);

			if (ze.getName().toLowerCase().equals(mainFile)) {
				hasMain = true;
			}
		}
		// ZIPファイルにメインファイルが含められた場合、エラー
		if (!hasMain) {
			throw new BusinessServiceException("MSGOE164");
		}
	}
	
	/**
	 * ファイル名チェック
	 * @param str
	 * @return
	 */
	private void isAlphanumeric(String str) {
		if (str == null) {
			return;
		}
		int sz = str.length();
		if(str.getBytes().length > str.length() && str.getBytes().length!=str.length()){
			throw new BusinessServiceException("MSGOE161");
		}
		String str_HALFKANA = "ｧｨｩｪｫｬｭｮｯｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝｶﾞｷﾞｸﾞｹﾞｺﾞｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟｳﾞｰ";
		for (int i = 0; i < sz; i++) {			
			if(str_HALFKANA.contains(String.valueOf(str.charAt(i)))){		
				throw new BusinessServiceException("MSGOE161");
			}						
		}
		String special = "`!@#$%^&()+=,'~\\[]{}";
		String specialRls = "`!@#\\$%^&()+=,'~\\\\[]{}";
		for (int i = 0; i < sz; i++) {			
			if (!Character.isLetterOrDigit(str.charAt(i))){
				if(special.contains(String.valueOf(str.charAt(i)))){
					throw new BusinessServiceException("MSGOE163", new String[]{specialRls});
				}
			}			
		}		
	}
	
	/**
	 * 特殊文字変換
	 * @param contents
	 * @return
	 */
	public String contentReplace(String contents){
		if (StringUtil.isNotEmpty(contents)){
//			contents = contents.replaceAll("&","&amp;");
		}
		return contents;
	}
	
	/**
	 * リンクファイル名を取得
	 */
	public String getLinkFileName(String pageId, String isReserve, String isTemplateFrom, String index, PageFormBean formBean) throws IOException {
		List<PageAttachmentBean> attachmentList = new ArrayList<PageAttachmentBean>();
		if ("1".equals(isTemplateFrom)) {
			formBean.setTemplatePageId(pageId);
			attachmentList = templatePageDao.getTemplatePageAttachList(formBean, "");
		} else {
			attachmentList = pageDownloadDao.getAttachmentList(pageId, isReserve);
		}
		for (int i = 0; i < attachmentList.size(); i++) {
			String fileName = StringUtil.nullToBlank(attachmentList.get(i).getAttachmentFileUrl());
			if(fileName!=null && !"".equals(fileName)) {
				try {
					fileName = fileName.substring(fileName.lastIndexOf("/") + 1,
							fileName.lastIndexOf("."));
				} catch (Exception e) {
				}
			}
			if ((pageId + index).equals(fileName)) {
				return attachmentList.get(i).getAttachmentName();
			}
		}
		
		return "";
	}
	
}