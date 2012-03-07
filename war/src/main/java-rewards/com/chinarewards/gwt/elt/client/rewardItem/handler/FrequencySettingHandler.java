package com.chinarewards.gwt.elt.client.rewardItem.handler;

import com.chinarewards.gwt.elt.client.rewards.model.FrequencyClient;
import com.google.gwt.event.shared.EventHandler;

public interface FrequencySettingHandler extends EventHandler {
	void setting(FrequencyClient frequency);
}
