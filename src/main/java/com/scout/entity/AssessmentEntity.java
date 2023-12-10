package com.scout.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "system_assessment")
public class AssessmentEntity {
	
	@Id
	@Column(name = "assessment_id")
	private String assessmentId;
	
	@Column(name = "assessment_name")
	private String assessmentName;

	public String getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}

	public String getAssessmentName() {
		return assessmentName;
	}

	public void setAssessmentName(String assessmentName) {
		this.assessmentName = assessmentName;
	}
	
	
}
