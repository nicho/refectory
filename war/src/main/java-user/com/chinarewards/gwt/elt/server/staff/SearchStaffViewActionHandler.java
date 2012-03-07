package com.chinarewards.gwt.elt.server.staff;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.model.user.UserRole;
import com.chinarewards.elt.service.staff.IStaffService;
import com.chinarewards.gwt.elt.client.staffList.model.StaffListCriteria.StaffStatus;
import com.chinarewards.gwt.elt.client.staffView.request.StaffViewRequest;
import com.chinarewards.gwt.elt.client.staffView.request.StaffViewResponse;
import com.chinarewards.gwt.elt.model.user.UserRoleVo;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * The handler to correspond the StaffViewRequest.
 * 
 * @author nicho
 * @since 2012年2月15日 10:04:40
 */
public class SearchStaffViewActionHandler extends
		BaseActionHandler<StaffViewRequest, StaffViewResponse> {

	@InjectLogger
	Logger logger;

	IStaffService staffService;


	@Inject
	public SearchStaffViewActionHandler(IStaffService staffService) {
		this.staffService = staffService;
	}

	@Override
	public StaffViewResponse execute(StaffViewRequest request,
			ExecutionContext response) throws DispatchException {
		StaffViewResponse staffResponse=new StaffViewResponse();
		Staff staff=staffService.findStaffById(request.getStaffId());
		staffResponse.setStaffId(staff.getId());
		staffResponse.setStaffNo(staff.getJobNo());
		staffResponse.setStaffName(staff.getName());
		if(staff.getLeadTime()!=null)
		   staffResponse.setLeadTime(staff.getLeadTime());//新加的返回的提前颁奖通知时间
		else
			staffResponse.setLeadTime(0);
		if(staff.getDepartment()!=null)
		{
		staffResponse.setDepartmentId(staff.getDepartment().getId());
		staffResponse.setDepartmentName(staff.getDepartment().getName());
		}
		staffResponse.setPhoto(staff.getPhoto());
		staffResponse.setJobPosition(staff.getJobPosition());
		staffResponse.setLeadership(staff.getLeadership());
		staffResponse.setPhone(staff.getPhone());
		staffResponse.setEmail(staff.getEmail());
		staffResponse.setDob(staff.getDob());
		if(staff.getStatus()!=null)
		staffResponse.setStatus(StaffStatus.valueOf(staff.getStatus().toString()));
		
		List<UserRoleVo> userRoleVos=new ArrayList<UserRoleVo>();
		 
		List<UserRole> userRoles=staffService.findUserRoles(staff.getId());
		if(userRoles!=null && userRoles.size()>0)
		{
			for (UserRole r:userRoles) {
				userRoleVos.add(UserRoleVo.valueOf(r.toString()));
			}
		}
		staffResponse.setUserRoleVos(userRoleVos);
		
		return staffResponse;
	}

	@Override
	public Class<StaffViewRequest> getActionType() {
		return StaffViewRequest.class;
	}

	@Override
	public void rollback(StaffViewRequest request,
			StaffViewResponse response, ExecutionContext context)
			throws DispatchException {
	}

}
