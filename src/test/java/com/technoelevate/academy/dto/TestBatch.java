/**
 * 
 */
package com.technoelevate.academy.dto;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Sahid
 *
 */
public class TestBatch {

	private String jsonObject="{\"batchId\":2,\"batchType\":\"Internal\",\"location\":\"Bangalore\",\"technology\":\"Spring Boot\",\"date\":\"2021-12-22\",\"tyMentor\":[\"Bharat\",\"xyz\"],\"files\":{\"fileId\":0,\"fileName\":null,\"fileType\":null,\"filePath\":null},\"mentors\":[{\"clientMentorId\":100,\"clientMentorName\":\"sahid\",\"designation\":\"SW\",\"contactNo\":8451254754,\"emailId\":\"sa123@gmail.com\"}],\"trainers\":[{\"trainerId\":100,\"trainerName\":\"Nilim\",\"days\":\"Friday\",\"emailId\":\"nilim123@gmail.com\",\"technology\":[\"Java\",\"Jdbc\"]}],\"candidates\":[{\"candidateId\":100,\"name\":\"Sahid\",\"branch\":\"BTM\",\"contactNo\":8547584541,\"email\":\"sahid123@gmail.com\",\"degree\":\"BE\",\"stream\":\"ENT\",\"passoutYear\":2019,\"tenthPercentage\":65.0,\"twelvethPercentage\":67.0,\"degreeAggregate\":74.0,\"masterAggregate\":0.0}]}";
	private ObjectMapper mapper;
	
	@BeforeEach
	public void setup() {
		this.mapper = new ObjectMapper();
		this.mapper.findAndRegisterModules();
	}

	@Test
	public void testBatchSerializable() throws JsonMappingException, JsonProcessingException {
		Batch batch = mapper.readValue(jsonObject, Batch.class);
		String jsonObject = mapper.writeValueAsString(batch);
		assertEquals(this.jsonObject, jsonObject);
	}
	@Test
	public void testBatchDeserializable() throws JsonMappingException, JsonProcessingException {
		Batch batch = mapper.readValue(jsonObject, Batch.class);
		int expected=2;
		assertEquals(expected, batch.getBatchId());
	}

}

//FileUpload files = new FileUpload();
//Mentor mentor = new Mentor(100, "sahid", "SW", 8451254754l, "sa123@gmail.com");
//List<Mentor> mentors = new ArrayList<>();
//List<Trainer> trainers = new ArrayList<>();
//List<Candidate> candidates = new ArrayList<>();
//mentors.add(mentor);
//String[] technology = { "Java", "Jdbc" };
//String[] tymentor = { "Bharat", "xyz" };
//Trainer trainer = new Trainer(100, "Nilim", "Friday", "nilim123@gmail.com", technology);
//trainers.add(trainer);
//Candidate candidate = new Candidate(100, "Sahid", "BTM", 8547584541l, "sahid123@gmail.com", "BE", "ENT", 2019,
//		65, 67, 74, 0);
//candidates.add(candidate);
//LocalDate localDate = LocalDate.now();
//Batch batch = new Batch(2, "Internal", "Bangalore", "Spring Boot", localDate, tymentor, files, mentors,
//		trainers, candidates);
