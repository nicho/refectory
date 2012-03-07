package com.chinarewards.gwt.elt.client.rewards.model;

import java.io.Serializable;
import java.util.List;

import com.chinarewards.gwt.elt.model.RewardsApprovalPolicyConstants;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * 符合标准的部门
 * 
 * @author yanxin
 * @since 1.0.0 2010-12-14
 */
public class DepartmentClient extends OrganicationClient implements
		IsSerializable, Serializable, Comparable<DepartmentClient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7549687690233240286L;

	private DepartmentClient parent;

	private String description;

	private int childCount;

	private boolean isLeaf;

	/**
	 * 部门成员
	 */
	private List<StaffClient> staffList;

	// 批复策略
	private String approvalPolicy;

	public DepartmentClient() {
	}

	public DepartmentClient(String name) {
		this.name = name;
	}

	public DepartmentClient(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public DepartmentClient(String id, String name, DepartmentClient parent) {
		this(id, name);
		this.parent = parent;
	}

	@Override
	public String toString() {
		return super.toString() + "DepartmentClient [parent=" + parent
				+ ", description=" + description + ", childCount=" + childCount
				+ ", isLeaf=" + isLeaf + ", staffList=" + staffList
				+ ", approvalPolicy=" + approvalPolicy + "]";
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

	public List<StaffClient> getStaffList() {
		return staffList;
	}

	public void setStaffList(List<StaffClient> staffList) {
		this.staffList = staffList;
	}

	/**
	 * @return the parent
	 */
	public DepartmentClient getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(DepartmentClient parent) {
		this.parent = parent;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the isLeaf
	 */
	public boolean isLeaf() {
		return isLeaf;
	}

	/**
	 * @param isLeaf
	 *            the isLeaf to set
	 */
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	/**
	 * @return the childCount
	 */
	public int getChildCount() {
		return childCount;
	}

	/**
	 * @param childCount
	 *            the childCount to set
	 */
	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}

	/**
	 * @see RewardsApprovalPolicyConstants
	 * @return the approvalPolicy
	 */
	public String getApprovalPolicy() {
		return approvalPolicy;
	}

	/**
	 * @see RewardsApprovalPolicyConstants
	 * @param approvalPolicy
	 *            the approvalPolicy to set
	 */
	public void setApprovalPolicy(String approvalPolicy) {
		this.approvalPolicy = approvalPolicy;
	}

	@Override
	public int compareTo(DepartmentClient dept) {
		return dept == null ? -1 : dept.getName().compareTo(name);
	}
}
