package com.leadiro.starter.service.starter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	
	private String id;
	private String displayTitle;
	private String[] keywords;
		
}
