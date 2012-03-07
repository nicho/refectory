package com.chinarewards.gwt.elt.client.department.view;

import java.util.List;

import com.chinarewards.gwt.elt.client.department.model.DepartmentLeaderTreeModel;
import com.chinarewards.gwt.elt.client.department.model.DepartmentNode;
import com.chinarewards.gwt.elt.client.department.presenter.DepartmentLeaderPresenter.DepartmentLeaderDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class DepartmentLeaderWidget extends Composite implements
		DepartmentLeaderDisplay {
	@UiField
	Panel cellTree;

	@UiField
	Hidden currentDepartmentId;

	@UiField
	Button addChildBtn;
	@UiField
	Button deleteBtn;
	@UiField
	Button editBtn;

	@UiField
	Panel breadCrumbs;

	private static DepartmentWidgetUiBinder uiBinder = GWT
			.create(DepartmentWidgetUiBinder.class);

	interface DepartmentWidgetUiBinder extends
			UiBinder<Widget, DepartmentLeaderWidget> {
	}

	public DepartmentLeaderWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void loadTreeData(List<DepartmentNode> nodeList,
			String corporationId) {
		DepartmentLeaderTreeModel treeModel = new DepartmentLeaderTreeModel(
				nodeList, corporationId,this);

		CellTree.Resources res = GWT.create(CellTree.BasicResources.class);
		CellTree tree = new CellTree(treeModel, null, res);
		tree.setAnimationEnabled(true);
		
		
		cellTree.clear();
		cellTree.add(tree);
	
//		cellTree.getElement().getFirstChild().insertFirst(testNode);
	}


	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);
	}

	@Override
	public HasClickHandlers getAddChildBtnClickHandlers() {
		return addChildBtn;
	}

	@Override
	public HasClickHandlers getDeleteBtnClickHandlers() {
		return deleteBtn;
	}

	@Override
	public HasClickHandlers getEditBtnClickHandlers() {
		return editBtn;
	}

	@Override
	public Panel getCellTree() {
		return cellTree;
	}

	@Override
	public Hidden getCurrentDepartmentId() {
		return currentDepartmentId;
	}
}
