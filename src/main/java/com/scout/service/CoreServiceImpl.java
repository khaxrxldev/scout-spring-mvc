package com.scout.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.scout.utility.BaseUtility;

// DAO Import
import com.scout.dao.BadgeRepository;
import com.scout.dao.GuardianRepository;
import com.scout.dao.MembershipRepository;
import com.scout.dao.StudentBadgeRepository;
import com.scout.dao.StudentRepository;
import com.scout.dao.AssessmentRepository;
import com.scout.dao.StudentAssessmentRepository;

// DTO Import
import com.scout.dto.BadgeRequest;
import com.scout.dto.BadgeResponse;
import com.scout.dto.GuardianResponse;
import com.scout.dto.MembershipResponse;
import com.scout.dto.ReportResponse;
import com.scout.dto.StudentBadgeRequest;
import com.scout.dto.StudentBadgeResponse;
import com.scout.dto.StudentResponse;
import com.scout.dto.AssessmentRequest;
import com.scout.dto.AssessmentResponse;
import com.scout.dto.StudentAssessmentRequest;
import com.scout.dto.StudentAssessmentResponse;

// Entity Import
import com.scout.entity.BadgeEntity;
import com.scout.entity.GuardianEntity;
import com.scout.entity.MembershipEntity;
import com.scout.entity.StudentBadgeEntity;
import com.scout.entity.StudentEntity;
import com.scout.entity.AssessmentEntity;
import com.scout.entity.StudentAssessmentEntity;

import jakarta.transaction.Transactional;

@Service
public class CoreServiceImpl implements CoreService {
	
	@Autowired
	BadgeRepository badgeRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	GuardianRepository guardianRepository;
	
	@Autowired
	AssessmentRepository assessmentRepository;
	
	@Autowired
	MembershipRepository membershipRepository;
	
	@Autowired
	StudentBadgeRepository studentBadgeRepository;
	
	@Autowired
	StudentAssessmentRepository studentAssessmentRepository;
	
	@Autowired
	UserService userService;

	// Badge 
	@Override
	public List<BadgeResponse> getBadges() {
		List<BadgeResponse> badgeResponses = new ArrayList<>();
		List<BadgeEntity> badgeEntities = badgeRepository.findAll();
		
		for (BadgeEntity badgeEntity : badgeEntities) {
			BadgeResponse badgeResponse = new BadgeResponse();
			
			badgeResponse.setBadgeId(badgeEntity.getBadgeId());
			badgeResponse.setBadgeName(badgeEntity.getBadgeName());
			badgeResponse.setBadgeOrder(badgeEntity.getBadgeOrder());
			badgeResponse.setBadgeForm(badgeEntity.getBadgeForm());
			badgeResponse.setBadgeIdFk(badgeEntity.getBadgeIdFk());
			badgeResponse.setBadgeCategory(badgeEntity.getBadgeCategory());

			badgeResponses.add(badgeResponse);
		}
		
		return badgeResponses;
	}

	@Override
	public BadgeResponse getBadge(String badgeId) {
		BadgeResponse badgeResponse = new BadgeResponse();
		BadgeEntity badgeEntity = badgeRepository.findByBadgeId(badgeId);
		
		if (BaseUtility.isObjectNotNull(badgeEntity)) {
			badgeResponse.setBadgeId(badgeEntity.getBadgeId());
			badgeResponse.setBadgeName(badgeEntity.getBadgeName());
			badgeResponse.setBadgeOrder(badgeEntity.getBadgeOrder());
			badgeResponse.setBadgeForm(badgeEntity.getBadgeForm());
			badgeResponse.setBadgeIdFk(badgeEntity.getBadgeIdFk());
			badgeResponse.setBadgeCategory(badgeEntity.getBadgeCategory());
		}
		
		return badgeResponse;
	}

	@Override
	public BadgeResponse insertBadge(BadgeRequest badgeRequest) {
		BadgeResponse badgeResponse = new BadgeResponse();
		BadgeEntity newBadgeEntity = new BadgeEntity();
		
		newBadgeEntity.setBadgeId(BaseUtility.generateId());
		newBadgeEntity.setBadgeName(badgeRequest.getBadgeName());
		newBadgeEntity.setBadgeOrder(badgeRequest.getBadgeOrder());
		newBadgeEntity.setBadgeForm(badgeRequest.getBadgeForm());
		newBadgeEntity.setBadgeIdFk(badgeRequest.getBadgeIdFk());
		newBadgeEntity.setBadgeCategory(badgeRequest.getBadgeCategory());		
		
		BadgeEntity insertedBadgeEntity = badgeRepository.save(newBadgeEntity);
		
		if (BaseUtility.isObjectNotNull(insertedBadgeEntity)) {
			badgeResponse.setBadgeId(insertedBadgeEntity.getBadgeId());
			badgeResponse.setBadgeName(insertedBadgeEntity.getBadgeName());
			badgeResponse.setBadgeOrder(insertedBadgeEntity.getBadgeOrder());
			badgeResponse.setBadgeForm(insertedBadgeEntity.getBadgeForm());
			badgeResponse.setBadgeIdFk(insertedBadgeEntity.getBadgeIdFk());
			badgeResponse.setBadgeCategory(insertedBadgeEntity.getBadgeCategory());			
		}
		
		return badgeResponse;
	}

