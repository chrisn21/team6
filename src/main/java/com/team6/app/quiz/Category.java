package com.team6.app.quiz;

import org.springframework.web.bind.annotation.ModelAttribute;

public class Category {
	
	private String name;
	private String description;
	
	@ModelAttribute("name")
	public String getName() {
		return name;
	}

	@ModelAttribute("description")
	public String getDescription() {
		return description;
	}
	
}
