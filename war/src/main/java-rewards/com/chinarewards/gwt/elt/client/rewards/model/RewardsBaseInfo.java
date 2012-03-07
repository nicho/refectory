package com.chinarewards.gwt.elt.client.rewards.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 奖项或奖励基本信息或可公用的信息
 * 
 * @author yanxin
 * @since 0.2.0 2010-01-22
 */
public class RewardsBaseInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5987139886258025342L;

	// id
	private String id;

	// 名称
	private String name;

	// 类别
	private RewardsTypeClient type;

	// 行为定义
	private String definition;

	// 评判标准
	private String standard;

	
	// 人数限制
	private int SizeLimit;

	// 设立部门
	private String builderDept;

	// 入账部门
	private String accountDept;

	// 设立日期
	private Date createAt;

	// 设立者的名字
	private String createdBy;

	// 奖励单位:BINFEN,RMB
	private String rewardsUnit;

	/** 奖金规则 **/
	private RewardsAmountRuleClient amountRule;

	/** 候选人信息 **/
	private ParticipateInfoClient participateInfo;
	
	

	// 从welcome页面传过来需要给其颁奖。
	private StaffClient staff;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public RewardsAmountRuleClient getAmountRule() {
		return amountRule;
	}

	public void setAmountRule(RewardsAmountRuleClient amountRule) {
		this.amountRule = amountRule;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RewardsTypeClient getType() {
		return type;
	}

	public void setType(RewardsTypeClient type) {
		this.type = type;
	}

	public ParticipateInfoClient getParticipateInfo() {
		return participateInfo;
	}

	public void setParticipateInfo(ParticipateInfoClient participateInfo) {
		this.participateInfo = participateInfo;
	}

	public String getBuilderDept() {
		return builderDept;
	}

	public void setBuilderDept(String builderDept) {
		this.builderDept = builderDept;
	}

	public String getAccountDept() {
		return accountDept;
	}

	public void setAccountDept(String accountDept) {
		this.accountDept = accountDept;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getRewardsUnit() {
		return rewardsUnit;
	}

	public void setRewardsUnit(String rewardsUnit) {
		this.rewardsUnit = rewardsUnit;
	}

	
	public int getSizeLimit() {
		return SizeLimit;
	}

	public void setSizeLimit(int sizeLimit) {
		SizeLimit = sizeLimit;
	}

	
	public StaffClient getStaff() {
		return staff;
	}

	public void setStaff(StaffClient staff) {
		this.staff = staff;
	}

	@Override
	public String toString() {
		return "RewardsBaseInfo [id=" + id + ", name=" + name + ", type="
				+ type + ", definition=" + definition + ", standard="
				+ standard 
				+ ", SizeLimit=" + SizeLimit + ", builderDept=" + builderDept
				+ ", accountDept=" + accountDept + ", createAt=" + createAt
				+ ", createdBy=" + createdBy + ", rewardsUnit=" + rewardsUnit
				+ ", amountRule=" + amountRule + ", participateInfo="
				+ participateInfo + ", staff=" + staff + "]";
	}

}
