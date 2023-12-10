package com.scout.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scout.dao.StudentRepository;
import com.scout.dao.TeacherRepository;
import com.scout.dao.EvaluatorRepository;
import com.scout.dto.LoginRequest;
import com.scout.dto.LoginResponse;
import com.scout.dto.StudentRequest;
import com.scout.dto.StudentResponse;
import com.scout.dto.TeacherRequest;
import com.scout.dto.TeacherResponse;
import com.scout.dto.EvaluatorRequest;
import com.scout.dto.EvaluatorResponse;
import com.scout.entity.StudentEntity;
import com.scout.entity.TeacherEntity;
import com.scout.entity.EvaluatorEntity;
import com.scout.utility.BaseUtility;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	TeacherRepository teacherRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	EvaluatorRepository evaluatorRepository;
	
	// Teacher 
	@Override
	public List<TeacherResponse> getTeachers() {
		List<TeacherResponse> teacherResponses = new ArrayList<>();
		List<TeacherEntity> teacherEntities = teacherRepository.findAll();
		
		for (TeacherEntity teacherEntity : teacherEntities) {
			TeacherResponse teacherResponse = new TeacherResponse();
			
			teacherResponse.setTeacherId(teacherEntity.getTeacherId());
			teacherResponse.setTeacherEmail(teacherEntity.getTeacherEmail());
			teacherResponse.setTeacherPhoneNo(teacherEntity.getTeacherPhoneNo());
			teacherResponse.setTeacherName(teacherEntity.getTeacherName());
			teacherResponse.setTeacherAddress(teacherEntity.getTeacherAddress());
			teacherResponse.setTeacherPassword(teacherEntity.getTeacherPassword());
			
			teacherResponses.add(teacherResponse);
		}
		
		return teacherResponses;
	}

	@Override
	public TeacherResponse getTeacher(String teacherId) {
		TeacherResponse teacherResponse = new TeacherResponse();
		TeacherEntity teacherEntity = teacherRepository.findByTeacherId(teacherId);
		
		if (BaseUtility.isObjectNotNull(teacherEntity)) {
			teacherResponse.setTeacherId(teacherEntity.getTeacherId());
			teacherResponse.setTeacherEmail(teacherEntity.getTeacherEmail());
			teacherResponse.setTeacherPhoneNo(teacherEntity.getTeacherPhoneNo());
			teacherResponse.setTeacherName(teacherEntity.getTeacherName());
			teacherResponse.setTeacherAddress(teacherEntity.getTeacherAddress());
			teacherResponse.setTeacherPassword(teacherEntity.getTeacherPassword());
		}
		
		return teacherResponse;
	}

	@Override
	public LoginResponse loginTeacher(LoginRequest loginRequest) {
		LoginResponse loginResponse = new LoginResponse();
		TeacherEntity teacherEntity = teacherRepository.findByTeacherEmail(loginRequest.getLoginUsername());
		
		if (BaseUtility.isObjectNotNull(teacherEntity)) {
			if (teacherEntity.getTeacherPassword().equals(loginRequest.getLoginPassword())) {
				loginResponse.setLoginId(teacherEntity.getTeacherId());
				loginResponse.setLoginStatus(true);
				loginResponse.setLoginMessage("success login");
			} else {
				loginResponse.setLoginStatus(false);
				loginResponse.setLoginError("wrong password entered");
			}
		} else {
			loginResponse.setLoginStatus(false);
			loginResponse.setLoginError("user does not exist");
		}
		
		return loginResponse;
	}

	@Override
	public TeacherResponse insertTeacher(TeacherRequest teacherRequest) {
		TeacherResponse teacherResponse = new TeacherResponse();
		TeacherEntity newTeacherEntity = new TeacherEntity();
		
		newTeacherEntity.setTeacherId(BaseUtility.generateId());
		newTeacherEntity.setTeacherEmail(teacherRequest.getTeacherEmail());
		newTeacherEntity.setTeacherPhoneNo(teacherRequest.getTeacherPhoneNo());
		newTeacherEntity.setTeacherName(teacherRequest.getTeacherName());
		newTeacherEntity.setTeacherAddress(teacherRequest.getTeacherAddress());
		newTeacherEntity.setTeacherPassword(teacherRequest.getTeacherPassword());
		
		TeacherEntity insertedTeacherEntity = teacherRepository.save(newTeacherEntity);
		
		if (BaseUtility.isObjectNotNull(insertedTeacherEntity)) {
			teacherResponse.setTeacherId(insertedTeacherEntity.getTeacherId());
			teacherResponse.setTeacherEmail(insertedTeacherEntity.getTeacherEmail());
			teacherResponse.setTeacherPhoneNo(insertedTeacherEntity.getTeacherPhoneNo());
			teacherResponse.setTeacherName(insertedTeacherEntity.getTeacherName());
			teacherResponse.setTeacherAddress(insertedTeacherEntity.getTeacherAddress());
			teacherResponse.setTeacherPassword(insertedTeacherEntity.getTeacherPassword());
		}
		
		return teacherResponse;
	}

	@Override
	public TeacherResponse updateTeacher(TeacherRequest teacherRequest) {
		TeacherResponse teacherResponse = new TeacherResponse();
		TeacherEntity existedTeacherEntity = teacherRepository.findByTeacherId(teacherRequest.getTeacherId());
		
		existedTeacherEntity.setTeacherEmail(teacherRequest.getTeacherEmail());
		existedTeacherEntity.setTeacherPhoneNo(teacherRequest.getTeacherPhoneNo());
		existedTeacherEntity.setTeacherName(teacherRequest.getTeacherName());
		existedTeacherEntity.setTeacherAddress(teacherRequest.getTeacherAddress());
		existedTeacherEntity.setTeacherPassword(teacherRequest.getTeacherPassword());
		
		TeacherEntity updatedTeacherEntity = teacherRepository.save(existedTeacherEntity);
		
		if (BaseUtility.isObjectNotNull(updatedTeacherEntity)) {
			teacherResponse.setTeacherId(updatedTeacherEntity.getTeacherId());
			teacherResponse.setTeacherEmail(updatedTeacherEntity.getTeacherEmail());
			teacherResponse.setTeacherPhoneNo(updatedTeacherEntity.getTeacherPhoneNo());
			teacherResponse.setTeacherName(updatedTeacherEntity.getTeacherName());
			teacherResponse.setTeacherAddress(updatedTeacherEntity.getTeacherAddress());
			teacherResponse.setTeacherPassword(updatedTeacherEntity.getTeacherPassword());
		}
		
		return teacherResponse;
	}

	@Transactional
	public Boolean deleteTeacher(String teacherId) {
		Integer totalDeleted = 0;
		
		try {
			totalDeleted = teacherRepository.deleteByTeacherId(teacherId);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		
		if (totalDeleted > 0) {
			return true;
		}
		
		return false;
	}
	
	// Student function
	@Override
	public List<StudentResponse> getStudents() {
		List<StudentResponse> studentResponses = new ArrayList<>();
		List<StudentEntity> studentEntities = studentRepository.findAll();
		
		for (StudentEntity studentEntity : studentEntities) {
			StudentResponse studentResponse = new StudentResponse();
			
			studentResponse.setStudentId(studentEntity.getStudentId());
			studentResponse.setStudentEmail(studentEntity.getStudentEmail());
			studentResponse.setStudentPhoneNo(studentEntity.getStudentPhoneNo());
			studentResponse.setStudentName(studentEntity.getStudentName());
			studentResponse.setStudentPassword(studentEntity.getStudentPassword());
			studentResponse.setStudentAddress(studentEntity.getStudentAddress());
			studentResponse.setStudentAge(studentEntity.getStudentAge());
			studentResponse.setStudentGender(studentEntity.getStudentGender());
			studentResponse.setStudentIdentificationNo(studentEntity.getStudentIdentificationNo());
			studentResponse.setStudentRace(studentEntity.getStudentRace());
			studentResponse.setStudentReligion(studentEntity.getStudentReligion());
			studentResponse.setStudentBirthDate(studentEntity.getStudentBirthDate());
			
			studentResponses.add(studentResponse);
		}
		
		return studentResponses;
	}

	@Override
	public StudentResponse getStudent(String studentId) {
		StudentResponse studentResponse = new StudentResponse();
		StudentEntity studentEntity = studentRepository.findByStudentId(studentId);
		
		if (BaseUtility.isObjectNotNull(studentEntity)) {
			studentResponse.setStudentId(studentEntity.getStudentId());
			studentResponse.setStudentEmail(studentEntity.getStudentEmail());
			studentResponse.setStudentPhoneNo(studentEntity.getStudentPhoneNo());
			studentResponse.setStudentName(studentEntity.getStudentName());
			studentResponse.setStudentPassword(studentEntity.getStudentPassword());
			studentResponse.setStudentAddress(studentEntity.getStudentAddress());
			studentResponse.setStudentAge(studentEntity.getStudentAge());
			studentResponse.setStudentGender(studentEntity.getStudentGender());
			studentResponse.setStudentIdentificationNo(studentEntity.getStudentIdentificationNo());
			studentResponse.setStudentRace(studentEntity.getStudentRace());
			studentResponse.setStudentReligion(studentEntity.getStudentReligion());
			studentResponse.setStudentBirthDate(studentEntity.getStudentBirthDate());
		}
		
		return studentResponse;
	}

	@Override
	public LoginResponse loginStudent(LoginRequest loginRequest) {
		LoginResponse loginResponse = new LoginResponse();
		StudentEntity studentEntity = studentRepository.findByStudentEmail(loginRequest.getLoginUsername());
		
		if (BaseUtility.isObjectNotNull(studentEntity)) {
			if (studentEntity.getStudentPassword().equals(loginRequest.getLoginPassword())) {
				loginResponse.setLoginId(studentEntity.getStudentId());
				loginResponse.setLoginStatus(true);
				loginResponse.setLoginMessage("success login");
			} else {
				loginResponse.setLoginStatus(false);
				loginResponse.setLoginError("wrong password entered");
			}
		} else {
			loginResponse.setLoginStatus(false);
			loginResponse.setLoginError("user does not exist");
		}
		
		return loginResponse;
	}

	@Override
	public StudentResponse insertStudent(StudentRequest studentRequest) {
		StudentResponse studentResponse = new StudentResponse();
		StudentEntity newStudentEntity = new StudentEntity();
		
		newStudentEntity.setStudentId(BaseUtility.generateId());
		newStudentEntity.setStudentEmail(studentRequest.getStudentEmail());
		newStudentEntity.setStudentPhoneNo(studentRequest.getStudentPhoneNo());
		newStudentEntity.setStudentName(studentRequest.getStudentName());
		newStudentEntity.setStudentPassword(studentRequest.getStudentPassword());
		newStudentEntity.setStudentAddress(studentRequest.getStudentAddress());
		newStudentEntity.setStudentAge(studentRequest.getStudentAge());
		newStudentEntity.setStudentGender(studentRequest.getStudentGender());
		newStudentEntity.setStudentIdentificationNo(studentRequest.getStudentIdentificationNo());
		newStudentEntity.setStudentRace(studentRequest.getStudentRace());
		newStudentEntity.setStudentReligion(studentRequest.getStudentReligion());
		newStudentEntity.setStudentBirthDate(studentRequest.getStudentBirthDate());
		
		StudentEntity insertedStudentEntity = studentRepository.save(newStudentEntity);
		
		if (BaseUtility.isObjectNotNull(insertedStudentEntity)) {
			studentResponse.setStudentId(insertedStudentEntity.getStudentId());
			studentResponse.setStudentEmail(insertedStudentEntity.getStudentEmail());
			studentResponse.setStudentPhoneNo(insertedStudentEntity.getStudentPhoneNo());
			studentResponse.setStudentName(insertedStudentEntity.getStudentName());
			studentResponse.setStudentPassword(insertedStudentEntity.getStudentPassword());
			studentResponse.setStudentAddress(insertedStudentEntity.getStudentAddress());
			studentResponse.setStudentAge(insertedStudentEntity.getStudentAge());
			studentResponse.setStudentGender(insertedStudentEntity.getStudentGender());
			studentResponse.setStudentIdentificationNo(insertedStudentEntity.getStudentIdentificationNo());
			studentResponse.setStudentRace(insertedStudentEntity.getStudentRace());
			studentResponse.setStudentReligion(insertedStudentEntity.getStudentReligion());
			studentResponse.setStudentBirthDate(insertedStudentEntity.getStudentBirthDate());
		}
		
		return studentResponse;
	}

	@Override
	public StudentResponse updateStudent(StudentRequest studentRequest) {
		StudentResponse studentResponse = new StudentResponse();
		StudentEntity existedStudentEntity = studentRepository.findByStudentId(studentRequest.getStudentId());
		
		existedStudentEntity.setStudentEmail(studentRequest.getStudentEmail());
		existedStudentEntity.setStudentPhoneNo(studentRequest.getStudentPhoneNo());
		existedStudentEntity.setStudentName(studentRequest.getStudentName());
		existedStudentEntity.setStudentPassword(studentRequest.getStudentPassword());
		existedStudentEntity.setStudentAddress(studentRequest.getStudentAddress());
		existedStudentEntity.setStudentAge(studentRequest.getStudentAge());
		existedStudentEntity.setStudentGender(studentRequest.getStudentGender());
		existedStudentEntity.setStudentIdentificationNo(studentRequest.getStudentIdentificationNo());
		existedStudentEntity.setStudentRace(studentRequest.getStudentRace());
		existedStudentEntity.setStudentReligion(studentRequest.getStudentReligion());
		existedStudentEntity.setStudentBirthDate(studentRequest.getStudentBirthDate());
		
		StudentEntity updatedStudentEntity = studentRepository.save(existedStudentEntity);
		
		if (BaseUtility.isObjectNotNull(updatedStudentEntity)) {
			studentResponse.setStudentId(updatedStudentEntity.getStudentId());
			studentResponse.setStudentEmail(updatedStudentEntity.getStudentEmail());
			studentResponse.setStudentPhoneNo(updatedStudentEntity.getStudentPhoneNo());
			studentResponse.setStudentName(updatedStudentEntity.getStudentName());
			studentResponse.setStudentPassword(updatedStudentEntity.getStudentPassword());
			studentResponse.setStudentAddress(updatedStudentEntity.getStudentAddress());
			studentResponse.setStudentAge(updatedStudentEntity.getStudentAge());
			studentResponse.setStudentGender(updatedStudentEntity.getStudentGender());
			studentResponse.setStudentIdentificationNo(updatedStudentEntity.getStudentIdentificationNo());
			studentResponse.setStudentRace(updatedStudentEntity.getStudentRace());
			studentResponse.setStudentReligion(updatedStudentEntity.getStudentReligion());
			studentResponse.setStudentBirthDate(updatedStudentEntity.getStudentBirthDate());
		}
		
		return studentResponse;
	}

	@Transactional
	public Boolean deleteStudent(String studentId) {
		Integer totalDeleted = 0;
		
		try {
			totalDeleted = studentRepository.deleteByStudentId(studentId);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		
		if (totalDeleted > 0) {
			return true;
		}
		
		return false;
	}
	
	// Evaluator function
	@Override
	public List<EvaluatorResponse> getEvaluators() {
		List<EvaluatorResponse> evaluatorResponses = new ArrayList<>();
		List<EvaluatorEntity> evaluatorEntities = evaluatorRepository.findAll();
		
		for (EvaluatorEntity evaluatorEntity : evaluatorEntities) {
			EvaluatorResponse evaluatorResponse = new EvaluatorResponse();
			
			evaluatorResponse.setEvaluatorId(evaluatorEntity.getEvaluatorId());
			evaluatorResponse.setEvaluatorEmail(evaluatorEntity.getEvaluatorEmail());
			evaluatorResponse.setEvaluatorPassword(evaluatorEntity.getEvaluatorPassword());
			evaluatorResponse.setEvaluatorName(evaluatorEntity.getEvaluatorName());
			evaluatorResponse.setEvaluatorPosition(evaluatorEntity.getEvaluatorPosition());
			
			evaluatorResponses.add(evaluatorResponse);
		}
		
		return evaluatorResponses;
	}

	@Override
	public EvaluatorResponse getEvaluator(String evaluatorId) {
		EvaluatorResponse evaluatorResponse = new EvaluatorResponse();
		EvaluatorEntity evaluatorEntity = evaluatorRepository.findByEvaluatorId(evaluatorId);
		
		if (BaseUtility.isObjectNotNull(evaluatorEntity)) {
			evaluatorResponse.setEvaluatorId(evaluatorEntity.getEvaluatorId());
			evaluatorResponse.setEvaluatorEmail(evaluatorEntity.getEvaluatorEmail());
			evaluatorResponse.setEvaluatorPassword(evaluatorEntity.getEvaluatorPassword());
			evaluatorResponse.setEvaluatorName(evaluatorEntity.getEvaluatorName());
			evaluatorResponse.setEvaluatorPosition(evaluatorEntity.getEvaluatorPosition());
		}
		
		return evaluatorResponse;
	}

	@Override
	public LoginResponse loginEvaluator(LoginRequest loginRequest) {
		LoginResponse loginResponse = new LoginResponse();
		EvaluatorEntity evaluatorEntity = evaluatorRepository.findByEvaluatorEmail(loginRequest.getLoginUsername());
		
		if (BaseUtility.isObjectNotNull(evaluatorEntity)) {
			if (evaluatorEntity.getEvaluatorPassword().equals(loginRequest.getLoginPassword())) {
				loginResponse.setLoginId(evaluatorEntity.getEvaluatorId());
				loginResponse.setLoginStatus(true);
				loginResponse.setLoginMessage("success login");
			} else {
				loginResponse.setLoginStatus(false);
				loginResponse.setLoginError("wrong password entered");
			}
		} else {
			loginResponse.setLoginStatus(false);
			loginResponse.setLoginError("user does not exist");
		}
		
		return loginResponse;
	}
	
	@Override
	public EvaluatorResponse insertEvaluator(EvaluatorRequest evaluatorRequest) {
		EvaluatorResponse evaluatorResponse = new EvaluatorResponse();
		EvaluatorEntity newEvaluatorEntity = new EvaluatorEntity();
		
		newEvaluatorEntity.setEvaluatorId(BaseUtility.generateId());
		newEvaluatorEntity.setEvaluatorEmail(evaluatorRequest.getEvaluatorEmail());
		newEvaluatorEntity.setEvaluatorPassword(evaluatorRequest.getEvaluatorPassword());
		newEvaluatorEntity.setEvaluatorName(evaluatorRequest.getEvaluatorName());
		newEvaluatorEntity.setEvaluatorPosition(evaluatorRequest.getEvaluatorPosition());
		
		EvaluatorEntity insertedEvaluatorEntity = evaluatorRepository.save(newEvaluatorEntity);
		
		if (BaseUtility.isObjectNotNull(insertedEvaluatorEntity)) {
			evaluatorResponse.setEvaluatorId(insertedEvaluatorEntity.getEvaluatorId());
			evaluatorResponse.setEvaluatorEmail(insertedEvaluatorEntity.getEvaluatorEmail());
			evaluatorResponse.setEvaluatorPassword(insertedEvaluatorEntity.getEvaluatorPassword());
			evaluatorResponse.setEvaluatorName(insertedEvaluatorEntity.getEvaluatorName());
			evaluatorResponse.setEvaluatorPosition(insertedEvaluatorEntity.getEvaluatorPosition());
		}
		
		return evaluatorResponse;
	}

	@Override
	public EvaluatorResponse updateEvaluator(EvaluatorRequest evaluatorRequest) {
		EvaluatorResponse evaluatorResponse = new EvaluatorResponse();
		EvaluatorEntity existedEvaluatorEntity = evaluatorRepository.findByEvaluatorId(evaluatorRequest.getEvaluatorId());
		
		existedEvaluatorEntity.setEvaluatorEmail(evaluatorRequest.getEvaluatorEmail());
		existedEvaluatorEntity.setEvaluatorPassword(evaluatorRequest.getEvaluatorPassword());
		existedEvaluatorEntity.setEvaluatorName(evaluatorRequest.getEvaluatorName());
		existedEvaluatorEntity.setEvaluatorPosition(evaluatorRequest.getEvaluatorPosition());

		EvaluatorEntity updatedEvaluatorEntity = evaluatorRepository.save(existedEvaluatorEntity);
		
		if (BaseUtility.isObjectNotNull(updatedEvaluatorEntity)) {
			evaluatorResponse.setEvaluatorId(updatedEvaluatorEntity.getEvaluatorId());
			evaluatorResponse.setEvaluatorEmail(updatedEvaluatorEntity.getEvaluatorEmail());
			evaluatorResponse.setEvaluatorPassword(updatedEvaluatorEntity.getEvaluatorPassword());
			evaluatorResponse.setEvaluatorName(updatedEvaluatorEntity.getEvaluatorName());
			evaluatorResponse.setEvaluatorPosition(updatedEvaluatorEntity.getEvaluatorPosition());
		}
		
		return evaluatorResponse;
	}

	@Transactional
	public Boolean deleteEvaluator(String evaluatorId) {
		Integer totalDeleted = 0;
		
		try {
			totalDeleted = evaluatorRepository.deleteByEvaluatorId(evaluatorId);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		
		if (totalDeleted > 0) {
			return true;
		}
		
		return false;
	}
}
