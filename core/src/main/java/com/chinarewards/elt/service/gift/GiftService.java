package com.chinarewards.elt.service.gift;

import com.chinarewards.elt.domain.gift.Gift;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.gift.search.GiftListVo;
import com.chinarewards.elt.model.gift.search.GiftStatus;
import com.chinarewards.elt.model.user.UserContext;

/**
 * Service of corporation.
 * 
 * @author lw
 * @since 1.5
 */
public interface GiftService {

	/**
	 * 保存
	 * @param context
	 * @param gift
	 * @return
	 */
	public Gift save(UserContext context, Gift gift);

	/**
	 * 查找根据ID
	 * @param id
	 * @return
	 */
	public Gift findGiftById(String id);
	/**
	 * 删除礼品根据ID
	 * @param id
	 * @return
	 */
	public String deleteGift(String id);
	/**
	 * 礼品列表
	 * @param context
	 * @param gift
	 * @return
	 */
	public PageStore<GiftListVo> giftList(UserContext context,GiftListVo giftListVo);

	/**
	 * 上下架
	 * @param id
	 * @return
	 */
	public String updateStatus(String id,GiftStatus status);
}
