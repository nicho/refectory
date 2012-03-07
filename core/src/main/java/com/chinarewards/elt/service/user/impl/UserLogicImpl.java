package com.chinarewards.elt.service.user.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chinarewards.elt.dao.org.CorporationDao;
import com.chinarewards.elt.dao.org.StaffDao;
import com.chinarewards.elt.dao.user.RoleDao;
import com.chinarewards.elt.dao.user.UserDao;
import com.chinarewards.elt.dao.user.UserRoleDao;
import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.domain.user.SysUserRole;
import com.chinarewards.elt.model.user.SearchUserInfo;
import com.chinarewards.elt.model.user.UserRole;
import com.chinarewards.elt.model.user.UserSearchCriteria;
import com.chinarewards.elt.model.user.UserSearchResult;
import com.chinarewards.elt.model.user.UserSessionVo;
import com.chinarewards.elt.model.user.UserStatus;
import com.chinarewards.elt.model.user.UserVo;
import com.chinarewards.elt.service.staff.StaffLogic;
import com.chinarewards.elt.service.user.UserLogic;
import com.chinarewards.elt.util.DateUtil;
import com.chinarewards.elt.util.MD5;
import com.google.inject.Inject;

/**
 * The implementation of {@link UserLogic}
 * 
 * @author yanxin
 * @since 1.0
 */
public class UserLogicImpl implements UserLogic {

	/**
	 * You should ensure only one user use this name.
	 */
	public static final String DEFAULT_NAME = "_damon_4076124377";

	UserDao userDao;
	StaffDao staffDao;
	CorporationDao corporationDao;
	RoleDao roleDao;
	UserRoleDao userRoleDao;
	StaffLogic staffLogic;
    MD5 md5 =new  MD5();
	@Inject
	public UserLogicImpl(UserDao userDao, CorporationDao corporationDao,RoleDao roleDao,
			UserRoleDao userRoleDao, StaffDao staffDao,StaffLogic staffLogic) {
		this.userDao = userDao;
		this.corporationDao = corporationDao;
		this.roleDao=roleDao;
		this.userRoleDao = userRoleDao;
		this.staffDao = staffDao;
		this.staffLogic=staffLogic;
	}

	@Override
	public SysUser getDefaultUser() {
		List<SysUser> users = userDao.findUserByUserName(DEFAULT_NAME);
		SysUser user;
		if (users.isEmpty()) {
			// Create a new user.
			Date now = DateUtil.getTime();
			user = new SysUser();
			user.setUserName(DEFAULT_NAME);
			user.setStatus(UserStatus.Inactive);
			user.setCreatedAt(now);
			user.setLastModifiedAt(now);
			userDao.save(user);
		} else {
			user = users.get(0);
		}

		return user;
	}

	@Override
	public SysUser getDefaultUserByStaff(Staff staff) {
		List<SysUser> users = userDao.findUserByUserName(DEFAULT_NAME);
		SysUser user;
		if (users.isEmpty()) {
			// Create a new user.
			Date now = DateUtil.getTime();
			user = new SysUser();
			user.setUserName(DEFAULT_NAME);
			user.setStatus(UserStatus.Inactive);
			user.setCreatedAt(now);
			user.setLastModifiedAt(now);
			user.setStaff(staff);
			userDao.save(user);
		} else {
			user = users.get(0);
		}

		return user;
	}

