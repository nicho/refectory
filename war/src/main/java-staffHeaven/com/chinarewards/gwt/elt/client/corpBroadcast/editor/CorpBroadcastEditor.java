package com.chinarewards.gwt.elt.client.corpBroadcast.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.corpBroadcast.presenter.CorpBroadcastPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月14日 10:25:52
 */
public class CorpBroadcastEditor extends AbstractEditor {

	final CorpBroadcastPresenter corpBroadcastPresenter;
	Object model;

	@Inject
	protected CorpBroadcastEditor(CorpBroadcastEditorDescriptor editorDescriptor,
			CorpBroadcastPresenter corpBroadcastPresenter) {
		super(editorDescriptor);
		this.corpBroadcastPresenter = corpBroadcastPresenter;
	}

	@Override
	public Widget asWidget() {
		return corpBroadcastPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		corpBroadcastPresenter.unbind();
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
		corpBroadcastPresenter.bind();
	}
}
