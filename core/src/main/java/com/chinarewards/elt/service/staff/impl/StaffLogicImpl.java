package com.chinarewards.elt.service.staff.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.elt.common.LogicContext;
import com.chinarewards.elt.common.UserContextProvider;
import com.chinarewards.elt.dao.org.DepartmentDao;
import com.chinarewards.elt.dao.org.DepartmentManagerDao;
import com.chinarewards.elt.dao.org.StaffDao;
import com.chinarewards.elt.dao.org.StaffDao.QueryStaffPageActionResult;
import com.chinarewards.elt.dao.reward.WinnerDao;
import com.chinarewards.elt.dao.user.RoleDao;
import com.chinarewards.elt.dao.user.UserDao;
import com.chinarewards.elt.dao.user.UserRoleDao;
import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.domain.org.Department;
import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.domain.user.SysRole;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.domain.user.SysUserRole;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.org.StaffVo;
import com.chinarewards.elt.model.staff.StaffProcess;
import com.chinarewards.elt.model.staff.StaffSearchCriteria;
import com.chinarewards.elt.model.staff.StaffStatus;
import com.chinarewards.elt.model.staff.StaffWinSearchCriteria;
import com.chinarewards.elt.model.staff.StaffWinVo;
import com.chinarewards.elt.model.user.GeneratedUserConstants;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.model.user.UserRole;
import com.chinarewards.elt.model.user.UserStatus;
import com.chinarewards.elt.model.vo.StaffSearchVo;
import com.chinarewards.elt.model.vo.StaffSearchVo.MultipleIdParam;
import com.chinarewards.elt.model.vo.WinnersRecordQueryResult;
import com.chinarewards.elt.model.vo.WinnersRecordQueryVo;
import com.chinarewards.elt.service.org.CorporationLogic;
import com.chinarewards.elt.service.org.DepartmentLogic;
import com.chinarewards.elt.service.org.DepartmentManagerLogic;
import com.chinarewards.elt.service.staff.StaffLogic;
import com.chinarewards.elt.tx.model.Unit;
import com.chinarewards.elt.tx.service.TransactionService;
import com.chinarewards.elt.util.DateUtil;
import com.chinarewards.elt.util.MD5;
import com.chinarewards.elt.util.StringUtil;
import com.chinarewards.gwt.elt.model.staff.StaffUserProcess;
import com.google.inject.Inject;

public class StaffLogicImpl implements StaffLogic {

	Logger logger = LoggerFactory.getLogger(getClass());

	private final StaffDao staffDao;
	private final DepartmentLogic deptLogic;
	private final CorporationLogic corporationLogic;
	private final TransactionService transactionService;
	private final DepartmentDao depDao;
	private final DepartmentManagerLogic departmentManagerLogic;
	private final UserDao userDao;
	private final WinnerDao winnerDao;
	private final UserRoleDao userRoleDao;
	private final RoleDao roleDao;
	private final DepartmentManagerDao deptMgrDao;
	private final DepartmentLogic departmentLogic;
	MD5 md5 = new MD5();

	@Inject
	public StaffLogicImpl(StaffDao staffDao, DepartmentLogic deptLogic,
			CorporationLogic corporationLogic, DepartmentDao depDao,
			TransactionService transactionService,
			DepartmentManagerLogic departmentManagerLogic, UserDao userDao,
			WinnerDao winnerDao, UserRoleDao userRoleDao, RoleDao roleDao,
			DepartmentManagerDao deptMgrDao, DepartmentLogic departmentLogic) {
		this.staffDao = staffDao;
		this.deptLogic = deptLogic;
		this.corporationLogic = corporationLogic;
		this.transactionService = transactionService;
		this.depDao = depDao;
		this.departmentManagerLogic = departmentManagerLogic;
		this.userDao = userDao;
		this.winnerDao = winnerDao;
		this.userRoleDao = userRoleDao;
		this.roleDao = roleDao;
		this.deptMgrDao = deptMgrDao;
		this.departmentLogic = departmentLogic;
	}

