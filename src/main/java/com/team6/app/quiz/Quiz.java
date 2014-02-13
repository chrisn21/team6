package com.team6.app.quiz;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.annotation.Id;

public class Quiz {

	@Id
	private String id;
	private String name;
	private String description;
	private String creatorId;
	private String categoryId;
	private Collection<String> questionIds;
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

	public Collection<String> getQuestionIds() {
		return questionIds;
	}

	public Integer getDifficulty() {
		return difficulty;
	}

	public Date getCreationTime() {
		return creationTime;
	}
	
}
