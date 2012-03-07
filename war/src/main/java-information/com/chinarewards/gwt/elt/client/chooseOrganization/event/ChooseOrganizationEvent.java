package com.chinarewards.gwt.elt.client.chooseOrganization.event;

import java.util.List;

import com.chinarewards.gwt.elt.client.chooseOrganization.handler.ChooseOrganizationHandler;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.google.gwt.event.shared.GwtEvent;

public class ChooseOrganizationEvent extends GwtEvent<ChooseOrganizationHandler> {

	private static Type<ChooseOrganizationHandler> TYPE = new Type<ChooseOrganizationHandler>();

	List<OrganicationClient> list;

	public ChooseOrganizationEvent() {

	}

	public ChooseOrganizationEvent(List<OrganicationClient> list) {
		this.list = list;
	}

	public static Type<ChooseOrganizationHandler> getType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ChooseOrganizationHandler handler) {
		handler.chosenOrganization(list);
	}

	@Override
	public Type<ChooseOrganizationHandler> getAssociatedType() {
		return getType();
	}
}
