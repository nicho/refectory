package com.chinarewards.elt.model.org.search;

import java.io.Serializable;
import java.util.List;

public class TeamParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  id;
	private String name; // 组名
	private String code;
	private String description;
	private List<String> fzIds;
	private List<String> membersIds;
	public List<String> getFzIds() {
		return fzIds;
	}
	public void setFzIds(List<String> fzIds) {
		this.fzIds = fzIds;
	}
	public List<String> getMembersIds() {
		return membersIds;
	}
	public void setMembersIds(List<String> membersIds) {
		this.membersIds = membersIds;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
}
