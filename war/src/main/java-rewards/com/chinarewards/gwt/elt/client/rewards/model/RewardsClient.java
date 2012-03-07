package com.chinarewards.gwt.elt.client.rewards.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.chinarewards.gwt.elt.client.rewards.model.RewardsCriteria.RewardsStatus;

public class RewardsClient implements Serializable, Comparable<RewardsClient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4934837755724342679L;

	/** 基本信息 **/
	private RewardsBaseInfo baseInfo = new RewardsBaseInfo();

	// 状态
	private RewardsStatus status;

	// 获奖日期
	private Date rewardsDate;

	// 奖项
	private String rewardsItemId;

	private String rewardsItemName;

	/** 奖项积分 **/
	private double totalAmtLimit;
	/** 预计提名时间 **/
	private Date expectNominateDate;
	/**
	 * Headcount limit
	 */
	private int headcountLimit;

	public int getHeadcountLimit() {
		return headcountLimit;
	}

	public void setHeadcountLimit(int headcountLimit) {
		this.headcountLimit = headcountLimit;
	}

	/** 已经被提名的人信息(当前用户) **/
	private List<NomineeModelClient> rewardList;
	private Map<String,String> nomineeLot;
	
	/** 提名人信息 **/
	private List<JudgeModelClient> judgeList;

	
	
	private List<WinnerModelClient> winnerList;
	
	
	public String getWinnersText(){
		String text="";
		if (winnerList!=null) {
			for (int i = 0; i < winnerList.size(); i++) {
				WinnerModelClient param=winnerList.get(i);
				if(param!=null){
					text+=param.getName()+",";
				}				
			}
			int subIndex=text.lastIndexOf(",");
			if(subIndex>-1){
				text=text.substring(0, subIndex);
			}
		}
		return text;
	}
	
	
	public List<WinnerModelClient> getWinnerList() {
		return winnerList;
	}

	public void setWinnerList(List<WinnerModelClient> winnerList) {
		this.winnerList = winnerList;
	}

	public List<JudgeModelClient> getJudgeList() {
		return judgeList;
	}

	public void setJudgeList(List<JudgeModelClient> judgeList) {
		this.judgeList = judgeList;
	}

	public Map<String, String> getNomineeLot() {
		return nomineeLot;
	}

	public void setNomineeLot(Map<String, String> nomineeLot) {
		this.nomineeLot = nomineeLot;
	}

	/**
	 * Corporation.id
	 */
	private String corporationId;

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

	public RewardsClient() {

	}

	public RewardsClient(String name, Date rewardsDate) {
		this.setName(name);
		this.rewardsDate = rewardsDate;
	}

	public RewardsBaseInfo getBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(RewardsBaseInfo baseInfo) {
		this.baseInfo = baseInfo;
	}

	public Date getRewardsDate() {
		return rewardsDate;
	}

	public void setRewardsDate(Date rewardsDate) {
		this.rewardsDate = rewardsDate;
	}

	public String getRewardsItemId() {
		return rewardsItemId;
	}

	public void setRewardsItemId(String rewardsItemId) {
		this.rewardsItemId = rewardsItemId;
	}

	public List<NomineeModelClient> getRewardList() {
		return rewardList;
	}

	public void setRewardList(List<NomineeModelClient> rewardList) {
		this.rewardList = rewardList;
	}

	public String getId() {
		return baseInfo.getId();
	}

	public void setId(String id) {
		baseInfo.setId(id);
	}

	public String getDefinition() {
		return baseInfo.getDefinition();
	}

	public void setDefinition(String definition) {
		baseInfo.setDefinition(definition);
	}

	public String getStandard() {
		return baseInfo.getStandard();
	}

	public void setStandard(String standard) {
		baseInfo.setStandard(standard);
	}

	public RewardsAmountRuleClient getAmountRule() {
		return baseInfo.getAmountRule();
	}

	public void setAmountRule(RewardsAmountRuleClient amountRule) {
		baseInfo.setAmountRule(amountRule);
	}

	public String getName() {
		return baseInfo.getName();
	}

	public void setName(String name) {
		baseInfo.setName(name);
	}

	public RewardsTypeClient getType() {
		return baseInfo.getType();
	}

	public void setType(RewardsTypeClient type) {
		baseInfo.setType(type);
	}

	public int getSizeLimit() {
		return baseInfo.getSizeLimit();
	}

	public void setSizeLimit(int sizeLimit) {
		baseInfo.setSizeLimit(sizeLimit);
	}

	public StaffClient getStaff() {
		return baseInfo.getStaff();
	}

	public void setStaff(StaffClient staff) {
		baseInfo.setStaff(staff);
	}

	public String getBuilderDept() {
		return baseInfo.getBuilderDept();
	}

	public void setBuilderDept(String builderDept) {
		baseInfo.setBuilderDept(builderDept);
	}

	public String getAccountDept() {
		return baseInfo.getAccountDept();
	}

	public void setAccountDept(String accountDept) {
		baseInfo.setAccountDept(accountDept);
	}

	public Date getCreateAt() {
		return baseInfo.getCreateAt();
	}

	public void setCreateAt(Date createAt) {
		baseInfo.setCreateAt(createAt);
	}

	public String getCreatedBy() {
		return baseInfo.getCreatedBy();
	}

	public void setCreatedBy(String createdBy) {
		baseInfo.setCreatedBy(createdBy);
	}

	public String getRewardsUnit() {
		return baseInfo.getRewardsUnit();
	}

	public void setRewardsUnit(String rewardsUnit) {
		baseInfo.setRewardsUnit(rewardsUnit);
	}

	

	public ParticipateInfoClient getParticipateInfo() {
		return baseInfo.getParticipateInfo();
	}

	public void setParticipateInfo(ParticipateInfoClient participateInfo) {
		baseInfo.setParticipateInfo(participateInfo);
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

	@Override
	public String toString() {
		return "RewardsClient [baseInfo=" + baseInfo + ", status=" + status
				+ ", rewardsDate=" + rewardsDate + ", rewardsItemId="
				+ rewardsItemId + ", rewardsItemName=" + rewardsItemName
				+ ", rewardList=" + rewardList + ", corporationId="
				+ corporationId + "]";
	}

	@Override
	public int compareTo(RewardsClient o) {
		return o == null ? -1 : o.getId().compareTo(this.getId());
	}

	public double getTotalAmtLimit() {
		return totalAmtLimit;
	}

	public void setTotalAmtLimit(double totalAmtLimit) {
		this.totalAmtLimit = totalAmtLimit;
	}

	public Date getExpectNominateDate() {
		return expectNominateDate;
	}

	public void setExpectNominateDate(Date expectNominateDate) {
		this.expectNominateDate = expectNominateDate;
	}



}
