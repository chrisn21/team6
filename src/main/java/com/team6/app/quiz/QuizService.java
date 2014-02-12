package com.team6.app.quiz;

import java.util.Collection;

public interface QuizService {

	static final String CATEGORIES_COLLECTION_NAME = "categories";
	static final String QUIZZES_COLLECTION_NAME = "quizzes";
	
	public Collection<Category> getCategories();
	public Quiz getQuiz(String quizId);
	public String getCreator(String quizId);
	
}
