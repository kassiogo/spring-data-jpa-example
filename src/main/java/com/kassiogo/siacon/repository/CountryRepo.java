package com.kassiogo.siacon.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kassiogo.siacon.model.Country;

public interface CountryRepo extends JpaRepository<Country, UUID> {

}
