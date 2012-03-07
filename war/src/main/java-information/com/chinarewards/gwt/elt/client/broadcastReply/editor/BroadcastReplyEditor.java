package com.chinarewards.gwt.elt.client.broadcastReply.editor;

import com.chinarewards.gwt.elt.client.broadcastReply.presenter.BroadcastReplyPresenter;
import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月20日 11:01:49
 */
public class BroadcastReplyEditor extends AbstractEditor {

	final BroadcastReplyPresenter broadcastReplyPresenter;
	Object model;

	@Inject
	protected BroadcastReplyEditor(BroadcastReplyEditorDescriptor editorDescriptor,
			BroadcastReplyPresenter broadcastReplyPresenter) {
		super(editorDescriptor);
		this.broadcastReplyPresenter = broadcastReplyPresenter;
	}

	@Override
	public Widget asWidget() {
		return broadcastReplyPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		broadcastReplyPresenter.unbind();
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
			broadcastReplyPresenter.initBroadcast((String)model);
		broadcastReplyPresenter.bind();
	}
}
