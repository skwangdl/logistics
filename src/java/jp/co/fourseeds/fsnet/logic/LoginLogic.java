package jp.co.fourseeds.fsnet.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.dao.LoginDao;

@Component
public class LoginLogic {
	@Autowired
	private LoginDao loginDao;
	
	public void upA(String password) {
		loginDao.updateTest(password);
	}

	public void upB(String password) throws Exception {
		if(1==1)throw new Exception("aaa");
		loginDao.updateTest2(password);
	}

	public List<LoginUserBean> getListUser() {
		return loginDao.getListUser();
	}
}
