package jp.co.fourseeds.fsnet.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jp.co.fourseeds.fsnet.beans.SubPageProxyUserFormBean;
import jp.co.fourseeds.fsnet.beans.page.ProxyUserBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.SubPageProxyUserDao;
import jp.co.common.frame.service.BaseBusinessService;

/**
 * 承認者機能サービス実装クラス
 * 
 * Created: 2016/01/12
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *　VersionWhen	  Who	  Why
 *-----------------------------------------------------------
 *　1.0		2016/01/12		 NTS	 		作成
 *
 **/
@SuppressWarnings(value={"rawtypes"})
@Component
public class SubPageProxyUserService extends BaseBusinessService{
	
	@Autowired
	private SubPageProxyUserDao proxyUserDao;
	
	/**
	 * 承認者リスト取得
	 * @param formBean
	 *        フォーム
	 * @return
	 *        承認者リスト
	 * */
	public List getSubProxyUserList(SubPageProxyUserFormBean formBean) {
		return proxyUserDao.getSubProxyUserList(formBean);
	}
	
	/**
	 * ユーザ有効情報を取得
	 * @param proxyUId
	 */
	@SuppressWarnings("unchecked")
	public List<ProxyUserBean> getProxyMailList(String proxyUId){
		List proxyMailList = new ArrayList();
		// パラメータユーザーIDがある場合
		if (!StringUtil.isEmpty(proxyUId)) {
			// ユーザ有効情報を取得
			proxyMailList = proxyUserDao.getProxyMailList(proxyUId);
		}
		return proxyMailList;
	}
}