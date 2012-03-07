package com.chinarewards.gwt.elt.client.orderHistory.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.order.model.OrderSearchVo;
import com.chinarewards.gwt.elt.client.orderHistory.presenter.OrderHistoryViewPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author yanrui
 */
public class OrderHistoryViewEditor extends AbstractEditor {

	final OrderHistoryViewPresenter orderHistoryViewPresenter;
	Object model;

	@Inject
	protected OrderHistoryViewEditor(
			OrderHistoryViewEditorDescriptor orderHistoryVieweditorDescriptor,
			OrderHistoryViewPresenter orderHistoryViewPresenter) {
		super(orderHistoryVieweditorDescriptor);
		this.orderHistoryViewPresenter = orderHistoryViewPresenter;
	}

	@Override
	public Widget asWidget() {
		return orderHistoryViewPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		orderHistoryViewPresenter.unbind();
		return true;
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public void save() {

	}

	public void setModel(String instanceId,Object model) {
		orderHistoryViewPresenter.bind();
		if (model != null) {
			orderHistoryViewPresenter.initInstanceId(instanceId, (OrderSearchVo) model);
		}
	}
}
