package com.technoelevate.academy.dto;

import static com.technoelevate.academy.common.BatchConstants.CLIENT_MENTOR_NAME_CANNOT_BE_BLANK;
import static com.technoelevate.academy.common.BatchConstants.DESIGNATION_CANNOT_BE_BLANK;
import static com.technoelevate.academy.common.BatchConstants.EMAIL_VALIDATION;
import static com.technoelevate.academy.common.BatchConstants.ENTER_THE_CORRECT_EMAIL;
import static com.technoelevate.academy.common.BatchConstants.ENTER_THE_CORRECT_NUMBER;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@SuppressWarnings("serial")
@Setter
@Getter
@Transactional
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Mentor implements Serializable {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientMentorId;
	
	
	@NotBlank(message = CLIENT_MENTOR_NAME_CANNOT_BE_BLANK)
	private String clientMentorName;
	
	@NotBlank(message = DESIGNATION_CANNOT_BE_BLANK)
	private String designation;
	
	@Range(min = 6000000000l , max = 9999999999l,message = ENTER_THE_CORRECT_NUMBER)
	private long contactNo;
	
	@Pattern(regexp = EMAIL_VALIDATION,message = ENTER_THE_CORRECT_EMAIL)
	private String emailId;
}
