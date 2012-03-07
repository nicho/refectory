package com.chinarewards.elt.service.user;

import java.util.List;

import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.user.UserRole;
import com.chinarewards.elt.model.user.UserSearchCriteria;
import com.chinarewards.elt.model.user.UserSearchResult;
import com.chinarewards.elt.model.user.UserSessionVo;

public interface UserService {
	public UserSessionVo authenticate(String userName,String pwd);
	public UserSessionVo tokenVaild(String token);
	/**
	 * Search user by paging.
	 * 
	 * @param criteria
	 * @return
	 */
	public UserSearchResult searchHrAdminUserPaging(UserSearchCriteria criteria);
	
	public SysUser findUserById(String id);
	public String deleteUserById(String id);
	public String updateLastLoginRole(String userId,UserRole role);
	
	/**
	 * 重置密码
	 * @param staffId
	 * @param pwd
	 * @return
	 */
	public String updateUserPwd(String staffId,String pwd,String byUserId);
	/**
	 * @param roleName
	 * @param staffId
	 */
	
	public void createUserRole(String roleName, List<String> staffIds);
	/**
	 * @param roleName
	 * @param staffId
	 */
	public void createUserRole(String roleName, String staffId);
	
	
	public void deleteUserRole(String roleName, List<String> staffIds);
	/**
	 * @param roleName
	 * @param staffId
	 */
	public void deleteUserRole(String roleName, String staffId);
	
	/**
	 * 员工天地重置密码
	 * @param staffId
	 * @param pwd
	 * @return
	 */
	public String updateStaffPwd(String staffId,String oldpwd,String pwd,String byUserId);
}
