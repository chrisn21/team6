package com.team6.app.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuizController {

	private static final String QUIZ_PAGE_PATH 			= "quiz/quiz";
	private static final String QUIZZES_PAGE_PATH 		= "quiz/quizzes";
	private static final String QUIZ_CREATE_PAGE_PATH 	= "quiz/create";
	
	@Autowired
	private QuizService quizService;
	
	@RequestMapping(value = "/quizzes", method = RequestMethod.GET)
	public ModelAndView showQuizzes() {
		ModelAndView mv = new ModelAndView(QUIZZES_PAGE_PATH);
		mv.addObject("categories", quizService.getCategories());
		mv.addObject("quizzes", quizService.getQuizzes());
		return mv;
	}
	
	@RequestMapping(value = "/quiz/{quizId}", method = RequestMethod.GET)
	public ModelAndView showQuiz(@PathVariable String quizId) {
		ModelAndView mv = new ModelAndView(QUIZ_PAGE_PATH);
		Quiz quiz = quizService.getQuiz(quizId);
		mv.addObject("quiz", quiz);
		mv.addObject("questions", quizService.getQuizQuestions(quiz.getId()));
		mv.addObject("creator", quizService.getCreator(quiz.getCreatorId()));
		return mv;
	}
	
	@RequestMapping(value = "/quiz/create", method = RequestMethod.GET)
	public ModelAndView showCreateQuiz() {
		ModelAndView mv = new ModelAndView(QUIZ_CREATE_PAGE_PATH);
		
		return mv;
	}
	
	@RequestMapping(value = "/quiz/create", method = RequestMethod.POST)
	public ModelAndView createQuiz() {
		ModelAndView mv = new ModelAndView(QUIZ_CREATE_PAGE_PATH);
		
		return mv;
	}
	
}
