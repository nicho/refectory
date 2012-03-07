package com.chinarewards.gwt.elt.server.broadcastSave;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.information.Broadcasting;
import com.chinarewards.elt.domain.information.BroadcastingReceiving;
import com.chinarewards.elt.domain.information.DepartmentObject;
import com.chinarewards.elt.domain.information.OrgObject;
import com.chinarewards.elt.domain.information.StaffObject;
import com.chinarewards.elt.domain.information.TeamObject;
import com.chinarewards.elt.service.broadcast.BroadcastService;
import com.chinarewards.gwt.elt.client.broadcastSave.request.BroadcastUpdateRequest;
import com.chinarewards.gwt.elt.client.broadcastSave.request.BroadcastUpdateResponse;
import com.chinarewards.gwt.elt.client.chooseOrganization.model.OrganSearchCriteria.OrganType;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * The handler to correspond the BroadcastUpdateRequest.
 * 
 * @author nicho
 * @since 2012年2月21日 14:00:40
 */
public class BroadcastUpdateActionHandler extends
		BaseActionHandler<BroadcastUpdateRequest, BroadcastUpdateResponse> {

	@InjectLogger
	Logger logger;

	BroadcastService broadcastService;

	@Inject
	public BroadcastUpdateActionHandler(BroadcastService broadcastService) {
		this.broadcastService = broadcastService;
	}

	@Override
	public BroadcastUpdateResponse execute(BroadcastUpdateRequest request,
			ExecutionContext response) throws DispatchException {

		BroadcastUpdateResponse staffResponse = new BroadcastUpdateResponse();

		Broadcasting vo = broadcastService.findBroadcast2ById(request
				.getBroadcastId());
		staffResponse.setContent(vo.getContent());
		staffResponse.setBroadcastingTimeStart(vo.getBroadcastingTimeStart());
		staffResponse.setBroadcastingTimeEnd(vo.getBroadcastingTimeEnd());
		staffResponse.setAllowreplies(vo.isAllowreplies());

		List<OrganicationClient> lt = new ArrayList<OrganicationClient>();

		List<BroadcastingReceiving> broadcastingList = broadcastService
				.findBroadcastReceiving(request.getBroadcastId());
		if (broadcastingList.size() > 0) {
			for (BroadcastingReceiving cast : broadcastingList) {
				OrganicationClient client = new OrganicationClient();

				client.setName(cast.getReceiving().getName());

				if (cast.getReceiving() instanceof DepartmentObject) {
					client.setId(((DepartmentObject) cast.getReceiving())
							.getDept().getId());
					client.setType(OrganType.DEPT);
				} else if (cast.getReceiving() instanceof OrgObject) {
					client.setId(((OrgObject) cast.getReceiving())
							.getCorporation().getId());
					client.setType(OrganType.ORG);
				}

				else if (cast.getReceiving() instanceof StaffObject) {
					client.setId(((StaffObject) cast.getReceiving()).getStaff()
							.getId());
					client.setType(OrganType.STAFF);
				} else if (cast.getReceiving() instanceof TeamObject) {
					client.setId(((TeamObject) cast.getReceiving()).getTeam()
							.getId());
					client.setType(OrganType.GROUP);
				}

				lt.add(client);
			}
		}
		staffResponse.setReceivingObject(lt);

		return staffResponse;
	}

	@Override
	public Class<BroadcastUpdateRequest> getActionType() {
		return BroadcastUpdateRequest.class;
	}

	@Override
	public void rollback(BroadcastUpdateRequest request,
			BroadcastUpdateResponse response, ExecutionContext context)
			throws DispatchException {
	}

}
