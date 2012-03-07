/**
 * 
 */
package com.chinarewards.elt.model.vo;

import com.chinarewards.elt.model.common.PaginationDetail;
import com.chinarewards.elt.model.common.SortingDetail;

/**
 * @author Cream
 * @since 0.2.0 2011-01-05
 */
public class DepartmentSearchVo {

	private PaginationDetail pagination;

	private SortingDetail sort;

	private String id;

	private String name;

	private String description;

	private String parentId;

	private String parentName;

	private Boolean topDepartment;

	// true to contains a list of staff which belong to this department.
	private Boolean containsStaffInfo;

	@Override
	public String toString() {
		return "DepartmentSearchVo [pagination=" + pagination + ", sort="
				+ sort + ", id=" + id + ", name=" + name + ", description="
				+ description + ", parentId=" + parentId + ", parentName="
				+ parentName + ", topDepartment=" + topDepartment
				+ ", containsStaffInfo=" + containsStaffInfo + "]";
	}

	// ---- getter & setter ----
	/**
	 * @return the pagination
	 */
	public PaginationDetail getPagination() {
		return pagination;
	}

	/**
	 * @param pagination
	 *            the pagination to set
	 */
	public void setPagination(PaginationDetail pagination) {
		this.pagination = pagination;
	}

	/**
	 * @return the sort
	 */
	public SortingDetail getSort() {
		return sort;
	}

	/**
	 * @param sort
	 *            the sort to set
	 */
	public void setSort(SortingDetail sort) {
		this.sort = sort;
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
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the topDepartment
	 */
	public Boolean getTopDepartment() {
		return topDepartment;
	}

	/**
	 * @param topDepartment
	 *            the topDepartment to set
	 */
	public void setTopDepartment(Boolean topDepartment) {
		this.topDepartment = topDepartment;
	}

	/**
	 * @return the parentName
	 */
	public String getParentName() {
		return parentName;
	}

	/**
	 * @param parentName
	 *            the parentName to set
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	/**
	 * Sets true will return a list of staff which belongs to this department.
	 * Sets false will always return an empty staff list.
	 * 
	 * @return the containsStaffInfo
	 */
	public Boolean getContainsStaffInfo() {
		return containsStaffInfo;
	}

	/**
	 * Sets true will return a list of staff which belongs to this department.
	 * Sets false will always return an empty staff list.
	 * 
	 * @param containsStaffInfo
	 *            the containsStaffInfo to set
	 */
	public void setContainsStaffInfo(Boolean containsStaffInfo) {
		this.containsStaffInfo = containsStaffInfo;
	}

}
