package com.chinarewards.gwt.elt.client.orderConfirmation.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.orderConfirmation.model.OrderConfirmationClient;
import com.chinarewards.gwt.elt.client.orderConfirmation.presenter.OrderConfirmationPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author nicho
 * @since 2012年1月31日 16:18:02
 */
public class OrderConfirmationEditor extends AbstractEditor {

	final OrderConfirmationPresenter orderConfirmationPresenter;
	Object model;

	@Inject
	protected OrderConfirmationEditor(OrderConfirmationEditorDescriptor editorDescriptor,
			OrderConfirmationPresenter orderConfirmationPresenter) {
		super(editorDescriptor);
		this.orderConfirmationPresenter = orderConfirmationPresenter;
	}

	@Override
	public Widget asWidget() {
		return orderConfirmationPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		orderConfirmationPresenter.unbind();
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
		if(model instanceof OrderConfirmationClient)
		{
			orderConfirmationPresenter.initOrderConfirmation(((OrderConfirmationClient) model).getOrderId());
		}
		orderConfirmationPresenter.bind();
	}
}
