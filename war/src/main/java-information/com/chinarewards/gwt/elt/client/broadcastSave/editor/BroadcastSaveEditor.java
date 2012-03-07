package com.chinarewards.gwt.elt.client.broadcastSave.editor;

import com.chinarewards.gwt.elt.client.broadcastSave.presenter.BroadcastSavePresenter;
import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月20日 11:01:49
 */
public class BroadcastSaveEditor extends AbstractEditor {

	final BroadcastSavePresenter broadcastSavePresenter;
	Object model;

	@Inject
	protected BroadcastSaveEditor(BroadcastSaveEditorDescriptor editorDescriptor,
			BroadcastSavePresenter broadcastSavePresenter) {
		super(editorDescriptor);
		this.broadcastSavePresenter = broadcastSavePresenter;
	}

	@Override
	public Widget asWidget() {
		return broadcastSavePresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		broadcastSavePresenter.unbind();
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
		if(model!=null)
			broadcastSavePresenter.initBroadcastUpdate((String)model);
		broadcastSavePresenter.bind();
	}
}
