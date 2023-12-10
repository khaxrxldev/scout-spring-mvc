package com.scout.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "system_teacher")
public class TeacherEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "teacher_id")
	private String teacherId;

	@Column(name = "teacher_email")
	private String teacherEmail;

	@Column(name = "teacher_phone_no")
	private String teacherPhoneNo;

	@Column(name = "teacher_name")
	private String teacherName;

	@Column(name = "teacher_address")
	private String teacherAddress;

	@Column(name = "teacher_password")
	private String teacherPassword;
	
	@Column(name = "teacher_category")
	private String teacherCategory;
	

	public String getTeacherCategory() {
		return teacherCategory;
	}

	public void setTeacherCategory(String teacherCategory) {
		this.teacherCategory = teacherCategory;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherEmail() {
		return teacherEmail;
	}

	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}

	public String getTeacherPhoneNo() {
		return teacherPhoneNo;
	}

	public void setTeacherPhoneNo(String teacherPhoneNo) {
		this.teacherPhoneNo = teacherPhoneNo;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherAddress() {
		return teacherAddress;
	}

	public void setTeacherAddress(String teacherAddress) {
		this.teacherAddress = teacherAddress;
	}

	public String getTeacherPassword() {
		return teacherPassword;
	}

	public void setTeacherPassword(String teacherPassword) {
		this.teacherPassword = teacherPassword;
	}
}
