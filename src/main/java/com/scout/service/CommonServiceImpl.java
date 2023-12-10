package com.scout.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.scout.utility.BaseUtility;
import jakarta.transaction.Transactional;

import com.scout.entity.MembershipEntity;
import com.scout.entity.GuardianEntity;

import com.scout.dao.MembershipRepository;
import com.scout.dao.GuardianRepository;

import com.scout.dto.MembershipRequest;
import com.scout.dto.MembershipResponse;
import com.scout.dto.GuardianRequest;
import com.scout.dto.GuardianResponse;

@Service
public class CommonServiceImpl implements CommonService {
	
	@Autowired
	MembershipRepository membershipRepository;
	
	@Autowired
	GuardianRepository guardianRepository;
	@Override
	public List<MembershipResponse> getMemberships() {
	    List<MembershipResponse> membershipResponses = new ArrayList < > ();
	    List<MembershipEntity> membershipEntities = membershipRepository.findAll();

	    for (MembershipEntity membershipEntity: membershipEntities) {
	        MembershipResponse membershipResponse = new MembershipResponse();

	        membershipResponse.setMembershipId(membershipEntity.getMembershipId());
	        membershipResponse.setMembershipType(membershipEntity.getMembershipType());
	        membershipResponse.setMembershipUnit(membershipEntity.getMembershipUnit());
	        membershipResponse.setMembershipRank(membershipEntity.getMembershipRank());
	        membershipResponse.setMembershipNo(membershipEntity.getMembershipNo());
	        membershipResponse.setMembershipJoinedYear(membershipEntity.getMembershipJoinedYear());
	        membershipResponse.setMembershipStudentForm(membershipEntity.getMembershipStudentForm());
	        membershipResponse.setStudentId(membershipEntity.getStudentId());
	        membershipResponse.setMembershipPayStatus(membershipEntity.getMembershipPayStatus());
	        membershipResponse.setMembershipPayDate(membershipEntity.getMembershipPayDate());
	        membershipResponse.setMembershipPayReceipt(membershipEntity.getMembershipPayReceipt());

	        membershipResponses.add(membershipResponse);
	    }

	    return membershipResponses;
	}

	@Override
	public MembershipResponse getMembership(String membershipId) {
	    MembershipResponse membershipResponse = new MembershipResponse();
	    MembershipEntity membershipEntity = membershipRepository.findByMembershipId(membershipId);

	    if (BaseUtility.isObjectNotNull(membershipEntity)) {
	        membershipResponse.setMembershipId(membershipEntity.getMembershipId());
	        membershipResponse.setMembershipType(membershipEntity.getMembershipType());
	        membershipResponse.setMembershipUnit(membershipEntity.getMembershipUnit());
	        membershipResponse.setMembershipRank(membershipEntity.getMembershipRank());
	        membershipResponse.setMembershipNo(membershipEntity.getMembershipNo());
	        membershipResponse.setMembershipJoinedYear(membershipEntity.getMembershipJoinedYear());
	        membershipResponse.setMembershipStudentForm(membershipEntity.getMembershipStudentForm());
	        membershipResponse.setStudentId(membershipEntity.getStudentId());
	        membershipResponse.setMembershipPayStatus(membershipEntity.getMembershipPayStatus());
	        membershipResponse.setMembershipPayDate(membershipEntity.getMembershipPayDate());
	        membershipResponse.setMembershipPayReceipt(membershipEntity.getMembershipPayReceipt());
	    }

	    return membershipResponse;
	}

