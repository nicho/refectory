package com.chinarewards.gwt.elt.client.corpBroadcast.presenter;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;

public interface CorpBroadcastPresenter extends
		Presenter<CorpBroadcastPresenter.CorpBroadcastDisplay> {

	public void initStaffBroadcast(String staffId);
	public static interface CorpBroadcastDisplay extends Display {

		void setDataCount(String text);
		void setTitleName(String text);

		Panel getResultPanel();

		Panel getResultpage();

		TextBox getQueryKey();

		Button getQueryBtn();
	}
}
