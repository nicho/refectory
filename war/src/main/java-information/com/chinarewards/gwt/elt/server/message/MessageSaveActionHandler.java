package com.chinarewards.gwt.elt.server.message;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.broadcast.BroadcastingVo;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.broadcast.BroadcastService;
import com.chinarewards.gwt.elt.client.messageSave.request.MessageSaveRequest;
import com.chinarewards.gwt.elt.client.messageSave.request.MessageSaveResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

/**
 * The handler to correspond the MessageSaveRequest.
 * 
 * @author nicho
 * @since 2012年2月20日 18:30:54
 */
public class MessageSaveActionHandler extends
		BaseActionHandler<MessageSaveRequest, MessageSaveResponse> {

	@InjectLogger
	Logger logger;

	BroadcastService broadcastService;


	@Inject
	public MessageSaveActionHandler(BroadcastService broadcastService) {
		this.broadcastService = broadcastService;
	}

	@Override
	public MessageSaveResponse execute(MessageSaveRequest request,
			ExecutionContext response) throws DispatchException {

		MessageSaveResponse staffResponse = new MessageSaveResponse();
		UserContext context=new UserContext();
		context.setCorporationId(request.getSession().getCorporationId());
		context.setUserId(request.getSession().getToken());
		context.setLoginName(request.getSession().getLoginName());
		context.setUserRoles(UserRoleTool.adaptToRole(request.getSession().getUserRoles()));
		
		BroadcastingVo vo=new BroadcastingVo();
		vo.setBroadcastingId(request.getBroadcastId());
		vo.setContent(request.getContent());
		vo.setOrganList(request.getOrganList());
		
		broadcastService.createOrUpdateMessage(vo, context);
		return staffResponse;
	}

	@Override
	public Class<MessageSaveRequest> getActionType() {
		return MessageSaveRequest.class;
	}

	@Override
	public void rollback(MessageSaveRequest request,
			MessageSaveResponse response, ExecutionContext context)
			throws DispatchException {
	}

}
