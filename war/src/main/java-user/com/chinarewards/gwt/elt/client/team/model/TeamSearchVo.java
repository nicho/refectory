package com.chinarewards.gwt.elt.client.team.model;

import java.io.Serializable;
import java.util.Date;

import com.chinarewards.gwt.elt.client.gift.model.GiftClient;
import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.chinarewards.gwt.elt.model.SortingDetailClient;


public class TeamSearchVo implements Serializable, Comparable<TeamSearchVo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4934837755724342679L;

	public TeamSearchVo() {
	}
	private PaginationDetailClient pagination;

	private SortingDetailClient sorting;
	private String  id;
	
	private String name; // 组名
	private String code;
	private String description;
	private String manager;
	private int people;
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public int getPeople() {
		return people;
	}
	public void setPeople(int people) {
		this.people = people;
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
	@Override
	public int compareTo(TeamSearchVo o) {
		return o == null ? -1 : o.getId().compareTo(id);
	}
	
}
