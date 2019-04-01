package jp.co.common.frame.util;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 基盤ファイル処理クラス<br>
 * 
 * 以下の機能をサポートする。<br>
 * 
 * <ul>
 *  <li>CSV出力</li>
 * </ul>
 * 
 * @author  NTS
 * @version 1.0
 */
public class CsvUtil {
	private String textData;
	private String fileName;
	private String csvMime = "text/comma-separated-values; charset=Windows-31J";
	
	public CsvUtil(String textData, String fileName) {
		this.textData = textData;
		this.fileName = fileName;
	}
	public void sendResponse(HttpServletResponse res) throws IOException {
		BufferedWriter bw = null;
		try {
			res.setContentType(csvMime.toString());
			res.setHeader("Content-Disposition", "attachment;filename=\"".concat(new String(fileName.getBytes(), "ISO8859_1")).concat("\""));
			res.resetBuffer();
			bw = new BufferedWriter(res.getWriter());
			bw.write(textData);
			bw.flush();

		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
	}
}
