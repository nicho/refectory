/**
 * 
 */
package com.chinarewards.elt.model.reward.search;

import java.util.Date;
import java.util.List;

import com.chinarewards.elt.model.common.PaginationDetail;
import com.chinarewards.elt.model.common.SortingDetail;

/**
 * This class contains all the require parameters to search RewardItem.
 * 
 * @author yanxin
 * @since 1.0
 */
public class RewardItemSearchVo {

	/**
	 * The paging detail, contains the info it requires.
	 */
	private PaginationDetail paginationDetail;

	/**
	 * Sorting detail.
	 */
	private SortingDetail sortingDetail;

	private String name;

	/**
	 * Search by id of RewardItem
	 */
	private String typeId;
	private Integer totalJF;// 总积分
	private double rewardFrom;// 个人得分
	private int headcountLimit;// 总人数
	private Integer tmdays;// 提前的天数
	private int degree;// 生成奖励的次数

	private String staffId;

	private String enabled;

	public String isEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public int getDegree() {
		return degree;
	}

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public Integer getTotalJF() {
		return totalJF;
	}

	public void setTotalJF(Integer totalJF) {
		this.totalJF = totalJF;
	}

	public double getRewardFrom() {
		return rewardFrom;
	}

	public void setRewardFrom(double rewardFrom) {
		this.rewardFrom = rewardFrom;
	}

	public int getHeadcountLimit() {
		return headcountLimit;
	}

	public void setHeadcountLimit(int headcountLimit) {
		this.headcountLimit = headcountLimit;
	}

	public Integer getTmdays() {
		return tmdays;
	}

	public void setTmdays(Integer tmdays) {
		this.tmdays = tmdays;
	}

	/**
	 * Search by name of RewardItem
	 */
	private String typeName;

	private String definition;

	private String standard;

	/**
	 * The award amount range.
	 */
	private Date startTime;

	private Date createTime;

	private Date createTimeEnd;

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * Search the RewardItem by the specified department manage directly.
	 * 
	 * Notice:
	 * 
	 * This filed should not have value when {@link #deptIds} have value.
	 * Because the query should use {@link #deptIds} first.
	 */
	private String departmentId;

	/**
	 * Search the RewardItem by the list of department.
	 */
	private List<String> deptIds;

	/**
	 * Whether search the RewardItem managed by all the children departments of
	 * the specified department. This value if false by default! When it is true
	 * ,the query maybe become slowly.
	 */
	private boolean subDepartmentChosen = false;

	private String accountDeptName;

	private String buildDeptName;

	/**
	 * Search all the RewardItem of the specified corporation.
	 */
	private String corporationId;

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

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public boolean isSubDepartmentChosen() {
		return subDepartmentChosen;
	}

	public void setSubDepartmentChosen(boolean subDepartmentChosen) {
		this.subDepartmentChosen = subDepartmentChosen;
	}

	public String getAccountDeptName() {
		return accountDeptName;
	}

	public void setAccountDeptName(String accountDeptName) {
		this.accountDeptName = accountDeptName;
	}

	public String getBuildDeptName() {
		return buildDeptName;
	}

	public void setBuildDeptName(String buildDeptName) {
		this.buildDeptName = buildDeptName;
	}

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

	public List<String> getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(List<String> deptIds) {
		this.deptIds = deptIds;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
}
