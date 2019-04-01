/**
 * File Name    : SendPersonalMail.java
 * Created Date : 2016/01/21
 * COPYRIGHT(c) : NTS
 * SendPersonalMail.javaからコピー新規する（画面とバッチ用ファイル）
 */
package jp.co.fourseeds.fsnet.common.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import jp.co.common.frame.util.MailUtil;
import jp.co.fourseeds.fsnet.beans.MailBean;
import jp.co.fourseeds.fsnet.beans.personalMail.PersonalMailBean;


public class SendPersonalMail {
	/**
	 * メール送信
	 * @param mailInfo,div,user
	 * @throws Exception
	 * @function sendPersonalMail
	 */
	public void sendPersonalMail(PersonalMailBean personalMailBean, String user) throws Exception {
		try {
			MailBean mailInfo = new MailBean();
			// メールテープを設定
			mailInfo.setMailType(MailBean.MAIL_TYPE_HTML);
			// メール送信者、宛先、CCを設定
			setAddress(personalMailBean, mailInfo);
			// メール件名を設定
			mailInfo.setSubject(personalMailBean.getMailSubject());
			// メール内容を設定
			mailInfo.setContent(personalMailBean.getMailContent());

			// CSV添付フラグ「1」の場合、メール添付ファイルを追加
			if ("1".equals(personalMailBean.getAttachFlag())) {
				// CSV出力
				createCSVFile(personalMailBean);
				// 添付ファイル
				addMailAttachment(personalMailBean, mailInfo);
			}

			// メール送信する
			MailUtil mail = new MailUtil();
			mail.sendMail(mailInfo, user, "");

			// CSV添付フラグ「1」の場合、送信完了後でCSVファイルを削除する
			if ("1".equals(personalMailBean.getAttachFlag())) {
				// ファイル削除
				deleteFile(personalMailBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * メール送信者、宛先、CC
	 * 
	 * @param div
	 * @param personalMailBean
	 * @param mailInfo
	 * @throws Exception
	 */
	private void setAddress(PersonalMailBean bean, MailBean mailInfo) throws Exception {
		// 送信者
		mailInfo.setFrom(bean.getFromMail());
		// 宛先
		List toMailList = bean.getToMailList();
		if (toMailList != null && toMailList.size() > 0) {
			String toList[] = new String[toMailList.size()];
			for (int i = 0; i < toMailList.size(); i++) {
				toList[i] = (String) toMailList.get(i);
			}
			mailInfo.setTo(toList);
		}

		// CC
		List ccMailList = bean.getCcMailList();
		if (ccMailList != null && ccMailList.size() > 0) {
			String ccList[] = new String[ccMailList.size()];
			for (int i = 0; i < ccMailList.size(); i++) {
				ccList[i] = (String) ccMailList.get(i);
			}
			mailInfo.setCc(ccList);
		}
	}
	
	/**
	 * CSVファイル内容を書く
	 * 
	 * @param exportData
	 * @param bean
	 * @return
	 */
	public void createCSVFile(PersonalMailBean bean) {
		File csvFile = null;
		BufferedWriter csvFileOutputStream = null;
		try {
			// ファイルのパスとファイル名前存在、CSV創建
			if (bean.getFilePath() != null && !"".equals(bean.getFilePath()) && bean.getFileName() != null
					&& !"".equals(bean.getFileName())) {
				csvFile = new File(bean.getFilePath() + bean.getFileName() + ".csv");
				// csvFile.getParentFile().mkdir();
				File parent = csvFile.getParentFile();
				if (parent != null && !parent.exists()) {
					parent.mkdirs();
				}

				// CSV創建
				csvFile.createNewFile();

				// utf-8
				csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "UTF8"));
				// リストを取る、CSVファイル内容を書く
				List list = bean.getCsvInfoList();
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						String content = (String) list.get(i);
						csvFileOutputStream.write(content);
						if (i != list.size() - 1) {
							csvFileOutputStream.write("\r\n");
						}
					}
				}
				csvFileOutputStream.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				csvFileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 添付ファイル
	 * 
	 * @param personalMailBean
	 * @param mailInfo
	 * @throws Exception
	 */
	private void addMailAttachment(PersonalMailBean bean, MailBean mailInfo) throws Exception {
		// ファイルのパスとファイル名前存在、CSV創建
		if (bean.getFilePath() != null && !"".equals(bean.getFilePath()) && bean.getFileName() != null
				&& !"".equals(bean.getFileName())) {
			String fileName = bean.getFileName() + ".csv";
			String filePath = bean.getFilePath() + fileName;
			// 添付ファイル
			FileDataSource fd = new FileDataSource(filePath);
			DataHandler dAttach = new DataHandler(fd);
			mailInfo.setDAttach(dAttach);
			mailInfo.setFileName(fileName);
			// 添付ファイルフラグ設定
			mailInfo.setAttachFlag(bean.getAttachFlag());
		}
	}
	
	/**
	 * ファイル削除
	 * 
	 * @param bean
	 * @throws Exception
	 */
	private void deleteFile(PersonalMailBean bean) throws Exception {
		String filePath = bean.getFilePath();
		String fileName = bean.getFileName();
		if (filePath != null && !"".equals(filePath) && fileName != null && !"".equals(fileName)) {
			File csvFile = new File(filePath + fileName + ".csv");
			if (csvFile.exists()) {
				csvFile.delete();
			}
		}
	}
	
	/**
	 * TRIM
	 * 
	 * @param string
	 * @return
	 */
	public static String conversionNullToString(String string) {
		if (string == null || "null".equals(string)) {
			string = "";
		}
		return string.trim();
	}
}
