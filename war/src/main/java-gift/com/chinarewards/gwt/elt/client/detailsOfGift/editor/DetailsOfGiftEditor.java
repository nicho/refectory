package com.chinarewards.gwt.elt.client.detailsOfGift.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.detailsOfGift.model.DetailsOfGiftClient;
import com.chinarewards.gwt.elt.client.detailsOfGift.presenter.DetailsOfGiftPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月1日 16:40:39
 */
public class DetailsOfGiftEditor extends AbstractEditor {

	final DetailsOfGiftPresenter detailsOfGiftPresenter;
	Object model;

	@Inject
	protected DetailsOfGiftEditor(DetailsOfGiftEditorDescriptor editorDescriptor,
			DetailsOfGiftPresenter detailsOfGiftPresenter) {
		super(editorDescriptor);
		this.detailsOfGiftPresenter = detailsOfGiftPresenter;
	}

	@Override
	public Widget asWidget() {
		return detailsOfGiftPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		detailsOfGiftPresenter.unbind();
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
		if(model instanceof DetailsOfGiftClient)
		{
			detailsOfGiftPresenter.initDetailsOfGift(((DetailsOfGiftClient) model));
		}
		detailsOfGiftPresenter.bind();
	}
}
