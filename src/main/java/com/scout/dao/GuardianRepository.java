package com.scout.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scout.entity.GuardianEntity;

public interface GuardianRepository extends JpaRepository<GuardianEntity, Long> {

	GuardianEntity findByGuardianId(String guardianId);

	GuardianEntity findByStudentId(String studentId);
	
	Integer deleteByGuardianId(String guardianId);
}
