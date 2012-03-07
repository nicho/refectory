package com.chinarewards.gwt.elt.client.enterprise.module;

import com.chinarewards.gwt.elt.client.enterprise.presenter.EnterprisePresenter;
import com.chinarewards.gwt.elt.client.enterprise.presenter.EnterprisePresenter.EnterpriseDisplay;
import com.chinarewards.gwt.elt.client.enterprise.presenter.EnterprisePresenterImpl;
import com.chinarewards.gwt.elt.client.enterprise.presenter.IntegralPricePresenter;
import com.chinarewards.gwt.elt.client.enterprise.presenter.IntegralPricePresenter.IntegralPriceDisplay;
import com.chinarewards.gwt.elt.client.enterprise.presenter.IntegralPricePresenterImpl;
import com.chinarewards.gwt.elt.client.enterprise.presenter.MailSetPresenter;
import com.chinarewards.gwt.elt.client.enterprise.presenter.MailSetPresenter.MailSetDisplay;
import com.chinarewards.gwt.elt.client.enterprise.presenter.MailSetPresenterImpl;
import com.chinarewards.gwt.elt.client.enterprise.presenter.PeriodPresenter;
import com.chinarewards.gwt.elt.client.enterprise.presenter.PeriodPresenter.PeriodDisplay;
import com.chinarewards.gwt.elt.client.enterprise.presenter.PeriodPresenterImpl;
import com.chinarewards.gwt.elt.client.enterprise.view.EnterpriseWidget;
import com.chinarewards.gwt.elt.client.enterprise.view.IntegralPriceWidget;
import com.chinarewards.gwt.elt.client.enterprise.view.MailSetWidget;
import com.chinarewards.gwt.elt.client.enterprise.view.PeriodWidget;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class EnterprisesModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(EnterprisePresenter.class).to(EnterprisePresenterImpl.class);
		bind(EnterpriseDisplay.class).to(EnterpriseWidget.class);
		
		bind(IntegralPricePresenter.class).to(IntegralPricePresenterImpl.class);
		bind(IntegralPriceDisplay.class).to(IntegralPriceWidget.class);
		
		bind(PeriodPresenter.class).to(PeriodPresenterImpl.class).in(Singleton.class);
		bind(PeriodDisplay.class).to(PeriodWidget.class);
		
		bind(MailSetPresenter.class).to(MailSetPresenterImpl.class);
		bind(MailSetDisplay.class).to(MailSetWidget.class);
	}

}
