package jp.co.fourseeds.fsnet.common;

/**
 * コンスタント定義
 * 
 * @author NTS
 * @version 1.0.0 : 2015/11/25 新規作成
 *
 **/
public class ConstantContainer {
	public static final String LOGIN_USER_KEY = "LoginUserKey";
	
	public static final String EXCEPTION_AUTHENTICATION = "Authentication Exception";
	public static final String EXCEPTION_ACTION = "Action Exception";
	public static final String EXCEPTION_SERVICE = "Service Exception";
	public static final String EXCEPTION_DATABASE = "Database Exception";
	public static final String EXCEPTION_PROPERTIES = "Properties Exception";
	public static final String EXCEPTION_GENERAL = "General Exception";

	public static final String SORT_CONFIRM_DATE_LATE = "confirmdate desc";
	public static final String SORT_START_DATE_LATE = "startdate desc";
	public static final String SORT_FIELD_SUBJECT_ASCENDING = "title asc";
	public static final String SORT_FIELD_SUBJECT_DESCENDING = "title desc";

	public static final String USER_SEARCH_LIST = "userSearchList";

	// 静的コンテンツ
	public static final String PAGE_STATIC = "0";
	// 動的コンテンツ
	public static final String PAGE_DYNAMIC = "1";
	// 画面値保持用セッション先頭
	public static final String SESSION_TRANSMIT_BEGINNING = "TRANSMIT_";
	// セッションにアクセスログ出力用ページIDを保持する
	public static final String SESSION_ACCESS_LOG_PAGE_ID = "ACCESS_LOG_PAGE_ID";
	
	/** The stream buffer size */
	public static final int ZIP_BUFFER_SIZE = 2048;
	public static final int BUFFER_SIZE = 1024;
	
	// コンテンツ状況確認検索結果の凡例
	public static final String BLUE_CONTENTS = "検索条件と一致するコンテンツ";
	public static final String GRAY_CONTENTS = "閲覧可不可確認ユーザを指定した場合該当ユーザが実配置で閲覧ができないコンテンツ";
	public static final String NOLINK_CONTENTS = "「リンク先未設定」もしくは「リンク先コンテンツなし」のリンクが存在するコンテンツ";
	public static final String FO_CONTENTS = "公開予定コンテンツ";
	public static final String NE_CONTENTS = "通常編集中コンテンツ";
	public static final String OE_CONTENTS = "公開したまま編集中コンテンツ";
	public static final String DONTLOOKIMAGE_CONTENTS = "指定ユーザ閲覧不可コンテンツ";
	public static final String DEL_CONTENTS = "公開期限切れコンテンツ";
	
	// サイト内検索：
	public static final int SITE_SEARCH_MAX = 100;
	
	public static final String MAIN_IMG_PATH = "main/";
	
	// ユーザ組織区分は本部
	public static final String USER_ORIGINAL = "0";
	
	// ユーザ組織区分は直営
	public static final String USER_DIRECT = "1";
	
	// ユーザ組織区分はＦＣ
	public static final String USER_FC = "4";
	
	// 組織区分
	public static final String ORGANIZATION_KBN = "ORGANIZATION_KBN";
	
	// 従業員区分はアルバイト 
	public static final  String PART_TIME_JOB_FLAG = "2";
	
	// 従業員区分
	public static final String USER_DIVISION_KEYCODE = "19";
	
	// 役職区分
	public static final String POSITION_KEYCODE = "233";
	
	// 区域区分
	public static final String AREA_KEYCODE = "189";
	
	// 店舗区分
	public static final String STORE_DIVISION_KEYCODE = "15";
	
	// 性別区分
	public static final String SEX_KEYCODE = "10";
	
	// ユーザグループシステム利用区分
	public static final String USER_GROUP_ROLE = "USER_GROUP_ROLE";

	// システム利用区分
	public static final String ROLE = "ROLE";
	
	// システム利用区分VALUE
	public static final String ROLE_ADMINISTRATOR = "1";
	public static final String ROLE_USER = "5";
	
	// 会社区分
	public static final String COMPANY_KEYCODE = "279";

	// 統括区分
	public static final String UNIFICATION_KEYCODE = "1";

	// 事業区分
	public static final String BUSINESS_KEYCODE = "2";

	// 営業部区分
	public static final String SALES_KEYCODE = "3";
	
	// 表示フラグ
	public static final String UDS_SHOW_FLAG = "UDS_SHOW_FLAG";
	
	// 表示フラグ：表示
	public static final String SHOW_FALG_YES = "表示";
	
	// 表示フラグ：表示しない
	public static final String SHOW_FALG_NO = "表示しない";
	
	// 全社員
	public static final String ALL_USER = "全社員";
	
	// 個人汎用区分
	public static final String PERSONAL_KEYCODE = "1";
}
