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

import com.kassiogo.siacon.dto.state.StateDTO;
import com.kassiogo.siacon.model.State;
import com.kassiogo.siacon.service.StateService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("states")
@AllArgsConstructor
public class StateController {

    private StateService service;
    private ModelMapper mapper;

    
    @GetMapping
    public ResponseEntity<Flux<StateDTO>> findAll() {
        var states = service.findAll().stream()
                .map(item -> mapper.map(item, StateDTO.class))
                .toList();
        return ResponseEntity.ok(Flux.fromStream(states.stream()));
    }
    
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<StateDTO> save(@RequestBody StateDTO dto) {
        var state = mapper.map(dto, State.class);
        state = service.save(state);
        return Mono.just( mapper.map(state, StateDTO.class) );
    }
    
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable("id") UUID id ) {
    	service.delete(id);
    }
    
}
