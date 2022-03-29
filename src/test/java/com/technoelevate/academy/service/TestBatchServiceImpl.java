///**
// * 
// */
//package com.technoelevate.academy.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.core.env.Environment;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockMultipartFile;
//
//import com.technoelevate.academy.dto.Batch;
//import com.technoelevate.academy.dto.Candidate;
//import com.technoelevate.academy.dto.FileUpload;
//import com.technoelevate.academy.dto.Mentor;
//import com.technoelevate.academy.dto.Trainer;
//import com.technoelevate.academy.repository.BatchRepository;
//import com.technoelevate.academy.repository.CandidateRepository;
//import com.technoelevate.academy.repository.ClientMentorRepository;
//import com.technoelevate.academy.repository.FileUploadRepository;
//import com.technoelevate.academy.repository.TrainersRepository;
//
///**
// * @author Sahid
// *
// */
//@SpringBootTest
//public class TestBatchServiceImpl {
//
//	@Mock
//	private BatchRepository batchRepository;
//
//	@Mock
//	private CandidateRepository candidateRepository;
//
//	@Mock
//	private ClientMentorRepository clientMentorRepository;
//
//	@Mock
//	private FileUploadRepository fileUploadRepository;
//
//	@Mock
//	private TrainersRepository trainersRepository;
//
//	@Mock
//	private Environment environment;
//	
//
//	@InjectMocks
//	private BatchServiceImpl batchServiceImpl;
//
//	/**
//	 * Test method for
//	 * {@link com.technoelevate.academy.service.BatchServiceImpl#saveBatch(org.springframework.web.multipart.MultipartFile, com.technoelevate.academy.dto.Batch)}.
//	 */
//	@Test
//	public void testSaveBatch() {
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
//		Batch batch = new Batch(0, "Internal", "Bangalore", "Spring Boot", localDate, tymentor, files, mentors,
//				trainers, candidates);
//		MockMultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.APPLICATION_JSON_VALUE,
//				"Hello, World!".getBytes());
//		Mockito.when(batchRepository.save(Mockito.any())).thenReturn(batch);
//		Batch batch2 = batchServiceImpl.saveBatch(file, batch);
//		assertEquals(batch2.getDate(), batch.getDate());
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.technoelevate.academy.service.BatchServiceImpl#getBatch(int)}.
//	 */
//	@Test
//	public void testGetBatch() {
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
//		Mockito.when(batchRepository.findByBatchId(Mockito.anyInt())).thenReturn(batch);
//		Batch batch2 = batchServiceImpl.getBatch(batch.getBatchId());
//		assertEquals(batch2.getDate(), batch.getDate());
//
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.technoelevate.academy.service.BatchServiceImpl#deleteBatch(int)}.
//	 */
//	@Test
//	public void testDeleteBatch() {
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
//		Batch batch = new Batch(5, "Internal", "Bangalore", "Spring Boot", localDate, tymentor, files, mentors,
//				trainers, candidates);
//		String dir="E:\\\\technoelevate\\\\";
//		Mockito.when(batchRepository.findByBatchId(Mockito.anyInt())).thenReturn(batch);
//		Mockito.when(environment.getProperty(Mockito.anyString())).thenReturn(dir);
//		Batch batch2 = batchServiceImpl.deleteBatch(batch.getBatchId());
//		assertEquals(batch2.getDate(), batch.getDate());
//	}
//
//}
