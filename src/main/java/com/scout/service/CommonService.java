package com.scout.service;

import java.util.List;
import com.scout.dto.MembershipRequest;
import com.scout.dto.MembershipResponse;
import com.scout.dto.GuardianRequest;
import com.scout.dto.GuardianResponse;

public interface CommonService {
	
	List<MembershipResponse> getMemberships();
	
	MembershipResponse getMembership(String membershipId);
		
	MembershipResponse insertMembership(MembershipRequest membershipRequest);
	
	MembershipResponse updateMembership(MembershipRequest membershipRequest);
	
	MembershipResponse updateMembershipApproval(MembershipRequest membershipRequest);
	
	Boolean deleteMembership(String membershipId);

	List<GuardianResponse> getGuardians();
	
	GuardianResponse getGuardian(String guardianId);
	
	GuardianResponse getGuardianByStudentId(String studentId);
		
	GuardianResponse insertGuardian(GuardianRequest guardianRequest);
	
	GuardianResponse updateGuardian(GuardianRequest guardianRequest);
	
	Boolean deleteGuardian(String guardianId);
}
