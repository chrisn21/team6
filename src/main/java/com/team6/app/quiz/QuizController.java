package com.team6.app.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuizController {

	private static final String QUIZ_PAGE_PATH = "quiz/quiz";
	private static final String QUIZZES_PAGE_PATH = "quiz/quizzes";
	
	@Autowired
	private QuizService quizService;
	
	@RequestMapping(value = "/quizzes", method = RequestMethod.GET)
	public ModelAndView getCategories() {
		ModelAndView mv = new ModelAndView(QUIZZES_PAGE_PATH);
		mv.addObject("categories", quizService.getCategories());
		return mv;
	}
	
	@RequestMapping(value = "/quiz/{quizId}", method = RequestMethod.GET)
	public ModelAndView getGame(@PathVariable String quizId) {
		ModelAndView mv = new ModelAndView(QUIZ_PAGE_PATH);
		mv.addObject("creator", quizService.getCreator(quizId));
		mv.addObject("game", quizService.getQuiz(quizId));
		return mv;
	}
	
}
