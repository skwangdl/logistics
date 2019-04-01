/*
 * プラスロジスティクス株式会社
 * 新配送管理システム
 * Copyright (C) 2007 PLUS Logistics Corporation. All rights reserved.
 */
package jp.co.common.frame.exception;

/**
 * <B>[クラス名]</B><UL>
 *    データアクセス例外クラス
 * </UL><P>
 * <B>[説明]</B><UL>
 *    データアクセス例外クラス
 * </UL><BR>
 * <B>[更新履歴]</B>
 * </UL><P>
 * <LI>2006/12/27 1.0 Cocom H.Yatake 新規作成
 * </UL>
 * @since JDK1.5.0
 */
public class CCMDataAccessException extends Exception {

	/** エラーメッセージのキー */
	private String key;

	/** エラーメッセージ埋め込み文字列 */
	private String[] values;

	/**
	 * コンストラクタ
	 * @param key エラーコード
	 */
	public CCMDataAccessException(String errCd) {
		this.key = errCd;

	}

	/**
	 * コンストラクタ
	 * @param key エラーコード
	 * @param values 埋め込み文字列

	 */
	public CCMDataAccessException(String errCd, String[] values) {

		this.key = errCd;
		this.values = values;
	}

	/**
	 * コンストラクタ
	 * @param key エラーコード
	 * @param ex 例外
	 */
	public CCMDataAccessException(String errCd, Exception ex) {
		super(ex);
		this.key = errCd;

	}

	/**
	 * コンストラクタ
	 * @param key エラーコード
	 * @param values 埋め込み文字列
	 * @param ex 例外
	 */
	public CCMDataAccessException(String errCd, String[] values, Exception ex) {

		super(ex);
		this.key = errCd;
		this.values = values;
	}

	/**
	 * エラーコード取得
	 * @return
	 */
	public String getKey() {
		return key;
	}

	/**
	 * エラーメッセージ引数取得
	 * @return
	 */
	public String[] getValues() {
		return values;
	}

}
