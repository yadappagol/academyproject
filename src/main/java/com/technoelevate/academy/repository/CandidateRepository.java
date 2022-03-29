package com.technoelevate.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technoelevate.academy.dto.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Integer>{

}
