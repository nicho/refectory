package com.chinarewards.gwt.elt.client.department.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;
import com.chinarewards.gwt.elt.client.department.presenter.MergeDepartmentPresenter.MergeDepartmentDisplay;
import com.chinarewards.gwt.elt.client.mvp.BaseDialogPresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class MergeDepartmentPresenterImpl extends
		BaseDialogPresenter<MergeDepartmentDisplay> implements
		MergeDepartmentPresenter {

	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;

	@Inject
	public MergeDepartmentPresenterImpl(EventBus eventBus,
			MergeDepartmentDisplay display, DispatchAsync dispatch,
			ErrorHandler errorHandler, SessionManager sessionManager) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
	}

	public Widget asWidget() {
		return display.asWidget();
	}

	public void bind() {
		initMerge();

		registerHandler(display.getMergeBtn().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						String departmentIds = display.getDepartmentIds()
								.getValue();
						String departmentName = display.getDepartmentName()
								.getValue();
						String leaderId = display.getLeaderId().getValue();

						eventBus.fireEvent(new MergeDepartmentEvent(
								departmentIds, departmentName, leaderId));

						closeDialog();
					}
				}));

		registerHandler(display.getCancelBtn().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						closeDialog();
					}
				}));
	}

	private void initMerge() {
	}

	@Override
	public void initDialog(String deparatmentIds) {
		display.getDepartmentIds().setValue(deparatmentIds);
	}

}
