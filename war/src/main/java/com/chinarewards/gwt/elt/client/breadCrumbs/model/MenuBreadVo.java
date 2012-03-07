package com.chinarewards.gwt.elt.client.breadCrumbs.model;

import java.io.Serializable;

public class MenuBreadVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String menuName;
	String menuUrl;
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

}