package jp.co.common.frame.util;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

/**
 *  文字列編集用クラス
 * <pre>
 * </pre>
 * <ul>
 *   <li></li>
 * </ul>
 * @author NTS
 * @version 1.0 
 */
public class StringBaseUtil extends StringUtils {

    /**
     * ヌル値をセロに変更する
     * 
     * <pre>
     * 例）
     * StringUtil.nullToBlank(null)  = ""
     * StringUtil.nullToBlank("")  = ""
     * StringUtil.nullToBlank(" ")  = ""
     * </pre>
     * 
     * 対象：CHAR型テキストボックス
     * @param strValue
     * @return strValue
     */     
    public static String nullToBlank(String strValue) {
       if (StringBaseUtil.isEmpty(strValue)) {
            strValue  = "";
       }else {
            strValue  = strValue.trim();
       }
       return strValue;
   }

    /**
     * 検査対象文字列がNULLまたは””かどうかチェックする
     * 
     * <pre>
     * 例）
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     * </pre>
     * @param obj　チェック対象
     * @return ブランク場合true、ブランクではない場合false
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 検査対象文字列がブランクかどうかチェックする
     * 
     * <pre>
     * 例）
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     * @param obj　チェック対象
     * @return ブランク場合true、ブランクではない場合false
     */
     public static boolean isBlank(Object obj){
          if(obj == null){
               return true;
          }
          return isBlank(obj.toString());
     }
     
     
    /**
     * 検査対象文字列が指定桁以内かどうかチェックする
     * (2byteを考慮しない)
     * 
     * <pre>
     * 例）
     * StringUtils.isUnderLength("bob", 1)      = false
     * StringUtils.isUnderLength("bob", 3)      = true
     * StringUtils.isUnderLength("bob", 5)      = true
     * </pre>
     * @param input
     * @param length 指定桁

     * @return 桁以内の場合true、指定桁以上の場合false
     */
    public static boolean isUnderLength(String input, int length) {
        if (input.length() <= length)
            return true;
        else
            return false;
    }

    /**
     * 検査対象文字列が指定桁以内かどうかチェックする
     * (2byteを考慮)
     * 
     * <pre>
     * 例）
     * StringUtils.isUnderLengthMulti("テスト", 5)      = false
     * StringUtils.isUnderLengthMulti("テスト", 6)      = true
     * StringUtils.isUnderLengthMulti("テスト", 10)      = true
     * </pre>
     * @param input
     * @param length 指定桁

     * @return 桁以内の場合true、指定桁以上の場合false
     */
    public static boolean isUnderLengthMulti(String input, int length) {
        byte[] bytes = input.getBytes();
        if (bytes.length <= length)
            return true;
        else
            return false;
    }
     
     /**
     * 文字列の後方ブランクを除去する
     * 
     * <pre>
     * 例）
     * StringUtils.rtrim("")          = ""
     * StringUtils.rtrim("bob ")     = "bob"
     * StringUtils.rtrim(" bob")    = " bob"
     * </pre>
     * @param input 入力文字列
     * @return 後方ブランクが除去された文字列
     */
    public static String rtrim(String input) {
        if (input == null || input.equals(EMPTY)){
            return EMPTY;
        }
        int length = input.length();
        while ((0 < length) && input.charAt(length - 1) == ' ') {
            length--;
        }
        return (length < input.length()) ? input.substring(0, length) : input;
    }
    
    /**
     * 文字列の後方全角ブランクを除去する
     * 
     * <pre>
     * 例）
     * StringUtils.rtrim("　")          = ""
     * StringUtils.rtrim("bob　 ")     = "bob"
     * StringUtils.rtrim(" bob")    = " bob"
     * </pre>
     * @param input 入力文字列
     * @return 後方ブランクが除去された文字列
     */
    public static String rlettertrim(String input) {
        if (input == null || input.equals(EMPTY)){
            return EMPTY;
        }
        int length = input.length();
        while ((0 < length) &&( "　".equals(String.valueOf(input.charAt(length - 1))) ||( " ".equals(String.valueOf(input.charAt(length - 1)))))) {
            length--;
        }
        return (length < input.length()) ? input.substring(0, length) : input;
    }
    /**
     * 文字列の前方ブランクを除去する
     * 
     * <pre>
     * 例）
     * StringUtils.ltrim("")          = ""
     * StringUtils.ltrim("bob ")     = "bob "
     * StringUtils.ltrim(" bob")    = "bob"
     * </pre>
     * @param input 入力文字列
     * @return 前方ブランクが除去された文字列
     */
    public static String ltrim(String input){
         return ltrimer(' ', input);
    }
    
