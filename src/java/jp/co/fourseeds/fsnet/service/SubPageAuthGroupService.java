package jp.co.fourseeds.fsnet.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jp.co.fourseeds.fsnet.beans.SubPageAuthGroupFormBean;
import jp.co.fourseeds.fsnet.beans.page.AuthGroupBean;
import jp.co.fourseeds.fsnet.dao.SubPageAuthGroupDao;

import jp.co.common.frame.service.BaseBusinessService;

/**
 * 公開するグループ機能サービス実装クラス
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
@SuppressWarnings(value={"unchecked"})
@Component
public class SubPageAuthGroupService extends BaseBusinessService{
	
	@Autowired
	private SubPageAuthGroupDao authGroupDao;
	
	/**
	 * 検索結果を取得
	 * @param formBean
	 *        フォーム
	 * @return
	 *        なし
	 * */
	public void search(SubPageAuthGroupFormBean formBean) {
		// 汎用
		if ("0".equals(formBean.getPersonalFlag())) {
			// トップグループ情報を取得
			List<AuthGroupBean> groupList = authGroupDao.searchCommTopGroupList(formBean.getGroupSearchName());
			// 取得のグループリストを設定する。
			formBean.setGroupList(groupList);
		// 個人用グループ
		} else if ("1".equals(formBean.getPersonalFlag())) {
			// 個人用グループを取得する。
			List<AuthGroupBean> groupList = authGroupDao.searchPersTopGroupList(
					formBean.getGroupSearchName(), super.getLoginUserBean().getUserId(),
					super.getLoginUserBean().getRole());
			// 取得のグループリストを設定する。
			formBean.setGroupList(groupList);
		}
	}
}