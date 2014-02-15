package com.team6.app.apirequest;


import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.team6.app.Constants;

/**
 * Handles all account related requests.
 */
@Controller
@EnableMongoRepositories
public class RequestAPIController {

	@Autowired
	private RequestAPIService service;
	
	
	@RequestMapping(value = "/requestAPI", method = RequestMethod.GET)
	public ModelAndView requestQuestions(@RequestParam("category") String category, Model model, HttpServletRequest request) throws UnirestException, JSONException {
		ModelAndView mv = new ModelAndView(Constants.QUIZ_PATH_FILE);
		String apicategory = "https://privnio-trivia.p.mashape.com/exec?category=" + category + "&v=1&method=getQuestions";
		HttpResponse<JsonNode> apirequest = Unirest.get(apicategory)
				  .header("X-Mashape-Authorization", "NQ5wMXaXDPSuhDtKZi6SgK7JjvWWdAWi")
				  .asJson();
		service.parseQuestions(apirequest.getBody());
		
		return mv;
	}
}