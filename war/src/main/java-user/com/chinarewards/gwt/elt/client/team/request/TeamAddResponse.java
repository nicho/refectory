package com.chinarewards.gwt.elt.client.team.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * Models the response after user process request.
 * 
 * @author yanrui
 */
public class TeamAddResponse implements Result {

	String teamId;

	public TeamAddResponse() {

	}

	public TeamAddResponse(String id) {

	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}



}
