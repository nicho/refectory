package com.chinarewards.gwt.elt.client.department.model;

import java.io.Serializable;

import com.chinarewards.gwt.elt.client.department.model.DepartmentCriteria.DepartmentStatus;

public class DepartmentClient implements Serializable, Comparable<DepartmentClient> {

	private static final long serialVersionUID = 1L;

	public DepartmentClient() {
	}

	

	private String id;
	private String ids;
	private String name;
	private DepartmentStatus status;// 状态
	
	private String thisAction;
	

	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public DepartmentStatus getStatus() {
		return status;
	}

	public void setStatus(DepartmentStatus status) {
		this.status = status;
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
	public int compareTo(DepartmentClient o) {
		return o == null ? -1 : o.getId().compareTo(id);
	}

	public String getThisAction() {
		return thisAction;
	}

	public void setThisAction(String thisAction) {
		this.thisAction = thisAction;
	}

}
