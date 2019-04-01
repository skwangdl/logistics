/*
 * プラスロジスティクス株式会社
 * 新配送管理システム
 * Copyright (C) 2007 PLUS Logistics Corporation. All rights reserved.
 */
package jp.co.common;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.plc.pl1.c0120.PLC1C0120Model;
import jp.co.plc.pl1.c0130.PLC1C0130Model;

/**
 * <DT><B>[クラス名]</B><UL>
 *    Commons BeanUtils ラッパークラス
 * </UL><P>
 * <DT><B>[説明]</B>
 * <UL>
 *    Commons BeanUtils ラッパークラス。
 *    とりあえず必要そうなもののみ実装。
 *    オブジェクト間のプロパティのコピーには必ず問うクラスを使用する。
 *    プロパティのコピー中に発生した例外は全てキャッチするかどうかは検討
 * </UL><P>
 * <DT><B>[更新履歴]</B><UL>
 * <LI>2006/08/21 1.0 Cocom H.Yatake 新規作成
 * </UL><P>
 * @since JDK1.5.0
 */
@Controller
@RequestMapping("/back")
public class CCMFormClearController {


	/* (non-Javadoc)
	 * @see jp.co.cocom.frame.CCMDefaultAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, jp.co.cocom.frame.CCMTrxFolder)
	 */
	@PostMapping("/back")
	public String back(@ModelAttribute("c0120model") PLC1C0120Model c0130model, Model model) throws Exception {
//		// アクションフォームを宣言
//		CCMDefaultActionForm aForm = (CCMDefaultActionForm)form;
//		
//		// 遷移元画面IDを取得
//		String prevPage = aForm.getPrevPageId();
//		
//		// 遷移元が設定されているか判定
//
//		if (CCMValidateUtils.isNullOrBlank(prevPage)) {
//			// 設定されていない場合は、デフォルトをセット
//			prevPage = CCMConstants.FORWAD_KEY.SUCCESS;
//		}
//
//		//セッション情報より、自身のアクションフォームを削除
//		removeActionForm(mapping, request);

		model.addAttribute("c0130model", c0130model);
		
		// 戻る
		return "plc1/c0130";
	}

}
