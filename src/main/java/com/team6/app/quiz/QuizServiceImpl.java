package com.team6.app.quiz;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.team6.app.Constants;
import com.team6.app.User;
import com.team6.app.UserRepository;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private MongoOperations db;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public Collection<Category> getCategories() {
		return db.findAll(Category.class, Constants.CATEGORIES_COLLECTION_NAME);
	}

	@Override
	public Collection<Quiz> getQuizzes() {
		return db.findAll(Quiz.class, Constants.QUIZZES_COLLECTION_NAME);
	}

	@Override
	public Quiz getQuiz(String quizId) {
		return db.findById(quizId, 
				Quiz.class, 
				Constants.QUIZZES_COLLECTION_NAME);
	}

	@Override
	public Collection<Question> getQuizQuestions(String quizId) {
		Quiz q = getQuiz(quizId);
		Collection<String> questionIds = q.getQuestionIds();
		return db.find(Query.query(
				Criteria.where("_id").in(questionIds)),
				Question.class,
				Constants.QUESTIONS_COLLECTION_NAME);
	}

	@Override
	public User getCreator(String creatorId) {
		return userRepo.findById(creatorId);
	}

}
