package com.chinarewards.gwt.elt.client.core;

import com.chinarewards.gwt.elt.client.core.presenter.PresentersGinjector;
import com.chinarewards.gwt.elt.client.mvp.MVPGinjector;
import com.google.gwt.inject.client.GinModules;
import com.google.inject.name.Named;

@GinModules( { PlatformModule.class })
public interface PlatformGinjector extends MVPGinjector, PresentersGinjector {

	Platform getPlatform();

	@Named("admin")
	PluginSet getPluginSetAdmin();
	@Named("dept")
	PluginSet getPluginSetDept();
	@Named("staff")
	PluginSet getPluginSetStaff();
	@Named("gift")
	PluginSet getPluginSetGift();
}
