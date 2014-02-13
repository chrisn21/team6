package com.team6.app;

import org.springframework.data.annotation.Id;

public class User {

	@Id //Fields of User table/collection
	private String id;
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String dob;
	
	public User (String firstName, String lastName, String username, String email, String dob)
	{
		//Initialize column fields with parameter values
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.dob = dob;
	}
	
	public String getId() {
        return id;
    }
	
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
    	return lastName;
    }
    
    public String getUsername() {
    	return username;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public String getDOB() {
    	return dob;
    }
}
