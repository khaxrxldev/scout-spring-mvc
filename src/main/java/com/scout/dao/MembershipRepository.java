package com.scout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scout.entity.MembershipEntity;

public interface MembershipRepository extends JpaRepository<MembershipEntity, Long> {
	
	MembershipEntity findByMembershipId(String membershipId);
		
	Integer deleteByMembershipId(String membershipId);

}
