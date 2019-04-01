package jp.co.common.frame.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import jp.co.common.frame.exception.CommonErrorPageException;
import jp.co.common.frame.exception.LocalRuntimeException;
import jp.co.common.frame.util.prop.MessagePropertyUtil;

/**
 * 
 * 基盤ファイル処理クラス<br>
 * 
 * @author  NTS
 * @version 1.0
 */
public class FileUtil {

	/**
	 *  ファイルをコピーする
	 *  @param from
	 *  @param to
	 *  @param fileName
	 */
	public static void copyFile(String from, String to, String fileName) throws Exception {
		try {
			File file = new File(from + File.separator + fileName);
			if (file.exists()) {
				InputStream input = new FileInputStream(file);
				OutputStream output = new FileOutputStream(to + File.separator + fileName);
				byte[] bytes = new byte[10240];
				int c;
				while ((c = input.read(bytes)) != -1) {
					output.write(bytes, 0, c);
				}
				input.close();
				output.close();
			}
		} catch (Exception e){
			throw new LocalRuntimeException("ファイル処理にて、エラーが発生しました。", e);			
		}
	}
	
	/**
	 *  ファイルをコピーする
	 *  @param from
	 *  @param to
	 *  @param fileName
	 */
	public static void copyFile(String from, String to, String fromFileName, String toFileName) throws Exception {
		try {
			File userFiles = new File(to);
			if(!userFiles.exists()) {
				userFiles.mkdir();
			}
			File file = new File(from + File.separator + fromFileName);
			if (file.exists()) {
				InputStream input = new FileInputStream(file);			
	        	OutputStream output = new FileOutputStream(to + File.separator + toFileName);
				byte[] bytes = new byte[10240];
				int c;
				while ((c = input.read(bytes)) != -1) {
					output.write(bytes, 0, c);
				}
				input.close();
				output.close();
			}
		} catch (Exception e){
			throw new LocalRuntimeException("ファイル処理にて、エラーが発生しました。", e);			
		}
	}
	
	/**
	 * HTMLコピー用（URL変更必要あり）
	 * @param from
	 * @param to
	 * @param fileName
	 * @throws IOException
	 */
    public static void copyHtmlRsvToReal(String from, String to, String fileName) throws Exception {
		try {
			File copyFrom = new File(from + File.separator + fileName);
	        if (!copyFrom.exists()) {
	            return;
	        }
			File toFiles = new File(to);
			if(!toFiles.exists()) {
				toFiles.mkdir();
			}
			File copyTo = new File(to + File.separator + fileName);
	        BufferedReader reader = new BufferedReader(new FileReader(copyFrom));
	        String readLine = "";  
	        FileWriter writer = new FileWriter(copyTo);  
	        while ((readLine = reader.readLine()) != null) {
	        	readLine = readLine.replace("&formBean.isReserve=1", "&formBean.isReserve=0");
	        	writer.write(readLine + "\n");
	        }
	        reader.close();
	        writer.flush();
	        writer.close();
		} catch (Exception e){
			throw new LocalRuntimeException("ファイル処理にて、エラーが発生しました。", e);			
		}
    }
    
	/**
	 * コンテンツHTMLをテンプレートフォルダにコピー
	 * @param from
	 * @param to
	 * @param fileName
	 * @throws IOException
	 */
    public static void copyHtmlPageToTemplate(String from, String to, String pageId, String templatePageId) throws Exception {
		try {
			File copyFrom = new File(from + File.separator + pageId + ".html");
	        if (!copyFrom.exists()) {
	            return;
	        }
			File toFiles = new File(to);
			if(!toFiles.exists()) {
				toFiles.mkdir();
			}
			File copyTo = new File(to + File.separator + templatePageId + ".html");
	        BufferedReader reader = new BufferedReader(new FileReader(copyFrom));
	        String readLine = "";  
	        FileWriter writer = new FileWriter(copyTo);  
	        while ((readLine = reader.readLine()) != null) {
	        	readLine = readLine.replace("pageId=" + pageId, "pageId=" + templatePageId);
	        	readLine = readLine.replace("&formBean.isReserve=0", "&formBean.isTemplateFrom=1");
	        	readLine = readLine.replace("&formBean.isReserve=1", "&formBean.isTemplateFrom=1");
	        	readLine = readLine.replace("../../..", "");
	        	writer.write(readLine + "\n");
	        }
	        reader.close();
	        writer.flush();
	        writer.close();
		} catch (Exception e){
			throw new LocalRuntimeException("ファイル処理にて、エラーが発生しました。", e);			
		}
    }    
	
