package com.leadiro.starter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.leadiro.starter.service.starter.dto.Book;
import com.leadiro.starter.service.starter.dto.Item;

public interface MuseumService {
	
	Item[] searchByKeyword(String[] keywords) throws JsonMappingException, JsonProcessingException;
	Book searchByID(String id) throws JsonMappingException, JsonProcessingException;

}
