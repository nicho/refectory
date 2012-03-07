/**
 * 
 */
package com.chinarewards.gwt.elt.client.team.request;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.order.model.OrderViewClient;
import com.chinarewards.gwt.elt.client.team.model.TeamVo;

/**
 * @author lw
 * @since
 */
public class SearchTeamByIdResponse implements Result {

	private TeamVo teamVoClient;

	public SearchTeamByIdResponse() {

	}

	public SearchTeamByIdResponse(TeamVo teamVoClient) {
		this.teamVoClient = teamVoClient;
	}

	public TeamVo getTeamVoClient() {
		return teamVoClient;
	}

	public void setTeamVoClient(TeamVo teamVoClient) {
		this.teamVoClient = teamVoClient;
	}

	
}
