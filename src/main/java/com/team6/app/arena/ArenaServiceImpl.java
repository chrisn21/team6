package com.team6.app.arena;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.team6.app.Constants;
import com.team6.app.User;

@Service
public class ArenaServiceImpl implements ArenaService {

	@Autowired
	private MongoOperations mo;
	
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

	
	
}
