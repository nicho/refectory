package com.chinarewards.gwt.elt.client.staffHeavenIndex.presenter;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Panel;

public interface StaffHeavenIndexPresenter extends
		Presenter<StaffHeavenIndexPresenter.StaffHeavenIndexDisplay> {

	public static interface StaffHeavenIndexDisplay extends Display {

		void setDataCount(String text);
		void setTopBroadcast(String text);

		Panel getResultPanel();

		Panel getResultpage();

		Anchor getAllInformation();
		Anchor getStaffInformation();
		Anchor getSysInformation();
		Anchor getThemeInformation();
		Anchor getCloseMessageBtn();
		Anchor getQuietlyInformation();
		
		void setTopMessage(String text);
		
		String getBroadcastContent();
		boolean getMoot();
		HasClickHandlers getAddBroadcastBtn();
		
		void successClean();
	}
}
