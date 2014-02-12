package com.team6.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;



/**
 * Handles all account related requests.
 */
@Controller
@EnableMongoRepositories
public class AccountController {

	@Autowired
	private AccountService service;

	@RequestMapping(value = "/Signup", method = RequestMethod.POST)
	public String processSignup(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("username") String username,
			@RequestParam("email") String email, @RequestParam("dob") String dob, Model model)
	{
		if(service.findByUsername(username) == null)
		{
			service.addUser(new User(firstName, lastName, username, email, dob));
			User user = service.findByUsername(username);
			
			System.out.println("Here's the user that just signed up: " + user.getFirstName() 
				+ " " + user.getLastName() + " " + user.getUsername() + " " + user.getEmail() + " " + user.getDOB());

			return "login";
		}
		else
		{
			model.addAttribute("userexists", "<script>alert('Username already exists, try a different one.');</script>");
			return "home";
		}
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginView() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("username") String username, 
			@RequestParam("password") String password, Model model) {
		
		return "login";
	}
		
}

