package com.technoelevate.academy.controller;

import static com.technoelevate.academy.common.BatchConstants.BATCH_FAIL_SAVE_MESSAGE;
import static com.technoelevate.academy.common.BatchConstants.BATCH_INVALID_ID;
import static com.technoelevate.academy.common.BatchConstants.BATCH_SAVE_SUCCESSFULL_MESSAGE;
import static com.technoelevate.academy.common.BatchConstants.DELETE_BATCH_SUCCESSFUL_MESSAGE;
import static com.technoelevate.academy.common.BatchConstants.FETCH_BATCH_SUCCESSFUL_MESSAGE;
import static com.technoelevate.academy.common.BatchConstants.SOMETHING_WENT_WRONG;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technoelevate.academy.dto.Batch;
import com.technoelevate.academy.exception.BatchException;
import com.technoelevate.academy.pojo.BatchPojo;
import com.technoelevate.academy.response.ResponseMessage;
import com.technoelevate.academy.service.BatchService;

@RestController
@RequestMapping(path = "/api/v1/academy")
public class BatchController {

	@Autowired
	private BatchService batchService;

	@Autowired
	private ObjectMapper mappper;

	@PostMapping(path = "/batch")
	public ResponseEntity<ResponseMessage> addBatch(@RequestParam("file") MultipartFile file,
			@RequestParam("data") String batch0) {
		Batch batch = new Batch();
		try {
			batch = mappper.readValue(batch0, Batch.class);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new BatchException(SOMETHING_WENT_WRONG);
		}
		BatchPojo saveBatch = batchService.saveBatch(file, batch);
		if (saveBatch != null) {
			return new ResponseEntity<>(
					new ResponseMessage(false, BATCH_SAVE_SUCCESSFULL_MESSAGE, saveBatch), HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(true, BATCH_FAIL_SAVE_MESSAGE, saveBatch),
				HttpStatus.BAD_REQUEST);
	}

	@GetMapping(path = "/batch/{id}")
	public ResponseEntity<ResponseMessage> getBatch(@PathVariable("id") int id) {
		Batch batch = batchService.getBatch(id);
		if (batch != null) {
			return new ResponseEntity<>(
					new ResponseMessage(false, FETCH_BATCH_SUCCESSFUL_MESSAGE, batch), HttpStatus.OK);

		}
		return new ResponseEntity<>(new ResponseMessage(true, BATCH_INVALID_ID, batch),
				HttpStatus.BAD_REQUEST);

	}

	@DeleteMapping(path = "/batch/{id}")
	public ResponseEntity<ResponseMessage> deleteBatch(@PathVariable("id") int id) {
		Batch batch = batchService.deleteBatch(id);
		if (batch != null) {
			return new ResponseEntity<>(
					new ResponseMessage(false, DELETE_BATCH_SUCCESSFUL_MESSAGE, batch), HttpStatus.OK);

		}
		return new ResponseEntity<>(new ResponseMessage(true, BATCH_INVALID_ID, batch),
				HttpStatus.BAD_REQUEST);

	}

}
