package com.scout.dto;

public class ReportResponse {

	private StudentResponse studentResponse;
	
	private GuardianResponse guardianResponse;
	
	private MembershipResponse membershipResponse;

	public StudentResponse getStudentResponse() {
		return studentResponse;
	}

	public void setStudentResponse(StudentResponse studentResponse) {
		this.studentResponse = studentResponse;
	}

	public GuardianResponse getGuardianResponse() {
		return guardianResponse;
	}

	public void setGuardianResponse(GuardianResponse guardianResponse) {
		this.guardianResponse = guardianResponse;
	}

	public MembershipResponse getMembershipResponse() {
		return membershipResponse;
	}

	public void setMembershipResponse(MembershipResponse membershipResponse) {
		this.membershipResponse = membershipResponse;
	}
}
