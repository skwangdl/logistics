package jp.co.common.frame.exception;

/**
 * アプリケーションで設定したタイムアウト時間が超える場合、
 * この異常をThrow
 * 
 * File Name: SessionTimeoutException.java 
 * Created: 2011/04/11 
 * Original Author: * yjl(NTS) 
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2011/04/11		   yjl(NTS)        作成
 *
 **/
public class SessionTimeoutException extends Exception {

	private static final long serialVersionUID = -597235394269425096L;

	/**
	 * コンストラクタメソッド。
	 *
	 */
	public SessionTimeoutException() {
		super();
	}

	/**
	 * コンストラクタメソッド。
	 *
	 * @param msg エラー文字列
	 */
	public SessionTimeoutException(String msg) {
		super(msg);
	}
}