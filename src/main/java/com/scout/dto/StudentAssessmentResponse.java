package com.scout.dto;

import java.util.Date;

public class StudentAssessmentResponse {
	
	private String studentAssessmentId;
	
	private String studentAssessmentResult;
	
	private Date studentAssessmentResultDate;
	
	private String studentId;
	
	private StudentResponse studentResponse;
	
	private String evaluatorId;
	
	private EvaluatorResponse evaluatorResponse;
	
	private String assessmentId;
	
	private AssessmentResponse assessmentResponse;

	public String getStudentAssessmentId() {
		return studentAssessmentId;
	}

	public void setStudentAssessmentId(String studentAssessmentId) {
		this.studentAssessmentId = studentAssessmentId;
	}

	public String getStudentAssessmentResult() {
		return studentAssessmentResult;
	}

	public void setStudentAssessmentResult(String studentAssessmentResult) {
		this.studentAssessmentResult = studentAssessmentResult;
	}

	public Date getStudentAssessmentResultDate() {
		return studentAssessmentResultDate;
	}

	public void setStudentAssessmentResultDate(Date studentAssessmentResultDate) {
		this.studentAssessmentResultDate = studentAssessmentResultDate;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public StudentResponse getStudentResponse() {
		return studentResponse;
	}

	public void setStudentResponse(StudentResponse studentResponse) {
		this.studentResponse = studentResponse;
	}

	public String getEvaluatorId() {
		return evaluatorId;
	}

	public void setEvaluatorId(String evaluatorId) {
		this.evaluatorId = evaluatorId;
	}

	public EvaluatorResponse getEvaluatorResponse() {
		return evaluatorResponse;
	}

	public void setEvaluatorResponse(EvaluatorResponse evaluatorResponse) {
		this.evaluatorResponse = evaluatorResponse;
	}

	public String getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}

	public AssessmentResponse getAssessmentResponse() {
		return assessmentResponse;
	}

	public void setAssessmentResponse(AssessmentResponse assessmentResponse) {
		this.assessmentResponse = assessmentResponse;
	}
}