	@Override
	public MembershipResponse insertMembership(MembershipRequest membershipRequest) {
	    MembershipResponse membershipResponse = new MembershipResponse();
	    MembershipEntity newMembershipEntity = new MembershipEntity();

	    newMembershipEntity.setMembershipId(BaseUtility.generateId());
	    newMembershipEntity.setMembershipType(membershipRequest.getMembershipType());
	    newMembershipEntity.setMembershipUnit(membershipRequest.getMembershipUnit());
	    newMembershipEntity.setMembershipRank(membershipRequest.getMembershipRank());
	    newMembershipEntity.setMembershipNo(membershipRequest.getMembershipNo());
	    newMembershipEntity.setMembershipJoinedYear(membershipRequest.getMembershipJoinedYear());
	    newMembershipEntity.setMembershipStudentForm(membershipRequest.getMembershipStudentForm());
	    newMembershipEntity.setStudentId(membershipRequest.getStudentId());
	    String defaultStatus = "PENDING APPROVAL";
	    newMembershipEntity.setMembershipPayStatus(defaultStatus);

	    Date currentDate = new Date();
	    newMembershipEntity.setMembershipPayDate(currentDate);
	    newMembershipEntity.setMembershipPayReceipt(membershipRequest.getMembershipPayReceipt());

	    MembershipEntity insertedMembershipEntity = membershipRepository.save(newMembershipEntity);

	    if (BaseUtility.isObjectNotNull(insertedMembershipEntity)) {
	        membershipResponse.setMembershipType(insertedMembershipEntity.getMembershipId());
	        membershipResponse.setMembershipType(insertedMembershipEntity.getMembershipType());
	        membershipResponse.setMembershipUnit(insertedMembershipEntity.getMembershipUnit());
	        membershipResponse.setMembershipRank(insertedMembershipEntity.getMembershipRank());
	        membershipResponse.setMembershipNo(insertedMembershipEntity.getMembershipNo());
	        membershipResponse.setMembershipJoinedYear(insertedMembershipEntity.getMembershipJoinedYear());
	        membershipResponse.setMembershipStudentForm(insertedMembershipEntity.getMembershipStudentForm());
	        membershipResponse.setStudentId(insertedMembershipEntity.getStudentId());
	        membershipResponse.setMembershipPayStatus(insertedMembershipEntity.getMembershipPayStatus());
	        membershipResponse.setMembershipPayDate(insertedMembershipEntity.getMembershipPayDate());
	        membershipResponse.setMembershipPayReceipt(insertedMembershipEntity.getMembershipPayReceipt());
	    }

	    return membershipResponse;
	}

	@Override
	public MembershipResponse updateMembership(MembershipRequest membershipRequest) {
	    MembershipResponse membershipResponse = new MembershipResponse();
	    MembershipEntity existedMembershipEntity = membershipRepository.findByMembershipId(membershipRequest.getMembershipId());

	    existedMembershipEntity.setMembershipType(membershipRequest.getMembershipType());
	    existedMembershipEntity.setMembershipUnit(membershipRequest.getMembershipUnit());
	    existedMembershipEntity.setMembershipRank(membershipRequest.getMembershipRank());
	    existedMembershipEntity.setMembershipNo(membershipRequest.getMembershipNo());
	    existedMembershipEntity.setMembershipJoinedYear(membershipRequest.getMembershipJoinedYear());
	    existedMembershipEntity.setMembershipStudentForm(membershipRequest.getMembershipStudentForm());
	    existedMembershipEntity.setMembershipPayReceipt(membershipRequest.getMembershipPayReceipt());

	    MembershipEntity updatedMembershipEntity = membershipRepository.save(existedMembershipEntity);

	    if (BaseUtility.isObjectNotNull(updatedMembershipEntity)) {
	        membershipResponse.setMembershipType(updatedMembershipEntity.getMembershipId());
	        membershipResponse.setMembershipType(updatedMembershipEntity.getMembershipType());
	        membershipResponse.setMembershipUnit(updatedMembershipEntity.getMembershipUnit());
	        membershipResponse.setMembershipRank(updatedMembershipEntity.getMembershipRank());
	        membershipResponse.setMembershipNo(updatedMembershipEntity.getMembershipNo());
	        membershipResponse.setMembershipJoinedYear(updatedMembershipEntity.getMembershipJoinedYear());
	        membershipResponse.setMembershipStudentForm(updatedMembershipEntity.getMembershipStudentForm());
	        membershipResponse.setStudentId(updatedMembershipEntity.getStudentId());
	        membershipResponse.setMembershipPayStatus(updatedMembershipEntity.getMembershipPayStatus());
	        membershipResponse.setMembershipPayDate(updatedMembershipEntity.getMembershipPayDate());
	        membershipResponse.setMembershipPayReceipt(updatedMembershipEntity.getMembershipPayReceipt());
	    }

	    return membershipResponse;
	}

