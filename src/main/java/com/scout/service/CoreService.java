package com.scout.service;

import java.util.List;

import com.scout.dto.BadgeRequest;
import com.scout.dto.BadgeResponse;
import com.scout.dto.ReportResponse;
import com.scout.dto.StudentBadgeRequest;
import com.scout.dto.StudentBadgeResponse;
import com.scout.dto.StudentResponse;
import com.scout.dto.AssessmentRequest;
import com.scout.dto.AssessmentResponse;
import com.scout.dto.StudentAssessmentRequest;
import com.scout.dto.StudentAssessmentResponse;

public interface CoreService {

	List<BadgeResponse> getBadges();
	
	BadgeResponse getBadge(String badgeId);
		
	BadgeResponse insertBadge(BadgeRequest badgeRequest);
	
	BadgeResponse updateBadge(BadgeRequest badgeRequest);
	
	Boolean deleteBadge(String badgeId);
	
	
	List<StudentBadgeResponse> getStudentBadges();
	
	List<StudentBadgeResponse> getStudentBadgesByStudentId(String studentId);
	
	StudentBadgeResponse getStudentBadge(String studentBadgeId);
		
	StudentBadgeResponse insertStudentBadge(StudentBadgeRequest studentBadgeRequest);
	
	StudentBadgeResponse updateStudentBadge(StudentBadgeRequest studentBadgeRequest);
	
	Boolean deleteStudentBadge(String studentBadgeId);
	
	List<StudentResponse> getStudentsPendingApproval();
	
	
	List<AssessmentResponse> getAssessments();
	
	AssessmentResponse getAssessment(String assessmentId);
		
	AssessmentResponse insertAssessment(AssessmentRequest assessmentRequest);
	
	AssessmentResponse updateAssessment(AssessmentRequest assessmentRequest);
	
	Boolean deleteAssessment (String assessmentId);
	
	
	List<StudentAssessmentResponse> getStudentAssessments();
	
	List<StudentAssessmentResponse> getStudentAssessmentsByStudentId(String studentId);
	
	StudentAssessmentResponse getStudentAssessment(String studentAssessmentId);
		
	StudentAssessmentResponse insertStudentAssessment(StudentAssessmentRequest studentAssessmentRequest);
	
	StudentAssessmentResponse updateStudentAssessment(StudentAssessmentRequest studentAssessmentRequest);
	
	Boolean deleteStudentAssessment (String studentAssessmentId);
	
	StudentAssessmentResponse getStudentAssessmentApplication(String studentId, String assessmentId);
	
	List<StudentAssessmentResponse> getStudentAssessmentsApplications(String studentId);
	
	List<ReportResponse> getReports();
}
