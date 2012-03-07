package com.chinarewards.elt.service.org.impl;

import com.chinarewards.elt.dao.org.StaffDao;
import com.chinarewards.elt.dao.org.StaffDao.QueryStaffPageActionResult;
import com.chinarewards.elt.domain.org.Department;
import com.chinarewards.elt.domain.org.Organization;
import com.chinarewards.elt.model.user.DeleteMarkConstant;
import com.chinarewards.elt.model.vo.StaffAndDeptmentAutoCompile;
import com.chinarewards.elt.model.vo.StaffSearchVo;
import com.google.inject.Inject;

public class DeptmentProcessor extends AbstractOrganizationProcessor {

	private final StaffDao staffDao;

	@Inject
	public DeptmentProcessor(StaffDao staffDao) {
		this.staffDao = staffDao;
	}

	@Override
	public StaffAndDeptmentAutoCompile setAutoCompileInfo(Organization organization) {
		logger.debug(" Process in setAutoCompileInfo method, parameter org:"+ organization.toString());
		StaffAndDeptmentAutoCompile res = super	.setAutoCompileInfo(organization);
		Department department = (Department) organization;

		// count staff number
		StaffSearchVo searchVo = new StaffSearchVo();
		searchVo.setDeptParam(new StaffSearchVo.OneIdParam(department.getId(),true));
		QueryStaffPageActionResult result = staffDao.queryStaffPageAction(searchVo);

		StringBuffer autoCompileName = new StringBuffer();
		autoCompileName.append(department.getName());
		autoCompileName.append("(" + result.getTotal() + "名员工)");
		res.setAutoCompileName(autoCompileName.toString());
		return res;
	}
}
