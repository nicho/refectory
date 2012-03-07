package com.chinarewards.elt.service.org.impl;

import java.util.List;

import com.chinarewards.elt.domain.org.Department;
import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.org.exception.DepartmentDeleteException;
import com.chinarewards.elt.model.org.search.DepartmentListVo;
import com.chinarewards.elt.model.org.search.DepartmentManageVo;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.org.DepartmentLogic;
import com.chinarewards.elt.service.org.DepartmentManagerLogic;
import com.chinarewards.elt.service.org.DepartmentService;
import com.chinarewards.elt.service.user.UserLogic;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	private final DepartmentLogic departmentLogic;
	private final DepartmentManagerLogic departmentManagerLogic;
	private final UserLogic userLogic;

	@Inject
	public DepartmentServiceImpl(DepartmentLogic departmentLogic,DepartmentManagerLogic departmentManagerLogic,
			UserLogic userLogic) {
		this.userLogic = userLogic;
		this.departmentLogic = departmentLogic;
		this.departmentManagerLogic=departmentManagerLogic;

	}

	@Override
	public Department save(UserContext context, Department department) {
		SysUser caller = userLogic.findUserById(context.getUserId());
		Department departments = departmentLogic.save(caller, department);
		return departments;
	}

	@Override
	public Department findDepartmentById(String id) {
		return departmentLogic.findDepartmentById(id);
	}

	@Override
	public String deleteDepartment(String id) throws DepartmentDeleteException {
		return departmentLogic.deleteDepartment(id);
	}

	@Override
	public PageStore<DepartmentListVo> departmentList(UserContext context,
			DepartmentListVo departmentVo) {
		SysUser caller = userLogic.findUserById(context.getUserId());
		return departmentLogic.departmentList(caller, departmentVo);
	}

	@Override
	public List<DepartmentManageVo> getDepartmentManageList(String corporationId) {
		return departmentLogic.getDepartmentManageList(corporationId);
	}

	@Override
	public Department saveDepartment(UserContext uc, Department department,List<String> staffIds,List<String> preLeaderIds) {
		SysUser caller = userLogic.findUserById(uc.getUserId());
		department= departmentLogic.saveDepartment(caller, department);
		
		
		departmentManagerLogic.deleteManager(department.getId(), preLeaderIds);
		departmentManagerLogic.createManager(department.getId(), staffIds);
		
		
		
		return department;
	}

	@Override
	public Department getRootDepartmentOfCorporation(String corpId) {
		return departmentLogic.getRootDepartmentOfCorporation(corpId);
	}

	@Override
	public String mergeDepartment(UserContext uc, String departmentIds,String departmentName,String leaderId) {
		return departmentLogic.mergeDepartment(uc, departmentIds,departmentName,leaderId);
	}

	@Override
	public List<DepartmentManageVo> getDepartmentLeaderList(String leaderId,String corporcationId) {
		return departmentLogic.getDepartmentLeaderList(leaderId,corporcationId);
	}


	@Override
	public List<Department> getWholeChildren(String deptId,
			boolean containItSelf){
		return departmentLogic.getWholeChildren(deptId, containItSelf);
	}
	
	@Override
	public List<Department> getWholeDepartmentsOfCorporation(
			String corporationId){
		return departmentLogic.getWholeDepartmentsOfCorporation(corporationId);
	}
	
	@Override
	public List<String> getWholeChildrenNames(String deptId, boolean containItSelf) {
		return departmentLogic.getWholeChildrenNames(deptId, containItSelf);
	}

	@Override
	public List<Department> getDepartmentsOfCorporationAndKey(String corporationId,String key)
	{
		return departmentLogic.getDepartmentsOfCorporationAndKey(corporationId, key);
	}
	
	@Override
	public List<Staff> findManagersByDepartmentId(String deptId) {
		return departmentManagerLogic.findManagersByDepartmentId(deptId);
	}

	@Override
	public List<Department> findDepartmentsManagedByStaffId(String staffId) {
		return departmentManagerLogic.findDepartmentsManagedByStaffId(staffId);
	}
}
