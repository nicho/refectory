package com.chinarewards.gwt.elt.server.broadcastSave;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.broadcast.BroadcastingVo;
import com.chinarewards.elt.model.information.BroadcastingCategory;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.broadcast.BroadcastService;
import com.chinarewards.gwt.elt.client.broadcastSave.request.BroadcastSaveRequest;
import com.chinarewards.gwt.elt.client.broadcastSave.request.BroadcastSaveResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

/**
 * The handler to correspond the BroadcastSaveRequest.
 * 
 * @author nicho
 * @since 2012年2月20日 18:30:54
 */
public class BroadcastingSaveActionHandler extends
		BaseActionHandler<BroadcastSaveRequest, BroadcastSaveResponse> {

	@InjectLogger
	Logger logger;

	BroadcastService broadcastService;


	@Inject
	public BroadcastingSaveActionHandler(BroadcastService broadcastService) {
		this.broadcastService = broadcastService;
	}

	@Override
	public BroadcastSaveResponse execute(BroadcastSaveRequest request,
			ExecutionContext response) throws DispatchException {

		BroadcastSaveResponse staffResponse = new BroadcastSaveResponse();
		UserContext context=new UserContext();
		context.setCorporationId(request.getSession().getCorporationId());
		context.setUserId(request.getSession().getToken());
		context.setLoginName(request.getSession().getLoginName());
		context.setUserRoles(UserRoleTool.adaptToRole(request.getSession().getUserRoles()));
		
		BroadcastingVo vo=new BroadcastingVo();
		vo.setBroadcastingId(request.getBroadcastId());
		vo.setBroadcastingTimeStart(request.getBroadcastingTimeStart());
		vo.setBroadcastingTimeEnd(request.getBroadcastingTimeEnd());
		vo.setAllowreplies(request.isAllowreplies());
		vo.setContent(request.getContent());
		vo.setOrganList(request.getOrganList());
		BroadcastingCategory broadcastingCategory=BroadcastingCategory.COMPANYBROADCAST;
		if(request.getBroadcastingCategory()!=null)
			broadcastingCategory=BroadcastingCategory.valueOf(request.getBroadcastingCategory().toString());
		broadcastService.createOrUpdateBroadcast(vo, context,broadcastingCategory);
		return staffResponse;
	}

	@Override
	public Class<BroadcastSaveRequest> getActionType() {
		return BroadcastSaveRequest.class;
	}

	@Override
	public void rollback(BroadcastSaveRequest request,
			BroadcastSaveResponse response, ExecutionContext context)
			throws DispatchException {
	}

}
