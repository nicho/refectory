package com.chinarewards.gwt.elt.client.message.presenter;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Panel;

public interface MessageListPresenter extends
		Presenter<MessageListPresenter.MessageListDisplay> {

	public static interface MessageListDisplay extends Display {

		void setDataCount(String text);

		Panel getResultPanel();

		Panel getResultpage();
		HasClickHandlers getAddBtn();
		Anchor getReceivedMessage();
		Anchor getSendMessage();
	}
}
