package com.team6.app;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



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
	
	
	@RequestMapping(value = "/MyCharacter", method = RequestMethod.GET)
	public String characterInfo(Model model, HttpServletRequest request) {
		String userid = request.getSession(true).getAttribute("userid").toString();
		System.out.println(userid);
    	User u = service.getCharacter(userid);
    	System.out.println(u.getCharacter().getCharacterName());
    	return "home";
	}
		
}

