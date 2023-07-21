package com.kassiogo.siacon.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kassiogo.siacon.model.State;

public interface StateRepo extends JpaRepository<State, UUID> {

}
