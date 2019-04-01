package jp.co.plc.pl1.menu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/menu")
public class MenuController {
	@Autowired
	MenuService menuService;
	
	@PostMapping("/toMenu")
	public String toMenu(){
		return "menuTest";
	}
	/**
	 * ajax getMenuList
	 * @return
	 */
	@PostMapping("/getMenuList")
	@ResponseBody
	public List<MenuNode> getMenuList(){
		List<MenuNode> menuList = menuService.getMenuList();
		List<MenuNode> childList = menuService.getMenuListForChild();

		if(menuList != null){
			this.addNode(menuList,childList);
		}
		return menuList;
	}
	
	/**
	 * (commonMethod)add node  
	 * @param menuList
	 * @param childList
	 * @return
	 */
	private List<MenuNode> addNode(List<MenuNode> menuList, List<MenuNode> childList) {
		for(int i = 0;i<menuList.size();i++){
			menuList.get(i).setNodes(childList);
		}
		return menuList;		
	}

}
