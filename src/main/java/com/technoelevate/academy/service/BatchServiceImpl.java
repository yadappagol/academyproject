package com.technoelevate.academy.service;

import static com.technoelevate.academy.common.BatchConstants.BATCH_INVALID_ID;
import static com.technoelevate.academy.common.BatchConstants.FILE_NOT_FOUND;
import static com.technoelevate.academy.common.BatchConstants.SOMETHING_WENT_WRONG;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.technoelevate.academy.dto.Batch;
import com.technoelevate.academy.dto.Candidate;
import com.technoelevate.academy.dto.FileUpload;
import com.technoelevate.academy.exception.BatchException;
import com.technoelevate.academy.exception.FileNotFoundException;
import com.technoelevate.academy.pojo.BatchPojo;
import com.technoelevate.academy.pojo.DataObject;
import com.technoelevate.academy.pojo.Person;
import com.technoelevate.academy.repository.BatchRepository;

@Service
@Validated
@Transactional
public class BatchServiceImpl implements BatchService {

	@Autowired
	private BatchRepository batchRepository;

	@Autowired
	private Environment environment;

	@Autowired
	private RestTemplate restTemplate;

	private Batch batch2;
	private BatchPojo batchPojo=new BatchPojo();
	private Path dirLocation;

	private Path getPath(String fileName) {
		String dir = environment.getProperty("file.upload.location") + "\\" + fileName;
		this.dirLocation = Paths.get(dir).toAbsolutePath().normalize();
		return this.dirLocation;
	}

	@Override
	public BatchPojo saveBatch(MultipartFile file, @Valid Batch batch) {
		final String uri = "http://localhost:8081/api/v1/file-upload/persons";
		DataObject data = restTemplate.getForObject(uri, DataObject.class);
		if (data != null) {
			batchPojo = getpersons(data, batch);
		}
		if (batchPojo.getCandidateSelected().isEmpty()) {
			return batchPojo;
//			throw new BatchException(BATCH_CAN_NOT_BE_ADD);
		}
		String batchPath = "Batch";
		try {
			if (batch.getBatchId() != 0 && file != null) {
				Batch batch0 = batchRepository.findByBatchId(batch.getBatchId());
				if (batch0 != null) {
					String pathString = batchPath + batch0.getBatchId();
					this.dirLocation = getPath(pathString);
					FileUtils.forceDelete(new java.io.File(this.dirLocation.toString()));
				} else
					throw new BatchException(BATCH_INVALID_ID);
			}
			batch.setCandidates(batchPojo.getCandidateSelected());
			if (batch.getBatchType().equalsIgnoreCase("Internal")) {
				batch2 = batchRepository.save(batch);
			} else {
				batch.setMentors(null);
				batch2 = batchRepository.save(batch);
			}
			this.dirLocation = getPath(batchPath + batch2.getBatchId());
			if (file != null) {
				Files.createDirectories(this.dirLocation);
				String filename = file.getOriginalFilename();
				Path path = this.dirLocation.resolve(filename);
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				FileUpload fileUpload = new FileUpload(filename, file.getContentType(), path.toString());
				batch.setFiles(fileUpload);
			} else {
				throw new FileNotFoundException(FILE_NOT_FOUND);
			}
			batchPojo.setBatchId(batch2.getBatchId());
			return batchPojo;
		} catch (FileNotFoundException | BatchException | IOException exception) {
			throw new FileNotFoundException(exception.getMessage());
		} catch (Exception exception) {
			throw new BatchException(SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Batch getBatch(int id) {
		try {
			return batchRepository.findByBatchId(id);
		} catch (Exception exception) {
			throw new BatchException(BATCH_INVALID_ID);
		}
	}

	@Override
	public Batch deleteBatch(int id) {
		try {
			Batch batch = batchRepository.findByBatchId(id);
			batchRepository.delete(batch);
			if (batch.getBatchId() != 0) {
				Batch batch0 = batchRepository.findByBatchId(batch.getBatchId());
				if (batch0 != null) {
					String pathString = "Batch" + batch0.getBatchId();
					this.dirLocation = getPath(pathString);
					FileUtils.forceDelete(new java.io.File(this.dirLocation.toString()));
				}
			}
			return batch;
		} catch (FileNotFoundException | IOException exception) {
			throw new FileNotFoundException(FILE_NOT_FOUND);
		} catch (Exception exception) {
			throw new BatchException(BATCH_INVALID_ID);
		}
	}

	@SuppressWarnings("unchecked")
	private BatchPojo getpersons(DataObject data, Batch batch) {
		List<Map<String, String>> persons = (List<Map<String, String>>) data.getData();
		List<Candidate> candidates = batch.getCandidates();
		List<Candidate> candidateSelected = new ArrayList<>();
		List<Candidate> candidateNotSelected = new ArrayList<>();
		Candidate candidate = null;
		BeanUtils.copyProperties(batch, batchPojo);
		for (Map<String, String> map : persons) {
			Person person2 = new Person();
			for (Map.Entry<String, String> person : map.entrySet()) {
				if (person.getKey().equalsIgnoreCase("status")) {
					person2.setStatus(person.getValue());
				}
				if (person.getKey().equalsIgnoreCase("email")) {
					person2.setEmail(person.getValue());
				}
			}
			for (int i = 0; i < candidates.size(); i++) {
				candidate = candidates.get(i);
				if (candidate.getEmail().equalsIgnoreCase(person2.getEmail())
						&& person2.getStatus().equalsIgnoreCase("selected")) {
					candidateSelected.add(candidate);
					break;
				}
				if (person2.getStatus().equalsIgnoreCase("hold")) {
					candidateNotSelected.add(candidate);
					break;
				}
			}
			
		}
		batchPojo.setCandidateSelected(candidateSelected);
		batchPojo.setCandidateNotSelected(candidateNotSelected);
		return batchPojo;
	}
}
