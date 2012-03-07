package com.chinarewards.gwt.elt.client.detailsOfBroadcast.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.detailsOfBroadcast.presenter.DetailsOfBroadcastPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月21日 13:34:33
 */
public class DetailsOfBroadcastEditor extends AbstractEditor {

	final DetailsOfBroadcastPresenter detailsOfBroadcastPresenter;
	Object model;

	@Inject
	protected DetailsOfBroadcastEditor(DetailsOfBroadcastEditorDescriptor editorDescriptor,
			DetailsOfBroadcastPresenter detailsOfBroadcastPresenter) {
		super(editorDescriptor);
		this.detailsOfBroadcastPresenter = detailsOfBroadcastPresenter;
	}

	@Override
	public Widget asWidget() {
		return detailsOfBroadcastPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		detailsOfBroadcastPresenter.unbind();
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
			detailsOfBroadcastPresenter.initBroadcastDetails((String)model);
		detailsOfBroadcastPresenter.bind();
	}
}
