package com.chinarewards.gwt.elt.client.orderHistory.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.orderHistory.presenter.OrderHistoryPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**

 * @author lw

 */
public class OrderHistoryEditor extends AbstractEditor {

	final OrderHistoryPresenter orderHistoryPresenter;
	Object model;

	@Inject
	protected OrderHistoryEditor(OrderHistoryEditorDescriptor editorDescriptor,
			OrderHistoryPresenter orderHistoryPresenter) {
		super(editorDescriptor);
		this.orderHistoryPresenter = orderHistoryPresenter;
	}

	@Override
	public Widget asWidget() {
		return orderHistoryPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		orderHistoryPresenter.unbind();
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
		orderHistoryPresenter.bind();
	}
}