    /**
     * 文字列の前方にある対象文字を除去する
     * 
     * <pre>
     * 例）
     * StringUtils.ltrimer('|', "")          = ""
     * StringUtils.ltrimer('|', "bob|")     = "bob|"
     * StringUtils.ltrimer('|', "||bob")    = "bob"
     * StringUtils.ltrimer('|', "||bo|b")    = "bo|b"
     * </pre>
     * @param input 入力文字列
     * @return 前方ブランクが除去された文字列
     */
    private static String ltrimer(char target, String input) {
        if (input == null || input.equals(EMPTY)){
            return EMPTY;
        }
        int length = 0;
        while ((length < input.length()) && input.charAt(length) == target) {
            length++;
        }
        return (length > 0 ) ? input.substring(length, input.length()) : input ;
    }
    
     /**
      * 文字列をキーを元にフォーマット通りにフォーマットする
      * (TestCode/S-PartNo/C-PartNoフォーマット用に使用する)
      * 
      * <pre>
      * 例）
      * strFormat("123456789", "111,111,11", ',')          = "123,456,78"
      * </pre>
      * @param param 変換対象文字列
      * @param format フォーマットデータ
      * @param splitKey フォーマットに対する分割文字列
      * @return
      */
    public static String strFormat(String param, String format, char splitKey) {
          if (param == null || param.equals(EMPTY)) {
               return EMPTY;
          }
          String[] splitRst = StringUtils.split(format, splitKey);
          StringBuffer buf = new StringBuffer();
          int index = 0;

          for (int i = 0; i < splitRst.length; i++) {
               if(param.length() <= (splitRst[i].length() + index)){
                    buf.append(param.substring(index, param.length()));
                    break;
               }
               
               buf.append(param.substring(index, splitRst[i].length() + index));
               if (i != splitRst.length - 1) {
                    buf.append(splitKey);
               }
               index = index + splitRst[i].length();
          }
          return buf.toString();
     }

    /**
     * 特殊文字のフィルタ　（<>&"\' ）
     * 
     * <pre>
     * 例）
     * StringUtils.filter("<bob>")          = "&lt;bob&gt;"
     * StringUtils.filter("b&o"b")     = "b&amp;o&quot;b"
     * </pre>
     * @param value
     * @return String
     */
    public static String filter(String value) {
        if (value == null || value.length() == 0) {
            return value;
        }
        StringBuffer result = null;
        String filtered = null;
        for (int i = 0; i < value.length(); i++) {
            filtered = null;
            switch (value.charAt(i)) {
                case '<':
                    filtered = "&lt;";
                    break;
                case '>':
                    filtered = "&gt;";
                    break;
                case '&':
                    filtered = "&amp;";
                    break;
                case '"':
                    filtered = "&quot;";
                    break;
                case '\'':
                    filtered = "&#39;";
                    break;
                case ' ':
                     filtered = "&nbsp;";
                     break;
            }

            if (result == null) {
                if (filtered != null) {
                    result = new StringBuffer(value.length() + 50);
                    if (i > 0) {
                        result.append(value.substring(0, i));
                    }
                    result.append(filtered);
                }
            } else {
                if (filtered == null) {
                    result.append(value.charAt(i));
                } else {
                    result.append(filtered);
                }
            }
        }
        return result == null ? value : result.toString();
    }
    
    /**
      * 第一引数の文字列が、第二引数のint型で指定されたバイト以上の長さを持つ場合に
      * 余分な部分を削除するメソッド
      * 
      * <pre>
      * 例）
      * StringUtils.deleteFromTail("123456789", 10)     = "123456789"
      * StringUtils.deleteFromTail("123456789", 5)     = "12345"
      * </pre>
      * @param          String               name               文字列
      * @param          int                    maxLength          最大バイト数を示す数字
      * @return          String               nayoseName          文字列から余分な文字列を削除した文字列
     */
     public static String deleteFromTail(String value, int maxLength) {
          // 文字列がnullの場合、そのまま返す
        if (value == null || value.length() == 0) {
            return value;
        }

          // 文字列の整形を行う
          StringBuffer strBuf = new StringBuffer(value);

          // 第二引数で指定された以上の余分な文字列を削除する
          if (strBuf.length() > maxLength) {
               strBuf = strBuf.delete(maxLength,strBuf.length());
          }

          return strBuf.toString();
     }

