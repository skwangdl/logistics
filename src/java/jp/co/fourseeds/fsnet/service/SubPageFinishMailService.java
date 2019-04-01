package jp.co.fourseeds.fsnet.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.MailBean;
import jp.co.fourseeds.fsnet.beans.SubPageFinishMailFormBean;
import jp.co.fourseeds.fsnet.beans.department.DepartmentBean;
import jp.co.fourseeds.fsnet.beans.page.PageFormBean;
import jp.co.fourseeds.fsnet.beans.page.ProxyUserBean;
import jp.co.fourseeds.fsnet.common.util.CommonUtil;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.SubPageFinishMailDao;

import jp.co.common.frame.service.BaseBusinessService;
import jp.co.common.frame.util.FreemarkerUtil;
import jp.co.common.frame.util.MailUtil;
import jp.co.common.frame.util.prop.FsPropertyUtil;

/**
 * メール送信画面サービス実装クラス
 * 
 * File Name: SubPageFinishMailService.java 
 * Created: 2015/12/22
 * Original Author: NTS 程
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2015/09/22		    NTS        	       作成
 *
 **/
@Component
public class SubPageFinishMailService extends BaseBusinessService{

	@Autowired
	private SubPageFinishMailDao subPageFinishMailDao;

	@Autowired
	private PageService pageService;

	@Autowired
	private SendMailService sendMailService;
	
	@Autowired
	private DepartmentService departmentService;
	
	/**
	 * メール送信初期化
	 * @param formBean
	 * @param request
	 * @throws Exception
	 */
	public void getSendMailInfo(SubPageFinishMailFormBean formBean, HttpServletRequest request) throws Exception{
		String pageId = formBean.getPageId();
		String onEditFlag = formBean.getOnEditFlag();
		
		// 編集中のコンテンツ情報
		PageFormBean pageEdit;
		
		String fileUrl = "http://" + request.getServerName() + request.getContextPath() + "/login_mailLogin.do?pageId=" + formBean.getPageId();
		
		if("1".equals(onEditFlag)){
			// 公開中コンテンツ情報取得
			PageFormBean pageOpen = pageService.getPageForm(pageId);
			if (pageOpen == null){
				// メール送信成功フラグをセット
				request.setAttribute("pageExist", "N");
				return;
			}
			pageOpen.setPageLocation(pageService.getPageLocationOpen(pageId));					//配置先
			pageOpen.setSourceDeptNm(getDeptName(pageOpen.getSourceDepartment()));	//発信部署
			formBean.setPageOpen(pageOpen);
			formBean.setOpenFileUrl(fileUrl);

			// 編集中コンテンツ情報取得
			pageEdit = pageService.getPageRsvForm(pageId);
			if (pageEdit == null){
				// メール送信成功フラグをセット
				request.setAttribute("pageExist", "N");
				return;
			}			
			pageEdit.setPageLocation(pageService.getPageLocation(pageId));						//配置先
			pageEdit.setSourceDeptNm(getDeptName(pageEdit.getSourceDepartment()));	//発信部署
			formBean.setPageEdit(pageEdit);

			String editFileUrl = "http://" + request.getServerName() + request.getContextPath() 
								+ "/login_mailLogin.do?isReserve=1&pageId=" + formBean.getPageId();
			formBean.setEditFileUrl(editFileUrl);
		} else {
			// 編集中コンテンツ情報取得
			pageEdit = pageService.getPageForm(pageId);
			if (pageEdit == null){
				// メール送信成功フラグをセット
				request.setAttribute("pageExist", "N");
				return;
			}
			pageEdit.setPageLocation(pageService.getPageLocationOpen(pageId));					//配置先
			pageEdit.setSourceDeptNm(getDeptName(pageEdit.getSourceDepartment()));	//発信部署
			formBean.setEditFileUrl(fileUrl);
			formBean.setPageEdit(pageEdit);
		}
		
		String subject = FsPropertyUtil.getStringProperty("contents.mail.title");
		formBean.setSubject(subject);
		
		List<String> proxyIdList = new ArrayList<String>();
		
		List<ProxyUserBean> proxyList = pageEdit.getProxyUserList();
		if(proxyList != null){
			for (ProxyUserBean proxyUser: proxyList) {
				proxyIdList.add(proxyUser.getProxyUserId());
			}
		}
		
		formBean.setSendTo(getUserMailAddress(proxyIdList));
		
		String[] ccTo = this.getTrMailAddress(proxyIdList);
		String ccToStr = StringUtil.nullToBlank(FsPropertyUtil.getStringProperty("news.mail.cc") + ";" + StringUtil.join(ccTo, ";"));
		formBean.setCcTo(ccToStr);
		
		formBean.setNumbersGroup(FsPropertyUtil.getIntProperty("numbers.group"));
		formBean.setNumbersUser(FsPropertyUtil.getIntProperty("numbers.user"));
		formBean.setMailBody(new FreemarkerUtil().getTemplateStr(formBean, "mail_page_finish.ftl"));		
	}

