package com.chinarewards.gwt.elt.client.department.model;

import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.chinarewards.gwt.elt.model.SortingDetailClient;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author yanrui
 */
public class DepartmentCriteria implements IsSerializable {
	public DepartmentCriteria() {
	}

	public static enum DepartmentStatus {

		/* 上架 */
		SHELVES("上架"),

		/* 下架 */
		SHELF("下架");

		private final String displayName;

		DepartmentStatus(String displayName) {
			this.displayName = displayName;
		}

		public String getDisplayName() {
			return displayName;
		}

	}

	private PaginationDetailClient pagination;

	private SortingDetailClient sorting;

	private String id;

	private String name;

	public DepartmentStatus getStatus() {
		return status;
	}

	public void setStatus(DepartmentStatus status) {
		this.status = status;
	}

	private DepartmentStatus status;

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

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
