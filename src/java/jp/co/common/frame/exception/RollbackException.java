package jp.co.common.frame.exception;

/**
 * 排他制御が発生する場合、この異常をThrow
 */
public class RollbackException extends LocalRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4705619183782614192L;

	/**
	 * コンストラクタメソッド。

	 *
	 */
	public RollbackException() {
		super();
	}

	/**
	 * コンストラクタメソッド。

	 *
	 * @param msg エラー文字列
	 */
	public RollbackException(String msg) {
		super(msg);
	}
}
