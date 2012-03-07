package com.chinarewards.elt.service.org.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.elt.common.Principal;
import com.chinarewards.elt.common.UserContextProvider;
import com.chinarewards.elt.dao.org.OrganizationDao;
import com.chinarewards.elt.domain.org.Organization;
import com.chinarewards.elt.model.vo.StaffAndDeptmentAutoCompile;
import com.chinarewards.elt.service.org.OrganizationLogic;
import com.chinarewards.elt.util.StringUtil;
import com.google.inject.Inject;

public class OrganizationLogicImpl implements OrganizationLogic {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private final OrganizationDao organizationDao;
	private final Organizationfactory organizationfactory;

	@Inject
	protected OrganizationLogicImpl(OrganizationDao organizationDao,
			Organizationfactory organizationfactory) {
		this.organizationDao = organizationDao;
		this.organizationfactory = organizationfactory;
	}

	public Organization findOrganizationById(String id) {
		logger.debug(" Process in findOrganizationById method, parameter id:"
				+ id);
		Organization organization = organizationDao.findById(
				Organization.class, id);
		if (organization == null
				|| StringUtil.isEmptyString(organization.getId())) {
			logger.debug("find Organization by Id fail Organization.id:" + id);
			throw new IllegalArgumentException(
					"find Organization by Id fail Organization.id:" + id);
		}
		return organization;
	}

	@Override
	public List<StaffAndDeptmentAutoCompile> staffAndDeptmentAutoCompile(
			String corporationId, String falg, int limit) {
		logger.debug(" process in staffAndDeptmentAutoCompile method, parameter name:"
				+ falg + ",limit:" + limit);
		// Principal principal = UserContextProvider.get().getPrincipal();
		// logger.debug(" principal.toString :" + principal.printTheProperty());
		List<StaffAndDeptmentAutoCompile> res = new ArrayList<StaffAndDeptmentAutoCompile>();
		List<String> orgIds = organizationDao
				.findOrganizationBySomePropertyPageAction(corporationId, falg,limit);// 查找出的符合条件的ID
		System.out.println("  orgIds.size : " + orgIds.size());
		if (orgIds.size() != 0) {
			Map<String, Organization> maps = organizationDao
					.findOrganizationByIds(orgIds);
			for (String key : maps.keySet()) {
				Organization organization = maps.get(key);// 得到企业内的组织机构所有的ID
				res.add(organizationfactory.generatorProcessor(organization)
						.setAutoCompileInfo(organization));
			}
		}

		return res;
	}

}
