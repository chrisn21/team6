package com.team6.app.quiz;

import org.springframework.data.annotation.Id;

public class Question {
	
	@Id
	private String id;
	private String question;
	private String answer;
	private Integer timesAttempted;
	private Boolean answered;
	
	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}

	public Integer getTimesAttempted() {
		return timesAttempted;
	}

	public Boolean getAnswered() {
		return answered;
	}
	
}
