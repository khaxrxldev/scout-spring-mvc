package com.scout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scout.entity.BadgeEntity;

@Repository
public interface BadgeRepository extends JpaRepository<BadgeEntity, Long> {
		
	BadgeEntity findByBadgeId(String badgeId);
				
	Integer deleteByBadgeId(String badgeId);
}