	@Override
	public List<Staff> getStaffsFromDeptId(String deptId,
			boolean includeChildren) {
		logger.debug(
				"Invoking method getStaffsFromDeptId, param[deptId={}, includeChildren={}]",
				new Object[] { deptId, includeChildren });
		if (includeChildren) {
			Department dept = deptLogic.findDepartmentById(deptId);
			String corpId = dept.getCorporation().getId();
			List<Staff> result = staffDao.findStaffsByDepartmentLftRgt(corpId,
					dept.getLft(), dept.getRgt());
			logger.debug("The gotten staff size={}", result.size());
			return result;
		} else {
			return staffDao.findStaffsByDepartmentId(deptId);
		}
	}

	@Override
	public Staff saveStaff(SysUser caller, StaffVo staff) {
		// FIXME implement it, just partly code here.
		if (StringUtil.isEmptyString(staff.getId())) {
			// Create a new staff
			Corporation corp = corporationLogic.findCorporationById(staff
					.getCorpId());
			Department dept = deptLogic.findDepartmentById(staff.getDeptId());
			Staff ff = new Staff();
			ff.setCorporation(corp);
			ff.setDepartment(dept);
			ff.setName(staff.getName());
			ff.setDescription(staff.getDescription());
			ff.setTxAccountId(staff.getTxAccountId());
			ff.setCreatedBy(caller);
			ff.setCreatedAt(DateUtil.getTime());
			ff.setDeleted(0);
			staffDao.save(ff);
			return ff;
		} else {
			// Edit
		}
		return null;
	}

	@Override
	public double getBalance(String staffId) {
		Staff staff = staffDao.findById(Staff.class, staffId);
		Unit defaultUnit = corporationLogic.getDefaultUnit(staff
				.getCorporation().getId());
		return transactionService.getBalance(staff.getTxAccountId(),
				defaultUnit.getCode());
	}

