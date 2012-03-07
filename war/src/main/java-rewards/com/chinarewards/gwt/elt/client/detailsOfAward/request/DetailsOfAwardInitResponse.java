package com.chinarewards.gwt.elt.client.detailsOfAward.request;

import java.util.Date;
import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.model.awardReward.WinnerParamVo;
import com.chinarewards.gwt.elt.model.nominate.CandidateParamVo;
import com.chinarewards.gwt.elt.model.nominate.JudgeParamVo;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2011年12月22日 10:15:02
 */
public class DetailsOfAwardInitResponse implements Result {

	private String rewardId;
	private String rewardName;
	private String RewardItemName;
	
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
	private Date createdAt;
	/**
	 * Expect award date. Some notice will send to the Reward owner if overtime
	 * according to it.
	 */
	private Date expectAwardDate;
	/**
	 * The date to expect nominate, usually it is ahead of award date.
	 */
	private Date expectNominateDate;
	
	/**
	 * 创建人
	 */
	private String createdStaffName;
	
	/**
	 * 奖项模式
	 */
	private String awardMode;
	/**
	 * 颁奖人
	 */
	private String awardingStaffName;
	
	/**
	 * 获取被提名人List 
	 * 
	 */
	List<CandidateParamVo> candidateList;
	
	/**
	 * 获取提名人List
	 */
	List<JudgeParamVo> judgeList;

	
/**
 * 获奖人List
 * @return
 */
	List<WinnerParamVo> WinnerList;
	public List<WinnerParamVo> getWinnerList() {
	return WinnerList;
}



public void setWinnerList(List<WinnerParamVo> winnerList) {
	WinnerList = winnerList;
}



	public String getRewardId() {
		return rewardId;
	}



	public void setRewardId(String rewardId) {
		this.rewardId = rewardId;
	}



	public String getRewardName() {
		return rewardName;
	}



	public void setRewardName(String rewardName) {
		this.rewardName = rewardName;
	}



	public String getRewardItemName() {
		return RewardItemName;
	}



	public void setRewardItemName(String rewardItemName) {
		RewardItemName = rewardItemName;
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



	public Date getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
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



	public String getCreatedStaffName() {
		return createdStaffName;
	}



	public void setCreatedStaffName(String createdStaffName) {
		this.createdStaffName = createdStaffName;
	}



	public String getAwardMode() {
		return awardMode;
	}



	public void setAwardMode(String awardMode) {
		this.awardMode = awardMode;
	}



	public String getAwardingStaffName() {
		return awardingStaffName;
	}



	public void setAwardingStaffName(String awardingStaffName) {
		this.awardingStaffName = awardingStaffName;
	}



	public List<CandidateParamVo> getCandidateList() {
		return candidateList;
	}



	public void setCandidateList(List<CandidateParamVo> candidateList) {
		this.candidateList = candidateList;
	}



	public List<JudgeParamVo> getJudgeList() {
		return judgeList;
	}



	public void setJudgeList(List<JudgeParamVo> judgeList) {
		this.judgeList = judgeList;
	}



	public DetailsOfAwardInitResponse() {

	}




}
