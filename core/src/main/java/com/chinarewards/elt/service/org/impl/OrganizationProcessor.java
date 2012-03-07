package com.chinarewards.elt.service.org.impl;

import com.chinarewards.elt.domain.org.Organization;
import com.chinarewards.elt.model.vo.StaffAndDeptmentAutoCompile;

public interface OrganizationProcessor {

	public StaffAndDeptmentAutoCompile setAutoCompileInfo(
			Organization organization);
}
