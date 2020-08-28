package com.tirmizee.jpa.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Converter
public class ProductCodeConverter implements AttributeConverter<String, String> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductCodeConverter.class);
	
	@Override
	public String convertToDatabaseColumn(String attribute) {
		LOGGER.info("convertToDatabaseColumn");
		return attribute;
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		LOGGER.info("convertToEntityAttribute");
		return dbData;
	}

}
