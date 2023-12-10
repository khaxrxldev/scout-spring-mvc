package com.scout.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "system_student_badge")
public class StudentBadgeEntity {
	
	@Id
	@Column(name = "student_badge_id")
	private String studentBadgeId;
	
	@Column(name = "student_badge_status")
	private String studentBadgeStatus;
	
	@Column(name = "student_badge_logbook")
	private String studentBadgeLogbook;

	@Column(name = "student_id")
	private String studentId;
	
	@Column(name = "teacher_id")
	private String teacherId;
	
	@Column(name = "badge_id")
	private String badgeId;
	
	@Column(name = "student_badge_approval_date")
	private Date studentApprovalDate;
	
	@Column(name = "student_badge_serial_num")
	private String studentBadgeSerialNum;
	
	@Column(name = "student_badge_sub")
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
