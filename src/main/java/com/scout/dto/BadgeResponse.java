package com.scout.dto;

public class BadgeResponse {
	
	private String badgeId;
	
	private String badgeName;
	
	private Integer badgeOrder;
	
	private String badgeForm;
	
	private String badgeIdFk;
	
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
