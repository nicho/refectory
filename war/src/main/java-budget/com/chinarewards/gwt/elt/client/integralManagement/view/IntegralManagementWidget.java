package com.chinarewards.gwt.elt.client.integralManagement.view;

import java.util.List;

import com.chinarewards.gwt.elt.client.breadCrumbs.ui.BreadCrumbsMenu;
import com.chinarewards.gwt.elt.client.core.presenter.DockPresenter;
import com.chinarewards.gwt.elt.client.core.ui.MenuProcessor;
import com.chinarewards.gwt.elt.client.integralManagement.model.Category;
import com.chinarewards.gwt.elt.client.integralManagement.model.ContactTreeViewModel;
import com.chinarewards.gwt.elt.client.integralManagement.presenter.IntegralManagementPresenter.IntegralManagementDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class IntegralManagementWidget extends Composite implements
		IntegralManagementDisplay {

	@UiField
	Panel cellTree;
	@UiField
	Panel breadCrumbs;
	@UiField
	Button nominatebutton;
	@UiField
	InlineLabel budgetIntegral;
	@UiField
	InlineLabel useIntegeral;
	
	 MenuProcessor menuProcessor;
	 DockPresenter dockPresenter;
	 BreadCrumbsMenu breadCrumbspresenter;
	private static IntegralManagementWidgetUiBinder uiBinder = GWT
			.create(IntegralManagementWidgetUiBinder.class);

	interface IntegralManagementWidgetUiBinder extends
			UiBinder<Widget, IntegralManagementWidget> {
	}

	public IntegralManagementWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Panel getCellTree() {
		return cellTree;
	}

	@Override
	public void refresh(List<Category> result,String corporationId) {

		CellTree.Resources res = GWT.create(CellTree.BasicResources.class);

		CellTree tree = new CellTree(new ContactTreeViewModel(result,corporationId,menuProcessor,dockPresenter,breadCrumbspresenter), null, res);
		tree.setAnimationEnabled(true);
		cellTree.clear();
		cellTree.add(tree);

	}
	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);
		
	}
	@Override
	public HasClickHandlers getNominateClickHandlers() {
		return nominatebutton;
	}

	@Override
	public void setBudgetIntegral(String text) {
		budgetIntegral.setText(text);
		
	}

	@Override
	public void setUseIntegeral(String text) {
		useIntegeral.setText(text);
		
	}

	@Override
	public void setMenuProcessor(MenuProcessor menuProcessor) {
		this.menuProcessor=menuProcessor;
		
	}

	@Override
	public void setDockPresenter(DockPresenter dockPresenter) {
		this.dockPresenter=dockPresenter;
	}

	@Override
	public void setBreadCrumbsMenu(BreadCrumbsMenu breadCrumbspresenter) {
		this.breadCrumbspresenter=breadCrumbspresenter;
	}

}
