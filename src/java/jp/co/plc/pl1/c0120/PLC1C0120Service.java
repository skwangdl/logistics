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
public class PLC1C0120Service extends BaseBusinessService{
	
	@Autowired
	private PLC1C0120Logic c0120Logic;
	
	public List<PLC1C0120Model> insert(PLC1C0120Model plc1c0120Model) {
		return c0120Logic.insert(plc1c0120Model);
	}
	public List<PLC1C0120Model> update(PLC1C0120Model plc1c0120Model) {
		return c0120Logic.update(plc1c0120Model);
	}
	public List<PLC1C0120Model> delete(PLC1C0120Model plc1c0120Model) {
		return c0120Logic.delete(plc1c0120Model);
	}
	
	public List<PLC1C0120Model> getMeisaiList(String depoCd) {
		return c0120Logic.getMeisaiList(depoCd);
	}
	
}