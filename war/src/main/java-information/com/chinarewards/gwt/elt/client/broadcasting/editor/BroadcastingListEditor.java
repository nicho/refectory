package com.chinarewards.gwt.elt.client.broadcasting.editor;

import com.chinarewards.gwt.elt.client.broadcasting.presenter.BroadcastingListPresenter;
import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月14日 10:25:52
 */
public class BroadcastingListEditor extends AbstractEditor {

	final BroadcastingListPresenter broadcastingListPresenter;
	Object model;

	@Inject
	protected BroadcastingListEditor(BroadcastingListEditorDescriptor editorDescriptor,
			BroadcastingListPresenter broadcastingListPresenter) {
		super(editorDescriptor);
		this.broadcastingListPresenter = broadcastingListPresenter;
	}

	@Override
	public Widget asWidget() {
		return broadcastingListPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		broadcastingListPresenter.unbind();
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
		broadcastingListPresenter.bind();
	}
}
