package com.scout.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "system_badge")
public class BadgeEntity {
	
	@Id
	@Column(name = "badge_id")
	private String badgeId;
	
	@Column(name = "badge_name")
	private String badgeName;
	
	@Column(name = "badge_order")
	private Integer badgeOrder;
	
	@Column(name = "badge_form")
	private String badgeForm;
	
	@Column(name = "badge_id_fk")
	private String badgeIdFk;
	
	@Column(name = "badge_category")
	private String badgeCategory;

	public String getBadgeId() {
		return badgeId;
	}

	public void setBadgeId(String badgeId) {
		this.badgeId = badgeId;
	}

	public String getBadgeName() {
		return badgeName;
	}

	public void setBadgeName(String badgeName) {
		this.badgeName = badgeName;
	}

	public Integer getBadgeOrder() {
		return badgeOrder;
	}

	public void setBadgeOrder(Integer badgeOrder) {
		this.badgeOrder = badgeOrder;
	}

	public String getBadgeForm() {
		return badgeForm;
	}

	public void setBadgeForm(String badgeForm) {
		this.badgeForm = badgeForm;
	}

	public String getBadgeIdFk() {
		return badgeIdFk;
	}

	public void setBadgeIdFk(String badgeIdFk) {
		this.badgeIdFk = badgeIdFk;
	}

	public String getBadgeCategory() {
		return badgeCategory;
	}

	public void setBadgeCategory(String badgeCategory) {
		this.badgeCategory = badgeCategory;
	}
}
