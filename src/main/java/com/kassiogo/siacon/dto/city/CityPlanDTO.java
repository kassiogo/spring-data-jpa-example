package com.kassiogo.siacon.dto.city;

import com.kassiogo.siacon.dto.BaseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CityPlanDTO extends BaseDTO {

	private String name;
	private String stateAbbreviation;
	
}
