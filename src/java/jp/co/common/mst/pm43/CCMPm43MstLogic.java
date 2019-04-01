package jp.co.common.mst.pm43;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.common.CCMWrappedBeanUtils;

@Component
public class CCMPm43MstLogic {
	@Autowired
	private CCMPm43MstDao CCMPm43MstDao;
	
	public List<CCMPm43MstModel> getPm43List() {
		
		return CCMPm43MstDao.getPm43List();
	}
}
