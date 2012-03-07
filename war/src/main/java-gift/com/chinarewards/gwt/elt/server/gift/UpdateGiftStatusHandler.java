package com.chinarewards.gwt.elt.server.gift;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.gift.search.GiftStatus;
import com.chinarewards.elt.service.gift.GiftService;
import com.chinarewards.gwt.elt.client.gift.request.UpdateGiftStatusRequest;
import com.chinarewards.gwt.elt.client.gift.request.UpdateGiftStatusResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年1月13日 10:02:37
 */
public class UpdateGiftStatusHandler extends
		BaseActionHandler<UpdateGiftStatusRequest, UpdateGiftStatusResponse> {

	@InjectLogger
	Logger logger;

	GiftService giftService;

	@Inject
	public UpdateGiftStatusHandler(GiftService giftService) {
		this.giftService = giftService;

	}

	@Override
	public UpdateGiftStatusResponse execute(UpdateGiftStatusRequest request,
			ExecutionContext context) throws DispatchException {

//wating.....最后修改人,最后修改时间
		GiftStatus updateStatus=null;
		if(GiftStatus.valueOf(request.getStatus().toString())==GiftStatus.SHELF)
			updateStatus=GiftStatus.SHELVES;
		else if(GiftStatus.valueOf(request.getStatus().toString())==GiftStatus.SHELVES)
			updateStatus=GiftStatus.SHELF;
		String totle = giftService.updateStatus(request.getGiftId(),updateStatus);
		
		return new UpdateGiftStatusResponse(totle);
	}

	
	@Override
	public Class<UpdateGiftStatusRequest> getActionType() {
		return UpdateGiftStatusRequest.class;
	}

	@Override
	public void rollback(UpdateGiftStatusRequest req, UpdateGiftStatusResponse resp,
			ExecutionContext cxt) throws DispatchException {
	}

}
