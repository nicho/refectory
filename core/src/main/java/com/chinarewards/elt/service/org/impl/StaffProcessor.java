package com.chinarewards.elt.service.org.impl;

import com.chinarewards.elt.domain.org.Organization;
import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.model.vo.StaffAndDeptmentAutoCompile;
import com.chinarewards.elt.util.StringUtil;

public class StaffProcessor extends AbstractOrganizationProcessor {

	public StaffAndDeptmentAutoCompile setAutoCompileInfo(
			Organization organization) {
		logger.debug(" Process in setAutoCompileInfo method, parameter org:"
				+ organization.toString());
		StaffAndDeptmentAutoCompile res = super
				.setAutoCompileInfo(organization);
		Staff staff = (Staff) organization;
		StringBuffer autoCompileName = new StringBuffer();
		autoCompileName.append(staff.getName());
		if (staff.getDepartment() != null) {
			autoCompileName.append("(" + staff.getDepartment().getName() + ")");
		}
		if (!StringUtil.isEmptyString(staff.getEmail())) {
			autoCompileName.append("<" + staff.getEmail() + ">");
		}
		logger.debug(" set autoCompileName value:" + autoCompileName);
		res.setAutoCompileName(autoCompileName.toString());
		return res;
	}
}
