package com.scout.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "system_guardian")
public class GuardianEntity {
	
	@Id
	@Column(name = "guardian_id")
	private String guardianId;
	
	@Column(name = "guardian_name")
	private String guardianName;
	
	@Column(name = "guardian_phone_no")
	private String guardianPhoneNo;
	
	@Column(name = "guardian_connection")
	private String guardianConnection;
	
	@Column(name = "student_id")
	private String studentId;

	public String getGuardianId() {
		return guardianId;
	}

	public void setGuardianId(String guardianId) {
		this.guardianId = guardianId;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public String getGuardianPhoneNo() {
		return guardianPhoneNo;
	}

	public void setGuardianPhoneNo(String guardianPhoneNo) {
		this.guardianPhoneNo = guardianPhoneNo;
	}

	public String getGuardianConnection() {
		return guardianConnection;
	}

	public void setGuardianConnection(String guardianConnection) {
		this.guardianConnection = guardianConnection;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
}
