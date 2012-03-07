package com.chinarewards.gwt.elt.client.rewards.model;

import java.io.Serializable;
import java.util.Date;

public class RewardsItemClient implements Serializable,
		Comparable<RewardsItemClient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7771222164734049059L;

	/** 基本信息 **/
	private RewardsBaseInfo baseInfo = new RewardsBaseInfo();

	public boolean isPeriodEnable() {
		return periodEnable;
	}

	public void setPeriodEnable(boolean periodEnable) {
		this.periodEnable = periodEnable;
	}

	public int getTotalJF() {
		return totalJF;
	}

	public void setTotalJF(int totalJF) {
		this.totalJF = totalJF;
	}

	public int getRewardsFrom() {
		return rewardsFrom;
	}

	public void setRewardsFrom(int rewardsFrom) {
		this.rewardsFrom = rewardsFrom;
	}

	/** 频率规则 **/
	// 频率是否有效(周期性)
	private boolean periodEnable;
	// 频率
	private FrequencyClient frequency;

	// 开始时间
	private Date startTime;

	// 下次奖励时间
	private Date nextTime;

	// 下次奖励公布时间
	private Date nextPublishTime;

	// 上一次颁奖时间--没有为Null
	private Date lastRewardedDate;

	// 结束时间
	private Date endTime;
	
	// 预期时间
	private Date expectAwardDate;


	public Date getExpectAwardDate() {
		return expectAwardDate;
	}

	public void setExpectAwardDate(Date expectAwardDate) {
		this.expectAwardDate = expectAwardDate;
	}

	// 是否自动奖项
	private boolean isAuto = false;

	// 是否有特殊条件(关于自动奖项。如生日)
	private boolean hasSpecialCondition;

	// 特殊条件
	private SpecialCondition condition;

	// 是否已经生成奖励
	private boolean isGeneratedRewards;
	// 生成奖励的次数
	private int degree;
	private boolean enabled;//是否激活
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	//总积分
	private int totalJF;
	
	//每人得的积分
	private int rewardsFrom;
	
	//提前的天数
	private Integer tmdays;
   
	/** 提名人人信息 **/
	private ParticipateInfoClient tmInfo;
	public ParticipateInfoClient getTmInfo() {
		return tmInfo;
	}

	public void setTmInfo(ParticipateInfoClient tmInfo) {
		this.tmInfo = tmInfo;
	}

	public Integer getTmdays() {
		return tmdays;
	}

	public void setTmdays(Integer tmdays) {
		this.tmdays = tmdays;
	}
	
	@Override
	public int compareTo(RewardsItemClient o) {
		return o == null ? -1 : o.getId().compareTo(this.getId());
	}

	@Override
	public String toString() {
		return "RewardsItemClient [baseInfo=" + baseInfo + ", enable=" + periodEnable
				+ ", frequency=" + frequency + ", startTime=" + startTime
				+ ", nextTime=" + nextTime + ", nextPublishTime="
				+ nextPublishTime + ", lastRewardedDate=" + lastRewardedDate
				+ ", endTime=" + endTime + ", isAuto=" + isAuto
				+ ", hasSpecialCondition=" + hasSpecialCondition
				+ ", condition=" + condition + ", isGeneratedRewards="
				+ isGeneratedRewards + "]";
	}

	public RewardsBaseInfo getBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(RewardsBaseInfo baseInfo) {
		this.baseInfo = baseInfo;
	}

	

	public FrequencyClient getFrequency() {
		return frequency;
	}

	public void setFrequency(FrequencyClient frequency) {
		this.frequency = frequency;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getNextTime() {
		return nextTime;
	}

	public void setNextTime(Date nextTime) {
		this.nextTime = nextTime;
	}

	public Date getNextPublishTime() {
		return nextPublishTime;
	}

	public void setNextPublishTime(Date nextPublishTime) {
		this.nextPublishTime = nextPublishTime;
	}

	public Date getLastRewardedDate() {
		return lastRewardedDate;
	}

	public void setLastRewardedDate(Date lastRewardedDate) {
		this.lastRewardedDate = lastRewardedDate;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public boolean isAuto() {
		return isAuto;
	}

	public void setAuto(boolean isAuto) {
		this.isAuto = isAuto;
	}

	public boolean isHasSpecialCondition() {
		return hasSpecialCondition;
	}

	public void setHasSpecialCondition(boolean hasSpecialCondition) {
		this.hasSpecialCondition = hasSpecialCondition;
	}

	public SpecialCondition getCondition() {
		return condition;
	}

	public void setCondition(SpecialCondition condition) {
		this.condition = condition;
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

	public String getBuilderDept() {
		return baseInfo.getBuilderDept();
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
   
	
	public boolean isGeneratedRewards() {
		return isGeneratedRewards;
	}

	public void setGeneratedRewards(boolean isGeneratedRewards) {
		this.isGeneratedRewards = isGeneratedRewards;
	}

}
