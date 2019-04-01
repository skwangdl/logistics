package jp.co.common.frame.util.prop;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jp.co.common.frame.exception.LocalRuntimeException;

import jp.co.common.frame.constant.BaseMessageConstant;
import jp.co.common.frame.constant.BaseSystemConstant;

import jp.co.common.frame.util.NumberUtil;
import jp.co.common.frame.util.StringBaseUtil;

/**
 *  Propertiesクラスを使い実行環境情報設定ファイルからの読み込み(load)を行うクラス。
 * <pre>
 *  以下の機能をサポートする。
 * </pre>
 * <ul>
 *   <li>ファイル内の情報をキーを引数に取得するメソッドを提供する。</li>
 * </ul> 
 * @author NTS
 * @version 1.0 
 */
public class BasePropertyUtil {

	/** Log4jの配置 */
	private final Log logger = LogFactory.getLog(this.getClass());
	private Properties PROPERTIES = new Properties();
	
	/**
	 *コンストラクタ
	 *他のクラスでnewできない。 
	 */
	protected BasePropertyUtil() {
  	}

	/**
	 * 初期化メソッド
	 * resources.applicationを検索し、
	 * 内容をPropertiesオブジェクトにセットする。
	 * @param propertyFile 環境情報設定ファイル
	 */
	protected void init(String propertyFile) {
		InputStream in = null;
		in = this.getClass().getResourceAsStream(propertyFile);
		if (in == null) {
			in = this.getClass().getClassLoader().getResourceAsStream(propertyFile);
		}
		try {
			PROPERTIES.load(in);
		} catch (IOException e) {
			throw new LocalRuntimeException(BaseMessageConstant.ERROR004, 
					"プロパティファイルは正常にロードできません。", e);
		}
	}

	/**
	 * 実行環境情報設定ファイルの内容を文字列の方式で返す
	 * @param argKey  ファイル内の情報キー
	 * @return String ファイル内の情報文字列値
	 */
	public String getPropertyString(String argKey) {
		if (argKey == null) {
			return null;
		}
		//設定値を取得
		String attribute = PROPERTIES.getProperty(argKey);
		if (logger.isDebugEnabled()) {
			logger.debug("【Key】= " + argKey + " 【Value】="+attribute+"のプロパティが返されます。");
		}
		return attribute;
	}
	
	/**
	 * 実行環境情報設定ファイルの内容を整数の方式で返す
	 * @param argKey  ファイル内の情報キー
	 * @return String ファイル内の情報整数値
	 */
	public int getPropertyInt(String argKey) {
		String attribute = getPropertyString(argKey);
		if (NumberUtil.isNumber(attribute)) {
			return NumberUtil.toInt(attribute);	
		} else {
			throw new LocalRuntimeException(BaseMessageConstant.ERROR005, "数字への変換に失敗しました。");
		}
	}
	
	/**
	 * 実行環境情報設定ファイルの内容を文字列の方式で返す
	 */
	public String getEncodeProperty(String argKey) {
		String message = getPropertyString(argKey);
		if (!StringBaseUtil.isBlank(message)) {
			try {
				message = new String(message.getBytes(BaseSystemConstant.CHARSET_NAME_ISO8859_1),
						BaseSystemConstant.CHARSET_NAME_SHIFT_JIS);
			} catch (UnsupportedEncodingException e) {
				if (logger.isInfoEnabled()) {
					logger.info("メッセージへの変換に失敗しました。");
				}
			}
		}
		return message == null? "" : message;
	}
	
	@SuppressWarnings("rawtypes")
	public Set getPropertyEntrySet() {
		return PROPERTIES.entrySet();
	}
}
