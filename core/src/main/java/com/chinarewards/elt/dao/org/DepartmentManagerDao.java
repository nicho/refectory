package com.chinarewards.elt.dao.org;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.org.Department;
import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.domain.org.manager.DepartmentManager;

public class DepartmentManagerDao extends BaseDao<DepartmentManager> {

	@SuppressWarnings("unchecked")
	public List<Department> findDepartmentsManagedByStaffId(String staffId) {
		return getEm()
				.createQuery(
						" SELECT dm.department FROM DepartmentManager dm WHERE dm.staff.id=:staffId ")
				.setParameter("staffId", staffId).getResultList();
	}

	public List<DepartmentManager> findDepartmentsManagedByDeptStaffId(
			String deptId, String staffId) {
		return getEm()
				.createQuery(
						" SELECT dm FROM DepartmentManager dm WHERE dm.staff.id=:staffId AND dm.department.id=:deptId ")
				.setParameter("staffId", staffId)
				.setParameter("deptId", deptId).getResultList();
	}

	public List<DepartmentManager> findDepartmentManagesByDeptId(String deptId) {
		return getEm()
				.createQuery(
						" SELECT dm FROM DepartmentManager dm "
								+ " WHERE dm.department.id = :deptId ")
				.setParameter("deptId", deptId).getResultList();
	}

	/**
	 * Find out the department that managed by giving staff. Not include sub
	 * department.
	 * 
	 * @param staffId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> findDepartmentIdsManagedByStaffId(String staffId) {
		logger.debug(
				" process in method findDepartmentIdsManagedByStaffId, staffId:{}",
				staffId);
		return getEm()
				.createQuery(
						" SELECT dm.department.id FROM DepartmentManager dm WHERE dm.staff.id=:staffId ")
				.setParameter("staffId", staffId).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<DepartmentManager> checkStaffIsDepartmentManager(String staffId) {
		return getEm()
				.createQuery(
						" FROM DepartmentManager dm WHERE dm.staff.id=:staffId ")
				.setParameter("staffId", staffId).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Staff> findManagersByDepartmentId(String deptId) {
		return getEm()
				.createQuery(
						" SELECT dm.staff FROM DepartmentManager dm "
								+ " WHERE dm.department.id = :deptId AND dm.staff.deleted =:deleted")
				.setParameter("deptId", deptId).setParameter("deleted", 0)
				.getResultList();
	}

	public int removeManagerFromDepartment(String deptId, List<String> staffs) {
		logger.debug(
				" Process in removeManagerFromDepartment, depart:{}, staffs:{}",
				new Object[] { deptId, staffs });
		return getEm()
				.createQuery(
						" DELETE FROM DepartmentManager dm WHERE dm.department.id = :deptId AND dm.staff.id IN (:staffIds) ")
				.setParameter("deptId", deptId)
				.setParameter("staffIds", staffs).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<String> getStaffIdsWithNoRelationOfDeptManager(
			List<String> staffIds) {
		List<String> result = new ArrayList<String>(
				Arrays.asList(new String[staffIds.size()]));
		Collections.copy(result, staffIds);

		logger.debug(
				" Process in method checkRelationWithDeptManager, staffIds:{}",
				staffIds);
		List<String> existedIds = getEm()
				.createQuery(
						" SELECT dm.staff.id FROM DepartmentManager dm WHERE dm.staff.id IN (:staffIds) ")
				.setParameter("staffIds", staffIds).getResultList();

		result.removeAll(existedIds);

		return result;
	}

	public List<DepartmentManager> createManager(String deptId,
			List<String> staffIds) {
		logger.debug(
				" Process in createManager method, departId:{}, staffs:{}",
				new Object[] { deptId, staffIds });
		List<DepartmentManager> resultList = new ArrayList<DepartmentManager>();
		Department dept = getEm().find(Department.class, deptId);
		for (String staffId : staffIds) {
			List<DepartmentManager> tempManageList = findDepartmentsManagedByDeptStaffId(
					dept.getId(), staffId);
			if (tempManageList != null && tempManageList.size() > 0) {
				// -------------
			} else {
				Staff staff = getEm().find(Staff.class, staffId);
				DepartmentManager dm = new DepartmentManager();
				dm.setDepartment(dept);
				dm.setStaff(staff);
				getEm().persist(dm);
				resultList.add(dm);
			}

		}

		return resultList;
	}

	public void deleteManager(String deptId) {
		List<DepartmentManager> manageList = findDepartmentManagesByDeptId(deptId);
		if (manageList != null) {
			for (int i = 0; i < manageList.size(); i++) {
				DepartmentManager tempManage = manageList.get(i);
				if (tempManage != null) {
					delete(tempManage);
				}
			}
		}

	}

	/**
	 * @param deptId
	 * @param staffIds
	 */
	public void deleteManager(String deptId, List<String> staffIds) {
		Department dept = getEm().find(Department.class, deptId);
		if (staffIds != null) {
			for (String staffId : staffIds) {
				List<DepartmentManager> tempManageList = findDepartmentsManagedByDeptStaffId(
						dept.getId(), staffId);
				if (tempManageList != null && tempManageList.size() > 0) {
					for (int i = 0; i < tempManageList.size(); i++) {
						delete(tempManageList.get(i));
					}
				} else {
					// --------
				}

			}
		}
	}
}
