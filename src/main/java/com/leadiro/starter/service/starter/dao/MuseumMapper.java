package com.leadiro.starter.service.starter.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.leadiro.starter.service.starter.dto.Book;
import com.leadiro.starter.service.starter.dto.Item;

public interface MuseumMapper {
	
	Item[] searchByKeyword(String[] keywords) throws JsonMappingException, JsonProcessingException;
	Book searchByID(String id) throws JsonMappingException, JsonProcessingException;

}
