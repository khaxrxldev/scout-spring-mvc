package com.scout.entity;


import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "system_student_assessment")
public class StudentAssessmentEntity {
	@Id
	@Column(name = "student_assessment_id")
	private String studentAssessmentId;
	
	@Column(name = "student_assessment_result")
	private String studentAssessmentResult;
	
	@Column(name = "student_assessment_result_date")
	private Date studentAssessmentResultDate;
	
	@Column(name = "student_id")
	private String studentId;
	
	@Column(name = "evaluator_id")
	private String evaluatorId;
	
	@Column(name = "assessment_id")
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
