package com.chinarewards.gwt.elt.server.department;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.org.search.DepartmentManageVo;
import com.chinarewards.elt.service.org.DepartmentService;
import com.chinarewards.gwt.elt.client.department.model.DepartmentNode;
import com.chinarewards.gwt.elt.client.department.request.DepartmentLeaderRequest;
import com.chinarewards.gwt.elt.client.department.request.DepartmentLeaderResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

public class DepartmentLeaderHandler extends
		BaseActionHandler<DepartmentLeaderRequest, DepartmentLeaderResponse> {

	@InjectLogger
	Logger logger;

	DepartmentService departmentService;

	@Inject
	public DepartmentLeaderHandler(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@Override
	public DepartmentLeaderResponse execute(DepartmentLeaderRequest action,
			ExecutionContext context) throws DispatchException {
		List<DepartmentNode> nodeList = new ArrayList<DepartmentNode>();

		List<DepartmentManageVo> departmentManageVoList = departmentService
				.getDepartmentLeaderList(action.getLeaderId(),
						action.getCorporcationId());

//		System.out
//				.println(action.getLeaderId()
//						+ "=====after====departmentService.getDepartmentLeaderList======="
//						+ departmentManageVoList.size());

		for (DepartmentManageVo vo : departmentManageVoList) {
			DepartmentNode c = new DepartmentNode(vo.getDepartmentName(),
					(int) vo.getBudgetIntegral() + "",
					(int) vo.getBudgetIntegral() + "", vo.getDepartmentId(),
					vo.isLeaf(), vo.getParentId());
			nodeList.add(c);
		}

		return new DepartmentLeaderResponse(nodeList);

	}

	@Override
	public Class<DepartmentLeaderRequest> getActionType() {
		return DepartmentLeaderRequest.class;
	}

	@Override
	public void rollback(DepartmentLeaderRequest arg0,
			DepartmentLeaderResponse arg1, ExecutionContext arg2)
			throws DispatchException {

	}

}
