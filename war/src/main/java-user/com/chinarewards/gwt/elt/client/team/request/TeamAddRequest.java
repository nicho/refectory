/**
 * 
 */
package com.chinarewards.gwt.elt.client.team.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.gift.model.GiftVo;
import com.chinarewards.gwt.elt.client.support.UserSession;
import com.chinarewards.gwt.elt.client.team.model.TeamVo;

/**
 * An action which perform request to search user.
 * 
 * @author lw
 */
public class TeamAddRequest implements Action<TeamAddResponse> {

	
	private TeamVo teamVo;
	private UserSession userSession;

	public TeamAddRequest(TeamVo teamVo, UserSession userSession) {
		this.teamVo = teamVo;
		this.userSession = userSession;
	}

	
	public TeamVo getTeamVo() {
		return teamVo;
	}


	public void setTeamVo(TeamVo teamVo) {
		this.teamVo = teamVo;
	}


	public TeamAddRequest() {
	}


	

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	

}
