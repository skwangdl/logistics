package jp.co.common.frame.interceptor;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.common.ConstantContainer;
import jp.co.common.frame.util.DateUtil;

/**
 * 業務メッセージ処理クラス
 * 
 * <pre>
   *  　 業務メッセージが発生する場合、Action　Input戻る
 *  以下の機能をサポートする。
 * </pre>
 * <ul>
 * <li>業務メッセージ処理</li>
 * </ul>
 * 
 * @author NTS
 * @version 1.0
 */
@Component
@Aspect
public class AccessLogInterceptor {
	/** Log4jの定義 */
	private final Log logger = LogFactory.getLog(getClass());

	/**
	 * 定?一个方法，用于声明切入点表?式，方法中一般不需要添加其他代? 使用@Pointcut声明切入点表?式
	 * 后面的通知直接使用方法名来引用当前的切点表?式；如果是其他?使用，加上包名即可
	 */
	@Pointcut("execution(public * jp.co.fourseeds.fsnet.action..*.*(..))")
	public void declearAccessLogJoinPointExpression() {
	}

	/**
	 * 前置通知
	 * 
	 * @param joinPoint
	 */
	@Before("declearAccessLogJoinPointExpression()")
	public void before() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		StringBuffer sb = new StringBuffer();
		sb.append("***START " + DateUtil.getNowTime() + ":");

		LoginUserBean user = (LoginUserBean) request.getSession().getAttribute(ConstantContainer.LOGIN_USER_KEY);

		if (user != null) {
			sb.append("　ユーザー:" + user.getUserId());
		} else {
			sb.append("　ユーザー:???");
		}
		// sb.append(" クラス:" + ai.getAction());
		// sb.append(" メソッド:" + ai.getInvocationContext().getName());
		logger.info(sb.toString());
	}

	/**
	 * 后置通知（无?方法是否?生?常都会?行,所以??不到方法的返回?）
	 * 
	 * @param joinPoint
	 */
	@After("declearAccessLogJoinPointExpression()")
	public void after() throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("***END " + DateUtil.getNowTime() + ":");

			LoginUserBean user = (LoginUserBean) request.getSession().getAttribute(ConstantContainer.LOGIN_USER_KEY);

			if (user != null) {
				sb.append("　ユーザー:" + user.getUserId());
			} else {
				sb.append("　ユーザー:???");
			}
			// sb.append(" クラス:" + ai.getAction());
			// sb.append(" メソッド:" + ai.getInvocationContext().getName());
			// Map<String, Object> map = ai.getInvocationContext().getParameters();
			// Set<String> keys = map.keySet();
			sb.append("　パラメータ：");
			// for (String key : keys) {
			// sb.append(key + "=" + ((Object[]) map.get(key))[0]+ "#");
			// }
			// sb.append(" 戻り結果:" + ai.getResultCode());

			logger.info(sb.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回通知（在方法正常?束?行的代?） 返回通知可以??到方法的返回?！
	 * 
	 * @param joinPoint
	 */
	@AfterReturning(value = "declearAccessLogJoinPointExpression()", returning = "result")
	public void afterReturnMethod(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("this method " + methodName + " end.result<" + result + ">");
	}

	/**
	 * ?常通知（方法?生?常?行的代?） 可以??到?常?象；且可以指定在出?特定?常??行的代?
	 * 
	 * @param joinPoint
	 * @param ex
	 */
	@AfterThrowing(value = "declearAccessLogJoinPointExpression()", throwing = "ex")
	public void afterThrowingMethod(JoinPoint joinPoint, Exception ex) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("this method " + methodName + " end.ex message<" + ex + ">");
	}

	/**
	 * ??通知(需要携??型?ProceedingJoinPoint?型的参数) ??通知包含前置、后置、返回、?常通知；ProceedingJoinPoin
	 * ?型的参数可以决定是否?行目?方法 且??通知必?有返回?，返回?即目?方法的返回?
	 * 
	 * @param point
	 * @throws Throwable 
	 */
	@Around(value = "declearAccessLogJoinPointExpression()")
	public Object aroundMethod(ProceedingJoinPoint point) throws Throwable {
		Object result = null;
		String methodName = point.getSignature().getName();
		try {
			// 前置通知
			System.out.println("The method " + methodName + " start. param<" + Arrays.asList(point.getArgs()) + ">");
			// ?行目?方法
			result = point.proceed();
			// 返回通知
			System.out.println("The method " + methodName + " end. result<" + result + ">");
		} catch (Throwable e) {
			// ?常通知
			System.out.println("this method " + methodName + " end.ex message<" + e + ">");
			throw e;
		}
		// 后置通知
		System.out.println("The method " + methodName + " end.");
		return result;
	}
}
