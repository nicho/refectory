/**
 * 
 */
package com.chinarewards.elt.guice.sub;

import com.chinarewards.elt.dao.org.CorporationDao;
import com.chinarewards.elt.dao.org.DepartmentDao;
import com.chinarewards.elt.dao.org.DepartmentManagerDao;
import com.chinarewards.elt.dao.org.IndustryDao;
import com.chinarewards.elt.dao.org.OrganizationDao;
import com.chinarewards.elt.service.org.CorporationLogic;
import com.chinarewards.elt.service.org.CorporationService;
import com.chinarewards.elt.service.org.DepartmentLogic;
import com.chinarewards.elt.service.org.DepartmentManagerLogic;
import com.chinarewards.elt.service.org.DepartmentService;
import com.chinarewards.elt.service.org.OrgInitLogic;
import com.chinarewards.elt.service.org.OrgInitService;
import com.chinarewards.elt.service.org.OrganizationLogic;
import com.chinarewards.elt.service.org.impl.CorporationLogicImpl;
import com.chinarewards.elt.service.org.impl.CorporationProcessor;
import com.chinarewards.elt.service.org.impl.CorporationServiceImpl;
import com.chinarewards.elt.service.org.impl.DepartmentLogicImpl;
import com.chinarewards.elt.service.org.impl.DepartmentManagerLogicImpl;
import com.chinarewards.elt.service.org.impl.DepartmentServiceImpl;
import com.chinarewards.elt.service.org.impl.DeptmentProcessor;
import com.chinarewards.elt.service.org.impl.OrgInitLogicImpl;
import com.chinarewards.elt.service.org.impl.OrgInitServiceImpl;
import com.chinarewards.elt.service.org.impl.OrganizationLogicImpl;
import com.chinarewards.elt.service.org.impl.OrganizationProcessor;
import com.chinarewards.elt.service.org.impl.Organizationfactory;
import com.chinarewards.elt.service.org.impl.OrganizationfactoryImpl;
import com.chinarewards.elt.service.org.impl.StaffProcessor;
import com.chinarewards.elt.service.org.impl.TeamProcessor;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

/**
 * Configure of organization module.
 * 
 * @author yanxin
 * @since 1.0
 */
public class OrgModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(DepartmentDao.class).in(Singleton.class);

		bind(OrganizationDao.class).in(Singleton.class);
	
		bind(CorporationDao.class).in(Singleton.class);
		bind(IndustryDao.class).in(Singleton.class);
		bind(DepartmentManagerDao.class).in(Singleton.class);
		bind(CorporationLogic.class).to(CorporationLogicImpl.class).in(
				Singleton.class);
		bind(CorporationService.class).to(CorporationServiceImpl.class).in(
				Singleton.class);
		bind(OrganizationLogic.class).to(OrganizationLogicImpl.class).in(
				Singleton.class);
		
		bind(DepartmentLogic.class).to(DepartmentLogicImpl.class);
		bind(DepartmentService.class).to(DepartmentServiceImpl.class);
		
		bind(OrgInitLogic.class).to(OrgInitLogicImpl.class);
		bind(OrgInitService.class).to(OrgInitServiceImpl.class);
		
		bind(Organizationfactory.class).to(OrganizationfactoryImpl.class).in(Singleton.class);
		bind(OrganizationProcessor.class).annotatedWith(Names.named("StaffProcessor")).to(StaffProcessor.class);
		bind(OrganizationProcessor.class).annotatedWith(Names.named("DeptmentProcessor")).to(DeptmentProcessor.class);
		bind(OrganizationProcessor.class).annotatedWith(Names.named("CorporationProcessor")).to(CorporationProcessor.class);
		bind(OrganizationProcessor.class).annotatedWith(Names.named("TeamProcessor")).to(TeamProcessor.class);
		bind(DepartmentManagerLogic.class).to(DepartmentManagerLogicImpl.class).in(Singleton.class);
		
	
	}

}