	public PageStore<WinnersRecordQueryResult> queryWinnerRecords(
			WinnersRecordQueryVo queryVo, String corporationId,
			boolean filterByAcl) {
		logger.debug(" Process in queryWinnerRecords method , parameter queryVO.toString:"
				+ queryVo.toString() + ", filterByAcl = " + filterByAcl);
		// Principal principal = UserContextProvider.get().getPrincipal();
		// logger.debug(" principal.toString :" + principal.printTheProperty());

		PageStore<WinnersRecordQueryResult> pageStore = new PageStore<WinnersRecordQueryResult>();

		// empty result
		PageStore<WinnersRecordQueryResult> emptyResult = new PageStore<WinnersRecordQueryResult>();
		emptyResult.setResultCount(0);
		emptyResult.setResultList(new ArrayList<WinnersRecordQueryResult>());

		if (queryVo.getSubDeptIds() != null
				&& !queryVo.getSubDeptIds().isEmpty()) {
			if (queryVo.isIncludeSubDepts()) {
				Set<String> resolvedDeptIds = new HashSet<String>();
				for (String deptId : queryVo.getSubDeptIds()) {
					Set<String> deptIdSubset = depDao.findSiblingIds(deptId,
							true);
					resolvedDeptIds.addAll(deptIdSubset);
				}
				queryVo.setSubDeptIds(new ArrayList<String>(resolvedDeptIds));
				queryVo.setIncludeSubDepts(false); // since we have resolved
				// them.
			}
		}
		// finally enforce ACL, if requested
		// cyril: limit data visible according to the logged in user.
		LogicContext ctx = UserContextProvider.get();
		if (filterByAcl) {
			if (ctx.isCallerInRole(UserRole.CORP_ADMIN)
					|| ctx.isCallerInRole(UserRole.SUB_CORP_ADMIN)) {
				// do nothing
				// XXX should be better.
			} else if (ctx.isCallerInRole(UserRole.DEPT_MGR)) {
				// filter on this
				// XXX bad
				// find out all departments (including siblings) managed
				// by the user.
				List<String> onelevelManagedDeptIds = departmentManagerLogic
						.findDepartmentIdsManagedByLoginUser();
				Set<String> managedDeptIds = new HashSet<String>();
				for (String id : onelevelManagedDeptIds) {
					managedDeptIds.addAll(depDao.findSiblingIds(id, true));
				}
				// now, strip off from the children Ids
				if (queryVo.getSubDeptIds() == null
						|| queryVo.getSubDeptIds().isEmpty()) {
					// since it is empty (no criteria given by user), we add
					// this constraint.
					queryVo.setSubDeptIds(new ArrayList<String>(managedDeptIds));
				} else {
					// criteria is given by user. we do a INTERSECT over the
					// two collections. If the result is an empty set, it
					// means what the user wants to access is all filtered
					// out, thus we return an empty result immediately.
					Set<String> userDeptIds = new HashSet<String>(
							queryVo.getSubDeptIds());
					userDeptIds.retainAll(managedDeptIds);
					if (userDeptIds.isEmpty()) {
						logger.debug("All user specified department criteria are filtered out, return empty result");
						return emptyResult;
					}
					// otherwise, update user's input criteria.
					logger.debug("Update user's input with new department IDs criteria");
					queryVo.setSubDeptIds(new ArrayList<String>(userDeptIds));
				}

			}
		}

		pageStore.setResultCount(staffDao.queryWinnerRewardsCount(queryVo,
				corporationId));
		// XXX implicit ACL found on corporation.
		List<WinnersRecordQueryResult> list = staffDao.queryWinnerRewardsData(
				queryVo, corporationId);

		// // grep the list of department IDs
		// Set<String> deptIds = new HashSet<String>();
		// for (WinnersRecordQueryResult r : list) {
		// if (r.getDepartment() != null) {
		// deptIds.add(r.getDepartment().getId());
		// }
		// }
		// // and resolve the full name, and build a map of Dept IDs <-> Dept
		// Tree
		// // Name.
		// Map<String /* dept ID */, Department> deptIdMap = new
		// Hashtable<String, Department>();
		// if (deptIds != null && !deptIds.isEmpty()) {
		// List<Department> depts = departmentLogic.findDepartmentByIds(new
		// ArrayList<String>(deptIds));
		// for (Department dept : depts) {
		// Department i = dept;
		// while (i != null) {
		// i = i.getParent();
		// }
		// deptIdMap.put(dept.getId(), dept);
		// }
		// }
		//
		// // FIXME too dirty to do!
		// for (WinnersRecordQueryResult i : list) {
		// if (i.getDepartment() != null) {
		// Department lookup = deptIdMap.get(i.getDepartment().getId());
		// i.setDepartment(lookup);
		// }
		// }

		pageStore.setResultList(list);
		return pageStore;
	}

	@Override
	public List<Staff> findStaffsByStaffIds(List<String> staffIds) {

		return staffDao.findStaffsByStaffIds(staffIds);

	}

	@Override
	public List<Staff> getStaffsFromCorporationId(String corporationId) {

		List<Staff> result = staffDao.findStaffsByCorporationId(corporationId);
		logger.debug("The gotten staff size={}", result.size());
		return result;

	}

