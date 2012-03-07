package com.chinarewards.elt.domain.reward.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.domain.org.Department;
import com.chinarewards.elt.domain.reward.frequency.Frequency;
import com.chinarewards.elt.domain.reward.rule.CandidateRule;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.reward.base.RequireAutoAward;
import com.chinarewards.elt.model.reward.base.RequireAutoGenerate;
import com.chinarewards.elt.model.transaction.TransactionUnit;

/**
 * It defines a kind of reward. You can award many times according to it. For
 * example, you can define a RewardItem whose name is MonthStar because maybe
 * every month you should award once. January star reward and February star
 * reward are not the same reward, but they are both from MonthStar RewardItem.
 * It is like that:
 * <ul>
 * RewardItem - "MonthStar"
 * <li>Reward - JanuaryStar</li>
 * <li>Reward - FebruaryStar</li>
 * <li>...</li>
 * </ul>
 * 
 * So we say, RewardItem defines a series of rules and definitions to describe a
 * kind of Reward.
 * 
 * More than this, you can find that a RewardItem was configured by
 * {@link RequireAutoGenerate} and {@link RequireAutoAward}
 * 
 * @author yanxin
 * @since 1.0
 */
@Entity
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "RewardItemType")
public class RewardItemStore implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 280986070434404834L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	/**
	 * the full name
	 */
	private String name;

	/**
	 * big type
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private RewardItemType type;

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
	 * Total amount limit（总积分）
	 */
	private double totalAmtLimit;

	/**
	 * award amount(每人积分)
	 */
	private double awardAmt;

	/**
	 * transaction unit
	 */
	@Enumerated(EnumType.STRING)
	private TransactionUnit awardUnit;

	/**
	 * Which department build this RewardItem, it also means this RewardItem
	 * owns which department.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private Department builderDept;

	/**
	 * Which department pay this RewardItem.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private Department accountDept;

	/**
	 * Flag this RewardItem was deleted
	 */
	private boolean deleted;

	/**
	 * The times of generate Reward.
	 */
	private int degree;

	/**
	 * The date of last award
	 */
	private Date lastAwardDate;

	/**
	 * The date expected to award. This field should copy to Reward when run a
	 * Reward out.
	 */
	private Date expectAwardDate;

	/**
	 * Redundancy field to flag which Corporation it owns.
	 */
	@ManyToOne
	private Corporation corporation;

	/**
	 * It defines whether require running Reward out by "batch system" at least
	 * one time.
	 */
	@Enumerated(EnumType.STRING)
	private RequireAutoGenerate autoGenerate;

	/**
	 * The candidate rule to get qualified staffs.
	 */
	@OneToOne
	private CandidateRule candidateRule;

	/**
	 * The frequency to trigger cyclic generating Reward.
	 */
	@OneToOne
	private Frequency frequency;

	private Date createdAt;
   
	private Date startTime;
	
	
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	

	private Date lastModifiedAt;

	@ManyToOne
	private SysUser createdBy;

	@ManyToOne
	private SysUser lastModifiedBy;

	/*********************************************************************
	 * Notice:
	 * 
	 * Fields below only make sense when this is a RewardItem who want to
	 * generate Reward automatic
	 * 
	 *********************************************************************/

	/**
	 * It defines whether the reward generated from RewardItem would award
	 * directly or others.
	 */
	@Enumerated(EnumType.STRING)
	private RequireAutoAward autoAward;

	/**
	 * How many days about the nominate date ahead of award date.
	 */
	private int nominateAheadDays;

	/**
	 * The date to nominate, usually it is ahead of award date.
	 */
	private Date nominateDate;

	/**
	 * Whether enabled, if true means the RewardItem may have the ability
	 * generate Reward automatic.
	 */
	private boolean enabled;

	/**
	 * The date to publish this Reward. In other words, it is the date to run a
	 * reward out according to RewardItem.
	 */
	private Date publishDate;

	/**
	 * How many days about the publish date ahead of award date.
	 */
	private int publishAheadDays;

	/**
	 * The date to run batch, usually calculate from awardDate or publishDate.
	 * In actually, it is awardDate when the RewardItem is full- automatic.
	 * Otherwise, it is publishDate.
	 */
	private Date nexRunBatchTime;

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

	public RewardItemType getType() {
		return type;
	}

	public void setType(RewardItemType type) {
		this.type = type;
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

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public Date getLastAwardDate() {
		return lastAwardDate;
	}

	public void setLastAwardDate(Date lastAwardDate) {
		this.lastAwardDate = lastAwardDate;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public int getPublishAheadDays() {
		return publishAheadDays;
	}

	public void setPublishAheadDays(int publishAheadDays) {
		this.publishAheadDays = publishAheadDays;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Date getNexRunBatchTime() {
		return nexRunBatchTime;
	}

	public void setNexRunBatchTime(Date nexRunBatchTime) {
		this.nexRunBatchTime = nexRunBatchTime;
	}

	public int getNominateAheadDays() {
		return nominateAheadDays;
	}

	public void setNominateAheadDays(int nominateAheadDays) {
		this.nominateAheadDays = nominateAheadDays;
	}

	public Date getNominateDate() {
		return nominateDate;
	}

	public void setNominateDate(Date nominateDate) {
		this.nominateDate = nominateDate;
	}

	public Corporation getCorporation() {
		return corporation;
	}

	public void setCorporation(Corporation corporation) {
		this.corporation = corporation;
	}

	public CandidateRule getCandidateRule() {
		return candidateRule;
	}

	public void setCandidateRule(CandidateRule candidateRule) {
		this.candidateRule = candidateRule;
	}

	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
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

	@Override
	public String toString() {
		return "RewardItem [id=" + id + ", name=" + name + ", type=" + type
				+ ", definition=" + definition + ", standard=" + standard
				+ ", headcountLimit=" + headcountLimit + ", totalAmtLimit="
				+ totalAmtLimit + ", awardAmt=" + awardAmt + ", awardUnit="
				+ awardUnit + ", builderDept=" + builderDept + ", accountDept="
				+ accountDept + ", deleted=" + deleted + ", degree=" + degree
				+ ", lastAwardDate=" + lastAwardDate + ", expectAwardDate="
				+ expectAwardDate + ", corporation=" + corporation
				+ ", autoGenerate=" + autoGenerate + ", candidateRule="
				+ candidateRule + ", frequency=" + frequency + ", createdAt="
				+ createdAt + ", lastModifiedAt=" + lastModifiedAt
				+ ", createdBy=" + createdBy + ", lastModifiedBy="
				+ lastModifiedBy + ", autoAward=" + autoAward
				+ ", nominateAheadDays=" + nominateAheadDays
				+ ", nominateDate=" + nominateDate + ", enabled=" + enabled
				+ ", publishDate=" + publishDate + ", nexRunBatchTime="
				+ nexRunBatchTime + "]";
	}
}
