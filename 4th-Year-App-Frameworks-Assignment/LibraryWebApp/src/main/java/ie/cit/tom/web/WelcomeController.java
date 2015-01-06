package ie.cit.tom.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

	@RequestMapping(value="/welcome")
	public String welcome() {
		return "welcome";
	}
	@RequestMapping(value="/public")
	public String publicView() {
		return "public";
	}
	@RequestMapping(value="/test/mytest")
	public String testView() {
		return "test/mytest";
	}
}
