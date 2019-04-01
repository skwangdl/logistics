package jp.co.common.frame.exception;

import jp.co.fourseeds.fsnet.common.ConstantContainer;

public class PropertyException extends BaseException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6741209825755038851L;
	/**
	 * Exception property key
	 */
	private String exceptionPropertyKey = null;

	/**
	 * @param exceptionMessage
	 */
	public PropertyException(Exception exception, String exceptionMessage) {
		super(exception, ConstantContainer.EXCEPTION_PROPERTIES,
				exceptionMessage);
	}

	/**
	 * @param exceptionMessage
	 * @param exceptionPropertyKey
	 */
	public PropertyException(Exception exception, String exceptionMessage,
			String exceptionPropertyKey) {
		super(exception, ConstantContainer.EXCEPTION_PROPERTIES,
				exceptionMessage);
		this.exceptionPropertyKey = exceptionPropertyKey;
	}

	/**
	 * @return
	 */
	public String getExceptionPropertyKey() {
		return exceptionPropertyKey;
	}

}
