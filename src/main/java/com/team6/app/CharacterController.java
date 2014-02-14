package com.team6.app;

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
public class CharacterController {

	@Autowired
	private CharacterService service;

	@Autowired
	private UserRepository repository;
	
	//Retrieve a character's info through querying for associated user
	@RequestMapping(value = "/MyCharacter", method = RequestMethod.GET)
	public ModelAndView characterInfo(Model model, HttpServletRequest request) {
		String userid = request.getSession(true).getAttribute("userid").toString();
    	User user = service.getCharacter(userid);
    	Character c = user.getCharacter();
    	
    	model.addAttribute("character", c);
    	return new ModelAndView(Constants.CHAR_PROFILE_PATH_FILE);
	}
	
	//Add experience to character and revise appropriate fields
	@RequestMapping(value = "/adjustStats", method = RequestMethod.GET)
	public ModelAndView adjustCharacterStats(@RequestParam("experience") String experience, Model model, HttpServletRequest request) {
		String userid = request.getSession(true).getAttribute("userid").toString();
		service.changeStats(userid, Integer.parseInt(experience));
		return new ModelAndView(Constants.HOME_PATH_FILE);
	}
}

