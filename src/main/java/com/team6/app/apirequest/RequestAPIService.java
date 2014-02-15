package com.team6.app.apirequest;


import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import com.mashape.unirest.http.JsonNode;
import com.team6.app.Constants;
import com.team6.app.quiz.QuizService;
 
 
@Repository
public class RequestAPIService {
     
    @Autowired
    private MongoTemplate mongoTemplate;
     
    @Autowired
    private QuizService service;
    
   public void parseQuestions(JsonNode j) throws JSONException
   {
	   JSONObject obj = new JSONObject(j.toString());
	   JSONArray results = obj.getJSONArray("result");
	   String[] questions = new String[results.length()];
	   String[] answers = new String[results.length()];
	   String category = "";
	   for(int i = 0; i < results.length(); i++)
	   {
		   JSONObject question = results.getJSONObject(i);
		   questions[i] = question.getString("question");
		   answers[i] = question.getString("answer");
		   category = question.getString("category");
		   //Question q = new Question(question.getString("category"), question.getString("answer"), question.getInt("difficulty"), question.getString("question"));
	   }
	   
	   service.createQuiz(category, Arrays.asList(questions), Arrays.asList(answers));
   }

    
}