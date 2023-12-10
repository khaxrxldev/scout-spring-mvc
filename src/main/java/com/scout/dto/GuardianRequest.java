package com.scout.dto;

public class GuardianRequest {
	
	private String guardianId;
	
	private String guardianName;
	
	private String guardianPhoneNo;
	
	private String guardianConnection;
	
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
