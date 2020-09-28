package com.leadiro.starter.service.starter.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.leadiro.starter.service.starter.dto.Name;

@Component
public class NameBuilder {

	private static final List<String> REMOVE_STRINGS = Arrays.asList(new String[] { "CSAR", "DR", "BSC" });

	public Name process(String name) {
		String punctDigit = "[\\p{Punct}\\p{Digit}]";
		Pattern punctDigitPattern = Pattern.compile(punctDigit);
		Name processedName = new Name();
		name = name.toUpperCase();
		if (name.contains(",")) {
			name = reverse(name).trim();
		}
		for (String string : REMOVE_STRINGS) {
			if (name.contains(string)) {
				name = name.replace(string, "");
			}
		}
		String[] names = name.split("\\s+");
		List<String> validNames = Arrays.asList(names).stream().filter(punctDigitPattern.asPredicate().negate())
				.filter(s -> s.length() > 1).map(s -> capitalization(s)).collect(Collectors.toList());
		if (validNames != null) {
			if (validNames.size() > 0) {
				processedName.setFisrt(validNames.get(0));
			} else {
				processedName = null;
			}
			if (validNames.size() > 1) {
				processedName.setLast(validNames.get(1));
			} else {
				processedName = null;
			}
		}
		return processedName;
	}
	
	private String reverse(String name) {
		String last = name.substring(0, name.indexOf(",")).trim();
		String first = name.substring(name.indexOf(",") + 1, name.length()).trim();
		name = first + " " + last;
		return name;
	}

	private String capitalization(String name) {
		name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
		return name;
	}

}
