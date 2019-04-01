package jp.co.common.frame.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import jp.co.common.frame.util.DateUtil;
/**
 *  業務メッセージ異常
 * <pre>
   *  　 業務メッセージが発生する場合、この異常をThrow
 *  以下の機能をサポートする。
 * </pre>
 * <ul>
 *   <li>業務メッセージを追加</li>
 * </ul>
 * @author NTS
 * @version 1.0 
 */
public class BusinessServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/** エラーフィールドID */
	private List<String> errorField;
	/** エラーID */
	private List<String> errorID;
	/** エラーメッセージ */
	private List<String> errorMessageList;
	
	public BusinessServiceException() {
		super();
	}

	public BusinessServiceException(String errorID) {
		super(errorID);
		this.addErrorID("", errorID);
	}

	public BusinessServiceException(String errorID, String replaceValue[]) {
		super(errorID);
		this.addErrorID("", errorID, replaceValue);
	}


	public void addErrorID(String errorFieldId, String errorId){
		this.addErrorID(errorFieldId, errorId, new String[] {});
	}	
	
	public void addErrorID(String errorFieldId, String errorId, String replaceValue[]){
		if(this.errorField == null){
			this.errorField = new ArrayList<String>();
		}
		if(this.errorID == null)
			this.errorID = new ArrayList<String>();
		if(this.errorMessageList == null) {
			this.errorMessageList = new ArrayList<String>();
		}
		if(!errorID.contains(errorId)){
			this.errorField.add(errorFieldId);
			this.errorID.add(errorId);
			this.errorMessageList.add(getErrorMessage(errorId, replaceValue));
		}
	}
	
	public String getErrorMessage(String errorId, String replaceValue[]) {		
		ResourceBundle bundle = ResourceBundle.getBundle("message",Locale.JAPAN);
		String ret = bundle.getString(errorId);
		for (int i=0; i<replaceValue.length; i++) {
			ret = ret.replaceAll("\\{"+i+"\\}", replaceValue[i]);
		}
		return ret;
	}
	
	public void addErrorID(List<String> errorIds){
		for(String strId : errorIds){
			this.addErrorID("", strId);
		}		
	}
	
	public List<String> getErrorIdList(){
		return this.errorID;
	}
	
	public List<String> getErrorMessageList() {
		return errorMessageList;
	}

	public void setErrorMessageList(List<String> errorMessageList) {
		this.errorMessageList = errorMessageList;
	}

	public List<String> getErrorFieldList(){
		return this.errorField;
	}
}
