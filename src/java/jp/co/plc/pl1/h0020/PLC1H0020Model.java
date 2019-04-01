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
@SuppressWarnings("rawtypes")
public class PLC1H0020Model {

	private String searchFlg;
	private String gyousyaCd;

	private String gyousyaNm;

	/** 一覧リスト */
	private List meisaiList;
//  *START ** 20090127 ***
	/**  管理元会社区分 */
	private String kanrimotoKaisyaKb;
	
	private String kanrimotoFlg;
	
	private List kanrimotoFlgItems;

	public List getKanrimotoFlgItems() {
		return kanrimotoFlgItems;
	}

	public void setKanrimotoFlgItems(List kanrimotoFlgItems) {
		this.kanrimotoFlgItems = kanrimotoFlgItems;
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
//  *END   ** 20090127 ***	
	/**
	 * gyousyaCdの取得
	 * @return gyousyaCd
	 */
	public String getGyousyaCd() {
		return gyousyaCd;
	}

	/**
	 * gyousyaCdの設定
	 * @param gyousyaCd
	 */
	public void setGyousyaCd(String gyousyaCd) {
		this.gyousyaCd = gyousyaCd;
	}

	/**
	 * gyousyaNmの取得
	 * @return gyousyaNm
	 */
	public String getGyousyaNm() {
		return gyousyaNm;
	}
	/**
	 * gyousyaNmの設定
	 * @param gyousyaNm
	 */
	public void setGyousyaNm(String gyousyaNm) {
		this.gyousyaNm = gyousyaNm;
	}
	/**
	 * meisaiListの取得
	 * @return meisaiList
	 */
	public List getMeisaiList() {
		return meisaiList;
	}

	/**
	 * meisaiListの設定
	 * @param meisaiList
	 */
	public void setMeisaiList(List meisaiList) {
		this.meisaiList = meisaiList;
	}

	/**
	 * kanrimotoFlgの取得
	 * @return kanrimotoFlg
	 */
	public String getKanrimotoFlg() {
		return kanrimotoFlg;
	}

	/**
	 * kanrimotoFlgの設定
	 * @param kanrimotoFlg
	 */
	public void setKanrimotoFlg(String kanrimotoFlg) {
		this.kanrimotoFlg = kanrimotoFlg;
	}

	/**
	 * @return the searchFlg
	 */
	public String getSearchFlg() {
		return searchFlg;
	}

	/**
	 * @param searchFlg the searchFlg to set
	 */
	public void setSearchFlg(String searchFlg) {
		this.searchFlg = searchFlg;
	}

}
