package com.chinarewards.gwt.elt.server.department;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.org.exception.DepartmentDeleteException;
import com.chinarewards.elt.service.org.DepartmentService;
import com.chinarewards.gwt.elt.client.department.request.DeleteDepartmentRequest;
import com.chinarewards.gwt.elt.client.department.request.DeleteDepartmentResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class DeleteDepartmentHandler extends
		BaseActionHandler<DeleteDepartmentRequest, DeleteDepartmentResponse> {

	@InjectLogger
	Logger logger;

	DepartmentService departmentService;

	@Inject
	public DeleteDepartmentHandler(DepartmentService departmentService) {
		this.departmentService = departmentService;

	}

	@Override
	public DeleteDepartmentResponse execute(DeleteDepartmentRequest request,
			ExecutionContext context) throws DispatchException {

//wating.....最后修改人,最后修改时间
		String totle="";
		try {
			totle = departmentService.deleteDepartment(request.getDepartmentId());
		} catch (DepartmentDeleteException e) {
			e.printStackTrace();
		}
		
		return new DeleteDepartmentResponse(totle);
	}

	
	@Override
	public Class<DeleteDepartmentRequest> getActionType() {
		return DeleteDepartmentRequest.class;
	}

	@Override
	public void rollback(DeleteDepartmentRequest req, DeleteDepartmentResponse resp,
			ExecutionContext cxt) throws DispatchException {
	}

}
