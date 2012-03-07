package com.chinarewards.gwt.elt.client.detailsOfGift.module;


import com.chinarewards.gwt.elt.client.detailsOfGift.presenter.DetailsOfGiftPresenter;
import com.chinarewards.gwt.elt.client.detailsOfGift.presenter.DetailsOfGiftPresenter.DetailsOfGiftDisplay;
import com.chinarewards.gwt.elt.client.detailsOfGift.presenter.DetailsOfGiftPresenterImpl;
import com.chinarewards.gwt.elt.client.detailsOfGift.view.DetailsOfGiftWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class DetailsOfGiftModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DetailsOfGiftPresenter.class).to(DetailsOfGiftPresenterImpl.class);
		bind(DetailsOfGiftDisplay.class).to(DetailsOfGiftWidget.class);
	}

}
