package com.chinarewards.gwt.elt.client.rewardItem.presenter;

import com.chinarewards.gwt.elt.client.mvp.DialogPresenter;
import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.FrequencySettingPresenter.FrequencySettingDisplay;
import com.chinarewards.gwt.elt.client.rewards.model.FrequencyClient;
import com.google.gwt.event.dom.client.HasClickHandlers;

/**
 * @author yanxin
 * @since 0.2.0 2010-12-20
 */
public interface FrequencySettingPresenter extends
		DialogPresenter<FrequencySettingDisplay> {

	public void initFrequency(FrequencyClient frequency, boolean show);

	public void initNewFrequency();

	public static interface FrequencySettingDisplay extends Display {

		public FrequencyClient getFrequency();

		public HasClickHandlers getOkClick();

		public HasClickHandlers getCancelClick();

		/**
		 * Set the active frequency. After calling this method, the display
		 * should update to reflect this change.
		 * 
		 * @param frequency
		 * @param show whether toggle the display to show the proper frequency.
		 */
		public void setFrequency(FrequencyClient frequency, boolean show);

	}
}
