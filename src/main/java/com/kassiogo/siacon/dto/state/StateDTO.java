package com.kassiogo.siacon.dto.state;

import com.kassiogo.siacon.dto.BaseDTO;
import com.kassiogo.siacon.dto.country.CountryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StateDTO extends BaseDTO {
    private String name;
    private String abbreviation;
    private CountryDTO country;
}
