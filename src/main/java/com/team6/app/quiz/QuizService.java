package com.team6.app.quiz;

import java.util.List;

public interface QuizService {
	
	public List<Category> getCategories();
	public List<Quiz> getQuizzes();
	public Quiz getQuiz(String quizId);
	public List<Boolean> getQuizFeedback(Quiz quiz, List<String> answers);
	public void createQuiz(String name, List<String> questions, 
			List<String> answers);
	
}
