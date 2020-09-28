package com.leadiro.starter.service.starter.dao;

import org.springframework.http.ResponseEntity;

public interface ValidateMapper {

	ResponseEntity<String> isValidPostcode(String postcode);
	
}
