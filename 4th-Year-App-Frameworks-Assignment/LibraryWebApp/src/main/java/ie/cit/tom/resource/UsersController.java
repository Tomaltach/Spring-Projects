package ie.cit.tom.resource;

import ie.cit.tom.entity.User;
import ie.cit.tom.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class UsersController {
	private UserService userService;
	
	@Autowired
	public UsersController(UserService userService) {
		this.userService = userService;
	}	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public @ResponseBody User getQuery(@PathVariable("username") String username) {
		User user = userService.getByName(username);	
		return user;
	}
	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	public @ResponseBody User getUser(@PathVariable("username") String username) {	
		User user = userService.getByName(username);	
		return user;
	}
}