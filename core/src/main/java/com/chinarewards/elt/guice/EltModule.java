package com.chinarewards.elt.guice;

import com.chinarewards.elt.tx.guice.TxModule;
import com.google.inject.AbstractModule;

public class EltModule extends AbstractModule {

	@Override
	protected void configure() {

		install(new CommonModule());

		install(new TxModule());


	}

}
