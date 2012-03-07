package com.chinarewards.gwt.elt.client.messageSave.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.messageSave.presenter.MessageSavePresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月20日 11:01:49
 */
public class MessageSaveEditor extends AbstractEditor {

	final MessageSavePresenter messageSavePresenter;
	Object model;

	@Inject
	protected MessageSaveEditor(MessageSaveEditorDescriptor editorDescriptor,
			MessageSavePresenter messageSavePresenter) {
		super(editorDescriptor);
		this.messageSavePresenter = messageSavePresenter;
	}

	@Override
	public Widget asWidget() {
		return messageSavePresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		messageSavePresenter.unbind();
		return true;
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public void save() {

	}

	public void setModel(Object model) {
		this.model = model;
//		if(model!=null)
//			messageSavePresenter.initBroadcastUpdate((String)model);
		messageSavePresenter.bind();
	}
}
