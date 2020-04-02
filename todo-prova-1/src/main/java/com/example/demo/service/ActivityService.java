package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Activity;

public interface ActivityService {

    Activity save(Activity activity);
    Activity findByID(Long id);
    void delete(Long id);
	List<Activity> getAllActivities();	
    
}