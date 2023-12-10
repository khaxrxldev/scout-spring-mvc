package com.scout.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scout.entity.StudentBadgeEntity;

@Repository
public interface StudentBadgeRepository extends JpaRepository<StudentBadgeEntity, Long> {
	
	StudentBadgeEntity findByStudentBadgeId(String studentBadgeId);
	
	List<StudentBadgeEntity> findByStudentId(String studentId);
	
	Integer deleteByStudentBadgeId(String studentBadgeId);	
}
