package com.chinarewards.elt.dao.user;

import java.util.List;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.user.SysUserRole;
import com.chinarewards.elt.model.user.UserRole;

public class UserRoleDao extends BaseDao<SysUserRole> {

	@SuppressWarnings("unchecked")
	public List<SysUserRole> findUserRoleByUserId(String userId) {
		return getEm()
				.createQuery("FROM SysUserRole u WHERE u.user.id = :userId")
				.setParameter("userId", userId).getResultList();
	}
	
	public String createUserRole(SysUserRole userRole) {
		this.save(userRole);	
		return userRole.getId();
	}

	@SuppressWarnings("unchecked")
	public List<String> findStaffIdsByRole(UserRole UserRoleName) {
		return getEm()
				.createQuery("SELECT u.user.staff.id FROM SysUserRole u WHERE u.role.name = :UserRoleName")
				.setParameter("UserRoleName", UserRoleName).getResultList();
	}

	
}
