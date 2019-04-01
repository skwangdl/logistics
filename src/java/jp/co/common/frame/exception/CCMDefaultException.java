/*
 * プラスロジスティクス株式会社
 * 新配送管理システム
 * Copyright (C) 2007 PLUS Logistics Corporation. All rights reserved.
 */
package jp.co.common.frame.exception;


/**
 * <B>[クラス名]</B><UL>
 *    基底例外クラス
 * </UL><P>
 * <B>[説明]</B><UL>
 *    全ての例外クラスの基底クラス<BR>
 *    全ての例外クラスは当クラスのサブクラスとして作成する。
 * </UL><P>
 * <B>[更新履歴]</B>
 * <UL>
 * <LI>2006/08/15 1.0 Cocom H.Yatake 新規作成
 * <LI>2006/11/24 1.1 Cocom H.Yatake ModuleExcedpitonサブクラス対応
 * <LI>2006/12/26 1.2 Cocom H.Yatake ModuleExcedpitonの継承を取り止め
 * </UL>
 * @since JDK1.5.0
 */
public abstract class CCMDefaultException extends Exception {

	/** メッセージキー */
	private String key;

	/** メッセージ埋め込み変数 */
	private Object[] values;

	/**
	 * コンストラクタ
	 * @param key メッセージキー
	 * @param values メッセージ埋め込み変数
	 */
	public CCMDefaultException(String key, Object[] values) {
		this.key = key;
		this.values = values;
	}

	/**
	 * コンストラクタ
	 * @param key メッセージキー
	 */
	public CCMDefaultException(String key) {
		this.key = key;
	}
	
	/**
	 * コンストラクタ
	 * @param key メッセージキー
	 * @param values メッセージ埋め込み変数
	 * @param throwable 例外親クラス
	 */
	public CCMDefaultException(String key, Object[] values, Throwable throwable) {
		super(throwable);
		this.key = key;
		this.values = values;
	}

	/**
	 * コンストラクタ
	 * @param key メッセージキー
	 * @param throwable 例外親クラス
	 */
	public CCMDefaultException(String key, Throwable throwable) {
		super(throwable);
		this.key = key;
	}

	/**
	 * コンストラクタ
	 */
	public CCMDefaultException() {
	}	
	
	/**
	 * メッセージキーの取得
	 * @return key メッセージキー
	 */
	public String getKey() {
		return key;
	}

	/**
	 * メッセージ埋め込み変数の取得
	 * @return values メッセージ埋め込み変数
	 */
	public Object[] getValues() {
		return values;
	}
	
	
	
}
