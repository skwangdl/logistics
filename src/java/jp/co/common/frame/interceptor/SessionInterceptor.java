package jp.co.common.frame.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * セッションタイムアウトチェック
 * 
 * File Name: SessionInterceptor.java Created: 2015/09/22 Original Author: NTS
 * 
 * ----------------------------------------------------------- Version When Who
 * Why ----------------------------------------------------------- 1.0
 * 2015/09/22 NTS 作成
 *
 **/
public class SessionInterceptor implements HandlerInterceptor {
	/** Log4j */
	private final Log logger = LogFactory.getLog(this.getClass());

	static final String timeoutError = "/jsp/error/errorSessionTimeout.jsp";
	static final String commonError = "/jsp/error/commonError.jsp";
	static final String error = "/jsp/error/error.jsp";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {
		System.out.println("preHandle");
		/*HttpSession session = request.getSession();

		String userAgent = request.getHeader("user-agent");

		System.out.println(userAgent);
		System.out.println(userAgent.indexOf("Windows"));
		// if(userAgent.indexOf("chrome") < 0){
		// System.out.println("start redirect ................");
		// return "ajaxTimeout";
		// }
		if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
			JSONObject resultJson = new JSONObject();
			resultJson.put("status", "error");

			// セッションチェック
			if (session == null) {
				OutputStream ps = response.getOutputStream();
				logger.error("セッション情報が失いました。");
				response.setStatus(403);
				response.setContentType("application/json;charset=UTF-8");
				resultJson.put("errorCode", "timeoutError");
				resultJson.put("errorMessage", "セッション情報が失いました。");
				ps.write(resultJson.toString().getBytes("UTF-8"));
				return false;
			} else {
				// セッションからログインユーザー情報を取得
				LoginUserBean user = (LoginUserBean) session.getAttribute(ConstantContainer.LOGIN_USER_KEY);

				// ユーザー情報がない場合
				if (user == null) {
					OutputStream ps = response.getOutputStream();
					logger.error("セッション情報が失いました。(ユーザー情報なし)。");
					response.setStatus(403);
					response.setContentType("application/json;charset=UTF-8");
					resultJson.put("errorCode", "timeoutError");
					resultJson.put("errorMessage", "セッション情報が失いました。(ユーザー情報なし)。");
					ps.write(resultJson.toString().getBytes("UTF-8"));
					return false;
				}
			}
		} else {
			// セッションチェック
			if (session == null) {
				logger.error("セッション情報が失いました。");
                request.getRequestDispatcher(timeoutError).forward(request, response);
                return false;
			} else {
				// セッションからログインユーザー情報を取得
				LoginUserBean user = (LoginUserBean) session.getAttribute(ConstantContainer.LOGIN_USER_KEY);

				// ユーザー情報がない場合
				if (user == null) {
					logger.error("セッション情報が失いました。(ユーザー情報なし)。");
	                request.getRequestDispatcher(timeoutError).forward(request, response);
	                return false;
				}
			}
		}*/
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) throws Exception {
		System.out.println("afterCompletion");
	}
}