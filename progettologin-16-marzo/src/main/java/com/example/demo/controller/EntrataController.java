package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.Utente;
import com.example.demo.service.UtenteServiceUso;

@Controller
public class EntrataController {
		
	@Autowired
	private UtenteServiceUso userService;
	
	@GetMapping("/")
	public String enter() {
		return "enter";
	}

}
