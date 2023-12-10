package com.scout.controller;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
import com.scout.service.CoreService;
import com.scout.service.FilesStorageService;
import com.scout.utility.BaseUtility;

@Controller
@RequestMapping("/core")
public class CoreController {
	
	@Autowired
	CoreService coreService;

	@Autowired
	FilesStorageService storageService;
	
	// VIEW all badges page
	@GetMapping("/badge/view")
	public String viewBadgePage() {
		return "badges";
	}
	
	@GetMapping("/result/view")
	public String viewResultPage() {
		return "results";
	}
	
	@GetMapping("/report/view")
	public String viewReportPage() {
		return "reports";
	}
	
	@GetMapping("/studentBadge/view")
	public String viewStudentBadgePage() {
		return "studentBadges";
	}
	
	@GetMapping("/assessment/view")
	public String viewAssessmentPage() {
		return "assessments";
	}
	
	@GetMapping("/students/assessments/view")
	public String viewStudentAssessmentPage() {
		return "students_assessments";
	}
	
	@GetMapping("/students/approvals/view")
	public String viewStudentApprovalPage() {
		return "students_approvals";
	}
	
	// VIEW single badge page
	@GetMapping("/badge/update/{badgeId}")
	public String updateBadgePage() {
		return "badge";
	}
	
	@GetMapping("/studentBadge/update/{studentBadgeId}")
	public String updateStudentBadgePage() {
		return "studentBadge";
	}
	
	@GetMapping("/assessment/update/{assessmentId}")
	public String updateAssessmentPage() {
		return "assessment";
	}
	
	@GetMapping("/student/assessments/update/{studentId}")
	public String updateStudentAssessmentPage() {
		return "student_assessments";
	}
	
	@GetMapping("/student/approvals/update/{studentId}")
	public String updateStudentApprovalPage() {
		return "student_approvals";
	}
	
	// (R) GET all badges data
	@GetMapping("/badges")
	@ResponseBody
	public List<BadgeResponse> getBadges() {
		return coreService.getBadges();
	}
	
	@GetMapping("/studentBadges")
	@ResponseBody
	public List<StudentBadgeResponse> getStudentBadges() {
		return coreService.getStudentBadges();
	}
	
	@GetMapping("/studentBadges/student/{studentId}")
	@ResponseBody
	public List<StudentBadgeResponse> getStudentBadgesByStudentId(@PathVariable("studentId") String studentId) {
		return coreService.getStudentBadgesByStudentId(studentId);
	}
	
	@GetMapping("/assessments")
	@ResponseBody
	public List<AssessmentResponse> getAssessments() {
		return coreService.getAssessments();
	}
	
	@GetMapping("/studentAssessments")
	@ResponseBody
	public List<StudentAssessmentResponse> getStudentAssessments() {
		return coreService.getStudentAssessments();
	}
	
	@GetMapping("/studentAssessments/student/{studentId}")
	@ResponseBody
	public List<StudentAssessmentResponse> getStudentAssessmentsByStudentId(@PathVariable("studentId") String studentId) {
		return coreService.getStudentAssessmentsByStudentId(studentId);
	}
	
	@GetMapping("/reports")
	@ResponseBody
	public List<ReportResponse> getReports() {
		return coreService.getReports();
	}
	
	// (R) GET single badge data by primary key
	@GetMapping("/badge/{badgeId}")
	@ResponseBody
	public BadgeResponse getBadge(@PathVariable("badgeId") String badgeId) {
		return coreService.getBadge(badgeId);
	}
	
	@GetMapping("/studentBadge/{studentBadgeId}")
	@ResponseBody
	public StudentBadgeResponse getStudentBadge(@PathVariable("studentBadgeId") String studentBadgeId) {
		return coreService.getStudentBadge(studentBadgeId);
	}
	
	@GetMapping("/files/{studentBadgeId}")
	@ResponseBody
	public String getFile(@PathVariable String studentBadgeId) {
		StudentBadgeResponse studentBadgeResponse = coreService.getStudentBadge(studentBadgeId);
		
		if (BaseUtility.isObjectNotNull(studentBadgeResponse)) {
			return Base64.getEncoder().encodeToString(storageService.retrieveFile(studentBadgeResponse.getStudentBadgeLogbook()));
		} else {
			return "";
		}
	}
	
	@GetMapping("/assessment/{assessmentId}")
	@ResponseBody
	public AssessmentResponse getAssessment(@PathVariable("assessmentId") String assessmentId) {
		return coreService.getAssessment(assessmentId);
	}
	
	@GetMapping("/studentAssessment/{studentAssessmentId}")
	@ResponseBody
	public StudentAssessmentResponse getStudentAssessment(@PathVariable("studentAssessmentId") String studentAssessmentId) {
		return coreService.getStudentAssessment(studentAssessmentId);
	}
	
	// (C) INSERT badge data
	@PostMapping("/badge")
	@ResponseBody
	public BadgeResponse insertBadge(@RequestPart(value="badgeRequest") BadgeRequest badgeRequest) {
		return coreService.insertBadge(badgeRequest);
	}
	
