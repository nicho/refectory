package com.chinarewards.gwt.elt.model.nominate;

import java.io.Serializable;

public class NominateCheckBox implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private String staffId;
	private String candidateId;
	private String staffName;
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}	

}
