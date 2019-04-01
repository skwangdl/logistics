package jp.co.common.frame.exception;

import jp.co.fourseeds.fsnet.common.ConstantContainer;

public class ServiceException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8790241948559863239L;
	
	/**
	 * Exception bo name
	 */
	private String exceptionServiceName = null;

	/**
	 * @param exceptionMessage
	 */
	public ServiceException(Exception exception, String exceptionMessage) {
		super(exception, ConstantContainer.EXCEPTION_SERVICE, exceptionMessage);
	}

	/**
	 * @param exceptionMessage
	 * @param exceptionServiceName
	 */
	public ServiceException(Exception exception, String exceptionMessage,
			String exceptionBOName) {
		super(exception, ConstantContainer.EXCEPTION_SERVICE, exceptionMessage);
		this.exceptionServiceName = exceptionBOName;
	}

	/**
	 * @return
	 */
	public String getExceptionServiceName() {
		return exceptionServiceName;
	}
}
