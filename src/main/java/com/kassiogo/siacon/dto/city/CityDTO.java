package com.kassiogo.siacon.dto.city;

import com.kassiogo.siacon.dto.BaseDTO;
import com.kassiogo.siacon.dto.state.StateDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CityDTO extends BaseDTO {

	private String name;
	private StateDTO state;
}
