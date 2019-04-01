package jp.co.plc.pl1.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Login")
public class LoginController {

	@RequestMapping("/toLogin")
	public String toLogin(){
		return "login/login";
	}
}
