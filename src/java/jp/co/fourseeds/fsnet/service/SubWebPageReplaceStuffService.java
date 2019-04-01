package jp.co.fourseeds.fsnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.common.frame.service.BaseBusinessService;

import jp.co.fourseeds.fsnet.beans.SubWebPageReplaceStuffBean;
import jp.co.fourseeds.fsnet.dao.SubWebPageReplaceStuffDao;

/**
 * ÉTÉuâÊñ (WebíSìñé“IDç∑Çµë÷Ç¶_é–àıèÓïÒê›íË)
 * 
 *-----------------------------------------------------------
 *Å@Version      When            Who            Why
 *-----------------------------------------------------------
 * 1.1		2017/09/11			NTS			å©íºÇµëŒâû
 * 
 **/

@Component
public class SubWebPageReplaceStuffService extends BaseBusinessService{

	@Autowired
	private SubWebPageReplaceStuffDao stuffDao;

	public List<SubWebPageReplaceStuffBean> getStuffList(SubWebPageReplaceStuffBean stuffBean, String strOrderBy, String popFromFlag) {
		return stuffDao.getStuffList(stuffBean, strOrderBy, popFromFlag);
	}

}