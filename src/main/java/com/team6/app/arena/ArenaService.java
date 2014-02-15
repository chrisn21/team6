package com.team6.app.arena;

import java.util.Collection;

import com.team6.app.User;

public interface ArenaService {

	public Collection<User> getPotentialOpponentsOf(String userId, 
			String limit, String offset);
	public Battle createBattle(String userId1, String userId2);
	public Battle getBattleById(String battleId);
	public Battle getBattleByUserIds(String userId1, String userId2);
	public Collection<Battle> getBattlesByUserId(String userId);
	public void doBattleCommand(Battle battle, String userId, String cmd);
	
}
