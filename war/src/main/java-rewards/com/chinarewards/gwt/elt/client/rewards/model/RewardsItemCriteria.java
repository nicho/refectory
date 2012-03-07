/**
 * 
 */
package com.chinarewards.gwt.elt.client.rewards.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.chinarewards.gwt.elt.model.SortingDetailClient;

/**
 * @author Cream
 * @since 0.2.0 2010-12-16
 */
public class RewardsItemCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8705022589947502175L;

	private PaginationDetailClient pagination;

	private SortingDetailClient sorting;

	private String id;

	private String name;

	private String typeName;

	private String definition;

	private String standard;
	//每人得的积分
	private Double rewardFrom;
	private int headcountLimit;//得奖总人数
	private Integer tmdays;//提前的天数
	private int degree;// 生成奖励的次数
	private String accountDeptName="";
    
	private String buildDeptName="";
	
	private String departmentId="";
	
	private boolean subDepartmentChoose;

	private Date startTime;

	private Date createTime;//查询时传的参数（创建时间起）
	private String enabled;
	public String isEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	private Date createTimeEnd;//查询时传的参数（创建时间止）

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	private Date endTime;
	/**
	 * 根据类型Id查询
	 */
	private String typeId;
	/**
	 * 按所属部门id查询
	 */
	private List<String> deptIds;

	/**
	 * 按所属企业id查询
	 */
	private String corporationId;
	
	
	private Integer totalJF;//总积分
	public Integer getTotalJF() {
		return totalJF;
	}

	public void setTotalJF(Integer totalJF) {
		this.totalJF = totalJF;
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

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RewardsItemCriteria [pagination=" + pagination + ", sorting="
				+ sorting + ", id=" + id + ", name=" + name + ", typeName="
				+ typeName + ", definition=" + definition + ", standard="
				+ standard + ", rewardFrom=" + rewardFrom 
				+ ", departmentId=" + departmentId + ", subDepartmentChoose="
				+ subDepartmentChoose + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", typeId=" + typeId + ", deptIds="
				+ deptIds + ", corporationId=" + corporationId + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Double getRewardFrom() {
		return rewardFrom;
	}

	public void setRewardFrom(Double rewardFrom) {
		this.rewardFrom = rewardFrom;
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<String> getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(List<String> deptIds) {
		this.deptIds = deptIds;
	}

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
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

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public boolean isSubDepartmentChoose() {
		return subDepartmentChoose;
	}

	public void setSubDepartmentChoose(boolean subDepartmentChoose) {
		this.subDepartmentChoose = subDepartmentChoose;
	}
	
	

}
