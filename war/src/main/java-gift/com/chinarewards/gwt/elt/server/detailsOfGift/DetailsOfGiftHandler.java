package com.chinarewards.gwt.elt.server.detailsOfGift;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.gift.Gift;
import com.chinarewards.elt.service.gift.GiftService;
import com.chinarewards.gwt.elt.client.detailsOfGift.request.DetailsOfGiftRequest;
import com.chinarewards.gwt.elt.client.detailsOfGift.request.DetailsOfGiftResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月1日 10:51:20
 */
public class DetailsOfGiftHandler extends
		BaseActionHandler<DetailsOfGiftRequest, DetailsOfGiftResponse> {

	@InjectLogger
	Logger logger;

	GiftService giftService;


	@Inject
	public DetailsOfGiftHandler(GiftService giftService) {
		this.giftService = giftService;


	}

	@Override
	public DetailsOfGiftResponse execute(DetailsOfGiftRequest request,
			ExecutionContext context) throws DispatchException {

		DetailsOfGiftResponse resp = new DetailsOfGiftResponse();
		Gift gift=giftService.findGiftById(request.getGiftId());		
		resp.setGiftName(gift.getName());
		resp.setGiftNo(gift.getId());
		resp.setBrand(gift.getBrand());
		resp.setType(gift.getType());
		resp.setStock(gift.getStock()+"");
		resp.setIntegral(gift.getIntegral()+"");
		resp.setSummary(gift.getSummary());
		resp.setExplains(gift.getExplains());
		resp.setNotes(gift.getNotes());
		resp.setDispatchcycle(gift.getDispatchcycle());
		resp.setBusiness(gift.getBusiness());
		resp.setServicetell(gift.getServicetell());
		resp.setGiftPhoto(gift.getPhoto());
		return resp;
	}

	
	 
	@Override
	public Class<DetailsOfGiftRequest> getActionType() {
		return DetailsOfGiftRequest.class;
	}

	@Override
	public void rollback(DetailsOfGiftRequest req, DetailsOfGiftResponse resp,
			ExecutionContext cxt) throws DispatchException {
	}

}
