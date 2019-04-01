/*
 * プラスロジスティクス株式会社
 * 新配送管理システム
 * Copyright (C) 2007 PLUS Logistics Corporation. All rights reserved.
 */
package jp.co.common.frame.exception;

/**
 * <B>[クラス名]</B><UL>
 *    アプリケーション例外クラス
 * </UL><P>
 * <B>[説明]</B><UL>
 *    再操作可能なエラーの場合にスローする例外クラス。
 * </UL><P>
 * <B>[更新履歴]</B>
 * <UL>
 * <LI>2006/08/15 1.0 Cocom H.Yatake 新規作成
 * <LI>2006/11/24 1.1 Cocom H.Yatake ModuleExcedpitonサブクラス対応
 * <LI>2006/12/26 1.2 Cocom H.Yatake ModuleExcedpitonの継承を取り止め
 * </UL>
 * @since JDK1.5.0
 */
public class CCMApplException extends CCMDefaultException {

	/**
	 * コンストラクタ
	 * @param key メッセージキー
	 * @param values メッセージ埋め込み変数
	 * @param throwable 例外親クラス
	 */
	public CCMApplException(String key, Object[] values, Throwable throwable) {
		super(key, values, throwable);
		throw new UnsupportedOperationException("用法が誤っています。例外を渡す場合はCCMBusinessExceptionの使用を検討してください。");

	}

	/**
	 * コンストラクタ
	 * @param key メッセージキー
	 * @param values メッセージ埋め込み変数
	 */
	public CCMApplException(String key, Object[] values) {
		super(key, values);

	}

	/**
	 * コンストラクタ
	 * @param key メッセージキー
	 * @param throwable 例外親クラス
	 */
	public CCMApplException(String key, Throwable throwable) {
		super(key, throwable);
		throw new UnsupportedOperationException("用法が誤っています。例外を渡す場合はCCMBusinessExceptionの使用を検討してください。");
	}

	/**
	 * コンストラクタ
	 * @param key メッセージキー
	 */
	public CCMApplException(String key) {
		super(key);

	}

	/**
	 * コンストラクタ
	 */
	public CCMApplException() {
	}	
}
