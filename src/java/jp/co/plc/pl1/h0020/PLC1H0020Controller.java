package jp.co.plc.pl1.h0020;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.common.mst.pm28.CCMPm28MstService;
import jp.co.fourseeds.fsnet.beans.test.PagingCommon;

/**
 * <p>
 * 
 * </p>
 * 
 * @author NTS
 * @version 1.0.0 : 2015.09.14 
 *
 * @author NTS
 * @version 1.1.0 : 2017.10.13 
 * 
 **/

@Controller
@RequestMapping("/h0020")
public class PLC1H0020Controller {
	
	@Autowired
	private PLC1H0020Service PLC1H0020Service;
	@Autowired
	private CCMPm28MstService CCMPm28MstService;
	
	/**
	 * 
	 * @param
	 * @return String 
	 * */
	@RequestMapping("/doInit")
	public String doInit(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
		//上位画面の入力検索キー（業者ＣＤ）を取得
		String w_parm = new String(request.getParameter("PARAM").getBytes("8859_1"), "MS932");
		String w_flg = new String(request.getParameter("FLG").getBytes("8859_1"), "MS932");

		PLC1H0020Model c0120model = new PLC1H0020Model();
		c0120model.setGyousyaCd(w_parm);
		if ("1".equals(w_flg)) {
			c0120model.setKanrimotoFlg("1");
		} else {
			c0120model.setKanrimotoFlg("0");
		}
		
		//初期表示フォームのクリア
		c0120model.setGyousyaCd(w_parm);
		c0120model.setGyousyaNm("");
		c0120model.setKanrimotoFlgItems(CCMPm28MstService.getPm28List("075"));
		c0120model.setSearchFlg("1");
		model.addAttribute("h0020model", c0120model);
		
		return "plc1/h0020";
	}
	
	/**
	 * 
	 * @param
	 * @return String 
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/doSearch")
	@ResponseBody
	public PagingCommon doSearch(Model model ,
			@RequestParam(value = "rows", defaultValue = "10") Integer rows,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "record", defaultValue = "0") Integer record,
			@ModelAttribute("h0020model")PLC1H0020Model h0020model,
			HttpServletRequest request
			) throws Exception {

			List<PLC1H0020Vo> list = PLC1H0020Service.getMeisaiList(h0020model);
			PagingCommon ts = new PagingCommon();
			ts.setPage(page);
			ts.setRows(rows);
			ts.setRecord(record);
			ts.paging(list);
			return ts;
	}
	
}