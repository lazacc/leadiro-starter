package com.leadiro.starter.service.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.leadiro.starter.service.ValidateService;
import com.leadiro.starter.service.starter.dao.ValidateMapper;

@Service
public class ConcreteValidateService implements ValidateService {
	@Autowired ValidateMapper mapper;

	@Override
	public boolean isValidEmail(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

	@Override
	public ResponseEntity<String> isValidPostcode(String postcode) {
		return mapper.isValidPostcode(postcode);
	}

}
