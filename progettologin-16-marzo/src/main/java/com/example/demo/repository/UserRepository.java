package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Utente;

public interface UserRepository extends CrudRepository<Utente, Long> {

}