	@Override
	public SysUser createUser(SysUser caller, UserVo user) {
		Date now = DateUtil.getTime();
		SysUser u = new SysUser();
		String password ="";
		try {
			 password = md5.MD5(user.getPassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		u.setUserName(user.getUsername());
		u.setPassword(password);
		Corporation corp = corporationDao.findById(Corporation.class,user.getCorporationId());
		u.setCorporation(corp);
		u.setCreatedAt(now);
		u.setCreatedBy(caller);
		u.setLastModifiedAt(now);
		u.setLastModifiedBy(caller);
		u.setStatus(UserStatus.Active);
		if (user.getStaffId() != null && user.getStaffId() != ""
				&& !"".equals(user.getStaffId())) {
			Staff staff = staffDao.findById(Staff.class, user.getStaffId());
			u.setStaff(staff);
		}
		userDao.save(u);
		return u;
	}

	@Override
	public SysUser findUserById(String id) {
		return userDao.findById(SysUser.class, id);
	}

	@Override
	public UserSessionVo findUserByNameAndPwd(String userName, String pwd) {
		 String password="";
		 try {
			password = md5.MD5(pwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SysUser user = userDao.findUserByNameAndPwd(userName, password);
		if (user != null)
			return findUserRolebySysUser(user);
		else
			return null;
	}

	@Override
	public UserSessionVo tokenVaild(String token) {
		try {
			userDao.getEm().refresh(SysUser.class);
		} catch (Exception e) {
			System.out.println("not refresh");
		}
		
		SysUser user = userDao.findById(SysUser.class, token);
		if (user != null)
			return findUserRolebySysUser(user);
		else
			return null;
	}

	private UserSessionVo findUserRolebySysUser(SysUser user) {
		UserSessionVo vo = new UserSessionVo();
		List<SysUserRole> listRole = userRoleDao.findUserRoleByUserId(user
				.getId());
		List<UserRole> userRoles = new ArrayList<UserRole>();
		if (listRole != null && listRole.size() > 0) {
			for (SysUserRole role : listRole) {
				userRoles.add(role.getRole().getName());
			}
		}

		vo.setId(user.getId());
		vo.setCorporationId(user.getCorporation().getId());
		vo.setCorporationName(user.getCorporation().getName());
		vo.setPhoto(user.getStaff().getPhoto());
		vo.setUsername(user.getUserName());
		vo.setUserRoles(userRoles);
		if(user.getStaff().getDepartment()!=null)
		vo.setDepartmentId(user.getStaff().getDepartment().getId());
		vo.setStaffId(user.getStaff().getId());
		vo.setLastLoginRole(user.getLastLoginRole());
		vo.setUserStatus(user.getStatus());
		
		return vo;
	}

	@Override
	public UserSearchResult searchHrAdminUserPaging(UserSearchCriteria criteria) {

		UserSearchResult result = new UserSearchResult();
		List<SysUser> hrUsers = userDao.searchHrAdminUserByCriteria(criteria);
		List<SearchUserInfo> userInfos = new ArrayList<SearchUserInfo>();
		
		for (SysUser user : hrUsers) {
			SearchUserInfo info = new SearchUserInfo();
			info.setUser(user);
			info.setBalance(staffLogic.getBalance(user.getStaff().getId()));
			info.setEnterpriseName(user.getCorporation().getName());
			userInfos.add(info);
		}
		result.setResult(userInfos);
		result.setTotal(userDao.countHrAdminUserByCriteria(criteria));
		return result;
	}

	@Override
	public String deleteUserById(String id) {
		SysUser user=userDao.findById(SysUser.class, id);
		user.setStatus(UserStatus.Inactive);
		Staff staff=user.getStaff();
		staff.setDeleted(1);
		staffDao.update(staff);
		userDao.update(user);
		return "success";
	}

	@Override
	public String updateLastLoginRole(String userId, UserRole role) {
		SysUser user=userDao.findById(SysUser.class, userId);
		user.setLastLoginRole(role);
		userDao.update(user);
		return "success";
	}

	@Override
	public String updateUserPwd(String staffId, String pwd,String byUserId) {
		String password ="";
		try {
			 password = md5.MD5(pwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SysUser user=userDao.findUserByStaffId(staffId);
		SysUser nowUser=userDao.findUserById(byUserId);
		user.setLastModifiedAt(DateUtil.getTime());
		user.setLastModifiedBy(nowUser);
		user.setPassword(password);
		userDao.update(user);
		return "success";
	}
	
	@Override
	public void createUserRole(String roleName,List<String> staffIds){
		if(staffIds!=null){
			for (int i = 0; i < staffIds.size(); i++) {
				String staffId=staffIds.get(i);
				createUserRole(roleName, staffId);
			}
		}		
	}
	
	@Override
	public void deleteUserRole(String roleName,List<String> staffIds){
		if(staffIds!=null){
			for (int i = 0; i < staffIds.size(); i++) {
				String staffId=staffIds.get(i);
				createUserRole(roleName, staffId);
			}
		}		
	}
	
	@Override
	public void createUserRole(String roleName,String staffId){
		SysUser user=userDao.findUserByStaffId(staffId);
		if(user!=null)
		{
		List<SysUserRole> userRoleList=userRoleDao.findUserRoleByUserId(user.getId());
		
		SysUserRole existUserRole=null;
		if(userRoleList!=null && userRoleList.size()>0)
		{
			for (int j = 0; j < userRoleList.size(); j++) {
				SysUserRole tempUserRole=userRoleList.get(j);
				if(tempUserRole.getRole().getName().equals(UserRole.valueOf(roleName))){
					existUserRole=tempUserRole;
					break;
				}
			}
		}
		if (existUserRole==null) {
		
			SysUserRole userRole = new SysUserRole();
			userRole.setRole(roleDao.findRoleByRoleName(UserRole.valueOf(roleName)));
			userRole.setCreatedBy(user);
			userRole.setCreatedAt(DateUtil.getTime());
			userRole.setLastModifiedAt(DateUtil.getTime());
			userRole.setLastModifiedBy(user);
			userRole.setUser(user);
			userRoleDao.createUserRole(userRole);	
		}
		}
	}
	
	
	
	
	@Override
	public void deleteUserRole(String roleName,String staffId){
		List<SysUserRole> userRoleList=userRoleDao.findUserRoleByUserId(staffId);
		
		SysUserRole existUserRole=null;
		for (int j = 0; j < userRoleList.size(); j++) {
			SysUserRole tempUserRole=userRoleList.get(j);
			if(tempUserRole.getRole().getName().equals(UserRole.valueOf(roleName))){
				existUserRole=tempUserRole;
				break;
			}
		}
		
		if (existUserRole!=null) {
			userRoleDao.delete(existUserRole);
		} else {
		
		}
	}
	@Override
	public String updateStaffPwd(String staffId,String oldpwd, String pwd,String byUserId) {
		String newpassword ="";
		String oldpassword ="";
		try {
			newpassword = md5.MD5(pwd);
			oldpassword = md5.MD5(oldpwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SysUser user=userDao.findUserByStaffId(staffId);
		
		SysUser nowUser=userDao.findUserById(byUserId);
		user.setLastModifiedAt(DateUtil.getTime());
		user.setLastModifiedBy(nowUser);
		user.setPassword(newpassword);
		if(user.getPassword().trim().equals(oldpassword.trim())){
		    userDao.update(user);
		    return "success";
		}else{
			return "faile";
		}
			
	}
}
