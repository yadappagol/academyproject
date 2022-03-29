package com.technoelevate.academy.pojo;

import static com.technoelevate.academy.common.BatchConstants.BATCH_TYPE_CANNOT_BE_BLANK;
import static com.technoelevate.academy.common.BatchConstants.DATE_SHOULD_BE_FUTURE_OR_PRESENT;
import static com.technoelevate.academy.common.BatchConstants.LOCTION_CANNOT_BE_BLANK;
import static com.technoelevate.academy.common.BatchConstants.MENTORS_CANNOT_BE_BLANK;
import static com.technoelevate.academy.common.BatchConstants.TECHNOLOGY_CANNOT_BE_BLANK;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Convert;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.technoelevate.academy.converter.StringListConverter;
import com.technoelevate.academy.dto.Candidate;
import com.technoelevate.academy.dto.FileUpload;
import com.technoelevate.academy.dto.Mentor;
import com.technoelevate.academy.dto.Trainer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchPojo implements Serializable {
	
	private int batchId;
	
	@NotBlank(message = BATCH_TYPE_CANNOT_BE_BLANK)
	private String batchType;
	
	@NotBlank(message = LOCTION_CANNOT_BE_BLANK)
	private String location;
	
	@NotBlank(message = TECHNOLOGY_CANNOT_BE_BLANK)
	private String technology;
	
	
	@FutureOrPresent(message = DATE_SHOULD_BE_FUTURE_OR_PRESENT)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	private LocalDate date;
	
	
	@NotEmpty(message = MENTORS_CANNOT_BE_BLANK)
	@Convert(converter = StringListConverter.class)
	private String[] tyMentor;

	
	private FileUpload files;
	
	private List<Mentor> mentors;
	
	private List<Trainer> trainers;
	
	private List<Candidate> candidateSelected;
	
	private List<Candidate> candidateNotSelected;
}
