package jp.co.plc.pl1.menu;

import java.util.List;

import org.springframework.stereotype.Repository;
import jp.co.common.frame.dao.BaseDao;

@Repository
public class MenuDao extends BaseDao{

	public List<MenuNode> getMenuList() {
		return this.sqlSessionTemplate.selectList("test.getMenuList");
	}
	
	public List<MenuNode> getMenuListForChild() {
		return this.sqlSessionTemplate.selectList("test.getMenuListForChild");
	}
}
