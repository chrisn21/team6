package com.team6.app;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles all account related requests.
 */

@Controller
@EnableMongoRepositories
public class AccountController {

	@Autowired
	private AccountService service;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signupView() {
		return new ModelAndView(Constants.SIGNUP_PATH_FILE);
	}

	@RequestMapping(value = "/processSignup", method = RequestMethod.POST)
	public ModelAndView processSignup(
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("email") String email, 
			@RequestParam("dob") String dob, @RequestParam("character") String character, 
			@RequestParam("characterName") String characterName, Model model,
			HttpServletRequest request)
	{
		if(service.findByUsername(username) == null)
		{
			Character pet = new Character(character, characterName);
			service.addUser(new User(firstName, lastName, username, password, email, dob, pet));
			User user = service.findByUsername(username);
			request.getSession(true).setAttribute("userid", user.getId());
			System.out.println("Here's the user that just signed up: " + user.getFirstName() 
					+ " " + user.getLastName() + " " + user.getUsername() + " " + user.getPassword() + " " + user.getEmail() + " " + user.getDOB());
			return new ModelAndView(Constants.LOGIN_PATH_FILE);
		}
		else
		{
			request.setAttribute("userexists", "<script>alert('Username already exists, try a different one.');</script>");
			return new ModelAndView(Constants.SIGNUP_PATH_FILE);
		}
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginView() {
		return new ModelAndView(Constants.LOGIN_PATH_FILE);
	}
	
	@RequestMapping(value = "/verifyLogin", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("username") String username, 
			@RequestParam("password") String password, Model model, HttpServletRequest request) {
		if(service.verifyLogin(username, password))
		{
    	User u = service.findByUsername(username);
    	request.getSession(true).setAttribute("loggedin", true);
    	request.getSession(true).setAttribute("userid", u.getId());
			return new ModelAndView("redirect:/");
		}
		else
			return new ModelAndView("redirect:login");
	}
	
	@RequestMapping(value = "/leaderboard", method = RequestMethod.GET)
	public ModelAndView queryLeaderboard(Model model, HttpServletRequest request)
	{
		List<User> leaderboard = service.retrieveLeaderboard();
		request.setAttribute("leaderboard", leaderboard);
		return new ModelAndView(Constants.LEADERBOARD_PATH_FILE);
	}
	
	/*
	@RequestMapping(value = "/updatepts", method = RequestMethod.GET)
	public String questionAnswered(Model model, @RequestParam("correct") String correct, HttpServletRequest request) {
		String userid = request.getSession(true).getAttribute("userid").toString();
    	service.updateQuestionPoints(userid, correct);
    	return "/adjustStats";
	}
	*/
}

