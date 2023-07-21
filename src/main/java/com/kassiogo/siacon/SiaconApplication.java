package com.kassiogo.siacon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.kassiogo\\.siacon\\.util\\..*"))
public class SiaconApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiaconApplication.class, args);
	}

}
