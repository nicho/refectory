package com.chinarewards.gwt.elt.client.rewards.model;

import java.io.Serializable;
import java.util.List;

import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.chinarewards.gwt.elt.model.SortingDetailClient;

public class StaffSearchCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2509102810254544279L;

	private String key;

	/**
	 * 部门Id
	 */
	private String deptId;

	private PaginationDetailClient pagination;

	private SortingDetailClient sorting;

	private boolean chooseAll = true;

	private List<String> orgIds;
	
	/**
	 * 奖励ID
	 */
	private String  rewardId;

	public String getRewardId() {
		return rewardId;
	}

	public void setRewardId(String rewardId) {
		this.rewardId = rewardId;
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

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public boolean isChooseAll() {
		return chooseAll;
	}

	public void setChooseAll(boolean isChooseAll) {
		this.chooseAll = isChooseAll;
	}

	public List<String> getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(List<String> orgIds) {
		this.orgIds = orgIds;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
