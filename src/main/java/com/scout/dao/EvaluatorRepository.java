package com.scout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scout.entity.EvaluatorEntity;

@Repository
public interface EvaluatorRepository extends JpaRepository<EvaluatorEntity, Long> {
	
	EvaluatorEntity findByEvaluatorId(String evaluatorId);
	
	EvaluatorEntity findByEvaluatorEmail(String evaluatorEmail);
	
	Integer deleteByEvaluatorId(String evaluatorId);
}
