package com.team6.app.quiz;

import java.util.Collection;

public interface QuizService {
	
	public Collection<Category> getCategories();
	public Quiz getQuiz(String quizId);
	public String getCreator(String quizId);
	
}
