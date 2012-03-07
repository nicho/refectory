package com.chinarewards.elt.service.staff.impl;

import java.util.List;

import com.chinarewards.elt.dao.org.DepartmentManagerDao;
import com.chinarewards.elt.dao.org.StaffDao;
import com.chinarewards.elt.dao.org.StaffDao.QueryStaffPageActionResult;
import com.chinarewards.elt.dao.user.RoleDao;
import com.chinarewards.elt.dao.user.UserRoleDao;
import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.domain.org.manager.DepartmentManager;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.domain.user.SysUserRole;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.org.StaffVo;
import com.chinarewards.elt.model.staff.StaffProcess;
import com.chinarewards.elt.model.staff.StaffSearchCriteria;
import com.chinarewards.elt.model.staff.StaffWinSearchCriteria;
import com.chinarewards.elt.model.staff.StaffWinVo;
import com.chinarewards.elt.model.user.GeneratedUserConstants;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.model.user.UserRole;
import com.chinarewards.elt.model.user.UserVo;
import com.chinarewards.elt.model.vo.WinnersRecordQueryResult;
import com.chinarewards.elt.model.vo.WinnersRecordQueryVo;
import com.chinarewards.elt.service.staff.IStaffService;
import com.chinarewards.elt.service.staff.StaffLogic;
import com.chinarewards.elt.service.user.UserLogic;
import com.chinarewards.elt.tx.service.TransactionService;
import com.chinarewards.elt.util.DateUtil;
import com.chinarewards.gwt.elt.model.staff.StaffUserProcess;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

@Transactional
public class StaffServiceImpl implements IStaffService {

	StaffDao staffDao;
	StaffLogic staffLogic;
	UserLogic userLogic;
	TransactionService transactionService;
	UserRoleDao userRoleDao;
	RoleDao roleDao;
	DepartmentManagerDao departmentManagerDao;

	@Inject
	public StaffServiceImpl(StaffDao staffDao, StaffLogic staffLogic,
			UserLogic userLogic, TransactionService transactionService,
			UserRoleDao userRoleDao, RoleDao roleDao,DepartmentManagerDao departmentManagerDao) {
		this.staffDao = staffDao;
		this.staffLogic = staffLogic;
		this.userLogic = userLogic;
		this.transactionService = transactionService;
		this.userRoleDao = userRoleDao;
		this.roleDao = roleDao;
		this.departmentManagerDao=departmentManagerDao;
	}

	@Override
	public String createStaff(StaffUserProcess staffProcess) {

		SysUser caller = userLogic.findUserById(staffProcess.getCreateUserId());
		// if (!em.getTransaction().isActive()) {
		// em.getTransaction().begin();
		// }
		StaffVo staffVo = new StaffVo();
		staffVo.setName(staffProcess.getName());
		staffVo.setPhone(staffProcess.getTell());
		staffVo.setEmail(staffProcess.getEmail());
		String accountId = transactionService.createNewAccount();
		staffVo.setTxAccountId(accountId);
		staffVo.setCorpId(caller.getCorporation().getId());
		staffVo.setDeptId(staffProcess.getDeptId());
		Staff staff = staffLogic.saveStaff(caller, staffVo);

		UserVo userVo = new UserVo();
		userVo.setCorporationId(caller.getCorporation().getId());
		userVo.setPassword(staffProcess.getPassword());
		userVo.setUsername(staffProcess.getUsername());
		userVo.setStaffId(staff.getId());
		SysUser user = userLogic.createUser(caller, userVo);

		// 添加角色...2012年2月3日 15:54:59..更新.加入角色权限---by nicho
	for (int i = 0; i < staffProcess.getRoles().size(); i++) {
		SysUserRole userRole = new SysUserRole();
		userRole.setRole(roleDao.findRoleByRoleName(staffProcess.getRoles().get(i)));
		userRole.setCreatedBy(user);
		userRole.setCreatedAt(DateUtil.getTime());
		userRole.setLastModifiedAt(DateUtil.getTime());
		userRole.setLastModifiedBy(user);
		userRole.setUser(user);
		userRoleDao.createUserRole(userRole);
		

	}
	//如果是部门管理员.加入部门管理表

			if(staffProcess.getRoles().contains(UserRole.DEPT_MGR))
			{
				DepartmentManager dm=new DepartmentManager();
				dm.setStaff(staff);
				dm.setDepartment(staff.getDepartment());
				departmentManagerDao.save(dm);
			}
			return user.getId();
	}


	public PageStore<WinnersRecordQueryResult> queryWinnerRecords(
			WinnersRecordQueryVo queryVo, String corporationId,
			boolean filterByAcl) {
		return staffLogic.queryWinnerRecords(queryVo, corporationId,
				filterByAcl);
	}

	@Override
	public double getBalance(String staffId) {
		return staffLogic.getBalance(staffId);
	}

	@Override
	public QueryStaffPageActionResult queryStaffList(StaffSearchCriteria criteria,
			UserContext context) {
		return staffLogic.queryStaffList(criteria, context);
	}

	@Override
	public String createOrUpdateStaff(StaffProcess staff, UserContext context) {
		return staffLogic.createOrUpdateStaff(staff, context);
	}

	@Override
	public Staff findStaffById(String staffId) {
		return staffLogic.findStaffById(staffId);
	}

	@Override
	public StaffWinVo findStaffWinReward(StaffWinSearchCriteria criteria) {
		return staffLogic.findStaffWinReward(criteria);
	}

	@Override
	public GeneratedUserConstants generatedUserbyStaff(String staffId, UserContext context) {
		return staffLogic.generatedUserbyStaff(staffId, context);
	}
	@Override
	public String createHrUser(StaffUserProcess staff){
		return staffLogic.createHrUser( staff);
	}
	@Override
	public Staff updateLeadTime(String staffId,int leadTime){
		return staffLogic.updateLeadTime(staffId, leadTime);
	}

	@Override
	public List<UserRole> findUserRoles(String staffId) {
		return staffLogic.findUserRoles(staffId);
	}
}
