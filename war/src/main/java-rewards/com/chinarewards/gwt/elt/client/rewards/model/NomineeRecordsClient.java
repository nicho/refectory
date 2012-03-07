package com.chinarewards.gwt.elt.client.rewards.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class NomineeRecordsClient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 537749985727463581L;

	private String id;

	private Date createdAt;

	private String reason;

	private List<NomineeModelClient> nominees;

	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public List<NomineeModelClient> getNominees() {
		return nominees;
	}

	public void setNominees(List<NomineeModelClient> nominees) {
		this.nominees = nominees;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
