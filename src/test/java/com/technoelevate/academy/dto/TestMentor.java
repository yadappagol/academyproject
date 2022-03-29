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
public class TestMentor {


	private String jsonObject="{\"clientMentorId\":100,\"clientMentorName\":\"sahid\",\"designation\":\"SW\",\"contactNo\":8451254754,\"emailId\":\"sa123@gmail.com\"}";
	private ObjectMapper mapper;
	
	@BeforeEach
	public void setup() {
		this.mapper = new ObjectMapper();
		this.mapper.findAndRegisterModules();
	}

	@Test
	public void testMentorSerializable() throws JsonMappingException, JsonProcessingException {
		Mentor mentor = mapper.readValue(jsonObject, Mentor.class);
		String jsonObject = mapper.writeValueAsString(mentor);
		assertEquals(this.jsonObject, jsonObject);
	}
	@Test
	public void testMentorDeserializable() throws JsonMappingException, JsonProcessingException {
		Mentor mentor = mapper.readValue(jsonObject, Mentor.class);
		int expected=100;
		assertEquals(expected, mentor.getClientMentorId());
	}

}
