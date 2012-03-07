package com.chinarewards.gwt.elt.client.department.presenter;

import java.util.List;

import com.chinarewards.gwt.elt.client.department.model.DepartmentNode;
import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public interface DepartmentLeaderPresenter extends
		Presenter<DepartmentLeaderPresenter.DepartmentLeaderDisplay> {

	public static interface DepartmentLeaderDisplay extends Display {

		public Hidden getCurrentDepartmentId();
		
		public HasClickHandlers getAddChildBtnClickHandlers();

		public HasClickHandlers getDeleteBtnClickHandlers();

		public HasClickHandlers getEditBtnClickHandlers();

		Panel getCellTree();

		void setBreadCrumbs(Widget breadCrumbs);

		void loadTreeData(List<DepartmentNode> result, String corporationId);

	}
	public void initEditor();
}
