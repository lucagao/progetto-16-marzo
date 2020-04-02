package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Activity;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Long> {

}