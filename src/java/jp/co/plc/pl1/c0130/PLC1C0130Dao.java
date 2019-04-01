package jp.co.plc.pl1.c0130;

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
public class PLC1C0130Dao extends BaseDao{
	
	public List<PLC1C0130Model> getMeisaiList(PLC1C0130Model plc1c0130Model) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_DEPO_CD", plc1c0130Model.getDepoCd());
		param.put("PARA_EIGYOUSHO_Cd", plc1c0130Model.getEigyoushoCd());
		param.put("PARA_KANRIMOTO_KAISYA_KB", plc1c0130Model.getKanrimotoKaisyaKb());
		param.put("PARA_SYOZOKUCHIIKI_KB", plc1c0130Model.getSyozokuchiikiKb());
		param.put("PARA_SAKUJO_KB", plc1c0130Model.getSakujoKb());
		return this.sqlSessionTemplate.selectList("plc1c0130.getMeisaiList",param);
	}
}