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
public class AccountService {
     
    @Autowired
    private MongoTemplate mongoTemplate;
    
    public static final String COLLECTION_NAME = "User";
     
    public void addUser(User User) {
        if (!mongoTemplate.collectionExists(User.class)) {
            mongoTemplate.createCollection(User.class);
        }       
        mongoTemplate.insert(User, COLLECTION_NAME);
    }
     
    public List<User> listUser() {
        return mongoTemplate.findAll(User.class, COLLECTION_NAME);
    }
    
    public User findByUsername(String username) {
    	Query query = new Query();
    	query.addCriteria(Criteria.where("username").is(username));
    	
    	return mongoTemplate.findOne(query, User.class, COLLECTION_NAME);
    }
    
    public void deleteUser(User User) {
        mongoTemplate.remove(User, COLLECTION_NAME);
    }
     
    public boolean verifyLogin(String username, String password)
    {
    	Query q = new Query();
    	q.addCriteria(Criteria.where("username").is(username).andOperator(Criteria.where("password").is(password)));
    	
    	User user = mongoTemplate.findOne(q, User.class, COLLECTION_NAME);
    	if(user == null)
    		return false;
    	
    	else
    		return true;
    }
    
    public void updateUserEmail(String username, String email) {
    	Query q = new Query();
    	q.addCriteria(Criteria.where("username").is(username));
    	Update u = new Update();
    	u.set("email", email);
    	mongoTemplate.findAndModify(q, u, User.class);
    }
    
    public void updateQuestionFields(String username)
    {
    	Query q = new Query();
    	q.addCriteria(Criteria.where("username").is(username));
    	Update u = new Update();
    	u.set("email", username);
    	mongoTemplate.findAndModify(q, u, User.class);
    }
    
    //Don't touch
    public void updateQuestionPoints(String userid, String correct)
    {
    	int offset = 0;
    	if (correct.equals("Yes"))
    		offset = 1;
    	User u = mongoTemplate.findById(userid, User.class, "User");
    	Query q = new Query(); 	q.addCriteria(Criteria.where("id").is(userid));
    	Update up = new Update(); up.set("points", u.getPoints() + offset);
    	mongoTemplate.findAndModify(q, up, User.class);

    	Update up2 = new Update(); up2.set("questionsCorrect", u.getQC() + offset);
    	mongoTemplate.findAndModify(q, up, User.class);
    	
    	Update up3 = new Update(); up3.set("questionsAttempted", u.getQA() + 1);
    	mongoTemplate.findAndModify(q, up, User.class);
    	
    }
}