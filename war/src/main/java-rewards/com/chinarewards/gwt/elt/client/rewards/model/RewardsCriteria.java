/**
 * 
 */
package com.chinarewards.gwt.elt.client.rewards.model;

import java.util.Date;

import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.chinarewards.gwt.elt.model.SortingDetailClient;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Cream
 * @since 0.2.0 2010-12-27
 */
public class RewardsCriteria implements IsSerializable {

	public static enum RewardsStatus {

		/* 待颁奖 */
		NEW("待颁奖"),

		/* 待提名 */
		PENDING_NOMINATE("待提名"),
		
		/* 待审批 */
		PENDING_APPLICATION("待审核"),
		
		/* 已完成 */
		REWARDED("已完成"),

		/* 已否决 */
		DENIED("已否决");

		private final String displayName;

		RewardsStatus(String displayName) {
			this.displayName = displayName;
		}

		public String getDisplayName() {
			return displayName;
		}

	}

	private PaginationDetailClient pagination;

	private SortingDetailClient sorting;

	private String id;

	private String name;

	private String rewardsItemId;
	private String definition;
	/**
	 * 提名人-用户ID
	 */
	private String judgeUserId;

	/**
	 * 奖项设立之部门
	 */
	private String builderDeptId;

	// 是否查子部门
	private boolean subDepartmentChoose;

	/**
	 * 奖项入账之部门
	 */
	private String accountDeptId;
	/**
	 * 奖励员工的单位(this property is From RewardsItem.rewardsUnit field)
	 */
	private String rewardsUnit;

	private RewardsStatus status;

	/**
	 * 某员工id---查某员工的奖励记录
	 */
	private String staffId;

	
	private String staffName;
	
	private Date rewardsTime;//颁奖时间

	 private Date lastMonth;//上个月是
	public Date getLastMonth() {
		return lastMonth;
	}

	public void setLastMonth(Date lastMonth) {
		this.lastMonth = lastMonth;
	}

	@Override
	public String toString() {
		return "RewardsCriteria [pagination=" + pagination + ", sorting="
				+ sorting + ", id=" + id + ", name=" + name
				+ ", rewardsItemId=" + rewardsItemId + ", builderDeptId="
				+ builderDeptId + ", subDepartmentChoose="
				+ subDepartmentChoose + ", accountDeptId=" + accountDeptId
				+ ", rewardsUnit=" + rewardsUnit + ", status=" + status
				+ ", staffId=" + staffId + "]";
	}

	
	
	public String getStaffName() {
		return staffName;
	}



	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}



	public String getJudgeUserId() {
		return judgeUserId;
	}

	public void setJudgeUserId(String judgeUserId) {
		this.judgeUserId = judgeUserId;
	}
	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	
	
	public Date getRewardsTime() {
		return rewardsTime;
	}

	public void setRewardsTime(Date rewardsTime) {
		this.rewardsTime = rewardsTime;
	}

	public PaginationDetailClient getPagination() {
		return pagination;
	}

	public void setPagination(PaginationDetailClient pagination) {
		this.pagination = pagination;
	}

	public SortingDetailClient getSorting() {
		return sorting;
	}

	public void setSorting(SortingDetailClient sorting) {
		this.sorting = sorting;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the rewardsItemId
	 */
	public String getRewardsItemId() {
		return rewardsItemId;
	}

	/**
	 * @param rewardsItemId
	 *            the rewardsItemId to set
	 */
	public void setRewardsItemId(String rewardsItemId) {
		this.rewardsItemId = rewardsItemId;
	}

	/**
	 * @return the builderDeptId
	 */
	public String getBuilderDeptId() {
		return builderDeptId;
	}

	/**
	 * @param builderDeptId
	 *            the builderDeptId to set
	 */
	public void setBuilderDeptId(String builderDeptId) {
		this.builderDeptId = builderDeptId;
	}

	/**
	 * @return the accountDeptId
	 */
	public String getAccountDeptId() {
		return accountDeptId;
	}

	/**
	 * @param accountDeptId
	 *            the accountDeptId to set
	 */
	public void setAccountDeptId(String accountDeptId) {
		this.accountDeptId = accountDeptId;
	}

	public String getRewardsUnit() {
		return rewardsUnit;
	}

	public void setRewardsUnit(String rewardsUnit) {
		this.rewardsUnit = rewardsUnit;
	}

	/**
	 * @return the status
	 */
	public RewardsStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(RewardsStatus status) {
		this.status = status;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public boolean isSubDepartmentChoose() {
		return subDepartmentChoose;
	}

	public void setSubDepartmentChoose(boolean subDepartmentChoose) {
		this.subDepartmentChoose = subDepartmentChoose;
	}


	public void setWinnerStaffName(String value) {
		
	}

}
