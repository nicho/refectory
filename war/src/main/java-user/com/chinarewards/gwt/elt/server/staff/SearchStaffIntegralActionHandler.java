package com.chinarewards.gwt.elt.server.staff;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.service.staff.IStaffService;
import com.chinarewards.elt.util.StringUtil;
import com.chinarewards.gwt.elt.client.staffIntegral.request.StaffIntegralRequest;
import com.chinarewards.gwt.elt.client.staffIntegral.request.StaffIntegralResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * The handler to correspond the StaffIntegralRequest.
 * 
 * @author yanrui
 */
public class SearchStaffIntegralActionHandler extends
		BaseActionHandler<StaffIntegralRequest, StaffIntegralResponse> {

	@InjectLogger
	Logger logger;

	IStaffService staffService;

	@Inject
	public SearchStaffIntegralActionHandler(IStaffService staffService) {
		this.staffService = staffService;
	}

	@Override
	public StaffIntegralResponse execute(StaffIntegralRequest request,
			ExecutionContext response) throws DispatchException {
		StaffIntegralResponse staffResponse = new StaffIntegralResponse();
		Staff staff = staffService.findStaffById(request.getStaffId());
		if (staff != null) {
			staffResponse.setStaffId(staff.getId());
			staffResponse.setHistoryIntegral(StringUtil.subZeroAndDot(staff.getHistoryIntegral() + ""));
			staffResponse.setConsumptionIntegral(StringUtil.subZeroAndDot(staff.getConsumptionIntegral()
					+ ""));
			staffResponse.setBalanceIntegral(StringUtil.subZeroAndDot(staffService.getBalance(staff
					.getId()) + ""));
		}
		return staffResponse;
	}

	@Override
	public Class<StaffIntegralRequest> getActionType() {
		return StaffIntegralRequest.class;
	}

	@Override
	public void rollback(StaffIntegralRequest request,
			StaffIntegralResponse response, ExecutionContext context)
			throws DispatchException {
	}

}
