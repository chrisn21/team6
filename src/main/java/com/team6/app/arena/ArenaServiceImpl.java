package com.team6.app.arena;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
	public Collection<User> getPotentialOpponentsOf(String userId, String l, String o) {
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
		return getPotentialOpponentsOf(userId, limit, offset);
	}
	
	private Collection<User> getPotentialOpponentsOf(String userId, Integer limit, 
			Integer offset) {
		Collection<ObjectId> opponentIds = getOpponentIdsOf(userId);
		Query query = new Query().
				addCriteria(Criteria.where("_id").ne(userId).
						andOperator(Criteria.where("_id").nin(opponentIds))).
						limit(limit).
						skip(offset);
		Collection<User> users = mo.find(query,
				com.team6.app.User.class,
				Constants.USER_COLLECTION_NAME);
		return users;
	}
	
	// Retrieve a collection of user ids currently engaged in 
	// combat with this user
	private Collection<ObjectId> getOpponentIdsOf(String userId) {
		Query query = new Query().
				addCriteria(new Criteria().orOperator(
						Criteria.where("userId1").is(userId),
						Criteria.where("userId2").is(userId)));
		Collection<Battle> battles = mo.find(query,
				Battle.class,
				Constants.BATTLES_COLLECTION_NAME);
		
		Collection<ObjectId> opponentIds = new ArrayList<ObjectId>();
		for (Battle battle : battles) {
			if (battle.getUserId1().equals(userId)) {
				opponentIds.add(ObjectId.massageToObjectId(battle.getUserId2()));
			} else {
				opponentIds.add(ObjectId.massageToObjectId(battle.getUserId1()));
			}
		}
		return opponentIds;
	}

	@Override
	public Battle createBattle(String userId1, String userId2) {
		User user1 = accService.findById(userId1);
		User user2 = accService.findById(userId2);
		if (user1 == null || user2 == null) {
			return null;
		}
		if (getBattleByUserIds(userId1, userId2) != null) {
			// user1 already battling user2
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
	public Battle getBattleById(String battleId) {
		return mo.findById(
				battleId,
				Battle.class,
				Constants.BATTLES_COLLECTION_NAME);
	}

	
	public Battle getBattleByUserIds(String userId1, String userId2) {
		if (userId1 == null || userId2 == null) {
			return null;
		}
		
		Query query = new Query(Criteria.
				where("userId1").is(userId1).
				andOperator(Criteria.
						where("userId2").is(userId2)));
		
		Battle battle = mo.findOne(query,
				Battle.class,
				Constants.BATTLES_COLLECTION_NAME);
		
		return battle;
	}

	@Override
	public Collection<Battle> getBattlesByUserId(String userId) {
		Query query = new Query().addCriteria(
				new Criteria().orOperator(
						Criteria.where("userId1").is(userId),
						Criteria.where("userId2").is(userId)));
		return mo.find(query,
				Battle.class,
				Constants.BATTLES_COLLECTION_NAME);
	}
	
	@Override
	public void doBattleCommand(String battleId, String userId, String cmd) {
		if (cmd == null) {
			return;
		}
		Query query = new Query(Criteria.where("_id").is(battleId)).limit(1);
		Update update = new Update().inc("char1.health",
				cmd.equalsIgnoreCase("attack") ? -1 : 1);
		
		mo.findAndModify(query, update,
				Battle.class,
				Constants.BATTLES_COLLECTION_NAME);
	}
	
}
