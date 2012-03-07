package com.chinarewards.gwt.elt.client.breadCrumbs.ui;

import java.util.List;

import com.chinarewards.gwt.elt.client.breadCrumbs.model.MenuBreadVo;


public interface BreadCrumbsMenu {

	void addBreadCrumbsItem(String name,String url);
	void addBreadCrumbsItemTop(String name,String url);
	void cleanBreadCrumbsItem();
	void cleanBreadCrumbsItemTop();
	void cleanBreadCrumbsItemAll();
	void cleanChildName();
	List<MenuBreadVo> getBreadCrumbsItem();
	List<MenuBreadVo> getHistoryBreadCrumbsItem();
	void setChildName(String name);

}
