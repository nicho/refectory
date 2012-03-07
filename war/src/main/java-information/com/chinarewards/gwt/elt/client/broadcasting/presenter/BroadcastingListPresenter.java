package com.chinarewards.gwt.elt.client.broadcasting.presenter;


import java.util.Date;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public interface BroadcastingListPresenter extends Presenter<BroadcastingListPresenter.BroadcastingListDisplay> {
	

	public static interface BroadcastingListDisplay extends Display {

		public HasClickHandlers getSearchBtnClickHandlers();

		public HasClickHandlers getAddBtnClickHandlers();


		void setDataCount(String text);
		void setBreadCrumbs(Widget breadCrumbs);
		
		Panel getResultPanel();
		Panel getResultpage();
		
		void initStatus();
		
		String getStatus();
		String getCreateUser();
		Date getBroadcastingTime();
		Date getBroadcastingTimeEnd();
		

	}
}
