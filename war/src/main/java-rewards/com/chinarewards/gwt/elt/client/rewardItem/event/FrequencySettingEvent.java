package com.chinarewards.gwt.elt.client.rewardItem.event;


import com.chinarewards.gwt.elt.client.rewards.model.FrequencyClient;
import com.google.gwt.event.shared.GwtEvent;
import com.chinarewards.gwt.elt.client.rewardItem.handler.FrequencySettingHandler;
public class FrequencySettingEvent extends GwtEvent<FrequencySettingHandler> {

	private static Type<FrequencySettingHandler> TYPE = new Type<FrequencySettingHandler>();

	FrequencyClient frequency;

	public FrequencySettingEvent() {

	}

	public FrequencySettingEvent(FrequencyClient frequency) {
		this.frequency = frequency;
	}

	public static Type<FrequencySettingHandler> getType() {
		return TYPE;
	}

	@Override
	protected void dispatch(FrequencySettingHandler handler) {
		handler.setting(frequency);
	}

	@Override
	public Type<FrequencySettingHandler> getAssociatedType() {
		return getType();
	}
}
