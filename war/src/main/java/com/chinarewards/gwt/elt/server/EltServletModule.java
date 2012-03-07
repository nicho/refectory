package com.chinarewards.gwt.elt.server;

import net.customware.gwt.dispatch.server.guice.GuiceStandardDispatchServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.elt.guice.EltModule;
import com.chinarewards.gwt.elt.client.Elt;
import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;

/**
 * 
 * 
 */
public class EltServletModule extends ServletModule {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected void configureServlets() {

		logger.info("Configuring servlet modules...");

		serve(Elt.GWT_MODULE_PATH + "/dispatch").with(
				GuiceStandardDispatchServlet.class);
		bind(InitServlet.class).in(Singleton.class);
		serve(Elt.GWT_MODULE_PATH + "/donottouchme").with(InitServlet.class);

		install(new EltModule());

		logger.info("Configuring servlet modules completed.");

	}

}
