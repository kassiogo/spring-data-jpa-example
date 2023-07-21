package com.kassiogo.siacon.model;

import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

// @Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Person extends BaseEntity {

	private String name;
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "district_id", foreignKey = @ForeignKey(name = "FK_PersonDistrict"))
	private District district;
	
	// @Embedded
	// private Address address;
}
