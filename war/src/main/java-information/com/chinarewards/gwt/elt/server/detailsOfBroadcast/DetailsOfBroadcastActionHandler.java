package com.chinarewards.gwt.elt.server.detailsOfBroadcast;

import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.information.BroadcastingReceiving;
import com.chinarewards.elt.model.broadcast.BroadcastAndReplyQueryListVo;
import com.chinarewards.elt.service.broadcast.BroadcastService;
import com.chinarewards.gwt.elt.client.detailsOfBroadcast.request.DetailsOfBroadcastRequest;
import com.chinarewards.gwt.elt.client.detailsOfBroadcast.request.DetailsOfBroadcastResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.DateTool;
import com.google.inject.Inject;

/**
 * The handler to correspond the DetailsOfBroadcastRequest.
 * 
 * @author nicho
 * @since 2012年2月21日 14:00:40
 */
public class DetailsOfBroadcastActionHandler
		extends
		BaseActionHandler<DetailsOfBroadcastRequest, DetailsOfBroadcastResponse> {

	@InjectLogger
	Logger logger;

	BroadcastService broadcastService;

	@Inject
	public DetailsOfBroadcastActionHandler(BroadcastService broadcastService) {
		this.broadcastService = broadcastService;
	}

	@Override
	public DetailsOfBroadcastResponse execute(
			DetailsOfBroadcastRequest request, ExecutionContext response)
			throws DispatchException {

		DetailsOfBroadcastResponse staffResponse = new DetailsOfBroadcastResponse();

		BroadcastAndReplyQueryListVo vo = broadcastService
				.findBroadcastById(request.getBroadcastId());
		staffResponse.setContent(vo.getBroadcasting().getContent());
		staffResponse.setBroadcastingTime(DateTool.dateToString(vo
				.getBroadcasting().getBroadcastingTimeStart())
				+ " - "
				+ DateTool.dateToString(vo.getBroadcasting()
						.getBroadcastingTimeEnd()));
		if (vo.getBroadcasting().isAllowreplies())
			staffResponse.setAllowreplies("允许回复");
		else
			staffResponse.setAllowreplies("不允许回复");
		
		String receivingobject="";
		List<BroadcastingReceiving> broadcastingList=broadcastService.findBroadcastReceiving(request.getBroadcastId());
		if (broadcastingList.size() > 0) {
			for (BroadcastingReceiving cast : broadcastingList) {
				receivingobject+=cast.getReceiving().getName()+";";
			}
		}
		staffResponse.setReceivingObject(receivingobject);
		staffResponse.setCreateUser(vo.getBroadcasting().getCreatedBy().getStaff().getName());
		return staffResponse;
	}

	@Override
	public Class<DetailsOfBroadcastRequest> getActionType() {
		return DetailsOfBroadcastRequest.class;
	}

	@Override
	public void rollback(DetailsOfBroadcastRequest request,
			DetailsOfBroadcastResponse response, ExecutionContext context)
			throws DispatchException {
	}

}
