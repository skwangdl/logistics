package jp.co.fourseeds.fsnet.service;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jp.co.fourseeds.fsnet.beans.page.PageRateItemBean;
import jp.co.fourseeds.fsnet.beans.pageRate.PageRateDetailBean;
import jp.co.fourseeds.fsnet.beans.pageRate.PageRateDetailFormBean;
import jp.co.fourseeds.fsnet.beans.pageRate.PageRateSearchFormBean;
import jp.co.fourseeds.fsnet.beans.pageRate.PageRateSearchResultBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.PageRateDao;
import jp.co.common.frame.service.BaseBusinessService;

/**
 * コンテンツ評価状況機能サービス実装クラス
 * 
 *-----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　1.0		2016/02/03		    NTS        	       作成
 * 1.1		2017/10/12			NTS			      イントラminaosi
 *
 **/
@Component
public class PageRateService extends BaseBusinessService{

	@Autowired
	private PageRateDao pageRateDao;
	
	/**
	 * コンテンツ評価情報
	 * @param HyoukaContentsForm
	 * @return
	 * @throws ServiceException
	 * @throws DataBaseException
	 */
	public List<PageRateSearchResultBean> searchResults(PageRateSearchFormBean formBean, String orderBy) {
		// コンテンツ情報を取得
		List<PageRateSearchResultBean> resultsList = pageRateDao.getContentResultsList(formBean, getLoginUserBean(), orderBy);
		// 取得のリストがある場合
		if (resultsList != null) {
			// 取得件数を設定する。
			formBean.setResultCount(resultsList.size());
			//画面表示リスト
			for (PageRateSearchResultBean bean: resultsList) {
				// 初期化
				int index = 0;
				// 明細項目を取得
				List<PageRateDetailBean> hyoukaList = pageRateDao.getPageRateaResultList(bean.getPageId());
				for (PageRateItemBean item:hyoukaList) {
					index++;
					// 項目SEQ
					bean.setSequence(item.getSequence());
					switch(index) {
					case 1 :
						// 項目１数
						bean.setEvaluationcount1(item.getEvaluationCount());
						// 項目１
						bean.setEvaluationName1(item.getEvaluationName());
						break;
					case 2 :
						// 項目２数
						bean.setEvaluationcount2(item.getEvaluationCount());
						// 項目２
						bean.setEvaluationName2(item.getEvaluationName());
						break;
					case 3 :
						// 項目３数
						bean.setEvaluationcount3(item.getEvaluationCount());
						// 項目３
						bean.setEvaluationName3(item.getEvaluationName());
						break;
					case 4 :
						// 項目４数
						bean.setEvaluationcount4(item.getEvaluationCount());
						// 項目４
						bean.setEvaluationName4(item.getEvaluationName());
						break;
					case 5 :
						// 項目５数
						bean.setEvaluationcount5(item.getEvaluationCount());
						// 項目５
						bean.setEvaluationName5(item.getEvaluationName());
						break;
					case 6 :
						// 項目６数
						bean.setEvaluationcount6(item.getEvaluationCount());
						// 項目６
						bean.setEvaluationName6(item.getEvaluationName());
						break;
					}
				}
			}
		}
		return resultsList;
	}
	
