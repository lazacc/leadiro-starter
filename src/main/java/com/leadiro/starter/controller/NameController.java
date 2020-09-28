package com.leadiro.starter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leadiro.starter.service.NameService;
import com.leadiro.starter.service.starter.dto.Name;

@RestController
public class NameController {
	@Autowired NameService service;
	
	@PostMapping("/parse/name")
	public Name[] parseName(@RequestBody String[] names) {
		return service.parseName(names);
	}

}
