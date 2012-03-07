package com.chinarewards.elt.service.common;

import com.chinarewards.elt.guice.EltModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

public class TestInjectorProvider implements Provider<Injector> {

	Injector injector;

	@Inject
	public TestInjectorProvider() {
		injector = Guice.createInjector(new EltModule());
	}

	public Injector get() {
		return injector;
	}
}
