package com.team6.app.quiz;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.team6.app.AccountService;
import com.team6.app.CharacterService;
import com.team6.app.Constants;
import com.team6.app.User;

@Controller
public class QuizController {
	
	@Autowired
	private QuizService quizService;
	
	@Autowired
	private AccountService accService;
	
	@Autowired
	private CharacterService charService;
	
	@RequestMapping(value = "/quiz", method = RequestMethod.GET)
	public ModelAndView showQuizzes() {
		ModelAndView mv = new ModelAndView(Constants.QUIZZES_PATH_FILE);
		mv.addObject("categories", quizService.getCategories());
		mv.addObject("quizzes", quizService.getQuizzes());
		return mv;
	}
	
	@RequestMapping(value = "/quiz/{quizId}", method = RequestMethod.GET)
	public ModelAndView showQuiz(HttpServletRequest req,
			@PathVariable String quizId) {
		HttpSession sesh = req.getSession(false);
		if (sesh == null) {
			// Session invalid
			return new ModelAndView(Constants.NOT_FOUND_PATH_FILE);
		}
		
		String userId = (String) sesh.getAttribute("userid");
		if (userId == null) {
			// Session doesn't contain logged in user
			return new ModelAndView(Constants.NOT_FOUND_PATH_FILE);
		}
		
		User user = accService.findById(userId);
		if (user == null) {
			// User doesn't exist
			return new ModelAndView(Constants.NOT_FOUND_PATH_FILE);
		}
		
		ModelAndView mv = new ModelAndView(Constants.QUIZ_PATH_FILE);
		Quiz quiz = quizService.getQuiz(quizId);
		mv.addObject("quiz", quiz);
		mv.addObject("questions", quiz.getQuestions());
		mv.addObject("creator", accService.findById(quiz.getCreatorId()));
		return mv;
	}
	
	@RequestMapping(value = "/quiz/{quizId}", method = RequestMethod.POST)
	public ModelAndView evalQuiz(HttpServletRequest req,
			@PathVariable String quizId,
			@RequestParam(value = "answers[]") String[] answers) {
		HttpSession sesh = req.getSession(false);
		if (sesh == null) {
			// Session invalid
			return new ModelAndView(Constants.NOT_FOUND_PATH_FILE);
		}
		
		String userId = (String) sesh.getAttribute("userid");
		if (userId == null) {
			// Session doesn't contain logged in user
			return new ModelAndView(Constants.NOT_FOUND_PATH_FILE);
		}
		
		Quiz quiz = quizService.getQuiz(quizId);
		if (quiz == null) {
			// Quiz doesn't exist
			return new ModelAndView(Constants.NOT_FOUND_PATH_FILE);
		}
		
		User user = accService.findById(userId);
		if (user == null) {
			// User doesn't exist
			return new ModelAndView(Constants.NOT_FOUND_PATH_FILE);
		}
		
		List<String> answersList = Arrays.asList(answers);
		List<Boolean> feedback = 
				quizService.getQuizFeedback(quiz, answersList);
		if (feedback == null) {
			// Incompatible question/answer list lengths detected
			return new ModelAndView(Constants.NOT_FOUND_PATH_FILE);
		}
		
		Integer expGained = charService.changeStats(userId, feedback, quiz.getDifficulty());
		
		ModelAndView mv = new ModelAndView(Constants.QUIZ_RESULT_PATH_FILE);
		mv.addObject("user", user);
		mv.addObject("quiz", quiz);
		mv.addObject("questions", quiz.getQuestions());
		mv.addObject("answers", answersList);
		mv.addObject("feedback", feedback);
		mv.addObject("expGained", expGained);
		return mv;
	}
	
	@RequestMapping(value = "/quiz/create", method = RequestMethod.GET)
	public ModelAndView showCreateQuiz() {
		ModelAndView mv = new ModelAndView(Constants.QUIZ_CREATE_PATH_FILE);
		
		return mv;
	}
	
	@RequestMapping(value = "/quiz/create", method = RequestMethod.POST)
	public ModelAndView createQuiz(
			@RequestParam(value = "name") String name,
			@RequestParam(value = "questions[]") String[] questions,
			@RequestParam(value = "answers[]") String[] answers) {
		quizService.createQuiz(name, Arrays.asList(questions), Arrays.asList(answers));
//		ModelAndView mv = new ModelAndView(Constants.QUIZ_CREATE_PATH_FILE);
//		return mv;
		return new ModelAndView("redirect:/");
	}
	
}
