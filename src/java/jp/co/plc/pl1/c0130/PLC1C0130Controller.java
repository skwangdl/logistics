package jp.co.plc.pl1.c0130;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.common.CCMWrappedBeanUtils;
import jp.co.common.mst.pm01.CCMPm01MstModel;
import jp.co.common.mst.pm01.CCMPm01MstService;
import jp.co.common.mst.pm28.CCMPm28MstModel;
import jp.co.common.mst.pm28.CCMPm28MstService;
import jp.co.fourseeds.fsnet.beans.test.PagingCommon;
import jp.co.plc.pl1.c0120.PLC1C0120Model;

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
@RequestMapping("/c0130")
public class PLC1C0130Controller {
	
	@Autowired
	private PLC1C0130Service PLC1C0130Service;
	@Autowired
	private CCMPm01MstService CCMPm01MstService;
	@Autowired
	private CCMPm28MstService CCMPm28MstService;
	
	/**
	 * 
	 * @param
	 * @return String 
	 * */
	@RequestMapping("/doInit")
	public String doInit(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
		List<CCMPm01MstModel> eigyoushoCdItems = CCMPm01MstService.getEigyoushoCd();
		List<CCMPm28MstModel> kanrimotoKaisyaKbItems = CCMPm28MstService.getPm28List("160");
		List<CCMPm28MstModel> syozokuchiikiKbItems = CCMPm28MstService.getPm28List("012");
		if ((PLC1C0130Model) request.getAttribute("c0130model")==null) {
			PLC1C0130Model c0130model = new PLC1C0130Model();
			c0130model.setEigyoushoCdItems(eigyoushoCdItems);
			c0130model.setKanrimotoKaisyaKbItems(kanrimotoKaisyaKbItems);
			c0130model.setSyozokuchiikiKbItems(syozokuchiikiKbItems);
			model.addAttribute("c0130model", c0130model);
		} else {
			PLC1C0130Model c0130model1 = (PLC1C0130Model) request.getAttribute("c0130model");
			c0130model1.setEigyoushoCdItems(eigyoushoCdItems);
			c0130model1.setKanrimotoKaisyaKbItems(kanrimotoKaisyaKbItems);
			c0130model1.setSyozokuchiikiKbItems(syozokuchiikiKbItems);
			model.addAttribute("c0130model", c0130model1);
		}
		if ((PLC1C0130Model) request.getAttribute("c0130model")!=null) {
			PLC1C0130Model c0130model2 = (PLC1C0130Model) request.getAttribute("c0130model");
			if ("1".equals(c0130model2.getDeleteFlg())) {
				model.addAttribute("errors", "DeleteSuccess");
				model.addAttribute("errorsFlag", "1");
			}
		}
		return "plc1/c0130";
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/doSearch")
	@ResponseBody
	public PagingCommon doSearch(
			Model model,
			@RequestParam(value = "rows", defaultValue = "10") Integer rows,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "record", defaultValue = "0") Integer record,
			@ModelAttribute("c0130model")PLC1C0130Model c0130model,
			HttpServletRequest request
			) throws Exception {
		List<PLC1C0130Model> list = PLC1C0130Service.getMeisaiList(c0130model);
		request.getSession().setAttribute("meisaiList", list);
		PagingCommon ts = new PagingCommon();
		ts.setPage(page);
		ts.setRows(rows);
		ts.setRecord(record);
		ts.paging(list);
		return ts;
	}
	
	@PostMapping("/back")
	public String back(Model model) throws Exception {
		return "login/login";
	}
	
	@PostMapping("/doShousai")
	@SuppressWarnings("unchecked")
	public String doShousai(@ModelAttribute("c0130model")PLC1C0130Model c0130model, Model model, HttpServletRequest request) throws Exception {
		request.getSession().setAttribute("cccc", c0130model);
		PLC1C0120Model c0120mod = new PLC1C0120Model();
		List<PLC1C0130Model> list =  (List<PLC1C0130Model>) request.getSession().getAttribute("meisaiList");
		CCMWrappedBeanUtils.copyProperties(c0120mod, list.get(0));
		request.setAttribute("c0120model", c0120mod);
		return "forward:/c0120/doShousai";
	}
	
	@PostMapping("/doInsert")
	public String doInsert(@ModelAttribute("c0130model")PLC1C0130Model c0130model, Model model, HttpServletRequest request) throws Exception {
		request.getSession().setAttribute("cccc", c0130model);
		return "forward:/c0120/doInit";
	}
	
}