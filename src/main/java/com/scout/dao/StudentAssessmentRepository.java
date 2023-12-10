package com.scout.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scout.entity.StudentAssessmentEntity;

@Repository 
public interface StudentAssessmentRepository extends JpaRepository<StudentAssessmentEntity, Long> {
	
	StudentAssessmentEntity findByStudentAssessmentId(String studentAssessmentId);
	
	StudentAssessmentEntity findByStudentIdAndAssessmentId(String studentId, String assessmentId);
		
	Integer deleteByStudentAssessmentId(String studentAssessmentId);
}
