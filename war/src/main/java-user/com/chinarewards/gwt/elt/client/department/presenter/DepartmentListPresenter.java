package com.chinarewards.gwt.elt.client.department.presenter;

import java.util.List;

import com.chinarewards.gwt.elt.client.department.model.DepartmentNode;
import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public interface DepartmentListPresenter extends
		Presenter<DepartmentListPresenter.DepartmentListDisplay> {

	public static interface DepartmentListDisplay extends Display {

		public Hidden getCurrentDepartmentId();
		
		public HasClickHandlers getAddSameLevelBtnClickHandlers();

		public HasClickHandlers getAddChildBtnClickHandlers();

		public HasClickHandlers getDeleteBtnClickHandlers();

		public HasClickHandlers getEditBtnClickHandlers();

		public HasClickHandlers getMergeBtnClickHandlers();

		public HasClickHandlers getSynchBtnClickHandlers();

		Panel getCellTree();

		void setBreadCrumbs(Widget breadCrumbs);

		void loadTreeData(List<DepartmentNode> result, String corporationId);

	}
	public void initEditor();
}
