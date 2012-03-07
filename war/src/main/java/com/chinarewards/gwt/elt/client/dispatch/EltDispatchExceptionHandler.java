package com.chinarewards.gwt.elt.client.dispatch;

import net.customware.gwt.dispatch.client.ExceptionHandler;
import net.customware.gwt.dispatch.shared.secure.InvalidSessionException;

import com.chinarewards.gwt.elt.client.login.event.LoginEvent;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.google.inject.Inject;

public class EltDispatchExceptionHandler implements ExceptionHandler {

	final EventBus eventBus;
	
	@Inject
	public EltDispatchExceptionHandler(EventBus eventBus) {
		this.eventBus = eventBus;
	}
	
	@Override
	public Status onFailure(Throwable e) {
		if (e instanceof InvalidSessionException) {
			eventBus.fireEvent(new LoginEvent(LoginEvent.LoginStatus.LOGOUT));
			return Status.STOP;
		}
		return Status.CONTINUE;
	}

}
