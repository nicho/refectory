package com.chinarewards.gwt.elt.server.register;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.org.OrgInit;
import com.chinarewards.elt.model.user.UserRole;
import com.chinarewards.elt.service.org.OrgInitService;
import com.chinarewards.elt.service.staff.IStaffService;
import com.chinarewards.gwt.elt.client.register.model.OrgInitVo;
import com.chinarewards.gwt.elt.client.registerHr.request.RegisterHrRequest;
import com.chinarewards.gwt.elt.client.registerHr.request.RegisterHrResponse;
import com.chinarewards.gwt.elt.model.staff.StaffUserProcess;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * The handler to correspond the RegisterHrRequest.
 * 
 * @author nicho
 * @since 2011年12月7日 09:41:42
 */
public class RegisterHrActionHandler extends
		BaseActionHandler<RegisterHrRequest, RegisterHrResponse> {

	@InjectLogger
	Logger logger;
	OrgInitService orgInitService;
	IStaffService staffService;


	@Inject
	public RegisterHrActionHandler(IStaffService staffService,OrgInitService orgInitService) {
		this.staffService = staffService;
		this.orgInitService = orgInitService;
	}

	@Override
	public RegisterHrResponse execute(RegisterHrRequest request,
			ExecutionContext response) throws DispatchException {

		RegisterHrResponse hrResponse = new RegisterHrResponse();
		StaffUserProcess process = new StaffUserProcess();
		process.setUsername(request.getHrvo().getUsername());
		process.setName(request.getHrvo().getName());
		process.setEmail(request.getHrvo().getEmail());
		process.setPassword(request.getHrvo().getPassword());
		process.setTell(request.getHrvo().getTell());
		//process.setDeptId(request.getHrvo().getDeptId());
        process.setCorpId(request.getHrvo().getCorpId());
		List<UserRole> roles=new ArrayList<UserRole>();
		for (int i = 0; i < request.getHrvo().getUserRoleVos().size(); i++) {
			roles.add(UserRole.valueOf(request.getHrvo().getUserRoleVos().get(i).toString()));
		}
		process.setRoles(roles);
		 OrgInit init=  orgInitService.getOrgInit();
		  init.setCorpInit(1);
		  init.setHrInit(1);
		 orgInitService.save(init);
		process.setCorpId(init.getCorpId());
		String userId=staffService.createHrUser(process);
		hrResponse.setUserId(userId);
		return hrResponse;
	}

	@Override
	public Class<RegisterHrRequest> getActionType() {
		return RegisterHrRequest.class;
	}

	@Override
	public void rollback(RegisterHrRequest request,
			RegisterHrResponse response, ExecutionContext context)
			throws DispatchException {
	}

}
