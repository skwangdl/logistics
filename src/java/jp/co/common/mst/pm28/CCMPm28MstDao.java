package jp.co.common.mst.pm28;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import jp.co.common.frame.dao.BaseDao;

/**
 * ntsAppシステムのログインDAOの実装クラス
 *
 * @author NTS
 * @version 1.0.0 : 2015.09.14 新規作成
 * 
 * @author NTS
 * @version 1.1.0 : 2017.10.13 見直し修正
 */
@Repository
public class CCMPm28MstDao extends BaseDao{

	/** Log4jの定義 */
	private final Log logger = LogFactory.getLog(getClass());
	
	/**
	 * 
	 * @return
	 */
	public List<CCMPm28MstModel> getPm28List(String kyoutuuCd) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARAM_KYOUTUU_CD", kyoutuuCd);
		return this.sqlSessionTemplate.selectList("ccmSelectMst.getPm28List", param);
	}
	
}
