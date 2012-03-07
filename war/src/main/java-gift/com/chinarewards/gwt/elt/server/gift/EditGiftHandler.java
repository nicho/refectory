package com.chinarewards.gwt.elt.server.gift;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;
import org.slf4j.Logger;
import com.chinarewards.elt.domain.gift.Gift;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.gift.GiftService;
import com.chinarewards.gwt.elt.client.gift.model.GiftVo;
import com.chinarewards.gwt.elt.client.gift.request.EditGiftRequest;
import com.chinarewards.gwt.elt.client.gift.request.EditGiftResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

/**
 * @author YanRui
 * */
public class EditGiftHandler extends
		BaseActionHandler<EditGiftRequest, EditGiftResponse> {

	@InjectLogger
	Logger logger;
	GiftService giftService;

	@Inject
	public EditGiftHandler(GiftService giftService) {
		this.giftService = giftService;
	}

	@Override
	public Class<EditGiftRequest> getActionType() {
		return EditGiftRequest.class;
	}

	@Override
	public EditGiftResponse execute(EditGiftRequest action,
			ExecutionContext context) throws DispatchException {
		logger.debug("AddGiftResponse , gift:" + action.getGiftVo().toString());
		logger.debug("AddGiftResponse ,rewardId=" + action.getGiftVo().getId());

		GiftVo giftVo = action.getGiftVo();

		Gift gift = assembleGift(giftVo);

		UserContext uc = new UserContext();
		uc.setCorporationId(action.getUserSession().getCorporationId());
		uc.setLoginName(action.getUserSession().getLoginName());
		uc.setUserId(action.getUserSession().getToken());
		uc.setUserRoles(UserRoleTool.adaptToRole(action.getUserSession()
				.getUserRoles()));
		Gift AdddItem = giftService.save(uc, gift);

		return new EditGiftResponse(AdddItem.getId());
	}

	/**
	 * Convert from GiftVo to GeneratorGiftModel.
	 */
	public static Gift assembleGift(GiftVo giftVo) {
		Gift gift = new Gift();
		gift.setId(giftVo.getId());
		gift.setName(giftVo.getName());

		gift.setSummary(giftVo.getSummary());
		gift.setDispatchcycle(giftVo.getDispatchcycle());
		gift.setNotes(giftVo.getNotes());

		gift.setExplains(giftVo.getExplains());
		gift.setType(giftVo.getType().trim());
		gift.setBrand(giftVo.getBrand().trim());
		gift.setSource(giftVo.getSource());
		System.out.println("assembleGift(giftVo):" + giftVo.getSummary());

		gift.setPhoto(giftVo.getPhoto());
		gift.setIntegral(giftVo.getIntegral());
		gift.setStock(giftVo.getStock());

		gift.setBusiness(giftVo.getBusiness().trim());
		gift.setAddress(giftVo.getAddress().trim());
		gift.setTell(giftVo.getTell().trim());
		gift.setServicetell(giftVo.getServicetell().trim());
		
		gift.setIndate(giftVo.getIndate());

		// gift.setGiftStatus();
		// gift.setIndate(getIndate());
		// private GiftStatus status; //状态（上下架）
		// private boolean deleted; //删除状态
		// private Date indate ; //有效截止期

		return gift;
	}

	@Override
	public void rollback(EditGiftRequest action, EditGiftResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
