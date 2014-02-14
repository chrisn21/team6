package com.team6.app.quiz;

import org.springframework.data.annotation.Id;

public class Question {
	
	@Id
	private String id;
	private String category;
	private String answer;
	private int difficulty;
	private String question;
	
	public Question(String category, String answer, int difficulty, String question)
	{
		this.category = category;
		this.answer = answer;
		this.difficulty = difficulty;
		this.question = question;
		
	}
	
	public String getCategory() {
		return category;
	}

	public int getDifficulty() {
		return difficulty;
	}

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
