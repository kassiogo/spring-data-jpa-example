package com.kassiogo.siacon.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kassiogo.siacon.model.City;
import com.kassiogo.siacon.repository.CityRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CityService {

	private CityRepo repo;
	
	public List<City> findAll() {
		return repo.findAll();
	}
	
	public City save( City city ) {
		return repo.save(city);
	}
	
	public void delete(UUID id) {
		repo.deleteById(id);
	}
	
	public void delete(City city) {
		repo.delete(city);
	}
}
