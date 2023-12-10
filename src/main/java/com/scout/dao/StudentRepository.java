package com.scout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scout.entity.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

	StudentEntity findByStudentId(String studentId);
	
	StudentEntity findByStudentEmail(String studentEmail);
	
	Integer deleteByStudentId(String studentId);
}
