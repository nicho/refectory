package com.chinarewards.elt.model.reward.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.domain.org.Department;
import com.chinarewards.elt.domain.reward.base.Reward;
import com.chinarewards.elt.domain.reward.base.RewardItem;
import com.chinarewards.elt.domain.reward.person.Candidate;
import com.chinarewards.elt.domain.reward.person.Judge;
import com.chinarewards.elt.domain.reward.person.NomineeLot;
import com.chinarewards.elt.domain.reward.person.PreWinnerLot;
import com.chinarewards.elt.domain.reward.person.Winner;
import com.chinarewards.elt.domain.reward.rule.CandidateRule;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.reward.base.RewardStatus;
import com.chinarewards.elt.model.transaction.TransactionUnit;

/**
 * Date container of {@link Reward}
 * 
 * @author yanxin
 * @since 1.0
 */
public class RewardVo {

	private Reward reward = new Reward();

	private CandidateRule candidateRule;

	private List<Candidate> candidates = new ArrayList<Candidate>();

	private List<Judge> judges = new ArrayList<Judge>();

	private List<NomineeLot> nomineeLots = new ArrayList<NomineeLot>();

	private List<PreWinnerLot> preWinnerLots = new ArrayList<PreWinnerLot>();

	private List<Winner> winners = new ArrayList<Winner>();

	public Reward getReward() {
		return reward;
	}

	public void setReward(Reward reward) {
		this.reward = reward;
	}

	public CandidateRule getCandidateRule() {
		return candidateRule;
	}

	public void setCandidateRule(CandidateRule candidateRule) {
		this.candidateRule = candidateRule;
	}

	public List<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}

	public List<Judge> getJudges() {
		return judges;
	}

	public void setJudges(List<Judge> judges) {
		this.judges = judges;
	}

	public List<NomineeLot> getNomineeLots() {
		return nomineeLots;
	}

	public void setNomineeLots(List<NomineeLot> nomineeLots) {
		this.nomineeLots = nomineeLots;
	}

	public List<PreWinnerLot> getPreWinnerLots() {
		return preWinnerLots;
	}

	public void setPreWinnerLots(List<PreWinnerLot> preWinnerLots) {
		this.preWinnerLots = preWinnerLots;
	}

	public List<Winner> getWinners() {
		return winners;
	}

	public void setWinners(List<Winner> winners) {
		this.winners = winners;
	}

	public String getId() {
		return reward.getId();
	}

	public void setId(String id) {
		reward.setId(id);
	}

	public String getName() {
		return reward.getName();
	}

	public void setName(String name) {
		reward.setName(name);
	}

	public RewardItem getRewardItem() {
		return reward.getRewardItem();
	}

	public void setRewardItem(RewardItem rewardItem) {
		reward.setRewardItem(rewardItem);
	}

	public Corporation getCorporation() {
		return reward.getCorporation();
	}

	public void setCorporation(Corporation corporation) {
		reward.setCorporation(corporation);
	}

	public Date getAwardDate() {
		return reward.getAwardDate();
	}

	public void setAwardDate(Date awardDate) {
		reward.setAwardDate(awardDate);
	}

	public RewardStatus getStatus() {
		return reward.getStatus();
	}

	public void setStatus(RewardStatus status) {
		reward.setStatus(status);
	}

	public String getDefinition() {
		return reward.getDefinition();
	}

	public void setDefinition(String definition) {
		reward.setDefinition(definition);
	}

	public String getStandard() {
		return reward.getStandard();
	}

	public void setStandard(String standard) {
		reward.setStandard(standard);
	}

	public int getHeadcountLimit() {
		return reward.getHeadcountLimit();
	}

	public void setHeadcountLimit(int headcountLimit) {
		reward.setHeadcountLimit(headcountLimit);
	}

	public double getTotalAmtLimit() {
		return reward.getTotalAmtLimit();
	}

	public void setTotalAmtLimit(double totalAmtLimit) {
		reward.setTotalAmtLimit(totalAmtLimit);
	}

	public double getAwardAmt() {
		return reward.getAwardAmt();
	}

	public void setAwardAmt(double awardAmt) {
		reward.setAwardAmt(awardAmt);
	}

	public Department getBuilderDept() {
		return reward.getBuilderDept();
	}

	public void setBuilderDept(Department builderDept) {
		reward.setBuilderDept(builderDept);
	}

	public Department getAccountDept() {
		return reward.getAccountDept();
	}

	public void setAccountDept(Department accountDept) {
		reward.setAccountDept(accountDept);
	}

	public TransactionUnit getRewardUnit() {
		return reward.getRewardUnit();
	}

	public void setRewardUnit(TransactionUnit rewardUnit) {
		reward.setRewardUnit(rewardUnit);
	}

	public Date getExpectAwardDate() {
		return reward.getExpectAwardDate();
	}

	public void setExpectAwardDate(Date expectAwardDate) {
		reward.setExpectAwardDate(expectAwardDate);
	}

	public Date getExpectNominateDate() {
		return reward.getExpectNominateDate();
	}

	public void setExpectNominateDate(Date expectNominateDate) {
		reward.setExpectNominateDate(expectNominateDate);
	}

	public Date getCreatedAt() {
		return reward.getCreatedAt();
	}

	public void setCreatedAt(Date createdAt) {
		reward.setCreatedAt(createdAt);
	}

	public Date getLastModifiedAt() {
		return reward.getLastModifiedAt();
	}

	public void setLastModifiedAt(Date lastModifiedAt) {
		reward.setLastModifiedAt(lastModifiedAt);
	}

	public SysUser getCreatedBy() {
		return reward.getCreatedBy();
	}

	public void setCreatedBy(SysUser createdBy) {
		reward.setCreatedBy(createdBy);
	}

	public SysUser getLastModifiedBy() {
		return reward.getLastModifiedBy();
	}

	public void setLastModifiedBy(SysUser lastModifiedBy) {
		reward.setLastModifiedBy(lastModifiedBy);
	}
}
