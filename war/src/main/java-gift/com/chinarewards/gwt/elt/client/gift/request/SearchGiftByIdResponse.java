/**
 * 
 */
package com.chinarewards.gwt.elt.client.gift.request;

import com.chinarewards.gwt.elt.client.gift.model.GiftVo;

import net.customware.gwt.dispatch.shared.Result;

/**
 * @author yanrui
 * @since
 */
public class SearchGiftByIdResponse implements Result {

	private GiftVo giftVo;

	public SearchGiftByIdResponse() {

	}

	public SearchGiftByIdResponse(GiftVo giftVo) {
		this.giftVo = giftVo;
	}

	public GiftVo getGift() {
		return giftVo;
	}

	public void setGift(GiftVo giftVo) {
		this.giftVo = giftVo;
	}

}
