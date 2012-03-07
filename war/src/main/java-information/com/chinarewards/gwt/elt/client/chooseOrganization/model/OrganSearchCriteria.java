package com.chinarewards.gwt.elt.client.chooseOrganization.model;

import java.io.Serializable;

import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.chinarewards.gwt.elt.model.SortingDetailClient;

public class OrganSearchCriteria implements Serializable {
	public enum OrganType {

		STAFF("员工"),

		DEPT("部门"), GROUP("小组"), ORG("机构");

		private final String displayName;

		OrganType(String displayName) {
			this.displayName = displayName;
		}

		public String getDisplayName() {
			return displayName;
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2509102810254544279L;

	private String key;
	private OrganType organType;

	private PaginationDetailClient pagination;

	private SortingDetailClient sorting;

	public OrganType getOrganType() {
		return organType;
	}

	public void setOrganType(OrganType organType) {
		this.organType = organType;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public PaginationDetailClient getPagination() {
		return pagination;
	}

	public void setPagination(PaginationDetailClient pagination) {
		this.pagination = pagination;
	}

	public SortingDetailClient getSorting() {
		return sorting;
	}

	public void setSorting(SortingDetailClient sorting) {
		this.sorting = sorting;
	}

}
