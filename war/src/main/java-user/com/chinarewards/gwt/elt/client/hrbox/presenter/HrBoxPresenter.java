package com.chinarewards.gwt.elt.client.hrbox.presenter;


import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Panel;

public interface HrBoxPresenter extends Presenter<HrBoxPresenter.HrBoxDisplay> {

	public void initHrBox();
	public static interface HrBoxDisplay extends Display {
		public Panel getResultPanel();
		public Panel getResultpage();
		void setHrSend(String text);
			
		void setOrder(String text);
		public void setMessage(String week);
		public HasClickHandlers getView() ;
		public Panel getRewardWindow();
	}
}
