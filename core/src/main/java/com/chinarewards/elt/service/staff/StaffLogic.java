package com.chinarewards.elt.service.staff;

import java.util.List;

import com.chinarewards.elt.dao.org.StaffDao.QueryStaffPageActionResult;
import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.org.StaffVo;
import com.chinarewards.elt.model.staff.StaffProcess;
import com.chinarewards.elt.model.staff.StaffSearchCriteria;
import com.chinarewards.elt.model.staff.StaffWinSearchCriteria;
import com.chinarewards.elt.model.staff.StaffWinVo;
import com.chinarewards.elt.model.user.GeneratedUserConstants;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.model.user.UserRole;
import com.chinarewards.elt.model.vo.WinnersRecordQueryResult;
import com.chinarewards.elt.model.vo.WinnersRecordQueryVo;
import com.chinarewards.gwt.elt.model.staff.StaffUserProcess;

/**
 * Provides some useful methods to manipulate Staff.
 * 
 * @author yanxin
 * @since 1.0
 */
public interface StaffLogic {

	/**
	 * Create or update a staff.Maybe here should create a user to the created
	 * staff.
	 * 
	 * @param caller
	 * @param staff
	 * @return
	 */
	public Staff saveStaff(SysUser caller, StaffVo staff);

	/**
	 * Get list of Staff by Department id.
	 * 
	 * @param deptId
	 * @param includeChildren
	 *            true - means it will return all the staffs containing the sub
	 *            department's. <br>
	 *            false - means just return the immediacy staffs not containing
	 *            any other sub department's.
	 * @return
	 */
	public List<Staff> getStaffsFromDeptId(String deptId,
			boolean includeChildren);
/**
 * 根据机构ID.返回所有员工
 * @param corporationId
 * @return
 */
	public List<Staff> getStaffsFromCorporationId(String corporationId);
	/**
	 * Get the current balance of the specified Staff.
	 * 
	 * @param staffId
	 * @return
	 */
	public double getBalance(String staffId);
	
	
	public PageStore<WinnersRecordQueryResult> queryWinnerRecords(	WinnersRecordQueryVo queryVo,String corporationId, boolean filterByAcl);
	
	/**
	 *  查询员工,根据员工ID List
	 * @param staffIds
	 * @return staffList(Staff)
	 */
	public List<Staff> findStaffsByStaffIds(List<String> staffIds);
	
	/**
	 * 查询员工列表
	 * @param criteria
	 * @param context
	 * @return
	 */
	public QueryStaffPageActionResult queryStaffList(StaffSearchCriteria criteria,UserContext context);
	/**
	 * 创建 and 修改..员工
	 * @param staffProcess
	 * @return
	 */
	public String createOrUpdateStaff(StaffProcess staff,UserContext context);
	/**
	 * 查询员工信息
	 * @param staffId
	 * @return
	 */
	public Staff findStaffById(String staffId);
	/**
	 * 查询员工获奖信息
	 * @param staffId
	 * @return
	 */
	public StaffWinVo findStaffWinReward(StaffWinSearchCriteria criteria);
	/**
	 * 根据员工生成账户
	 * @param staffId,context
	 * @return
	 */
	public GeneratedUserConstants generatedUserbyStaff(String staffId,UserContext context);
	
	public String createHrUser(StaffUserProcess staff);
	
	public Staff updateLeadTime(String staffId,int leadTime);
	/**
	 * 查询员工角色
	 * @param staffId
	 * @return
	 */
	public List<UserRole> findUserRoles(String staffId);
}
