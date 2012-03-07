/**
 * 
 */
package com.chinarewards.gwt.elt.client.staffList.model;

import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.chinarewards.gwt.elt.model.SortingDetailClient;
import com.chinarewards.gwt.elt.model.user.UserRoleVo;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author nicho
 * @since 2012年2月14日 11:29:59
 */
public class StaffListCriteria implements IsSerializable {

	public static enum StaffStatus {

		/* 待入职 */
		ENTRY("待入职"),

		/* 在职 */
		JOB("在职"),

		/* 已离职 */
		DEPARTURE("已离职");

		private final String displayName;

		StaffStatus(String displayName) {
			this.displayName = displayName;
		}

		public String getDisplayName() {
			return displayName;
		}

	}

	private PaginationDetailClient pagination;

	private SortingDetailClient sorting;

	private StaffStatus staffStatus;
	private UserRoleVo staffRole;

	private String staffNameorNo;
	private boolean colleaguePage;

	public boolean isColleaguePage() {
		return colleaguePage;
	}

	public void setColleaguePage(boolean colleaguePage) {
		this.colleaguePage = colleaguePage;
	}

	public UserRoleVo getStaffRole() {
		return staffRole;
	}

	public void setStaffRole(UserRoleVo staffRole) {
		this.staffRole = staffRole;
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

	public StaffStatus getStaffStatus() {
		return staffStatus;
	}

	public void setStaffStatus(StaffStatus staffStatus) {
		this.staffStatus = staffStatus;
	}

	public String getStaffNameorNo() {
		return staffNameorNo;
	}

	public void setStaffNameorNo(String staffNameorNo) {
		this.staffNameorNo = staffNameorNo;
	}

}
