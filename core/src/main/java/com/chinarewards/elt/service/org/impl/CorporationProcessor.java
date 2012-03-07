package com.chinarewards.elt.service.org.impl;


import com.chinarewards.elt.domain.org.Organization;
import com.chinarewards.elt.model.vo.StaffAndDeptmentAutoCompile;

public class CorporationProcessor extends AbstractOrganizationProcessor {

	public StaffAndDeptmentAutoCompile setAutoCompileInfo(
			Organization organization) {
		throw new UnsupportedOperationException(
				" this case is must Staff of Department,not allow corporation ");
	}

}
