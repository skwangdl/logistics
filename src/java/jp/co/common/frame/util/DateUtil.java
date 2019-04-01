package jp.co.common.frame.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.time.DateFormatUtils;

import jp.co.common.frame.util.prop.EnvPropertyUtil;


/**
 *  日付用クラス
 * <pre>
 * </pre>
 * <ul>
 *   <li></li>
 * </ul>
 * @author NTS
 * @version 1.0 
 */
public class DateUtil extends DateFormatUtils {
	private static final String MONTH_FORMAT = "yyyyMM";
	private static final String DATE_FORMAT = "yyyyMMdd";
	public static final String TIME_FORMAT = "yyyyMMddHHmmss";
	private static final String SEC_FORMAT = "yyyyMMddHHmmssSSS";
	private static final String SECTIME_FORMAT = "HHmmssSSS";
	private static final Map<String, String> formatMap = new HashMap<String, String>();
	private static final Map<String, String> formatMonthMap = new HashMap<String, String>();
	
	/*-----------------------------------*/
	/*      日付フォーマットの定義                          */ 
	/*-----------------------------------*/
	static {
		formatMap.put("0", "yyyy/MM/dd");
		formatMap.put("1", "dd/MM/yyyy");
		formatMap.put("2", "yyyy-MM-dd");
		formatMap.put("3", "yyyy.MM.dd");
		formatMap.put("4", "MM/dd/yyyy");
		formatMap.put("5", "dd.MM.yyyy");
		formatMap.put("6", "dd-MM-yyyy");
		formatMonthMap.put("0", "yyyy/MM");
		formatMonthMap.put("1", "MM/yyyy");
		formatMonthMap.put("2", "yyyy-MM");
		formatMonthMap.put("3", "yyyy.MM");
		formatMonthMap.put("4", "MM/yyyy");
		formatMonthMap.put("5", "MM.yyyy");
		formatMonthMap.put("6", "MM-yyyy");
	}

    /** 元号開始日付[明治] */
    public static final int GENGO_DATE_MEIJI = 18680908;
    /** 元号開始日付[大正] */
    public static final int GENGO_DATE_TAISHO = 19120730;
    /** 元号開始日付[昭和] */
    public static final int GENGO_DATE_SHOWA = 19261225;
    /** 元号開始日付[平成] */
    public static final int GENGO_DATE_HEISEI = 19890108;

    /** 元号文字列(記号)[明治] */
    public static final String GENGO_MEIJI = "M";
    /** 元号文字列(記号)[大正] */
    public static final String GENGO_TAISHO = "T";
    /** 元号文字列(記号)[昭和] */
    public static final String GENGO_SHOWA = "S";
    /** 元号文字列(記号)[平成] */
    public static final String GENGO_HEISEI = "H";

    /** 元号文字列(漢字)[明治] */
    public static final String GENGO_MEIJI_J = "明治";
    /** 元号文字列(漢字)[大正] */
    public static final String GENGO_TAISHO_J = "大正";
    /** 元号文字列(漢字)[昭和] */
    public static final String GENGO_SHOWA_J = "昭和";
    /** 元号文字列(漢字)[平成] */
    public static final String GENGO_HEISEI_J = "平成";

    /** 年号文字列(数値)[平成] */
    public static final String GENGO_HEISEI_S = "1";
    /** 年号文字列(数値)[昭和] */
    public static final String GENGO_SHOWA_S = "2";
    /** 年号文字列(数値)[大正] */
    public static final String GENGO_TAISHO_S = "3";
    /** 年号文字列(数値)[明治] */
    public static final String GENGO_MEIJI_S = "4";
    
	/*-----------------------------------*/
	/*		現在時刻関連					 */
	/*-----------------------------------*/
	/**
	 * 現在の年月を取得する
	 * 
     * <pre>
     * 例）
     *   getNowMonth()   = 200608
     * </pre>
	 * @return 現在の年月
	 */
	public static String getNowMonth() {
		return DateFormatUtils.format(new Date(), MONTH_FORMAT);
	}

	/**
	 * 現在日付を年月日形式で取得する
	 * 
     * <pre>
     * 例）
     *   getNowDate()   = 20060815
     * </pre>
	 * @return 現在の日付
	 */
	public static String getNowDate() {
		return DateFormatUtils.format(new Date(), DATE_FORMAT);
	}
	
