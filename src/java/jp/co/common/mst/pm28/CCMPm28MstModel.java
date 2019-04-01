/*
 * プラスロジスティクス株式会社
 * 新配送管理システム
 * Copyright (C) 2007 PLUS Logistics Corporation. All rights reserved.
 */
package jp.co.common.mst.pm28;

import java.io.Serializable;

/**
 * <DT><B>[クラス名]</B><UL>
 *    デポマスタ用ラベルバリューＶＯ
 * </UL><P>
 * <DT><B>[説明]</B>
 * <UL>
 *    デポマスタのラベルとコードの組み合わせを表すＶＯ
 * </UL><P>
 * <DT><B>[更新履歴]</B><UL>
 * <LI>2007/01/11 1.0 Cocom T.Satoh 新規作成
 * </UL><P>
 * @since JDK1.5.0
 */
public class CCMPm28MstModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** コード */
	private String codeId;
	/** ラベル */
	private String fullName;
	/** 短縮ラベル */
	private String shortName;
	/** サブコード１ **/
	private String subCd1;
	/** サブコード２ **/
	private String subCd2;	
	
	/**
	 * subCd1の取得
	 * @return subCd1
	 */
	public String getSubCd1() {
		return subCd1;
	}
	/**
	 * subCd2の取得
	 * @return subCd2
	 */
	public String getSubCd2() {
		return subCd2;
	}
	/**
	 * subCd1の設定

	 * @param subCd1
	 */
	public void setSubCd1(String subCd1) {
		this.subCd1 = subCd1;
	}
	/**
	 * subCd2の設定

	 * @param subCd2
	 */
	public void setSubCd2(String subCd2) {
		this.subCd2 = subCd2;
	}
	/**
	 * fullNameの取得
	 * @return fullName
	 */
	public final String getFullName() {
		return fullName;
	}
	/**
	 * fullNameの設定

	 * @param fullName
	 */
	public final void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * shortNameの取得
	 * @return shortName
	 */
	public final String getShortName() {
		return shortName;
	}
	/**
	 * shortNameの設定

	 * @param shortName
	 */
	public final void setShortName(String shortName) {
		this.shortName = shortName;
	}
	/**
	 * valueの取得
	 * @return codeId
	 */
	public final String getCodeId() {
		return codeId;
	}
	/**
	 * valueの設定

	 * @param codeId
	 */
	public final void setCodeId(String value) {
		this.codeId = value;
	}
	
}
