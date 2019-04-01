package jp.co.common.frame.util;

import java.util.ResourceBundle;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import jp.co.common.frame.util.prop.FsPropertyUtil;

import jp.co.fourseeds.fsnet.beans.MailBean;

/**
 * 
 * 基盤メール送信処理クラス<br>
 * 
 * 以下の機能をサポートする。<br>
 * 
 * <ul>
 *  <li>メール送信</li>
 * </ul>
 * 
 * @author  NTS
 * @version 1.0
 */
public class MailUtil {
	/**
	 * メール送信
	 * @throws DataBaseException
	 * @function send mail
	 */
	public void sendMail(MailBean mailInfo, String user, String kinowu) throws Exception {
		try {
			String host = FsPropertyUtil.getStringProperty("mail.server.ip");
			String userName = FsPropertyUtil.getStringProperty("mail.user.name");
			String password = FsPropertyUtil.getStringProperty("mail.user.password");
			Properties props = System.getProperties();

			// メールサーバー
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			// セッション
			Session session = Session.getInstance(props, null);

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mailInfo.getFrom()));
			if (mailInfo.getSubject() != null) {
				String subject = mailInfo.getSubject();
				if (subject != null) {

					message.setSubject(subject, "UTF-8");
				} else {
					message.setSubject("");
				}
			} else {
				message.setSubject("");
			}

			String[] to = mailInfo.getTo();
			if (to == null) {
				to = new String[0];
			}
			InternetAddress[] addrTo = new InternetAddress[to.length];
			for (int i = 0; i < addrTo.length; i++) {
				addrTo[i] = new InternetAddress(to[i]);
			}
			message.addRecipients(Message.RecipientType.TO, addrTo);

			String[] cc = mailInfo.getCc();
			if (cc != null && cc.length > 0) {
				InternetAddress[] addrCc = new InternetAddress[cc.length];
				for (int i = 0; i < addrCc.length; i++) {
					addrCc[i] = new InternetAddress(cc[i]);
				}
				message.addRecipients(Message.RecipientType.CC, addrCc);
			}

			String[] bcc = mailInfo.getBcc();
			if (bcc != null && bcc.length > 0) {
				InternetAddress[] addrBcc = new InternetAddress[bcc.length];
				for (int i = 0; i < addrBcc.length; i++) {
					addrBcc[i] = new InternetAddress(bcc[i]);
				}
				message.addRecipients(Message.RecipientType.BCC, addrBcc);
			}

			Multipart multipart = new MimeMultipart();
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			String content = null;
			if (mailInfo.getContent() == null) {
				content = "";
			} else {
				content = mailInfo.getContent();
			}
			messageBodyPart.setContent(content, mailInfo.getMailType()
					+ ";charset=\"UTF-8\"");
			multipart.addBodyPart(messageBodyPart);
			
			// 添付ファイル
			if (mailInfo.getAttachFlag() != null && "1".equals(mailInfo.getAttachFlag())) {
				MimeBodyPart mbp = new MimeBodyPart(); 
				mbp.setDataHandler(mailInfo.getDAttach());
				mbp.setFileName(MimeUtility.encodeText(mailInfo.getFileName(), "iso-2022-jp", "B"));
				multipart.addBodyPart(mbp);
			}

			message.setContent(multipart);
			Transport transport = session.getTransport("smtp");
			transport.connect((String) props.get("mail.smtp.host"), userName,
					password);
			transport.sendMessage(message, message.getAllRecipients());
		} catch (SendFailedException me) {
			errorSendMail(me, mailInfo, user, kinowu);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * エラーメール送信
	 * @throws DataBaseException
	 * @function errorSendMail
	 */
	public void errorSendMail(SendFailedException me, MailBean mailInfo,
			String user, String kinowu) throws Exception {
		// エラーメールアドレス
		Address[] address = me.getInvalidAddresses();
		if (address == null) {
			throw new Exception("Can't send mail from service.");
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd  kk:mm:ss ");
		// システムデータ
		String systemDate = sdf.format(date);
		mailInfo = new MailBean();
		mailInfo.setMailType("text/html");
		ResourceBundle bundle = ResourceBundle.getBundle("fsnet");
		// 送信人のメールアドレス
		mailInfo.setFrom(StringBaseUtil.nullToBlank(bundle
				.getString("mail.user.address")));
		String toList[] = new String[1];
		toList[0] = StringBaseUtil.nullToBlank(bundle
				.getString("error.mail.address.to"));
		mailInfo.setTo(toList);
		try {
			// メールのタイトル
			mailInfo.setSubject(FsPropertyUtil.getStringProperty("errormail.title.context"));
			StringBuffer content = new StringBuffer();
			// メールの内容
			// メールの内容
			
			content.append(FsPropertyUtil.getStringProperty("errormail.context1") + user + 
					FsPropertyUtil.getStringProperty("errormail.context2") + 
					FsPropertyUtil.getStringProperty("errormail.context3")
							+ systemDate + FsPropertyUtil.getStringProperty("errormail.context4") 
			+ kinowu + FsPropertyUtil.getStringProperty("errormail.context5")
							+ FsPropertyUtil.getStringProperty("errormail.context6")
							+ FsPropertyUtil.getStringProperty("errormail.context7"));
				StringBuffer userIdStr = new StringBuffer();
			for (int i = 0; i < address.length; i++) {
				userIdStr.append(address[i].toString()).append("<br>");
			}
			mailInfo.setContent(content.append(userIdStr.toString()).toString());
			sendMail(mailInfo, user, kinowu);
		} catch (Exception e) {
			throw new Exception("エラーメール送信が失敗しました。", e);
		}
	}
}