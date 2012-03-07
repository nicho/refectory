package com.chinarewards.gwt.elt.client.department.presenter;

import java.util.List;

import com.chinarewards.gwt.elt.client.rewards.model.StaffClient;
import com.google.gwt.event.shared.EventHandler;

public interface ChooseLeaderHandler extends EventHandler {
	void chosenLeader(List<StaffClient> list);
}
