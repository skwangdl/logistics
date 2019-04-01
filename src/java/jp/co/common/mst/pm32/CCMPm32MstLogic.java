package jp.co.common.mst.pm32;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.common.CCMWrappedBeanUtils;

@Component
public class CCMPm32MstLogic {
	@Autowired
	private CCMPm32MstDao CCMPm32MstDao;
	
	public List<CCMPm32MstModel> getPm32List() {
		
		return CCMPm32MstDao.getPm32List();
	}
}
