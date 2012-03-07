package com.chinarewards.gwt.elt.client.department.presenter;

import java.util.List;

import com.chinarewards.gwt.elt.client.rewards.model.StaffClient;
import com.google.gwt.event.shared.GwtEvent;

public class ChooseLeaderEvent extends GwtEvent<ChooseLeaderHandler> {

	private static Type<ChooseLeaderHandler> TYPE = new Type<ChooseLeaderHandler>();

	List<StaffClient> list;

	public ChooseLeaderEvent() {

	}

	public ChooseLeaderEvent(List<StaffClient> list) {
		this.list = list;
	}

	public static Type<ChooseLeaderHandler> getType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ChooseLeaderHandler handler) {
		handler.chosenLeader(list);
	}

	@Override
	public Type<ChooseLeaderHandler> getAssociatedType() {
		return getType();
	}
}