	@PostMapping("/studentBadge")
	@ResponseBody
	public StudentBadgeResponse insertStudentBadge(@RequestPart(value="studentBadgeRequest") StudentBadgeRequest studentBadgeRequest, @RequestParam(name = "logbook", required = false) MultipartFile logbook) {
		if (logbook != null) {
			String message = "";
			try {
				storageService.insertFile(logbook);
				message = "Complete upload file : " + logbook.getOriginalFilename();
			} catch (Exception e) {
				message = "Fail upload file : " + logbook.getOriginalFilename() + ". Error : " + e.getMessage();
			}
			System.out.println(message);
			
			studentBadgeRequest.setStudentBadgeLogbook(logbook.getOriginalFilename());
		}
		
		return coreService.insertStudentBadge(studentBadgeRequest);
	}
	
	@PostMapping("/assessment")
	@ResponseBody
	public AssessmentResponse insertAssessment(@RequestPart(value="assessmentRequest") AssessmentRequest assessmentRequest) {
		return coreService.insertAssessment(assessmentRequest);
	}
	
	@PostMapping("/studentAssessment")
	@ResponseBody
	public StudentAssessmentResponse insertStudentAssessment(@RequestPart(value="studentAssessmentRequest") StudentAssessmentRequest studentAssessmentRequest) {
		return coreService.insertStudentAssessment(studentAssessmentRequest);
	}
	
	// (U) UPDATE badge data
	@PutMapping("/badge")
	@ResponseBody
	public BadgeResponse updateBadge(@RequestPart(value="badgeRequest") BadgeRequest badgeRequest) {
		return coreService.updateBadge(badgeRequest);
	}
	
	@PutMapping("/studentBadge")
	@ResponseBody
	public StudentBadgeResponse updateStudentBadge(@RequestPart(value="studentBadgeRequest") StudentBadgeRequest studentBadgeRequest, @RequestParam(name = "logbook", required = false) MultipartFile logbook) {
		if (logbook != null) {
			String message = "";
			try {
				storageService.insertFile(logbook);
				message = "Complete upload file : " + logbook.getOriginalFilename();
			} catch (Exception e) {
				message = "Fail upload file : " + logbook.getOriginalFilename() + ". Error : " + e.getMessage();
			}
			System.out.println(message);
			
			studentBadgeRequest.setStudentBadgeLogbook(logbook.getOriginalFilename());
		}
		
		return coreService.updateStudentBadge(studentBadgeRequest);
	}
	
	@PutMapping("/assessment")
	@ResponseBody
	public AssessmentResponse updateAssessment(@RequestPart(value="assessmentRequest") AssessmentRequest assessmentRequest) {
		return coreService.updateAssessment(assessmentRequest);
	}
	
	@PutMapping("/studentAssessment")
	@ResponseBody
	public StudentAssessmentResponse updateStudentAssessment(@RequestPart(value="studentAssessmentRequest") StudentAssessmentRequest studentAssessmentRequest) {
		return coreService.updateStudentAssessment(studentAssessmentRequest);
	}
	
	// (D) DELETE badge data by primary key
	@DeleteMapping("/badge/{badgeId}")
	@ResponseBody
	public Boolean deleteBadge(@PathVariable("badgeId") String badgeId) {
		return coreService.deleteBadge(badgeId);
	}
	
	@DeleteMapping("/studentBadge/{studentBadgeId}")
	@ResponseBody
	public Boolean deleteStudentBadge(@PathVariable("studentBadgeId") String studentBadgeId) {
		return coreService.deleteStudentBadge(studentBadgeId);
	}
	
	@DeleteMapping("/assessment/{assessmentId}")
	@ResponseBody
	public Boolean deleteAssessment(@PathVariable("assessmentId") String assessmentId) {
		return coreService.deleteAssessment(assessmentId);
	}
	
	@DeleteMapping("/studentAssessment/{studentAssessmentId}")
	@ResponseBody
	public Boolean deleteStudentAssessment(@PathVariable("studentAssessmentId") String studentAssessmentId) {
		return coreService.deleteStudentAssessment(studentAssessmentId);
	}
	
	@GetMapping("/student/badge/submission")
	public String viewStudentBadgeSubmission() {
		return "student_badge_submission";
	}
	
	@GetMapping("/student/badge/approval")
	public String viewStudentBadgeApproval() {
		return "student_badge_approval_list";
	}
	
	@GetMapping("/student/badge/approval/pending")
	@ResponseBody
	public List<StudentResponse> getStudentsPendingApproval() {
		return coreService.getStudentsPendingApproval();
	}
	
	@GetMapping("/student/badge/approval/{studentId}")
	public String viewStudentBadgeApprovalByStudentId() {
		return "student_badge_approval";
	}
	
	@GetMapping("/student/assessment/application")
	public String viewStudentAssessmentApplication() {
		return "student_assessment_application_list";
	}
	
	@GetMapping("/student/assessment/evaluation")
	public String viewStudentAssessmenEvaluation() {
		return "student_assessment_evaluation_list";
	}
	
	@GetMapping("/student/assessment/application/{studentId}/{assessmentId}")
	@ResponseBody
	public StudentAssessmentResponse getStudentAssessmentApplication(@PathVariable("studentId") String studentId, @PathVariable("assessmentId") String assessmentId) {
		StudentAssessmentResponse studentAssessmentResponse = coreService.getStudentAssessmentApplication(studentId, assessmentId);
		return BaseUtility.isObjectNotNull(studentAssessmentResponse) ? studentAssessmentResponse : null;
	}
	
	@GetMapping("/student/assessments/applications/{studentId}")
	@ResponseBody
	public List<StudentAssessmentResponse> getStudentAssessmentsApplications(@PathVariable("studentId") String studentId) {
		return coreService.getStudentAssessmentsApplications(studentId);
	}
}

	
	
	
	
	
	
	