	/**
	 * 現在日付を指定形式で取得する
	 * 
     * <pre>
     * 例）
     *   getNowDate('yyyy/MM/dd')   = 2006/08/15
     * </pre>
	 * @return 現在の日付
	 */
	public static String getNowDate(String format) {
		return DateFormatUtils.format(new Date(), format);
	}

	/**
	 * 現在日付を年月日時分秒形式で取得する
	 * 
     * <pre>
     * 例）
     *   getNowTime()   = 20060815152502
     * </pre>
	 * @return 現在の日付時刻
	 */
	public static String getNowTime() {
		return DateFormatUtils.format(new Date(), TIME_FORMAT);
	}

	/**
	 * 現在日付を年月日時分秒ミリ秒形式で取得する
	 * 
     * <pre>
     * 例）
     *   getNowMilSec()   = 20060815152502099
     * </pre>
	 * @return 現在の日付時刻
	 */
	public static String getNowMilSec() {
		return DateFormatUtils.format(new Date(), SEC_FORMAT);
	}

	/**
	 * 現在日付を時分秒ミリ秒形式で取得する
	 * 
     * <pre>
     * 例）
     *   getNowTimeMilSec()   = 152502099
     * </pre>
	 * @return 現在の日付時刻
	 */
	public static String getNowTimeMilSec() {
		return DateFormatUtils.format(new Date(), SECTIME_FORMAT);
	}

