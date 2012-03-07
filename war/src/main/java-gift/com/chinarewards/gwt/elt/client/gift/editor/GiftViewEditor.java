package com.chinarewards.gwt.elt.client.gift.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.gift.model.GiftClient;
import com.chinarewards.gwt.elt.client.gift.presenter.GiftViewPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author yanrui
 * @since 2012年1月16日 
 */
public class GiftViewEditor extends AbstractEditor {

	final GiftViewPresenter giftViewPresenter;
	Object model;

	@Inject
	protected GiftViewEditor(GiftViewEditorDescriptor editorDescriptor,
			GiftViewPresenter giftViewPresenter) {
		super(editorDescriptor);
		this.giftViewPresenter = giftViewPresenter;
	}

	@Override
	public Widget asWidget() {
		return giftViewPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		giftViewPresenter.unbind();
		return true;
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public void save() {

	}

	public void setModel(String instanceId, Object model) {
		this.model=model;
		giftViewPresenter.bind();
		if (model != null) {
			giftViewPresenter.initInstanceId(instanceId, (GiftClient) model);

		}
	}
}
