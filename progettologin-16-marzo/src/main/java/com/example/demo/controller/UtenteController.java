package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.*;
import com.example.demo.service.*;

@RestController
public class UtenteController {

	@Autowired
	private UtenteServiceUso userService;
	
    
	@PostMapping("/utente")
	public void addUser(@RequestBody Utente user) {
		userService.add(user);
	}

	@GetMapping("/utenti")
	public List<Utente> allUsers() {
		return userService.findAll();
	}

	@GetMapping("/utenti/count")
	public Long count() {
		return userService.count();
	}

	@DeleteMapping("/utenti/{id}")
	public void delete(@PathVariable String id) {
		Long userId = Long.parseLong(id);
		userService.deleteById(userId);
	}

}