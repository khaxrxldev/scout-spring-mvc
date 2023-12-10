package com.scout.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "system_membership")
public class MembershipEntity {

	@Id
	@Column(name = "membership_id")
	private String membershipId;
	
	@Column(name = "membership_type")
	private String membershipType;
	
	@Column(name = "membership_unit")
	private String membershipUnit;
	
	@Column(name = "membership_rank")
	private String membershipRank;
	
	@Column(name = "membership_no")
	private String membershipNo;
	
	@Column(name = "membership_Joined_Year")
	private String membershipJoinedYear;
	
	@Column(name = "membership_student_form")
	private Integer membershipStudentForm;
	
	@Column(name = "membership_pay_status")
	private String membershipPayStatus;
	
	@Column(name = "membership_pay_receipt")
	private String membershipPayReceipt;
	
	@Column(name = "membership_pay_date")
	private Date membershipPayDate;
	
	@Column(name = "student_id")
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
