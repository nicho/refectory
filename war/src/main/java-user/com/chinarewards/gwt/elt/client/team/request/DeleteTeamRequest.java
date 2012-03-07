/**
 * 
 */
package com.chinarewards.gwt.elt.client.team.request;

import com.chinarewards.gwt.elt.client.team.request.DeleteTeamResponse;
import com.chinarewards.gwt.elt.client.support.UserSession;

import net.customware.gwt.dispatch.shared.Action;

/**
 * @author lw
 */
public class DeleteTeamRequest implements Action<DeleteTeamResponse> {

	private String teamId;
	private UserSession userSession;

	public DeleteTeamRequest() {
	}

	public DeleteTeamRequest(String teamId, UserSession userSession) {
		this.teamId = teamId;

		this.userSession = userSession;

	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	

}
