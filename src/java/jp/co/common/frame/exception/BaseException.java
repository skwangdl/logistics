package jp.co.common.frame.exception;

import java.util.Date;

import jp.co.fourseeds.fsnet.common.ConstantContainer;


public class BaseException extends Exception {
	/**
	 * Serial version uid
	 */
	private static final long serialVersionUID = -3317495505793994403L;

	/**
	 * Exception's RuntimeException
	 */
	protected Exception exception = null;

	/**
	 * Exception's type
	 */
	protected String exceptionType = null;

	/**
	 * Exception's time
	 */
	protected String exceptionTime = null;

	/**
	 * Exception's message
	 */
	protected String exceptionMessage = null;

	/**
	 * @param exceptionMessage
	 */
	public BaseException(Exception exception, String exceptionMessage) {
		this(exception, ConstantContainer.EXCEPTION_GENERAL, exceptionMessage);
	}

	/**
	 * @param exception
	 * @param exceptionType
	 * @param exceptionMessage
	 */
	public BaseException(Exception exception, String exceptionType,
			String exceptionMessage) {
		this.exception = exception;
		this.exceptionType = exceptionType;
		this.exceptionTime = new Date(System.currentTimeMillis()).toString();
		this.exceptionMessage = exceptionMessage;
	}

	/**
	 * @return
	 */
	public String getExceptionTime() {
		return exceptionTime;
	}

	/**
	 * @return
	 */
	public String getExceptionType() {
		return exceptionType;
	}

	/**
	 * @return
	 */
	public String getExceptionMessage() {
		return exceptionMessage;
	}

	/**
	 * @return the exception
	 */
	public Exception getException() {
		return exception;
	}
}
