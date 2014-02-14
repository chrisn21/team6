package com.team6.app.apirequest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.team6.app.Constants;
import com.team6.app.quiz.Question;

/**
 * Handles all account related requests.
 */
@Controller
@EnableMongoRepositories
public class RequestAPIController {

	@Autowired
	private RequestAPIService service;
	
	
	@RequestMapping(value = "/requestAPI", method = RequestMethod.GET)
	public ModelAndView requestQuestions(Model model, HttpServletRequest request) throws UnirestException, JSONException {
		ModelAndView mv = new ModelAndView(Constants.QUIZ_PATH_FILE);
		HttpResponse<JsonNode> apirequest = Unirest.get("https://privnio-trivia.p.mashape.com/exec?category=animal&v=1&method=getQuestions")
				  .header("X-Mashape-Authorization", "NQ5wMXaXDPSuhDtKZi6SgK7JjvWWdAWi")
				  .asJson();
		List<Question> lq = service.parseQuestions(apirequest.getBody());
		mv.addObject("questions", lq);
		return mv;
	}
}