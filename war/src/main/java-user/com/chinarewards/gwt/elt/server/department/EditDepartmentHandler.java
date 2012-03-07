package com.chinarewards.gwt.elt.server.department;

import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.domain.org.Department;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.model.user.UserRole;
import com.chinarewards.elt.service.org.CorporationService;
import com.chinarewards.elt.service.org.DepartmentService;
import com.chinarewards.elt.service.user.UserService;
import com.chinarewards.elt.util.StringUtil;
import com.chinarewards.gwt.elt.client.department.model.DepartmentVo;
import com.chinarewards.gwt.elt.client.department.request.EditDepartmentRequest;
import com.chinarewards.gwt.elt.client.department.request.EditDepartmentResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

/**
 * @author YanRui
 * */
public class EditDepartmentHandler extends
		BaseActionHandler<EditDepartmentRequest, EditDepartmentResponse> {

	@InjectLogger
	Logger logger;
	DepartmentService departmentService;
	CorporationService corporationService;
	UserService userService;

	@Inject
	public EditDepartmentHandler(DepartmentService departmentService,CorporationService corporationService,UserService userService) {
		this.departmentService = departmentService;
		this.corporationService=corporationService;
		this.userService=userService;		
	}

	@Override
	public Class<EditDepartmentRequest> getActionType() {
		return EditDepartmentRequest.class;
	}

	@Override
	public EditDepartmentResponse execute(EditDepartmentRequest action,
			ExecutionContext context) throws DispatchException {
		logger.debug("AddDepartmentResponse , department:" + action.getDepartmentVo().toString());
		logger.debug("AddDepartmentResponse ,rewardId=" + action.getDepartmentVo().getId());

		DepartmentVo departmentVo = action.getDepartmentVo();
		List<String> leaderIds=departmentVo.getLeaderIds();
		List<String> preLeaderIds=departmentVo.getPreLeaderIds();
		
		
		Department department = assembleDepartment(departmentVo,action);

		UserContext uc = new UserContext();
		uc.setCorporationId(action.getUserSession().getCorporationId());
		uc.setLoginName(action.getUserSession().getLoginName());
		uc.setUserId(action.getUserSession().getToken());
		uc.setUserRoles(UserRoleTool.adaptToRole(action.getUserSession()
				.getUserRoles()));
		
		
		Department AdddItem = departmentService.saveDepartment(uc, department,leaderIds,preLeaderIds);

	
		userService.deleteUserRole(UserRole.DEPT_MGR.toString(),preLeaderIds);
		userService.createUserRole(UserRole.DEPT_MGR.toString(),leaderIds);

		
		return new EditDepartmentResponse(AdddItem.getId());
	}

	/**
	 * Convert from DepartmentVo to GeneratorDepartmentModel.
	 */
	public Department assembleDepartment(DepartmentVo departmentVo,EditDepartmentRequest action) {
		Department department = new Department();
		department.setId(departmentVo.getId());
		department.setName(departmentVo.getName());
		
		Department parent;
		Corporation corporation;
		if (StringUtil.isEmptyString(departmentVo.getParentId())) {
			String corpId = departmentVo.getCorporationId();
			if(StringUtil.isEmptyString(corpId)){
				corpId=action.getUserSession().getCorporationId();
			}
			parent = departmentService.getRootDepartmentOfCorporation(corpId);			
			corporation = corporationService.findCorporationById(corpId);			
		} else {
			parent = departmentService.findDepartmentById(departmentVo.getParentId());
			corporation = parent.getCorporation();
		}
		department.setParent(parent);
		department.setCorporation(corporation);
		
		
		return department;
	}

	@Override
	public void rollback(EditDepartmentRequest action, EditDepartmentResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
