package com.chinarewards.gwt.elt.client.rewardItem.event;

import java.util.List;

import com.chinarewards.gwt.elt.client.rewardItem.handler.ChooseStaffHandler;
import com.chinarewards.gwt.elt.client.rewards.model.StaffClient;
import com.google.gwt.event.shared.GwtEvent;

public class ChooseStaffEvent extends GwtEvent<ChooseStaffHandler> {

	private static Type<ChooseStaffHandler> TYPE = new Type<ChooseStaffHandler>();

	List<StaffClient> list;

	public ChooseStaffEvent() {

	}

	public ChooseStaffEvent(List<StaffClient> list) {
		this.list = list;
	}

	public static Type<ChooseStaffHandler> getType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ChooseStaffHandler handler) {
		handler.chosenStaff(list);
	}

	@Override
	public Type<ChooseStaffHandler> getAssociatedType() {
		return getType();
	}
}
