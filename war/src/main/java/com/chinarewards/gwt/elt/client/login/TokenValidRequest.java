package com.chinarewards.gwt.elt.client.login;

import net.customware.gwt.dispatch.shared.Action;

public class TokenValidRequest implements Action<TokenValidResponse> {

	private String token;

	public TokenValidRequest() {

	}

	public TokenValidRequest(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
