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
    	
    	User user = mongoTemplate.findOne(q, User.class);
    	return false;
    }
    
    public void updateUserEmail(String username, String email) {
    	Query q = new Query();
    	q.addCriteria(Criteria.where("username").is(username));
    	Update u = new Update();
    	u.set("email", email);
    	mongoTemplate.findAndModify(q, u, User.class);
    }
}