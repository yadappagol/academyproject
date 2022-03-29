package com.technoelevate.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technoelevate.academy.dto.Mentor;

public interface ClientMentorRepository extends JpaRepository<Mentor, Integer>{

}
