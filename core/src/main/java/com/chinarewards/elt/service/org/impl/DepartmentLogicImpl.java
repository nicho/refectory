package com.chinarewards.elt.service.org.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.elt.dao.budget.DepartmentBudgetDao;
import com.chinarewards.elt.dao.org.CorporationDao;
import com.chinarewards.elt.dao.org.DepartmentDao;
import com.chinarewards.elt.dao.org.DepartmentManagerDao;
import com.chinarewards.elt.dao.org.OrgPolicyDao;
import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.domain.org.Department;
import com.chinarewards.elt.domain.org.OrgPolicy;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.org.DepartmentPolicyConstants;
import com.chinarewards.elt.model.org.DepartmentVo;
import com.chinarewards.elt.model.org.RewardsApprovalPolicyEnum;
import com.chinarewards.elt.model.org.exception.DepartmentDeleteException;
import com.chinarewards.elt.model.org.search.DepartmentListVo;
import com.chinarewards.elt.model.org.search.DepartmentManageVo;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.org.DepartmentLogic;
import com.chinarewards.elt.util.DateUtil;
import com.chinarewards.elt.util.StringUtil;
import com.google.inject.Inject;

/**
 * The implementation of {@link DepartmentLogic}
 * 
 * @author yanxin
 * @since 1.0
 */
public class DepartmentLogicImpl implements DepartmentLogic {

	Logger logger = LoggerFactory.getLogger(getClass());

	OrgPolicyDao organizationPolicyDao;
	DepartmentDao departmentDao;
	DepartmentManagerDao departmentManagerDao;
	CorporationDao corporationDao;
	DepartmentBudgetDao departmentBudgetDao;

	@Inject
	public DepartmentLogicImpl(OrgPolicyDao organizationPolicyDao,
			DepartmentDao departmentDao,DepartmentManagerDao departmentManagerDao,CorporationDao corporationDao,DepartmentBudgetDao departmentBudgetDao) {
		this.organizationPolicyDao = organizationPolicyDao;
		this.departmentDao = departmentDao;
		this.departmentManagerDao=departmentManagerDao;
		this.corporationDao = corporationDao;
		this.departmentBudgetDao=departmentBudgetDao;
	}

	@Override
	public Department findDepartmentById(String id) {
		return departmentDao.findById(Department.class, id);
	}

	@Override
	public RewardsApprovalPolicyEnum getDepartmentRewardApprovalPolicy(
			String departmentId) {
		String policyKey = DepartmentPolicyConstants.REWARDS_APPROVAL_POLICY;

		// look up the record.
		OrgPolicy policyObj = organizationPolicyDao
				.findByOrganizationIdPolicyKey(departmentId, policyKey);
		logger.debug("policyObj:" + policyObj);
		RewardsApprovalPolicyEnum policy = RewardsApprovalPolicyEnum.SYSTEM_DEFAULT;
		if (policyObj != null) {
			try {
				policy = RewardsApprovalPolicyEnum
						.valueOf(policyObj.getValue());
			} catch (IllegalArgumentException e) {
			} catch (NullPointerException e) {
			}
		}
		logger.debug("policy:" + policy);
		return policy;
	}

	@Override
	public Department saveDepartment(SysUser caller, Department department) {
		Department parent = department.getParent();
		Corporation corporation = department.getCorporation();

		if (parent == null) {
			throw new IllegalArgumentException("Can not find the root parent...");
		}		
		
		int index = parent.getRgt();		
		departmentDao.maintainIndexAfterAddNode(index, corporation.getId());// maintain index

		if (StringUtil.isEmptyString(department.getId())) {	
			department.setLft(parent.getRgt());
			department.setRgt(parent.getRgt() + 1);
			
			department.setCreatedAt(DateUtil.getTime());
			department.setCreatedBy(caller);
			departmentDao.save(department);
		} else {
			Department tempDepartment = departmentDao.findById(Department.class,department.getId());
			
			tempDepartment.setName(department.getName());
			
			tempDepartment.setLastModifiedAt(DateUtil.getTime());
			tempDepartment.setLastModifiedBy(caller);
			departmentDao.update(tempDepartment);
		}
		
		

		return department;
	}
	
	
	
