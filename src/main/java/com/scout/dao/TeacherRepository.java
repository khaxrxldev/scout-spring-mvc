package com.scout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scout.entity.TeacherEntity;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {

	TeacherEntity findByTeacherId(String teacherId);
	
	TeacherEntity findByTeacherEmail(String teacherEmail);
	
	Integer deleteByTeacherId(String teacherId);
}