	/**
	 * 指定した時間を加算した年月を取得する
	 * 
     * <pre>
     * 例）今は2006年8月だと
     *   getLocalMonth(1)   = 200609
     *   getLocalMonth(-1)   = 200607
     * </pre>
	 * @param difference 月間差分、マイナスの場合減算する
	 * @return 年月
	 */
	public static String getLocalMonth(int difference) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, difference);
		return DateFormatUtils.format(cal.getTime(), MONTH_FORMAT);
	}

	/**
	 * 指定した時間を加算した日付を年月日形式で取得する
	 * 
     * <pre>
     * 例）今は2006年8月15日だと
     *   getLocalDate(2)   = 20060817
     *   getLocalDate(-2)  = 20060813
     * </pre>
	 * @param difference 日間差分、マイナスの場合減算する
	 * @return 日付
	 */
	public static String getLocalDate(int difference) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, difference);
		return DateFormatUtils.format(cal.getTime(), DATE_FORMAT);
	}

	/**
	 * 指定した時間を加算した日付を年月日時分秒形式で取得する
	 * 
     * <pre>
     * 例）今は2006年8月15日15時だと
     *   getLocalTime(10)   = 20060816012502
     *   getLocalTime(-10)  = 20060815052502
     * </pre>
	 * @param difference 時間差分(24時間形式)、マイナスの場合減算する
	 * @return 日付時刻
	 */
	public static String getLocalTime(int difference) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR_OF_DAY, difference);
		return DateFormatUtils.format(cal.getTime(), TIME_FORMAT);
	}

	/**
	 * 指定した時間を加算したDateオブジェクトを取得する
	 * 
     * <pre>
     * 例）今はWes Aug 15 15:31:07 JST 2006 だと
     *   getLocalDateObj(1)   = Wed Aug 16 15:31:07 JST 2006
     *   getLocalDateObj(-1)  = Mon Aug 14 15:31:07 JST 2006
     * </pre>
	 * @param difference 時間差分、マイナスの場合減算する
	 * @return Dateオブジェクト
	 */
	public static Date getLocalDateObj(int difference) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, difference);
		return cal.getTime();
	}

	/**
	 * 指定された日付オブジェクトから、該当する日付を年月日時分秒ミリ秒形式で取得する
	 * 
     * <pre>
     * 例）
     *   getDateMilSec(new Date())   = 20060815153107487
     * </pre>
	 * @param date Dateオブジェクト
	 * @return 日付時刻
	 */
	public static String getDateMilSec(Date date) {
		return DateFormatUtils.format(date,SEC_FORMAT);
	}
	
	/**
	 * YYYYMMDDフォーマットの日付を指定したロケールの標準フォーマットに変換するメソッド
	 * 
     * <pre>
     * 例）
     *   getDateString("20060815")   = 2006/08/15
     * </pre>
	 * @param date YYYYMMDDフォーマットの日付
	 * @param localeString ロケールを表す文字列
	 * @return YYYYMMDDフォーマットの日付をロケール標準フォーマットに変換した値
	 */
	public static String getDateString(String date) {
		return getDateString(date, "yyyy/MM/dd");
	}
	
	/**
	 * YYYYMMDDフォーマットの日付を指定したロケール、指定したパターンのフォーマットに変換するメソッド
	 * 
     * <pre>
     * 例）
     *   getDateString("20060815", "EEEE-MMMM-dd-yyyy-HH-mm-ss")	= 火曜日-8月-15-2006-15-34-02
     *   getDateString("20060815", "yyyy/MM/dd HH:mm:ss")	= 2006/08/15 15:34:02
     *   getDateString("20060815", "yyyy-MM-dd")			= 2006-08-15
     * </pre>
	 * @param date YYYYMMDDフォーマットの日付
	 * @param localeString ロケールを表す文字列
	 * @param pattern SimpleDateFormatと互換性のあるパターン
	 * @return YYYYMMDDフォーマットの日付をロケールフォーマットに変換した値
	 */
	public static String getDateString(String date, String pattern) {
	    date = StringBaseUtil.allBlankTrim(date);
	    if (date == null || date.length() != 8) {
	        return "";
	    }
		//カレンダーオブジェクトを生成
		Calendar cal = createCalender(date, Locale.getDefault());
		//フォーマットの変換
		return DateFormatUtils.format(cal.getTime(), pattern);
	}
	
	/**
	 * Calendar Objectを生成する。
	 * 
	 * @param date YYYYMMDDフォーマットの日付
	 * @param locale ロケール
	 * @return Calendar Object
	 */
	public static Calendar createCalender(String date, Locale locale) {
		//西暦、月、日に分割
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6)) - 1;
		int day = Integer.parseInt(date.substring(6, 8));
		//カレンダーオブジェクトを生成
		Calendar cal = Calendar.getInstance(locale);
		cal.set(year, month, day);
		return cal;
	}
	
	/**
	 * ロケールJAPANでCalendar Objectを生成する
	 * 
	 * @param date YYYYMMDDフォーマットの日付
	 * @return Calendar Object
	 */
	public static Calendar createCalender(String date) {
		return createCalender(date, Locale.getDefault());
	}
	
	/**
	 * 年月を指定したフォーマットで変換するメソッド
	 * 
     * <pre>
     * 例）
     *   formatMonth("200608", "MMMM-yyyy")	= 8月-2006
     * </pre>
     * @see フォーマットはgetFormatString()を参照
	 * @param dateStr YYYYMMフォーマットの年月
	 * @param formatStr フォーマット定義文字列
	 * @return フォーマット変換後年月
	 */
	public static String formatMonth(String dateStr, String formatStr) {
        int idate = NumberUtil.toInt(dateStr);
        if (String.valueOf(idate).length() != 6) {
	        return "";
	    }
	    String newDateStr = null;
		//日付の取得
		int year = Integer.parseInt(dateStr.substring(0, 4));
		int month = Integer.parseInt(dateStr.substring(4, 6));
		//カレンダーオブジェクトの生成
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, 0);
		//フォーマット
		newDateStr = DateFormatUtils.format(cal.getTime(), formatStr);
		return newDateStr;
	}
	
	/**
	 * 日付文字列を指定したフォーマットで変換するメソッド
	 * 
     * <pre>
     * 例）
     *   formatDate("20060815", "MMMM-dd-yyyy")	= 8月-15-2006
     * </pre>
	 * @param dateStr 日付文字列
	 * @param formatStr フォーマット定義文字列
	 * @return フォーマット変換後日付文字列
	 */
	public static String formatDate(String dateStr, String formatStr) {
        if (StringBaseUtil.isEmpty(dateStr)) {
	        return "";
	    }
		String newDateStr = null;
		//日付の取得

		int year = Integer.parseInt(dateStr.substring(0, 4));
		int month = Integer.parseInt(dateStr.substring(4, 6)) -1;
		int day = Integer.parseInt(dateStr.substring(6, 8));
		//カレンダーオブジェクトの生成
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		//フォーマット
		newDateStr = DateFormatUtils.format(cal.getTime(), formatStr);
		return newDateStr;
	}
	
	/**
	 * 日付文字列のタイプをチェックする
	 * 
     * <pre>
     * 例）
     *   checkDateType("2008/1/1")	= false
     *   checkDateType("2008/01/01") = true
     * </pre>
	 * @param dateStr 日付文字列
	 * @return boolean
	 */
	public static boolean checkDateType(String dateStr) {
		String regex = "[0-9]{4}/[0-9]{2}/[0-9]{2}";
		boolean flag = Pattern.matches(regex, dateStr);
		if (flag) {

			try {
				String[] dateArray = dateStr.split("/");
				int i = Integer.parseInt(dateArray[0]);
				int k = Integer.parseInt(dateArray[1]) - 1;
				int i1 = Integer.parseInt(dateArray[2]);
				GregorianCalendar gregoriancalendar = new GregorianCalendar();
				gregoriancalendar.setLenient(false);
				gregoriancalendar.set(i, k, i1);
				return true;
			} catch (IllegalArgumentException illegalargumentexception) {
				return false;
			} catch (Exception exception) {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * 日付文字列を指定したフォーマットで変換するメソッド
	 * 
	 * <pre>
	 * 例）
	 *   formatDateToSec(&quot;20060815123456&quot;, &quot;MMMM-dd-yyyy-HH-mm-ss&quot;)	= 8月-15-2006-12-34-56
	 * </pre>
	 * 
	 * @param dateStr
	 *            YYYYMMDDHHMMSSフォーマットの日付文字列
	 * @param formatStr
	 *            フォーマット定義文字列
	 * @return フォーマット変換後日付文字列
	 */
	public static String formatDateToSec(String dateStr, String formatStr) {
		String newDateStr = null;
		// 日付の取得
		int year = Integer.parseInt(dateStr.substring(0, 4));
		int month = Integer.parseInt(dateStr.substring(4, 6)) - 1;
		int day = Integer.parseInt(dateStr.substring(6, 8));
		int hour = Integer.parseInt(dateStr.substring(8, 10));
		int minute = Integer.parseInt(dateStr.substring(10, 12));
		int second = Integer.parseInt(dateStr.substring(12, 14));		
		
		//カレンダーオブジェクトの生成
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day, hour, minute, second);
		//フォーマット
		newDateStr = DateFormatUtils.format(cal.getTime(), formatStr);
		return newDateStr;
	}

	/**
	 * 日付フォーマットの文字列を取得するメソッド<br>
	 * <li>0 yyyy/MM/dd<li>
	 * <li>1 dd/MM/yyyy<li>
	 * <li>2 yyyy-MM-dd<li>
	 * <li>3 yyyy.MM.dd<li>
	 * <li>4 MM/dd/yyyy<li>
	 * <li>5 dd.MM.yyyy<li>
	 * <li>6 dd-MM-yyyy<li>
	 * 
	 * @param pattern 日付フォーマットの文字列を指定するためのパターン
	 * @return 日付フォーマットの文字列
	 */
	public static String getFormatString(String pattern) {
		return (String) formatMap.get(pattern);
	}

	/**
	 * 年月フォーマットの文字列を取得するメソッド<br>
	 * <li>0 yyyy/MM<li>
	 * <li>1 MM/yyyy<li>
	 * <li>2 yyyy-MM<li>
	 * <li>3 yyyy.MM<li>
	 * <li>4 MM/yyyy<li>
	 * <li>5 MM.yyyy<li>
	 * <li>6 MM-yyyy<li>
	 * 
	 * @param pattern 年月フォーマットの文字列を指定するためのパターン
	 * @return 年月フォーマットの文字列
	 */
	public static String getFormatMonthString(String pattern) {
		return (String) formatMonthMap.get(pattern);
	}

	/**
	 * 基準日付のnヵ月後の日付文字列を取得する
	 * 
     * <pre>
     * 例）
     *   addMonthDate("200608", 2)	= 200610
     * </pre>
	 * @param startDate YYYYMMフォーマットの基準日付
	 * @param n　基準日付にプラスする月数
	 * @return startDateのnヵ月後の日付
	 * @throws NumberFormatException
	 */
	public static String addMonthDate(String startDate, int n) {
		int year = Integer.parseInt(startDate.substring(0, 4));
		int month = Integer.parseInt(startDate.substring(4, 6));
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, 0);
		cal.add(Calendar.MONTH, n);
		return DateFormatUtils.format(cal.getTime(), "yyyyMM");
	}

    /**
	 * 指定された日付にパラメータ月数を加算して日付を返却します
     * <pre>
     * 例）
     *   addMonth("20060128", 1)	= 20060228
     *   addMonth("20060130", 1)	= 20060228
     *   addMonth("20060329", -1)	= 20060228
     * </pre>
     * @param strDate 依頼日付(YYYYMMDD)の8桁
     * @param month 月数
     * @return 指定日から、月数経過後の日付を返却する(※日数ではなく、月数によって、返す)
 	 */	
	public static String addMonth(String strDate, int n) {
		int year = Integer.parseInt(strDate.substring(0, 4));
		int month = Integer.parseInt(strDate.substring(4, 6)) -1 ;
		int day = Integer.parseInt(strDate.substring(6, 8));
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		//Ｎヶ月後の日付を取得
		cal.add(Calendar.MONTH, n);
		return DateFormatUtils.format(cal.getTime(), DATE_FORMAT); 	
	}

    /**
	 * 指定された日付にパラメータ月数を加算して日付を返却します
     * <pre>
     * 例）
     *   addMonth("200601", 1)	= 200602
     *   addMonth("200601", 1)	= 200602
     *   addMonth("200603", -1)	= 200602
     * </pre>
     * @param strDate 依頼日付(YYYYMM)の6桁
     * @param month 月数
     * @return 指定日から、月数経過後の日付を返却する(※日数ではなく、月数によって、返す)
 	 */	
	public static String addMonthYM(String strDate, int n) {
		int year = Integer.parseInt(strDate.substring(0, 4));
		int month = Integer.parseInt(strDate.substring(4, 6)) -1 ;
		int day = Integer.parseInt("01");
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		//Ｎヶ月後の日付を取得
		cal.add(Calendar.MONTH, n);
		return DateFormatUtils.format(cal.getTime(), MONTH_FORMAT); 	
	}

    /**
	 * 指定された日付にパラメータ月数を加算して日付を返却します
     * <pre>
     * 例）
     *   addDay("20060228", 1)	= 20060301
     *   addDay("20060228", -1)= 20060227
     *   addDay("20040228", 1)	=20040229
     *   addDay("20040228", 2)	=20040301
     *   addDay("20040301", -1)=20040229
     * </pre>
     * @param strDate 依頼日付(YYYYMMDD)の8桁
     * @param month 日数
     * @return 指定日から、日数経過後の日付を返却する)
 	 */	
	public static String addDay(String strDate, int n) {
		int year = Integer.parseInt(strDate.substring(0, 4));
		int month = Integer.parseInt(strDate.substring(4, 6)) -1 ;
		int day = Integer.parseInt(strDate.substring(6, 8));
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		//Ｎヶ月後の日付を取得
		cal.add(Calendar.DATE, n);
		return DateFormatUtils.format(cal.getTime(), DATE_FORMAT); 	
	}	

	/**
	 * 指定された日付にパラメータ時間数を加算して日付を返却します
     * <pre>
     * 例）
     *   addHour("20060228100000", 1) = 20060228110000
     *   addHour("20060228100000", -1) = 20060228090000
     * </pre>
     * @param strDate 依頼日付(yyyyMMddHHmmss)の14桁
     * @param n 時間数
     * @return 指定時間数から、時間数経過後の日付を返却する)
 	 */
	public static String addHour(String dateStr, int n) {
		// 日付の取得
		int year = Integer.parseInt(dateStr.substring(0, 4));
		int month = Integer.parseInt(dateStr.substring(4, 6)) - 1;
		int day = Integer.parseInt(dateStr.substring(6, 8));
		int hour = Integer.parseInt(dateStr.substring(8, 10));
		int minute = Integer.parseInt(dateStr.substring(10, 12));
		int second = Integer.parseInt(dateStr.substring(12, 14));		
		
		//カレンダーオブジェクトの生成
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day, hour, minute, second);
		//Ｎ時間後の日付を取得
		cal.add(Calendar.HOUR_OF_DAY, n);
		return DateFormatUtils.format(cal.getTime(), TIME_FORMAT); 	
	}
	
	/**
	 * 日付の期間を取得
	 * @param 日付１
	 * @param 日付２
	 * @param フォーマット
	 * @return 期間
	 */
	public static long getTimeRangeNum(String date1, String date2, String format)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date start = df.parse(date1);
		Date end = df.parse(date2);
		long l = end.getTime() - start.getTime();
		return l;
	}
	
    /**
     * 西暦→和暦変換２
     * @param   sDate 日付(YYYYMMDD)<BR>
     * @return  和暦日付(元号記号YYMMDD)<BR>
     *          エラーの場合は、nullを返却する<BR>
     * 元号番号 明治：4<BR>
     *          大正：3<BR>
     *          昭和：2<BR>
     *          平成：1<BR>
     */
    public static String chgSeirekiToWareki2( String sDate ) {
        String result = null;
        result = chgSeirekiToWareki(sDate);
        if ( result == null || "".equals(result)) {
            return "";
        }
        String gengo = result.substring(0, 1);
        String gengoKanji = "";
        // 平成
        if (GENGO_HEISEI_S.equals(gengo)) {
            gengoKanji = GENGO_HEISEI_J;
        
        // 昭和
        } else if (GENGO_SHOWA_S.equals(gengo)) {
            gengoKanji = GENGO_SHOWA_J;
            
        // 大正
        } else if (GENGO_TAISHO_S.equals(gengo)) {
            gengoKanji = GENGO_TAISHO_J;
            
        // 昭和
        } else if (GENGO_MEIJI_S.equals(gengo)) {
            gengoKanji = GENGO_MEIJI_J;
        }
        result = gengoKanji + NumberUtil.toInt(result.substring(1, 3)) + "年"
                            + NumberUtil.toInt(result.substring(3, 5)) + "月"
                            + NumberUtil.toInt(result.substring(5)) + "日";
        return result;
    }

    /**
     * 西暦→和暦変換２
     * @param   sDate 日付(YYYYMMDD)<BR>
     * @return  和暦日付(元号記号.YY/MM/DD)<BR>
     *          エラーの場合は、nullを返却する<BR>
     * 元号番号 明治：4<BR>
     *          大正：3<BR>
     *          昭和：2<BR>
     *          平成：1<BR>
     */
    public static String chgSeirekiToWareki( String sDate ) {
        int numDate = 0;
        try {
            numDate = Integer.parseInt( sDate );
        } catch(NumberFormatException e) {
            return "";
        }
        int nBase;
        String gengo;
        if (numDate < GENGO_DATE_MEIJI) {
            return "";
        } else if (numDate < GENGO_DATE_TAISHO) {   // 明治
            nBase = 1867;
            gengo = GENGO_MEIJI_S;
        } else if (numDate < GENGO_DATE_SHOWA) {    // 大正
            nBase = 1911;
            gengo = GENGO_TAISHO_S;
        } else if (numDate < GENGO_DATE_HEISEI) {   // 昭和
            nBase = 1925;
            gengo = GENGO_SHOWA_S;
        } else {                                    // 平成
            nBase = 1988;
            gengo = GENGO_HEISEI_S;
        }
        
        String sYear = String.valueOf((numDate/10000) - nBase);
        if (sYear.length() < 2) {
            sYear = '0' + sYear;
        }
        return (gengo + sYear + sDate.substring(4,6) + sDate.substring(6,8));
    }
    /**
     * 和暦元号を取得する
     * @param   sDate 日付(YYYYMMDD)<BR>
     * @return  和暦元号<BR>
     *          エラーの場合は、nullを返却する<BR>
     * 元号番号 明治：4<BR>
     *          大正：3<BR>
     *          昭和：2<BR>
     *          平成：1<BR>
     */
    public static String getJapanYear( String sDate ) {
        int numDate = 0;
        try {
            numDate = Integer.parseInt( sDate );
        } catch(NumberFormatException e) {
            return "";
        }
        int nBase;
        String gengo;
        String gengoKanji = "";
        if (numDate < GENGO_DATE_MEIJI) {
            return "";
        } else if (numDate < GENGO_DATE_TAISHO) {   // 明治
            nBase = 1867;
            gengo = GENGO_MEIJI_S;
        } else if (numDate < GENGO_DATE_SHOWA) {    // 大正
            nBase = 1911;
            gengo = GENGO_TAISHO_S;
        } else if (numDate < GENGO_DATE_HEISEI) {   // 昭和
            nBase = 1925;
            gengo = GENGO_SHOWA_S;
        } else {                                    // 平成
            nBase = 1988;
            gengo = GENGO_HEISEI_S;
        }
     // 平成
        if (GENGO_HEISEI_S.equals(gengo)) {
            gengoKanji = GENGO_HEISEI_J;
        
        // 昭和
        } else if (GENGO_SHOWA_S.equals(gengo)) {
            gengoKanji = GENGO_SHOWA_J;
            
        // 大正
        } else if (GENGO_TAISHO_S.equals(gengo)) {
            gengoKanji = GENGO_TAISHO_J;
            
        // 昭和
        } else if (GENGO_MEIJI_S.equals(gengo)) {
            gengoKanji = GENGO_MEIJI_J;
        }
        String sYear = String.valueOf((numDate/10000) - nBase);
        if (sYear.length() < 2) {
            sYear = '0' + sYear;
        }
        return gengoKanji + sYear;
    }
    /**
     * 和暦月を取得する
     * @param   sDate 日付(YYYYMMDD)<BR>
     * @return  和暦月<BR>
     *          エラーの場合は、nullを返却する<BR>
     * 元号番号 明治：4<BR>
     *          大正：3<BR>
     *          昭和：2<BR>
     *          平成：1<BR>
     */
    public static String getJapanDate( String sDate ) {
        try {
            Integer.parseInt( sDate );
        } catch(NumberFormatException e) {
            return "";
        }
        return sDate.substring(6,8);
    }
    /**
     * 和暦日を取得する
     * @param   sDate 日付(YYYYMMDD)<BR>
     * @return  和暦日<BR>
     *          エラーの場合は、nullを返却する<BR>
     * 元号番号 明治：4<BR>
     *          大正：3<BR>
     *          昭和：2<BR>
     *          平成：1<BR>
     */
    public static String getJapanMonth( String sDate ) {
        try {
            Integer.parseInt( sDate );
        } catch(NumberFormatException e) {
            return "";
        }
        return sDate.substring(4,6);
    }

    /**
     * 和暦→西暦変換２
     * @param src 日付(X.YY/MM/DD)
     * @return 西暦日付<br>
     *         エラーの場合は、nullを返却する<br>
     */
    public static String toSeireki(String src) {
        if (src==null || src.length() != 10) return "";
        String stDate = src.substring(2, 4);
        stDate += src.substring(5, 7);
        stDate += src.substring(8, 10);
        String gengo = src.substring(0,1);
        int ret = chgWarekiToSeireki(stDate, gengo);
        if (ret == -1) return "";
        return Integer.toString(ret);
    }

    /**
     * 和暦→西暦変換
     * @param src 和暦日付(元号記号YYMMDD)
     * @return 西暦日付<br>
     *         エラーの場合は、nullを返却する<br>
     */
    public static String chgWarekiToSeireki(String src) {
        if ( src == null ) { return null; }
        try {
            String gengo = src.substring(0, 1);
            String kbn = "";
            if( gengo.equals(GENGO_MEIJI_S)) {
                kbn = GENGO_MEIJI;
            } else if ( gengo.equals(GENGO_TAISHO_S)) {
                kbn = GENGO_TAISHO;
            } else if ( gengo.equals(GENGO_SHOWA_S)) {
                kbn = GENGO_SHOWA;
            } else if ( gengo.equals(GENGO_HEISEI_S)) {
                kbn = GENGO_HEISEI;
            }
            String stDate = src.substring(1, src.length());
            int iDate = chgWarekiToSeireki(stDate, kbn);
            if ( iDate == -1 ) { return null; }
            return Integer.toString(iDate);
        } catch (NumberFormatException e) {
            return "";
        } catch (IndexOutOfBoundsException ie) {
            return "";
        }
    }
    
    /**
     * 和暦→西暦変換
     * @param stDate 日付(YYMMDD)
     * @param gengo  元号(M,T,S,H)
     * @return 西暦日付<br>
     *         エラーの場合は、-1を返却する<br>
     */
    public static int chgWarekiToSeireki(int stDate, String gengo) {
        int nBase;
        int nMinLim;
        int nMaxLim;
        //西暦日付に変換
        if (GENGO_MEIJI.equals(gengo)) {
            nBase = 18670000;
            nMinLim = GENGO_DATE_MEIJI;
            nMaxLim = GENGO_DATE_TAISHO;
        } else if (GENGO_TAISHO.equals(gengo)) {
            nBase = 19110000;
            nMinLim = GENGO_DATE_TAISHO;
            nMaxLim = GENGO_DATE_SHOWA;
        } else if (GENGO_SHOWA.equals(gengo)) {
            nBase = 19250000;
            nMinLim = GENGO_DATE_SHOWA;
            nMaxLim = GENGO_DATE_HEISEI;
        } else if (GENGO_HEISEI.equals(gengo)) {
            nBase = 19880000;
            nMinLim = GENGO_DATE_HEISEI;
            nMaxLim = 99991231;
        } else {
            return -1;
        }
        stDate += nBase;
        if ((stDate < nMinLim) || (stDate >= nMaxLim)) {
            return -1;
        }
        return stDate;
    }

    /**
     * 和暦→西暦変換
     * @param stDate 日付(YYMMDD)
     * @param gengo  元号(M,T,S,H)
     * @return 西暦日付<br>
     *         エラーの場合は、-1を返却する<br>
     */
    public static int chgWarekiToSeireki(String stDate, String gengo) {
        try {
            int nDate = Integer.parseInt(stDate);
            return chgWarekiToSeireki(nDate, gengo);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
	/**
	 * 現在日付を時分秒形式で取得する
	 * 
     * <pre>
     * 例）
     *   getNowTimeMilSec()   = 15250209
     * </pre>
	 * @return 現在の日付時刻
	 */
	public static String getNowTimeSec() {
		return getNowTimeMilSec().substring(0, 6);
	}
	
	/**
	 * 日付の曜日を取得
	 * @param dateparam
	 * @return 曜日
	 */
	public static String getDayOfWeek(String date, String format){
		Calendar calendar = toCalendar(date, format);
		// 曜日を取得
		int week = calendar.get(Calendar.DAY_OF_WEEK);

		if(week == 1){
			return "日";
		} else if(week == 2){
			return "月";
		} else if(week == 3){
			return "火";
		} else if(week == 4){
			return "水";
		} else if(week == 5){
			return "木";
		} else if(week == 6){
			return "金";
		} else if(week == 7){
			return "土";
		}
		return "";
	}
	
	/**
	 * カレンダーに変換します。
	 * @param val
	 * @param format
	 * @return
	 */
	public static Calendar toCalendar(String val, String format){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Date date;
		
		try {
			date = simpleDateFormat.parse(val);
		} catch (ParseException e) {
			throw new RuntimeException("日付変換に例外が発生しました。");
		}
		
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		return now;
	}
	
	/**
	 * 現在の時間を取得します。（日付フィールド含まない）
	 * @param format
	 * @return
	 */
	public static String getNowTime(String format){
		Calendar c = Calendar.getInstance();
	    c.setTimeInMillis(new Date().getTime());
		String tiem = new SimpleDateFormat(format).format(c.getTime());
		return tiem;
	}
	
	/**
	 * 日付の期間を取得
	 * @param 日付１
	 * @param 日付２
	 * @param フォーマット
	 * @return 期間
	 */
	public static String getTimeRange(String date1, String date2, String format)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date start = df.parse(date1);
		Date end = df.parse(date2);
		long l = end.getTime() - start.getTime();
		long day = l / (24 * 60 * 60 * 1000);									// 日
		long hour = (l / (60 * 60 * 1000) - day * 24);							// 時
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);				// 分
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);	// 秒

		StringBuffer sb = new StringBuffer();
		sb.append(frontCompWithZore(hour, 2));
		sb.append(frontCompWithZore(min, 2));
		sb.append(frontCompWithZore(s, 2));
		return sb.toString();
	}
	
	/** 
	  * 文字列の前に０をセット
	  * @param sourceDate 
	  * @param formatLength 
	  * @return 前に０をセットした文字列
	  */  
	public static String frontCompWithZore(long sourceDate, int formatLength) {
		if(sourceDate < 0){
			sourceDate = -1 * sourceDate;
		}
		String newString = String.format("%0" + formatLength + "d", sourceDate);
		return newString;
	}
	
	public static String getEigyoubi() {
		
		String eigyoubi = DateUtil.getNowDate();
		
		String nowTime = DateUtil.getNowTime("HHmmss");
		
		String eigyoubiStartTime = StringBaseUtil.zeroPadding(EnvPropertyUtil.getInstance().getPropertyString("eigyoubi.hour.start"), 2) + "0000";
		
		if (NumberUtil.toLong(eigyoubiStartTime) > NumberUtil.toLong(nowTime)) {
			eigyoubi = DateUtil.addDay(eigyoubi, -1);
		}
		return eigyoubi;
		
	}
	
	/** 
	  * 二つ日付の比較
	  * @param 比較先日付 
	  * @param カレント日付の加算値 
	  * @return boolean
	  */  
	public static boolean isOverCurrentDate(Date compareDate, int shiftDays) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

		GregorianCalendar today = new GregorianCalendar();
		today.add(GregorianCalendar.DATE, shiftDays);
		Date nextDate = today.getTime();
		String pwDate = df.format(compareDate);
		String currentDate = df.format(nextDate);
		int flag = Integer.parseInt(currentDate) - Integer.parseInt(pwDate);
		if (flag > 0) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 日付の差を取得します。
	 * @param day1
	 * @param day2
	 * @return
	 */
	public static int dateDiff(String day1, String day2, String format){
		Date v1 = toCalendar(day1, format).getTime();
		Date v2 = toCalendar(day2, format).getTime();
		return (int)((v2.getTime() - v1.getTime()) / (1000 * 60 * 60 * 24));
	}
}