	@Override
	public String mergeDepartment(UserContext uc, String departmentIds,String departmentName,String leaderId) {
		return departmentDao.mergeDepartment(departmentIds,departmentName,leaderId);
	}

	@Override
	public Department addDepartment(SysUser caller, DepartmentVo department) {

		Department parent;
		Corporation corporation;

		if (StringUtil.isEmptyString(department.getParentId())) {
			String corpId = department.getCorporationId();
			corporation = corporationDao.findById(Corporation.class, corpId);
			parent = getRootDepartmentOfCorporation(corpId);
		} else {
			parent = departmentDao.findById(Department.class,
					department.getParentId());
			corporation = parent.getCorporation();
		}

		if (parent == null) {
			throw new IllegalArgumentException("Can not find the root parent.");
		}

		int index = parent.getRgt();
		// maintain index
		departmentDao.maintainIndexAfterAddNode(index, corporation.getId());

		Date now = DateUtil.getTime();
		Department dept = new Department();
		dept.setName(department.getName());
		dept.setDescription(department.getDescription());
		dept.setParent(parent);
		dept.setCorporation(corporation);
		dept.setLft(parent.getRgt());
		dept.setRgt(parent.getRgt() + 1);
		dept.setCreatedAt(now);
		dept.setCreatedBy(caller);
		dept.setLastModifiedAt(now);
		dept.setLastModifiedBy(caller);
		departmentDao.save(dept);

		return dept;
	}

	@Override
	public String deleteDepartment(String deptId)
			throws DepartmentDeleteException {
		Department department = departmentDao
				.findById(Department.class, deptId);
		if (!isLeaf(department)) {
			throw new DepartmentDeleteException(
					"It is not a leaf node, can not delete!");
		}
		int index = department.getLft();
		String corpId = department.getCorporation().getId();
		departmentDao.delete(department);
		
		departmentManagerDao.deleteManager(deptId);

		// maintain index
		departmentDao.maintainIndexAfterDeleteNode(index, corpId);

		return department.getId();
	}

	@Override
	public boolean isLeaf(Department department) {
		return department.getRgt() - department.getLft() == 1 ? true : false;
	}

	@Override
	public Department editDepartment(SysUser caller, String id,
			Department department) {
		Date now = DateUtil.getTime();
		Department dept = departmentDao.findById(Department.class, id);
		dept.setName(department.getName());
		dept.setDescription(department.getDescription());
		dept.setLastModifiedAt(now);
		dept.setLastModifiedBy(caller);
		departmentDao.save(dept);

		return dept;
	}

	@Override
	public List<Department> getImmediacyChildren(String deptId) {

		return departmentDao.findDepartmentsByParentId(deptId);
	}

	@Override
	public List<Department> getWholeChildren(String deptId,
			boolean containItSelf) {
		Department dept = departmentDao.findById(Department.class, deptId);
		logger.debug("Prepare to search by lft={} and rgt={}", new Object[] {
				dept.getLft(), dept.getRgt() });
		List<Department> depts = departmentDao.findDepartmentsByLefRgt(dept.getLft(), dept.getRgt());
		if (containItSelf) {
			depts.add(dept);
		}
		return depts;
	}

	@Override
	public List<String> getWholeChildrenIds(String deptId, boolean containItSelf) {
		List<String> list = new ArrayList<String>();
		List<Department> depts = getWholeChildren(deptId, containItSelf);
		for (Department dept : depts) {
			list.add(dept.getId());
		}
		return list;
	}
	
	@Override
	public List<String> getWholeChildrenNames(String deptId, boolean containItSelf) {
		List<String> list = new ArrayList<String>();
		List<Department> depts = getWholeChildren(deptId, containItSelf);
		for (Department dept : depts) {
			list.add(dept.getName());
		}
		return list;
	}

	@Override
	public List<Department> getImmediacyDepartmentsOfCorporation(
			String corporationId) {
		Department root = getRootDepartmentOfCorporation(corporationId);

		return departmentDao.findDepartmentsByParentIdAndCoporationId(
				root.getId(), corporationId);
	}

