package com.kassiogo.siacon.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Embeddable
@Data
public class Address {

	private String addressNumber;
	
	private String addressComplement;
	
	private String addressZipCode;
	
	private String addressDistrictName;
	
	private String addressCityName;
	
	private String addressStateInitials;
	
	private String addressCountryName;
	
	@ManyToOne
	@JoinColumn(name = "street_id", foreignKey = @ForeignKey(name = "FK_AdressStreet"))
	private Street addressStreet;
}
