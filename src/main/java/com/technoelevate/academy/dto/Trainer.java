package com.technoelevate.academy.dto;

import static com.technoelevate.academy.common.BatchConstants.DAY_CANNOT_BE_BLANK;
import static com.technoelevate.academy.common.BatchConstants.EMAIL_VALIDATION;
import static com.technoelevate.academy.common.BatchConstants.ENTER_THE_CORRECT_EMAIL;
import static com.technoelevate.academy.common.BatchConstants.TECHNOLOGY_CANNOT_BE_BLANK;
import static com.technoelevate.academy.common.BatchConstants.TRAINER_NAME_CANNOT_BE_BLANK;

import java.io.Serializable;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.transaction.annotation.Transactional;

import com.technoelevate.academy.converter.StringListConverter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings({ "serial" })
@Data
@Transactional
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Trainer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int trainerId;

	@NotBlank(message = TRAINER_NAME_CANNOT_BE_BLANK)
	private String trainerName;

	@NotBlank(message = DAY_CANNOT_BE_BLANK)
	private String days;

	@Pattern(regexp = EMAIL_VALIDATION, message = ENTER_THE_CORRECT_EMAIL)
	private String emailId;

	@NotEmpty(message = TECHNOLOGY_CANNOT_BE_BLANK)
	@Convert(converter = StringListConverter.class)
	private String[] technology;
}
