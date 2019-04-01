package jp.co.common.frame.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import jp.co.common.frame.constant.BaseSystemConstant;
import jp.co.common.frame.util.prop.EnvPropertyUtil;

/**
 * Excel出力ヘルプクラス
 * @author NTS
 *
 */
/**
 *  Excel出力ヘルプクラス。
 * <pre>
 *  以下の機能をサポートする。
 * </pre>
 * <ul>
 *   <li>Excelテンプレートバス初期化処理</li>
 *   <li>Excel出力データを書込み</li>
 *   <li>テンプレートが存在するかチェックします</li>
 *   <li>Excelファイルを指定ストリームに出力します</li>
 * </ul>
 * @author NTS
 * @version 1.0 
 */
public class ExcelUtil{
	
	/** Excelテンプレートバス */
	private static String templatepath = EnvPropertyUtil.getInstance().getPropertyString("EXCEL.PATH.TEMPLATE");
	/** Excelテンプレートファイル名*/
	private String template;
	/** Excelのworkbook*/
	private XSSFWorkbook workbook;
	/** テンプレートファイル名称 */	
	private String excelTemplate;
	/** Excel出力ファイル名称 */	
	private String excelOutputFileName;
	
	/**
	 * コンストラクタ
	 * @param excelTemplate テンプレートファイル名称
	 * @param excelOutputFileName Excel出力ファイル名称
	 * @throws IOException
	 */
	public ExcelUtil(String excelTemplate, String excelOutputFileName) throws IOException{
		//テンプレートファイル名称
		this.excelTemplate = excelTemplate;
		//Excel出力ファイル名称
		this.excelOutputFileName = excelOutputFileName;
	}
	
	/**
	 * Excel出力
	 * @return
	 */
	public String printExcel(Map<String, Object> outDataMap, HttpServletResponse response, ServletContext context){
		OutputStream outputStream = null;
		
		String excels = BaseSystemConstant.EXTENSION_EXCEL_XLSM;
		
		//テンプレートファイル名称
		this.template = context.getRealPath("/") + templatepath	+ this.excelTemplate + excels;
		
		String strExcelCharSet = BaseSystemConstant.CHARSET_NAME_SHIFT_JIS;
		
		
		//Excel出力ファイル名称
		
		String strFileName = excelOutputFileName + excels;
		
		try {
			String filename = new String(strFileName.getBytes(strExcelCharSet),
					BaseSystemConstant.CHARSET_NAME_ISO8859_1);
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" + filename);
			outputStream = response.getOutputStream();
			writeExcel(outDataMap);
			outputExcel(outputStream);
			outputStream.flush();
		}catch(IOException ex){
		}finally{
			try{
				outputStream.close();
			}catch(Exception ex){
			}
		}
		return null;
	}
	
	/**
	 * Excel出力
	 * @return
	 */
	public String printExcelXlsx(Map<String, Object> outDataMap, HttpServletResponse response, ServletContext context){
		OutputStream outputStream = null;
		
		String excels = BaseSystemConstant.EXTENSION_EXCEL_XLSX;
		
		//テンプレートファイル名称
		this.template = context.getRealPath("/") + templatepath	+ this.excelTemplate + excels;
		
		String strExcelCharSet = BaseSystemConstant.CHARSET_NAME_SHIFT_JIS;
		
		
		//Excel出力ファイル名称
		
		String strFileName = excelOutputFileName + excels;
		
		try {
			String filename = new String(strFileName.getBytes(strExcelCharSet),
					BaseSystemConstant.CHARSET_NAME_ISO8859_1);
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" + filename);
			outputStream = response.getOutputStream();
			writeExcel(outDataMap);
			outputExcel(outputStream);
			outputStream.flush();
		}catch(IOException ex){
		}finally{
			try{
				outputStream.close();
			}catch(Exception ex){
			}
		}
		return null;
	}
	
