package com.team6.app;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class User {

	@Id //Fields of User table/collection
	private String id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private String dob;
	
	public User (String firstName, String lastName, String username, String password, String email, String dob)
	{
		//Initialize column fields with parameter values
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
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
    
    public String getPassword() {
    	return password;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public String getDOB() {
    	return dob;
    }
}
