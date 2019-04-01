package jp.co.fourseeds.fsnet.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.fourseeds.fsnet.beans.page.PageAttachmentBean;
import jp.co.fourseeds.fsnet.beans.page.PageFormBean;
import jp.co.fourseeds.fsnet.beans.page.PageRateItemBean;
import jp.co.fourseeds.fsnet.beans.page.PageBean;
import jp.co.fourseeds.fsnet.common.ConstantContainer;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.TemplatePageDao;

import jp.co.common.frame.service.BaseBusinessService;
import jp.co.common.frame.util.FileUtil;
import jp.co.common.frame.util.WrappedBeanUtil;
import jp.co.common.frame.util.prop.FsPropertyUtil;

/**
 * ホーム機能サービス実装クラス
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2015/11/24		    NTS        	       作成
 *
 **/
@SuppressWarnings(value={"unchecked"})
@Component
public class TemplatePageService extends BaseBusinessService{

	@Autowired
	private TemplatePageDao templatePageDao;
	
	/**
	 * テンプレートコンテンツ情報を取得
	 * @param user
	 */
	public PageBean getTemplatePage(String pageId, String currentUserId) {
		return templatePageDao.getTemplatePage(pageId, currentUserId);
	}
	
	/**
	 * テンプレートコンテンツ情報を取得
	 * @param user
	 */
	public PageBean getTemplatePagebyID(String templatePageId) {
		return templatePageDao.getTemplatePagebyID(templatePageId);
	}
	
	/**
	 * テンプレートテンプレートコンテンツ情報の取得
	 * @param param　
	 * @return　
	 */
	public PageFormBean getTemplatePageForm(String templatePageId) throws Exception {
		
		PageFormBean formBean = null;

		// コンテンツ情報を取得
		PageBean pageBean = getTemplatePagebyID(templatePageId);
		if (pageBean != null) {
			formBean = new PageFormBean();
			WrappedBeanUtil.copyProperties(formBean, pageBean);
			
			// テンプレートコンテンツリンク情報を取得
			formBean.setPageLinkList(templatePageDao.getTemplatePageLinkList(templatePageId));
			
			// テンプレートコンテンツ添付ファイル情報を取得
			formBean.setPageAttachmentList(templatePageDao.getTemplatePageAttachList(templatePageId));
			
			// テンプレートコンテンツ閲覧権限トップグループ情報を取得
			formBean.setAuthGroupList(templatePageDao.getTemplateAuthGroupList(templatePageId));
			
			// テンプレートコンテンツ閲覧権限ユーザ情報を取得
			formBean.setAuthUserList(templatePageDao.getTemplateAuthUserList(templatePageId));
			
			// テンプレート更新代行者情報情報を取得
			formBean.setProxyUserList(templatePageDao.getTemplateProxyUserList(templatePageId));
			
			// テンプレートコンテンツ評価リスト
			formBean.setPageRateItemBeanList(templatePageDao.getTemplatePageRateItemList(templatePageId));
			
			// テンプレートコンテンツコメント管理者リスト
			formBean.setPageCommentAdminList(templatePageDao.getTemplateCommentAdminList(templatePageId));
			
			// テンプレートコンテンツコメント管理者選択リスト
			formBean.setPageCommentAdminOptionList(templatePageDao.getTemplateCommentAdminOptList(templatePageId));
		}

		return formBean;
		
	}
	
	/**
	 * テンプレートコンテンツ評価アイテム情報を取得
	 * @param user
	 */
	public List<PageRateItemBean> getTemplatePageRateItemList(String templatePageId) {
		return templatePageDao.getTemplatePageRateItemList(templatePageId);
	}
	
