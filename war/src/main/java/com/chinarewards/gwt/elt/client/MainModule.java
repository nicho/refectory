package com.chinarewards.gwt.elt.client;

import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.support.impl.CookieSessionManager;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * Define the main initializer
 * 
 * @author kmtong
 * 
 */
public class MainModule extends AbstractGinModule {

	protected void configure() {

		// bind(DispatchAsync.class).to(GinStandardDispatchAsync.class);
		// bind(ExceptionHandler.class).to(DefaultExceptionHandler.class);

		bind(Main.class).to(MainImpl.class);
		bind(SessionManager.class).to(CookieSessionManager.class).in(Singleton.class);
	}

}
