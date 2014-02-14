package com.team6.app.arena;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.team6.app.AccountService;
import com.team6.app.Constants;
import com.team6.app.User;

@Service
public class ArenaServiceImpl implements ArenaService {

	@Autowired
	private MongoOperations mo;
	
	@Autowired
	private AccountService accService; 
	
	@Override
	public Collection<User> getOpponentsOf(String userId, String l, String o) {
		Integer limit, offset;
		try {
			limit = Integer.valueOf(l);
		} catch (NumberFormatException e) {
			limit = 0;
		}
		try {
			offset = Integer.valueOf(o);
		} catch (NumberFormatException e) {
			offset = 0;
		}
		return getOpponentsOf(userId, limit, offset);
	}
	
	public Collection<User> getOpponentsOf(String userId, Integer limit, 
			Integer offset) {
		Query query = new Query().
				addCriteria(Criteria.where("_id").ne(userId)).
				limit(limit).
				skip(offset);
		return mo.find(query, com.team6.app.User.class,
				Constants.USER_COLLECTION_NAME);
	}

	@Override
	public Battle createBattle(String userId1, String userId2) {
		User user1 = accService.findById(userId1);
		User user2 = accService.findById(userId2);
		if (user1 == null || user2 == null) {
			return null;
		}
		
		List<String> log = new ArrayList<String>();
		log.add(String.format("%s has challenged %s!",
				user1.getUsername(),
				user2.getUsername()));
		
		Battle battle = new Battle();
		battle.setUserId1(userId1);
		battle.setUserId2(userId2);
		battle.setChar1(user1.getCharacter());
		battle.setChar2(user2.getCharacter());
		battle.setUser1Turn(true);
		battle.setLog(log);
		
		mo.insert(battle, Constants.BATTLES_COLLECTION_NAME);
		return battle;
	}

	@Override
	public Battle getBattle(String battleId) {
		return mo.findById(
				battleId,
				Battle.class,
				Constants.BATTLES_COLLECTION_NAME);
	}
	
}
