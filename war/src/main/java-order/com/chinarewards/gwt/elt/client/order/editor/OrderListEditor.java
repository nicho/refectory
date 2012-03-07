package com.chinarewards.gwt.elt.client.order.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.order.model.OrderStatus;
import com.chinarewards.gwt.elt.client.order.presenter.OrderListPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
<<<<<<< HEAD
 * @author lw
 * @since 2012年1月19日 17:25:16
=======
 * @author yanrui
>>>>>>> refs/remotes/origin/master
 */
public class OrderListEditor extends AbstractEditor {

	final OrderListPresenter orderListPresenter;
	Object model;

	@Inject
	protected OrderListEditor(OrderListEditorDescriptor editorDescriptor,
			OrderListPresenter orderListPresenter) {
		super(editorDescriptor);
		this.orderListPresenter = orderListPresenter;
	}

	@Override
	public Widget asWidget() {
		return orderListPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		orderListPresenter.unbind();
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
		if(model instanceof OrderStatus)
			orderListPresenter.getBoxOrder(model.toString());
		orderListPresenter.bind();
		
	}
}
