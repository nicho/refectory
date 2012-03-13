package com.chinarewards.gwt.elt.client.dictionaryList.module;


import com.chinarewards.gwt.elt.client.dictionaryList.presenter.DictionaryListPresenter;
import com.chinarewards.gwt.elt.client.dictionaryList.presenter.DictionaryListPresenter.DictionaryListDisplay;
import com.chinarewards.gwt.elt.client.dictionaryList.presenter.DictionaryListPresenterImpl;
import com.chinarewards.gwt.elt.client.dictionaryList.view.DictionaryListWidget;
import com.google.gwt.inject.client.AbstractGinModule;

public class DictionaryListModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DictionaryListPresenter.class).to(DictionaryListPresenterImpl.class);
		bind(DictionaryListDisplay.class).to(DictionaryListWidget.class);
	}

}
