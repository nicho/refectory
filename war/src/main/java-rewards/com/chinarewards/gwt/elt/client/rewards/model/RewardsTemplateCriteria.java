/**
 * 
 */
package com.chinarewards.gwt.elt.client.rewards.model;

import java.io.Serializable;

import com.chinarewards.gwt.elt.model.BaseSearchCriteria;

/**
 * @author sunhongliang
 * @since 0.2.0 2010-12-16
 */
public class RewardsTemplateCriteria extends BaseSearchCriteria implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8453586159948226529L;

	private String id;

	private String name;

	private String typeId;

	private String typeName;

	private String definition;

	private String standard;

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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

	@Override
	public String toString() {
		return "RewardsTemplateCriteria [id=" + id + ", name=" + name
				+ ", typeId=" + typeId + ", typeName=" + typeName
				+ ", definition=" + definition + ", standard=" + standard
				+ ", corporationId=" + corporationId + ",sortingDetail="
				+ getSorting() + ",paginationDetail=" + getPagination() + "]";
	}

}
