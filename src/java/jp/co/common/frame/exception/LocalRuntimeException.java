package jp.co.common.frame.exception;

import jp.co.common.frame.util.prop.MessagePropertyUtil;
import jp.co.common.frame.util.DateUtil;
/**
 * システムレベルの異常クラス。
 * こんな異常が発生する場合、エラーページにエラーIDとエラーメッセージと発生時間を画面に表す
 * 以下の機能をサポートする。<br>
 * <ul>
 *  <li>BaseRuntimeExceptionを継承</li>
 *  <li>エラーID設定</li>
 *  <li>エラーメッセージ設定</li>
 *  <li>エラー発生時間 設定</li>
 * </ul>
 * 
 * @author NTS
 * @version 1.0
 * 
 */
public class LocalRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	/** エラーID */
	private String errorID;
	/** エラーメッセージ */
	private String errorMessage;
	/** エラーコメント(開発する際エラーIDを見るだけで、指す意味が分かりにくいなので、このフィールドを追加、無視してもいい) */
	private String errorComment;
	/** エラー発生時間 */
	private String errorTime = DateUtil.formatDateToSec(DateUtil.getNowTime(), "yyyy/MM/dd HH:mm:ss");

	/**
	 * コンストラクタメソッド。
	 */
	public LocalRuntimeException() {
		super();
	}

	/**
	 * コンストラクタメソッド。
	 *
	 * @param msg エラーコード文字列
	 */
	public LocalRuntimeException(String errorID, String errorComment) {
		super(errorID);
		this.errorID = errorID;
		this.errorComment = errorComment;
		errorMessage = MessagePropertyUtil.getInstance().getEncodeProperty(errorID);
	}

	/**
	 * コンストラクタメソッド。
	 *
	 * @param msg エラーコード文字列
	 */
	public LocalRuntimeException(String errorID) {
		super(errorID);
		this.errorID = errorID;
		errorMessage = MessagePropertyUtil.getInstance().getEncodeProperty(errorID);
	}

	/**
	 * コンストラクタメソッド。
	 *
	 * @param msg   エラーコード文字列
	 * @param cause 発生してる異常
	 */
	public LocalRuntimeException(String errorID, Throwable cause) {
		super(errorID, cause);
		this.errorID = errorID;
		errorMessage = MessagePropertyUtil.getInstance().getEncodeProperty(errorID);
	}

	/**
	 * コンストラクタメソッド。
	 *
	 * @param msg   エラーコード文字列
	 * @param cause 発生してる異常
	 */
	public LocalRuntimeException(String errorID, String errorComment, Throwable cause) {
		super(errorID, cause);
		this.errorID = errorID;
		this.errorComment = errorComment;
		errorMessage = MessagePropertyUtil.getInstance().getEncodeProperty(errorID);
	}
	
	/**
	 * 発生してる異常（Throwable）を返す
	 */
	public Throwable getCause() {
		return super.getCause();
	}

	public String getErrorComment() {
		return errorComment;
	}

	public void setErrorComment(String errorComment) {
		this.errorComment = errorComment;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorID() {
		return errorID;
	}

	public void setErrorID(String errorID) {
		this.errorID = errorID;
	}

	public String getErrorTime() {
		return errorTime;
	}

	public void setErrorTime(String errorTime) {
		this.errorTime = errorTime;
	}
}
