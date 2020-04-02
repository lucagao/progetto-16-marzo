package com.example.demo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.dao.UserRegistrationDao;
import com.example.demo.model.User;
import com.example.demo.model.Activity;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDao registration);
    
    Long count();
    
    void deleteById(Long userId);
    
    void addActivities(User user, Activity activities);
	
}