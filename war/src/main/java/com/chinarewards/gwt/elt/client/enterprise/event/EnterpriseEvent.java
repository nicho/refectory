package com.chinarewards.gwt.elt.client.enterprise.event;

import com.google.gwt.event.shared.GwtEvent;

public class EnterpriseEvent extends GwtEvent<EnterpriseHandler> {

	private static Type<EnterpriseHandler> TYPE;

	public static Type<EnterpriseHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<EnterpriseHandler>());
	}

	

//	private final LoginStatus status;
	private final Throwable exception=null;
//
//	public EnterpriseEvent(LoginStatus status) {
//		this.status = status;
//		this.exception = null;
//	}
//
//	public EnterpriseEvent(LoginStatus status, Throwable t) {
//		this.status = status;
//		this.exception = t;
//	}
//
//	public LoginStatus getStatus() {
//		return status;
//	}

	@Override
	public final Type<EnterpriseHandler> getAssociatedType() {
		return getType();
	}

	@Override
	protected void dispatch(EnterpriseHandler handler) {
		handler.onLogin(this);
	}

	public Throwable getException() {
		return exception;
	}
}
