/**
 * 
 */
package com.chinarewards.gwt.elt.client.dishesTypeList.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.dishesTypeList.model.DishesTypeListCriteria;
import com.chinarewards.gwt.elt.client.support.UserSession;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class SearchDishesTypeListRequest implements Action<SearchDishesTypeListResponse> {

	private DishesTypeListCriteria criteria;
	private UserSession session;


	public SearchDishesTypeListRequest() {
	}

	public DishesTypeListCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(DishesTypeListCriteria criteria) {
		this.criteria = criteria;
	}

	public UserSession getSession() {
		return session;
	}

	public void setSession(UserSession session) {
		this.session = session;
	}

	/**
	 * 
	 * @param DishesTypeListVo
	 */
	public SearchDishesTypeListRequest(DishesTypeListCriteria criteria,UserSession session) {
		this.criteria = criteria;
		this.session=session;
	}


}
