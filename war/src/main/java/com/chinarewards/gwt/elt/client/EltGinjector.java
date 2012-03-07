package com.chinarewards.gwt.elt.client;

import com.chinarewards.gwt.elt.client.awardShopLattice.presenter.AwardShopLatticePresenter;
import com.chinarewards.gwt.elt.client.core.PlatformGinjector;
import com.chinarewards.gwt.elt.client.dispatch.EltStandardDispatchModule;
import com.chinarewards.gwt.elt.client.login.presenter.LoginModule;
import com.chinarewards.gwt.elt.client.login.presenter.LoginPresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.register.presenter.RegisterModule;
import com.chinarewards.gwt.elt.client.register.presenter.RegisterPresenter;
import com.chinarewards.gwt.elt.client.registerHr.presenter.RegisterHrModule;
import com.chinarewards.gwt.elt.client.registerHr.presenter.RegisterHrPresenter;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.google.gwt.inject.client.GinModules;

@GinModules({ MainModule.class, LoginModule.class, RegisterModule.class,RegisterHrModule.class, EltStandardDispatchModule.class })
public interface EltGinjector extends PlatformGinjector {

	// MainPresenter getMainPresenter();

	LoginPresenter getLoginPresenter();
	RegisterPresenter getRegisterPresenter();
	AwardShopLatticePresenter getAwardShopLatticePresenter();
	RegisterHrPresenter getRegisterHrPresenter();
	EventBus getEventBus();

	SessionManager getSessionManager();

	ErrorHandler getErrorHandler();

	Main getMain();
}
