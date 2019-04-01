package jp.co.common.frame.exception;

import jp.co.fourseeds.fsnet.common.ConstantContainer;

public class DataBaseException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -549832908414742010L;
	/**
	 * Exception dao name
	 */
	private String exceptionDaoName = null;

	/**
	 * Exception sql
	 */
	private String exceptionSQL = null;

	/**
	 * @param exceptionMessage
	 */
	public DataBaseException(Exception exception, String exceptionMessage) {
		super(exception, ConstantContainer.EXCEPTION_DATABASE, exceptionMessage);
	}

	/**
	 * @param exceptionMessage
	 * @param exceptionSQL
	 */
	public DataBaseException(Exception exception, String exceptionMessage,
			String exceptionSQL) {
		super(exception, ConstantContainer.EXCEPTION_DATABASE, exceptionMessage);
		this.exceptionSQL = exceptionSQL;
	}

	/**
	 * @param exceptionMessage
	 * @param exceptionDaoName
	 * @param exceptionSQL
	 */
	public DataBaseException(Exception exception, String exceptionMessage,
			String exceptionDaoName, String exceptionSQL) {
		super(exception, ConstantContainer.EXCEPTION_DATABASE, exceptionMessage);
		this.exceptionDaoName = exceptionDaoName;
		this.exceptionSQL = exceptionSQL;
	}

	/**
	 * @return
	 */
	public String getExceptionDaoName() {
		return exceptionDaoName;
	}

	/**
	 * @return
	 */
	public String getExceptionSQL() {
		return exceptionSQL;
	}
}
