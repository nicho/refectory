package com.chinarewards.gwt.elt.client.colleague.presenter;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;

public interface ColleagueListPresenter extends
		Presenter<ColleagueListPresenter.ColleagueListDisplay> {

	public static interface ColleagueListDisplay extends Display {


		void setDataCount(String text);

		Panel getResultPanel();

		Panel getResultpage();
		TextBox getQueryKey();

		Button getQueryBtn();
	}
}
