package com.scout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scout.dto.EvaluatorRequest;
import com.scout.dto.EvaluatorResponse;
import com.scout.dto.LoginRequest;
import com.scout.dto.LoginResponse;
import com.scout.dto.StudentRequest;
import com.scout.dto.StudentResponse;
import com.scout.dto.TeacherRequest;
import com.scout.dto.TeacherResponse;
import com.scout.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	// VIEW all users page
	@GetMapping("/teacher/view")
	public String viewTeacherPage() {
		return "teachers";
	}
	
	@GetMapping("/student/view") 
	public String viewStudentPage() {
		return "students";
	}
	
	@GetMapping("/evaluator/view") 
	public String viewEvaluatorPage() {
		return "evaluators";
	}

	// VIEW single user page
	@GetMapping("/teacher/update/{teacherId}")
	public String updateTeacherPage() {
		return "teacher";
	}

	@GetMapping("/student/update/{studentId}")
	public String updateStudentPage() {
		return "student";
	}

	@GetMapping("/evaluator/update/{evaluatorId}")
	public String updateEvaluatorPage() {
		return "evaluator";
	}

	// (R) GET all users data
	@GetMapping("/teachers")
	@ResponseBody
	public List<TeacherResponse> getTeachers() {
		return userService.getTeachers();
	}

	@GetMapping("/students")
	@ResponseBody
	public List<StudentResponse> getStudents() {
		return userService.getStudents();
	}

	@GetMapping("/evaluators")
	@ResponseBody
	public List<EvaluatorResponse> getEvaluators() {
		return userService.getEvaluators();
	}

	// (R) GET single user data by primary key
	@GetMapping("/teacher/{teacherId}")
	@ResponseBody
	public TeacherResponse getTeacher(@PathVariable("teacherId") String teacherId) {
		return userService.getTeacher(teacherId);
	}

	@GetMapping("/student/{studentId}")
	@ResponseBody
	public StudentResponse getStudent(@PathVariable("studentId") String studentId) {
		return userService.getStudent(studentId);
	}

	@GetMapping("/evaluator/{evaluatorId}")
	@ResponseBody
	public EvaluatorResponse getEvaluator(@PathVariable("evaluatorId") String evaluatorId) {
		return userService.getEvaluator(evaluatorId);
	}
	
	// LOGIN user
	@PostMapping("/teacher/login")
	@ResponseBody
	public LoginResponse loginTeacher(@RequestPart(value="loginRequest") LoginRequest loginRequest) {
		return userService.loginTeacher(loginRequest);
	}
	
	@PostMapping("/student/login")
	@ResponseBody
	public LoginResponse loginStudent(@RequestPart(value="loginRequest") LoginRequest loginRequest) {
		return userService.loginStudent(loginRequest);
	}
	
	@PostMapping("/evaluator/login")
	@ResponseBody
	public LoginResponse loginEvaluator(@RequestPart(value="loginRequest") LoginRequest loginRequest) {
		return userService.loginEvaluator(loginRequest);
	}
	
	// (C) INSERT user data
	@PostMapping("/teacher")
	@ResponseBody
	public TeacherResponse insertTeacher(@RequestPart(value="teacherRequest") TeacherRequest teacherRequest) {
		return userService.insertTeacher(teacherRequest);
	}
	
	@PostMapping("/student")
	@ResponseBody
	public StudentResponse insertStudent(@RequestPart(value="studentRequest") StudentRequest studentRequest) {
		return userService.insertStudent(studentRequest);
	}
	
	@PostMapping("/evaluator")
	@ResponseBody
	public EvaluatorResponse insertEvaluator(@RequestPart(value="evaluatorRequest") EvaluatorRequest evaluatorRequest) {
		return userService.insertEvaluator(evaluatorRequest);
	}
	
	// (U) UPDATE user data
	@PutMapping("/teacher")
	@ResponseBody
	public TeacherResponse updateTeacher(@RequestPart(value="teacherRequest") TeacherRequest teacherRequest) {
		return userService.updateTeacher(teacherRequest);
	}
	
	@PutMapping("/student")
	@ResponseBody
	public StudentResponse updateStudent(@RequestPart(value="studentRequest") StudentRequest studentRequest) {
		return userService.updateStudent(studentRequest);
	}
	
	@PutMapping("/evaluator")
	@ResponseBody
	public EvaluatorResponse updateEvaluator(@RequestPart(value="evaluatorRequest") EvaluatorRequest evaluatorRequest) {
		return userService.updateEvaluator(evaluatorRequest);
	}
	
	// (D) DELETE user data by primary key
	@DeleteMapping("/teacher/{teacherId}")
	@ResponseBody
	public Boolean deleteTeacher(@PathVariable("teacherId") String teacherId) {
		return userService.deleteTeacher(teacherId);
	}
	
	@DeleteMapping("/student/{studentId}")
	@ResponseBody
	public Boolean deleteStudent(@PathVariable("studentId") String studentId) {
		return userService.deleteStudent(studentId);
	}
	
	@DeleteMapping("/evaluator/{evaluatorId}")
	@ResponseBody
	public Boolean deleteEvaluator(@PathVariable("evaluatorId") String evaluatorId) {
		return userService.deleteEvaluator(evaluatorId);
	}
}