	@Override
	public QueryStaffPageActionResult queryStaffList(
			StaffSearchCriteria criteria, UserContext context) {
		StaffSearchVo searchVo = new StaffSearchVo();
		// 待添加查询条件
		if (!StringUtil.isEmptyString(criteria.getStaffNameorNo()))
			searchVo.setKeywords(criteria.getStaffNameorNo());
		if (criteria.getStaffStatus() != null)
			searchVo.setStatus(criteria.getStaffStatus());
		if (context.getCorporationId() != null)
			searchVo.setEnterpriseId(context.getCorporationId());
		if (criteria.getStaffRole() != null) {
			List<String> qstaffIds = new ArrayList<String>();
			List<String> staffIds = userRoleDao.findStaffIdsByRole(criteria
					.getStaffRole());
			if (staffIds != null && staffIds.size() > 0) {
				qstaffIds = staffIds;
			} else {
				qstaffIds.add("notStaffId");
			}
			searchVo.setStaffids(qstaffIds);
		}

		searchVo.setPaginationDetail(criteria.getPaginationDetail());
		searchVo.setSortingDetail(criteria.getSortingDetail());

		// 如果员工界面..不过滤
		if (!criteria.isColleaguePage()) {
			// 处理部门管理员..进入..只查询本部门数据
			boolean fal = false;
			for (UserRole role : context.getUserRoles()) {
				if (role == UserRole.CORP_ADMIN) {
					fal = true;
				}
			}
			if (fal == false) {
				SysUser user = userDao.findUserById(context.getUserId());
				if (user != null && user.getStaff() != null) {
					List<String> departmentIds = deptMgrDao
							.findDepartmentIdsManagedByStaffId(user.getStaff()
									.getId());
					if (departmentIds.size() > 0) {
						Set<String> allDepartmentIds = new HashSet<String>();
						for (String id : departmentIds) {
							allDepartmentIds.addAll(departmentLogic
									.getWholeChildrenIds(id, true));
						}
						searchVo.setDeptParam(new MultipleIdParam(
								allDepartmentIds));
					}

				}

			}
		}
		return staffDao.queryStaffPageAction(searchVo);
	}

	@Override
	public String createOrUpdateStaff(StaffProcess staff, UserContext context) {
		Staff ff = null;
		SysUser nowuser = userDao.findUserById(context.getUserId());
		if (StringUtil.isEmptyString(staff.getStaffId())) {
			ff = new Staff();
		} else {
			ff = staffDao.findById(Staff.class, staff.getStaffId());
		}

		if (!StringUtil.isEmptyString(staff.getDepartmentId())) {
			Department dept = deptLogic.findDepartmentById(staff
					.getDepartmentId());
			ff.setDepartment(dept);
		} else {
			// 如果传入空部门,默认当前用户部门
			ff.setDepartment(nowuser.getStaff().getDepartment());

		}
		if (staff.getStatus() != null)
			ff.setStatus(staff.getStatus());
		if (staff.getStaffNo() != null)
			ff.setJobNo(staff.getStaffNo());
		if (staff.getPhoto() != null)
			ff.setPhoto(staff.getPhoto());
		if (staff.getPhone() != null)
			ff.setPhone(staff.getPhone());
		if (staff.getJobPosition() != null)
			ff.setJobPosition(staff.getJobPosition());
		if (staff.getLeadership() != null)
			ff.setLeadership(staff.getLeadership());
		if (staff.getEmail() != null)
			ff.setEmail(staff.getEmail());
		if (staff.getDob() != null)
			ff.setDob(staff.getDob());
		if (staff.getStaffName() != null)
			ff.setName(staff.getStaffName());

		// ff.setTxAccountId(staff.getTxAccountId());---放到激活账户在做

		if (StringUtil.isEmptyString(staff.getStaffId())) {
			// Create a new staff
			ff.setCorporation(nowuser.getCorporation());
			ff.setCreatedBy(nowuser);
			ff.setCreatedAt(DateUtil.getTime());
			ff.setDeleted(0);
			staffDao.save(ff);
		} else {
			ff.setId(staff.getStaffId());
			ff.setLastModifiedAt(DateUtil.getTime());
			ff.setLastModifiedBy(nowuser);

			staffDao.update(ff);
		}
		// 判断用户离职..
		if (staff.getStatus() == StaffStatus.DEPARTURE) {
			SysUser u = userDao.findUserByStaffId(ff.getId());
			if (u != null) {
				u.setStatus(UserStatus.Inactive);
				userDao.update(u);
			}
		} else if (staff.getStatus() == StaffStatus.JOB) {
			SysUser u = userDao.findUserByStaffId(ff.getId());
			if (u != null) {
				u.setStatus(UserStatus.Active);
				userDao.update(u);
			}
		}

		if (staff.getUserRoleVos() != null && staff.getUserRoleVos().size() > 0) {
			SysUser u = userDao.findUserByStaffId(ff.getId());
			if (u != null) {
				// 清除角色(除开用户,部门管理员)
				List<SysUserRole> lt = userRoleDao.findUserRoleByUserId(u
						.getId());
				if (lt.size() > 0) {
					for (SysUserRole r : lt) {
						if (r.getRole().getName() != UserRole.STAFF
								&& r.getRole().getName() != UserRole.DEPT_MGR)
							userRoleDao.delete(r);
					}
				}

				// 创建角色--
				for (UserRole role : staff.getUserRoleVos()) {
					SysUserRole userRole = new SysUserRole();
					userRole.setRole(roleDao.findRoleByRoleName(role));
					userRole.setCreatedBy(nowuser);
					userRole.setCreatedAt(DateUtil.getTime());
					userRole.setLastModifiedAt(DateUtil.getTime());
					userRole.setLastModifiedBy(nowuser);
					userRole.setUser(u);
					userRoleDao.createUserRole(userRole);
				}

			}
		} else if (staff.getUserRoleVos() != null) {
			SysUser u = userDao.findUserByStaffId(ff.getId());
			if (u != null) {
				// 清除角色(除开用户,部门管理员)
				List<SysUserRole> lt = userRoleDao.findUserRoleByUserId(u
						.getId());
				if (lt.size() > 0) {
					for (SysUserRole r : lt) {
						if (r.getRole().getName() != UserRole.STAFF
								&& r.getRole().getName() != UserRole.DEPT_MGR)
							userRoleDao.delete(r);
					}
				}
			}
		}
		return ff.getId();
	}

