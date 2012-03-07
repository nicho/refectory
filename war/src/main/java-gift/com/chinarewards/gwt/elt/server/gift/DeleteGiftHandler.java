package com.chinarewards.gwt.elt.server.gift;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.service.gift.GiftService;
import com.chinarewards.gwt.elt.client.gift.request.DeleteGiftRequest;
import com.chinarewards.gwt.elt.client.gift.request.DeleteGiftResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年1月13日 10:02:37
 */
public class DeleteGiftHandler extends
		BaseActionHandler<DeleteGiftRequest, DeleteGiftResponse> {

	@InjectLogger
	Logger logger;

	GiftService giftService;

	@Inject
	public DeleteGiftHandler(GiftService giftService) {
		this.giftService = giftService;

	}

	@Override
	public DeleteGiftResponse execute(DeleteGiftRequest request,
			ExecutionContext context) throws DispatchException {

//wating.....最后修改人,最后修改时间
		String totle = giftService.deleteGift(request.getGiftId());
		
		return new DeleteGiftResponse(totle);
	}

	
	@Override
	public Class<DeleteGiftRequest> getActionType() {
		return DeleteGiftRequest.class;
	}

	@Override
	public void rollback(DeleteGiftRequest req, DeleteGiftResponse resp,
			ExecutionContext cxt) throws DispatchException {
	}

}
