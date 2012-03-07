package com.chinarewards.gwt.elt.client.broadcastReply.presenter;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Widget;

public interface BroadcastReplyPresenter extends
		Presenter<BroadcastReplyPresenter.BroadcastReplyDisplay> {

	public void initBroadcast(String broadcastId);

	public static interface BroadcastReplyDisplay extends Display {

		public HasClickHandlers getSaveBtnClickHandlers();

		void setBreadCrumbs(Widget breadCrumbs);

		
		String getContent();

	
		 
	}
}
