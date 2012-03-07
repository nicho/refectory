package com.chinarewards.gwt.elt.client.rewards.model;

import java.io.Serializable;

/**
 * 
 * @author yanxin
 * @since 0.2.0 2011-01-22
 */
public class StaffLevelClient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3477348246173451844L;

	public StaffLevelClient() {

	}

	public StaffLevelClient(String id, String name) {
		this.id = id;
		this.name = name;
	}

	private String id;
	private String name;

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
