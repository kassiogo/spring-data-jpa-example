package com.kassiogo.siacon.controller;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kassiogo.siacon.dto.city.CityDTO;
import com.kassiogo.siacon.dto.city.CityPlanDTO;
import com.kassiogo.siacon.model.City;
import com.kassiogo.siacon.service.CityService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("cities")
@AllArgsConstructor
public class CityController {

	private CityService service;
	private ModelMapper mapper;

	/**
	 * Method that find all the cities
	 */
	@GetMapping
	public ResponseEntity<Flux<CityPlanDTO>> findAll() {
		var cities = service.findAll()
				.stream()
				.map(item -> mapper.map(item, CityPlanDTO.class))
				.toList();
		return ResponseEntity.ok(Flux.fromStream(cities.stream()));
	}


	/**
	 * Method that create a new city
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<CityDTO> create( @RequestBody CityDTO dto  ) {
		var city = mapper.map(dto, City.class);
		city = service.save(city);
		return Mono.just(mapper.map(city, CityDTO.class));
	}


	/**
	 * Method that delete a city
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") UUID id) {
		service.delete(id);
	}
	
}
