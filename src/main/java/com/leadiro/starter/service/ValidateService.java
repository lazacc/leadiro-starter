package com.leadiro.starter.service;

import org.springframework.http.ResponseEntity;

public interface ValidateService {
	
	boolean isValidEmail(String email);
	ResponseEntity<String> isValidPostcode(String postcode);

}
