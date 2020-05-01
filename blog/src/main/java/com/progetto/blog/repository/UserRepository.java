package com.progetto.blog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.progetto.blog.model.User;

public interface UserRepository extends MongoRepository<User, String>{
	User findByUsername(String username);
}