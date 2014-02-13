package com.team6.app.quiz;

import java.util.Collection;
import com.team6.app.User;

public interface QuizService {
	
	public Collection<Category> getCategories();
	public Collection<Quiz> getQuizzes();
	public Quiz getQuiz(String quizId);
	public Collection<Question> getQuizQuestions(String quizId);
	public User getCreator(String creatorId);
	
}
