package com.chinarewards.elt.model.vo;

import java.io.Serializable;
import java.util.List;

import com.chinarewards.elt.model.common.PaginationDetail;
import com.chinarewards.elt.model.common.SortingDetail;
import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.domain.org.Members;
public class TeamListVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The paging detail, contains the info it requires.
	 */
	private PaginationDetail paginationDetail;

	/**
	 * Sorting detail.
	 */
	private SortingDetail sortingDetail;
	private String id;
	private String corpid ;
	private String departId;

	private String name; // 组名
	private String code;
	private String description;
	private List<Members>  membersList;
	private List<Members>  managersList;
	public List<Members> getManagersList() {
		return managersList;
	}

	public void setManagersList(List<Members> managersList) {
		this.managersList = managersList;
	}

	private String manager;
	private int people;
	public int getPeople() {
		return people;
	}

	public void setPeople(int people) {
		this.people = people;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getCorpid() {
		return corpid;
	}

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	public void setCorpid(String corpid) {
		this.corpid = corpid;
	}
	public TeamListVo() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public List<Members> getMembersList() {
		return membersList;
	}

	public void setMembersList(List<Members> membersList) {
		this.membersList = membersList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PaginationDetail getPaginationDetail() {
		return paginationDetail;
	}

	public void setPaginationDetail(PaginationDetail paginationDetail) {
		this.paginationDetail = paginationDetail;
	}

	public SortingDetail getSortingDetail() {
		return sortingDetail;
	}

	public void setSortingDetail(SortingDetail sortingDetail) {
		this.sortingDetail = sortingDetail;
	}

}