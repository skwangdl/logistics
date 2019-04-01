package jp.co.common.frame.exception;

import jp.co.fourseeds.fsnet.common.ConstantContainer;

public class AuthenticationException extends BaseException{
	/**
	 * Serial version uid
	 */
	private static final long serialVersionUID = 3840486834691836566L;

	/**
	 * User's id
	 */
	private String exceptionUserId = null;

	/**
	 * User's name
	 */
	private String exceptionUserName = null;

	/**
	 * @param exceptionMessage
	 */
	public AuthenticationException(Exception exception, String exceptionMessage) {
		super(exception, ConstantContainer.EXCEPTION_AUTHENTICATION,
				exceptionMessage);
	}

	/**
	 * @param exceptionUserId
	 * @param exceptionUserName
	 * @param exceptionMessage
	 */
	public AuthenticationException(Exception exception, String exceptionUserId,
			String exceptionUserName, String exceptionMessage) {
		super(exception, ConstantContainer.EXCEPTION_AUTHENTICATION,
				exceptionMessage);
		this.exceptionUserId = exceptionUserId;
		this.exceptionUserName = exceptionUserName;
	}

	/**
	 * @return
	 */
	public String getExceptionUserId() {
		return exceptionUserId;
	}

	/**
	 * @return
	 */
	public String getExceptionUserName() {
		return exceptionUserName;
	}
}
