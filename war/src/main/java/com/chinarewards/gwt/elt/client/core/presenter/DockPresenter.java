package com.chinarewards.gwt.elt.client.core.presenter;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Panel;

public interface DockPresenter extends Presenter<DockPresenter.DockDisplay> {

	public static interface DockDisplay extends Display {
		
		HasClickHandlers getbtnUser();
		HasClickHandlers getbtnOrder();
		HasClickHandlers getbtnDishesMenu();
		
		HasClickHandlers getlogBtn();


		DockLayoutPanel getDock();
		
		Panel getMenu();
		
		void setMessage(String userName);
		void setMenu(Panel panel);
		void setMenuTitle(String title);
		
		void disableManagementCenter();
		void disableGiftExchange();
		void disableStaffCorner();
		
		void changeTopMenu(String key);

	}
	
	
}
