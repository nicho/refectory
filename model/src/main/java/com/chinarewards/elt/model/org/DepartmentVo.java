package com.chinarewards.elt.model.org;

/**
 * The model to save or update entity Department.
 * 
 * @author yanxin
 * @since 1.0
 */
public class DepartmentVo {
	
	private String id;

	private String name;

	private String description;

	private String parentId;

	/**
	 * When {@link #parentId} is null, you must give the {@link #corporationId}
	 */
	private String corporationId;
	
	
	

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}
}
