package jp.co.fourseeds.fsnet.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jp.co.common.frame.service.BaseBusinessService;
import jp.co.fourseeds.fsnet.beans.HotPageFormBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.HotPageDao;

/**
 * Subお気に入りサービス実装クラス
 * 
 * File Name: HotPageService.java 
 * Created: 2016/01/06
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0      2016/01/06          NTS            作成
 *
 **/
@Component
public class HotPageService extends BaseBusinessService{

	@Autowired
	private HotPageDao hotPageDao;
	
	/**
	 * お気に入りデータ取得
	 * @param 
	 *            なし
	 * @return 
	 *            お気に入りデータリスト
	 */
	public List<HotPageFormBean> getHotPageFile() {
		return hotPageDao.getHotPageFile(super.getLoginUserBean().getUserId());
	}
	
	/**
	 * お気に入りコンテンツ情報登録
	 * @param formBean
	 *            フォームビン
	 * @return 
	 *            なし
	 */
	private void insertHotPage(HotPageFormBean formBean) {
		// Max配列順を取得する。
		String maxOrderBy = hotPageDao.getMaxOrderBy(formBean, super.getLoginUserBean().getUserId());
		// Max配列順がありません場合
		if (StringUtil.isEmpty(maxOrderBy)) {
			// 初期値を設定する。
			formBean.setOrderBy("1");
		} else {
			// Max配列順 + 1
			formBean.setOrderBy(String.valueOf(Long.valueOf(maxOrderBy) + 1));
		}
		hotPageDao.insertHotPage(formBean, super.getLoginUserBean().getUserId());
	}
	
	/**
	 * お気に入り情報登録
	 * @param formBean
	 *            フォームビン
	 * @return 
	 *            なし
	 */
	private void insertHotPageFile(HotPageFormBean formBean) {
		// Maxお気に入りフォルダIDを取得する。
		String maxHotPageId = hotPageDao.getMaxHotPageId(formBean, super.getLoginUserBean().getUserId());
		// Maxお気に入りフォルダIDがありません場合
		if (StringUtil.isEmpty(maxHotPageId)) {
			// 初期値を設定する。
			formBean.setHotPageId("100001");
		} else {
			// Maxお気に入りフォルダID + 1
			formBean.setHotPageId(String.valueOf(Long.valueOf(maxHotPageId) + 1));
		}
		hotPageDao.insertHotPageFile(formBean, super.getLoginUserBean().getUserId());
	}
	
	/**
	 * お気に入り明細データ取得
	 * @param detailBean
	 *            お気に入りビン
	 * @return 
	 *            お気に入り明細データリスト
	 */
	public List<HotPageFormBean> getHotPageDetail(HotPageFormBean detailBean) {
		// お気に入り明細データを取得する。
		return hotPageDao.getHotPageDetail(detailBean,super.getLoginUserBean());
	}
	
	/**
	 * お気に入り明細データ削除
	 * @param formBean
	 *            お気に入りビン
	 * @return 
	 *            なし
	 */
	public void deleteHotPageFile (HotPageFormBean formBean) {
		// お気に入り情報を削除する。
		hotPageDao.updateHotPageFileInvalid(formBean,super.getLoginUserBean().getUserId());
		// お気に入りコンテンツ情報を削除する。
		hotPageDao.updateHotPageInvalid(formBean,super.getLoginUserBean().getUserId());
	}
	
	/**
	 * お気に入り明細データ削除
	 * @param formBean
	 *            お気に入りビン
	 * @return 
	 *            なし
	 */
	public void updateHotPage (HotPageFormBean formBean) {
		// お気に入りフォルダ名を更新する。
		hotPageDao.updateHotPageFileTitle(formBean,super.getLoginUserBean().getUserId());
		// お気に入り情報を削除する。
		hotPageDao.deleteHotPageAll(formBean,super.getLoginUserBean().getUserId());
		// 配列順Index初期化
		int orderIdx = 0;
		// 明細データをループ。
		for (HotPageFormBean bean: formBean.getHotPageList()) {
			// 削除フラグをチェックしない場合
			if (StringUtil.isEmpty(bean.getDeleteFlag())) {
				// 配列順を設定する。
				bean.setOrderBy(String.valueOf(orderIdx++));
				// お気に入りコンテンツ情報を登録する。
				hotPageDao.insertHotPage(bean,super.getLoginUserBean().getUserId());
			}
		}
	}
	
	/**
	 * お気に入り明細データ更新
	 * @param formBean
	 *            お気に入りビン
	 * @return 
	 *            なし* 
	 * */
	public void modifyHotPage(HotPageFormBean formBean) {
		// 新規場合
		if(StringUtil.isEmpty(formBean.getHotPageId())){
			// お気に入り情報登録
			insertHotPageFile(formBean);
		}
		// お気に入りコンテンツ情報削除
		hotPageDao.deleteHotPageSingle(formBean, super.getLoginUserBean().getUserId());
		// お気に入りコンテンツ情報登録
		insertHotPage(formBean);
	}
	
	
	/**
	 * 明細データを取得する。
	 * @param 
	 *          なし
	 * @return 
	 *          なし
	 * */
	public void searchHotPage(HotPageFormBean formBean) {
		// お気に入りデータ取得
		List<HotPageFormBean> fileList = getHotPageFile();
		// お気に入りリストループ
		for (HotPageFormBean detail:fileList) {
			// お気に入りフォルダ情報とお気に入り情報を取得する
			List<HotPageFormBean> detailList = getHotPageDetail(detail);
			// 閲覧する有効なコンテンツがグループに存在する場合
			if (detailList != null && detailList.size() > 0) {
				// 明細リストを設定する。
				detail.setHotPageList(detailList);
			}
		}
		// 上記取得のリストはフォームに設定する。
		formBean.setHotPageList(fileList);
	}
	
}