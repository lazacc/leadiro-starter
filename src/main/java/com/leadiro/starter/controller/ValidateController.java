package com.leadiro.starter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leadiro.starter.service.ValidateService;

@RestController
public class ValidateController {
	@Autowired ValidateService service;
	
	@GetMapping("/validate/email")
	public boolean isValidEmail(@RequestParam String email) {
		return service.isValidEmail(email);
	}
	
	@GetMapping("/validate/postcode")
	public ResponseEntity<String> isValidPostcode(@RequestParam String postcode) {
		return service.isValidPostcode(postcode);
	}

}
