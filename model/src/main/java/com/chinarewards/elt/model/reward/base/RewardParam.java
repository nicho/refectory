package com.chinarewards.elt.model.reward.base;

import java.util.Date;

import com.chinarewards.elt.model.transaction.TransactionUnit;

/**
 * Create or edit a Reward model.
 * 
 * @author yanxin
 * @since 1.0
 */
public class RewardParam {

	/**
	 * id
	 */
	private String id;

	/**
	 * reward name
	 */
	private String name;

	/**
	 * Come from which RewardItem
	 */
	private String rewardItemId;

	// ------copy from RewardItem
	/**
	 * definition
	 */
	private String definition;

	/**
	 * standard
	 */
	private String standard;

	/**
	 * Headcount limit
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

	/**
	 * which department build it.
	 */
	private String builderDeptId;

	/**
	 * which department pay it
	 */
	private String accountDeptId;

	/**
	 * currency unit
	 */
	private TransactionUnit rewardUnit;

	/**
	 * Expect award date. Some notice will send to the Reward owner if overtime
	 * according to it.
	 */
	private Date expectAwardDate;

	/**
	 * The date to expect nominate, usually it is ahead of award date.
	 */
	private Date expectNominateDate;

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

	public String getRewardItemId() {
		return rewardItemId;
	}

	public void setRewardItemId(String rewardItemId) {
		this.rewardItemId = rewardItemId;
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

	public TransactionUnit getRewardUnit() {
		return rewardUnit;
	}

	public void setRewardUnit(TransactionUnit rewardUnit) {
		this.rewardUnit = rewardUnit;
	}

	public Date getExpectAwardDate() {
		return expectAwardDate;
	}

	public void setExpectAwardDate(Date expectAwardDate) {
		this.expectAwardDate = expectAwardDate;
	}

	public Date getExpectNominateDate() {
		return expectNominateDate;
	}

	public void setExpectNominateDate(Date expectNominateDate) {
		this.expectNominateDate = expectNominateDate;
	}
}