	/**
	 * コンテンツ画面、コンテンツ評価情報、コメント情報
	 * @param PageRateDetailFormBean
	 *                明細フォーム
	 * @param eventType
	 *                イベント種類情報
	 * @return
	 *                検索結果
	 */
	public List<PageRateDetailBean> searchEvaluationDetailCondition(PageRateDetailFormBean formBean,String eventType, String orderBy) {
		// コメント情報リスト
		List<PageRateDetailBean> commentList = new ArrayList<PageRateDetailBean>();
		// 評価状況詳細画面コンテンツ情報リスト
		formBean.setDetailSearchResultsList(pageRateDao.getEvaluationDetailList(formBean.getPageId(), getLoginUserBean()));
		// 評価リスト
		formBean.setEvaluationContentsList(pageRateDao.getPageRateaResultList(formBean.getPageId()));
		// 評価詳細統計リスト(評価詳細CSV出力用)
		formBean.setEvaluationCountList(pageRateDao.getEvaluationCountList(formBean.getPageId()));
		// タイトル表示項目を取得
		setFormData(formBean);
		// コメントリスト
		if (!"init".equals(eventType)) {
			// ユーザーリスト
			List<String> list = new ArrayList<String>();
			List<String> list3 = new ArrayList<String>();
			// 個別選択の場合
			if ("0".equals(formBean.getEvaluationRadioFlag())) {
				// 評価項目--（「And条件」チェックする）
				if ("1".equals(formBean.getPluralEvaluationFlag())) {
					// 評価項目（評価１□　評価２□、、、）
					String hyoukaFlag[] = StringUtil.isEmpty(formBean.getEvaluationFlag()) ? null
							: formBean.getEvaluationFlag().split(",");
					String sequence[] = StringUtil.isEmpty(formBean.getEvaluationSequence()) ? null
							: formBean.getEvaluationSequence().split(",");
					if (hyoukaFlag != null && hyoukaFlag.length > 0) {
						for (int i = 0; i < hyoukaFlag.length; i ++) {
							if ("1".equals(hyoukaFlag[i])) {
								list.add(sequence[i]);
							}
						}
						// 抽出条件（評価）評価する
						List<PageRateDetailBean> evaluationUserIdList = pageRateDao.getEvaluationUserIdList(formBean.getPageId(), list.size());
						//画面検索条件に満たすユーザ
						List<String> list1 = new ArrayList<String>();
						//画面検索条件に満たさないユーザ
						List<String> list2 = new ArrayList<String>();
						//１と２のマージ
						for (int i = 0; i < evaluationUserIdList.size(); i ++) {
							PageRateDetailBean page = (PageRateDetailBean)evaluationUserIdList.get(i);
							String userId = page.getUserId();
							String seq = page.getSequence();
							if (list.contains(seq)) {
								list1.add(userId);
							} else {
								list2.add(userId);
							}
						}
						
						for (int i = 0; i < list1.size(); i++) {
							String user_id = (String) list1.get(i);
							if (list2.contains(user_id) || list3.contains(user_id)) {
								continue;
							} else {
								list3.add(user_id);
							}
						}
					}
					
				// 評価項目--（「And条件」チェックしない）
				} else {
					// 評価項目
					String hyoukaFlag[] = StringUtil.isEmpty(formBean.getEvaluationFlag()) ? null
							: formBean.getEvaluationFlag().split(",");
					String sequence[] = StringUtil.isEmpty(formBean.getEvaluationSequence()) ? null
							: formBean.getEvaluationSequence().split(",");
					String str = "";
					if (hyoukaFlag != null && hyoukaFlag.length > 0) {
						for (int i = 0; i < hyoukaFlag.length; i ++) {
							if ("1".equals(hyoukaFlag[i])) {
								str += " A.SEQUENCE='" + sequence[i] + "' OR ";
								list.add(sequence[i]);
							}
						}
						if (list.size() > 0) {
							if (str.lastIndexOf("OR") > -1) {
								str = str.substring(0, str.lastIndexOf("OR"));
							}
							List<String> hyoukaUserIdList = pageRateDao.getPluralNotCheckHyoukaUserIdList(formBean.getPageId(), str);
							for (int i = 0; i < hyoukaUserIdList.size(); i ++) {
								String userId = hyoukaUserIdList.get(i);
								list3.add(userId);
							}
						}
					}
				}
			}
			
			List<String> mergelist = new ArrayList<String>();
			
			// 抽出条件（評価項目）チェックの場合
			if (list.size() > 0) {
				mergelist = list3;
			//  抽出条件（評価項目）チェック以外の場合
			} else {
				mergelist = null;
			}
			
			if (mergelist == null) {
				commentList = pageRateDao.getCommentList(formBean, mergelist, orderBy);
			} else if (mergelist.size() > 0) {
				commentList = pageRateDao.getCommentList(formBean, mergelist, orderBy);
			}
		}
		formBean.setCommentList(commentList);
		// CSV出力フラグを設定する。
		setCsvOutPutFlag(formBean);
		
		return commentList;
	}
	
