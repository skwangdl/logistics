package jp.co.common.frame.util.prop;

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
public class FsPropertyUtil extends BasePropertyUtil {

	/** Log4jの配置 */
	private static final Log logger = LogFactory.getLog(FsPropertyUtil.class);
	/** 実行環境設定保持オブジェクト */
	private static FsPropertyUtil instance = null;
	/** 環境情報設定ファイル名 **/
	private static final String FSNET_FILE = BaseSystemConstant.FSNET_FILE;
	
	/**
	 * コンストラクタ
	 * 他のクラスでnewできない。 
	 */
	private FsPropertyUtil() {
		init(FSNET_FILE);
  	}

	/**
	 * Environmentクラスのインスタンスを返すメソッド。
	 * このメソッドを呼び出す事により、Environmentクラスのインスタンスを構築する。
	 * 既にオブジェクトが作成されている場合は参照を返す。
	 * @param None 引数なし
	 * @return Environment 当該クラスのオブジェクト
	 */
	public synchronized static FsPropertyUtil getInstance() {
		
		if (instance == null) {
			instance = new FsPropertyUtil();
			if (logger.isDebugEnabled()) {
				logger.debug("新規オブジェクト(FsnetPropertyUtil)を作成");
			}
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("既存オブジェクト(FsnetPropertyUtil)を返す");
			}
		}
		return instance;
	}
	
	public static int getIntProperty(String key) {
		
		return getInstance().getPropertyInt(key); 
	}
	
	public static String getStringProperty(String key) {
		
		return getInstance().getPropertyString(key); 
	}
}