/**
 * 
 */
package com.chinarewards.gwt.elt.client.orderHistory.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.order.model.OrderSearchVo;
import com.chinarewards.gwt.elt.client.support.UserSession;

/**
 * @author lw
 * @since 2012年1月20日 19:00:40
 */
public class SearchOrderHistoryRequest implements Action<SearchOrderHistoryResponse> {

	private OrderSearchVo orderSearchVo;
	private UserSession userSession;
	

	public SearchOrderHistoryRequest() {
	}

	public SearchOrderHistoryRequest(OrderSearchVo orderSearchVo,UserSession userSession) {
		this.orderSearchVo = orderSearchVo;
		this.userSession = userSession;
	}

	public OrderSearchVo getOrderSearchVo() {
		return orderSearchVo;
	}

	public void setOrderSearchVo(OrderSearchVo orderSearchVo) {
		this.orderSearchVo = orderSearchVo;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	

}