	@Override
	public MembershipResponse updateMembershipApproval(MembershipRequest membershipRequest) {
	    MembershipResponse membershipResponse = new MembershipResponse();
	    MembershipEntity existedMembershipEntity = membershipRepository.findByMembershipId(membershipRequest.getMembershipId());

	    existedMembershipEntity.setMembershipPayStatus(membershipRequest.getMembershipPayStatus());

	    MembershipEntity updatedMembershipEntity = membershipRepository.save(existedMembershipEntity);

	    if (BaseUtility.isObjectNotNull(updatedMembershipEntity)) {
	        membershipResponse.setMembershipType(updatedMembershipEntity.getMembershipId());
	        membershipResponse.setMembershipType(updatedMembershipEntity.getMembershipType());
	        membershipResponse.setMembershipUnit(updatedMembershipEntity.getMembershipUnit());
	        membershipResponse.setMembershipRank(updatedMembershipEntity.getMembershipRank());
	        membershipResponse.setMembershipNo(updatedMembershipEntity.getMembershipNo());
	        membershipResponse.setMembershipJoinedYear(updatedMembershipEntity.getMembershipJoinedYear());
	        membershipResponse.setMembershipStudentForm(updatedMembershipEntity.getMembershipStudentForm());
	        membershipResponse.setStudentId(updatedMembershipEntity.getStudentId());
	        membershipResponse.setMembershipPayStatus(updatedMembershipEntity.getMembershipPayStatus());
	        membershipResponse.setMembershipPayDate(updatedMembershipEntity.getMembershipPayDate());
	        membershipResponse.setMembershipPayReceipt(updatedMembershipEntity.getMembershipPayReceipt());
	    }

	    return membershipResponse;
	}

	@Transactional
	public Boolean deleteMembership(String membershipId) {
	    Integer totalDeleted = 0;

	    try {
	        totalDeleted = membershipRepository.deleteByMembershipId(membershipId);
	    } catch (Exception exception) {
	        System.out.println(exception.getMessage());
	    }

	    if (totalDeleted > 0) {
	        return true;
	    }
	    return false;
	}

	@Override
	public List<GuardianResponse> getGuardians() {
	    List<GuardianResponse> guardianResponses = new ArrayList < > ();
	    List<GuardianEntity> guardianEntities = guardianRepository.findAll();

	    for (GuardianEntity guardianEntity: guardianEntities) {
	        GuardianResponse guardianResponse = new GuardianResponse();

	        guardianResponse.setGuardianId(guardianEntity.getGuardianId());
	        guardianResponse.setGuardianName(guardianEntity.getGuardianName());
	        guardianResponse.setGuardianPhoneNo(guardianEntity.getGuardianPhoneNo());
	        guardianResponse.setGuardianConnection(guardianEntity.getGuardianConnection());
	        guardianResponse.setStudentId(guardianEntity.getStudentId());

	        guardianResponses.add(guardianResponse);
	    }

	    return guardianResponses;
	}

	@Override
	public GuardianResponse getGuardian(String guardianId) {
	    GuardianResponse guardianResponse = new GuardianResponse();
	    GuardianEntity guardianEntity = guardianRepository.findByGuardianId(guardianId);

	    if (BaseUtility.isObjectNotNull(guardianEntity)) {
	        guardianResponse.setGuardianId(guardianEntity.getGuardianId());
	        guardianResponse.setGuardianName(guardianEntity.getGuardianName());
	        guardianResponse.setGuardianPhoneNo(guardianEntity.getGuardianPhoneNo());
	        guardianResponse.setGuardianConnection(guardianEntity.getGuardianConnection());
	        guardianResponse.setStudentId(guardianEntity.getStudentId());
	    }

	    return guardianResponse;
	}

