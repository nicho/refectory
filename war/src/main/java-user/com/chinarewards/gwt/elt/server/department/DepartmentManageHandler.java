package com.chinarewards.gwt.elt.server.department;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.org.search.DepartmentManageVo;
import com.chinarewards.elt.service.org.DepartmentService;
import com.chinarewards.gwt.elt.client.department.model.DepartmentNode;
import com.chinarewards.gwt.elt.client.department.request.DepartmentManageRequest;
import com.chinarewards.gwt.elt.client.department.request.DepartmentManageResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * @author yanrui
 * */
public class DepartmentManageHandler extends
		BaseActionHandler<DepartmentManageRequest, DepartmentManageResponse> {

	@InjectLogger
	Logger logger;

	DepartmentService departmentService;

	@Inject
	public DepartmentManageHandler(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@Override
	public DepartmentManageResponse execute(DepartmentManageRequest action,
			ExecutionContext context) throws DispatchException {
		List<DepartmentNode> rs = new ArrayList<DepartmentNode>();
		List<DepartmentManageVo> departmentManageVoList = departmentService
				.getDepartmentManageList(action.getCorporationId());
		for (DepartmentManageVo vo : departmentManageVoList) {
			DepartmentNode c = new DepartmentNode(vo.getDepartmentName(),
					(int) vo.getBudgetIntegral() + "",
					(int) vo.getBudgetIntegral() + "", vo.getDepartmentId(),
					vo.isLeaf(), vo.getParentId());
			rs.add(c);
		}
		return new DepartmentManageResponse(rs);

	}

	@Override
	public Class<DepartmentManageRequest> getActionType() {
		return DepartmentManageRequest.class;
	}

	@Override
	public void rollback(DepartmentManageRequest arg0,
			DepartmentManageResponse arg1, ExecutionContext arg2)
			throws DispatchException {

	}

}
