package jp.co.common.mst.pm01;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.common.frame.service.BaseBusinessService;

/**
 *  ログイン機能サービス実装。
 * 
 * @author NTS
 * @version 1.0.0 : 2015.09.14 新規作成
 * 
 * @author NTS
 * @version 1.1.0 : 2017.10.13 見直し修正
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class CCMPm01MstService extends BaseBusinessService{

	@Autowired
	private CCMPm01MstLogic CCMPm01MstLogic;
	

	public List<CCMPm01MstModel> getDepoList() throws Exception{
		return CCMPm01MstLogic.getDepoList();
	}
	
	public List<CCMPm01MstModel> getEigyoushoCd() throws Exception{
		return CCMPm01MstLogic.getEigyoushoCd();
	}

}