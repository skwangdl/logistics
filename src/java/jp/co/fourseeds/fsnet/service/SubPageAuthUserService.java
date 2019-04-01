package jp.co.fourseeds.fsnet.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jp.co.fourseeds.fsnet.dao.SubPageAuthUserDao;

import jp.co.common.frame.service.BaseBusinessService;
import jp.co.fourseeds.fsnet.beans.SubPageAuthUserFormBean;

/**
 * 公開する個人機能サービス実装クラス
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
public class SubPageAuthUserService extends BaseBusinessService{
	
	@Autowired
	private SubPageAuthUserDao subPageAuthUserDao;
	
	/**
	 * 検索結果を取得
	 * @param formBean
	 *        フォーム
	 * @return
	 *        なし
	 * */
	public List search(SubPageAuthUserFormBean formBean) {
		return subPageAuthUserDao.getUserListByCondition(formBean);
	}
	
}