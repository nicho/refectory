package com.chinarewards.gwt.elt.client.dispatch;

import net.customware.gwt.dispatch.client.gin.StandardDispatchModule;

import com.google.gwt.inject.client.AbstractGinModule;

public class EltStandardDispatchModule extends AbstractGinModule {

	@Override
	protected void configure() {
		install(new StandardDispatchModule(EltDispatchExceptionHandler.class));
	}
}
