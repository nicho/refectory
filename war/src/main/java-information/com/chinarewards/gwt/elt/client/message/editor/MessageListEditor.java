package com.chinarewards.gwt.elt.client.message.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.message.presenter.MessageListPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月14日 10:25:52
 */
public class MessageListEditor extends AbstractEditor {

	final MessageListPresenter messageListPresenter;
	Object model;

	@Inject
	protected MessageListEditor(MessageListEditorDescriptor editorDescriptor,
			MessageListPresenter messageListPresenter) {
		super(editorDescriptor);
		this.messageListPresenter = messageListPresenter;
	}

	@Override
	public Widget asWidget() {
		return messageListPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		messageListPresenter.unbind();
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
		messageListPresenter.bind();
	}
}
