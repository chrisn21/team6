package com.team6.app.apirequest;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mashape.unirest.http.JsonNode;
import com.team6.app.quiz.Question;
 
 
@Repository
public class RequestAPIService {
     
    @Autowired
    private MongoTemplate mongoTemplate;
     
   public List<Question> parseQuestions(JsonNode j) throws JSONException
   {
	   JSONObject obj = new JSONObject(j.toString());
	   JSONArray results = obj.getJSONArray("result");
	   List<Question> lq = new ArrayList<Question>();
	   for(int i = 0; i < results.length(); i++)
	   {
		   JSONObject question = results.getJSONObject(i);
		   Question q = new Question(question.getString("category"), question.getString("answer"), question.getInt("difficulty"), question.getString("question"));
		   lq.add(q);
	   }
	   return lq;
   }

    
}