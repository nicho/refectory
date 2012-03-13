package com.chinarewards.elt.guice;

import com.chinarewards.elt.guice.sub.OrderModule;
import com.chinarewards.elt.guice.sub.UserModule;
import com.google.inject.AbstractModule;

public class EltModule extends AbstractModule {

	@Override
	protected void configure() {

		install(new CommonModule());
		install(new OrderModule());
		install(new UserModule());



	}

}
