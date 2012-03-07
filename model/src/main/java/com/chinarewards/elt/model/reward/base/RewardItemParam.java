package com.chinarewards.elt.model.reward.base;

import java.util.Date;
import java.util.List;

import com.chinarewards.elt.model.reward.frequency.RewardsFrequency;
import com.chinarewards.elt.model.transaction.TransactionUnit;

/**
 * Create or edit RewardItem model.
 * 
 * @author yanxin
 * @since 1.0
 */
public class RewardItemParam {

	private String id;

	/**
	 * the full name
	 */
	private String name;

	/**
	 * RewardItem type id. If not exist, set null.
	 */
	private String typeId;

	/**
	 * definition
	 */
	private String definition;

	/**
	 * judge standard
	 */
	private String standard;

	/**
	 * headcount limit
	 */
	private int headcountLimit;

	/**
	 * Total amount limit
	 */
	private double totalAmtLimit;

	/**
	 * award amount
	 */
	private double awardAmt;

    private Date startTime;
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	

	
	/**
	 * transaction unit
	 */
	private TransactionUnit awardUnit;

	/**
	 * Which department build this RewardItem, it also means this RewardItem
	 * owns which department.
	 */
	private String builderDeptId;

	/**
	 * Which department pay this RewardItem.
	 */
	private String accountDeptId;

	/**
	 * The date expected to award. This field should copy to Reward when run a
	 * Reward out.
	 */
	private Date expectAwardDate;

	/**
	 * It defines whether require running Reward out by "batch system" at least
	 * one time.
	 */
	private RequireAutoGenerate autoGenerate;

	/**
	 * It defines whether the reward generated from RewardItem would award
	 * directly or others.
	 */
	private RequireAutoAward autoAward;

	/**
	 * The list of id who would have the power to judge.
	 */
	private List<String> judgeIds;

	/**
	 * How many days about the nominate date ahead of award date.
	 */
	private int nominateAheadDays;

	/**
	 * Someone who have the qualification to win this reward. The string should
	 * given the id of Organization.
	 */
	private List<String> candidateList;

	/**
	 * Whether a birthday reward.
	 */
	private boolean isDob;

	/**
	 * The date to publish this Reward. In other words, it is the date to run a
	 * reward out according to RewardItem.
	 */
	private Date publishDate;

	/**
	 * There will be frequency if it is a cyclic RewardItem.
	 */
	private RewardsFrequency frequency;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public int getHeadcountLimit() {
		return headcountLimit;
	}

	public void setHeadcountLimit(int headcountLimit) {
		this.headcountLimit = headcountLimit;
	}

	public double getTotalAmtLimit() {
		return totalAmtLimit;
	}

	public void setTotalAmtLimit(double totalAmtLimit) {
		this.totalAmtLimit = totalAmtLimit;
	}

	public double getAwardAmt() {
		return awardAmt;
	}

	public void setAwardAmt(double awardAmt) {
		this.awardAmt = awardAmt;
	}

	public TransactionUnit getAwardUnit() {
		return awardUnit;
	}

	public void setAwardUnit(TransactionUnit awardUnit) {
		this.awardUnit = awardUnit;
	}

	public String getBuilderDeptId() {
		return builderDeptId;
	}

	public void setBuilderDeptId(String builderDeptId) {
		this.builderDeptId = builderDeptId;
	}

	public String getAccountDeptId() {
		return accountDeptId;
	}

	public void setAccountDeptId(String accountDeptId) {
		this.accountDeptId = accountDeptId;
	}

	public Date getExpectAwardDate() {
		return expectAwardDate;
	}

	public void setExpectAwardDate(Date expectAwardDate) {
		this.expectAwardDate = expectAwardDate;
	}

	public RequireAutoGenerate getAutoGenerate() {
		return autoGenerate;
	}

	public void setAutoGenerate(RequireAutoGenerate autoGenerate) {
		this.autoGenerate = autoGenerate;
	}

	public RequireAutoAward getAutoAward() {
		return autoAward;
	}

	public void setAutoAward(RequireAutoAward autoAward) {
		this.autoAward = autoAward;
	}

	public List<String> getJudgeIds() {
		return judgeIds;
	}

	public void setJudgeIds(List<String> judgeIds) {
		this.judgeIds = judgeIds;
	}

	public RewardsFrequency getFrequency() {
		return frequency;
	}

	public void setFrequency(RewardsFrequency frequency) {
		this.frequency = frequency;
	}

	public int getNominateAheadDays() {
		return nominateAheadDays;
	}

	public void setNominateAheadDays(int nominateAheadDays) {
		this.nominateAheadDays = nominateAheadDays;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public List<String> getCandidateList() {
		return candidateList;
	}

	public void setCandidateList(List<String> candidateList) {
		this.candidateList = candidateList;
	}

	public boolean isDob() {
		return isDob;
	}

	public void setDob(boolean isDob) {
		this.isDob = isDob;
	}

	@Override
	public String toString() {
		return "RewardItemParam [id=" + id + ", name=" + name + ", typeId="
				+ typeId + ", definition=" + definition + ", standard="
				+ standard + ", headcountLimit=" + headcountLimit
				+ ", totalAmtLimit=" + totalAmtLimit + ", awardAmt=" + awardAmt
				+ ", awardUnit=" + awardUnit + ", builderDeptId="
				+ builderDeptId + ", accountDeptId=" + accountDeptId
				+ ", expectAwardDate=" + expectAwardDate + ", autoGenerate="
				+ autoGenerate + ", autoAward=" + autoAward + ", judgeIds="
				+ judgeIds + ", nominateAheadDays=" + nominateAheadDays
				+ ", candidateList=" + candidateList + ", isDob=" + isDob
				+ ", publishDate=" + publishDate + ", frequency=" + frequency
				+ "]";
	}
}
