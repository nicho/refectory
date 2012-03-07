package com.chinarewards.elt.service.staff;

import java.util.Date;

import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.staff.StaffProcess;
import com.chinarewards.elt.model.user.GeneratedUserConstants;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.model.user.UserRole;
import com.chinarewards.elt.service.common.JPATestCase;
import com.chinarewards.elt.service.helper.DepartmentHelper;
import com.chinarewards.elt.service.helper.UserHelper;

public class StaffServiceTest extends JPATestCase {
	public void testCreateStaff() {
		// need services
		IStaffService staffService = injector.getInstance(IStaffService.class);
		

		StaffProcess sp=new StaffProcess();
		sp.setStaffNo("001");
		sp.setStaffName("textStaff");
		sp.setDepartmentId(DepartmentHelper.getDefaultDept(injector).getId());
		sp.setPhoto("");
		sp.setPhone("5597025");
		sp.setJobPosition("测试职位");
		sp.setLeadership("直属领导");
		sp.setEmail("textStaff@qq.com");
		sp.setDob(new Date());
		
		SysUser caller = UserHelper.getDefaultUser(injector);
		
		UserContext context=new UserContext();
		context.setCorporationId(DepartmentHelper.getDefaultDept(injector).getCorporation().getId());
		context.setUserId(caller.getId());
		context.setLoginName(caller.getUserName());
		UserRole [] role={UserRole.CORP_ADMIN};
		context.setUserRoles(role);
		
		String staffId=staffService.createOrUpdateStaff(sp, context);
		

		// check staff
		assertNotNull(staffId);

	}
	public void testStaffCreateUser() {
		// need services
		IStaffService staffService = injector.getInstance(IStaffService.class);
		

		StaffProcess sp=new StaffProcess();
		sp.setStaffNo("001");
		sp.setStaffName("textStaff");
		sp.setDepartmentId(DepartmentHelper.getDefaultDept(injector).getId());
		sp.setPhoto("");
		sp.setPhone("5597025");
		sp.setJobPosition("测试职位");
		sp.setLeadership("直属领导");
		sp.setEmail("textStaff@qq.com");
		sp.setDob(new Date());
		
		SysUser caller = UserHelper.getDefaultUser(injector);
		
		UserContext context=new UserContext();
		context.setCorporationId(DepartmentHelper.getDefaultDept(injector).getCorporation().getId());
		context.setUserId(caller.getId());
		context.setLoginName(caller.getUserName());
		UserRole [] role={UserRole.CORP_ADMIN};
		context.setUserRoles(role);
		
		String staffId=staffService.createOrUpdateStaff(sp, context);
		
		GeneratedUserConstants con=staffService.generatedUserbyStaff(staffId, context);

		// check staff
		assertNotNull(staffId);
		assertEquals(con, GeneratedUserConstants.Success);

	}
	

}
