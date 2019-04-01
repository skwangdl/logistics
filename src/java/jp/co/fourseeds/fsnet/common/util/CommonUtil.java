package jp.co.fourseeds.fsnet.common.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import jp.co.common.frame.util.prop.FsPropertyUtil;

public class CommonUtil {

	/** Log4j action logger instance */
	private static final Logger logger = Logger.getLogger("fsnet.action");
	public static StringBuffer[] ngWords = {new StringBuffer(""),new StringBuffer("")};

	/**
	 * ログインユーザのグループ情報により、クエリ検索用の文字列に変換して返す
	 * 呼び出し元
	 * A)MenuとTree生成するクエリ
	 * B)お気に入りの検索クエリ
	 * 
	 */
	public static String getGroupSql(List<String> groupList) {
		StringBuffer groupBuffer = new StringBuffer();
		if (groupList == null || groupList.isEmpty()) {
			groupBuffer.append("null");
		} else {
			groupBuffer.append("'");
			groupBuffer.append(groupList.get(0));
			groupBuffer.append("'");
			for (int i = 1; i < groupList.size(); i++) {
				groupBuffer.append(",'");
				groupBuffer.append(groupList.get(i));
				groupBuffer.append("'");
			}
		}
		return groupBuffer.toString();
	}
	
	/**
	 * コンテンツのエラー変換
	 */
	public static String getErrorContent(String content) {
		String errorContent = "";

		content = " " + StringUtil.nullToBlank(content) + " ";

		String enNGWords = StringUtil.nullToBlank(ngWords[0].toString());
		errorContent = markNGWords(content, enNGWords, true);

		String jaNGWords = StringUtil.nullToBlank(ngWords[1].toString());
		errorContent = markNGWords(errorContent, jaNGWords, false);

		return errorContent.trim();
	}

	/**
	 * @created dingzw 2007/04/17
	 * @modified dingzw 2007/04/17
	 * @param content
	 * @param useNGWords
	 * @param isEnglish
	 * @return
	 * @function Mark the NG words.
	 */
	private static String markNGWords(String content, String useNGWords,
			boolean isEnglish) {
		StringBuffer errorContent = new StringBuffer(content);

		if (useNGWords != null && !useNGWords.equals("")) {
			Pattern pattern = Pattern.compile(useNGWords,
					Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(content);

			int start = 0;
			int end = 0;
			int inc = 0;
			while (matcher.find(end)) {
				start = matcher.start();
				end = matcher.end();

				if (isEnglish) {
					String temp = content.substring(start, end);
					if (temp.matches("[^a-z][a-z]*[^a-z]+"))
						start++;
					if (temp.matches("[^a-z]+[a-z]*[^a-z]"))
						end--;
				}

				errorContent.insert(start + inc, "<font color='red'>");
				inc += 18;
				errorContent.insert(end + inc, "</font>");
				inc += 7;
			}
		}

		return errorContent.toString();
	}

	public static void writeActionLog(String userId, String messgae) {
		logger.info("[USER_ID:" + userId + "] - 【機能】" + messgae);
	}

	/**
	 * (x)静的ファイルの拡張子をチェック
	 * @param pageId
	 * @param fileSuffix
	 * @return
	 */
	public static boolean checkStaticFileSuffix(String pageId, String fileSuffix) {
		String fileSuffixStr = FsPropertyUtil.getStringProperty("static.main.suffix");
		String[] fileSuffixArray = fileSuffixStr.split(",");
		for (String fileSuffixTempt : fileSuffixArray) {
			if ((pageId + "." + fileSuffixTempt).toUpperCase().equals(fileSuffix.toUpperCase())) {
				return true;
			}
		}
		return false;
	}
}
