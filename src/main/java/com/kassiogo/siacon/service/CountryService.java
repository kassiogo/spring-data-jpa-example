package com.kassiogo.siacon.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kassiogo.siacon.model.Country;
import com.kassiogo.siacon.repository.CountryRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CountryService {

	private CountryRepo repo;
	
	public Optional<Country> findById(UUID id) {
		return repo.findById(id);
	}
	
	public List<Country> findAll() {
		return repo.findAll();
	}
	
	public Country save(Country ent) {
		return repo.save(ent);
	}
	
	public void delete(UUID id) {
		repo.deleteById(id);
	}
	
	public void delete(Country country) {
		repo.delete(country);
	}
}
