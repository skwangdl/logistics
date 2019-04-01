package jp.co.common.frame.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TokenInteractor implements HandlerInterceptor {
	static final String TOKEN_NAME = "myAppToken";
	static final String TOKEN_ERROR = "/jsp/error/errorToken.jsp";

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = request.getParameter(TOKEN_NAME);
		Object sessionToken = request.getSession().getAttribute(TOKEN_NAME);
		// ï\?íÒå
		if (token != null) {
			// èd?íÒå
			if (sessionToken == null) {
				request.getRequestDispatcher(TOKEN_ERROR).forward(request, response);
				return false;
			} else {
				if (token.equals(sessionToken)) {
					request.getSession().removeAttribute(TOKEN_NAME);
					return true;
				} else {
					request.getRequestDispatcher(TOKEN_ERROR).forward(request, response);
					return false;
				}
			}
		} else {
			return true;
		}
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle");
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion");
	}
}