	/**
	 * (x)パージされたコンテンツの物理ファイルの削除
	 * @param filefrom コンテンツの物理ファイルを格納している場所
	 * @param pageId
	 * @return
	 * @throws IOException
	 */
	public static void deletePurgeFile(String filefrom, String pageId) throws Exception {
		try {
			File[] files = new File(filefrom).listFiles(new MyFilenameFilter(pageId));
			if(files != null){
				for (File file : files) {
					if (file.getName().contains(pageId)) {
						deleteFile(filefrom, file.getName());
					}
				}
			}			
		} catch (Exception e){
			throw new LocalRuntimeException("ファイル処理にて、エラーが発生しました。", e);			
		}
	}
	
    static class MyFilenameFilter implements FilenameFilter{  
        private String pageId;  
        public MyFilenameFilter(String pageId){  
            this.pageId = pageId;  
        }  
        public boolean accept(File dir,String name){  
            return name.contains(pageId);  
        }  
    } 

	/**
	 *  ファイルを削除する
	 *  @param from
	 *  @param fileName
	 */
	public static void deleteFile(String from, String fileName) throws Exception {
		try {
			File file = new File(from + File.separator + fileName);
			if (file.exists()) {
				deleteFile(file);
			}
		} catch (Exception e){
			throw new LocalRuntimeException("ファイル処理にて、エラーが発生しました。", e);			
		}
	}
	
	/**
	 * ファイルおよびフォルダを削除
	 * @param file
	 * @throws Exception
	 */
	public static void deleteFile(File file) throws Exception {
		try {
			if (!file.isDirectory()) {
				file.delete();
				return;
			}
			File[] fileFromArray = file.listFiles();
			for (File deleteFile : fileFromArray) {
				deleteFile(deleteFile);
			}
			file.delete();
		} catch (Exception e){
			throw new LocalRuntimeException("ファイル処理にて、エラーが発生しました。", e);			
		}
	}	
	
	/**
	 * 添付ファイルをアップロードする
	 * @param File input アップロードファイル
	 * @param String fileNm アップロードファイル名
	 * @param String アップロードファイルのパス
	 */
	public static void uploadFile(File input, String fileNm, String uploadPath) throws Exception {
		FileOutputStream out = null;
		InputStream in = null;
		try {
			
			String fileSeparator = File.separator;
			File path = new File(uploadPath + fileSeparator + fileSeparator);
			if (!path.exists()) {
				path.mkdirs();
			}
			File realFile = new File(path, fileNm);
			if (realFile.exists()) {
				realFile.delete();
			}
			realFile.createNewFile();
			out = new FileOutputStream(realFile);
			int c;
			in = new FileInputStream(input);
			byte[] bytes = new byte[10240];
			while ((c = in.read(bytes)) != -1) {
				out.write(bytes, 0, c);
			}
			out.flush();
		} catch (Exception e) {
			throw new LocalRuntimeException("ファイル処理にて、エラーが発生しました。", e);			
		} finally {
			if (in != null) {
				in.close();
				in = null;
			}
			if (out != null) {
				out.close();
				out = null;
			}
		}
	}
	
	/**
	 * ZIPファイルを解凍し、元ZIPファイルを削除する。
	 * @param uploadPath
	 * @param zipUploadName
	 * @param mainFile
	 * @param mainName
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static void unzipFile(String uploadPath, String zipUploadName, String mainFile, String mainName) throws Exception {
		OutputStream out = null;
		InputStream in = null;
		try {
			ZipFile zfile = new ZipFile(zipUploadName);
			Enumeration zList = zfile.getEntries();
			ZipEntry ze = null;
			while (zList.hasMoreElements()) {
				ze = (ZipEntry) zList.nextElement();
				if (!ze.isDirectory()) {
					String zeName = ze.getName();
					if (ze.getName().equals(mainFile)) {
						zeName = mainName;
					}
					File outFile = new File(uploadPath + zeName);
					if (!outFile.exists()) {
						outFile.getParentFile().mkdirs();
						outFile.createNewFile();
					}
					out = new BufferedOutputStream(new FileOutputStream(outFile));
					in = new BufferedInputStream(zfile.getInputStream(ze));
					byte[] bytes = new byte[10240];
					int c = 0;
					while ((c = in.read(bytes)) != -1) {
						out.write(bytes, 0, c);
					}
					in.close();
					out.close();
				}
			}
			zfile.close();
			new File(zipUploadName).delete();			
		} catch (Exception e) {
			throw new LocalRuntimeException("ファイル処理にて、エラーが発生しました。", e);			
		} finally {
			if (in != null) {
				in.close();
				in = null;
			}
			if (out != null) {
				out.close();
				out = null;
			}
		}

	}
	
	/**
	 * サーバ接続判定Handler
	 * @param ipAddress
	 */
	public static void pingServer(String ipAddress) {
		if (!ping(ipAddress)){
			ServerAccessException();
		}
	}
	
