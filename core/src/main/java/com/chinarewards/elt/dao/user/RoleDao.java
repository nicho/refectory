package com.chinarewards.elt.dao.user;

import java.util.List;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.user.SysRole;
import com.chinarewards.elt.model.user.UserRole;

public class RoleDao extends BaseDao<SysRole> {


	@SuppressWarnings("unchecked")
	public SysRole findRoleByRoleName(UserRole roleName) {
		
		List<SysRole> rolelt=getEm().createQuery("FROM SysRole u WHERE u.name = :roleName").setParameter("roleName", roleName).getResultList();
		if(rolelt.size()>0)
		return rolelt.get(0);
		else
		return null;
	}
	


	

	
}
