package com.chinarewards.gwt.elt.server.enterprise;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.org.CorporationService;
import com.chinarewards.elt.service.user.UserService;
import com.chinarewards.gwt.elt.client.enterprise.model.EnterpriseVo;
import com.chinarewards.gwt.elt.client.enterprise.request.EditIntegralPriceRequest;
import com.chinarewards.gwt.elt.client.enterprise.request.EditIntegralPriceResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

/**
 * @author YanRui
 * */
public class EditIntegralPriceHandler extends
		BaseActionHandler<EditIntegralPriceRequest, EditIntegralPriceResponse> {

	@InjectLogger
	Logger logger;
	CorporationService corporationService;
	UserService userService;

	@Inject
	public EditIntegralPriceHandler(CorporationService corporationService) {
		this.corporationService = corporationService;
	}

	@Override
	public Class<EditIntegralPriceRequest> getActionType() {
		return EditIntegralPriceRequest.class;
	}

	@Override
	public EditIntegralPriceResponse execute(EditIntegralPriceRequest action,
			ExecutionContext context) throws DispatchException {
		logger.debug("AddIntegralPriceResponse , gift:"
				+ action.getEnterpriseVo().toString());
		logger.debug("AddIntegralPriceResponse ,rewardId="
				+ action.getEnterpriseVo().getId());

		EnterpriseVo enterpriseVo = action.getEnterpriseVo();

		Corporation corporation = assembleIntegralPrice(enterpriseVo);

		UserContext uc = new UserContext();
		uc.setCorporationId(action.getUserSession().getCorporationId());
		uc.setLoginName(action.getUserSession().getLoginName());
		uc.setUserId(action.getUserSession().getToken());
		uc.setUserRoles(UserRoleTool.adaptToRole(action.getUserSession()
				.getUserRoles()));

//		SysUser sysUser = userService.findUserById(uc.getUserId());
		Corporation AdddItem = corporationService.updateIntegralPrice(uc, corporation);
	
		return new EditIntegralPriceResponse(AdddItem.getId());

	}

	public Corporation assembleIntegralPrice(EnterpriseVo enterpriseVo) {
		Corporation corporation = corporationService
				.findCorporationById(enterpriseVo.getId());

		corporation.setIntegralPrice(enterpriseVo.getIntegralPrice());
		corporation.setMoneyType(enterpriseVo.getMoneyType());


		return corporation;
	}
	

	@Override
	public void rollback(EditIntegralPriceRequest action,
			EditIntegralPriceResponse result, ExecutionContext context)
			throws DispatchException {

	}

}
