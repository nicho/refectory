package com.chinarewards.gwt.elt.server.staff;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.service.staff.IStaffService;
import com.chinarewards.gwt.elt.client.staff.model.StaffVo;
import com.chinarewards.gwt.elt.client.staff.request.LeadTimeRequest;
import com.chinarewards.gwt.elt.client.staff.request.LeadTimeResponse;
import com.chinarewards.gwt.elt.model.staff.StaffUserProcess;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * The handler to correspond the LeadTimeRequest.
 * 
 * @author lw
 * @since 2012年2月27日 09:41:42
 */
public class LeadTimeActionHandler extends
		BaseActionHandler<LeadTimeRequest, LeadTimeResponse> {

	@InjectLogger
	Logger logger;

	IStaffService staffService;


	@Inject
	public LeadTimeActionHandler(IStaffService staffService) {
		this.staffService = staffService;
	}

	@Override
	public LeadTimeResponse execute(LeadTimeRequest request,
			ExecutionContext response) throws DispatchException {

		LeadTimeResponse leadTimeResponse = new LeadTimeResponse();
	
		StaffVo staffVo = new StaffVo();
		Staff staff =staffService.updateLeadTime(request.getStaffId(),request.getLeadTime());
		staffVo.setId(staff.getId());
		staffVo.setLeadTime(staff.getLeadTime());
		
		leadTimeResponse.setStaffVo(staffVo);
		return leadTimeResponse;
	}

	@Override
	public Class<LeadTimeRequest> getActionType() {
		return LeadTimeRequest.class;
	}

	@Override
	public void rollback(LeadTimeRequest request,
			LeadTimeResponse response, ExecutionContext context)
			throws DispatchException {
	}

}
