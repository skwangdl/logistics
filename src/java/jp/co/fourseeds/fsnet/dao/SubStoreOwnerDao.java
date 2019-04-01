package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.SubStoreOwnerBean;

@Repository
public class SubStoreOwnerDao extends BaseDao {

	public List<SubStoreOwnerBean> getownerList(SubStoreOwnerBean ownerBean, String strOrderBy) {
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("PARA_OWNER_ID", ownerBean.getSearchOwnerCode());
		param.put("PARA_OWNER_NAME", ownerBean.getSearchOwnerName());
		param.put("PARA_ORDER_BY", strOrderBy);
		return this.sqlSessionTemplate.selectList("subStoreOwner.getownerList",param);
	}
}