package com.chinarewards.gwt.elt.client.rewardItem.handler;

import java.util.List;

import com.chinarewards.gwt.elt.client.rewards.model.StaffClient;
import com.google.gwt.event.shared.EventHandler;

public interface ChooseStaffHandler extends EventHandler {
	void chosenStaff(List<StaffClient> list);
}
