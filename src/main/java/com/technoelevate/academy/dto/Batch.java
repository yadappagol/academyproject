package com.technoelevate.academy.dto;

import static com.technoelevate.academy.common.BatchConstants.BATCH_TYPE_CANNOT_BE_BLANK;
import static com.technoelevate.academy.common.BatchConstants.DATE_SHOULD_BE_FUTURE_OR_PRESENT;
import static com.technoelevate.academy.common.BatchConstants.LOCTION_CANNOT_BE_BLANK;
import static com.technoelevate.academy.common.BatchConstants.MENTORS_CANNOT_BE_BLANK;
import static com.technoelevate.academy.common.BatchConstants.TECHNOLOGY_CANNOT_BE_BLANK;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.technoelevate.academy.converter.StringListConverter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@SuppressWarnings("serial")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Batch implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int batchId;
	
	@NotBlank(message = BATCH_TYPE_CANNOT_BE_BLANK)
	@Column(name = "batchType")
	private String batchType;
	
	@NotBlank(message = LOCTION_CANNOT_BE_BLANK)
	@Column(name = "location")
	private String location;
	
	@NotBlank(message = TECHNOLOGY_CANNOT_BE_BLANK)
	private String technology;
	
	
	@FutureOrPresent(message = DATE_SHOULD_BE_FUTURE_OR_PRESENT)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	private LocalDate date;
	
	
	@NotEmpty(message = MENTORS_CANNOT_BE_BLANK)
	@Convert(converter = StringListConverter.class)
	private String[] tyMentor;

	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "batch_id")
	@Valid
	private FileUpload files;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "batch_id")
	private List<@Valid  Mentor> mentors;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "batch_id")
	private List<@Valid  Trainer> trainers;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "batch_id")
	private List<@Valid  Candidate> candidates;


	
}
