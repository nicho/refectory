package com.chinarewards.gwt.elt.client.rewards.model;

import java.io.Serializable;

public class RewardsTypeClient implements Serializable,
		Comparable<RewardsTypeClient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6224947535960236129L;

	private String id;

	private String name;

	public RewardsTypeClient() {

	}

	public RewardsTypeClient(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	@Override
	public String toString() {
	
		return this.name;
	}

	@Override
	public int compareTo(RewardsTypeClient o) {
		return o == null ? -1 : o.getId().compareTo(id);
	}

}
