package com.chinarewards.gwt.elt.client.team.editor;

import com.chinarewards.gwt.elt.client.core.ui.impl.AbstractEditor;
import com.chinarewards.gwt.elt.client.order.model.OrderSearchVo;
import com.chinarewards.gwt.elt.client.order.presenter.OrderViewPresenter;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author lw
 * @since 2012年2月1日 13:35:29
 */
public class TeamViewEditor extends AbstractEditor {

	final OrderViewPresenter orderViewPresenter;
	Object model;

	@Inject
	protected TeamViewEditor(TeamViewEditorDescriptor editorDescriptor,
			OrderViewPresenter orderViewPresenter) {
		super(editorDescriptor);
		this.orderViewPresenter = orderViewPresenter;
	}

	@Override
	public Widget asWidget() {
		return orderViewPresenter.getDisplay().asWidget();
	}

	@Override
	public boolean beforeClose() {
		orderViewPresenter.unbind();
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
		if(model instanceof OrderSearchVo)
		{
			orderViewPresenter.initOrderView(((OrderSearchVo) model));
		}
		
		orderViewPresenter.bind();
	}
}
