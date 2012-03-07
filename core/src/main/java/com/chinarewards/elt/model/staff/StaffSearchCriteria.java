package com.chinarewards.elt.model.staff;


import java.io.Serializable;

import com.chinarewards.elt.model.common.PaginationDetail;
import com.chinarewards.elt.model.common.SortingDetail;
import com.chinarewards.elt.model.user.UserRole;

/**
 * This class is designed to wrap the parameter to search main-accounts using
 * paging.
 * 
 * @author nicho
 * @since 2012年2月14日 14:18:46
 */
public class StaffSearchCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8038695352233973821L;
	private PaginationDetail paginationDetail = new PaginationDetail();

	private SortingDetail sortingDetail = new SortingDetail();

	private StaffStatus staffStatus;

	private String staffNameorNo;
	private UserRole staffRole;
	
	private boolean colleaguePage;
	
	public boolean isColleaguePage() {
		return colleaguePage;
	}

	public void setColleaguePage(boolean colleaguePage) {
		this.colleaguePage = colleaguePage;
	}

	public UserRole getStaffRole() {
		return staffRole;
	}

	public void setStaffRole(UserRole staffRole) {
		this.staffRole = staffRole;
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
