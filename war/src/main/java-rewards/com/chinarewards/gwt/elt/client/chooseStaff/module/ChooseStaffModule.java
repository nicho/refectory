package com.chinarewards.gwt.elt.client.chooseStaff.module;


import com.chinarewards.gwt.elt.client.chooseStaff.presenter.ChooseStaffListPresenter;
import com.chinarewards.gwt.elt.client.chooseStaff.presenter.ChooseStaffListPresenter.ChooseStaffListDisplay;
import com.chinarewards.gwt.elt.client.chooseStaff.presenter.ChooseStaffListPresenterImpl;
import com.chinarewards.gwt.elt.client.chooseStaff.presenter.ChooseStaffPanelPresenter;
import com.chinarewards.gwt.elt.client.chooseStaff.presenter.ChooseStaffPanelPresenter.ChooseStaffPanelDisplay;
import com.chinarewards.gwt.elt.client.chooseStaff.presenter.ChooseStaffPanelPresenterImpl;
import com.chinarewards.gwt.elt.client.chooseStaff.view.ChooseStaffListWidget;
import com.chinarewards.gwt.elt.client.chooseStaff.view.ChooseStaffPanelWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class ChooseStaffModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(ChooseStaffListPresenter.class).to(ChooseStaffListPresenterImpl.class);
		bind(ChooseStaffListDisplay.class).to(ChooseStaffListWidget.class);
		
		bind(ChooseStaffPanelPresenter.class).to(ChooseStaffPanelPresenterImpl.class);
		bind(ChooseStaffPanelDisplay.class).to(ChooseStaffPanelWidget.class);
	}

}
