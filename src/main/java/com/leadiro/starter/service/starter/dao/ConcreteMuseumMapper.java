package com.leadiro.starter.service.starter.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leadiro.starter.service.starter.dto.Book;
import com.leadiro.starter.service.starter.dto.Item;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ConcreteMuseumMapper implements MuseumMapper {

	@Value("${museum.url}")
	private String url;

	@Override
	public Item[] searchByKeyword(String[] keywords) throws JsonMappingException, JsonProcessingException {
		List<Item> items = new ArrayList<Item>();
		Item[] museumItems = getMuseumItems();

		Arrays.sort(keywords);
		for (Item item : museumItems) {
			if (item.getKeywords() != null) {
				for (String keyword : item.getKeywords()) {
					if (Arrays.binarySearch(keywords, keyword) >= 0) {
						items.add(item);
						log.debug(item.getId() + " - " + item.getDisplayTitle() + " - " + keyword);
						break;
					}
				}
			}
		}

		return items.toArray(new Item[items.size()]);
	}

	@Override
	public Book searchByID(String id) throws JsonMappingException, JsonProcessingException {
		Book book = new Book();
		Item[] museumItems = getMuseumItems();
		String formattedId = "items/" + id;

		List<Book> books = Arrays.asList(museumItems).stream().filter(item -> formattedId.equals(item.getId()))
				.map(item -> new Book(item.getId(), item.getDisplayTitle(), item.getKeywords()))
				.collect(Collectors.toList());
		
		if (books != null) {
			if(!books.isEmpty()) {
				book = books.get(0);
			}
		}
		
		return book;
	}

	private Item[] getMuseumItems() throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		RestTemplate restTemplate = new RestTemplate();
		RequestEntity<String> entity = new RequestEntity<String>(HttpMethod.GET,
				UriComponentsBuilder.fromUriString(url).build().toUri());
		ResponseEntity<String> response = restTemplate.exchange(entity, String.class);
		Item[] items = mapper.readValue(response.getBody().toString(), Item[].class);
		return items;
	}

}
