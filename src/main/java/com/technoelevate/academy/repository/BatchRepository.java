package com.technoelevate.academy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.technoelevate.academy.dto.Batch;

public interface BatchRepository extends JpaRepository<Batch, Integer> {
	public Batch findByBatchId(int id);

	@Query(value = "SELECT p.status , p.email FROM technoelevate_ess_lite.Person p", nativeQuery = true)
	List<String> getPersonStatus();
}

/*
 * select `status` from `technoelevate_ess_lite`.`person`
 * 
 */
