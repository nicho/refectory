package com.chinarewards.gwt.elt.client.gloryBroadcast.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.gloryBroadcast.presenter.GloryBroadcastPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月14日 10:25:52
 */
public class GloryBroadcastEditor extends AbstractEditor {

	final GloryBroadcastPresenter gloryBroadcastPresenter;
	Object model;

	@Inject
	protected GloryBroadcastEditor(GloryBroadcastEditorDescriptor editorDescriptor,
			GloryBroadcastPresenter gloryBroadcastPresenter) {
		super(editorDescriptor);
		this.gloryBroadcastPresenter = gloryBroadcastPresenter;
	}

	@Override
	public Widget asWidget() {
		return gloryBroadcastPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		gloryBroadcastPresenter.unbind();
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
		gloryBroadcastPresenter.bind();
	}
}
