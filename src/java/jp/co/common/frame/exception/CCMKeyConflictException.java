/*
 * プラスロジスティクス株式会社
 * 新配送管理システム
 * Copyright (C) 2007 PLUS Logistics Corporation. All rights reserved.
 */
package jp.co.common.frame.exception;

/**
 * <B>[クラス名]</B><UL>
 *    キー重複例外クラス
 * </UL><P>
 * <B>[説明]</B><UL>
 *    ＤＢ登録時にキー重複が発生した際にスローされる例外
 * </UL><BR>
 * <B>[更新履歴]</B>
 * </UL><P>
 * <LI>2006/08/17 1.0 Cocom H.Yatake 新規作成
 * </UL>
 * @since JDK1.5.0
 */
public class CCMKeyConflictException extends CCMDataAccessException {

	/**
	 * コンストラクタ
	 * @param errCd
	 * @param ex
	 */
	public CCMKeyConflictException(String errCd, Exception ex) {
		super(errCd, ex);

	}

	/**
	 * コンストラクタ
	 * @param errCd
	 * @param errArgs
	 * @param ex
	 */
	public CCMKeyConflictException(String errCd, String[] errArgs, Exception ex) {
		super(errCd, errArgs, ex);

	}

	/**
	 * コンストラクタ
	 * @param errCd
	 * @param errArgs
	 */
	public CCMKeyConflictException(String errCd, String[] errArgs) {
		super(errCd, errArgs);

	}

	/**
	 * コンストラクタ
	 * @param errCd
	 */
	public CCMKeyConflictException(String errCd) {
		super(errCd);

	}

}
