package com.example.demo.service;

import java.util.List;
import com.example.demo.model.*;

public interface UtenteService {
		
	List<Utente> findAll();

	Long count();

	void deleteById(Long userId);

	void add(Utente user);
}
