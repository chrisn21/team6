package com.team6.app.quiz;

import org.springframework.web.bind.annotation.ModelAttribute;

public class Quiz {

	private String name;
	
	@ModelAttribute("name")
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
