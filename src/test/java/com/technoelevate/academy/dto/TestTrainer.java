/**
 * 
 */
package com.technoelevate.academy.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Sahid
 *
 */
public class TestTrainer {

	private String jsonObject="{\"trainerId\":100,\"trainerName\":\"Nilim\",\"days\":\"Friday\",\"emailId\":\"nilim123@gmail.com\",\"technology\":[\"Java\",\"Jdbc\"]}";
	private ObjectMapper mapper;
	
	@BeforeEach
	public void setup() {
		this.mapper = new ObjectMapper();
		this.mapper.findAndRegisterModules();
	}

	@Test
	public void testTrainerSerializable() throws JsonMappingException, JsonProcessingException {
		Trainer trainer = mapper.readValue(jsonObject, Trainer.class);
		String jsonObject = mapper.writeValueAsString(trainer);
		assertEquals(this.jsonObject, jsonObject);
	}
	@Test
	public void testTrainerDeserializable() throws JsonMappingException, JsonProcessingException {
		Trainer trainer = mapper.readValue(jsonObject, Trainer.class);
		int expected=100;
		assertEquals(expected, trainer.getTrainerId());
	}
}