	@Override
	public Staff findStaffById(String staffId) {
		return staffDao.findById(Staff.class, staffId);
	}

	@Override
	public StaffWinVo findStaffWinReward(StaffWinSearchCriteria criteria) {
		return winnerDao.queryStaffWinRewardPageAction(criteria);
	}

	@Override
	public GeneratedUserConstants generatedUserbyStaff(String staffId,
			UserContext context) {
		if ("ALL".equals(staffId)) {
			List<Staff> list=staffDao.findStaffsByNotUser();
			if(list!=null && list.size()>0)
			{
				for (Staff s:list) {
					generatedUserbyStaffToo(s.getId(),context);
				}
			}
		} else {
			return generatedUserbyStaffToo(staffId, context);
		}
		return GeneratedUserConstants.Success;
	}

	private GeneratedUserConstants generatedUserbyStaffToo(String staffId,
			UserContext context) {
		Staff staff = staffDao.findById(Staff.class, staffId);
		SysUser user = userDao.findUserByStaffId(staff.getId());
		SysUser nowuser = userDao.findUserById(context.getUserId());
		String password = "";
		try {
			password = md5.MD5("123");// 初始密码123
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user != null) {
			return GeneratedUserConstants.UsernamePresence;
		} else {
			// create user
			Date now = DateUtil.getTime();
			SysUser u = new SysUser();
			// check duplicate username
			String username = staff.getEmail().substring(0,
					staff.getEmail().indexOf("@"));
			if (userDao.findUserByUserName(username).size() > 0) {
				return GeneratedUserConstants.UsernameRepeat;
			} else {
				// 创建交易系统ID
				String accountId = transactionService.createNewAccount();
				staff.setTxAccountId(accountId);
				staffDao.update(staff);

				// 创建用户
				u.setUserName(username);
				u.setPassword(password);
				u.setCorporation(staff.getCorporation());
				u.setCreatedAt(now);
				u.setCreatedBy(nowuser);
				u.setLastModifiedAt(now);
				u.setLastModifiedBy(nowuser);
				u.setStatus(UserStatus.Active);
				u.setStaff(staff);
				userDao.save(u);

				// 创建角色---员工

				SysUserRole userRole = new SysUserRole();
				userRole.setRole(roleDao.findRoleByRoleName(UserRole.STAFF));
				userRole.setCreatedBy(nowuser);
				userRole.setCreatedAt(DateUtil.getTime());
				userRole.setLastModifiedAt(DateUtil.getTime());
				userRole.setLastModifiedBy(nowuser);
				userRole.setUser(u);
				userRoleDao.createUserRole(userRole);

			}
		}
		return GeneratedUserConstants.Success;
	}

