package com.progetto.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.progetto.blog.model.User;
import com.progetto.blog.service.UserService;

@RestController
public class UserRegistrationController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "awesomeBlog/register", method = RequestMethod.POST)
	public String addUser(@RequestBody User user) {
		String username = user.getUsername();
		if (userService.findByUsername(username) != null) {
			return "There is another user with that username";
		}
		userService.register(user);
		return "Registered user";
	}
	
}