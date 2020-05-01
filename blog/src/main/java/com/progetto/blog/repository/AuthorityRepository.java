package com.progetto.blog.repository;

import com.progetto.blog.model.AuthorityName;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.progetto.blog.model.Authority;

public interface AuthorityRepository extends MongoRepository<Authority, Long> {
    Authority findByName(AuthorityName name);

}