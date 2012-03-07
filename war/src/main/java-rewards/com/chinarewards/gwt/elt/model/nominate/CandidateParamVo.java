package com.chinarewards.gwt.elt.model.nominate;

import java.io.Serializable;

public class CandidateParamVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String staffid;
	public String getStaffid() {
		return staffid;
	}
	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}
	/**
	 * 提名次数
	 */
	private int nominateCount;
	public int getNominateCount() {
		return nominateCount;
	}
	public void setNominateCount(int nominateCount) {
		this.nominateCount = nominateCount;
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
