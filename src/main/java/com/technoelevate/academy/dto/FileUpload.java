package com.technoelevate.academy.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Setter
@Getter
@Transactional
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FileUpload implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fileId;

	private String fileName;

	private String fileType;

	private String filePath;

	public FileUpload(String fileName, String fileType, String filePath) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.filePath = filePath;
	}

}
