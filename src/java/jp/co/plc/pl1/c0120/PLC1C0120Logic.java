package jp.co.plc.pl1.c0120;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.common.frame.service.BaseBusinessService;

/**
 * <p>
 * 
 * </p>
 * 
 * @author NTS
 * @version 1.0.0 : 2015.09.14 
 *
 * @author NTS
 * @version 1.1.0 : 2017.10.13 
 * 
 **/

@Service
@Transactional(rollbackFor=Exception.class)
public class PLC1C0120Logic extends BaseBusinessService{
	
	@Autowired
	private PLC1C0120Dao c0120Dao;
	
	public List<PLC1C0120Model> insert(PLC1C0120Model plc1c0120Model) {
		return c0120Dao.insert(plc1c0120Model);
	}
	public List<PLC1C0120Model> update(PLC1C0120Model plc1c0120Model) {
		return c0120Dao.update(plc1c0120Model);
	}
	public List<PLC1C0120Model> delete(PLC1C0120Model plc1c0120Model) {
		return c0120Dao.delete(plc1c0120Model);
	}
	
	public List<PLC1C0120Model> getMeisaiList(String depoCd) {
		return c0120Dao.getMeisaiList(depoCd);
	}
}