package com.chinarewards.elt.domain.reward.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.domain.org.Department;
import com.chinarewards.elt.domain.reward.rule.CandidateRule;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.reward.base.RewardStatus;
import com.chinarewards.elt.model.transaction.TransactionUnit;

/**
 * Reward is an instance of {@link RewardItem}. It is the actual award
 * activity.A Reward may have ShortListing, Nominee, Pre-Winner, Winner.
 * 
 * @author yanxin
 * @since 1.0
 */
@Entity
public class Reward implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7780067961015636155L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	/**
	 * reward name
	 */
	private String name;

	/**
	 * Come from which RewardItem
	 */
	@ManyToOne
	private RewardItem rewardItem;

	/**
	 * Flag this Reward was deleted
	 */
	private boolean deleted;

	/**
	 * Redundancy field to flag which Corporation it owns.
	 */
	@ManyToOne
	private Corporation corporation;

	/**
	 * actually award date, the budget calculate according to it.
	 */
	private Date awardDate;

	@Enumerated(EnumType.STRING)
	private RewardStatus status;

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
	@ManyToOne
	private Department builderDept;

	/**
	 * which department pay it
	 */
	@ManyToOne
	private Department accountDept;

	/**
	 * currency unit
	 */
	@Enumerated(EnumType.STRING)
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

	@OneToOne
	private CandidateRule candidateRule;

	private Date createdAt;

	private Date lastModifiedAt;

	@ManyToOne
	private SysUser createdBy;

	@ManyToOne
	private SysUser lastModifiedBy;

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

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public RewardItem getRewardItem() {
		return rewardItem;
	}

	public void setRewardItem(RewardItem rewardItem) {
		this.rewardItem = rewardItem;
	}

	public Corporation getCorporation() {
		return corporation;
	}

	public void setCorporation(Corporation corporation) {
		this.corporation = corporation;
	}

	public Date getAwardDate() {
		return awardDate;
	}

	public void setAwardDate(Date awardDate) {
		this.awardDate = awardDate;
	}

	public RewardStatus getStatus() {
		return status;
	}

	public void setStatus(RewardStatus status) {
		this.status = status;
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

	public Department getBuilderDept() {
		return builderDept;
	}

	public void setBuilderDept(Department builderDept) {
		this.builderDept = builderDept;
	}

	public Department getAccountDept() {
		return accountDept;
	}

	public void setAccountDept(Department accountDept) {
		this.accountDept = accountDept;
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

	public CandidateRule getCandidateRule() {
		return candidateRule;
	}

	public void setCandidateRule(CandidateRule candidateRule) {
		this.candidateRule = candidateRule;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getLastModifiedAt() {
		return lastModifiedAt;
	}

	public void setLastModifiedAt(Date lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}

	public SysUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(SysUser createdBy) {
		this.createdBy = createdBy;
	}

	public SysUser getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(SysUser lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
}
