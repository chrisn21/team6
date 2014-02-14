package com.team6.app.arena;

import java.util.Collection;

import com.team6.app.User;

public interface ArenaService {

	public Collection<User> getOpponentsOf(String userId, 
			String limit, String offset);
	public Collection<User> getOpponentsOf(String userId,
			Integer limit, Integer offset);
	public Battle createBattle(String userId1, String userId2);
	public Battle getBattle(String battleId);
	
}
