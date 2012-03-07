package com.chinarewards.gwt.elt.client.staffList.presenter;


import com.chinarewards.gwt.elt.client.mvp.DialogPresenter;
import com.chinarewards.gwt.elt.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Panel;

public interface StaffListPrintPresenter extends DialogPresenter<StaffListPrintPresenter.StaffListPrintDisplay> {
	

	public static interface StaffListPrintDisplay extends Display {

		Panel getResultPanel();
		public HasClickHandlers getPrintBtnClickHandlers();

	}
}
