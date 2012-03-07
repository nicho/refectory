package com.chinarewards.gwt.elt.client.sample;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractDialog;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

public class SampleDialog extends AbstractDialog {

	protected SampleDialog() {
		super("sample.dialogId", "someID", "Hello Dialog");
	}

	@Override
	public Widget asWidget() {
		Button b = new Button("Close Me");
		b.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				close();
			}
		});
		return b;
	}

	@Override
	public boolean beforeClose() {
		Window.alert("Closing Dialog");
		return true;
	}

}
