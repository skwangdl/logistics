package jp.co.plc.pl1.h0020;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

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

@Repository
public class PLC1H0020Dao extends BaseDao{
	
	public List<PLC1H0020Vo> getMeisaiList(PLC1H0020Model plc1h0020Model) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARAM_GYOUSYA_CD", plc1h0020Model.getGyousyaCd());
		param.put("PARAM_GYOUSYA_NM", plc1h0020Model.getGyousyaNm());
		param.put("PARAM_KANRIMOTOKAISYA_KB", plc1h0020Model.getKanrimotoKaisyaKb());
		return this.sqlSessionTemplate.selectList("plc1h0020.getMeisaiList",param);
	}
	
}