package com.team6.app.quiz;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private MongoOperations db;
	
	@Override
	public Collection<Category> getCategories() {
		return db.findAll(Category.class, "categories");
	}

	@Override
	public Quiz getQuiz(String gameId) {
		return db.findOne(Query.query(
				Criteria.where("_id").is(gameId)),
				Quiz.class,
				"quizzes");
	}

	@Override
	public String getCreator(String gameId) {
		return null;
	}

}