	@Override
	public BadgeResponse updateBadge(BadgeRequest badgeRequest) {
		BadgeResponse badgeResponse = new BadgeResponse();
		BadgeEntity existedBadgeEntity = badgeRepository.findByBadgeId(badgeRequest.getBadgeId());
		
		existedBadgeEntity.setBadgeName(badgeRequest.getBadgeName());
		existedBadgeEntity.setBadgeOrder(badgeRequest.getBadgeOrder());
		existedBadgeEntity.setBadgeForm(badgeRequest.getBadgeForm());
		existedBadgeEntity.setBadgeIdFk(badgeRequest.getBadgeIdFk());
		existedBadgeEntity.setBadgeCategory(badgeRequest.getBadgeCategory());
		
		BadgeEntity updatedBadgeEntity = badgeRepository.save(existedBadgeEntity);
		
		if (BaseUtility.isObjectNotNull(updatedBadgeEntity)) {
			badgeResponse.setBadgeId(updatedBadgeEntity.getBadgeId());
			badgeResponse.setBadgeName(updatedBadgeEntity.getBadgeName());
			badgeResponse.setBadgeOrder(updatedBadgeEntity.getBadgeOrder());
			badgeResponse.setBadgeForm(updatedBadgeEntity.getBadgeForm());
			badgeResponse.setBadgeIdFk(updatedBadgeEntity.getBadgeIdFk());
			badgeResponse.setBadgeCategory(updatedBadgeEntity.getBadgeCategory());
		}
		
		return badgeResponse;
	}

