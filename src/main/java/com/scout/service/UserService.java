package com.scout.service;

import java.util.List;

import com.scout.dto.LoginRequest;
import com.scout.dto.LoginResponse;
import com.scout.dto.StudentRequest;
import com.scout.dto.StudentResponse;
import com.scout.dto.TeacherRequest;
import com.scout.dto.TeacherResponse;
import com.scout.dto.EvaluatorRequest;
import com.scout.dto.EvaluatorResponse;

public interface UserService {

	List<TeacherResponse> getTeachers();
	
	TeacherResponse getTeacher(String teacherId);
	
	LoginResponse loginTeacher(LoginRequest loginRequest);
	
	TeacherResponse insertTeacher(TeacherRequest teacherRequest);
	
	TeacherResponse updateTeacher(TeacherRequest teacherRequest);
	
	Boolean deleteTeacher(String teacherId);
	
	// View All Students: Displays the complete list of students.
	List<StudentResponse> getStudents();
	
	// Get One Student: Retrieves information student based on their unique identifier.
	StudentResponse getStudent(String studentId);
	
	LoginResponse loginStudent(LoginRequest loginRequest); 
	
	StudentResponse insertStudent(StudentRequest studentRequest);
	
	StudentResponse updateStudent(StudentRequest studentRequest);
	
	Boolean deleteStudent(String studentId);
	
	// View All Evaluator: Displays the complete list of students.
	List<EvaluatorResponse> getEvaluators();
	
	// Get One Evaluator: Retrieves information Evaluator based on their unique identifier.
	EvaluatorResponse getEvaluator(String evaluatorId);
	
	LoginResponse loginEvaluator(LoginRequest loginRequest); 
	
	EvaluatorResponse insertEvaluator(EvaluatorRequest evaluatorRequest);
	
	EvaluatorResponse updateEvaluator(EvaluatorRequest evaluatorRequest);
	
	Boolean deleteEvaluator(String evaluatorId);
}
