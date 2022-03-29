///**
// * 
// */
//package com.technoelevate.academy.controller;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
//
//import java.io.UnsupportedEncodingException;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.technoelevate.academy.dto.Batch;
//import com.technoelevate.academy.dto.Candidate;
//import com.technoelevate.academy.dto.FileUpload;
//import com.technoelevate.academy.dto.Mentor;
//import com.technoelevate.academy.dto.Trainer;
//import com.technoelevate.academy.response.ResponseMessage;
//import com.technoelevate.academy.service.BatchService;
//
///**
// * @author Sahid
// *
// */
//@SpringBootTest
//public class TestBatchController {
//
//	@Mock
//	private BatchService batchService;
//
//	@InjectMocks
//	private BatchController batchController;
//
//	private MockMvc mockMvc;
//	@Mock
//	private ObjectMapper mapper;
//	private ObjectMapper mapperTest;
//
//	@BeforeEach
//	public void setup() {
//		this.mockMvc = MockMvcBuilders.standaloneSetup(batchController).build();
//		this.mapperTest = new ObjectMapper();
//		this.mapperTest.findAndRegisterModules();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.technoelevate.academy.controller.BatchController#addBatch(org.springframework.web.multipart.MultipartFile, java.lang.String)}.
//	 * 
//	 * @throws Exception
//	 * @throws UnsupportedEncodingException
//	 */
//	@SuppressWarnings("unchecked")
//	@Test
//	public void testAddBatch() throws UnsupportedEncodingException, Exception {
//		FileUpload files = new FileUpload();
//		Mentor mentor = new Mentor(100, "sahid", "SW", 8451254754l, "sa123@gmail.com");
//		List<Mentor> mentors = new ArrayList<>();
//		List<Trainer> trainers = new ArrayList<>();
//		List<Candidate> candidates = new ArrayList<>();
//		mentors.add(mentor);
//		String[] technology = { "Java", "Jdbc" };
//		String[] tymentor = { "Bharat", "xyz" };
//		Trainer trainer = new Trainer(100, "Nilim", "Friday", "nilim123@gmail.com", technology);
//		trainers.add(trainer);
//		Candidate candidate = new Candidate(100, "Sahid", "BTM", 8547584541l, "sahid123@gmail.com", "BE", "ENT", 2019,
//				65, 67, 74, 0);
//		candidates.add(candidate);
//		LocalDate localDate = LocalDate.now();
//		Batch batch = new Batch(100, "Internal", "Bangalore", "Spring Boot", localDate, tymentor, files, mentors,
//				trainers, candidates);
//		MockMultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.APPLICATION_JSON_VALUE,
//				"Hello, World!".getBytes());
//		Mockito.when(batchService.saveBatch(Mockito.any(), Mockito.any())).thenReturn(batch);
//		String jsonObject = mapperTest.writeValueAsString(batch);
//		String result = mockMvc.perform(multipart("/api/v1/academy/batch").file(file).param("data", jsonObject))
//				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
//		ResponseMessage responseMessage = mapperTest.readValue(result, ResponseMessage.class);
//		Map<String, String> responseMessageMap = (Map<String, String>) responseMessage.getData();
//		for (Map.Entry<String, String> message : responseMessageMap.entrySet()) {
//			assertEquals(batch.getBatchId(), message.getValue());
//			break;
//		}
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.technoelevate.academy.controller.BatchController#getBatch(int)}.
//	 * 
//	 * @throws Exception
//	 * @throws UnsupportedEncodingException
//	 */
//	@SuppressWarnings("unchecked")
//	@Test
//	public void testGetBatch() throws UnsupportedEncodingException, Exception {
//		FileUpload files = new FileUpload();
//		Mentor mentor = new Mentor(100, "sahid", "SW", 8451254754l, "sa123@gmail.com");
//		List<Mentor> mentors = new ArrayList<>();
//		List<Trainer> trainers = new ArrayList<>();
//		List<Candidate> candidates = new ArrayList<>();
//		mentors.add(mentor);
//		String[] technology = { "Java", "Jdbc" };
//		String[] tymentor = { "Bharat", "xyz" };
//		Trainer trainer = new Trainer(100, "Nilim", "Friday", "nilim123@gmail.com", technology);
//		trainers.add(trainer);
//		Candidate candidate = new Candidate(100, "Sahid", "BTM", 8547584541l, "sahid123@gmail.com", "BE", "ENT", 2019,
//				65, 67, 74, 0);
//		candidates.add(candidate);
//		LocalDate localDate = LocalDate.now();
//		Batch batch = new Batch(100, "Internal", "Bangalore", "Spring Boot", localDate, tymentor, files, mentors,
//				trainers, candidates);
//		Mockito.when(batchService.getBatch(Mockito.anyInt())).thenReturn(batch);
//		String result = mockMvc.perform(get("/api/v1/academy/batch/" + batch.getBatchId()))
//				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
//		ResponseMessage responseMessage = mapperTest.readValue(result, ResponseMessage.class);
//		Map<String, String> responseMessageMap = (Map<String, String>) responseMessage.getData();
//		for (Map.Entry<String, String> message : responseMessageMap.entrySet()) {
//			assertEquals(batch.getBatchId(), message.getValue());
//			break;
//		}
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.technoelevate.academy.controller.BatchController#deleteBatch(int)}.
//	 * @throws Exception 
//	 * @throws UnsupportedEncodingException 
//	 */
//	@SuppressWarnings("unchecked")
//	@Test
//	public void testDeleteBatch() throws UnsupportedEncodingException, Exception {
//		FileUpload files = new FileUpload();
//		Mentor mentor = new Mentor(100, "sahid", "SW", 8451254754l, "sa123@gmail.com");
//		List<Mentor> mentors = new ArrayList<>();
//		List<Trainer> trainers = new ArrayList<>();
//		List<Candidate> candidates = new ArrayList<>();
//		mentors.add(mentor);
//		String[] technology = { "Java", "Jdbc" };
//		String[] tymentor = { "Bharat", "xyz" };
//		Trainer trainer = new Trainer(100, "Nilim", "Friday", "nilim123@gmail.com", technology);
//		trainers.add(trainer);
//		Candidate candidate = new Candidate(100, "Sahid", "BTM", 8547584541l, "sahid123@gmail.com", "BE", "ENT", 2019,
//				65, 67, 74, 0);
//		candidates.add(candidate);
//		LocalDate localDate = LocalDate.now();
//		Batch batch = new Batch(100, "Internal", "Bangalore", "Spring Boot", localDate, tymentor, files, mentors,
//				trainers, candidates);
//		Mockito.when(batchService.deleteBatch(Mockito.anyInt())).thenReturn(batch);
//		String result = mockMvc.perform(delete("/api/v1/academy/batch/" + batch.getBatchId()))
//				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
//		ResponseMessage responseMessage = mapperTest.readValue(result, ResponseMessage.class);
//		Map<String, String> responseMessageMap = (Map<String, String>) responseMessage.getData();
//		for (Map.Entry<String, String> message : responseMessageMap.entrySet()) {
//			assertEquals(batch.getBatchId(), message.getValue());
//			break;
//		}
//	}
//
//}
