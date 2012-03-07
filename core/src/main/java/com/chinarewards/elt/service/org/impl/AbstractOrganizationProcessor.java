package com.chinarewards.elt.service.org.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.elt.domain.org.Organization;
import com.chinarewards.elt.model.vo.StaffAndDeptmentAutoCompile;

public abstract class AbstractOrganizationProcessor implements
		OrganizationProcessor {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public StaffAndDeptmentAutoCompile setAutoCompileInfo(
			Organization organization) {
		StaffAndDeptmentAutoCompile res = new StaffAndDeptmentAutoCompile();
		res.setId(organization.getId());
		res.setChooseItemShowName(organization.getName());
		return res;
	}
}
