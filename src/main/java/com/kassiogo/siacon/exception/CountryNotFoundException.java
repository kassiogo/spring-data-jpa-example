package com.kassiogo.siacon.exception;

import java.util.UUID;

public class CountryNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public CountryNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public CountryNotFoundException(UUID countryId) {
		this(String.format("There is not country register with code %s", countryId.toString() ));
	}

}