	/**
	 * メール送信
	 * @param formBean
	 * @throws Exception
	 */
	public void sendMail(SubPageFinishMailFormBean formBean) throws Exception{
		LoginUserBean loginUser = this.getLoginUserBean();
		
		MailBean mailBean = new MailBean();
		mailBean.setMailType("text/html");
		mailBean.setFrom(sendMailService.getMailAddCount(loginUser.getUserId()));
		mailBean.setTo(formBean.getSendTo().split(";"));
		mailBean.setSubject(formBean.getSubject());
		mailBean.setContent(urlhenkou(StringUtil.nullToBlank(formBean.getMailBody().replaceAll("\r\n", "<br>").replaceAll(" ", "&nbsp;"))));
		if (StringUtil.isNotEmpty(formBean.getCcTo())) {
			mailBean.setCc(formBean.getCcTo().split(";"));
		}
		String currentUserId = StringUtil.nullToBlank(loginUser.getUserId());
		String kanaMei = StringUtil.nullToBlank(loginUser.getKanjiMei());
		String kanaSei = StringUtil.nullToBlank(loginUser.getKanjiSei());
		String user = kanaSei+kanaMei+"("+currentUserId+")";
		MailUtil mail = new MailUtil();
		mail.sendMail(mailBean,user,"");		
	}
	
	/**
	 * 社員メールアドレスを取得
	 * @param usersId　社員Idリスト
	 * @return　検索結果
	 */
	private String getUserMailAddress(List<String> usersId) {
		
		String usersIdParaStr = CommonUtil.getGroupSql(usersId);
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERS_ID", usersIdParaStr);
		
		List<String> addressList = subPageFinishMailDao.getUserMailAddress(param);
		
		return StringUtil.join(addressList, ";");
	}
	
	/**
	 * 社員転送先メールアドレスを取得
	 * @param userIdList　社員IDリスト
	 * @return　検索結果
	 */
	public String[] getTrMailAddress(List<String> userIdList ) {
		
		return subPageFinishMailDao.getTrMailAddress(userIdList);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String urlhenkou(String mailbody)throws Exception {
		
		if (!StringUtil.isEmpty(mailbody)){
			String[] su = mailbody.split("http://");
			List<String> httplist = new ArrayList();
			int httpstart = 0;
			int httpend = 0;
			for (int i = 0;i<su.length;i++){
				if (i == 0){
					httpstart = mailbody.indexOf("http://");
					httpend = mailbody.indexOf("<br>", httpstart);
					if (httpend < 0 ){
						httpend = mailbody.length();
					}
					httplist.add(mailbody.substring(0,httpstart)+"<a href='");
					httplist.add(mailbody.substring(httpstart,httpend)+"'>");
					httplist.add(mailbody.substring(httpstart,httpend)+"</a>");
				}else {
					httpstart = mailbody.indexOf("http://", httpend);
					if (httpstart < 0){
						break;
					}
					httplist.add(mailbody.substring(httpend,httpstart)+"<a href='");
					httpend = mailbody.indexOf("<br>", httpstart);
					if (httpend < 0 ){
						httpend = mailbody.length();
					}
					httplist.add(mailbody.substring(httpstart,httpend)+"'>");
					httplist.add(mailbody.substring(httpstart,httpend)+"</a>");
				}
			}
			httplist.add(mailbody.substring(httpend,mailbody.length()));
			StringBuffer renturnbody = new StringBuffer();
			if (httplist != null && httplist.size() > 0){
				for (int i = 0;i<httplist.size();i++){
					renturnbody.append(httplist.get(i));
				}
				return renturnbody.toString();
			}else {
				return mailbody;
			}
		}else {
			return "";
		}
	}
	
	/**
	 * 部署名称を取得
	 * @param departmentId
	 * @return
	 */
	private String getDeptName(String departmentId){
		String departmentName = StringUtil.EMPTY;
		DepartmentBean departmentBean = departmentService.getDept_Open(departmentId);
		if (departmentBean != null){
			departmentName = departmentBean.getDepartmentName();
		}
		return departmentName;
	}

}