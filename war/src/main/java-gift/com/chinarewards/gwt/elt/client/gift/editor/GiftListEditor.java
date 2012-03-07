package com.chinarewards.gwt.elt.client.gift.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.gift.presenter.GiftListPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年1月9日 17:25:16
 */
public class GiftListEditor extends AbstractEditor {

	final GiftListPresenter giftListPresenter;
	Object model;

	@Inject
	protected GiftListEditor(GiftListEditorDescriptor editorDescriptor,
			GiftListPresenter giftListPresenter) {
		super(editorDescriptor);
		this.giftListPresenter = giftListPresenter;
	}

	@Override
	public Widget asWidget() {
		return giftListPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		giftListPresenter.unbind();
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
		giftListPresenter.bind();
	}
}
