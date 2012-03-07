package com.chinarewards.gwt.elt.client.rewards.model;

import java.io.Serializable;

import com.chinarewards.gwt.elt.client.chooseOrganization.model.OrganSearchCriteria.OrganType;

public class OrganicationClient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1198342894580577960L;

	protected String id;

	protected String name;
	protected OrganType type;

	public OrganicationClient() {

	}

	public OrganicationClient(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public OrganType getType() {
		return type;
	}

	public void setType(OrganType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "OrganicationClient [id=" + id + ", name=" + name + "]";
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

}
