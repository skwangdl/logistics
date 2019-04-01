package jp.co.common.frame.constant;

/**
 *  基盤用各種定義クラス。
 * <pre>
 *  以下の機能をサポートする。
 * </pre>
 * <ul>
 *   <li>共通 の各種定義</li>
 * </ul>
 * @author NTS
 * @version 1.0 
 */
public class BaseSystemConstant {
	/** セッションにユーザー情報を保管するためのキー **/
	public static final String USER_INFO_KEY = "USER_INFO_KEY";
	/** メッセージマッピングファイルの名前（エラーメッセージと業務メッセージを含む） **/
	public static final String MESSAGE_FILE = "message.properties";
	/** 全局環境設定ファイルの名前 **/
	public static final String ENV_FILE = "environment.properties";
	/** 全局環境設定ファイルの名前 **/
	public static final String FSNET_FILE = "fsnet.properties";
    /** 文字セット名：SHIFT_JIS */
	public static String CHARSET_NAME_SHIFT_JIS = "SHIFT_JIS";
    /** 文字セット名：ISO8859_1 */
    public static String CHARSET_NAME_ISO8859_1 = "ISO8859_1";
    /** Excelファイル拡張子XLSM */
    public static String EXTENSION_EXCEL = ".xls";
    /** Excelファイル拡張子XLSM */
    public static String EXTENSION_EXCEL_XLSM = ".xlsm";
    /** Excelファイル拡張子XLSX */
    public static String EXTENSION_EXCEL_XLSX = ".xlsx";
    /** Textファイル拡張子 */
    public static String EXTENSION_TEXT = ".txt";
	/** カンマ **/
	public static final String COMMA_SEPARATOR = ",";
	/** コロン **/
	public static final String COLON_SEPARATOR = ":";
	/** タプ **/
	public static final String TAB_SEPARATOR = "\t";
	/** システムエンコーディング */
	public static final String SYSTEM_ENCODING = "UTF-8";
}
