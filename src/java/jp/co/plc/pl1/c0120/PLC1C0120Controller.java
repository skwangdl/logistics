package jp.co.plc.pl1.c0120;

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

import jp.co.common.mst.pm28.CCMPm28MstService;
import jp.co.fourseeds.fsnet.beans.test.PagingCommon;
import jp.co.plc.pl1.c0130.PLC1C0130Model;

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
@RequestMapping("/c0120")
public class PLC1C0120Controller {
	
	@Autowired
	private PLC1C0120Service PLC1C0120Service;
	@Autowired
	private CCMPm28MstService CCMPm28MstService;
	
	/**
	 * 
	 * @param
	 * @return String 
	 * */
	@PostMapping("/doInit")
	public String doInit(Model model ,HttpServletRequest request) throws Exception {
//		String eigyoushoCd = req.getParameter("eigyoushoCd");
		PLC1C0120Model c0120model = new PLC1C0120Model();
		c0120model.setDepoCd("");
		c0120model.setDepoKanrishaNm("");
		c0120model.setDepoNm("");
		c0120model.setHelp2_itakuGyoushaCd("");
		c0120model.setItakuGyoushaCd("");
		c0120model.setSyoriFlg("0");
		c0120model.setDepoSoshikiKb("");
		c0120model.setDepoSoshikiKbItems(CCMPm28MstService.getPm28List("075"));
		
		model.addAttribute("c0120model", c0120model);
		return "plc1/c0120";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/doSearch")
	@ResponseBody
	public PagingCommon doSearch(
			Model model,
			@RequestParam(value = "rows", defaultValue = "10") Integer rows,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "record", defaultValue = "0") Integer record,
			@ModelAttribute("c0120model")PLC1C0120Model c0120model
			) throws Exception {
		List<PLC1C0120Model> list = PLC1C0120Service.getMeisaiList(c0120model.getEigyoushoCd());
		model.addAttribute("meisaiList", list);
		PagingCommon ts = new PagingCommon();
		ts.setPage(page);
		ts.setRows(rows);
		ts.setRecord(record);
		ts.paging(list);
		return ts;
	}
	
	@PostMapping("/doBack")
	public String doBack(@ModelAttribute("c0120model") PLC1C0120Model c0120model, Model model, HttpServletRequest request) throws Exception {
		PLC1C0130Model c0130model = new PLC1C0130Model();
		c0130model = (PLC1C0130Model) request.getSession().getAttribute("cccc");
		request.getSession().removeAttribute("cccc");
		request.setAttribute("c0130model", c0130model);
		return "forward:/c0130/doInit";
	}
	
	@PostMapping("/doInsert")
	public String doInsert(@ModelAttribute("c0120model") PLC1C0120Model c0120model, Model model) throws Exception {
		try {
			PLC1C0120Service.insert(c0120model);
			model.addAttribute("c0120model", c0120model);
			c0120model.setDepoSoshikiKbItems(CCMPm28MstService.getPm28List("075"));
			model.addAttribute("errors", "InsertSuccess");
			model.addAttribute("errorsFlag", "1");
		} catch (Exception e) {
			c0120model.setDepoSoshikiKbItems(CCMPm28MstService.getPm28List("075"));
			model.addAttribute("errors", "InsertError");
			model.addAttribute("errorsFlag", "1");
		}
		return "plc1/c0120";
	}
	
	@PostMapping("/doUpdate")
	public String doUpdate(@ModelAttribute("c0120model") PLC1C0120Model c0120model, Model model) throws Exception {
		PLC1C0120Service.update(c0120model);
		model.addAttribute("c0120model", c0120model);
		return "plc1/c0120";
	}
	
	@PostMapping("/doDelete")
	public String doDelete(@ModelAttribute("c0120model") PLC1C0120Model c0120model, Model model, HttpServletRequest request) throws Exception {
		PLC1C0120Service.delete(c0120model);
		PLC1C0130Model c0130model = new PLC1C0130Model();
		c0130model.setDeleteFlg("1");
		request.setAttribute("c0130model", c0130model);
		return "forward:/c0130/doInit";
	}
	
	@PostMapping("/doShousai")
	public String doShousai(Model model, HttpServletRequest request) throws Exception {
		PLC1C0120Model c0120model = new PLC1C0120Model();
		c0120model = (PLC1C0120Model) request.getAttribute("c0120model");
		c0120model.setDepoSoshikiKbItems(CCMPm28MstService.getPm28List("075"));
		c0120model.setSyoriFlg("1");
		model.addAttribute("c0120model", c0120model);
		return "plc1/c0120";
	}
	
}