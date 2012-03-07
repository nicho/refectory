package com.chinarewards.gwt.elt.client.department.model;

import java.io.Serializable;
import java.util.List;

import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;

public class DepartmentVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String corporationId;
	private String name; // 部门名称
	private List<String> leaderIds;
	private List<String> preLeaderIds;//临时字段 记录修改前的Leaders
	
	private List<OrganicationClient> leaderList;
	
	private String parentId;
	private String parentName;
	private List<String> childIds;
	private List<String> childNames;
	private String peopleNumber;//员工数
	private String yearintegral;//财年积分
	private String issueintegral;//已颁发积分
	private String procesRewarditemCount;//进行中奖项(数量)
	
	private String thisAction="";
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getThisAction() {
		return thisAction;
	}

	public void setThisAction(String thisAction) {
		this.thisAction = thisAction;
	}

	public List<OrganicationClient> getLeaderList() {
		return leaderList;
	}

	
	public void setLeaderList(List<OrganicationClient> leaderList) {
		this.leaderList = leaderList;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {		
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<String> getPreLeaderIds() {
		return preLeaderIds;
	}

	public void setPreLeaderIds(List<String> preLeaderIds) {
		this.preLeaderIds = preLeaderIds;
	}

	public List<String> getChildIds() {
		return childIds;
	}

	public void setChildIds(List<String> childIds) {
		this.childIds = childIds;
	}

	public List<String> getChildNames() {
		return childNames;
	}

	public void setChildNames(List<String> childNames) {
		this.childNames = childNames;
	}

	public String getPeopleNumber() {
		return peopleNumber;
	}

	public void setPeopleNumber(String peopleNumber) {
		this.peopleNumber = peopleNumber;
	}

	public String getYearintegral() {
		return yearintegral;
	}

	public void setYearintegral(String yearintegral) {
		this.yearintegral = yearintegral;
	}

	public String getIssueintegral() {
		return issueintegral;
	}

	public void setIssueintegral(String issueintegral) {
		this.issueintegral = issueintegral;
	}

	public String getProcesRewarditemCount() {
		return procesRewarditemCount;
	}

	public void setProcesRewarditemCount(String procesRewarditemCount) {
		this.procesRewarditemCount = procesRewarditemCount;
	}

	public List<String> getLeaderIds() {
		return leaderIds;
	}

	public void setLeaderIds(List<String> leaderIds) {
		this.leaderIds = leaderIds;
	}
	
	

}
