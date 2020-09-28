package com.leadiro.starter.service.starter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leadiro.starter.service.NameService;
import com.leadiro.starter.service.starter.dto.Name;
import com.leadiro.starter.service.starter.util.NameBuilder;

@Service
public class ConcreteNameService implements NameService {
	
	@Autowired
	NameBuilder builder;

	@Override
	public Name[] parseName(String[] names) {
		List<Name> processedNames = Arrays.asList(names).stream().map(name -> builder.process(name))
				.collect(Collectors.toList());
		return processedNames.toArray(new Name[processedNames.size()]);
	}

}
