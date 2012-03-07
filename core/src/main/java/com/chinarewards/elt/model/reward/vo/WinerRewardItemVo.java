package com.chinarewards.elt.model.reward.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.domain.org.Department;
import com.chinarewards.elt.domain.reward.base.Reward;
import com.chinarewards.elt.domain.reward.base.RewardItem;
import com.chinarewards.elt.domain.reward.base.RewardItemType;
import com.chinarewards.elt.domain.reward.frequency.Frequency;
import com.chinarewards.elt.domain.reward.person.Judge;
import com.chinarewards.elt.domain.reward.rule.CandidateRule;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.reward.base.RequireAutoAward;
import com.chinarewards.elt.model.reward.base.RequireAutoGenerate;
import com.chinarewards.elt.model.transaction.TransactionUnit;

/**
 * The data container about RewardItem
 * 
 * @author yanrui
 * 
 * 员工 我参与的奖项对象
 */
public class WinerRewardItemVo {

	private RewardItem item = new RewardItem();
	private Reward reward=new Reward();
	

	private Frequency frequency;

	private CandidateRule candidateRule;

	private List<Judge> judgeList = new ArrayList<Judge>();
	
	private String nominateCount="";	
	
	
	

	public Reward getReward() {
		return reward;
	}

	public void setReward(Reward reward) {
		this.reward = reward;
	}

	public String getNominateCount() {
		return nominateCount;
	}

	public void setNominateCount(String nominateCount) {
		this.nominateCount = nominateCount;
	}

	public RewardItem getItem() {
		return item;
	}

	public void setItem(RewardItem item) {
		this.item = item;
	}

	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	public CandidateRule getCandidateRule() {
		return candidateRule;
	}

	public void setCandidateRule(CandidateRule candidateRule) {
		this.candidateRule = candidateRule;
	}

	public List<Judge> getJudgeList() {
		return judgeList;
	}

	public void setJudgeList(List<Judge> judgeList) {
		this.judgeList = judgeList;
	}

	public String getId() {
		return item.getId();
	}

	public void setId(String id) {
		item.setId(id);
	}

	public String getName() {
		return item.getName();
	}

	public void setName(String name) {
		item.setName(name);
	}

	public RewardItemType getType() {
		return item.getType();
	}

	public void setType(RewardItemType type) {
		item.setType(type);
	}

	public String getDefinition() {
		return item.getDefinition();
	}

	public void setDefinition(String definition) {
		item.setDefinition(definition);
	}

	public String getStandard() {
		return item.getStandard();
	}

	public void setStandard(String standard) {
		item.setStandard(standard);
	}

	public int getHeadcountLimit() {
		return item.getHeadcountLimit();
	}

	public void setHeadcountLimit(int headcountLimit) {
		item.setHeadcountLimit(headcountLimit);
	}

	public double getTotalAmtLimit() {
		return item.getTotalAmtLimit();
	}

	public void setTotalAmtLimit(double totalAmtLimit) {
		item.setTotalAmtLimit(totalAmtLimit);
	}

	public double getAwardAmt() {
		return item.getAwardAmt();
	}

	public void setAwardAmt(double awardAmt) {
		item.setAwardAmt(awardAmt);
	}

	public TransactionUnit getAwardUnit() {
		return item.getAwardUnit();
	}

	public void setAwardUnit(TransactionUnit awardUnit) {
		item.setAwardUnit(awardUnit);
	}

	public Department getBuilderDept() {
		return item.getBuilderDept();
	}

	public void setBuilderDept(Department builderDept) {
		item.setBuilderDept(builderDept);
	}

	public Department getAccountDept() {
		return item.getAccountDept();
	}

	public void setAccountDept(Department accountDept) {
		item.setAccountDept(accountDept);
	}

	public int getDegree() {
		return item.getDegree();
	}

	public void setDegree(int degree) {
		item.setDegree(degree);
	}

	public Date getLastAwardDate() {
		return item.getLastAwardDate();
	}

	public void setLastAwardDate(Date lastAwardDate) {
		item.setLastAwardDate(lastAwardDate);
	}

	public boolean isDeleted() {
		return item.isDeleted();
	}

	public void setDeleted(boolean deleted) {
		item.setDeleted(deleted);
	}

	public Date getExpectAwardDate() {
		return item.getExpectAwardDate();
	}

	public void setExpectAwardDate(Date expectAwardDate) {
		item.setExpectAwardDate(expectAwardDate);
	}

	public RequireAutoGenerate getAutoGenerate() {
		return item.getAutoGenerate();
	}

	public void setAutoGenerate(RequireAutoGenerate autoGenerate) {
		item.setAutoGenerate(autoGenerate);
	}

	public RequireAutoAward getAutoAward() {
		return item.getAutoAward();
	}

	public void setAutoAward(RequireAutoAward autoAward) {
		item.setAutoAward(autoAward);
	}

	public boolean isEnabled() {
		return item.isEnabled();
	}

	public void setEnabled(boolean enabled) {
		item.setEnabled(enabled);
	}

	public Date getPublishDate() {
		return item.getPublishDate();
	}

	public void setPublishDate(Date publishDate) {
		item.setPublishDate(publishDate);
	}

	public Date getNexRunBatchTime() {
		return item.getNexRunBatchTime();
	}

	public void setNexRunBatchTime(Date nexRunBatchTime) {
		item.setNexRunBatchTime(nexRunBatchTime);
	}

	public Corporation getCorporation() {
		return item.getCorporation();
	}

	public void setCorporation(Corporation corporation) {
		item.setCorporation(corporation);
	}

	public int getNominateAheadDays() {
		return item.getNominateAheadDays();
	}

	public void setNominateAheadDays(int nominateAheadDays) {
		item.setNominateAheadDays(nominateAheadDays);
	}

	public Date getNominateDate() {
		return item.getNominateDate();
	}

	public void setNominateDate(Date nominateDate) {
		item.setNominateDate(nominateDate);
	}

	public Date getCreatedAt() {
		return item.getCreatedAt();
	}

	public void setCreatedAt(Date createdAt) {
		item.setCreatedAt(createdAt);
	}

	public Date getLastModifiedAt() {
		return item.getLastModifiedAt();
	}

	public void setLastModifiedAt(Date lastModifiedAt) {
		item.setLastModifiedAt(lastModifiedAt);
	}

	public SysUser getCreatedBy() {
		return item.getCreatedBy();
	}

	public void setCreatedBy(SysUser createdBy) {
		item.setCreatedBy(createdBy);
	}

	public SysUser getLastModifiedBy() {
		return item.getLastModifiedBy();
	}

	public void setLastModifiedBy(SysUser lastModifiedBy) {
		item.setLastModifiedBy(lastModifiedBy);
	}

}
