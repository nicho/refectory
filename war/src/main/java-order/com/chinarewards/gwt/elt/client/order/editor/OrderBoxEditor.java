package com.chinarewards.gwt.elt.client.order.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.order.model.OrderSearchVo;
import com.chinarewards.gwt.elt.client.order.presenter.OrderBoxPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author lw
 * @since 2012年2月1日 13:35:29
 */
public class OrderBoxEditor extends AbstractEditor {

	final OrderBoxPresenter orderBoxPresenter;
	Object model;

	@Inject
	protected OrderBoxEditor(OrderBoxEditorDescriptor editorDescriptor,
			OrderBoxPresenter orderBoxPresenter) {
		super(editorDescriptor);
		this.orderBoxPresenter = orderBoxPresenter;
	}

	@Override
	public Widget asWidget() {
		return orderBoxPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		orderBoxPresenter.unbind();
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
		orderBoxPresenter.initOrderBox();
		orderBoxPresenter.bind();
	}
}
