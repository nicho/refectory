package com.chinarewards.elt.model.reward.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.domain.org.Department;

import com.chinarewards.elt.domain.reward.base.RewardItemStore;
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
 * @author yanxin
 * @since 0.2.0 2011-01-14
 */
public class RewardItemStoreVo {

	private RewardItemStore itemStore = new RewardItemStore();

	private Frequency frequency;

	private CandidateRule candidateRule;

	private List<Judge> judgeList = new ArrayList<Judge>();

	

	public RewardItemStore getItemStore() {
		return itemStore;
	}

	public void setItemStore(RewardItemStore itemStore) {
		this.itemStore = itemStore;
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
		return itemStore.getId();
	}

	public void setId(String id) {
		itemStore.setId(id);
	}

	public String getName() {
		return itemStore.getName();
	}

	public void setName(String name) {
		itemStore.setName(name);
	}

	public RewardItemType getType() {
		return itemStore.getType();
	}

	public void setType(RewardItemType type) {
		itemStore.setType(type);
	}

	public String getDefinition() {
		return itemStore.getDefinition();
	}

	public void setDefinition(String definition) {
		itemStore.setDefinition(definition);
	}

	public String getStandard() {
		return itemStore.getStandard();
	}

	public void setStandard(String standard) {
		itemStore.setStandard(standard);
	}

	public int getHeadcountLimit() {
		return itemStore.getHeadcountLimit();
	}

	public void setHeadcountLimit(int headcountLimit) {
		itemStore.setHeadcountLimit(headcountLimit);
	}

	public double getTotalAmtLimit() {
		return itemStore.getTotalAmtLimit();
	}

	public void setTotalAmtLimit(double totalAmtLimit) {
		itemStore.setTotalAmtLimit(totalAmtLimit);
	}

	public double getAwardAmt() {
		return itemStore.getAwardAmt();
	}

	public void setAwardAmt(double awardAmt) {
		itemStore.setAwardAmt(awardAmt);
	}

	public TransactionUnit getAwardUnit() {
		return itemStore.getAwardUnit();
	}

	public void setAwardUnit(TransactionUnit awardUnit) {
		itemStore.setAwardUnit(awardUnit);
	}

	public Department getBuilderDept() {
		return itemStore.getBuilderDept();
	}

	public void setBuilderDept(Department builderDept) {
		itemStore.setBuilderDept(builderDept);
	}

	public Department getAccountDept() {
		return itemStore.getAccountDept();
	}

	public void setAccountDept(Department accountDept) {
		itemStore.setAccountDept(accountDept);
	}

	public int getDegree() {
		return itemStore.getDegree();
	}

	public void setDegree(int degree) {
		itemStore.setDegree(degree);
	}

	public Date getLastAwardDate() {
		return itemStore.getLastAwardDate();
	}

	public void setLastAwardDate(Date lastAwardDate) {
		itemStore.setLastAwardDate(lastAwardDate);
	}

	public boolean isDeleted() {
		return itemStore.isDeleted();
	}

	public void setDeleted(boolean deleted) {
		itemStore.setDeleted(deleted);
	}

	public Date getExpectAwardDate() {
		return itemStore.getExpectAwardDate();
	}

	public void setExpectAwardDate(Date expectAwardDate) {
		itemStore.setExpectAwardDate(expectAwardDate);
	}

	public RequireAutoGenerate getAutoGenerate() {
		return itemStore.getAutoGenerate();
	}

	public void setAutoGenerate(RequireAutoGenerate autoGenerate) {
		itemStore.setAutoGenerate(autoGenerate);
	}

	public RequireAutoAward getAutoAward() {
		return itemStore.getAutoAward();
	}

	public void setAutoAward(RequireAutoAward autoAward) {
		itemStore.setAutoAward(autoAward);
	}

	public boolean isEnabled() {
		return itemStore.isEnabled();
	}

	public void setEnabled(boolean enabled) {
		itemStore.setEnabled(enabled);
	}

	public Date getPublishDate() {
		return itemStore.getPublishDate();
	}

	public void setPublishDate(Date publishDate) {
		itemStore.setPublishDate(publishDate);
	}

	public Date getNexRunBatchTime() {
		return itemStore.getNexRunBatchTime();
	}

	public void setNexRunBatchTime(Date nexRunBatchTime) {
		itemStore.setNexRunBatchTime(nexRunBatchTime);
	}

	public Corporation getCorporation() {
		return itemStore.getCorporation();
	}

	public void setCorporation(Corporation corporation) {
		itemStore.setCorporation(corporation);
	}

	public int getNominateAheadDays() {
		return itemStore.getNominateAheadDays();
	}

	public void setNominateAheadDays(int nominateAheadDays) {
		itemStore.setNominateAheadDays(nominateAheadDays);
	}

	public Date getNominateDate() {
		return itemStore.getNominateDate();
	}

	public void setNominateDate(Date nominateDate) {
		itemStore.setNominateDate(nominateDate);
	}

	public Date getCreatedAt() {
		return itemStore.getCreatedAt();
	}

	public void setCreatedAt(Date createdAt) {
		itemStore.setCreatedAt(createdAt);
	}

	public Date getLastModifiedAt() {
		return itemStore.getLastModifiedAt();
	}

	public void setLastModifiedAt(Date lastModifiedAt) {
		itemStore.setLastModifiedAt(lastModifiedAt);
	}

	public SysUser getCreatedBy() {
		return itemStore.getCreatedBy();
	}

	public void setCreatedBy(SysUser createdBy) {
		itemStore.setCreatedBy(createdBy);
	}

	public SysUser getLastModifiedBy() {
		return itemStore.getLastModifiedBy();
	}

	public void setLastModifiedBy(SysUser lastModifiedBy) {
		itemStore.setLastModifiedBy(lastModifiedBy);
	}

}
