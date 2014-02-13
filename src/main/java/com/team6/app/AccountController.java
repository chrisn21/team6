package com.team6.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles all account related requests.
 */
@Controller
@EnableMongoRepositories
public class AccountController {

	@Autowired
	private AccountService service;

	@RequestMapping(value = "/Signup", method = RequestMethod.POST)
	public ModelAndView processSignup(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("email") String email, 
			@RequestParam("dob") String dob, @RequestParam("character") String character, 
			@RequestParam("characterName") String characterName, Model model,
			HttpServletRequest request)
	{
		System.out.println(character);
		if(service.findByUsername(username) == null)
		{
			Character pet = new Character(character, characterName);
			service.addUser(new User(firstName, lastName, username, password, email, dob, pet));
			User user = service.findByUsername(username);
			
			System.out.println("Here's the user that just signed up: " + user.getFirstName() 
				+ " " + user.getLastName() + " " + user.getUsername() + " " + user.getPassword() + " " + user.getEmail() + " " + user.getDOB());
			
			//System.out.println(user.getId());
			request.getSession(true).setAttribute("userid", user.getId());
			return "login";
		}
		else
		{
			model.addAttribute("userexists", "<script>alert('Username already exists, try a different one.');</script>");
			return new ModelAndView("home");
		}
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginView() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("username") String username, 
			@RequestParam("password") String password, Model model, HttpServletRequest request) {
		if(service.verifyLogin(username, password))
		{
	    	User u = service.findByUsername(username);
	    	request.getSession(true).setAttribute("userid", u.getId());
			return "home";
		}
		else
			return "login";
	}
	

	@RequestMapping(value = "/updateQpts", method = RequestMethod.GET)
	public String questionAnswered(Model model, HttpServletRequest request) {
		String userid = request.getSession(true).getAttribute("userid").toString();
    	service.updateQuestionPoints(userid, request.getParameter("correct"));
    	return "home";
	}
		
}

