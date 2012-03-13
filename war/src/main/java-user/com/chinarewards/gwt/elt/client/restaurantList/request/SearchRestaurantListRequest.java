/**
 * 
 */
package com.chinarewards.gwt.elt.client.restaurantList.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.restaurantList.model.RestaurantListCriteria;
import com.chinarewards.gwt.elt.client.support.UserSession;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class SearchRestaurantListRequest implements Action<SearchRestaurantListResponse> {

	private RestaurantListCriteria criteria;
	private UserSession session;


	public SearchRestaurantListRequest() {
	}

	public RestaurantListCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(RestaurantListCriteria criteria) {
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
	 * @param RestaurantListVo
	 */
	public SearchRestaurantListRequest(RestaurantListCriteria criteria,UserSession session) {
		this.criteria = criteria;
		this.session=session;
	}


}
