/*
 * プラスロジスティクス株式会社
 * 新配送管理システム
 * Copyright (C) 2007 PLUS LOGISTICS CORPORATION, All rights reserved.
 */
package jp.co.plc.pl1.c0120;

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
public class PLC1C0120Model {

	private String depoCd;

	private String bumonCd;
	private List bumonCdItems;

	private String depoSoshikiKb;
	private List depoSoshikiKbItems;

	private String itakuGyoushaCd;

	private String gyousyaNm;

	private String depoNm;

	private String depoKanrishaNm;

	private String eigyoushoCd;
	private List eigyoushoCdItems;
	private String hidEigyoushoCd;

	/**
	 * @return the hidEigyoushoCd
	 */
	public String getHidEigyoushoCd() {
		return hidEigyoushoCd;
	}

	/**
	 * @param hidEigyoushoCd the hidEigyoushoCd to set
	 */
	public void setHidEigyoushoCd(String hidEigyoushoCd) {
		this.hidEigyoushoCd = hidEigyoushoCd;
	}

	private String yubinNo;

	private String juushoCd;

	private String jyusyo1Ot;

	private String jyusyo2Ot;

	private String jyusyo3Ot;

	private String denwaNo;

	private String faxNo;

	private String kasutamaNpHyoujiTaniCd;
	private List kasutamaNpHyoujiTaniCdItems;

	private String syozokuchiikiKb;
	private List syozokuchiikiKbItems;

	private String npKstSyurui1Cd;
	private List npKstSyurui1CdItems;

	private String npKstSyurui2Cd;
	private List npKstSyurui2CdItems;

	private String npKstSyurui3Cd;
	private List npKstSyurui3CdItems;

	private String npKstSyurui4Cd;
	private List npKstSyurui4CdItems;

	private String npKstSyurui5Cd;
	private List npKstSyurui5CdItems;

	private String npKstSyurui6Cd;
	private List npKstSyurui6CdItems;

	private String npKstSyurui7Cd;
	private List npKstSyurui7CdItems;

	private String npKstSyurui8Cd;
	private List npKstSyurui8CdItems;

	private String npKstSyurui9Cd;
	private List npKstSyurui9CdItems;

	private String sakujoKb;

	private BigDecimal koushinCt;

	private String help2_itakuGyoushaCd;

	private String help6_yubinNo;

	private String syoriFlg;
	
	private String eigyoushoKb;
	
	private String eigyoushoChk;
	
	private String kanrimotoKaisyaKb;
	private List kanrimotoKaisyaKbItems;
	private String cstmCenterBumonCd;
	private List cstmCenterBumonCdItems;
	
	private String  kanrimotoKaisyaKb_comom;
	
