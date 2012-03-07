package com.chinarewards.elt.service.helper;

import javax.persistence.EntityManager;

import com.chinarewards.elt.dao.org.DepartmentDao;
import com.chinarewards.elt.dao.org.OrgPolicyDao;
import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.domain.org.Department;
import com.chinarewards.elt.domain.org.OrgPolicy;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.org.DepartmentPolicyConstants;
import com.chinarewards.elt.model.org.DepartmentVo;
import com.chinarewards.elt.model.org.RewardsApprovalPolicyEnum;
import com.chinarewards.elt.service.common.JPATestCase;
import com.chinarewards.elt.service.org.DepartmentLogic;
import com.google.inject.Injector;

/**
 * Help us to get a useful {@link Department}
 * 
 * @author yanxin
 * @since 1.0
 */
public class DepartmentHelper extends JPATestCase{

	private static Department defaultDept = null;

	public static Department getDefaultDept(Injector injector) {
		EntityManager em = injector.getInstance(EntityManager.class);
		if (defaultDept != null
				&& em.find(Department.class, defaultDept.getId()) != null)
			return defaultDept;
		// require some services
		DepartmentLogic departmentLogic = injector
				.getInstance(DepartmentLogic.class);
		DepartmentVo dept = new DepartmentVo();
		dept.setName("IT部");
		dept.setDescription("技术支持的骨干部门,从事软件开发和维护");
		Corporation corp = CorporationHelper.getDefaultCorporation(injector);
		dept.setCorporationId(corp.getId());
		SysUser caller = UserHelper.getDefaultUser(injector);
		defaultDept = departmentLogic.addDepartment(caller, dept);
		System.out.println("lft=" + defaultDept.getLft() + ", rgt="
				+ defaultDept.getRgt());
		return defaultDept;
	}

	/**
	 * Permit the specified department no need to approve.
	 * 
	 * @param deptId
	 * @param injector
	 */
	public static void permitDepartmentNoApproval(String deptId,
			Injector injector) {
		// need some services
		DepartmentDao deptDao = injector.getInstance(DepartmentDao.class);
		OrgPolicyDao orgPolicyDao = injector.getInstance(OrgPolicyDao.class);

		Department dept = deptDao.findById(Department.class, deptId);
		OrgPolicy policy = new OrgPolicy();
		policy.setKey2(DepartmentPolicyConstants.REWARDS_APPROVAL_POLICY);
		policy.setValue(RewardsApprovalPolicyEnum.NOT_REQUIRED.toString());
		policy.setOwner(dept);
		orgPolicyDao.save(policy);
	}
}