	public String createHrUser(StaffUserProcess staffProcess) {
		Corporation corporation = corporationLogic
				.findCorporationById(staffProcess.getCorpId());
		// 创建部门为顶级部门
		Department rootdepart = depDao.addRootDepartment(corporation);
		Staff ff = new Staff();
		// 默认当前用户部门为顶级部门Root_
		ff.setDepartment(rootdepart);
		ff.setStatus(StaffStatus.JOB);
		ff.setPhone(staffProcess.getTell());
		ff.setEmail(staffProcess.getEmail());
		ff.setName(staffProcess.getName());
		String accountId = transactionService.createNewAccount();
		ff.setTxAccountId(accountId);
		// Create a new staff

		ff.setCorporation(corporation);
		// ff.setCreatedBy(nowuser);
		ff.setCreatedAt(DateUtil.getTime());
		ff.setDeleted(0);
		Staff newstaff = staffDao.save(ff);

		// ==================创建用户
		String password = "";
		try {
			password = md5.MD5(staffProcess.getPassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// create user
		Date now = DateUtil.getTime();
		SysUser user = new SysUser();
		user.setUserName(staffProcess.getUsername());
		user.setPassword(password);
		user.setCorporation(corporation);
		user.setCreatedAt(now);
		// u.setCreatedBy(nowuser);
		user.setLastModifiedAt(now);
		// u.setLastModifiedBy(nowuser);
		user.setStatus(UserStatus.Active);
		user.setStaff(newstaff);
		user = userDao.save(user);
		// 初始化增加角色表数据
		SysRole role1 = new SysRole();
		role1.setName(UserRole.CORP_ADMIN);
		roleDao.save(role1);
		SysRole role2 = new SysRole();
		role2.setName(UserRole.GIFT);
		roleDao.save(role2);
		SysRole role3 = new SysRole();
		role3.setName(UserRole.DEPT_MGR);
		roleDao.save(role3);
		SysRole role4 = new SysRole();
		role4.setName(UserRole.STAFF);
		roleDao.save(role4);
		// 创建用户角色
		for (int i = 0; i < staffProcess.getRoles().size(); i++) {
			SysUserRole userRole = new SysUserRole();
			userRole.setRole(roleDao.findRoleByRoleName(staffProcess.getRoles()
					.get(i)));
			userRole.setCreatedBy(user);
			userRole.setCreatedAt(DateUtil.getTime());
			userRole.setLastModifiedAt(DateUtil.getTime());
			userRole.setLastModifiedBy(user);
			userRole.setUser(user);
			userRoleDao.createUserRole(userRole);

		}
		return user.getId();
	}

	public Staff updateLeadTime(String staffId, int leadTime) {
		Staff staff = staffDao.findById(Staff.class, staffId);
		staff.setLeadTime(leadTime);
		return staffDao.update(staff);
	}

	@Override
	public List<UserRole> findUserRoles(String staffId) {
		SysUser u = userDao.findUserByStaffId(staffId);
		if (u != null) {
			List<SysUserRole> userRole = userRoleDao.findUserRoleByUserId(u
					.getId());
			if (userRole != null && userRole.size() > 0) {
				List<UserRole> roles = new ArrayList<UserRole>();
				for (SysUserRole r : userRole) {
					roles.add(r.getRole().getName());
				}
				return roles;
			}
		}
		return null;
	}
}