	/**
	 * cstmCenterBumonCdの取得
	 * @return cstmCenterBumonCd
	 */
	public String getCstmCenterBumonCd() {
		return cstmCenterBumonCd;
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
	 * bumonCdの取得
	 * 
	 * @return bumonCd
	 */
	public String getBumonCd() {
		return bumonCd;
	}

	/**
	 * bumonCdの設定
	 * 
	 * @param bumonCd
	 */
	public void setBumonCd(String bumonCd) {
		this.bumonCd = bumonCd;
	}

	/**
	 * denwaNoの取得
	 * 
	 * @return denwaNo
	 */
	public String getDenwaNo() {
		return denwaNo;
	}

	/**
	 * denwaNoの設定
	 * 
	 * @param denwaNo
	 */
	public void setDenwaNo(String denwaNo) {
		this.denwaNo = denwaNo;
	}

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
	 * depoKanrishaNmの取得
	 * 
	 * @return depoKanrishaNm
	 */
	public String getDepoKanrishaNm() {
		return depoKanrishaNm;
	}

	/**
	 * depoKanrishaNmの設定
	 * 
	 * @param depoKanrishaNm
	 */
	public void setDepoKanrishaNm(String depoKanrishaNm) {
		this.depoKanrishaNm = depoKanrishaNm;
	}

	/**
	 * depoNmの取得
	 * 
	 * @return depoNm
	 */
	public String getDepoNm() {
		return depoNm;
	}

	/**
	 * depoNmの設定
	 * 
	 * @param depoNm
	 */
	public void setDepoNm(String depoNm) {
		this.depoNm = depoNm;
	}

	/**
	 * depoSoshikiKbの取得
	 * 
	 * @return depoSoshikiKb
	 */
	public String getDepoSoshikiKb() {
		return depoSoshikiKb;
	}

	/**
	 * depoSoshikiKbの設定
	 * 
	 * @param depoSoshikiKb
	 */
	public void setDepoSoshikiKb(String depoSoshikiKb) {
		this.depoSoshikiKb = depoSoshikiKb;
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
	 * faxNoの取得
	 * 
	 * @return faxNo
	 */
	public String getFaxNo() {
		return faxNo;
	}

	/**
	 * faxNoの設定
	 * 
	 * @param faxNo
	 */
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	/**
	 * gyousyaNmの取得
	 * 
	 * @return gyousyaNm
	 */
	public String getGyousyaNm() {
		return gyousyaNm;
	}

	/**
	 * gyousyaNmの設定
	 * 
	 * @param gyousyaNm
	 */
	public void setGyousyaNm(String gyousyaNm) {
		this.gyousyaNm = gyousyaNm;
	}

	/**
	 * itakuGyoushaCdの取得
	 * 
	 * @return itakuGyoushaCd
	 */
	public String getItakuGyoushaCd() {
		return itakuGyoushaCd;
	}

	/**
	 * itakuGyoushaCdの設定
	 * 
	 * @param itakuGyoushaCd
	 */
	public void setItakuGyoushaCd(String itakuGyoushaCd) {
		this.itakuGyoushaCd = itakuGyoushaCd;
	}

	/**
	 * juushoCdの取得
	 * 
	 * @return juushoCd
	 */
	public String getJuushoCd() {
		return juushoCd;
	}

	/**
	 * juushoCdの設定
	 * 
	 * @param juushoCd
	 */
	public void setJuushoCd(String juushoCd) {
		this.juushoCd = juushoCd;
	}

	/**
	 * jyusyo1Otの取得
	 * 
	 * @return jyusyo1Ot
	 */
	public String getJyusyo1Ot() {
		return jyusyo1Ot;
	}

	/**
	 * jyusyo1Otの設定
	 * 
	 * @param jyusyo1Ot
	 */
	public void setJyusyo1Ot(String jyusyo1Ot) {
		this.jyusyo1Ot = jyusyo1Ot;
	}

	/**
	 * jyusyo2Otの取得
	 * 
	 * @return jyusyo2Ot
	 */
	public String getJyusyo2Ot() {
		return jyusyo2Ot;
	}

	/**
	 * jyusyo2Otの設定
	 * 
	 * @param jyusyo2Ot
	 */
	public void setJyusyo2Ot(String jyusyo2Ot) {
		this.jyusyo2Ot = jyusyo2Ot;
	}

	/**
	 * jyusyo3Otの取得
	 * 
	 * @return jyusyo3Ot
	 */
	public String getJyusyo3Ot() {
		return jyusyo3Ot;
	}

	/**
	 * jyusyo3Otの設定
	 * 
	 * @param jyusyo3Ot
	 */
	public void setJyusyo3Ot(String jyusyo3Ot) {
		this.jyusyo3Ot = jyusyo3Ot;
	}

	/**
	 * kasutamaNpHyoujiTaniCdの取得
	 * 
	 * @return kasutamaNpHyoujiTaniCd
	 */
	public String getKasutamaNpHyoujiTaniCd() {
		return kasutamaNpHyoujiTaniCd;
	}

	/**
	 * kasutamaNpHyoujiTaniCdの設定
	 * 
	 * @param kasutamaNpHyoujiTaniCd
	 */
	public void setKasutamaNpHyoujiTaniCd(String kasutamaNpHyoujiTaniCd) {
		this.kasutamaNpHyoujiTaniCd = kasutamaNpHyoujiTaniCd;
	}

	/**
	 * koushinCtの取得
	 * 
	 * @return koushinCt
	 */
	public BigDecimal getKoushinCt() {
		return koushinCt;
	}

	/**
	 * koushinCtの設定
	 * 
	 * @param koushinCt
	 */
	public void setKoushinCt(BigDecimal koushinCt) {
		this.koushinCt = koushinCt;
	}

	/**
	 * npKstSyurui1Cdの取得
	 * 
	 * @return npKstSyurui1Cd
	 */
	public String getNpKstSyurui1Cd() {
		return npKstSyurui1Cd;
	}

	/**
	 * npKstSyurui1Cdの設定
	 * 
	 * @param npKstSyurui1Cd
	 */
	public void setNpKstSyurui1Cd(String npKstSyurui1Cd) {
		this.npKstSyurui1Cd = npKstSyurui1Cd;
	}

	/**
	 * npKstSyurui2Cdの取得
	 * 
	 * @return npKstSyurui2Cd
	 */
	public String getNpKstSyurui2Cd() {
		return npKstSyurui2Cd;
	}

	/**
	 * npKstSyurui2Cdの設定
	 * 
	 * @param npKstSyurui2Cd
	 */
	public void setNpKstSyurui2Cd(String npKstSyurui2Cd) {
		this.npKstSyurui2Cd = npKstSyurui2Cd;
	}

	/**
	 * npKstSyurui3Cdの取得
	 * 
	 * @return npKstSyurui3Cd
	 */
	public String getNpKstSyurui3Cd() {
		return npKstSyurui3Cd;
	}

	/**
	 * npKstSyurui3Cdの設定
	 * 
	 * @param npKstSyurui3Cd
	 */
	public void setNpKstSyurui3Cd(String npKstSyurui3Cd) {
		this.npKstSyurui3Cd = npKstSyurui3Cd;
	}

	/**
	 * npKstSyurui4Cdの取得
	 * 
	 * @return npKstSyurui4Cd
	 */
	public String getNpKstSyurui4Cd() {
		return npKstSyurui4Cd;
	}

	/**
	 * npKstSyurui4Cdの設定
	 * 
	 * @param npKstSyurui4Cd
	 */
	public void setNpKstSyurui4Cd(String npKstSyurui4Cd) {
		this.npKstSyurui4Cd = npKstSyurui4Cd;
	}

	/**
	 * npKstSyurui5Cdの取得
	 * 
	 * @return npKstSyurui5Cd
	 */
	public String getNpKstSyurui5Cd() {
		return npKstSyurui5Cd;
	}

	/**
	 * npKstSyurui5Cdの設定
	 * 
	 * @param npKstSyurui5Cd
	 */
	public void setNpKstSyurui5Cd(String npKstSyurui5Cd) {
		this.npKstSyurui5Cd = npKstSyurui5Cd;
	}

	/**
	 * npKstSyurui6Cdの取得
	 * 
	 * @return npKstSyurui6Cd
	 */
	public String getNpKstSyurui6Cd() {
		return npKstSyurui6Cd;
	}

	/**
	 * npKstSyurui6Cdの設定
	 * 
	 * @param npKstSyurui6Cd
	 */
	public void setNpKstSyurui6Cd(String npKstSyurui6Cd) {
		this.npKstSyurui6Cd = npKstSyurui6Cd;
	}

	/**
	 * npKstSyurui7Cdの取得
	 * 
	 * @return npKstSyurui7Cd
	 */
	public String getNpKstSyurui7Cd() {
		return npKstSyurui7Cd;
	}

	/**
	 * npKstSyurui7Cdの設定
	 * 
	 * @param npKstSyurui7Cd
	 */
	public void setNpKstSyurui7Cd(String npKstSyurui7Cd) {
		this.npKstSyurui7Cd = npKstSyurui7Cd;
	}

	/**
	 * npKstSyurui8Cdの取得
	 * 
	 * @return npKstSyurui8Cd
	 */
	public String getNpKstSyurui8Cd() {
		return npKstSyurui8Cd;
	}

	/**
	 * npKstSyurui8Cdの設定
	 * 
	 * @param npKstSyurui8Cd
	 */
	public void setNpKstSyurui8Cd(String npKstSyurui8Cd) {
		this.npKstSyurui8Cd = npKstSyurui8Cd;
	}

	/**
	 * npKstSyurui9Cdの取得
	 * 
	 * @return npKstSyurui9Cd
	 */
	public String getNpKstSyurui9Cd() {
		return npKstSyurui9Cd;
	}

	/**
	 * npKstSyurui9Cdの設定
	 * 
	 * @param npKstSyurui9Cd
	 */
	public void setNpKstSyurui9Cd(String npKstSyurui9Cd) {
		this.npKstSyurui9Cd = npKstSyurui9Cd;
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
	 * yubinNoの取得
	 * 
	 * @return yubinNo
	 */
	public String getYubinNo() {
		return yubinNo;
	}

	/**
	 * yubinNoの設定
	 * 
	 * @param yubinNo
	 */
	public void setYubinNo(String yubinNo) {
		this.yubinNo = yubinNo;
	}

//	// 削除区分のリセット（チェックボックスのクリア ）
//	public void reset(ActionMapping mapping, HttpServletRequest request) {
//
//		setSakujoKb(CCMCMConsts.SAKUJO_KB.YUKOU);
//		setEigyoushoChk("0");
//
//	}

	/**
	 * help2_itakuGyoushaCdの取得
	 * 
	 * @return help2_itakuGyoushaCd
	 */
	public String getHelp2_itakuGyoushaCd() {
		return help2_itakuGyoushaCd;
	}

	/**
	 * help2_itakuGyoushaCdの設定
	 * 
	 * @param help2_itakuGyoushaCd
	 */
	public void setHelp2_itakuGyoushaCd(String help2_itakuGyoushaCd) {
		this.help2_itakuGyoushaCd = help2_itakuGyoushaCd;
	}

	/**
	 * help6_yubinNoの取得
	 * 
	 * @return help6_yubinNo
	 */
	public String getHelp6_yubinNo() {
		return help6_yubinNo;
	}

	/**
	 * help6_yubinNoの設定
	 * 
	 * @param help6_yubinNo
	 */
	public void setHelp6_yubinNo(String help6_yubinNo) {
		this.help6_yubinNo = help6_yubinNo;
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
	 * eigyoushoChkの取得
	 * @return eigyoushoChk
	 */
	public String getEigyoushoChk() {
		return eigyoushoChk;
	}

	/**
	 * eigyoushoChkの設定
	 * @param eigyoushoChk
	 */
	public void setEigyoushoChk(String eigyoushoChk) {
		this.eigyoushoChk = eigyoushoChk;
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

	/**
	 * kcstmCenterBumonCdの取得
	 * @return kcstmCenterBumonCd
	 */
	public String getKcstmCenterBumonCd() {
		return cstmCenterBumonCd;
	}

	/**
	 * kcstmCenterBumonCdの設定
	 * @param kcstmCenterBumonCd
	 */
	public void setCstmCenterBumonCd(String cstmCenterBumonCd) {
		this.cstmCenterBumonCd = cstmCenterBumonCd;
	}

	/**
	 * kanrimotoKaisyaKb_comomの取得
	 * @return kanrimotoKaisyaKb_comom
	 */
	public String getKanrimotoKaisyaKb_comom() {
		return kanrimotoKaisyaKb_comom;
	}

	/**
	 * kanrimotoKaisyaKb_comomの設定
	 * @param kanrimotoKaisyaKb_comom
	 */
	public void setKanrimotoKaisyaKb_comom(String kanrimotoKaisyaKb_comom) {
		this.kanrimotoKaisyaKb_comom = kanrimotoKaisyaKb_comom;
	}

	/**
	 * @return the bumonCdItems
	 */
	public List getBumonCdItems() {
		return bumonCdItems;
	}

	/**
	 * @param bumonCdItems the bumonCdItems to set
	 */
	public void setBumonCdItems(List bumonCdItems) {
		this.bumonCdItems = bumonCdItems;
	}

	/**
	 * @return the depoSoshikiKbItems
	 */
	public List getDepoSoshikiKbItems() {
		return depoSoshikiKbItems;
	}

	/**
	 * @param depoSoshikiKbItems the depoSoshikiKbItems to set
	 */
	public void setDepoSoshikiKbItems(List depoSoshikiKbItems) {
		this.depoSoshikiKbItems = depoSoshikiKbItems;
	}

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

	/**
	 * @return the kasutamaNpHyoujiTaniCdItems
	 */
	public List getKasutamaNpHyoujiTaniCdItems() {
		return kasutamaNpHyoujiTaniCdItems;
	}

	/**
	 * @param kasutamaNpHyoujiTaniCdItems the kasutamaNpHyoujiTaniCdItems to set
	 */
	public void setKasutamaNpHyoujiTaniCdItems(List kasutamaNpHyoujiTaniCdItems) {
		this.kasutamaNpHyoujiTaniCdItems = kasutamaNpHyoujiTaniCdItems;
	}

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

	/**
	 * @return the npKstSyurui1CdItems
	 */
	public List getNpKstSyurui1CdItems() {
		return npKstSyurui1CdItems;
	}

	/**
	 * @param npKstSyurui1CdItems the npKstSyurui1CdItems to set
	 */
	public void setNpKstSyurui1CdItems(List npKstSyurui1CdItems) {
		this.npKstSyurui1CdItems = npKstSyurui1CdItems;
	}

	/**
	 * @return the npKstSyurui2CdItems
	 */
	public List getNpKstSyurui2CdItems() {
		return npKstSyurui2CdItems;
	}

	/**
	 * @param npKstSyurui2CdItems the npKstSyurui2CdItems to set
	 */
	public void setNpKstSyurui2CdItems(List npKstSyurui2CdItems) {
		this.npKstSyurui2CdItems = npKstSyurui2CdItems;
	}

	/**
	 * @return the npKstSyurui3CdItems
	 */
	public List getNpKstSyurui3CdItems() {
		return npKstSyurui3CdItems;
	}

	/**
	 * @param npKstSyurui3CdItems the npKstSyurui3CdItems to set
	 */
	public void setNpKstSyurui3CdItems(List npKstSyurui3CdItems) {
		this.npKstSyurui3CdItems = npKstSyurui3CdItems;
	}

	/**
	 * @return the npKstSyurui4CdItems
	 */
	public List getNpKstSyurui4CdItems() {
		return npKstSyurui4CdItems;
	}

	/**
	 * @param npKstSyurui4CdItems the npKstSyurui4CdItems to set
	 */
	public void setNpKstSyurui4CdItems(List npKstSyurui4CdItems) {
		this.npKstSyurui4CdItems = npKstSyurui4CdItems;
	}

	/**
	 * @return the npKstSyurui5CdItems
	 */
	public List getNpKstSyurui5CdItems() {
		return npKstSyurui5CdItems;
	}

	/**
	 * @param npKstSyurui5CdItems the npKstSyurui5CdItems to set
	 */
	public void setNpKstSyurui5CdItems(List npKstSyurui5CdItems) {
		this.npKstSyurui5CdItems = npKstSyurui5CdItems;
	}

	/**
	 * @return the npKstSyurui6CdItems
	 */
	public List getNpKstSyurui6CdItems() {
		return npKstSyurui6CdItems;
	}

	/**
	 * @param npKstSyurui6CdItems the npKstSyurui6CdItems to set
	 */
	public void setNpKstSyurui6CdItems(List npKstSyurui6CdItems) {
		this.npKstSyurui6CdItems = npKstSyurui6CdItems;
	}

	/**
	 * @return the npKstSyurui7CdItems
	 */
	public List getNpKstSyurui7CdItems() {
		return npKstSyurui7CdItems;
	}

	/**
	 * @param npKstSyurui7CdItems the npKstSyurui7CdItems to set
	 */
	public void setNpKstSyurui7CdItems(List npKstSyurui7CdItems) {
		this.npKstSyurui7CdItems = npKstSyurui7CdItems;
	}

	/**
	 * @return the npKstSyurui8CdItems
	 */
	public List getNpKstSyurui8CdItems() {
		return npKstSyurui8CdItems;
	}

	/**
	 * @param npKstSyurui8CdItems the npKstSyurui8CdItems to set
	 */
	public void setNpKstSyurui8CdItems(List npKstSyurui8CdItems) {
		this.npKstSyurui8CdItems = npKstSyurui8CdItems;
	}

	/**
	 * @return the npKstSyurui9CdItems
	 */
	public List getNpKstSyurui9CdItems() {
		return npKstSyurui9CdItems;
	}

	/**
	 * @param npKstSyurui9CdItems the npKstSyurui9CdItems to set
	 */
	public void setNpKstSyurui9CdItems(List npKstSyurui9CdItems) {
		this.npKstSyurui9CdItems = npKstSyurui9CdItems;
	}

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

	/**
	 * @return the cstmCenterBumonCdItems
	 */
	public List getCstmCenterBumonCdItems() {
		return cstmCenterBumonCdItems;
	}

	/**
	 * @param cstmCenterBumonCdItems the cstmCenterBumonCdItems to set
	 */
	public void setCstmCenterBumonCdItems(List cstmCenterBumonCdItems) {
		this.cstmCenterBumonCdItems = cstmCenterBumonCdItems;
	}

}
