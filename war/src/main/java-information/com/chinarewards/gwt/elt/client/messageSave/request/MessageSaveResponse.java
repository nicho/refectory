package com.chinarewards.gwt.elt.client.messageSave.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class MessageSaveResponse implements Result {

	String message;


	

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public MessageSaveResponse() {

	}
	public MessageSaveResponse(String message) {
		this.message=message;

	}

	

}
