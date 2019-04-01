package jp.co.common.mst.pm28;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.common.CCMWrappedBeanUtils;

@Component
public class CCMPm28MstLogic {
	@Autowired
	private CCMPm28MstDao CCMPm28MstDao;
	
	public List<CCMPm28MstModel> getPm28List(String kyoutuuCd) {
		
		return CCMPm28MstDao.getPm28List(kyoutuuCd);
	}
}
