package com.chinarewards.elt.model.vo;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.elt.model.common.PaginationDetail;
import com.chinarewards.elt.model.common.SortingDetail;
import com.chinarewards.elt.model.staff.StaffStatus;
import com.chinarewards.elt.model.user.DeleteMarkConstant;

public class StaffSearchVo {

	Logger logger = LoggerFactory.getLogger(getClass());

	public static interface DeptSearchParam {
	}

	public static class OneIdParam implements DeptSearchParam {
		private String deptId;
		private boolean includeSiblings;

		public OneIdParam() {
		}

		public OneIdParam(String deptId, boolean includeSiblings) {
			this.deptId = deptId;
			this.includeSiblings = includeSiblings;
		}

		/**
		 * @return the includeSiblings
		 */
		public boolean isIncludeSiblings() {
			return includeSiblings;
		}

		/**
		 * @param includeSiblings
		 *            the includeSiblings to set
		 */
		public void setIncludeSiblings(boolean includeSiblings) {
			this.includeSiblings = includeSiblings;
		}

		/**
		 * @return the deptId
		 */
		public String getDeptId() {
			return deptId;
		}

		/**
		 * @param deptId
		 *            the deptId to set
		 */
		public void setDeptId(String deptId) {
			this.deptId = deptId;
		}

	}

	public static class MultipleIdParam implements DeptSearchParam {
		private Set<String> ids = new TreeSet<String>();

		public MultipleIdParam() {
		}

		public MultipleIdParam(Set<String> ids) {
			super();
			this.ids = ids;
		}

		/**
		 * @return the ids
		 */
		public Set<String> getIds() {
			return ids;
		}
	}

	private PaginationDetail paginationDetail;

	private SortingDetail sortingDetail;
	// 员工姓名
	private String staffName;
	// 员工手机号码
	private String staffMobiletelephoneNumber;
	// 员工卡号
	private String staffCardNumber;
	// 员工离职状态
	private DeleteMarkConstant deleteMarkConstant;
	// 企业ID
	private String enterpriseId;
	// 部门ID
	/**
	 * @deprecated
	 */
	private String deptId;

	// 关键字(姓名/部门/卡号/邮箱)
	private String keywords;

	private DeptSearchParam deptParam;

	// 部门名称
	private String departmentname;

	private StaffStatus status;

	public StaffStatus getStatus() {
		return status;
	}

	public void setStatus(StaffStatus status) {
		this.status = status;
	}
	private List<String> staffids;
	
	
	public List<String> getStaffids() {
		return staffids;
	}

	public void setStaffids(List<String> staffids) {
		this.staffids = staffids;
	}

	@Override
	public String toString() {
		return "StaffSearchVo [logger=" + logger + ", paginationDetail="
				+ paginationDetail + ", sortingDetail=" + sortingDetail
				+ ", staffName=" + staffName + ", staffMobiletelephoneNumber="
				+ staffMobiletelephoneNumber + ", staffCardNumber="
				+ staffCardNumber + ", deleteMarkConstant="
				+ deleteMarkConstant + ", enterpriseId=" + enterpriseId
				+ ", deptId=" + deptId + ", keywords=" + keywords
				+ ", deptParam=" + deptParam + ", departmentname="
				+ departmentname + "]";
	}

	/**
	 * 部门名称
	 * 
	 * @return
	 */
	public String getDepartmentname() {
		return departmentname;
	}

	/**
	 * 部门名称
	 * 
	 * @param departmentname
	 */
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	/**
	 * 关键字(姓名/部门/卡号/邮箱)
	 * 
	 * @return
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * 关键字(姓名/部门/卡号/邮箱)
	 * 
	 * @param keywords
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffMobiletelephoneNumber() {
		return staffMobiletelephoneNumber;
	}

	public void setStaffMobiletelephoneNumber(String staffMobiletelephoneNumber) {
		this.staffMobiletelephoneNumber = staffMobiletelephoneNumber;
	}

	public String getStaffCardNumber() {
		return staffCardNumber;
	}

	public void setStaffCardNumber(String staffCardNumber) {
		this.staffCardNumber = staffCardNumber;
	}

	public DeleteMarkConstant getDeleteMarkConstant() {
		return deleteMarkConstant;
	}

	public void setDeleteMarkConstant(DeleteMarkConstant deleteMarkConstant) {
		this.deleteMarkConstant = deleteMarkConstant;
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

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	/**
	 * Returns the department ID to use.
	 * 
	 * @return
	 */
	public String getDeptId() {
		return deptId;
	}

	/**
	 * Sets the department IDs to look for staff which does belong to these
	 * departments.
	 * 
	 * @param deptId
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	/**
	 * @return the deptParam
	 */
	public DeptSearchParam getDeptParam() {
		return deptParam;
	}

	/**
	 * @param deptParam
	 *            the deptParam to set
	 */
	public void setDeptParam(DeptSearchParam deptParam) {
		this.deptParam = deptParam;
	}

}
