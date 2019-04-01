package jp.co.fourseeds.fsnet.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.common.frame.exception.BusinessServiceException;
import jp.co.common.frame.exception.CCMApplException;
import jp.co.common.frame.exception.CCMKeyConflictException;
import jp.co.common.frame.exception.CCMSystemException;
import jp.co.common.frame.exception.CommonErrorPageException;
import jp.co.common.frame.util.ExcelUtil;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.test.TestBean;
import jp.co.fourseeds.fsnet.beans.test.TestSearchBean;
import jp.co.fourseeds.fsnet.beans.test.PagingCommon;
import jp.co.fourseeds.fsnet.service.LoginService;

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
@RequestMapping("/login")
public class LoginAction{
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private LoginService loginService;
	
	/**
	 * 
	 * 
	 * @param
	 * @return String 
	 * */
	@PostMapping("/login")
	public String login(TestBean user,Model model) throws Exception {
//		LoginUserBean loginUser = loginService.updateLoginUserInfo(user.getUserId(), user.getPassword());
		System.out.println("aaaaaaaaaaaaaaa");
		System.out.println(user.toString());
		model.addAttribute("aaa", user);
		return "aaa";
	}
	
	@PostMapping("/searchTest")
	public String searchTest(@ModelAttribute("searchBean2")TestSearchBean searchBean2 ,
			@ModelAttribute("searchBean")TestSearchBean searchBean,Model model) throws Exception {
		System.out.println(searchBean);
		// 
		List<TestBean> list = loginService.getTestData(searchBean2.getShopcd());
		if(list != null){
//			super.paging(list);
			model.addAttribute("aaa", list);
			model.addAttribute("list",list);
		}
		return "aaa";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/searchTest2")
	@ResponseBody
	public PagingCommon searchTest2(
			Model model,
//			@RequestParam(value = "gridModel", defaultValue = "1") List<TestBean> gridModel,
			@RequestParam(value = "rows", defaultValue = "10") Integer rows,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "record", defaultValue = "0") Integer record
			) throws Exception {
		List<TestBean> list = loginService.getTestDataJqGrid(); 
		System.out.println("SearchTest in");
		
		PagingCommon ts = new PagingCommon();
		ts.setPage(page);
		ts.setRows(rows);
		ts.setRecord(record);
		ts.paging(list);
		/*// 
		int pageNo = Integer.parseInt(page);
		// 
		int size = 10;
		if (!StringUtils.isEmpty(rows)) {
			size = Integer.parseInt(rows);
		}
		return service.methodName(params);*/
		return ts;
	}
	
	@PostMapping("/insertTest")
	public String insertTest(TestBean testBean,Model model) throws CCMApplException, CCMSystemException, CCMKeyConflictException  {
		// クエリ実行
		int cnt = loginService.insertTestData(testBean);
		System.out.println(cnt);
		// 実行結果判定
		if (cnt == 0) {
			System.out.println("paitaException");
			// DB登録件数が不正です。
			throw new CCMSystemException("paitaException");
		} else {
			// {0}を登録しました。
			//addMessage("msg.PLCAI001", new String[] { "デポ情報" });
			System.out.println("insert success");
			// キャッシュされているデポマスタの情報を削除
			//CCMPm01MstManager.getInstance().clear();
		}
		return "aaa";
	}
	
	@PostMapping("/updateTest")
	public String updateTest(TestBean testBean,Model model) throws Exception {
		// クエリ実行
		int cnt = loginService.updateTestData(testBean);
		System.out.println(cnt);
		// 実行結果判定
		if (cnt == 0) {
			System.out.println("paitaException");
			// DB登録件数が不正です。
			throw new CCMSystemException("paitaException");
		} else {
			// {0}を登録しました。
			//addMessage("msg.PLCAI001", new String[] { "デポ情報" });
			System.out.println("insert success");
			// キャッシュされているデポマスタの情報を削除
			//CCMPm01MstManager.getInstance().clear();
		}
		return "aaa";
	}
	
	@PostMapping("/deleteTest")
	public String deleteTest(TestBean testBean,Model model) throws Exception {
		// クエリ実行
		int cnt = loginService.deleteTestData(testBean);
		System.out.println(cnt);
		// 実行結果判定
		if (cnt == 0) {
			System.out.println("paitaException");
			// DB登録件数が不正です。
			throw new CCMSystemException("paitaException");
		} else {
			// {0}を登録しました。
			//addMessage("msg.PLCAI001", new String[] { "デポ情報" });
			System.out.println("insert success");
			// キャッシュされているデポマスタの情報を削除
			//CCMPm01MstManager.getInstance().clear();
		}
		return "aaa";
	}
	
	// searchBean
	@InitBinder("searchBean")  
	public void initBinder1(WebDataBinder binder){  
	    binder.setFieldDefaultPrefix("searchBean.");  
	}
	// searchBean2
	@InitBinder("searchBean2")  
	public void initBinder2(WebDataBinder binder){
	    binder.setFieldDefaultPrefix("searchBean2.");
	} 
	
	@GetMapping("/error")
	public String error(Model model) throws Exception {
		try {
			BusinessServiceException a = new BusinessServiceException("MSGOE010");
			a.setErrorMessageList(new ArrayList() {{add("a");add("b");add("c");add("d");add("e");}});
			if(1==1)throw a;
		} catch (Exception e) {
			model.addAttribute("errors", ((BusinessServiceException)e).getErrorMessageList());
		}
		return "/login/login";
	}
	@GetMapping("/ajax")
	@ResponseBody
	public String ajax() throws Exception {
		if(1==1)throw new CommonErrorPageException("MSGOE011");
		return "aaa";
	}

	@RequestMapping("/print")
	public void print(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 出力結果マップを取得する。
		List<LoginUserBean> outPutData = loginService.getListUser();
		
		Map<String, Object> outPutResult = new HashMap<String, Object>();
		outPutResult.put("dataList", outPutData);
		
		// エクセル作成
		ExcelUtil helper = new ExcelUtil("RRL010", "マイナンバー破棄対象リスト");
		helper.printExcelXlsx(outPutResult, response, request.getSession().getServletContext());
	}
}