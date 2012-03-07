/**
 * 
 */
package com.chinarewards.elt.model.reward.search;

import java.util.Date;
import java.util.List;

import com.chinarewards.elt.model.common.PaginationDetail;
import com.chinarewards.elt.model.common.SortingDetail;
import com.chinarewards.elt.model.reward.base.RewardStatus;
import com.chinarewards.elt.model.transaction.TransactionUnit;

/**
 * It is used to query for Reward.
 * 
 * @author yanxin
 * @since 1.0
 */
public class RewardSearchVo {

	private PaginationDetail paginationDetail;

	private SortingDetail sortingDetail;
	/**
	 * 提名人-用户ID
	 */
	private String judgeUserId;

	/**
	 * The staff who win a reward
	 */
	private String winnerStaffId;
	private String winnerStaffName;	
	
	private Date rewardsTime;

	private String rewardId;

	private String name;

	private RewardStatus status;

	private String rewardItemId;

	private String corporationId;

	private String definition;

	private String standard;

	/**
	 * award amount range
	 */
	private Double awardFrom;

	/**
	 * award amount range
	 */
	private Double awardTo;

	/**
	 * which department build it
	 */
	private String builderDeptId;

	/**
	 * which department pay it
	 */
	private String accountDeptId;

	/**
	 * whether search children departments
	 */
	private boolean subDepartmentChosen;

	/**
	 * ids of department
	 */
	private List<String> deptIds;

	/**
	 * Unit to award
	 */
	private TransactionUnit awardUnit;
	

	public Date getRewardsTime() {
		return rewardsTime;
	}

	public void setRewardsTime(Date rewardsTime) {
		this.rewardsTime = rewardsTime;
	}


    private Date lastMonth;//上个月是

	public String getJudgeUserId() {
		return judgeUserId;
	}

	public void setJudgeUserId(String judgeUserId) {
		this.judgeUserId = judgeUserId;
	}

	public String getRewardId() {
		return rewardId;
	}

	public void setRewardId(String rewardId) {
		this.rewardId = rewardId;
	}

	public Date getLastMonth() {
		return lastMonth;
	}

	public void setLastMonth(Date lastMonth) {
		this.lastMonth = lastMonth;
	}

	public PaginationDetail getPaginationDetail() {
		return paginationDetail;
	}

	public void setPaginationDetail(PaginationDetail paginationDetail) {
		this.paginationDetail = paginationDetail;
	}

	public SortingDetail getSortingDetail() {
		return sortingDetail;
	}

	public void setSortingDetail(SortingDetail sortingDetail) {
		this.sortingDetail = sortingDetail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public String getWinnerStaffName() {
		return winnerStaffName;
	}

	public void setWinnerStaffName(String winnerStaffName) {
		this.winnerStaffName = winnerStaffName;
	}

	public RewardStatus getStatus() {
		return status;
	}

	public void setStatus(RewardStatus status) {
		this.status = status;
	}

	public String getRewardItemId() {
		return rewardItemId;
	}

	public void setRewardItemId(String rewardItemId) {
		this.rewardItemId = rewardItemId;
	}

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
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

	public Double getAwardFrom() {
		return awardFrom;
	}

	public void setAwardFrom(Double awardFrom) {
		this.awardFrom = awardFrom;
	}

	public Double getAwardTo() {
		return awardTo;
	}

	public void setAwardTo(Double awardTo) {
		this.awardTo = awardTo;
	}

	public String getBuilderDeptId() {
		return builderDeptId;
	}

	public void setBuilderDeptId(String builderDeptId) {
		this.builderDeptId = builderDeptId;
	}

	public String getAccountDeptId() {
		return accountDeptId;
	}

	public void setAccountDeptId(String accountDeptId) {
		this.accountDeptId = accountDeptId;
	}

	public boolean isSubDepartmentChosen() {
		return subDepartmentChosen;
	}

	public void setSubDepartmentChosen(boolean subDepartmentChosen) {
		this.subDepartmentChosen = subDepartmentChosen;
	}

	public List<String> getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(List<String> deptIds) {
		this.deptIds = deptIds;
	}

	public TransactionUnit getAwardUnit() {
		return awardUnit;
	}

	public void setAwardUnit(TransactionUnit awardUnit) {
		this.awardUnit = awardUnit;
	}

	public String getWinnerStaffId() {
		return winnerStaffId;
	}

	public void setWinnerStaffId(String winnerStaffId) {
		this.winnerStaffId = winnerStaffId;
	}
}
