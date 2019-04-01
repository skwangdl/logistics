package jp.co.plc.pl1.c0120;

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
public class PLC1C0120Dao extends BaseDao{
	
	public List<PLC1C0120Model> insert(PLC1C0120Model plc1c0120Model) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARAM_DEPO_CD", plc1c0120Model.getDepoCd());
		param.put("PARAM_DEPO_SOSHIKI_KB", plc1c0120Model.getDepoSoshikiKb());
		param.put("PARAM_ITAKU_GYOUSHA_CD", plc1c0120Model.getHelp2_itakuGyoushaCd());
		param.put("PARAM_DEPO_NM", plc1c0120Model.getDepoNm());
		return this.sqlSessionTemplate.selectList("plc1c0120.insert",param);
	}
	public List<PLC1C0120Model> update(PLC1C0120Model plc1c0120Model) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARAM_DEPO_CD", plc1c0120Model.getDepoCd());
		param.put("PARAM_DEPO_SOSHIKI_KB", plc1c0120Model.getDepoSoshikiKb());
		param.put("PARAM_ITAKU_GYOUSHA_CD", plc1c0120Model.getHelp2_itakuGyoushaCd());
		param.put("PARAM_DEPO_NM", plc1c0120Model.getDepoNm());
		return this.sqlSessionTemplate.selectList("plc1c0120.update",param);
	}
	public List<PLC1C0120Model> delete(PLC1C0120Model plc1c0120Model) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARAM_DEPO_CD", plc1c0120Model.getDepoCd());
		return this.sqlSessionTemplate.selectList("plc1c0120.delete",param);
	}
	
	public List<PLC1C0120Model> getMeisaiList(String depoCd) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_DEPO_CD", depoCd);
		return this.sqlSessionTemplate.selectList("plc1c0120.getMeisaiList",param);
	}
}