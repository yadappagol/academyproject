package com.technoelevate.academy.dto;

import static com.technoelevate.academy.common.BatchConstants.*;
import static com.technoelevate.academy.common.BatchConstants.ENTER_THE_CORRECT_NUMBER;
import static com.technoelevate.academy.common.BatchConstants.ENTER_THE_CORRECT_PASSOUT_YEAR;
import static com.technoelevate.academy.common.BatchConstants.ENTER_THE_CORRECT_PERCENTAGE;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings({ "serial", "unused" })
@Setter
@Getter
@Transactional
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Candidate implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int candidateId;
	
	@NotBlank(message = CANDIDATE_NAME_CANNOT_BE_BLANK)
	private String name;

	@NotBlank(message =BRANCH_NAME_CANNOT_BE_BLANK)
	private String branch;

	@Range(min = 6000000000l, max = 9999999999l, message = ENTER_THE_CORRECT_NUMBER)
	private long contactNo;

	@Pattern(regexp = EMAIL_VALIDATION,message = ENTER_THE_CORRECT_EMAIL)
	private String email;

	@NotBlank(message = DEGREE_CANNOT_BE_BLANK)
	private String degree;

	@NotBlank(message = STREAM_CANNOT_BE_BLANK)
	private String stream;

	@Range(min = 2000, max = 2099, message = ENTER_THE_CORRECT_PASSOUT_YEAR)
	private int passoutYear;

	@Digits(integer = 2, fraction = 2, message = ENTER_THE_CORRECT_PERCENTAGE)
	private double tenthPercentage;

	@Digits(integer = 2, fraction = 2, message = ENTER_THE_CORRECT_PERCENTAGE)
	private double twelvethPercentage;

	@Digits(integer = 2, fraction = 2, message = ENTER_THE_CORRECT_PERCENTAGE)
	private double degreeAggregate;

	@Digits(integer = 2, fraction = 2, message = ENTER_THE_CORRECT_PERCENTAGE)
	private double masterAggregate;
	
}
