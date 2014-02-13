package com.team6.app.quiz;

import org.springframework.data.annotation.Id;

public class Question {
	
	@Id
	private String id;
	private String question;
	private String answer;
	
	public String getId() {
		return id;
	}
	
	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}
	
}
