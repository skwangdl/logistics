package jp.co.plc.pl1.menu;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=Exception.class)
public class MenuService {

	@Autowired
	MenuDao menuDao;
	
	public List<MenuNode> getMenuList() {
		return menuDao.getMenuList();
	}
	
	public List<MenuNode> getMenuListForChild() {
		return menuDao.getMenuListForChild();
	}

}
