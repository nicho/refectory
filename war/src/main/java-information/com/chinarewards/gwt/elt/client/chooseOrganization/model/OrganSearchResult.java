package com.chinarewards.gwt.elt.client.chooseOrganization.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;

public class OrganSearchResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -412350825877288258L;
	private List<OrganicationClient> result = new ArrayList<OrganicationClient>();
	private int total;

	public List<OrganicationClient> getResult() {
		return result;
	}

	public void setResult(List<OrganicationClient> result) {
		this.result = result;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
