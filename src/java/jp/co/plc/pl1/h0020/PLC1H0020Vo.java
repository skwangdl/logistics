/*
 * プラスロジスティクス株式会社
 * 新配送管理システム
 * Copyright (C) 2007 PLUS LOGISTICS CORPORATION, All rights reserved.
 */
package jp.co.plc.pl1.h0020;

import java.math.BigDecimal;
import java.util.List;

/**
 * <B>[クラス名]</B>
 * <UL>
 * デポ情報一覧画面・アクションフォーム
 * 
 * </UL>
 * <P>
 * <B>[説明]</B>
 * <UL>
 * デポ情報一覧画面のトップレベルのアクションフォーム
 * 
 * </UL>
 * <BR>
 * <B>[更新履歴]</B>
 * </UL>
 * <P>
 * <LI>2007/01/01 1.0 Cocom K.Yoshihashi 新規作成
 * </UL>
 * 
 * @since JDK1.5.0
 */
public class PLC1H0020Vo {

	private String gyousyaCd = null;

	private String gyousyaNm = null;
	private String listKey = null;
	private String denwaNo = null;
	private String kanrimotoKaisyaNm = null;
	private String gyousyaKj = null;
	/**
	 * @return the gyousyaCd
	 */
	public String getGyousyaCd() {
		return gyousyaCd;
	}
	/**
	 * @param gyousyaCd the gyousyaCd to set
	 */
	public void setGyousyaCd(String gyousyaCd) {
		this.gyousyaCd = gyousyaCd;
	}
	/**
	 * @return the gyousyaNm
	 */
	public String getGyousyaNm() {
		return gyousyaNm;
	}
	/**
	 * @param gyousyaNm the gyousyaNm to set
	 */
	public void setGyousyaNm(String gyousyaNm) {
		this.gyousyaNm = gyousyaNm;
	}
	/**
	 * @return the listKey
	 */
	public String getListKey() {
		return listKey;
	}
	/**
	 * @param listKey the listKey to set
	 */
	public void setListKey(String listKey) {
		this.listKey = listKey;
	}
	/**
	 * @return the denwaNo
	 */
	public String getDenwaNo() {
		return denwaNo;
	}
	/**
	 * @param denwaNo the denwaNo to set
	 */
	public void setDenwaNo(String denwaNo) {
		this.denwaNo = denwaNo;
	}
	/**
	 * @return the kanrimotoKaisyaNm
	 */
	public String getKanrimotoKaisyaNm() {
		return kanrimotoKaisyaNm;
	}
	/**
	 * @param kanrimotoKaisyaNm the kanrimotoKaisyaNm to set
	 */
	public void setKanrimotoKaisyaNm(String kanrimotoKaisyaNm) {
		this.kanrimotoKaisyaNm = kanrimotoKaisyaNm;
	}
	/**
	 * @return the gyousyaKj
	 */
	public String getGyousyaKj() {
		return gyousyaKj;
	}
	/**
	 * @param gyousyaKj the gyousyaKj to set
	 */
	public void setGyousyaKj(String gyousyaKj) {
		this.gyousyaKj = gyousyaKj;
	}

}
