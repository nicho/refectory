package com.chinarewards.gwt.elt.server.broadcastReply;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.broadcast.BroadcastService;
import com.chinarewards.gwt.elt.client.broadcastReply.request.BroadcastReplyAddRequest;
import com.chinarewards.gwt.elt.client.broadcastReply.request.BroadcastReplyAddResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

/**
 * The handler to correspond the BroadcastReplyAddRequest.
 * 
 * @author nicho
 * @since 2011年12月7日 09:41:42
 */
public class BroadcastReplyAddActionHandler extends
		BaseActionHandler<BroadcastReplyAddRequest, BroadcastReplyAddResponse> {

	@InjectLogger
	Logger logger;

	BroadcastService broadcastService;

	@Inject
	public BroadcastReplyAddActionHandler(BroadcastService broadcastService) {
		this.broadcastService = broadcastService;
	}

	@Override
	public BroadcastReplyAddResponse execute(BroadcastReplyAddRequest request,
			ExecutionContext response) throws DispatchException {

		BroadcastReplyAddResponse staffResponse = new BroadcastReplyAddResponse();

		UserContext context = new UserContext();
		context.setCorporationId(request.getSession().getCorporationId());
		context.setUserId(request.getSession().getToken());
		context.setLoginName(request.getSession().getLoginName());
		context.setUserRoles(UserRoleTool.adaptToRole(request.getSession()
				.getUserRoles()));
		broadcastService.saveBroadcastReply(request.getBroadcastId(), request.getReplyContent(), context,request.getReplyParentId());
		
		return staffResponse;
	}

	@Override
	public Class<BroadcastReplyAddRequest> getActionType() {
		return BroadcastReplyAddRequest.class;
	}

	@Override
	public void rollback(BroadcastReplyAddRequest request,
			BroadcastReplyAddResponse response, ExecutionContext context)
			throws DispatchException {
	}

}
