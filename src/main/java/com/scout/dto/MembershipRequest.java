package com.scout.dto;

import java.util.Date;

public class MembershipRequest {
	
	private String membershipId;
	
	private String membershipType;
	
	private String membershipUnit;
	
	private String membershipRank;
	
	private String membershipNo;
	
	private String membershipJoinedYear;
	
	private Integer membershipStudentForm;
	
	private String membershipPayStatus;
	
	private String membershipPayReceipt;
	
	private Date membershipPayDate;
	
	private String studentId;

	public String getMembershipId() {
		return membershipId;
	}

	public void setMembershipId(String membershipId) {
		this.membershipId = membershipId;
	}

	public String getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(String membershipType) {
		this.membershipType = membershipType;
	}

	public String getMembershipUnit() {
		return membershipUnit;
	}

	public void setMembershipUnit(String membershipUnit) {
		this.membershipUnit = membershipUnit;
	}

	public String getMembershipRank() {
		return membershipRank;
	}

	public void setMembershipRank(String membershipRank) {
		this.membershipRank = membershipRank;
	}

	public String getMembershipNo() {
		return membershipNo;
	}

	public void setMembershipNo(String membershipNo) {
		this.membershipNo = membershipNo;
	}

	public String getMembershipJoinedYear() {
		return membershipJoinedYear;
	}

	public void setMembershipJoinedYear(String membershipJoinedYear) {
		this.membershipJoinedYear = membershipJoinedYear;
	}

	public Integer getMembershipStudentForm() {
		return membershipStudentForm;
	}

	public void setMembershipStudentForm(Integer membershipStudentForm) {
		this.membershipStudentForm = membershipStudentForm;
	}

	public String getMembershipPayStatus() {
		return membershipPayStatus;
	}

	public void setMembershipPayStatus(String membershipPayStatus) {
		this.membershipPayStatus = membershipPayStatus;
	}

	public String getMembershipPayReceipt() {
		return membershipPayReceipt;
	}

	public void setMembershipPayReceipt(String membershipPayReceipt) {
		this.membershipPayReceipt = membershipPayReceipt;
	}

	public Date getMembershipPayDate() {
		return membershipPayDate;
	}

	public void setMembershipPayDate(Date membershipPayDate) {
		this.membershipPayDate = membershipPayDate;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
}
