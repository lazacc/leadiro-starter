package com.leadiro.starter.service.starter.dao;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class ConcreteValidateMapper implements ValidateMapper {

	@Override
	public ResponseEntity<String> isValidPostcode(String postcode) {
		RestTemplate restTemplate = new RestTemplate();
		String baseUrl = "https://api.postcodes.io/postcodes/";
		ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + postcode, String.class);
		return response;
	}

}
