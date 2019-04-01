package jp.co.plc.pl1.h0020;

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
public class PLC1H0020Logic extends BaseBusinessService{
	
	@Autowired
	private PLC1H0020Dao h0020Dao;
	
	public List<PLC1H0020Vo> getMeisaiList(PLC1H0020Model plc1h0020Model) {
		return h0020Dao.getMeisaiList(plc1h0020Model);
	}
	
}