package com.chinarewards.gwt.elt.client.core.presenter;

import com.chinarewards.gwt.elt.client.PresenterModule;
import com.google.gwt.inject.client.GinModules;

/**
 * Centralize all Presenter/Display binding here. As Presenter depends some
 * basic infrastructure from MVP, so it extends MVPGinjector
 * 
 * @author yanxin
 * 
 */
@GinModules({ PresenterModule.class })
public interface PresentersGinjector {

}
