/*
 * プラスロジスティクス株式会社
 * 新配送管理システム
 * Copyright (C) 2007 PLUS Logistics Corporation. All rights reserved.
 */
package jp.co.common.mst.pm01;

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
public class CCMPm01MstModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8881183986784761854L;

	/**
	 * 
	 */

	/** デポコード */
	private String depoCd;
	
	/** デポ名 */
	private String depoNm;
	
	/** デポ組織区分 */
	private String depoSoshikiKb;
	
	/** デポ管理者 */
	private String depoKanrishaNm;
	
	/** 営業所コード */
	private String eigyoushoCd;
	
	/** 所属地域区分 */
	private String areaKbn;
	
	/** 営業所区分 */
	private String eigyoushoKb;
	
	/** 管理元会社区分 */
	private String kanrimotoKaisyaKb;

	/**
	 * コンストラクタ
	 */
	public CCMPm01MstModel() {
		super();
		
	}

	/**
	 * コンストラクタ
	 */
	public CCMPm01MstModel(String depoCd, String depoNm, String depoKanrishaNm, String eigyoushoCd, String areaKbn, String eigyoushoKb) {
		
		this.depoCd = depoCd;
		this.depoNm = depoNm;
		this.depoKanrishaNm = depoKanrishaNm;
		this.eigyoushoCd = eigyoushoCd;
		this.areaKbn = areaKbn;
		this.eigyoushoKb = eigyoushoKb;
		
	}	
	
	
	/**
	 * depoCdの取得
	 * @return depoCd
	 */
	public String getDepoCd() {
		return depoCd;
	}

	/**
	 * depoCdの設定
	 * @param depoCd
	 */
	public void setDepoCd(String depoCd) {
		this.depoCd = depoCd;
	}

	/**
	 * depoKanrishaNmの取得
	 * @return depoKanrishaNm
	 */
	public String getDepoKanrishaNm() {
		return depoKanrishaNm;
	}

	/**
	 * depoKanrishaNmの設定
	 * @param depoKanrishaNm
	 */
	public void setDepoKanrishaNm(String depoKanrishaNm) {
		this.depoKanrishaNm = depoKanrishaNm;
	}

	/**
	 * depoNmの取得
	 * @return depoNm
	 */
	public String getDepoNm() {
		return depoNm;
	}

	/**
	 * depoNmの設定
	 * @param depoNm
	 */
	public void setDepoNm(String depoNm) {
		this.depoNm = depoNm;
	}

	/**
	 * eigyoushoCdの取得
	 * @return eigyoushoCd
	 */
	public String getEigyoushoCd() {
		return eigyoushoCd;
	}

	/**
	 * eigyoushoCdの設定
	 * @param eigyoushoCd
	 */
	public void setEigyoushoCd(String eigyoushoCd) {
		this.eigyoushoCd = eigyoushoCd;
	}

	/**
	 * areaKbnの取得
	 * @return areaKbn
	 */
	public String getAreaKbn() {
		return areaKbn;
	}

	/**
	 * areaKbnの設定
	 * @param areaKbn
	 */
	public void setAreaKbn(String areaKbn) {
		this.areaKbn = areaKbn;
	}

	/**
	 * eigyoushoKbの取得
	 * @return eigyoushoKb
	 */
	public String getEigyoushoKb() {
		return eigyoushoKb;
	}

	/**
	 * eigyoushoKbの設定
	 * @param eigyoushoKb
	 */
	public void setEigyoushoKb(String eigyoushoKb) {
		this.eigyoushoKb = eigyoushoKb;
	}

	/**
	 * depoSoshikiKbの取得
	 * @return depoSoshikiKb
	 */
	public String getDepoSoshikiKb() {
		return depoSoshikiKb;
	}

	/**
	 * depoSoshikiKbの設定
	 * @param depoSoshikiKb
	 */
	public void setDepoSoshikiKb(String depoSoshikiKb) {
		this.depoSoshikiKb = depoSoshikiKb;
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
