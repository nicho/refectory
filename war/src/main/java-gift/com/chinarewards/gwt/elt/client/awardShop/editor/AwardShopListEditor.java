package com.chinarewards.gwt.elt.client.awardShop.editor;

import com.chinarewards.gwt.elt.client.awardShop.presenter.AwardShopListPresenter;
import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年1月20日 10:41:01
 */
public class AwardShopListEditor extends AbstractEditor {

	final AwardShopListPresenter awardShopListPresenter;
	Object model;

	@Inject
	protected AwardShopListEditor(AwardShopListEditorDescriptor editorDescriptor,
			AwardShopListPresenter awardShopListPresenter) {
		super(editorDescriptor);
		this.awardShopListPresenter = awardShopListPresenter;
	}

	@Override
	public Widget asWidget() {
		return awardShopListPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		awardShopListPresenter.unbind();
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
		awardShopListPresenter.bind();
	}
}
