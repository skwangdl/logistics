package jp.co.common.mst.pm01;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.common.CCMWrappedBeanUtils;

@Component
public class CCMPm01MstLogic {
	@Autowired
	private CCMPm01MstDao CCMPm01MstDao;
	
	public List<CCMPm01MstModel> getPm01List() {
		return CCMPm01MstDao.getPm01List();
	}
	
	@SuppressWarnings("unchecked")
	public List<CCMPm01MstModel> getDepoList() {
		
		List<CCMPm01MstModel> returnList = new ArrayList<CCMPm01MstModel>();
		List<CCMPm01MstModel> pm01List;
		try {
			pm01List = CCMWrappedBeanUtils.copyList(CCMPm01MstModel.class, getPm01List());
			for (int i=0; i<pm01List.size(); i++) {
				CCMPm01MstModel model = pm01List.get(i);
				model.setDepoNm(model.getDepoNm() + "(" + model.getDepoCd() + ")");
				CCMPm01MstModel modelCopy = new CCMPm01MstModel();
				CCMWrappedBeanUtils.copyProperties(modelCopy, model);
				if (model.getDepoCd().equals(model.getEigyoushoCd())) {
					returnList.add(model);
				}
				modelCopy.setDepoNm("@@" + modelCopy.getDepoNm());
				returnList.add(modelCopy);
			}
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		}
		
		return returnList;
	}
	
	@SuppressWarnings("unchecked")
	public List<CCMPm01MstModel> getEigyoushoCd() {
		List<CCMPm01MstModel> returnList = new ArrayList<CCMPm01MstModel>();
		try {
			List<CCMPm01MstModel> pm01List = CCMWrappedBeanUtils.copyList(CCMPm01MstModel.class, getPm01List());
			for (int i=0; i<pm01List.size(); i++) {
				CCMPm01MstModel model = pm01List.get(i);
				if (model.getDepoCd().equals(model.getEigyoushoCd())) {
					model.setDepoNm(model.getDepoNm() + "(" + model.getDepoCd() + ")");
					returnList.add(model);
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		
		return returnList;
	}
}
