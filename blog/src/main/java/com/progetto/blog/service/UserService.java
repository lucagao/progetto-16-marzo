package com.progetto.blog.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.progetto.blog.model.Authority;
import com.progetto.blog.model.AuthorityName;
import com.progetto.blog.model.User;
import com.progetto.blog.model.Post;
import com.progetto.blog.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;	
	
	public User findByUsername(String user) {
		return userRepository.findByUsername(user);
	}
	
	public void register(User registration) {
	    User user = new User();
	    user.setUsername(registration.getUsername()); 
	    user.setPassword(passwordEncoder.encode(registration.getPassword()));
	    user.setEnabled(true);
	    user.setAuthorities(Arrays.asList(new Authority(AuthorityName.ROLE_USER)));
		userRepository.save(user);
	}
	public void addPosts(User user, Post post) {
		user.addPosts(post);
		userRepository.save(user);
	}	
}