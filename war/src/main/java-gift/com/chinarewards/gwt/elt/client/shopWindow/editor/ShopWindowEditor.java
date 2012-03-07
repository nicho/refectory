package com.chinarewards.gwt.elt.client.shopWindow.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.shopWindow.presenter.ShopWindowPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年1月31日 11:22:19
 */
public class ShopWindowEditor extends AbstractEditor {

	final ShopWindowPresenter shopWindowPresenter;
	Object model;

	@Inject
	protected ShopWindowEditor(ShopWindowEditorDescriptor editorDescriptor,
			ShopWindowPresenter shopWindowPresenter) {
		super(editorDescriptor);
		this.shopWindowPresenter = shopWindowPresenter;
	}

	@Override
	public Widget asWidget() {
		return shopWindowPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		shopWindowPresenter.unbind();
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
		shopWindowPresenter.initShopWindow(1, 5);
		shopWindowPresenter.bind();
	}
}
