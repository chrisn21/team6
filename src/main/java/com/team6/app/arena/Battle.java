package com.team6.app.arena;

import java.util.Collection;

import org.springframework.data.annotation.Id;

import com.team6.app.Character;

public class Battle {

	@Id
	private String id;
	private String userId1;
	private String userId2;
	private Character char1;
	private Character char2;
	private Collection<String> log;
	private boolean user1Turn;
	
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
	public boolean isUser1Turn() {
		return user1Turn;
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
	public void setUser1Turn(boolean user1Turn) {
		this.user1Turn = user1Turn;
	}
	
}
