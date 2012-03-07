package com.chinarewards.elt.service.org.impl;

import com.chinarewards.elt.domain.org.OrgInit;
import com.chinarewards.elt.domain.org.Team;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.org.OrgInitLogic;
import com.chinarewards.elt.service.org.OrgInitService;
import com.chinarewards.elt.service.user.UserLogic;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
@Transactional
public class OrgInitServiceImpl implements OrgInitService {
	private final OrgInitLogic orgInitLogic;
	

	@Inject
	public OrgInitServiceImpl(OrgInitLogic orgInitLogic) {
		this.orgInitLogic = orgInitLogic;
		
	}


	@Override
	public OrgInit save(OrgInit init) {
		// TODO Auto-generated method stub
		return orgInitLogic.save(init);
	}


	@Override
	public OrgInit getOrgInit() {
		// TODO Auto-generated method stub
		return orgInitLogic.getOrgInit();
	}
	
	
	
	
	

}
