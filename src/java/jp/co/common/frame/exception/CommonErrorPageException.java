package jp.co.common.frame.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * 共通エラーException
 * エラーがあった場合、共通エラー画面へ遷移した場合、この異常をThrow
 */
public class CommonErrorPageException extends LocalRuntimeException {

	private static final long serialVersionUID = 1L;
	/** エラーIDリスト */
	private List<String> errorIDList = new ArrayList<String>();
	/** エラーメッセージリスト */
	private List<String> errorMessageList = new ArrayList<String>();
	/**
	 * コンストラクタメソッド。
	 */
	public CommonErrorPageException() {
		super();
	}

	/**
	 * コンストラクタメソッド。
	 * @param msg エラー文字列
	 */
	public CommonErrorPageException(String msg) {
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