package com.progetto.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import com.progetto.blog.model.Post;
import com.progetto.blog.model.User;
import com.progetto.blog.service.UserService;

@RestController
public class PostController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "myPersonal/create", method = RequestMethod.POST)
	public String createPost(@RequestBody Post post ) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsername(auth.getName());
		userService.addPosts(user, post);
		return "Post saved";
	}
	
	@RequestMapping(value="myPersonal/posts", method = RequestMethod.GET)
	public @ResponseBody List<Post> getPosts() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsername(auth.getName());		
	    return user.getPosts();
	}	

}
