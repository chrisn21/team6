package com.team6.app.arena;

import java.util.Collection;

import org.springframework.data.annotation.Id;

import com.team6.app.Character;

public class Battle {

	@Id
	private String id;
	private String userId1;
	private String userId2;
	private String userCmd1;
	private String userCmd2;
	private Character char1;
	private Character char2;
	private Collection<String> log;
	
	public String getId() {
		return id;
	}
	public String getUserId1() {
		return userId1;
	}
	public String getUserId2() {
		return userId2;
	}
	public Character getChar1() {
		return char1;
	}
	public Character getChar2() {
		return char2;
	}
	public Collection<String> getLog() {
		return log;
	}
	public String getUserCmd1() {
		return userCmd1;
	}
	public String getUserCmd2() {
		return userCmd2;
	}
	public void setUserId1(String userId1) {
		this.userId1 = userId1;
	}
	public void setUserId2(String userId2) {
		this.userId2 = userId2;
	}
	public void setChar1(Character char1) {
		this.char1 = char1;
	}
	public void setChar2(Character char2) {
		this.char2 = char2;
	}
	public void setLog(Collection<String> log) {
		this.log = log;
	}
	public void setUserCmd1(String userCmd1) {
		this.userCmd1 = userCmd1;
	}
	public void setUserCmd2(String userCmd2) {
		this.userCmd2 = userCmd2;
	}
	
}
