package com.kassiogo.siacon.controller;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kassiogo.siacon.dto.country.CountryDTO;
import com.kassiogo.siacon.exception.BusinessException;
import com.kassiogo.siacon.exception.CountryNotFoundException;
import com.kassiogo.siacon.model.Country;
import com.kassiogo.siacon.service.CountryService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("countries")
@AllArgsConstructor
public class CountryController {

	private CountryService service;
	private ModelMapper mapper;
	
	/**
	 * Method that find all the countries
	 */
	@GetMapping
	public ResponseEntity<Flux<CountryDTO>> findAll() {
		var dtos = service.findAll().stream()
				.map(item -> mapper.map(item, CountryDTO.class))
				.toList();
		
		return ResponseEntity.ok( Flux.fromStream(dtos.stream()) );
	}
	
	
	/**
	 * Method that create a new country
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<CountryDTO> save( @RequestBody @Valid CountryDTO dto ) {
		var country = mapper.map(dto, Country.class);
		country = service.save(country);
		return Mono.just(mapper.map(country, CountryDTO.class));
	}
	
	
	/**
	 * Method that update a country
	 */
	@PutMapping
	public Mono<CountryDTO> update( @RequestBody CountryDTO dto ) {
		try {
			var countryUpdate = service.findById(dto.getId()).orElseThrow( () -> new CountryNotFoundException(dto.getId()) );
			mapper.map(dto, countryUpdate);
			countryUpdate = service.save(countryUpdate);
			return Mono.just(mapper.map(countryUpdate, CountryDTO.class));
		} catch (CountryNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	
	/**
	 * Method that delete a country
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") UUID id) {
		service.delete(id);
	}
}
