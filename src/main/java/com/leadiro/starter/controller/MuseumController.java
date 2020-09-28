package com.leadiro.starter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.leadiro.starter.service.MuseumService;
import com.leadiro.starter.service.starter.dto.Book;
import com.leadiro.starter.service.starter.dto.Item;

@RestController
public class MuseumController {
	
	@Autowired MuseumService service;
	
	@PostMapping("/museum")
	public Item[] searchByKeyword(@RequestBody String[] keywords) throws JsonMappingException, JsonProcessingException {
		return service.searchByKeyword(keywords);
	}
	
	@GetMapping("/museum/{id}")
	public Book searchByID(@PathVariable String id) throws JsonMappingException, JsonProcessingException {
		return service.searchByID(id);	
	}

}
