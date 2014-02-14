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
		battle.setUserCmd1("");
		battle.setUserCmd2("");
		battle.setChar1(user1.getCharacter());
		battle.setChar2(user2.getCharacter());
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
	public void doBattleCommand(Battle battle, String userId, String cmd) {
		if (!cmd.equalsIgnoreCase("attack") &&
				!cmd.equalsIgnoreCase("charge") &&
				!cmd.equalsIgnoreCase("defend")) {
			// Invalid command. Change nothing
			return;
		}
		
		if (battle.getUserId1().equals(userId)) {
			if (cmd.equalsIgnoreCase("attack")) {
				if (!battle.getChar1().isCharged()) {
					// Can't attack if not charged
					return;
				}
			}
			battle.setUserCmd1(cmd);
			
		} else if (battle.getUserId2().equals(userId)) {
			if (cmd.equalsIgnoreCase("attack")) {
				if (!battle.getChar2().isCharged()) {
					// Can't attack if not charged
					return;
				}
			}
			battle.setUserCmd2(cmd);
		} else {
			// Not a participating user. Change nothing
			return;
		}
		
		if (!battle.getUserCmd1().isEmpty() && !battle.getUserCmd2().isEmpty()) {
			String cmd1 = battle.getUserCmd1().toLowerCase();
			String cmd2 = battle.getUserCmd2().toLowerCase();
			com.team6.app.Character char1 = battle.getChar1();
			com.team6.app.Character char2 = battle.getChar2();
			int rawDmgTo2 = calculateDmg(
					char1.getLevel(),
					char1.getStr(),
					char1.getCritChance(),
					0);
			int rawDmgTo1 = calculateDmg(
					char2.getLevel(),
					char2.getStr(),
					char2.getCritChance(),
					0);
//			int mitigatedDmgTo2 = calculateDmg(
//					char1.getLevel(),
//					char1.getStr(),
//					char1.getCritChance(),
//					char2.getDef());
//			int mitigatedDmgTo1 = calculateDmg(
//					char2.getLevel(),
//					char2.getStr(),
//					char2.getCritChance(),
//					char1.getDef());
			
			if (cmd1.equals("attack") && cmd2.equals("attack")) {
				char1.incrementHealth(-rawDmgTo1);
				char2.incrementHealth(-rawDmgTo2);
				battle.getLog().add(String.format("%s attacked %s for %d of damage!",
						char1.getCharacterName(),
						char2.getCharacterName(),
						rawDmgTo2));
				battle.getLog().add(String.format("%s attacked %s for %d of damage!",
						char2.getCharacterName(),
						char1.getCharacterName(),
						rawDmgTo1));
			} else if (cmd1.equals("defend") && cmd2.equals("defend")) {
				battle.getLog().add(String.format("%s and %s both tried to defend! It wasn't very effective....",
						char1.getCharacterName(),
						char2.getCharacterName()));
			} else if (cmd1.equals("charge") && cmd2.equals("charge")) {
				char1.setCharged(true);
				char2.setCharged(true);
				battle.getLog().add(String.format("%s and %s both charged their attacks! Things are really heating up!",
						char1.getCharacterName(),
						char2.getCharacterName()));
			} else if (cmd1.equals("attack") && cmd2.equals("defend")) {
				battle.getLog().add(String.format("%s attacked %s, who was defending! It wasn't very effective....",
						char1.getCharacterName(),
						char2.getCharacterName()));
			} else if (cmd1.equals("attack") && cmd2.equals("charge")) {
				char2.incrementHealth(-rawDmgTo2);
				battle.getLog().add(String.format("%s attacked %s, who was trying to charge an attack! It was very effective!",
						char1.getCharacterName(),
						char2.getCharacterName()));
			} else if (cmd1.equals("defend") && cmd2.equals("attack")) {
				battle.getLog().add(String.format("%s attacked %s, who was defending! It wasn't very effective....",
						char2.getCharacterName(),
						char1.getCharacterName()));
			} else if (cmd1.equals("defend") && cmd2.equals("charge")) {
				char2.setCharged(true);
				battle.getLog().add(String.format("% is defending.",
						char1.getCharacterName()));
				battle.getLog().add(String.format("% is charging for an attack.",
						char2.getCharacterName()));
			} else if (cmd1.equals("charge") && cmd2.equals("attack")) {
				char1.incrementHealth(-rawDmgTo2);
				battle.getLog().add(String.format("%s attacked %s, who was trying to charge an attack! It was very effective!",
						char2.getCharacterName(),
						char1.getCharacterName()));
			} else if (cmd1.equals("charge") && cmd2.equals("defend")) {
				char1.setCharged(true);
				battle.getLog().add(String.format("% is defending.",
						char2.getCharacterName()));
				battle.getLog().add(String.format("% is charging for an attack.",
						char1.getCharacterName()));
			}

			battle.setUserCmd1("");
			battle.setUserCmd2("");
		}
		
		mo.save(battle, Constants.BATTLES_COLLECTION_NAME);
	}
	
	static int calculateDmg(int level, int str, double critChance, int mitigation) {
		return level + str;
	}
	
}
