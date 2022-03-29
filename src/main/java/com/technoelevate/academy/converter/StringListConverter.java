package com.technoelevate.academy.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StringListConverter implements AttributeConverter<String[], String> {
	private static final String SPLIT_CHAR = ",";

	@Override
	public String convertToDatabaseColumn(String[] stringList) {
		return stringList != null ? String.join(SPLIT_CHAR, stringList) : "";
	}

	@Override
	public String[] convertToEntityAttribute(String string) {
		return string != null ? string.split(SPLIT_CHAR) : null;
	}
}

/*
 * 
 * 
 * @Converter public class StringListConverter implements
 * AttributeConverter<List<String>, String> { private static final String
 * SPLIT_CHAR = ";";
 * 
 * @Override public String convertToDatabaseColumn(List<String> stringList) {
 * return stringList != null ? String.join(SPLIT_CHAR, stringList) : ""; }
 * 
 * @Override public List<String> convertToEntityAttribute(String string) {
 * return string != null ? Arrays.asList(string.split(SPLIT_CHAR)) :
 * emptyList(); } }
 * 
 * 
 * 
 */
