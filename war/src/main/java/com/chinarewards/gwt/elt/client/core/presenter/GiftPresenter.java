package com.chinarewards.gwt.elt.client.core.presenter;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Panel;

public interface GiftPresenter extends Presenter<GiftPresenter.GiftDisplay> {

	public static interface GiftDisplay extends Display {
		
		HasClickHandlers getlogBtn();

		HasClickHandlers getBtnCollection();
		
		HasClickHandlers getManagementCenter();
		HasClickHandlers getGiftExchange();
		HasClickHandlers getStaffCorner();
		DockLayoutPanel getDock();
		
		HasClickHandlers getBtnEmail();
		HasClickHandlers getBtnGb();
		HasClickHandlers getBtnGift();
		Panel getMenu();
		void setMenu(Panel panel);
		void setMenuTitle(String title);
		void setMessage(String userName);
		void disableManagementCenter();
		void disableGiftExchange();
		void disableStaffCorner();
	}
	
	
}
