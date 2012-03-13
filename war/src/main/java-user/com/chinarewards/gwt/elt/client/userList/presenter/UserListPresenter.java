package com.chinarewards.gwt.elt.client.userList.presenter;


import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public interface UserListPresenter extends Presenter<UserListPresenter.UserListDisplay> {
	

	public static interface UserListDisplay extends Display {

		public HasClickHandlers getSearchBtnClickHandlers();
		public HasClickHandlers getSynchronousStaffBtnClickHandlers();
		public HasClickHandlers getAddStaffBtnClickHandlers();
		void initStaffStatus();
		String getSttaffStatus();
		String getSttaffRole();
		HasValue<String> getStaffNameorNo();

		void setDataCount(String text);
		void setBreadCrumbs(Widget breadCrumbs);
		
		Panel getResultPanel();
		Panel getResultpage();

		void displayBtn();


	}
}
