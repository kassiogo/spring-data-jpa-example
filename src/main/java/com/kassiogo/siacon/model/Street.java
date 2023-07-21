package com.kassiogo.siacon.model;

import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

// @Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Street extends BaseEntity {

	@Column(nullable = false, length = 250)
	private String name;
	
	@Column(length = 20)
	private String typeStreet;
	
	@Column(nullable = false, length = 9)
	private String zipCode;
	
	@ManyToOne
	@JoinColumn(name = "district_id", nullable = false, foreignKey = @ForeignKey(name = "FK_StreetDistrict"))
	private District district;
	
}
