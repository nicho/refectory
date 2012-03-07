package com.chinarewards.gwt.elt.client.detailsOfGift.model;

import java.io.Serializable;

public class DetailsOfGiftClient implements Serializable,
		Comparable<DetailsOfGiftClient> {

	private static final long serialVersionUID = 4934837755724342679L;

	private String giftId;
	public DetailsOfGiftClient() {

	}

	public DetailsOfGiftClient(String giftId) {
		this.giftId = giftId;
	}
	public String getGiftId() {
		return giftId;
	}

	public void setGiftId(String giftId) {
		this.giftId = giftId;
	}

	@Override
	public int compareTo(DetailsOfGiftClient o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
