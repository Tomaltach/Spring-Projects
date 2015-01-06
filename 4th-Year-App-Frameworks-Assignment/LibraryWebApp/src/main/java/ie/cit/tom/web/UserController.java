package ie.cit.tom.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import ie.cit.tom.entity.User;
import ie.cit.tom.service.UserService;

@Controller
public class UserController {
	@SuppressWarnings("unused")
	private UserService userService;
	private final String REST_URL = "http://localhost:8080/LibraryWebApp";

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	@RequestMapping(value="/user/{username}", method=RequestMethod.GET)
	public String showUser(@PathVariable("username") String username, Model model) {
		User user = new RestTemplate().getForObject(REST_URL + "/users/{username}", User.class, username);
		model.addAttribute("user", user);
		return "profilePage";
	}
}