/**
 * 
 */
package com.chinarewards.gwt.elt.server;

import net.customware.gwt.dispatch.server.Dispatch;

import com.google.inject.AbstractModule;

/**
 * 
 * 
 * 
 * @author cyril
 * 
 */
public class EltServerDispatchModule extends AbstractModule {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		bind(Dispatch.class).to(EltDispatch.class);
	}

}
