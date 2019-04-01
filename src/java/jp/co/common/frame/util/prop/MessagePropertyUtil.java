package jp.co.common.frame.util.prop;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jp.co.common.frame.constant.BaseSystemConstant;

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
public class MessagePropertyUtil extends BasePropertyUtil {

	/** Log4jの配置 */
	private static final Log logger = LogFactory.getLog(MessagePropertyUtil.class);
	/** 実行環境設定保持オブジェクト */
	private static MessagePropertyUtil instance = null;
	/** 環境情報設定ファイル名 **/
	private static final String MESSAGE_FILE = BaseSystemConstant.MESSAGE_FILE;
	
	/**
	 * コンストラクタ
	 * 他のクラスでnewできない。 
	 */
	private MessagePropertyUtil() {
		init(MESSAGE_FILE);
  	}

	/**
	 * Environmentクラスのインスタンスを返すメソッド。
	 * このメソッドを呼び出す事により、Environmentクラスのインスタンスを構築する。
	 * 既にオブジェクトが作成されている場合は参照を返す。
	 * @param None 引数なし
	 * @return Environment 当該クラスのオブジェクト
	 */
	public synchronized static MessagePropertyUtil getInstance() {
		
		if (instance == null) {
			instance = new MessagePropertyUtil();
			if (logger.isDebugEnabled()) {
				logger.debug("新規オブジェクト(MessagePropertyUtil)を作成");
			}
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("既存オブジェクト(MessagePropertyUtil)を返す");
			}
		} 
		return instance;
	}
	
	@SuppressWarnings("rawtypes")
	public static Set getMessagesList() {
		System.out.println("size:" + getInstance().getPropertyEntrySet().size());
		
		return getInstance().getPropertyEntrySet();
	}

	public static String getStringProperty(String key) {
		
		return getInstance().getPropertyString(key); 
	}	
	
}