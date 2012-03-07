package com.chinarewards.elt.guice.sub;

import com.chinarewards.elt.service.sendmail.SendMailService;
import com.chinarewards.elt.service.sendmail.SendMailServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * It is used to send sms or send email.
 * 
 * @author lw
 * @since 0.0.1 2010-09-21
 */
public class EmailModule extends AbstractModule {

	@Override
	protected void configure() {
		
		bind(SendMailService.class).to(SendMailServiceImpl.class).in(Singleton.class);

		
	}

}
