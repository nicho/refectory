package com.chinarewards.gwt.elt.client.restaurantList.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.dictionaryList.editor.DictionaryListEditorDescriptor;
import com.chinarewards.gwt.elt.client.restaurantList.presenter.RestaurantListPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月14日 10:25:52
 */
public class RestaurantListEditor extends AbstractEditor {

	final RestaurantListPresenter restaurantListPresenter;
	Object model;

	@Inject
	protected RestaurantListEditor(DictionaryListEditorDescriptor editorDescriptor,
			RestaurantListPresenter restaurantListPresenter) {
		super(editorDescriptor);
		this.restaurantListPresenter = restaurantListPresenter;
	}

	@Override
	public Widget asWidget() {
		return restaurantListPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		restaurantListPresenter.unbind();
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
		restaurantListPresenter.bind();
	}
}
