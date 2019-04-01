package jp.co.common.frame.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基盤DAOクラス<br>
 * 以下の機能をサポートする。<br> 
 * <ul>
 *  <li>SqlMapClientDaoSupportを継承</li>
 * </ul>
 * 
 * @author NTS
 * @version 1.0
 * 
 */
public class BaseDao {
	@Autowired
	public SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	@Autowired
	public SqlSessionTemplate sqlSessionTemplateMysql;

	public SqlSessionTemplate getSqlSessionTemplateMysql() {
		return sqlSessionTemplateMysql;
	}

	public void setSqlSessionTemplateMysql(SqlSessionTemplate sqlSessionTemplateMysql) {
		this.sqlSessionTemplateMysql = sqlSessionTemplateMysql;
	}
}
