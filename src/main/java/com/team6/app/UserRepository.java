package com.team6.app;

import org.springframework.data.mongodb.repository.MongoRepository;

//Because UserRepository extends MongoRepository, it gains access to CRUD operations
public interface UserRepository extends MongoRepository<User, String> {

	//findByFirstName finds user by their firstName, "String firstName" maps to the firstName column in Users
	//Same with findByLastName
    public User findByFirstName(String firstName);
    public User findByLastName(String lastName);
    public User findByUsername(String username);

}