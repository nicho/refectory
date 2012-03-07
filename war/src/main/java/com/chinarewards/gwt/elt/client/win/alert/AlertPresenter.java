package com.chinarewards.gwt.elt.client.win.alert;


import com.chinarewards.gwt.elt.client.mvp.DialogPresenter;
import com.chinarewards.gwt.elt.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;

public interface AlertPresenter extends
		DialogPresenter<AlertPresenter.AlertDisplay> {

	public static interface AlertDisplay extends Display {
		HasClickHandlers getOkBtn();

		void setMsg(String msg);
		void setImage(String url);
	}

}
