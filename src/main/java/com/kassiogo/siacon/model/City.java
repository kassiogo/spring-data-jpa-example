package com.kassiogo.siacon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class City extends BaseEntity {

	@Column(nullable = false, length = 250)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "state_id", nullable = false, foreignKey = @ForeignKey(name = "FK_CityState"))
	private State state;
}
