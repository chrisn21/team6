package com.team6.app.quiz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.team6.app.Constants;

@Controller
public class QuizController {
	
	@Autowired
	private QuizService quizService;
	
	@Autowired
	private MongoOperations mo;
	
	@RequestMapping(value = "/quiz", method = RequestMethod.GET)
	public ModelAndView showQuizzes() {
		ModelAndView mv = new ModelAndView("quiz/quizzes");
		mv.addObject("categories", quizService.getCategories());
		mv.addObject("quizzes", quizService.getQuizzes());
		return mv;
	}
	
	@RequestMapping(value = "/quiz/{quizId}", method = RequestMethod.GET)
	public ModelAndView showQuiz(@PathVariable String quizId) {
		ModelAndView mv = new ModelAndView("quiz/quiz");
		Quiz quiz = quizService.getQuiz(quizId);
		mv.addObject("quiz", quiz);
		mv.addObject("questions", quizService.getQuizQuestions(quiz.getId()));
		mv.addObject("creator", mo.findById(quiz.getCreatorId(), 
				com.team6.app.User.class, Constants.USERS_COLLECTION_NAME));
		return mv;
	}
	
	@RequestMapping(value = "/quiz/{quizId}", method = RequestMethod.POST)
	public ModelAndView evalQuiz(HttpServletRequest req,
			@PathVariable String quizId,
			@RequestParam("answers") String answers) {
		
		ModelAndView mv;
		HttpSession sesh = req.getSession(false);
		
		if (sesh == null) {
			mv = new ModelAndView("404");
		} else {
			String userId = (String) sesh.getAttribute("userid");
			mv = new ModelAndView("quiz/quiz-result");
			mv.addObject("userId", userId);
		}
		return mv;
	}
	
	@RequestMapping(value = "/quiz/create", method = RequestMethod.GET)
	public ModelAndView showCreateQuiz() {
		ModelAndView mv = new ModelAndView("quiz/create");
		
		return mv;
	}
	
	@RequestMapping(value = "/quiz/create", method = RequestMethod.POST)
	public ModelAndView createQuiz() {
		ModelAndView mv = new ModelAndView("quiz/create");
		
		return mv;
	}
	
}
