package com.chinarewards.gwt.elt.client.detailsOfBroadcast.presenter;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public interface DetailsOfBroadcastPresenter extends
		Presenter<DetailsOfBroadcastPresenter.DetailsOfBroadcastDisplay> {

	public void initBroadcastDetails(String broadcastId);

	public static interface DetailsOfBroadcastDisplay extends Display {

		public HasClickHandlers getUpdateBtnClickHandlers();

		void setBreadCrumbs(Widget breadCrumbs);

		void setContent(String text);
		void setReceivingObject(String text);
		void setBroadcastingTime(String text);
		void setAllowreplies(String text);
		void setCreateUser(String text);
		
		
		void setDataCount(String text);
		Panel getResultPanel();
		Panel getResultpage();
		
		InlineLabel getDataCount();
	}
}