	/**
	 * サーバ接続Exception
	 * @param ipAddress
	 */
	public static void ServerAccessException() {
		String message = MessagePropertyUtil.getStringProperty("MSGOE016");
		CommonErrorPageException e = new CommonErrorPageException(message);
		e.getErrorMessageList().add(message);
		throw e;
	}	
	
	/**
	 * サーバ接続判定
	 * @param ipAddress
	 * @return　接続OK：True 接続不能：False
	 */
	public static boolean ping(String ipAddress) {
        int  timeOut =  2000 ;
        boolean status = false;
        try {
        	status = ping(ipAddress, 3 , timeOut);
        }catch(Exception e){
        	status = false;
        }
        return status;
    }
	/**
	 * サーバ接続確認
	 * @param ipAddress
	 * @param pingTimes
	 * @param timeOut
	 * @return
	 */
	public static boolean ping(String ipAddress, int pingTimes, int timeOut) {  
        BufferedReader in = null;  
        Runtime r = Runtime.getRuntime();
        String pingCommand = "ping " + ipAddress + " -n " + pingTimes + " -w " + timeOut;  
        try {
            Process p = r.exec(pingCommand);   
            if (p == null) {    
                return false;   
            }
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            int connectedCount = 0;   
            String line = null;   
            while ((line = in.readLine()) != null) {    
                connectedCount += getCheckResult(line);   
            }
            return connectedCount == pingTimes;  
        } catch (Exception ex) {   
            return false;  
        } finally {   
            try {    
                in.close();   
            } catch (Exception e) {    
            }  
        }
    }

	/**
	 * ping状態を確認
	 * @param line
	 * @return
	 */
    private static int getCheckResult(String line) {
        Pattern pattern = Pattern.compile("(\\d+ms)(\\s+)(TTL=\\d+)", Pattern.CASE_INSENSITIVE);  
        Matcher matcher = pattern.matcher(line);  
        while (matcher.find()) {
            return 1;
        }
        return 0; 
    }
    
    /**
     * ファイル削除
     * 指定したフォルダに期限を超えた場合、ファイルを削除
     * @param path
     * @param nDays
     */
    public static void deleteFilePeriod(String path, int nDays){
    	try{
    		File dir = new File(path);
    		if (!dir.isDirectory()) {
    			return;
    		}
    		File[] files = dir.listFiles();
    		// その出力
    		for (int i = 0; i < files.length; i++) {
    			File file = files[i];
    			if (fileExpiredCheck(file, nDays)){
    				file.delete();
    			}
    		}
		} catch (Exception e) {
			throw new LocalRuntimeException("ファイル処理にて、エラーが発生しました。", e);
		}
    }
    
    /**
     * ファイルの最後更新時間は指定日付を超えた場合、期限切れを判定する。
     * @param file
     * @param period
     * @return
     */
	private static boolean fileExpiredCheck(File file, int nDays) {
		// ファイル更新日付
		Date lastModifiedDate = new Date(file.lastModified());
		String lastModifiedDateStr = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(lastModifiedDate);

		// 指定の日付（１日をミリ秒で計算）
		long oneDayTime = 24L * 60L * 60L * 1000L;
		long periodTime = oneDayTime * Math.abs(nDays);
		Date designatedDate = new Date(System.currentTimeMillis() - periodTime);
		String designatedDateStr = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(designatedDate);
		if (nDays > 0) {
			if (lastModifiedDateStr.compareTo(designatedDateStr) < 0) {
				return false;
			}
		} else {
			if (lastModifiedDateStr.compareTo(designatedDateStr) >= 0) {
				return false;
			}
		}
		return true;
	}
	
    /**
     * フォルダコピー用関数
     * @param folderFrom コピー元フォルダ対象
     * @param folderTo   コピー目標フォルダ対象
     */
    public static void folderCopy(File folderFrom, File folderTo) throws IOException {
    	try{
    		// コピー元フォルダ存在するかどか判定する
            if (!folderFrom.isDirectory()) {
                // もし、フォルダ存在しない場合、「false」戻る。
                return;
            }
            // コピー目標フォルダ存在するか判定する。 
            if (!folderTo.exists()) {
                // もし、目標フォルダ存在しない場合、フォルダ新規する。
                folderTo.mkdir();
            }
            // コピー元フォルダの中に全部ファイル取得
            File[] fileFromArray = folderFrom.listFiles();
            File copyTo = null;
            for(File copyFrom : fileFromArray) {
                // 目標フォルダとファイルのパース取得
                copyTo = new File(folderTo.getAbsolutePath() + "\\" + copyFrom.getName());
                if (copyFrom.isDirectory()) {
                    // フォルダコピー行こう
                    folderCopy(copyFrom, copyTo);
                } else {
                    // ファイルコピー行こう
                	copyFile(copyFrom, copyTo);
                }
            }
    	} catch (Exception e) {
			throw new LocalRuntimeException("ファイル処理にて、エラーが発生しました。", e);
		}
    }

