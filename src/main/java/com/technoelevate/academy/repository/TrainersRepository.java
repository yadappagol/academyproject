package com.technoelevate.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technoelevate.academy.dto.Trainer;

public interface TrainersRepository extends JpaRepository<Trainer, Integer>{

}
