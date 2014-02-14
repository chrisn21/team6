package com.team6.app.quiz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.team6.app.Constants;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private MongoOperations db;
	
	@Override
	public List<Category> getCategories() {
		return db.findAll(Category.class, Constants.CATEGORIES_COLLECTION_NAME);
	}

	@Override
	public List<Quiz> getQuizzes() {
		return db.findAll(Quiz.class, Constants.QUIZZES_COLLECTION_NAME);
	}

	@Override
	public Quiz getQuiz(String quizId) {
		return db.findById(quizId, 
				Quiz.class, 
				Constants.QUIZZES_COLLECTION_NAME);
	}

	@Override
	public List<Boolean> getQuizFeedback(Quiz quiz, List<String> answers) {
		List<Question> questions = quiz.getQuestions();
		if (questions.size() != answers.size()) {
			return null;
		}
		
		List<Boolean> feedback = new ArrayList<Boolean>(answers.size());
		for (int i = 0; i < questions.size(); i++) {
			Boolean correct = questions.get(i).getAnswer().equalsIgnoreCase(answers.get(i));
			feedback.add(i, correct);
		}
		return feedback;
	}

	@Override
	public void createQuiz(String name, List<String> questions,
			List<String> answers) {
		if (questions.size() != answers.size()) {
			System.out.println("FAILED. QUESTIONS ANSWERS LIST LENGTH MISMATCH");
			return;
		}
		
		List<Question> questionList = new ArrayList<Question>();
		for (int i = 0; i < questions.size(); i++) {
			questionList.add(new Question("Trivia", answers.get(i), 1, questions.get(i)));
		}
		
		db.insert(new Quiz(name, questionList), Constants.QUIZZES_COLLECTION_NAME);
	}

}
