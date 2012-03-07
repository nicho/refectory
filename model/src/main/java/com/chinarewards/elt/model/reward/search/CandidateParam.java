package com.chinarewards.elt.model.reward.search;

public class CandidateParam {
	private String id;
	private String staffid;

	private String name;
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
	public String getStaffid() {
		return staffid;
	}
	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}
}
