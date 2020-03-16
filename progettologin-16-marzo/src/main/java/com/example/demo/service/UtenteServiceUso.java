package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.*;
import com.example.demo.model.*;

@Service
public class UtenteServiceUso implements UtenteService {
		
	@Autowired
	private UserRepository userRepository;

	public List<Utente> findAll() {

		Iterable<Utente> it = userRepository.findAll();

		List<Utente> users = new ArrayList<Utente>();
		it.forEach(e -> users.add(e));

		return users;
	}

	public Long count() {

		return userRepository.count();
	}

	public void deleteById(Long userId) {

		userRepository.deleteById(userId);
	}

	@Override
	public void add(Utente user) {
		userRepository.save(user);
	}
	
}
