package jp.co.fourseeds.fsnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.fourseeds.fsnet.beans.SubStoreOwnerBean;
import jp.co.fourseeds.fsnet.dao.SubStoreOwnerDao;

@Component
public class SubStoreOwnerService {
	@Autowired
	private SubStoreOwnerDao ownerDao;
	public List<SubStoreOwnerBean> getownerList(SubStoreOwnerBean ownerBean, String strOrderBy) {

		return ownerDao.getownerList(ownerBean, strOrderBy);
	}
}