package com.kassiogo.siacon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Country extends BaseEntity {

	@Column(nullable = false, length = 250)
	private String name;
	
	@Column(nullable = false, length = 3)
	private String initials;
	
}
