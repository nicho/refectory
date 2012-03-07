/**
 * 
 */
package com.chinarewards.gwt.elt.client.team.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.team.model.TeamSearchVo;
import com.chinarewards.gwt.elt.client.support.UserSession;

/**
 * @author lw
 * @since 2012年2月15日 19:00:40
 */
public class SearchTeamRequest implements Action<SearchTeamResponse> {

	private TeamSearchVo teamSearchVo;
	private UserSession userSession;
	

	public SearchTeamRequest() {
	}

	public SearchTeamRequest(TeamSearchVo teamSearchVo,UserSession userSession) {
		this.teamSearchVo = teamSearchVo;
		this.userSession = userSession;
	}

	public TeamSearchVo getTeamSearchVo() {
		return teamSearchVo;
	}

	public void setTeamSearchVo(TeamSearchVo teamSearchVo) {
		this.teamSearchVo = teamSearchVo;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	

}
