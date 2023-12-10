package com.scout.controller;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.scout.dto.MembershipRequest;
import com.scout.dto.MembershipResponse;
import com.scout.dto.GuardianRequest;
import com.scout.dto.GuardianResponse;
import com.scout.service.CommonService;
import com.scout.service.FilesStorageService;
import com.scout.utility.BaseUtility;

@Controller
@RequestMapping("/common")
public class CommonController {
	
	@Autowired
	CommonService commonService;

	@Autowired
	FilesStorageService storageService;
	
	// VIEW all memberships page
	@GetMapping("/membership/view")
	public String viewMembershipPage() {
		return "memberships";
	}
	
	@GetMapping("/payment/view")
	public String viewPaymentPage() {
		return "payments";
	}
	
	@GetMapping("/guardian/view")
	public String viewGuardianPage() {
		return "guardians";
	}
	
	// VIEW single membership page
	@GetMapping("/membership/update/{membershipId}")
	public String updateMembershipPage() {
		return "membership";
	}
	
	@GetMapping("/payment/update/{paymentId}")
	public String updatePaymentPage() {
		return "payment";
	}

	@GetMapping("/guardian/update/{guardianId}")
	public String updateGuardianPage() {
		return "guardian";
	}
	
	// (R) GET all memberships data
	@GetMapping("/memberships")
	@ResponseBody
	public List<MembershipResponse> getMemberships() {
		return commonService.getMemberships();
	}
	
	@GetMapping("/guardians")
	@ResponseBody
	public List<GuardianResponse> getGuardians() {
		return commonService.getGuardians();
	}
	
	// (R) GET single membership data by primary key
	@GetMapping("/membership/{membershipId}")
	@ResponseBody
	public MembershipResponse getMembership(@PathVariable("membershipId") String membershipId) {
		return commonService.getMembership(membershipId);
	}
	
	@GetMapping("/files/{membershipId}")
	@ResponseBody
	public String getFile(@PathVariable String membershipId) {
		MembershipResponse membershipResponse = commonService.getMembership(membershipId);
		
		if (BaseUtility.isObjectNotNull(membershipResponse)) {
			return Base64.getEncoder().encodeToString(storageService.retrieveFile(membershipResponse.getMembershipPayReceipt()));
		} else {
			return "";
		}
	}
	
	@GetMapping("/guardian/{guardianId}")
	@ResponseBody
	public GuardianResponse getGuardian(@PathVariable("guardianId") String guardianId) {
		return commonService.getGuardian(guardianId);
	}
	
	@GetMapping("/guardian/student/{studentId}")
	@ResponseBody
	public GuardianResponse getGuardianByStudentId(@PathVariable("studentId") String studentId) {
		return commonService.getGuardianByStudentId(studentId);
	}
	
	// (C) CREATE membership data
	@PostMapping("/membership")
	@ResponseBody
	public MembershipResponse insertMembership(@RequestPart(value="membershipRequest") MembershipRequest membershipRequest, @RequestParam(name = "receipt", required = false) MultipartFile receipt) {
		if (receipt != null) {
			String message = "";
			try {
				storageService.insertFile(receipt);
				message = "Complete upload file : " + receipt.getOriginalFilename();
			} catch (Exception e) {
				message = "Fail upload file : " + receipt.getOriginalFilename() + ". Error : " + e.getMessage();
			}
			System.out.println(message);
			
			membershipRequest.setMembershipPayReceipt(receipt.getOriginalFilename());
		}
		
		return commonService.insertMembership(membershipRequest);
	}
	
	@PostMapping("/guardian")
	@ResponseBody
	public GuardianResponse insertGuardian(@RequestPart(value="guardianRequest") GuardianRequest guardianRequest) {
		return commonService.insertGuardian(guardianRequest);
	}
	
	// (U) UPDATE membership data
	@PutMapping("/membership")
	@ResponseBody
	public MembershipResponse updateMembership(@RequestPart(value="membershipRequest") MembershipRequest membershipRequest, @RequestParam(name = "receipt", required = false) MultipartFile receipt) {
		if (receipt != null) {
			String message = "";
			try {
				storageService.insertFile(receipt);
				message = "Complete upload file : " + receipt.getOriginalFilename();
			} catch (Exception e) {
				message = "Fail upload file : " + receipt.getOriginalFilename() + ". Error : " + e.getMessage();
			}
			System.out.println(message);
			
			membershipRequest.setMembershipPayReceipt(receipt.getOriginalFilename());
		}
		
		return commonService.updateMembership(membershipRequest);
	}
	
	@PutMapping("/membership/approval")
	@ResponseBody
	public MembershipResponse updateMembershipApproval(@RequestPart(value="membershipRequest") MembershipRequest membershipRequest) {		
		return commonService.updateMembershipApproval(membershipRequest);
	}
	
	@PutMapping("/guardian")
	@ResponseBody
	public GuardianResponse updateGuardian(@RequestPart(value="guardianRequest") GuardianRequest guardianRequest) {
		return commonService.updateGuardian(guardianRequest);
	}
	
	// (D) DELETE membership data by primary key
	@DeleteMapping("/membership/{membershipId}")
	@ResponseBody
	public Boolean deleteMembership(@PathVariable("membershipId") String membershipId) {
		return commonService.deleteMembership(membershipId);
	}
	
	@DeleteMapping("/guardian/{guardianId}")
	@ResponseBody
	public Boolean deleteGuardian(@PathVariable("guardianId") String guardianId) {
		return commonService.deleteGuardian(guardianId);
	}
}
