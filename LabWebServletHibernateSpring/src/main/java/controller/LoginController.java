package controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.CustomerBean;
import model.CustomerService;

@Controller
@SessionAttributes("user")
public class LoginController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ApplicationContext context;
	
	@RequestMapping("/secure/login.controller")
	public String method(String username,String password, Model model, Locale locale) {
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("errors", errors);
		
		if(username==null || username.length()==0) {
			String error=context.getMessage("login.username.required", null, locale);
			errors.put("username", error);
		}
		if(password==null || password.length()==0) {
			String error=context.getMessage("login.password.required", null, locale);
			errors.put("password", error);
		}
		
		if(errors!=null && !errors.isEmpty()) {
			
			return "login.errors";
		}
		//呼叫model
		CustomerBean bean = customerService.login(username, password);

				
		//根據model執行結果，導向view
				if(bean==null) {
					errors.put("password", "Login failed, please try again.");
					return "login.errors";
					
				} else {
					
					model.addAttribute("user", bean);
					return "login.success";
					
				}


	}
}
