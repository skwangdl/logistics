/*
 * プラスロジスティクス株式会社
 * 新配送管理システム
 * Copyright (C) 2007 PLUS Logistics Corporation. All rights reserved.
 */
package jp.co.common.mst.pm43;

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
public class CCMPm43MstModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 部門コード */
	private String bumonCd;
	
	/** カスタマセンタ名 */
	private String custCenterNm;
		
	/** カスタマセンタ略称 */
	private String custCenterRm;
	
	/** 管理元会社区分 */
	private String kanrimotoKaisyaKb;

	/** 部門区分 */
	private String bumonKb;
	
	/**
	 * bumonKbの取得
	 * @return bumonKb
	 */
	public String getBumonKb() {
		return bumonKb;
	}

	/**
	 * bumonKbの設定
	 * @param bumonKb
	 */
	public void setBumonKb(String bumonKb) {
		this.bumonKb = bumonKb;
	}

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
	 * custCenterNmの取得
	 * @return custCenterNm
	 */
	public String getCustCenterNm() {
		return custCenterNm;
	}

	/**
	 * custCenterNmの設定
	 * @param custCenterNm
	 */
	public void setCustCenterNm(String custCenterNm) {
		this.custCenterNm = custCenterNm;
	}

	/**
	 * custCenterRmの取得
	 * @return custCenterRm
	 */
	public String getCustCenterRm() {
		return custCenterRm;
	}

	/**
	 * custCenterRmの設定
	 * @param custCenterRm
	 */
	public void setCustCenterRm(String custCenterRm) {
		this.custCenterRm = custCenterRm;
	}

	/**
	 * kanrimotoKaisyaKbの取得
	 * @return kanrimotoKaisyaKb
	 */
	public String getKanrimotoKaisyaKb() {
		return kanrimotoKaisyaKb;
	}

	/**
	 * kanrimotoKaisyaKbの設定
	 * @param kanrimotoKaisyaKb
	 */
	public void setKanrimotoKaisyaKb(String kanrimotoKaisyaKb) {
		this.kanrimotoKaisyaKb = kanrimotoKaisyaKb;
	}


	
}
