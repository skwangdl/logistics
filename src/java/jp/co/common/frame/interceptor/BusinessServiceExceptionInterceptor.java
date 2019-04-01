package jp.co.common.frame.interceptor;

import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import net.sf.json.JSONObject;
import jp.co.common.frame.exception.ActionException;
import jp.co.common.frame.exception.AuthenticationException;
import jp.co.common.frame.exception.BaseException;
import jp.co.common.frame.exception.CommonErrorPageException;
import jp.co.common.frame.exception.DataBaseException;
import jp.co.common.frame.exception.OptimisticLockException;
import jp.co.common.frame.exception.PropertyException;
import jp.co.common.frame.exception.ServiceException;

/**
 * 業務メッセージ処理クラス
 * 
 * <pre>
 *  業務メッセージが発生する場合、Action Input戻る
 *  以下の機能をサポートする。
 * </pre>
 * <ul>
 * <li>業務メッセージ処理</li>
 * </ul>
 * 
 * @author NTS
 * @version 1.0
 */
public class BusinessServiceExceptionInterceptor implements HandlerExceptionResolver {
	/** Log4jの定義 */
	private final Log logger = LogFactory.getLog(getClass());
	
	String timeoutError = "/error/errorSessionTimeout";
	String commonError = "/error/commonError";
	String error = "/error/error";
	String error2 = "/error/error2";

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) {
		// 排他制御Exception
		if (ex instanceof OptimisticLockException) {
			OptimisticLockException error = (OptimisticLockException) ex;
			if (logger.isInfoEnabled()) {
				logger.info(ex.getMessage());
			}
//			BaseActionSupport baseAction = (BaseActionSupport) handler;
//			List<String> errorIDList = error.getErrorIDList();
//			for (int i = 0; i < errorIDList.size(); i++) {
//				baseAction.addActionError(baseAction.getText(errorIDList.get(i)));
//			}
//			List<String> errorMessageList = error.getErrorMessageList();
//			for (int i = 0; i < errorMessageList.size(); i++) {
//				baseAction.addActionError(errorMessageList.get(i));
//			}
			return getFinalReturnStr(request, response, "commonError");
		// 共通エラーException
		} else if (ex instanceof CommonErrorPageException) {
			CommonErrorPageException error = (CommonErrorPageException) ex;
			if (logger.isInfoEnabled()) {
				logger.info(ex.getMessage());
			}
//			BaseActionSupport baseAction = (BaseActionSupport) handler;
//			List<String> errorIDList = error.getErrorIDList();
//			for (int i = 0; i < errorIDList.size(); i++) {
//				baseAction.addActionError(baseAction.getText(errorIDList.get(i)));
//			}
//			List<String> errorMessageList = error.getErrorMessageList();
//			for (int i = 0; i < errorMessageList.size(); i++) {
//				baseAction.addActionError(errorMessageList.get(i));
//			}
			return getFinalReturnStr(request, response, "commonError");
		} else {
			logger.error("*** Start : [Exception]" + ex);
			ex.printStackTrace();
			logException(ex, request);
			return getFinalReturnStr(request, response, "error");
		}
	}

	private void logException(Exception e, HttpServletRequest request) {
		StringBuffer logMessage = new StringBuffer("\n");

		if (e instanceof BaseException) {
			BaseException be = (BaseException) e;
			String exceptionType = be.getExceptionType();
			String exceptionTime = be.getExceptionTime();
			String exceptionMessage = be.getExceptionMessage();

			logMessage.append("Exception type:");
			logMessage.append("\n");
			logMessage.append("  ");
			logMessage.append(StringUtil.nullToBlank(exceptionType));
			logMessage.append("\n");

			logMessage.append("Exception time:");
			logMessage.append("\n");
			logMessage.append("  ");
			logMessage.append(StringUtil.nullToBlank(exceptionTime));
			logMessage.append("\n");

			logMessage.append("Exception message:");
			logMessage.append("\n");
			logMessage.append("  ");
			logMessage.append(StringUtil.nullToBlank(exceptionMessage));
			logMessage.append("\n");

			if (be instanceof ActionException) {
				ActionException tempException = (ActionException) be;
				String exceptionActionName = tempException.getExceptionActionName();

				logMessage.append("Action name:");
				logMessage.append("\n");
				logMessage.append("  ");
				logMessage.append(StringUtil.nullToBlank(exceptionActionName));
				logMessage.append("\n");
			} else if (be instanceof AuthenticationException) {
				AuthenticationException tempException = (AuthenticationException) be;
				String exceptionUserId = tempException.getExceptionUserId();
				String exceptionUserName = tempException.getExceptionUserName();

				logMessage.append("User id:");
				logMessage.append("\n");
				logMessage.append("  ");
				logMessage.append(StringUtil.nullToBlank(exceptionUserId));
				logMessage.append("\n");

				logMessage.append("User name:");
				logMessage.append("\n");
				logMessage.append("  ");
				logMessage.append(StringUtil.nullToBlank(exceptionUserName));
				logMessage.append("\n");
			} else if (be instanceof DataBaseException) {
				DataBaseException tempException = (DataBaseException) be;
				String exceptionDaoName = tempException.getExceptionDaoName();
				String exceptionSQL = tempException.getExceptionSQL();

				logMessage.append("Dao name:");
				logMessage.append("\n");
				logMessage.append("  ");
				logMessage.append(StringUtil.nullToBlank(exceptionDaoName));
				logMessage.append("\n");

				logMessage.append("SQL:");
				logMessage.append("\n");
				logMessage.append("  ");
				logMessage.append(StringUtil.nullToBlank(exceptionSQL));
				logMessage.append("\n");
			} else if (be instanceof PropertyException) {
				PropertyException tempException = (PropertyException) be;
				String exceptionPropertyKey = tempException.getExceptionPropertyKey();

				logMessage.append("Property key:");
				logMessage.append("\n");
				logMessage.append("  ");
				logMessage.append(StringUtil.nullToBlank(exceptionPropertyKey));
				logMessage.append("\n");
			} else if (be instanceof ServiceException) {
				ServiceException tempException = (ServiceException) be;
				String exceptionServiceName = tempException.getExceptionServiceName();

				logMessage.append("Service name:");
				logMessage.append("\n");
				logMessage.append("  ");
				logMessage.append(StringUtil.nullToBlank(exceptionServiceName));
				logMessage.append("\n");
			}
			e = be.getException();
		}

		logMessage.append("Exception message and stack trace:");
		logMessage.append("\n");
		// Send Error Mail
		StringBuffer strTemp = new StringBuffer();
		strTemp = logMessage;
		strTemp.append("\n");
		StackTraceElement[] ste = e.getStackTrace();
		for (int i = 0; i < ste.length; i++) {
			strTemp.append(ste[i].toString());
		}
		request.getSession().setAttribute("exceptionForError", strTemp.toString());
		// Send Error Mail
		logger.error(logMessage, e);
	}

	private ModelAndView getFinalReturnStr(HttpServletRequest request, HttpServletResponse response,String normalReturn) {
		if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
			logger.error("AJAX訪問システム異常が発生しました。");
			
			response.setStatus(500);
			response.setContentType("application/json;charset=UTF-8");
			
			JSONObject resultJson = new JSONObject();
			resultJson.put("status", "error");
			resultJson.put("errorCode", normalReturn);
			resultJson.put("errorMessage", "AJAX訪問システム異常が発生しました。");

			try(OutputStream ps = response.getOutputStream()) {
				ps.write(resultJson.toString().getBytes("UTF-8"));
			} catch (IOException e) {
			}
			return new ModelAndView();
		} else {
			ModelAndView mv;
			switch (normalReturn) {
			case "timeoutError":
				mv = new ModelAndView(timeoutError);
				break;
			case "commonError":
				mv = new ModelAndView(commonError);
				break;
			case "error":
				mv = new ModelAndView(error);
				break;
			default:
				mv = new ModelAndView(error);
				break;
			}
			return mv;
		}
	}
}