	/**
	 * テンプレート保存処理
	 * @param formBean
	 * @throws Exception 
	 */
	public void insertTemplate(PageFormBean formBean) throws Exception{

		// ページID
		String pageId = formBean.getPageId();
		// ログインユーザID
		String currentUserId = StringUtil.nullToBlank(getLoginUserBean().getUserId());

		// 登録前、テンプレートコンテンツ相関情報を論理削除
		// テンプレートコンテンツ情報を論理削除
		templatePageDao.updateTemplatePageExtendInvalid("TEMPLATE_PAGE", pageId, currentUserId);
		// テンプレートコンテンツ添付ファイル情報を論理削除
		templatePageDao.updateTemplatePageExtendInvalid("TEMPLATE_PAGE_ATTACHMENT", pageId, currentUserId);
		// テンプレートコンテンツリンク情報を論理削除
		templatePageDao.updateTemplatePageExtendInvalid("TEMPLATE_PAGE_LINK", pageId, currentUserId);
		// テンプレートコンテンツ閲覧権限トップグループ情報を論理削除
		templatePageDao.updateTemplatePageExtendInvalid("TEMPLATE_AUTH_LEADING_GROUP", pageId, currentUserId);
		// テンプレートコンテンツ閲覧権限ユーザ情報を論理削除
		templatePageDao.updateTemplatePageExtendInvalid("TEMPLATE_AUTH_USER", pageId, currentUserId);
		// テンプレート更新代行者情報を論理削除
		templatePageDao.updateTemplatePageExtendInvalid("TEMPLATE_UPDATE_PROXY_USER", pageId, currentUserId);
		// テンプレートコンテンツ評価アイテム情報を論理削除
		templatePageDao.updateTemplatePageExtendInvalid("TEMPLATE_PAGE_RATE_ITEM", pageId, currentUserId);
		// テンプレートコンテンツコメント管理者情報を論理削除
		templatePageDao.updateTemplatePageExtendInvalid("TEMPLATE_PAGE_COMMENT_ADMIN", pageId, currentUserId);

		// テンプレートコンテンツ相関情報を登録
		// テンプレートコンテンツ情報を登録
		templatePageDao.insertTemplatePage(formBean, currentUserId);
		// テンプレートコンテンツ添付ファイル情報を登録
		templatePageDao.insertTemplatePageAttachment(formBean, currentUserId);
		// テンプレートコンテンツリンク情報を登録
		templatePageDao.insertTemplatePageLink(formBean, currentUserId);
		// テンプレートコンテンツ閲覧権限トップグループ情報を登録
		templatePageDao.insertTemplateAuthLeadingGroup(formBean, currentUserId);
		// テンプレートコンテンツ閲覧権限ユーザ情報を登録
		templatePageDao.insertTemplateAuthUser(formBean, currentUserId);
		// テンプレート更新代行者情報を登録
		templatePageDao.insertTemplateUpdateProxyUser(formBean, currentUserId);
		
		// コンテンツ評価が「する」の場合、評価相関を実行
		if ("1".equals(formBean.getEvaluationFlag())) {
			// テンプレートコンテンツ評価アイテム情報を登録
			templatePageDao.insertTemplatePageEvaluation(formBean, currentUserId);
			// テンプレートコンテンツコメント管理者情報を登録
			templatePageDao.insertTemplatePageCommentAdmin(formBean, currentUserId);
		}
		
		// テンプレート相関操作
		templateFileRelated(formBean, currentUserId);
	}

