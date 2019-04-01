package jp.co.plc.pl1.c0130;

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
public class PLC1C0130Service extends BaseBusinessService{
	
	@Autowired
	private PLC1C0130Logic c0130Logic;
	
	public List<PLC1C0130Model> getMeisaiList(PLC1C0130Model plc1c0130Model) {
		return c0130Logic.getMeisaiList(plc1c0130Model);
	}
}