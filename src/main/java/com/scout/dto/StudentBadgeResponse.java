package com.scout.dto;

import java.util.Date;

public class StudentBadgeResponse {

	private String studentBadgeId;
	
	private String studentBadgeStatus;
	
	private String studentBadgeLogbook;
	
	private String studentId;
	
	private String teacherId;
	
	private String badgeId;
	
	private BadgeResponse badge;
	
	private Date studentApprovalDate;

	private String studentBadgeSerialNum;

	private String studentBadgeSub;

	public String getStudentBadgeId() {
		return studentBadgeId;
	}

	public void setStudentBadgeId(String studentBadgeId) {
		this.studentBadgeId = studentBadgeId;
	}

	public String getStudentBadgeStatus() {
		return studentBadgeStatus;
	}

	public void setStudentBadgeStatus(String studentBadgeStatus) {
		this.studentBadgeStatus = studentBadgeStatus;
	}

	public String getStudentBadgeLogbook() {
		return studentBadgeLogbook;
	}

	public void setStudentBadgeLogbook(String studentBadgeLogbook) {
		this.studentBadgeLogbook = studentBadgeLogbook;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public BadgeResponse getBadge() {
		return badge;
	}

	public void setBadge(BadgeResponse badge) {
		this.badge = badge;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getBadgeId() {
		return badgeId;
	}

	public void setBadgeId(String badgeId) {
		this.badgeId = badgeId;
	}

	public Date getStudentApprovalDate() {
		return studentApprovalDate;
	}

	public void setStudentApprovalDate(Date studentApprovalDate) {
		this.studentApprovalDate = studentApprovalDate;
	}

	public String getStudentBadgeSerialNum() {
		return studentBadgeSerialNum;
	}

	public void setStudentBadgeSerialNum(String studentBadgeSerialNum) {
		this.studentBadgeSerialNum = studentBadgeSerialNum;
	}

	public String getStudentBadgeSub() {
		return studentBadgeSub;
	}

	public void setStudentBadgeSub(String studentBadgeSub) {
		this.studentBadgeSub = studentBadgeSub;
	}
}