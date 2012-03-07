package com.chinarewards.gwt.elt.client.sample2;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.login.presenter.LoginPresenter;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class Sample2Editor extends AbstractEditor {

	Object model;
	LoginPresenter loginPresenter;

	@Inject
	protected Sample2Editor(Sample2EditorDescriptor editorDescriptor,
			LoginPresenter loginPresenter) {
		super(editorDescriptor);
		this.loginPresenter = loginPresenter;
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public void save() {
	}

	public Widget asWidget() {

		// Button b = new Button("Open Dialog");
		// b.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent arg0) {
		// Platform.getInstance()
		// .getSiteManager()
		// .openDialog(new Sample2Dialog(),
		// new DialogCloseListener() {
		// public void onClose(String editorId,
		// String instanceId) {
		// // close the editor
		// close();
		// }
		// });
		// }
		// });
		// return b;
	
		return loginPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		Window.alert("Closing Sample Editor");
		return true;
	}

	public void setModel(Object model) {
		this.model = model;
		loginPresenter.bind();
	}

}
