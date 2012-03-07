package com.chinarewards.gwt.elt.client.rewards.model;

import java.io.Serializable;
import java.util.Date;

import com.chinarewards.gwt.elt.client.rewards.model.RewardsCriteria.RewardsStatus;

public class RewardsGridClient implements Serializable,
		Comparable<RewardsGridClient> {

	private static final long serialVersionUID = 1L;

	private String rewardsId;
	private String rewardsName;

	private String awardAmt;// 奖励积分
	private String awardName;// 颁奖人
	
	private String rewardStatusName;

	// 奖项
	private String rewardsItemId;
	private String rewardsItemName;
	private String rewardsItemPhoto;
	private String rewardsItemDefinition;
	private String rewardsItemCreateBy;// 奖项创建人

	/** 奖项积分 **/
	private double totalAmtLimit;

	private String corporationId;

	private Date rewardsDate;

	private RewardsStatus status;

	private ParticipateInfoClient tmInfo;// 提名人

	private String nominateName = "";
	private int nominateCount;

	private String winnersName;

	public RewardsGridClient() {

	}
	
	

	public String getRewardStatusName() {
		return rewardStatusName;
	}



	public void setRewardStatusName(String rewardStatusName) {
		this.rewardStatusName = rewardStatusName;
	}



	public String getRewardsItemCreateBy() {
		return rewardsItemCreateBy;
	}

	public void setRewardsItemCreateBy(String rewardsItemCreateBy) {
		this.rewardsItemCreateBy = rewardsItemCreateBy;
	}

	public String getAwardName() {
		return awardName;
	}

	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}

	public String getWinnersName() {
		return winnersName;
	}

	public void setWinnersName(String winnersName) {
		this.winnersName = winnersName;
	}

	public String getNominateName() {
		return nominateName;
	}

	public void setNominateName(String nominateName) {
		this.nominateName = nominateName;
	}

	public int getNominateCount() {
		return nominateCount;
	}

	public void setNominateCount(int nominateCount) {
		this.nominateCount = nominateCount;
	}

	public ParticipateInfoClient getTmInfo() {
		return tmInfo;
	}

	public void setTmInfo(ParticipateInfoClient tmInfo) {
		this.tmInfo = tmInfo;
	}

	public Date getRewardsDate() {
		return rewardsDate;
	}

	public void setRewardsDate(Date rewardsDate) {
		this.rewardsDate = rewardsDate;
	}

	public String getRewardsItemPhoto() {
		return rewardsItemPhoto;
	}

	public void setRewardsItemPhoto(String rewardsItemPhoto) {
		this.rewardsItemPhoto = rewardsItemPhoto;
	}

	public String getRewardsItemDefinition() {
		return rewardsItemDefinition;
	}

	public void setRewardsItemDefinition(String rewardsItemDefinition) {
		this.rewardsItemDefinition = rewardsItemDefinition;
	}

	public String getRewardsId() {
		return rewardsId;
	}

	public void setRewardsId(String rewardsId) {
		this.rewardsId = rewardsId;
	}

	public String getRewardsName() {
		return rewardsName;
	}

	public void setRewardsName(String rewardsName) {
		this.rewardsName = rewardsName;
	}

	public String getAwardAmt() {
		return awardAmt;
	}

	public void setAwardAmt(String awardAmt) {
		this.awardAmt = awardAmt;
	}

	public String getRewardsItemName() {
		return rewardsItemName;
	}

	public void setRewardsItemName(String rewardsItemName) {
		this.rewardsItemName = rewardsItemName;
	}

	public RewardsStatus getStatus() {
		return status;
	}

	public void setStatus(RewardsStatus status) {
		this.status = status;
	}

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

	public String getRewardsItemId() {
		return rewardsItemId;
	}

	public void setRewardsItemId(String rewardsItemId) {
		this.rewardsItemId = rewardsItemId;
	}

	@Override
	public String toString() {
		return "RewardsClient [baseInfo, status=" + status + " rewardsItemId="
				+ rewardsItemId + ", rewardsItemName=" + rewardsItemName
				+ ", corporationId=" + corporationId + "]";
	}

	public double getTotalAmtLimit() {
		return totalAmtLimit;
	}

	public void setTotalAmtLimit(double totalAmtLimit) {
		this.totalAmtLimit = totalAmtLimit;
	}

	@Override
	public int compareTo(RewardsGridClient arg0) {
		return 0;
	}

}
