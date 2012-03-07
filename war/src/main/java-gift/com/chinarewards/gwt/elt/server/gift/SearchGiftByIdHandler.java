package com.chinarewards.gwt.elt.server.gift;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;
import org.slf4j.Logger;
import com.chinarewards.elt.domain.gift.Gift;
import com.chinarewards.elt.service.gift.GiftService;
import com.chinarewards.gwt.elt.client.gift.model.GiftVo;
import com.chinarewards.gwt.elt.client.gift.request.SearchGiftByIdRequest;
import com.chinarewards.gwt.elt.client.gift.request.SearchGiftByIdResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class SearchGiftByIdHandler extends
		BaseActionHandler<SearchGiftByIdRequest, SearchGiftByIdResponse> {
	@InjectLogger
	Logger logger;
	GiftService giftService;

	@Inject
	public SearchGiftByIdHandler(GiftService giftService) {
		this.giftService = giftService;
	}

	@Override
	public SearchGiftByIdResponse execute(SearchGiftByIdRequest request,
			ExecutionContext context) throws DispatchException {
		logger.debug(" parameters:{}", request.getId());
		Gift gift = giftService.findGiftById(request.getId());
		return new SearchGiftByIdResponse(adapter(gift));

	}

	private GiftVo adapter(Gift gift) {
		GiftVo giftVo = new GiftVo();
		giftVo.setId(gift.getId());
		giftVo.setName(gift.getName());
		giftVo.setSummary(gift.getSummary());
		giftVo.setDispatchcycle(gift.getDispatchcycle());
		giftVo.setExplains(gift.getExplains());
		giftVo.setNotes(gift.getNotes());
		giftVo.setType(gift.getType());
		giftVo.setBrand(gift.getBrand());
		giftVo.setSource(gift.getSource());
		giftVo.setBusiness(gift.getBusiness());
		giftVo.setAddress(gift.getAddress());
		giftVo.setTell(gift.getTell());
		giftVo.setServicetell(gift.getServicetell());
		giftVo.setPhoto(gift.getPhoto());
		giftVo.setStock(gift.getStock());
		giftVo.setIntegral(gift.getIntegral());
		giftVo.setPhoto(gift.getPhoto());
		giftVo.setIndate(gift.getIndate());

		return giftVo;
	}

	@Override
	public Class<SearchGiftByIdRequest> getActionType() {
		return SearchGiftByIdRequest.class;
	}

	@Override
	public void rollback(SearchGiftByIdRequest arg0,
			SearchGiftByIdResponse arg1, ExecutionContext arg2)
			throws DispatchException {
	}

}
