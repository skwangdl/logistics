package jp.co.common.frame.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import org.apache.commons.lang.math.NumberUtils;

/**
 *  数字用クラス
 * <pre>
 * </pre>
 * <ul>
 *   <li></li>
 * </ul>
 * @author NTS
 * @version 1.0 
 */
public class NumberUtil extends NumberUtils {

	private NumberUtil() {

	}

	/**
	 *　パラメータの数字を指定したロケールの標準フォーマットに変換するメソッド
	 *
     * <pre>
     * 例）

	 * getCurrencyString("1234567890") = "￥1,234,567,890"
     * </pre>
	 * @param currency
	 * @param localeString
	 * @return 受け取った数字をロケール標準フォーマットに変換した値
	 */
	public static String getCurrencyString(String currency) {
		String newCurrency = null;
		//ロケールに応じた通貨フォーマットを生成
		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.getDefault());
		//フォーマットの変換
		newCurrency = nf.format(Long.parseLong(currency));
		return newCurrency;
	}
	
	/**
	 * 数値文字列を指定したフォーマットで変換するメソッド
	 * 
     * <pre>
     * 例）

     * formatNumber("123456789")  		= "123,456,789"
     * </pre>
	 * @param numberStr 数値文字列
	 * @param formatStr フォーマット定義文字列
	 * @return フォーマット変換後数値文字列
	 */
	public static String formatNumber(String numberStr) {
		return formatNumber(numberStr, "###,###");
	}

	/**
	 * 数値文字列を指定したフォーマットで変換するメソッド
	 * 
     * <pre>
     * 例）

     * formatNumber("123456789", "###,###")  		= "123,456,789"
     * formatNumber("123456789.98765", "#,###.##")  = "123,456,789.99"
     * </pre>
	 * @param numberStr 数値文字列
	 * @param formatStr フォーマット定義文字列
	 * @return フォーマット変換後数値文字列
	 */
	public static String formatNumber(String numberStr, String formatStr) {
		if (numberStr == null || "".equals(numberStr)) {
			return "";
		}
		
		String newNumberStr = null;
		//フォーマット定義済みDecimalFormatオブジェクトの生成
		DecimalFormat df = new DecimalFormat(formatStr);
		//フォーマット

		newNumberStr = df.format(Double.parseDouble(numberStr));
		return newNumberStr;
	}
	/**
	 * 数値文字列をで%変換するメソッド
     * <pre>
     * 例）
     * toPer("0.05")  		= "5%"
     * </pre>
	 * @param numberStr 数値文字列
	 * @return フォーマット変換後数値文字列
	 */
	public static String toPer(String numberStr) {
		if (!isNumber(numberStr)) {
			return "";
		}
		String newNumberStr = String.valueOf(toBigDecimal(numberStr).multiply(toBigDecimal("100")).toBigInteger())+"%";
		return newNumberStr;
	}
	
	public static String toPer2(String numberStr, int scale) {
		if (!isNumber(numberStr)) {
			return "";
		}
		if("0".equals(numberStr)){
			return " ";
		}
		String newNumberStr = String.valueOf((toBigDecimal(numberStr).multiply(toBigDecimal("100"))).setScale(scale, BigDecimal.ROUND_HALF_UP))+"%";
		return newNumberStr;
	}
	
	/**
	 * 文字列をintに変換する、変換エラーの場合、0を返す
	 * 
     * <pre>
     * 例）

     *   toInt(null) = 0
     *   toInt("")   = 0
     *   toInt("1")  = 1
     * </pre>
	 * @param str
	 * @return int
	 */
	public static int toInt(String str){
		return NumberUtils.toInt(str);
	}
	/**
	 * 文字列をlongに変換する、変換エラーの場合、0を返す
	 * 
     * <pre>
     * 例）

     *   toLong(null) = 0L
     *   toLong("")   = 0L
     *   toLong("1")  = 1L
     * </pre>
	 * @param str
	 * @return long
	 */
	public static long toLong(String str){
		return NumberUtils.toLong(str);
	}
	/**
	 * 文字列をfloatに変換する、変換エラーの場合、0.0を返す
	 * 
     * <pre>
     * 例）

     *   toFloat(null)   = 0.0f
     *   toFloat("")     = 0.0f
     *   toFloat("1.5")  = 1.5f
     * </pre>
	 * @param str
	 * @return float
	 */
	public static float toFloat(String str){
		return NumberUtils.toFloat(str);
	}
	/**
	 * 文字列をdoubleに変換する、変換エラーの場合、0.0を返す
	 * 
     * <pre>
     * 例）

     *   toDouble(null)   = 0.0d
     *   toDouble("")     = 0.0d
     *   toDouble("1.5")  = 1.5d
     * </pre>
	 * @param str
	 * @return double
	 */
	public static double toDouble(String str){
		return NumberUtils.toDouble(str);
	}
	
	/**
	 * 文字列が数字の文字だけで構成されているかをチェックします

	 * 
     * <pre>
     * 例）

     *   isDigits(null)   = false
     *   isDigits("")     = false
     *   isDigits("1.5")  = false
     *   isDigits("15")   = true
     *   isDigits("abc")  = false
     * </pre>
	 * @param str
	 * @return null 、空の文字列が指定された場合には false を返す
	 */
	public static boolean isDigits(String str){
		return NumberUtils.isDigits(str);
	}
	
	/**
	 *文字列が数字の文字とJavaの数値リテラル表記だけで構成されているかをチェックする
	 * 
     * <pre>
     * 例）

     *   isNumber(null)   = false
     *   isNumber("")     = false
     *   isNumber("1.5")  = true
     *   isNumber("1-")   = false
     *   isNumber("-1")   = true
     *   isNumber("15")   = true
     *   isNumber("abc")  = false
     * </pre>
	 * @param str
	 * @return null 、空の文字列が指定された場合には false を返す
	 */
	public static boolean isNumber(String str){
		return NumberUtils.isNumber(str);
	}
	
	/**
	 * 文字列をBigDecimalに変換する、変換エラーの場合、0を返す
	 * 
     * <pre>
     * 例）

     *   toBigDecimal(null)   = 0
     *   toBigDecimal("")     = 0
     *   toBigDecimal("1.501",new BigDecimal(0))  = 1.501
     * </pre>
	 * @param str
	 * @return BigDecimal
	 */
	public static BigDecimal toBigDecimal(String str){
		return toBigDecimal(str, new BigDecimal(0));
	}
	/**
	 * 文字列をBigDecimalに変換する、変換エラーの場合、defaultValueを返す
   	 * <pre>
     * 例）

     *   toBigDecimal(null,new BigDecimal(0))   = 0
     *   toBigDecimal("",new BigDecimal(0))     = 0
     *   toBigDecimal("1.501",new BigDecimal(0))  = 1.501
     * </pre>
	 * @param str
 	 * @param defaultValue
	 * @return BigDecimal
	 */
	public static BigDecimal toBigDecimal(String str,BigDecimal defaultValue){
		try {
			return new BigDecimal(str);
		} catch (Exception nfe) {
			return defaultValue;
		}
	}
	
	/**
	 * 文字列をObjectに変換する、変換エラーの場合、0を返す
	 * 
     * <pre>
     * 例）

     *   toBigDecimal(null)   = 0
     *   toBigDecimal("")     = 0
     *   toBigDecimal("1.501",new BigDecimal(0))  = 1.501
     * </pre>
	 * @param str
	 * @return BigDecimal
	 */
	public static Object toObject(String str){
		if(StringBaseUtil.isEmpty(str)){
			return "";
		}
		return toBigDecimal(str, new BigDecimal(0));
	}
		
	/**
	 * 機能：小数のある場合、整数部分を取得する

	 * 対象：小数点つきDecimal型（金額など）

	 * <pre>
     * 例）

     *   clearDecimal(50.0031)   = 50
     *   clearDecimal("",new BigDecimal(0))     = 0
     *   clearDecimal("1.501",new BigDecimal(0))  = 1.501
     *   clearDecimal("5001") = 5001
     *   clearDecimal("5.00.1") = 5
     *   clearDecimal("5001.") = 500
     *    clearDecimal(".5001") = 
     * </pre>
	 * @param decValue
	 * @return decValue
	 */
	public static String clearDecimal(String decValue) {
		if (decValue != null && decValue.indexOf(".")>=0) {
			decValue = decValue.substring(0,decValue.indexOf("."));
		}
	    return decValue;
	}

	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.add(b2).doubleValue();
	}
	
	public static double subtract(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}
	
	public static double multiply(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.multiply(b2).doubleValue();
	}
	
	public static double multiply(double v1, double v2,int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.multiply(b2).setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static double multiply(double v1, double v2,int scale,int round) {
		  if (scale < 0) {
			  throw new IllegalArgumentException("The scale must be a positive integer or zero");
		  }
		  BigDecimal b1 = new BigDecimal(v1*v2);
		  return b1.setScale(scale,round).doubleValue();
	}
	
	public static double divide(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2).doubleValue();
	}

	public static double divide(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}

		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(v);
		return b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public final static BigDecimal ZERO = new BigDecimal("0");
	
	/**
	 * パーセント値を算出
	 * @param divisor
	 * @param numerator
	 * @return
	 */
	public static BigDecimal percent(BigDecimal divisor, BigDecimal numerator, int scale){
		if (divisor == null || numerator == null || ZERO.compareTo(numerator) == 0 ){
			return ZERO;
		}
		
		return divisor.divide(numerator, scale, BigDecimal.ROUND_HALF_UP);
	}
	
	public static void main(String args[]) {
    	try {
	    	System.out.println("###################################### NumberUtilのテスト ###########################################");
			System.out.println("getCurrencyString(\"1234567890\")			= " + getCurrencyString("1234567890"));
			System.out.println("");
	        System.out.println("formatNumber(\"123456789\", \"###.###\")			= " + formatNumber("123456789", "###,###"));
	        System.out.println("formatNumber(\"123456789.98765\", \"#,###.##\")		= " + formatNumber("123456789.98765", "#,###.##"));
	    	System.out.println("");
	    	System.out.println("toInt(null)			= " + toInt("null"));
	        System.out.println("toInt(\"\")			= " + toInt(""));
	        System.out.println("toInt(\"1\")		= " + toInt("1"));
	    	System.out.println("");
	        System.out.println("toLong(null)		= " + toLong("null"));
	        System.out.println("toLong(\"\")		= " + toLong(""));
	        System.out.println("toLong(\"1\")		= " + toLong("1"));
	    	System.out.println("");
	        System.out.println("toFloat(null)		= " + toFloat("null"));
	        System.out.println("toFloat(\"\")		= " + toFloat(""));
	        System.out.println("toFloat(\"1.5\")		= " + toFloat("1.5"));
	    	System.out.println("");
	        System.out.println("toDouble(null)		= " + toDouble("null"));
	        System.out.println("toDouble(\"\")		= " + toDouble(""));
	        System.out.println("toDouble(\"1.5\")	= " + toDouble("1.5"));
	    	System.out.println("");
	        System.out.println("isDigits(null)		= " + isDigits("null"));
	        System.out.println("isDigits(\"\")		= " + isDigits(""));
	        System.out.println("isDigits(\"1.5\")	= " + isDigits("1.5"));
	        System.out.println("isDigits(\"15\")		= " + isDigits("15"));
	        System.out.println("isDigits(\"abc\")	= " + isDigits("abc"));
	    	System.out.println("");
	        System.out.println("isNumber(null)		= " + isNumber("null"));
	        System.out.println("isNumber(\"\")		= " + isNumber(""));
	        System.out.println("isNumber(\"1.5\")	= " + isNumber("1.5"));
	        System.out.println("isNumber(\"1-\")	= " + isNumber("1-"));
	        System.out.println("isNumber(\"-1\")	= " + isNumber("-1"));
	        System.out.println("isNumber(\"15\")	= " + isNumber("15"));
	        System.out.println("isNumber(\"abc\")	= " + isNumber("abc"));
	    	System.out.println("");
	    	System.out.println("toBigDecimal(null)			= " + toBigDecimal(null));
	        System.out.println("toBigDecimal(\"\")			= " + toBigDecimal(""));
	        System.out.println("toBigDecimal(\"1.501\")		= " + toBigDecimal("1.501"));
	        System.out.println("clearDecimal(\"50.0031\")		= " + clearDecimal("50.0031"));
	        System.out.println("clearDecimal(\"5001\")		= " + clearDecimal("5001"));
	        System.out.println("clearDecimal(\"5001.\")		= " + clearDecimal("5001."));
	        System.out.println("toPer(\"0.05\")	= " + toPer("0.05"));
	        System.out.println("toPer(\"0.08\")	= " + toPer("0.08"));
	        System.out.println("toPer(\"0.10\")	= " + toPer("0.10"));
	        System.out.println("toPer(\"\")	= " + toPer(""));
	    	System.out.println("###################################### NumberUtilのテスト ###########################################");
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}