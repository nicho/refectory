package com.chinarewards.gwt.elt.client.gift.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.gift.editor.GiftEditorDescriptor;
import com.chinarewards.gwt.elt.client.gift.model.GiftClient;
import com.chinarewards.gwt.elt.client.gift.presenter.GiftPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author yanrui
 * @since 2012年1月9日 17:25:16
 */
public class GiftEditor extends AbstractEditor {

	final GiftPresenter giftPresenter;
	Object model;

	@Inject
	protected GiftEditor(GiftEditorDescriptor editorDescriptor,
			GiftPresenter giftPresenter) {
		super(editorDescriptor);
		this.giftPresenter = giftPresenter;
	}

	@Override
	public Widget asWidget() {
		return giftPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		giftPresenter.unbind();
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
		GiftClient giftClient = (GiftClient) model;
		giftPresenter
				.initEditor(giftClient.getId(), giftClient.getThisAction());
		giftPresenter.bind();
	}
}
