package com.chinarewards.elt.service.org.impl;

import java.util.List;

import com.chinarewards.elt.dao.org.MembersDao;
import com.chinarewards.elt.domain.org.Members;
import com.chinarewards.elt.domain.org.Organization;
import com.chinarewards.elt.domain.org.Team;
import com.chinarewards.elt.model.vo.StaffAndDeptmentAutoCompile;
import com.google.inject.Inject;

public class TeamProcessor extends AbstractOrganizationProcessor {

	private final MembersDao membersDao;

	@Inject
	public TeamProcessor(MembersDao membersDao) {
		this.membersDao = membersDao;
	}

	@Override
	public StaffAndDeptmentAutoCompile setAutoCompileInfo(Organization organization) {
		logger.debug(" Process in setAutoCompileInfo method, parameter org:"+ organization.toString());
		StaffAndDeptmentAutoCompile res = super	.setAutoCompileInfo(organization);
		Team team = (Team) organization;

		List<Members> stafflist = membersDao.findMemberByTeam(team.getId());//查所有人   

		StringBuffer autoCompileName = new StringBuffer();
		autoCompileName.append(team.getName());
		autoCompileName.append("(" + stafflist.size() + "名员工)");
		res.setAutoCompileName(autoCompileName.toString());
		return res;
	}
}
