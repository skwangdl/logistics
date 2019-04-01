package jp.co.common.frame.exception;

import jp.co.fourseeds.fsnet.common.ConstantContainer;

public class ActionException extends BaseException{
	/**
	 * Serial version uid
	 */
	private static final long serialVersionUID = 2915473297175387847L;

	/**
	 * Exception action name
	 */
	private String exceptionActionName = null;

	/**
	 * @param exceptionMessage
	 */
	public ActionException(Exception exception, String exceptionMessage) {
		super(exception, ConstantContainer.EXCEPTION_ACTION, exceptionMessage);
	}

	/**
	 * @param exceptionMessage
	 * @param exceptionActionName
	 */
	public ActionException(Exception exception, String exceptionMessage,
			String exceptionActionName) {
		super(exception, ConstantContainer.EXCEPTION_ACTION, exceptionMessage);
		this.exceptionActionName = exceptionActionName;
	}

	/**
	 * @return
	 */
	public String getExceptionActionName() {
		return exceptionActionName;
	}
}
