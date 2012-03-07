package com.chinarewards.gwt.elt.client.gloryBroadcast.presenter;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.user.client.ui.Panel;

public interface GloryBroadcastPresenter extends
		Presenter<GloryBroadcastPresenter.GloryBroadcastDisplay> {

	public void initGloryBroadcast(String staffId);
	public static interface GloryBroadcastDisplay extends Display {

		void setDataCount(String text);

		Panel getResultPanel();

		Panel getResultpage();

	}
}