	/**
	 * Excel出力データを書込み
	 * @param outDataMap　出力データ
	 * @throws IOException 
	 */
	public void writeExcel(Map<String, Object> outDataMap) throws IOException{
		if (!isTemplateExist()){
			throw new IOException("the file" + this.template + " does not exist.");
		}
		// テンプレートファイルを開き、新データシートを追加します。
		else {
			FileInputStream fis = null;
			try {				
				fis = new FileInputStream(this.template);				
		  		XLSTransformer transformer = new XLSTransformer();
				this.workbook = (XSSFWorkbook) transformer.transformXLS(fis , outDataMap);
			}catch(Exception ex){
			}
			finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					fis = null;
				}
			}
		}   	
	}	
	
	/**
	 * テンプレートが存在するかチェックします。
	 * @return
	 */
	public boolean isTemplateExist(){
		return new File(this.template).exists();
	}
   
	/**
	 * Excelファイルを指定ストリームに出力します。
	 * @param stream
	 * @throws IOException
	 */
	public void outputExcel(OutputStream stream) throws IOException{
		if (!isTemplateExist()){
			throw new IOException("the file" + this.template + " does not exist.");
		}
		setForceFormulaRecalculation();
		this.workbook.write(stream); 
	}

	/**
	 * Excel出力データを書込み(シート分出力)
	 * @param outPutList 出力データリスト
	 * @param sheetNameList シート名称リスト
	 * @param beanName 出力データ名称
	 * @throws IOException 
	 */
	public void writeExcel(List<Object> outPutList, List<String> sheetNameList, String beanName) throws IOException{
		if (!isTemplateExist()){
			throw new IOException("the file" + this.template + " does not exist.");
		}
		// テンプレートファイルを開き、新データシートを追加します。
		else {
			InputStream is1 = null;
			XLSTransformer transformer = new XLSTransformer();
			try {
				is1 = new BufferedInputStream(
						new FileInputStream(this.template));

				Map<String, Object> outPutMap = new HashMap<String, Object>();
				outPutMap.put("NumberUtil", NumberUtil.class);				// 数字用クラス
				Workbook book = transformer.transformMultipleSheetsList(is1, outPutList,
						sheetNameList, beanName, outPutMap, 0);
				this.workbook = (XSSFWorkbook) book;
			}catch(Exception ex){
				ex.printStackTrace();
			}
			finally {
				if (is1 != null) {
					try {
						is1.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					is1 = null;
				}
			}
		}
	}

	/**
	 * Excelファイルを指定ストリームに出力します。
	 * @param stream
	 * @throws IOException
	 */
	public void outputCsvToExcel(OutputStream csvStream, OutputStream stream) throws IOException{
		if (!isTemplateExist()){
			throw new IOException("the file" + this.template + " does not exist.");
		}
		
		FileInputStream fis = null;
		XSSFSheet sheet = null;
		try {
			fis = new FileInputStream(this.template);
			this.workbook = new XSSFWorkbook(fis);
			sheet = workbook.createSheet("data");
	
		} finally {
			if (fis != null) {
				fis.close();
				fis = null;
			}
		}
	
		int rownum = 0;
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new ByteArrayInputStream(((ByteArrayOutputStream) csvStream).toByteArray())
				, BaseSystemConstant.SYSTEM_ENCODING));
		
		while(rownum < 65536){
			// 行データ読み取り
			String line = reader.readLine();
			if (line == null){
				break;
			}
			
			// 行オブジェクト作成
			Row row = sheet.createRow(rownum);
			
			// セル値設定
			String[] items = splite(line);
			if (items.length > 1){
				for(int i = 1; i < items.length; i++){
					row.createCell(i - 1);
					setCellValue(sheet, rownum, i - 1, items[i]);
				}
			}
			rownum++;
		}
		reader.close();

		this.workbook.write(stream);
	}
	
	/**
	 * Excelセルの値を設定
	 * @param sheet
	 * @param rownum
	 * @param columnIndex
	 * @param val
	 */
	private static void setCellValue(XSSFSheet sheet, int rownum, int columnIndex, String val){
		// ^[ ]*\\d+[.]\\d+[ ]*$ 		小数点付き数値 1.2 0.1
		// ^[ ]*[1-9]\\d*[ ]*$ 			ゼロ以外で始まる数値（ゼロで始まるのものはコードで、文字列と見る）
		// ^[ ]*[0][ ]*$ 				ゼロ
		// ^[ ]*[-]\\d+[.]?\\d+[ ]*$ 	マイナスの数値
		if( val.matches("(^[ ]*\\d+[.]\\d+[ ]*$)|(^[ ]*[1-9]\\d*[ ]*$)|(^[ ]*[0][ ]*$)|([ ]*[-]\\d+[.]?\\d+[ ]*$)") ){
			sheet.getRow(rownum).getCell(columnIndex).setCellValue(Double.parseDouble(val));
		} else {
			sheet.getRow(rownum).getCell(columnIndex).setCellValue(new XSSFRichTextString(val));
		}
	}
	
	
	/**
	 * CSV項目を分割
	 * @param param
	 * @return
	 */
	private static String[] splite(String param){
		if (param == null){
			return new String[]{};
		}
		
		return param.split("^\"|\"$|\"[ ]*,[ ]*\"");
	}
	
	/**
	 * 数式再計算
	 */
	private void setForceFormulaRecalculation(){
		// 作業シートオブジェクト
		XSSFSheet sheet = null;
		int numSheets = this.workbook.getNumberOfSheets();
		for(int i = 0; i < numSheets; i++){
			sheet = this.workbook.getSheetAt(i);
			//自動計算
			sheet.setForceFormulaRecalculation(true);
		}
	}
}