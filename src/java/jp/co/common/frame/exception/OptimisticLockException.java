package jp.co.common.frame.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * 排他制御が発生する場合、この異常をThrow
 */
public class OptimisticLockException extends LocalRuntimeException {

	private static final long serialVersionUID = 1L;
	/** エラーIDリスト */
	private List<String> errorIDList = new ArrayList<String>();
	/** エラーメッセージリスト */
	private List<String> errorMessageList = new ArrayList<String>();
	/**
	 * コンストラクタメソッド。
	 */
	public OptimisticLockException() {
		super();
	}

	/**
	 * コンストラクタメソッド。
	 * @param msg エラー文字列
	 */
	public OptimisticLockException(String msg) {
		super(msg);
	}

	public List<String> getErrorIDList() {
		return errorIDList;
	}

	public void setErrorIDList(List<String> errorIDList) {
		this.errorIDList = errorIDList;
	}

	public List<String> getErrorMessageList() {
		return errorMessageList;
	}

	public void setErrorMessageList(List<String> errorMessageList) {
		this.errorMessageList = errorMessageList;
	}
}