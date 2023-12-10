package com.scout.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "system_evaluator")
public class EvaluatorEntity {
	
	@Id
	@Column(name = "evaluator_id")
	private String evaluatorId;
	
	@Column(name = "evaluator_email")
	private String evaluatorEmail;
	
	@Column(name = "evaluator_password")
	private String evaluatorPassword;
	
	@Column(name = "evaluator_name")
	private String evaluatorName;
	
	@Column(name = "evaluator_position")
	private String evaluatorPosition;
	
	public String getEvaluatorId() {
		return evaluatorId;
	}

	public void setEvaluatorId(String evaluatorId) {
		this.evaluatorId = evaluatorId;
	}

	public String getEvaluatorEmail() {
		return evaluatorEmail;
	}

	public void setEvaluatorEmail(String evaluatorEmail) {
		this.evaluatorEmail = evaluatorEmail;
	}

	public String getEvaluatorPassword() {
		return evaluatorPassword;
	}

	public void setEvaluatorPassword(String evaluatorPassword) {
		this.evaluatorPassword = evaluatorPassword;
	}

	public String getEvaluatorName() {
		return evaluatorName;
	}

	public void setEvaluatorName(String evaluatorName) {
		this.evaluatorName = evaluatorName;
	}

	public String getEvaluatorPosition() {
		return evaluatorPosition;
	}

	public void setEvaluatorPosition(String evaluatorPosition) {
		this.evaluatorPosition = evaluatorPosition;
	}
}