	@Override
	public List<Department> getWholeDepartmentsOfCorporation(
			String corporationId) {
		return departmentDao.findDepartmentsByCoporationId(corporationId);
	}

	@Override
	public Department getRootDepartmentOfCorporation(String corpId) {
		Department dept = departmentDao.getRootDepartmentOfCorp(corpId);
		if (dept == null) {
			Corporation corp = corporationDao.findById(Corporation.class,
					corpId);
			dept = departmentDao.addRootDepartment(corp);
		}
		return dept;
	}

	@Override
	public Department save(SysUser caller, Department department) {
		Date currTime = DateUtil.getTime();

		if (StringUtil.isEmptyString(department.getId())) {
			// Create
			// department.setDeleted(false);
			// department.setRecorddate(currTime);
			// department.setStatus(GiftStatus.SHELVES);//新增的是上架的商品
			departmentDao.save(department);
		} else {
			// Update
			Department tempDepartment = departmentDao.findById(
					Department.class, department.getId());
			tempDepartment.setName(department.getName());
//			tempDepartment.setLeaderId(department.getLeaderId());

			departmentDao.update(tempDepartment);
		}

		return department;
	}

	@Override
	public PageStore<DepartmentListVo> departmentList(SysUser caller,
			DepartmentListVo departmentVo) {
		return null;
	}

	@Override
	public List<DepartmentManageVo> getDepartmentManageList(String corpId) {
		List<DepartmentManageVo> volist = new ArrayList<DepartmentManageVo>();
		List<Department> department = getWholeDepartmentsOfCorporation(corpId);
		for (Department dep : department) {
			DepartmentManageVo vo = new DepartmentManageVo();
			vo.setDepartmentId(dep.getId());
			vo.setDepartmentName(dep.getName());
			if (dep.getParent() != null) {
				vo.setParentId(dep.getParent().getId());
			}

			vo.setLeaf(isLeaf(dep));

			// DepartmentBudget budget = departmentBudgetDao
			// .findDepartmentBudgetByDepartmentId(dep.getId());
			// if (budget != null) {
			// vo.setCorpBudgetId(budget.getCorpBudgetId());
			// vo.setBudgetIntegral(budget.getBudgetIntegral());
			// vo.setUseIntegeral(budget.getUseIntegeral());
			// }
			volist.add(vo);
		}

		return volist;
	}

	
	@Override
	public List<DepartmentManageVo> getDepartmentLeaderList(String leaderId,String corporcationId) {
		List<DepartmentManageVo> volist = new ArrayList<DepartmentManageVo>();
		List<Department> departmentList = new ArrayList<Department>();
	
		//Leader负责的部门
		List<Department> tempList=departmentManagerDao.findDepartmentsManagedByStaffId(leaderId);
		departmentList.addAll(tempList);		
		
		for (int i = 0; i <departmentList.size(); i++) {
			Department tempDept=departmentList.get(i);
			List<Department> childList=departmentDao.findDepartmentsByParentId(tempDept.getId());
			departmentList.addAll(childList);	
		}
		
		//Root顶级部门
		departmentList.add(getRootDepartmentOfCorporation(corporcationId));

		
		for (Department dep : departmentList) {
			DepartmentManageVo vo = new DepartmentManageVo();
			vo.setDepartmentId(dep.getId());
			vo.setDepartmentName(dep.getName());
			if (dep.getParent() != null) {
				vo.setParentId(dep.getParent().getId());
			}

			vo.setLeaf(isLeaf(dep));

			// DepartmentBudget budget = departmentBudgetDao
			// .findDepartmentBudgetByDepartmentId(dep.getId());
			// if (budget != null) {
			// vo.setCorpBudgetId(budget.getCorpBudgetId());
			// vo.setBudgetIntegral(budget.getBudgetIntegral());
			// vo.setUseIntegeral(budget.getUseIntegeral());
			// }
			volist.add(vo);
		}

		return volist;
	}



	@Override

	public List<Department> getDepartmentsOfCorporationAndKey(
			String corporationId, String key) {
		return departmentDao.getDepartmentsOfCorporationAndKey(corporationId, key);
	}

}
