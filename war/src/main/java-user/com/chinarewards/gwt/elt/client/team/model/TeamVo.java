package com.chinarewards.gwt.elt.client.team.model;

import java.io.Serializable;
import java.util.Date;

import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient;

public class TeamVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  id;
	private String name; // 组名
	private String code;
	private String description;
	/** 负责人信息 **/
	private ParticipateInfoClient fzInfo;
	/** 成员信息 **/
	private ParticipateInfoClient memberInfo;
	public ParticipateInfoClient getFzInfo() {
		return fzInfo;
	}
	public void setFzInfo(ParticipateInfoClient fzInfo) {
		this.fzInfo = fzInfo;
	}
		public ParticipateInfoClient getMemberInfo() {
		return memberInfo;
	}
	public void setMemberInfo(ParticipateInfoClient memberInfo) {
		this.memberInfo = memberInfo;
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

	
}
