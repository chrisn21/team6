package com.team6.app.quiz;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Quiz {

	public Quiz(String name, List<Question> questions) {
		this.name = name;
		this.questions = questions;
		this.difficulty = 1;
		this.creationTime = new Date();
	}

	@Id
	private String id;
	private String name;
	private String description;
	private String creatorId;
	private String categoryId;
	private List<Question> questions;
	private Integer difficulty;
	private Date creationTime;
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public Integer getDifficulty() {
		return difficulty;
	}

	public Date getCreationTime() {
		return creationTime;
	}
	
}
