package com.chinarewards.elt.service.org;

import java.util.List;

import com.chinarewards.elt.domain.org.Department;
import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.org.exception.DepartmentDeleteException;
import com.chinarewards.elt.model.org.search.DepartmentListVo;
import com.chinarewards.elt.model.org.search.DepartmentManageVo;
import com.chinarewards.elt.model.user.UserContext;

/**
 * Service of corporation.
 * 
 * @author yanrui
 * @since 1.5
 */
public interface DepartmentService {

	/**
	 * 保存
	 * @param context
	 * @param department
	 * @return
	 */
	public Department save(UserContext context, Department department);

	/**
	 * 查找根据ID
	 * @param id
	 * @return
	 */
	public Department findDepartmentById(String id);
	/**
	 * 删除部门根据ID
	 * @param id
	 * @return
	 * @throws DepartmentDeleteException 
	 */
	public String deleteDepartment(String id) throws DepartmentDeleteException;
	/**
	 * 部门列表
	 * @param context
	 * @param department
	 * @return
	 */
	public PageStore<DepartmentListVo> departmentList(UserContext context,DepartmentListVo departmentListVo);

	/**
	 * @param corporationId
	 * @return
	 */
	public List<DepartmentManageVo> getDepartmentManageList(String corporationId);
	
	public List<DepartmentManageVo> getDepartmentLeaderList(String leaderId,String corporcationId);

	/**
	 * @param uc
	 * @param department
	 * @return
	 */
	public Department saveDepartment(UserContext uc, Department department,List<String> staffIds,List<String> preLeaderIds);

	/**
	 * @param corpId
	 * @return
	 */
	public Department getRootDepartmentOfCorporation(String corpId);

	/**
	 * @param uc
	 * @param departmentIds
	 * @return
	 */
	public String mergeDepartment(UserContext uc, String departmentIds,String departmentName,String leaderId);

	/**
	 *获取部门.查询.key(name).去掉根部门
	 * 
	 * @param corporationId
	 * @return
	 */
	public List<Department> getDepartmentsOfCorporationAndKey(String corporationId,String key);

	
	/**
	 * @param deptId
	 * @param containItSelf
	 * @return
	 */
	public List<Department> getWholeChildren(String deptId, boolean containItSelf);

	/**
	 * @param deptId
	 * @param containItSelf
	 * @return
	 */
	public List<String> getWholeChildrenNames(String deptId, boolean containItSelf);


	public List<Staff> findManagersByDepartmentId(String deptId);
	
	public List<Department> findDepartmentsManagedByStaffId(String staffId);

	/**
	 * @param corporationId
	 * @return
	 */
	public List<Department> getWholeDepartmentsOfCorporation(String corporationId);

}
