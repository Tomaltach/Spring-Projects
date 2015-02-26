package com.tom.myadspace.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping(value="/login")
	public String login(Model model) {
		return "login";
	}	
	@RequestMapping(value="/loginfail")
	public String loginFail(Model model) {
		model.addAttribute("errorMsg","Login Failed!");
		return "login";
	}	
	@RequestMapping(value="/logout")
	public String logout(Model model) {
		return "login";
	}	
	@RequestMapping(value="/accessdenied")
	public String accessDenied(Model model) {
		return "accessdenied";
	}	
}
