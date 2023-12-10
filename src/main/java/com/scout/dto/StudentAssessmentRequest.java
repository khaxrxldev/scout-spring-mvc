package com.scout.dto;

import java.util.Date;

public class StudentAssessmentRequest {

	private String studentAssessmentId;
	
	private String studentAssessmentResult;
	
	private Date studentAssessmentResultDate;
	
	private String studentId;
	
	private String evaluatorId;
	
	private String assessmentId;

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

	public String getEvaluatorId() {
		return evaluatorId;
	}

	public void setEvaluatorId(String evaluatorId) {
		this.evaluatorId = evaluatorId;
	}

	public String getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}
	
}

