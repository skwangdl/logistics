/*
 * プラスロジスティクス株式会社
 * 新配送管理システム
 * Copyright (C) 2007 PLUS Logistics Corporation. All rights reserved.
 */
package jp.co.common.mst.pm32;

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
public class CCMPm32MstModel implements Serializable {
	
	/** 部門コード */
	private String bumonCd;
	
	/** 部門名 */
	private String bumonNm;
	
	/** 会計部門コード */
	private String kaikeiBumonCd;
	
	/** 会計部門名 */
	private String kaikeiBumonNm;

	/**
	 * bumonCdの取得
	 * @return bumonCd
	 */
	public String getBumonCd() {
		return bumonCd;
	}

	/**
	 * bumonCdの設定
	 * @param bumonCd
	 */
	public void setBumonCd(String bumonCd) {
		this.bumonCd = bumonCd;
	}

	/**
	 * bumonNmの取得
	 * @return bumonNm
	 */
	public String getBumonNm() {
		return bumonNm;
	}

	/**
	 * bumonNmの設定
	 * @param bumonNm
	 */
	public void setBumonNm(String bumonNm) {
		this.bumonNm = bumonNm;
	}

	/**
	 * kaikeiBumonCdの取得
	 * @return kaikeiBumonCd
	 */
	public String getKaikeiBumonCd() {
		return kaikeiBumonCd;
	}

	/**
	 * kaikeiBumonCdの設定
	 * @param kaikeiBumonCd
	 */
	public void setKaikeiBumonCd(String kaikeiBumonCd) {
		this.kaikeiBumonCd = kaikeiBumonCd;
	}

	/**
	 * kaikeiBumonNmの取得
	 * @return kaikeiBumonNm
	 */
	public String getKaikeiBumonNm() {
		return kaikeiBumonNm;
	}

	/**
	 * kaikeiBumonNmの設定
	 * @param kaikeiBumonNm
	 */
	public void setKaikeiBumonNm(String kaikeiBumonNm) {
		this.kaikeiBumonNm = kaikeiBumonNm;
	}
	
	
}
