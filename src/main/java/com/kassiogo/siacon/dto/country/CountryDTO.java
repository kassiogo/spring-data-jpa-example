package com.kassiogo.siacon.dto.country;

import com.kassiogo.siacon.dto.BaseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CountryDTO extends BaseDTO {

	private String name;
	private String initials;
	
}
