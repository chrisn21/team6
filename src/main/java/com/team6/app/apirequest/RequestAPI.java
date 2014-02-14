package com.team6.app.apirequest;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.team6.app.quiz.Question;


public class RequestAPI 
{
	@Id //Fields of Character table/collection
	private String id;
	private List<Question> result;
	private String count;
	private String success;
	
	public RequestAPI ()
	{
		
	}

	public String getId() {
		return id;
	}

	public List<Question> getResult() {
		return result;
	}

	public String getCount() {
		return count;
	}

	public String getSuccess() {
		return success;
	}
}
