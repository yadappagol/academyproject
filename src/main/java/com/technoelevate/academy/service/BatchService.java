package com.technoelevate.academy.service;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import com.technoelevate.academy.dto.Batch;
import com.technoelevate.academy.pojo.BatchPojo;

public interface BatchService {
	
	public BatchPojo saveBatch(MultipartFile file, @Valid  Batch batch);
	
	public Batch getBatch(int id);
	
	public Batch deleteBatch(int id);
	
}
