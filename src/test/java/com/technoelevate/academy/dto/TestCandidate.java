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
public class TestCandidate {

	private String jsonObject = "{\"candidateId\":100,\"name\":\"Sahid\",\"branch\":\"BTM\",\"contactNo\":8547584541,\"email\":\"sahid123@gmail.com\",\"degree\":\"BE\",\"stream\":\"ENT\",\"passoutYear\":2019,\"tenthPercentage\":65.0,\"twelvethPercentage\":67.0,\"degreeAggregate\":74.0,\"masterAggregate\":0.0}";
	private ObjectMapper mapper;

	@BeforeEach
	public void setup() {
		this.mapper = new ObjectMapper();
		this.mapper.findAndRegisterModules();
	}

	@Test
	public void testCandidateSerializable() throws JsonMappingException, JsonProcessingException {
		Candidate candidate = mapper.readValue(jsonObject, Candidate.class);
		String jsonObject = mapper.writeValueAsString(candidate);
		assertEquals(this.jsonObject, jsonObject);
	}

	@Test
	public void testCandidateDeserializable() throws JsonMappingException, JsonProcessingException {
		Candidate candidate = mapper.readValue(jsonObject, Candidate.class);
		int expected = 100;
		assertEquals(expected, candidate.getCandidateId());
	}

}
