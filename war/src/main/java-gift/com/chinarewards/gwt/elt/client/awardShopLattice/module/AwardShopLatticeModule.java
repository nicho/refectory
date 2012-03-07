package com.chinarewards.gwt.elt.client.awardShopLattice.module;


import com.chinarewards.gwt.elt.client.awardShopLattice.presenter.AwardShopLatticePresenter;
import com.chinarewards.gwt.elt.client.awardShopLattice.presenter.AwardShopLatticePresenter.AwardShopLatticeDisplay;
import com.chinarewards.gwt.elt.client.awardShopLattice.presenter.AwardShopLatticePresenterImpl;
import com.chinarewards.gwt.elt.client.awardShopLattice.view.AwardShopLatticeWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class AwardShopLatticeModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(AwardShopLatticePresenter.class).to(AwardShopLatticePresenterImpl.class);
		bind(AwardShopLatticeDisplay.class).to(AwardShopLatticeWidget.class);
	}

}