    /**
     * 指定された文字列を指定された区切り文字で分割する
     *
     * <pre>
     * 例）
     * split(null, *)         = null
     * split("", *)           = []
     * split("a.b.c", '.')    = ["a", "b", "c"]
     * split("a..b.c", '.')   = ["a", "b", "c"]
     * split("a:b:c", '.')    = ["a:b:c"]
     * split("a\tb\nc", null) = ["a", "b", "c"]
     * split("a b c", ' ')    = ["a", "b", "c"]
     * </pre>
     *
     * @param str            解析文字列
     * @param separatorChar  分割符
     * @return 文字列配列
     */
    public static String[] split(String str, char separatorChar) {
        return StringUtils.split(str, separatorChar);
    }
    
    public static String[] split(String str, String separatorChar) {
        return StringUtils.splitByWholeSeparator(str, separatorChar);
    }
    /**
     * 指定された文字列を指定された区切り文字で分割する
     *
     * <pre>
     * 例）
     * splitPreserveAllTokens(null, *)         = null
     * splitPreserveAllTokens("", *)           = []
     * splitPreserveAllTokens("a.b.c", '.')    = ["a", "b", "c"]
     * splitPreserveAllTokens("a..b.c", '.')   = ["a", "", "b", "c"]
     * splitPreserveAllTokens("a:b:c", '.')    = ["a:b:c"]
     * splitPreserveAllTokens("a\tb\nc", null) = ["a", "b", "c"]
     * splitPreserveAllTokens("a b c", ' ')    = ["a", "b", "c"]
     * splitPreserveAllTokens("a,b,c,", ',')    = ["a", "b", "c", ""]
     * </pre>
     *
     * @param str            解析文字列
     * @param separatorChar  分割符
     * @return 文字列配列
     */
    public static String[] splitPreserveAllTokens(String str, char separatorChar) {
        return StringUtils.splitPreserveAllTokens(str, separatorChar);
    }

    /**
     * 指定された配列オブジェクトの文字列表現を指定された区切り文字を挟んで連結する
     *
     * <pre>
     * 例）
     * join(null, *)                = null
     * join([], *)                  = ""
     * join(["a", "b", "c"], "--")  = "a--b--c"
     * join(["a", "b", "c"], null)  = "abc"
     * join(["a", "b", "c"], "")    = "abc"
     * join([null, "", "a"], ',')   = ",,a"
     * </pre>
     *
     * @param array  連結されよう配列

     * @param separator  連結文字列
     * @return 連接される文字列
     */
    public static String join(Object[] array, String separator) {
        return StringUtils.join(array, separator);
    }
    
    /**
     * 指定された配列オブジェクトの文字列(""とnull以外)表現を指定された区切り文字を挟んで連結する
     *
     * <pre>
     * 例）
     * join(null, *)                = null
     * join([], *)                  = ""
     * join(["a", "b", "c"], "--")  = "a--b--c"
     * join(["a", "b", "c"], null)  = "abc"
     * join(["a", "b", "c"], "")    = "abc"
     * join([null, "", "a"], ',')   = ",,a"
     * </pre>
     *
     * @param array  連結されよう配列

     * @param separator  連結文字列
     * @return 連接される文字列
     */
    public static String joinExceptSpace(Object[] array, String separator) {
        String retStr = "";
        for (int i=0; i < array.length; i++) {
            if (array[i] == null || "".equals(array[i])) {
                continue;
            } else {
                retStr = retStr + array[i] + separator;
            }
        }
        if (retStr.length() < 1) {
            return retStr;
        }
        if (separator.equals(retStr.substring(retStr.length()-1, retStr.length()))) {
            retStr = retStr.substring(0,retStr.length()-1);
        }
        return retStr;
    }

    /**
     * 指定されたIteratorオブジェクトの文字列表現を指定された区切り文字を挟んで連結する
     *
     * <pre>
     * 例）
     * join(Object[] array, String separator)のサンプルを参照して
     * </pre>
     *
     * @param iterator  連結されよう配列

     * @param separator  連結文字列
     * @return 連接される文字列
     */
	@SuppressWarnings("rawtypes")
	public static String join(Iterator iterator, String separator) {
        return StringUtils.join(iterator, separator);
    }

