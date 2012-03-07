package com.chinarewards.gwt.elt.client.chooseOrganization.handler;

import java.util.List;

import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.google.gwt.event.shared.EventHandler;

public interface ChooseOrganizationHandler extends EventHandler {
	void chosenOrganization(List<OrganicationClient> list);
}
