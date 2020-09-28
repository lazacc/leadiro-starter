package com.leadiro.starter.service.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.leadiro.starter.service.MuseumService;
import com.leadiro.starter.service.starter.dao.MuseumMapper;
import com.leadiro.starter.service.starter.dto.Book;
import com.leadiro.starter.service.starter.dto.Item;

@Service
public class ConcreteMuseumService implements MuseumService {
	@Autowired MuseumMapper mapper;

	@Override
	public Item[] searchByKeyword(String[] keywords) throws JsonMappingException, JsonProcessingException {
		return mapper.searchByKeyword(keywords);
	}

	@Override
	public Book searchByID(String id) throws JsonMappingException, JsonProcessingException {
		return mapper.searchByID(id);
	}

}
