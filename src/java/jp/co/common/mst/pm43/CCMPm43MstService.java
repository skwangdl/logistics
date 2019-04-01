package jp.co.common.mst.pm43;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.fourseeds.fsnet.beans.LoginImageFormBean;
import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.MailBean;
import jp.co.fourseeds.fsnet.beans.personalMail.PersonalMailBean;
import jp.co.fourseeds.fsnet.beans.test.TestBean;
import jp.co.fourseeds.fsnet.common.ConstantContainer;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.LoginDao;
import jp.co.fourseeds.fsnet.dao.PersonalMailDao;
import jp.co.fourseeds.fsnet.logic.LoginLogic;

import jp.co.common.frame.service.BaseBusinessService;
import jp.co.common.frame.util.DateUtil;
import jp.co.common.frame.util.FreemarkerUtil;
import jp.co.common.frame.util.MailUtil;
import jp.co.common.frame.util.prop.FsPropertyUtil;

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
public class CCMPm43MstService extends BaseBusinessService{

	@Autowired
	private CCMPm43MstLogic CCMPm43MstLogic;
	

	public List<CCMPm43MstModel> getPm43List() throws Exception{
		return CCMPm43MstLogic.getPm43List();
	}

}