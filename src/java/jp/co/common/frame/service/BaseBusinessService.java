package jp.co.common.frame.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.common.ConstantContainer;
import jp.co.common.frame.exception.BusinessServiceException;
import jp.co.common.frame.util.prop.MessagePropertyUtil;

/**
 * 
 * プロジェクトのビジネスロジックの具体な実現<br>
 * 
 * 以下の機能をサポートする。<br>
 * 
 * <ul>
 *  <li>業務メッセージ追加</li>
 *  <li>業務メッセージを取得</li>
 *  <li>業務メッセージThrow</li>
 * </ul>
 * 
 * @author  NTS
 * @version 1.0
 */
public class BaseBusinessService  {
	
	//業務異常クラス
	protected BusinessServiceException businessServiceEx = new BusinessServiceException();;
	
	/**
	 * @return the userInfoBean
	 */
	public LoginUserBean getLoginUserBean() {
		//セッションからユーザー情報を取得
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
        HttpSession session = request.getSession();  
        return (LoginUserBean)session.getAttribute(ConstantContainer.LOGIN_USER_KEY);
	}
	
	/**
	 * 業務メッセージ追加
	 * @param messageID
	 */
	public void addBusinessMessage(String fieldID, String messageID, String replaceValue[]) {		
		businessServiceEx.addErrorID(fieldID,messageID, replaceValue);
	}
	
	/**
	 * 業務メッセージ追加
	 * @param messageID
	 */
	public void addBusinessMessage(String messageID, String replaceValue[]) {		
		this.addBusinessMessage("",messageID, replaceValue);
	}

	/**
	 * 業務メッセージ追加
	 * @param messageID
	 */
	public void addBusinessMessage(String fieldID, String messageID) {		
		businessServiceEx.addErrorID(fieldID, messageID);
	}

	/**
	 * 業務メッセージ追加
	 * @param messageID
	 */
	public void addBusinessMessage(String messageID) {		
		addBusinessMessage("", messageID);
	}

	/**
	 * 業務メッセージを設定
	 * @param businessMessageList
	 */
	public void setBusinessMessageList(List<String> businessMessageList) {		
		businessServiceEx.addErrorID(businessMessageList);
	}

	public BusinessServiceException getBusinessServiceException() {		
		return businessServiceEx;
	}
	
	public boolean isThrowBusinsessException(){
		if(this.businessServiceEx.getErrorIdList() != null 
				&& this.businessServiceEx.getErrorIdList().size() > 0){
			return true;
		}else{
			return false;
		}
	}
	
	public void throwBusinsessExcepton()throws BusinessServiceException{
		if(isThrowBusinsessException()){
			throw businessServiceEx;
		}
	}
	
	public String getErrorMessage(String errorId, String replaceValue[]) {
		String ret = MessagePropertyUtil.getStringProperty(errorId);
		for (int i=0; i<replaceValue.length; i++) {
			ret = ret.replaceAll("\\{"+i+"\\}", replaceValue[i]);
		}
		return ret;
	}
}
