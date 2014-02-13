package com.team6.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoAction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
 
 
@Repository
public class CharacterService {
     
    @Autowired
    private MongoTemplate mongoTemplate;
    
    public static final String COLLECTION_NAME = "Character";
     
    public User getCharacter(String userid)
    {
    	User u = mongoTemplate.findById(userid, User.class, "User");
    	return u;
    }
    
    public void changeStats(String userid)
    {
    	Character c = getCharacter(userid).getCharacter();
    	c.setExperience(c.getExperience() + 1);
    	
    	mongoTemplate.save(c, "User");
    }
    

    
}