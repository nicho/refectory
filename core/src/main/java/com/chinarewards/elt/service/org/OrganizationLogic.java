package com.chinarewards.elt.service.org;

import java.util.List;

import com.chinarewards.elt.domain.org.Organization;
import com.chinarewards.elt.model.vo.StaffAndDeptmentAutoCompile;

public interface OrganizationLogic {

	public Organization findOrganizationById(String id);
	
	public List<StaffAndDeptmentAutoCompile> staffAndDeptmentAutoCompile(String corporationId,String falg, int limit);
}