	@Transactional
	public Boolean deleteBadge(String badgeId) {
		Integer totalDeleted = 0;
		
		try {
			totalDeleted = badgeRepository.deleteByBadgeId(badgeId);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		
		if (totalDeleted > 0) {
			return true;
		}
		
		return false;
	}
	
	// Student Badge
	@Override
	public List<StudentBadgeResponse> getStudentBadges() {
		List<StudentBadgeResponse> studentBadgeResponses = new ArrayList<>();
		List<StudentBadgeEntity> studentBadgeEntities = studentBadgeRepository.findAll();
		
		for (StudentBadgeEntity studentBadgeEntity : studentBadgeEntities) {
			StudentBadgeResponse studentBadgeResponse = new StudentBadgeResponse();
			
			studentBadgeResponse.setStudentBadgeId(studentBadgeEntity.getStudentBadgeId());
			studentBadgeResponse.setStudentBadgeStatus(studentBadgeEntity.getStudentBadgeStatus());
			studentBadgeResponse.setStudentBadgeLogbook(studentBadgeEntity.getStudentBadgeLogbook());
			studentBadgeResponse.setStudentId(studentBadgeEntity.getStudentId());
			studentBadgeResponse.setTeacherId(studentBadgeEntity.getTeacherId());
			studentBadgeResponse.setBadgeId(studentBadgeEntity.getBadgeId());
			studentBadgeResponse.setStudentApprovalDate(studentBadgeEntity.getStudentApprovalDate());
			studentBadgeResponse.setStudentBadgeSerialNum(studentBadgeEntity.getStudentBadgeSerialNum());
			studentBadgeResponse.setStudentBadgeSub(studentBadgeEntity.getStudentBadgeSub());

			studentBadgeResponses.add(studentBadgeResponse);
		}
		
		return studentBadgeResponses;
	}

	@Override
	public List<StudentBadgeResponse> getStudentBadgesByStudentId(String studentId) {
		List<StudentBadgeResponse> studentBadgeResponses = new ArrayList<>();
		List<StudentBadgeEntity> studentBadgeEntities = studentBadgeRepository.findAll();
		
		for (StudentBadgeEntity studentBadgeEntity : studentBadgeEntities) {
			StudentBadgeResponse studentBadgeResponse = new StudentBadgeResponse();
			
			studentBadgeResponse.setStudentBadgeId(studentBadgeEntity.getStudentBadgeId());
			studentBadgeResponse.setStudentBadgeStatus(studentBadgeEntity.getStudentBadgeStatus());
			studentBadgeResponse.setStudentBadgeLogbook(studentBadgeEntity.getStudentBadgeLogbook());
			studentBadgeResponse.setStudentId(studentBadgeEntity.getStudentId());
			studentBadgeResponse.setTeacherId(studentBadgeEntity.getTeacherId());
			studentBadgeResponse.setBadgeId(studentBadgeEntity.getBadgeId());
			studentBadgeResponse.setStudentApprovalDate(studentBadgeEntity.getStudentApprovalDate());
			studentBadgeResponse.setStudentBadgeSerialNum(studentBadgeEntity.getStudentBadgeSerialNum());
			studentBadgeResponse.setStudentBadgeSub(studentBadgeEntity.getStudentBadgeSub());

			BadgeResponse badgeResponse = getBadge(studentBadgeEntity.getBadgeId());
			if (BaseUtility.isObjectNotNull(badgeResponse)) {
				studentBadgeResponse.setBadge(badgeResponse);
			}
			
			if (studentId.equals(studentBadgeEntity.getStudentId())) {
				studentBadgeResponses.add(studentBadgeResponse);
			}
		}
		
		return studentBadgeResponses;
	}

	@Override
	public StudentBadgeResponse getStudentBadge(String studentBadgeId) {
		StudentBadgeResponse studentBadgeResponse = new StudentBadgeResponse();
		StudentBadgeEntity studentBadgeEntity = studentBadgeRepository.findByStudentBadgeId(studentBadgeId);
		
		if (BaseUtility.isObjectNotNull(studentBadgeEntity)) {
			studentBadgeResponse.setStudentBadgeId(studentBadgeEntity.getStudentBadgeId());
			studentBadgeResponse.setStudentBadgeStatus(studentBadgeEntity.getStudentBadgeStatus());
			studentBadgeResponse.setStudentBadgeLogbook(studentBadgeEntity.getStudentBadgeLogbook());
			studentBadgeResponse.setStudentId(studentBadgeEntity.getStudentId());
			studentBadgeResponse.setTeacherId(studentBadgeEntity.getTeacherId());
			studentBadgeResponse.setBadgeId(studentBadgeEntity.getBadgeId());
			studentBadgeResponse.setStudentApprovalDate(studentBadgeEntity.getStudentApprovalDate());
			studentBadgeResponse.setStudentBadgeSerialNum(studentBadgeEntity.getStudentBadgeSerialNum());
			studentBadgeResponse.setStudentBadgeSub(studentBadgeEntity.getStudentBadgeSub());
		}
		
		return studentBadgeResponse;
	}

	@Override
	public StudentBadgeResponse insertStudentBadge(StudentBadgeRequest studentBadgeRequest) {
		StudentBadgeResponse studentBadgeResponse = new StudentBadgeResponse();
		StudentBadgeEntity newStudentBadgeEntity = new StudentBadgeEntity();
		
		newStudentBadgeEntity.setStudentBadgeId(BaseUtility.generateId());
		newStudentBadgeEntity.setStudentBadgeStatus(studentBadgeRequest.getStudentBadgeStatus());
		newStudentBadgeEntity.setStudentBadgeLogbook(studentBadgeRequest.getStudentBadgeLogbook());
		newStudentBadgeEntity.setStudentId(studentBadgeRequest.getStudentId());
		newStudentBadgeEntity.setTeacherId(studentBadgeRequest.getTeacherId());
		newStudentBadgeEntity.setBadgeId(studentBadgeRequest.getBadgeId());
		newStudentBadgeEntity.setStudentApprovalDate(studentBadgeRequest.getStudentApprovalDate());
		newStudentBadgeEntity.setStudentBadgeSerialNum(studentBadgeRequest.getStudentBadgeSerialNum());
		newStudentBadgeEntity.setStudentBadgeSub(studentBadgeRequest.getStudentBadgeSub());
		
		StudentBadgeEntity insertedStudentBadgeEntity = studentBadgeRepository.save(newStudentBadgeEntity);
		
		if (BaseUtility.isObjectNotNull(insertedStudentBadgeEntity)) {
			studentBadgeResponse.setStudentBadgeId(insertedStudentBadgeEntity.getStudentBadgeId());
			studentBadgeResponse.setStudentBadgeStatus(insertedStudentBadgeEntity.getStudentBadgeStatus());
			studentBadgeResponse.setStudentBadgeLogbook(insertedStudentBadgeEntity.getStudentBadgeLogbook());
			studentBadgeResponse.setStudentId(insertedStudentBadgeEntity.getStudentId());
			studentBadgeResponse.setTeacherId(insertedStudentBadgeEntity.getTeacherId());
			studentBadgeResponse.setBadgeId(insertedStudentBadgeEntity.getBadgeId());
			studentBadgeResponse.setStudentApprovalDate(insertedStudentBadgeEntity.getStudentApprovalDate());
			studentBadgeResponse.setStudentBadgeSerialNum(insertedStudentBadgeEntity.getStudentBadgeSerialNum());
			studentBadgeResponse.setStudentBadgeSub(insertedStudentBadgeEntity.getStudentBadgeSub());
		}
		
		return studentBadgeResponse;
	}

	@Override
	public StudentBadgeResponse updateStudentBadge(StudentBadgeRequest studentBadgeRequest) {
		StudentBadgeResponse studentBadgeResponse = new StudentBadgeResponse();
		StudentBadgeEntity existedStudentBadgeEntity = studentBadgeRepository.findByStudentBadgeId(studentBadgeRequest.getStudentBadgeId());
		
		if (BaseUtility.isNotBlank(studentBadgeRequest.getStudentBadgeStatus())) {
			existedStudentBadgeEntity.setStudentBadgeStatus(studentBadgeRequest.getStudentBadgeStatus());
		}
		if (BaseUtility.isNotBlank(studentBadgeRequest.getStudentBadgeLogbook())) {
			existedStudentBadgeEntity.setStudentBadgeLogbook(studentBadgeRequest.getStudentBadgeLogbook());
		}
		if (BaseUtility.isNotBlank(studentBadgeRequest.getStudentId())) {
			existedStudentBadgeEntity.setStudentId(studentBadgeRequest.getStudentId());
		}
		if (BaseUtility.isNotBlank(studentBadgeRequest.getTeacherId())) {
			existedStudentBadgeEntity.setTeacherId(studentBadgeRequest.getTeacherId());
		}
		if (BaseUtility.isNotBlank(studentBadgeRequest.getBadgeId())) {
			existedStudentBadgeEntity.setBadgeId(studentBadgeRequest.getBadgeId());
		}
		if (BaseUtility.isNotBlank(studentBadgeRequest.getStudentBadgeStatus())) {
			if (studentBadgeRequest.getStudentBadgeStatus().equals("APPROVE")) {
				Date currentDate = new Date();
				existedStudentBadgeEntity.setStudentApprovalDate(currentDate);
			}
		}
		if (BaseUtility.isNotBlank(studentBadgeRequest.getStudentBadgeSerialNum())) {
			existedStudentBadgeEntity.setStudentBadgeSerialNum(studentBadgeRequest.getStudentBadgeSerialNum());
		}
		if (BaseUtility.isNotBlank(studentBadgeRequest.getStudentBadgeSub())) {
			existedStudentBadgeEntity.setStudentBadgeSub(studentBadgeRequest.getStudentBadgeSub());
		}
		
		StudentBadgeEntity updatedStudentBadgeEntity = studentBadgeRepository.save(existedStudentBadgeEntity);
		
		if (BaseUtility.isObjectNotNull(updatedStudentBadgeEntity)) {
			studentBadgeResponse.setStudentBadgeId(updatedStudentBadgeEntity.getBadgeId());
			studentBadgeResponse.setStudentBadgeStatus(updatedStudentBadgeEntity.getStudentBadgeStatus());
			studentBadgeResponse.setStudentBadgeLogbook(updatedStudentBadgeEntity.getStudentBadgeLogbook());
			studentBadgeResponse.setStudentId(updatedStudentBadgeEntity.getStudentId());
			studentBadgeResponse.setTeacherId(updatedStudentBadgeEntity.getTeacherId());
			studentBadgeResponse.setBadgeId(updatedStudentBadgeEntity.getBadgeId());
			studentBadgeResponse.setStudentApprovalDate(updatedStudentBadgeEntity.getStudentApprovalDate());
			studentBadgeResponse.setStudentBadgeSerialNum(updatedStudentBadgeEntity.getStudentBadgeSerialNum());
			studentBadgeResponse.setStudentBadgeSub(updatedStudentBadgeEntity.getStudentBadgeSub());
		}
		
		return studentBadgeResponse;
	}

	@Transactional
	public Boolean deleteStudentBadge(String studentBadgeId) {
		Integer totalDeleted = 0;
		
		try {
			totalDeleted = studentBadgeRepository.deleteByStudentBadgeId(studentBadgeId);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		
		if (totalDeleted > 0) {
			return true;
		}
		
		return false;
	}

	@Override
	public List<StudentResponse> getStudentsPendingApproval() {
		List<StudentResponse> studentResponses = userService.getStudents();
		List<StudentResponse> pendingStudentResponses = new ArrayList<StudentResponse>();
		
		for (StudentResponse studentResponse : studentResponses) {
			Boolean status = false;
			List<StudentBadgeEntity> studentBadgeEntities = studentBadgeRepository.findByStudentId(studentResponse.getStudentId());
			
			for (StudentBadgeEntity studentBadgeEntity : studentBadgeEntities) {
				if (BaseUtility.isBlank(studentBadgeEntity.getStudentBadgeStatus())) {
					status = true;
				}
			}
			
			if (status) {
				pendingStudentResponses.add(studentResponse);
			}
		}
		
		return pendingStudentResponses;
	}

	// Assessment
	@Override
	public List<AssessmentResponse> getAssessments() {
		List<AssessmentResponse> assessmentResponses = new ArrayList<>();
		List<AssessmentEntity> assessmentEntities = assessmentRepository.findAll();
		
		for (AssessmentEntity assessmentEntity : assessmentEntities) {
			AssessmentResponse assessmentResponse = new AssessmentResponse();
			
			assessmentResponse.setAssessmentId(assessmentEntity.getAssessmentId());
			assessmentResponse.setAssessmentName(assessmentEntity.getAssessmentName());

			assessmentResponses.add(assessmentResponse);
		}
		
		return assessmentResponses;
	}

	@Override
	public AssessmentResponse getAssessment(String assessmentId) {
		AssessmentResponse assessmentResponse = new AssessmentResponse();
		AssessmentEntity assessmentEntity = assessmentRepository.findByAssessmentId(assessmentId);
		
		if (BaseUtility.isObjectNotNull(assessmentEntity)) {
			assessmentResponse.setAssessmentId(assessmentEntity.getAssessmentId());
			assessmentResponse.setAssessmentName(assessmentEntity.getAssessmentName());

		}
		
		return assessmentResponse;
	}

	@Override
	public AssessmentResponse insertAssessment(AssessmentRequest assessmentRequest) {
		AssessmentResponse assessmentResponse = new AssessmentResponse();
		AssessmentEntity newAssessmentEntity = new AssessmentEntity();
		
		newAssessmentEntity.setAssessmentId(BaseUtility.generateId());
		newAssessmentEntity.setAssessmentName(assessmentRequest.getAssessmentName());

		
		AssessmentEntity insertedAssessmentEntity = assessmentRepository.save(newAssessmentEntity);
		
		if (BaseUtility.isObjectNotNull(insertedAssessmentEntity)) {
			
			assessmentResponse.setAssessmentId(insertedAssessmentEntity.getAssessmentId());
			assessmentResponse.setAssessmentName(insertedAssessmentEntity.getAssessmentName());
	
		}
		
		return assessmentResponse;
	}

	@Override
	public AssessmentResponse updateAssessment(AssessmentRequest assessmentRequest) {
		AssessmentResponse assessmentResponse = new AssessmentResponse();
		AssessmentEntity existedAssessmentEntity = assessmentRepository.findByAssessmentId(assessmentRequest.getAssessmentId());
		
		existedAssessmentEntity.setAssessmentName(assessmentRequest.getAssessmentName());

		
		AssessmentEntity updatedAssessmentEntity = assessmentRepository.save(existedAssessmentEntity);
		
		if (BaseUtility.isObjectNotNull(updatedAssessmentEntity)) {
			assessmentResponse.setAssessmentId(updatedAssessmentEntity.getAssessmentId());
			assessmentResponse.setAssessmentName(updatedAssessmentEntity.getAssessmentName());

		}
		
		return assessmentResponse;
	}

	@Transactional
	public Boolean deleteAssessment(String assessmentId) {
		Integer totalDeleted = 0;
		
		try {
			totalDeleted = assessmentRepository.deleteByAssessmentId(assessmentId);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		
		if (totalDeleted > 0) {
			return true;
		}
		
		return false;
	}

    // Student Assessment
	@Override
	public List<StudentAssessmentResponse> getStudentAssessments() {
		List<StudentAssessmentResponse> studentAssessmentResponses = new ArrayList<>();
		List<StudentAssessmentEntity> studentAssessmentEntities = studentAssessmentRepository.findAll();
		
		for (StudentAssessmentEntity studentAssessmentEntity : studentAssessmentEntities) {
			StudentAssessmentResponse studentAssessmentResponse = new StudentAssessmentResponse();
			
			studentAssessmentResponse.setStudentAssessmentId(studentAssessmentEntity.getStudentAssessmentId());
			studentAssessmentResponse.setStudentAssessmentResult(studentAssessmentEntity.getStudentAssessmentResult());
			studentAssessmentResponse.setStudentAssessmentResultDate(studentAssessmentEntity.getStudentAssessmentResultDate());
			studentAssessmentResponse.setStudentId(studentAssessmentEntity.getStudentId());
			studentAssessmentResponse.setStudentResponse(userService.getStudent(studentAssessmentEntity.getStudentId()));
			studentAssessmentResponse.setEvaluatorId(studentAssessmentEntity.getEvaluatorId());
			studentAssessmentResponse.setEvaluatorResponse(userService.getEvaluator(studentAssessmentEntity.getEvaluatorId()));
			studentAssessmentResponse.setAssessmentId(studentAssessmentEntity.getAssessmentId());
			studentAssessmentResponse.setAssessmentResponse(getAssessment(studentAssessmentEntity.getAssessmentId()));

			studentAssessmentResponses.add(studentAssessmentResponse);
		}
		
		return studentAssessmentResponses;
	}
	
	@Override
	public List<StudentAssessmentResponse> getStudentAssessmentsByStudentId(String studentId) {
		List<StudentAssessmentResponse> studentAssessmentResponses = new ArrayList<>();
		List<StudentAssessmentEntity> studentAssessmentEntities = studentAssessmentRepository.findAll();
		
		for (StudentAssessmentEntity studentAssessmentEntity : studentAssessmentEntities) {
			StudentAssessmentResponse studentAssessmentResponse = new StudentAssessmentResponse();
			
			studentAssessmentResponse.setStudentAssessmentId(studentAssessmentEntity.getStudentAssessmentId());
			studentAssessmentResponse.setStudentAssessmentResult(studentAssessmentEntity.getStudentAssessmentResult());
			studentAssessmentResponse.setStudentAssessmentResultDate(studentAssessmentEntity.getStudentAssessmentResultDate());
			studentAssessmentResponse.setStudentId(studentAssessmentEntity.getStudentId());
			studentAssessmentResponse.setStudentResponse(userService.getStudent(studentAssessmentEntity.getStudentId()));
			studentAssessmentResponse.setEvaluatorId(studentAssessmentEntity.getEvaluatorId());
			studentAssessmentResponse.setEvaluatorResponse(userService.getEvaluator(studentAssessmentEntity.getEvaluatorId()));
			studentAssessmentResponse.setAssessmentId(studentAssessmentEntity.getAssessmentId());
			studentAssessmentResponse.setAssessmentResponse(getAssessment(studentAssessmentEntity.getAssessmentId()));

			if (studentId.equals(studentAssessmentEntity.getStudentId())) {
				studentAssessmentResponses.add(studentAssessmentResponse);
			}
		}
		
		return studentAssessmentResponses;
	}

	@Override
	public 	StudentAssessmentResponse getStudentAssessment(String studentAssessmentId) {
		StudentAssessmentResponse studentAssessmentResponse = new StudentAssessmentResponse();
		StudentAssessmentEntity studentAssessmentEntity = studentAssessmentRepository.findByStudentAssessmentId(studentAssessmentId);
		
		if (BaseUtility.isObjectNotNull(studentAssessmentEntity)) {
			studentAssessmentResponse.setStudentAssessmentId(studentAssessmentEntity.getStudentAssessmentId());
			studentAssessmentResponse.setStudentAssessmentResult(studentAssessmentEntity.getStudentAssessmentResult());
			studentAssessmentResponse.setStudentAssessmentResultDate(studentAssessmentEntity.getStudentAssessmentResultDate());
			studentAssessmentResponse.setStudentId(studentAssessmentEntity.getStudentId());
			studentAssessmentResponse.setEvaluatorId(studentAssessmentEntity.getEvaluatorId());
			studentAssessmentResponse.setAssessmentId(studentAssessmentEntity.getAssessmentId());
		}
		
		return studentAssessmentResponse;
	}

	@Override
	public StudentAssessmentResponse insertStudentAssessment(StudentAssessmentRequest studentAssessmentRequest) {
		StudentAssessmentResponse studentAssessmentResponse = new StudentAssessmentResponse();
		StudentAssessmentEntity newStudentAssessmentEntity = new StudentAssessmentEntity();
		
		newStudentAssessmentEntity.setStudentAssessmentId(BaseUtility.generateId());
//		newStudentAssessmentEntity.setStudentAssessmentResult(studentAssessmentRequest.getStudentAssessmentResult());
		
//		Date currentDate = new Date();
//		newStudentAssessmentEntity.setStudentAssessmentResultDate(currentDate);
		newStudentAssessmentEntity.setStudentId(studentAssessmentRequest.getStudentId());
//		newStudentAssessmentEntity.setEvaluatorId(studentAssessmentRequest.getEvaluatorId());
		newStudentAssessmentEntity.setAssessmentId(studentAssessmentRequest.getAssessmentId());
		
		StudentAssessmentEntity insertedStudentAssessmentEntity = studentAssessmentRepository.save(newStudentAssessmentEntity);
		
		if (BaseUtility.isObjectNotNull(insertedStudentAssessmentEntity)) {
			studentAssessmentResponse.setStudentAssessmentId(insertedStudentAssessmentEntity.getStudentAssessmentId());
			studentAssessmentResponse.setStudentAssessmentResult(insertedStudentAssessmentEntity.getStudentAssessmentResult());
			studentAssessmentResponse.setStudentAssessmentResultDate(insertedStudentAssessmentEntity.getStudentAssessmentResultDate());
			studentAssessmentResponse.setStudentId(insertedStudentAssessmentEntity.getStudentId());
			studentAssessmentResponse.setEvaluatorId(insertedStudentAssessmentEntity.getEvaluatorId());
			studentAssessmentResponse.setAssessmentId(insertedStudentAssessmentEntity.getAssessmentId());
		}
		
		return studentAssessmentResponse;
	}

	@Override
	public StudentAssessmentResponse updateStudentAssessment(StudentAssessmentRequest studentAssessmentRequest) {
		StudentAssessmentResponse studentAssessmentResponse = new StudentAssessmentResponse();
		StudentAssessmentEntity existedStudentAssessmentEntity = studentAssessmentRepository.findByStudentAssessmentId(studentAssessmentRequest.getStudentAssessmentId());
		
		existedStudentAssessmentEntity.setStudentAssessmentResult(studentAssessmentRequest.getStudentAssessmentResult());
		
		Date currentDate = new Date();
		existedStudentAssessmentEntity.setStudentAssessmentResultDate(currentDate);
		existedStudentAssessmentEntity.setEvaluatorId(studentAssessmentRequest.getEvaluatorId());		
		
		StudentAssessmentEntity updatedStudentAssessmentEntity = studentAssessmentRepository.save(existedStudentAssessmentEntity);
		
		if (BaseUtility.isObjectNotNull(updatedStudentAssessmentEntity)) {
			studentAssessmentResponse.setStudentAssessmentId(updatedStudentAssessmentEntity.getStudentAssessmentId());
			studentAssessmentResponse.setStudentAssessmentResult(updatedStudentAssessmentEntity.getStudentAssessmentResult());
			studentAssessmentResponse.setStudentAssessmentResultDate(updatedStudentAssessmentEntity.getStudentAssessmentResultDate());
			studentAssessmentResponse.setStudentId(updatedStudentAssessmentEntity.getStudentId());
			studentAssessmentResponse.setEvaluatorId(updatedStudentAssessmentEntity.getEvaluatorId());
			studentAssessmentResponse.setAssessmentId(updatedStudentAssessmentEntity.getAssessmentId());
		}
		
		return studentAssessmentResponse;
	}

	@Transactional
	public Boolean deleteStudentAssessment(String studentAssessmentId) {
		Integer totalDeleted = 0;
		
		try {
			totalDeleted = studentAssessmentRepository.deleteByStudentAssessmentId(studentAssessmentId);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		
		if (totalDeleted > 0) {
			return true;
		}
		
		return false;
	}

	@Override
	public StudentAssessmentResponse getStudentAssessmentApplication(String studentId, String assessmentId) {
		StudentAssessmentResponse studentAssessmentResponse = new StudentAssessmentResponse();
		StudentAssessmentEntity studentAssessmentEntity = studentAssessmentRepository.findByStudentIdAndAssessmentId(studentId, assessmentId);
		
		if (BaseUtility.isObjectNotNull(studentAssessmentEntity)) {
			studentAssessmentResponse.setStudentAssessmentId(studentAssessmentEntity.getStudentAssessmentId());
			studentAssessmentResponse.setStudentAssessmentResult(studentAssessmentEntity.getStudentAssessmentResult());
			studentAssessmentResponse.setStudentAssessmentResultDate(studentAssessmentEntity.getStudentAssessmentResultDate());
			studentAssessmentResponse.setStudentId(studentAssessmentEntity.getStudentId());
			studentAssessmentResponse.setEvaluatorId(studentAssessmentEntity.getEvaluatorId());
			studentAssessmentResponse.setAssessmentId(studentAssessmentEntity.getAssessmentId());
			
			return studentAssessmentResponse;
		} else {
			return null;
		}
	}

	@Override
	public List<StudentAssessmentResponse> getStudentAssessmentsApplications(String studentId) {
		List<StudentAssessmentResponse> studentAssessmentResponses = new ArrayList<StudentAssessmentResponse>();
		List<AssessmentResponse> assessmentResponses = getAssessments();
		
		for (AssessmentResponse assessmentResponse : assessmentResponses) {
			StudentAssessmentEntity studentAssessmentEntity = studentAssessmentRepository.findByStudentIdAndAssessmentId(studentId, assessmentResponse.getAssessmentId());
			
			StudentAssessmentResponse studentAssessmentResponse = new StudentAssessmentResponse();
			studentAssessmentResponse.setAssessmentResponse(assessmentResponse);
			if (BaseUtility.isObjectNotNull(studentAssessmentEntity)) {
				studentAssessmentResponse.setStudentAssessmentId(studentAssessmentEntity.getStudentAssessmentId());
				studentAssessmentResponse.setStudentAssessmentResult(studentAssessmentEntity.getStudentAssessmentResult());
				studentAssessmentResponse.setStudentAssessmentResultDate(studentAssessmentEntity.getStudentAssessmentResultDate());
				studentAssessmentResponse.setStudentId(studentAssessmentEntity.getStudentId());
				studentAssessmentResponse.setStudentResponse(userService.getStudent(studentAssessmentEntity.getStudentId()));
				studentAssessmentResponse.setEvaluatorId(studentAssessmentEntity.getEvaluatorId());
				studentAssessmentResponse.setEvaluatorResponse(userService.getEvaluator(studentAssessmentEntity.getEvaluatorId()));
				studentAssessmentResponse.setAssessmentId(studentAssessmentEntity.getAssessmentId());
			}
			
			studentAssessmentResponses.add(studentAssessmentResponse);
		}
		
		return studentAssessmentResponses;
	}

	@Override
	public List<ReportResponse> getReports() {
		List<ReportResponse> reportResponses = new ArrayList<ReportResponse>();
		
		List<MembershipEntity> membershipEntities = membershipRepository.findAll();
		
		for (MembershipEntity membershipEntity : membershipEntities) {
			ReportResponse reportResponse = new ReportResponse();
			
			MembershipResponse membershipResponse = new MembershipResponse();
			membershipResponse.setMembershipId(membershipEntity.getMembershipId());
	        membershipResponse.setMembershipType(membershipEntity.getMembershipType());
	        membershipResponse.setMembershipUnit(membershipEntity.getMembershipUnit());
	        membershipResponse.setMembershipRank(membershipEntity.getMembershipRank());
	        membershipResponse.setMembershipNo(membershipEntity.getMembershipNo());
	        membershipResponse.setMembershipJoinedYear(membershipEntity.getMembershipJoinedYear());
	        membershipResponse.setMembershipStudentForm(membershipEntity.getMembershipStudentForm());
	        membershipResponse.setStudentId(membershipEntity.getStudentId());
	        membershipResponse.setMembershipPayStatus(membershipEntity.getMembershipPayStatus());
	        membershipResponse.setMembershipPayDate(membershipEntity.getMembershipPayDate());
	        membershipResponse.setMembershipPayReceipt(membershipEntity.getMembershipPayReceipt());
	        reportResponse.setMembershipResponse(membershipResponse);
	        
	        StudentEntity studentEntity = studentRepository.findByStudentId(membershipEntity.getStudentId());
	        
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
			reportResponse.setStudentResponse(studentResponse);
			
			GuardianEntity guardianEntity = guardianRepository.findByStudentId(membershipEntity.getStudentId());
			
			if (BaseUtility.isObjectNotNull(guardianEntity)) {
				GuardianResponse guardianResponse = new GuardianResponse();
		        guardianResponse.setGuardianId(guardianEntity.getGuardianId());
		        guardianResponse.setGuardianName(guardianEntity.getGuardianName());
		        guardianResponse.setGuardianPhoneNo(guardianEntity.getGuardianPhoneNo());
		        guardianResponse.setGuardianConnection(guardianEntity.getGuardianConnection());
		        guardianResponse.setStudentId(guardianEntity.getStudentId());
		        reportResponse.setGuardianResponse(guardianResponse);
			}
			
			reportResponses.add(reportResponse);
		}
		
		return reportResponses;
	}
}


