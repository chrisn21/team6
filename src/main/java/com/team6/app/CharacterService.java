package com.team6.app;


import java.util.Collection;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
 
 
@Repository
public class CharacterService {
     
    @Autowired
    private MongoTemplate mongoTemplate;
     
    //Retrieve character associated with the user's id
    public User getCharacter(String userid)
    {
    	User user = mongoTemplate.findById(userid, User.class, "User");
    	return user;
    }
    
    //Update the statistics for the retrieved character
    public Integer changeStats(String userid, int experience)
    {
    	Character c = getCharacter(userid).getCharacter();
    	c.setExperience(c.getExperience() + experience);
    	c.checkLevel();
    	
    	Query q = new Query();
    	q.addCriteria(Criteria.where("id").is(userid));
    	Update u = new Update();
    	u.set("character", c);
    	mongoTemplate.findAndModify(q, u, User.class, Constants.USER_COLLECTION_NAME);
    	return experience;
    }

	public Integer changeStats(String userId, Collection<Boolean> feedback,
			Integer difficulty) {
		Integer numCorrect = 0;
		for (Boolean correct : feedback) {
			numCorrect += correct ? 1 : 0;
		}
		return changeStats(userId, difficulty * numCorrect);
	}
    
}