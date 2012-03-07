package com.chinarewards.gwt.elt.client.register.model;

import java.io.Serializable;

public class OrgInitVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String corpId;//企业ID
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	private int corpInit; //0是没有初始化注册企业，1是已有注册
	private int hrInit;   //0没有初始化HR账号，1是已有HR账号
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCorpInit() {
		return corpInit;
	}
	public void setCorpInit(int corpInit) {
		this.corpInit = corpInit;
	}
	public int getHrInit() {
		return hrInit;
	}
	public void setHrInit(int hrInit) {
		this.hrInit = hrInit;
	}
	public OrgInitVo() {
	}

	
	

}
