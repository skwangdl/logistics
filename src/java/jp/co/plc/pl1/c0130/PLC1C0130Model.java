/*
 * プラスロジスティクス株式会社
 * 新配送管理システム
 * Copyright (C) 2007 PLUS LOGISTICS CORPORATION, All rights reserved.
 */
package jp.co.plc.pl1.c0130;

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
public class PLC1C0130Model {

	private String searchFlg;
	private String returnFlg;
	
	private String deleteFlg;
	
	public String getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	/**
	 * @return the returnFlg
	 */
	public String getReturnFlg() {
		return returnFlg;
	}

	/**
	 * @param returnFlg the returnFlg to set
	 */
	public void setReturnFlg(String returnFlg) {
		this.returnFlg = returnFlg;
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

	private String eigyoushoCd;
	private String eigyoushoNm;
	
	/**
	 * @return the eigyoushoNm
	 */
	public String getEigyoushoNm() {
		return eigyoushoNm;
	}

	/**
	 * @param eigyoushoNm the eigyoushoNm to set
	 */
	public void setEigyoushoNm(String eigyoushoNm) {
		this.eigyoushoNm = eigyoushoNm;
	}

	private List eigyoushoCdItems;
	

	/**
	 * @return the eigyoushoCdItems
	 */
	public List getEigyoushoCdItems() {
		return eigyoushoCdItems;
	}

	/**
	 * @param eigyoushoCdItems the eigyoushoCdItems to set
	 */
	public void setEigyoushoCdItems(List eigyoushoCdItems) {
		this.eigyoushoCdItems = eigyoushoCdItems;
	}

	private String syozokuchiikiKb;
	private List syozokuchiikiKbItems;
	

	/**
	 * @return the syozokuchiikiKbItems
	 */
	public List getSyozokuchiikiKbItems() {
		return syozokuchiikiKbItems;
	}

	/**
	 * @param syozokuchiikiKbItems the syozokuchiikiKbItems to set
	 */
	public void setSyozokuchiikiKbItems(List syozokuchiikiKbItems) {
		this.syozokuchiikiKbItems = syozokuchiikiKbItems;
	}

	private List kanrimotoKaisyaKbItems;

	/**
	 * @return the kanrimotoKaisyaKbItems
	 */
	public List getKanrimotoKaisyaKbItems() {
		return kanrimotoKaisyaKbItems;
	}

	/**
	 * @param kanrimotoKaisyaKbItems the kanrimotoKaisyaKbItems to set
	 */
	public void setKanrimotoKaisyaKbItems(List kanrimotoKaisyaKbItems) {
		this.kanrimotoKaisyaKbItems = kanrimotoKaisyaKbItems;
	}

	private String depoCd;
	private String depoNm;
	private String denwaNo;
	private String depoSoshikiKb;
	
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
	 * @return the depoSoshikiKb
	 */
	public String getDepoSoshikiKb() {
		return depoSoshikiKb;
	}

	/**
	 * @param depoSoshikiKb the depoSoshikiKb to set
	 */
	public void setDepoSoshikiKb(String depoSoshikiKb) {
		this.depoSoshikiKb = depoSoshikiKb;
	}

	/**
	 * @return the depoNm
	 */
	public String getDepoNm() {
		return depoNm;
	}

	/**
	 * @param depoNm the depoNm to set
	 */
	public void setDepoNm(String depoNm) {
		this.depoNm = depoNm;
	}

	private String sakujoKb;

	private String retrunFlg;

	private String syoriFlg;
	
	/** 管理元会社区分 */
	private String kanrimotoKaisyaKb;	

	/** 選択行番号 */
	private String lineNo;
	
	/** 一覧リスト */
	private List meisaiList;

//	// 削除区分のリセット（チェックボックスのクリア ）
//	public void reset(ActionMapping mapping, HttpServletRequest request) {
//
//		setSakujoKb(CCMCMConsts.SAKUJO_KB.YUKOU);
//
//	}

	/**
	 * depoCdの取得
	 * 
	 * @return depoCd
	 */
	public String getDepoCd() {
		return depoCd;
	}

	/**
	 * depoCdの設定
	 * 
	 * @param depoCd
	 */
	public void setDepoCd(String depoCd) {
		this.depoCd = depoCd;
	}

	/**
	 * eigyoushoCdの取得
	 * 
	 * @return eigyoushoCd
	 */
	public String getEigyoushoCd() {
		return eigyoushoCd;
	}

	/**
	 * eigyoushoCdの設定
	 * 
	 * @param eigyoushoCd
	 */
	public void setEigyoushoCd(String eigyoushoCd) {
		this.eigyoushoCd = eigyoushoCd;
	}

	/**
	 * meisaiListの取得
	 * 
	 * @return meisaiList
	 */
	public List getMeisaiList() {
		return meisaiList;
	}

	/**
	 * meisaiListの設定
	 * 
	 * @param meisaiList
	 */
	public void setMeisaiList(List meisaiList) {
		this.meisaiList = meisaiList;
	}

	/**
	 * sakujoKbの取得
	 * 
	 * @return sakujoKb
	 */
	public String getSakujoKb() {
		return sakujoKb;
	}

	/**
	 * sakujoKbの設定
	 * 
	 * @param sakujoKb
	 */
	public void setSakujoKb(String sakujoKb) {
		this.sakujoKb = sakujoKb;
	}

	/**
	 * syozokuchiikiKbの取得
	 * 
	 * @return syozokuchiikiKb
	 */
	public String getSyozokuchiikiKb() {
		return syozokuchiikiKb;
	}

	/**
	 * syozokuchiikiKbの設定
	 * 
	 * @param syozokuchiikiKb
	 */
	public void setSyozokuchiikiKb(String syozokuchiikiKb) {
		this.syozokuchiikiKb = syozokuchiikiKb;
	}

	/**
	 * lineNoの取得
	 * 
	 * @return lineNo
	 */
	public String getLineNo() {
		return lineNo;
	}

	/**
	 * lineNoの設定
	 * 
	 * @param lineNo
	 */
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	/**
	 * syoriFlgの取得
	 * 
	 * @return syoriFlg
	 */
	public String getSyoriFlg() {
		return syoriFlg;
	}

	/**
	 * syoriFlgの設定
	 * 
	 * @param syoriFlg
	 */
	public void setSyoriFlg(String syoriFlg) {
		this.syoriFlg = syoriFlg;
	}

	/**
	 * retrunFlgの取得
	 * 
	 * @return retrunFlg
	 */
	public String getRetrunFlg() {
		return retrunFlg;
	}

	/**
	 * retrunFlgの設定
	 * 
	 * @param retrunFlg
	 */
	public void setRetrunFlg(String retrunFlg) {
		this.retrunFlg = retrunFlg;
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
