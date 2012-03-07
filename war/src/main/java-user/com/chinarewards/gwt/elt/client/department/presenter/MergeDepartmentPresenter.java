package com.chinarewards.gwt.elt.client.department.presenter;

import com.chinarewards.gwt.elt.client.mvp.DialogPresenter;
import com.chinarewards.gwt.elt.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Widget;

public interface MergeDepartmentPresenter extends
		DialogPresenter<MergeDepartmentPresenter.MergeDepartmentDisplay> {

	public static interface MergeDepartmentDisplay extends Display {
		public Hidden getDepartmentIds();

		public HasValue<String> getDepartmentName();

		public Hidden getLeaderId();

		public HasClickHandlers getMergeBtn();

		public HasClickHandlers getCancelBtn();

		public Widget asWidget();
	}

	void initDialog(String departmentIds);

}
