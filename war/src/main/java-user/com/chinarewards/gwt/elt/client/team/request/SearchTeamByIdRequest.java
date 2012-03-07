/**
 * 
 */
package com.chinarewards.gwt.elt.client.team.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * @author lw
 */
public class SearchTeamByIdRequest implements
		Action<SearchTeamByIdResponse> {

	private String id;
   
	public SearchTeamByIdRequest() {
	}

	public SearchTeamByIdRequest(String id) {
		this.id = id;
		
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
