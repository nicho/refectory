package com.chinarewards.gwt.elt.server.enterprise;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.org.CorporationService;
import com.chinarewards.elt.service.user.UserService;
import com.chinarewards.gwt.elt.client.enterprise.model.EnterpriseVo;
import com.chinarewards.gwt.elt.client.enterprise.request.EditPeriodRequest;
import com.chinarewards.gwt.elt.client.enterprise.request.EditPeriodResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

/**
 * @author YanRui
 * */
public class EditPeriodHandler extends
		BaseActionHandler<EditPeriodRequest, EditPeriodResponse> {

	@InjectLogger
	Logger logger;
	CorporationService corporationService;
	UserService userService;

	@Inject
	public EditPeriodHandler(CorporationService corporationService) {
		this.corporationService = corporationService;
	}

	@Override
	public Class<EditPeriodRequest> getActionType() {
		return EditPeriodRequest.class;
	}

	@Override
	public EditPeriodResponse execute(EditPeriodRequest action,
			ExecutionContext context) throws DispatchException {
		logger.debug("AddPeriodResponse , gift:"
				+ action.getEnterpriseVo().toString());
		logger.debug("AddPeriodResponse ,rewardId="
				+ action.getEnterpriseVo().getId());

		EnterpriseVo enterpriseVo = action.getEnterpriseVo();

		Corporation corporation = assemblePeriod(enterpriseVo);

		UserContext uc = new UserContext();
		uc.setCorporationId(action.getUserSession().getCorporationId());
		uc.setLoginName(action.getUserSession().getLoginName());
		uc.setUserId(action.getUserSession().getToken());
		uc.setUserRoles(UserRoleTool.adaptToRole(action.getUserSession()
				.getUserRoles()));

//		SysUser sysUser = userService.findUserById(uc.getUserId());
		Corporation AdddItem = corporationService.updatePeriod(uc, corporation);
	
		return new EditPeriodResponse(AdddItem.getId());

	}

	public Corporation assemblePeriod(EnterpriseVo enterpriseVo) {
		Corporation corporation = corporationService
				.findCorporationById(enterpriseVo.getId());

		corporation.setPeriod(enterpriseVo.getPeriod());
		corporation.setFirstTime(enterpriseVo.getFirstTime());
		
//		System.out.println("----------------------"+enterpriseVo.getId()+"----"+enterpriseVo.getPeriod()+"--"+enterpriseVo.getFirstTime());

		return corporation;
	}
	

	@Override
	public void rollback(EditPeriodRequest action,
			EditPeriodResponse result, ExecutionContext context)
			throws DispatchException {

	}

}
