package com.scout.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "system_student")
public class StudentEntity {
	
	@Id
	@Column(name = "student_id")
	private String studentId;
	
	@Column(name = "student_email")
	private String studentEmail;
	
	@Column(name = "student_phone_no")
	private String studentPhoneNo;
	
	@Column(name = "student_name")
	private String studentName;
	
	@Column(name = "student_password")
	private String studentPassword;
	
	@Column(name = "student_address")
	private String studentAddress;
	
	@Column(name = "student_age")
	private Integer studentAge;
	
	@Column(name = "student_gender")
	private String studentGender;
	
	@Column(name = "student_identification_no")
	private String studentIdentificationNo;
	
	@Column(name = "student_race")
	private String studentRace;
	
	@Column(name = "student_religion")
	private String studentReligion;
	
	@Column(name = "student_birth_date")
	private Date studentBirthDate;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getStudentPhoneNo() {
		return studentPhoneNo;
	}

	public void setStudentPhoneNo(String studentPhoneNo) {
		this.studentPhoneNo = studentPhoneNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentPassword() {
		return studentPassword;
	}

	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}

	public String getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}

	public Integer getStudentAge() {
		return studentAge;
	}

	public void setStudentAge(Integer studentAge) {
		this.studentAge = studentAge;
	}

	public String getStudentGender() {
		return studentGender;
	}

	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}

	public String getStudentIdentificationNo() {
		return studentIdentificationNo;
	}

	public void setStudentIdentificationNo(String studentIdentificationNo) {
		this.studentIdentificationNo = studentIdentificationNo;
	}

	public String getStudentRace() {
		return studentRace;
	}

	public void setStudentRace(String studentRace) {
		this.studentRace = studentRace;
	}

	public String getStudentReligion() {
		return studentReligion;
	}

	public void setStudentReligion(String studentReligion) {
		this.studentReligion = studentReligion;
	}

	public Date getStudentBirthDate() {
		return studentBirthDate;
	}

	public void setStudentBirthDate(Date studentBirthDate) {
		this.studentBirthDate = studentBirthDate;
	}
}
