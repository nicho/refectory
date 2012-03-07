package com.chinarewards.elt.service.gift.impl;

import com.chinarewards.elt.domain.gift.Gift;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.gift.search.GiftListVo;
import com.chinarewards.elt.model.gift.search.GiftStatus;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.gift.GiftLogic;
import com.chinarewards.elt.service.gift.GiftService;
import com.chinarewards.elt.service.user.UserLogic;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
@Transactional
public class GiftServiceImpl implements GiftService {
	private final GiftLogic giftLogic;
	private final UserLogic userLogic;

	@Inject
	public GiftServiceImpl(GiftLogic giftLogic,UserLogic userLogic) {
		this.userLogic = userLogic;
		this.giftLogic = giftLogic;
		
	}
	@Override
	public Gift save(UserContext context, Gift gift) {
		SysUser caller = userLogic.findUserById(context.getUserId());
		Gift gifts = giftLogic.save(caller, gift);
		return gifts;
	}
	

	@Override
	public Gift findGiftById(String id) {
		
		return giftLogic.findGiftById(id);
	}

	@Override
	public String deleteGift(String id) {
		
		return giftLogic.deleteGift(id);
	}

	@Override
	public PageStore<GiftListVo> giftList(UserContext context, GiftListVo giftVo) {
		SysUser caller = userLogic.findUserById(context.getUserId());
		return giftLogic.giftList(caller, giftVo);
	}

	@Override
	public String updateStatus(String id,GiftStatus status) {
		return giftLogic.updateStatus(id,status);
	}

}