	/**
	 * テンプレート相関操作
	 * @param formBean
	 * @param currentUserId
	 * @throws Exception 
	 */
	private void templateFileRelated(PageFormBean formBean, String currentUserId) throws Exception {
		try {
			// テンプレートページID
			String templatePageId = formBean.getTemplatePageId();
			// ページID
			String pageId = formBean.getPageId();

			// コピー元コンテンツのHTMLファイル格納パス
			String htmlfilefrom = "";
			// コピー先テンプレートコンテンツのHTMLファイル格納パス
			String htmlfileto = "";
			String attfilefrom = "";
			String attfileto = "";
			
			// 予約の場合
			if("1".equals(formBean.getIsReserve())) {
				htmlfilefrom = FsPropertyUtil.getStringProperty("htmlFile.temp.path");
				attfilefrom = FsPropertyUtil.getStringProperty("server.upload.temp.path");
			} else {
				htmlfilefrom = FsPropertyUtil.getStringProperty("htmlFile.path");
				attfilefrom = FsPropertyUtil.getStringProperty("server.upload.path");
			}
			String fileSeparator = File.separator;
			htmlfileto = FsPropertyUtil.getStringProperty("template.htmlFile.path") + fileSeparator + currentUserId;
			attfileto = FsPropertyUtil.getStringProperty("template.server.upload.path") + fileSeparator + currentUserId;
			// 既存のフォルダを削除
			FileUtil.deletePurgeFile(htmlfileto, templatePageId);
			FileUtil.deletePurgeFile(attfileto, templatePageId);
			// 動的コンテンツの場合
			if (ConstantContainer.PAGE_DYNAMIC.equals(formBean.getHtmlFlag())){
				List<PageAttachmentBean> fileList = templatePageDao.getTemplatePageAttachList(formBean, currentUserId);
				if (fileList != null){
					for (PageAttachmentBean bean : fileList) {
						String fileUrl = bean.getAttachmentFileUrl();
						String toFileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
						String fromFileName = pageId + fileUrl.substring(fileUrl.lastIndexOf(templatePageId) + 13);
						FileUtil.copyFile(attfilefrom, attfileto, fromFileName, toFileName);
					}
				}
				
				// コンテンツHTMLをテンプレートフォルダにコピー
				FileUtil.copyHtmlPageToTemplate(htmlfilefrom, htmlfileto, formBean.getPageId(), templatePageId);
			} else{
				//　静的コンテンツの場合
				// 静的テンプレートコンテンツファイル拡張子
				PageBean templatePageBean = templatePageDao.getTemplatePage(pageId, currentUserId);
				String file_suffix = templatePageBean.getFileSuffix();
	            if (file_suffix != null && !"".equals(file_suffix) && file_suffix.indexOf(templatePageId) < 0) {                    
	                String toFileName = templatePageId + file_suffix;
	                String fromFileName = pageId + file_suffix;
	                File file = new File(htmlfilefrom + File.separator + fromFileName);
	                if (file.isFile()) {
	                    FileUtil.copyFile(htmlfilefrom, htmlfileto, fromFileName, toFileName);
	                }
	            } else {
	                FileUtil.copyStaticForTemplate(htmlfilefrom, htmlfileto, pageId, templatePageId);
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
	 * テンプレート削除処理
	 * @param formBean
	 * @throws Exception 
	 */
	public void deleteTemplate(PageFormBean formBean) throws Exception{

		// テンプレートページID
		String templatePageId = formBean.getTemplatePageId();
		// ログインユーザID
		String currentUserId = StringUtil.nullToBlank(getLoginUserBean().getUserId());

		// テンプレートコンテンツ相関情報を論理削除
		// テンプレートコンテンツ情報を論理削除
		templatePageDao.updateTemplatePageExtendInvalidById("TEMPLATE_PAGE", templatePageId, currentUserId);
		// テンプレートコンテンツ添付ファイル情報を論理削除
		templatePageDao.updateTemplatePageExtendInvalidById("TEMPLATE_PAGE_ATTACHMENT", templatePageId, currentUserId);
		// テンプレートコンテンツリンク情報を論理削除
		templatePageDao.updateTemplatePageExtendInvalidById("TEMPLATE_PAGE_LINK", templatePageId, currentUserId);
		// テンプレートコンテンツ閲覧権限トップグループ情報を論理削除
		templatePageDao.updateTemplatePageExtendInvalidById("TEMPLATE_AUTH_LEADING_GROUP", templatePageId, currentUserId);
		// テンプレートコンテンツ閲覧権限ユーザ情報を論理削除
		templatePageDao.updateTemplatePageExtendInvalidById("TEMPLATE_AUTH_USER", templatePageId, currentUserId);
		// テンプレート更新代行者情報を論理削除
		templatePageDao.updateTemplatePageExtendInvalidById("TEMPLATE_UPDATE_PROXY_USER", templatePageId, currentUserId);
		// テンプレートコンテンツ評価アイテム情報を論理削除
		templatePageDao.updateTemplatePageExtendInvalidById("TEMPLATE_PAGE_RATE_ITEM", templatePageId, currentUserId);
		// テンプレートコンテンツコメント管理者情報を論理削除
		templatePageDao.updateTemplatePageExtendInvalidById("TEMPLATE_PAGE_COMMENT_ADMIN", templatePageId, currentUserId);

	}	
	
}