	/**
	 * CSVダウンロード
	 * @param response
	 * @param delegator
	 * @return
	 * @throws Exception
	 */
	public void downloadCsv(HttpServletResponse response,List<PageRateSearchResultBean> csvList) throws Exception {
		
		response.setCharacterEncoding("shift_jis");
		response.setContentType("text/plain;charset=shift-jis");
		response.setHeader("Content-Disposition", "attachment; filename=" + getCsvFileName());
		response.setHeader("Cache-Control","");
		response.setHeader("Pragma","");
		
		OutputStream os = response.getOutputStream();
		OutputStreamWriter w = new OutputStreamWriter(os); 
		BufferedWriter writer = new BufferedWriter(w); 
		try {
			//CSVヘッダ
			writer.write("タイトル,");
			writer.write("公開開始日,");
			writer.write("公開終了日,");
			writer.write("閲覧者数,");
			writer.write("項目1,");
			writer.write("項目1数,");
			writer.write("項目2,");
			writer.write("項目2数,");
			writer.write("項目3,");
			writer.write("項目3数,");
			writer.write("項目4,");
			writer.write("項目4数,");
			writer.write("項目5,");
			writer.write("項目5数,");
			writer.write("項目6,");
			writer.write("項目6数,");
			writer.write("コメント");
			writer.write("\r\n");																		//改行
			
			PageRateSearchResultBean row = new PageRateSearchResultBean();
			if (csvList != null && csvList.size() != 0) {
				for (int i = 0; i < csvList.size(); i++) {
					row = (PageRateSearchResultBean)csvList.get(i);
					writer.write(row.getTitle() + ",");									//タイトル
					writer.write(StringUtil.nullToBlank(row.getStartDate()) + ",");		//公開開始日
					writer.write(StringUtil.nullToBlank(row.getEndDate()) + ",");		//公開終了日
					writer.write(row.getReaderCount() + ",");							//閲覧者数
					writer.write((StringUtil.isEmpty(row.getEvaluationName1()) ? "-" : row.getEvaluationName1()) + ","); 	// 項目1
					writer.write((StringUtil.isEmpty(row.getEvaluationcount1()) ? "-" : row.getEvaluationcount1()) + ",");	//項目1数
					writer.write((StringUtil.isEmpty(row.getEvaluationName2()) ? "-" : row.getEvaluationName2()) + ","); 	// 項目2
					writer.write((StringUtil.isEmpty(row.getEvaluationcount2()) ? "-" : row.getEvaluationcount2()) + ",");	//項目2数
					writer.write((StringUtil.isEmpty(row.getEvaluationName3()) ? "-" : row.getEvaluationName3()) + ","); 	// 項目3
					writer.write((StringUtil.isEmpty(row.getEvaluationcount3()) ? "-" : row.getEvaluationcount3()) + ",");	//項目3数
					writer.write((StringUtil.isEmpty(row.getEvaluationName4()) ? "-" : row.getEvaluationName4()) + ","); 	// 項目4
					writer.write((StringUtil.isEmpty(row.getEvaluationcount4()) ? "-" : row.getEvaluationcount4()) + ",");	//項目4数
					writer.write((StringUtil.isEmpty(row.getEvaluationName5()) ? "-" : row.getEvaluationName5()) + ","); 	// 項目5
					writer.write((StringUtil.isEmpty(row.getEvaluationcount5()) ? "-" : row.getEvaluationcount5()) + ",");	//項目5数
					writer.write((StringUtil.isEmpty(row.getEvaluationName6()) ? "-" : row.getEvaluationName6()) + ","); 	// 項目6
					writer.write((StringUtil.isEmpty(row.getEvaluationcount6()) ? "-" : row.getEvaluationcount6()) + ",");	//項目6数
					writer.write(row.getCommentCount());								//コメント
					writer.write("\r\n");												//改行
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			writer.close(); 
			w.close(); 
			// 出力処理
			os.flush();
			os.close();
		}
	}
	
	/**
	 * CSVダウンロード
	 * @param response
	 * @param delegator
	 * @return
	 * @throws Exception
	 */
	public void downloadCsvDetail( HttpServletResponse response,PageRateDetailFormBean contentForm, String csvKb) throws Exception {
		
		response.setCharacterEncoding("shift_jis");
		response.setContentType("text/plain;charset=shift-jis");
		response.setHeader("Content-Disposition", "attachment; filename=" + getCsvFileName());
		response.setHeader("Cache-Control","");
		response.setHeader("Pragma","");
		
		OutputStream os = response.getOutputStream();
		OutputStreamWriter w = new OutputStreamWriter(os); 
		BufferedWriter writer = new BufferedWriter(w); 
		try {
			// 「評価詳細CSV出力」を押下
			if ("evaluationDetail".equals(csvKb)) {
				// CSVヘッダ
				writer.write("評価日時,");
				
				// 評価者氏名表示チェックする場合、ユーザーID、氏名、所属ID、所属名、出力する
				if ("1".equals(contentForm.getEvaluatorDisplayFlag())) {
					writer.write("ユーザーID,");
					writer.write("氏名,");
					writer.write("所属ID,");
					writer.write("所属名,");
				}
				writer.write("評価");
				writer.write("\r\n");		// 改行
	
				// 評価詳細統計リスト(評価詳細CSV出力用)
				for(PageRateDetailBean row :contentForm.getEvaluationCountList()){
					writer.write(StringUtil.formatTheDate(row.getCreateDate(), "yyyy/MM/dd HH:mm:ss") + ",");				// 評価日時
					// 評価者氏名表示チェックする場合、ユーザーID、氏名、所属ID、所属名、出力する
					if ("1".equals(contentForm.getEvaluatorDisplayFlag())) {
						writer.write(row.getUserId() + ",");				// ユーザーID
						writer.write(row.getUserName() + ",");				// 氏名
						writer.write(row.getBelong() + ",");				// 所属ID
						writer.write(row.getBelongName() + ",");			// 所属名
					}
					writer.write(row.getEvaluationName());					// 評価
					writer.write("\r\n");									// 改行
				}
			// flag = comment場合、「コメントCSV出力」を押下
			} else if ("comment".equals(csvKb)) {
				// CSVヘッダ
				writer.write("No,");
				writer.write("投稿日時,");
				
				// 評価者氏名表示チェックする場合、ユーザーID、氏名、所属ID、所属名、出力する
				if ("1".equals(contentForm.getEvaluatorDisplayFlag())) {
					writer.write("ユーザーID,");
					writer.write("氏名,");
					writer.write("所属ID,");
					writer.write("所属名,");
				}
				writer.write("コメント");
				writer.write("\r\n");		// 改行
				// コメント情報リスト
				for(PageRateDetailBean row : contentForm.getCommentList()){
					writer.write(row.getCommentOrderBy() + ",");						// No
					writer.write(StringUtil.formatTheDate(row.getCommentDateCsv(), "yyyy/MM/dd HH:mm:ss") + ",");	// 投稿日時
					// 評価者氏名表示チェックする場合、ユーザーID、氏名、所属ID、所属名、出力する
					if ("1".equals(contentForm.getEvaluatorDisplayFlag())) {
						writer.write(row.getUserId() + ",");							// ユーザーID
						writer.write(row.getUserName() + ",");							// 氏名
						writer.write(row.getBelong() + ",");							// 所属ID
						writer.write(row.getBelongName() + ",");						// 所属名
					}
					writer.write(row.getCommentInfoCsv());								// コメント
					writer.write("\r\n");												// 改行
				}
			}
		} catch(Exception e) {
			throw e;
		} finally{
			writer.close(); 
			w.close(); 
			// 送信処理
			os.flush();
			os.close();
		}
	} 
	
	/**
	 * CSV名前
	 * 
	 * @return
	 */
	private String getCsvFileName() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return getLoginUserBean().getUserId() + df.format(new Date()) + ".csv";
	}
	
	/**
	 * タイトル表示項目を取得
	 * 
	 */
	private void setFormData(PageRateDetailFormBean formBean) {
		// 評価状況詳細画面コンテンツ情報リスト
		List<PageRateSearchResultBean> list = formBean.getDetailSearchResultsList();
		if (list != null && list.size() > 0) {
			PageRateSearchResultBean resultBean = list.get(0);
			// 評価者氏名表示フラグ
			formBean.setEvaluatorDisplayFlag(resultBean.getEvaluatorDisplayFlag());
			// タイトル
			formBean.setTitle(resultBean.getTitle());
			// コンテンツ閲覧者数
			formBean.setReaderCount(resultBean.getReaderCount());
			// コメント数
			formBean.setCommentCount(resultBean.getCommentCount());
		}
	}
	
	/**
	 * CSV出力フラグを設定する。
	 */
	private void setCsvOutPutFlag(PageRateDetailFormBean formBean) {
		// コメントCSV出力フラグ
		String commentCsvFlag = "0";
		// 評価CSV出力フラグ
		String evaluationCsvFlag = "0";
		// コメントリストがあります場合、出力可
		if(formBean.getCommentList() != null && formBean.getCommentList().size() > 0) {
			commentCsvFlag = "1";
		}
		// 評価詳細統計リスト取得の件数があります場合、出力可
		for(PageRateDetailBean bean :formBean.getEvaluationContentsList()) {
			if(!"0".equals(bean.getEvaluationCount())) {
				evaluationCsvFlag = "1";
				break;
			}
		}
		// コメントCSV出力フラグ
		formBean.setCommentCsvFlag(commentCsvFlag);
		// 評価CSV出力フラグ
		formBean.setEvaluationCsvFlag(evaluationCsvFlag);
	}
}