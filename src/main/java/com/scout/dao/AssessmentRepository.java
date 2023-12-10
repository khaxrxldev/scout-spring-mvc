package com.scout.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scout.entity.AssessmentEntity;

@Repository
public interface AssessmentRepository extends JpaRepository<AssessmentEntity, Long> {
	
	AssessmentEntity findByAssessmentId(String assessmentId);
		
	Integer deleteByAssessmentId(String assessmentId);

}