    /**
     * 定された文字列中の小文字をすべて大文字にする
     *
     * <pre>
     * 例）
     * StringUtil.upperCase(null)  = null
     * StringUtil.upperCase("")    = ""
     * StringUtil.upperCase("aBc") = "ABC"
     * </pre>
     *
     * @param str
     * @return the upper cased String, 
     */
    public static String upperCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase();
    }

    /**
     * 指定された文字列中の大文字をすべて小文字にする
     *
     * <pre>
     * 例）
     * StringUtil.lowerCase(null)  = null
     * StringUtil.lowerCase("")    = ""
     * StringUtil.lowerCase("aBc") = "abc"
     * </pre>
     *
     * @param str  the String to lower case, may be null
     * @return the lower cased String
     */
    public static String lowerCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase();
    }

    /**
     * 指定桁数に達するまで前ゼロ編集します。
     * <pre>
     * 例）
     * StringUtil.zeroPadding(null,3)  = "000"
     * StringUtil.defaultString("1",3) = "001"
     * StringUtil.defaultString("1234",3) = "1234"
     * </pre>
     * @param argString     文字列
     * @param argWidth     指定桁数
     * @return 前ゼロ編集された文字列
     */
    public static String zeroPadding(String argString, int argWidth) {
          if (argString == null) argString = "";
        if (argString.length() < argWidth) {
            for (int i = argString.length(); i < argWidth; i++) {
              argString = "0" + argString;
            }
        }
        return argString;
    }
    
    /**
     * 指定されたカラム名の値のHTML形式を取得します。
     * 
     * @param colName カラム名
     * @return 指定されたカラム名の値のHTML形式
     */
    public static String getColumnValueHTML(String colName) {

        if (isBlank(colName)) {
             return "&nbsp;";
        }
        if (colName.indexOf("\"") >= 0) {
            colName = colName.replaceAll("\"", "&quot;");
        }
        if (colName.indexOf("<") >= 0) {
            colName = colName.replaceAll("<", "&lt;");
        }
        if (colName.indexOf(">") >= 0) {
            colName = colName.replaceAll(">", "&gt;");
        }
        if (colName.indexOf("\n") >= 0) {
            colName = colName.replaceAll("\n", "<br>");
        }
        
        return colName;
    }
    
    /**
     * 文字列を３桁カンマ区切りします。
     * <pre>
     * 例）
     * StringUtil.insComma("123")  = "123"
     * StringUtil.insComma("1234")  = "1,234"
     * StringUtil.insComma("1234567")  = "1,234,567"
     * StringUtil.insComma("-900")  = "-900"
     * StringUtil.insComma("-20000")  = "-20,000"
     * StringUtil.insComma("-1000")  = "-1,000"
     * </pre>
     * @param val 文字列
     * @return ３桁カンマ区切りされた文字列
     * @return 前ゼロ編集された文字列
     */    
    public static String  insComma(String val) {
        String strBegin = "";
        if (!isEmpty(val) && val.charAt(0) == '-') {
             strBegin = "-";
             val = val.substring(1);
        }
        byte wk[] = val.getBytes();
        StringBuffer ret = new StringBuffer();
        int cnt = 0;
        for(int lp = wk.length - 1; lp >= 0 ; lp--) {
            if((cnt != 0) && (cnt % 3) == 0) {
                ret.insert(0, ',');
            }
            ret.insert(0, (char)wk[lp]);
            cnt++;
        }
        return strBegin + ret.toString();
    }

    /**
     * 文字列の後方にある対象文字を除去する
     * 
     * <pre>
     * 例）
     * StringUtils.rtrimer('|', "")          = ""
     * StringUtils.rtrimer('|', "|bob")     = "|bob"
     * StringUtils.rtrimer('|', "bob||")    = "bob"
     * StringUtils.rtrimer('|', "bo|b||")    = "bo|b"
     * </pre>
     * @param input 入力文字列
     * @return 前方ブランクが除去された文字列
     */
    public static String rtrimer(char target, String input) {
        if (input == null || input.equals(EMPTY)){
            return EMPTY;
        }
        int length = input.length();
        while ((length > 0) && input.charAt(length -1) == target) {
            length--;
        }
        return (length < input.length()) ? input.substring(0, length) : input ;
    }

    /**
     * 文字列の前方と後方にある対象文字を除去する
     * 
     * <pre>
     * 例）
     * StringUtils.lrtrimer('|', "")          = ""
     * StringUtils.lrtrimer('|', "|bob|")     = "bob"
     * StringUtils.lrtrimer('|', "||bob||")    = "bob"
     * StringUtils.lrtrimer('|', "||bo|b||")    = "bo|b"
     * </pre>
     * @param input 入力文字列
     * @return 前方ブランクが除去された文字列
     */
    public static String trimer(char target, String input) {
        if (input == null || input.equals(EMPTY)){
            return EMPTY;
        }
        return ltrimer(target, rtrimer(target, input));
    }
    
    /**
     * 文字列にある対象文字を除去する
     * 
     * <pre>
     * 例）
     * StringUtils.alltrimer({"|","."}, "")          = ""
     * StringUtils.alltrimer({"|","."}, "|b.o.b|")     = "bob"
     * StringUtils.alltrimer({"|","."}, "||b.o.b||")    = "bob"
     * StringUtils.alltrimer({"|","."}, "||b.o|b.||")    = "bob"
     * </pre>
     * @param 削除しなければならない文字列
     * @param input 入力文字列
     * @return 対象文字が除去された文字列
     */
    public static String alltrimer(String[] target,String input){
         for(int i = 0 ;i < target.length;i++){
              input = input.replace(target[i], "");
         }
         return input;
    }
    
    /**
     * 文字列にある対象文字を除去する
     * 
     * <pre>
     * 例）
     * StringUtils.allchange({"ぁ","ゅ"},{"あ","ゆ"},"")          = ""
     * StringUtils.allchange({"ぁ","ゅ"},{"あ","ゆ"},"文ぁ字ゅ列")     = "文あ字ゆ列"
     * </pre>
     * @param target 変換しなければならない文字列
     * @param src 変換文字列
     * @param input 入力文字列
     * @return 対象文字が変換された文字列
     */
    public static String allchange(String[] target,String[] src,String input){
         for(int i = 0 ;i < target.length;i++){
              input = input.replace(target[i], src[i]);
         }
         return input;
    }
    
    /**
     * 指定桁数に達するまで前ゼロ編集します。
     * <pre>
     * 例）
     * StringUtil.zeroPadding(null,3,"A")  = "AAA"
     * StringUtil.defaultString("1",3,"A") = "AA1"
     * StringUtil.defaultString("1234",3,"A") = "1234"
     * </pre>
     * @param argString     文字列
     * @param argWidth     指定桁数
     * @param target          指定文字列
     * @return 前ゼロ編集された文字列
     */
    public static String charPadding(String argString, int argWidth, String target) {
          if (argString == null) argString = "";
        if (argString.length() < argWidth) {
            for (int i = argString.length(); i < argWidth; i++) {
              argString = String.valueOf(target) + argString;
            }
        }
        return argString;
    }    

    /**
     * 文字列の前方と後方にある半角スペースと全角スペースを除去する
     * 
     * <pre>
     * 例）
     * StringUtil.allBlanktrim(null)  = ""
     * StringUtil.allBlanktrim("")  = ""
     * StringUtil.allBlanktrim(" ")  = ""
     * StringUtil.allBlanktrim("　")  = ""
     * StringUtil.allBlanktrim("　a　a ")  = a　a
     * StringUtil.allBlanktrim("　a a ")  = a a
     * </pre>
     * 
     * @param strValue
     * @return strValue
     */     
    public static String allBlankTrim(String strValue) {
        if (StringBaseUtil.isEmpty(strValue)) {
             strValue = "";
        }else {
             strValue = trimer('　', strValue.trim()).trim();
        }
        return strValue;
    }
     
     /**
      * ヌル値をセロに変更する
      * 対象：すべての数値型,フラグ、チェックボックス
      * 
      * StringUtil.nullToZero(null)  = "0"
      * StringUtil.nullToZero("")  = "0"
      * StringUtil.nullToZero(" ")  = "0"
      * StringUtil.nullToZero("1 ")  = "1"
      * 
      * @param strValue
      * @return strValue
      */
     public static String nullToZero(String strValue) {
          if (strValue == null || strValue.trim().length() == 0) {
               strValue = "0";
          } else {
               strValue = strValue.trim();
          }
          return strValue;
     }
     
     /**
      * '' 時刻 がnull場合、""を設定する。
      * 
      * StringUtil.setTimeDefalutVal(null)  = ""
      * StringUtil.setTimeDefalutVal("")  = ""
      * StringUtil.setTimeDefalutVal("111")  = "111"
      * 
      * @param strValue
      * @return strValue
      */
     public static String setTimeDefalutVal(String strValue) {
        if (StringBaseUtil.isEmpty(strValue)) {
             strValue  = "";
        }
        return strValue;
    }
     
     /**
      * 文字列の数値部分を指定桁数分カウントして採番する。
     * <pre>
     * 例）
     * 入力文字列:"09", 指定桁数:"1", 採番対象文字の桁数："2" ->  加工後の数値:"10"
     * 入力文字列:"001", 指定桁数:"2", 採番対象文字の桁数："1" ->  加工後の数値:"003"
     * 入力文字列:"A0008", 指定桁数:"1", 採番対象文字の桁数："4" ->  加工後の数値:"A0009"
     * </pre>
      * @param strValue 入力文字列
      * @param index 指定桁数
      * @param size 採番対象文字の桁数（入力文字列の後ろからの桁数）
      * @return retStr　加工後の数値
      */
     public static String getSaibanNO(String strValue, int index, int size) {
 		String retStr = strValue;
    	int length = strValue.length();
    	if (!StringBaseUtil.isEmpty(strValue) && length >= size) {
    		int iParam = 0;
    		try{
        		if(length == 1){
        			iParam = Integer.parseInt(strValue);
        			retStr = "";
        		} else {
        			retStr = strValue.substring(0, length - size);
        			iParam = Integer.parseInt(strValue.substring(length - size));
        		}
        		for(int i=0; i<index; i++){
        			iParam += 1;
        		}
        		retStr += StringBaseUtil.zeroPadding(String.valueOf(iParam), size);
	       		if(retStr.length() > length){
	       			retStr = strValue;
	       		}
    		} catch (Exception e) {
    			return strValue;
    		}
    		
    	}
   	 return retStr;
    }
     
     /**
      * 加工対象文字列の後ろに、指定した桁数分の指定文字列をパディングする。
      * <pre>
      * 例）
      * StringUtil.paddingChar(null,3,"A")  = "AAA"
      * StringUtil.paddingChar("1",3,"A") = "1AA"
      * StringUtil.paddingChar("1234",3,"A") = "1234"
      * </pre>
      * @param argString     文字列
      * @param argWidth     指定桁数
      * @param target          指定文字列
      * @return 加工後の文字列
      */
     public static String paddingChar(String argString, int argWidth, String target) {
    	 if (argString == null) argString = "";
         if (argString.length() < argWidth) {
             for (int i = argString.length(); i < argWidth; i++) {
            	 argString += target;
             }
         }
         return argString;
     } 
     
	 /**
	  * パラメータの文字列を指定のバイト数で切り出す。<br>
	  * 最終文字が2バイト文字の1バイト目の場合、その1つ前の文字までを返却値とする。
	  * @param str 入力文字列
	  * @param byte バイト数
	  * @return 入力値がOKであればtrueを返却
	  */
	public static String getByteLength(String str, int byt) {
		// nullの場合空文字を返却
		if (str == null) return "";
		
		// 指定のバイト数まで文字列を付与
		StringBuffer buf = new StringBuffer();
		String tmp;
		int cnt = 0;
		String ret = str;
		try {
			for (int i = 0; i < ret.length(); i++) {
				tmp = ret.substring(i, i + 1);
				if (tmp.getBytes("UTF-8").length == 1) {
					cnt++;
				} else {
					cnt += 2;
				}
				// 指定のバイト数を超えたらbreak
				if (cnt > byt) break;
				// 返却値に追加
				buf.append(tmp);
				// 指定のバイト数だったらbreak
				if (cnt == byt) break;
			}
		} catch (UnsupportedEncodingException e) {
			return null;
		}
		return buf.toString();
	}

	/**
	 * ダブルコーテーションをつけます。（CSV用）
	 * @param itemValue
	 * @return
	 */
	public static String addWQuotes(Object itemValue) {
		return StringBaseUtil.addWQuotes(itemValue, true);
	}

	/**
	 * ダブルコーテーションをつけます。（CSV用）
	 * @param itemValue
	 * @param hasComma
	 * @return
	 */
	public static String addWQuotes(Object itemValue, boolean hasComma) {
		if (itemValue == null) {
			return hasComma ? "\"\"," : "\"\"";
		}
		
		return "\"".concat(rtrim(itemValue)).concat(hasComma ? "\"," : "\"");
	}

	
	/**
	 * 右側の空白を削除します
	 * @param src
	 * @return
	 */
	public static String rtrim(Object src){
		if (src instanceof String){
			return ((String)src).replaceAll("[　 ]*$", "");
		}
		return String.valueOf(src);
	}
}