    /**
     * ファイルコピー用関数
     * @param copyFrom コピー元ファイル
     * @param copyTo   コピー目標ファイル
     */
    public static void copyFile(File copyFrom, File copyTo) throws IOException {
    	try{
        	// コピー元ファイル存在するか判定する。
            if (!copyFrom.exists()) {
                // もし、コピー元ファイル存在しない場合、「false」戻る。
                return;
            }
            // コピー元ファイル対象より、ファイル読む対象定義する。
            FileInputStream fis = new FileInputStream(copyFrom);
            // コピー目標ファイル対象より、ファイル書く対象定義する。
            FileOutputStream fos = new FileOutputStream(copyTo);
            // 読むバッファ定義する。
            byte[] readByte = new byte[1024*5];
            // 読むバッファにデータ入る
            int getByteLength = fis.read(readByte);
            while (-1 != getByteLength) {
                // 目標ファイルにデータを書く
                fos.write(readByte, 0, getByteLength);
                // バッファクリア
                fos.flush();
                // 読むバッファにデータ入る
                getByteLength = fis.read(readByte);
            }
            fis.close();
            fos.close();
    	} catch (Exception e) {
			throw new LocalRuntimeException("ファイル処理にて、エラーが発生しました。", e);
		}    	
    }
    
	/**
	 *  ファイルまたはフォルダコピー
	 *  @param from
	 *  @param to
	 *  @param fromFileName
	 *  @param toFileName
	 */
	public static void fileCopyForStatic(String from, String to, String fromFileName, String toFileName) throws Exception {
		try {
			File copyFromFile = new File(from + File.separator + fromFileName);
			if (copyFromFile.exists()) {
				File copyToFile = new File(to + File.separator + toFileName);
				if (copyFromFile.isDirectory()) {
					folderCopy(copyFromFile, copyToFile);
				} else {
					copyFile(copyFromFile, copyToFile);
				}				
			}
		} catch (Exception e){
			throw new LocalRuntimeException("ファイル処理にて、エラーが発生しました。", e);			
		}
	}
	
	/**
	 * 静的コンテンツテンプレートフォルダにコピー
	 * @param user
	 */
	public static void copyStaticForTemplate(String from, String to, String fromFileName, String toFileName) throws Exception {
		try{
			File file = new File(from + File.separator + fromFileName);
	        if (file.isFile()) {
	            copyFile(from, to, fromFileName, toFileName);
	        } else if (file.isDirectory()) {
	        	File file1 = new File(to+ File.separator + toFileName);
	    		if(!file1.isDirectory()) {
	    			file1.mkdirs();
	    		}
	            File[] files = file.listFiles();
	            String formFolderFileName = "";
				String fileSuffix = "";
				String toFolderFileName = "";
	            for (int i = 0; i < files.length; i++) {
	            	formFolderFileName  = files[i].getName();
	            	if (formFolderFileName.indexOf(fromFileName) >= 0) {
						fileSuffix = formFolderFileName.substring(formFolderFileName.lastIndexOf("."));
	            		toFolderFileName = toFileName + fileSuffix;
	            	} else {
	            		toFolderFileName = formFolderFileName;
	            	}
	            	copyStaticForTemplate((from + File.separator + fromFileName), (to + File.separator + toFileName), formFolderFileName, toFolderFileName);
	            }
	        }		
		} catch (Exception e){
			throw new LocalRuntimeException("ファイル処理にて、エラーが発生しました。", e);			
		}
    }	

	/**
	 * file to byte
	 * @param file
	 */
	public static byte[] fileToByte(File file) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
		
		byte[] bytes = new byte[10240];
		int c;
		while ((c = fis.read(bytes)) != -1)
			out.write(bytes, 0, c);
		fis.close();
		out.close();
		
		return out.toByteArray();
	}
	
	/**
	 * フォルダーの全てファールパースを取得
	 * @param strPath
	 * @param filelist
	 * @return
	 */
	public static List<File> getFileList(String strPath, List<File> filelist) {
		File dir = new File(strPath);
		File[] files = dir.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) { 
					getFileList(files[i].getAbsolutePath(), filelist); 
				} else {
					filelist.add(files[i]);
				}
			}
		}
		return filelist;
	}
}