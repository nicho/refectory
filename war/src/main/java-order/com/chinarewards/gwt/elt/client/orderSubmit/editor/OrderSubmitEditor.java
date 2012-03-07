package com.chinarewards.gwt.elt.client.orderSubmit.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.orderSubmit.model.OrderSubmitClient;
import com.chinarewards.gwt.elt.client.orderSubmit.presenter.OrderSubmitPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年2月1日 13:35:29
 */
public class OrderSubmitEditor extends AbstractEditor {

	final OrderSubmitPresenter orderSubmitPresenter;
	Object model;

	@Inject
	protected OrderSubmitEditor(OrderSubmitEditorDescriptor editorDescriptor,
			OrderSubmitPresenter orderSubmitPresenter) {
		super(editorDescriptor);
		this.orderSubmitPresenter = orderSubmitPresenter;
	}

	@Override
	public Widget asWidget() {
		return orderSubmitPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		orderSubmitPresenter.unbind();
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
		if(model instanceof OrderSubmitClient)
		{
			orderSubmitPresenter.initOrderSubmit(((OrderSubmitClient) model));
		}
		orderSubmitPresenter.bind();
	}
}