	@Override
	public GuardianResponse getGuardianByStudentId(String studentId) {
	    GuardianResponse guardianResponse = new GuardianResponse();
	    GuardianEntity guardianEntity = guardianRepository.findByStudentId(studentId);

	    if (BaseUtility.isObjectNotNull(guardianEntity)) {
	        guardianResponse.setGuardianId(guardianEntity.getGuardianId());
	        guardianResponse.setGuardianName(guardianEntity.getGuardianName());
	        guardianResponse.setGuardianPhoneNo(guardianEntity.getGuardianPhoneNo());
	        guardianResponse.setGuardianConnection(guardianEntity.getGuardianConnection());
	        guardianResponse.setStudentId(guardianEntity.getStudentId());
	    }

	    return guardianResponse;
	}

	@Override
	public GuardianResponse insertGuardian(GuardianRequest guardianRequest) {
	    GuardianResponse guardianResponse = new GuardianResponse();
	    GuardianEntity newGuardianEntity = new GuardianEntity();

	    newGuardianEntity.setGuardianId(BaseUtility.generateId());
	    newGuardianEntity.setGuardianName(guardianRequest.getGuardianName());
	    newGuardianEntity.setGuardianPhoneNo(guardianRequest.getGuardianPhoneNo());
	    newGuardianEntity.setGuardianConnection(guardianRequest.getGuardianConnection());
	    newGuardianEntity.setStudentId(guardianRequest.getStudentId());

	    GuardianEntity insertedGuardianEntity = guardianRepository.save(newGuardianEntity);

	    if (BaseUtility.isObjectNotNull(insertedGuardianEntity)) {
	        guardianResponse.setGuardianId(insertedGuardianEntity.getGuardianId());
	        guardianResponse.setGuardianName(insertedGuardianEntity.getGuardianName());
	        guardianResponse.setGuardianPhoneNo(insertedGuardianEntity.getGuardianPhoneNo());
	        guardianResponse.setGuardianConnection(insertedGuardianEntity.getGuardianConnection());
	        guardianResponse.setStudentId(insertedGuardianEntity.getStudentId());
	    }

	    return guardianResponse;
	}

	@Override
	public GuardianResponse updateGuardian(GuardianRequest guardianRequest) {
	    GuardianResponse guardianResponse = new GuardianResponse();
	    GuardianEntity existedGuardianEntity = guardianRepository.findByGuardianId(guardianRequest.getGuardianId());

	    existedGuardianEntity.setGuardianName(guardianRequest.getGuardianName());
	    existedGuardianEntity.setGuardianPhoneNo(guardianRequest.getGuardianPhoneNo());
	    existedGuardianEntity.setGuardianConnection(guardianRequest.getGuardianConnection());
	    existedGuardianEntity.setStudentId(guardianRequest.getStudentId());

	    GuardianEntity updatedGuardianEntity = guardianRepository.save(existedGuardianEntity);

	    if (BaseUtility.isObjectNotNull(updatedGuardianEntity)) {
	        guardianResponse.setGuardianId(updatedGuardianEntity.getGuardianId());
	        guardianResponse.setGuardianName(updatedGuardianEntity.getGuardianName());
	        guardianResponse.setGuardianPhoneNo(updatedGuardianEntity.getGuardianPhoneNo());
	        guardianResponse.setGuardianConnection(updatedGuardianEntity.getGuardianConnection());
	        guardianResponse.setStudentId(updatedGuardianEntity.getStudentId());
	    }

	    return guardianResponse;
	}

	@Transactional
	public Boolean deleteGuardian(String guardianId) {
	    Integer totalDeleted = 0;

	    try {
	        totalDeleted = guardianRepository.deleteByGuardianId(guardianId);
	    } catch (Exception exception) {
	        System.out.println(exception.getMessage());
	    }

	    if (totalDeleted > 0) {
	        return true;
	    }

	    return false;
	}
}