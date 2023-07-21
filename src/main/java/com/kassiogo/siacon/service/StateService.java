package com.kassiogo.siacon.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kassiogo.siacon.model.State;
import com.kassiogo.siacon.repository.StateRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StateService {

    private StateRepo repository;

    public State save(State state) {
        return repository.save(state);
    }

    public List<State> findAll() {
        return repository.findAll();
    }
    
    public void delete(UUID id) {
    	repository.deleteById(id);
    }

}
